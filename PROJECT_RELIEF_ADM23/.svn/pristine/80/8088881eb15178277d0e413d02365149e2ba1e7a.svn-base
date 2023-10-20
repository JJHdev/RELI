package business.sys.sms.web;

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
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - SMS이력 관리 Controller
 * 
 * @class   : SmsController
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class SmsController extends BaseController {

    @Resource(name="SmsService")
    protected SmsService smsService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * SMS이력 관리 화면
     */
    @RequestMapping("/sys/sms/listSms.do")
    public String listSms(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute SmsVO smsVO) throws Exception {
				
        setGlobalSession(smsVO);
        // -------------------- Default Setting End -----------------------//
        
        // 발송번호 기본값 정의
        smsVO.setTrnsterNo(CommUtils.formatPhone(CommConst.SMS_TRANSFER_NO));

        model.addAttribute("model", smsVO);
    	
        return "sys/sms/listSms";
    }

    /**
     * SMS이력 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/sys/sms/getListSms.do")
    @ResponseBody
    public Map getListSms(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute SmsVO smsVO
            , ModelMap model) throws Exception {

        setGlobalSession(smsVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = smsService.listSms(smsVO, page, size);
        }
        else {
        	list = smsService.listSms(smsVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * SMS이력 조회JSON 반환
     */
    @RequestMapping("/sys/sms/getSms.do")
    @ResponseBody
    public Map getSms(HttpServletRequest request
            , @ModelAttribute SmsVO smsVO
			, ModelMap model) throws Exception {

        SmsVO obj = smsService.viewSms(smsVO);
        return getSuccess(obj);
    }

    /**
     * SMS이력 저장처리 (다중삭제)
     */
    @RequestMapping("/sys/sms/saveSms.do")
    @ResponseBody
    public Map saveSms(HttpServletRequest request
			, @RequestBody SmsVO smsVO) throws Exception {
    	
    	// 처리모드(삭제)
    	smsVO.setMode(CommConst.DELETE);
        // SMS이력를 다중삭제한다.
    	String result = smsService.saveSms(smsVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 2021.09.24 LSH
     * SMS 전송처리 (JSON 데이터로 받기)
     */
    @RequestMapping("/sys/sms/sendSms.do")
    @ResponseBody
    public Map sendSms(HttpServletRequest request
			, @RequestBody SmsVO smsVO) throws Exception {
    	// 처리모드(등록)
    	smsVO.setMode(CommConst.INSERT);
	    // SMS 발송처리 (시스템)
    	smsService.saveSms(smsVO);
    	// 성공결과 반환
        return getSuccess();
    }

    /**
     * 2021.09.24 LSH
     * SMS 일괄전송 대상 목록을 조회한다.
     * - 피해구제 신청자 목록
     */
    @RequestMapping("/sys/sms/getListTarget.do")
    @ResponseBody
    public Map getListTarget(HttpServletRequest request
            , @ModelAttribute SmsVO smsVO) throws Exception {

    	Map paramMap = getParameterMap(request, true);
    	
        setGlobalSession(smsVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(smsVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	smsVO.setSrchAplyKnd(
       			CommUtils.mergeString(smsVO.getAplyKndList(),"|")
        	);
        }
        
        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = smsService.listSmsTarget(smsVO, page, size);
        }
        else {
        	list = smsService.listSmsTarget(smsVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 2022.01.19 LSH
     * SMS 상세보기 팝업
     */
    @RequestMapping("/sys/sms/modalSms.do")
    public String modalSms(HttpServletRequest request
    		, @RequestParam String sn
    		, ModelMap model) throws Exception {
    	
    	SmsVO smsVO = SmsVO.builder().sn(sn).build();
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(smsVO);
        
        SmsVO result = smsService.viewSms(smsVO);

        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 관리자만 접근 가능
    	if (!CommConst.isAdminRole(smsVO.getUserInfo().getRoleId()))
        	throw new EgovBizException(message.getMessage("error.comm.notAccess"));

        model.addAttribute("form", result);
        
        return "sys/sms/modalSms";
    }

    /**
     * 2022.01.19 LSH SMS 로고이미지 URL링크 보기
     */
    @RequestMapping(value="/sys/sms/linkSmsLogo.do")
    @ResponseBody
    public HttpEntity<byte[]> linkSmsLogo(HttpServletRequest request
    		, HttpServletResponse response) throws Exception {
    	
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(FileDirectory.SMSFILE.getRealPath())
        		.saveName(CommConst.SMS_INTRO_IMAGE)
        		.build();
        
        // 미디어 URL 링크용 HttpEntity 반환
    	return fileManager.linkFile(f , request, response);
    }
        
}
