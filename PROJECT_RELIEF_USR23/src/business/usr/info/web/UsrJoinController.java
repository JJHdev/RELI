package business.usr.info.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.sys.user.service.UserInfoVO;
import business.usr.info.service.UsrInfoService;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 회원가입
 *
 * @class : UsrJoinController
 * @author : gjhan
 * @since : 2021.10.12
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */

@Controller
@SuppressWarnings("rawtypes")
public class UsrJoinController extends BaseController {

	@Autowired
	private UsrInfoService usrInfoService;

	/**
	 * 회원가입 - 이용약관 화면 오픈
	 */
	@RequestMapping("/usr/cmm/openAgree.do")
	public String openAgree(HttpServletRequest request, 
			@ModelAttribute UserInfoVO userInfoVO) throws Exception {
		return "usr/cmm/openAgree";
	}

	@RequestMapping("/usr/cmm/joinAgree.do")
	public String joinAgree(HttpServletRequest request, 
			@ModelAttribute UserInfoVO userInfoVO
		   , ModelMap model) throws Exception {
		  model.addAttribute("model", userInfoVO);
		return "usr/cmm/joinAgree";
	}
	
	/*
	@RequestMapping("/usr/cmm/perInfomation.do")
	public String perInfomation(HttpServletRequest request, @ModelAttribute UserInfoVO userInfoVO
		   , ModelMap model) throws Exception {
		
		model.addAttribute("model", userInfoVO);
		
		return "usr/pinfo/popupPerInfo";
	}
	*/
	
	@RequestMapping("/usr/pinfo/popupPerinfo.do")
	public String popupPerinfo(HttpServletRequest request, @ModelAttribute UserInfoVO userInfoVO
			, ModelMap model) throws Exception {
		
		model.addAttribute("model", userInfoVO);
		
		Map paramMap = getParameterMap(request, true);
		
		String pageinfo = CommUtils.nvlTrim((String)paramMap.get("pageinfo"));
		
		return "usr/pinfo/popupPerInfo"+pageinfo;
	}
	
	/**
	 * 회원가입 - 회원가입 정보 입력
	 * @return 
	 */
	@RequestMapping("/usr/cmm/JoinUserAgree.do")
    @ResponseBody
	public Map JoinUserAgree(HttpServletRequest request, 
			@ModelAttribute UserInfoVO userInfoVO,
			@RequestParam("userId") String userId) throws Exception {
		
		String checkJoinUser = userInfoVO.getUserInfo().getUserId();
		
		
		// 세션사용자정보를 정의
		setGlobalSession(userInfoVO);
		// 세션 사용자정보의 사용자번호를 모델객체에 맵핑
		userInfoVO.setGsUserNo(userInfoVO.getUserInfo().getUserNo());
		// 사용자의 기본권한을 ROLE_AUTH_USR(회원사용자)로 정의
		userInfoVO.setRoleId(CommConst.ROLE_USER);
		
		if(!CommUtils.isEmpty(checkJoinUser)) {
    		Map paramMap = new HashMap();
            paramMap.put("userId" , userId);
            if(usrInfoService.checkJoinUser(paramMap)) {
                return getFailure(message.getMessage("비정상적인 접근입니다."));
            }
		}
		// 회원가입처리
    	int result = usrInfoService.JoinUserAgree(userInfoVO);
    	
    	if (result > 0) {
        	// 성공결과 반환
    		return getSuccess();
    	}
    	// 실패결과 반환
        return getFailure();
	}

	/**
	 * 사용자아이디 중복체크
	 */
	@RequestMapping("/usr/cmm/checkDuplicate.do")
	@ResponseBody
	public boolean checkDuplicate(@RequestParam("userId") String userId) throws Exception {
		return (usrInfoService.existUserId(userId) == false);
	}
}
