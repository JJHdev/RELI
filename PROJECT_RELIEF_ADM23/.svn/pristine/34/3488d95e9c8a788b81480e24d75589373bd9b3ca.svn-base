package business.adm.biz.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.biz.service.SplemntService;
import business.com.biz.service.SplemntVO;
import business.com.biz.service.SplemntValidator;
import business.com.cmm.service.CommService;
import business.com.file.service.AplyFileVO;
import business.sys.code.service.CodeVO;
import common.base.BaseController;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - 보완요청 Controller
 * 
 * @class   : SplemntController
 * @author  : LSH
 * @since   : 2021.10.24
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class SplemntController extends BaseController {

    @Resource(name="SplemntService")
    protected SplemntService splemntService;

    @Resource(name="CommService")
    protected CommService commService;

    // 데이터 검증용 VALIDATOR
    @Autowired 
    private SplemntValidator splemntValidator;

    /**
     * 2021.10.23
     * 구제급여 보완요청 모달팝업 오픈
     */
    @RequestMapping("/adm/biz/modalSplemnt.do")
    public String modalSplemnt(HttpServletRequest request
    		, @RequestBody SplemntVO splemntVO
    		, ModelMap model) throws Exception {

    	String mode = splemntVO.getMode();
    	
    	// 보완내용
    	StringBuilder trsmCn = new StringBuilder("");
    	
    	// 업무메세지 공통코드 조회
    	CodeVO codeVO = commService.getCode(
    						CommConst.CODE_SMSMSG, 
    						CommConst.CODE_SMSMSG_SPPLMNT
    					);
    	if (codeVO != null && 
    		codeVO.getCdCn() != null) {
    		trsmCn.append(codeVO.getCdCn()+"\n");
    	}
    	
    	// 제출기한 업무일수
    	int workCnt = CommConst.SPLEMNT_LIMIT;
    	// 현재일자
    	String currDate = CommUtils.getCurDateString();
    	// 2022.01.20 업무일 기준 30일 이후 날짜 조회
    	String workDate = commService.getWorkDate(workCnt, currDate);
    	// 보완기간날짜 정의 (2022.01.07 수정)
    	String spplDate = CommUtils.formatDateAdd(workDate, 0, "yyyy-MM-dd");
    	// 제출기한 (업무일 30일 이내)
    	String spplText = CommUtils.formatDateAdd(workDate, 0, "MM월dd일");

    	trsmCn.append("o 제출기한 : "+spplText+"까지("+workCnt+"일간)\n");
    	
    	List<AplyFileVO> files = splemntVO.getFileList();
    	if (files != null &&
    		files.size() > 0) {
    		trsmCn.append("o 보완서류 : \n");
    		for (AplyFileVO f : files) {
    			trsmCn.append(" - "+f.getPapeNm()+"\n");
    		}
    	}
    	// 기본 메세지
    	splemntVO.setSplemntDmndCn(trsmCn.toString());
    	// 기본 보완요청일
    	splemntVO.setSplemntDmndYmd(currDate);
    	// 기본 보완기한
    	splemntVO.setSplemntTermYmd(spplDate);
    	// 기본 발신번호
    	splemntVO.setTrnsterNo(CommUtils.formatPhone(CommConst.SMS_TRANSFER_NO));
    	
        model.addAttribute("form", splemntVO);
        
        return "adm/biz/modalSplemnt";
    }

    /**
     * 보완요청 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/biz/getListSplemnt.do")
    @ResponseBody
    public Map getListSplemnt(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute SplemntVO splemntVO
            , ModelMap model) throws Exception {

        setGlobalSession(splemntVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = splemntService.listSplemnt(splemntVO, page, size);
        }
        else {
        	list = splemntService.listSplemnt(splemntVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 보완요청 조회JSON 반환
     */
    @RequestMapping("/adm/biz/getSplemnt.do")
    @ResponseBody
    public Map getSplemnt(HttpServletRequest request
            , @ModelAttribute SplemntVO splemntVO
			, ModelMap model) throws Exception {

        SplemntVO obj = splemntService.viewSplemnt(splemntVO);
        return getSuccess(obj);
    }

    /**
     * 2021.10.26 LSH
     * 보완요청 저장처리 (등록,수정)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/biz/saveSplemnt.do")
    @ResponseBody
    public Map saveSplemnt(HttpServletRequest request
			, @RequestBody SplemntVO splemntVO
			, BindingResult bindingResult) throws Exception {
    	
        setGlobalSession(splemntVO);
        
        if (splemntVO.getUserInfo() != null)
        	splemntVO.setGsUserNo(splemntVO.getUserInfo().getUserNo());
    	
        String mode = splemntVO.getMode();
        
        // 등록인 경우
        if (CommConst.INSERT.equals(mode)) {
        	// 처리상태 (보완요청) 정의
        	splemntVO.setPrcsStusCd(CommConst.SPLEMNT_APPLY);
        }
        // 저장할 입력값 검증
        splemntValidator.validate(splemntVO, bindingResult);
    	
    	// 입력값 검증시 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	logger.info("Splemnt Validate Error...", bindingResult.getAllErrors());
        	return getFailure(bindingResult);
        }
    	
        // 보완요청를 저장한다.
    	String result = splemntService.saveSplemnt(splemntVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}
