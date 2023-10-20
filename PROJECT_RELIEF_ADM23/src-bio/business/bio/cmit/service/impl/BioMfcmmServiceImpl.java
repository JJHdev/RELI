package business.bio.cmit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.cmit.service.BioCmitMngService;
import business.bio.cmit.service.BioCmitMngVO;
import business.bio.cmit.service.BioMfcmmService;
import business.com.CommConst;
import commf.exception.BusinessException;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 살생물제품 위원정보 Service 구현 클래스
 * 
 * @class   : BioMfcmmServiceImpl
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Service("BioMfcmmService")
@SuppressWarnings({"all"})
public class BioMfcmmServiceImpl extends BaseService implements BioMfcmmService {

    @Resource(name = "BioMfcmmDAO")
    private BioMfcmmDAO mfcmmDAO;

    @Resource(name = "BioMfcmmTenureDAO")
    private BioMfcmmTenureDAO mfcmmTenureDAO;

    @Resource(name = "BioCmitMngService")
    private BioCmitMngService cmitMngService;
	
    /**
     * 위원정보 페이징목록조회
     */
    @Override
    public PaginatedArrayList listBioMfcmm(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception {
    	return mfcmmDAO.listBioMfcmm(bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 위원정보 목록조회
     */
    @Override
    public List listBioMfcmm(BioCmitMngVO bioCmitMngVO) throws Exception {
    	return mfcmmDAO.listBioMfcmm(bioCmitMngVO);
    }

    /**
     * 위원정보 상세조회
     */
	@Override
	public BioCmitMngVO viewBioMfcmm(BioCmitMngVO bioCmitMngVO) throws Exception {
		return mfcmmDAO.viewBioMfcmm(bioCmitMngVO);
	}

    /**
     * 위원정보 등록,수정,삭제
     */
	@Override
	public String saveBioMfcmm(BioCmitMngVO bioCmitMngVO) throws Exception {
		
		if (bioCmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = bioCmitMngVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 위원정보 수정
			ret = mfcmmDAO.updtBioMfcmm(bioCmitMngVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 위원정보 등록
			ret = mfcmmDAO.regiBioMfcmm(bioCmitMngVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 위원정보 삭제
			ret = mfcmmDAO.deltBioMfcmm(bioCmitMngVO);
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
	public List listBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) throws Exception {
    	return mfcmmTenureDAO.listBioMfcmmTenure(bioCmitMngVO);
	}

    /**
     * 위원임기차수 다중항목 저장처리
     */
	@Override
	public String saveBioMfcmmTenure(BioCmitMngVO bioCmitMngVO) throws Exception {
		
		if (bioCmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		// 수정이 아닌 경우
		if (!CommConst.UPDATE.equals(bioCmitMngVO.getMode()))
			throw processException("error.comm.notValid");

		// 등록,수정 목록
		List<BioCmitMngVO> saveList = bioCmitMngVO.getSaveList();
		// 삭제 목록
		List<BioCmitMngVO> removeList = bioCmitMngVO.getRemoveList();
		
		if (CommUtils.isEmptyList(saveList  ) &&
			CommUtils.isEmptyList(removeList))
			throw processException("error.comm.notTarget");

		int ret = 0;
		String msg = "";
		// 항목 삭제를 먼저 수행한다.
		for (BioCmitMngVO data : removeList) {
			if (CommConst.UPDATE.equals(data.getMode())) {
				// 2023.02.13 위원회에 등록되어 있는지 체크
				if (cmitMngService.existBioCmitMngForTenure(data)) {
					msg  = "위원회에 등록된 임기정보는 삭제하실 수 없습니다.<br>";
					msg += "[ 번호: "+data.getTenureNo();
					msg += ", 차수: "+data.getTenureOder();
					msg += ", 시작: "+data.getTenureBgngYmd();
					msg += ", 종료: "+data.getTenureEndYmd()+" ]";
					throw new BusinessException(msg);
				}
				// 위원임기차수 삭제
				ret += mfcmmTenureDAO.deltBioMfcmmTenure(data);
			}
		}
		// 항목 등록,수정을 수행한다.
		for (BioCmitMngVO data : saveList) {
			data.setGsUserNo(bioCmitMngVO.getGsUserNo());
			data.setMfcmmNo (bioCmitMngVO.getMfcmmNo ());
			data.setCmitSeCd(bioCmitMngVO.getCmitSeCd());

			if (CommConst.UPDATE.equals(data.getMode())) {
				// 위원임기차수 수정
				ret += mfcmmTenureDAO.updtBioMfcmmTenure(data);
			}
			else if (CommConst.INSERT.equals(data.getMode())) {
				// 위원임기차수 등록
				ret += mfcmmTenureDAO.regiBioMfcmmTenure(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

	/**
     * 위원회 등록대상 위원정보 페이징목록 조회
     */
	@Override
	public PaginatedArrayList listBioMfcmmTarget(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) throws Exception {
		return mfcmmDAO.listBioMfcmmTarget(bioCmitMngVO, currPage, pageSize);
	}

    /**
     * 위원회 등록대상 위원정보 목록조회
     */
	@Override
	public List listBioMfcmmTarget(BioCmitMngVO bioCmitMngVO) throws Exception {
		return mfcmmDAO.listBioMfcmmTarget(bioCmitMngVO);
	}
}