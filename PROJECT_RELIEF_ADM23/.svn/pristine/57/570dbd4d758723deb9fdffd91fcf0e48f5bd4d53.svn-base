/*
 *  운영서버 반영완료 (2023/02/22)
 */
/*
/*
 * ****************** 엑셀작업으로 등록처리..
 * 
 * 이 파일은 참조용으로 활용.
 * 
 * 
 * > 요양생활수당평가등급 -> 정보등록.
 * > 본조사 테이블 > 피해등급 등록 (2등급)?
 * > 본조사 테이블 > 지급기간, 소급기간 등록 필요. (2020.01 ~ 2023.02)
 * > 
 * > 소급금액, 소급연월, 지급연월
 * >    - 8,031,284 [2020/01 ~ 2023/02], 소급기간 : { 2020/01 ~ 2020/07 }
 * > 소급기간 (2020/01 ~ 2020/07 [7개월]) 소급지급일자 2020/07/31, 소급금액 8,031,289
 * > 2020 : 1,147,327,  2021 : 1,184,177
 * > 본조사 2번? 2020-06-16(3년), 2021-11-30(5년)
 * > 본조사 테이블 > 지급기간 (5년),  ( 2020.01 ~ 2021.09) 까지 등록된 데이터
 * > 본조사 테이블 > 피해등급 등록 (2등급 - 노화복, 구재란)
 */
        
-- 요양생활수당 지급정보 조회
SELECT B.BIZ_AREA_CD                AS "지역코드"
     , B.BIZ_ODER                   AS "사업차수"
     , B.EXMN_ODER                  AS "조사차수"
     , B.APLY_NO                    AS "신청번호"
     , B.GIVE_YR                    AS "지급연도"
     , B.GIVE_MM                    AS "지급월"
     , B.GIVE_YMD                   AS "지급일자"
     , A.RCPER_LVLH_ALLWNC_AMT      AS "지급금액"
     , ''                           AS "지급구분코드"
     , B.GIVE_YN                    AS "지급여부"
  FROM TB_RCPER_LVLH_MNG A
     , TB_RCPER_LVLH_DTLS B
 WHERE A.BIZ_AREA_CD    = B.BIZ_AREA_CD(+)
   AND A.BIZ_ODER       = B.BIZ_ODER(+)
   AND A.EXMN_ODER      = B.EXMN_ODER(+)
   AND A.APLY_NO        = B.APLY_NO(+)  
   AND A.GIVE_YR        = B.GIVE_YR(+)
 ORDER BY B.APLY_NO, B.GIVE_YR, B.GIVE_MM   

 
 /*
  * 본조사 - 피해등급 및 소급기간 Update
  * R190000032(구재란) - 2등급, 202001~202007
  * R190000156(노화복) - 2등급, 202001~202007
  */
UPDATE TB_MNSVY
   SET LAST_DMGE_GRD_CD = '2'
     , RTROACT_BGNG_YM  = '202001'
     , RTROACT_END_YM   = '202007'
 WHERE 0 = 0
   AND APLY_NO IN ('R190000032', 'R190000156')
   AND EXMN_ODER = 8; 
   

 
=========================================================================================
-- 수정소스 (관리자)
- mapper-AdmMain.xml
- mapper-Mnsvy.xml
- mapper-RcperLvlh.xml
- mapper-ReamtPay.xml
- mapper_Relief.xml
- mapper-Statistics.xml
                      
-- 수정소스 (사용자)
- mapper-Mnsvy.xml
- mapper-RcperLvlh.xml
- mapper-ReamtPay.xml
- mapper-Relief.xml
- mapper-Statistics.xml
*/

