package business.adm.exmn.web;

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

import business.com.exmn.service.DmgeGrdService;
import business.com.exmn.service.DmgeGrdVO;
import business.com.exmn.service.PrptExmnVO;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 피해등급 관리 Controller
 * 
 * @class   : DmgeGrdController
 * @author  : LSH
 * @since   : 2022.12.22
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class DmgeGrdController extends BaseController {

    @Resource(name="DmgeGrdService")
    protected DmgeGrdService dmgeGrdService;
    
    
    /**
     * 피해등급관리 화면 오픈
     */
    @RequestMapping("/adm/exmn/listDmgeGrd.do")
    public String listDmgeGrd(HttpServletRequest request
	        , @ModelAttribute DmgeGrdVO dmgeGrdVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(dmgeGrdVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", dmgeGrdVO);
    	
        return "adm/exmn/listDmgeGrd";
    }
    
    /**
     * 피해등급관리 년도별 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListDmgeGrdGroup.do")
    @ResponseBody
    public Map getListDmgeGrdGroup(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute DmgeGrdVO dmgeGrdVO
            , ModelMap model) throws Exception {

        setGlobalSession(dmgeGrdVO);
        // -------------------- Default Setting End -----------------------//

        List list = dmgeGrdService.listDmgeGrdGroup();
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 피해등급관리 년도별 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getDmgeGrdGroup.do")
    @ResponseBody
    public Map getDmgeGrdGroup(HttpServletRequest request
            , @ModelAttribute DmgeGrdVO dmgeGrdVO
			, ModelMap model) throws Exception {

        DmgeGrdVO obj = dmgeGrdService.viewDmgeGrdGroup(dmgeGrdVO);
        return getSuccess(obj);
    }
    
    /**
     * 피해등급관리 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/exmn/getListDmgeGrd.do")
    @ResponseBody
    public Map getListDmgeGrd(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute DmgeGrdVO dmgeGrdVO
            , ModelMap model) throws Exception {

        setGlobalSession(dmgeGrdVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = dmgeGrdService.listDmgeGrd(dmgeGrdVO, page, size);
        }
        else {
        	list = dmgeGrdService.listDmgeGrd(dmgeGrdVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 피해등급관리 조회JSON 반환
     */
    @RequestMapping("/adm/exmn/getDmgeGrd.do")
    @ResponseBody
    public Map getDmgeGrd(HttpServletRequest request
            , @ModelAttribute DmgeGrdVO dmgeGrdVO
			, ModelMap model) throws Exception {

        DmgeGrdVO obj = dmgeGrdService.viewDmgeGrd(dmgeGrdVO);
        return getSuccess(obj);
    }

    /**
     * 피해등급관리 저장처리 (등록,수정,삭제)
     * 1) mode=I (등록) : 기준년도별 등급이 없는 경우
     * 2) mode=U (수정) : 기준년도별 등급이 있는 경우
     *    - dmgeGrdList 포함
     */
    @RequestMapping("/adm/exmn/saveDmgeGrd.do")
    @ResponseBody
    public Map saveDmgeGrd(HttpServletRequest request
			, @RequestBody DmgeGrdVO dmgeGrdVO) throws Exception {
    	
        setGlobalSession(dmgeGrdVO);
        
        if (dmgeGrdVO.getUserInfo() != null)
        	dmgeGrdVO.setGsUserNo(dmgeGrdVO.getUserInfo().getUserNo());
    	
        // 피해등급관리를 저장한다.
    	String result = dmgeGrdService.saveDmgeGrd(dmgeGrdVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}
