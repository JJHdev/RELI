package business.usr.cmit.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.cmit.service.CmitMngService;
import business.com.cmit.service.CmitMngVO;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 온라인위원회 Controller
 * 
 * @class   : UsrCmitController
 * @author  : LSH
 * @since   : 2023.10.19
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class UsrCmitController extends BaseController {

    @Resource(name="CmitMngService")
    protected CmitMngService cmitMngService;

	// 사용자정보 Service
	@Resource(name = "UserInfoService")
	protected UserInfoService userInfoService;
    
	/**
	 * 온라인위원회 - 나의정보 화면
	 */
	@RequestMapping("/usr/cmit/openMypage.do")
	public String openMypage(HttpServletRequest request,
			@ModelAttribute UserInfoVO userInfoVO,
			ModelMap model)
			throws Exception {

		setGlobalSession(userInfoVO);
		String userNo = userInfoVO.getUserInfo().getUserNo();
		model.addAttribute("model", userInfoService.openMypage(userNo));
		return "usr/cmit/openMypage";
	}
    
	/**
	 * 온라인위원회 - 나의정보 수정처리
	 */
	@RequestMapping("/usr/cmit/saveMypage.do")
	@ResponseBody
	public Map saveMypage(@ModelAttribute UserInfoVO userInfoVO) throws Exception {

		setGlobalSession(userInfoVO);

		String userNo = userInfoVO.getUserInfo().getUserNo();

		if (!CommUtils.isEqual(userInfoVO.getUserNo(),userNo)) {
			return getFailure("입력하신 회원정보가 일치하지 않습니다.");
		}

        // 수정 처리
		userInfoService.updateMyCmit(userInfoVO);

		return getSuccess();
	}
	
    /**
     * 온라인위원회 - 위원회 현황 화면 오픈
     */
    @RequestMapping("/usr/cmit/openMyCmit.do")
    public String openMyCmit(HttpServletRequest request
	        , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitMngVO);
        model.addAttribute("model", cmitMngVO);
    	
        return "usr/cmit/openMyCmit";
    }

    /**
     * 온라인위원회 - 나의 온라인 위원회 목록JSON 반환
     */
    @RequestMapping("/usr/cmit/getListMyCmit.do")
    @ResponseBody
    public Map getListMyCmit(HttpServletRequest request
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
     * 온라인위원회 - 위원회 상세조회JSON 반환
     */
    @RequestMapping("/usr/cmit/getMyCmit.do")
    @ResponseBody
    public Map getMyCmit(HttpServletRequest request
            , @ModelAttribute CmitMngVO cmitMngVO
			, ModelMap model) throws Exception {

    	CmitMngVO obj = cmitMngService.viewCmitDmge(cmitMngVO);
        return getSuccess(obj);
    }
}
