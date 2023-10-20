package business.com.file.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 서류코드관리 Service Interface
 *
 * @class   : PapeCodeService
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface PapeCodeService {

    /**
     * 서류코드관리 페이징목록 조회
     */
    public PaginatedArrayList listPapeCode(PapeCodeVO papeCodeVO, int currPage, int pageSize) throws Exception;

    /**
     * 서류코드관리 목록조회
     */
    public List listPapeCode(PapeCodeVO papeCodeVO) throws Exception;

    /**
     * 서류코드관리 계층형 목록조회
     */
    public List listPapeCodeTree(PapeCodeVO papeCodeVO) throws Exception;

    /**
     * 서류코드관리 상세조회
     */
    public PapeCodeVO viewPapeCode(PapeCodeVO papeCodeVO) throws Exception;

    /**
     * 서류코드관리 등록,수정,삭제
     */
    public String savePapeCode(PapeCodeVO papeCodeVO) throws Exception;

    /**
     * 2022.01.11 CSLEE 추가
     * 다운로드 카운트 층가 저장
     */
    public int updtPapeCodeDownCount(PapeCodeVO papeCodeVO) throws Exception ;
}