package business.bio.relief.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.relief.service.BioIdntfcService;
import business.bio.relief.service.BioIdntfcVO;
import business.com.CommConst;
import common.base.BaseService;

/**
 * [서비스클래스] - 살생물제품 피해자정보 Service 구현 클래스
 * 
 * @class   : BioIdntfcServiceImpl
 * @author  : LSH
 * @since   : 2023.01.16
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
	public BioIdntfcVO viewBioIdntfc(String idntfcId) throws Exception {
		return idntfcDAO.viewBioIdntfc(idntfcId);
	}

    /**
     * 피해자정보 등록
     */
	@Override
	public int regiBioIdntfc(BioIdntfcVO idntfcVO) throws Exception {
		int ret = idntfcDAO.regiBioIdntfc(idntfcVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			idntfcVO.setLogTy(CommConst.INSERT);
			idntfcDAO.regiBioIdntfcHst(idntfcVO);
		}
		return ret;
    }

    /**
     * 피해자정보 수정
     */
	@Override
	public int updtBioIdntfc(BioIdntfcVO idntfcVO) throws Exception {
		int ret = idntfcDAO.updtBioIdntfc(idntfcVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			idntfcVO.setLogTy(CommConst.UPDATE);
			idntfcDAO.regiBioIdntfcHst(idntfcVO);
		}
		return ret;
    }
	
    /**
     * 성명/생년월일 기준 피해자정보 KEY 조회
     */
	@Override
    public String getBioIdntfcExistNo(BioIdntfcVO idntfcVO) throws Exception {
    	return idntfcDAO.getBioIdntfcExistNo(idntfcVO);
    }
}