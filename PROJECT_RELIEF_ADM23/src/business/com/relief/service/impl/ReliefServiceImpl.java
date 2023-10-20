package business.com.relief.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.biz.service.MngHstService;
import business.com.biz.service.MngHstVO;
import business.com.biz.service.SplemntService;
import business.com.biz.service.SplemntVO;
import business.com.file.service.AplyFileService;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.IdntfcVO;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import business.com.survey.service.SurveyRspnsService;
import business.com.survey.service.SurveyVO;
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileInfo;
import common.util.CommUtils;

/**
 * [서비스클래스] - 구제급여신청 Service 구현 클래스
 * 
 * @class   : ReliefServiceImpl
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("ReliefService")
@SuppressWarnings({"all"})
public class ReliefServiceImpl extends BaseService implements ReliefService {

    @Resource(name = "ReliefDAO")
    private ReliefDAO reliefDAO;

    @Resource(name = "ReliefOderDAO")
    private ReliefOderDAO reliefOderDAO;

    // 피해자정보
    @Resource(name = "IdntfcService")
    private IdntfcService idntfcService;

    // 신청파일정보
    @Resource(name = "AplyFileService")
    private AplyFileService aplyFileService;

    // 보완요청정보
    @Resource(name = "SplemntService")
    private SplemntService splemntService;

    @Resource(name="SurveyRspnsService")
    protected SurveyRspnsService surveyRspnsService;

    @Resource(name = "SmsService")
    private SmsService smsService;

    @Resource(name = "MngHstService")
    private MngHstService mngHstService;

    /**
     * 구제급여신청 페이징목록조회
     */
    @Override
    public PaginatedArrayList listRelief(ReliefVO reliefVO, int currPage, int pageSize) throws Exception {
    	return reliefDAO.listRelief(reliefVO, currPage, pageSize);
    }

    /**
     * 구제급여신청 목록조회
     */
    @Override
    public List listRelief(ReliefVO reliefVO) throws Exception {
    	return reliefDAO.listRelief(reliefVO);
    }

    /**
     * 2022.01.05 LSH
     * 예비조사/본조사 구제급여 대상자목록조회
     */
    @Override
    public List listReliefTarget(ReliefVO reliefVO) throws Exception {
    	return reliefDAO.listReliefTarget(reliefVO);
    }

    /**
     * 구제급여신청 상세조회
     */
	@Override
	public ReliefVO viewRelief(ReliefVO reliefVO) throws Exception {
		return reliefDAO.viewRelief(reliefVO);
	}

	/**
	 * 2021.10.18 LSH
	 * 구제급여신청 상세조회 (피해자정보 포함)
	 */
	@Override
	public ReliefVO viewReliefWidhIdntfc(ReliefVO reliefVO) throws Exception {
		
        // 구제급여 신청정보 상세조회
        ReliefVO data = viewRelief(reliefVO);
        
        if (data != null) {
        	// 신청정보 조회데이터 재정의
        	data.rebuildView();
            // 피해자정보 상세조회
            IdntfcVO idntfcVO = idntfcService.viewIdntfc(data.getSufrerNo());
		    data.setSufrerNm      (idntfcVO.getSufrerNm      ());
		    data.setIdntfcId      (idntfcVO.getIdntfcId      ());
		    data.setSufrerRrno    (idntfcVO.getSufrerRrno    ());
		    data.setSufrerTelno   (idntfcVO.getSufrerTelno   ());
		    data.setSufrerMbtelNo (idntfcVO.getSufrerMbtelNo ());
		    data.setSufrerEmlAddr (idntfcVO.getSufrerEmlAddr ());
		    data.setSufrerZip     (idntfcVO.getSufrerZip     ());
		    data.setSufrerAddr    (idntfcVO.getSufrerAddr    ());
		    data.setSufrerDaddr   (idntfcVO.getSufrerDaddr   ());
		    data.setSufrerBrdt    (idntfcVO.getSufrerBrdt    ());
		    data.setSufrerSxdst   (idntfcVO.getSufrerSxdst   ());
		    data.setSufrerSxdstNm (idntfcVO.getSufrerSxdstNm ());
		    data.setSufrerAge     (idntfcVO.getSufrerAge     ());
		    data.setSufrerAddrAll (idntfcVO.getSufrerAddrAll ());
        }
		return data;
	}
	
    /**
     * 2021.10.14 LSH
     * 구제급여신청 본인이 작성한 임시저장 KEY 조회
     * @param reliefVO.aplySeCd 신청구분
     * @param reliefVO.aplcntNo 신청인사용자번호
     * @param reliefVO.prgreStusCd 처리상태
     * @param reliefVO.rgtrNo 작성자번호
     */
	@Override
    public ReliefVO viewReliefAplyLast(ReliefVO reliefVO) throws Exception {
        return reliefDAO.viewReliefAplyLast(reliefVO);
    }
	
    /**
     * 2022.12.01 LSH
     * 관리자대행으로 구제급여신청 임시저장한 최종 신청정보 KEY 조회
     * @param reliefVO.prgreStusCd 처리상태 (임시저장상태)
     * @param reliefVO.rgtrNo 작성자번호 (관리자번호)
     */
	@Override
    public ReliefVO viewReliefAplyLastAdmn(ReliefVO reliefVO) throws Exception {
        return reliefDAO.viewReliefAplyLastAdmn(reliefVO);
    }

    /**
     * 2021.11.08 LSH
     * 마이페이지 - 식별아이디기준 최종 KEY 조회
     */
	@Override
    public ReliefVO viewReliefByIdntfcId(String idntfcId) throws Exception {
        return reliefDAO.viewReliefByIdntfcId(idntfcId);
    }

    /**
     * 구제급여신청 등록
     */
    private int regiRelief(ReliefVO reliefVO) throws Exception {
		int ret = reliefDAO.regiRelief(reliefVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			reliefVO.setLogTy(CommConst.INSERT);
			reliefDAO.regiReliefHst(reliefVO);
		}
		return ret;
    }

    /**
     * 구제급여신청 수정
     */
    private int updtRelief(ReliefVO reliefVO) throws Exception {
		int ret = reliefDAO.updtRelief(reliefVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			reliefVO.setLogTy(CommConst.UPDATE);
			reliefDAO.regiReliefHst(reliefVO);
		}
		return ret;
    }

    /**
     * (사용안함)
     * 구제급여신청 삭제 
     * (이력을 먼저 저장후 삭제한다)
     */
    private int deltRelief(ReliefVO reliefVO) throws Exception {
    	int ret = 0;
		// 2021.10.14 LSH 이력저장
		reliefVO.setLogTy(CommConst.DELETE);
		if (reliefDAO.regiReliefHst(reliefVO) > 0) {
			ret = reliefDAO.deltRelief(reliefVO);
		}
		return ret;
    }

    /**
     * 2021.10.26
     * 구제급여 접수처리
     * 2022.12.08 접수일자 입력처리 적용
     * 2022.12.08 휴대전화번호 있을 경우에만 SMS 전송처리 적용
     */
	@Override
	public String saveReceipt(ReliefVO reliefVO) throws Exception {

		if (reliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		List<ReliefVO> list = reliefVO.getReceiptList();
		
		for (ReliefVO item : list) {

			// 2021.11.01 피해자정보 접수처리 (식별ID 생성)
			// 1) 피해자정보 모델객체 생성
			IdntfcVO idntfcVO = IdntfcVO.builder()
				    .sufrerNo(item.getSufrerNo())
					.gsUserNo(reliefVO.getGsUserNo())
					.build();
			// 2) 피해자정보 신청접수처리 (식별ID 생성)
			idntfcService.saveIdntfcReceipt(idntfcVO);
			
	    	// 2022.12.08 접수일자 포맷제거
	    	reliefVO.setRcptYmd(CommUtils.toShortDate(reliefVO.getRcptYmd(),"-"));
			
			// 3) 신청정보 모델객체 생성
			ReliefVO data = ReliefVO.builder()
					.aplyNo      (item.getAplyNo())
					.aplyOder    (item.getAplyOder())
					.rcptYmd     (reliefVO.getRcptYmd())
					.prgreStusCd (CommConst.PRGRE_RECEIPT)
					.bizAreaCd   (reliefVO.getBizAreaCd()) // 2021.12.27 ADD 접수시 피해지역 정의함
					.gsUserNo    (reliefVO.getGsUserNo())
					.build();
			
			// 4) 신청정보 접수처리
			if (updtRelief(data) > 0) {

				// 2022.12.08 휴대전화번호가 있을 경우에만 SMS 전송처리로 변경
				// - 관리자대행시 휴대전화번호가 없을 수 있으므로 해당 적용함.
				if (CommUtils.isNotEmpty(item.getAplcntMbtelNo())) {
					// SMS 업무메세지 전송
					SmsVO smsVO = SmsVO.builder()
							.rcvrNo    (item.getAplcntMbtelNo())
							.rcvrNm    (item.getAplcntNm())
							.trnsterNo (reliefVO.getTrnsterNo())
							.trnsterNm (CommConst.SMS_TRANSFER_NM   )
							.smsSeCd   (CommConst.SMS_RELIEF_RECEIPT) // 신청접수
							.trsmStusCd(CommConst.SMS_RESULT_WAIT   ) // 발송대기
							.trsmCn    (reliefVO.getTrsmCn())
							.build();
					
					// SMS 발송
					smsService.sendSms(smsVO);
				}
				ret++;
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 구제급여신청 
     * 1) mode = 'I' : 임시저장/제출하기
     *    2021.10.19 피해자정보 성명/생년월일 기준 중복확인
     */
	@Override
	public String saveRelief(ReliefVO reliefVO) throws Exception {
		
		if (reliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		// 처리모드
		String mode = reliefVO.getMode();
		
		// 1) 피해자정보 모델객체 생성
		IdntfcVO idntfcVO = IdntfcVO.builder()
			    .sufrerNm      (reliefVO.getSufrerNm      ())
			    .sufrerRrno    (reliefVO.getSufrerRrno    ())
			    .sufrerTelno   (reliefVO.getSufrerTelno   ())
			    .sufrerMbtelNo (reliefVO.getSufrerMbtelNo ())
			    .sufrerEmlAddr (reliefVO.getSufrerEmlAddr ())
			    .sufrerZip     (reliefVO.getSufrerZip     ())
			    .sufrerAddr    (reliefVO.getSufrerAddr    ())
			    .sufrerDaddr   (reliefVO.getSufrerDaddr   ())
				.sufrerBrdt    (reliefVO.getSufrerBrdt    ())
				.sufrerSxdst   (reliefVO.getSufrerSxdst   ())
				.sufrerAge     (reliefVO.getSufrerAge     ())
				.gsUserNo      (reliefVO.getGsUserNo      ())
				.build();

		// 신규신청인 경우
		if (CommConst.INSERT.equals(mode)) {
			// 2) 성명/생년월일 기준 피해자정보가 있는지 확인
			String sufrerNo = idntfcService.getIdntfcExistNo(idntfcVO);
			// 피해자정보 저장결과
			int idntfcRet = 0;
			// 3) 피해자정보가 없으면 신규 등록
			if (CommUtils.isEmpty(sufrerNo)) {
				// 피해자정보 등록 (식별ID 신규생성)
				idntfcRet = idntfcService.regiIdntfc(idntfcVO);
				// 생성된 피해자번호를 신청정보에 맵핑한다.
				if (idntfcRet > 0)
					reliefVO.setSufrerNo(idntfcVO.getSufrerNo());
				
			}
			// 3) 피해자정보가 있으면 피해자정보 수정
			else {
				idntfcVO.setSufrerNo(sufrerNo);
				reliefVO.setSufrerNo(sufrerNo);
				// 피해자정보 수정
				idntfcRet = idntfcService.updtIdntfc(idntfcVO);
			}
			if (idntfcRet > 0) {
				// 신청정보를 저장한다. (aplyNo 생성 / frstAplyNo와 동일)
				if (regiRelief(reliefVO) > 0) {
					// 신청파일 업데이트
					// 1) 문서번호 및 처리상태 업데이트
					// 2) 임시경로에서 실제경로로 파일이동처리
					aplyFileService.saveReliefFile(reliefVO);
					
					// 2022.01.04 LSH 온라인 설문지 신청번호 업데이트
					if (CommUtils.isNotEmpty(reliefVO.getRspnsMngNo())) {
						SurveyVO surveyVO = SurveyVO.builder()
								.aplyNo     (reliefVO.getAplyNo     ())
								.rspnsMngNo (reliefVO.getRspnsMngNo ())
								.gsUserNo   (reliefVO.getGsUserNo   ())
								.build      ();
						surveyRspnsService.updtSurvey(surveyVO);
					}
					ret = 1;
				}
			}
		}
		// 수정인 경우
		else if (CommConst.UPDATE.equals(mode)) {
			// 1) 피해자정보 수정
			idntfcVO.setSufrerNo(reliefVO.getSufrerNo());

			// 피해자정보 수정처리
			if (idntfcService.updtIdntfc(idntfcVO) > 0) {
				// 신청정보를 저장한다.
				if (updtRelief(reliefVO) > 0) {
					// 신청파일 업데이트
					// 1) 문서번호 및 처리상태 업데이트
					// 2) 임시경로에서 실제경로로 파일이동처리
					aplyFileService.saveReliefFile(reliefVO);
					ret = 1;
				}
			}
		}
		
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 페이징목록조회
     */
    @Override
    public PaginatedArrayList listReliefGive(ReliefVO reliefVO, int currPage, int pageSize) throws Exception {
    	return reliefDAO.listReliefGive(reliefVO, currPage, pageSize);
    }

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 목록조회
     */
    @Override
    public List listReliefGive(ReliefVO reliefVO) throws Exception {
    	return reliefDAO.listReliefGive(reliefVO);
    }

    /**
     * 2021.10.30 LSH
     * 구제급여신청 보완제출하기 [마이페이지에서 호출됨]
     * - 신청파일의 저장처리 및 상태변경
     * - 보완요청 상태변경 처리
     */
	@Override
	public String saveReliefSplemnt(ReliefVO reliefVO) throws Exception {
		
		if (reliefVO == null)
			throw processException("error.comm.notTarget");

		// 2022.02.03 LSH 신청일자를 가져오기위해 신청정보 조회
		ReliefVO viewVO = viewRelief(reliefVO);
		
		if (viewVO == null)
			throw processException("error.comm.notTarget");
		
		// 신청상태 정의 (제출상태)
		reliefVO.setPrgreStusCd(CommConst.PRGRE_APPLY);
    	// 신청일자 칼럼 재정의 (파일경로에 필요함)
    	reliefVO.setAplyYmd(CommUtils.toShortDate(viewVO.getAplyYmd(),"-"));
    	// 신청차수 재정의
		// 2022.02.03 LSH 보완요청시엔 기본신청차수로 맵핑함
    	reliefVO.setAplyOder(CommConst.APLY_ODER_RELIEF);
		
		SplemntVO splemntVO = SplemntVO.builder()
				.aplyNo         (reliefVO.getAplyNo())
				.aplyOder       (reliefVO.getAplyOder())
				.gsUserNo       (reliefVO.getGsUserNo())
				.prcsStusCd     (CommConst.SPLEMNT_COMPLETE)   // 보완완료
				.splemntPrcsYmd (CommUtils.getCurDateString()) // 보완처리일자
				.build();
		
		// 보완요청상태 업데이트
		splemntVO.setMode(CommConst.UPDATE);
		splemntService.saveSplemnt(splemntVO);

		// 신청파일 업데이트
		aplyFileService.saveReliefFile(reliefVO);

		return message.getMessage("prompt.success");
	}

    /**
     * 2021.11.09 LSH
     * 구제급여 의료비 추가신청처리 [마이페이지에서 호출됨]
     * - 신청파일의 저장처리 및 상태변경
     * - 신청차수 증가처리
     */
	@Override
	public String saveReliefAdd(ReliefVO reliefVO) throws Exception {
		
		if (reliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;

    	// 신청일자 정의
    	reliefVO.setAplyYmd(CommUtils.getCurDateString());
		// 신청상태 정의 (제출상태)
		reliefVO.setPrgreStusCd(CommConst.PRGRE_APPLY);
		// 신청차수정보 등록
		if (reliefOderDAO.regiReliefOder(reliefVO) > 0) {
			// 신청파일 업데이트
			// 1) 문서번호 및 처리상태 업데이트
			// 2) 임시경로에서 실제경로로 파일이동처리
			aplyFileService.saveReliefFile(reliefVO);
			ret = 1;
		}
		return message.getMessage("prompt.success");
	}

    /**
     * 2021.11.29 LSH
     * 구제급여신청 상태변경처리
     * (본조사완료 / 지급완료 / 반려완료)
     */
	@Override
	public int updtReliefStatus(String aplyNo, String prgreStusCd, String gsUserNo) throws Exception {
		ReliefVO reliefVO = ReliefVO.builder()
				.aplyNo      (aplyNo)
				.prgreStusCd (prgreStusCd)
				.gsUserNo    (gsUserNo)
				.build();
		// 상태업데이트 및 이력저장
		return updtRelief(reliefVO);
	}
	
    /**
     * 2022.12.23 LSH
     * 구제급여신청 상태변경처리 (예비조사완료)
     * updtReliefStatus에서 예비조사만 별도로 분리 처리함
     * @param reliefVO.aplyNo      신청번호
     * @param reliefVO.prgreStusCd 진행상태
     * @param reliefVO.dmgeRcognYn 인정여부
     * @param reliefVO.gsUserNo    사용자번호
     */
	@Override
    public int updtReliefStatus(ReliefVO reliefVO) throws Exception {
		// 상태업데이트 및 이력저장
		return updtRelief(reliefVO);
	}

    /**
     * 2021.12.08 LSH
     * 마이페이지 구제급여신청 목록조회
     */
    @Override
    public List listReliefMypage(ReliefVO reliefVO) throws Exception {
    	return reliefDAO.listReliefMypage(reliefVO);
    }

    /**
     * 2021.12.07 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 의료비 총합 조회
     */
    @Override
    public Map viewReliefGiveMCP(ReliefVO reliefVO) throws Exception {
		return reliefDAO.viewReliefGiveMCP(reliefVO);
    }
    /**
     * 2021.12.07 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 요양생활수당 총합 조회
     */
    @Override
    public Map viewReliefGiveRCP(ReliefVO reliefVO) throws Exception {
		return reliefDAO.viewReliefGiveRCP(reliefVO);
    }

    /**
     * 2021.12.09 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 의료비 세부내역 목록조회
     */
    @Override
    public PaginatedArrayList listReliefGiveMCPDtls(ReliefVO reliefVO, int currPage, int pageSize) throws Exception {
    	return reliefDAO.listReliefGiveMCPDtls(reliefVO, currPage, pageSize);
    }    
    /**
     * 2021.12.09 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 요양생활수당 세부내역 목록조회
     */
    @Override
    public PaginatedArrayList listReliefGiveRCPDtls(ReliefVO reliefVO, int currPage, int pageSize) throws Exception {
    	return reliefDAO.listReliefGiveRCPDtls(reliefVO, currPage, pageSize);
    }
    
    /**
     * 2021.12.13 LSH
     * 의료비 추가신청 최종차수 가져오기
     * 본조사인 경우 접수(04)/본조사(06)건의 최종차수
     * 지급인   경우 본조사(06)/지급(07)건의 최종차수
     */
    @Override
    public String getReliefLastOder(String aplyNo, String prgreStusCd) throws Exception {
    	ReliefVO reliefVO = ReliefVO.builder()
    			.aplyNo      (aplyNo)
    			.prgreStusCd (prgreStusCd)
    			.build       ();
    	return reliefOderDAO.getReliefLastOder(reliefVO);
    }
    
    /**
     * 2021.12.13 LSH
     * 의료비 추가신청 상태변경처리
     * (본조사완료 / 지급완료)
     * @param reliefVO.aplyNo      신청번호
     * @param reliefVO.aplyOder    신청차수
     * @param reliefVO.prgreStusCd 변경상태
     * @param reliefVO.gsUserNo    세션사용자번호
     */
    @Override
    public int updtReliefOderStatus(ReliefVO reliefVO) throws Exception {
    	return reliefOderDAO.updtReliefOder(reliefVO);
    }
    
    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 페이징목록 조회
     */
    @Override
    public PaginatedArrayList listReliefOder(ReliefVO reliefVO, int currPage, int pageSize) throws Exception {
    	return reliefOderDAO.listReliefOder(reliefVO, currPage, pageSize);
    }

    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 목록조회
     */
    @Override
    public List listReliefOder(ReliefVO reliefVO) throws Exception {
    	return reliefOderDAO.listReliefOder(reliefVO);
    }

    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 상세조회
     */
    @Override
    public ReliefVO viewReliefOder(ReliefVO reliefVO) throws Exception {
		
        // 상세조회
        ReliefVO data = reliefOderDAO.viewReliefOder(reliefVO);
        
        if (data != null) {
        	// 신청정보 조회데이터 재정의
        	data.rebuildView();
            // 피해자정보 상세조회
            IdntfcVO idntfcVO = idntfcService.viewIdntfc(data.getSufrerNo());
		    data.setSufrerNm      (idntfcVO.getSufrerNm      ());
		    data.setIdntfcId      (idntfcVO.getIdntfcId      ());
		    data.setSufrerRrno    (idntfcVO.getSufrerRrno    ());
		    data.setSufrerTelno   (idntfcVO.getSufrerTelno   ());
		    data.setSufrerMbtelNo (idntfcVO.getSufrerMbtelNo ());
		    data.setSufrerEmlAddr (idntfcVO.getSufrerEmlAddr ());
		    data.setSufrerZip     (idntfcVO.getSufrerZip     ());
		    data.setSufrerAddr    (idntfcVO.getSufrerAddr    ());
		    data.setSufrerDaddr   (idntfcVO.getSufrerDaddr   ());
		    data.setSufrerBrdt    (idntfcVO.getSufrerBrdt    ());
		    data.setSufrerSxdst   (idntfcVO.getSufrerSxdst   ());
		    data.setSufrerSxdstNm (idntfcVO.getSufrerSxdstNm ());
		    data.setSufrerAge     (idntfcVO.getSufrerAge     ());
		    data.setSufrerAddrAll (idntfcVO.getSufrerAddrAll ());
        }
		return data;
    }
    
    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 접수처리
     */
    @Override
    public String saveReliefOder(ReliefVO reliefVO) throws Exception {

		if (reliefVO == null)
			throw processException("error.comm.notTarget");
		
		// 모델객체 생성
		ReliefVO data = ReliefVO.builder()
				.aplyNo      (reliefVO.getAplyNo())
				.aplyOder    (reliefVO.getAplyOder())
				.rcptYmd     (CommUtils.getCurDateString())
				.prgreStusCd (CommConst.PRGRE_RECEIPT)
				.gsUserNo    (reliefVO.getGsUserNo())
				.build();
		
		// 접수처리
		int ret = reliefOderDAO.updtReliefOder(data);
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
    }
    
    /**
     * 2021.12.15 LSH
     * 예비조사 기인정자의 진행상태 일괄 업데이트
     */
    @Override
    public int updtReliefStatusLgcyAll(ReliefVO reliefVO) throws Exception {
    	return reliefDAO.updtReliefStatusLgcyAll(reliefVO);
    }
    
    /**
     * 2022.12.05
     * 구제급여접수 - 피해자정보 수정처리
     * 구제급여접수 - 신청인정보 수정처리
     * 구제급여접수 - 피해내용 수정처리
     */
	@Override
	public String saveReliefModify(ReliefVO reliefVO) throws Exception {
		
		if (reliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		// 이력구분
		String hstCd = reliefVO.getHstSeCd();
		
		// 피해자정보 수정시
		if      (CommConst.HST_SUFRER.equals(hstCd)) {
			IdntfcVO idntfcVO = IdntfcVO.builder()
				    .sufrerNm      (reliefVO.getSufrerNm      ())
				    .sufrerTelno   (reliefVO.getSufrerTelno   ())
				    .sufrerMbtelNo (reliefVO.getSufrerMbtelNo ())
				    .sufrerEmlAddr (reliefVO.getSufrerEmlAddr ())
				    .sufrerZip     (reliefVO.getSufrerZip     ())
				    .sufrerAddr    (reliefVO.getSufrerAddr    ())
				    .sufrerDaddr   (reliefVO.getSufrerDaddr   ())
					.sufrerNo      (reliefVO.getSufrerNo      ())
					.gsUserNo      (reliefVO.getGsUserNo      ())
					.build();
			// 피해자정보 수정처리
			ret = idntfcService.updtIdntfc(idntfcVO);
		}
		// 신청인정보 수정시
		else if (CommConst.HST_APLCNT.equals(hstCd)) {
			ret = 1;
		}
		// 피해내용 수정시
		else if (CommConst.HST_DAMAGE.equals(hstCd)) {
			ret = 1;
		}
		if (ret > 0) {
			// 신청정보를 저장한다.
			ret = updtRelief(reliefVO);
			
			// 관리이력을 저장한다.
			if (ret > 0) {
				MngHstVO mngHstVO = MngHstVO.builder()
						.dtySeCd  (CommConst.DTY_RELIEF  )
						.aplyNo   (reliefVO.getAplyNo  ())
						.hstSeCd  (reliefVO.getHstSeCd ())
						.hstCn    (reliefVO.getHstCn   ())
						.gsUserNo (reliefVO.getGsUserNo())
						.build    ();
				mngHstVO.setMode(CommConst.INSERT);

				mngHstService.saveMngHst(mngHstVO);
			}
		}
		
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 2022.12.06 LSH
     * 관리자 제출서류 추가등록 업로드 및 저장처리
     */
	@Override
	public String saveReliefAddfile(ReliefVO reliefVO, FileInfo fileInfo) throws Exception {
		
		int ret = 0;
		
		if (fileInfo == null)
			throw processException("error.comm.notTarget");
		
    	// 신청번호 기준 신청정보 조회
		 ReliefVO vo = viewRelief(reliefVO);
    	if (vo == null)
			throw processException("error.comm.notTarget");
		
    	fileInfo.setGsUserNo(reliefVO.getGsUserNo());
    	// 파일경로맵핑시 신청일자가 필요함
    	reliefVO.setAplyYmd(CommUtils.toShortDate(vo.getAplyYmd(), "-"));
    	
	    // 업로드한 파일정보를 저장한다.
    	String sn = aplyFileService.saveReliefAddfile(reliefVO, fileInfo);
    	
		if (sn != null) {
			// 관리이력 저장처리
			MngHstVO mngHstVO = MngHstVO.builder()
					.dtySeCd  (CommConst.DTY_RELIEF  )
					.hstSeCd  (CommConst.HST_PAPER   )
					.relKeyNo (sn                    )
					.aplyNo   (reliefVO.getAplyNo  ())
					.hstCn    (reliefVO.getHstCn   ())
					.gsUserNo (reliefVO.getGsUserNo())
					.build    ();
			mngHstVO.setMode(CommConst.INSERT);
			mngHstService.saveMngHst(mngHstVO);
			ret = 1;
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}