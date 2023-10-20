package business.com.exmn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.cmm.service.CommService;
import business.com.exmn.service.MnsvyService;
import business.com.exmn.service.MnsvyVO;
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
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [서비스클래스] - 본조사 Service 구현 클래스
 * 
 * @class   : MnsvyServiceImpl
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("MnsvyService")
@SuppressWarnings({"all"})
public class MnsvyServiceImpl extends BaseService implements MnsvyService {

    @Resource(name = "MnsvyDAO")
    private MnsvyDAO mnsvyDAO;

    @Resource(name = "MnsvyPlanDAO")
    private MnsvyPlanDAO mnsvyPlanDAO;
    
    @Resource(name="ExmnFileService")
    private ExmnFileService exmnFileService;

    @Resource(name = "SmsService")
    private SmsService smsService;

    @Resource(name="CommService")
    private CommService commService;
    
    @Resource(name="ReliefService")
    protected ReliefService reliefService;
	
    /**
     * 본조사 페이징목록조회
     */
    @Override
    public PaginatedArrayList listMnsvy(MnsvyVO mnsvyVO, int currPage, int pageSize) throws Exception {
    	return mnsvyDAO.listMnsvy(mnsvyVO, currPage, pageSize);
    }

    /**
     * 본조사 목록조회
     */
    @Override
    public List listMnsvy(MnsvyVO mnsvyVO) throws Exception {
    	return mnsvyDAO.listMnsvy(mnsvyVO);
    }

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 본조사 목록 조회
     */
	@Override
	public List listMnsvyMypage(String aplyNo) throws Exception {
		return mnsvyDAO.listMnsvyMypage(aplyNo);
	}	

    /**
     * 본조사 상세조회
     */
	@Override
	public MnsvyVO viewMnsvy(MnsvyVO mnsvyVO) throws Exception {
		return mnsvyDAO.viewMnsvy(mnsvyVO);
	}

    /**
     * 2021.12.03 LSH
     * 본조사 신청번호 기준 최종 조사차수 조회
     */
	@Override
	public String getMnsvyOderLast(String aplyNo) throws Exception {
		return mnsvyDAO.getMnsvyOderLast(aplyNo);
	}

    /**
     * 본조사 등록
     */
    private int regiMnsvy(MnsvyVO mnsvyVO) throws Exception {
		int ret = mnsvyDAO.regiMnsvy(mnsvyVO);
		if (ret > 0) {
			// 2021.11.29 LSH 이력저장
			mnsvyVO.setLogTy(CommConst.INSERT);
			mnsvyDAO.regiMnsvyHst(mnsvyVO);
		}
		return ret;
    }

    /**
     * 본조사 수정
     * 2022.12.27 private 에서 public 으로 변경 (요양생활수당/장의비/유족보상비에서 사용됨)
     */
    @Override
    public int updtMnsvy(MnsvyVO mnsvyVO) throws Exception {
		int ret = mnsvyDAO.updtMnsvy(mnsvyVO);
		if (ret > 0) {
			// 2021.11.29 LSH 이력저장
			mnsvyVO.setLogTy(CommConst.UPDATE);
			mnsvyDAO.regiMnsvyHst(mnsvyVO);
		}
		return ret;
    }

