package business.bio.relief.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import business.com.CommConst;
import business.com.file.service.PapeMngVO;
import common.util.CommUtils;

/**
 * [검증클래스] - 살생물제품 구제급여신청 Server Side의 데이터 검증을 위한 Validator
 *
 * @class   : BioReliefValidator
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 * Errors 에 대한 사용가능 메서드 
 *     
 *     reject(String errorCode) 
 *     : 전 객체에 대한 글로벌 에러 코드를 추가
 *     
 *     reject(String errorCode, String defaultMessage) 
 *     : 전 객체에 대한 글로벌 에러코드를 추가하고, 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
 *     
 *     reject(String errorCode, Object[] errorArgs, String defaultMessage) 
 *     : 전 객체에 대한 글로벌 에러코드를 추가, 
 *       메세지 인자로 errorArgs를 전달, 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
 *     
 *     rejectValue(String field, String errorCode) 
 *     : 필드에 대한 에러코드를 추가
 *     
 *     rejectValue(String field, String errorCode, String defaultMessage) 
 *     : 필드에 대한 에러코드를 추가 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage를 사용
 *     
 *     rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) 
 *     : 필드에 대한 에러코드를 추가, 
 *       메세지 인자로 errorArgs를 전달, 
 *       에러코드에 대한 메세지가 존재하지 않을 경우 defaultMessage사용
 */
