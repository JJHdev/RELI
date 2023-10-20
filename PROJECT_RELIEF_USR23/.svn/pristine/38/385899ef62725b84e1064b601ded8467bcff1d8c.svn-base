package business.com.support.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.support.service.DtlService;
import business.com.support.service.LwstService;
import business.com.support.service.LwstVO;
import business.com.support.service.SprtAplyService;
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

@Service("SprtAplyService")
@SuppressWarnings({ "all" })
public class SprtAplyServiceImpl extends BaseService implements SprtAplyService {

	@Resource(name = "SprtAplyDAO")
	private SprtAplyDAO sprtAplyDAO;

	@Override
	public Integer saveSprtList(List<LwstVO> sprtList, LwstVO lwstVO) throws Exception {
		if (sprtList == null || sprtList.size() == 0)
			return 0;

		int ret = 0;

		for (LwstVO data : sprtList) {
			data.setAplyNo(lwstVO.getAplyNo());
			data.setRgtrNo(lwstVO.getRgtrNo());
			data.setRegYmd(lwstVO.getRegYmd());
			data.setMdfcnYmd(lwstVO.getMdfcnYmd());
			data.setMdfrNo(lwstVO.getMdfrNo());

			String mode = data.getMode();
			if (CommConst.UPDATE.equals(mode)) {
				ret += udtSprtList(data);
			} else if (CommConst.INSERT.equals(mode)) {
				ret += regiSprtList(data);
			} else if (CommConst.DELETE.equals(mode)) {
				ret += delSprtList(data);
			}
		}
		return ret;
	}

	@Override
	public Integer delSprtListAplyNo(LwstVO lwstVO) throws Exception {

		if (lwstVO.getAplyNo() == null)
			throw processException("exception.notKey");
		return sprtAplyDAO.delSprtListAplyNo(lwstVO);
	}

	private int regiSprtList(LwstVO lwstVO) {
		return sprtAplyDAO.regiSprtList(lwstVO);
	}

	private int delSprtList(LwstVO lwstVO) {
		return sprtAplyDAO.delSprtList(lwstVO);
	}

	private int udtSprtList(LwstVO lwstVO) {
		return sprtAplyDAO.udtSprtList(lwstVO);
	}

	@Override
	public List listSprtList(LwstVO lwstVO) throws Exception {
		return sprtAplyDAO.listSprtList(lwstVO);
	}

	@Override
	public LwstVO viewSprtList(LwstVO lwstVO) throws Exception {
		return sprtAplyDAO.viewSprtList(lwstVO);
	}

	@Override
	public List listLwstSprtAply(LwstVO lwstVO) throws Exception {
		return sprtAplyDAO.listLwstSprtAply(lwstVO);
	}

}