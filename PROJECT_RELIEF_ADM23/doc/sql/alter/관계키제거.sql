/*
 * 관계키 제거
 */

/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
-- 2023.01.02 TB_RCPER_LVLH_MNG(요양생활수당관리) 테이블 제외에 따른 관련 CONTRAINT 제거
ALTER TABLE TB_RCPER_LVLH_DTLS DROP CONSTRAINT FK_RCPER_LVLH_DTLS_BIZ_AREA_CD CASCADE;
*/
