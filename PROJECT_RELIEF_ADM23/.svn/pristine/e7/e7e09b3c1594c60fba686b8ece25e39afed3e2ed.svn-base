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
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 구제급여지급 > 유족보상비 관리 Controller
 * 
 * @class   : BrvfmRwrdController
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
public class BrvfmRwrdController extends BaseController {

    @Resource(name="MnsvyService")
    protected MnsvyService mnsvyService;

    @Resource(name="ReliefService")
    protected ReliefService reliefService;

    /**
     * 구제급여지급 - 유족보상비 - 화면 오픈
     */
    @RequestMapping("/adm/exmn/listBrvfmRwrd.do")
    public String listBrvfmRwrd(HttpServletRequest request
	        , @ModelAttribute MnsvyVO mnsvyVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", mnsvyVO);
    	
        return "adm/exmn/listBrvfmRwrd";
    }

    /**
     * 구제급여지급 - 유족보상비 - 대상목록 JSON 반환 (EasyUI GRID)
     * ReliefController의 getListReliefGive 응용
     */
    @RequestMapping("/adm/exmn/getListBrvfmRwrd.do")
    @ResponseBody
    public Map getListBrvfmRwrd(HttpServletRequest request
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
     * 구제급여지급 - 유족보상비 - 대상목록 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downBrvfmRwrdExcel.do")
    public String downBrvfmRwrdExcel(HttpServletRequest request
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
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "BrvfmRwrd");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "유족보상비_대상목록");

		return "excelTempView";
    }
    
    /**
     * 구제급여지급 - 유족보상비 - 유족보상비,기지급의료비,요양생활수당 조회
     * 1) 유족보상비 확정 = (장의비 * 피해등급별 장의비 지급비율)
     * 2) 유족보상비 잔액 = (유족보상비) - (기지급 의료비 + 요양생활수당) 
     * 3) 장의비의 등급별 지급비율 
     *    - 1등급 1500%
     *    - 2등급 1080%
     *    - 3등급 750%
     *    - 4등급 500%
     *    - 5등급 250%
     * 4) 유족보상비 FN_BRVFM_RWRD_AMT 함수 사용
     * 
     * @param mnsvyVO.bizAreaCd       사업지역코드
     * @param mnsvyVO.bizOder         사업차수
     * @param mnsvyVO.exmnOder        조사차수
     * @param mnsvyVO.aplyNo          신청번호
     * @param mnsvyVO.lastDmgeGrdCd   피해등급
     * @return brvfmRwrdAmt           유족보상비
     * @return mcpDtlsSum             기지급 의료비 합계
     * @return rcperLvlhSum           기지급 요양생활수당 함계
     */
    @RequestMapping("/adm/exmn/getBrvfmRwrdAmt.do")
    @ResponseBody
    public Map getBrvfmRwrdAmt(HttpServletRequest request
            , @ModelAttribute MnsvyVO mnsvyVO) throws Exception {

        setGlobalSession(mnsvyVO);
        // -------------------- Default Setting End -----------------------//

        MnsvyVO result = mnsvyService.viewBrvfmRwrdAmt(mnsvyVO);
    	// 성공결과 반환
        return getSuccess(result);
    }

    /**
     * 구제급여지급 - 유족보상비 - 지급등록 저장처리
     */
    @RequestMapping("/adm/exmn/saveBrvfmRwrd.do")
    @ResponseBody
    public Map saveBrvfmRwrd(HttpServletRequest request
			, @RequestBody MnsvyVO mnsvyVO) throws Exception {
    	
        setGlobalSession(mnsvyVO);
        
        if (mnsvyVO.getUserInfo() != null)
        	mnsvyVO.setGsUserNo(mnsvyVO.getUserInfo().getUserNo());
    	
        // 유족보상비 지급등록 정보를 저장한다.
    	String result = mnsvyService.saveBrvfmRwrd(mnsvyVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}
