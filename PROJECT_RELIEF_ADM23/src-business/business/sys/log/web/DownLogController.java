package business.sys.log.web;

import java.util.ArrayList;
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

import business.com.CommBizUtils;
import business.com.file.service.AplyFileService;
import business.com.file.service.AplyFileVO;
import business.sys.log.service.DownLogService;
import business.sys.log.service.LogVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 다운로드이력 Controller
 *
 * @class   : DownLogController
 * @author  : LSH
 * @since   : 2021.11.04
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class DownLogController extends BaseController {

    @Resource(name="DownLogService")
    protected DownLogService downLogService;

    @Resource(name="AplyFileService")
    protected AplyFileService aplyFileService;

    /**
     * 다운로드이력 관리 화면
     */
    @RequestMapping("/sys/log/listDownLog.do")
    public String listDownLog(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute LogVO logVO) throws Exception {

        setGlobalSession(logVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", logVO);

        return "sys/log/listDownLog";
    }

    /**
     * 다운로드이력 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/sys/log/getListDownLog.do")
    @ResponseBody
    public Map getListDownLog(HttpServletRequest request, @RequestParam Map<String,String> reqMap
            , @ModelAttribute LogVO logVO, ModelMap model) throws Exception {

        setGlobalSession(logVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = downLogService.listDownLog(logVO, page, size);
        }
        else {
        	list = downLogService.listDownLog(logVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 다운로드 이력 엑셀 다운로드
     */
    @RequestMapping("/sys/log/downDownLogExcel.do")
    public String downDownLogExcel(HttpServletRequest request
            , @ModelAttribute LogVO logVO, ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);

        setGlobalSession(logVO);
        logVO.setGsUserNo(logVO.getUserInfo().getUserNo());
        logVO.setGsUserNm(logVO.getUserInfo().getUserNm());
        // -------------------- Default Setting End -----------------------//

        // 취약계층 신청접수목록
        List list = downLogService.listDownLog(logVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());

        model.addAttribute(ExcelTempView.DATA_KEY    , list);
        model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
        model.addAttribute(ExcelTempView.TEMPLATE_KEY, "DownLog");
        model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "다운로드이력_현황");

        return "excelTempView";
    }

    /**
     * 다운로드이력 저장처리
     * 2021.12.10 CSLEE 다중건 다운로드이력 등록 추가
     * 2022.01.07 LSH   문서번호 입력시 신청정보 검색후 다운로드이력 등록 추가
     */
    @RequestMapping("/sys/log/saveDownLog.do")
    @ResponseBody
    public Map saveDownLog(
    		HttpServletRequest request,
    		@RequestBody LogVO logVO
    	) throws Exception {

        setGlobalSession(logVO);
        // -------------------- Default Setting End -----------------------//

        String atchFileSn        = null;
        List<String> atchFileSns = null;

        // 2022.01.07 LSH 문서번호가 있을 경우
        if (CommUtils.isNotEmpty(logVO.getDtySeCd()) &&
        	CommUtils.isNotEmpty(logVO.getDcmtNo())) {

        	AplyFileVO aplyFileVO = AplyFileVO.builder()
        			.dtySeCd(logVO.getDtySeCd())
        			.dcmtNo(logVO.getDcmtNo())
        			.dtlDcmtNo(logVO.getDtlDcmtNo())
        			.build();
            // 다운로드할 파일 목록
            List<Map> list = aplyFileService.listAplySubmitFile(aplyFileVO);
            if (list != null && list.size() > 0) {
            	atchFileSns = new ArrayList<String> ();
                for (Map vo : list) {
                	atchFileSns.add(String.valueOf(vo.get("sn")));
                }
            }
        }
        // 단건 처리
        else if(logVO.getAtchFileSns() == null || logVO.getAtchFileSns().size() == 0) {
            atchFileSn = logVO.getAtchFileSn();
        }
        // 다중 처리
        else {
            atchFileSns= logVO.getAtchFileSns();
        }

        LogVO params = LogVO.builder()
        		.userNo     (CommUtils.nvlTrim(logVO.getUserInfo().getUserNo(),"guest"))
        		.srvrNm     (CommBizUtils.getServerName(request))
        		.ipAddr     (request.getRemoteAddr())
        		.sysCd      ((String)request.getAttribute("sysCd"))
        		.progUrl    (logVO.getProgUrl()) // CommBizUtils.getRequestURL(request)
        		.downResn   (logVO.getDownResn())
        		.atchFileSn (atchFileSn)
        		.atchFileSns(atchFileSns)
        		.build();

		String result = null;
		if(atchFileSns == null) {
		    result = downLogService.regiDownLog(params);
		}
		else {
		    result = downLogService.regiDownLogs(params);
		}
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 다운로드이력 다중삭제처리
     */
    @RequestMapping("/sys/log/deltDownLog.do")
    @ResponseBody
    public Map deltDownLog(
    		HttpServletRequest request,
    		@RequestBody LogVO logVO
    	) throws Exception {
        // 다운로드이력를 다중삭제한다.
    	String result = downLogService.deltDownLog(logVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
}
