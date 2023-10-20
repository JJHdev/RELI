package business.com.cmit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.cmit.service.CmitMngService;
import business.com.cmit.service.CmitMngVO;
import business.com.cmit.service.MfcmmService;
import commf.exception.BusinessException;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 위원정보 Service 구현 클래스
 * 
 * @class   : MfcmmServiceImpl
 * @author  : LSH
 * @since   : 2023.01.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("MfcmmService")
@SuppressWarnings({"all"})
public class MfcmmServiceImpl extends BaseService implements MfcmmService {

    @Resource(name = "MfcmmDAO")
    private MfcmmDAO mfcmmDAO;

    @Resource(name = "MfcmmTenureDAO")
    private MfcmmTenureDAO mfcmmTenureDAO;

    @Resource(name = "CmitMngService")
    private CmitMngService cmitMngService;
	
    /**
     * 위원정보 페이징목록조회
     */
    @Override
    public PaginatedArrayList listMfcmm(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception {
    	return mfcmmDAO.listMfcmm(cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원정보 목록조회
     */
    @Override
    public List listMfcmm(CmitMngVO cmitMngVO) throws Exception {
    	return mfcmmDAO.listMfcmm(cmitMngVO);
    }

    /**
     * 위원정보 상세조회
     */
	@Override
	public CmitMngVO viewMfcmm(CmitMngVO cmitMngVO) throws Exception {
		return mfcmmDAO.viewMfcmm(cmitMngVO);
	}

    /**
     * 위원정보 등록,수정,삭제
     */
	@Override
	public String saveMfcmm(CmitMngVO cmitMngVO) throws Exception {
		
		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = cmitMngVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 위원정보 수정
			ret = mfcmmDAO.updtMfcmm(cmitMngVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 위원정보 등록
			ret = mfcmmDAO.regiMfcmm(cmitMngVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 위원정보 삭제
			ret = mfcmmDAO.deltMfcmm(cmitMngVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 위원임기차수 목록조회
     */
	@Override
	public List listMfcmmTenure(CmitMngVO cmitMngVO) throws Exception {
    	return mfcmmTenureDAO.listMfcmmTenure(cmitMngVO);
	}

    /**
     * 위원임기차수 다중항목 저장처리
     */
	@Override
	public String saveMfcmmTenure(CmitMngVO cmitMngVO) throws Exception {
		
		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		// 수정이 아닌 경우
		if (!CommConst.UPDATE.equals(cmitMngVO.getMode()))
			throw processException("error.comm.notValid");

		// 등록,수정 목록
		List<CmitMngVO> saveList = cmitMngVO.getSaveList();
		// 삭제 목록
		List<CmitMngVO> removeList = cmitMngVO.getRemoveList();
		
		if (CommUtils.isEmptyList(saveList  ) &&
			CommUtils.isEmptyList(removeList))
			throw processException("error.comm.notTarget");

		int ret = 0;
		String msg = "";
		// 항목 삭제를 먼저 수행한다.
		for (CmitMngVO data : removeList) {
			if (CommConst.UPDATE.equals(data.getMode())) {
				// 2023.02.13 위원회에 등록되어 있는지 체크
				if (cmitMngService.existCmitMngForTenure(data)) {
					msg  = "위원회에 등록된 임기정보는 삭제하실 수 없습니다.<br>";
					msg += "[ 번호: "+data.getTenureNo();
					msg += ", 차수: "+data.getTenureOder();
					msg += ", 시작: "+data.getTenureBgngYmd();
					msg += ", 종료: "+data.getTenureEndYmd()+" ]";
					throw new BusinessException(msg);
				}
				// 위원임기차수 삭제
				ret += mfcmmTenureDAO.deltMfcmmTenure(data);
			}
		}
		// 항목 등록,수정을 수행한다.
		for (CmitMngVO data : saveList) {
			data.setGsUserNo(cmitMngVO.getGsUserNo());
			data.setMfcmmNo (cmitMngVO.getMfcmmNo ());
			data.setCmitSeCd(cmitMngVO.getCmitSeCd());

			if (CommConst.UPDATE.equals(data.getMode())) {
				// 위원임기차수 수정
				ret += mfcmmTenureDAO.updtMfcmmTenure(data);
			}
			else if (CommConst.INSERT.equals(data.getMode())) {
				// 위원임기차수 등록
				ret += mfcmmTenureDAO.regiMfcmmTenure(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

	/**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 페이징목록 조회
     */
	@Override
	public PaginatedArrayList listMfcmmTarget(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception {
		return mfcmmDAO.listMfcmmTarget(cmitMngVO, currPage, pageSize);
	}

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 목록조회
     */
	@Override
	public List listMfcmmTarget(CmitMngVO cmitMngVO) throws Exception {
		return mfcmmDAO.listMfcmmTarget(cmitMngVO);
	}
}