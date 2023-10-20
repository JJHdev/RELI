package business.com.exmn.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 본조사 Service Interface
 * 
 * @class   : MnsvyService
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface MnsvyService {

    /**
     * 본조사 페이징목록 조회
     */
    public PaginatedArrayList listMnsvy(MnsvyVO mnsvyVO, int currPage, int pageSize) throws Exception;

    /**
     * 본조사 목록조회
     */
    public List listMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 상세조회
     */
    public MnsvyVO viewMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 2021.12.03 LSH
     * 본조사 신청번호 기준 최종 조사차수 조회
     */
    public String getMnsvyOderLast(String aplyNo) throws Exception;

    /**
     * 본조사 등록,수정,삭제
     */
    public String saveMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 요양생활수당 저장
     */
    public String saveMnsvyRcper(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 심의결과 저장
     */
    public String saveMnsvyRslt(MnsvyVO mnsvyVO) throws Exception;
    /**
     * 2021.12.02
     * 본조사 심의결과 SMS 발송
     */
	public void sendMnsvyRsltSms(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사계획 페이징목록 조회
     */
    public PaginatedArrayList listMnsvyPlan(MnsvyVO mnsvyVO, int currPage, int pageSize) throws Exception;

    /**
     * 본조사계획 목록조회
     */
    public List listMnsvyPlan(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사계획 상세조회
     */
    public MnsvyVO viewMnsvyPlan(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사계획 등록,수정,삭제
     */
    public String saveMnsvyPlan(MnsvyVO mnsvyVO) throws Exception;
    
    /**
     * 본조사계획 NEXT 조사차수 가져오기
     */
	public String getMnsvyPlanNextOder(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 본조사 목록 조회
     */
    public List listMnsvyMypage(String aplyNo) throws Exception;
}