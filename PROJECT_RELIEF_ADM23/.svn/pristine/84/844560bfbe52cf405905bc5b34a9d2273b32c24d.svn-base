package business.sys.code.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.relief.service.ReliefVO;
import business.sys.code.service.CodeService;
import business.sys.code.service.CodeVO;
import common.base.BaseController;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러 클래스] - 코드관리
 *
 * @class   : CodeController
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일         수정자                수정내용
 *  -------        --------      ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class CodeController extends BaseController {

    @Resource(name="CodeService")
    protected CodeService codeService;

    /**
     * 코드리스트 페이지
     */
    @RequestMapping("/sys/code/listCode.do")
    public String listCode(HttpServletRequest request, @RequestParam Map paramMap
            , @ModelAttribute CodeVO codeVO, ModelMap model) throws Exception {

        setGlobalSession(codeVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model",     codeVO);

        return "sys/code/listCode";
    }

    /**
     * 코드리스트 조회
     */
    @RequestMapping("/sys/code/getListCode.do")
    @ResponseBody
    public Map getlistCode(HttpServletRequest request
    		, @RequestParam Map<String,String> reqMap
            , @ModelAttribute CodeVO codeVO
            , ModelMap model) throws Exception {

        setGlobalSession(codeVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = codeService.listCode(codeVO, page, size);
        }
        else {
        	list = codeService.listCode(codeVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 코드상세조회JSON 반환
     */
    @RequestMapping("/sys/code/getCode.do")
    @ResponseBody
    public Map getCode(HttpServletRequest request
            , @ModelAttribute CodeVO codeVO
            , ModelMap model) throws Exception {

        CodeVO obj = codeService.viewCode(codeVO);
        return getSuccess(obj);
    }
    
    /**
     * EasyUI용 코드리스트 조회 (개발샘플용)
     */
    @RequestMapping("/sys/code/getListCodeGrid.do")
    @ResponseBody
    public Map getListCodeGrid(HttpServletRequest request, @RequestParam Map paramMap
            , @ModelAttribute CodeVO codeVO, ModelMap model) throws Exception {

        setGlobalSession(codeVO);

        // -------------------- Default Setting End -----------------------//
        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = codeService.listCode(codeVO, page, size);
        }
        else {
        	list = codeService.listCode(codeVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 코드저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/sys/code/saveCode.do")
    @ResponseBody
    public Map saveCode(HttpServletRequest request
    		, @ModelAttribute CodeVO codeVO) throws Exception {
    	
        setGlobalSession(codeVO);
        
        if (codeVO.getUserInfo() != null)
        	codeVO.setGsUserNo(codeVO.getUserInfo().getUserNo());
    	
        // 코드관리를 저장한다.
    	String result = codeService.saveCode(codeVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 코드엑셀 다운로드
     */
    @RequestMapping("/sys/code/downCodeExcel.do")
    public String downCodeExcel(HttpServletRequest request
    		, @ModelAttribute CodeVO codeVO
    		, ModelMap model) throws Exception {
    	
        Map paramMap = getParameterMap(request, true);
        // 목록 조회
        List<CodeVO> list = codeService.listCode(codeVO);
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "Code");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "코드관리");
		return "excelTempView";
    }    

    @Resource(name="fileManager")
    FileManager fileManager;
    
    /**
     * 샘플암호화/복호화 실행
     */
    @RequestMapping("/sys/code/execSampleDRM.do")
    @ResponseBody
    public Map execSampleDRM(
    		HttpServletRequest request, 
    		@RequestBody ReliefVO reliefVO
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        String mode = reliefVO.getMode();
        Map result = getFailure();
        
    	// 관리자가 아닌 경우
    	if (!CommConst.isAdminRole(reliefVO.getUserInfo().getRoleId())) {
    		return result;
    	}
        if ("ENCRYPT".equals(mode)) {
        	result = fileManager.encryptSample();
        }
        else if ("DECRYPT".equals(mode)) {
        	result = fileManager.decryptSample();
        }
    	// 성공결과 반환
        return result;
    }

    /**
     * 샘플암호화/복호화 파일보기
     */
    @RequestMapping(value="/sys/code/linkSampleDRM.do")
    @ResponseBody
    public HttpEntity<byte[]> linkFile(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
    	return fileManager.previewSample(request, response);
    }
    
}