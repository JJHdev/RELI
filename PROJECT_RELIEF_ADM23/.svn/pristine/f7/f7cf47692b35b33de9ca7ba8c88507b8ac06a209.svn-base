package business.adm.cmit.web;

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
import business.com.cmit.service.CmitMngVO;
import business.com.cmit.service.MfcmmService;
import business.com.relief.service.ReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 위원정보 Controller
 * 
 * @class   : MfcmmController
 * @author  : LSH
 * @since   : 2023.01.09
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class MfcmmController extends BaseController {

    @Resource(name="MfcmmService")
    protected MfcmmService mfcmmService;
    
    /**
     * 위원정보 화면 오픈
     */
    @RequestMapping("/adm/cmit/listMfcmm.do")
    public String listMfcmm(HttpServletRequest request
	        , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", cmitMngVO);
    	
        return "adm/cmit/listMfcmm";
    }

    /**
     * 위원정보 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/cmit/getListMfcmm.do")
    @ResponseBody
    public Map getListMfcmm(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mfcmmService.listMfcmm(cmitMngVO, page, size);
        }
        else {
        	list = mfcmmService.listMfcmm(cmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원정보 조회JSON 반환
     */
    @RequestMapping("/adm/cmit/getMfcmm.do")
    @ResponseBody
    public Map getMfcmm(HttpServletRequest request
            , @ModelAttribute CmitMngVO cmitMngVO
			, ModelMap model) throws Exception {

        CmitMngVO obj = mfcmmService.viewMfcmm(cmitMngVO);
        return getSuccess(obj);
    }

    /**
     * 위원정보 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/cmit/saveMfcmm.do")
    @ResponseBody
    public Map saveMfcmm(HttpServletRequest request
			, @ModelAttribute CmitMngVO cmitMngVO) throws Exception {
    	
        setGlobalSession(cmitMngVO);
        
        if (cmitMngVO.getUserInfo() != null)
        	cmitMngVO.setGsUserNo(cmitMngVO.getUserInfo().getUserNo());
    	
        // 위원정보를 저장한다.
    	String result = mfcmmService.saveMfcmm(cmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 위원정보 목록 엑셀 다운로드
     */
    @RequestMapping("/adm/cmit/downMfcmmExcel.do")
    public String downMfcmmExcel(HttpServletRequest request
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = mfcmmService.listMfcmm(cmitMngVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "Mfcmm");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "위원 목록");

		return "excelTempView";
    }

    /**
     * 위원임기차수 목록JSON 반환 (EasyUI GRID)
     * - 전문위원회 목록
     * - 심의위원회 목록
     * - 심사위원회 목록
     */
    @RequestMapping("/adm/cmit/getListMfcmmTenure.do")
    @ResponseBody
    public Map getListMfcmmTenure(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = mfcmmService.listMfcmmTenure(cmitMngVO);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원임기차수 저장처리 (다중처리)
     * - 전문위원회 저장
     * - 심의위원회 저장
     * - 심사위원회 저장
     * - saveList  : 등록/수정목록 
     * - removeList: 삭제목록
     */
    @RequestMapping("/adm/cmit/saveMfcmmTenure.do")
    @ResponseBody
    public Map saveMfcmmTenure(HttpServletRequest request
			, @RequestBody CmitMngVO cmitMngVO) throws Exception {
    	
        setGlobalSession(cmitMngVO);
        
        if (cmitMngVO.getUserInfo() != null)
        	cmitMngVO.setGsUserNo(cmitMngVO.getUserInfo().getUserNo());
    	
        // 위원임기차수 다중목록을 저장한다.
    	String result = mfcmmService.saveMfcmmTenure(cmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 위원목록 - 임기이력 조회 모달팝업 오픈
     */
    @RequestMapping("/adm/cmit/modalMfcmmTenure.do")
    public String modalMfcmmTenure(HttpServletRequest request
    		, @ModelAttribute CmitMngVO cmitMngVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(cmitMngVO);
        
        model.addAttribute("form", cmitMngVO);
        
        return "adm/cmit/modalMfcmmTenure";
    }

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/cmit/getListMfcmmTarget.do")
    @ResponseBody
    public Map getListMfcmmTarget(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute CmitMngVO cmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mfcmmService.listMfcmmTarget(cmitMngVO, page, size);
        }
        else {
        	list = mfcmmService.listMfcmmTarget(cmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
}
