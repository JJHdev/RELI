/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
/**
 * 신청서류 코드 등록 (살생물)
 * 
 * 1. SYS_CODE      : PP04 업무구분코드 등록 (code.sql)
 * 2. TB_PAPE_CODE  : 살생물 코드 등록
 * 3. TB_PAPE_MNG   : 살생물 데이터 등록
 */

-- 1. 신청서류관리 (업무구분코드 추가)
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('PP04', 'CT034', '살생물제품', null, null, 'Y', 'system', sysdate); 


-- 2. [ 살생물 - 공통 ] 
    --> 신청파일관리_살생물.xlsx 참조

	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30', 'NONE', '살생물제품 구제급여신청서류종류', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D301', 'D30', '공통', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30101', 'D301', '살생물 제품 사진 및 구입영수증', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30102', 'D301', '신청인의 주민등록 등본', 1, 2, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30103', 'D301', '신분증 사본', 1, 3, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30104', 'D301', '구제급여 신청서', 1, 4, '구제급여_지급_신청서.hwp', '/formfile', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30105', 'D301', '의무기록(살생물제품 피해내용 확인)', 1, 5, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30106', 'D301', '통장사본', 1, 6, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D302', 'D30', '진료비', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30201', 'D302', '의료비 내역서', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D303', 'D30', '장애일시 보상금', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30301', 'D303', '장애 진단서', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D304', 'D30', '사망일시 보상금', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30401', 'D304', '피해자 사망진단서', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D305', 'D30', '장례비', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30501', 'D305', '장례비 결재 영수증 및 계약서', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D306', 'D30', '미지급 진료비', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30601', 'D306', '의료비 내역서', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30602', 'D306', '신청인의 주민등록 등본', 1, 2, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D309', 'D30', '추가서류', 0, 0, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30901', 'D309', '구제급여지급 위임장', 1, 1, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30902', 'D309', '대리인 신분증 사본', 1, 2, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30903', 'D309', '가족관계증명서', 1, 3, '', '', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_CODE (PAPE_CD, UP_PAPE_CD, PAPE_NM, LIMT_CNT, CD_ORDR, FILE_NM, FILE_PATH, DOWN_TRGT_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30904', 'D309', '그밖의 기타서류', 10, 4, '', '', 'N', 'Y', 'system', sysdate);



-- 3. [ 살생물 - 서류관리 ] 
    -- > 신청파일관리_살생물.xlsx 참조

	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30101', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30102', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30103', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30104', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30105', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30106', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30201', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30301', 'R01', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30904', 'R01', 'PP04', 'N', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30101', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30104', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30105', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30106', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30201', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30301', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30901', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30902', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30903', 'R02', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30101', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30104', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30105', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30106', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30401', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30501', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30601', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30602', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30901', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30902', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);
	INSERT INTO TB_PAPE_MNG (PAPE_CD, APLY_SE_CD, PAPE_DTY_SE_CD, ESNTL_YN, USE_YN, RGTR_NO, REG_YMD) VALUES ('D30903', 'R03', 'PP04', 'Y', 'Y', 'system', sysdate);

*/