 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.McpDtlsVO;
import business.com.exmn.service.RcperLvlhVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 요양생활수당관리을 관리하는 DAO 클래스
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
 * @class   : RcperLvlhDAO
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 * 2022.12.27   LSH         현재 사용안함
 */

@Repository("RcperLvlhDAO")
@SuppressWarnings({"all"})
public class RcperLvlhDAO extends BaseDAO {

    /**
     * 요양생활수당관리 목록 조회
     */
    public List listRcperLvlh(RcperLvlhVO rcperLvlhVO) {
        return list("RcperLvlh.listRcperLvlh", rcperLvlhVO);
    }

    /**
     * 요양생활수당관리 상세 조회
     */
    public RcperLvlhVO viewRcperLvlh(RcperLvlhVO rcperLvlhVO) {
        return (RcperLvlhVO)view("RcperLvlh.viewRcperLvlh", rcperLvlhVO);
    }

    /**
     * 요양생활수당관리 등록
     */
    public int regiRcperLvlh(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlh.regiRcperLvlh", rcperLvlhVO);
    }

    /**
     * 요양생활수당관리 이력등록
     */
    public int regiRcperLvlhHst(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlh.regiRcperLvlhHst", rcperLvlhVO);
    }

    /**
     * 요양생활수당관리 수정
     */
    public int updtRcperLvlh(RcperLvlhVO rcperLvlhVO) {
        return update("RcperLvlh.updtRcperLvlh", rcperLvlhVO);
    }

    /**
     * 요양생활수당관리 삭제
     */
    public int deltRcperLvlh(RcperLvlhVO rcperLvlhVO) {
        return delete("RcperLvlh.deltRcperLvlh", rcperLvlhVO);
    }

}