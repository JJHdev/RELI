/*
 *  운영서버 반영완료 (2022/12/07)
 */
/*
UPDATE TB_RELIEF A
   SET A.MNGR_REG_YN = (SELECT DECODE(SIGN(COUNT(*)), 1, 'Y', 'N')
                        FROM TB_USER U
                           , SYS_ROLE_USER R
                       WHERE U.USER_NO = R.USER_NO
                         AND U.USER_NO = A.RGTR_NO
                         AND R.ROLE_ID IN ('ROLE_AUTH_SYS', 'ROLE_AUTH_ADM', 'ROLE_AUTH_ADM2') )
 WHERE A.RGTR_NO <> 'system'
*/