package business.adm.biz.web;

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

import business.com.biz.service.BizMngService;
import business.com.biz.service.BizMngVO;
import business.com.exmn.service.PrptExmnVO;
import business.sys.user.service.InfoIntrlckVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 사업관리 Controller
 * 
 * @class   : BizMngController
 * @author  : 김주호
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class BizMngController extends BaseController {

    @Resource(name="BizMngService")
    protected BizMngService bizMngService;
    
    /**
     * 사업관리 화면
     */
    @RequestMapping("/adm/biz/listBizMng.do")
    public String listBizMng(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute BizMngVO bizMngVO) throws Exception {
				
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//
        
        
        model.addAttribute("model", bizMngVO);
    	
        return "adm/biz/listBizMng";
    }
    
    
    /**
     * 사업관리화면 - 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/biz/getListBizMng.do")
    @ResponseBody
    public Map getListBizMng(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BizMngVO bizMngVO
            , ModelMap model) throws Exception {
    	
    	// 페이징정보를 받기위한 맵
    	Map paramMap = getParameterMap(request, true);
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = bizMngService.getListBizMng(bizMngVO, page, size);
        }
        else {
        	list = bizMngService.getListBizMng(bizMngVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }
    
	 /**
     * 사업관리 화면 - 사업차수등록 상세보기 (JSON)
     */
	@RequestMapping("/adm/biz/viewListBizMng.do")
	@ResponseBody
	public Map viewListBizMng(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//
	
		
		BizMngVO result = bizMngService.viewListBizMng(bizMngVO);
		
		return getSuccess(result);
	}
	
	
	
	 /**
     * 사업관리 화면 - 신규사업지역등록 (JSON)
     */
	@RequestMapping("/adm/biz/saveNewBiz.do")
	@ResponseBody
	public Map saveNewBiz(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
		
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//
		String bizArea = request.getParameter("newBizArea");
		String bizDtlArea = request.getParameter("newBizDtlArea");
		bizMngVO.setBizArea(bizArea);     // 피해지역
		bizMngVO.setBizDtlArea(bizDtlArea);  // 피해상세지역
		bizMngVO.setBizCn(request.getParameter("newBizCn"));
		bizMngVO.setPolusrcCn(request.getParameter("newPolusrcCn"));// 오염원내용
		bizMngVO.setHrmflns(request.getParameter("newHrmflns")); // 유해인자노출내용
		bizMngVO.setHealthDmgeCn(request.getParameter("newHealthDmgeCn"));  //건강피해내용
		bizMngVO.setAffcScopeCn(request.getParameter("newAffcScopeCn")); // 영향범위내용
		bizMngVO.setExpsrWhlCn(request.getParameter(""));  // 노출기간내용
		bizMngVO.setResiWhlCn(request.getParameter("")); // 거주기간
		bizMngVO.setRgtrNo(bizMngVO.getUserInfo().getUserNo()); 
		
		
		int saveBiz = bizMngService.saveNewBiz(bizMngVO);
		
		if(saveBiz > 0 ) {
			BizMngVO getBizKey = getBizKey(bizMngVO, bizArea, bizDtlArea);
			
			
			  if(getBizKey != null ) { 
			      bizMngVO.setBizAreaCd(getBizKey.getBizAreaCd());
				  saveOder(bizMngVO);
				  
			     return getSuccess();
			  }
			 
			
			
		}
	    	return getFailure();
		
	
	}
	
	public BizMngVO getBizKey(BizMngVO bizMngVO, String bizArea,String bizDtlArea) throws Exception {
		
		BizMngVO addOder = new BizMngVO();
		addOder.setBizArea(bizArea);
		addOder.setBizDtlArea(bizDtlArea);
		
		
		return bizMngService.getBizKey(addOder);
	}
	
	
	public int saveOder(BizMngVO bizMngVO) throws Exception {
		
		int result = bizMngService.saveOder(bizMngVO);
		
		return result;
	}
	
	
	/**
     * 사업관리 화면 - 기존지역 등록차수 검색 (JSON)
     */
	@RequestMapping("/adm/biz/searchGernOder.do")
	@ResponseBody
	public Map searchGernOder(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
		
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//
		String bizArea = request.getParameter("bizArea");
		String bizDtlArea = request.getParameter("bizDtlArea");
		bizMngVO.setBizArea(bizArea);
		bizMngVO.setBizDtlArea(bizDtlArea);
		
	    BizMngVO result = bizMngService.searchGernOder(bizMngVO);
		
		return getSuccess(result);
	}
    
	
	
	/**
     * 사업관리 화면 - 기존지역 차수 추가 등록
     */
	@RequestMapping("/adm/biz/addAreaOder.do")
	@ResponseBody
	public Map addAreaOder(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
	  	Map paramMap = getParameterMap(request, true);
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//

		bizMngVO.setBizOder(request.getParameter("newBizOder"));
		bizMngVO.setBizOderNm(bizMngVO.getBizOder()+"차");
		bizMngVO.setRgtrNo(bizMngVO.getUserInfo().getUserNo()); 
		
	    int result = bizMngService.addAreaOder(bizMngVO);
		
		return getSuccess();
	}
	
	
	/**
     * 사업관리 화면 - 사업 수정
     */
	@RequestMapping("/adm/biz/modifyBiz.do")
	@ResponseBody
	public Map modifyBiz(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
	  	Map paramMap = getParameterMap(request, true);
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//

		bizMngVO.setMdfrNo(bizMngVO.getUserInfo().getUserNo());
	
	    int result = bizMngService.modifyBiz(bizMngVO);
		
		return getSuccess();
	}
	
	
	/**
     * 사업관리 화면 - 사업 차수 삭제
     */
	@RequestMapping("/adm/biz/deleteBiz.do")
	@ResponseBody
	public Map deleteBiz(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
	  	Map paramMap = getParameterMap(request, true);
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//
		bizMngVO.setMdfrNo(bizMngVO.getUserInfo().getUserNo());
		int prptExmnCount =prptExmnCount(bizMngVO);
		
		if(prptExmnCount != 0 ) {
			
			return getFailure("예비조사계획이 존재하여 해당 사업차수를 삭제하지 못했습니다.");
			
		}else {
			  int result = bizMngService.deleteBiz(bizMngVO);
				 // 사업 차수가 없는걸 확인
			  BizMngVO bizOder = bizMngService.oderCount(bizMngVO);
			  if(bizOder.getOderCount() == 0 ) {
				  
					if(allPrptExmnCount(bizMngVO) != 0) {
						return getFailure("예비조사계획이 존재하여 해당 사업을 삭제하지 못했습니다.");
					}
					int allDeleteBizOder = bizMngService.allDeleteBizOder(bizMngVO);
					if(allDeleteBizOder == 0) {
						return getFailure();
					}
			  }
		}
		return getSuccess();
	}
	/**
     * 사업관리 화면 - 사업 차수 삭제중 예비조사 계획 유무 확인 메소드 
     */	
	public int prptExmnCount(BizMngVO bizMngVO) throws Exception {
		
		BizMngVO result = bizMngService.prptExmnCount(bizMngVO);
		
		return result.getPrptCount();
	}
	
	/**
     * 사업관리 화면 - 사업 삭제
     */
	@RequestMapping("/adm/biz/allDeleteBiz.do")
	@ResponseBody
	public Map allDeleteBiz(HttpServletRequest request 
			, @ModelAttribute BizMngVO bizMngVO
			,ModelMap model) throws Exception {
	  	Map paramMap = getParameterMap(request, true);
        setGlobalSession(bizMngVO);
        // -------------------- Default Setting End -----------------------//
		bizMngVO.setMdfrNo(bizMngVO.getUserInfo().getUserNo());
		int prptExmnCount =allPrptExmnCount(bizMngVO);
		
		if(prptExmnCount != 0 ) {
			
			return getFailure("예비조사계획이 존재하여 해당 사업을 삭제하지 못했습니다.");
			
		}else {
			
			  int result = bizMngService.allDeleteBizOder(bizMngVO);
			  
			  
			  if(result ==0) {
				  
				  return getFailure();
				  
			  }
			
		}
		
		
		return getSuccess();
	}
	
	/**
     * 사업관리 화면 - 사업 삭제중 예비조사 계획 유무 확인 메소드 
     */	
	public int allPrptExmnCount(BizMngVO bizMngVO) throws Exception {
		
		BizMngVO result = bizMngService.allPrptExmnCount(bizMngVO);
		
		return result.getPrptCount();
	}
	
}
