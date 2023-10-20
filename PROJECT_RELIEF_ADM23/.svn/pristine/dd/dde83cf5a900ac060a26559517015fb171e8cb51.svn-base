package business.com.statistics.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.cmm.service.CommService;
import business.com.statistics.service.StatisticsService;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 통계 관리하는 Service 구현 클래스
 * 
 * @class   : StatisticsServiceImpl
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("StatisticsService")
@SuppressWarnings({"all"})
public class StatisticsServiceImpl extends BaseService implements StatisticsService {

    @Resource(name = "StatisticsDAO")
    private StatisticsDAO statisticsDAO;

    @Resource(name="CommService")
    private CommService commService;

    /**
     * [사업별] 건강피해 인정현황 조회
     * 2021.12.21 LSH 최종합계의 1/2차사업 합계 계산
     */
    @Override
    public List listAreaDmgRcogn(Map params) throws Exception {
    	List<Map> list = statisticsDAO.listAreaDmgRcogn(params);
    	for (Map item : list) {
    		if ("TOT".equals(item.get("bizAreaCd"))) {
    			list.remove(item);
    			break;
    		}
    	}
    	Map keys = new HashMap();
    	List<Map> total = new ArrayList<Map>();
    	for (Map item : list) {
    		String bizOder = (String)item.get("bizOder");
    		if (!keys.containsKey(bizOder)) {
    			keys.put(bizOder, total.size());
    			Map data = new HashMap();
    			data.put("bizAreaCd", "TOT");
    			data.put("bizAreaNm", "계");
    			data.put("bizOder"  , bizOder);
    			data.put("bizOderNm", item.get("bizOderNm"));
    			
    			data.put("aplyAllCnt"  , item.get("aplyAllCnt"  ));
    			data.put("rcognAllCnt1", item.get("rcognAllCnt1"));
    			data.put("rcognAllCnt2", item.get("rcognAllCnt2"));
    			data.put("rcognAllCnt3", item.get("rcognAllCnt3"));
    			data.put("aplyFltCnt", 
	    				CommUtils.getInt(item, "aplyFltCnt") == 0 ? 
	    				CommUtils.getInt(item, "aplyAllCnt"  ) : 
	    				CommUtils.getInt(item, "aplyFltCnt"  )
	    			);
    			data.put("rcognFltCnt1", 
        				CommUtils.getInt(item, "rcognFltCnt1") == 0 ? 
        				CommUtils.getInt(item, "rcognAllCnt1"  ) : 
        				CommUtils.getInt(item, "rcognFltCnt1"  )
        			);
    			data.put("rcognFltCnt2", 
        				CommUtils.getInt(item, "rcognFltCnt2") == 0 ? 
        				CommUtils.getInt(item, "rcognAllCnt2"  ) : 
        				CommUtils.getInt(item, "rcognFltCnt2"  )
        			);
    			data.put("rcognFltCnt3", 
        				CommUtils.getInt(item, "rcognFltCnt3") == 0 ? 
        				CommUtils.getInt(item, "rcognAllCnt3"  ) : 
        				CommUtils.getInt(item, "rcognFltCnt3"  )
        			);
    			total.add(data);
    		}
    		else {
    			int index = CommUtils.getInt(keys, bizOder);
    			Map data = (Map)total.get(index);
    			
    			data.put("aplyAllCnt"  , CommUtils.getInt(data , "aplyAllCnt" )+ CommUtils.getInt(item, "aplyAllCnt"  ));
    			data.put("rcognAllCnt1", CommUtils.getInt(data, "rcognAllCnt1")+ CommUtils.getInt(item, "rcognAllCnt1"));
    			data.put("rcognAllCnt2", CommUtils.getInt(data, "rcognAllCnt2")+ CommUtils.getInt(item, "rcognAllCnt2"));
    			data.put("rcognAllCnt3", CommUtils.getInt(data, "rcognAllCnt3")+ CommUtils.getInt(item, "rcognAllCnt3"));
    			
    			data.put("aplyFltCnt", 
	    				CommUtils.getInt(data, "aplyFltCnt"  )+ (
	    				CommUtils.getInt(item, "aplyFltCnt") == 0 ? 
	    				CommUtils.getInt(item, "aplyAllCnt"  ) : 
	    				CommUtils.getInt(item, "aplyFltCnt"  )
	    			));
    			data.put("rcognFltCnt1", 
        				CommUtils.getInt(data, "rcognFltCnt1"  )+ (
        				CommUtils.getInt(item, "rcognFltCnt1") == 0 ? 
        				CommUtils.getInt(item, "rcognAllCnt1"  ) : 
        				CommUtils.getInt(item, "rcognFltCnt1"  )
        			));
    			data.put("rcognFltCnt2", 
        				CommUtils.getInt(data, "rcognFltCnt2"  )+ (
        				CommUtils.getInt(item, "rcognFltCnt2") == 0 ? 
        				CommUtils.getInt(item, "rcognAllCnt2"  ) : 
        				CommUtils.getInt(item, "rcognFltCnt2"  )
        			));
    			data.put("rcognFltCnt3", 
        				CommUtils.getInt(data, "rcognFltCnt3"  )+ (
        				CommUtils.getInt(item, "rcognFltCnt3") == 0 ? 
        				CommUtils.getInt(item, "rcognAllCnt3"  ) : 
        				CommUtils.getInt(item, "rcognFltCnt3"  )
        			));
    		}
    	}
    	for (Map t : total) {
			if (!"0".equals(t.get("bizOder"))) {
    			t.put("bizOderNm", t.get("bizOderNm")+"계");
			}
			if (CommUtils.getInt(t, "aplyFltCnt") == 
				CommUtils.getInt(t, "aplyAllCnt")) {
				t.put("aplyFltCnt", 0);
			}
			if (CommUtils.getInt(t, "rcognFltCnt1") == 
				CommUtils.getInt(t, "rcognAllCnt1")) {
				t.put("rcognFltCnt1", 0);
			}
			if (CommUtils.getInt(t, "rcognFltCnt2") == 
				CommUtils.getInt(t, "rcognAllCnt2")) {
				t.put("rcognFltCnt2", 0);
			}
			if (CommUtils.getInt(t, "rcognFltCnt3") == 
				CommUtils.getInt(t, "rcognAllCnt3")) {
				t.put("rcognFltCnt3", 0);
			}
    	}
    	// 합계 정렬
        total.sort(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                return ((String)o1.get("bizOder")).compareTo((String)o2.get("bizOder"));
            }
        });
        // 2022.01.18 총계를 최상단으로 변경처리
    	list.addAll(0,total);
        return list;
    }

    /**
     * [연도별] 건강피해 인정현황 조회
     */
    @Override
    public List listYearDmgRcogn(Map params) throws Exception {
        return statisticsDAO.listYearDmgRcogn(params);
    }

    /**
     * 2021.12.15 LSH 추가
     * [1,2차통합] 건강피해 인정현황 조회
     * 결과: 
     *  - reqCnt  - 신청
     *  - apprCnt - 인정
     *  - liveCnt - (인정)생존
     *  - dthCnt  - (인정)사망
     *  - rjctCnt - 미인정
     *  - exmnCnt - 조사중
     */
    @Override
    public List listTotalDmgRcogn(Map params) throws Exception {
        return statisticsDAO.listTotalDmgRcogn(params);
    }

    /**
     * 지역별 피해구제 신청현황 조회
     */
    @Override
    public Map viewReliefAply(Map params) throws Exception {
		// 지역코드
    	String mode = (String)params.get("bizAreaCd");
		Map data = null;
		// 대구
		if ("A0001".equals(mode)) {
			data = statisticsDAO.viewReliefAplyA0001(params);
		}
		// 김포
		else if ("A0003".equals(mode)) {
			data = statisticsDAO.viewReliefAplyA0003(params);
		}
		// 서천
		else if ("A0002".equals(mode)) {
			data = statisticsDAO.viewReliefAplyA0002(params);
		}
		if (data == null)
			data = new HashMap();
		data.put("bizAreaCd", mode); 
		return data;
    }

    /**
     * [사업별] 구제급여 지급현황 조회
     */
    @Override
    public List listAreaReliefGive(Map params) throws Exception {
        return statisticsDAO.listAreaReliefGive(params);
    }

    /**
     * [연도별] 구제급여 지급현황 조회
     */
    @Override
    public List listYearReliefGive(Map params) throws Exception {

    	List<Map> list     = _listYearReliefGive(params);
		List<String> years = new ArrayList<String>();

		// 연도별 데이터를 칼럼항목으로 재정의
		int stYear  = CommUtils.getInt(params,"srchStYear");
		int enYear  = CommUtils.getInt(params,"srchEnYear");
		
	     // 구제급여 지급현황 연도별 칼럼항목 생성
	     // - 행단위 연도별 데이터를 칼럼단위로 변형
	     // - 지역별 SUMMARY 이므로 지역별로 행단위를 고정
	     // - 최종 합계행 계산
		
		years.add("TOTAL");
		for (int i = stYear; i<= enYear; i++) {
			years.add(String.valueOf(i));
		}
		// 합계
		Map total = new HashMap();
		// 지역목록 조회
		List<Map> rows = commService.getListBizMng(null);
		total.put("bizAreaNm", "합계");
		total.put("bizAreaCd", "TOTAL");
		for (Map row : rows) {
			row.put("bizAreaCd" , row.get("code"));
			row.put("bizAreaNm" , row.get("text"));
			row.put("bizDtlArea", row.get("desc"));
			
			String code = (String)row.get("code");
			for (String year : years) {
				int i = 0;
				for (Map item : list) {
					if (code.equals(item.get("bizAreaCd")) &&
						year.equals(item.get("giveYear"))) {
						row.put("giveAllCnt"+year, item.get("giveAllCnt"));
						row.put("giveFltCnt"+year, item.get("giveFltCnt"));
						row.put("giveAllAmt"+year, item.get("giveAllAmt"));
						total.put("giveAllCnt"+year, CommUtils.getInt(total,"giveAllCnt"+year) + CommUtils.getInt(item, "giveAllCnt"));
						total.put("giveFltCnt"+year, CommUtils.getInt(total,"giveFltCnt"+year) + CommUtils.getInt(item, "giveFltCnt"));
						total.put("giveAllAmt"+year, CommUtils.getInt(total,"giveAllAmt"+year) + CommUtils.getInt(item, "giveAllAmt"));
						
						row.put("giveAllTot", item.get("giveAllTot"));
						row.put("giveFltTot", item.get("giveFltTot"));
						row.put("giveAmtTot", item.get("giveAmtTot"));
						
						list.remove(i);
						break;
					}
					i++;
				}
			}
			total.put("giveAllTot", CommUtils.getInt(total,"giveAllTot") + CommUtils.getInt(row, "giveAllTot"));
			total.put("giveFltTot", CommUtils.getInt(total,"giveFltTot") + CommUtils.getInt(row, "giveFltTot"));
			total.put("giveAmtTot", CommUtils.getInt(total,"giveAmtTot") + CommUtils.getInt(row, "giveAmtTot"));
		}
        // 2022.01.18 총계를 최상단으로 변경처리
		rows.add(0, total);
		
		return rows;
    }

    /**
     * 2021.11.15 
     * [연도별] 구제급여 지급현황 조회 (엑셀다운로드용)
     */
    @Override
    public Map listYearReliefGiveExcel(Map params) throws Exception {

    	List<Map> list = _listYearReliefGive(params);
    	
		// 연도별 항목 생성
    	List<Map> years = new ArrayList<Map>();
		int stYear  = CommUtils.getInt(params,"srchStYear");
		int enYear  = CommUtils.getInt(params,"srchEnYear");
		
		Map yeart = new HashMap();
		yeart.put("name", "TOTAL");
		years.add(yeart);
		for (int i = stYear; i<= enYear; i++) {
			Map year = new HashMap();
			year.put("name", String.valueOf(i));
			year.put("allCnt", 0);
			year.put("fltCnt", 0);
			year.put("allAmt", 0);
			years.add(year);
		}

		// 지역목록 조회 (행단위: 지역별 SUMMARY)
		List<Map> rows = commService.getListBizMng(null);
		
		for (Map row : rows) {
			row.put("bizAreaNm" , row.get("text"));
			row.put("bizDtlArea", row.get("desc"));
			
			String code = (String)row.get("code");
			
			List<Map> items = new ArrayList<Map>();
			
			for (Map year : years) {

				String name = (String)year.get("name");

				Map item = new HashMap();
				item.put("name"  , name);
				item.put("allCnt", 0);
				item.put("fltCnt", 0);
				item.put("allAmt", 0);
				int i = 0;
				boolean sum = false;
				for (Map obj : list) {
					if ("TOTAL".equals(name) && code.equals(obj.get("bizAreaCd"))) {
						item.put("allCnt", obj.get("giveAllTot"));
						item.put("fltCnt", obj.get("giveFltTot"));
						item.put("allAmt", obj.get("giveAmtTot"));
						if (sum == false) {
							yeart.put("allCnt", CommUtils.getInt(yeart,"allCnt") + CommUtils.getInt(obj, "giveAllTot"));
							yeart.put("fltCnt", CommUtils.getInt(yeart,"fltCnt") + CommUtils.getInt(obj, "giveFltTot"));
							yeart.put("allAmt", CommUtils.getInt(yeart,"allAmt") + CommUtils.getInt(obj, "giveAmtTot"));
							sum = true;
						}
					}
					if (code.equals(obj.get("bizAreaCd")) &&
						name.equals(obj.get("giveYear"))) {
						item.put("allCnt", obj.get("giveAllCnt"));
						item.put("fltCnt", obj.get("giveFltCnt"));
						item.put("allAmt", obj.get("giveAllAmt"));
						
						year.put("allCnt", CommUtils.getInt(year,"allCnt") + CommUtils.getInt(obj, "giveAllCnt"));
						year.put("fltCnt", CommUtils.getInt(year,"fltCnt") + CommUtils.getInt(obj, "giveFltCnt"));
						year.put("allAmt", CommUtils.getInt(year,"allAmt") + CommUtils.getInt(obj, "giveAllAmt"));
						list.remove(i);
						break;
					}
					i++;
				}
				items.add(item);
			}
			row.put("items", items);
		}
		
		Map result = new HashMap();
		result.put("headers", years);
		result.put("list"   , rows);
		return result;
    }

    // [연도별] 구제급여 지급현황 검색
    private List _listYearReliefGive(Map params) throws Exception {
    	return statisticsDAO.listYearReliefGive(params);
    }
    
    /**
     * 구제급여 지급현황 연도별 칼럼항목 생성
     * - 행단위 연도별 데이터를 칼럼단위로 변형하여 반환
     * - 지역별 SUMMARY 이므로 지역별로 행단위를 고정한다.
     * - 최종 합계행을 계산하여 생성한다.
     */
    private List buildReliefGivePivot(List<Map> list, int stYear, int enYear) throws Exception {
    	
		List<String> years = new ArrayList<String>();
		years.add("TOTAL");
		for (int i = stYear; i<= enYear; i++) {
			years.add(String.valueOf(i));
		}
		// 합계
		Map total = new HashMap();
		// 지역목록 조회
		List<Map> rows = commService.getListBizMng(null);
		total.put("bizAreaNm", "합계");
		total.put("bizAreaCd", "TOTAL");
		for (Map row : rows) {
			row.put("bizAreaCd" , row.get("code"));
			row.put("bizAreaNm" , row.get("text"));
			row.put("bizDtlArea", row.get("desc"));
			
			String code = (String)row.get("code");
			for (String year : years) {
				int i = 0;
				for (Map item : list) {
					if (code.equals(item.get("bizAreaCd")) &&
						year.equals(item.get("giveYear"))) {
						row.put("giveAllCnt"+year, item.get("giveAllCnt"));
						row.put("giveFltCnt"+year, item.get("giveFltCnt"));
						row.put("giveAllAmt"+year, item.get("giveAllAmt"));
						row.put("giveFltAmt"+year, item.get("giveFltAmt"));
						total.put("giveAllCnt"+year, CommUtils.getInt(total,"giveAllCnt"+year) + CommUtils.getInt(item, "giveAllCnt"));
						total.put("giveFltCnt"+year, CommUtils.getInt(total,"giveFltCnt"+year) + CommUtils.getInt(item, "giveFltCnt"));
						total.put("giveAllAmt"+year, CommUtils.getInt(total,"giveAllAmt"+year) + CommUtils.getInt(item, "giveAllAmt"));
						total.put("giveFltAmt"+year, CommUtils.getInt(total,"giveFltAmt"+year) + CommUtils.getInt(item, "giveFltAmt"));
						list.remove(i);
						break;
					}
					i++;
				}
			}
		}
        // 2022.01.18 총계를 최상단으로 변경처리
		rows.add(0, total);
		
		return rows;
    }

    /**
     * 인정질환별 인정자 현황 조회
     * @param params.bizAreaCd 지역코드
     * 2021.12.20 LSH 수정: 통계분류코드 맵핑 추가
     */
    @Override
    public Map viewDissRcognChart(Map params) throws Exception {
    	
    	// 지역코드배열
    	String[] bizAreaCds = {"A0001","A0002","A0003"};
    	// 통계분류배열
    	String[] statClCds  = { "ST01", "ST02", "ST01"};
    	// 지역코드
    	String bizAreaCd = (String)params.get("bizAreaCd");
    	int index = Arrays.asList(bizAreaCds).indexOf(bizAreaCd);
    	if (index >= 0) {
    		// 통계분류
    		params.put("statClCd", statClCds[index]);
    	}
        List list = statisticsDAO.listDissRcogn(params);
		// 차트용 데이터 생성
		return buildDissRcognChart(list, bizAreaCd);
    }
    
    /**
     * 인정질환별 인정자 현황 차트용 데이터 생성
     */
    private Map buildDissRcognChart(List<Map> list, String bizAreaCd) {
    	
    	List<String>  labels = new ArrayList<String> ();
        List<Integer> values = new ArrayList<Integer> ();
        List<String>  colors = new ArrayList<String> ();
    	
    	for (Map item : list) {
    		// 해당 지역이 아니면 SKIP
    		if (!bizAreaCd.equals(item.get("bizAreaCd")))
    			continue;
    		
    		int    value = CommUtils.getInt(item, "rcognCnt");
    		String label = (String)item.get("dissClNm");
    		
    		// 레이블 정의
    		labels.add(label);
    		values.add(value);
           	colors.add(value > 50 ? "#FFC000" : "#5B9BD5");
    	}
    	
    	Map data = new HashMap();
    	data.put("labels", labels);
    	data.put("colors", colors);
    	data.put("values", values);
    	return data;
    }

    /**
     * 2021.12.20 LSH
     * 인정질환별 의료비 현황 목록 조회
     */
    @Override
    public List listDissMcp(Map params) throws Exception {
        return statisticsDAO.listDissMcp(params);
    }

    /**
     * 2021.12.18 LSH 현재사용안함 (메뉴에서 제외)
     * [연도별실적] 찾아가는 서비스 통계 조회
     */
    @Override
    public List listYearVisitService(Map params) throws Exception {
    	//List<Map> list = statisticsDAO.listYearVisitService(params);
        
		// 샘플 생성 (실제 구현시 지울것)
		List<Map> list = new ArrayList<Map>();
        list.add(_getSampleMap("PP01"  ,"2017" ,3  ,16  ,3   ));
        list.add(_getSampleMap("PP01"  ,"2018" ,5  ,69  ,37  ));
        list.add(_getSampleMap("PP01"  ,"2019" ,11 ,210 ,52  ));
        list.add(_getSampleMap("PP01"  ,"2020" ,9  ,227 ,153 ));
        list.add(_getSampleMap("PP01"  ,"2021" ,5  ,131 ,103 ));
        list.add(_getSampleMap("PP02"  ,"2017" ,0  ,0   ,0   ));
        list.add(_getSampleMap("PP02"  ,"2018" ,4  ,51  ,4   ));
        list.add(_getSampleMap("PP02"  ,"2019" ,3  ,18  ,0   ));
        list.add(_getSampleMap("PP02"  ,"2020" ,9  ,141 ,111 ));
        list.add(_getSampleMap("PP02"  ,"2021" ,3  ,8   ,0   ));

        // 연도별/업무별 데이터를 칼럼항목으로 재정의
		int stYear  = CommUtils.getInt(params,"srchStYear");
		int enYear  = CommUtils.getInt(params,"srchEnYear");
		return buildServiceYearPivot(list, stYear, enYear);
    }

    /**
     * 2021.12.18 LSH 현재사용안함 (메뉴에서 제외)
     * [서비스제공율] 찾아가는 서비스 통계 조회
     */
    @Override
    public List listRateVisitService(Map params) throws Exception {
    	//List<Map> list = statisticsDAO.listRateVisitService(params);
        
		// 샘플 생성 (실제 구현시 지울것)
		List<Map> list = new ArrayList<Map>();
        list.add(_getSampleMap("PP01", "2017" ,3   ,247  ));
        list.add(_getSampleMap("PP01", "2018" ,37  ,43   ));
        list.add(_getSampleMap("PP01", "2019" ,52  ,71   ));
        list.add(_getSampleMap("PP01", "2020" ,153 ,178  ));
        list.add(_getSampleMap("PP02", "2017" ,0   ,5    ));
        list.add(_getSampleMap("PP02", "2018" ,4   ,29   ));
        list.add(_getSampleMap("PP02", "2019" ,0   ,2    ));
        list.add(_getSampleMap("PP02", "2020" ,111 ,121  ));
    	
		// 연도별/업무별 데이터를 칼럼항목으로 재정의 (제공율 계산)
    	int stYear  = CommUtils.getInt(params,"srchStYear");
		int enYear  = CommUtils.getInt(params,"srchEnYear");
		return buildServiceRatePivot(list, stYear, enYear);
    }
    
    /**
     * 2021.12.18 LSH 현재사용안함 (메뉴에서 제외)
     * 찾아가는서비스 서비스제공율 데이터 재생성
     * - 행단위 업무구분 데이터를 칼럼단위로 재생성한다.
     * - 칼럼단위 합계를 계산하여 생성한다.
     */
    private List buildServiceRatePivot(List<Map> list, int stYear, int enYear) throws Exception {
    	
    	String[] groups = new String[] {"PP01","PP02"};
    	// 년도별 행데이터 생성
    	List<Map> rows = new ArrayList<Map>();
		for (int i = stYear; i<= enYear; i++) {
			Map row = new HashMap();
			String year = String.valueOf(i);
			row.put("servYear", year);
			// 칼럼항목 생성
			for (String group : groups) {
				// DATA LOOP
				int n = 0;
				for (Map item : list) {
					if (group.equals(item.get("dtySeCd")) &&
						year.equals(item.get("servYear"))) {
						row.put("servSplyCnt"+group, item.get("servSplyCnt"));
						row.put("servAplyCnt"+group, item.get("servAplyCnt"));
						list.remove(n);
						break;
					}
					n++;
				}
				// 칼럼합계
				row.put("servSplyTot", CommUtils.getInt(row,"servSplyTot") + CommUtils.getInt(row, "servSplyCnt"+group));
				row.put("servAplyTot", CommUtils.getInt(row,"servAplyTot") + CommUtils.getInt(row, "servAplyCnt"+group));
			}
			// 서비스제공율 계산
			double splyTot = CommUtils.getDouble(row,"servSplyTot");
			double aplyTot = CommUtils.getDouble(row,"servAplyTot");
			double rateTot = 0.0;
			if (aplyTot > 0)
				rateTot = splyTot / aplyTot * 100.0;
			row.put("servRate", String.format("%.1f", rateTot));
			
			rows.add(row);
		}
		return rows;
    }
    
    /**
     * 2021.12.18 LSH 현재사용안함 (메뉴에서 제외)
     * 찾아가는서비스 연도별실적 데이터 재생성
     * - 행단위 업무구분 데이터를 칼럼단위로 재생성한다.
     * - 칼럼단위 합계를 계산하여 생성한다.
     */
    private List buildServiceYearPivot(List<Map> list, int stYear, int enYear) throws Exception {
    	
    	String[] groups = new String[] {"PP01","PP02"};
    	// 년도별 행데이터 생성
    	List<Map> rows = new ArrayList<Map>();
		for (int i = stYear; i<= enYear; i++) {
			Map row = new HashMap();
			String year = String.valueOf(i);
			row.put("servYear", year);
			// 칼럼항목 생성
			for (String group : groups) {
				// DATA LOOP
				int n = 0;
				for (Map item : list) {
					if (group.equals(item.get("dtySeCd")) &&
						year.equals(item.get("servYear"))) {
						row.put("servOutCnt"+group, item.get("servOutCnt"));
						row.put("servAllCnt"+group, item.get("servAllCnt"));
						row.put("servAllMen"+group, item.get("servAllMen"));
						list.remove(n);
						break;
					}
					n++;
				}
				// 칼럼합계
				row.put("totalOutCnt", CommUtils.getInt(row,"totalOutCnt") + CommUtils.getInt(row, "servOutCnt"+group));
				row.put("totalAllCnt", CommUtils.getInt(row,"totalAllCnt") + CommUtils.getInt(row, "servAllCnt"+group));
				row.put("totalAllMen", CommUtils.getInt(row,"totalAllMen") + CommUtils.getInt(row, "servAllMen"+group));
			}
			rows.add(row);
		}
		return rows;
    }

	// [나중에지울것] 연도별실적 찾아가는서비스 샘플맵 생성
	private Map _getSampleMap(String s1, String s2, int n1, int n2, int n3) {
		Map map = new HashMap();
		map.put("dtySeCd"    , s1); // 업무구분
		map.put("servYear"   , s2); // 연도
		map.put("servOutCnt" , n1); // 현장지원건수
		map.put("servAllCnt" , n2); // 전체지원건수
		map.put("servAllMen" , n3); // 전체지원인원
		return map;
	}
	
	// [나중에지울것] 서비스제공율 찾아가는서비스 샘플맵 생성
	private Map _getSampleMap(String s1, String s2, int n1, int n2) {
		Map map = new HashMap();
		map.put("dtySeCd"    , s1); // 업무구분
		map.put("servYear"   , s2); // 연도
		map.put("servSplyCnt", n1); // 서비스제공자 인원
		map.put("servAplyCnt", n2); // 피해구제신청인 인원
		return map;
	}
}