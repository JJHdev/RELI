package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.exmn.service.MnsvyVO;
import business.com.exmn.service.RcperLvlhService;
import business.com.exmn.service.RcperLvlhVO;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 요양생활수당관리 Service 구현 클래스
 * 
 * @class   : RcperLvlhServiceImpl
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("RcperLvlhService")
@SuppressWarnings({"all"})
public class RcperLvlhServiceImpl extends BaseService implements RcperLvlhService {

    @Resource(name = "RcperLvlhDAO")
    private RcperLvlhDAO rcperLvlhDAO;

    @Resource(name = "RcperLvlhDtlsDAO")
    private RcperLvlhDtlsDAO rcperLvlhDtlsDAO;

    /**
     * 요양생활수당관리 목록조회
     */
    @Override
    public List listRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhDAO.listRcperLvlh(rcperLvlhVO);
    }

    /**
     * 요양생활수당관리 상세조회
     */
	@Override
	public RcperLvlhVO viewRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception {
		return rcperLvlhDAO.viewRcperLvlh(rcperLvlhVO);
	}

    /**
     * 요양생활수당관리 등록
     */
    private int regiRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhDAO.regiRcperLvlh(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.INSERT);
			rcperLvlhDAO.regiRcperLvlhHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 요양생활수당관리 수정
     */
    private int updtRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhDAO.updtRcperLvlh(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.UPDATE);
			rcperLvlhDAO.regiRcperLvlhHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 요양생활수당관리 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = 0;
		// 이력저장
		rcperLvlhVO.setLogTy(CommConst.DELETE);
		if (rcperLvlhDAO.regiRcperLvlhHst(rcperLvlhVO) > 0) {
	        ret = rcperLvlhDAO.deltRcperLvlh(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 2021.12.06 LSH
     * 구제급여 - 지급 - 나-1. 요양생활수당 결정
     * 요양생활수당 연도별 반영금액 저장처리 (다중처리)
     */
	@Override
	public int saveRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception {
		
		if (rcperLvlhVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		// 처리모드
		String mode = rcperLvlhVO.getMode();

		// 등록인 경우 (다중행 등록)
		if (CommConst.INSERT.equals(mode)) {
			// 년도범위만큼 LOOP
			for (int year = rcperLvlhVO.getStYear(); year <= rcperLvlhVO.getEnYear(); year++) {
				RcperLvlhVO data = RcperLvlhVO.builder()
						.bizAreaCd   (rcperLvlhVO.getBizAreaCd())
						.bizOder     (rcperLvlhVO.getBizOder  ())
						.exmnOder    (rcperLvlhVO.getExmnOder ())
						.aplyNo      (rcperLvlhVO.getAplyNo   ())
						.gsUserNo    (rcperLvlhVO.getGsUserNo ())
						.giveYr      (String.valueOf(year))
						.rcperLvlhAmt("0")
						.build       ();
				// 요양생활수당관리가 있으면 SKIP
				if (viewRcperLvlh(data) != null)
					continue;
				// 요양생활수당관리 등록
				ret += regiRcperLvlh(data);
			}
		}
		// 수정인 경우
		else if (CommConst.UPDATE.equals(mode)) {
			// 연도별 반영금액 목록
			List<RcperLvlhVO> list = rcperLvlhVO.getYearList();
			for (RcperLvlhVO data : list) {
				data.setBizAreaCd(rcperLvlhVO.getBizAreaCd());
				data.setBizOder  (rcperLvlhVO.getBizOder  ());
				data.setExmnOder (rcperLvlhVO.getExmnOder ());
				data.setAplyNo   (rcperLvlhVO.getAplyNo   ());
				data.setGsUserNo (rcperLvlhVO.getGsUserNo ());

				// 요양생활수당관리 수정
				ret += updtRcperLvlh(data);
			}
		}
		// 삭제인 경우
		else if (CommConst.DELETE.equals(mode)) {
			// 요양생활수당관리 삭제
			ret = deltRcperLvlh(rcperLvlhVO);
		}
        return ret;
	}

    /**
     * 요양생활수당지급상세 목록조회
     */
    @Override
    public List listRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhDtlsDAO.listRcperLvlhDtls(rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 상세조회
     */
	@Override
	public RcperLvlhVO viewRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		return rcperLvlhDtlsDAO.viewRcperLvlhDtls(rcperLvlhVO);
	}

    /**
     * 요양생활수당지급상세 등록
     */
    private int regiRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhDtlsDAO.regiRcperLvlhDtls(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.INSERT);
			rcperLvlhDtlsDAO.regiRcperLvlhDtlsHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 요양생활수당지급상세 수정
     */
    private int updtRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhDtlsDAO.updtRcperLvlhDtls(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.UPDATE);
			rcperLvlhDtlsDAO.regiRcperLvlhDtlsHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 요양생활수당지급상세 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = 0;
		// 이력저장
		rcperLvlhVO.setLogTy(CommConst.DELETE);
		if (rcperLvlhDtlsDAO.regiRcperLvlhDtlsHst(rcperLvlhVO) > 0) {
	        ret = rcperLvlhDtlsDAO.deltRcperLvlhDtls(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 2021.12.06 LSH
     * 구제급여 - 지급 - 나-2. 요양생활수당 지급
     * 요양생활수당 월지급일자 등록처리
     */
	@Override
	public int saveRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		
		if (rcperLvlhVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = rcperLvlhVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 요양생활수당지급상세 수정
			ret = updtRcperLvlhDtls(rcperLvlhVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 요양생활수당관리 지급여부 'Y'로 설정
			rcperLvlhVO.setGiveYn(CommConst.YES);
			// 요양생활수당지급상세 등록
			ret = regiRcperLvlhDtls(rcperLvlhVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 요양생활수당지급상세 삭제
			ret = deltRcperLvlhDtls(rcperLvlhVO);
		}
        return ret;
	}
	
}