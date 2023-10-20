package business.sys.user.web;

import java.security.NoSuchAlgorithmException;
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
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 사용자정보 관리 Controller
 *
 * @class : UserInfoController
 * @author : LSH
 * @since : 2021.09.09
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 -------- -------- ---------------------------
 *
 */
@Controller
@SuppressWarnings({ "all" })
public class UserInfoController extends BaseController {

	@Resource(name = "UserInfoService")
	protected UserInfoService userInfoService;
	
	@Resource(name="SmsService")
    protected SmsService smsService;
	
	/**
	 * 사용자정보 관리 화면
	 */
	@RequestMapping("/sys/user/listUserInfo.do")
	public String listUserInfo(HttpServletRequest request, @RequestParam Map paramMap, ModelMap model)
			throws Exception {

		setGlobalSession(paramMap);
		// -------------------- Default Setting End -----------------------//
		UserInfoVO userInfoVO = UserInfoVO.builder().gsUserNo((String) paramMap.get("gsUserNo"))
				.gsRoleId((String) paramMap.get("gsRoleId")).build();
		model.addAttribute("model", userInfoVO);

		return "sys/user/listUserInfo";
	}

	/**
	 * 사용자정보 목록JSON 반환 (EasyUI GRID)
	 */
	@RequestMapping("/sys/user/getListUserInfo.do")
	@ResponseBody
	public Map getListUserInfo(HttpServletRequest request, @RequestParam Map<String, String> reqMap, ModelMap model)
			throws Exception {

		setGlobalSession(reqMap);
		// -------------------- Default Setting End -----------------------//

		List list = null;
		if (reqMap.containsKey("page")) {
			int page = CommUtils.getInt(reqMap, "page");
			int size = CommUtils.getInt(reqMap, "rows");
			list = userInfoService.listUserInfo(reqMap, page, size);
		} else {
			list = userInfoService.listUserInfo(reqMap);
		}
		// Easy UI GRID용 결과값 반환
		return getEasyUIResult(list);
	}

	/**
	 * 사용자정보 조회JSON 반환
	 */
	@RequestMapping("/sys/user/getUserInfo.do")
	@ResponseBody
	public Map getUserInfo(HttpServletRequest request, @ModelAttribute UserInfoVO userInfoVO, ModelMap model)
			throws Exception {

		UserInfoVO obj = userInfoService.viewUserInfo(userInfoVO.getUserNo());
		return getSuccess(obj);
	}

	/**
	 * 사용자정보 저장처리 (등록,수정,삭제) mode 값에 따라 분기
	 */
	@RequestMapping("/sys/user/saveUserInfo.do")
	@ResponseBody
	public Map saveUserInfo(HttpServletRequest request, @RequestParam Map<String, String> reqMap,
			@ModelAttribute UserInfoVO userInfoVO) throws Exception {

		setGlobalSession(reqMap);

		if (reqMap.get("gsUserNo") != null)
			userInfoVO.setGsUserNo((String) reqMap.get("gsUserNo"));

		// 사용자정보를 저장한다.
		String result = userInfoService.saveUserInfo(userInfoVO);
		// 성공결과 반환
		return getSuccess("Message", result);
	}

	/**
	 * 사용자아이디 중복체크
	 */
	@RequestMapping("/sys/user/checkDuplicate.do")
	@ResponseBody
	public boolean checkDuplicate(@RequestParam("userId") String userId) throws Exception {
		return (userInfoService.existUserId(userId) == false);
	}

	/**
	 * 2021.12.30 CSLEE 비밀번호 변경처리 사용자 비밀번호 변경 화면
	 */
	@RequestMapping("/sys/user/viewChgPwd.do")
	public String viewChgPwd(HttpServletRequest request, @RequestParam Map paramMap, ModelMap model) throws Exception {

		setGlobalSession(paramMap);
		// -------------------- Default Setting End -----------------------//
		model.addAttribute("model", paramMap);

		return "sys/user/viewChgPwd";
	}

