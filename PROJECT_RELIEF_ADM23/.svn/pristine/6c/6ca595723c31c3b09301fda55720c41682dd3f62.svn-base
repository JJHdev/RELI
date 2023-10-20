package business.bio.relief.web;

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

import business.bio.relief.service.BioMngHstService;
import business.bio.relief.service.BioMngHstVO;
import business.com.CommConst;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 살생물제품 관리이력정보 Controller
 * 
 * @class   : BioMngHstController
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class BioMngHstController extends BaseController {

    @Resource(name="BioMngHstService")
    protected BioMngHstService mngHstService;

    /**
     * 이력관리 모달팝업 오픈
     */
    @RequestMapping("/adm/bio/modalBioMngHst.do")
    public String modalBioMngHst(HttpServletRequest request
    		, @ModelAttribute BioMngHstVO bioMngHstVO
    		, ModelMap model) throws Exception {

    	setGlobalSession(bioMngHstVO);
        // -------------------- Default Setting End -----------------------//

    	String mode = bioMngHstVO.getMode();
    	String sn   = bioMngHstVO.getSn();
    	
    	// 등록인 경우
    	if (CommUtils.isEqual(mode, CommConst.INSERT)) {
    		bioMngHstVO.setRgtrNo(bioMngHstVO.getUserInfo().getUserNo());
    		bioMngHstVO.setRgtrNm(bioMngHstVO.getUserInfo().getUserNm());
    		bioMngHstVO.setRegDate(CommUtils.getCurDate());
    	}
    	// 수정,조회인 경우
    	else {
    		bioMngHstVO = mngHstService.viewBioMngHst(sn);
    		bioMngHstVO.setMode(mode);
    	}
        model.addAttribute("form", bioMngHstVO);
        
        return "adm/bio/modalBioMngHst";
    }

    /**
     * 관리이력정보 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/bio/getListBioMngHst.do")
    @ResponseBody
    public Map getListBioMngHst(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioMngHstVO bioMngHstVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioMngHstVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mngHstService.listBioMngHst(bioMngHstVO, page, size);
        }
        else {
        	list = mngHstService.listBioMngHst(bioMngHstVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 관리이력정보 조회JSON 반환
     */
    @RequestMapping("/adm/bio/getBioMngHst.do")
    @ResponseBody
    public Map getBioMngHst(HttpServletRequest request
            , @ModelAttribute BioMngHstVO bioMngHstVO
			, ModelMap model) throws Exception {

        BioMngHstVO obj = mngHstService.viewBioMngHst(bioMngHstVO.getSn());
        return getSuccess(obj);
    }

    /**
     * 관리이력정보 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/bio/saveBioMngHst.do")
    @ResponseBody
    public Map saveBioMngHst(HttpServletRequest request
			, @RequestBody BioMngHstVO bioMngHstVO) throws Exception {
    	
        setGlobalSession(bioMngHstVO);
        
        if (bioMngHstVO.getUserInfo() != null)
        	bioMngHstVO.setGsUserNo(bioMngHstVO.getUserInfo().getUserNo());
    	
        // 관리이력정보를 저장한다.
    	String result = mngHstService.saveBioMngHst(bioMngHstVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}
