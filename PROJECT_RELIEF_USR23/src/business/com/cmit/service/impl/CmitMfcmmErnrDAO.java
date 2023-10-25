 package business.com.cmit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원회위원수당을 관리하는 DAO 클래스
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
 * @class   : CmitMfcmmErnrDAO
 * @author  : LSH
 * @since   : 2023.10.24
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("CmitMfcmmErnrDAO")
@SuppressWarnings({"all"})
public class CmitMfcmmErnrDAO extends BaseDAO {

    /**
     * 위원회위원수당 페이징목록 조회
     */
    public PaginatedArrayList listCmitMfcmmErnr(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("CmitMfcmmErnr.listCmitMfcmmErnr", cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회위원수당 목록 조회
     */
    public List listCmitMfcmmErnr(CmitMngVO cmitMngVO) {
        return list("CmitMfcmmErnr.listCmitMfcmmErnr", cmitMngVO);
    }

    /**
     * 위원회위원수당 상세 조회
     */
    public CmitMngVO viewCmitMfcmmErnr(CmitMngVO cmitMngVO) {
        return (CmitMngVO)view("CmitMfcmmErnr.viewCmitMfcmmErnr", cmitMngVO);
    }

    /**
     * 위원회위원수당 등록
     */
    public int regiCmitMfcmmErnr(CmitMngVO cmitMngVO) {
        return insert("CmitMfcmmErnr.regiCmitMfcmmErnr", cmitMngVO);
    }

    /**
     * 위원회위원수당 수정
     */
    public int updtCmitMfcmmErnr(CmitMngVO cmitMngVO) {
        return update("CmitMfcmmErnr.updtCmitMfcmmErnr", cmitMngVO);
    }

    /**
     * 위원회위원수당 삭제
     */
    public int deltCmitMfcmmErnr(CmitMngVO cmitMngVO) {
        return delete("CmitMfcmmErnr.deltCmitMfcmmErnr", cmitMngVO);
    }

}