	/**
	 * 2021.12.30 CSLEE 비밀번호 변경처리 세션으로 본인을 체크함
	 */
	@RequestMapping("/sys/user/savePswdSelf.do")
	@ResponseBody
	public Map savePswdSelf(HttpServletRequest request, @ModelAttribute UserInfoVO userInfoVO) throws Exception {

		setGlobalSession(userInfoVO);
		// -------------------- Default Setting End -----------------------//

		String userNo = userInfoVO.getUserInfo().getUserNo();
		String pswd = userInfoVO.getPswd();

		// 세션이 없을 때
		if (CommUtils.isEmpty(userNo)) {
			// 해당 데이터에 접근권한이 없습니다.
			return getFailure(message.getMessage("error.comm.notAccess"));
		}

		Map paramMap = new HashMap();
		paramMap.put("userNo", userNo);
		paramMap.put("pswd", pswd);

		if (userInfoService.checkUserPswdDupl(paramMap)) {
			// 기존과 동일한 비밀번호로 변경할 수 없습니다.
			return getFailure(message.getMessage("error.user.duplPassword"));
		}

		// 비밀번호 변경처리
		userInfoService.updtUserPswd(paramMap);

		// 비밀번호 변경이 완료되었습니다.
		return getSuccess("Message", message.getMessage("success.user.changePswd"));
	}

	/**
	 * 2021.12.30 CSLEE 비밀번호 변경을 '다음에' 하기로 처리 세션으로 본인을 체크함
	 */
	@RequestMapping("/sys/user/savePswdSelfNext.do")
	@ResponseBody
	public Map savePswdSelfNext(HttpServletRequest request, @ModelAttribute UserInfoVO userInfoVO) throws Exception {

		setGlobalSession(userInfoVO);
		// -------------------- Default Setting End -----------------------//

		String userNo = userInfoVO.getUserInfo().getUserNo();

		// 세션이 없을 때
		if (CommUtils.isEmpty(userNo)) {
			// 해당 데이터에 접근권한이 없습니다.
			return getFailure(message.getMessage("error.comm.notAccess"));
		}

		Map paramMap = new HashMap();
		paramMap.put("userNo", userNo);
		// 비밀번호 변경처리
		userInfoService.updtUserPswdNext(paramMap);

		// 비밀번호 변경이 완료되었습니다.
		return getSuccess("Message", message.getMessage("success.user.changePswd"));
	}

	/**
	 * 2023.01.03 JDY 비밀번호 초기화 시 임시비밀번호 전송
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping("/sys/user/restPswd.do")
	@ResponseBody
	public Map restUserPswd(HttpServletRequest request, @RequestBody UserInfoVO userInfoVO, ModelMap model
			) throws Exception {

		Map paramMap = getParameterMap(request, true);

		// 비밀번호 평문
		userInfoVO.setPswd(CommUtils.getRamdomPassword(8));
		
		String mbtelNo	= CommUtils.nvlTrim(userInfoVO.getMbtelNo());
		String userNm	= CommUtils.nvlTrim(userInfoVO.getUserNm());
		String newPswd	= CommUtils.nvlTrim(userInfoVO.getPswd());
		
		// 비밀번호 초기화값 DB Update
		paramMap.put("userNo", userInfoVO.getUserNo());
		paramMap.put("pswd"  , newPswd);
		int result = userInfoService.updtUserPswd(paramMap);
		
		// 비밀번호 초기화값 DB Update 성공시 SMS전송
		if(result > 0) {
			SmsVO smsVO = SmsVO.builder()
				.rcvrNo(mbtelNo)							  // 수신번호
				.rcvrNm(userNm)							  // 수신자명
				.trsmCn("[환경산업기술원] 임시비밀번호는 "+ newPswd +"입니다.") // 발송내용
				.trnsterNo(CommUtils.formatPhone(CommConst.SMS_TRANSFER_NO)) // 발송번호 기본값 정의
				.smsSeCd(CommConst.SMS_USER_PSW)		  	  // 전송구분 (SS05 : 비밀번호전송)
				.trnsterNm(CommConst.SMS_TRANSFER_NM) 		  // 발송자명 (관리자)
				.build();
			smsVO.setMode(CommConst.INSERT); // 처리모드(등록)
			
		    // SMS 발송처리 (시스템)
	    	smsService.sendSms(smsVO);
		}
		return getSuccess(); 
	}
}
	
