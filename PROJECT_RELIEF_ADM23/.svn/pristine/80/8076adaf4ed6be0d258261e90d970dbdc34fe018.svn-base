CREATE OR REPLACE FUNCTION FN_GET_AGE (
    /**
     * =============================================================
     * title    : 나이 계산 
     * author   : ntarget
     * date     : 2023.02.23
     * tables   : none
     * -------------------------------------------------------------
     * @param   : vi_base_ymd     생년월일
     *          : vi_tagt_ymd     사망일자
     * =============================================================
     */
    vi_base_ymd             VARCHAR2,
    vi_tagt_ymd             VARCHAR2
    
)
RETURN VARCHAR2
IS    
    vo_age                  NUMBER(10);

BEGIN
    vo_age := null;
    
    -- 현재일자 기준
    IF (vi_tagt_ymd is null or vi_tagt_ymd = '') THEN
        SELECT TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(vi_base_ymd,'YYYYMMDD')) / 12) INTO vo_age
          FROM DUAL; 
    -- 기준일자 기준
    ELSE
        SELECT TRUNC(MONTHS_BETWEEN(TO_DATE(vi_tagt_ymd,'YYYYMMDD'), TO_DATE(vi_base_ymd,'YYYYMMDD')) / 12) INTO vo_age
          FROM DUAL;  
    END IF;
    
    RETURN vo_age;

END FN_GET_AGE;

