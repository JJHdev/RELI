package business.bio.cmit.web;

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

import business.bio.cmit.service.BioCmitMngVO;
import business.bio.cmit.service.BioMfcmmService;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 살생물제품 위원정보 Controller
 * 
 * @class   : BioMfcmmController
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class BioMfcmmController extends BaseController {

    @Resource(name="BioMfcmmService")
    protected BioMfcmmService mfcmmService;
    
    /**
     * 위원정보 화면 오픈
     */
    @RequestMapping("/adm/bio/listBioMfcmm.do")
    public String listBioMfcmm(HttpServletRequest request
	        , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", bioCmitMngVO);
    	
        return "adm/bio/listBioMfcmm";
    }

    /**
     * 위원정보 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/bio/getListBioMfcmm.do")
    @ResponseBody
    public Map getListBioMfcmm(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mfcmmService.listBioMfcmm(bioCmitMngVO, page, size);
        }
        else {
        	list = mfcmmService.listBioMfcmm(bioCmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원정보 조회JSON 반환
     */
    @RequestMapping("/adm/bio/getBioMfcmm.do")
    @ResponseBody
    public Map getBioMfcmm(HttpServletRequest request
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
			, ModelMap model) throws Exception {

        BioCmitMngVO obj = mfcmmService.viewBioMfcmm(bioCmitMngVO);
        return getSuccess(obj);
    }

    /**
     * 위원정보 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/bio/saveBioMfcmm.do")
    @ResponseBody
    public Map saveBioMfcmm(HttpServletRequest request
			, @ModelAttribute BioCmitMngVO bioCmitMngVO) throws Exception {
    	
        setGlobalSession(bioCmitMngVO);
        
        if (bioCmitMngVO.getUserInfo() != null)
        	bioCmitMngVO.setGsUserNo(bioCmitMngVO.getUserInfo().getUserNo());
    	
        // 위원정보를 저장한다.
    	String result = mfcmmService.saveBioMfcmm(bioCmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 위원정보 목록 엑셀 다운로드
     */
    @RequestMapping("/adm/bio/downBioMfcmmExcel.do")
    public String downBioMfcmmExcel(HttpServletRequest request
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = mfcmmService.listBioMfcmm(bioCmitMngVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "BioMfcmm");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "살생물제품_피해구제_위원목록");

		return "excelTempView";
    }

    /**
     * 위원임기차수 목록JSON 반환 (EasyUI GRID)
     * - 전문위원회 목록
     * - 심의위원회 목록
     * - 심사위원회 목록
     */
    @RequestMapping("/adm/bio/getListBioMfcmmTenure.do")
    @ResponseBody
    public Map getListBioMfcmmTenure(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = mfcmmService.listBioMfcmmTenure(bioCmitMngVO);
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
    @RequestMapping("/adm/bio/saveBioMfcmmTenure.do")
    @ResponseBody
    public Map saveBioMfcmmTenure(HttpServletRequest request
			, @RequestBody BioCmitMngVO bioCmitMngVO) throws Exception {
    	
        setGlobalSession(bioCmitMngVO);
        
        if (bioCmitMngVO.getUserInfo() != null)
        	bioCmitMngVO.setGsUserNo(bioCmitMngVO.getUserInfo().getUserNo());
    	
        // 위원임기차수 다중목록을 저장한다.
    	String result = mfcmmService.saveBioMfcmmTenure(bioCmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 위원목록 - 임기이력 조회 모달팝업 오픈
     */
    @RequestMapping("/adm/bio/modalBioMfcmmTenure.do")
    public String modalBioMfcmmTenure(HttpServletRequest request
    		, @ModelAttribute BioCmitMngVO bioCmitMngVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(bioCmitMngVO);
        
        model.addAttribute("form", bioCmitMngVO);
        
        return "adm/bio/modalBioMfcmmTenure";
    }

    /**
     * 2023.01.12 LSH
     * 위원회 등록대상 위원정보 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/bio/getListBioMfcmmTarget.do")
    @ResponseBody
    public Map getListBioMfcmmTarget(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mfcmmService.listBioMfcmmTarget(bioCmitMngVO, page, size);
        }
        else {
        	list = mfcmmService.listBioMfcmmTarget(bioCmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
}
