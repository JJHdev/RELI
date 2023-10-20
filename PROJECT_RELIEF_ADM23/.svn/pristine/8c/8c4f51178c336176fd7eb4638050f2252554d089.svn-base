 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.MnsvyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 본조사을 관리하는 DAO 클래스
 * 
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 * 
 *
 * @class   : MnsvyDAO
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("MnsvyDAO")
@SuppressWarnings({"all"})
public class MnsvyDAO extends BaseDAO {

    /**
     * 본조사 페이징목록 조회
     */
    public PaginatedArrayList listMnsvy(MnsvyVO mnsvyVO, int currPage, int pageSize) {
        return pageList("Mnsvy.listMnsvy", mnsvyVO, currPage, pageSize);
    }

    /**
     * 본조사 목록 조회
     */
    public List listMnsvy(MnsvyVO mnsvyVO) {
        return list("Mnsvy.listMnsvy", mnsvyVO);
    }

    /**
     * 본조사 상세 조회
     */
    public MnsvyVO viewMnsvy(MnsvyVO mnsvyVO) {
        return (MnsvyVO)view("Mnsvy.viewMnsvy", mnsvyVO);
    }

    /**
     * 2021.12.03 LSH
     * 본조사 신청번호 기준 최종 조사차수 조회
     */
    public String getMnsvyOderLast(String aplyNo) {
        return (String)view("Mnsvy.getMnsvyOderLast", aplyNo);
    }

    /**
     * 본조사 등록
     */
    public int regiMnsvy(MnsvyVO mnsvyVO) {
        return insert("Mnsvy.regiMnsvy", mnsvyVO);
    }

    /**
     * 본조사 이력등록
     */
    public int regiMnsvyHst(MnsvyVO mnsvyVO) {
        return insert("Mnsvy.regiMnsvyHst", mnsvyVO);
    }

    /**
     * 본조사 수정
     */
    public int updtMnsvy(MnsvyVO mnsvyVO) {
        return update("Mnsvy.updtMnsvy", mnsvyVO);
    }

    /**
     * 본조사 삭제
     */
    public int deltMnsvy(MnsvyVO mnsvyVO) {
        return delete("Mnsvy.deltMnsvy", mnsvyVO);
    }

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 본조사 목록 조회
     */
    public List listMnsvyMypage(String aplyNo) {
        return list("Mnsvy.listMnsvyMypage", aplyNo);
    }
    
    /**
     * 2022.02.04 LSH 요양생활수당관리 존재여부확인
     */
    public boolean existRcperLvlh(MnsvyVO mnsvyVO) {
    	return (Boolean)view("Mnsvy.existRcperLvlh", mnsvyVO);
    }

    /**
    * 2022.02.04 LSH 의료비 존재여부확인
    */
    public boolean existMcpDtls(MnsvyVO mnsvyVO) {
    	return (Boolean)view("Mnsvy.existMcpDtls", mnsvyVO);
    }
    
    /**
     * 2022.03.17 인정금액 업데이트
     */
    public int updtMcpDtlsAmt(MnsvyVO mnsvyVO) {
        return delete("Mnsvy.updtMcpDtlsAmt", mnsvyVO);
    }

    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 장의비 확정금액 조회
     * 1) 지급기준년도의 중위소득의 89.7%
     * 2) FN_FNRL_CST_AMT 함수 사용
     * 
     * @param fnrlCrtrYr 지급기준년도 (신청년도)
     */
	public long getFnrlCstAmt(String fnrlCrtrYr) throws Exception {
        return (Long)view("Mnsvy.getFnrlCstAmt", fnrlCrtrYr);
	}
    
    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 유족보상비,기지급의료비,요양생활수당 조회
     * 1) 유족보상비 확정 = (장의비 * 피해등급별 장의비 지급비율)
     * 2) 유족보상비 잔액 = (유족보상비) - (기지급 의료비 + 요양생활수당) 
     * 3) 장의비의 등급별 지급비율 
     *    - 1등급 1500%
     *    - 2등급 1080%
     *    - 3등급 750%
     *    - 4등급 500%
     *    - 5등급 250%
     * 4) 유족보상비 FN_BRVFM_RWRD_AMT 함수 사용
     * 
     * @param mnsvyVO.bizAreaCd       사업지역코드
     * @param mnsvyVO.bizOder         사업차수
     * @param mnsvyVO.exmnOder        조사차수
     * @param mnsvyVO.aplyNo          신청번호
     * @return brvfmRwrdAmt           유족보상비
     * @return mcpDtlsSum             기지급 의료비 합계
     * @return rcperLvlhSum           기지급 요양생활수당 함계
     */
	public MnsvyVO viewBrvfmRwrdAmt(MnsvyVO mnsvyVO) throws Exception {
        return (MnsvyVO)view("Mnsvy.viewBrvfmRwrdAmt", mnsvyVO);
	}
}