package business.bio.relief.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.bio.relief.service.BioReliefService;
import business.bio.relief.service.BioReliefVO;
import business.bio.relief.service.BioReliefValidator;
import business.com.CommConst;
import business.com.file.service.PapeMngService;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileManager;
import common.user.UserInfo;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * [컨트롤러클래스] - 살생물제품 구제급여 관리 Controller
 * 
 * @class   : BioReliefController
 * @author  : LSH
 * @since   : 2023.01.16
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class BioReliefController extends BaseController {

    @Resource(name="BioReliefService")
    protected BioReliefService reliefService;

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngSerivce;

    @Resource(name="UserInfoService")
    protected UserInfoService userInfoService;

    // 데이터 검증용 VALIDATOR
    @Autowired 
    private BioReliefValidator reliefValidator;

    @Resource(name="fileManager")
    FileManager fileManager;
    
    /**
     * 2021.10.05 LSH
     * 구제급여 신청 1단계 화면
     * - 신청 구분 선택 
     */
    @RequestMapping("/usr/relief/openBioRelief.do")
    public String openBioRelief(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute BioReliefVO bioReliefVO) throws Exception {
				
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", bioReliefVO);
    	
        return "usr/relief/openBioRelief";
    }
    
    /**
     * 2021.10.05 LSH
     * 구제급여 신청 2단계 화면
     * - 신청정보 입력 
     * 
     * 2022.12.01 LSH
     * 관리자대행 추가
     */
    @RequestMapping("/usr/relief/openBioReliefForm.do")
    public String openBioReliefForm(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute BioReliefVO bioReliefVO) throws Exception {
				
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//

        // 서류업무구분 정의
        bioReliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_BIO);
        // 세션사용자번호 정의
        bioReliefVO.setGsUserNo(bioReliefVO.getUserInfo().getUserNo());
        
        // 2022.12.01 LSH 관리자대행 처리 추가
    	if (CommConst.isAdminRole(bioReliefVO.getUserInfo().getRoleId())) {
            // 2022.12.01 관리자여부
            bioReliefVO.setAdmYn   (CommConst.YES);
            // 임시저장한 내용이 있는지 확인
            bioReliefVO.setRgtrNo  (bioReliefVO.getGsUserNo());  // 작성자번호
            bioReliefVO.setPrgreStusCd(CommConst.PRGRE_SAVE); // 임시저장상태
            
            // 관리자대행으로 임시저장한 신청정보 KEY 조회
            BioReliefVO keyObj = reliefService.viewBioReliefAplyLastAdmn(bioReliefVO);
            if (keyObj != null) {
            	bioReliefVO.setAplyNo  (keyObj.getAplyNo());
            	bioReliefVO.setMode    (CommConst.UPDATE);
            }
            else {
            	bioReliefVO.setMode    (CommConst.INSERT);
            }
    	}
    	else {
            // 2022.12.01 관리자여부
            bioReliefVO.setAdmYn   (CommConst.NO);
            // 임시저장한 내용이 있는지 확인
            bioReliefVO.setAplcntNo(bioReliefVO.getGsUserNo());  // 신청인번호
            bioReliefVO.setRgtrNo  (bioReliefVO.getGsUserNo());  // 작성자번호
            bioReliefVO.setPrgreStusCd(CommConst.PRGRE_SAVE); // 임시저장상태
            
            // 임시저장한 신청정보 KEY 조회
            BioReliefVO keyObj = reliefService.viewBioReliefAplyLast(bioReliefVO);
            
            if (keyObj != null) {
            	bioReliefVO.setAplyNo  (keyObj.getAplyNo());
            	bioReliefVO.setMode    (CommConst.UPDATE);
            }
            else {
            	bioReliefVO.setMode    (CommConst.INSERT);
            }
    	}
        
        // -------------------- Default Setting End -----------------------//
        model.addAttribute("model", bioReliefVO);
    	
        return "usr/relief/openBioReliefForm";
    }

    /**
     * 2021.10.11 LSH
     * 구제급여 신청 임시저장/제출처리
     */
    @RequestMapping("/usr/relief/saveBioRelief.do")
    @ResponseBody
    public Map saveBioRelief(
    		HttpServletRequest request, 
    		@RequestBody BioReliefVO bioReliefVO,
    		BindingResult bindingResult
    	) throws Exception {
    	
        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//
    	
    	// 저장항목에 맞게 데이터 재정의
    	bioReliefVO.rebuildProperties();

		// [검증용] 신청양식 서류목록 조회
		Map papeParams = new HashMap();
		papeParams.put("papeDtySeCd", bioReliefVO.getPapeDtySeCd());
		papeParams.put("aplySeCd"   , bioReliefVO.getAplySeCd());
		bioReliefVO.setPapeList(papeMngSerivce.getListPape(papeParams));
    	
        // 저장할 입력값 검증
    	reliefValidator.validate(bioReliefVO, bindingResult);
    	
    	// 입력값 검증시 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	logger.info("BioRelief Validate Error...", bindingResult.getAllErrors());
        	return getFailure(bindingResult);
        }
        // 저장처리
    	String result = reliefService.saveBioRelief(bioReliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.12.08 LSH
     * 구제급여 신청목록JSON 반환
     */
    @RequestMapping("/usr/relief/getListBioRelief.do")
    @ResponseBody
    public Map getListBioRelief(HttpServletRequest request
            , @ModelAttribute BioReliefVO bioReliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = null;
        
		// 식별아이디 사용자인 경우 식별ID기준으로 조회한다.
		if (bioReliefVO.getUserInfo().isIdntfc()) {
			bioReliefVO.setIdntfcId(bioReliefVO.getUserInfo().getUserId());
			list = reliefService.listBioReliefMypage(bioReliefVO);
		}
		// 로그인회원 사용자인 경우 신청자번호기준으로 조회한다.
		else {
			bioReliefVO.setAplcntNo(bioReliefVO.getUserInfo().getUserNo());
			list = reliefService.listBioReliefMypage(bioReliefVO);
		}
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.10.14 LSH
     * 구제급여 신청정보 가져오기
     */
    @RequestMapping("/usr/relief/getBioRelief.do")
    @ResponseBody
    public Map getBioRelief(
    		HttpServletRequest request, 
            @ModelAttribute BioReliefVO bioReliefVO
    	) throws Exception {

        setGlobalSession(bioReliefVO);
        // -------------------- Default Setting End -----------------------//

        // 2021.11.20 TODO. 신청상세 조회시 사업차수 기본값 맵핑
        if (CommUtils.isEmpty(bioReliefVO.getBizOder()))
        	bioReliefVO.setBizOder(CommConst.BIZ_ODER_RELIEF);

        // 구제급여 신청정보 상세조회 (피해자정보 포함)
        BioReliefVO result = reliefService.viewBioReliefWidhIdntfc(bioReliefVO);
        if (result != null) {
	        // 세션사용자번호 정의
		    result.setGsUserNo(bioReliefVO.getUserInfo().getUserNo());
        }
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 2022.01.19 접근가능여부 체크 (관리자만 접근가능)
     */
    private boolean _accessible(UserInfo user, String rgtrNo) {
    	
    	if (user == null)
    		return false;

    	// 관리자인 경우 ACCESS OK
    	if (CommConst.isAdminRole(user.getRoleId())) {
    		return true;
    	}
    	// 회원사용자인 경우 본인인지 체크
    	else if (CommConst.isUserRole(user.getRoleId())) {
    		// 파일 생성자이면 ACCESS OK
    		if (CommUtils.isEqual(user.getUserNo(), rgtrNo))
    			return true;
    	}
    	// 그외엔 NOT ACCESS
    	else {}

    	return false;
    }

    /**
     * 2022.12.01 LSH
     * 관리자대행 기능 추가
     * - 이전 CommController의 getUserInfo 사용되던 부분을
     *   별도로 신청인정보 가져오기로 처리변경함.
     * - 일반사용자의 경우 로그인 회원정보 가져오기
     * - 관리자인 경우 빈값으로 반환
     */
    @RequestMapping("/usr/relief/getBioUserInfo.do")
    @ResponseBody
    public Map getBioUserInfo(HttpServletRequest request
            , ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        // -------------------- Default Setting End -----------------------//
        UserInfoVO obj = null;
        String userNo = (String)paramMap.get("gsUserNo");
        String roleId = (String)paramMap.get("gsRoleId");
        
    	// 관리자인 경우 ACCESS OK
    	if (CommConst.isAdminRole(roleId)) {
    	}
    	// 회원사용자인 경우 본인인지 체크
    	else if (!CommUtils.isEmpty(userNo)) {
    		obj = userInfoService.viewUserInfo(userNo);
    	}
    	return getSuccess(obj);
    }

    /**
     * 전자서명 저장처리
     */
    @RequestMapping("/usr/relief/saveBioSignature.do")
    @ResponseBody
    public Map saveBioSignature(HttpServletRequest request
    		, @RequestBody Map<String,Object> reqMap) throws Exception {
    	String sign  = (String)reqMap.get("sign");
    	String[] arr = CommUtils.split(sign, ",");
    	String signFile = null;
    	if (arr.length > 1) {
    		signFile = "sign"+System.currentTimeMillis()+".png";
    		// 전자서명경로에 파일 저장
    		FileUtils.saveSignFile(FileDirectory.BIOSIGN, signFile, arr[1]);
    	}
    	// 성공결과 반환
        return getSuccess("FileName", signFile);
    }
    
}
