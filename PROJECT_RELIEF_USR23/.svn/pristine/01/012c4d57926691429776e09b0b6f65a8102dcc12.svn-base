package business.com.exmn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.exmn.service.PrptExmnVO;
import business.com.exmn.service.ResiHstService;
import business.com.exmn.service.ResiHstVO;
import common.base.BaseService;

/**
 * [서비스클래스] - 거주이력정보 Service 구현 클래스
 * 
 * @class   : ResiHstServiceImpl
 * @author  : LSH
 * @since   : 2021.11.22
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("ResiHstService")
@SuppressWarnings({"all"})
public class ResiHstServiceImpl extends BaseService implements ResiHstService {

    @Resource(name = "ResiHstDAO")
    private ResiHstDAO resiHstDAO;

    /**
     * 거주이력정보 목록조회
     */
    @Override
    public List listResiHst(ResiHstVO resiHstVO) throws Exception {
    	return resiHstDAO.listResiHst(resiHstVO);
    }

    /**
     * 거주이력정보 상세조회
     */
	@Override
	public ResiHstVO viewResiHst(ResiHstVO resiHstVO) throws Exception {
		return resiHstDAO.viewResiHst(resiHstVO);
	}

    /**
     * 거주이력정보 등록
     */
    private int regiResiHst(ResiHstVO resiHstVO) throws Exception {
        return resiHstDAO.regiResiHst(resiHstVO);
    }

    /**
     * 거주이력정보 수정
     */
    private int updtResiHst(ResiHstVO resiHstVO) throws Exception {
        return resiHstDAO.updtResiHst(resiHstVO);
    }

    /**
     * 거주이력정보 삭제
     */
    private int deltResiHst(ResiHstVO resiHstVO) throws Exception {
        return resiHstDAO.deltResiHst(resiHstVO);
    }

    /**
     * 거주이력정보 다중저장처리
     */
	@Override
	public int saveResiHst(List<ResiHstVO> list, PrptExmnVO prptExmnVO) throws Exception {
		
		if (list == null || list.size() == 0)
			return 0;
		
		int ret = 0;
		
		for (ResiHstVO data : list) {
			
			data.setBizAreaCd(prptExmnVO.getBizAreaCd());
			data.setBizOder  (prptExmnVO.getBizOder  ());
			data.setExmnOder (prptExmnVO.getExmnOder ());
			data.setAplyNo   (prptExmnVO.getAplyNo   ());
			data.setGsUserNo (prptExmnVO.getGsUserNo ());

			String mode = data.getMode();

			if (CommConst.UPDATE.equals(mode)) {
				// 거주이력정보 수정
				ret += updtResiHst(data);
			}
			else if (CommConst.INSERT.equals(mode)) {
				// 거주이력정보 등록
				ret += regiResiHst(data);
			}
			else if (CommConst.DELETE.equals(mode)) {
				// 거주이력정보 삭제
				ret += deltResiHst(data);
			}
		}
		return ret;
	}
}