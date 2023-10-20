/*
 *  운영서버 반영완료 (2023/02/22)
 */
/*
/* 사업차수 정보 수정 */
-- 서천
UPDATE TB_BIZ_ODER
   SET AFFC_SCOPE_CN = '4Km'
 WHERE BIZ_AREA_CD = 'A0002'
   AND BIZ_ODER = 2;
   
-- 김포
UPDATE TB_BIZ_ODER
   SET AFFC_SCOPE_CN = '1Km'
 WHERE BIZ_AREA_CD = 'A0003'
   AND BIZ_ODER = 2;   
   
-- 대구 (2차 데이터 없음) 신규 등록
INSERT INTO TB_BIZ_ODER (BIZ_AREA_CD, BIZ_ODER, BIZ_ODER_NM, AFFC_SCOPE_CN, EXPSR_WHL_CN, BIZ_USE_YN, RGTR_NO, REG_YMD)
VALUES ('A0001', 2, '2차', '1Km', '1981년 ~ 2021년', 'Y', 'system', sysdate);   
*/