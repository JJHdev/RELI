package business.com.biz.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.biz.service.BizMngService;
import business.com.biz.service.BizMngVO;
import business.sys.user.service.InfoIntrlckVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 사업관리하는 Service 구현 클래스
 * 
 * @class : BizMngServiceImpl
 * @author : 김주호
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */

@Service("BizMngService")
@SuppressWarnings({ "all" })
public class BizMngServiceImpl extends BaseService implements BizMngService {

	@Resource(name = "BizMngDAO")
	private BizMngDAO bizMngDAO;

	@Override
	public List listBizMng(BizMngVO bizMngVO) throws Exception {
		List list = bizMngDAO.listBizMng(bizMngVO);
		if (list == null)
			throw processException("exception.NoResult");
		return list;
	}

	/*
	 * 사업관리 페이징목록 조회
	 */
	@Override
	public PaginatedArrayList getListBizMng(BizMngVO bizMngVO, int currPage, int pageSize) throws Exception {
		PaginatedArrayList list = bizMngDAO.getListBizMng(bizMngVO, currPage, pageSize);
		if (list == null)
			throw processException("exception.NoResult");
		return list;
	}

	/*
	 * 사업관리 목록 조회
	 */
	@Override
	public List getListBizMng(BizMngVO bizMngVO) throws Exception {
		List list = bizMngDAO.getListBizMng(bizMngVO);
		if (list == null)
			throw processException("exception.NoResult");
		return list;
	}

	/**
	 * 사업관리 상세조회
	 */
	@Override
	public BizMngVO viewListBizMng(BizMngVO bizMngVO) throws Exception {
		BizMngVO result = bizMngDAO.viewListBizMng(bizMngVO);
		if (result == null)
			throw processException("exception.NoResult");
		return result;
	}
	/**
	 * 사업관리 - 신규사업등록
	 */
	@Override
	public Integer saveNewBiz(BizMngVO bizMngVO) throws Exception {
		int result = bizMngDAO.saveNewBiz(bizMngVO);
		if (result == 0) {
			throw processException("exception.NoResult");
		}
		return result;

	}
    /*
     * 사업관리 - 기존사업 추가 차수 검색
     */
	@Override
	public BizMngVO searchGernOder(BizMngVO bizMngVO) throws Exception {
		BizMngVO result = bizMngDAO.searchGernOder(bizMngVO);
		if (result == null)
			throw processException("exception.NoResult");
		return result;
	}

	@Override
	public BizMngVO getBizKey(BizMngVO bizMngVO) throws Exception {
		BizMngVO result = bizMngDAO.getBizKey(bizMngVO);
		if (result == null)
			throw processException("exception.NoResult");
		return result;
	}

	@Override
	public Integer saveOder(BizMngVO bizMngVO) throws Exception {
		int result =  bizMngDAO.saveOder(bizMngVO);
		return result;
	}
    /*
     * 사업관리 - 기존사업 차수추가등록
     */
	@Override
	public Integer addAreaOder(BizMngVO bizMngVO) throws Exception {
		int result = bizMngDAO.addAreaOder(bizMngVO);
		if (result == 0) {
			throw processException("exception.NoResult");
		}
		return result;
	}
	 /*
     * 사업관리 - 사업 수정 (사업 이름, 사업내용)
     * 
     **/
	@Override
	public Integer modifyBiz(BizMngVO bizMngVO) throws Exception {
		int saveArea = bizMngDAO.modifyAREA(bizMngVO);
		int result = bizMngDAO.modifyBiz(bizMngVO);
		
		if (result == 0 || saveArea == 0) {
			throw processException("exception.NoResult");
		}
		return result;
	}
	 /*
     * 사업관리 - 사업 삭제 (예비조사 계획이 존재할실 삭제 불가능)
     * 
     **/
	@Override
	public Integer deleteBiz(BizMngVO bizMngVO) throws Exception {
		
		int result = bizMngDAO.deleteBiz(bizMngVO);

			if (result == 0 ) {
				throw processException("exception.NoResult");
			}
			return result;
	
	}
	/**
     * 사업관리 화면 - 사업 차수 삭제중 예비조사 계획 유무 확인 메소드 
     */	
	@Override
	public BizMngVO prptExmnCount(BizMngVO bizMngVO) throws Exception {
		BizMngVO prptExmnCount = bizMngDAO.prptExmnCount(bizMngVO);
		
		if (prptExmnCount == null) {
			throw processException("exception.NoResult");
		}
			
			return prptExmnCount;
		
		
	}
	/**
     * 사업관리 화면 - 사업 삭제
     */
	@Override
	public int allDeleteBizOder(BizMngVO bizMngVO) throws Exception {
		int allDeleteBizOder = bizMngDAO.allDeleteBizOder(bizMngVO);
		int allDeleteBiz = bizMngDAO.allDeleteBiz(bizMngVO);
		

		if (allDeleteBiz == 0 && allDeleteBizOder==0) {
			throw processException("exception.NoResult");
		}
		return allDeleteBiz+allDeleteBizOder;
	}
	/**
     * 사업관리 화면 - 사업 삭제중 예비조사 계획 유무 확인 메소드 
     */	
	@Override
	public BizMngVO allPrptExmnCount(BizMngVO bizMngVO) throws Exception {
		BizMngVO allPrptExmnCount = bizMngDAO.allPrptExmnCount(bizMngVO);
		
		if (allPrptExmnCount == null) {
			throw processException("exception.NoResult");
		}
			
			return allPrptExmnCount;
	}
	/**
     * 사업관리 화면 - 사업 차수 삭제중 사업차수가 존재하는지 확인  
     */	
	@Override
	public BizMngVO oderCount(BizMngVO bizMngVO) throws Exception {
		BizMngVO oderCount = bizMngDAO.oderCount(bizMngVO);
		if (oderCount == null) {
			throw processException("exception.NoResult");
		}
		return oderCount;
	}
	
	/**
     * GIS분석-초본주소 등록에서 피해자 피해지역 조회
     */		
	@Override
	public String findBizAreaBySufrerNo(String sufrerNo) throws Exception {
		String bizArea = bizMngDAO.findBizAreaBySufrerNo(sufrerNo);
		return bizArea;
	}
	
	/**
     * GIS분석-초본주소 등록에서 피해자 피해지역 조회
     */		
	@Override
	public Map findPollutnLiveBySufrerNo(String sufrerNo) throws Exception {
		Map result = bizMngDAO.findPollutnLiveBySufrerNo(sufrerNo);
		return result;
	}
	
	/**
     * GIS분석-초본주소 등록에서 피해자 거주지역  상세정보 조회
     */		
	@Override
	public BizMngVO viewBizMngBySufrerNo(String sufrerNo) throws Exception {
		BizMngVO result = (BizMngVO)bizMngDAO.viewBizMngBySufrerNo(sufrerNo);
		return result;
	}
	/**
     * GIS분석-초본주소 등록에서 피해거주기간 조회
     */		
	@Override
	public Map findPollutnLiveByBizMngEntity(BizMngVO bizMngVO) throws Exception {
		Map result = bizMngDAO.findPollutnLiveByBizMngEntity(bizMngVO);
		return result;
	}

}