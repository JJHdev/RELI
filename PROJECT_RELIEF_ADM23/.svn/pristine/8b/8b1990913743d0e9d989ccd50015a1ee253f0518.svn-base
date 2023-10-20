package business.sys.code.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.sys.code.service.SckwndService;
import business.sys.code.service.SckwndVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 상병코드관리 관리 Controller
 * 
 * @class   : SckwndController
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
public class SckwndController extends BaseController {

    @Resource(name="SckwndService")
    protected SckwndService sckwndService;
    
    /**
     * 상병코드관리 화면 오픈
     */
    @RequestMapping("/sys/code/listSckwnd.do")
    public String listSckwnd(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute SckwndVO sckwndVO) throws Exception {
				
        setGlobalSession(sckwndVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", sckwndVO);
    	
        return "sys/code/listSckwnd";
    }

    /**
     * 상병코드 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/sys/code/getListSckwnd.do")
    @ResponseBody
    public List getListSckwnd(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute SckwndVO sckwndVO
            , ModelMap model) throws Exception {

        setGlobalSession(sckwndVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isEmpty(sckwndVO.getId())) {
        	sckwndVO.setId(CommConst.ROOT_CODE);
        }
    	return sckwndService.listSckwnd(sckwndVO);
    }

    /**
     * 상병코드 조회JSON 반환
     */
    @RequestMapping("/sys/code/getSckwnd.do")
    @ResponseBody
    public Map getSckwnd(HttpServletRequest request
            , @ModelAttribute SckwndVO sckwndVO
			, ModelMap model) throws Exception {

        SckwndVO obj = sckwndService.viewSckwnd(sckwndVO);
        return getSuccess(obj);
    }

    /**
     * 상병코드 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/sys/code/saveSckwnd.do")
    @ResponseBody
    public Map saveSckwnd(HttpServletRequest request
			, @ModelAttribute SckwndVO sckwndVO) throws Exception {
    	
        setGlobalSession(sckwndVO);
        
        if (sckwndVO.getUserInfo() != null)
        	sckwndVO.setGsUserNo(sckwndVO.getUserInfo().getUserNo());
    	
        // 상병코드를 저장한다.
    	String result = sckwndService.saveSckwnd(sckwndVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 상병코드 엑셀 다운로드
     */
    @RequestMapping("/sys/code/downSckwndExcel.do")
    public String downSckwndExcel(HttpServletRequest request
            , @ModelAttribute SckwndVO sckwndVO
			, ModelMap model) throws Exception {
    	
        Map paramMap = getParameterMap(request, true);
        // 목록 조회
        List<SckwndVO> list = sckwndService.listSckwnd(sckwndVO);
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "Sckwnd");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "상병코드관리");
		return "excelTempView";
    }
}
