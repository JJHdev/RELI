package business.com.exmn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.cmm.service.CommService;
import business.com.exmn.service.LbdyNdxService;
import business.com.exmn.service.PrptExmnService;
import business.com.exmn.service.PrptExmnVO;
import business.com.exmn.service.ResiHstService;
import business.com.exmn.service.ResiHstVO;
import business.com.file.service.ExmnFileService;
import business.com.file.service.ExmnFileVO;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import business.sys.code.service.CodeVO;
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.util.CommUtils;

/**
 * [서비스클래스] - 예비조사 Service 구현 클래스
 * 
 * @class   : PrptExmnServiceImpl
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("PrptExmnService")
@SuppressWarnings({"all"})
public class PrptExmnServiceImpl extends BaseService implements PrptExmnService {

    @Resource(name = "PrptExmnDAO")
    private PrptExmnDAO prptExmnDAO;

    @Resource(name = "PrptExmnPlanDAO")
    private PrptExmnPlanDAO prptExmnPlanDAO;

    @Resource(name="LbdyNdxService")
    protected LbdyNdxService lbdyNdxService;
    
    @Resource(name="ResiHstService")
    protected ResiHstService resiHstService;
    
    @Resource(name="ExmnFileService")
    private ExmnFileService exmnFileService;

    @Resource(name = "SmsService")
    private SmsService smsService;

    @Resource(name="CommService")
    private CommService commService;
    
    @Resource(name="ReliefService")
    protected ReliefService reliefService;

    /**
     * 예비조사 페이징목록조회
     */
    @Override
    public PaginatedArrayList listPrptExmn(PrptExmnVO prptExmnVO, int currPage, int pageSize) throws Exception {
    	return prptExmnDAO.listPrptExmn(prptExmnVO, currPage, pageSize);
    }

    /**
     * 예비조사 목록조회
     */
    @Override
    public List listPrptExmn(PrptExmnVO prptExmnVO) throws Exception {
    	return prptExmnDAO.listPrptExmn(prptExmnVO);
    }

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 예비조사 목록 조회
     */
	@Override
	public List listPrptExmnMypage(String aplyNo) throws Exception {
		return prptExmnDAO.listPrptExmnMypage(aplyNo);
	}	

    /**
     * 예비조사 상세조회
     */
	@Override
	public PrptExmnVO viewPrptExmn(PrptExmnVO prptExmnVO) throws Exception {
		return prptExmnDAO.viewPrptExmn(prptExmnVO);
	}

    /**
     * 2021.12.03 LSH
     * 예비조사 신청번호 기준 최종 조사차수 조회
     */
	@Override
	public String getPrptExmnOderLast(String aplyNo) throws Exception {
		return prptExmnDAO.getPrptExmnOderLast(aplyNo);
	}

    /**
     * 예비조사 등록
     */
    private int regiPrptExmn(PrptExmnVO prptExmnVO) throws Exception {
		int ret = prptExmnDAO.regiPrptExmn(prptExmnVO);
		if (ret > 0) {
			// 2021.11.25 LSH 이력저장
			prptExmnVO.setLogTy(CommConst.INSERT);
			prptExmnDAO.regiPrptExmnHst(prptExmnVO);
		}
		return ret;
    }

    /**
     * 예비조사 수정
     */
    private int updtPrptExmn(PrptExmnVO prptExmnVO) throws Exception {
		int ret = prptExmnDAO.updtPrptExmn(prptExmnVO);
		if (ret > 0) {
			// 2021.11.25 LSH 이력저장
			prptExmnVO.setLogTy(CommConst.UPDATE);
			prptExmnDAO.regiPrptExmnHst(prptExmnVO);
		}
		return ret;
    }

    /**
     * 예비조사 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltPrptExmn(PrptExmnVO prptExmnVO) throws Exception {
    	int ret = 0;
		// 2021.11.25 LSH 이력저장
		prptExmnVO.setLogTy(CommConst.DELETE);
		if (prptExmnDAO.regiPrptExmnHst(prptExmnVO) > 0) {
			ret = prptExmnDAO.deltPrptExmn(prptExmnVO);
		}
		return ret;
    }

    /**
     * 2021.11.20
     * 예비조사 저장처리
     * 1) mode=I (등록) : 예비조사계획에서 대상자 조회 등록
     * 2) mode=D (삭제) : 예비조사계획에서 대상자 선택 삭제
     * 3) mode=U (수정) : 예비조사관리에서 예비조사정보 저장
     *    - 거주이력 (resiList) / 생체지표 (lbdyList) 포함
     * 
     * 2021.12.14 기인정여부 체크된 대상자는 기인정조사차수,예비조사적합으로 등록
     */
	@Override
	public String savePrptExmn(PrptExmnVO prptExmnVO) throws Exception {
		
		if (prptExmnVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = prptExmnVO.getMode();

		List<PrptExmnVO> list = prptExmnVO.getExmnList();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 예비조사 수정
			ret = updtPrptExmn(prptExmnVO);
			
			if (ret > 0) {
				// 거주이력목록
				List<ResiHstVO> resiList = prptExmnVO.getResiList();
				if (resiList != null && 
					resiList.size() > 0) {
					// 거주이력 저장
					resiHstService.saveResiHst(resiList, prptExmnVO);
				}
				// [2021.11.26 현재사용안함] 생체지표목록
				/*
				List<LbdyNdxVO> lbdyList = prptExmnVO.getLbdyList();
				if (lbdyList != null && 
					lbdyList.size() > 0) {
					// 생체지표 저장
					lbdyNdxService.saveLbdyNdx(lbdyList, prptExmnVO);
				}
				*/
			}
		}
		else if (CommConst.INSERT.equals(mode)) {
			for (PrptExmnVO data : list) {
				data.setBizAreaCd(prptExmnVO.getBizAreaCd());
				data.setBizOder  (prptExmnVO.getBizOder  ());
				data.setExmnOder (prptExmnVO.getExmnOder ());
				data.setGsUserNo (prptExmnVO.getGsUserNo ());
				// 2021.12.14 기인정여부 체크된 대상자는 
				// 기인정조사차수,예비조사적합으로 등록
				if (CommUtils.isNotEmpty(data.getLgcyExmnOder())) {
					// 예비조사적합
					data.setDltncRsltCd(CommConst.CODE_DLTNC_YES);
				}
				// 예비조사등록
				ret += regiPrptExmn(data);
			}
		}
		else if (CommConst.DELETE.equals(mode)) {
			for (PrptExmnVO data : list) {
				data.setGsUserNo(prptExmnVO.getGsUserNo());
				// 예비조사 삭제
				ret += deltPrptExmn(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 2021.11.24
     * 예비조사 심의회결과 저장
     * 예비조사 첨부서류 저장
     * 예비조사 심의결과 SMS 발송
     * 
     * 2021.12.15 심의회결과 저장시 기인정자의 심의일자 일괄 업데이트
     * 2021.12.15 심의회결과 저장시 기인정자의 구제급여 진행상태 일괄 업데이트
     */
	@Override
	public String savePrptExmnRslt(PrptExmnVO prptExmnVO) throws Exception {
		
		if (prptExmnVO == null)
			throw processException("error.comm.notTarget");
		
		
		// 예비조사 심의회결과 수정
		int ret = updtPrptExmn(prptExmnVO);
		
		// 첨부서류목록
		List<ExmnFileVO> files = prptExmnVO.getFileList();
		
		if (ret > 0) {
			if (files != null &&
				files.size() > 0) {
				// 첨부서류 저장처리
				exmnFileService.saveExmnFile(files, FileDirectory.PRPTEXMN);
			}
			// 구제급여신청 상태 업데이트
			// 2022.12.23 인정여부 업데이트 추가
			String dmgeRcognYn = CommConst.NO;
			if (CommConst.CODE_DLTNC_YES.equals(prptExmnVO.getDltncRsltCd()))
				dmgeRcognYn = CommConst.YES;
			
			ReliefVO reliefVO = ReliefVO.builder()
					.aplyNo      (prptExmnVO.getAplyNo()   ) // 신청번호
					.prgreStusCd (CommConst.PRGRE_PREPARE  ) // 상태코드 (예비조사완료)
					.dmgeRcognYn (dmgeRcognYn              ) // 인정여부
					.gsUserNo    (prptExmnVO.getGsUserNo() ) // 사용자번호
					.build();
			reliefService.updtReliefStatus(reliefVO);
			
			// 2021.12.15 심의일자가 있는 경우
			if (CommUtils.isNotEmpty(prptExmnVO.getDltncOpmtYmd())) {
				// 2021.12.15 기인정자의 심의일자 일괄 업데이트
				prptExmnDAO.updtPrptExmnLgcyAll(prptExmnVO);
				// 2021.12.15 기인정자의 구제급여 진행상태 일괄 업데이트
				reliefService.updtReliefStatusLgcyAll(
						ReliefVO.builder()
						.gsUserNo    (prptExmnVO.getGsUserNo ())
						.bizAreaCd   (prptExmnVO.getBizAreaCd())
						.bizOder     (prptExmnVO.getBizOder  ())
						.exmnOder    (prptExmnVO.getExmnOder ())
						.prgreStusCd (CommConst.PRGRE_PREPARE)
						.build       ()
				);
			}

			// 예비조사정보 상세조회
			PrptExmnVO data = viewPrptExmn(prptExmnVO);
			// SMS 업무메세지 공통코드 조회
			CodeVO codeVO = commService.getCode(
				CommConst.CODE_SMSMSG, 
				CommConst.CODE_SMSMSG_PRPTEXMN
			);
			
			// SMS 업무메세지 전송
			SmsVO smsVO = SmsVO.builder()
					.rcvrNm    (data.getAplcntNm())
					.rcvrNo    (data.getAplcntMbtelNo())
					.trnsterNo (CommConst.SMS_TRANSFER_NO)
					.trnsterNm (CommConst.SMS_TRANSFER_NM)
					.smsSeCd   (CommConst.SMS_RELIEF_RESULT) // 심의결과
					.trsmStusCd(CommConst.SMS_RESULT_WAIT)   // 발송대기
					.trsmCn    (codeVO.getCdCn())
					.build();
			// SMS 발송
			smsService.sendSms(smsVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 예비조사계획 페이징목록조회
     */
    @Override
    public PaginatedArrayList listPrptExmnPlan(PrptExmnVO prptExmnVO, int currPage, int pageSize) throws Exception {
    	return prptExmnPlanDAO.listPrptExmnPlan(prptExmnVO, currPage, pageSize);
    }

    /**
     * 예비조사계획 목록조회
     */
    @Override
    public List listPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception {
    	return prptExmnPlanDAO.listPrptExmnPlan(prptExmnVO);
    }

    /**
     * 예비조사계획 상세조회
     */
	@Override
	public PrptExmnVO viewPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception {
		return prptExmnPlanDAO.viewPrptExmnPlan(prptExmnVO);
	}

    /**
     * 예비조사계획 등록
     */
    private int regiPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception {
        return prptExmnPlanDAO.regiPrptExmnPlan(prptExmnVO);
    }

    /**
     * 예비조사계획 수정
     */
    private int updtPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception {
        return prptExmnPlanDAO.updtPrptExmnPlan(prptExmnVO);
    }

    /**
     * 예비조사계획 삭제
     */
    private int deltPrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception {
        return prptExmnPlanDAO.deltPrptExmnPlan(prptExmnVO);
    }

    /**
     * 2021.11.20
     * 예비조사계획 등록,수정,삭제
     */
	@Override
	public String savePrptExmnPlan(PrptExmnVO prptExmnVO) throws Exception {
		
		if (prptExmnVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = prptExmnVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 예비조사계획 수정
			ret = updtPrptExmnPlan(prptExmnVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 예비조사계획 등록
			ret = regiPrptExmnPlan(prptExmnVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 조사대상자 확인
			PrptExmnVO data = viewPrptExmnPlan(prptExmnVO);
			if (data.getExmnCnt() > 0) {
				throw processException("error.adm.exmnPlan.notRemove");
			}
			// 예비조사계획 삭제
			ret = deltPrptExmnPlan(prptExmnVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 예비조사계획 NEXT 조사차수 가져오기
     */
	@Override
	public String getPrptExmnPlanNextOder(PrptExmnVO prptExmnVO) throws Exception {
		return prptExmnPlanDAO.getPrptExmnPlanNextOder(prptExmnVO);
	}
}