package business.com.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.biz.service.MngHstService;
import business.com.biz.service.MngHstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 관리이력정보 Service 구현 클래스
 * 
 * @class   : MngHstServiceImpl
 * @author  : LSH
 * @since   : 2021.10.21
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("MngHstService")
@SuppressWarnings({"all"})
public class MngHstServiceImpl extends BaseService implements MngHstService {

    @Resource(name = "MngHstDAO")
    private MngHstDAO mngHstDAO;
	
    /**
     * 관리이력정보 페이징목록조회
     */
    @Override
    public PaginatedArrayList listMngHst(MngHstVO mngHstVO, int currPage, int pageSize) throws Exception {
    	return mngHstDAO.listMngHst(mngHstVO, currPage, pageSize);
    }

    /**
     * 관리이력정보 목록조회
     */
    @Override
    public List listMngHst(MngHstVO mngHstVO) throws Exception {
    	return mngHstDAO.listMngHst(mngHstVO);
    }

    /**
     * 관리이력정보 상세조회
     */
	@Override
	public MngHstVO viewMngHst(String sn) throws Exception {
		return mngHstDAO.viewMngHst(sn);
	}

    /**
     * 관리이력정보 등록
     */
    private int regiMngHst(MngHstVO mngHstVO) throws Exception {
        return mngHstDAO.regiMngHst(mngHstVO);
    }

    /**
     * 관리이력정보 수정
     */
    private int updtMngHst(MngHstVO mngHstVO) throws Exception {
        return mngHstDAO.updtMngHst(mngHstVO);
    }

    /**
     * 관리이력정보 삭제
     */
    private int deltMngHst(String sn) throws Exception {
        return mngHstDAO.deltMngHst(sn);
    }

    /**
     * 관리이력정보 등록,수정,삭제
     */
	@Override
	public String saveMngHst(MngHstVO mngHstVO) throws Exception {
		
		if (mngHstVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = mngHstVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 관리이력정보 수정
			ret = updtMngHst(mngHstVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 관리이력정보 등록
			ret = regiMngHst(mngHstVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 관리이력정보 삭제
			ret = deltMngHst(mngHstVO.getSn());
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}