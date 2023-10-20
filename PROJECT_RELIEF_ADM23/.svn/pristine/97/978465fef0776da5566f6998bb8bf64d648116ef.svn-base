package business.com.relief.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.relief.service.ReamtPayService;
import business.com.relief.service.ReamtPayVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 구상금납부내역 Service 구현 클래스
 * 
 * @class   : ReamtPayServiceImpl
 * @author  : LSH
 * @since   : 2021.12.16
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("ReamtPayService")
@SuppressWarnings({"all"})
public class ReamtPayServiceImpl extends BaseService implements ReamtPayService {

    @Resource(name = "ReamtPayDAO")
    private ReamtPayDAO reamtPayDAO;
	
    /**
     * 구상금납부내역 페이징목록조회
     */
    @Override
    public PaginatedArrayList listReamtPay(ReamtPayVO reamtPayVO, int currPage, int pageSize) throws Exception {
    	return reamtPayDAO.listReamtPay(reamtPayVO, currPage, pageSize);
    }

    /**
     * 구상금납부내역 목록조회
     */
    @Override
    public List listReamtPay(ReamtPayVO reamtPayVO) throws Exception {
    	return reamtPayDAO.listReamtPay(reamtPayVO);
    }
    /**
     * 구상금관리 등록
     */
    private int regiReamtMng(ReamtPayVO reamtPayVO) throws Exception {
        return reamtPayDAO.regiReamtMng(reamtPayVO);
    }

    /**
     * 구상금관리 수정
     */
    private int updtReamtMng(ReamtPayVO reamtPayVO) throws Exception {
        return reamtPayDAO.updtReamtMng(reamtPayVO);
    }

    /**
     * 구상금관리 삭제
     */
    private int deltReamtMng(ReamtPayVO reamtPayVO) throws Exception {
        return reamtPayDAO.deltReamtMng(reamtPayVO);
    }

    /**
     * 구상금납부내역 등록
     */
    private int regiReamtPay(ReamtPayVO reamtPayVO) throws Exception {
        return reamtPayDAO.regiReamtPay(reamtPayVO);
    }

    /**
     * 구상금납부내역 수정
     */
    private int updtReamtPay(ReamtPayVO reamtPayVO) throws Exception {
        return reamtPayDAO.updtReamtPay(reamtPayVO);
    }

    /**
     * 구상금납부내역 삭제
     */
    private int deltReamtPay(ReamtPayVO reamtPayVO) throws Exception {
        return reamtPayDAO.deltReamtPay(reamtPayVO);
    }

    /**
     * 구상금납부내역 등록,수정,삭제
     */
	@Override
	public String saveReamtPay(ReamtPayVO reamtPayVO) throws Exception {
		
		if (reamtPayVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = reamtPayVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 구상금관리 수정
			if (updtReamtMng(reamtPayVO) > 0) {
				// 구상금납부내역 수정
				ret = updtReamtPay(reamtPayVO);
			}
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 사업지역,업체명 기준 관리번호 가져오기
			String reamtMngNo = reamtPayDAO.getReamtMngNo(reamtPayVO);
			// 관리번호가 없는 경우
			if (reamtMngNo == null) {
				// 구상금관리 등록
				if (regiReamtMng(reamtPayVO) > 0) {
					// 구상금납부내역 등록
					ret = regiReamtPay(reamtPayVO);
				}
			}
			// 관리번호가 있는 경우
			else {
				// 관리번호 맵핑
				reamtPayVO.setReamtMngNo(reamtMngNo);
				// 구상금관리 수정
				if (updtReamtMng(reamtPayVO) > 0) {
					// 구상금납부내역 등록
					ret = regiReamtPay(reamtPayVO);
				}
			}
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 구상금납부내역 삭제
			if (deltReamtPay(reamtPayVO) > 0) {
				// 구상금관리 삭제
				ret = deltReamtMng(reamtPayVO);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 구상금납부내역 상세조회 (구제급여 항목포함)
     */
	@Override
    public ReamtPayVO viewReamtPay(ReamtPayVO reamtPayVO) throws Exception {
		ReamtPayVO result = reamtPayDAO.viewReamtPay(reamtPayVO);
		if (result != null) {
			long amt = CommUtils.getLong(result.getRefbnfTotAmt(), 0)
					 - CommUtils.getLong(result.getPayInfrmAmt (), 0);
			// 차액계산
			result.setRefbnfDiffAmt(String.valueOf(amt));
		}
		return result;
	}

    /**
     * 피해지역코드 기준 구제급여 총액 조회
     * 의료비 + 요양생활수당 + 장례비 + 유족보상금 + 재산피해보상금
     */
	@Override
    public String viewReliefTotal(String bizAreaCd) throws Exception {
		return reamtPayDAO.viewReliefTotal(bizAreaCd);
	}
}