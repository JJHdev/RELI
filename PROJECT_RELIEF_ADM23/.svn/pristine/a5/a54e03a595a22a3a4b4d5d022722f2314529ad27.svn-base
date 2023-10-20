package business.com.cmit.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 위원회관리 Service Interface
 * 
 * @class   : CmitMngService
 * @author  : LSH
 * @since   : 2023.01.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface CmitMngService {

    /**
     * 위원회 페이징목록 조회
     */
    public PaginatedArrayList listCmitDmge(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원회 목록조회
     */
    public List listCmitDmge(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원회 상세조회
     */
    public CmitMngVO viewCmitDmge(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원회 등록,수정,삭제
     */
    public String saveCmitDmge(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원회 소속 위원 페이징목록 조회
     */
    public PaginatedArrayList listCmitMng(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 위원회관리 목록조회
     */
    public List listCmitMng(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 위원회관리 등록,수정,삭제
     */
    public String saveCmitMng(CmitMngVO cmitMngVO) throws Exception;
    
    /**
     * 2023.02.13 LSH
     * 임기번호가 속한 위원회가 있는지 체크
     */
    public boolean existCmitMngForTenure(CmitMngVO cmitMngVO) throws Exception;
}