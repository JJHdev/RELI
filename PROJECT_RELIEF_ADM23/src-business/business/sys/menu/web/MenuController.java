package business.sys.menu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommBizUtils;
import business.sys.menu.service.MenuService;
import business.sys.menu.service.MenuVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 메뉴관리 관리 Controller
 * 
 * @class   : MenuController
 * @author  : LSH
 * @since   : 2021.09.05
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class MenuController extends BaseController {

    @Resource(name="MenuService")
    protected MenuService menuService;
    
    /**
     * 메뉴관리 관리 화면
     */
    @RequestMapping("/sys/menu/listMenu.do")
    public String listMenu(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute MenuVO menuVO) throws Exception {
				
        setGlobalSession(menuVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", menuVO);
    	
        return "sys/menu/listMenu";
    }

    /**
     * 메뉴관리 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/sys/menu/getListMenu.do")
    @ResponseBody
    public Map getListMenu(HttpServletRequest request
    		, @RequestParam Map<String,String> reqMap
            , @ModelAttribute MenuVO menuVO
            , ModelMap model) throws Exception {

        setGlobalSession(menuVO);
        // -------------------- Default Setting End -----------------------//

        List data = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
            data = menuService.listMenu(menuVO, page, size);
        }
        else {
        	data = menuService.listMenu(menuVO);
        }
        
        if ("Y".equals(reqMap.get("tree"))) {
            // 2021.08.26 메뉴를 계층구조로 변경
    		// 2021.09.09 계층구조 생성유틸로 변경
    		Map keys = new HashMap();
    		keys.put("level" , "level");
    		keys.put("itemId", "menuId");
    		keys.put("itemNm", "menuNm");
    		keys.put("parentId", "upMenuId");
    		
    		List<Map> list = CommBizUtils.buildTree(data, keys);
            // Easy UI GRID용 결과값 반환
            return getEasyUIResult(list);
        }
        else {
            // Easy UI GRID용 결과값 반환
            return getEasyUIResult(data);
        }
    }

    /**
     * 메뉴관리 조회JSON 반환
     */
    @RequestMapping("/sys/menu/getMenu.do")
    @ResponseBody
    public Map getMenu(HttpServletRequest request
            , @ModelAttribute MenuVO menuVO
			, ModelMap model) throws Exception {

        MenuVO obj = menuService.viewMenu(menuVO);
        return getSuccess(obj);
    }

    /**
     * 메뉴관리 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/sys/menu/saveMenu.do")
    @ResponseBody
    public Map saveMenu(
    		HttpServletRequest request, 
    		@ModelAttribute MenuVO menuVO
    	) throws Exception {
    	
        setGlobalSession(menuVO);
        
        if (menuVO.getUserInfo() != null)
        	menuVO.setGsUserNo(menuVO.getUserInfo().getUserNo());
    	
        // 메뉴관리를 저장한다.
    	String result = menuService.saveMenu(menuVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 메뉴관리 엑셀 다운로드
     */
    @RequestMapping("/sys/menu/downMenuExcel.do")
    public String downMenuExcel(
			HttpServletRequest request, 
            @ModelAttribute MenuVO menuVO,
			ModelMap model
		) throws Exception {
    	
        Map paramMap = getParameterMap(request, true);
        // 목록 조회
        List<MenuVO> list = menuService.listMenu(menuVO);
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "Menu");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "메뉴관리");
		return "excelTempView";
    }
}
