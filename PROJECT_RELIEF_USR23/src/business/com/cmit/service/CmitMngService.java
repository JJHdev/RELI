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
 * @since   : 2023.10.19
 * @version : 3.0
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

    /**
     * 2023.10.25 LSH
     * 위원회안건 목록조회
     */
    public List listCmitDmgeAgnd(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 2023.10.26 LSH
     * 위원회 의견서 상세조회
     * @param cmitMngVO.cmitMngNo 위원회관리번호
     * @param cmitMngVO.tenureNo  임기번호
     * @param cmitMngVO.agndNo    안건번호
     */
    public CmitMngVO viewCmitOpinion(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 2023.10.26 LSH
     * 위원회 의결서 상세조회
     * @param cmitMngVO.cmitMngNo 위원회관리번호
     * @param cmitMngVO.tenureNo  임기번호
     * @param cmitMngVO.agndNo    안건번호
     */
    public CmitMngVO viewCmitDecision(CmitMngVO cmitMngVO) throws Exception;

    /**
     * 2023.10.26 LSH
     * 위원회 수당지 상세조회
     * @param cmitMngVO.cmitMngNo 위원회관리번호
     * @param cmitMngVO.tenureNo  임기번호
     */
    public CmitMngVO viewCmitPension(CmitMngVO cmitMngVO) throws Exception;
    
    /**
     * 2023.10.31 LSH
     * 위원회 의견서,의결서,수당지 등록,수정
     * cmitMngVO.saveMode = "WO" : 의견서 저장
     * cmitMngVO.saveMode = "WD" : 의결서 저장
     * cmitMngVO.saveMode = "WP" : 수당지 저장
     */
    public String saveCmitItem(CmitMngVO cmitMngVO) throws Exception;
}