package business.com.relief.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import business.com.CommConst;
import business.com.file.service.AplyFileVO;
import business.com.file.service.PapeMngVO;
import common.util.CommUtils;

/**
 * [검증클래스] - 구제급여신청 Server Side의 데이터 검증을 위한 Validator
 *
 * @class   : ReliefValidator
 * @author  : LSH
 * @since   : 2021.10.12
 * @version : 1.0
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
public class ReliefValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return ReliefVO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	
        ReliefVO data = (ReliefVO) o;
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
        // 2021.12.27 피해지역 필수제외 / 기타피해지역 필수추가
        if (CommUtils.nvlTrim(data.getEtcDmgeArea()).isEmpty()) {
            errors.reject("etcDmgeArea", "피해지역은 필수입력사항입니다.");
        }
        //if (CommUtils.nvlTrim(data.getBizAreaCd()).isEmpty()) {
        //    errors.reject("bizAreaCd", "피해지역은 필수선택사항입니다.");
        //}
        //else if (CommUtils.nvlTrim(data.getEtcDmgeArea()).isEmpty() &&
        //		 CommConst.ETC_AREA.equals(data.getBizAreaCd())) {
        //    errors.reject("etcDmgeArea", "기타피해지역이 입력되지 않았습니다.");
        //}
        
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
        // 2022.12.01 LSH 휴대전화번호 필수 제외
        //if (CommUtils.nvlTrim(data.getAplcntMbtelNo()).isEmpty()) {
        //    errors.reject("aplcntMbtelNo", "신청자휴대전화번호는 필수입력사항입니다.");
        //}
        if (CommUtils.nvlTrim(data.getAplcntZip()).isEmpty()) {
            errors.reject("aplcntZip", "신청자우편번호는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getAplcntAddr()).isEmpty()) {
            errors.reject("aplcntAddr", "신청자주소는 필수입력사항입니다.");
        }
        //if (CommUtils.nvlTrim(data.getAplcntDaddr()).isEmpty()) {
        //    errors.reject("aplcntDaddr", "신청자상세주소는 필수입력사항입니다.");
        //}
        
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
//	        if (CommUtils.nvlTrim(data.getSufrerDaddr()).isEmpty()) {
//	            errors.reject("sufrerDaddr", "피해자상세주소는 필수입력사항입니다.");
//	        }
	        // 2022.12.01 LSH 휴대전화번호 필수 제외
	        // 대리(생)인 경우
	        //if (CommConst.RELIEF_APLY_LIVE.equals(aplyCd) &&
	        //	CommUtils.nvlTrim(data.getSufrerMbtelNo()).isEmpty()) {
	        //	errors.rejectValue("sufrerMbtelNo", "피해자휴대전화번호는 필수입력사항입니다.");
	        //}
        }
        
        // 신청양식에 따라 업로드한 파일을 VALIDATION한다.
        
        // 신청양식목록
        List<PapeMngVO> papeList = data.getPapeList();
        // 업로드파일목록
        List<AplyFileVO> saveFiles = data.getSaveFiles();
        // 신청종류
        List<String> aplyKnds = data.getAplyKndList();
        // 의료비(RK1)       - 의료비서류그룹(D002) 필수
        // 요양생활수당(RK2) - 요양생활수당서류그룹(D003) 필수
        
        for (PapeMngVO pape : papeList) {
        	
        	pape.setFiles(new ArrayList<AplyFileVO>());
        	
    		for (AplyFileVO fileVO : saveFiles) {
    			if (CommUtils.isEqual(pape.getPapeCd(), fileVO.getPapeCd())) {
    				pape.addFiles(fileVO);
    			}
    		}
    		// 의료비가 없는 경우 
    		// 의료비 서류그룹 SKIP
    		if (aplyKnds.contains(CommConst.APLY_KND_MCP) == false &&
    			CommConst.PAPE_MCP.equals(pape.getUpPapeCd())) {
    			continue;
    		} 
    		// 요양생활수당이 없는 경우 
    		// 요양생활수당 서류그룹 SKIP
    		if (aplyKnds.contains(CommConst.APLY_KND_RCP) == false && 
    			CommConst.PAPE_RCP.equals(pape.getUpPapeCd())) {
    			continue;
    		} 
    		String title = "["+pape.getUpPapeNm()+"-"+pape.getPapeNm()+"]";
    		// 업로드 파일목록
    		List<AplyFileVO> files = pape.getFiles();
    		
    		// 2022.01.18 임시저장의 경우 필수 체크 SKIP
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
    	
        ReliefVO data = (ReliefVO) o;
        // 본인, 대리(생), 대리(사)
        String aplyCd = data.getAplySeCd();
        // 이력구분 (피해자정보수정/신청인정보수정/피해내용수정)
        String hstCd = data.getHstSeCd();
        
        // 공통필수조건
        if (CommUtils.nvlTrim(data.getHstCn()).isEmpty()) {
            errors.reject("hstCn", "수정사유는 필수입력사항입니다.");
        }
        if (CommUtils.nvlTrim(data.getAplyNo()).isEmpty()) {
            errors.reject("aplyNo", "신청번호가 입력되지 않았습니다.");
        }
        if (CommUtils.nvlTrim(data.getAplySeCd()).isEmpty()) {
            errors.reject("aplySeCd", "신청구분을 확인할 수 없습니다.");
        }

		// 피해자정보 수정시
		if      (CommConst.HST_SUFRER.equals(hstCd)) {
	        // 피해자정보 필수조건
	        if (CommUtils.nvlTrim(data.getAplyKndCd()).isEmpty()) {
	            errors.reject("aplyKndCd", "신청종류는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerNo()).isEmpty()) {
	            errors.reject("sufrerNo", "피해자번호가 존재하지 않습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerNm()).isEmpty()) {
	            errors.reject("sufrerNm", "피해자명는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerZip()).isEmpty()) {
	            errors.reject("sufrerZip", "피해자우편번호는 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSufrerAddr()).isEmpty()) {
	            errors.reject("sufrerAddr", "피해자주소는 필수입력사항입니다.");
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
		// 신청인정보 수정시
		else if (CommConst.HST_APLCNT.equals(hstCd)) {
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
		}
		// 피해내용 수정시
		else if (CommConst.HST_DAMAGE.equals(hstCd)) {
		}
    }    
}
