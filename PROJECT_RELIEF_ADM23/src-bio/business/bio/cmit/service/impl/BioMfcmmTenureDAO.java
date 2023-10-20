 package business.bio.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.bio.cmit.service.BioCmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 위원임기차수을 관리하는 DAO 클래스
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
 * @class   : BioMfcmmTenureDAO
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Repository("BioMfcmmTenureDAO")
@SuppressWarnings({"all"})
public class BioMfcmmTenureDAO extends BaseDAO {

    /**
     * 위원임기차수 페이징목록 조회
     */
    public PaginatedArrayList listBioMfcmmTenure(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) {
        return pageList("BioMfcmmTenure.listBioMfcmmTenure", bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 위원임기차수 목록 조회
     */
    public List listBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) {
        return list("BioMfcmmTenure.listBioMfcmmTenure", bioCmitMngVO);
    }

    /**
     * 위원임기차수 상세 조회
     */
    public BioCmitMngVO viewBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) {
        return (BioCmitMngVO)view("BioMfcmmTenure.viewBioMfcmmTenure", bioCmitMngVO);
    }

    /**
     * 위원임기차수 등록
     */
    public int regiBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) {
        return insert("BioMfcmmTenure.regiBioMfcmmTenure", bioCmitMngVO);
    }

    /**
     * 위원임기차수 수정
     */
    public int updtBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) {
        return update("BioMfcmmTenure.updtBioMfcmmTenure", bioCmitMngVO);
    }

    /**
     * 위원임기차수 삭제
     */
    public int deltBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) {
        return delete("BioMfcmmTenure.deltBioMfcmmTenure", bioCmitMngVO);
    }

}