 package business.com.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원회 소속 위원을 관리하는 DAO 클래스
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
 * @class   : CmitMngDAO
 * @author  : LSH
 * @since   : 2023.01.11
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("CmitMngDAO")
@SuppressWarnings({"all"})
public class CmitMngDAO extends BaseDAO {

    /**
     * 위원회 소속 위원 페이징목록 조회
     */
    public PaginatedArrayList listCmitMng(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("CmitMng.listCmitMng", cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회 소속 위원 목록 조회
     */
    public List listCmitMng(CmitMngVO cmitMngVO) {
        return list("CmitMng.listCmitMng", cmitMngVO);
    }

    /**
     * 위원회 소속 위원 등록
     */
    public int regiCmitMng(CmitMngVO cmitMngVO) {
        return insert("CmitMng.regiCmitMng", cmitMngVO);
    }

    /**
     * 위원회 소속 위원 삭제
     */
    public int deltCmitMng(CmitMngVO cmitMngVO) {
        return delete("CmitMng.deltCmitMng", cmitMngVO);
    }

    /**
     * 위원회 소속 위원 전체삭제
     */
    public int deltCmitMngAll(CmitMngVO cmitMngVO) {
        return delete("CmitMng.deltCmitMngAll", cmitMngVO);
    }

    /**
     * 해당 위원회에 동일한 위원이 존재하는지 체크
     * @param cmitMngVO.cmitMngNo 위원회 번호
     * @param cmitMngVO.mfcmmNo   위원 번호
     */
    public boolean existCmitMng(CmitMngVO cmitMngVO) {
        return (Boolean)view("CmitMng.existCmitMng", cmitMngVO);
    }
    
    /**
     * 2023.02.13 LSH
     * 조건에 해당하는 위원회 건수 조회
     */
    public int getCmitMngCountForTenure(CmitMngVO cmitMngVO) {
        return (Integer)view("CmitMng.listCmitMngCount", cmitMngVO);
    }
}