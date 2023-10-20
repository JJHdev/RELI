 package business.com.relief.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.relief.service.ReamtPayVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 구상금납부내역을 관리하는 DAO 클래스
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
 * @class   : ReamtPayDAO
 * @author  : LSH
 * @since   : 2021.12.16
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("ReamtPayDAO")
@SuppressWarnings({"all"})
public class ReamtPayDAO extends BaseDAO {

    /**
     * 구상금납부내역 페이징목록 조회
     */
    public PaginatedArrayList listReamtPay(ReamtPayVO reamtPayVO, int currPage, int pageSize) {
        return pageList("ReamtPay.listReamtPay", reamtPayVO, currPage, pageSize);
    }

    /**
     * 구상금납부내역 목록 조회
     */
    public List listReamtPay(ReamtPayVO reamtPayVO) {
        return list("ReamtPay.listReamtPay", reamtPayVO);
    }

    /**
     * 구상금납부내역 등록
     */
    public int regiReamtPay(ReamtPayVO reamtPayVO) {
        return insert("ReamtPay.regiReamtPay", reamtPayVO);
    }

    /**
     * 구상금납부내역 수정
     */
    public int updtReamtPay(ReamtPayVO reamtPayVO) {
        return update("ReamtPay.updtReamtPay", reamtPayVO);
    }

    /**
     * 구상금납부내역 삭제
     */
    public int deltReamtPay(ReamtPayVO reamtPayVO) {
        return delete("ReamtPay.deltReamtPay", reamtPayVO);
    }

    /**
     * 구상금관리 등록
     */
    public int regiReamtMng(ReamtPayVO reamtPayVO) {
        return insert("ReamtPay.regiReamtMng", reamtPayVO);
    }

    /**
     * 구상금관리 수정
     */
    public int updtReamtMng(ReamtPayVO reamtPayVO) {
        return update("ReamtPay.updtReamtMng", reamtPayVO);
    }

    /**
     * 구상금관리 삭제
     */
    public int deltReamtMng(ReamtPayVO reamtPayVO) {
        return delete("ReamtPay.deltReamtMng", reamtPayVO);
    }

    /**
     * 구상금관리 사업지역,업체명 기준 관리번호 가져오기
     */
    public String getReamtMngNo(ReamtPayVO reamtPayVO) {
        return (String)view("ReamtPay.getReamtMngNo", reamtPayVO);
    }

    /**
     * 구상금납부내역 상세 조회
     */
    public ReamtPayVO viewReamtPay(ReamtPayVO reamtPayVO) {
        return (ReamtPayVO)view("ReamtPay.viewReamtPay", reamtPayVO);
    }

    /**
     * 피해지역코드 기준 구제급여 총액 조회
     * 의료비 + 요양생활수당 + 장례비 + 유족보상금 + 재산피해보상금
     */
    public String viewReliefTotal(String bizAreaCd) {
        return (String)view("ReamtPay.viewReliefTotal", bizAreaCd);
    }
}