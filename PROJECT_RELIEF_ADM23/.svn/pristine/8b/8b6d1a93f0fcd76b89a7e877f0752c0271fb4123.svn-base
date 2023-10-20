package business.bio.relief.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.relief.service.BioIdntfcService;
import business.bio.relief.service.BioIdntfcVO;
import business.com.CommConst;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 살생물제품 피해자정보 Service 구현 클래스
 * 
 * @class   : BioIdntfcServiceImpl
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("BioIdntfcService")
@SuppressWarnings({"all"})
public class BioIdntfcServiceImpl extends BaseService implements BioIdntfcService {

    @Resource(name = "BioIdntfcDAO")
    private BioIdntfcDAO idntfcDAO;

    /**
     * 피해자정보 상세조회
     */
	@Override
	public BioIdntfcVO viewBioIdntfc(String sufrerNo) throws Exception {
		return idntfcDAO.viewBioIdntfc(sufrerNo);
	}

    /**
     * 피해자정보 등록
     */
	@Override
	public int regiBioIdntfc(BioIdntfcVO bioIdntfcVO) throws Exception {
		int ret = idntfcDAO.regiBioIdntfc(bioIdntfcVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			bioIdntfcVO.setLogTy(CommConst.INSERT);
			idntfcDAO.regiBioIdntfcHst(bioIdntfcVO);
		}
		return ret;
    }

    /**
     * 피해자정보 수정
     */
	@Override
	public int updtBioIdntfc(BioIdntfcVO bioIdntfcVO) throws Exception {
		int ret = idntfcDAO.updtBioIdntfc(bioIdntfcVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			bioIdntfcVO.setLogTy(CommConst.UPDATE);
			idntfcDAO.regiBioIdntfcHst(bioIdntfcVO);
		}
		return ret;
    }

    /**
     * 성명/생년월일 기준 피해자정보 KEY 조회
     */
    public String getBioIdntfcExistNo(BioIdntfcVO bioIdntfcVO) throws Exception {
    	return idntfcDAO.getBioIdntfcExistNo(bioIdntfcVO);
    }
    
    /**
     * 구제급여 신청접수처리 (식별ID 생성반환)
     */
	@Override
	public String saveBioIdntfcReceipt(BioIdntfcVO bioIdntfcVO) throws Exception {
		
		if (bioIdntfcVO == null)
			throw processException("error.comm.notTarget");
		
		// 2023.01.06 피해자정보 조회
		BioIdntfcVO obj = viewBioIdntfc(bioIdntfcVO.getSufrerNo());
		// 2023.01.06 식별ID가 없을 경우에만 신규 생성
		if (CommUtils.isEmpty(obj.getIdntfcId())) {
			// 식별ID 신규생성
			bioIdntfcVO.setIdntfcId(idntfcDAO.getCreateBioIdntfcId());
			if (updtBioIdntfc(bioIdntfcVO) > 0)
				return bioIdntfcVO.getIdntfcId();
			return null;
		}
		// 2023.01.06 식별ID가 있을 경우 식별ID 반환
		else {
			return obj.getIdntfcId();
		}
	}
}