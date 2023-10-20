package business.com.ocr.service.impl;

import java.io.IOException;
import java.util.Map;

import business.com.CommConst;
import commf.exception.BusinessException;
import commf.ocr.OcrEntity;
import commf.ocr.OcrExcel;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * OCR 분석을 위한 데이터 및 설정정보
 * - OCR 분석에 필요한 설정정보를 담고 있다.
 *   : API 인증키, 서버주소, 캐릭터셋 등의 CommConst에 정의된 상수
 * - OCR 분석전 입력받은 설정정보를 담고 있다.
 *   : 저장파일명, 실제파일명, 페이징여부, 페이지번호 등의 설정 정보
 * 
 * @class   : OcrEntityObject
 * @author  : LSH
 * @since   : 2023.02.14
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 */
public class OcrEntityObject implements OcrEntity {
	
	// 다운로드타입
	private String downType;
    // 파일전체명칭 (경로포함)
    private String saveName; 
    // 실제파일명
    private String fileName; 
    // 페이징여부
    private String pagingYn;
	// 엑셀 형식의 텍스트원본 포함여부 (Y/N)
	private String originalYn;
    // 입력받은 시작 페이지
    private int stPageArgs;
    // 입력받은 종료 페이지
    private int enPageArgs;
	// 엑셀 양식
	private OcrExcel ocrExcel;
	
    // [내부정의] 전체 페이지수
    private int allPages;
    // [내부정의] 시작 페이지
    private int stPage;
    // [내부정의] 종료 페이지
    private int enPage;
	// [내부정의] 다운로드명칭
	private String downName;
	
	public OcrEntityObject() {
	}
	public OcrEntityObject(Map<String,Object> args, OcrExcel ocrExcel) {
		init(args, ocrExcel);
	}

    /**
     * OCR REST API를 위한 데이터 설정
     * 
     * @param ocrArgs.saveName   String  저장된 물리적 파일명칭(경로포함)
     * @param ocrArgs.fileName   String  실제 파일명
     * @param ocrArgs.downType   String  다운로드 타입 (XLS / TXT)
     * @param ocrArgs.pagingYn   String  페이징 처리 여부 (Y/N)
     * @param ocrArgs.originalYn String  엑셀형식의 텍스트원본 포함 여부 (Y/N)
     * @param ocrArgs.stPage     Integer 입력된 시작 페이지
     * @param ocrArgs.enPage     Integer 입력된 종료 페이지
     */
	@Override
	public void init(Map<String,Object> args, OcrExcel ocrExcel) {
		
    	if (args == null)
    		throw new BusinessException("OCR REST API 실행을 위한 설정정보를 확인할 수 없습니다.");
        
    	if (args.get("saveName") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 저장 파일명이 정의되지 않았습니다.");
    	if (args.get("fileName") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 실제 파일명이 정의되지 않았습니다.");
    	if (args.get("downType") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 다운로드 타입이 정의되지 않았습니다.");
    	if (args.get("pagingYn") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 페이징 처리 여부가 정의되지 않았습니다.");
    	if ("XLS".equals((String)args.get("downType")) && args.get("originalYn") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 엑셀 형식의 원본 포함여부가 정의되지 않았습니다.");

    	if ("Y".equals((String)args.get("pagingYn")) && args.get("stPage") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 시작 페이지 번호가 정의되지 않았습니다.");
    	if ("Y".equals((String)args.get("pagingYn")) && args.get("enPage") == null)
    		throw new BusinessException("OCR REST API 실행을 위한 종료 페이지 번호가 정의되지 않았습니다.");

    	this.saveName   = (String)args.get("saveName");
    	this.fileName   = (String)args.get("fileName");
    	this.downType   = (String)args.get("downType");
    	this.pagingYn   = (String)args.get("pagingYn");
    	this.originalYn = (String)args.get("originalYn");
    	this.stPageArgs = (Integer)args.get("stPage");
    	this.enPageArgs = (Integer)args.get("enPage");
		this.ocrExcel   = ocrExcel;
		
    	if ("XLS".equals(getDownType()) && getOcrExcel() == null)
    		throw new BusinessException("OCR REST API 실행을 위한 엑셀 양식(OcrExcel)을 확인할 수 없습니다.");
		
    	// 파일확장자
		String ext  = FileUtils.getFileExt(getFileName());
		// 파일다운로드명
		String down = FileUtils.getFileRemoveExt(getFileName()) + "_OCR";

		int all = 1;  // 전체페이지수
		int stp = 1;  // 시작페이지
		int enp = 1;  // 종료페이지
    	
        // PDF인 경우
        if ("pdf".equalsIgnoreCase(ext)) {
        	try {
                // 전체 페이지수 설정
        		all = FileUtils.getPDFPages(getSaveName());
			} catch (IOException e) {
			}
        	// 페이지범위가 있는 경우
        	if ("Y".equals(getPagingYn())) {
        		stp = stPageArgs;
        		enp = enPageArgs;
        		
        		// 전체범위가 아니면
        		if (all != (enp - stp + 1))
        			down += "_("+stp+"P_"+enp+"P)";
        	}
        	// 페이지범위가 없는 경우
        	else {
            	// 최대 페이지수 제한
            	if (getMaxPages() > 0 &&
            		getAllPages() > getMaxPages())
            		all = getMaxPages();
            	stp = 1;
            	enp = all;
        	}
        }
    	// 파일명에 (날짜+시간) 붙이기
        down += "_" + CommUtils.getCurrDateTime2();
        // OCR결과 파일형식을 엑셀로 선택시
        if ("XLS".equals(getDownType())) {
        	down += ".xls";
        }
        // OCR결과 파일형식을 텍스트로 선택시
        else {
        	down += ".txt";
        }
    	this.allPages  = all;
    	this.stPage    = stp;
    	this.enPage    = enp;
    	this.downName  = down;
	}

	@Override
	public OcrExcel getOcrExcel() {
		return ocrExcel;
	}

	@Override
	public int getAllPages() {
		return allPages;
	}
	@Override
	public int getStartPage() {
		return stPage;
	}
	@Override
	public int getEndPage() {
		return enPage;
	}

	@Override
	public String getDownType() {
		return downType;
	}

	@Override
	public String getDownName() {
		return downName;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public String getSaveName() {
		return saveName;
	}

	@Override
	public String getPagingYn() {
		return pagingYn;
	}

	@Override
	public String getOriginalYn() {
		return originalYn;
	}
	
	@Override
	public int getMaxPages() {
		return CommConst.OCR_API_MAXPAGES;
	}

	@Override
	public String getApiKey() {
		return CommConst.OCR_API_KEY;
	}

	@Override
	public String getAddress() {
		return CommConst.OCR_API_ADDRESS;
	}

	@Override
	public String getCharset() {
		return CommConst.OCR_API_CHARSET;
	}

	@Override
	public String getTemplatePath() {
		return CommConst.EXCEL_TEMPLATE_PATH;
	}
	@Override
	public String getAnalyzeUrl() {
		return CommConst.OCR_API_URL;
	}

	@Override
	public String getDeleteUrl() {
		return CommConst.OCR_API_DELETE_URL;
	}

	@Override
	public String getStatusUrl() {
		return CommConst.OCR_API_STATUS_URL;
	}
}
