package business.usr.support.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.support.service.LwstService;
import business.com.support.service.LwstVO;
import common.base.BaseController;

/**
 * [컨트롤러클래스] - 취약계층소송지원 Controller
 * 
 * @class   : UsrLwstController
 * @author  : 한금주
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class UsrLwstController extends BaseController {

    @Resource(name="LwstService")
    protected LwstService lwstService;
    
    /**
     * 취약계층소송지원 신청
     */
    @RequestMapping("/usr/support/openLwst.do")
    public String openLwst(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute LwstVO lwstVO) throws Exception {
				
        setGlobalSession(lwstVO);
        String aplcntNo = lwstVO.getUserInfo().getUserNo();
        // -------------------- Default Setting End -----------------------//
        model.addAttribute("model", lwstService.openLwst(aplcntNo));
        return "usr/support/openLwst";
    }
    
    /**
     * 취약계층소송지원 신청 저장
     */
    @RequestMapping("/usr/support/saveUserLwst.do")
    @ResponseBody
    public Map saveUserLwst(HttpServletRequest request
            , @RequestBody LwstVO lwstVO) throws Exception {
				
        setGlobalSession(lwstVO);
        lwstVO.setGsUserNo(lwstVO.getUserInfo().getUserNo());
        int result = lwstService.saveUserLwst(lwstVO);
        if (result > 0) {
			return getSuccess();
		}
		return getFailure();
    }
}
