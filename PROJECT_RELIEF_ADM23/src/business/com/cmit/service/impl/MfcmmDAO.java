 package business.com.cmit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원정보을 관리하는 DAO 클래스
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
 * @class   : MfcmmDAO
 * @author  : LSH
 * @since   : 2023.01.09
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("MfcmmDAO")
@SuppressWarnings({"all"})
public class MfcmmDAO extends BaseDAO {

    /**
     * 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listMfcmm(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("Mfcmm.listMfcmm", cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원정보 목록 조회
     */
    public List listMfcmm(CmitMngVO cmitMngVO) {
        return list("Mfcmm.listMfcmm", cmitMngVO);
    }

    /**
     * 위원정보 상세 조회
     */
    public CmitMngVO viewMfcmm(CmitMngVO cmitMngVO) {
        return (CmitMngVO)view("Mfcmm.viewMfcmm", cmitMngVO);
    }

    /**
     * 위원정보 등록
     */
    public int regiMfcmm(CmitMngVO cmitMngVO) {
        return insert("Mfcmm.regiMfcmm", cmitMngVO);
    }

    /**
     * 위원정보 수정
     */
    public int updtMfcmm(CmitMngVO cmitMngVO) {
        return update("Mfcmm.updtMfcmm", cmitMngVO);
    }

    /**
     * 위원정보 삭제
     */
    public int deltMfcmm(CmitMngVO cmitMngVO) {
        return delete("Mfcmm.deltMfcmm", cmitMngVO);
    }
    
    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listMfcmmTarget(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("Mfcmm.listMfcmmTarget", cmitMngVO, currPage, pageSize);
    }

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 목록조회
     */
    public List listMfcmmTarget(CmitMngVO cmitMngVO) {
        return list("Mfcmm.listMfcmmTarget", cmitMngVO);
    }
}