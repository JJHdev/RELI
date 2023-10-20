 package business.com.exmn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.PrptExmnVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 예비조사계획을 관리하는 DAO 클래스
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
 * @class   : PrptExmnPlanDAO
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("PrptExmnPlanDAO")
@SuppressWarnings({"all"})
public class PrptExmnPlanDAO extends BaseDAO {

    /**
     * 예비조사계획 페이징목록 조회
     */
    public PaginatedArrayList listPrptExmnPlan(PrptExmnVO prptExmnVO, int currPage, int pageSize) {
        return pageList("PrptExmnPlan.listPrptExmnPlan", prptExmnVO, currPage, pageSize);
    }

    /**
     * 예비조사계획 목록 조회
     */
    public List listPrptExmnPlan(PrptExmnVO prptExmnVO) {
        return list("PrptExmnPlan.listPrptExmnPlan", prptExmnVO);
    }

    /**
     * 예비조사계획 상세 조회
     */
    public PrptExmnVO viewPrptExmnPlan(PrptExmnVO prptExmnVO) {
        return (PrptExmnVO)view("PrptExmnPlan.viewPrptExmnPlan", prptExmnVO);
    }

    /**
     * 예비조사계획 등록
     */
    public int regiPrptExmnPlan(PrptExmnVO prptExmnVO) {
        return insert("PrptExmnPlan.regiPrptExmnPlan", prptExmnVO);
    }

    /**
     * 예비조사계획 수정
     */
    public int updtPrptExmnPlan(PrptExmnVO prptExmnVO) {
        return update("PrptExmnPlan.updtPrptExmnPlan", prptExmnVO);
    }

    /**
     * 예비조사계획 삭제
     */
    public int deltPrptExmnPlan(PrptExmnVO prptExmnVO) {
        return delete("PrptExmnPlan.deltPrptExmnPlan", prptExmnVO);
    }

    /**
     * 예비조사계획 NEXT 조사차수 가져오기
     */
    public String getPrptExmnPlanNextOder(PrptExmnVO prptExmnVO) {
        return (String)view("PrptExmnPlan.getPrptExmnPlanNextOder", prptExmnVO);
    }

}