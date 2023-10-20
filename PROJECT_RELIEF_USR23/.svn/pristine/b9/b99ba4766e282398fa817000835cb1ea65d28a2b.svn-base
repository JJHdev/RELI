package business.usr.main.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import business.usr.main.service.UsrMainService;
import business.usr.main.service.UsrMainVO;
import common.base.BaseController;

/**
 * [컨트롤러클래스] - 개발탬플릿(개발자 표준 가이드용)
 *
 * @class   : SampleController
 * @author  : ntarget
 * @since   : 2021.02.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class UsrMainController extends BaseController {

    @Resource(name="UsrMainService")
    protected UsrMainService mainService;

    @RequestMapping("/usr/main/main.do")
    public String main(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model)  {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/main";
    }
    
    @RequestMapping("/usr/cmm/sitemap.do")
    public String sitemap(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/cmm/sitemap";
    }
    
    @RequestMapping("/usr/cmm/info.do")
    public String info(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/cmm/info";
    }
    
    @RequestMapping("/usr/cmm/policy.do")
    public String policy(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/cmm/policy";
    }
    
    @RequestMapping("/usr/cmm/mail.do")
    public String mail(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/cmm/mail";
    }
    @RequestMapping("/usr/cmm/video.do")
    public String video(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/cmm/video";
    }
    
    @RequestMapping("/usr/info/openReliefInfo.do")
    public String openReliefInfo(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/info/openReliefInfo";
    }
    
    @RequestMapping("/usr/info/openProcessRelief.do")
    public String openProcessRelief(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/info/openProcessRelief";
    }
    
    @RequestMapping("/usr/info/openProcessLwst.do")
    public String openProcessLwst(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/info/openProcessLwst";
    }
    
    @RequestMapping("/usr/info/openInsurance.do")
    public String openInsurance(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {

    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/info/openInsurance";
    }
    @RequestMapping("/usr/info/openBiocideInfo.do")
    public String openBiocideInfo(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {
    	
    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/info/openBiocideInfo";
    }
    	 @RequestMapping("/usr/info/openBiocideProduct.do")
    public String openBiocideProduct(HttpServletRequest request
    		, @ModelAttribute UsrMainVO mainVO
    		, ModelMap model) {
    	
    	Map paramMap = getParameterMap(request, true);
    	
        return "usr/info/openBiocideProduct";
    }
    
}

