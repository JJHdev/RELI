package business.com.ocr.service;

import business.com.CommConst;
import business.com.ocr.service.impl.OcrExcelRCP;
import commf.ocr.OcrExcel;

public class OcrFactory {
	
    /**
     * 파일 서류코드에 해당하는 양식 객체를 생성하여 반환한다.
     * 
     * 만약 양식이 새로 추가되어야 하는 경우
     * OcrExcel을 implement하는 papeCd 에 맵핑되는 클래스를 생성 후
     * if 구문에 추가된 서류코드에 해당하는 구현체 클래스를 반환하도록
     * 구문을 추가해야 한다.
     * 
     * @param  papeCd  파일서류코드
     * @return 양식 객체 (OcrExcel의 구현체)
     */
	public static OcrExcel get(String papeCd) {
		if (papeCd == null)
			return null;
		if (CommConst.MEDIC_PAPE_CD.equals(papeCd))
			return new OcrExcelRCP();
		// 만약 양식이 하나더 추가될 경우 
		// 이 부분에 IF문으로 항목을 추가한다.
		return null;
	}
}
