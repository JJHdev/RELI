-- 2023.10.19 위원회 사용자 샘플 등록
INSERT INTO TB_USER (USER_NO, PSWD, USER_ID, EML_ADDR, USER_NM, BRDT, MBTEL_NO, TELNO, JOIN_YMD, ZIP, ADDR, DADDR, SXDST, MBTEL_RCPTN_AGRE_YN, USE_STUS_CD, RGTR_NO, REG_YMD) VALUES 
(SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr01', 'cmtusr01@test.com','홍길동', '19770101', '01012345678', '0212345678', '20231019', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE);

INSERT INTO SYS_ROLE_USER 
(ROLE_ID, USER_NO, RGTR_NO, REG_YMD) VALUES 
('ROLE_AUTH_CMT','100139','system',SYSDATE);

-- 2023.10.19 16:00 실행완료 RELIEF03


-- 2023.10.25 위원회 안건 샘플 등록
INSERT INTO TB_CMIT_DMGE_AGND
 (RGTR_NO, REG_YMD, CMIT_MNG_NO, AGND_NO, AGND_SE_CD, AGND_NM) VALUES 
 ('system', SYSDATE, 7, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 7, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 7, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
;
UPDATE TB_CMIT_DMGE SET PRGRE_STUS_CD = 'C1' WHERE CMIT_MNG_NO = '7';

-- 2023.10.26 위원회 안건 샘플파일 등록
INSERT INTO TB_CMIT_DMGE_FILE
 (SN, CMIT_MNG_NO, AGND_NO, FILE_PATH, STRG_FILE_NM, FILE_NM, FILE_SZ, DOWN_CNT, DEL_YN, RGTR_NO, REG_YMD) VALUES 
 (SEQ_CMIT_DMGE_FILE.NEXTVAL, 7, 1, '/cmit', 'CMIT_SAMPLE101.hwp', '구제급여 선지급 신청인(101명) 구제급여 지급요건.hwp', NULL, 0, 'N', 'system', SYSDATE)
,(SEQ_CMIT_DMGE_FILE.NEXTVAL, 7, 2, '/cmit', 'CMIT_SAMPLE201.hwp', '환경오염 피해구제(서천) 101명 예비조사보고서.hwp', NULL, 0, 'N', 'system', SYSDATE)
,(SEQ_CMIT_DMGE_FILE.NEXTVAL, 7, 2, '/cmit', 'CMIT_SAMPLE202.hwp', '개인별 인과관계 총괄표(2023, 101명).hwp', NULL, 0, 'N', 'system', SYSDATE)
,(SEQ_CMIT_DMGE_FILE.NEXTVAL, 7, 3, '/cmit', 'CMIT_SAMPLE301.hwp', '환경오염 피해인정자(77명) 피해등급.hwp', NULL, 0, 'N', 'system', SYSDATE)
;

INSERT INTO TB_CMIT_DMGE_AGND
 (RGTR_NO, REG_YMD, CMIT_MNG_NO, AGND_NO, AGND_SE_CD, AGND_NM) VALUES 
 ('system', SYSDATE, 2, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 2, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 2, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
;
INSERT INTO TB_CMIT_DMGE_FILE
 (SN, CMIT_MNG_NO, AGND_NO, FILE_PATH, STRG_FILE_NM, FILE_NM, FILE_SZ, DOWN_CNT, DEL_YN, RGTR_NO, REG_YMD) VALUES 
 (SEQ_CMIT_DMGE_FILE.NEXTVAL, 2, 1, '/cmit', 'CMIT_A101.hwp', '구제급여 선지급 신청인(101명) 구제급여 지급요건.hwp', NULL, 0, 'N', 'system', SYSDATE)
,(SEQ_CMIT_DMGE_FILE.NEXTVAL, 2, 2, '/cmit', 'CMIT_A201.hwp', '환경오염 피해구제(서천) 101명 예비조사보고서.hwp', NULL, 0, 'N', 'system', SYSDATE)
,(SEQ_CMIT_DMGE_FILE.NEXTVAL, 2, 2, '/cmit', 'CMIT_A202.hwp', '개인별 인과관계 총괄표(2023, 101명).hwp', NULL, 0, 'N', 'system', SYSDATE)
,(SEQ_CMIT_DMGE_FILE.NEXTVAL, 2, 3, '/cmit', 'CMIT_A301.hwp', '환경오염 피해인정자(77명) 피해등급.hwp', NULL, 0, 'N', 'system', SYSDATE)
;


-- 1, '이영수', '미래기술단', 'CF2', '선임연구원', '01023455678', null, 'lee@test.com', '100139', 'sign1698051778087.png'
-- C1 CT045 전문위원회 
-- C2 CT045 심의위원회 
-- C3 CT045 심사위원회 

-- 2023.10.27 위원회 샘플데이터 등록
INSERT INTO TB_MFCMM_TENURE 
(RGTR_NO, REG_YMD, TENURE_NO, MFCMM_NO, CMIT_SE_CD, BIZ_AREA_CD, TENURE_ODER, TENURE_BGNG_YMD, TENURE_END_YMD ) VALUES 
 ('system', SYSDATE, SEQ_MFCMM_TENURE.NEXTVAL, 1, 'C1', 'A0001', '샘플1차', '20230101', '20231130')
,('system', SYSDATE, SEQ_MFCMM_TENURE.NEXTVAL, 1, 'C2',    NULL, '샘플2차', '20230301', '20231130')
,('system', SYSDATE, SEQ_MFCMM_TENURE.NEXTVAL, 1, 'C3',    NULL, '샘플3차', '20230501', '20231231')
,('system', SYSDATE, SEQ_MFCMM_TENURE.NEXTVAL, 1, 'C3',    NULL, '샘플4차', '20230501', '20231231')
,('system', SYSDATE, SEQ_MFCMM_TENURE.NEXTVAL, 1, 'C2',    NULL, '샘플5차', '20230501', '20231231')
;

SELECT * FROM TB_MFCMM_TENURE WHERE MFCMM_NO = 1
39	1	C1	A0001	샘플1차	20230101	20231130	system	2023/10/27	<NULL>	<NULL>
40	1	C2	<NULL>	샘플2차	20230301	20231130	system	2023/10/27	<NULL>	<NULL>
41	1	C3	<NULL>	샘플3차	20230501	20231231	system	2023/10/27	<NULL>	<NULL>
42	1	C3	<NULL>	샘플4차	20230501	20231231	system	2023/10/27	<NULL>	<NULL>

INSERT INTO TB_CMIT_DMGE 
(RGTR_NO, REG_YMD, CMIT_MNG_NO, BIZ_AREA_CD, DMGE_SE_CD, CMIT_SE_CD, CMIT_ODER, OPMT_BGNG_YMD, OPMT_END_YMD, PRGRE_STUS_CD ) VALUES 
 ('system', SYSDATE, SEQ_CMIT_MNG.NEXTVAL, 'A0001', 'D1', 'C1', '샘플1차', '20230101', '20231130', '01')
,('system', SYSDATE, SEQ_CMIT_MNG.NEXTVAL,    NULL, 'D1', 'C2', '샘플2차', '20230301', '20231130', '02')
,('system', SYSDATE, SEQ_CMIT_MNG.NEXTVAL,    NULL, 'D1', 'C3', '샘플3차', '20230501', '20231231', '03')
,('system', SYSDATE, SEQ_CMIT_MNG.NEXTVAL,    NULL, 'D1', 'C3', '샘플4차', '20230501', '20231231', '04')
,('system', SYSDATE, SEQ_CMIT_MNG.NEXTVAL,    NULL, 'D1', 'C2', '샘플5차', '20230501', '20231231', '03')
;
SELECT * FROM TB_CMIT_DMGE ORDER BY CMIT_MNG_NO DESC
16	<NULL>	D1	C3	<NULL>	샘플4차	<NULL>	20230501	20231231	system	2023/10/27	<NULL>	<NULL>	04
15	<NULL>	D1	C3	<NULL>	샘플3차	<NULL>	20230501	20231231	system	2023/10/27	<NULL>	<NULL>	03
14	<NULL>	D1	C2	<NULL>	샘플2차	<NULL>	20230301	20231130	system	2023/10/27	<NULL>	<NULL>	02
13	A0001	D1	C1	<NULL>	샘플1차	<NULL>	20230101	20231130	system	2023/10/27	<NULL>	<NULL>	01

INSERT INTO TB_CMIT_MNG 
(RGTR_NO, REG_YMD, CMIT_MNG_NO, TENURE_NO, CHARMN_YN ) VALUES 
 ('system', SYSDATE, 13, 39, 'N')
,('system', SYSDATE, 14, 40, 'N')
,('system', SYSDATE, 15, 41, 'Y')
,('system', SYSDATE, 16, 42, 'N')
,('system', SYSDATE, 17, 43, 'N')
;
INSERT INTO TB_CMIT_DMGE_AGND (RGTR_NO, REG_YMD, CMIT_MNG_NO, AGND_NO, AGND_SE_CD, AGND_NM) VALUES 
 ('system', SYSDATE, 13, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 13, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 13, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
,('system', SYSDATE, 14, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 14, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 14, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
,('system', SYSDATE, 15, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 15, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 15, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
,('system', SYSDATE, 16, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 16, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 16, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
,('system', SYSDATE, 17, 1, 'AD', '구제급여 선지급 신청인(101명)에 대한 구제급여 지급요건 적합여부 심의·의결')
,('system', SYSDATE, 17, 2, 'AD', '환경오염 피해인정자(13명)에 대한 구제급여(의료비) 지급 심의·의결')
,('system', SYSDATE, 17, 3, 'AD', '환경오염 피해인정자(77명)에 대한 피해등급 심의·의결')
;

-- 2023.11.01 위원회 사용자 샘플 등록
SELECT * FROM TB_MFCMM
1	이영수	미래기술단	CF2	선임연구원	01023455678	<NULL>	lee@test.com	100000	2023/01/12	<NULL>	2023/10/23	100139	sign1698051778087.png
2	김산수	한국환경공단	CF2	소장	<NULL>	<NULL>	<NULL>	100000	2023/01/12	<NULL>	<NULL>	<NULL>	<NULL>
3	박미술	한강유역환경청	CF1	서기관	<NULL>	<NULL>	<NULL>	100000	2023/01/12	<NULL>	<NULL>	<NULL>	<NULL>
4	홍길동	수도권대기환경청	CF2	본부장	<NULL>	<NULL>	<NULL>	100000	2023/01/12	<NULL>	<NULL>	<NULL>	<NULL>
5	김기동	한국산업안전기술단	CF3	단장	<NULL>	<NULL>	<NULL>	100000	2023/01/12	<NULL>	<NULL>	<NULL>	<NULL>
6	고길동	한국환경공단	CF4	소장	<NULL>	<NULL>	aaa@aaa.com	100000	2023/01/31	<NULL>	<NULL>	<NULL>	<NULL>
7	장병준	대한법률구조공단	CF3	과장	0548100001	<NULL>	jang@test.com	100000	2023/02/15	<NULL>	<NULL>	<NULL>	<NULL>

INSERT INTO TB_USER (USER_NO, PSWD, USER_ID, EML_ADDR, USER_NM, BRDT, MBTEL_NO, TELNO, JOIN_YMD, ZIP, ADDR, DADDR, SXDST, MBTEL_RCPTN_AGRE_YN, USE_STUS_CD, RGTR_NO, REG_YMD) VALUES 
 (SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr02', 'cmtusr02@test.com','김산수', '19770101', '01012345678', '0212345678', '20231101', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE)
,(SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr03', 'cmtusr03@test.com','박미술', '19770101', '01012345678', '0212345678', '20231101', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE)
,(SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr04', 'cmtusr04@test.com','홍길동', '19770101', '01012345678', '0212345678', '20231101', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE)
,(SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr05', 'cmtusr05@test.com','김기동', '19770101', '01012345678', '0212345678', '20231101', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE)
,(SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr06', 'cmtusr06@test.com','고길동', '19770101', '01012345678', '0212345678', '20231101', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE)
,(SEQ_USER.nextval, SCP.HASH_B64('73', '1234'), 'cmtusr07', 'cmtusr07@test.com','장병준', '19770101', '01012345678', '0212345678', '20231101', '10037', '경기도 대곶면 거물대리 오니산로 33', NULL, 'M','N','1','system',SYSDATE)
 ;
SELECT * FROM TB_USER WHERE JOIN_YMD = '20231101'
100143	cmtusr02	김산수	1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==	cmtusr02@test.com	0212345678	01012345678	19770101	10037	경기도 대곶면 거물대리 오니산로 33	<NULL>	M	<NULL>	<NULL>	<NULL>	20231101	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	N	1	<NULL>	system	2023/11/01	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>
100144	cmtusr03	박미술	1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==	cmtusr03@test.com	0212345678	01012345678	19770101	10037	경기도 대곶면 거물대리 오니산로 33	<NULL>	M	<NULL>	<NULL>	<NULL>	20231101	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	N	1	<NULL>	system	2023/11/01	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>
100145	cmtusr04	홍길동	1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==	cmtusr04@test.com	0212345678	01012345678	19770101	10037	경기도 대곶면 거물대리 오니산로 33	<NULL>	M	<NULL>	<NULL>	<NULL>	20231101	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	N	1	<NULL>	system	2023/11/01	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>
100146	cmtusr05	김기동	1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==	cmtusr05@test.com	0212345678	01012345678	19770101	10037	경기도 대곶면 거물대리 오니산로 33	<NULL>	M	<NULL>	<NULL>	<NULL>	20231101	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	N	1	<NULL>	system	2023/11/01	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>
100147	cmtusr06	고길동	1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==	cmtusr06@test.com	0212345678	01012345678	19770101	10037	경기도 대곶면 거물대리 오니산로 33	<NULL>	M	<NULL>	<NULL>	<NULL>	20231101	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	N	1	<NULL>	system	2023/11/01	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>
100148	cmtusr07	장병준	1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==	cmtusr07@test.com	0212345678	01012345678	19770101	10037	경기도 대곶면 거물대리 오니산로 33	<NULL>	M	<NULL>	<NULL>	<NULL>	20231101	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>	N	1	<NULL>	system	2023/11/01	<NULL>	<NULL>	<NULL>	<NULL>	<NULL>

INSERT INTO SYS_ROLE_USER 
(ROLE_ID, USER_NO, RGTR_NO, REG_YMD) VALUES 
 ('ROLE_AUTH_CMT','100143','system',SYSDATE)
,('ROLE_AUTH_CMT','100144','system',SYSDATE)
,('ROLE_AUTH_CMT','100145','system',SYSDATE)
,('ROLE_AUTH_CMT','100146','system',SYSDATE)
,('ROLE_AUTH_CMT','100147','system',SYSDATE)
,('ROLE_AUTH_CMT','100148','system',SYSDATE)
;

UPDATE TB_MFCMM SET USER_NO = '100143' WHERE MFCMM_NO = '2';
UPDATE TB_MFCMM SET USER_NO = '100144' WHERE MFCMM_NO = '3';
UPDATE TB_MFCMM SET USER_NO = '100145' WHERE MFCMM_NO = '4';
UPDATE TB_MFCMM SET USER_NO = '100146' WHERE MFCMM_NO = '5';
UPDATE TB_MFCMM SET USER_NO = '100147' WHERE MFCMM_NO = '6';
UPDATE TB_MFCMM SET USER_NO = '100148' WHERE MFCMM_NO = '7';

-- 2023.11.01 10:20 실행완료 RELIEF03

