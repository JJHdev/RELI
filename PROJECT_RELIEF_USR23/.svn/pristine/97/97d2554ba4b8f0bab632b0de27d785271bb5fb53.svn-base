package business.com.cmit.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 위원정보 Service Interface
 * 
 * @class   : MfcmmService
 * @author  : LSH
 * @since   : 2023.10.19
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface MfcmmService {

    /**
     * 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listMfcmm(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원정보 목록조회
     */
    public List listMfcmm(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원정보 상세조회
     */
    public CmitMngVO viewMfcmm(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원정보 등록,수정,삭제
     */
    public String saveMfcmm(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원임기차수 목록조회
     */
    public List listMfcmmTenure(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원임기차수 다중항목 저장처리
     */
    public String saveMfcmmTenure(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 페이징목록 조회
     */
    public PaginatedArrayList listMfcmmTarget(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 목록조회
     */
    public List listMfcmmTarget(CmitMngVO cmitMngVO) throws Exception;
}