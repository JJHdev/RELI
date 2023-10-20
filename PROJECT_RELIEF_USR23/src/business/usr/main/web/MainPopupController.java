package business.usr.main.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.bbs.service.BbsService;
import business.com.bbs.service.BbsVO;
import common.base.BaseController;

/**
 * [컨트롤러클래스] - 메인팝업
 * 
 * @class : MainPopupController
 * @author : 전덕윤
 * @since : 2022.11.02
 * @version : 1.0
 *
 */
@Controller
@SuppressWarnings({ "all" })
public class MainPopupController extends BaseController {

	@Resource(name = "BbsService")
	protected BbsService bbsService;

	
	  /**
	   *  메인화면 공지사항 팝업창(layer) 
	   */
	  @RequestMapping("/usr/cmm/modalNotice.do") 
	  public String modalNotice(BbsVO bbsVO, ModelMap model) throws Exception {
	  	  ArrayList<BbsVO> popupData = bbsService.popupNotice(bbsVO);
		  model.addAttribute("lstPopup", popupData); 
		  return "com/cmm/modalNotice"; 
	  }
	  
	  /**
	   *  메인화면 공지사항 팝업창(window) 
	   */
	  @RequestMapping("/usr/cmm/viewPopupNotice.do") 
	  public String popupNotice(BbsVO bbsVO, ModelMap model) throws Exception {
	  	  BbsVO popupData = bbsService.viewPopupNotice(bbsVO);
		  model.addAttribute("viewPopup", popupData); 
		  return "com/cmm/popupNotice"; 
	  }
	  
	  /**
	   *  메인화면 공지사항 속성정보(window)
	   */
	  @RequestMapping("/usr/cmm/getPopupNoticeInfo.do")
	  @ResponseBody
	  public Map popupNotice(HttpServletRequest request, BbsVO bbsVO, ModelMap model) throws Exception {
		  Map result = new HashMap();
		  ArrayList<BbsVO> popupData = bbsService.popupNotice(bbsVO);
		  result.put("result", popupData);
		  return result;
	  }
	  
}
