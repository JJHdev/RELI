 package business.bio.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.bio.cmit.service.BioCmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 위원정보을 관리하는 DAO 클래스
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
 * @class   : BioMfcmmDAO
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Repository("BioMfcmmDAO")
@SuppressWarnings({"all"})
public class BioMfcmmDAO extends BaseDAO {

    /**
     * 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listBioMfcmm(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) {
        return pageList("BioMfcmm.listBioMfcmm", bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 위원정보 목록 조회
     */
    public List listBioMfcmm(BioCmitMngVO bioCmitMngVO) {
        return list("BioMfcmm.listBioMfcmm", bioCmitMngVO);
    }

    /**
     * 위원정보 상세 조회
     */
    public BioCmitMngVO viewBioMfcmm(BioCmitMngVO bioCmitMngVO) {
        return (BioCmitMngVO)view("BioMfcmm.viewBioMfcmm", bioCmitMngVO);
    }

    /**
     * 위원정보 등록
     */
    public int regiBioMfcmm(BioCmitMngVO bioCmitMngVO) {
        return insert("BioMfcmm.regiBioMfcmm", bioCmitMngVO);
    }

    /**
     * 위원정보 수정
     */
    public int updtBioMfcmm(BioCmitMngVO bioCmitMngVO) {
        return update("BioMfcmm.updtBioMfcmm", bioCmitMngVO);
    }

    /**
     * 위원정보 삭제
     */
    public int deltBioMfcmm(BioCmitMngVO bioCmitMngVO) {
        return delete("BioMfcmm.deltBioMfcmm", bioCmitMngVO);
    }
    
    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listBioMfcmmTarget(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) {
        return pageList("BioMfcmm.listBioMfcmmTarget", bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 목록조회
     */
    public List listBioMfcmmTarget(BioCmitMngVO bioCmitMngVO) {
        return list("BioMfcmm.listBioMfcmmTarget", bioCmitMngVO);
    }
}