CREATE OR REPLACE FUNCTION FN_GET_RESIWHLCN (
    /**
     * =============================================================
     * name     : 피해지역 거주기간 계산 함수
     * author   : LSH
     * date     : 2023.02.20
     * modified : 2023.02.24 전출년도는 제외하도록 수정
     * -------------------------------------------------------------
     * @param     vi_sufrer_no 피해자번호
     * =============================================================
     */
    vi_sufrer_no IN NUMBER
)
RETURN NUMBER
IS
    vo_cnt NUMBER;
BEGIN
    vo_cnt := 0;
    
    WITH T_BIZ AS (
        SELECT T1.BIZ_AREA_CD          AS BIZ_AREA_CD
             , T2.SUFRER_NO            AS SUFRER_NO
             , T1.AFFC_SCOPE_DSTNC     AS AFFC_DSTNC
             , T1.FRST_POLLUTN_OCRN_YR AS FRST_YR
             , T1.LAST_POLLUTN_OCRN_YR AS LAST_YR
          FROM (SELECT O.BIZ_AREA_CD
                     , O.AFFC_SCOPE_DSTNC
                     , O.FRST_POLLUTN_OCRN_YR
                     , O.LAST_POLLUTN_OCRN_YR
                     , ROW_NUMBER() OVER(PARTITION BY O.BIZ_AREA_CD ORDER BY O.BIZ_ODER DESC) AS RIDX
                  FROM TB_BIZ_ODER O
               ) T1,
               (SELECT BIZ_AREA_CD
                     , SUFRER_NO
                     , ROW_NUMBER() OVER(PARTITION BY SUFRER_NO ORDER BY RCPT_YMD DESC) AS RIDX
                  FROM TB_RELIEF
                 WHERE BIZ_AREA_CD IS NOT NULL
                   AND RCPT_YMD    IS NOT NULL
                   AND SUFRER_NO = vi_sufrer_no
               ) T2
         WHERE T1.RIDX = 1
           AND T2.RIDX = 1
           AND T1.BIZ_AREA_CD = T2.BIZ_AREA_CD
    )
    SELECT (SELECT COUNT(DISTINCT Y.CU_YEAR)
              FROM (SELECT TO_NUMBER(B.FRST_YR) + LEVEL - 1 AS CU_YEAR 
                      FROM DUAL 
                   CONNECT BY LEVEL < (TO_NUMBER(B.LAST_YR) - TO_NUMBER(B.FRST_YR) + 2)
                   ) Y
                 , (SELECT DISTINCT
                           SUFRER_NO
                         , GAP_DSTNC
                         , ABSTRCT_ADDR
                         , DCLR_YR AS ST_YEAR
                         , NVL(LEAD(DCLR_YR) OVER (ORDER BY SUFRER_NO, DCLR_YR),TO_CHAR(SYSDATE,'YYYY')) AS EN_YEAR
                      FROM TB_ABSTRCT_ADDR
                     WHERE SUFRER_NO = B.SUFRER_NO
                   ) A
             WHERE A.GAP_DSTNC <= B.AFFC_DSTNC
               AND (   (Y.CU_YEAR = TO_NUMBER(A.ST_YEAR)) 
                    OR (Y.CU_YEAR BETWEEN TO_NUMBER(A.ST_YEAR) AND TO_NUMBER(A.EN_YEAR) - 1) 
                   )
           ) INTO vo_cnt
      FROM T_BIZ     B 
    ;
    RETURN vo_cnt;
END;