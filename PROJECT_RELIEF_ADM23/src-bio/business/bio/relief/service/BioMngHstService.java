package business.bio.relief.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 살생물제품 관리이력정보 Service Interface
 * 
 * @class   : BioMngHstService
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioMngHstService {

    /**
     * 관리이력정보 페이징목록 조회
     */
    public PaginatedArrayList listBioMngHst(BioMngHstVO bioMngHstVO, int currPage, int pageSize) throws Exception;

    /**
     * 관리이력정보 목록조회
     */
    public List listBioMngHst(BioMngHstVO bioMngHstVO) throws Exception;

    /**
     * 관리이력정보 상세조회
     */
    public BioMngHstVO viewBioMngHst(String sn) throws Exception;

    /**
     * 관리이력정보 등록,수정,삭제
     */
    public String saveBioMngHst(BioMngHstVO bioMngHstVO) throws Exception;
}