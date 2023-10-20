package business.com.cmm.service;

import java.util.List;
import java.util.Map;

import business.sys.code.service.CodeVO;
import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 공통 Service IF
 *
 * @class   : CommService
 * @author  :
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일            수정자                             수정내용
 *  -------    --------    ---------------------------
 *
 */
@SuppressWarnings("rawtypes")
public interface CommService {

    /**
     * 코드리스트 조회
     */
    public List listCode(Map map) throws Exception;

    /**
     * 코드상세조회
     */
    public CodeVO getCode(String upCdId, String cdId) throws Exception;
    
    /**
     * 2021.08.20 LSH
     * 엑셀설정정보 콤보 리스트 조회
     */
    public List getListExcel(Map map) throws Exception;
    
    /**
     * 2021.09.24 LSH
     * 피해지역 콤보 리스트 조회
     */
    public List getListBizMng(Map map) throws Exception;
    
    /**
     * 2021.11.17 LSH
     * 사업차수 콤보 리스트 조회
     */
    public List getListBizOder(Map paramMap) throws Exception;
    
    /**
     * 2021.11.23 LSH
     * 예비조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    public List getListPrptOder(Map paramMap) throws Exception;
    
    /**
     * 2021.11.23 LSH
     * 본조사차수 콤보 리스트 조회
     * bizAreaCd, bizOder 필수
     */
    public List getListMnsvyOder(Map paramMap) throws Exception;
    
    /**
     * 2021.11.11 LSH
     * 질병분류 콤보 리스트 조회
     */
    public List getListDissCl(Map map) throws Exception;
    
    /**
     * 2021.11.30 LSH
     * 코드목록을 Name KEY 맵으로 변환
     */
    public Map<String,Object> getMapCodeByName(String upCdId) throws Exception;
    
    /**
     * 2022.01.27 LSH
     * 코드목록을 Code KEY 맵으로 변환
     */
    public Map<String,Object> getMapCode(String upCdId) throws Exception;

    /**
     * 2021.12.14 LSH
     * 신청차수 콤보 리스트 조회
     */
    public List getListReliefOder(Map paramMap) throws Exception;
    
    /**
     * 2021.12.23 LSH
     * 주소검색 페이징목록 조회
     */
    public PaginatedArrayList listAddress(Map paramMap, int currPage, int pageSize) throws Exception;

    /**
     * 2022.01.20 LSH
     * 영업일수에 해당하는 날짜 반환
     * @param  workCnt  영업일수 (숫자형)
     * @param  currDate 기준일자 (YYYYMMDD)
     * @return 영업일수에 해당하는 날짜
     */
    public String getWorkDate(int workCnt, String currDate) throws Exception;
    
    /**
     * 2023.01.16 LSH
     * 살생물제품 제품분류및유형 콤보 조회
     * @param paramMap.upCdId 상위코드(CT202)
     */
    public List getListBioPrduct(Map paramMap) throws Exception;
}
