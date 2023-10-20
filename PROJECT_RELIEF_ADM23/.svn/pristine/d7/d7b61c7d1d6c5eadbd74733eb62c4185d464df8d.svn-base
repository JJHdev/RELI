package business.com.ocr.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clipsoft.lowagie.text.pdf.PdfReader;

import business.com.CommConst;
import business.com.file.service.AplyFileService;
import business.com.file.service.AplyFileVO;
import business.com.ocr.service.OcrVO;
import business.com.ocr.service.impl.OcrEntityObject;
import commf.exception.BusinessException;
import commf.ocr.OcrAPI;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * OCR 분석 및 결과 반환을 위한 컨트롤러
 * 
 * - 2023.02.06 LSH 재작업
 * 
 * @class   : OcrRestController
 * @author  : LSH
 * @since   : 2023.02.06
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class OcrRestController extends BaseController {
	
    protected static Log log = LogFactory.getLog(OcrRestController.class);
    
    @Autowired
    protected AplyFileService aplyFileService;
	
    @Resource(name="fileManager")
    FileManager fileManager;
    
    /**
     * 2023.02.06 LSH
     * OCR 분석전 확인정보 반환
     * - 대상 파일명
     * - 대상 파일 확장자명
     * - 전체 페이지수
     * - 최대 페이지수
     * - 대상 파일 존재여부
     * - 페이지 설정 가능여부
     * - 타입 설정 가능 여부
     * - 대상 파일 서류코드
     */
    @RequestMapping("/adm/ocr/checkOCR.do")
    @ResponseBody
    public Map checkOCR(HttpServletRequest request
            , @ModelAttribute OcrVO ocrVO) throws Exception {

    	FileInfo fileInfo = null;
    	try {
            // 파일정보조회
            AplyFileVO fileVO = aplyFileService.viewAplySubmitFile(ocrVO.getSn());
            // 파일확장자
            String fileExt    = FileUtils.getFileExt(fileVO.getFileNm());
            // 실제경로
            String fullPath   = fileVO.getRealFilePath(FileDirectory.PAPER);
            // 실제파일
            String fullName   = FileUtils.mergePath(fullPath, fileVO.getStrgNm());
            // 최대 페이지수
            int maxPages      = CommConst.OCR_API_MAXPAGES;
            // 전체 페이지수
            int allPages      = 0;
            
            ocrVO.setExistYn ("N");
            ocrVO.setPageYn  ("N");
            ocrVO.setTypeYn  ("N");
            ocrVO.setPapeCd  (fileVO.getPapeCd());
            ocrVO.setFileName(fileVO.getFileNm());
            ocrVO.setFileExt (fileExt);

            // 파일이 실제 있을 경우
            if (FileUtils.existFile(fullName)) {
	            // PDF인 경우
	            if ("pdf".equalsIgnoreCase(fileExt)) {
	                // 파일복호화를 위한 객체 생성
	            	fileInfo = FileInfo.builder()
	        					.saveName(fileVO.getStrgNm())
	        					.fileName(fileVO.getFileNm())
	        					.fullFile(fullName)
	        					.decrypt (true)
	        					.build();
	
	            	// OCR 분석할 파일 복호화 처리 (fullFile 변경)
	            	fileManager.decryptDownload(fileInfo);
	                // 전체 페이지수 설정
	            	allPages = new PdfReader(fileInfo.getFullFile()).getNumberOfPages();
	            	// 최대 페이지수가 0인 경우 제한없이 전체 가능
	            	if (maxPages == 0)
	            		maxPages = allPages;
	            }
	            // 파일 존재여부
	            ocrVO.setExistYn ("Y");
	            ocrVO.setMaxPages(maxPages);
	            ocrVO.setAllPages(allPages);
	            // OCR 페이지 설정여부
	            if (allPages > 1)
	            	ocrVO.setPageYn("Y");
	            // OCR 파일형식 선택여부
	            if (CommConst.MEDIC_PAPE_CD.equals(ocrVO.getPapeCd()))
	            	ocrVO.setTypeYn("Y");
            }
    	}
    	catch(IOException e) {
    		log.error(e);
    	}
    	finally {
    		// 삭제 대상이면 복호화된 파일 삭제 처리
    		if (fileInfo != null &&
    			fileInfo.isDeleted()) {
    		    // 해당 파일 삭제
                FileUtils.deleteFile(fileInfo.getFullFile());
    		}
    	}
        return getSuccess(ocrVO);
    }

    /**
     * 2023.01.31 LSH 수정
     * 
     * PDF 문서의 OCR 분석 
     * 
     * 1. 대상파일 조회
     * 2. 대상파일 확장자 확인
     * 3. 대상파일 암호화된 파일 -> 복호화 
     * 4. 파일 구분에 따라 페이지 수 확인
     * 5. Text 파일생성 또는 Excel 파일 생성
     * 6. OCR 분석
     * 7. 생성된 OCR파일 서버 삭제
     * 8. 복호화된 파일 서버 삭제
     */
    @RequestMapping(value = "/adm/ocr/analyzeOCR.do", method=RequestMethod.GET)
    public void analyzeOCR(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute OcrVO ocrVO) throws Exception {

    	FileInfo fileInfo = null;
    	
    	try {
            // 파일정보조회
            AplyFileVO fileVO = aplyFileService.viewAplySubmitFile(ocrVO.getSn());
            // 실제경로
            String fullPath = fileVO.getRealFilePath(FileDirectory.PAPER);
            // 파일복호화를 위한 객체 생성
        	fileInfo = FileInfo.builder()
    					.saveName(fileVO.getStrgNm())
    					.fileName(fileVO.getFileNm())
    					.fullFile(FileUtils.mergePath(fullPath, fileVO.getStrgNm() ))
    					.decrypt (true)
    					.build();

        	// OCR 분석할 파일 복호화 처리 (fullFile 변경)
        	fileManager.decryptDownload(fileInfo);
        	
        	ocrVO.setPapeCd  (fileVO.getPapeCd());
        	ocrVO.setSaveName(fileInfo.getFullFile());
        	ocrVO.setFileName(fileInfo.getFileName());
        	// 엑셀타입일 경우 원본(TEXT) 포함여부
        	ocrVO.setOriginalYn(CommConst.OCR_INCLUDE_ORIGINAL);
        	if (CommUtils.isEmpty(ocrVO.getOcrType()))
        		ocrVO.setOcrType(CommConst.OCR_EXT_TXT);
        	if (CommUtils.isEmpty(ocrVO.getPageYn()))
        		ocrVO.setPageYn(CommConst.NO);
            
            log.info("OCR ANALYZE ORIGNIAL FILE NAME : "+ocrVO.getSaveName());

            // OCR 분석다운로드 실행
        	OcrAPI api = new OcrAPI( ocrVO.toOcrEntity() );
        	api.download(request, response);
    	}
    	catch(BusinessException e) {
    		log.error("OCR 분석 오류 : ", e);
    		request.setAttribute("errorMessage", e.getMessage());
    	    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    	}
    	catch(Exception e) {
    		log.error("OCR 분석 오류 : ", e);
    		request.setAttribute("errorMessage", "OCR 분석 중 오류가 발생하였습니다.");
    		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    	}
    	finally {
    		// 삭제 대상이면 복호화된 파일 삭제 처리
    		if (fileInfo != null &&
    			fileInfo.isDeleted()) {
    		    // 해당 파일 삭제
                FileUtils.deleteFile(fileInfo.getFullFile());
    		}
    	}
    }

    /**
     * 2023.02.21 LSH
     * OCR 분석수행전 엔진상태체크
     */
    @RequestMapping("/adm/ocr/statusOCR.do")
    @ResponseBody
    public Map statusOCR() throws Exception {
    	try {
            // OCR 엔진상태조회 API 실행
        	OcrAPI api = new OcrAPI( new OcrEntityObject() );
            // 엔진상태조회 REST API 호출
    		JSONObject data = api.openRestAPI(api.getEntity().getStatusUrl());
            if (data != null) {
    	        JSONObject result = (JSONObject)data.get("result");
    	        if (result != null) {
    	        	log.debug(
    	        		"OCR REST API (Engine Status Check) "+
    	        		"[Engine = "  + result.get("engine" )+
    	        		",Version = " + result.get("version")+
    	        		",Running = " + result.get("running")+
    	        		",Pending = " + result.get("pending")+
    	        		",Expired = " + result.get("expired")+"]"
    	        	);
    		        // 만료가 아닌 경우 라이센스 유효함 
    		        if ("False".equalsIgnoreCase((String) result.get("expired")))
    		        	return getSuccess("OK");
    		        else
    		        	return getFailure("OCR 서버엔진 라이센스 유효기간이 만료되었습니다.");
    	        }
            }
    		return getFailure("OCR 서버엔진 상태를 확인할 수 없습니다");
    	}
    	catch(Exception e) {
    		return getFailure("OCR 서버 통신상의 오류가 발생하였습니다.");
    	}
    }
}

