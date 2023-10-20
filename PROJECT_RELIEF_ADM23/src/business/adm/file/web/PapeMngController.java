package business.adm.file.web;

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

import business.com.file.service.PapeMngService;
import business.com.file.service.PapeMngVO;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 서류양식관리 Controller
 *
 * @class   : PapeMngController
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class PapeMngController extends BaseController {

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngService;

    /**
     * 서류양식관리 화면 오픈
     */
    @RequestMapping("/adm/file/listPapeMng.do")
    public String openPapeMng(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute PapeMngVO papeMngVO) throws Exception {

        setGlobalSession(papeMngVO);
        // -------------------- Default Setting End -----------------------//

//        // 공통 코드 조회 (급여종류 : 공통서류/의료비/요행생활수당/장의비 및 유족보상비/추가서류)
//        model.addAttribute("listUpPape",papeMngService.listPapeMngUpPapeAll(null));

        model.addAttribute("model", papeMngVO);

        return "adm/file/listPapeMng";
    }

    /**
     * 서류양식관리 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/file/getListPapeMng.do")
    @ResponseBody
    public Map getListPapeMng(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {

        setGlobalSession(papeMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = papeMngService.listPapeMng(papeMngVO, page, size);
        }
        else {
        	list = papeMngService.listPapeMng(papeMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 서류양식관리 조회JSON 반환
     */
    @RequestMapping("/adm/file/getPapeMng.do")
    @ResponseBody
    public Map getPapeMng(HttpServletRequest request
            , @ModelAttribute PapeMngVO papeMngVO
			, ModelMap model) throws Exception {

        PapeMngVO obj = papeMngService.viewPapeMng(papeMngVO);
        return getSuccess(obj);
    }

    /**
     * 서류양식관리 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/file/savePapeMng.do")
    @ResponseBody
    public Map savePapeMng(HttpServletRequest request
			, @ModelAttribute PapeMngVO papeMngVO) throws Exception {

        setGlobalSession(papeMngVO);
        try {
            if (papeMngVO.getUserInfo() != null)
            	papeMngVO.setGsUserNo(papeMngVO.getUserInfo().getUserNo());

            // 서류양식관리를 저장한다.
        	String result = papeMngService.savePapeMng(papeMngVO);

        	// 성공결과 반환
            return getSuccess("Message", result);
        } catch (Exception e){
            return getFailure(e.getMessage());
        }
    }

    /**
     * '급여종류' 목록 조회 (신청 구분에 속하는 모든 '급여종류' 목록)
     * * Tab 화면 구성에 사용
     */
    @RequestMapping("/adm/file/getPapeMngUpPapeList.do")
    @ResponseBody
    public List getPapeMngUpPapeList(HttpServletRequest request
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {

        List list = papeMngService.listPapeMngUpPapeAll(papeMngVO);
        return list;
    }

    /**
     * UpPape(급여종류:공통서류/의료비...)에 속하는 모든 제출서류 코드 목록 조회
     */
    @RequestMapping("/adm/file/listPapeMngPapeCodeByUpPape.do")
    @ResponseBody
    public List listPapeMngPapeCodeByUpPape(HttpServletRequest request
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {

        List list = papeMngService.listPapeMngPapeCodeByUpPape(papeMngVO);
        return list;
    }
}
