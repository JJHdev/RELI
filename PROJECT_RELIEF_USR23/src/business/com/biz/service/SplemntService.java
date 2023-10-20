package business.com.biz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 보완요청 Service Interface
 * 
 * @class   : SplemntService
 * @author  : LSH
 * @since   : 2021.10.24
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface SplemntService {

    /**
     * 보완요청 페이징목록 조회
     */
    public PaginatedArrayList listSplemnt(SplemntVO splemntVO, int currPage, int pageSize) throws Exception;

    /**
     * 보완요청 목록조회
     */
    public List listSplemnt(SplemntVO splemntVO) throws Exception;

    /**
     * 보완요청 상세조회
     */
    public SplemntVO viewSplemnt(SplemntVO splemntVO) throws Exception;

    /**
     * 보완요청 등록,수정,삭제
     */
    public String saveSplemnt(SplemntVO splemntVO) throws Exception;

    /**
     * 2021.10.30
     * 보완요청 중인 최종건 상세조회
     */
    public SplemntVO viewSplemntLast(SplemntVO splemntVO) throws Exception;
}
