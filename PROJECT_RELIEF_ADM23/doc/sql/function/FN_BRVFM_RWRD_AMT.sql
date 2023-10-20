/**
 *  2023/02/01 운영서버 반영 완료
 */
/*
CREATE OR REPLACE FUNCTION FN_BRVFM_RWRD_AMT (
    /**
     * =============================================================
     * title    : 유족보상비 조회 
     * author   : ntarget
     * date     : 2023.01.05
     * tables   : TB_MNSVY
     *            SYS_CODE (
     * -------------------------------------------------------------
     * @param   : vi_grd        피해등급
     *            vi_aply_no    신청번호
     *            vi_oder       본조사차수
     * =============================================================
     */
    vi_grd              VARCHAR2,
    vi_aply_no          VARCHAR2,
    vi_oder             NUMBER
)
RETURN VARCHAR2
IS    
    vo_amt              NUMBER(15);

BEGIN
    
    vo_amt := null;
    
    SELECT TRUNC((TO_NUMBER(C.CD_NM)/100) * NVL(M.FNRL_CST_AMT, 0), -1) INTO vo_amt
      FROM TB_MNSVY M
         , SYS_CODE C
     WHERE C.CD_ID      = NVL(vi_grd, M.LAST_DMGE_GRD_CD)
       AND C.UP_CD_ID   = 'CT048'
       AND M.EXMN_ODER  = vi_oder
       AND M.APLY_NO    = vi_aply_no
    ;
    
    RETURN vo_amt;
        
END FN_BRVFM_RWRD_AMT;
*/