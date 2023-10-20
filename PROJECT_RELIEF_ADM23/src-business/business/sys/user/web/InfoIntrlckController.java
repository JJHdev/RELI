package business.sys.user.web;

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
import business.com.support.service.LwstVO;
import business.sys.user.service.InfoIntrlckService;
import business.sys.user.service.InfoIntrlckVO;
import business.sys.user.service.UserInfoVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 정보연동신청 관리 Controller
 * 
 * @class   : InfoIntrlckController
 * @author  : 한금주
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class InfoIntrlckController extends BaseController {

    @Resource(name="InfoIntrlckService")
    protected InfoIntrlckService infoIntrlckService;
    

    /**
     * 정보연동신청 관리 화면 
     */
	@RequestMapping("/sys/user/listInfoIntrlck.do")
	public String listInfoIntrlck(HttpServletRequest request 
			,@ModelAttribute InfoIntrlckVO infoIntrlckVO
			,ModelMap model) throws Exception {
		
		setGlobalSession(infoIntrlckVO);
		infoIntrlckVO.setGsUserNo(infoIntrlckVO.getUserInfo().getUserNo());
		infoIntrlckVO.setRgtrNo(infoIntrlckVO.getGsUserNo());
		infoIntrlckVO.setAplyNo(infoIntrlckVO.getAplyNo());;
		model.addAttribute("model", infoIntrlckVO);
		return "sys/user/listInfoIntrlck";
	}
	
	 /**
     * 정보연동신청 관리 화면 - 정보연동신청 목록 (EasyUI GRID)
     */
	@RequestMapping("/sys/user/getListInfoIntrlck.do")
	@ResponseBody
	public Map getListInfoIntrlck(HttpServletRequest request 
			/* 다중선택항목을 검색조건으로 받을땐 반드시 조건값을 VO객체로 받아야함 */
			,@ModelAttribute InfoIntrlckVO infoIntrlckVO
			,ModelMap model) throws Exception {
		
		// 페이징정보를 받기위한 맵
		Map paramMap = getParameterMap(request, true);

		setGlobalSession(infoIntrlckVO);
		
		List list = null;
		if(paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = infoIntrlckService.listInfoIntrlck(infoIntrlckVO,page,size);
		}
		else {
			list = infoIntrlckService.listInfoIntrlck(infoIntrlckVO);
		}
		return getEasyUIResult(list);
	}
	 /**
     * 정보연동신청 관리 화면 - 정보연동신청 상세보기 (JSON)
     */
	@RequestMapping("/sys/user/viewIntrlckList.do")
	@ResponseBody
	public Map viewIntrlckList(HttpServletRequest request 
			, @ModelAttribute InfoIntrlckVO infoIntrlckVO
			,ModelMap model) throws Exception {
		
		InfoIntrlckVO intrList = infoIntrlckService.viewIntrlckList(infoIntrlckVO.getAplyNo());
		return getSuccess(intrList);
	}
	
	@RequestMapping("/sys/user/downlistInfoIntrlckExcel.do")
    public String downlistInfoIntrlckExcel(HttpServletRequest request
    		, @ModelAttribute InfoIntrlckVO infoIntrlckVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(infoIntrlckVO);
        infoIntrlckVO.setGsUserNo(infoIntrlckVO.getUserInfo().getUserNo());
		infoIntrlckVO.setRgtrNo(infoIntrlckVO.getGsUserNo());
		infoIntrlckVO.setAplyNo(infoIntrlckVO.getAplyNo());

        // 취약계층 신청접수목록
        List list = infoIntrlckService.listInfoIntrlck(infoIntrlckVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "InfoIntrlck_List");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "정보연동관리_정보연동 신청현황");

		return "excelTempView";
    }
	
	 /**
     * 정보연동신청 관리 화면 - 정보연동신청 업데이트
     */
	@RequestMapping("/sys/user/updateIntrlckList.do")
	@ResponseBody
	public Map updateIntrlckList(HttpServletRequest request 
			, @ModelAttribute InfoIntrlckVO infoIntrlckVO
			,Map requestMap) throws Exception {
		setGlobalSession(requestMap);
		infoIntrlckVO.setGsUserNo(infoIntrlckVO.getUserInfo().getUserNo());
		infoIntrlckVO.setRgtrNo(infoIntrlckVO.getGsUserNo());
		infoIntrlckVO.setAplyNo(infoIntrlckVO.getAplyNo());;
		if(requestMap.get("gsUserNo") != null)
			infoIntrlckVO.setGsUserNo((String)requestMap.get("gsUserNo"));
		int result = infoIntrlckService.updateIntrlckList(infoIntrlckVO);
		return getSuccess("Message", result);
	}
}