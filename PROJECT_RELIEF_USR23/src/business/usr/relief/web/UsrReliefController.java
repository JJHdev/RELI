package business.usr.relief.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommBizUtils;
import business.com.CommConst;
import business.com.cmm.service.CommService;
import business.com.exmn.service.MnsvyService;
import business.com.exmn.service.MnsvyVO;
import business.com.exmn.service.PrptExmnService;
import business.com.exmn.service.PrptExmnVO;
import business.com.file.service.PapeMngService;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import business.com.relief.service.ReliefValidator;
import business.com.survey.service.SurveyRspnsService;
import business.com.survey.service.SurveyVO;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.user.UserInfo;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 구제급여 관리 Controller
 * 
 * @class   : UsrReliefController
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class UsrReliefController extends BaseController {

    @Resource(name="ReliefService")
    protected ReliefService reliefService;

    @Resource(name="IdntfcService")
    protected IdntfcService idntfcService;

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngSerivce;

    @Resource(name="CommService")
    protected CommService commService;

    @Resource(name="PrptExmnService")
    protected PrptExmnService prptExmnService;

    @Resource(name="MnsvyService")
    protected MnsvyService mnsvyService;

    @Resource(name="SurveyRspnsService")
    protected SurveyRspnsService surveyRspnsService;

    @Resource(name="UserInfoService")
    protected UserInfoService userInfoService;

    // 데이터 검증용 VALIDATOR
    @Autowired 
    private ReliefValidator reliefValidator;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 2021.10.05 LSH
     * 나에게 맞는 피해구제 제도찾기 화면
     */
    @RequestMapping("/usr/relief/openSelfCheck.do")
    public String openSelfCheck(HttpServletRequest request
	        , @RequestParam Map<String,String> reqMap
            , ModelMap model) throws Exception {
				
        setGlobalSession(reqMap);
        // -------------------- Default Setting End -----------------------//
        
        // 단계
        String step = (String)reqMap.get("step");
        if (CommUtils.isEmpty(step))
        	reqMap.put("step", "1");

        model.addAttribute("model", reqMap);
    	
        return "usr/relief/openSelfCheck";
    }
    
    /**
     * 2021.10.05 LSH
     * 구제급여 신청 1단계 화면
     * - 신청 구분 선택 
     */
    @RequestMapping("/usr/relief/openRelief.do")
    public String openRelief(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", reliefVO);
    	
        return "usr/relief/openRelief";
    }
    
    /**
     * 2021.10.05 LSH
     * 구제급여 신청 2단계 화면
     * - 신청정보 입력 
     * 
     * 2022.12.01 LSH
     * 관리자대행 추가
     */
    @RequestMapping("/usr/relief/openReliefForm.do")
    public String openReliefForm(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 서류업무구분 정의
        reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);
        // 세션사용자번호 정의
        reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());
        // 사업차수 기본값 정의
        reliefVO.setBizOder(CommConst.BIZ_ODER_RELIEF);
        // 신청차수 기본값 정의
        reliefVO.setAplyOder(CommConst.APLY_ODER_RELIEF);
        
        // 2022.12.01 LSH 관리자대행 처리 추가
    	if (CommConst.isAdminRole(reliefVO.getUserInfo().getRoleId())) {
            // 2022.12.01 관리자여부
            reliefVO.setAdmYn   (CommConst.YES);
            // 임시저장한 내용이 있는지 확인
            reliefVO.setRgtrNo  (reliefVO.getGsUserNo());  // 작성자번호
            reliefVO.setPrgreStusCd(CommConst.PRGRE_SAVE); // 임시저장상태
            
            // 관리자대행으로 임시저장한 신청정보 KEY 조회
            ReliefVO keyObj = reliefService.viewReliefAplyLastAdmn(reliefVO);
            if (keyObj != null) {
            	reliefVO.setAplyNo  (keyObj.getAplyNo());
            	reliefVO.setMode    (CommConst.UPDATE);
            }
            else {
            	reliefVO.setMode    (CommConst.INSERT);
            }
    	}
    	else {
            // 2022.12.01 관리자여부
            reliefVO.setAdmYn   (CommConst.NO);
            // 임시저장한 내용이 있는지 확인
            reliefVO.setAplcntNo(reliefVO.getGsUserNo());  // 신청인번호
            reliefVO.setRgtrNo  (reliefVO.getGsUserNo());  // 작성자번호
            reliefVO.setPrgreStusCd(CommConst.PRGRE_SAVE); // 임시저장상태
            
            // 임시저장한 신청정보 KEY 조회
            ReliefVO keyObj = reliefService.viewReliefAplyLast(reliefVO);
            
            if (keyObj != null) {
            	reliefVO.setAplyNo  (keyObj.getAplyNo());
            	reliefVO.setMode    (CommConst.UPDATE);
            }
            else {
            	reliefVO.setMode    (CommConst.INSERT);
            }
    	}
        
        // -------------------- Default Setting End -----------------------//
        model.addAttribute("model", reliefVO);
    	
        return "usr/relief/openReliefForm";
    }

    /**
     * 2021.10.11 LSH
     * 구제급여 신청 임시저장/제출처리
     */
    @RequestMapping("/usr/relief/saveRelief.do")
    @ResponseBody
    public Map saveRelief(
    		HttpServletRequest request, 
    		@RequestBody ReliefVO reliefVO,
    		BindingResult bindingResult
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 2023.01.25 프로그램 세션정보에서 SYS_CD 가져오기
        Map gsPageMap = (Map)request.getSession().getAttribute(CommConst.SESS_PAGE_INFO);
        if (gsPageMap != null) {
        	// 구제급여신청 시스템구분코드 정의
        	reliefVO.setSysSeCd((String)gsPageMap.get("sysCd"));
        }
    	
    	// 저장항목에 맞게 데이터 재정의
    	reliefVO.rebuildProperties();

		// [검증용] 신청양식 서류목록 조회
		Map papeParams = new HashMap();
		papeParams.put("papeDtySeCd", reliefVO.getPapeDtySeCd());
		papeParams.put("aplySeCd"   , reliefVO.getAplySeCd());
		reliefVO.setPapeList(papeMngSerivce.getListPape(papeParams));
    	
        // 저장할 입력값 검증
    	reliefValidator.validate(reliefVO, bindingResult);
    	
    	// 입력값 검증시 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	logger.info("Relief Validate Error...", bindingResult.getAllErrors());
        	return getFailure(bindingResult);
        }
        // 저장처리
    	String result = reliefService.saveRelief(reliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.10.30 LSH
     * 구제급여 보완제출하기 [마이페이지]
     */
    @RequestMapping("/usr/relief/saveReliefSplemnt.do")
    @ResponseBody
    public Map saveReliefSplemnt(
    		HttpServletRequest request, 
    		@RequestBody ReliefVO reliefVO
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
    	if (CommUtils.isNotEmpty(reliefVO.getUserInfo().getUserNo())) {
    		reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());
    		reliefVO.setGsRoleId(reliefVO.getUserInfo().getRoleId());
    	}
        // 보완제출처리
    	String result = reliefService.saveReliefSplemnt(reliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.11.09 LSH
     * 구제급여 의료비 추가신청처리
     */
    @RequestMapping("/usr/relief/saveReliefAdd.do")
    @ResponseBody
    public Map saveReliefAdd(
    		HttpServletRequest request, 
    		@RequestBody ReliefVO reliefVO
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
    	if (CommUtils.isNotEmpty(reliefVO.getUserInfo().getUserNo())) {
    		reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());
    		reliefVO.setGsRoleId(reliefVO.getUserInfo().getRoleId());
    	}
        // 저장처리
    	String result = reliefService.saveReliefAdd(reliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.12.08 LSH
     * 구제급여 신청목록JSON 반환
     */
    @RequestMapping("/usr/relief/getListRelief.do")
    @ResponseBody
    public Map getListRelief(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = null;
        
		// 식별아이디 사용자인 경우 식별ID기준으로 조회한다.
		if (reliefVO.getUserInfo().isIdntfc()) {
			reliefVO.setIdntfcId(reliefVO.getUserInfo().getUserId());
			list = reliefService.listReliefMypage(reliefVO);
		}
		// 로그인회원 사용자인 경우 신청자번호기준으로 조회한다.
		else {
			reliefVO.setAplcntNo(reliefVO.getUserInfo().getUserNo());
			list = reliefService.listReliefMypage(reliefVO);
		}
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.10.14 LSH
     * 구제급여 신청정보 가져오기
     */
    @RequestMapping("/usr/relief/getRelief.do")
    @ResponseBody
    public Map getRelief(
    		HttpServletRequest request, 
            @ModelAttribute ReliefVO reliefVO
    	) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 2021.11.20 TODO. 신청상세 조회시 사업차수 기본값 맵핑
        if (CommUtils.isEmpty(reliefVO.getBizOder()))
        	reliefVO.setBizOder(CommConst.BIZ_ODER_RELIEF);

        // 구제급여 신청정보 상세조회 (피해자정보 포함)
        ReliefVO result = reliefService.viewReliefWidhIdntfc(reliefVO);
        if (result != null) {
	        // 세션사용자번호 정의
		    result.setGsUserNo(reliefVO.getUserInfo().getUserNo());
		    // 2021.12.13 LSH 의료비 추가신청 최종차수
		    String aplyOder = reliefService.getReliefLastOder(reliefVO.getAplyNo(), null);
		    if (aplyOder != null)
		    	result.setAplyOder(aplyOder);
		    
        }
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 2021.10.15 LSH
     * 구제급여 예비조사정보 가져오기
     * - 마이페이지 예비조사정보에 필요한 것만 내려준다.
     * - 2021.12.03 LSH 조회개발
     * - 2021.12.09 LSH 신청번호기준 목록형태로 변경
     */
    @RequestMapping("/usr/relief/getListPrptExmn.do")
    @ResponseBody
    public Map getListPrptExmn(
    		HttpServletRequest request, 
            @ModelAttribute PrptExmnVO prptExmnVO
    	) throws Exception {
    	
        setGlobalSession(prptExmnVO);
        // -------------------- Default Setting End -----------------------//

        // 조건: aplyNo (신청번호)
        // 결과(목록): bizAreaCd, bizOder, aplyNo, exmnOder
        //             dltncOpmtYmd (심의회 개최일자)
        //             dltncRsltCd  (심의회 결과코드)
        //             dltncRsltNm  (심의회 결과명칭)
        //             dltncRsltResn(심의회 결과사유)
        // 신청번호
        String aplyNo = prptExmnVO.getAplyNo();
        // 마이페이지용 예비조사 목록 조회
        List list = prptExmnService.listPrptExmnMypage(aplyNo);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 2021.10.15 LSH
     * 구제급여 본조사정보 가져오기
     * - 마이페이지 본조사정보에 필요한 것만 내려준다.
     * - 2021.12.03 LSH 본조사 정보 조회 구현
     * - 2021.12.07 LSH 지급 정보 조회 구현
     * - 2021.12.09 LSH 신청번호기준 목록형태로 변경
     */
    @RequestMapping("/usr/relief/getListMnsvy.do")
    @ResponseBody
    public Map getListMnsvy(
    		HttpServletRequest request, 
            @ModelAttribute MnsvyVO mnsvyVO
    	) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        // 조건: aplyNo (신청번호)
        // 결과(목록): bizAreaCd, bizOder, aplyNo, exmnOder
        //             giveDcsnYmd  (심의회 개최일자)
        //             dltncRsltCd  (심의회 결과코드)
        //             dltncRsltNm  (심의회 결과명칭)
        //             dltncRsltResn(심의회 결과사유)
		//             lastDmgeGrdCd(최종 피해등급)
		//             rcognSckwndNm(인정질환 대표명칭)
		//             rcognAmt     (인정 금액)
		//             giveBankNm   (지급 은행)
		//             giveActno    (지급 계좌)
		//             giveYmd      (지급 일자)
		//             giveAmt      (지급 결정금액)
		//             giveBgngYm   (지급 시작년월)
		//             giveEndYm    (지급 종료년월)
        
        // 신청번호
        String aplyNo = mnsvyVO.getAplyNo();
        // 마이페이지용 본조사 목록 조회
        List list = mnsvyService.listMnsvyMypage(aplyNo);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 2021.11.01 LSH 생성
     * 2021.12.07 LSH 기능구현
     * 마이페이지 구제급여 지급현황 가져오기
     * - 입력값: aplyNo, bizAreaCd, bizOder
     * - 결과값: mcpAmt(의료비 지급총액), 
     *           rcpAmt(요양생활수당비 지급총액), 
     *           mcpYmd(의료비 지급일자), 
     *           rcpYmd(요양생활비 지급일자)
     */
    @RequestMapping("/usr/relief/getGiveSummary.do")
    @ResponseBody
    public Map getGiveSummary(HttpServletRequest request, 
            @ModelAttribute ReliefVO reliefVO
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 구제급여 지급현황 의료비 총합 조회
        Map mcp = reliefService.viewReliefGiveMCP(reliefVO);
        // 구제급여 지급현황 요양생활수당 총합 조회
        Map rcp = reliefService.viewReliefGiveRCP(reliefVO);
        
        Map result = new HashMap();
        if (mcp != null) result.putAll(mcp);
        if (rcp != null) result.putAll(rcp);
        return result;
    }
    
    /**
     * 2021.11.01 LSH
     * 마이페이지 구제급여 지급세부내역 목록 검색(JSON)
     * - 입력값: aplyNo, bizAreaCd, bizOder, mode (구분값)
     *           mode = MCP: 의료비
     *           mode = RCP: 요양생활수당
     * - 결과값: 페이징목록
     *           {name: 'giveYmd'   , label: '지급일자'},
     *           {name: 'bankNm'    , label: '지급은행'},
     *           {name: 'actno'     , label: '지급계좌'},
     *           {name: 'dpstrNm'   , label: '예금주'},
     *           {name: 'giveAmt'   , label: '지급액'}
     */
    @RequestMapping("/usr/relief/getListGive.do")
    @ResponseBody
    public Map getListGive(HttpServletRequest request, 
            @ModelAttribute ReliefVO reliefVO
    	) throws Exception {
		// 페이징정보를 가져오기 위한 조건맵
    	Map paramMap = getParameterMap(request, true);
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 화면에서 넘어온 페이지번호 (없으면 디폴트 1)
        int page = CommUtils.getInt(paramMap.get("page" ), 1);
        // 화면에서 넘어온 화면출력수 (없으면 디폴트 10)
        int size = CommUtils.getInt(paramMap.get("rows"), basePageSize);
        // 처리모드 (MCP:의료비,RCP:요양생활수당)
        String mode = reliefVO.getMode();
		// 식별아이디 사용자인 경우 식별ID기준으로 조회한다.
		if (reliefVO.getUserInfo().isIdntfc()) {
			reliefVO.setIdntfcId(reliefVO.getUserInfo().getUserId());
		}
		// 로그인회원 사용자인 경우 신청자번호기준으로 조회한다.
		else {
			reliefVO.setAplcntNo(reliefVO.getUserInfo().getUserNo());
		}
        List list = null;
        // 의료비 세부내역인 경우
        if (CommConst.GIVE_MCP.equals(mode)) {
        	list = reliefService.listReliefGiveMCPDtls(reliefVO, page, size);
        }
        // 요양생활수당 세부내역인 경우
        else if (CommConst.GIVE_RCP.equals(mode)) {
        	list = reliefService.listReliefGiveRCPDtls(reliefVO, page, size);
        }
        // 페이징정보가 담긴 데이터맵을 반환한다.
    	// (BaseController에 정의되어 있음)
        return getPaginatedResult(list);
    }
    
    /**
     * 2021.12.27 LSH
     * 구제급여신청 - 온라인설문지 팝업 오픈
     */
    @RequestMapping("/usr/relief/modalSurvey.do")
    public String modalSurvey(HttpServletRequest request
    		, @ModelAttribute SurveyVO surveyVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(surveyVO);

        // YYYYMMDD
        String regDate = CommUtils.getCurDateString();
        
        // 2021.12.29 LSH 구제급여신청 온라인설문지 관리번호
        surveyVO.setQstnnMngNo(CommConst.SURVEY_RELIEF_NO);
        // 2022.01.03 LSH 신청인 성명
        surveyVO.setRgtrNm    (surveyVO.getUserInfo().getUserNm());
        surveyVO.setRgtrYear  (CommUtils.substring(regDate, 0, 4));
        surveyVO.setRgtrMonth (CommUtils.substring(regDate, 4, 6));
        surveyVO.setRgtrDay   (CommUtils.substring(regDate, 6, 8));
        
        // 답변정보가 있을 경우 수정 모드
        if (CommUtils.isNotEmpty(surveyVO.getRspnsMngNo()))
        	surveyVO.setMode(CommConst.UPDATE);
        else
        	surveyVO.setMode(CommConst.INSERT);

        model.addAttribute("form", surveyVO);
        
        return "usr/relief/modalSurvey";
    }

    /**
     * 2022.01.04 LSH
     * 구제급여 신청 온라인설문지 제출 처리
     * [입력조건]
     * surveyVO.qstnnMngNo 설문관리번호
     * surveyVO.signCn     전자서명파일
     * surveyVO.items      설문응답목록
     * 
     * 저장된 관리번호를 반환한다.
     */
    @RequestMapping("/usr/relief/saveSurvey.do")
    @ResponseBody
    public Map saveSurvey(
    		HttpServletRequest request, 
    		@RequestBody SurveyVO surveyVO,
    		BindingResult bindingResult
    	) throws Exception {
    	
        setGlobalSession(surveyVO);
        // -------------------- Default Setting End -----------------------//
        surveyVO.setGsUserNo(surveyVO.getUserInfo().getUserNo());
        
        // 저장처리
    	String rspnsMngNo = surveyRspnsService.saveSurvey(surveyVO);
    	
    	if (rspnsMngNo == null)
    		return getFailure(message.getMessage("exception.proc.err"));
    	
    	// 성공결과 반환
        return getSuccess(rspnsMngNo);
    }
    
    /**
     * 2022.01.19 LSH 구제급여신청 - 온라인설문지 상세팝업 오픈
     */
    @RequestMapping("/usr/relief/modalSurveyView.do")
    public String modalSurveyView(HttpServletRequest request
    		, @ModelAttribute SurveyVO surveyVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(surveyVO);
        
        SurveyVO result = surveyRspnsService.viewRspnsMng(surveyVO);

        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 소유자 또는 관리자만 접근 가능
    	if (!_accessible(surveyVO.getUserInfo(), result.getRgtrNo()))
        	throw new EgovBizException(message.getMessage("error.comm.notAccess"));
        
        // 설문응답목록 조회
        List<SurveyVO> items = surveyRspnsService.listRspnsItem(surveyVO.getRspnsMngNo());
        if (items != null) {
    		// 2단계 계층구조생성
        	result.setItems(CommBizUtils.buildTreeSurvey(items));
        }
        // 2022.01.14 LSH 한글타입 작성일자 정의 (yyyy년 mm월 dd일)
        if (CommUtils.isNotEmpty(result.getRegDate())) {
        	result.setRegDate(
    			CommUtils.toKorDate(
					result.getRegDate()
    			)
        	);
        }
        model.addAttribute("form", result);
        
        return "usr/relief/modalSurveyView";
    }
    
    /**
     * 2022.01.19 LSH 서명파일 URL링크보기
     */
    @RequestMapping(value="/usr/relief/linkSurveySign.do")
    @ResponseBody
    public HttpEntity<byte[]> linkSurveySign(HttpServletRequest request
    		, HttpServletResponse response
    		, @ModelAttribute SurveyVO surveyVO) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(surveyVO);
        
        SurveyVO result = surveyRspnsService.viewRspnsMng(surveyVO);

        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 소유자 또는 관리자만 접근 가능
    	if (!_accessible(surveyVO.getUserInfo(), result.getRgtrNo()))
        	throw new EgovBizException(message.getMessage("error.file.notAccess"));

        if (CommUtils.isEmpty(result.getSignCn()))
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(FileDirectory.SIGNATURE.getRealPath())
        		.saveName(result.getSignCn())
        		.build();
        
        // 미디어 URL 링크용 HttpEntity 반환
    	return fileManager.linkFile(f , request, response);
    }
    
    /**
     * 2022.01.20 LSH
     * 구제급여신청 - 온라인설문지 수정정보 가져오기
     */
    @RequestMapping("/usr/relief/getSurvey.do")
    @ResponseBody
    public Map getSurvey(
    		HttpServletRequest request, 
    		@ModelAttribute SurveyVO surveyVO
    	) throws Exception {
    	
        setGlobalSession(surveyVO);
        // -------------------- Default Setting End -----------------------//

        // 수정용도의 설문지 응답정보 조회
        Map result = surveyRspnsService.viewRspnsMap(surveyVO);
        
        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 소유자 또는 관리자만 접근 가능
    	if (!_accessible(surveyVO.getUserInfo(), (String)result.get("rgtrNo")))
        	throw new EgovBizException(message.getMessage("error.file.notAccess"));

    	if (CommUtils.isEmpty((String)result.get("rgtrNm"))) {
        	result.put("rgtrNm", surveyVO.getUserInfo().getUserNm());
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
    @RequestMapping("/usr/relief/getUserInfo.do")
    @ResponseBody
    public Map getUserInfo(HttpServletRequest request
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
    
}
