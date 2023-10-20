package business.sys.hldy.web;

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

import business.sys.hldy.service.HldyService;
import business.sys.hldy.service.HldyVO;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 공휴일관리 Controller
 * 
 * @class   : HldyController
 * @author  : KSH
 * @since   : 2023.02.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class HldyController extends BaseController{
	
	@Resource(name="HldyService")
	protected HldyService hldyService;
	
	/**
     * 공휴일관리 화면
     */
	@RequestMapping("/sys/hldy/listHldy.do")
	public String listHldy(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute HldyVO hldyVo) throws Exception {
		
		 setGlobalSession(hldyVo);
	     // -------------------- Default Setting End -----------------------//

	     model.addAttribute("model", hldyVo);
		
		return "sys/hldy/listHldy";	
	}
	/**
     * 공휴일관리 리스트 조회
     */
    @RequestMapping("/sys/hldy/getListHldy.do")
    @ResponseBody
    public Map getListRole(HttpServletRequest request, @RequestParam Map<String,String> reqMap
            , @ModelAttribute HldyVO hldyVo, ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);
        setGlobalSession(hldyVo);
        // -------------------- Default Setting End -----------------------//
        
        List list = null;
        if(reqMap.containsKey("page")) {
        	int page = CommUtils.getInt(reqMap, "page");
        	int size = CommUtils.getInt(reqMap, "rows");
        	list = hldyService.listHldy(hldyVo, page, size);
        }
        else {       	
        	list = hldyService.listHldy(hldyVo);
        }

        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 공휴일관리 조희 JSON반환
     */
    @RequestMapping("/sys/hldy/getHldy.do")
    @ResponseBody
    public Map getHldy(HttpServletRequest request
            , @ModelAttribute HldyVO hldyVo
			, ModelMap model) throws Exception {

      	HldyVO obj = hldyService.viewHldy(hldyVo);
        return getSuccess(obj);
    }
    
    /**
     * 공휴일관리 저장처리(등록, 수정, 삭제)
     */
    @RequestMapping("/sys/hldy/saveHldy.do")
    @ResponseBody
    public Map saveHldy(
    		HttpServletRequest request, 
    		@ModelAttribute HldyVO hldyVo
    	) throws Exception {
    	
        setGlobalSession(hldyVo);
        
        //if (hldyVo.getUserInfo() != null)
        //	hldyVo.setUserNo(hldyVo.getUserInfo().getUserNo());
    	
        // 공휴일관리를 저장한다.
    	String result = hldyService.saveHldy(hldyVo);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    

}
