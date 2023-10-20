package business.com.relief.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 구상금납부내역 Service Interface
 * 
 * @class   : ReamtPayService
 * @author  : LSH
 * @since   : 2021.12.16
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface ReamtPayService {

    /**
     * 구상금납부내역 페이징목록 조회
     */
    public PaginatedArrayList listReamtPay(ReamtPayVO reamtPayVO, int currPage, int pageSize) throws Exception;

    /**
     * 구상금납부내역 목록조회
     */
    public List listReamtPay(ReamtPayVO reamtPayVO) throws Exception;

    /**
     * 구상금납부내역 등록,수정,삭제
     */
    public String saveReamtPay(ReamtPayVO reamtPayVO) throws Exception;

    /**
     * 구상금납부내역 상세조회 (구제급여 항목포함)
     */
    public ReamtPayVO viewReamtPay(ReamtPayVO reamtPayVO) throws Exception;

    /**
     * 피해지역코드 기준 구제급여 총액 조회
     * 의료비 + 요양생활수당 + 장례비 + 유족보상금 + 재산피해보상금
     */
    public String viewReliefTotal(String bizAreaCd) throws Exception;
}