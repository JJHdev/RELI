package business.bio.relief.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.relief.service.BioAplyFileService;
import business.bio.relief.service.BioIdntfcService;
import business.bio.relief.service.BioIdntfcVO;
import business.bio.relief.service.BioMngHstService;
import business.bio.relief.service.BioMngHstVO;
import business.bio.relief.service.BioReliefService;
import business.bio.relief.service.BioReliefVO;
import business.com.CommConst;
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileInfo;
import common.util.CommUtils;

/**
 * [서비스클래스] - 살생물제품 구제급여신청 Service 구현 클래스
 * 
 * @class   : BioReliefServiceImpl
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("BioReliefService")
@SuppressWarnings({"all"})
public class BioReliefServiceImpl extends BaseService implements BioReliefService {

    @Resource(name = "BioReliefDAO")
    private BioReliefDAO reliefDAO;

    // 피해자정보
    @Resource(name = "BioIdntfcService")
    private BioIdntfcService idntfcService;

    // 신청파일정보
    @Resource(name = "BioAplyFileService")
    private BioAplyFileService aplyFileService;

    @Resource(name = "SmsService")
    private SmsService smsService;

    @Resource(name = "BioMngHstService")
    private BioMngHstService mngHstService;

    /**
     * 구제급여신청 페이징목록조회
     */
    @Override
    public PaginatedArrayList listBioRelief(BioReliefVO bioReliefVO, int currPage, int pageSize) throws Exception {
    	return reliefDAO.listBioRelief(bioReliefVO, currPage, pageSize);
    }

    /**
     * 구제급여신청 목록조회
     */
    @Override
    public List listBioRelief(BioReliefVO bioReliefVO) throws Exception {
    	return reliefDAO.listBioRelief(bioReliefVO);
    }

    /**
     * 구제급여신청 상세조회
     */
	@Override
	public BioReliefVO viewBioRelief(BioReliefVO bioReliefVO) throws Exception {
		return reliefDAO.viewBioRelief(bioReliefVO);
	}

	/**
	 * 구제급여신청 상세조회 (피해자정보 포함)
	 */
	@Override
	public BioReliefVO viewBioReliefWidhIdntfc(BioReliefVO bioReliefVO) throws Exception {
		
        // 구제급여 신청정보 상세조회
        BioReliefVO data = viewBioRelief(bioReliefVO);
        
        if (data != null) {
        	// 신청정보 조회데이터 재정의
        	data.rebuildView();
            // 피해자정보 상세조회
            BioIdntfcVO bioIdntfcVO = idntfcService.viewBioIdntfc(data.getSufrerNo());
		    data.setSufrerNm      (bioIdntfcVO.getSufrerNm      ());
		    data.setIdntfcId      (bioIdntfcVO.getIdntfcId      ());
		    data.setSufrerRrno    (bioIdntfcVO.getSufrerRrno    ());
		    data.setSufrerTelno   (bioIdntfcVO.getSufrerTelno   ());
		    data.setSufrerMbtelNo (bioIdntfcVO.getSufrerMbtelNo ());
		    data.setSufrerEmlAddr (bioIdntfcVO.getSufrerEmlAddr ());
		    data.setSufrerZip     (bioIdntfcVO.getSufrerZip     ());
		    data.setSufrerAddr    (bioIdntfcVO.getSufrerAddr    ());
		    data.setSufrerDaddr   (bioIdntfcVO.getSufrerDaddr   ());
		    data.setSufrerBrdt    (bioIdntfcVO.getSufrerBrdt    ());
		    data.setSufrerSxdst   (bioIdntfcVO.getSufrerSxdst   ());
		    data.setSufrerSxdstNm (bioIdntfcVO.getSufrerSxdstNm ());
		    data.setSufrerAge     (bioIdntfcVO.getSufrerAge     ());
		    data.setSufrerAddrAll (bioIdntfcVO.getSufrerAddrAll ());
        }
		return data;
	}

    /**
     * 구제급여신청 등록
     */
    private int regiBioRelief(BioReliefVO bioReliefVO) throws Exception {
		int ret = reliefDAO.regiBioRelief(bioReliefVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			bioReliefVO.setLogTy(CommConst.INSERT);
			reliefDAO.regiBioReliefHst(bioReliefVO);
		}
		return ret;
    }

    /**
     * 구제급여신청 수정
     */
    private int updtBioRelief(BioReliefVO bioReliefVO) throws Exception {
		int ret = reliefDAO.updtBioRelief(bioReliefVO);
		if (ret > 0) {
			// 2021.10.14 LSH 이력저장
			bioReliefVO.setLogTy(CommConst.UPDATE);
			reliefDAO.regiBioReliefHst(bioReliefVO);
		}
		return ret;
    }

    /**
     * 구제급여 접수처리
     * 2022.12.08 접수일자 입력처리 적용
     * 2022.12.08 휴대전화번호 있을 경우에만 SMS 전송처리 적용
     */
	@Override
	public String saveBioReceipt(BioReliefVO bioReliefVO) throws Exception {

		if (bioReliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		List<BioReliefVO> list = bioReliefVO.getReceiptList();
		
		for (BioReliefVO item : list) {

			// 2021.11.01 피해자정보 접수처리 (식별ID 생성)
			// 1) 피해자정보 모델객체 생성
			BioIdntfcVO bioIdntfcVO = BioIdntfcVO.builder()
				    .sufrerNo(item.getSufrerNo())
					.gsUserNo(bioReliefVO.getGsUserNo())
					.build();
			// 2) 피해자정보 신청접수처리 (식별ID 생성)
			idntfcService.saveBioIdntfcReceipt(bioIdntfcVO);
			
	    	// 2022.12.08 접수일자 포맷제거
	    	bioReliefVO.setRcptYmd(CommUtils.toShortDate(bioReliefVO.getRcptYmd(),"-"));
			
			// 3) 신청정보 모델객체 생성
			BioReliefVO data = BioReliefVO.builder()
					.aplyNo      (item.getAplyNo())
					.rcptYmd     (bioReliefVO.getRcptYmd())
					.prgreStusCd (CommConst.PRGRE_RECEIPT)
					.gsUserNo    (bioReliefVO.getGsUserNo())
					.build();
			
			// 4) 신청정보 접수처리
			if (updtBioRelief(data) > 0) {

				// 2022.12.08 휴대전화번호가 있을 경우에만 SMS 전송처리로 변경
				// - 관리자대행시 휴대전화번호가 없을 수 있으므로 해당 적용함.
				if (CommUtils.isNotEmpty(item.getAplcntMbtelNo())) {
					// SMS 업무메세지 전송
					SmsVO smsVO = SmsVO.builder()
							.rcvrNo    (item.getAplcntMbtelNo())
							.rcvrNm    (item.getAplcntNm())
							.trnsterNo (bioReliefVO.getTrnsterNo())
							.trnsterNm (CommConst.SMS_TRANSFER_NM   )
							.smsSeCd   (CommConst.SMS_RELIEF_RECEIPT) // 신청접수
							.trsmStusCd(CommConst.SMS_RESULT_WAIT   ) // 발송대기
							.trsmCn    (bioReliefVO.getTrsmCn())
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
	public String saveBioRelief(BioReliefVO bioReliefVO) throws Exception {
		
		if (bioReliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		// 처리모드
		String mode = bioReliefVO.getMode();
		
		// 1) 피해자정보 모델객체 생성
		BioIdntfcVO bioIdntfcVO = BioIdntfcVO.builder()
			    .sufrerNm      (bioReliefVO.getSufrerNm      ())
			    .sufrerRrno    (bioReliefVO.getSufrerRrno    ())
			    .sufrerTelno   (bioReliefVO.getSufrerTelno   ())
			    .sufrerMbtelNo (bioReliefVO.getSufrerMbtelNo ())
			    .sufrerEmlAddr (bioReliefVO.getSufrerEmlAddr ())
			    .sufrerZip     (bioReliefVO.getSufrerZip     ())
			    .sufrerAddr    (bioReliefVO.getSufrerAddr    ())
			    .sufrerDaddr   (bioReliefVO.getSufrerDaddr   ())
				.sufrerBrdt    (bioReliefVO.getSufrerBrdt    ())
				.sufrerSxdst   (bioReliefVO.getSufrerSxdst   ())
				.sufrerAge     (bioReliefVO.getSufrerAge     ())
				.gsUserNo      (bioReliefVO.getGsUserNo      ())
				.build();

		// 신규신청인 경우
		if (CommConst.INSERT.equals(mode)) {
			// 2) 성명/생년월일 기준 피해자정보가 있는지 확인
			String sufrerNo = idntfcService.getBioIdntfcExistNo(bioIdntfcVO);
			// 피해자정보 저장결과
			int idntfcRet = 0;
			// 3) 피해자정보가 없으면 신규 등록
			if (CommUtils.isEmpty(sufrerNo)) {
				// 피해자정보 등록 (식별ID 신규생성)
				idntfcRet = idntfcService.regiBioIdntfc(bioIdntfcVO);
				// 생성된 피해자번호를 신청정보에 맵핑한다.
				if (idntfcRet > 0)
					bioReliefVO.setSufrerNo(bioIdntfcVO.getSufrerNo());
				
			}
			// 3) 피해자정보가 있으면 피해자정보 수정
			else {
				bioIdntfcVO.setSufrerNo(sufrerNo);
				bioReliefVO.setSufrerNo(sufrerNo);
				// 피해자정보 수정
				idntfcRet = idntfcService.updtBioIdntfc(bioIdntfcVO);
			}
			if (idntfcRet > 0) {
				// 신청정보를 저장한다. (aplyNo 생성 / frstAplyNo와 동일)
				if (regiBioRelief(bioReliefVO) > 0) {
					// 신청파일 업데이트
					// 1) 문서번호 및 처리상태 업데이트
					// 2) 임시경로에서 실제경로로 파일이동처리
					aplyFileService.saveBioReliefFile(bioReliefVO);
					ret = 1;
				}
			}
		}
		// 수정인 경우
		else if (CommConst.UPDATE.equals(mode)) {
			// 1) 피해자정보 수정
			bioIdntfcVO.setSufrerNo(bioReliefVO.getSufrerNo());

			// 피해자정보 수정처리
			if (idntfcService.updtBioIdntfc(bioIdntfcVO) > 0) {
				// 신청정보를 저장한다.
				if (updtBioRelief(bioReliefVO) > 0) {
					// 신청파일 업데이트
					// 1) 문서번호 및 처리상태 업데이트
					// 2) 임시경로에서 실제경로로 파일이동처리
					aplyFileService.saveBioReliefFile(bioReliefVO);
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
     * 구제급여접수 - 피해자정보 수정처리
     * 구제급여접수 - 신청인정보 수정처리
     * 구제급여접수 - 피해내용 수정처리
     */
	@Override
	public String saveBioReliefModify(BioReliefVO bioReliefVO) throws Exception {
		
		if (bioReliefVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		// 이력구분
		String hstCd = bioReliefVO.getHstSeCd();
		
		// 피해자정보 수정시
		if      (CommConst.HST_SUFRER.equals(hstCd)) {
			BioIdntfcVO bioIdntfcVO = BioIdntfcVO.builder()
				    .sufrerNm      (bioReliefVO.getSufrerNm      ())
				    .sufrerTelno   (bioReliefVO.getSufrerTelno   ())
				    .sufrerMbtelNo (bioReliefVO.getSufrerMbtelNo ())
				    .sufrerEmlAddr (bioReliefVO.getSufrerEmlAddr ())
				    .sufrerZip     (bioReliefVO.getSufrerZip     ())
				    .sufrerAddr    (bioReliefVO.getSufrerAddr    ())
				    .sufrerDaddr   (bioReliefVO.getSufrerDaddr   ())
					.sufrerNo      (bioReliefVO.getSufrerNo      ())
					.gsUserNo      (bioReliefVO.getGsUserNo      ())
					.build();
			// 피해자정보 수정처리
			ret = idntfcService.updtBioIdntfc(bioIdntfcVO);
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
			ret = updtBioRelief(bioReliefVO);
			
			// 관리이력을 저장한다.
			if (ret > 0) {
				BioMngHstVO bioMngHstVO = BioMngHstVO.builder()
						.dtySeCd  (CommConst.DTY_BIO        )
						.aplyNo   (bioReliefVO.getAplyNo  ())
						.hstSeCd  (bioReliefVO.getHstSeCd ())
						.hstCn    (bioReliefVO.getHstCn   ())
						.gsUserNo (bioReliefVO.getGsUserNo())
						.build    ();
				bioMngHstVO.setMode(CommConst.INSERT);

				mngHstService.saveBioMngHst(bioMngHstVO);
			}
		}
		
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 관리자 제출서류 추가등록 업로드 및 저장처리
     */
	@Override
	public String saveBioReliefAddfile(BioReliefVO bioReliefVO, FileInfo fileInfo) throws Exception {
		
		int ret = 0;
		
		if (fileInfo == null)
			throw processException("error.comm.notTarget");
		
    	// 신청번호 기준 신청정보 조회
		 BioReliefVO vo = viewBioRelief(bioReliefVO);
    	if (vo == null)
			throw processException("error.comm.notTarget");
		
    	fileInfo.setGsUserNo(bioReliefVO.getGsUserNo());
    	// 파일경로맵핑시 신청일자가 필요함
    	bioReliefVO.setAplyYmd(CommUtils.toShortDate(vo.getAplyYmd(), "-"));
    	
	    // 업로드한 파일정보를 저장한다.
    	String sn = aplyFileService.saveBioReliefAddfile(bioReliefVO, fileInfo);
    	
		if (sn != null) {
			// 관리이력 저장처리
			BioMngHstVO bioMngHstVO = BioMngHstVO.builder()
					.dtySeCd  (CommConst.DTY_BIO     )
					.hstSeCd  (CommConst.HST_PAPER   )
					.relKeyNo (sn                    )
					.aplyNo   (bioReliefVO.getAplyNo  ())
					.hstCn    (bioReliefVO.getHstCn   ())
					.gsUserNo (bioReliefVO.getGsUserNo())
					.build    ();
			bioMngHstVO.setMode(CommConst.INSERT);
			mngHstService.saveBioMngHst(bioMngHstVO);
			ret = 1;
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}