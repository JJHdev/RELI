 package business.com.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.biz.service.MngHstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 관리이력정보을 관리하는 DAO 클래스
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
 * @class   : MngHstDAO
 * @author  : LSH
 * @since   : 2021.10.21
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("MngHstDAO")
@SuppressWarnings({"all"})
public class MngHstDAO extends BaseDAO {

    /**
     * 관리이력정보 페이징목록 조회
     */
    public PaginatedArrayList listMngHst(MngHstVO mngHstVO, int currPage, int pageSize) {
        return pageList("MngHst.listMngHst", mngHstVO, currPage, pageSize);
    }

    /**
     * 관리이력정보 목록 조회
     */
    public List listMngHst(MngHstVO mngHstVO) {
        return list("MngHst.listMngHst", mngHstVO);
    }

    /**
     * 관리이력정보 상세 조회
     */
    public MngHstVO viewMngHst(String sn) {
        return (MngHstVO)view("MngHst.viewMngHst", sn);
    }

    /**
     * 관리이력정보 등록
     */
    public int regiMngHst(MngHstVO mngHstVO) {
        return insert("MngHst.regiMngHst", mngHstVO);
    }

    /**
     * 관리이력정보 수정
     */
    public int updtMngHst(MngHstVO mngHstVO) {
        return update("MngHst.updtMngHst", mngHstVO);
    }

    /**
     * 관리이력정보 삭제
     */
    public int deltMngHst(String sn) {
        return delete("MngHst.deltMngHst", sn);
    }

}