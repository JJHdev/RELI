package business.com.exmn.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;
import common.file.FileInfo;

/**
 * [서비스인터페이스] - 의료비내역 Service Interface
 * 
 * @class   : McpDtlsService
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface McpDtlsService {

    /**
     * 의료비내역 페이징목록 조회
     */
    public PaginatedArrayList listMcpDtls(McpDtlsVO mcpDtlsVO, int currPage, int pageSize) throws Exception;

    /**
     * 의료비내역 목록조회
     */
    public List listMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception;

    /**
     * 의료비내역 상세조회
     */
    public McpDtlsVO viewMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception;

    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 목록 조회
     */
    public List listMcpDtlsSummary(McpDtlsVO mcpDtlsVO) throws Exception;
    
    /**
     * 2021.12.01 LSH
     * 본조사 의료비산정결과 집계 합계
     */
    public List listMcpDtlsSummaryTotal(McpDtlsVO mcpDtlsVO) throws Exception;

    /**
     * 의료비내역 등록,수정,삭제
     */
    public String saveMcpDtls(McpDtlsVO mcpDtlsVO) throws Exception;
    
    /**
     * 의료비내역 엑셀로드
     */
    public int loadMcpDtls(McpDtlsVO mcpDtlsVO, FileInfo fileInfo) throws Exception;
}