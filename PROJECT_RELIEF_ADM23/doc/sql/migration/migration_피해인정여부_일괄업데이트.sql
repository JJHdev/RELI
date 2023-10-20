/*
-- 피해인정여부 일괄 업데이트
UPDATE TB_RELIEF A
   SET DMGE_RCOGN_YN = (SELECT CASE WHEN MIN(DLTNC_RSLT_CD) = 'R1' 
                                    THEN 'Y'
                                    ELSE 'N'
                               END
                          FROM TB_PRPT_EXMN B
                         WHERE APLY_NO = A.APLY_NO
                         GROUP BY APLY_NO )
 WHERE DMGE_RCOGN_YN IS NULL   
*/