package business.adm.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.adm.main.service.AdmMainService;
import business.com.exmn.service.McpDtlsVO;
import business.com.relief.service.ReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 관리자메인 서비스
 *
 * @class   : MainServiceImpl
 * @author  : ntarget
 * @since   : 2021.02.03
 * @version : 1.0
 *
 *  수정일      수정자             수정내용
 *  -------    --------    ---------------------------
 *
 */

@Service("AdmMainService")
@SuppressWarnings({"all"})
public class AdmMainServiceImpl extends BaseService implements AdmMainService {

    @Resource(name = "AdmMainDAO")
    private AdmMainDAO admMainDAO;
	
    /**
     * 2021.11.01 LSH
     * 구제급여 신청상태 조회
     * (접수/예비조사/본조사/지급)
     */
	@Override
	public List listReliefStatus() throws Exception {
		return admMainDAO.listReliefStatus();
	}
	
    /**
     * 2021.11.02 LSH
     * 종합현황 페이징목록 조회
     */
	@Override
    public PaginatedArrayList listReliefTotal(ReliefVO reliefVO, int currPage, int pageSize) {
        return admMainDAO.listReliefTotal(reliefVO, currPage, pageSize);
    }

    /**
     * 2021.11.02 LSH
     * 종합현황 목록 조회
     */
	@Override
    public List listReliefTotal(ReliefVO reliefVO) {
        return admMainDAO.listReliefTotal(reliefVO);
    }

    /**
     * 2022.12.16 LSH
     * 종합현황 상세조회
     */
	@Override
	public Map viewReliefTotal(ReliefVO reliefVO) throws Exception {
		return admMainDAO.viewReliefTotal(reliefVO);
	}

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 피해구제신청 및 인정현황 목록 조회
     */
	@Override
    public List listReliefRecogn(String idntfcId) throws Exception {
        return admMainDAO.listReliefRecogn(idntfcId);
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 인정현황 목록 조회
     */
	@Override
    public List listDmgeRecogn(String idntfcId) throws Exception {
        return admMainDAO.listDmgeRecogn(idntfcId);
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 상세현황 목록 조회
     */
	@Override
    public List listDmgeDetails(String idntfcId) throws Exception {
        return admMainDAO.listDmgeDetails(idntfcId);
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 상세현황 조회기간 조회
     * @return rvwBgngYmd 조회기간 시작일
     * @return rvwEndYmd  조회기간 종료일
     */
	@Override
	public Map viewDmgeDetailsReview(String idntfcId) throws Exception {
		return admMainDAO.viewDmgeDetailsReview(idntfcId);
	}
    
    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 영향범위 및 거주기간 조회
     * - 대구(A0001),서천(A0002),김포(A0003) 의 영향범위 데이터 상세조회
     * - 사업지역코드 3가지는 고정 / 기준 사업차수는 '2차'로 고정함
     */
	@Override
	public Map viewDmgeAffcScope(String idntfcId) throws Exception {
		return admMainDAO.viewDmgeAffcScope(idntfcId);
	}

    /**
     * 2022.12.20 LSH
     * 종합현황 - 개인기록카드 - 구제급여 지급현황 목록 조회
     */
	@Override
    public List listReliefGive(String idntfcId) throws Exception {
		return admMainDAO.listReliefGive(idntfcId);
    }

    /**
     * 2022.12.20 LSH
     * 종합현황 - 개인기록카드 - 민원응대 이력 목록 조회
     * TODO. 다음 프로젝트 고도화에 개발할 영역임
     */
	@Override
    public List listCmplHst(String idntfcId) throws Exception {
		return admMainDAO.listCmplHst(idntfcId);
    }

    /**
     * 2022.12.21 LSH
     * 종합현황 - 개인기록카드 - 피해등급 평가질환 목록
     */
	@Override
    public List listDmgeGrdMattr(String idntfcId) throws Exception {
		return admMainDAO.listDmgeGrdMattr(idntfcId);
	}
}