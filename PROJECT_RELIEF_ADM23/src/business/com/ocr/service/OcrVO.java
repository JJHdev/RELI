package business.com.ocr.service;

import java.util.HashMap;
import java.util.Map;

import business.com.ocr.service.impl.OcrEntityObject;
import commf.ocr.OcrEntity;
import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - OCR 분석시 사용되는 전달용 모델 클래스 VO
 *
 * @class   : OcrVO
 * @author  : LSH
 * @since   : 2023.02.06
 * @version : 2.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class OcrVO extends BaseModel {

	// 파일번호
	private String sn;
	// OCR분석결과 파일형식 (TXT/XLS)
	private String ocrType;
	// 페이지범위 적용여부 (Y/N)
	private String pageYn;
	// OCR 파일형식 선택여부 (Y/N)
	private String typeYn;
	// 대상 파일 존재여부 (Y/N)
	private String existYn;
	// 엑셀 형식의 텍스트원본 포함여부 (Y/N)
	private String originalYn;
	// 대상 파일 서류코드
	private String papeCd;
	// 최대 페이지수
	private int maxPages;
	// 전체 페이지수
	private int allPages;
	// 시작 페이지
	private int stPage;
	// 종료 페이지
	private int enPage;
	// 대상 파일 확장자
	private String fileExt;
	// 대상 파일명
	private String saveName;
	// 실제 파일명
	private String fileName;
	
	/**
	 * OCR 분석을 위한 데이터 및 설정정보 Entity를 반환한다.
	 * 
	 * @return OcrEntity
	 */
	public OcrEntity toOcrEntity() {

		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("saveName"   , getSaveName  ()); // 저장된 물리적 파일명칭(경로포함)
    	map.put("fileName"   , getFileName  ()); // 실제 파일명
    	map.put("downType"   , getOcrType   ()); // 다운로드 타입 (XLS / TXT)
    	map.put("originalYn" , getOriginalYn()); // 엑셀 형식의 텍스트원본 포함 여부 (Y/N)
    	map.put("pagingYn"   , getPageYn    ()); // 페이징 처리 여부 (Y/N)
    	map.put("stPage"     , getStPage    ()); // 입력된 시작 페이지
    	map.put("enPage"     , getEnPage    ()); // 입력된 종료 페이지
    	
        return new OcrEntityObject(map, OcrFactory.get(getPapeCd()));
	}
	
}
