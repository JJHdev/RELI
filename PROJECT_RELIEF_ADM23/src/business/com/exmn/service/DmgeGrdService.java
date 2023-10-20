package business.com.exmn.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 피해등급 Service Interface
 * 
 * @class   : DmgeGrdService
 * @author  : LSH
 * @since   : 2022.12.22
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface DmgeGrdService {

    /**
     * 피해등급 페이징목록 조회
     */
    public PaginatedArrayList listDmgeGrd(DmgeGrdVO dmgeGrdVO, int currPage, int pageSize) throws Exception;

    /**
     * 피해등급 목록조회
     */
    public List listDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception;

    /**
     * 피해등급 상세조회
     */
    public DmgeGrdVO viewDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception;

    /**
     * 피해등급 등록,수정,삭제
     */
    public String saveDmgeGrd(DmgeGrdVO dmgeGrdVO) throws Exception;

    /**
     * 피해등급관리 년도별 목록 조회 
     */
    public List listDmgeGrdGroup() throws Exception;
    
    /**
     * 피해등급 년도별 상세 조회
     */
    public DmgeGrdVO viewDmgeGrdGroup(DmgeGrdVO dmgeGrdVO) throws Exception;
}