    /**
     * 본조사 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltMnsvy(MnsvyVO mnsvyVO) throws Exception {
		int ret = 0;
		
		// 2022.02.04 요양생활수당 정보 확인
		if (mnsvyDAO.existRcperLvlh(mnsvyVO))
			throw new EgovBizException("요양생활수당이 등록된 정보는 삭제하실 수 없습니다.");
		
		// 2022.02.04 의료비 정보 확인
		if (mnsvyDAO.existMcpDtls(mnsvyVO))
			throw new EgovBizException("의료비내역이 등록된 정보는 삭제하실 수 없습니다.");
		
		// 2021.11.29 LSH 이력저장
		mnsvyVO.setLogTy(CommConst.DELETE);
		if (mnsvyDAO.regiMnsvyHst(mnsvyVO) > 0) {
	        ret = mnsvyDAO.deltMnsvy(mnsvyVO);
		}
		return ret;
    }

    /**
     * 2021.11.29 본조사 저장처리 
     * 2021.12.06 지급 - 의료비 저장처리
     * 1) mode=I (등록) : 본조사계획에서 대상자 조회 등록
     * 2) mode=D (삭제) : 본조사계획에서 대상자 선택 삭제
     * 3) mode=U (수정) : 본조사관리에서 장의비/유족보상비/사망원인조사 저장
     *    - act=BRV : 본조사 - 장의비/유족보상비 저장
     *    - act=DTH : 본조사 - 사망원인조사 저장
     *    - act=MCP : 지급 - 의료비지급정보 저장
     * 2022.12.27 요양생활수당 지급처리 항목은 제외함
     */
	@Override
	public String saveMnsvy(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = mnsvyVO.getMode();

		List<MnsvyVO> list = mnsvyVO.getExmnList();
		
		if (CommConst.UPDATE.equals(mode)) {
			
			// 기능구분
			String act = mnsvyVO.getAct();
			
			// 지급 - 의료비 저장시 입금계좌 맵핑
			if (CommConst.GIVE_MCP.equals(act)) {
				// 구제급여신청정보 조회
				ReliefVO reliefVO = ReliefVO.builder()
						.aplyNo(mnsvyVO.getAplyNo())
						.build();
				reliefVO = reliefService.viewRelief(reliefVO);
				
				if (reliefVO != null) {
					// 은행명,입금계좌,예금주 불러오기
					mnsvyVO.setGiveBankCd  (reliefVO.getBankCd  ());
					mnsvyVO.setGiveDpstrNm (reliefVO.getDpstrNm ());
					mnsvyVO.setGiveActno   (reliefVO.getActno   ());
				}
			}
			// 본조사 수정
			ret = updtMnsvy(mnsvyVO);

			// 지급 - 의료비 저장시 구제급여 상태변경
			if (CommConst.GIVE_MCP.equals(act)) {
				// 2021.12.13 LSH 의료비 추가신청 최종차수 조회
				String aplyOder = reliefService.getReliefLastOder(mnsvyVO.getAplyNo(), CommConst.PRGRE_MNSVY);
				// 2021.12.13 LSH 의료비 추가신청 대상일 경우
				if (CommUtils.isNotEmpty(aplyOder)) {
					// 2021.12.13 LSH 의료비 추가신청정보에 상태 업데이트
					reliefService.updtReliefOderStatus(
						ReliefVO.builder()
						.aplyNo      (mnsvyVO.getAplyNo())
						.aplyOder    (aplyOder)
						.prgreStusCd (CommConst.PRGRE_GIVE)
						.gsUserNo    (mnsvyVO.getGsUserNo())
						.build       ()
					);
				}
				// 2021.12.13 LSH 의료비 추가신청 대상이 아닐 경우
				else {
					// 구제급여신청 상태 업데이트
					reliefService.updtReliefStatus(
						mnsvyVO.getAplyNo(),   // 신청번호 
						CommConst.PRGRE_GIVE,  // 상태코드 (지급)
						mnsvyVO.getGsUserNo()  // 등록자번호
					);
				}
			}
		}
		else if (CommConst.INSERT.equals(mode)) {
			for (MnsvyVO data : list) {
				data.setBizAreaCd(mnsvyVO.getBizAreaCd());
				data.setBizOder  (mnsvyVO.getBizOder  ());
				data.setExmnOder (mnsvyVO.getExmnOder ());
				data.setGsUserNo (mnsvyVO.getGsUserNo ());
				// 본조사 등록
				ret += regiMnsvy(data);
			}
		}
		else if (CommConst.DELETE.equals(mode)) {
			for (MnsvyVO data : list) {
				data.setGsUserNo(mnsvyVO.getGsUserNo());
				// 본조사 삭제
				ret += deltMnsvy(data);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 2021.12.02
     * 본조사 요양생활수당 최종피해등급 저장
     * 본조사 요양생활수당 첨부서류 저장
     * 2023.01.06 최종피해등급 저장 제거함 (구제급여지급-요양생활수당 에서 처리)
     */
	@Override
	public String saveMnsvyRcper(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		// 본조사 최종피해등급 수정
		//ret = updtMnsvy(mnsvyVO);
		
		// 첨부서류목록
		List<ExmnFileVO> files = mnsvyVO.getFileList();
		
		if (files != null &&
			files.size() > 0) {
			// 첨부서류 저장처리
			ret = exmnFileService.saveExmnFile(files, FileDirectory.MNSVY);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 2021.12.02
     * 본조사 심의회결과 저장
     * 본조사 첨부서류 저장
     */
	@Override
	public String saveMnsvyRslt(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		// 본조사 심의회결과 수정
		int ret = updtMnsvy(mnsvyVO);
		
		// 첨부서류목록
		List<ExmnFileVO> files = mnsvyVO.getFileList();
		
		if (ret > 0) {
			if (files != null &&
				files.size() > 0) {
				// 첨부서류 저장처리
				exmnFileService.saveExmnFile(files, FileDirectory.MNSVY);
			}
			// 2021.12.13 LSH 의료비 추가신청 최종차수 조회
			String aplyOder = reliefService.getReliefLastOder(mnsvyVO.getAplyNo(), CommConst.PRGRE_MNSVY);
			// 2021.12.13 LSH 의료비 추가신청 대상일 경우
			if (CommUtils.isNotEmpty(aplyOder)) {
				// 2021.12.13 LSH 의료비 추가신청정보에 상태 업데이트
				reliefService.updtReliefOderStatus(
					ReliefVO.builder()
					.aplyNo      (mnsvyVO.getAplyNo())
					.aplyOder    (aplyOder)
					.prgreStusCd (CommConst.PRGRE_MNSVY)
					.gsUserNo    (mnsvyVO.getGsUserNo())
					.build       ()
				);
			}
			// 2021.12.13 LSH 의료비 추가신청 대상이 아닐 경우
			else {
				// 구제급여신청 상태 업데이트
				reliefService.updtReliefStatus(
					mnsvyVO.getAplyNo(),   // 신청번호 
					CommConst.PRGRE_MNSVY, // 상태코드 (본조사완료)
					mnsvyVO.getGsUserNo()  // 등록자번호
				);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2021.12.02
     * 본조사 심의결과 SMS 발송
     */
	@Override
	public void sendMnsvyRsltSms(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		// 본조사정보 상세조회
		MnsvyVO data = viewMnsvy(mnsvyVO);

		// SMS 업무메세지 공통코드 조회
		CodeVO codeVO = commService.getCode(
			CommConst.CODE_SMSMSG, 
			CommConst.CODE_SMSMSG_MNSVY
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

    /**
     * 본조사계획 페이징목록조회
     */
    @Override
    public PaginatedArrayList listMnsvyPlan(MnsvyVO mnsvyVO, int currPage, int pageSize) throws Exception {
    	return mnsvyPlanDAO.listMnsvyPlan(mnsvyVO, currPage, pageSize);
    }

    /**
     * 본조사계획 목록조회
     */
    @Override
    public List listMnsvyPlan(MnsvyVO mnsvyVO) throws Exception {
    	return mnsvyPlanDAO.listMnsvyPlan(mnsvyVO);
    }

    /**
     * 본조사계획 상세조회
     */
	@Override
	public MnsvyVO viewMnsvyPlan(MnsvyVO mnsvyVO) throws Exception {
		return mnsvyPlanDAO.viewMnsvyPlan(mnsvyVO);
	}

    /**
     * 본조사계획 등록
     */
    private int regiMnsvyPlan(MnsvyVO mnsvyVO) throws Exception {
        return mnsvyPlanDAO.regiMnsvyPlan(mnsvyVO);
    }

    /**
     * 본조사계획 수정
     */
    private int updtMnsvyPlan(MnsvyVO mnsvyVO) throws Exception {
        return mnsvyPlanDAO.updtMnsvyPlan(mnsvyVO);
    }

    /**
     * 본조사계획 삭제
     */
    private int deltMnsvyPlan(MnsvyVO mnsvyVO) throws Exception {
        return mnsvyPlanDAO.deltMnsvyPlan(mnsvyVO);
    }

    /**
     * 본조사계획 등록,수정,삭제
     */
	@Override
	public String saveMnsvyPlan(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = mnsvyVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 본조사계획 수정
			ret = updtMnsvyPlan(mnsvyVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 본조사계획 등록
			ret = regiMnsvyPlan(mnsvyVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 조사대상자 확인
			MnsvyVO data = viewMnsvyPlan(mnsvyVO);
			if (data.getExmnCnt() > 0) {
				throw processException("error.adm.exmnPlan.notRemove");
			}
			// 본조사계획 삭제
			ret = deltMnsvyPlan(mnsvyVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 본조사계획 NEXT 조사차수 가져오기
     */
	@Override
	public String getMnsvyPlanNextOder(MnsvyVO mnsvyVO) throws Exception {
		return mnsvyPlanDAO.getMnsvyPlanNextOder(mnsvyVO);
	}

    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 장의비 확정금액 조회
     * 1) 지급기준년도의 중위소득의 89.7%
     * 2) FN_FNRL_CST_AMT 함수 사용
     * 
     * @param fnrlCrtrYr 지급기준년도 (신청년도)
     */
	@Override
	public long getFnrlCstAmt(String fnrlCrtrYr) throws Exception {
		return mnsvyDAO.getFnrlCstAmt(fnrlCrtrYr);
	}
    
    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 유족보상비,기지급의료비,요양생활수당 조회
     * 1) 유족보상비 확정 = (장의비 * 피해등급별 장의비 지급비율)
     * 2) 유족보상비 잔액 = (유족보상비) - (기지급 의료비 + 요양생활수당) 
     * 3) 장의비의 등급별 지급비율 
     *    - 1등급 1500%
     *    - 2등급 1080%
     *    - 3등급 750%
     *    - 4등급 500%
     *    - 5등급 250%
     * 4) 유족보상비 FN_BRVFM_RWRD_AMT 함수 사용
     * 
     * @param mnsvyVO.bizAreaCd       사업지역코드
     * @param mnsvyVO.bizOder         사업차수
     * @param mnsvyVO.exmnOder        조사차수
     * @param mnsvyVO.aplyNo          신청번호
     * @return brvfmRwrdAmt           유족보상비
     * @param mnsvyVO.lastDmgeGrdCd   피해등급
     * @return mcpDtlsSum             기지급 의료비 합계
     * @return rcperLvlhSum           기지급 요양생활수당 함계
     */
	@Override
	public MnsvyVO viewBrvfmRwrdAmt(MnsvyVO mnsvyVO) throws Exception {
		return mnsvyDAO.viewBrvfmRwrdAmt(mnsvyVO);
	}

    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 장의비 지급등록처리
     * @param mnsvyVO.fnrlCrtrYr     지급기준년도
     * @param mnsvyVO.fnrlCstAmt     장의비확정금액
     * @param mnsvyVO.fnrlCstGiveYmd 장의비지급일자
     */
	@Override
	public String saveFnrlCst(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = mnsvyVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 본조사 수정 (입력정보만 업데이트하도록 재정의)
			ret = updtMnsvy(MnsvyVO.builder()
					.bizAreaCd     (mnsvyVO.getBizAreaCd     ())
					.bizOder       (mnsvyVO.getBizOder       ())
					.exmnOder      (mnsvyVO.getExmnOder      ())
					.aplyNo        (mnsvyVO.getAplyNo        ())
					.gsUserNo      (mnsvyVO.getGsUserNo      ())
					.fnrlCrtrYr    (mnsvyVO.getFnrlCrtrYr    ())
					.fnrlCstAmt    (mnsvyVO.getFnrlCstAmt    ())
					.fnrlCstGiveYmd(mnsvyVO.getFnrlCstGiveYmd())
					.build()
				);
			// 구제급여신청 상태 업데이트
			reliefService.updtReliefStatus(
				mnsvyVO.getAplyNo(),   // 신청번호 
				CommConst.PRGRE_GIVE,  // 상태코드 (지급)
				mnsvyVO.getGsUserNo()  // 등록자번호
			);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 유족보상비 지급등록처리
     * @param mnsvyVO.lastDmgeGrdCd     최종피해등급
     * @param mnsvyVO.brvfmRwrdAmt      유족보상비잔액
     * @param mnsvyVO.brvfmRwmnyGiveYmd 유족보상비지급일자
     */
	@Override
	public String saveBrvfmRwrd(MnsvyVO mnsvyVO) throws Exception {
		
		if (mnsvyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = mnsvyVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 본조사 수정 (입력정보만 업데이트하도록 재정의)
			ret = updtMnsvy(MnsvyVO.builder()
					.bizAreaCd        (mnsvyVO.getBizAreaCd        ())
					.bizOder          (mnsvyVO.getBizOder          ())
					.exmnOder         (mnsvyVO.getExmnOder         ())
					.aplyNo           (mnsvyVO.getAplyNo           ())
					.gsUserNo         (mnsvyVO.getGsUserNo         ())
					.lastDmgeGrdCd    (mnsvyVO.getLastDmgeGrdCd    ())
					.brvfmRwrdAmt     (mnsvyVO.getBrvfmRwrdAmt     ())
					.brvfmRwmnyGiveYmd(mnsvyVO.getBrvfmRwmnyGiveYmd())
					.build()
				);
			// 구제급여신청 상태 업데이트
			reliefService.updtReliefStatus(
				mnsvyVO.getAplyNo(),   // 신청번호 
				CommConst.PRGRE_GIVE,  // 상태코드 (지급)
				mnsvyVO.getGsUserNo()  // 등록자번호
			);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}