package business.com.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원회피해조사을 관리하는 DAO 클래스
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
 * @class   : CmitDmgeDAO
 * @author  : LSH
 * @since   : 2023.10.19
 * @version : 3.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("CmitDmgeDAO")
@SuppressWarnings({"all"})
public class CmitDmgeDAO extends BaseDAO {

    /**
     * 위원회피해조사 페이징목록 조회
     */
    public PaginatedArrayList listCmitDmge(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("CmitDmge.listCmitDmge", cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회피해조사 목록 조회
     */
    public List listCmitDmge(CmitMngVO cmitMngVO) {
        return list("CmitDmge.listCmitDmge", cmitMngVO);
    }

    /**
     * 위원회피해조사 상세 조회
     */
    public CmitMngVO viewCmitDmge(CmitMngVO cmitMngVO) {
        return (CmitMngVO)view("CmitDmge.viewCmitDmge", cmitMngVO);
    }

    /**
     * 위원회피해조사 등록
     */
    public int regiCmitDmge(CmitMngVO cmitMngVO) {
        return insert("CmitDmge.regiCmitDmge", cmitMngVO);
    }

    /**
     * 위원회피해조사 수정
     */
    public int updtCmitDmge(CmitMngVO cmitMngVO) {
        return update("CmitDmge.updtCmitDmge", cmitMngVO);
    }

    /**
     * 위원회피해조사 삭제
     */
    public int deltCmitDmge(CmitMngVO cmitMngVO) {
        return delete("CmitDmge.deltCmitDmge", cmitMngVO);
    }

}