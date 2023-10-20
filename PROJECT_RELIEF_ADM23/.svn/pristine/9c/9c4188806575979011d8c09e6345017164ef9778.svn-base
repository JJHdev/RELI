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

import business.bio.cmit.service.BioCmitMngService;
import business.bio.cmit.service.BioCmitMngVO;
import business.com.CommConst;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 살생물제품 위원회관리 Controller
 * 
 * @class   : BioCmitMngController
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class BioCmitMngController extends BaseController {

    @Resource(name="BioCmitMngService")
    protected BioCmitMngService cmitMngService;
    
    
    /**
     * 위원회 관리 화면 오픈
     */
    @RequestMapping("/adm/bio/listBioCmitMng.do")
    public String listBioCmitMng(HttpServletRequest request
	        , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", bioCmitMngVO);
    	
        return "adm/bio/listBioCmitMng";
    }

    /**
     * 위원회 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/bio/getListBioCmitDmge.do")
    @ResponseBody
    public Map getListBioCmitDmge(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = cmitMngService.listBioCmitDmge(bioCmitMngVO, page, size);
        }
        else {
        	list = cmitMngService.listBioCmitDmge(bioCmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원회 조회JSON 반환
     */
    @RequestMapping("/adm/bio/getBioCmitDmge.do")
    @ResponseBody
    public Map getBioCmitDmge(HttpServletRequest request
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
			, ModelMap model) throws Exception {

    	BioCmitMngVO obj = cmitMngService.viewBioCmitDmge(bioCmitMngVO);
        return getSuccess(obj);
    }

    /**
     * 위원회 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/bio/saveBioCmitDmge.do")
    @ResponseBody
    public Map saveBioCmitDmge(HttpServletRequest request
			, @ModelAttribute BioCmitMngVO bioCmitMngVO) throws Exception {
    	
        setGlobalSession(bioCmitMngVO);
        
        if (bioCmitMngVO.getUserInfo() != null)
        	bioCmitMngVO.setGsUserNo(bioCmitMngVO.getUserInfo().getUserNo());
    	
        // 위원회피해조사를 저장한다.
    	String result = cmitMngService.saveBioCmitDmge(bioCmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 위원회 목록 엑셀 다운로드
     */
    @RequestMapping("/adm/bio/downBioCmitDmgeExcel.do")
    public String downBioMfcmmExcel(HttpServletRequest request
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//
        
        List list = cmitMngService.listBioCmitDmge(bioCmitMngVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "BioCmitDmge");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "살생물제품_피해구제_위원회목록");

		return "excelTempView";
    }

    /**
     * 위원회 소속 위원 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/bio/getListBioCmitMng.do")
    @ResponseBody
    public Map getListBioCmitMng(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioCmitMngVO bioCmitMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioCmitMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = cmitMngService.listBioCmitMng(bioCmitMngVO, page, size);
        }
        else {
        	list = cmitMngService.listBioCmitMng(bioCmitMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 위원회 소속 위원 저장처리 (다중처리)
     * - saveList  : 등록목록 
     * - removeList: 삭제목록
     */
    @RequestMapping("/adm/bio/saveBioCmitMng.do")
    @ResponseBody
    public Map saveBioCmitMng(HttpServletRequest request
			, @RequestBody BioCmitMngVO bioCmitMngVO) throws Exception {
    	
        setGlobalSession(bioCmitMngVO);
        
        if (bioCmitMngVO.getUserInfo() != null)
        	bioCmitMngVO.setGsUserNo(bioCmitMngVO.getUserInfo().getUserNo());
    	
        // 위원회 소속 위원 다중목록을 저장한다.
    	String result = cmitMngService.saveBioCmitMng(bioCmitMngVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 위원회 소속 위원 등록 모달팝업 오픈
     */
    @RequestMapping("/adm/bio/modalBioCmitMng.do")
    public String modalBioCmitMng(HttpServletRequest request
    		, @ModelAttribute BioCmitMngVO bioCmitMngVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(bioCmitMngVO);
        
        // 위원회정보를 조회한다.
        BioCmitMngVO obj = cmitMngService.viewBioCmitDmge(bioCmitMngVO);
        // 위원회등록대상여부 정의
        obj.setSrchMngYn(CommConst.YES);
        
        model.addAttribute("form", obj);
        
        return "adm/bio/modalBioCmitMng";
    }
}
