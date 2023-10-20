package business.adm.relief.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommBizUtils;
import business.com.CommConst;
import business.com.file.service.PapeMngService;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.IdntfcVO;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import business.com.relief.service.ReliefValidator;
import business.com.survey.service.SurveyRspnsService;
import business.com.survey.service.SurveyVO;
import business.sys.code.service.CodeService;
import business.sys.code.service.CodeVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.user.UserInfo;
import common.util.CommUtils;
import common.view.ExcelTempView;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 구제급여 관리 Controller
 * 
 * @class   : ReliefController
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 * 2021.12.16   LSH        사후관리(구상권) 컨트롤러 분리
 */
@Controller
@SuppressWarnings({"all"})
public class ReliefController extends BaseController {

    @Resource(name="ReliefService")
    protected ReliefService reliefService;

    @Resource(name="IdntfcService")
    protected IdntfcService idntfcService;

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngSerivce;

    @Resource(name="CodeService")
    protected CodeService codeService;

    @Resource(name="SurveyRspnsService")
    protected SurveyRspnsService surveyRspnsService;

    // 데이터 검증용 VALIDATOR
    @Autowired 
    private ReliefValidator reliefValidator;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 구제급여정보 목록JSON 반환 (EasyUI GRID)
     * 
     * 2021.10.21 구제급여 신청접수현황 목록조회
     */
    @RequestMapping("/adm/relief/getListRelief.do")
    @ResponseBody
    public Map getListRelief(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 2021.11.20 TODO. 신청목록 검색시 사업차수 기본값 맵핑 
        if (CommUtils.isEmpty(reliefVO.getBizOder()))
        	reliefVO.setBizOder(CommConst.BIZ_ODER_RELIEF);
        
        if (CommUtils.isNotEmptyList(reliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	reliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(reliefVO.getAplyKndList(),"|")
        	);
        }
        
        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = reliefService.listRelief(reliefVO, page, size);
        }
        else {
        	list = reliefService.listRelief(reliefVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2022.01.05 LSH
     * 예비조사/본조사 구제급여 대상자목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/relief/getListReliefTarget.do")
    @ResponseBody
    public Map getListReliefTarget(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        List list = reliefService.listReliefTarget(reliefVO);
        
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 2021.10.18 LSH
     * 구제급여 신청 1단계 화면
     * - 신청 구분 선택 
     */
    @RequestMapping("/adm/relief/openRelief.do")
    public String openRelief(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 임시저장상태만 검색하도록 조건정의
        reliefVO.setPrgreStusCd(CommConst.PRGRE_SAVE);
        // 업무구분을 구제급여로 정의 (이력관리에서 사용)
        reliefVO.setDtySeCd(CommConst.DTY_RELIEF);

        model.addAttribute("model", reliefVO);
    	
        return "adm/relief/openRelief";
    }
    
    /**
     * 2021.10.18 LSH
     * 구제급여 신청 2단계 화면
     * - 신청정보 입력 
     */
    @RequestMapping("/adm/relief/openReliefForm.do")
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
        // 신청번호가 있으면
        if (CommUtils.isNotEmpty(reliefVO.getAplyNo())) {
        	reliefVO.setMode(CommConst.UPDATE);
        }
        else {
        	reliefVO.setMode(CommConst.INSERT);
        }
        model.addAttribute("model", reliefVO);
    	
        return "adm/relief/openReliefForm";
    }

    /**
     * 2021.10.18 LSH
     * 구제급여 신청 임시저장/제출처리
     */
    @RequestMapping("/adm/relief/saveRelief.do")
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
     * 2021.10.18 LSH
     * 구제급여 신청정보 가져오기
     */
    @RequestMapping("/adm/relief/getRelief.do")
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
        }
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 2021.10.19
     * 구제급여 접수 화면
     */
    @RequestMapping("/adm/relief/listReliefRcpt.do")
    public String listReliefRcpt(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 서류업무구분 정의
        reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);
        // 세션사용자번호 정의
        reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());

        model.addAttribute("model", reliefVO);
    	
