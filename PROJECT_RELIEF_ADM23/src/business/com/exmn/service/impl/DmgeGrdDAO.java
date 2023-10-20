 package business.com.exmn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.DmgeGrdVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 피해등급을 관리하는 DAO 클래스
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
 * @class   : DmgeGrdDAO
 * @author  : LSH
 * @since   : 2022.12.22
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("DmgeGrdDAO")
@SuppressWarnings({"all"})
public class DmgeGrdDAO extends BaseDAO {

    /**
     * 피해등급 페이징목록 조회
     */
    public PaginatedArrayList listDmgeGrd(DmgeGrdVO dmgeGrdVO, int currPage, int pageSize) {
        return pageList("DmgeGrd.listDmgeGrd", dmgeGrdVO, currPage, pageSize);
    }

    /**
     * 피해등급 목록 조회
     */
    public List listDmgeGrd(DmgeGrdVO dmgeGrdVO) {
        return list("DmgeGrd.listDmgeGrd", dmgeGrdVO);
    }

    /**
     * 피해등급 목록 카운트
     */
    public int listDmgeGrdCount(DmgeGrdVO dmgeGrdVO) {
        return (Integer)view("DmgeGrd.listDmgeGrdCount", dmgeGrdVO);
    }

    /**
     * 피해등급 상세 조회
     */
    public DmgeGrdVO viewDmgeGrd(DmgeGrdVO dmgeGrdVO) {
        return (DmgeGrdVO)view("DmgeGrd.viewDmgeGrd", dmgeGrdVO);
    }

    /**
     * 피해등급 등록
     */
    public int regiDmgeGrd(DmgeGrdVO dmgeGrdVO) {
        return insert("DmgeGrd.regiDmgeGrd", dmgeGrdVO);
    }
    
    /**
     * 피해등급 수정
     */
    public int updtDmgeGrd(DmgeGrdVO dmgeGrdVO) {
        return update("DmgeGrd.updtDmgeGrd", dmgeGrdVO);
    }

    /**
     * 피해등급 삭제
     */
    public int deltDmgeGrd(DmgeGrdVO dmgeGrdVO) {
        return delete("DmgeGrd.deltDmgeGrd", dmgeGrdVO);
    }

    /**
     * 피해등급관리 년도별 목록 조회 
     */
    public List listDmgeGrdGroup() {
        return list("DmgeGrd.listDmgeGrdGroup", null);
    }

    /**
     * 피해등급 년도별 상세 조회
     */
    public DmgeGrdVO viewDmgeGrdGroup(DmgeGrdVO dmgeGrdVO) {
        return (DmgeGrdVO)view("DmgeGrd.viewDmgeGrdGroup", dmgeGrdVO);
    }
}