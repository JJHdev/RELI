package business.com.exmn.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 생체지표정보 Service Interface
 * 
 * @class   : LbdyNdxService
 * @author  : LSH
 * @since   : 2021.11.22
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface LbdyNdxService {

    /**
     * 생체지표정보 목록조회
     */
    public List listLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception;

    /**
     * 생체지표정보 상세조회
     */
    public LbdyNdxVO viewLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception;

    /**
     * 생체지표정보 등록,수정,삭제
     */
    public int saveLbdyNdx(List<LbdyNdxVO> list, PrptExmnVO prptExmnVO) throws Exception;
}