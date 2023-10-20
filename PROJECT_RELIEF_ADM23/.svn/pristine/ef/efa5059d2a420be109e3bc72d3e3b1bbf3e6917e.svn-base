package business.com.exmn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.exmn.service.DmgeGrdService;
import business.com.exmn.service.DmgeGrdVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 피해등급 Service 구현 클래스
 * 
 * @class   : DmgeGrdServiceImpl
 * @author  : LSH
 * @since   : 2022.12.22
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("DmgeGrdService")
@SuppressWarnings({"all"})
public class DmgeGrdServiceImpl extends BaseService implements DmgeGrdService {

    @Resource(name = "DmgeGrdDAO")
    private DmgeGrdDAO dmgeGrdDAO;

    /**
     * 피해등급 페이징목록조회
     */
    @Override
    public PaginatedArrayList listDmgeGrd(DmgeGrdVO dmgeGrdVO, int currPage, int pageSize) throws Exception {
    	return dmgeGrdDAO.listDmgeGrd(dmgeGrdVO, currPage, pageSize);
    }

    /**
     * 피해등급 목록조회
     */
    @Override
    public List listDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception {
    	return dmgeGrdDAO.listDmgeGrd(dmgeGrdVO);
    }

    /**
     * 피해등급 상세조회
     */
	@Override
	public DmgeGrdVO viewDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception {
		return dmgeGrdDAO.viewDmgeGrd(dmgeGrdVO);
	}

    /**
     * 피해등급 등록
     */
    private int regiDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception {
		return dmgeGrdDAO.regiDmgeGrd(dmgeGrdVO);
    }

    /**
     * 피해등급 수정
     */
    private int updtDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception {
		return dmgeGrdDAO.updtDmgeGrd(dmgeGrdVO);
    }

    /**
     * 피해등급 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception {
		return dmgeGrdDAO.deltDmgeGrd(dmgeGrdVO);
    }

    /**
     * 피해등급 저장처리 (등록,수정)
     * 1) mode=I (등록) : 기준년도별 등급이 없는 경우
     * 2) mode=U (수정) : 기준년도별 등급이 있는 경우
     *    - dmgeGrdList: mode, dmgeGrdCd, dmgeGrdYr, crtrIncomeAmt
     *                   svrtyBgngScre, svrtyEndScre
     *                   grdRate, grdAmt
	*/
	@Override
	public String saveDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception {
		
		if (dmgeGrdVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		List<DmgeGrdVO> list = dmgeGrdVO.getDmgeGrdList();

		for (DmgeGrdVO data : list) {

			data.setGsUserNo(dmgeGrdVO.getGsUserNo());

			if (CommConst.UPDATE.equals(data.getMode())) {
				// 피해등급관리 수정
				ret += updtDmgeGrd(data);
			}
			else if (CommConst.INSERT.equals(data.getMode())) {
				// 피해등급관리 등록
				ret += regiDmgeGrd(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 피해등급관리 년도별 목록 조회 
     */
	@Override
    public List listDmgeGrdGroup() throws Exception {
    	return dmgeGrdDAO.listDmgeGrdGroup();
    }

    /**
     * 피해등급 년도별 상세 조회
     */
	@Override
    public DmgeGrdVO viewDmgeGrdGroup(DmgeGrdVO dmgeGrdVO) throws Exception {
    	return dmgeGrdDAO.viewDmgeGrdGroup(dmgeGrdVO);
    }
}