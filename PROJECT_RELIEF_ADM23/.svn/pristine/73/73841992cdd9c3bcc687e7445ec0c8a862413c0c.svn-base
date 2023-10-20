package business.adm.main.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.adm.main.service.AdmMainService;
import business.adm.main.service.AdmMainVO;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.IdntfcVO;
import business.com.relief.service.ReliefVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 개발탬플릿(개발자 표준 가이드용)
 *
 * @class   : SampleController
 * @author  : ntarget
 * @since   : 2021.02.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class AdmMainController extends BaseController {

    @Resource(name="AdmMainService")
    protected AdmMainService mainService;

    @Resource(name="IdntfcService")
    protected IdntfcService idntfcService;

    @RequestMapping("/adm/main/main.do")
    public String main(HttpServletRequest request, ModelMap model
            , @ModelAttribute AdmMainVO mainVO) {
    	
        Map paramMap = getParameterMap(request, true);
        
        return "adm/main";
    }

    /**
     * 2021.11.01 LSH
     * 구제급여 신청상태 조회 JSON 반환
     */
    @RequestMapping("/adm/main/getListReliefStatus.do")
    @ResponseBody
    public Map getListReliefStatus(HttpServletRequest request
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        // -------------------- Default Setting End -----------------------//
        List list = mainService.listReliefStatus();
    	// 성공결과 반환
        return getSuccess(list);
    }
    

    /**
     * 2021.11.02 LSH
     * 종합현황 목록 JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/main/getListReliefTotal.do")
    @ResponseBody
    public Map getListReliefTotal(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(reliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	reliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(reliefVO.getAplyKndList(),"|")
        	);
        }
        // 2022.03.02 [ntarget] 진행상태 배열 -> 문자열로
        /*
        if (CommUtils.isNotEmptyList(reliefVO.getPrgreStusList())) {
        	reliefVO.setPrgreStusStr(
        		CommUtils.mergeString(reliefVO.getPrgreStusList(),"|")
        	);
        }
        */
        // 화면에서 넘어온 페이지번호 (없으면 디폴트 1)
        int page = CommUtils.getInt(paramMap.get("page" ), 1);
        // 화면에서 넘어온 화면출력수 (없으면 디폴트 10)
        int size = CommUtils.getInt(paramMap.get("rows"), basePageSize);
        // 페이징으로 검색한다.
    	List list = mainService.listReliefTotal(reliefVO, page, size);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.11.02 LSH
     * 종합현황 목록 엑셀 다운로드
     */
    @RequestMapping("/adm/main/downReliefTotalExcel.do")
    public String downReliefTotalExcel(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        if (CommUtils.isNotEmptyList(reliefVO.getAplyKndList())) {
        	// 신청종류 조건병합(|) : REGEXP_LIKE를 쓰기위한 조정
        	reliefVO.setSrchAplyKnd(
       			CommUtils.mergeString(reliefVO.getAplyKndList(),"|")
        	);
        }
        // 구제급여 종합현황목록
        List list = mainService.listReliefTotal(reliefVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "ReliefTotal");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "구제급여_종합현황");

		return "excelTempView";
    }

    /**
     * 2021.10.18 LSH
     * 종합현황 상세정보 가져오기
     */
    @RequestMapping("/adm/main/getReliefTotal.do")
    @ResponseBody
    public Map getReliefTotal(
    		HttpServletRequest request, 
            @ModelAttribute ReliefVO reliefVO
    	) throws Exception {
    	
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        // 구제급여 신청정보 상세조회 (피해자정보 포함)
        Map result = mainService.viewReliefTotal(reliefVO);
        if (result != null) {
	        // 세션사용자번호 정의
		    result.put("gsUserNo", reliefVO.getUserInfo().getUserNo());
        }
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 2022.12.15 LSH
     * 종합현황 - 개인별 상세기록카드 피해자개요 정보 가져오기
     * @param idntfcVO.idntfcId 피해자 식별ID
     */
    @RequestMapping("/adm/main/getIdntfcInfo.do")
    @ResponseBody
    public Map getIdntfcInfo(
    		HttpServletRequest request, 
    		@ModelAttribute IdntfcVO idntfcVO
    	) throws Exception {
    	
        setGlobalSession(idntfcVO);
        // -------------------- Default Setting End -----------------------//

        IdntfcVO result = idntfcService.viewIdntfcById(idntfcVO);
        if (result == null)
        	throw new EgovBizException(message.getMessage("exception.NoResult"));
        
    	// 성공결과 반환
        return getSuccess(result);
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 피해구제신청 및 인정현황 목록 JSON 반환
     */
    @RequestMapping("/adm/main/getListReliefRecogn.do")
    @ResponseBody
    public Map getListReliefRecogn(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        List list = mainService.listReliefRecogn(reliefVO.getIdntfcId());
        
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 인정현황 목록 JSON 반환
     */
    @RequestMapping("/adm/main/getListDmgeRecogn.do")
    @ResponseBody
    public Map getListDmgeRecogn(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        List list = mainService.listDmgeRecogn(reliefVO.getIdntfcId());
        
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 상세현황 목록 JSON 반환
     */
    @RequestMapping("/adm/main/getListDmgeDetails.do")
    @ResponseBody
    public Map getListDmgeDetails(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        String idntfcId = reliefVO.getIdntfcId();
        
        // 목록 조회
        List list = mainService.listDmgeDetails(idntfcId);
        // 조회기간 조회
        Map map = mainService.viewDmgeDetailsReview(idntfcId);
        
        Map result = getEasyUIResult(list);
        result.put("Data", map);

        return result; 
    }

    /**
     * 2022.12.19 LSH
     * 종합현황 - 개인기록카드 - 건강피해 영향범위 및 거주기간 조회
     * - 대구(A0001),서천(A0002),김포(A0003) 의 영향범위 데이터 상세조회
     * - 사업지역코드 3가지는 고정 / 기준 사업차수는 '2차'로 고정함
     */
    @RequestMapping("/adm/main/getDmgeAffcScope.do")
    @ResponseBody
    public Map getDmgeAffcScope(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        Map result = mainService.viewDmgeAffcScope(reliefVO.getIdntfcId());
        
    	// 성공결과 반환
        return getSuccess(result);
    }

    /**
     * 2022.12.20 LSH
     * 종합현황 - 개인기록카드 - 구제급여 지급현황 목록 JSON 반환
     */
    @RequestMapping("/adm/main/getListReliefGive.do")
    @ResponseBody
    public Map getListReliefGive(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        String idntfcId = reliefVO.getIdntfcId();
        
        // 목록 조회
        List<Map> list = mainService.listReliefGive(idntfcId);
        
        long totalAmt = 0;
        
        for (Map row : list) {
        	if ("TOTAL".equals(row.get("giveYr"))) {
        		totalAmt = CommUtils.getLong(row, "brvfmRwrdAmt") 
        				 + CommUtils.getLong(row, "fnrlCstAmt"  ) 
        				 + CommUtils.getLong(row, "mcpAmt"      ) 
        				 + CommUtils.getLong(row, "rcpAmt"      );
        		break;
        	}
        }
        // 지급총액
        Map map = new HashMap();
        map.put("totalAmt", totalAmt); 
        
        Map result = getEasyUIResult(list);
        result.put("Data", map);

        return result; 
    }

    /**
     * 2022.12.20 LSH
     * 종합현황 - 개인기록카드 - 민원응대이력 목록 JSON 반환
     * TODO. 다음 프로젝트 고도화에 개발할 영역임
     */
    @RequestMapping("/adm/main/getListCmplHst.do")
    @ResponseBody
    public Map getListCmplHst(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        String idntfcId = reliefVO.getIdntfcId();
        
        // 목록 조회
        List list = mainService.listCmplHst(idntfcId);
        
        return getEasyUIResult(list);
    }

    /**
     * 2022.12.20 LSH
     * 종합현황 - 개인기록카드 - 피해등급 조회
     */
    @RequestMapping("/adm/main/getDmgeGrd.do")
    @ResponseBody
    public Map getDmgeGrd(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        String idntfcId = reliefVO.getIdntfcId();
        
        Map data = new HashMap();
        // 2022.12.21 피해등급의 평가질환 목록 조회
        List<Map> list = mainService.listDmgeGrdMattr(idntfcId);
        
        if (list != null && 
        	list.size() > 0) {
        	data.put("lastDmgeGrdCd", list.get(0).get("lastDmgeGrdCd"));
        	data.put("lastDmgeScre" , list.get(0).get("lastDmgeScre" ));
        }
        // 성공결과
        Map result = getSuccess(data);
        if (data != null) {
            result.put("rows", list);
        }
        return result;
    }
}
