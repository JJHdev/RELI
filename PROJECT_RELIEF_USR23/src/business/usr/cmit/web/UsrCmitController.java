package business.usr.cmit.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.cmit.service.CmitMngService;
import business.com.cmit.service.CmitMngVO;
import business.com.cmit.service.MfcmmService;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

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

    @Resource(name="MfcmmService")
    protected MfcmmService mfcmmService;

    @Resource(name="fileManager")
    FileManager fileManager;
    
	/**
	 * 2023.10.23 LSH
	 * 온라인위원회 - 나의정보 화면
	 */
	@RequestMapping("/usr/cmit/openMypage.do")
	public String openMypage(@ModelAttribute CmitMngVO cmitMngVO,
			ModelMap model)
			throws Exception {

		setGlobalSessionVO(cmitMngVO);
		
		model.addAttribute("model", mfcmmService.viewMfcmm(cmitMngVO));
		return "usr/cmit/openMypage";
	}
    
	/**
	 * 2023.10.23 LSH
	 * 온라인위원회 - 나의정보 수정처리
	 */
	@RequestMapping("/usr/cmit/saveMypage.do")
	@ResponseBody
	public Map saveMypage(@ModelAttribute CmitMngVO cmitMngVO) throws Exception {

		setGlobalSessionVO(cmitMngVO);
        // 수정 처리
		if (mfcmmService.updtMfcmm(cmitMngVO) > 0)
			return getSuccess();
		return getFailure();
	}
	
    /**
	 * 2023.10.24 LSH
     * 온라인위원회 - 위원회 현황 화면 오픈
     */
    @RequestMapping("/usr/cmit/openMyCmit.do")
    public String openMyCmit(@ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

		setGlobalSessionVO(cmitMngVO);
		model.addAttribute("model", cmitMngVO);
    	
        return "usr/cmit/openMyCmit";
    }
	
    /**
     * 온라인위원회 - 위원회 현황 세부정보 화면 오픈
     */
    @RequestMapping("/usr/cmit/viewMyCmit.do")
    public String viewMyCmit(@ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

		setGlobalSessionVO(cmitMngVO);
        model.addAttribute("model", cmitMngVO);
    	
        return "usr/cmit/viewMyCmit";
    }

    /**
	 * 2023.10.24 LSH
     * 온라인위원회 - 나의 온라인 위원회 목록JSON 반환
     */
    @RequestMapping("/usr/cmit/getListMyCmit.do")
    @ResponseBody
    public Map getListMyCmit(@ModelAttribute CmitMngVO cmitMngVO
            , HttpServletRequest request
            , ModelMap model) throws Exception {

    	Map paramMap = getParameterMap(request, true);

		setGlobalSessionVO(cmitMngVO);

        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = cmitMngService.listCmitDmge(cmitMngVO, page, size);
        }
        else {
        	list = cmitMngService.listCmitDmge(cmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getPaginatedResult(list);
    }

    /**
	 * 2023.10.25 LSH
     * 온라인위원회 - 위원회 상세조회JSON 반환
     */
    @RequestMapping("/usr/cmit/getMyCmit.do")
    @ResponseBody
    public Map getMyCmit(@ModelAttribute CmitMngVO cmitMngVO
			, ModelMap model) throws Exception {

		setGlobalSessionVO(cmitMngVO);

		CmitMngVO obj = cmitMngService.viewCmitDmge(cmitMngVO);
		// 위원번호정의
		obj.setMfcmmNo(cmitMngVO.getMfcmmNo());
		// 임기번호정의
		obj.setTenureNo(cmitMngVO.getTenureNo());

    	if (obj != null) {
    		// 안건목록 조회
    		obj.setAgndList(
    			cmitMngService.listCmitDmgeAgnd(obj)
    		);
    	}
        return getSuccess(obj);
    }
    
    /**
	 * 2023.10.26 LSH
     * 온라인위원회 - 위원회 의견서 작성 팝업 오픈
     */
    @RequestMapping("/usr/cmit/modalCmitOpinion.do")
    public String modalCmitOpinion(@ModelAttribute CmitMngVO cmitMngVO
    		, ModelMap model) throws Exception {
    	
		setGlobalSessionVO(cmitMngVO);
		
		CmitMngVO result = cmitMngService.viewCmitOpinion(cmitMngVO);
		// 조회모드인 경우
		if (CommConst.VIEW.equals(cmitMngVO.getMode()))
			result.setMode(cmitMngVO.getMode());
		
		// 의견서 조회
		model.addAttribute("form", result);
        
        return "usr/cmit/modalCmitOpinion";
    }
    
    /**
	 * 2023.10.26 LSH
     * 온라인위원회 - 위원회 의결서 서명 팝업 오픈
     */
    @RequestMapping("/usr/cmit/modalCmitDecision.do")
    public String modalCmitDecision(@ModelAttribute CmitMngVO cmitMngVO
    		, ModelMap model) throws Exception {
    	
		setGlobalSessionVO(cmitMngVO);

		CmitMngVO result = cmitMngService.viewCmitDecision(cmitMngVO);
		// 조회모드인 경우
		if (CommConst.VIEW.equals(cmitMngVO.getMode()))
			result.setMode(cmitMngVO.getMode());
		
		// 의결서 조회
		model.addAttribute("form", result);
        
        return "usr/cmit/modalCmitDecision";
    }
    
    /**
	 * 2023.10.26 LSH
     * 온라인위원회 - 위원회 수당지 작성 팝업 오픈
     */
    @RequestMapping("/usr/cmit/modalCmitPension.do")
    public String modalCmitPension(@ModelAttribute CmitMngVO cmitMngVO
    		, ModelMap model) throws Exception {
    	
		setGlobalSessionVO(cmitMngVO);

		CmitMngVO result = cmitMngService.viewCmitPension(cmitMngVO);
		// 조회모드인 경우
		if (CommConst.VIEW.equals(cmitMngVO.getMode()))
			result.setMode(cmitMngVO.getMode());
		
		// 수당지 조회
		model.addAttribute("form", result);
        
        return "usr/cmit/modalCmitPension";
    }

    /**
     * 2023.10.31 LSH
     * 온라인위원회 - 위원회 의견서 임시저장/제출처리
     */
    @RequestMapping("/usr/cmit/saveCmitOpinion.do")
    @ResponseBody
    public Map saveCmitOpinion(@RequestBody CmitMngVO cmitMngVO) throws Exception {
    	
    	setGlobalSessionVO(cmitMngVO);
    	// 저장모드 정의
    	cmitMngVO.setSaveMode(CommConst.CMIT_OPINION);
    	// 저장처리
    	return saveItem(cmitMngVO);
    }

    /**
     * 2023.10.31 LSH
     * 온라인위원회 - 위원회 의결서 임시저장/제출처리
     */
    @RequestMapping("/usr/cmit/saveCmitDecision.do")
    @ResponseBody
    public Map saveCmitDecision(@RequestBody CmitMngVO cmitMngVO) throws Exception {
    	
    	setGlobalSessionVO(cmitMngVO);
    	// 저장모드 정의
    	cmitMngVO.setSaveMode(CommConst.CMIT_DECISION);
    	// 저장처리
    	return saveItem(cmitMngVO);
    }

    /**
     * 2023.10.31 LSH
     * 온라인위원회 - 위원회 의견서 임시저장/제출처리
     */
    @RequestMapping("/usr/cmit/saveCmitPension.do")
    @ResponseBody
    public Map saveCmitPension(@RequestBody CmitMngVO cmitMngVO) throws Exception {
    	
    	setGlobalSessionVO(cmitMngVO);
    	// 저장모드 정의
    	cmitMngVO.setSaveMode(CommConst.CMIT_PENSION);
    	// 저장처리
    	return saveItem(cmitMngVO);
    }
    
    // 위원회 의견서,의결서,수당지 저장처리
    private Map saveItem(CmitMngVO cmitMngVO) throws Exception {
    	// 저장항목에 맞게 데이터 재정의
    	cmitMngVO.rebuildProperties();
        // 저장처리
    	String result = cmitMngService.saveCmitItem(cmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2023.10.31 LSH
     * 전자서명파일 URL링크보기
     * 로그인한 위원의 서명파일 링크를 가져온다.
     */
    @RequestMapping("/usr/cmit/linkSignFile.do")
    @ResponseBody
    public HttpEntity<byte[]> linkSignFile(@ModelAttribute CmitMngVO cmitMngVO
    		, HttpServletRequest request
    		, HttpServletResponse response) throws Exception {

		setGlobalSessionVO(cmitMngVO);

		CmitMngVO mfcmmObj = mfcmmService.viewMfcmm(cmitMngVO);
    	if (mfcmmObj == null)
    		throw new EgovBizException(message.getMessage("error.file.notExist"));
    	if (CommUtils.isEmpty(mfcmmObj.getSignCn()))
    		throw new EgovBizException(message.getMessage("error.file.notExist"));
		
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(FileDirectory.SIGNATURE.getRealPath())
        		.saveName(mfcmmObj.getSignCn())
        		.build();
        // 미디어 URL 링크용 HttpEntity 반환
    	return fileManager.linkFile(f, request, response);
    }
    

	// 세션사용자정보를 모델객체에 담는다
    private void setGlobalSessionVO(CmitMngVO cmitMngVO) {
		setGlobalSession(cmitMngVO);
		cmitMngVO.setMfcmmNo(cmitMngVO.getUserInfo().getMfcmmNo());
    }
}
