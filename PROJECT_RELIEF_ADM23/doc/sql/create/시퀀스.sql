/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
/*
 * 시퀀스 생성
 */
-- 2022.12.27 요양생활수당평가등급이력(TB_RCPER_LVLH_GRD) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_RCPER_LVLH_GRD_HST
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;
  
-- 2023.01.10 위원정보(TB_MFCMM) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_MFCMM
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.10 위원임기차수(TB_MFCMM_TENURE) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_MFCMM_TENURE
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.10 위원회(TB_CMIT_MNG) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_CMIT_MNG
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 초본주소 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_ABSTRCT_ADDR
START WITH 3001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_구제급여 신청(TB_BIO_RELIEF) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_RELIEF
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
CYCLE
NOCACHE ;

-- 2023.01.17 살생물_구제급여 신청이력(TB_BIO_RELIEF_HST) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_RELIEF_HST
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_피해자정보(TB_BIO_IDNTFC) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_IDNTFC
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_피해자정보이력(TB_BIO_IDNTFC_HST) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_IDNTFC_HST
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_신청첨부파일(TB_BIO_APLY_FILE) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_APLY_FILE
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_관리이력정보(TB_BIO_MNG_HST) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_MNG_HST
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;


-- 2023.01.17 살생물_위원회관리(TB_BIO_CMIT_MNG) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_CMIT_MNG
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_위원정보(TB_BIO_MFCMM) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_MFCMM
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;

-- 2023.01.17 살생물_위원임기정보(TB_BIO_MFCMM_TENURE) 테이블 시퀀스 생성
CREATE SEQUENCE SEQ_BIO_MFCMM_TENURE
START WITH 1001
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999999
NOCYCLE
NOCACHE ;
*/
