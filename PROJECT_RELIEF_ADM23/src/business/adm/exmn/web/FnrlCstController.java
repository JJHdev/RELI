package business.adm.exmn.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.exmn.service.MnsvyService;
import business.com.exmn.service.MnsvyVO;
import business.com.exmn.service.RcperLvlhVO;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 구제급여지급 > 장의비 관리 Controller
 * 
 * @class   : FnrlCstController
 * @author  : LSH
 * @since   : 2023.01.04
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class FnrlCstController extends BaseController {

    @Resource(name="MnsvyService")
    protected MnsvyService mnsvyService;

    @Resource(name="ReliefService")
    protected ReliefService reliefService;
    
    /**
     * 구제급여지급 - 장의비 - 화면 오픈
     */
    @RequestMapping("/adm/exmn/listFnrlCst.do")
    public String listFnrlCst(HttpServletRequest request
	        , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", mnsvyVO);
    	
        return "adm/exmn/listFnrlCst";
    }

    /**
     * 2023.01.05 LSH
     * 구제급여지급 - 장의비 - 대상목록 JSON 반환 (EasyUI GRID)
     * ReliefController의 getListReliefGive 응용
     */
    @RequestMapping("/adm/exmn/getListFnrlCst.do")
    @ResponseBody
    public Map getListFnrlCst(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 사망여부조건 정의
        reliefVO.setSrchDthYn(CommConst.YES);
        
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
     * 구제급여지급 - 장의비 - 대상목록 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downFnrlCstExcel.do")
    public String downFnrlCstExcel(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 사망여부조건 정의
        reliefVO.setSrchDthYn(CommConst.YES);
        
        List list = reliefService.listReliefGive(reliefVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "FnrlCst");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "장의비_대상목록");

		return "excelTempView";
    }

    /**
     * 구제급여지급 - 장의비 - 장의비 확정금액 조회
     * 1) 지급기준년도의 중위소득의 89.7%
     * 2) FN_FNRL_CST_AMT 함수 사용
     * 
     * @param fnrlCrtrYr 지급기준년도 (신청년도)
     */
    @RequestMapping("/adm/exmn/getFnrlCstAmt.do")
    @ResponseBody
    public Map getFnrlCstAmt(HttpServletRequest request
            , @ModelAttribute MnsvyVO mnsvyVO) throws Exception {

        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        // 장의비 확정금액 조회
        long sum = mnsvyService.getFnrlCstAmt(mnsvyVO.getFnrlCrtrYr());
    	// 성공결과 반환
        return getSuccess(String.valueOf(sum));
    }

    /**
     * 구제급여지급 - 장의비 - 지급등록 저장처리
     */
    @RequestMapping("/adm/exmn/saveFnrlCst.do")
    @ResponseBody
    public Map saveFnrlCst(HttpServletRequest request
			, @RequestBody MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
    	
        // 장의비 지급등록 정보를 저장한다.
    	String result = mnsvyService.saveFnrlCst(mnsvyVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}