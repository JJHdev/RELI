 package business.com.cmit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원회피해조사안건을 관리하는 DAO 클래스
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
 * @class   : CmitDmgeAgndDAO
 * @author  : LSH
 * @since   : 2023.10.24
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("CmitDmgeAgndDAO")
@SuppressWarnings({"all"})
public class CmitDmgeAgndDAO extends BaseDAO {

    /**
     * 위원회피해조사안건 페이징목록 조회
     */
    public PaginatedArrayList listCmitDmgeAgnd(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("CmitDmgeAgnd.listCmitDmgeAgnd", cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회피해조사안건 목록 조회
     */
    public List listCmitDmgeAgnd(CmitMngVO cmitMngVO) {
        return list("CmitDmgeAgnd.listCmitDmgeAgnd", cmitMngVO);
    }

    /**
     * 위원회피해조사안건 상세 조회
     */
    public CmitMngVO viewCmitDmgeAgnd(CmitMngVO cmitMngVO) {
        return (CmitMngVO)view("CmitDmgeAgnd.viewCmitDmgeAgnd", cmitMngVO);
    }

    /**
     * 위원회피해조사안건 등록
     */
    public int regiCmitDmgeAgnd(CmitMngVO cmitMngVO) {
        return insert("CmitDmgeAgnd.regiCmitDmgeAgnd", cmitMngVO);
    }

    /**
     * 위원회피해조사안건 수정
     */
    public int updtCmitDmgeAgnd(CmitMngVO cmitMngVO) {
        return update("CmitDmgeAgnd.updtCmitDmgeAgnd", cmitMngVO);
    }

    /**
     * 위원회피해조사안건 삭제
     */
    public int deltCmitDmgeAgnd(CmitMngVO cmitMngVO) {
        return delete("CmitDmgeAgnd.deltCmitDmgeAgnd", cmitMngVO);
    }

}