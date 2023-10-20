
/**
 * 위원분야 - 살생물 (의학, 법학, 화학, 환경보건, 기타)
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT205', 'NONE', '위원분야(살생물)', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF21', 'CT205', '의학', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF22', 'CT205', '법학', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF23', 'CT205', '화학', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF24', 'CT205', '환경보건', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF25', 'CT205', '기타', null, 4, 'Y', 'system', sysdate);


/**
 *  2023/02/15 운영서버 반영 완료
 */
/*
-- 환경오염피해구제 위원회 코드 수정, 살생물 위원회 코드 신규등록
DELETE FROM SYS_CODE 
WHERE CD_ID = 'CT045' OR UP_CD_ID = 'CT045';

INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT045', 'NONE', '위원회구분', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C1', 'CT045', '전문위원회', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C2', 'CT045', '심의위원회', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C3', 'CT045', '심사위원회', null, 3, 'Y', 'system', sysdate);

INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT204', 'NONE', '살생물 위원회구분', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C1', 'CT204', '심사위원회', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C2', 'CT204', '재심사위원회', null, 2, 'Y', 'system', sysdate);

COMMIT;
*/

/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
-- 2022.12.05 이력구분 (정보수정 관련)
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('H3', 'CT016', '피해자정보', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('H4', 'CT016', '신청인정보', null, 4, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('H5', 'CT016', '피해내용', null, 5, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('H6', 'CT016', '제출서류', null, 6, 'Y', 'system', sysdate);

/**
 * 요양생활수당 지급구분(소급, 월지급, 일시지급)
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT043', 'NONE', '구제급여지급구분', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('01', 'CT043', '소급', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('02', 'CT043', '월지급', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('03', 'CT043', '일시지급', null, 2, 'Y', 'system', sysdate);

/**
 * 피해구분코드 (환경오염피해구제, 살생물제품피해구제)
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT044', 'NONE', '피해구분', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('D1', 'CT044', '환경오염피해구제', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('D2', 'CT044', '살생물제품피해구제', null, 2, 'Y', 'system', sysdate);

/**
 * 위원회구분 (전문위원회, 구제심사위원회, 정책위원회, 심의위원회)
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT045', 'NONE', '위원회구분', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C1', 'CT045', '전문위원회', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C2', 'CT045', '구제심사위원회', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C3', 'CT045', '정책위원회', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('C4', 'CT045', '심의위원회', null, 4, 'Y', 'system', sysdate);

/**
 * 위원분야 (의학및보건, 환경, 법률및보상, 기타)
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT046', 'NONE', '위원분야', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF1', 'CT046', '의학및보건', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF2', 'CT046', '환경', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF3', 'CT046', '법률및보상', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CF4', 'CT046', '기타', null, 4, 'Y', 'system', sysdate);

/**
 * 중증도평가 대상 33종
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT047', 'NONE', '중증도평가대상', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('A15', 'CT047', '세균학적 및 조직학적으로 확인된 호흡기 결핵', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('A16', 'CT047', '세균학적으로나 조직학적으로 확인되지 않은 호흡기결핵', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('C34', 'CT047', '기관지 및 폐의 악성 신생물', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('D61', 'CT047', '기타 무형성빈혈', null, 4, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('E11', 'CT047', '2형 당뇨병', null, 5, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('G20', 'CT047', '파킨슨병', null, 6, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('H90', 'CT047', '전음성 및 감각신경성 청력소실', null, 7, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I10', 'CT047', '본태성(원발성) 고혈압', null, 8, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I20', 'CT047', '협심증', null, 9, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I25', 'CT047', '만성 허혈심장병', null, 10, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I50', 'CT047', '심부전', null, 11, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I49', 'CT047', '기타 심장부정맥', null, 12, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I63', 'CT047', '뇌경색증', null, 13, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('I69', 'CT047', '뇌혈관질환의 후유증', null, 14, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J18', 'CT047', '상세불명 병원체의 폐렴', null, 15, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J40', 'CT047', '급성인지 만성인지 명시되지 않은 기관지염', null, 16, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J41', 'CT047', '단순성 및 점액화농성 만성 기관지염', null, 17, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J42', 'CT047', '상세불명의 만성 기관지염', null, 18, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J43', 'CT047', '폐기종', null, 19, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J44', 'CT047', '기타 만성 폐쇄성 폐질환', null, 20, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J47', 'CT047', '기관지확장증', null, 21, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J45', 'CT047', '천식', null, 22, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J46', 'CT047', '천식지속상태', null, 23, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('J60', 'CT047', '진폐증', null, 24, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('L23', 'CT047', '알레르기성 접촉피부염', null, 25, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('L24', 'CT047', '자극물접촉피부염', null, 26, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('L25', 'CT047', '상세불명의 접촉피부염', null, 27, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('L50', 'CT047', '두드러기', null, 28, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('M80', 'CT047', '병적 골절을 동반한 골다공증', null, 29, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('M81', 'CT047', '병적 골절이 없는 골다공증', null, 30, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('M82', 'CT047', '달리 분류된 질환에서의 골다공증', null, 31, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('M83', 'CT047', '성인골연화증', null, 32, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD) VALUES ('N18', 'CT047', '만성 신장병', null, 33, 'Y', 'system', sysdate);


/**
 * 장의비 등급비율 5등급
 * 
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT048', 'NONE', '장의비 등급비율', null, 0, 'Y', 'system', sysdate)
     , ('1', 'CT048', '1500', '1500%', 1, 'Y', 'system', sysdate)
     , ('2', 'CT048', '1080', '1080%', 2, 'Y', 'system', sysdate)
     , ('3', 'CT048', '750',  '750%',  3, 'Y', 'system', sysdate)
     , ('4', 'CT048', '500',  '500%',  4, 'Y', 'system', sysdate)
     , ('5', 'CT048', '250',  '250%',  5, 'Y', 'system', sysdate);

/**
 * 비밀번호 SMS전송 코드
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('SS05', 'CT007', '비밀번호전송', null, null, 'Y', 'system', sysdate);




/***************************************************   살생물 관련 코드  ****************************************************/
/**
 * 살생물 제품사용구분
 * 
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT201', 'NONE', '살생물 제품사용구분', null, 0, 'Y', 'system', sysdate)
     , ('U1', 'CT201', '일반사용자', '', 1, 'Y', 'system', sysdate)
     , ('U2', 'CT201', '직업사용자', '', 2, 'Y', 'system', sysdate)
;


/**
 * 살생물 제품분류유형
 * 
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT202', 'NONE', '살생물 제품분류유형', null, 0, 'Y', 'system', sysdate)
     , ('BP100', 'CT202', '살균제류',               '', 1, 'Y', 'system', sysdate)
     , ('BP101', 'BP100', '살균제',                 '', 2, 'Y', 'system', sysdate)
     , ('BP102', 'BP100', '살조제',                 '', 3, 'Y', 'system', sysdate)
     , ('BP200', 'CT202', '구제제류',               '', 4, 'Y', 'system', sysdate)
     , ('BP201', 'BP200', '살서제',                 '', 5, 'Y', 'system', sysdate)
     , ('BP202', 'BP200', '기타 척추동물제거제',    '', 5, 'Y', 'system', sysdate)
     , ('BP203', 'BP200', '살충제',                 '', 6, 'Y', 'system', sysdate)
     , ('BP204', 'BP200', '기타 무척추동물제거제',  '', 7, 'Y', 'system', sysdate)
     , ('BP205', 'BP200', '기피제',                 '', 8, 'Y', 'system', sysdate)
     , ('BP300', 'CT202', '보존제류',               '', 9, 'Y', 'system', sysdate)
     , ('BP301', 'BP300', '제품보관용 보존제',      '', 10, 'Y', 'system', sysdate)
     , ('BP302', 'BP300', '제품표면처리용 보존제',  '', 11, 'Y', 'system', sysdate)
     , ('BP303', 'BP300', '섬유 가죽류용 보존제',   '', 12, 'Y', 'system', sysdate)
     , ('BP304', 'BP300', '목재용 보존제',          '', 13, 'Y', 'system', sysdate)
     , ('BP305', 'BP300', '건축자재용 보존제',      '', 14, 'Y', 'system', sysdate)
     , ('BP306', 'BP300', '재료.장비용 보존제',     '', 15, 'Y', 'system', sysdate)
     , ('BP307', 'BP300', '사체.박제용 보존제',     '', 16, 'Y', 'system', sysdate)
     , ('BP400', 'CT202', '기타',                   '', 17, 'Y', 'system', sysdate)
     , ('BP401', 'BP400', '선박,수중 시설용 오염방지제', '', 18, 'Y', 'system', sysdate)
;

/**
 * 살생물 제품신청종류
 * 
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('CT203', 'NONE', '살생물제품신청종류', null, 0, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BK1', 'CT203', '진료비', null, 1, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BK2', 'CT203', '장애일시보상금', null, 2, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BK3', 'CT203', '사망일시보상금', null, 3, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BK4', 'CT203', '장례비', null, 4, 'Y', 'system', sysdate);
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BK5', 'CT203', '미지급진료비', null, 4, 'Y', 'system', sysdate);

/**
 * 살생물 업무구분코드
 * 
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, CD_ORDR, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BR04', 'CT004', '살생물제품구제급여신청', null, null, 'Y', 'system', sysdate);

/**
 * 살생물 SMS메세지 추가
 * 
 */
INSERT INTO SYS_CODE (CD_ID, UP_CD_ID, CD_NM, CD_CN, USE_YN, RGTR_NO, REG_YMD)
VALUES ('BR04001', 'CT036', '살생물제품 구제급여 신청접수', '안녕하십니까.
한국환경산업기술원 환경오염피해구제실입니다. 
귀하의 살생물제품 구제급여 신청이 접수되었음을 알려드리며, 
구제급여 신청과 관련하여 궁금한 사항은 02-2284-1850번으로 
문의하여 주시기 바랍니다. 
감사합니다.', 'Y', 'system', sysdate);

*/