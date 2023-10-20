package business.com.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import business.com.CommConst;
import business.com.file.service.AplyFileVO;
import common.util.CommUtils;

/**
 * [검증클래스] - 구제급여신청 Server Side의 데이터 검증을 위한 Validator
 *
 * @class   : SplemntValidator
 * @author  : LSH
 * @since   : 2021.10.26
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
public class SplemntValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return SplemntVO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	
    	SplemntVO data = (SplemntVO) o;
        // 등록,수정
        String mode = data.getMode();
        
        // 수정인 경우
        if (CommConst.UPDATE.equals(mode)) {
	        if (CommUtils.nvlTrim(data.getSn()).isEmpty()) {
	            errors.reject("sn", "보완요청번호가 입력되지 않았습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrcsStusCd()).isEmpty()) {
	            errors.reject("prcsStusCd", "처리상태를 확인할 수 없습니다.");
	        }
        }
        // 등록인 경우
        else if (CommConst.INSERT.equals(mode)) {
	        if (CommUtils.nvlTrim(data.getAplyNo()).isEmpty()) {
	            errors.reject("aplyNo", "신청번호가 입력되지 않았습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSplemntDmndSeCd()).isEmpty()) {
	            errors.reject("splemntDmndSeCd", "업무구분이 존재하지 않습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getPrcsStusCd()).isEmpty()) {
	            errors.reject("prcsStusCd", "처리상태를 확인할 수 없습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSplemntDmndYmd()).isEmpty()) {
	            errors.reject("splemntDmndYmd", "보완요청일을 확인할 수 없습니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSplemntTermYmd()).isEmpty()) {
	            errors.reject("splemntTermYmd", "보완기한은 필수입력사항입니다.");
	        }
	        if (CommUtils.nvlTrim(data.getSplemntDmndCn()).isEmpty()) {
	            errors.reject("splemntDmndCn", "보완요청내용은 필수입력사항입니다.");
	        }
	        // 신청서류목록
	        List<AplyFileVO> fileList = data.getFileList();
	        if (fileList == null || 
	        	fileList.size() == 0) {
	            errors.reject("fileList", "보완요청할 제출서류목록을 확인할 수 없습니다.");
	        }
        }
        // 삭제인 경우
        else if (CommConst.INSERT.equals(mode)) {
	        if (CommUtils.nvlTrim(data.getSn()).isEmpty()) {
	            errors.reject("sn", "보완요청번호가 입력되지 않았습니다.");
	        }
        }
    }
}
