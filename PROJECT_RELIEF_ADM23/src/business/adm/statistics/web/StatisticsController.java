package business.adm.statistics.web;

import java.util.ArrayList;
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

import business.com.statistics.service.StatisticsService;
import business.com.statistics.service.StatisticsVO;
import common.base.BaseController;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 통계 관리 Controller
 *
 * @class   : StatisticsController
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class StatisticsController extends BaseController {

    @Resource(name="StatisticsService")
    protected StatisticsService statisticsService;

    /**
     * 건강피해 인정현황 통계 화면
     */
    @RequestMapping("/adm/statistics/viewDmgRcogn.do")
    public String viewDmgRcogn(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute StatisticsVO StatisticsVO) throws Exception {

        setGlobalSession(StatisticsVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", StatisticsVO);
        StatisticsVO.setBasisDateString(CommUtils.formatCurDate("yy.M.dd"));

        return "adm/statistics/viewDmgRcogn";
    }

    /**
     * 건강피해 인정현황 조회 (JSON GRID)
     * 2021.12.15 LSH [1,2차통합] 건강피해 인정현황추가
     */
    @RequestMapping("/adm/statistics/getListDmgRcogn.do")
    @ResponseBody
    public Map getListDmgRcogn(HttpServletRequest request) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
		String mode = (String)paramMap.get("mode");

		List list = null;
		if ("YEAR".equals(mode))
			list = statisticsService.listYearDmgRcogn(paramMap);
		else if ("AREA".equals(mode))
			list = statisticsService.listAreaDmgRcogn(paramMap);
		else
			list = statisticsService.listTotalDmgRcogn(paramMap);
		// Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 건강피해 인정현황 엑셀 다운로드
     */
    @RequestMapping("/adm/statistics/downDmgRcognExcel.do")
    public String downDmgRcognExcel(HttpServletRequest request
            , ModelMap model) throws Exception {

    	Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());

    	String mode = (String)paramMap.get("mode");

		if ("YEAR".equals(mode)) {
			model.addAttribute(ExcelTempView.DATA_KEY    , statisticsService.listYearDmgRcogn(paramMap));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatDmgRcognYear");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "연도별_건강피해_인정현황");
		}
		else if ("AREA".equals(mode)) {
			model.addAttribute(ExcelTempView.DATA_KEY    , statisticsService.listAreaDmgRcogn(paramMap));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatDmgRcognArea");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "사업별_건강피해_인정현황");
		}
		else {
			model.addAttribute(ExcelTempView.DATA_KEY    , statisticsService.listTotalDmgRcogn(paramMap));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatDmgRcognTotal");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "1차사업2차사업통합_건강피해_인정현황");
		}
		return "excelTempView";
    }

    /**
     * 지역별 피해구제 신청자 현황 통계 화면
     */
    @RequestMapping("/adm/statistics/viewReliefAply.do")
    public String viewReliefAply(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute StatisticsVO StatisticsVO) throws Exception {

        setGlobalSession(StatisticsVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", StatisticsVO);
        StatisticsVO.setBasisDateString(CommUtils.formatCurDate("yy.M.dd"));

        return "adm/statistics/viewReliefAply";
    }

    /**
     * 지역별 피해구제 신청현황 조회 (JSON GRID)
     */
    @RequestMapping("/adm/statistics/getListReliefAply.do")
    @ResponseBody
    public Map getListReliefAply(HttpServletRequest request) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        String mode = (String)paramMap.get("bizAreaCd");

        Map data = statisticsService.viewReliefAply(paramMap);
		// 차트데이터로 변환한다.
		Map result = getReliefAplyChart(data, mode);
		return result;
    }

    /**
     * 지역별 피해구제 신청인 현황 데이터를 차트데이터로 변환한다.
     */
    private Map getReliefAplyChart(Map data, String mode) {

    	Map result = getSuccess();

		// 대구 (Pie Chart)
		if ("A0001".equals(mode)) {

			String[] fields = {"aplyD01","aplyR01"};
			List<String>  labels = new ArrayList();
			List<Integer> values = new ArrayList();

			for (String field : fields) {
				labels.add(GraphLabel.get(field).getLabel());
				values.add(CommUtils.getInt(data, field));
			}

			Map header = new HashMap();
			header.put("valueT0T", GraphLabel.T0T.getValue(data));
			header.put("labelT01", GraphLabel.T01.getLabel());
			header.put("valueT01", GraphLabel.T01.getValue(data));

			result.put("labels", labels);
			result.put("values", values);
			result.put("header", header);
			return result;
		}
		// 김포 (Doughnut Chart)
		else if ("A0003".equals(mode)) {

			String[] fields = {"aplyR02","aplyD02","aplyE02"};
			List<String>  labels = new ArrayList();
			List<Integer> values = new ArrayList();

			for (String field : fields) {
				labels.add(GraphLabel.get(field).getLabel());
				values.add(CommUtils.getInt(data, field));
			}

			Map header = new HashMap();
			header.put("valueT0T", GraphLabel.T0T.getValue(data));
			header.put("labelT01", GraphLabel.T01.getLabel());
			header.put("valueT01", GraphLabel.T01.getValue(data));
			header.put("labelT02", GraphLabel.T02.getLabel());
			header.put("valueT02", GraphLabel.T02.getValue(data));
			header.put("labelCricle", GraphLabel.R01R02.getLabel());
			header.put("valueCricle", GraphLabel.R01R02.getValue(data));

			result.put("labels", labels);
			result.put("values", values);
			result.put("header", header);
			return result;
		}
		// 서천 (이미지그래프)
		else if ("A0002".equals(mode)) {
			Map labels = new HashMap();
			for (GraphLabel gl : GraphLabel.values()) {
				if (data.containsKey(gl.getField())) {
					labels.put(gl.getField(), gl.getLabel());
				}
			}

			Map header = new HashMap();
			header.put("valueT0T", GraphLabel.T0T.getValue(data));

			result.put("labels", labels);
			result.put("values", data);
			result.put("header", header);
			return result;
		}
		return getFailure();
    }

    // 지역별 피해구제 신청인 현황 레이블 정의
    static enum GraphLabel {
    	T0T           			("aplyT0T",             "전체 인원"),
    	T01           			("aplyT01",             "1차 사업"),
    	T02           			("aplyT02",             "2차 사업"),
    	R01           			("aplyR01",             "1차 인정"),
    	R02           			("aplyR02",             "2차 인정"),
    	D01           			("aplyD01",             "1차 불인정"),
    	D02           			("aplyD02",             "2차 불인정"),
    	E02           			("aplyE02",             "2차 조사중"),
    	R01R02        			("aplyR01R02",          "1차,2차 모두 인정"),
    	R01D02        			("aplyR01D02",          "1차 인정, 2차 불인정"),
    	D01R02        			("aplyD01R02",          "1차 불인정, 2차 인정"),
    	D01D02      			("aplyD01D02",          "1차,2차 모두 불인정")
    	;

	    private String field;
		private String label;

		private GraphLabel(String field, String label) {
			this.field = field;
			this.label = label;
		}

		public String getField() {
			return this.field;
		}
		public String getLabel() {
			return this.label;
		}
		public Object getValue(Map map) {
			return map.get(this.field);
		}
		public static GraphLabel get(String field) {
			if (field == null)
				return null;
			for (GraphLabel c : values()) {
				if (field.equals(c.field))
					return c;
			}
			return null;
		}
    };

    /**
     * 구제급여 지급현황 통계 화면
     */
    @RequestMapping("/adm/statistics/viewReliefGive.do")
    public String viewReliefGive(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute StatisticsVO StatisticsVO) throws Exception {

        setGlobalSession(StatisticsVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", StatisticsVO);
        StatisticsVO.setBasisDateString(CommUtils.formatCurDate("yy.M.dd"));

        return "adm/statistics/viewReliefGive";
    }

    /**
     * 구제급여 지급현황 조회 (JSON GRID)
     */
    @RequestMapping("/adm/statistics/getListReliefGive.do")
    @ResponseBody
    public Map getListReliefGive(HttpServletRequest request) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
		String mode = (String)paramMap.get("mode");

		List list = null;
		if ("YEAR".equals(mode)) {
			list = statisticsService.listYearReliefGive(paramMap);
		}
		else {
			list = statisticsService.listAreaReliefGive(paramMap);
		}
		// Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 구제급여 지급현황 엑셀 다운로드
     */
    @RequestMapping("/adm/statistics/downReliefGiveExcel.do")
    public String downReliefGiveExcel(HttpServletRequest request
            , ModelMap model) throws Exception {

    	Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());

    	String mode = (String)paramMap.get("mode");

		if ("YEAR".equals(mode)) {
			Map result = statisticsService.listYearReliefGiveExcel(paramMap);
			paramMap.put("headers", result.get("headers"));

			model.addAttribute(ExcelTempView.DATA_KEY    , result.get("list"));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatReliefGiveYear");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "연도별_구제급여_지급현황");
		}
		else {
			model.addAttribute(ExcelTempView.DATA_KEY    , statisticsService.listAreaReliefGive(paramMap));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatReliefGiveArea");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "항목별_구제급여_지급현황");
		}
		return "excelTempView";
    }

    /**
     * 인정질환별 인정자 현황 통계 화면
     */
    @RequestMapping("/adm/statistics/viewDissRcogn.do")
    public String viewDissRcogn(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute StatisticsVO StatisticsVO) throws Exception {

        setGlobalSession(StatisticsVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", StatisticsVO);
        StatisticsVO.setBasisDateString(CommUtils.formatCurDate("yy.M.dd"));

        return "adm/statistics/viewDissRcogn";
    }

    /**
     * 인정질환별 인정자 현황 조회 (JSON GRID)
     */
    @RequestMapping("/adm/statistics/getListDissRcogn.do")
    @ResponseBody
    public Map getListDissRcogn(HttpServletRequest request) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
		// 차트용 데이터 생성
		Map data = statisticsService.viewDissRcognChart(paramMap);
        return getSuccess(data);
    }


    /**
     * 2021.12.20 LSH
     * 인정질환별 의료비 현황 통계 화면
     */
    @RequestMapping("/adm/statistics/viewDissMcp.do")
    public String viewDissMcp(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute StatisticsVO StatisticsVO) throws Exception {

        setGlobalSession(StatisticsVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", StatisticsVO);
        StatisticsVO.setBasisDateString(CommUtils.formatCurDate("yy.M.dd"));

        return "adm/statistics/viewDissMcp";
    }

    /**
     * 2021.12.20 LSH
     * 인정질환별 의료비 현황 조회 (JSON GRID)
     */
    @RequestMapping("/adm/statistics/getListDissMcp.do")
    @ResponseBody
    public Map getListDissMcp(HttpServletRequest request) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
		List list = statisticsService.listDissMcp(paramMap);
		// Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.12.20 LSH
     * 인정질환별 의료비 현황 엑셀 다운로드
     */
    @RequestMapping("/adm/statistics/downDissMcpExcel.do")
    public String downDissMcpExcel(HttpServletRequest request
            , ModelMap model) throws Exception {

    	Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());

		List list = statisticsService.listDissMcp(paramMap);

		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatDissMcp");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "인정질환별_의료비현황");

		return "excelTempView";
    }


    /**
     * 2021.12.20 LSH 현재사용안함
     * 찾아가는 서비스 통계 화면
     */
    @RequestMapping("/adm/statistics/viewVisitService.do")
    public String viewVisitService(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute StatisticsVO StatisticsVO) throws Exception {

        setGlobalSession(StatisticsVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", StatisticsVO);
        StatisticsVO.setBasisDateString(CommUtils.formatCurDate("yy.M.dd"));

        return "adm/statistics/viewVisitService";
    }

    /**
     * 2021.12.20 LSH 현재사용안함
     * 찾아가는 서비스 통계 조회 (JSON GRID)
     */
    @RequestMapping("/adm/statistics/getListVisitService.do")
    @ResponseBody
    public Map getListVisitService(HttpServletRequest request) throws Exception {

        Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//
		String mode = (String)paramMap.get("mode");
		List list = null;
		if ("YEAR".equals(mode)) {
			list = statisticsService.listYearVisitService(paramMap);
		}
		else {
			list = statisticsService.listRateVisitService(paramMap);
		}
		// Easy UI GRID용 결과값 반환
		return getEasyUIResult(list);
    }

    /**
     * 2021.12.20 LSH 현재사용안함
     * 찾아가는 서비스 통계 엑셀 다운로드
     */
    @RequestMapping("/adm/statistics/downVisitServiceExcel.do")
    public String downVisitServiceExcel(HttpServletRequest request
            , ModelMap model) throws Exception {

    	Map paramMap = getParameterMap(request, true);
        // -------------------- Default Setting End -----------------------//

        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());

    	String mode = (String)paramMap.get("mode");
		if ("YEAR".equals(mode)) {
			model.addAttribute(ExcelTempView.DATA_KEY    , statisticsService.listYearVisitService(paramMap));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatVisitServiceYear");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "찾아가는서비스통계_연도별실적");
		}
		else {
			model.addAttribute(ExcelTempView.DATA_KEY    , statisticsService.listRateVisitService(paramMap));
			model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
			model.addAttribute(ExcelTempView.TEMPLATE_KEY, "StatVisitServiceRate");
			model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "찾아가는서비스통계_서비스제공율");
		}
		return "excelTempView";
    }

}
