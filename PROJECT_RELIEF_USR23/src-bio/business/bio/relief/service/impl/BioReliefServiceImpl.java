package business.bio.relief.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.file.service.BioAplyFileService;
import business.bio.relief.service.BioIdntfcService;
import business.bio.relief.service.BioIdntfcVO;
import business.bio.relief.service.BioReliefService;
import business.bio.relief.service.BioReliefVO;
import business.com.CommConst;
import business.sys.sms.service.SmsService;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 살생물제품 구제급여신청 Service 구현 클래스
 * 
 * @class   : BioReliefServiceImpl
 * @author  : LSH
 * @since   : 2023.01.16
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
     * 마이페이지 구제급여신청 목록조회
     */
    @Override
    public List listBioReliefMypage(BioReliefVO bioReliefVO) throws Exception {
    	return reliefDAO.listBioReliefMypage(bioReliefVO);
    }

    /**
     * 구제급여신청 상세조회
     */
	@Override
	public BioReliefVO viewBioRelief(BioReliefVO bioReliefVO) throws Exception {
		return reliefDAO.viewBioRelief(bioReliefVO);
	}
	
    /**
     * 구제급여신청 본인이 작성한 임시저장 KEY 조회
     * @param bioReliefVO.aplySeCd 신청구분
     * @param bioReliefVO.aplcntNo 신청인사용자번호
     * @param bioReliefVO.prgreStusCd 처리상태
     * @param bioReliefVO.rgtrNo 작성자번호
     */
	@Override
    public BioReliefVO viewBioReliefAplyLast(BioReliefVO bioReliefVO) throws Exception {
        return reliefDAO.viewBioReliefAplyLast(bioReliefVO);
    }
	
    /**
     * 관리자대행으로 구제급여신청 임시저장한 최종 신청정보 KEY 조회
     * @param bioReliefVO.prgreStusCd 처리상태 (임시저장상태)
     * @param bioReliefVO.rgtrNo 작성자번호 (관리자번호)
     */
	@Override
    public BioReliefVO viewBioReliefAplyLastAdmn(BioReliefVO bioReliefVO) throws Exception {
        return reliefDAO.viewBioReliefAplyLastAdmn(bioReliefVO);
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
            BioIdntfcVO idntfcVO = idntfcService.viewBioIdntfc(data.getSufrerNo());
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
     * 마이페이지 - 식별아이디기준 최종 KEY 조회
     */
	@Override
    public BioReliefVO viewBioReliefByIdntfcId(String idntfcId) throws Exception {
        return reliefDAO.viewBioReliefByIdntfcId(idntfcId);
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
}