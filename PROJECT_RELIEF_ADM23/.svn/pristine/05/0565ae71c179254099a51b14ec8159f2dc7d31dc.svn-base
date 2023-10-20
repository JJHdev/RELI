 package business.com.exmn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.PrptExmnVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 예비조사을 관리하는 DAO 클래스
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
 * @class   : PrptExmnDAO
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("PrptExmnDAO")
@SuppressWarnings({"all"})
public class PrptExmnDAO extends BaseDAO {

    /**
     * 예비조사 페이징목록 조회
     */
    public PaginatedArrayList listPrptExmn(PrptExmnVO prptExmnVO, int currPage, int pageSize) {
        return pageList("PrptExmn.listPrptExmn", prptExmnVO, currPage, pageSize);
    }

    /**
     * 예비조사 목록 조회
     */
    public List listPrptExmn(PrptExmnVO prptExmnVO) {
        return list("PrptExmn.listPrptExmn", prptExmnVO);
    }

    /**
     * 예비조사 목록 카운트
     */
    public int listPrptExmnCount(PrptExmnVO prptExmnVO) {
        return (Integer)view("PrptExmn.listPrptExmnCount", prptExmnVO);
    }

    /**
     * 예비조사 상세 조회
     */
    public PrptExmnVO viewPrptExmn(PrptExmnVO prptExmnVO) {
        return (PrptExmnVO)view("PrptExmn.viewPrptExmn", prptExmnVO);
    }

    /**
     * 2021.12.03 LSH
     * 예비조사 신청번호 기준 최종 조사차수 조회
     */
    public String getPrptExmnOderLast(String aplyNo) {
        return (String)view("PrptExmn.getPrptExmnOderLast", aplyNo);
    }

    /**
     * 예비조사 등록
     */
    public int regiPrptExmn(PrptExmnVO prptExmnVO) {
        return insert("PrptExmn.regiPrptExmn", prptExmnVO);
    }

    /**
     * 예비조사 이력등록
     */
    public int regiPrptExmnHst(PrptExmnVO prptExmnVO) {
        return insert("PrptExmn.regiPrptExmnHst", prptExmnVO);
    }
    
    /**
     * 예비조사 수정
     */
    public int updtPrptExmn(PrptExmnVO prptExmnVO) {
        return update("PrptExmn.updtPrptExmn", prptExmnVO);
    }

    /**
     * 예비조사 삭제
     */
    public int deltPrptExmn(PrptExmnVO prptExmnVO) {
        return delete("PrptExmn.deltPrptExmn", prptExmnVO);
    }

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 예비조사 목록 조회
     */
    public List listPrptExmnMypage(String aplyNo) {
        return list("PrptExmn.listPrptExmnMypage", aplyNo);
    }
    
    /**
     * 2021.12.15 LSH
     * 예비조사 기인정자의 심의회일자 일괄 업데이트
     */
    public int updtPrptExmnLgcyAll(PrptExmnVO prptExmnVO) {
        return update("PrptExmn.updtPrptExmnLgcyAll", prptExmnVO);
    }
}