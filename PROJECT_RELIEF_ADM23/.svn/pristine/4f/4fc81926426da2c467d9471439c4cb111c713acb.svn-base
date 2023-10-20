package business.adm.biz.web;

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
import business.com.biz.service.MngHstService;
import business.com.biz.service.MngHstVO;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 관리이력정보 Controller
 * 
 * @class   : MngHstController
 * @author  : LSH
 * @since   : 2021.10.21
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class MngHstController extends BaseController {

    @Resource(name="MngHstService")
    protected MngHstService mngHstService;

    /**
     * 2021.10.23
     * 이력관리 모달팝업 오픈
     */
    @RequestMapping("/adm/biz/modalMngHst.do")
    public String modalMngHst(HttpServletRequest request
    		, @RequestBody MngHstVO mngHstVO
    		, ModelMap model) throws Exception {

    	setGlobalSession(mngHstVO);
        // -------------------- Default Setting End -----------------------//

    	String mode = mngHstVO.getMode();
    	String sn   = mngHstVO.getSn();
    	
    	// 등록인 경우
    	if (CommUtils.isEqual(mode, CommConst.INSERT)) {
    		mngHstVO.setRgtrNo(mngHstVO.getUserInfo().getUserNo());
    		mngHstVO.setRgtrNm(mngHstVO.getUserInfo().getUserNm());
    		mngHstVO.setRegDate(CommUtils.getCurDate());
    	}
    	// 수정,조회인 경우
    	else {
    		mngHstVO = mngHstService.viewMngHst(sn);
    		mngHstVO.setMode(mode);
    	}
        model.addAttribute("form", mngHstVO);
        
        return "adm/biz/modalMngHst";
    }

    /**
     * 관리이력정보 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/biz/getListMngHst.do")
    @ResponseBody
    public Map getListMngHst(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute MngHstVO mngHstVO
            , ModelMap model) throws Exception {

        setGlobalSession(mngHstVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = mngHstService.listMngHst(mngHstVO, page, size);
        }
        else {
        	list = mngHstService.listMngHst(mngHstVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 관리이력정보 조회JSON 반환
     */
    @RequestMapping("/adm/biz/getMngHst.do")
    @ResponseBody
    public Map getMngHst(HttpServletRequest request
            , @ModelAttribute MngHstVO mngHstVO
			, ModelMap model) throws Exception {

        MngHstVO obj = mngHstService.viewMngHst(mngHstVO.getSn());
        return getSuccess(obj);
    }

    /**
     * 관리이력정보 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/biz/saveMngHst.do")
    @ResponseBody
    public Map saveMngHst(HttpServletRequest request
			, @RequestBody MngHstVO mngHstVO) throws Exception {
    	
        setGlobalSession(mngHstVO);
        
        if (mngHstVO.getUserInfo() != null)
        	mngHstVO.setGsUserNo(mngHstVO.getUserInfo().getUserNo());
    	
        // 관리이력정보를 저장한다.
    	String result = mngHstService.saveMngHst(mngHstVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}
