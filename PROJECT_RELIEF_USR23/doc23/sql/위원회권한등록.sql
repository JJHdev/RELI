-- 2023.10.19 위원회 권한 / 메뉴 / 프로그램 등록
INSERT INTO SYS_ROLE 
(ROLE_ID, ROLE_NM, RGTR_NO, REG_YMD) VALUES 
('ROLE_AUTH_CMT','위원회사용자','system',SYSDATE);

INSERT INTO SYS_MENU 
(POPUP_YN, SYS_SE_CD, USE_YN, RGTR_NO, REG_YMD, MENU_ID, UP_MENU_ID, MENU_LVL, MENU_ORDR, TRGT_URL, MENU_NM ) values 
('N', 'USR', 'Y', 'system', SYSDATE, 'MU_USR_CMT'    , 'NONE'      , 6, 0, '/usr/cmit/openMypage.do', '온라인 위원회'),
('N', 'USR', 'Y', 'system', SYSDATE, 'MU_USR_CMT0100', 'MU_USR_CMT', 6, 1, '/usr/cmit/openMypage.do', '나의 정보'),
('N', 'USR', 'Y', 'system', SYSDATE, 'MU_USR_CMT0200', 'MU_USR_CMT', 6, 2, '/usr/cmit/openMyCmit.do', '위원회 현황');

INSERT INTO SYS_PROG 
(PROG_ORDR, SYS_SE_CD, USE_YN, RGTR_NO, REG_YMD, PROG_TY, MENU_ID, PROG_ID, PROG_URL, PROG_NM ) values 
(0, 'USR', 'Y', 'system', SYSDATE, 'url', 'MU_USR_CMT0100', 'PG_USR_CMT0101', '/usr/cmit/openMypage.do'   , '나의정보 화면'),
(0, 'USR', 'Y', 'system', SYSDATE, 'url', 'MU_USR_CMT0100', 'PG_USR_CMT0102', '/usr/cmit/saveMypage.do'   , '나의정보 수정 처리'),
(0, 'USR', 'Y', 'system', SYSDATE, 'url', 'MU_USR_CMT0200', 'PG_USR_CMT0201', '/usr/cmit/openMyCmit.do'   , '위원회 현황 화면'),
(0, 'USR', 'Y', 'system', SYSDATE, 'url', 'MU_USR_CMT0200', 'PG_USR_CMT0202', '/usr/cmit/viewMyCmit.do'   , '위원회 현황 세부정보 화면'),
(0, 'USR', 'Y', 'system', SYSDATE, 'url', 'MU_USR_CMT0200', 'PG_USR_CMT0203', '/usr/cmit/getListMyCmit.do', '위원회 목록 조회'),
(0, 'USR', 'Y', 'system', SYSDATE, 'url', 'MU_USR_CMT0200', 'PG_USR_CMT0204', '/usr/cmit/getMyCmit.do'    , '위원회 상세 조회');

INSERT INTO SYS_ROLE_MENU 
(ROLE_ID, MENU_ID, USE_YN, RGTR_NO, REG_YMD ) values 
('ROLE_AUTH_CMT', 'MU_USR_BRD'    , 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_BRD0100', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_BRD0200', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_DTA'    , 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_DTA0100', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_DTA0200', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_INT'    , 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_INT0100', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_INT0200', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_RQT'    , 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_RQT0100', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_RQT0200', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_RQT0300', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_RQT0400', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_CMT'    , 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_CMT0100', 'Y', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'MU_USR_CMT0200', 'Y', 'system', SYSDATE);

INSERT INTO SYS_ROLE_PROG 
(ROLE_ID, PROG_ID, RGTR_NO, REG_YMD ) values 
('ROLE_AUTH_CMT', 'PG_USR_BRD0205', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_BRD0206', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0201', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0202', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0203', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0204', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0205', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0206', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0207', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0208', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0209', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0210', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0211', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0212', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0213', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0214', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0215', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0216', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0301', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0302', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0303', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0401', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0402', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0403', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0404', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0405', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_RQT0406', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_CMT0101', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_CMT0102', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_CMT0201', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_CMT0202', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_CMT0203', 'system', SYSDATE),
('ROLE_AUTH_CMT', 'PG_USR_CMT0204', 'system', SYSDATE);

-- 2023.10.19 16:00 실행완료 RELIEF03
