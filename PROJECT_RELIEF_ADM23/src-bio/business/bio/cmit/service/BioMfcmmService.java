package business.bio.cmit.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 살생물제품 위원정보 Service Interface
 * 
 * @class   : BioMfcmmService
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BioMfcmmService {

    /**
     * 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listBioMfcmm(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원정보 목록조회
     */
    public List listBioMfcmm(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원정보 상세조회
     */
    public BioCmitMngVO viewBioMfcmm(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원정보 등록,수정,삭제
     */
    public String saveBioMfcmm(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원임기차수 목록조회
     */
    public List listBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원임기차수 다중항목 저장처리
     */
    public String saveBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) throws Exception;

    /**
     * 위원회 등록대상 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listBioMfcmmTarget(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원회 등록대상 위원정보 목록조회
     */
    public List listBioMfcmmTarget(BioCmitMngVO bioCmitMngVO) throws Exception;
}