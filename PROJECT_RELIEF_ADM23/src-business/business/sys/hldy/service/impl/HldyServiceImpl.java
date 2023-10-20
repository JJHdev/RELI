package business.sys.hldy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.sys.hldy.service.HldyService;
import business.sys.hldy.service.HldyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
/**
 * [서비스클래스] - 공휴일관리를 관리하는 Service 구현 클래스
 * 
 * @class   : HdlyServiceImpl
 * @author  : KSH
 * @since   : 2023.02.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("HldyService")
@SuppressWarnings({"all"})
public class HldyServiceImpl extends BaseService implements HldyService {
	
	@Resource(name="HldyDAO")
	private HldyDAO hldyDAO;
	/**
     * 공휴일관리 목록조회
     */
	@Override
	public List listHldy(HldyVO HldyVO) throws Exception {
		return hldyDAO.listHldy(HldyVO);
	}
	/**
     * 공휴일관리 페이징목록조회
     */
	@Override
	public PaginatedArrayList listHldy(HldyVO HldyVO, int currPage, int pageSize) throws Exception{
		PaginatedArrayList list = hldyDAO.listHldy(HldyVO, currPage, pageSize);
		if(list == null)
			throw processException("exception.NoResult");
		return list;
	}	
	/**
     * 공휴일관리 상세조회
     */
	@Override
	public HldyVO viewHldy(HldyVO hldyVO) throws Exception {
		return hldyDAO.viewHldy(hldyVO);
	}
	/**
     * 공휴일관리 삭제
     */
	private int deltHldy(HldyVO hldyVo) throws Exception{
		return hldyDAO.deltHldy(hldyVo);
	}
	/**
     * 공휴일관리 등록
     */
	private int regiHldy(HldyVO hldyVo) {
		return hldyDAO.regiHldy(hldyVo);
	}
	/**
     * 공휴일관리 수정
     */
	private int updtHldy(HldyVO hldyVo) {
		return hldyDAO.updtHldy(hldyVo);
	}
	 /**
     * 공휴일관리 등록,수정,삭제
     */
	@Override
	public String saveHldy(HldyVO hldyVo) throws Exception {
		
		if (hldyVo == null)
			throw processException("error.comm.notTarget");
		int ret=0;
		String mode= hldyVo.getMode();
		String oldHldyYmd = hldyVo.getOldHldyYmd();
		
		String[] args = { oldHldyYmd };
		
		if (CommConst.UPDATE.equals(mode)) {			
			// 공휴일관리 수정
			ret = updtHldy(hldyVo);
		}
		else if(CommConst.INSERT.equals(mode)) {
			// 공휴일 중복체크
			if (viewHldy(hldyVo) != null) {
			//이미 존재하는 데이터 입니다. 확인 후 다시 하시기 바랍니다.
			throw processException("prompt.duplicate", args);
			}	
			//공휴일 등록
			ret = regiHldy(hldyVo);
		}
		else if(CommConst.DELETE.equals(mode)) {
			//공휴일 삭제
			ret = deltHldy(hldyVo);
		}	
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}
