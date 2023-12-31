package business.com.biz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import business.sys.user.service.InfoIntrlckVO;
import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 사업관리하는 Service Interface
 * 
 * @class   : BizMngService
 * @author  : 김주호
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface BizMngService {

	public List listBizMng(BizMngVO bizMngVO) throws Exception;

    /**
     * 사업관리 페이징목록조회
     */
	public PaginatedArrayList getListBizMng(BizMngVO bizMngVO, int currPage, int pageSize) throws Exception;
    /**
     *사업관리 목록조회
     */
	public List getListBizMng(BizMngVO bizMngVO) throws Exception;
    /**
     *사업관리 상세조회
     */
	public BizMngVO viewListBizMng(BizMngVO bizMngVO)throws Exception;
	 /**
     * 사업관리 - 신규사업 등록
     */
	public Integer saveNewBiz(BizMngVO bizMngVO) throws Exception;
     /*
      * 사업관리 - 기존사업 추가 차수 검색
      */
	public BizMngVO searchGernOder(BizMngVO bizMngVO)throws Exception;

	/*
	 *  사업관리 - 추가한 사업 pk값가져오기
	 * @param bizMngVO
	 * @return
	 */
	public BizMngVO getBizKey(BizMngVO bizMngVO) throws Exception;

	public Integer saveOder(BizMngVO bizMngVO) throws Exception;

	public Integer addAreaOder(BizMngVO bizMngVO) throws Exception;
	/*
     * 사업관리 - 사업 수정
     */
	public Integer modifyBiz(BizMngVO bizMngVO)throws Exception;
	/*
     * 사업관리 - 사업 삭제
     */
	public Integer deleteBiz(BizMngVO bizMngVO)throws Exception;
    /*
     * 사업관리 - 사업 삭제중에 예비조사 계획확인
     */
	public BizMngVO prptExmnCount(BizMngVO bizMngVO)throws Exception;
	/**
     * 사업관리 화면 - 사업 삭제
     */
	public int allDeleteBizOder(BizMngVO bizMngVO)throws Exception;
	/**
     * 사업관리 화면 - 사업 삭제중 예비조사 계획 유무 확인 메소드 
     */	
	public BizMngVO allPrptExmnCount(BizMngVO bizMngVO)throws Exception;
	/**
     * 사업관리 화면 - 사업 차수 삭제중 사업차수가 존재하는지 확인  
     */	
	public BizMngVO oderCount(BizMngVO bizMngVO)throws Exception;
	/**
     * GIS분석-초본주소 등록에서 피해자 피해지역 조회
     */	
	String findBizAreaBySufrerNo(String sufrerNo) throws Exception;
	
	/**
     * GIS분석-초본주소 등록에서 피해자 피해지역, 최초/최종오염 발생년도, 피해지역 거주기간 조회
     */	
	public Map findPollutnLiveBySufrerNo(String sufrerNo) throws Exception;
	/**
     * GIS분석-초본주소 등록에서 피해자 거주지역  상세정보 조회
     */
	public BizMngVO viewBizMngBySufrerNo(String sufrerNo) throws Exception;
	/**
     * GIS분석-초본주소 등록에서 피해거주기간 조회
     */
	public Map findPollutnLiveByBizMngEntity(BizMngVO bizMngVO) throws Exception;
	/**
     * GIS분석-초본주소 등록에서 식별ID로 피해지역 조회
     */
	public BizMngVO viewBizMngByIdntfcId(String idntfcId) throws Exception;
}