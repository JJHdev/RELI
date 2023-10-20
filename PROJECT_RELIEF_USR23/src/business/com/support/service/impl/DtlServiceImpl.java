package business.com.support.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.support.service.DtlService;
import business.com.support.service.LwstService;
import business.com.support.service.LwstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 취약계층소송지원을 관리하는 Service 구현 클래스
 * 
 * @class : LwstServiceImpl
 * @author : 한금주
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */

@Service("DtlService")
@SuppressWarnings({ "all" })
public class DtlServiceImpl extends BaseService implements DtlService {

	@Resource(name = "DtlDAO")
	private DtlDAO dtlDAO;

	// 향후기일목록 다중등록/다중수정/다중삭제
	@Override
	public Integer saveDtlsList(List<LwstVO> dtlsList, LwstVO lwstVO) throws Exception {
		
		if (dtlsList == null || dtlsList.size() == 0)
			return  0;
			
			int ret = 0;
			
			for (LwstVO data : dtlsList) {
				data.setIncdntMngNo(lwstVO.getIncdntMngNo());
				data.setRgtrNo(lwstVO.getRgtrNo());
				data.setRegYmd(lwstVO.getRegYmd());
				data.setMdfcnYmd(lwstVO.getMdfcnYmd());
				data.setMdfrNo(lwstVO.getMdfrNo());

				String mode = data.getMode();
				if (CommConst.UPDATE.equals(mode)) {
					ret += udtDtlList(data);
				} else if (CommConst.INSERT.equals(mode)) {
					ret += regiDtlList(data);
				} else if (CommConst.DELETE.equals(mode)) {
					ret += delDtlList(data);
				}
			}
		return ret;
	}
	
	// 향후기일목록 사건관리번호 기준 삭제
	public Integer deltDtlsListByMngNo(LwstVO lwstVO) throws Exception {
		
		// 사건관리번호가 없을경우 Exception 발생시킴
		if (lwstVO.getIncdntMngNo() == 0)
			throw processException("exception.notKey");
		
		// 사건관리번호 기준 삭제 처리
		return dtlDAO.delDtlListByMngNo(lwstVO);
	}

	private int regiDtlList(LwstVO lwstVO) {
		return dtlDAO.regiDtlList(lwstVO);
	}
	
	private int udtDtlList(LwstVO lwstVO) {
		return dtlDAO.udtDtlList(lwstVO);
	}
	private int delDtlList(LwstVO lwstVO) {
		return dtlDAO.delDtlList(lwstVO);
	}

	@Override
	public List listDtlsList(LwstVO lwstVO) throws Exception {
		return dtlDAO.listDtlsList(lwstVO);
	}

	@Override
	public LwstVO viewDtlsList(LwstVO lwstVO) throws Exception {
		return dtlDAO.viewDtlsList(lwstVO);
	}


}