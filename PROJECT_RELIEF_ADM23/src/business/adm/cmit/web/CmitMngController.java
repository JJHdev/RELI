package business.adm.cmit.web;

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
import business.com.cmit.service.CmitMngService;
import business.com.cmit.service.CmitMngVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 위원회관리 Controller
 * 
 * @class   : CmitMngController
 * @author  : LSH
 * @since   : 2023.01.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class CmitMngController extends BaseController {

    @Resource(name="CmitMngService")
    protected CmitMngService cmitMngService;
    
    
    /**
     * 위원회 관리 화면 오픈
     */
    @RequestMapping("/adm/cmit/listCmitMng.do")
    public String listCmitMng(HttpServletRequest request
	        , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", cmitMngVO);
    	
        return "adm/cmit/listCmitMng";
    }

    /**
     * 위원회 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/cmit/getListCmitDmge.do")
    @ResponseBody
    public Map getListCmitDmge(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = cmitMngService.listCmitDmge(cmitMngVO, page, size);
        }
        else {
        	list = cmitMngService.listCmitDmge(cmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원회 조회JSON 반환
     */
    @RequestMapping("/adm/cmit/getCmitDmge.do")
    @ResponseBody
    public Map getCmitDmge(HttpServletRequest request
            , @ModelAttribute CmitMngVO cmitMngVO
			, ModelMap model) throws Exception {

    	CmitMngVO obj = cmitMngService.viewCmitDmge(cmitMngVO);
        return getSuccess(obj);
    }

    /**
     * 위원회 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/cmit/saveCmitDmge.do")
    @ResponseBody
    public Map saveCmitDmge(HttpServletRequest request
			, @ModelAttribute CmitMngVO cmitMngVO) throws Exception {
    	
        setGlobalSession(cmitMngVO);
        
        if (cmitMngVO.getUserInfo() != null)
        	cmitMngVO.setGsUserNo(cmitMngVO.getUserInfo().getUserNo());
    	
        // 위원회피해조사를 저장한다.
    	String result = cmitMngService.saveCmitDmge(cmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 위원회 목록 엑셀 다운로드
     */
    @RequestMapping("/adm/cmit/downCmitDmgeExcel.do")
    public String downMfcmmExcel(HttpServletRequest request
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = cmitMngService.listCmitDmge(cmitMngVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "CmitDmge");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "위원회 목록");

		return "excelTempView";
    }

    /**
     * 위원회 소속 위원 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/cmit/getListCmitMng.do")
    @ResponseBody
    public Map getListCmitMng(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = cmitMngService.listCmitMng(cmitMngVO, page, size);
        }
        else {
        	list = cmitMngService.listCmitMng(cmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원회 소속 위원 저장처리 (다중처리)
     * - saveList  : 등록목록 
     * - removeList: 삭제목록
     */
    @RequestMapping("/adm/cmit/saveCmitMng.do")
    @ResponseBody
    public Map saveCmitMng(HttpServletRequest request
			, @RequestBody CmitMngVO cmitMngVO) throws Exception {
    	
        setGlobalSession(cmitMngVO);
        
        if (cmitMngVO.getUserInfo() != null)
        	cmitMngVO.setGsUserNo(cmitMngVO.getUserInfo().getUserNo());
    	
        // 위원회 소속 위원 다중목록을 저장한다.
    	String result = cmitMngService.saveCmitMng(cmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 위원회 소속 위원 등록 모달팝업 오픈
     */
    @RequestMapping("/adm/cmit/modalCmitMng.do")
    public String modalCmitMng(HttpServletRequest request
    		, @ModelAttribute CmitMngVO cmitMngVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(cmitMngVO);
        
        // 위원회정보를 조회한다.
        CmitMngVO obj = cmitMngService.viewCmitDmge(cmitMngVO);
        // 위원회등록대상여부 정의
        obj.setSrchMngYn(CommConst.YES);
        
        model.addAttribute("form", obj);
        
        return "adm/cmit/modalCmitMng";
    }
}
