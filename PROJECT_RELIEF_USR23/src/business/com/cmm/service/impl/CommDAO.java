package business.com.cmm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.relief.service.ReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 공통 DAO
 *
 * @class   : CommDAO
 * @author  :
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */
@Repository("CommDAO")
@SuppressWarnings({"all"})
public class CommDAO extends BaseDAO {

    /**
     * 코드 리스트 조회
     */
    public List listCode(Map paramMap) {
        return list("Comm.listCode", paramMap);
    }
    
    /**
     * 2021.08.20 LSH
     * 엑셀설정정보 콤보 리스트 조회
     */
    public List getListExcel(Map paramMap) {
        return list("Comm.getListExcel", paramMap);
    }
    
    /**
     * 2021.09.24 LSH
     * 피해지역 콤보 리스트 조회
     */
    public List getListBizMng(Map paramMap) {
        return list("Comm.getListBizMng", paramMap);
    }
    
    /**
     * 2021.11.17 LSH
     * 사업차수 콤보 리스트 조회
     */
    public List getListBizOder(Map paramMap) {
        return list("Comm.getListBizOder", paramMap);
    }
    
    /**
     * 2021.11.23 LSH
     * 예비조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    public List getListPrptOder(Map paramMap) {
        return list("Comm.getListPrptOder", paramMap);
    }
    
    /**
     * 2021.11.23 LSH
     * 본조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    public List getListMnsvyOder(Map paramMap) {
        return list("Comm.getListMnsvyOder", paramMap);
    }
    
    /**
     * 2021.11.11 LSH
     * 질병분류 콤보 리스트 조회
     */
    public List getListDissCl(Map paramMap) {
        return list("Comm.getListDissCl", paramMap);
    }

    /**
     * 2021.12.14 LSH
     * 신청차수 콤보 리스트 조회
     */
    public List getListReliefOder(Map paramMap) {
        return list("Comm.getListReliefOder", paramMap);
    }
    
    /**
     * 2021.12.23 LSH
     * 주소검색 페이징목록 조회
     */
    public PaginatedArrayList listAddress(Map paramMap, int currPage, int pageSize) {
        return pageList("Comm.listAddress", paramMap, currPage, pageSize);
    }

    /**
     * 2022.01.20 LSH
     * 영업일수에 해당하는 날짜 반환
     * @param  paramMap.workCnt  영업일수 (숫자형)
     * @param  paramMap.currDate 기준일자 (YYYYMMDD)
     * @return 영업일수에 해당하는 날짜
     */
    public String getWorkDate(Map paramMap) {
        return (String)view("Comm.getWorkDate", paramMap);
    }
    
    /**
     * 2023.01.16 LSH
     * 살생물제품 제품분류및유형 콤보 조회
     * @param paramMap.upCdId 상위코드(CT202)
     */
    public List getListBioPrduct(Map paramMap) {
        return list("Comm.getListBioPrduct", paramMap);
    }
}
