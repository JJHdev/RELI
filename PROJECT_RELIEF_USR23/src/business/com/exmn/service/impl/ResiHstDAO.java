 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.ResiHstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 거주이력정보을 관리하는 DAO 클래스
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
 * @class   : ResiHstDAO
 * @author  : LSH
 * @since   : 2021.11.22
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("ResiHstDAO")
@SuppressWarnings({"all"})
public class ResiHstDAO extends BaseDAO {

    /**
     * 거주이력정보 목록 조회
     */
    public List listResiHst(ResiHstVO resiHstVO) {
        return list("ResiHst.listResiHst", resiHstVO);
    }

    /**
     * 거주이력정보 상세 조회
     */
    public ResiHstVO viewResiHst(ResiHstVO resiHstVO) {
        return (ResiHstVO)view("ResiHst.viewResiHst", resiHstVO);
    }

    /**
     * 거주이력정보 등록
     */
    public int regiResiHst(ResiHstVO resiHstVO) {
        return insert("ResiHst.regiResiHst", resiHstVO);
    }

    /**
     * 거주이력정보 수정
     */
    public int updtResiHst(ResiHstVO resiHstVO) {
        return update("ResiHst.updtResiHst", resiHstVO);
    }

    /**
     * 거주이력정보 삭제
     */
    public int deltResiHst(ResiHstVO resiHstVO) {
        return delete("ResiHst.deltResiHst", resiHstVO);
    }

}