/*
 *  운영서버 반영완료 (2023/02/22)
 */
/*
UPDATE TB_RELIEF R
   SET SYS_SE_CD = (
                        SELECT CASE WHEN SIGN_CN IS NOT NULL 
                                    THEN 'USR'
                                    ELSE 'ADM'
                               END
                          FROM TB_RELIEF A
                             , TB_IDNTFC B
                         WHERE A.SUFRER_NO = B.SUFRER_NO(+)
                           AND A.APLY_NO = R.APLY_NO
                   )
 WHERE SYS_SE_CD IS NULL                   
;
COMMIT;         
*/