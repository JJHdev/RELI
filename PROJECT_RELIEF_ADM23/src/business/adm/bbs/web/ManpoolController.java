package business.adm.bbs.web;

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

import business.com.bbs.service.ManpoolService;
import business.com.bbs.service.ManpoolVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 인력풀 관리 Controller
 * 
 * @class   : ManpoolController
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
public class ManpoolController extends BaseController {

    @Resource(name="ManpoolService")
    protected ManpoolService manpoolService;
    
    /**
     * 게시판 관리 화면
     */
    @RequestMapping("/adm/bbs/listManpool.do")
    public String listManpool(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ManpoolVO manpoolVO) throws Exception {
				
        setGlobalSession(manpoolVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", manpoolVO);
    	
        return "adm/bbs/listManpool";
    }

}
