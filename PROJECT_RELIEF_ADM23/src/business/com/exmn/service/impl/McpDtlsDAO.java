 package business.com.exmn.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.McpDtlsVO;
import business.com.exmn.service.MnsvyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;
import common.util.CommUtils;

/**
 * [DAO클래스] - 의료비내역을 관리하는 DAO 클래스
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
 * @class   : McpDtlsDAO
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("McpDtlsDAO")
@SuppressWarnings({"all"})
public class McpDtlsDAO extends BaseDAO {

    /**
     * 의료비내역 페이징목록 조회
     */
    public PaginatedArrayList listMcpDtls(McpDtlsVO mcpDtlsVO, int currPage, int pageSize) {
        return pageList("McpDtls.listMcpDtls", mcpDtlsVO, currPage, pageSize);
    }

    /**
     * 의료비내역 목록 조회
     */
    public List listMcpDtls(McpDtlsVO mcpDtlsVO) {
        return list("McpDtls.listMcpDtls", mcpDtlsVO);
    }

    /**
     * 의료비내역 상세 조회
     */
    public McpDtlsVO viewMcpDtls(McpDtlsVO mcpDtlsVO) {
        return (McpDtlsVO)view("McpDtls.viewMcpDtls", mcpDtlsVO);
    }

    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 목록 조회
     */
    public List listMcpDtlsSummary(McpDtlsVO mcpDtlsVO) {
        return list("McpDtls.listMcpDtlsSummary", mcpDtlsVO);
    }
    
    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 합계
     */
    public List listMcpDtlsSummaryTotal(McpDtlsVO mcpDtlsVO) {
        return list("McpDtls.listMcpDtlsSummaryTotal", mcpDtlsVO);
    }

    /**
     * 의료비내역 등록
     */
    public int regiMcpDtls(McpDtlsVO mcpDtlsVO) {
        return insert("McpDtls.regiMcpDtls", mcpDtlsVO);
    }

    /**
     * 의료비내역 이력등록
     */
    public int regiMcpDtlsHst(McpDtlsVO mcpDtlsVO) {
        return insert("McpDtls.regiMcpDtlsHst", mcpDtlsVO);
    }

    /**
     * 의료비내역 수정
     */
    public int updtMcpDtls(McpDtlsVO mcpDtlsVO) {
        return update("McpDtls.updtMcpDtls", mcpDtlsVO);
    }

    /**
     * 의료비내역 삭제
     */
    public int deltMcpDtls(McpDtlsVO mcpDtlsVO) {
        return delete("McpDtls.deltMcpDtls", mcpDtlsVO);
    }
    
    /**
     * 의료비내역 전체삭제
     */
    public int deltMcpDtlsAll(McpDtlsVO mcpDtlsVO) {
        return delete("McpDtls.deltMcpDtlsAll", mcpDtlsVO);
    }
    
    /**
     * 2022.12.09 LSH
     * 본조사 세부 의료비내역 마스터 상세조회
     * @param mcpDtlsVO.bizAreaCd 사업지역코드
     * @param mcpDtlsVO.bizOder   사업차수
     * @param mcpDtlsVO.exmnOder  조사차수
     * @param mcpDtlsVO.aplyNo    신청번호
     * @param mcpDtlsVO.sckwndCd  상병코드
     */
    public McpDtlsVO viewMcpSckwnd(McpDtlsVO mcpDtlsVO) {
        return (McpDtlsVO)view("McpDtls.viewMcpSckwnd", mcpDtlsVO);
    }

    /**
     * 2022.12.12 LSH
     * 종합현황 - 의료비내역 - 의료비 검색 총합 조회
     */
    public long viewMcpDtlsTotalBySearch(McpDtlsVO mcpDtlsVO) {
    	Object obj = view("McpDtls.viewMcpDtlsTotalBySearch", mcpDtlsVO);
    	if (obj == null)
    		return 0;
        return (Long)obj;
    }

    /**
     * 2022.12.12 LSH
     * 종합현황 - 의료비내역 - 의료비 검색 총합 조회
     */
    public long viewMcpDtlsTotalByAply(McpDtlsVO mcpDtlsVO) {
    	Object obj = view("McpDtls.viewMcpDtlsTotalByAply", mcpDtlsVO);
    	if (obj == null)
    		return 0;
        return (Long)obj;
    }
}