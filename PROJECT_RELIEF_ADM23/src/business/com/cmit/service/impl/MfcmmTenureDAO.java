 package business.com.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 위원임기차수을 관리하는 DAO 클래스
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
 * @class   : MfcmmTenureDAO
 * @author  : LSH
 * @since   : 2023.01.09
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 * 2023.01.12   LSH        테이블명/클래스명 변경 (TenureOder -> MfcmmTenure)
 */

@Repository("MfcmmTenureDAO")
@SuppressWarnings({"all"})
public class MfcmmTenureDAO extends BaseDAO {

    /**
     * 위원임기차수 페이징목록 조회
     */
    public PaginatedArrayList listMfcmmTenure(CmitMngVO cmitMngVO, int currPage, int pageSize) {
        return pageList("MfcmmTenure.listMfcmmTenure", cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원임기차수 목록 조회
     */
    public List listMfcmmTenure(CmitMngVO cmitMngVO) {
        return list("MfcmmTenure.listMfcmmTenure", cmitMngVO);
    }

    /**
     * 위원임기차수 상세 조회
     */
    public CmitMngVO viewMfcmmTenure(CmitMngVO cmitMngVO) {
        return (CmitMngVO)view("MfcmmTenure.viewMfcmmTenure", cmitMngVO);
    }

    /**
     * 위원임기차수 등록
     */
    public int regiMfcmmTenure(CmitMngVO cmitMngVO) {
        return insert("MfcmmTenure.regiMfcmmTenure", cmitMngVO);
    }

    /**
     * 위원임기차수 수정
     */
    public int updtMfcmmTenure(CmitMngVO cmitMngVO) {
        return update("MfcmmTenure.updtMfcmmTenure", cmitMngVO);
    }

    /**
     * 위원임기차수 삭제
     */
    public int deltMfcmmTenure(CmitMngVO cmitMngVO) {
        return delete("MfcmmTenure.deltMfcmmTenure", cmitMngVO);
    }

}