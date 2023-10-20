package business.sys.code.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 상병코드관리을 관리하는 Service Interface
 * 
 * @class   : SckwndService
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface SckwndService {

    /**
     * 상병코드관리 페이징목록 조회
     */
    public PaginatedArrayList listSckwnd(SckwndVO sckwndVO, int currPage, int pageSize) throws Exception;

    /**
     * 상병코드관리 목록조회
     */
    public List listSckwnd(SckwndVO sckwndVO) throws Exception;

    /**
     * 상병코드관리 계층형 목록 조회
     */
    public List listSckwndTree(SckwndVO sckwndVO) throws Exception;

    /**
     * 상병코드관리 상세조회
     */
    public SckwndVO viewSckwnd(SckwndVO sckwndVO) throws Exception;

    /**
     * 상병코드관리 등록,수정,삭제
     */
    public String saveSckwnd(SckwndVO sckwndVO) throws Exception;
}