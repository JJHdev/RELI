package business.com.cmit.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.cmit.service.CmitMngService;
import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 위원회관리 Service 구현 클래스
 * 
 * @class   : CmitMngServiceImpl
 * @author  : LSH
 * @since   : 2023.10.19
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("CmitMngService")
@SuppressWarnings({"all"})
public class CmitMngServiceImpl extends BaseService implements CmitMngService {

    @Resource(name = "CmitMngDAO")
    private CmitMngDAO cmitMngDAO;

    @Resource(name = "CmitDmgeDAO")
    private CmitDmgeDAO cmitDmgeDAO;
	
    /**
     * 위원회 페이징목록조회
     */
	@Override
	public PaginatedArrayList listCmitDmge(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception {
    	return cmitDmgeDAO.listCmitDmge(cmitMngVO, currPage, pageSize);
	}

    /**
     * 위원회 목록조회
     */
	@Override
	public List listCmitDmge(CmitMngVO cmitMngVO) throws Exception {
    	return cmitDmgeDAO.listCmitDmge(cmitMngVO);
	}

    /**
     * 위원회 상세조회
     */
	@Override
	public CmitMngVO viewCmitDmge(CmitMngVO cmitMngVO) throws Exception {
    	return cmitDmgeDAO.viewCmitDmge(cmitMngVO);
	}

    /**
     * 위원회 저장처리
     */
	@Override
	public String saveCmitDmge(CmitMngVO cmitMngVO) throws Exception {
		
		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = cmitMngVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 위원회 수정
			ret = cmitDmgeDAO.updtCmitDmge(cmitMngVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 위원회 등록
			ret = cmitDmgeDAO.regiCmitDmge(cmitMngVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 위원회 소속위원을 전체 삭제한다.
			cmitMngDAO.deltCmitMngAll(cmitMngVO);
			// 위원회 삭제
			ret = cmitDmgeDAO.deltCmitDmge(cmitMngVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 위원회 소속 위원 페이징목록조회
     */
    @Override
    public PaginatedArrayList listCmitMng(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception {
    	return cmitMngDAO.listCmitMng(cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회 소속 위원 목록조회
     */
    @Override
    public List listCmitMng(CmitMngVO cmitMngVO) throws Exception {
    	return cmitMngDAO.listCmitMng(cmitMngVO);
    }

    /**
     * 위원회 소속 위원 등록,수정,삭제
     */
	@Override
	public String saveCmitMng(CmitMngVO cmitMngVO) throws Exception {

		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");

		// 처리 모드
		String mode = cmitMngVO.getMode();
		// 결과 건수
		int ret = 0;
		
		// 선택삭제인 경우
		if (CommConst.DELETE.equals(mode)) {
			// 삭제 목록
			List<CmitMngVO> removeList = cmitMngVO.getRemoveList();

			if (CommUtils.isEmptyList(removeList))
				throw processException("error.comm.notTarget");
			
			// 위원회 소속 위원 삭제를 수행한다.
			for (CmitMngVO data : removeList) {
				ret += cmitMngDAO.deltCmitMng(data);
			}
		}
		else {
			// 등록 목록
			List<CmitMngVO> saveList = cmitMngVO.getSaveList();

			if (CommUtils.isEmptyList(saveList))
				throw processException("error.comm.notTarget");
			
			// 위원회 소속 위원 등록을 수행한다.
			for (CmitMngVO data : saveList) {
				data.setGsUserNo (cmitMngVO.getGsUserNo ());
				data.setCmitMngNo(cmitMngVO.getCmitMngNo());
				
				// 해당 위원회에 동일한 위원이 존재하는지 체크
				if (cmitMngDAO.existCmitMng(data)) {
					// 해당 위원회에 이미 등록된 위원입니다.
					throw processException("error.adm.cmitMng.duplMfcmm", 
						new String[] {data.getMfcmmOgdpNm() + " - " + data.getMfcmmNm()} 
					);
				}
				ret += cmitMngDAO.regiCmitMng(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2023.02.13 LSH
     * 임기번호가 속한 위원회가 있는지 체크
     */
	@Override
    public boolean existCmitMngForTenure(CmitMngVO cmitMngVO) throws Exception {
    	return (cmitMngDAO.getCmitMngCountForTenure(cmitMngVO) > 0);
	}
}