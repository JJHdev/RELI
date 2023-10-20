/*
 *  운영서버 반영완료 (2023/02/22)
 */
/*
-- 사망일자 업데이트 (기존 본조사에 등록된 사망일자)
UPDATE TB_RELIEF A
   SET DTH_YMD = (SELECT MAX(DTH_YMD)
                    FROM TB_MNSVY  
                   WHERE APLY_NO = A.APLY_NO
                   GROUP BY APLY_NO
                 );
                 
COMMIT;
*/