package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.exmn.service.LbdyNdxService;
import business.com.exmn.service.LbdyNdxVO;
import business.com.exmn.service.PrptExmnVO;
import business.com.exmn.service.ResiHstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 생체지표정보 Service 구현 클래스
 * 
 * @class   : LbdyNdxServiceImpl
 * @author  : LSH
 * @since   : 2021.11.22
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("LbdyNdxService")
@SuppressWarnings({"all"})
public class LbdyNdxServiceImpl extends BaseService implements LbdyNdxService {

    @Resource(name = "LbdyNdxDAO")
    private LbdyNdxDAO lbdyNdxDAO;

    /**
     * 생체지표정보 목록조회
     */
    @Override
    public List listLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception {
    	return lbdyNdxDAO.listLbdyNdx(lbdyNdxVO);
    }

    /**
     * 생체지표정보 상세조회
     */
	@Override
	public LbdyNdxVO viewLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception {
		return lbdyNdxDAO.viewLbdyNdx(lbdyNdxVO);
	}

    /**
     * 생체지표정보 등록
     */
    private int regiLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception {
        return lbdyNdxDAO.regiLbdyNdx(lbdyNdxVO);
    }

    /**
     * 생체지표정보 수정
     */
    private int updtLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception {
        return lbdyNdxDAO.updtLbdyNdx(lbdyNdxVO);
    }

    /**
     * 생체지표정보 삭제
     */
    private int deltLbdyNdx(LbdyNdxVO lbdyNdxVO) throws Exception {
        return lbdyNdxDAO.deltLbdyNdx(lbdyNdxVO);
    }

    /**
     * 생체지표정보 등록,수정,삭제
     */
	@Override
	public int saveLbdyNdx(List<LbdyNdxVO> list, PrptExmnVO prptExmnVO) throws Exception {
		
		if (list == null || list.size() == 0)
			return 0;
		
		int ret = 0;
		
		for (LbdyNdxVO data : list) {
			
			data.setBizAreaCd(prptExmnVO.getBizAreaCd());
			data.setBizOder  (prptExmnVO.getBizOder  ());
			data.setExmnOder (prptExmnVO.getExmnOder ());
			data.setAplyNo   (prptExmnVO.getAplyNo   ());
			data.setGsUserNo (prptExmnVO.getGsUserNo ());

			String mode = data.getMode();

			if (CommConst.UPDATE.equals(mode)) {
				// 생체지표정보 수정
				ret += updtLbdyNdx(data);
			}
			else if (CommConst.INSERT.equals(mode)) {
				// 생체지표정보 등록
				ret += regiLbdyNdx(data);
			}
			else if (CommConst.DELETE.equals(mode)) {
				// 생체지표정보 삭제
				ret += deltLbdyNdx(data);
			}
		}
		return ret;
	}
}