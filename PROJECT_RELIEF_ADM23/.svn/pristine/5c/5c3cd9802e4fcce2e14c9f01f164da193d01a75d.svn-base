package business.com.ocr.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.com.CommConst;
import commf.ocr.OcrExcel;
import commf.ocr.OcrExcelType;

/**
 * OCR 분석결과 엑셀 생성을 위한 폼 타입
 * 
 * - 현재의 요양급여내역서를 기준으로 분석한 4가지 폼타입 정의
 * 
 * @class   : OcrExcelRCP
 * @author  : LSH
 * @since   : 2023.02.06
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 */
public class OcrExcelRCP implements OcrExcel {
	
	private static Log log = LogFactory.getLog(OcrExcelRCP.class);

	@Override
	public Log getLog() {
		return log;
	}

	@Override
	public String getCode() {
		// 해당 양식의 서류코드
		return CommConst.MEDIC_PAPE_CD;
	}

	@Override
	public String getTemplate() {
		// 해당 양식의 엑셀 템플릿 파일명
		return "OcrRCP_Template.xlsx";
	}
	
	@Override
	public OcrExcelType getExcelType(String content) {
		// 해당 양식 타입 반환
		return OcrExcelRCPType.get(content);
	}
}