        return "adm/relief/listReliefRcpt";
    }

    /**
     * 2021.10.22
     * 구제급여 신청접수 모달팝업 오픈
     * 2022.12.08 접수일자 입력처리 적용
     */
    @RequestMapping("/adm/relief/modalReceipt.do")
    public String modalReceipt(HttpServletRequest request
    		, @RequestBody ReliefVO reliefVO
    		, ModelMap model) throws Exception {

    	String mode = reliefVO.getMode();
    	
    	CodeVO codeVO = CodeVO.builder()
    			.upCdId(CommConst.CODE_SMSMSG)
    			.cdId  (CommConst.CODE_SMSMSG_RECEIPT)
    			.build();
    	
    	codeVO = codeService.viewCode(codeVO);
    	
    	String trsmCn = "";
    	if (codeVO != null && codeVO.getCdCn() != null) {
    		trsmCn = codeVO.getCdCn();
    		trsmCn += "\n\n";
    	}
    	trsmCn += "접수일자 : " + CommUtils.formatCurDate("yyyy.MM.dd");
    	
    	// 기본 접수일자
    	reliefVO.setRcptYmd(CommUtils.formatCurDate("yyyy.MM.dd"));
    	// 기본 발신번호
    	reliefVO.setTrnsterNo(CommUtils.formatPhone(CommConst.SMS_TRANSFER_NO));
    	// 기본 메세지
    	reliefVO.setTrsmCn(trsmCn);
    	
        model.addAttribute("form", reliefVO);
        
        return "adm/relief/modalReceipt";
    }

    /**
     * 2021.10.26 LSH
     * 구제급여 신청접수처리
     * 2022.12.08 접수일자 입력처리 적용
     * 2022.12.08 휴대전화번호 있을 경우에만 SMS 전송처리 적용
     */
    @RequestMapping("/adm/relief/saveReceipt.do")
    @ResponseBody
    public Map saveReceipt(HttpServletRequest request
			, @RequestBody ReliefVO reliefVO
			, BindingResult bindingResult) throws Exception {
    	
        setGlobalSession(reliefVO);
        
        if (reliefVO.getUserInfo() != null)
        	reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());
    	
        // 신청접수를 처리한다.
    	String result = reliefService.saveReceipt(reliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 2021.12.14 LSH
     * 구제급여 접수 - 추가의료비 화면
     */
    @RequestMapping("/adm/relief/listReliefOder.do")
    public String listReliefOder(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", reliefVO);
    	
        return "adm/relief/listReliefOder";
    }

    /**
     * 2021.12.14 LSH
     * 구제급여 접수 - 추가의료비 신청현황목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/relief/getListReliefOder.do")
    @ResponseBody
    public Map getListReliefOder(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = reliefService.listReliefOder(reliefVO, page, size);
        }
        else {
        	list = reliefService.listReliefOder(reliefVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
    /**
     * 2021.12.14 LSH
     * 구제급여 접수 - 추가의료비 신청정보 가져오기
     */
    @RequestMapping("/adm/relief/getReliefOder.do")
    @ResponseBody
    public Map getReliefOder(
    		HttpServletRequest request, 
            @ModelAttribute ReliefVO reliefVO
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 2021.11.20 TODO. 신청상세 조회시 사업차수 기본값 맵핑
        if (CommUtils.isEmpty(reliefVO.getBizOder()))
        	reliefVO.setBizOder(CommConst.BIZ_ODER_RELIEF);

        // 신청정보 상세조회 (피해자정보 포함)
        ReliefVO result = reliefService.viewReliefOder(reliefVO);
        if (result != null) {
	        // 세션사용자번호 정의
		    result.setGsUserNo(reliefVO.getUserInfo().getUserNo());
        }
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 2021.12.14 LSH
     * 구제급여 접수 - 추가의료비 신청정보 접수처리
     */
    @RequestMapping("/adm/relief/saveReliefOder.do")
    @ResponseBody
    public Map saveReliefOder(HttpServletRequest request
			, @RequestBody ReliefVO reliefVO
			, BindingResult bindingResult) throws Exception {
    	
        setGlobalSession(reliefVO);
        
        if (reliefVO.getUserInfo() != null)
        	reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());
    	
        // 신청접수를 처리한다.
    	String result = reliefService.saveReliefOder(reliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 구제급여 지급 화면
     */
    @RequestMapping("/adm/relief/listReliefGive.do")
    public String listReliefGive(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", reliefVO);
    	
        return "adm/relief/listReliefGive";
    }

    /**
     * 2021.10.26 LSH
     * 구제급여 엑셀 다운로드
     */
    @RequestMapping("/adm/relief/downReliefExcel.do")
    public String downReliefExcel(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(reliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	reliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(reliefVO.getAplyKndList(),"|")
        	);
        }
        // 구제급여 신청접수목록
        List list = reliefService.listRelief(reliefVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "Relief");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "구제급여_신청접수현황");

		return "excelTempView";
    }

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/relief/getListReliefGive.do")
    @ResponseBody
    public Map getListReliefGive(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = reliefService.listReliefGive(reliefVO, page, size);
        }
        else {
        	list = reliefService.listReliefGive(reliefVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 엑셀 다운로드
     */
    @RequestMapping("/adm/relief/downReliefGiveExcel.do")
    public String downReliefGiveExcel(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(reliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	reliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(reliefVO.getAplyKndList(),"|")
        	);
        }
        // 구제급여 지급현황목록
        List list = reliefService.listReliefGive(reliefVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "ReliefGive");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "구제급여_지급현황");

		return "excelTempView";
    }
    
    /**
     * 2021.12.27 LSH
     * 구제급여신청 - 온라인설문지 팝업 오픈
     */
    @RequestMapping("/adm/relief/modalSurvey.do")
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
        
        return "adm/relief/modalSurvey";
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
    @RequestMapping("/adm/relief/saveSurvey.do")
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
     * 2022.01.05 LSH 구제급여신청 - 온라인설문지 상세팝업 오픈
     */
    @RequestMapping("/adm/relief/modalSurveyView.do")
    public String modalSurveyView(HttpServletRequest request
    		, @ModelAttribute SurveyVO surveyVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(surveyVO);
        
        SurveyVO result = surveyRspnsService.viewRspnsMng(surveyVO);

        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 관리자만 접근 가능
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
        
        return "adm/relief/modalSurveyView";
    }
    
    /**
     * 2022.01.19 LSH 서명파일 URL링크보기
     */
    @RequestMapping(value="/adm/relief/linkSurveySign.do")
    @ResponseBody
    public HttpEntity<byte[]> linkSurveySign(HttpServletRequest request
    		, HttpServletResponse response
    		, @ModelAttribute SurveyVO surveyVO) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(surveyVO);
        
        SurveyVO result = surveyRspnsService.viewRspnsMng(surveyVO);

        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
        // 관리자만 접근 가능
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
    @RequestMapping("/adm/relief/getSurvey.do")
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
        
        // 관리자만 접근 가능
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
    	// 그외엔 NOT ACCESS
    	else {}

    	return false;
    }

    /**
     * 2022.12.02 LSH
     * 구제급여 개인정보수정 모달팝업 오픈
     */
    @RequestMapping("/adm/relief/modalReliefForm.do")
    public String modalReliefForm(HttpServletRequest request
    		, @ModelAttribute ReliefVO reliefVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(reliefVO);
        
        String hstCd = reliefVO.getHstSeCd();
        if (CommUtils.isEmpty(hstCd))
        	reliefVO.setHstSeCd(CommConst.HST_SUFRER);
        
        // 구제급여 신청정보 상세조회 (피해자정보 포함)
        ReliefVO result = reliefService.viewRelief(reliefVO);
        if (result != null) {
        	reliefVO.setAplySeCd(result.getAplySeCd());
        }
        // 수정모드
        reliefVO.setMode(CommConst.UPDATE);
        
        model.addAttribute("form", reliefVO);
        
        return "adm/relief/modalReliefForm";
    }

    /**
     * 2022.12.05 LSH
     * 구제급여접수 - 피해자정보 수정처리
     * 구제급여접수 - 신청인정보 수정처리
     * 구제급여접수 - 피해내용 수정처리
     */
    @RequestMapping("/adm/relief/saveReliefModify.do")
    @ResponseBody
    public Map saveReliefModify(
    		HttpServletRequest request, 
    		@RequestBody ReliefVO reliefVO,
    		BindingResult bindingResult
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
    	
    	// 저장항목에 맞게 데이터 재정의
    	reliefVO.rebuildModifyProperties();
        // 저장할 입력값 검증
    	reliefValidator.validateModify(reliefVO, bindingResult);
    	
    	// 입력값 검증시 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	logger.info("Relief Validate Error...", bindingResult.getAllErrors());
        	return getFailure(bindingResult);
        }
        // 저장처리
    	String result = reliefService.saveReliefModify(reliefVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2022.12.06 LSH
     * 관리자 제출서류 추가등록 업로드
     * reliefVO 에 화면에서 입력되어야할 항목
     * - aplyNo   : 신청번호
     * - papeCd   : 서류코드
     * - upPapeCd : 상위서류코드
     * - hstCn    : 추가등록사유
     */
    @RequestMapping("/adm/relief/saveReliefAddfile.do")
    @ResponseBody
    public Map saveReliefAddfile(HttpServletRequest request
			, @ModelAttribute ReliefVO reliefVO) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        if (reliefVO.getUserInfo() != null)
        	reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());

	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    
	    if (files == null || files.size() != 1) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
	    }
	    FileInfo fileInfo = files.get(0);
    	if (!"Y".equals(fileInfo.getFileYn())) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
    	}
    	String result = reliefService.saveReliefAddfile(reliefVO, fileInfo);

    	// 성공결과 반환
        return getSuccess();
    }
    
    /**
     * 2022.12.15 LSH
     * 종합현황 - 개인별 상세기록카드 모달팝업 오픈
     */
    @RequestMapping("/adm/relief/modalReliefView.do")
    public String modalReliefView(HttpServletRequest request
    		, @ModelAttribute ReliefVO reliefVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(reliefVO);
        
        model.addAttribute("form", reliefVO);
        
        return "adm/relief/modalReliefView";
    }
}
