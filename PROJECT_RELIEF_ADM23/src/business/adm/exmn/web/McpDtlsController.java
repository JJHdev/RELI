package business.adm.exmn.web;

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

import business.com.CommFormFile;
import business.com.exmn.service.McpDtlsService;
import business.com.exmn.service.McpDtlsVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 의료비내역 Controller
 * 
 * @class   : McpDtlsController
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class McpDtlsController extends BaseController {

    @Resource(name="McpDtlsService")
    protected McpDtlsService mcpDtlsService;

    // 파일업로드용 
    @Resource(name="fileManager")
    protected FileManager fileManager;

    /**
     * 의료비내역 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListMcpDtls.do")
    @ResponseBody
    public Map getListMcpDtls(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute McpDtlsVO mcpDtlsVO) throws Exception {

        setGlobalSession(mcpDtlsVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mcpDtlsService.listMcpDtls(mcpDtlsVO, page, size);
        }
        else {
        	list = mcpDtlsService.listMcpDtls(mcpDtlsVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 의료비내역 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getMcpDtls.do")
    @ResponseBody
    public Map getMcpDtls(HttpServletRequest request
            , @ModelAttribute McpDtlsVO mcpDtlsVO) throws Exception {

        McpDtlsVO obj = mcpDtlsService.viewMcpDtls(mcpDtlsVO);
        return getSuccess(obj);
    }

    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListMcpDtlsSummary.do")
    @ResponseBody
    public Map getListMcpDtlsSummary(HttpServletRequest request
            , @ModelAttribute McpDtlsVO mcpDtlsVO) throws Exception {

        setGlobalSession(mcpDtlsVO);
        // -------------------- Default Setting End -----------------------//

        // 집계 목록
        List list = mcpDtlsService.listMcpDtlsSummary(mcpDtlsVO);
        // 집계 합계
        List total = mcpDtlsService.listMcpDtlsSummaryTotal(mcpDtlsVO);
        // Easy UI GRID용 결과값 반환
        Map result = getEasyUIResult(list);
        result.put("footer", total);
        
        return result;
    }

    /**
     * 2021.11.30
     * 본조사 의료비 엑셀 업로드 (일괄등록)
     */
    @RequestMapping("/adm/exmn/saveMcpDtls.do")
    @ResponseBody
    public Map saveMcpDtls(HttpServletRequest request
			, @ModelAttribute McpDtlsVO mcpDtlsVO) throws Exception {
    	
        setGlobalSession(mcpDtlsVO);
        
        if (mcpDtlsVO.getUserInfo() != null)
        	mcpDtlsVO.setGsUserNo(mcpDtlsVO.getUserInfo().getUserNo());
    	
	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    
	    if (files == null || files.size() != 1) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
	    }
	    FileInfo fileInfo = files.get(0);
    	if (!"Y".equals(fileInfo.getFileYn())) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
    	}
    	// 의료비 세부내역 엑셀을 읽어 저장한다.
    	mcpDtlsService.loadMcpDtls(mcpDtlsVO, fileInfo);
    	
    	// 성공결과 반환
        return getSuccess();
    }

    /**
     * 2021.11.30 LSH
     * 의료비 세부내역 산정결과 양식파일 다운로드
     */
    @RequestMapping(value="/adm/exmn/downMcpDtlsForm.do")
    public void downMcpDtlsForm(HttpServletRequest request
    		, HttpServletResponse response) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);
    	
        // 다운로드할 양식 파일 정보
    	CommFormFile cf = CommFormFile.MCPDTLS;
        
        FileInfo fileInfo = FileInfo.builder()
							.saveName(cf.getSaveName())
							.fileName(cf.getFileName())
							.fullPath(cf.getFullPath())
							.build();
        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 2021.11.30 LSH
     * 의료비 세부내역 산정결과 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downMcpDtlsExcel.do")
    public String downMcpDtlsExcel(HttpServletRequest request
            , @ModelAttribute McpDtlsVO mcpDtlsVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(mcpDtlsVO);
        // -------------------- Default Setting End -----------------------//
        
        // 세부의료비내역
        List list = mcpDtlsService.listMcpDtls(mcpDtlsVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "McpDtls");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "세부의료비내역");

		return "excelTempView";
    }
    
    /**
     * 2022.12.09 
     * 본조사 - 의료비 - 세부 의료비 내역 팝업 오픈
     */
    @RequestMapping("/adm/exmn/modalMcpSckwnd.do")
    public String modalMcpSckwnd(HttpServletRequest request
    		, @RequestBody McpDtlsVO mcpDtlsVO
    		, ModelMap model) throws Exception {

    	// 세부의료비 상세조회
    	McpDtlsVO result = mcpDtlsService.viewMcpSckwnd(mcpDtlsVO);
    	result.setBizAreaCd (mcpDtlsVO.getBizAreaCd());
    	result.setBizOder   (mcpDtlsVO.getBizOder());
    	result.setExmnOder  (mcpDtlsVO.getExmnOder());
    	result.setAplyNo    (mcpDtlsVO.getAplyNo());
        model.addAttribute("form", result);
        
        return "adm/exmn/modalMcpSckwnd";
    }

    /**
     * 2022.12.09 
     * 본조사 - 의료비 - 세부 의료비 내역 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListMcpSckwnd.do")
    @ResponseBody
    public Map getListMcpSckwnd(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute McpDtlsVO mcpDtlsVO) throws Exception {

        setGlobalSession(mcpDtlsVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        
        if (CommUtils.isNotEmpty(mcpDtlsVO.getBizAreaCd()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getBizOder  ()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getExmnOder ()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getAplyNo   ()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getSckwndCd ()))
        	list = mcpDtlsService.listMcpDtls(mcpDtlsVO);

        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 2022.12.09 
     * 본조사 - 의료비 - 세부 의료비 내역 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downMcpSckwndExcel.do")
    public String downMcpSckwndExcel(HttpServletRequest request
            , @ModelAttribute McpDtlsVO mcpDtlsVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(mcpDtlsVO);
        // -------------------- Default Setting End -----------------------//

    	// 세부의료비 상세조회
    	McpDtlsVO result = mcpDtlsService.viewMcpSckwnd(mcpDtlsVO);
    	paramMap.put("bizAreaCd", mcpDtlsVO.getBizAreaCd());
    	paramMap.put("bizOder"  , mcpDtlsVO.getBizOder());
    	paramMap.put("exmnOder" , mcpDtlsVO.getExmnOder());
    	paramMap.put("aplyNo"   , mcpDtlsVO.getAplyNo());
    	paramMap.put("sckwndCd" , mcpDtlsVO.getSckwndCd());
    	paramMap.put("sckwndNm" , result.getSckwndNm());
    	paramMap.put("mcpTotAmt", Long.parseLong(result.getMcpTotAmt()));
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
    	
        List list = null;
        if (CommUtils.isNotEmpty(mcpDtlsVO.getBizAreaCd()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getBizOder  ()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getExmnOder ()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getAplyNo   ()) &&
       		CommUtils.isNotEmpty(mcpDtlsVO.getSckwndCd ()))
        	list = mcpDtlsService.listMcpDtls(mcpDtlsVO);
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "McpSckwnd");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "세부의료비내역");

		return "excelTempView";
    }

    /**
     * 2022.12.12 LSH
     * 종합현황 - 의료비내역 - 의료비 검색 총합 조회
     * 종합현황 - 의료비내역 - 신청정보 기준 의료비 총합 조회
     */
    @RequestMapping("/adm/exmn/getMcpDtlsSummary.do")
    @ResponseBody
    public Map getMcpDtlsSummary(HttpServletRequest request
            , @ModelAttribute McpDtlsVO mcpDtlsVO) throws Exception {

        // 의료비 검색 총합
    	long searchAmt = mcpDtlsService.viewMcpDtlsTotalBySearch(mcpDtlsVO);
    	// 신청정보 기준 의료비 총합
    	long totalAmt  = mcpDtlsService.viewMcpDtlsTotalByAply(mcpDtlsVO);
    	
    	mcpDtlsVO.setMcpSrchAmt( String.valueOf(searchAmt) );
    	mcpDtlsVO.setMcpTotAmt ( String.valueOf(totalAmt ) );
    	
        return getSuccess(mcpDtlsVO);
    }
}