@Service
public class BioReliefValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return BioReliefVO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	
        BioReliefVO data = (BioReliefVO) o;
        // 등록,수정
        String mode = data.getMode();
        // 본인, 대리(생), 대리(사)
        String aplyCd = data.getAplySeCd();
        // 세션사용자권한
        String gsRoleId = data.getGsRoleId();
        // 2022.01.18 임시저장,제출하기
        String act = data.getAct();
        
        // 수정인 경우
        if (CommConst.UPDATE.equals(mode)) {
	        if (CommUtils.nvlTrim(data.getAplyNo()).isEmpty()) {
	            errors.reject("aplyNo", "신청번호가 입력되지 않았습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerNo()).isEmpty()) {
	            errors.reject("sufrerNo", "피해자번호가 존재하지 않습니다.");
	        }
        }

        if (CommUtils.nvlTrim(data.getAplySeCd()).isEmpty()) {
            errors.reject("aplySeCd", "신청구분을 확인할 수 없습니다.");
        }
        if (CommUtils.nvlTrim(data.getAplyKndCd()).isEmpty()) {
            errors.reject("aplyKndCd", "신청종류는 필수입력사항입니다.");
        }
        
        // 관리자인 경우엔 신청자번호는 SKIP
        if (!CommConst.isAdminRole(gsRoleId)) {
	        if (CommUtils.nvlTrim(data.getAplcntNo()).isEmpty()) {
	            errors.reject("aplcntNo", "신청자번호를 확인할 수 없습니다.");
	        }
        }
        if (CommUtils.nvlTrim(data.getAplcntNm()).isEmpty()) {
            errors.reject("aplcntNm", "신청자명는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getAplcntRrno()).isEmpty()) {
            errors.reject("aplcntRrno", "신청자주민등록번호는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getAplcntZip()).isEmpty()) {
            errors.reject("aplcntZip", "신청자우편번호는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getAplcntAddr()).isEmpty()) {
            errors.reject("aplcntAddr", "신청자주소는 필수입력사항입니다.");
        }
        
        // 본인이 아닌 경우
        if (CommConst.RELIEF_APLY_SELF.equals(aplyCd) == false &&
        	CommUtils.nvlTrim(data.getSufrerRelCd()).isEmpty()) {
            errors.reject("sufrerRelCd", "피해자관계코드는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getBankCd()).isEmpty()) {
            errors.reject("bankCd", "은행명는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getActno()).isEmpty()) {
            errors.reject("actno", "계좌번호는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getDpstrNm()).isEmpty()) {
            errors.reject("dpstrNm", "예금주명는 필수입력사항입니다.");
        }
        
        // 대리인 경우
        if (CommConst.RELIEF_APLY_LIVE.equals(aplyCd) ||
        	CommConst.RELIEF_APLY_DPTH.equals(aplyCd)) {
	        if (CommUtils.nvlTrim(data.getSufrerNm()).isEmpty()) {
	            errors.reject("sufrerNm", "피해자명는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerRrno()).isEmpty()) {
	            errors.reject("sufrerRrno", "피해자주민등록번호는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerZip()).isEmpty()) {
	            errors.reject("sufrerZip", "피해자우편번호는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerAddr()).isEmpty()) {
	            errors.reject("sufrerAddr", "피해자주소는 필수입력사항입니다.");
	        }
        }
        // 살생물제품 관련 검증 항목
        if (CommUtils.nvlTrim(data.getDmgePrductCd()).isEmpty()) {
            errors.reject("dmgePrductCd", "피해제품분류는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getDmgePrductCn()).isEmpty()) {
            errors.reject("dmgePrductCn", "피해제품명칭은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductSbmsnYn()).isEmpty()) {
            errors.reject("prductSbmsnYn", "제품제출여부는 필수입력사항입니다.");
        }
        // 제품제출여부가 NO인 경우
        if (CommConst.NO.equals(data.getPrductSbmsnYn()) &&
        	CommUtils.nvlTrim(data.getPrductSbmsnResn()).isEmpty()) {
            errors.reject("prductSbmsnResn", "미제출사유는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUsePrps()).isEmpty()) {
            errors.reject("prductUsePrps", "제품사용목적은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseSe()).isEmpty()) {
            errors.reject("prductUseSe", "제품사용구분은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUsePlce()).isEmpty()) {
            errors.reject("prductUsePlce", "제품사용장소는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseMthd()).isEmpty()) {
            errors.reject("prductUseMthd", "제품사용방법은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductIdntyYn()).isEmpty()) {
            errors.reject("prductIdntyYn", "제품확인여부는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseBgngYmd()).isEmpty()) {
            errors.reject("prductUseBgngYmd", "제품사용시작일자는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseEndYmd()).isEmpty()) {
            errors.reject("prductUseEndYmd", "제품사용종료일자는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseBgngHour()).isEmpty()) {
            errors.reject("prductUseBgngHour", "제품사용시작시는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseEndHour()).isEmpty()) {
            errors.reject("prductUseEndHour", "제품사용종료시는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUseCuntCn()).isEmpty()) {
            errors.reject("prductUseCuntCn", "제품사용횟수내용은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getPrductUsgqtyCn()).isEmpty()) {
            errors.reject("prductUsgqtyCn", "제품사용량내용은 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getDmgeDissNm()).isEmpty()) {
            errors.reject("dmgeDissNm", "건강피해 및 질환명은 필수입력사항입니다.");
        }
        // 신청양식에 따라 업로드한 파일을 VALIDATION한다.
        
        // 신청양식목록
        List<PapeMngVO> papeList = data.getPapeList();
        // 업로드파일목록
        List<BioAplyFileVO> saveFiles = data.getSaveFiles();
        // 신청종류
        List<String> aplyKnds = data.getAplyKndList();
        
        for (PapeMngVO pape : papeList) {
        	
    		// 업로드 파일목록
        	List<BioAplyFileVO> files = new ArrayList<BioAplyFileVO>();
        	
    		for (BioAplyFileVO fileVO : saveFiles) {
    			if (CommUtils.isEqual(pape.getPapeCd(), fileVO.getPapeCd())) {
    				files.add(fileVO);
    			}
    		}
    		// 서류그룹별 검증필요여부 체크
    		boolean check = true;
    		for (int i = 0; i < CommConst.BIO_PAPE_CDS.length; i++) {
    			// 서류그룹코드
    			String papeCd  = CommConst.BIO_PAPE_CDS  [i];
    			// 신청종류코드
    			String aplyKnd = CommConst.BIO_APLY_KNDS [i];
    			// 포함된 신청종류이면 SKIP
    			if (aplyKnds.contains(aplyKnd))
    				continue;
        		if (papeCd.equals(pape.getUpPapeCd())) {
        			check = false;
        			break;
        		}
    		}
    		// 검증필요없는 경우 SKIP
    		if (!check)
    			continue;

    		// 파일 제목명칭
    		String title = "["+pape.getUpPapeNm()+"-"+pape.getPapeNm()+"]";
    		
    		// 임시저장의 경우 필수 체크 SKIP
    		if (CommConst.SUBMIT.equals(act)) {
            	// 필수인 경우
            	if ("Y".equals(pape.getEsntlYn()) && files.size() == 0) {
            		errors.reject("papeFile", title + " 파일은 필수등록사항입니다.");
            	}
    		}
        	// 파일갯수 초과인 경우
        	if (files.size() > Integer.parseInt(pape.getLimtCnt())) {
        		errors.reject("papeFile", title + " 파일은 최대 "+pape.getLimtCnt()+"개 까지만 등록가능합니다.");
        	}
        }
    }

    public void validateModify(Object o, Errors errors) {
    	
        BioReliefVO data = (BioReliefVO) o;
        // 본인, 대리(생), 대리(사)
        String aplyCd = data.getAplySeCd();
        // 이력구분 (피해자정보수정/신청인정보수정/피해내용수정)
        String hstCd = data.getHstSeCd();
        
        // 공통필수조건
        if (CommUtils.nvlTrim(data.getAplyNo()).isEmpty()) {
            errors.reject("aplyNo", "신청번호가 입력되지 않았습니다.");
        }
        if (CommUtils.nvlTrim(data.getAplySeCd()).isEmpty()) {
            errors.reject("aplySeCd", "신청구분을 확인할 수 없습니다.");
        }
        if (CommUtils.nvlTrim(data.getHstCn()).isEmpty()) {
            errors.reject("hstCn", "수정사유는 필수입력사항입니다.");
        }

		// 피해자정보 수정시 (필수조건)
		if      (CommConst.HST_SUFRER.equals(hstCd)) {
	        if (CommUtils.nvlTrim(data.getSufrerNo()).isEmpty()) {
	            errors.reject("sufrerNo", "피해자번호가 존재하지 않습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerNm()).isEmpty()) {
	            errors.reject("sufrerNm", "피해자명는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getAplyKndCd()).isEmpty()) {
	            errors.reject("aplyKndCd", "신청종류는 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerZip()).isEmpty()) {
	            errors.reject("sufrerZip", "피해자우편번호는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerAddr()).isEmpty()) {
	            errors.reject("sufrerAddr", "피해자주소는 필수입력사항입니다.");
	        }
		}
		// 신청인정보 수정시 (필수조건)
		else if (CommConst.HST_APLCNT.equals(hstCd)) {
	        if (CommUtils.nvlTrim(data.getAplyYmd()).isEmpty()) {
	            errors.reject("aplyYmd", "신청일자는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getAplcntNm()).isEmpty()) {
	            errors.reject("aplcntNm", "신청자명는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getAplcntZip()).isEmpty()) {
	            errors.reject("aplcntZip", "신청자우편번호는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getAplcntAddr()).isEmpty()) {
	            errors.reject("aplcntAddr", "신청자주소는 필수입력사항입니다.");
	        }
	        // 본인이 아닌 경우
	        if (CommConst.RELIEF_APLY_SELF.equals(aplyCd) == false &&
	        	CommUtils.nvlTrim(data.getSufrerRelCd()).isEmpty()) {
	            errors.reject("sufrerRelCd", "피해자관계코드는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getBankCd()).isEmpty()) {
	            errors.reject("bankCd", "은행명는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getActno()).isEmpty()) {
	            errors.reject("actno", "계좌번호는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getDpstrNm()).isEmpty()) {
	            errors.reject("dpstrNm", "예금주명는 필수입력사항입니다.");
	        }
	        
		}
		// 피해내용 수정시 (필수조건)
		else if (CommConst.HST_DAMAGE.equals(hstCd)) {
	        // 살생물제품 관련 검증 항목
	        if (CommUtils.nvlTrim(data.getDmgePrductCd()).isEmpty()) {
	            errors.reject("dmgePrductCd", "제품유형은 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getDmgePrductCn()).isEmpty()) {
	            errors.reject("dmgePrductCn", "제품명은 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductSbmsnYn()).isEmpty()) {
	            errors.reject("prductSbmsnYn", "제품제출여부는 필수선택사항입니다.");
	        }
	        // 제품제출여부가 NO인 경우
	        if (CommConst.NO.equals(data.getPrductSbmsnYn()) &&
	        	CommUtils.nvlTrim(data.getPrductSbmsnResn()).isEmpty()) {
	            errors.reject("prductSbmsnResn", "미제출사유는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUsePrps()).isEmpty()) {
	            errors.reject("prductUsePrps", "사용목적은 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseSe()).isEmpty()) {
	            errors.reject("prductUseSe", "사용구분은 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUsePlce()).isEmpty()) {
	            errors.reject("prductUsePlce", "사용장소는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseMthd()).isEmpty()) {
	            errors.reject("prductUseMthd", "사용방법은 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductIdntyYn()).isEmpty()) {
	            errors.reject("prductIdntyYn", "사용주의사항 확인여부는 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseBgngYmd()).isEmpty()) {
	            errors.reject("prductUseBgngYmd", "사용시작일은 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseEndYmd()).isEmpty()) {
	            errors.reject("prductUseEndYmd", "사용종료일은 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseBgngHour()).isEmpty()) {
	            errors.reject("prductUseBgngHour", "사용시작시간은 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseEndHour()).isEmpty()) {
	            errors.reject("prductUseEndHour", "사용종료시간은 필수선택사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUseCuntCn()).isEmpty()) {
	            errors.reject("prductUseCuntCn", "사용빈도(사용횟수)는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrductUsgqtyCn()).isEmpty()) {
	            errors.reject("prductUsgqtyCn", "사용빈도(사용량)는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getDmgeDissNm()).isEmpty()) {
	            errors.reject("dmgeDissNm", "건강피해내용은 필수입력사항입니다.");
	        }
		}
    }    
}
