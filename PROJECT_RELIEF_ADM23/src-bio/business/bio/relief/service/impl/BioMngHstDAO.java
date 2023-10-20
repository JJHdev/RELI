 package business.bio.relief.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.bio.relief.service.BioMngHstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 관리이력정보을 관리하는 DAO 클래스
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
 * @class   : BioMngHstDAO
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("BioMngHstDAO")
@SuppressWarnings({"all"})
public class BioMngHstDAO extends BaseDAO {

    /**
     * 관리이력정보 페이징목록 조회
     */
    public PaginatedArrayList listBioMngHst(BioMngHstVO bioMngHstVO, int currPage, int pageSize) {
        return pageList("BioMngHst.listBioMngHst", bioMngHstVO, currPage, pageSize);
    }

    /**
     * 관리이력정보 목록 조회
     */
    public List listBioMngHst(BioMngHstVO bioMngHstVO) {
        return list("BioMngHst.listBioMngHst", bioMngHstVO);
    }

    /**
     * 관리이력정보 상세 조회
     */
    public BioMngHstVO viewBioMngHst(String sn) {
        return (BioMngHstVO)view("BioMngHst.viewBioMngHst", sn);
    }

    /**
     * 관리이력정보 등록
     */
    public int regiBioMngHst(BioMngHstVO bioMngHstVO) {
        return insert("BioMngHst.regiBioMngHst", bioMngHstVO);
    }

    /**
     * 관리이력정보 수정
     */
    public int updtBioMngHst(BioMngHstVO bioMngHstVO) {
        return update("BioMngHst.updtBioMngHst", bioMngHstVO);
    }

    /**
     * 관리이력정보 삭제
     */
    public int deltBioMngHst(String sn) {
        return delete("BioMngHst.deltBioMngHst", sn);
    }

}