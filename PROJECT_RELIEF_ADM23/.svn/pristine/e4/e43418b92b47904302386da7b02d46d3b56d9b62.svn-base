package business.adm.relief.web;

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
import business.com.relief.service.ReamtPayService;
import business.com.relief.service.ReamtPayVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 사후관리(구상권) Controller
 * 
 * @class   : ReamtPayController
 * @author  : LSH
 * @since   : 2021.12.16
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 * 2021.12.16   LSH        ReliefController에서 분리됨
 */
@Controller
@SuppressWarnings({"all"})
public class ReamtPayController extends BaseController {

    @Resource(name="ReamtPayService")
    protected ReamtPayService reamtPayService;
    
    /**
     * 사후관리(구상권) 화면
     */
    @RequestMapping("/adm/relief/listReliefClaim.do")
    public String listReliefClaim(HttpServletRequest request
            , @ModelAttribute ReamtPayVO reamtPayVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(reamtPayVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", reamtPayVO);
    	
        return "adm/relief/listReliefClaim";
    }

    /**
     * 2021.12.16
     * 사후관리(구상권) 구상권정보 등록/수정 팝업
     */
    @RequestMapping("/adm/relief/modalReamtForm.do")
    public String modalReamtForm(HttpServletRequest request
    		, @RequestBody ReamtPayVO reamtPayVO
    		, ModelMap model) throws Exception {

    	if (CommUtils.isNotEmpty(reamtPayVO.getSn())) {
    		reamtPayVO.setMode(CommConst.UPDATE);
    	}
    	else {
    		reamtPayVO.setMode(CommConst.INSERT);
    	}
        model.addAttribute("form", reamtPayVO);
        
        return "adm/relief/modalReamtForm";
    }

    /**
     * 2021.12.16
     * 사후관리(구상권) 구제급여 상세내역 조회 팝업
     */
    @RequestMapping("/adm/relief/modalReamtView.do")
    public String modalReamtView(HttpServletRequest request
    		, @RequestBody ReamtPayVO reamtPayVO
    		, ModelMap model) throws Exception {

    	// 구상금납부내역 구제급여 항목포함 상세조회
    	ReamtPayVO result = reamtPayService.viewReamtPay(reamtPayVO);
    	
        model.addAttribute("form", result);
        
        return "adm/relief/modalReamtView";
    }
    
    /**
     * 구상금납부내역 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/relief/getListReamtPay.do")
    @ResponseBody
    public Map getListReamtPay(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute ReamtPayVO reamtPayVO
            , ModelMap model) throws Exception {

        setGlobalSession(reamtPayVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = reamtPayService.listReamtPay(reamtPayVO, page, size);
        }
        else {
        	list = reamtPayService.listReamtPay(reamtPayVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 구상금납부내역 조회JSON 반환
     */
    @RequestMapping("/adm/relief/getReamtPay.do")
    @ResponseBody
    public Map getReamtPay(HttpServletRequest request
            , @ModelAttribute ReamtPayVO reamtPayVO
			, ModelMap model) throws Exception {

        ReamtPayVO obj = reamtPayService.viewReamtPay(reamtPayVO);
        return getSuccess(obj);
    }

    /**
     * 2021.12.16 LSH
     * 피해지역 기준 구제급여 총액 조회 JSON
     * 의료비 + 요양생활수당 + 장례비 + 유족보상금 + 재산피해보상금
     */
    @RequestMapping("/adm/relief/getReliefTotal.do")
    @ResponseBody
    public Map getReliefTotal(HttpServletRequest request
    		, @ModelAttribute ReamtPayVO reamtPayVO) throws Exception {
    	
        setGlobalSession(reamtPayVO);
        // -------------------- Default Setting End -----------------------//
        // 피해지역 코드
        String bizAreaCd = reamtPayVO.getBizAreaCd();
        // 구제급여 총액 조회
        String total = reamtPayService.viewReliefTotal(bizAreaCd);
    	// 성공결과 반환
        return getSuccess(total);
    }
    
    /**
     * 2021.12.16 LSH
     * 구상금납부내역 저장처리 (등록,수정)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/relief/saveReamtPay.do")
    @ResponseBody
    public Map saveReamtPay(HttpServletRequest request
			, @RequestBody ReamtPayVO reamtPayVO) throws Exception {
    	
        setGlobalSession(reamtPayVO);
        
        if (reamtPayVO.getUserInfo() != null)
        	reamtPayVO.setGsUserNo(reamtPayVO.getUserInfo().getUserNo());
    	
        // 구상금납부내역를 저장한다.
    	String result = reamtPayService.saveReamtPay(reamtPayVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.12.16 LSH
     * 사후관리(구상권) 현황 엑셀 다운로드
     */
    @RequestMapping("/adm/relief/downReamtPayExcel.do")
    public String downReamtPayExcel(HttpServletRequest request
            , @ModelAttribute ReamtPayVO reamtPayVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reamtPayVO);
        // -------------------- Default Setting End -----------------------//

        List list = reamtPayService.listReamtPay(reamtPayVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "ReamtPay");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "사후관리(구상권)현황");

		return "excelTempView";
    }
}
