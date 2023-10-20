/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
CREATE OR REPLACE FUNCTION FN_SVRTY_GRD (
    /**
     * =============================================================
     * title    : 년도별 중증도 평가점수로 피해등급 조회 
     * author   : ntarget
     * date     : 2022.12.28
     * tables   : TB_DMGE_GRD_MNG
     * -------------------------------------------------------------
     * @param   : vi_yr     연도
     *          : vi_scre   점수
     * =============================================================
     */
    vi_yr               VARCHAR2,
    vi_scre             NUMBER
    
)
RETURN VARCHAR2
IS    
    vo_grd              VARCHAR2(10);

BEGIN
    vo_grd := null;
    
    SELECT CASE WHEN MIN(DMGE_GRD_CD) = 99 
                THEN null
           ELSE MIN(DMGE_GRD_CD)
           END INTO vo_grd
      FROM TB_DMGE_GRD_MNG
     WHERE DMGE_GRD_YR = vi_yr
       AND vi_scre BETWEEN SVRTY_BGNG_SCRE + 0.01 AND SVRTY_END_SCRE;
       
    RETURN vo_grd;

END FN_SVRTY_GRD;
*/