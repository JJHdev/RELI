 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.RcperLvlhVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 요양생활수당지급상세을 관리하는 DAO 클래스
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
 * @class   : RcperLvlhDtlsDAO
 * @author  : LSH
 * @since   : 2021.12.06
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("RcperLvlhDtlsDAO")
@SuppressWarnings({"all"})
public class RcperLvlhDtlsDAO extends BaseDAO {

    /**
     * 요양생활수당지급상세 목록 조회
     */
    public List listRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return list("RcperLvlhDtls.listRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 상세 조회
     */
    public RcperLvlhVO viewRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return (RcperLvlhVO)view("RcperLvlhDtls.viewRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 등록
     */
    public int regiRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlhDtls.regiRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 이력등록
     */
    public int regiRcperLvlhDtlsHst(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlhDtls.regiRcperLvlhDtlsHst", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 수정
     */
    public int updtRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return update("RcperLvlhDtls.updtRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 삭제
     */
    public int deltRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return delete("RcperLvlhDtls.deltRcperLvlhDtls", rcperLvlhVO);
    }

}