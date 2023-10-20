 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.MnsvyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 본조사계획을 관리하는 DAO 클래스
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
 * @class   : MnsvyPlanDAO
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("MnsvyPlanDAO")
@SuppressWarnings({"all"})
public class MnsvyPlanDAO extends BaseDAO {

    /**
     * 본조사계획 페이징목록 조회
     */
    public PaginatedArrayList listMnsvyPlan(MnsvyVO mnsvyVO, int currPage, int pageSize) {
        return pageList("MnsvyPlan.listMnsvyPlan", mnsvyVO, currPage, pageSize);
    }

    /**
     * 본조사계획 목록 조회
     */
    public List listMnsvyPlan(MnsvyVO mnsvyVO) {
        return list("MnsvyPlan.listMnsvyPlan", mnsvyVO);
    }

    /**
     * 본조사계획 상세 조회
     */
    public MnsvyVO viewMnsvyPlan(MnsvyVO mnsvyVO) {
        return (MnsvyVO)view("MnsvyPlan.viewMnsvyPlan", mnsvyVO);
    }

    /**
     * 본조사계획 등록
     */
    public int regiMnsvyPlan(MnsvyVO mnsvyVO) {
        return insert("MnsvyPlan.regiMnsvyPlan", mnsvyVO);
    }

    /**
     * 본조사계획 수정
     */
    public int updtMnsvyPlan(MnsvyVO mnsvyVO) {
        return update("MnsvyPlan.updtMnsvyPlan", mnsvyVO);
    }

    /**
     * 본조사계획 삭제
     */
    public int deltMnsvyPlan(MnsvyVO mnsvyVO) {
        return delete("MnsvyPlan.deltMnsvyPlan", mnsvyVO);
    }

    /**
     * 예비조사계획 NEXT 조사차수 가져오기
     */
    public String getMnsvyPlanNextOder(MnsvyVO mnsvyVO) {
        return (String)view("MnsvyPlan.getMnsvyPlanNextOder", mnsvyVO);
    }

}