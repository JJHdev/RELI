package business.adm.support.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.support.service.DtlService;
import business.com.support.service.LwstService;
import business.com.support.service.LwstVO;
import business.com.support.service.SprtAplyService;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 취약계층소송지원 관리 Controller
 * 
 * @class : LwstController
 * @author : 한금주
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */
@Controller
@SuppressWarnings({ "all" })
public class LwstController extends BaseController {

	@Resource(name = "LwstService")
	protected LwstService lwstService;

	@Resource(name = "DtlService")
	protected DtlService dtlService;
	
	@Resource(name = "SprtAplyService")
	protected SprtAplyService sprtAplyservice;

	// 2023.01.27 LSH 제출서류 추가등록을 위한 파일컨트롤
    @Resource(name="fileManager")
    FileManager fileManager;

	/**
	 * 취약계층소송지원 - 소송신청현황 화면
	 */
	@RequestMapping("/adm/support/listLwst.do")
	public String listLwst(HttpServletRequest request, ModelMap model, 
			@ModelAttribute LwstVO lwstVO) throws Exception {

		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
		// -------------------- Default Setting End -----------------------//
		model.addAttribute("model", lwstVO);
		return "adm/support/listLwst";
	}

	/**
	 * 취약계층소송지원 - 소송신청현황 목록 (EasyUI Grid)
	 */
	@RequestMapping("/adm/support/getlistLwst.do")
	@ResponseBody
	public Map getlistLwst(HttpServletRequest request, ModelMap model, 
			@ModelAttribute LwstVO lwstVO) throws Exception {

		setGlobalSession(lwstVO);
		Map paramMap = getParameterMap(request, true);

		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = lwstService.listLwst(lwstVO, page, size);
		} else {
			list = lwstService.listLwst(lwstVO);
		}
		return getEasyUIResult(list);
	}

	/**
	 * 취약계층소송지원 - 소송신청현황 목록 (JSON)
	 */
	@RequestMapping("/adm/support/viewListLwst.do")
	@ResponseBody
	public Map viewListLwst(HttpServletRequest request, 
			@ModelAttribute LwstVO lwstVO, ModelMap model)
			throws Exception {

		LwstVO lwstList = lwstService.viewListLwst(lwstVO.getAplyNo());

		if (lwstList != null) {
			lwstList.setSprtSeCdList(CommUtils.splitToList(lwstList.getSprtSeCd(), ","));
		}
		return getSuccess(lwstList);
	}

	/**
	 * 취약계층소송지원 - 소송지원 신청 접수
	 */
	@RequestMapping("/adm/support/updateLwstIncdnt.do")
	@ResponseBody
	public Map updateLwstIncdnt(HttpServletRequest request, 
			@ModelAttribute LwstVO lwstVO) throws Exception {

		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
		
		if (CommUtils.isNotEmptyList(lwstVO.getSprtSeCdList())) {
			// 2021.12.06 LSH 구분자는 콤마(,)로 처리할것
			lwstVO.setSprtSeCd(CommUtils.mergeString(lwstVO.getSprtSeCdList(), ","));
		}
		int result = lwstService.updateLwstIncdnt(lwstVO);
		return getSuccess("Message", result);
	}

	/**
	 * 취약계층소송지원 - 소송지원 취소
	 */
	@RequestMapping("/adm/support/cancelLwstIncdnt.do")
	@ResponseBody
	public Map cancelLwstIncdnt(HttpServletRequest request, 
			@ModelAttribute LwstVO lwstVO, Map requestMap)
			throws Exception {

		setGlobalSession(requestMap);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
		if (requestMap.get("gsUserNo") != null)
			lwstVO.setGsUserNo((String) requestMap.get("gsUserNo"));
		int result = lwstService.cancelLwstIncdnt(lwstVO);
		return getSuccess("Message", result);
	}
	
	 /**
     * 취약계층 소송지원 신청 접수 엑셀 다운로드
     */
    @RequestMapping("/adm/support/downLwstExcel.do")
    public String downLwstExcel(HttpServletRequest request
            , @ModelAttribute LwstVO lwstVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(lwstVO);
        lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
        lwstVO.setGsUserNm(lwstVO.getUserInfo().getUserNm());
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(lwstVO.getSprtSeCdList())) {
			// 2021.12.06 LSH 구분자는 콤마(,)로 처리할것
			lwstVO.setSprtSeCd(CommUtils.mergeString(lwstVO.getSprtSeCdList(), ","));
		}
        
        // 취약계층 신청접수목록
        List list = lwstService.listLwst(lwstVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "LWST");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "취약계층소송지원_신청접수현황");

		return "excelTempView";
    }
	

	/**
	 * 취약계층소송지원 - 소송개요현황 화면
	 */
	@RequestMapping("/adm/support/listLwstIncdnt.do")
	public String listLwstIncdnt(HttpServletRequest request, 
			ModelMap model, 
			@ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		// -------------------- Default Setting End -----------------------//
		model.addAttribute("model", lwstVO);
		return "adm/support/listLwstIncdnt";
	}

	/**
	 * 취약계층소송지원 - 소송개요현황 화면 (소송저장)
	 */
	@RequestMapping("/adm/support/regiLwstIncdnt.do")
	@ResponseBody
	public Map regiLwstIncdnt(HttpServletRequest request, 
			@RequestBody LwstVO lwstVO) throws Exception {
		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setMdfrNo(lwstVO.getGsUserNo());
		lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		String result = lwstService.regiLwstIncdnt(lwstVO);

		return getSuccess("Message", result);
	}

	/**
	 * 2021.12.02 취약계층소송지원 - 소송개요현황 - 소송추가팝업/소송수정팝업
	 */
	@RequestMapping("/adm/support/modalLwstIncdnt.do")
	public String modalLwstIncdnt(HttpServletRequest request, 
			@RequestBody LwstVO lwstVO, ModelMap model)
			throws Exception {

		// 처리모드가 없을 경우 등록모드로 설정
		if (CommUtils.isEmpty(lwstVO.getMode()))
			lwstVO.setMode(CommConst.INSERT);

		if (CommConst.UPDATE.equals(lwstVO.getMode()) && lwstVO.getIncdntMngNo() > 0) {
			lwstVO = lwstService.viewListLwstIncdnt(lwstVO.getIncdntMngNo());
			lwstVO.setMode(CommConst.UPDATE);
		}
		model.addAttribute("form", lwstVO);
		return "adm/support/modalLwstIncdnt";
	}

	/**
	 * 2021.12.03 취약계층소송지원 - 소송추가팝업의 향후기일목록 조회
	 */
	@RequestMapping("/adm/support/getlistLwstIncdntDtl.do")
	@ResponseBody
	public Map getlistLwstIncdntDtl(HttpServletRequest request, 
			@ModelAttribute LwstVO lwstVO) throws Exception {

		setGlobalSession(lwstVO);
		List list = dtlService.listDtlsList(lwstVO);
		return getEasyUIResult(list);
	}

	/**
	 * 취약계층소송지원 - 소송개요현황 목록 (EasyUI Grid)
	 */
	@RequestMapping("/adm/support/getlistLwstIncdnt.do")
	@ResponseBody
	public Map getlistLwstIncdnt(HttpServletRequest request,
			ModelMap model, 
			@ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		Map paramMap = getParameterMap(request, true);

		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = lwstService.listLwstIncdnt(lwstVO, page, size);
		} else {
			list = lwstService.listLwstIncdnt(lwstVO);
		}
		return getEasyUIResult(list);
	}

	/**
	 * 취약계층소송지원 - 소송개요현황 목록 (JSON)
	 */
	@RequestMapping("/adm/support/viewListLwstIncdnt.do")
	@ResponseBody
	public Map viewListLwstIncdnt(HttpServletRequest request, 
			@ModelAttribute LwstVO lwstVO, 
			ModelMap model)
			throws Exception {

		LwstVO lwstList = lwstService.viewListLwstIncdnt(lwstVO.getIncdntMngNo());
		return getSuccess(lwstList);
	}
	
	/**
     * 취약계층 소송지원 개요 접수 엑셀 다운로드
     */
    @RequestMapping("/adm/support/downLwstIncdntExcel.do")
    public String downLwstIncdntExcel(HttpServletRequest request
            , @ModelAttribute LwstVO lwstVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(lwstVO);
        lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
        lwstVO.setGsUserNm(lwstVO.getUserInfo().getUserNm());
        lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		// -------------------- Default Setting End -----------------------//

        // 취약계층 신청접수목록
        List list = lwstService.listLwstIncdnt(lwstVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "LWST_Incdnt");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "취약계층소송지원_소송개요현황");

		return "excelTempView";
    }

	/**
	 * 취약계층소송지원 - 소송진행현황 화면
	 */
	@RequestMapping("/adm/support/listLwstPrgre.do")
	public String listLwstPrgre(HttpServletRequest request, 
			ModelMap model, 
			@ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		HashMap paramMap = getParameterMap(request);
		ArrayList listAllLwst = lwstService.listAllLwst(paramMap);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
		model.addAttribute("model", lwstVO);
		model.addAttribute("listAllLwst", listAllLwst);
		return "adm/support/listLwstPrgre";
	}
	
	/**
     * 취약계층 소송지원 개요 접수 엑셀 다운로드
     */
    @RequestMapping("/adm/support/downLwstPrgreExcel.do")
    public String downLwstPrgreExcel(HttpServletRequest request
            , @ModelAttribute LwstVO lwstVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
        lwstVO.setGsUserNm(lwstVO.getUserInfo().getUserNm());
        lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		// -------------------- Default Setting End -----------------------//

		 if (CommUtils.isNotEmptyList(lwstVO.getSprtSeCdList())) {
			// 2021.12.06 LSH 구분자는 콤마(,)로 처리할것
			lwstVO.setSprtSeCd(CommUtils.mergeString(lwstVO.getSprtSeCdList(), ","));
		}

        // 취약계층 신청접수목록
        List list = lwstService.listLwstPrgre(lwstVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "LWST_Prgre");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "취약계층소송지원_진행현황");

		return "excelTempView";
    }

	/**
	 * 취약계층소송지원 - 소송진행현황 목록 (EasyUI Grid)
	 */
	@RequestMapping("/adm/support/getlistLwstPrgre.do")
	@ResponseBody
	public Map getlistLwstPrgre(HttpServletRequest request, 
			ModelMap model, 
			@ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		Map paramMap = getParameterMap(request, true);
		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = lwstService.listLwstPrgre(lwstVO, page, size);
		} else {
			list = lwstService.listLwstPrgre(lwstVO);
		}
		return getEasyUIResult(list);
	}

	/**
	 * 취약계층소송지원 - 소송진행현황 목록 (상세보기)
	 */
	@RequestMapping("/adm/support/viewlistLwstPrgre.do")
	@ResponseBody
	public Map viewlistLwstPrgre(HttpServletRequest request, 
			ModelMap model, 
			@ModelAttribute LwstVO lwstVO)
			throws Exception {
		LwstVO lwstList = lwstService.viewlistLwstPrgre(lwstVO.getAplyNo());
		if (lwstList != null) {
			lwstList.setSprtSeCdList(CommUtils.splitToList(lwstList.getSprtSeCd(), ","));
		}
		return getSuccess(lwstList);
	}

	/**
	 * 취약계층소송지원 - 소송진행현황 화면 (소송별신청)
	 */
	@RequestMapping("/adm/support/regiLwstAply.do")
	@ResponseBody
	public Map regiLwstAply(HttpServletRequest request, 
			@RequestBody LwstVO lwstVO) throws Exception {
		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
		String result = lwstService.regiLwstAply(lwstVO);

		return getSuccess("Message", result);
	}

	/**
	 * 2021.12.02 취약계층소송지원 - 소송개요현황 - 소송추가팝업/소송수정팝업
	 */
	@RequestMapping("/adm/support/modalLwstAplyPrgre.do")
	public String modalLwstAplyPrgre(HttpServletRequest request, 
			@RequestBody LwstVO lwstVO, ModelMap model)
			throws Exception {
		
		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());

		// 처리모드가 없을 경우 등록모드로 설정
		if (CommUtils.isEmpty(lwstVO.getMode()))
			lwstVO.setMode(CommConst.INSERT);

		if (CommConst.UPDATE.equals(lwstVO.getMode()) && lwstVO.getAplyNo() != null) {
			lwstVO = lwstService.viewlistLwstPrgre(lwstVO.getAplyNo());
			lwstVO.setMode(CommConst.UPDATE);
		}
		model.addAttribute("form", lwstVO);
		return "adm/support/modalLwstAplyPrgre";
	}

	/**
	 * 취약계층소송지원 - 소송진행현황 사건번호 찾기
	 */
	@RequestMapping("/adm/support/searchLwstNo.do")
	@ResponseBody
	public Map searchLwstNo(HttpServletRequest request, 
			ModelMap model, 
			@ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		HashMap paramMap = getParameterMap(request);
		ArrayList searchLwstNo = lwstService.searchLwstNo(paramMap);
		model.addAttribute("searchLwstNo",searchLwstNo);
		model.addAttribute("paramMap",paramMap);
		return getSuccess(searchLwstNo);
	}
	
	@RequestMapping("/adm/support/getlistLwstSprtAply.do")
	@ResponseBody
	public Map getlistLwstSprtAply(HttpServletRequest request, 
			@ModelAttribute LwstVO lwstVO) throws Exception {

		setGlobalSession(lwstVO);
		List list = sprtAplyservice.listLwstSprtAply(lwstVO);
		return getEasyUIResult(list);
	}

	/**
	 * 취약계층소송지원 - 소송취소현황 화면
	 */
	@RequestMapping("/adm/support/listLwstRtrcn.do")
	public String listLwstRtrcn(HttpServletRequest request, ModelMap model, @ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setRgtrNo(lwstVO.getGsUserNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
		// -------------------- Default Setting End -----------------------//

		model.addAttribute("model", lwstVO);
		return "adm/support/listLwstRtrcn";
	}
	
	/**
     * 취약계층 소송지원 취소 접수 엑셀 다운로드
     */
    @RequestMapping("/adm/support/downlistLwstRtrcnExcel.do")
    public String downlistLwstRtrcnExcel(HttpServletRequest request
            , @ModelAttribute LwstVO lwstVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(lwstVO);
		lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
		lwstVO.setIncdntMngNo(lwstVO.getIncdntMngNo());
		lwstVO.setAplyNo(lwstVO.getAplyNo());
        lwstVO.setGsUserNm(lwstVO.getUserInfo().getUserNm());
		// -------------------- Default Setting End -----------------------//

		 if (CommUtils.isNotEmptyList(lwstVO.getSprtSeCdList())) {
			// 2021.12.06 LSH 구분자는 콤마(,)로 처리할것
			lwstVO.setSprtSeCd(CommUtils.mergeString(lwstVO.getSprtSeCdList(), ","));
		}

        // 취약계층 신청접수목록
        List list = lwstService.listLwstRtrcn(lwstVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "LWST_Rtrcn");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "취약계층소송지원_취소현황");

		return "excelTempView";
    }

	/**
	 * 취약계층소송지원 - 소송취소현황 목록 (EasyUI Grid)
	 */
	@RequestMapping("/adm/support/getlistLwstRtrcn.do")
	@ResponseBody
	public Map getlistLwstRtrcn(HttpServletRequest request, ModelMap model, @ModelAttribute LwstVO lwstVO)
			throws Exception {

		setGlobalSession(lwstVO);
		Map paramMap = getParameterMap(request, true);

		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = lwstService.listLwstRtrcn(lwstVO, page, size);
		} else {
			list = lwstService.listLwstRtrcn(lwstVO);
		}
		return getEasyUIResult(list);
	}

	/**
	 * 취약계층소송지원 - 소송취소현황 목록 (JSON)
	 */
	@RequestMapping("/adm/support/viewlistLwstRtrcn.do")
	@ResponseBody
	public Map viewlistLwstRtrcn(HttpServletRequest request, @ModelAttribute LwstVO lwstVO, ModelMap model)
			throws Exception {
		LwstVO lwstList = lwstService.viewlistLwstRtrcn(lwstVO.getAplyNo());
		if (lwstList != null) {
			lwstList.setSprtSeCdList(CommUtils.splitToList(lwstList.getSprtSeCd(), ","));
		}
		return getSuccess(lwstList);
	}


    /**
     * 2023.01.27 LSH
     * 관리자 제출서류 추가등록 업로드
     * lwstVO 에 화면에서 입력되어야할 항목
     * - aplyNo   : 신청번호
     * - papeCd   : 서류코드
     * - upPapeCd : 상위서류코드
     * - hstCn    : 추가등록사유
     */
    @RequestMapping("/adm/support/saveLwstAddfile.do")
    @ResponseBody
    public Map saveLwstAddfile(HttpServletRequest request
			, @ModelAttribute LwstVO lwstVO) throws Exception {
    	
        setGlobalSession(lwstVO);
        // -------------------- Default Setting End -----------------------//
        
        if (lwstVO.getUserInfo() != null)
        	lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());

	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    
	    if (files == null || files.size() != 1) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
	    }
	    FileInfo fileInfo = files.get(0);
    	if (!"Y".equals(fileInfo.getFileYn())) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
    	}
    	String result = lwstService.saveLwstAddfile(lwstVO, fileInfo);

    	// 성공결과 반환
        return getSuccess();
    }
}
