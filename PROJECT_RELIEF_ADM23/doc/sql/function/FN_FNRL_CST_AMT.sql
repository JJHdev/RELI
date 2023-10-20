/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
CREATE OR REPLACE FUNCTION FN_FNRL_CST_AMT (
    /**
     * =============================================================
     * title    : 장의비 조회 
     * author   : ntarget
     * date     : 2023.01.05
     * tables   : TB_DMGE_GRD_MNG
     * -------------------------------------------------------------
     * @param   : vi_yr     연도
     * =============================================================
     */
    vi_yr               VARCHAR2
)
RETURN VARCHAR2
IS    
    vo_amt              NUMBER(15);

BEGIN
    
	vo_amt := null;
    
    SELECT TRUNC(MAX(CRTR_INCOME_AMT) * 0.897, -1) INTO vo_amt
      FROM TB_DMGE_GRD_MNG
     WHERE DMGE_GRD_YR = vi_yr
    ;
        
    RETURN vo_amt;
        
END FN_FNRL_CST_AMT;
*/