/* =============================================================================
 *  2023.02.06 OCR 재작업 이전 소스 백업
 * -----------------------------------------------------------------------------
    public static final String apiKey			        = ApplicationProperty.get("OCR.API.KEY");			// OCR API Key
    public static final String serverAddress	   = ApplicationProperty.get("OCR.SERVER.ADDRESS");	// OCR Server Address
    
    @RequestMapping("/ocr/getOcrAnalysis.do")
    public void exportOCR(HttpServletRequest request, HttpServletResponse response, 
            @RequestParam String sn) throws Exception {
        // 파일조회
        FileInfo fileInfo   = getFilePath(sn);
        
        // 기본정보
        OCRResult result    = null;
        String exts			= ".txt";
        FileDirectory d 	= FileDirectory.OCR;
        
        // 파일 생성
        String ocrSaveName 	= "ocr_"+fileManager.getFileName(d.getRealPath(d.getPath()), fileInfo.getFileName() + exts);
        String ocrFullPath 	= d.getRealPath(d.getPath() + File.separator + ocrSaveName);
        //FileUtils.createNewFile(ocrFullPath, result.getFullText());
        
        log.info("### fileInfo.getFullPath() : "+fileInfo.getFullPath());
        
        String dotext   = "";
        int pageCnt     = 1;
        
        log.info("### getFileName : "+fileInfo.getFileName());
        
        // 확장자
        if (!CommUtils.nvlTrim((String)fileInfo.getFileName()).equals("")) {
            dotext = fileInfo.getFileName().substring(fileInfo.getFileName().lastIndexOf(".")+1);
        }
        
        log.info("### dotext : "+dotext);
        
        // PDF인경우 전체 페이지 수 확인
        if (dotext.equalsIgnoreCase("pdf")) {
            // 4. OCR 분석 (TO-DO 테스트로 3페이지까지만.. 이후 PDF 전체페이지 구해서 사용)
            PdfReader reader = new PdfReader(fileInfo.getFullPath());
            pageCnt = reader.getNumberOfPages();
        }
        
        log.info("### pageCnt : "+pageCnt);
        
        // 임시 테스트 페이지
        if (pageCnt > 3)    pageCnt = 2;
        
        // OCR 분석
        for(int i = 0; i < pageCnt; i++) {
            log.info("### i : "+i);
            result = analysisOcr(fileInfo.getFullPath(), i);
            // 파일 작성
            FileUtils.writeFile(ocrFullPath, result.getFullText());
        }
        
        // 다운로드 파일 정보
        fileInfo = FileInfo.builder()
                    .decrypt(false)
                    .fullPath(ocrFullPath)
                    .fileName(fileInfo.getFileName()+"_ocr.txt")
                    .build();
        
        log.info("result.getFormCsvFile() : "+result.getFormCsvFile());
        log.info("OCR PATH : "+ocrFullPath);
        
        fileManager.procFileDownload(request, response, fileInfo);
    }
	
    //@PostMapping("/ocr/getOcrAnalysis.do")
    //public void exportOCR(@RequestParam String sn, HttpServletRequest request, HttpServletResponse response) throws Exception {
    //	// 1. 파일  상세조회
    //	FileInfo fileInfo = getFilePath(sn);
    //	FileDirectory d = FileDirectory.OCR;
    //	String fileName = "ocr_" + fileInfo.getSaveName() + ".txt";
    //	String txtOcrPath = d.getRealPath(d.getPath() + File.separator + fileName);
    //	
    //	// 2. 파일 삭제
    //	FileUtils.deleteFile(txtOcrPath);
    //	
    //	// 3. 파일 분석(PDF인 경우 페이지수 조회)
    //	int count = 1;
    //	File file = new File(fileInfo.getFullPath());
    //	String contentType = Files.probeContentType(file.toPath());
    //	
    //	if(contentType.equals("application/pdf")) {
    //		PDDocument doc = PDDocument.load(file);
    //		count = doc.getNumberOfPages();
    //	} else {
    //		//image/png : png파일
    //		//application/haansofthwp : 한글(인식못함)
    //	}
    //	
    //	// 4. OCR 분석
    //	for(int i=0; i<count; i++) {
    //		// ocr 분석
    //		OCRResult result = analysisOcr(fileInfo.getFullPath(), i);
    //		// 파일 작성
    //		FileUtils.writeFile(txtOcrPath, result.getFullText());
    //	}
    //	
    //	// 5. return
    //	file = new File(txtOcrPath);
    //	response.setContentType("application/octet-stream");
    //	response.setContentLength((int)file.length());
    //	response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
    //	response.setHeader("Content-Transfer-Encoding", "binary");
    //	OutputStream out = response.getOutputStream();
    //	
    //	//파일 카피 후 마무리
    //	try (FileInputStream fis = new FileInputStream(file)){
    //		FileUtils.copyFile(fis, out);
    //	} catch(Exception e){
    //		e.printStackTrace();
    //	}
    //	out.flush();
    //}
    
    // 파일 상세조회
    private FileInfo getFilePath(String sn) throws Exception {
        // 파일정보조회
        AplyFileVO fileVO = aplyFileService.viewAplySubmitFile(sn);
        
        FileInfo fileInfo = FileInfo.builder()
                        		.saveName(fileVO.getStrgNm())
                        		.fileName(fileVO.getFileNm())
                        		.build();
        
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;
        
        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo())) {
            fileInfo.setFullPath(d.getTempDir());
        } else {
            fileInfo.setFullPath(d.getRealPath(fileVO.getFilePath()));
        }
        
        fileInfo.setFullPath(fileInfo.getFullPath() + File.separator + fileInfo.getSaveName());
        
        return fileInfo;
    }
	
    // SynapOCR 분석
    public OCRResult analysisOcr(String filePath, int pageIndex) {
        String imagePath = filePath;
        //String imagePath = "D:/junk/221026001.jpg";
        boolean upload = true;
        String boxesType = "";
        String fileId = "";
        boolean formRecognition = true;
        boolean bCopy = false;
        boolean bTextOut = true;
        boolean bSkew = true;
        boolean bCrop = true;
        // int pageIndex = 0;
        String regExp = "";
        String langs = "all";
        String coord = "origin";
        String maskType = "";
        String outputFormat = "";
        String maskedImagePath = "";
        String finalImagePath = "";
        String formCsvPath = "";
        ROIBox roiBox = null;
        boolean bRemove = false;
        String skewMode = "";
        ROIBox[] basicBoxes = null;
        ROIBox[] advancedBoxes = null;
        String formIdList = "";
        boolean bExtractTable = false;
        String tableOutputPath = "";
        String formTypeList = "";
        // boolean debugMode = false;
        boolean bShowStatus = false;
        
        // !!! DO OCR !!!
        OCRResult result = null;
        OCREngineStatus status = null;
        try (OCREngine engine = Engine.createOCREngine(apiKey, serverAddress)) {
            log.debug("analysisOcr : " + pageIndex);
            //System.out.print("Engine Status : " + Engine.getStatus(engine));
            // engine.setDebugMode(debugMode);
            if (bShowStatus) {
            	    status = Engine.getStatus(engine);
            } else if (basicBoxes != null || advancedBoxes != null) {
            	    result = Engine.doRecognize(imagePath, upload, fileId, pageIndex, langs, bRemove, basicBoxes, advancedBoxes, engine);
            } else if (bRemove && fileId != null && fileId.length() > 0) {
            	    Engine.deleteTemporaryFiles(fileId, engine);
            	    //System.out.print("Synap OCR Engine -> deleteTemporaryFiles : 200");
            } else {
            	    result = Engine.doOCR(imagePath, upload, boxesType, formRecognition, fileId, pageIndex, bCopy, bSkew, bCrop, bTextOut, regExp, langs, coord, maskType, outputFormat, maskedImagePath, finalImagePath, formCsvPath, roiBox, bRemove, skewMode, formIdList, bExtractTable, tableOutputPath, formTypeList, engine);
            }
        } catch (IOException e) {
        	    log.debug("Synap OCR Engine Stop Fail!!");
        	    //System.out.println("Synap OCR Engine Stop Fail!!");
        } catch(OCRException e) {
            if (bShowStatus) {
            	    log.debug("Synap OCR Engine -> getStatus Error : " + e.getCode());
            	    //System.out.println("Synap OCR Engine -> getStatus Error : " + e.getCode());
            } else if (bRemove && fileId != null && fileId.length() > 0) {
            	    log.debug("Synap OCR Engine -> deleteTemporaryFiles : " + e.getCode());
            	    //System.out.println("Synap OCR Engine -> deleteTemporaryFiles : " + e.getCode());
            } else {
            	    log.debug("Synap OCR Engine -> doOCR Error : " + e.getCode());
            	    //System.out.println("Synap OCR Engine -> doOCR Error : " + e.getCode());
            }
        }
        if(status != null) {
            log.debug(status);
            //System.out.print(status);
        }
        //if(result != null){
        //    log.debug(result);
        //	//System.out.print(result);
        
        return result;
    }
* ========================================================================== */
