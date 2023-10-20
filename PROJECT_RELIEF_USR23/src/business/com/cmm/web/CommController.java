package business.com.cmm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommBizUtils;
import business.com.CommConst;
import business.com.CommFormFile;
import business.com.cmm.service.CommService;
import business.com.survey.service.SurveyQstnService;
import business.com.survey.service.SurveyVO;
import business.sys.authority.AuthorityService;
import business.sys.code.service.CodeService;
import business.sys.code.service.CodeVO;
import business.sys.code.service.SckwndService;
import business.sys.code.service.SckwndVO;
import business.sys.menu.service.MenuService;
import business.sys.menu.service.MenuVO;
import business.sys.role.service.RoleService;
import business.sys.role.service.RoleVO;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.util.FileUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 *  [컨트롤러클래스] - 공통 Controller
 *
 * @class   : CommController
 * @author  :
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class CommController extends BaseController {

    @Resource(name="CommService")
    protected CommService commService;

    @Resource(name="CodeService")
    protected CodeService codeService;

    @Resource(name="AuthorityService")
    protected AuthorityService authorityService;

    @Resource(name="MenuService")
    protected MenuService menuService;

    @Resource(name="RoleService")
    protected RoleService roleService;

    @Resource(name="SckwndService")
    protected SckwndService sckwndService;

    @Resource(name="UserInfoService")
    protected UserInfoService userInfoService;

    @Resource(name="SurveyQstnService")
    protected SurveyQstnService surveyQstnService;

    @Resource(name="fileManager")
    protected FileManager fileManager;

    /**
     * 2021.10.06 LSH
     * 로그인 회원정보 가져오기 (JSON)
     */
    @RequestMapping("/com/cmm/getUserInfo.do")
    @ResponseBody
    public Map getUserInfo(HttpServletRequest request
            , ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        // -------------------- Default Setting End -----------------------//
        UserInfoVO obj = null;
        String userNo = (String)paramMap.get("gsUserNo");
        
        if (!CommUtils.isEmpty(userNo)) {
        	obj = userInfoService.viewUserInfo(userNo);
        }
    	return getSuccess(obj);
    }

    /**
     * Combo Code 조회
     */
    @RequestMapping("/com/cmm/getComboCode.do")
    @ResponseBody
    public List getComboCode(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.listCode(paramMap);
    }

    /**
     * 2021.10.22 LSH
     * 특정 코드 조회
     */
    @RequestMapping("/com/cmm/getCode.do")
    @ResponseBody
    public Map getCode(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        CodeVO codeVO = CodeVO.builder()
        		.upCdId((String)paramMap.get("upCdId"))
        		.cdId((String)paramMap.get("cdId"))
        		.build();
        
        codeVO = codeService.viewCode(codeVO);
    	return getSuccess(codeVO);
    }

    /**
     * 2021.08.20 LSH
     * 엑셀설정정보 Combo Excel 조회
     */
    @RequestMapping("/com/cmm/getComboExcel.do")
    @ResponseBody
    public List getComboExcel(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListExcel(paramMap);
    }

    /**
     * 2021.09.07 LSH
     * 상위메뉴 Combo 목록 조회
     * sysCd (시스템구분) 필수
     * notId (제외할 메뉴ID)
     */
    @RequestMapping("/com/cmm/getComboMenu.do")
    @ResponseBody
    public List getComboMenu(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        MenuVO menuVO = MenuVO.builder()
        		.srchSysCd((String)paramMap.get("sysCd"))
        		.srchNotId((String)paramMap.get("notId"))
        		.build();
        if (CommUtils.isEmpty(menuVO.getSrchSysCd()))
        	return null;
        return menuService.listMenu(menuVO);
    }

    /**
     * 2021.09.08 LSH
     * 상위역할 Combo 목록 조회
     * notId (제외할 역할ID)
     */
    @RequestMapping("/com/cmm/getComboRole.do")
    @ResponseBody
    public List getComboRole(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        setGlobalSession(paramMap);
        
        // -------------------- Default Setting End -----------------------//
        RoleVO roleVO = RoleVO.builder()
        		.srchNotId((String)paramMap.get("notId"))
        		.gsUserNo((String)paramMap.get("gsUserNo"))
        		.gsRoleId((String)paramMap.get("gsRoleId"))
        		.build();
        return roleService.listRole(roleVO);
    }

    /**
     * 2021.09.23 LSH
     * 상위상병코드 Combo 목록 조회
     */
    @RequestMapping("/com/cmm/getComboSckwnd.do")
    @ResponseBody
    public List getComboSckwnd(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        SckwndVO sckwndVO = SckwndVO.builder()
        		.upSckwndCd((String)paramMap.get("upSckwndCd"))
        		.build();
        return sckwndService.listSckwnd(sckwndVO);
    }

    /**
     * 2021.09.24 LSH
     * 피해지역 Combo 목록 조회
     */
    @RequestMapping("/com/cmm/getComboBizMng.do")
    @ResponseBody
    public List getComboBizMng(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListBizMng(paramMap);
    }

    /**
     * 2021.11.17 LSH
     * 사업차수 Combo 목록 조회
     * bizAreaCd 필수값
     */
    @RequestMapping("/com/cmm/getComboBizOder.do")
    @ResponseBody
    public List getComboBizOder(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListBizOder(paramMap);
    }
    
    /**
     * 2021.11.23 LSH
     * 예비조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    @RequestMapping("/com/cmm/getComboPrptOder.do")
    @ResponseBody
    public List getComboPrptOder(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListPrptOder(paramMap);
    }
    
    /**
     * 2021.11.23 LSH
     * 본조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    @RequestMapping("/com/cmm/getComboMnsvyOder.do")
    @ResponseBody
    public List getComboMnsvyOder(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListMnsvyOder(paramMap);
    }

    /**
     * 2021.11.11 LSH
     * 질병분류 Combo 목록 조회
     */
    @RequestMapping("/com/cmm/getComboDissCl.do")
    @ResponseBody
    public List getComboDissCl(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListDissCl(paramMap);
    }

    /**
     * 2021.12.14 LSH
     * 신청차수 Combo 목록 조회
     */
    @RequestMapping("/com/cmm/getComboReliefOder.do")
    @ResponseBody
    public List getComboReliefOder(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListReliefOder(paramMap);
    }

    /**
     * 2021.08.26 LSH
     * 해당 시스템 메뉴 목록 조회 (JSON)
     * 
     * 입력조건에 따라 메뉴가 2가지로 조회되어야 한다.
     * - 관리자시스템 (sysCd = ADM)
     * - 사용자시스템 (sysCd = USR)
     * TREE 형태의 계층구조 목록으로 반환한다.
     */
    @RequestMapping("/com/cmm/listMenu.do")
    @ResponseBody
    public List listMenu(HttpServletRequest request, 
    		ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        // 최상위 항목
        String rootId   = (String)paramMap.get("rootId");
        String rootText = (String)paramMap.get("rootText");
        
        // -------------------- Default Setting End -----------------------//
        Map menuMap = new HashMap();
        menuMap.put("roleId",   CommUtils.nvlTrim(userInfo.getRoleId(), CommConst.ROLE_RESTRICTED));
        menuMap.put("sysCd",    paramMap.get("sysCd"));
        menuMap.put("notId",    paramMap.get("notId"));
        menuMap.put("menuLvl",  paramMap.get("menuLvl"));
        menuMap.put("upMenuId", paramMap.get("upMenuId"));
        List<Map> menuList = authorityService.getMenuList(menuMap);

        // 2021.08.26 메뉴를 계층구조로 변경
		// 2021.09.09 계층구조 생성유틸로 변경
		Map keys = new HashMap();
		keys.put("level" , "level");
		keys.put("itemId", "menuId");
		keys.put("itemNm", "menuNm");
		keys.put("parentId", "upMenuId");
		
		List<Map> list = CommBizUtils.buildTree(menuList, keys);
		
        // 최상위 항목이 존재하는 경우
		if (CommUtils.isNotEmpty(rootText)) {
			Map root = new HashMap();
			root.put("id", rootId);
			root.put("text", rootText);
			root.put("children", list);
			List<Map> result = new ArrayList<Map>();
			result.add(root);
			return result;
		}
		return list; 
    }

    /**
     * 2021.12.14 LSH
     * 전자서명 저장처리 (공통사용)
     * - 구제급여신청
     * - 취약계층소송지원 
     */
    @RequestMapping("/com/cmm/saveSignature.do")
    @ResponseBody
    public Map saveSignature(HttpServletRequest request
    		, @RequestBody Map<String,Object> reqMap) throws Exception {
    	String sign  = (String)reqMap.get("sign");
    	String[] arr = CommUtils.split(sign, ",");
    	String signFile = null;
    	if (arr.length > 1) {
    		signFile = "sign"+System.currentTimeMillis()+".png";
    		// 전자서명경로에 파일 저장
    		FileUtils.saveSignFile(signFile, arr[1]);
    	}
    	// 성공결과 반환
        return getSuccess("FileName", signFile);
    }

    /**
     * 2021.12.29 LSH
     * 설문지 문항 목록 조회 (JSON)
     * TREE 형태의 계층구조 목록으로 반환한다.
     */
    @RequestMapping("/com/cmm/getListSurvey.do")
    @ResponseBody
    public List getListSurvey(HttpServletRequest request, 
    		ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        
        // 설문관리모델
        SurveyVO surveyVO = SurveyVO.builder()
        		.qstnnMngNo((String)paramMap.get("qstnnMngNo"))
        		.build();
        
        // 설문문항목록
        List<SurveyVO> qstnList = surveyQstnService.listQstnItem(surveyVO);
        
		// 2단계 계층구조생성
		List<SurveyVO> rootLst = CommBizUtils.buildTreeSurvey(qstnList);
		return rootLst;
    }
    
    /**
     * 2022.03.16 ntarget
     * 양식 파일 다운로드 
     */
    @RequestMapping("/com/cmm/downFormFile.do")
    public void downFormFile(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception {

        Map paramMap = getParameterMap(request, true);

        String formFileType = CommUtils.nvlTrim((String)paramMap.get("fType"));
        
        // 다운로드할 양식 파일 정보 (
    	CommFormFile cf = null;
    	
    	if (formFileType.equals("T1")) //T1(구) -> T3(최신)
    		cf = CommFormFile.PERSINFO01;
    	else if (formFileType.equals("T2")) //T2(구) -> T4(최신)
    		cf = CommFormFile.PERSINFO02;
    	else if (formFileType.equals("T3"))
    		cf = CommFormFile.PERSINFO03;
    	else if (formFileType.equals("T4"))
    		cf = CommFormFile.PERSINFO04;
    	else if (formFileType.equals("T5"))
    		cf = CommFormFile.PERSINFO05;
    	else if (formFileType.equals("T6"))
    		cf = CommFormFile.PERSINFO06;
    	
    	if (cf == null)
    		throw new EgovBizException("잘못된 파일다운로드 타입입니다.");
    	
        FileInfo fileInfo = FileInfo.builder()
							.saveName(cf.getSaveName())
							.fileName(cf.getFileName())
							.fullPath(cf.getFullPath())
							.build();

        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }    

    /**
     * 2023.01.16 LSH
     * 살생물제품 제품분류및유형 Combo 목록 조회
     * @param paramMap.upCdId 상위코드(CT202)
     */
    @RequestMapping("/com/cmm/getComboBioPrduct.do")
    @ResponseBody
    public List getComboBioPrduct(HttpServletRequest request
    		, @ModelAttribute ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        return commService.getListBioPrduct(paramMap);
    }
}
