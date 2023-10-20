package business.com.biz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 관리이력정보 Service Interface
 * 
 * @class   : MngHstService
 * @author  : LSH
 * @since   : 2021.10.21
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface MngHstService {

    /**
     * 관리이력정보 페이징목록 조회
     */
    public PaginatedArrayList listMngHst(MngHstVO mngHstVO, int currPage, int pageSize) throws Exception;

    /**
     * 관리이력정보 목록조회
     */
    public List listMngHst(MngHstVO mngHstVO) throws Exception;

    /**
     * 관리이력정보 상세조회
     */
    public MngHstVO viewMngHst(String sn) throws Exception;

    /**
     * 관리이력정보 등록,수정,삭제
     */
    public String saveMngHst(MngHstVO mngHstVO) throws Exception;
}