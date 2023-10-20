package business.bio.relief.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.bio.relief.service.BioReliefService;
import business.bio.relief.service.BioReliefVO;
import business.bio.relief.service.BioReliefValidator;
import business.com.CommConst;
import business.com.file.service.PapeMngService;
import business.sys.code.service.CodeService;
import business.sys.code.service.CodeVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 살생물제품 구제급여접수 Controller
 * 
 * @class   : BioReliefController
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 */
@Controller
@SuppressWarnings({"all"})
public class BioReliefController extends BaseController {

    @Resource(name="BioReliefService")
    protected BioReliefService reliefService;

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngSerivce;

    @Resource(name="CodeService")
    protected CodeService codeService;

    // 데이터 검증용 VALIDATOR
    @Autowired 
    private BioReliefValidator reliefValidator;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 구제급여 신청목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/bio/getListBioRelief.do")
    @ResponseBody
    public Map getListBioRelief(HttpServletRequest request
            , @ModelAttribute BioReliefVO bioReliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//
        
        if (CommUtils.isNotEmptyList(bioReliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	bioReliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(bioReliefVO.getAplyKndList(),"|")
        	);
        }
        
        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = reliefService.listBioRelief(bioReliefVO, page, size);
        }
        else {
        	list = reliefService.listBioRelief(bioReliefVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
        
    /**
     * 2021.10.18 LSH
     * 구제급여 신청정보 가져오기
     */
    @RequestMapping("/adm/bio/getBioRelief.do")
    @ResponseBody
    public Map getBioRelief(
    		HttpServletRequest request, 
            @ModelAttribute BioReliefVO bioReliefVO
    	) throws Exception {
    	
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//

        // 구제급여 신청정보 상세조회 (피해자정보 포함)
        BioReliefVO result = reliefService.viewBioReliefWidhIdntfc(bioReliefVO);
        if (result != null) {
	        // 세션사용자번호 정의
		    result.setGsUserNo(bioReliefVO.getUserInfo().getUserNo());
        }
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 2021.10.19
     * 구제급여 접수 화면
     */
    @RequestMapping("/adm/bio/listBioReliefRcpt.do")
    public String listBioReliefRcpt(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute BioReliefVO bioReliefVO) throws Exception {
				
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 서류업무구분 정의
        bioReliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_BIO);
        // 세션사용자번호 정의
        bioReliefVO.setGsUserNo(bioReliefVO.getUserInfo().getUserNo());

        model.addAttribute("model", bioReliefVO);
    	
        return "adm/bio/listBioReliefRcpt";
    }

    /**
     * 구제급여 신청접수 모달팝업 오픈
     */
    @RequestMapping("/adm/bio/modalBioReceipt.do")
    public String modalBioReceipt(HttpServletRequest request
    		, @ModelAttribute BioReliefVO bioReliefVO
    		, ModelMap model) throws Exception {

    	CodeVO codeVO = CodeVO.builder()
    			.upCdId(CommConst.CODE_SMSMSG)
    			.cdId  (CommConst.CODE_SMSMSG_BIO_RECEIPT)
    			.build();
    	
    	codeVO = codeService.viewCode(codeVO);
    	
    	String trsmCn = "";
    	if (codeVO != null && codeVO.getCdCn() != null) {
    		trsmCn = codeVO.getCdCn();
    		trsmCn += "\n\n";
    	}
    	trsmCn += "접수일자 : " + CommUtils.formatCurDate("yyyy.MM.dd");
    	
    	// 기본 접수일자
    	bioReliefVO.setRcptYmd(CommUtils.formatCurDate("yyyy.MM.dd"));
    	// 기본 발신번호
    	bioReliefVO.setTrnsterNo(CommUtils.formatPhone(CommConst.SMS_TRANSFER_NO));
    	// 기본 메세지
    	bioReliefVO.setTrsmCn(trsmCn);
    	
        model.addAttribute("form", bioReliefVO);
        
        return "adm/bio/modalBioReceipt";
    }

    /**
     * 구제급여 신청접수처리
     * 2022.12.08 접수일자 입력처리 적용
     * 2022.12.08 휴대전화번호 있을 경우에만 SMS 전송처리 적용
     */
    @RequestMapping("/adm/bio/saveBioReceipt.do")
    @ResponseBody
    public Map saveBioReceipt(HttpServletRequest request
			, @RequestBody BioReliefVO bioReliefVO
			, BindingResult bindingResult) throws Exception {
    	
        setGlobalSession(bioReliefVO);
        
        if (bioReliefVO.getUserInfo() != null)
        	bioReliefVO.setGsUserNo(bioReliefVO.getUserInfo().getUserNo());
    	
        // 신청접수를 처리한다.
    	String result = reliefService.saveBioReceipt(bioReliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 구제급여 엑셀 다운로드
     */
    @RequestMapping("/adm/bio/downBioReliefExcel.do")
    public String downBioReliefExcel(HttpServletRequest request
            , @ModelAttribute BioReliefVO bioReliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(bioReliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	bioReliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(bioReliefVO.getAplyKndList(),"|")
        	);
        }
        // 구제급여 신청접수목록
        List list = reliefService.listBioRelief(bioReliefVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "BioReliefRcpt");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "살생물제품_구제급여_접수현황");

		return "excelTempView";
    }

    /**
     * 구제급여 개인정보수정 모달팝업 오픈
     */
    @RequestMapping("/adm/bio/modalBioReliefForm.do")
    public String modalBioReliefForm(HttpServletRequest request
    		, @ModelAttribute BioReliefVO bioReliefVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(bioReliefVO);
        
        String hstCd = bioReliefVO.getHstSeCd();
        if (CommUtils.isEmpty(hstCd))
        	bioReliefVO.setHstSeCd(CommConst.HST_SUFRER);
        
        // 구제급여 신청정보 상세조회 (피해자정보 포함)
        BioReliefVO result = reliefService.viewBioRelief(bioReliefVO);
        if (result != null) {
        	bioReliefVO.setAplySeCd(result.getAplySeCd());
        }
        // 수정모드
        bioReliefVO.setMode(CommConst.UPDATE);
        
        model.addAttribute("form", bioReliefVO);
        
        return "adm/bio/modalBioReliefForm";
    }

    /**
     * 구제급여접수 - 피해자정보 수정처리
     * 구제급여접수 - 신청인정보 수정처리
     * 구제급여접수 - 피해내용 수정처리
     */
    @RequestMapping("/adm/bio/saveBioReliefModify.do")
    @ResponseBody
    public Map saveBioReliefModify(
    		HttpServletRequest request, 
    		@RequestBody BioReliefVO bioReliefVO,
    		BindingResult bindingResult
    	) throws Exception {
    	
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//
    	
    	// 저장항목에 맞게 데이터 재정의
    	bioReliefVO.rebuildModifyProperties();
        // 저장할 입력값 검증
    	reliefValidator.validateModify(bioReliefVO, bindingResult);
    	
    	// 입력값 검증시 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	logger.info("BioRelief Validate Error...", bindingResult.getAllErrors());
        	return getFailure(bindingResult);
        }
        // 저장처리
    	String result = reliefService.saveBioReliefModify(bioReliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 관리자 제출서류 추가등록 업로드
     * bioReliefVO 에 화면에서 입력되어야할 항목
     * - aplyNo   : 신청번호
     * - papeCd   : 서류코드
     * - upPapeCd : 상위서류코드
     * - hstCn    : 추가등록사유
     */
    @RequestMapping("/adm/bio/saveBioReliefAddfile.do")
    @ResponseBody
    public Map saveBioReliefAddfile(HttpServletRequest request
			, @ModelAttribute BioReliefVO bioReliefVO) throws Exception {
    	
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//
        
        if (bioReliefVO.getUserInfo() != null)
        	bioReliefVO.setGsUserNo(bioReliefVO.getUserInfo().getUserNo());

	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    
	    if (files == null || files.size() != 1) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
	    }
	    FileInfo fileInfo = files.get(0);
    	if (!"Y".equals(fileInfo.getFileYn())) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
    	}
    	String result = reliefService.saveBioReliefAddfile(bioReliefVO, fileInfo);

    	// 성공결과 반환
        return getSuccess();
    }
}
