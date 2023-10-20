package business.adm.exmn.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.exmn.service.LbdyNdxService;
import business.com.exmn.service.LbdyNdxVO;
import business.com.exmn.service.PrptExmnService;
import business.com.exmn.service.PrptExmnVO;
import business.com.exmn.service.ResiHstService;
import business.com.exmn.service.ResiHstVO;
import business.com.file.service.ExmnFileVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 예비조사 관리 Controller
 * 
 * @class   : PrptExmnController
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
public class PrptExmnController extends BaseController {

    @Resource(name="PrptExmnService")
    protected PrptExmnService prptExmnService;
    
    @Resource(name="LbdyNdxService")
    protected LbdyNdxService lbdyNdxService;
    
    @Resource(name="ResiHstService")
    protected ResiHstService resiHstService;

    // 파일업로드용 
    @Resource(name="fileManager")
    protected FileManager fileManager;

    /**
     * 예비조사 화면 오픈
     */
    @RequestMapping("/adm/exmn/listPrptExmn.do")
    public String listPrptExmn(HttpServletRequest request
	        , @ModelAttribute PrptExmnVO prptExmnVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(prptExmnVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", prptExmnVO);
    	
        return "adm/exmn/listPrptExmn";
    }

    /**
     * 예비조사 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListPrptExmn.do")
    @ResponseBody
    public Map getListPrptExmn(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute PrptExmnVO prptExmnVO
            , ModelMap model) throws Exception {

        setGlobalSession(prptExmnVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = prptExmnService.listPrptExmn(prptExmnVO, page, size);
        }
        else {
        	list = prptExmnService.listPrptExmn(prptExmnVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.11.26 LSH
     * 예비조사 대상자목록 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downPrptExmnExcel.do")
    public String downReliefExcel(HttpServletRequest request
            , @ModelAttribute PrptExmnVO prptExmnVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(prptExmnVO);
        // -------------------- Default Setting End -----------------------//
        
        // 예비조사 대상자목록
        List list = prptExmnService.listPrptExmn(prptExmnVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "PrptExmn");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "예비조사_대상자목록");

		return "excelTempView";
    }
    
    /**
     * 예비조사 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getPrptExmn.do")
    @ResponseBody
    public Map getPrptExmn(HttpServletRequest request
            , @ModelAttribute PrptExmnVO prptExmnVO
			, ModelMap model) throws Exception {

        PrptExmnVO obj = prptExmnService.viewPrptExmn(prptExmnVO);
        return getSuccess(obj);
    }

    /**
     * 2021.11.24
     * 예비조사 저장처리 
     * 1) mode=I (등록) : 예비조사계획에서 대상자 조회 등록
     * 2) mode=D (삭제) : 예비조사계획에서 대상자 선택 삭제
     * 3) mode=U (수정) : 예비조사관리에서 예비조사정보 저장
     *    - 거주이력 (resiList) / 생체지표 (lbdyList) 포함
     */
    @RequestMapping("/adm/exmn/savePrptExmn.do")
    @ResponseBody
    public Map savePrptExmn(HttpServletRequest request
			, @RequestBody PrptExmnVO prptExmnVO) throws Exception {
    	
        setGlobalSession(prptExmnVO);
        
        if (prptExmnVO.getUserInfo() != null)
        	prptExmnVO.setGsUserNo(prptExmnVO.getUserInfo().getUserNo());
    	
        // 예비조사를 저장한다.
    	String result = prptExmnService.savePrptExmn(prptExmnVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.11.24
     * 예비조사 심의회결과 저장 / 첨부서류 업로드
     */
    @RequestMapping("/adm/exmn/savePrptExmnRslt.do")
    @ResponseBody
    public Map savePrptExmnRslt(HttpServletRequest request
			, @ModelAttribute PrptExmnVO prptExmnVO) throws Exception {
    	
        setGlobalSession(prptExmnVO);
        
        if (prptExmnVO.getUserInfo() != null)
        	prptExmnVO.setGsUserNo(prptExmnVO.getUserInfo().getUserNo());
        
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
				    .bizAreaCd (prptExmnVO.getBizAreaCd())
				    .bizOder   (prptExmnVO.getBizOder  ())
				    .exmnOder  (prptExmnVO.getExmnOder ())
				    .aplyNo    (prptExmnVO.getAplyNo   ())
				    .gsUserNo  (prptExmnVO.getGsUserNo ())
				    .dtySeCd   (CommConst.DTY_EXMN_PRPT  ) // 업무구분(예비조사)
				    .dtyClCd   (CommConst.CODE_EXMN_DLT  ) // 업무분류(심의회결과)
	    			.build());
	    }
	    prptExmnVO.setFileList(list);
        // 예비조사 심의결과를 저장한다.
    	String result = prptExmnService.savePrptExmnRslt(prptExmnVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 예비조사계획 화면 오픈
     */
    @RequestMapping("/adm/exmn/listPrptExmnPlan.do")
    public String listPrptExmnPlan(HttpServletRequest request
	        , @ModelAttribute PrptExmnVO prptExmnVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(prptExmnVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", prptExmnVO);
    	
        return "adm/exmn/listPrptExmnPlan";
    }

    /**
     * 예비조사계획 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListPrptExmnPlan.do")
    @ResponseBody
    public Map getListPrptExmnPlan(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute PrptExmnVO prptExmnVO
            , ModelMap model) throws Exception {

        setGlobalSession(prptExmnVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = prptExmnService.listPrptExmnPlan(prptExmnVO, page, size);
        }
        else {
        	list = prptExmnService.listPrptExmnPlan(prptExmnVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 예비조사계획 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getPrptExmnPlan.do")
    @ResponseBody
    public Map getPrptExmnPlan(HttpServletRequest request
            , @ModelAttribute PrptExmnVO prptExmnVO
			, ModelMap model) throws Exception {

    	PrptExmnVO obj = prptExmnService.viewPrptExmnPlan(prptExmnVO);
        return getSuccess(obj);
    }

    /**
     * 예비조사계획 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/exmn/savePrptExmnPlan.do")
    @ResponseBody
    public Map savePrptExmnPlan(HttpServletRequest request
			, @ModelAttribute PrptExmnVO prptExmnVO) throws Exception {
    	
        setGlobalSession(prptExmnVO);
        
        if (prptExmnVO.getUserInfo() != null)
        	prptExmnVO.setGsUserNo(prptExmnVO.getUserInfo().getUserNo());
    	
        // 예비조사계획를 저장한다.
    	String result = prptExmnService.savePrptExmnPlan(prptExmnVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 예비조사계획 NEXT 조사차수 반환
     */
    @RequestMapping("/adm/exmn/getPrptExmnPlanNextOder.do")
    @ResponseBody
    public Map getPrptExmnPlanNextOder(HttpServletRequest request
            , @ModelAttribute PrptExmnVO prptExmnVO
			, ModelMap model) throws Exception {

        String obj = prptExmnService.getPrptExmnPlanNextOder(prptExmnVO);
        return getSuccess(obj);
    }

    /**
     * 2021.11.20
     * 예비조사계획 대상자등록 팝업 오픈
     */
    @RequestMapping("/adm/exmn/modalPrptExmn.do")
    public String modalPrptExmn(HttpServletRequest request
    		, @RequestBody PrptExmnVO prptExmnVO
    		, ModelMap model) throws Exception {

    	// 예비조사계획 상세조회
    	PrptExmnVO data = prptExmnService.viewPrptExmnPlan(prptExmnVO);
    	// 등록모드
    	data.setMode(CommConst.INSERT);
    	
        model.addAttribute("form", data);
        
        return "adm/exmn/modalPrptExmn";
    }
    

    /**
     * 2021.11.22
     * 거주이력정보 목록JSON 반환 (EasyUI GRID)
     * 2021.12.22 URL 변경 (/com/exmn/getListResiHst.do -> /adm/exmn/getListResiHst.do)
     */
    @RequestMapping("/adm/exmn/getListResiHst.do")
    @ResponseBody
    public Map getListResiHst(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute ResiHstVO resiHstVO
            , ModelMap model) throws Exception {

        setGlobalSession(resiHstVO);
        // -------------------- Default Setting End -----------------------//
        List list = resiHstService.listResiHst(resiHstVO);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.11.22
     * 생체지표정보 목록JSON 반환 (EasyUI GRID)
     * 2021.12.22 URL 변경 (/com/exmn/getListLbdyNdx.do -> /adm/exmn/getListLbdyNdx.do)
     */
    @RequestMapping("/adm/exmn/getListLbdyNdx.do")
    @ResponseBody
    public Map getListLbdyNdx(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute LbdyNdxVO lbdyNdxVO
            , ModelMap model) throws Exception {

        setGlobalSession(lbdyNdxVO);
        // -------------------- Default Setting End -----------------------//
        List list = lbdyNdxService.listLbdyNdx(lbdyNdxVO);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

}
