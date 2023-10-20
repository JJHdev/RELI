package business.com.exmn.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 예비조사 Service Interface
 * 
 * @class   : PrptExmnService
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface PrptExmnService {

    /**
     * 예비조사 페이징목록 조회
     */
    public PaginatedArrayList listPrptExmn(PrptExmnVO prptExmnVO, int currPage, int pageSize) throws Exception;

    /**
     * 예비조사 목록조회
     */
    public List listPrptExmn(PrptExmnVO prptExmnVO) throws Exception;

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 예비조사 목록 조회
     */
    public List listPrptExmnMypage(String aplyNo) throws Exception;

    /**
     * 예비조사 상세조회
     */
    public PrptExmnVO viewPrptExmn(PrptExmnVO prptExmnVO) throws Exception;

    /**
     * 2021.12.03 LSH
     * 예비조사 신청번호 기준 최종 조사차수 조회
     */
    public String getPrptExmnOderLast(String aplyNo) throws Exception;

    /**
     * 예비조사 등록,수정,삭제
     */
    public String savePrptExmn(PrptExmnVO prptExmnVO) throws Exception;

    /**
     * 예비조사 심의결과 저장
     */
    public String savePrptExmnRslt(PrptExmnVO prptExmnVO) throws Exception;

    /**
     * 예비조사계획 페이징목록 조회
     */
    public PaginatedArrayList listPrptExmnPlan(PrptExmnVO prptExmnVO, int currPage, int pageSize) throws Exception;

    /**
     * 예비조사계획 목록조회
     */
    public List listPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception;

    /**
     * 예비조사계획 상세조회
     */
    public PrptExmnVO viewPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception;

    /**
     * 예비조사계획 등록,수정,삭제
     */
    public String savePrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception;
    
    /**
     * 예비조사계획 NEXT 조사차수 가져오기
     */
	public String getPrptExmnPlanNextOder(PrptExmnVO prptExmnVO) throws Exception;
}