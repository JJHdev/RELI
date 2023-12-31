package business.com.cmit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.cmit.service.CmitMngService;
import business.com.cmit.service.CmitMngVO;
import business.com.cmit.service.MfcmmService;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 위원회관리 Service 구현 클래스
 * 
 * @class   : CmitMngServiceImpl
 * @author  : LSH
 * @since   : 2023.10.19
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("CmitMngService")
@SuppressWarnings({"all"})
public class CmitMngServiceImpl extends BaseService implements CmitMngService {

    @Resource(name = "CmitMngDAO")
    private CmitMngDAO cmitMngDAO;

    @Resource(name = "CmitDmgeDAO")
    private CmitDmgeDAO cmitDmgeDAO;

    // 2023.10.25 위원회 안건정보
    @Resource(name = "CmitDmgeAgndDAO")
    private CmitDmgeAgndDAO cmitDmgeAgndDAO;

    // 2023.10.26 위원회 의견서/의결서정보
    @Resource(name = "CmitMfcmmRsltDAO")
    private CmitMfcmmRsltDAO cmitMfcmmRsltDAO;

    // 2023.10.26 위원회 수당지정보
    @Resource(name = "CmitMfcmmErnrDAO")
    private CmitMfcmmErnrDAO cmitMfcmmErnrDAO;

    // 2023.10.30 위원정보
    @Resource(name = "MfcmmService")
    private MfcmmService mfcmmService;
	
