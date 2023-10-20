package business.com.relief.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.IdntfcVO;
import business.com.relief.service.ReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 피해자정보 Service 구현 클래스
 * 
 * @class   : IdntfcServiceImpl
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("IdntfcService")
@SuppressWarnings({"all"})
public class IdntfcServiceImpl extends BaseService implements IdntfcService {

    @Resource(name = "IdntfcDAO")
    private IdntfcDAO idntfcDAO;
	
    /**
     * 피해자정보 페이징목록조회
     */
    @Override
    public PaginatedArrayList listIdntfc(IdntfcVO idntfcVO, int currPage, int pageSize) throws Exception {
    	return idntfcDAO.listIdntfc(idntfcVO, currPage, pageSize);
    }

    /**
     * 피해자정보 목록조회
     */
    @Override
    public List listIdntfc(IdntfcVO idntfcVO) throws Exception {
    	return idntfcDAO.listIdntfc(idntfcVO);
    }

    /**
     * 피해자정보 상세조회
     */
	@Override
	public IdntfcVO viewIdntfc(String sufrerNo) throws Exception {
		return idntfcDAO.viewIdntfc(sufrerNo);
	}

    /**
     * 피해자정보 등록
     * (idntfcId 식별ID가 새로 생성됨)
     */
	@Override
	public int regiIdntfc(IdntfcVO idntfcVO) throws Exception {
		int ret = idntfcDAO.regiIdntfc(idntfcVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			idntfcVO.setLogTy(CommConst.INSERT);
			idntfcDAO.regiIdntfcHst(idntfcVO);
		}
		return ret;
    }

    /**
     * 피해자정보 수정
     */
	@Override
	public int updtIdntfc(IdntfcVO idntfcVO) throws Exception {
		int ret = idntfcDAO.updtIdntfc(idntfcVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			idntfcVO.setLogTy(CommConst.UPDATE);
			idntfcDAO.regiIdntfcHst(idntfcVO);
		}
		return ret;
    }

    /**
     * 피해자정보 삭제
     * (이력을 먼저 저장후 삭제한다)
     */
	@Override
	public int deltIdntfc(IdntfcVO idntfcVO) throws Exception {
    	int ret = 0;
		// 2021.10.14 LSH 이력저장
    	idntfcVO.setLogTy(CommConst.DELETE);
		if (idntfcDAO.regiIdntfcHst(idntfcVO) > 0) {
			ret = idntfcDAO.deltIdntfc(idntfcVO);
		}
		return ret;
    }

    /**
     * 피해자정보 등록,수정,삭제
     */
	@Override
	public String saveIdntfc(IdntfcVO idntfcVO) throws Exception {
		
		if (idntfcVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = idntfcVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 피해자정보 수정
			ret = updtIdntfc(idntfcVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 피해자정보 등록
			ret = regiIdntfc(idntfcVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 피해자정보 삭제
			ret = deltIdntfc(idntfcVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2021.10.19 LSH
     * 성명/생년월일 기준 피해자정보 KEY 조회
     */
    public String getIdntfcExistNo(IdntfcVO idntfcVO) throws Exception {
    	return idntfcDAO.getIdntfcExistNo(idntfcVO);
    }
    
    /**
     * 2021.11.01 LSH
     * 구제급여 신청접수처리 (식별ID 생성반환)
     * 
     * 2023.01.06 접수처리시 무조건 식별ID가 새로 생성되는 오류 수정
     */
	@Override
	public String saveIdntfcReceipt(IdntfcVO idntfcVO) throws Exception {
		
		if (idntfcVO == null)
			throw processException("error.comm.notTarget");
		
		// 2023.01.06 피해자정보 조회
		IdntfcVO obj = viewIdntfc(idntfcVO.getSufrerNo());
		// 2023.01.06 식별ID가 없을 경우에만 신규 생성
		if (CommUtils.isEmpty(obj.getIdntfcId())) {
			// 식별ID 신규생성
			idntfcVO.setIdntfcId(idntfcDAO.getCreateIdntfcId());
			if (updtIdntfc(idntfcVO) > 0)
				return idntfcVO.getIdntfcId();
			return null;
		}
		// 2023.01.06 식별ID가 있을 경우 식별ID 반환
		else {
			return obj.getIdntfcId();
		}
	}
    
	/**
	 * 2021.11.08 LSH
	 * 주민등록번호 기준 인증 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm   : 성명
	 * @param  (필수) idntfcVO.idntfcId   : 식별아이디
	 * @param  (필수) idntfcVO.sufrerRrno : 주민등록번호
	 * */
	public boolean checkIdntfcByRrno(IdntfcVO idntfcVO) throws Exception {
		return idntfcDAO.checkIdntfcByRrno(idntfcVO);
	}
	/**
	 * 2021.11.08 LSH
	 * 휴대전화번호 기준 인증 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm      : 성명
	 * @param  (필수) idntfcVO.sufrerBrdt    : 생년월일
	 * @param  (필수) idntfcVO.sufrerSxdst   : 성별
	 * @param  (필수) idntfcVO.sufrerMbtelNo : 휴대전화번호
	 * */
	public boolean checkIdntfcByMbtelNo(IdntfcVO idntfcVO) throws Exception {
		return idntfcDAO.checkIdntfcByMbtelNo(idntfcVO);
	}

	/**
	 * 2021.11.08 LSH
	 * 이름/식별ID기준 일치하는 사용자가 있는지 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm   : 성명
	 * @param  (필수) idntfcVO.idntfcId   : 식별아이디
	 * */
	public boolean existIdntfc(IdntfcVO idntfcVO) throws Exception {
		return idntfcDAO.existIdntfc(idntfcVO);
	}

    /**
     * 2022.12.15 LSH
     * 식별ID 기준 피해자정보 상세조회
     * 종합현황의 개인별상세기록카드에서 사용됨
     */
	@Override
	public IdntfcVO viewIdntfcById(IdntfcVO idntfcVO) throws Exception {
		return idntfcDAO.viewIdntfcById(idntfcVO);
	}

    /**
     * 2023.02.21 LSH
     * 피해자의 피해지역 거주기간 업데이트
     */
	@Override
	public int updtIdntfcResiWhlCn(IdntfcVO idntfcVO) throws Exception {
		return idntfcDAO.updtIdntfcResiWhlCn(idntfcVO);
	}
}