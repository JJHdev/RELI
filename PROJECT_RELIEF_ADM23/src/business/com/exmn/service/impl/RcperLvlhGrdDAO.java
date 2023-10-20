 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.McpDtlsVO;
import business.com.exmn.service.RcperLvlhVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 요양생활수당평가등급을 관리하는 DAO 클래스
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
 * @class   : RcperLvlhGrdDAO
 * @author  : LSH
 * @since   : 2022.12.27
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("RcperLvlhGrdDAO")
@SuppressWarnings({"all"})
public class RcperLvlhGrdDAO extends BaseDAO {

    /**
     * 요양생활수당평가등급 목록 조회
     */
    public List listRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) {
        return list("RcperLvlhGrd.listRcperLvlhGrd", rcperLvlhVO);
    }

    /**
     * 요양생활수당평가등급 상세 조회
     */
    public RcperLvlhVO viewRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) {
        return (RcperLvlhVO)view("RcperLvlhGrd.viewRcperLvlhGrd", rcperLvlhVO);
    }

    /**
     * 요양생활수당평가등급 등록
     */
    public int regiRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlhGrd.regiRcperLvlhGrd", rcperLvlhVO);
    }

    /**
     * 요양생활수당평가등급 이력등록
     */
    public int regiRcperLvlhGrdHst(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlhGrd.regiRcperLvlhGrdHst", rcperLvlhVO);
    }

    /**
     * 요양생활수당평가등급 수정
     */
    public int updtRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) {
        return update("RcperLvlhGrd.updtRcperLvlhGrd", rcperLvlhVO);
    }

    /**
     * 요양생활수당평가등급 삭제
     */
    public int deltRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) {
        return delete("RcperLvlhGrd.deltRcperLvlhGrd", rcperLvlhVO);
    }

    /**
     * 평가점수 / 최종피해등급 산출
     * @param rcperLvlhVO.svrtyScreStr 점수를 파이프라인으로 묶은 문자열
     *                                 ex) 31.25|12.5|56.25|62.5|37.5
     * @return lastDmgeScre  최종평가점수
     * @return lastDmgeGrdCd 최종피해등급
     */
    public RcperLvlhVO viewDmgeGrdScre(RcperLvlhVO rcperLvlhVO) {
        return (RcperLvlhVO)view("RcperLvlhGrd.viewDmgeGrdScre", rcperLvlhVO);
    }
}