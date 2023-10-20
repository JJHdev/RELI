 package business.com.statistics.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import common.base.BaseDAO;

/**
 * [DAO클래스] - 통계 관리하는 DAO 클래스
 * 
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 * 
 *
 * @class   : StatisticsDAO
 * @author  : LSH
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("StatisticsDAO")
@SuppressWarnings({"all"})
public class StatisticsDAO extends BaseDAO {

	/**
     * 2021.12.15 LSH SQL 적용
     * 사업별 건강피해 인정현황 조회
     */
    public List listAreaDmgRcogn(Map params) {
        return list("Statistics.listAreaDmgRcogn", params);
    }

    /**
     * 2021.12.15 LSH SQL 적용
     * 연도별 건강피해 인정현황 조회
     */
    public List listYearDmgRcogn(Map params) {
        return list("Statistics.listYearDmgRcogn", params);
    }

    /**
     * 2021.12.15 LSH 추가
     * 1,2차통합 건강피해 인정현황 조회
     * 결과: 
     *  - reqCnt  - 신청
     *  - apprCnt - 인정
     *  - liveCnt - (인정)생존
     *  - dthCnt  - (인정)사망
     *  - rjctCnt - 미인정
     *  - exmnCnt - 조사중
     */
    public List listTotalDmgRcogn(Map params) {
        return list("Statistics.listTotalDmgRcogn", params);
    }

    /**
     * 2021.12.15 LSH SQL 적용
     * 지역별 피해구제 신청현황 조회 (대구)
     * 결과: 
     *  - aplyT0T - 전체인원
     *  - aplyR01 - 1차인정
     *  - aplyD01 - 1차불인정
     */
    public Map viewReliefAplyA0001(Map params) {
        return (Map)view("Statistics.viewReliefAplyA0001", params);
    }
    /**
     * 2021.12.15 LSH SQL 적용
     * 지역별 피해구제 신청현황 조회 (서천)
     * 결과: 
     *  - aplyT01 - 1차사업
     *  - aplyT02 - 2차사업
     *  - aplyR01 - 1차인정
     *  - aplyD01 - 1차불인정
     *  - aplyR02 - 2차인정
     *  - aplyD02 - 2차불인정
     *  - aplyR01R02 - R1_R1: 1차인정,2차인정
     *  - aplyD01D02 - R9_R9: 1차불인정,2차불인정
     *  - aplyR01N02 - R1_X : 1차인정,2차미인정
     *  - aplyN01R02 - X_R1 : 1차미인정,2차인정
     */
    public Map viewReliefAplyA0002(Map params) {
        return (Map)view("Statistics.viewReliefAplyA0002", params);
    }
    		  
    /**
     * 2021.12.15 LSH SQL 적용
     * 지역별 피해구제 신청현황 조회 (김포)
     * 결과: 
     *  - aplyT01 - 1차사업
     *  - aplyT02 - 2차사업
     *  - aplyR02 - 2차인정
     *  - aplyD02 - 2차불인정
     *  - aplyE02 - 2차조사중
     *  - aplyR01R02 - 1차인정,2차인정
     */
    public Map viewReliefAplyA0003(Map params) {
        return (Map)view("Statistics.viewReliefAplyA0003", params);
    }

    /**
     * 사업별 구제급여 지급현황 조회
     */
    public List listAreaReliefGive(Map params) {
        return list("Statistics.listAreaReliefGive", params);
    }
    
    /**
     * 연도별 구제급여 지급현황 조회
     */
    public List listYearReliefGive(Map params) {
        return list("Statistics.listYearReliefGive", params);
    }

    /**
     * 인정질환별 인정자 현황 조회
     */
    public List listDissRcogn(Map params) {
        return list("Statistics.listDissRcogn", params);
    }
    
    /**
     * 2021.12.20 LSH
     * 인정질환별 의료비 현황 조회
     */
    public List listDissMcp(Map params) {
        return list("Statistics.listDissMcp", params);
    }
    
    /**
     * 2021.12.20 LSH 현재사용안함
     * [연도별실적] 찾아가는 서비스 통계 조회
     */
    public List listYearVisitService(Map params) {
        return list("Statistics.listYearVisitService", params);
    }

    /**
     * 2021.12.20 LSH 현재사용안함
     * [서비스제공율] 찾아가는 서비스 통계 조회
     */
    public List listRateVisitService(Map params) {
        return list("Statistics.listRateVisitService", params);
    }
    
}