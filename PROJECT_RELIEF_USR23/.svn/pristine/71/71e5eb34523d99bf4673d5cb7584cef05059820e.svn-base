package business.com.statistics.service;

import java.util.List;
import java.util.Map;

/**
 * [서비스인터페이스] - 통계 관리하는 Service Interface
 * 
 * @class   : StatisticsService
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface StatisticsService {
    /**
     * 사업별 건강피해 인정현황 조회
     */
    public List listAreaDmgRcogn(Map params) throws Exception;

    /**
     * 연도별 건강피해 인정현황 조회
     */
    public List listYearDmgRcogn(Map params) throws Exception;

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
    public List listTotalDmgRcogn(Map params) throws Exception;

    /**
     * 지역별 피해구제 신청현황 조회
     */
    public Map viewReliefAply(Map params) throws Exception;

    /**
     * 사업별 구제급여 지급현황 조회
     */
    public List listAreaReliefGive(Map params) throws Exception;

    /**
     * 연도별 구제급여 지급현황 조회
     */
    public List listYearReliefGive(Map params) throws Exception;

    /**
     * 연도별 구제급여 지급현황 조회 (엑셀다운로드용)
     */
    public Map listYearReliefGiveExcel(Map params) throws Exception;

    /**
     * 인정질환별 인정자 현황 조회
     */
    public Map viewDissRcognChart(Map params) throws Exception;

    /**
     * 2021.12.20 LSH
     * 인정질환별 의료비 현황 조회
     */
    public List listDissMcp(Map params) throws Exception;

    /**
     * 2021.12.20 LSH 현재사용안함
     * [연도별실적] 찾아가는 서비스 통계 조회
     */
    public List listYearVisitService(Map params) throws Exception;

    /**
     * 2021.12.20 LSH 현재사용안함
     * [서비스제공율] 찾아가는 서비스 통계 조회
     */
    public List listRateVisitService(Map params) throws Exception;

}