package business.adm.exmn.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.CommFormFile;
import business.com.exmn.service.MnsvyService;
import business.com.exmn.service.MnsvyVO;
import business.com.file.service.ExmnFileVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 본조사 관리 Controller
 * 
 * @class   : MnsvyController
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class MnsvyController extends BaseController {

    @Resource(name="MnsvyService")
    protected MnsvyService mnsvyService;

    // 파일업로드용 
    @Resource(name="fileManager")
    protected FileManager fileManager;
    
    /**
     * 본조사 화면 오픈
     */
    @RequestMapping("/adm/exmn/listMnsvy.do")
    public String listMnsvy(HttpServletRequest request
	        , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", mnsvyVO);
    	
        return "adm/exmn/listMnsvy";
    }

    /**
     * 본조사 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListMnsvy.do")
    @ResponseBody
    public Map getListMnsvy(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {

        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mnsvyService.listMnsvy(mnsvyVO, page, size);
        }
        else {
        	list = mnsvyService.listMnsvy(mnsvyVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.11.30 LSH
     * 본조사 대상자목록 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downMnsvyExcel.do")
    public String downMnsvyExcel(HttpServletRequest request
            , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//
        
        // 본조사 대상자목록
        List list = mnsvyService.listMnsvy(mnsvyVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "Mnsvy");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "본조사_대상자목록");

		return "excelTempView";
    }

    /**
     * 본조사 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getMnsvy.do")
    @ResponseBody
    public Map getMnsvy(HttpServletRequest request
            , @ModelAttribute MnsvyVO mnsvyVO
			, ModelMap model) throws Exception {

        MnsvyVO obj = mnsvyService.viewMnsvy(mnsvyVO);
        return getSuccess(obj);
    }

    /**
     * 2021.11.29 본조사 저장처리 
     * 2021.12.06 지급 - 의료비 저장처리
     * 1) mode=I (등록) : 본조사계획에서 대상자 조회 등록
     * 2) mode=D (삭제) : 본조사계획에서 대상자 선택 삭제
     * 3) mode=U (수정) : 본조사관리에서 장의비/유족보상비/사망원인조사 저장
     *    - act=BRV : 본조사 - 장의비/유족보상비 저장
     *    - act=DTH : 본조사 - 사망원인조사 저장
     *    - act=MCP : 지급 - 의료비지급정보 저장
     * 2022.12.27 요양생활수당 지급처리 항목은 제외함
     */
    @RequestMapping("/adm/exmn/saveMnsvy.do")
    @ResponseBody
    public Map saveMnsvy(HttpServletRequest request
			, @RequestBody MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
    	
        // 본조사를 저장한다.
    	String result = mnsvyService.saveMnsvy(mnsvyVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.12.02
     * 본조사 요양생활수당 저장 / 첨부서류 업로드
     */
    @RequestMapping("/adm/exmn/saveMnsvyRcper.do")
    @ResponseBody
    public Map saveMnsvyRcper(HttpServletRequest request
			, @ModelAttribute MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
        
	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    List<ExmnFileVO> list = new ArrayList<ExmnFileVO> ();
    	// 임시경로에 저장된 파일 객체
	    for (FileInfo fileInfo : files) {
	    	// 업로드할 파일이 아니면 SKIP
	    	if (!"Y".equals(fileInfo.getFileYn()))
	    		continue;
	        // 파일정보를 저장할 모델객체에 맵핑
	    	list.add(ExmnFileVO.builder()
				    .fileNm    (fileInfo.getFileName   ()) // 파일명칭
				    .strgNm    (fileInfo.getSaveName   ()) // 파일저장명칭
				    .fileSz    (fileInfo.getFileSize   ()) // 파일크기
				    .bizAreaCd (mnsvyVO.getBizAreaCd())
				    .bizOder   (mnsvyVO.getBizOder  ())
				    .exmnOder  (mnsvyVO.getExmnOder ())
				    .aplyNo    (mnsvyVO.getAplyNo   ())
				    .gsUserNo  (mnsvyVO.getGsUserNo ())
				    .dtySeCd   (CommConst.DTY_EXMN_MNSVY ) // 업무구분(본조사)
				    .dtyClCd   (CommConst.CODE_EXMN_RCP  ) // 업무분류(요양생활수당)
	    			.build());
	    }
	    mnsvyVO.setFileList(list);
        // 본조사 요양생활수당을 저장한다.
    	String result = mnsvyService.saveMnsvyRcper(mnsvyVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.12.02
     * 본조사 심의회결과 저장 / 첨부서류 업로드
     */
    @RequestMapping("/adm/exmn/saveMnsvyRslt.do")
    @ResponseBody
    public Map saveMnsvyRslt(HttpServletRequest request
			, @ModelAttribute MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
        
	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    List<ExmnFileVO> list = new ArrayList<ExmnFileVO> ();
    	// 임시경로에 저장된 파일 객체
	    for (FileInfo fileInfo : files) {
	    	// 업로드할 파일이 아니면 SKIP
	    	if (!"Y".equals(fileInfo.getFileYn()))
	    		continue;
	        // 파일정보를 저장할 모델객체에 맵핑
	    	list.add(ExmnFileVO.builder()
				    .fileNm    (fileInfo.getFileName   ()) // 파일명칭
				    .strgNm    (fileInfo.getSaveName   ()) // 파일저장명칭
				    .fileSz    (fileInfo.getFileSize   ()) // 파일크기
				    .bizAreaCd (mnsvyVO.getBizAreaCd())
				    .bizOder   (mnsvyVO.getBizOder  ())
				    .exmnOder  (mnsvyVO.getExmnOder ())
				    .aplyNo    (mnsvyVO.getAplyNo   ())
				    .gsUserNo  (mnsvyVO.getGsUserNo ())
				    .dtySeCd   (CommConst.DTY_EXMN_MNSVY ) // 업무구분(본조사)
				    .dtyClCd   (CommConst.CODE_EXMN_DLT  ) // 업무분류(심의회결과)
	    			.build());
	    }
	    mnsvyVO.setFileList(list);
        // 본조사 심의결과를 저장한다.
    	String result = mnsvyService.saveMnsvyRslt(mnsvyVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 2021.12.02
     * 본조사 심의회결과 SMS 발송
     */
    @RequestMapping("/adm/exmn/sendMnsvyRsltSms.do")
    @ResponseBody
    public Map sendMnsvyRsltSms(HttpServletRequest request
			, @RequestBody MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
        
        // 본조사 심의회결과 SMS를 신청자에게 발송한다.
    	mnsvyService.sendMnsvyRsltSms(mnsvyVO);

    	// 성공결과 반환
        return getSuccess();
    }

    /**
     * 본조사계획 화면 오픈
     */
    @RequestMapping("/adm/exmn/listMnsvyPlan.do")
    public String listMnsvyPlan(HttpServletRequest request
	        , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", mnsvyVO);
    	
        return "adm/exmn/listMnsvyPlan";
    }

    /**
     * 본조사계획 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListMnsvyPlan.do")
    @ResponseBody
    public Map getListMnsvyPlan(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {

        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mnsvyService.listMnsvyPlan(mnsvyVO, page, size);
        }
        else {
        	list = mnsvyService.listMnsvyPlan(mnsvyVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 본조사계획 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getMnsvyPlan.do")
    @ResponseBody
    public Map getMnsvyPlan(HttpServletRequest request
            , @ModelAttribute MnsvyVO mnsvyVO
			, ModelMap model) throws Exception {

        MnsvyVO obj = mnsvyService.viewMnsvyPlan(mnsvyVO);
        return getSuccess(obj);
    }

    /**
     * 본조사계획 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/exmn/saveMnsvyPlan.do")
    @ResponseBody
    public Map saveMnsvyPlan(HttpServletRequest request
			, @ModelAttribute MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
    	
        // 본조사계획를 저장한다.
    	String result = mnsvyService.saveMnsvyPlan(mnsvyVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 본조사계획 NEXT 조사차수 반환
     */
    @RequestMapping("/adm/exmn/getMnsvyPlanNextOder.do")
    @ResponseBody
    public Map getPrptExmnPlanNextOder(HttpServletRequest request
            , @ModelAttribute MnsvyVO mnsvyVO
			, ModelMap model) throws Exception {

        String obj = mnsvyService.getMnsvyPlanNextOder(mnsvyVO);
        return getSuccess(obj);
    }

    /**
     * 2021.11.20
     * 본조사계획 대상자등록 팝업 오픈
     */
    @RequestMapping("/adm/exmn/modalMnsvy.do")
    public String modalMnsvy(HttpServletRequest request
    		, @RequestBody MnsvyVO mnsvyVO
    		, ModelMap model) throws Exception {

    	// 예비조사계획 상세조회
    	MnsvyVO data = mnsvyService.viewMnsvyPlan(mnsvyVO);
    	// 등록모드
    	data.setMode(CommConst.INSERT);
    	
        model.addAttribute("form", data);
        
        return "adm/exmn/modalMnsvy";
    }
}
