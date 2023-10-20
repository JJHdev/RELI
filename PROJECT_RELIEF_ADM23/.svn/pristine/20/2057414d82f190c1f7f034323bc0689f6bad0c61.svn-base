package business.sys.code.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.sys.code.service.SckwndService;
import business.sys.code.service.SckwndVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 상병코드관리을 관리하는 Service 구현 클래스
 * 
 * @class   : SckwndServiceImpl
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("SckwndService")
@SuppressWarnings({"all"})
public class SckwndServiceImpl extends BaseService implements SckwndService {

    @Resource(name = "SckwndDAO")
    private SckwndDAO sckwndDAO;
	
    /**
     * 상병코드관리 페이징목록조회
     */
    @Override
    public PaginatedArrayList listSckwnd(SckwndVO sckwndVO, int currPage, int pageSize) throws Exception {
    	return sckwndDAO.listSckwnd(sckwndVO, currPage, pageSize);
    }

    /**
     * 상병코드관리 목록조회
     */
    @Override
    public List listSckwnd(SckwndVO sckwndVO) throws Exception {
    	return sckwndDAO.listSckwnd(sckwndVO);
    }

    /**
     * 상병코드관리 계층형 목록 조회
     */
    @Override
    public List listSckwndTree(SckwndVO sckwndVO) throws Exception {
    	return sckwndDAO.listSckwndTree(sckwndVO);
    }

    /**
     * 상병코드관리 상세조회
     */
	@Override
	public SckwndVO viewSckwnd(SckwndVO sckwndVO) throws Exception {
		return sckwndDAO.viewSckwnd(sckwndVO);
	}

    /**
     * 상병코드관리 등록
     */
    private int regiSckwnd(SckwndVO sckwndVO) throws Exception {
    	
    	String sckwndCd   = sckwndVO.getSckwndCd();
    	String upSckwndCd = sckwndVO.getUpSckwndCd();
    	
        // 상위 코드와 하위 코드가 동일한 경우
    	if (CommUtils.isEqual(sckwndCd, upSckwndCd)) {
            throw processException("exception.adm.sameUpperLowerCode", new String[] {sckwndCd});
    	}
        int count = 0;

        // 코드를 확인한다.
        count = (Integer) sckwndDAO.confSckwnd(sckwndVO);

        // 코드가 존재하는 경우
        if (count > 0) {
            // 상위 코드인 경우
            if (CommUtils.isEmpty(upSckwndCd)) {
                throw processException("exception.adm.duplUpperCode", new String[] {sckwndCd});
            }
            // 하위 코드인 경우
            else {
                throw processException("exception.adm.duplLowerCode", new String[] {upSckwndCd, sckwndCd});
            }
        }

        // 상위 코드를 확인한다.
        count = (Integer) sckwndDAO.confUpperSckwnd(sckwndCd);

        // 상위 코드가 존재하는 경우
        if (count > 0) {
            throw processException("exception.adm.useUpperCode", new String[] {sckwndCd});
        }

        // 코드를 등록한다.
        return sckwndDAO.regiSckwnd(sckwndVO);
    }

    /**
     * 상병코드관리 수정
     */
    private int updtSckwnd(SckwndVO sckwndVO) throws Exception {
        return sckwndDAO.updtSckwnd(sckwndVO);
    }

    /**
     * 상병코드관리 삭제
     */
    private int deltSckwnd(SckwndVO sckwndVO) throws Exception {
    	
    	String sckwndCd = sckwndVO.getSckwndCd();
    	
        // 하위 코드를 확인한다.
        int count = (Integer) sckwndDAO.confLowerSckwnd(sckwndCd);

        // 하위 코드가 존재하는 경우
        if (count > 0) {
            throw processException("exception.adm.hasLowerCode", new String[] {sckwndCd});
        }

        // 코드를 삭제한다.
        return sckwndDAO.deltSckwnd(sckwndVO);
    }

    /**
     * 상병코드관리 등록,수정,삭제
     */
	@Override
	public String saveSckwnd(SckwndVO sckwndVO) throws Exception {
		
		if (sckwndVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = sckwndVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 상병코드관리 수정
			ret = updtSckwnd(sckwndVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 상병코드관리 등록
			ret = regiSckwnd(sckwndVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 상병코드관리 삭제
			ret = deltSckwnd(sckwndVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}