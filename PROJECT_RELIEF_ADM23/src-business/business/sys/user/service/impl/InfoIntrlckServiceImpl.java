package business.sys.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.AplyFileService;
import business.com.relief.service.IdntfcVO;
import business.sys.user.service.InfoIntrlckService;
import business.sys.user.service.InfoIntrlckVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 정보연동신청을 관리하는 Service 구현 클래스
 * 
 * @class : InfoIntrlckServiceImpl
 * @author : 한금주
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */

@Service("InfoIntrlckService")
@SuppressWarnings({ "all" })
public class InfoIntrlckServiceImpl extends BaseService implements InfoIntrlckService {

	@Resource(name = "InfoIntrlckDAO")
	private InfoIntrlckDAO infoIntrlckDAO;

    // 2021.12.11 LSH 신청파일정보
    @Resource(name = "AplyFileService")
    private AplyFileService aplyFileService;

	@Override
	public ArrayList searchInfoIntrlck(InfoIntrlckVO infoIntrlckVO) throws Exception {
		return (ArrayList) infoIntrlckDAO.searchInfoIntrlck(infoIntrlckVO);
	}

	/**
	 */
	@Override
	public Integer regiInfoIntrlck(InfoIntrlckVO infoIntrlckVO) throws Exception {
		if (infoIntrlckDAO.regiInfoIntrlck(infoIntrlckVO) > 0) {
			aplyFileService.saveIntrlckFile(infoIntrlckVO);
			return 1;
		}
		return 0;
	}

	@Override
	public PaginatedArrayList listInfoIntrlck(InfoIntrlckVO infoIntrlckVO, int currPage, int pageSize) throws Exception {
		return infoIntrlckDAO.listInfoIntrlck(infoIntrlckVO,currPage,pageSize);
	}

	@Override
	public List listInfoIntrlck(InfoIntrlckVO infoIntrlckVO) throws Exception {
		return infoIntrlckDAO.listInfoIntrlck(infoIntrlckVO);
	}

	@Override
	public InfoIntrlckVO viewIntrlckList(String aplyNo) throws Exception {
		return infoIntrlckDAO.viewIntrlckList(aplyNo);
	}

	@Override
	public Integer updateIntrlckList(InfoIntrlckVO infoIntrlckVO) throws Exception {
		if (infoIntrlckVO == null)
			throw processException("error.comm.notTarget");	
		
		int ret = 0;
		return infoIntrlckDAO.updateIntrlckList(infoIntrlckVO);
	}
}