    /**
     * 위원회 페이징목록조회
     */
	@Override
	public PaginatedArrayList listCmitDmge(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception {
    	return cmitDmgeDAO.listCmitDmge(cmitMngVO, currPage, pageSize);
	}

    /**
     * 위원회 목록조회
     */
	@Override
	public List listCmitDmge(CmitMngVO cmitMngVO) throws Exception {
    	return cmitDmgeDAO.listCmitDmge(cmitMngVO);
	}

    /**
     * 위원회 상세조회
     */
	@Override
	public CmitMngVO viewCmitDmge(CmitMngVO cmitMngVO) throws Exception {
    	return cmitDmgeDAO.viewCmitDmge(cmitMngVO);
	}

    /**
     * 위원회 저장처리
     */
	@Override
	public String saveCmitDmge(CmitMngVO cmitMngVO) throws Exception {
		
		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = cmitMngVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 위원회 수정
			ret = cmitDmgeDAO.updtCmitDmge(cmitMngVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 위원회 등록
			ret = cmitDmgeDAO.regiCmitDmge(cmitMngVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 위원회 소속위원을 전체 삭제한다.
			cmitMngDAO.deltCmitMngAll(cmitMngVO);
			// 위원회 삭제
			ret = cmitDmgeDAO.deltCmitDmge(cmitMngVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 위원회 소속 위원 페이징목록조회
     */
    @Override
    public PaginatedArrayList listCmitMng(CmitMngVO cmitMngVO, int currPage, int pageSize) throws Exception {
    	return cmitMngDAO.listCmitMng(cmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회 소속 위원 목록조회
     */
    @Override
    public List listCmitMng(CmitMngVO cmitMngVO) throws Exception {
    	return cmitMngDAO.listCmitMng(cmitMngVO);
    }

    /**
     * 위원회 소속 위원 등록,수정,삭제
     */
	@Override
	public String saveCmitMng(CmitMngVO cmitMngVO) throws Exception {

		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");

		// 처리 모드
		String mode = cmitMngVO.getMode();
		// 결과 건수
		int ret = 0;
		
		// 선택삭제인 경우
		if (CommConst.DELETE.equals(mode)) {
			// 삭제 목록
			List<CmitMngVO> removeList = cmitMngVO.getRemoveList();

			if (CommUtils.isEmptyList(removeList))
				throw processException("error.comm.notTarget");
			
			// 위원회 소속 위원 삭제를 수행한다.
			for (CmitMngVO data : removeList) {
				ret += cmitMngDAO.deltCmitMng(data);
			}
		}
		else {
			// 등록 목록
			List<CmitMngVO> saveList = cmitMngVO.getSaveList();

			if (CommUtils.isEmptyList(saveList))
				throw processException("error.comm.notTarget");
			
			// 위원회 소속 위원 등록을 수행한다.
			for (CmitMngVO data : saveList) {
				data.setGsUserNo (cmitMngVO.getGsUserNo ());
				data.setCmitMngNo(cmitMngVO.getCmitMngNo());
				
				// 해당 위원회에 동일한 위원이 존재하는지 체크
				if (cmitMngDAO.existCmitMng(data)) {
					// 해당 위원회에 이미 등록된 위원입니다.
					throw processException("error.adm.cmitMng.duplMfcmm", 
						new String[] {data.getMfcmmOgdpNm() + " - " + data.getMfcmmNm()} 
					);
				}
				ret += cmitMngDAO.regiCmitMng(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2023.02.13 LSH
     * 임기번호가 속한 위원회가 있는지 체크
     */
	@Override
    public boolean existCmitMngForTenure(CmitMngVO cmitMngVO) throws Exception {
    	return (cmitMngDAO.getCmitMngCountForTenure(cmitMngVO) > 0);
	}

    /**
     * 2023.10.25 LSH
     * 위원회안건 목록조회
     */
	@Override
    public List listCmitDmgeAgnd(CmitMngVO cmitMngVO) throws Exception {
    	return cmitDmgeAgndDAO.listCmitDmgeAgnd(cmitMngVO);
    }

    /**
     * 2023.10.26 LSH
     * 위원회 의견서 상세조회
     * @param cmitMngVO.cmitMngNo 위원회관리번호
     * @param cmitMngVO.tenureNo  임기번호
     * @param cmitMngVO.agndNo    안건번호
     */
	@Override
    public CmitMngVO viewCmitOpinion(CmitMngVO cmitMngVO) throws Exception {
    	// 안건조회
		CmitMngVO agndObj = cmitDmgeAgndDAO.viewCmitDmgeAgnd(cmitMngVO);
		if (agndObj == null)
			throw processException("error.comm.notTarget"); 
		// 위원번호정의
		agndObj.setMfcmmNo(cmitMngVO.getMfcmmNo());
		// 임기번호정의
		agndObj.setTenureNo(cmitMngVO.getTenureNo());
		// 작성구분정의
		agndObj.setWrtSeCd(CommConst.CMIT_OPINION);
		
		// 위원정보정의
		_setMfcmmObject(agndObj);
		// 의견서조회
		cmitMngVO.setWrtSeCd("WO");
		CmitMngVO opnnObj = cmitMfcmmRsltDAO.viewCmitMfcmmRslt(cmitMngVO);
		if (opnnObj == null) {
	        // YYYYMMDD
	        String regDate = CommUtils.getCurDateString();
	        agndObj.setRgtrYear      (CommUtils.substring(regDate, 0, 4));
	        agndObj.setRgtrMonth     (CommUtils.substring(regDate, 4, 6));
	        agndObj.setRgtrDay       (CommUtils.substring(regDate, 6, 8));
	        agndObj.setRgtrNm        (agndObj.getMfcmmNm    ());
	        agndObj.setErnrNm        (agndObj.getMfcmmNm    ());
	        agndObj.setErnrOgdpNm    (agndObj.getMfcmmOgdpNm());
			agndObj.setMode          (CommConst.INSERT);
		}
		else {
	        agndObj.setDlbrCn        (opnnObj.getDlbrCn        ());
	        agndObj.setDecsnMattrCd  (opnnObj.getDecsnMattrCd  ());
	        agndObj.setDecsnMattrResn(opnnObj.getDecsnMattrResn());
	        agndObj.setDecsnOdrCn    (opnnObj.getDecsnOdrCn    ());
	        agndObj.setDecsnOdrResn  (opnnObj.getDecsnOdrResn  ());
	        agndObj.setDecsnMainCn   (opnnObj.getDecsnMainCn   ());
	        agndObj.setSignAgreYn    (opnnObj.getSignAgreYn    ());
	        agndObj.setDecsnStusCd   (opnnObj.getDecsnStusCd   ());
	        agndObj.setRgtrYear      (opnnObj.getRgtrYear      ());
	        agndObj.setRgtrMonth     (opnnObj.getRgtrMonth     ());
	        agndObj.setRgtrDay       (opnnObj.getRgtrDay       ());
	        agndObj.setRgtrNm        (opnnObj.getRgtrNm        ());
			agndObj.setMode          (CommConst.UPDATE);
		}
		return agndObj;
    }

    /**
     * 2023.10.26 LSH
     * 위원회 의결서 상세조회
     * @param cmitMngVO.cmitMngNo 위원회관리번호
     * @param cmitMngVO.tenureNo  임기번호
     * @param cmitMngVO.agndNo    안건번호
     */
	@Override
    public CmitMngVO viewCmitDecision(CmitMngVO cmitMngVO) throws Exception{
    	// 안건조회
		CmitMngVO agndObj = cmitDmgeAgndDAO.viewCmitDmgeAgnd(cmitMngVO);
		if (agndObj == null)
			throw processException("error.comm.notTarget"); 
		// 위원번호정의
		agndObj.setMfcmmNo(cmitMngVO.getMfcmmNo());
		// 임기번호정의
		agndObj.setTenureNo(cmitMngVO.getTenureNo());
		// 작성구분정의
		agndObj.setWrtSeCd(CommConst.CMIT_DECISION);
		// 위원정보정의
		_setMfcmmObject(agndObj);
		// 의결서조회
		cmitMngVO.setWrtSeCd("WD");
		CmitMngVO dcsnObj = cmitMfcmmRsltDAO.viewCmitMfcmmRslt(cmitMngVO);
		if (dcsnObj == null) {
	        // YYYYMMDD
	        String regDate = CommUtils.getCurDateString();
	        agndObj.setRgtrYear      (CommUtils.substring(regDate, 0, 4));
	        agndObj.setRgtrMonth     (CommUtils.substring(regDate, 4, 6));
	        agndObj.setRgtrDay       (CommUtils.substring(regDate, 6, 8));
	        agndObj.setRgtrNm        (agndObj.getMfcmmNm    ());
	        agndObj.setErnrNm        (agndObj.getMfcmmNm    ());
	        agndObj.setErnrOgdpNm    (agndObj.getMfcmmOgdpNm());
			agndObj.setMode          (CommConst.INSERT);
		}
		else {
	        agndObj.setDlbrCn        (dcsnObj.getDlbrCn        ());
	        agndObj.setDecsnMattrCd  (dcsnObj.getDecsnMattrCd  ());
	        agndObj.setDecsnMattrResn(dcsnObj.getDecsnMattrResn());
	        agndObj.setDecsnOdrCn    (dcsnObj.getDecsnOdrCn    ());
	        agndObj.setDecsnOdrResn  (dcsnObj.getDecsnOdrResn  ());
	        agndObj.setDecsnMainCn   (dcsnObj.getDecsnMainCn   ());
	        agndObj.setSignAgreYn    (dcsnObj.getSignAgreYn    ());
	        agndObj.setDecsnStusCd   (dcsnObj.getDecsnStusCd   ());
	        agndObj.setRgtrYear      (dcsnObj.getRgtrYear      ());
	        agndObj.setRgtrMonth     (dcsnObj.getRgtrMonth     ());
	        agndObj.setRgtrDay       (dcsnObj.getRgtrDay       ());
	        agndObj.setRgtrNm        (dcsnObj.getRgtrNm        ());
			agndObj.setMode          (CommConst.UPDATE);
		}
		return agndObj;
    }
	
    /**
     * 2023.10.26 LSH
     * 위원회 수당지 상세조회
     * @param cmitMngVO.cmitMngNo 위원회관리번호
     * @param cmitMngVO.tenureNo  임기번호
     */
	@Override
    public CmitMngVO viewCmitPension(CmitMngVO cmitMngVO) throws Exception{
    	// 위원회조회
		CmitMngVO result = cmitDmgeDAO.viewCmitDmge(cmitMngVO);
		if (result == null)
			throw processException("error.comm.notTarget");
		// 위원번호정의
		result.setMfcmmNo(cmitMngVO.getMfcmmNo());
		// 임기번호정의
		result.setTenureNo(cmitMngVO.getTenureNo());
		// 작성구분정의
		result.setWrtSeCd(CommConst.CMIT_PENSION);
		// 위원정보정의
		_setMfcmmObject(result);
		
		// 수당지조회
		CmitMngVO pnsnObj = cmitMfcmmErnrDAO.viewCmitMfcmmErnr(cmitMngVO);
		if (pnsnObj == null) {
	        // YYYYMMDD
	        String regDate = CommUtils.getCurDateString();
	        result.setRgtrYear          (CommUtils.substring(regDate, 0, 4));
	        result.setRgtrMonth         (CommUtils.substring(regDate, 4, 6));
	        result.setRgtrDay           (CommUtils.substring(regDate, 6, 8));
	        result.setRgtrNm            (result.getMfcmmNm    ());
	        result.setErnrNm            (result.getMfcmmNm    ());
	        result.setErnrOgdpNm        (result.getMfcmmOgdpNm());
	        result.setMode              (CommConst.INSERT);
		}
		else {
	        result.setSn                (pnsnObj.getSn                ());
	        result.setErnrNm            (pnsnObj.getErnrNm            ());
	        result.setErnrOgdpNm        (pnsnObj.getErnrOgdpNm        ());
	        result.setErnrRrno          (pnsnObj.getErnrRrno          ());
	        result.setErnrAddr          (pnsnObj.getErnrAddr          ());
	        result.setBankNm            (pnsnObj.getBankNm            ());
	        result.setDpstrNm           (pnsnObj.getDpstrNm           ());
	        result.setActno             (pnsnObj.getActno             ());
	        result.setIndvInfoClctAgreYn(pnsnObj.getIndvInfoClctAgreYn());
	        result.setWthtxAgreYn       (pnsnObj.getWthtxAgreYn       ());
	        result.setBtrpsGiveYn       (pnsnObj.getBtrpsGiveYn       ());
	        result.setIncomeSeCd        (pnsnObj.getIncomeSeCd        ());
	        result.setRgtrYear          (pnsnObj.getRgtrYear          ());
	        result.setRgtrMonth         (pnsnObj.getRgtrMonth         ());
	        result.setRgtrDay           (pnsnObj.getRgtrDay           ());
	        result.setRgtrNm            (pnsnObj.getRgtrNm            ());
	        result.setMode              (CommConst.UPDATE);
		}
		return result;
    }
	/**
	 * 2023.10.30 LSH
	 * 위원정보 정의
	 */
	private void _setMfcmmObject(CmitMngVO result) throws Exception {
		// 위원조회
		CmitMngVO mfcmmObj = mfcmmService.viewMfcmm(result);
		if (mfcmmObj == null)
			throw processException("error.comm.notTarget"); 
		
        result.setMfcmmNo       (mfcmmObj.getMfcmmNo       ());
        result.setMfcmmNm       (mfcmmObj.getMfcmmNm       ());
        result.setMfcmmOgdpNm   (mfcmmObj.getMfcmmOgdpNm   ());
        result.setMfcmmRlmCd    (mfcmmObj.getMfcmmRlmCd    ());
        result.setMfcmmRlmNm    (mfcmmObj.getMfcmmRlmNm    ());
        result.setMfcmmRspofcNm (mfcmmObj.getMfcmmRspofcNm ());
        result.setMfcmmTelno    (mfcmmObj.getMfcmmTelno    ());
        result.setMfcmmBrdt     (mfcmmObj.getMfcmmBrdt     ());
        result.setMfcmmEmlAddr  (mfcmmObj.getMfcmmEmlAddr  ());
        result.setUserId        (mfcmmObj.getUserId        ());
        result.setUserNo        (mfcmmObj.getUserNo        ());
        result.setSignYn        (CommUtils.isEmpty(mfcmmObj.getSignCn()) ? CommConst.NO : CommConst.YES);
	}
	
    
    /**
     * 2023.10.31 LSH
     * 위원회 의견서,의결서,수당지 등록,수정
     * 
     * 1. 의견서 저장 : cmitMngVO.saveMode = "WO" 
     *    - 동일 위원회번호 기준 모든 안건에 대해 모든 위원이 제출처리할 경우 
     *      "의결서작성" 상태로 변경할것
     * 
     * 2. 의결서 저장 : cmitMngVO.saveMode = "WD" 
     *    - 위원장이 의결서 제출시 
     *      "수당지작성" 상태로 변경할것
     * 
     * 3. 수당지 저장 : cmitMngVO.saveMode = "WP" 
     */
	@Override
    public String saveCmitItem(CmitMngVO cmitMngVO) throws Exception {
		
		if (cmitMngVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		// 임시저장,제출처리
		String act      = cmitMngVO.getAct();
		// 등록,수정
		String mode     = cmitMngVO.getMode();
		// 의견서,의결서,수당지
		String saveMode = cmitMngVO.getSaveMode();
		
		// 의견서인 경우 ============================>
		if (CommConst.CMIT_OPINION.equals(saveMode)) {
			if (CommConst.UPDATE.equals(mode)) {
				// 위원회 의견서 수정
				ret = cmitMfcmmRsltDAO.updtCmitMfcmmRslt(cmitMngVO);
			}
			else if (CommConst.INSERT.equals(mode)) {
				// 위원회 의견서 등록
				ret = cmitMfcmmRsltDAO.regiCmitMfcmmRslt(cmitMngVO);
			}
			// 제출이면서 
			// 위원회 모든의견이 제출된경우
			// 위원회 상태변경처리 (의결서작성)
			if (ret > 0 &&
				CommConst.SUBMIT.equals(act) &&
				cmitDmgeDAO.isCmitDmgeComplete(cmitMngVO)) {
				// 의결서작성 상태로 변경처리
				cmitMngVO.setPrgreStusCd(CommConst.CMIT_PRGRE_DECISION);
				// 상태변경처리
				cmitDmgeDAO.updtCmitDmgeStatus(cmitMngVO);
			}
		}
		// 의결서인 경우 ============================>
		else if (CommConst.CMIT_DECISION.equals(saveMode)) {
			if (CommConst.UPDATE.equals(mode)) {
				// 위원회 의결서 수정
				ret = cmitMfcmmRsltDAO.updtCmitMfcmmRslt(cmitMngVO);
			}
			else if (CommConst.INSERT.equals(mode)) {
				// 위원회 의결서 등록
				ret = cmitMfcmmRsltDAO.regiCmitMfcmmRslt(cmitMngVO);
			}
			// 위원장의 의결서 제출시 
			// 위원회 모든의견이 제출된경우
			// 위원회 상태변경처리 (수당지작성)
			if (ret > 0 &&
				CommConst.SUBMIT.equals(act) &&
				CommConst.YES.equals(cmitMngVO.getCharmnYn())) {
				// 수당지작성 상태로 변경처리
				cmitMngVO.setPrgreStusCd(CommConst.CMIT_PRGRE_PENSION);
				// 상태변경처리
				cmitDmgeDAO.updtCmitDmgeStatus(cmitMngVO);
			}
			
		}
		// 수당지인 경우 ============================>
		else if (CommConst.CMIT_PENSION.equals(saveMode)) {
			if (CommConst.UPDATE.equals(mode)) {
				// 위원회 수당지 수정
				ret = cmitMfcmmErnrDAO.updtCmitMfcmmErnr(cmitMngVO);
			}
			else if (CommConst.INSERT.equals(mode)) {
				// 위원회 수당지 등록
				ret = cmitMfcmmErnrDAO.regiCmitMfcmmErnr(cmitMngVO);
			}
		}
		
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

}