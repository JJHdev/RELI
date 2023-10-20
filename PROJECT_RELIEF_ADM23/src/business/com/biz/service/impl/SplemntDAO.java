 package business.com.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.biz.service.SplemntVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 보완요청을 관리하는 DAO 클래스
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
 * @class   : SplemntDAO
 * @author  : LSH
 * @since   : 2021.10.24
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("SplemntDAO")
@SuppressWarnings({"all"})
public class SplemntDAO extends BaseDAO {

    /**
     * 보완요청 페이징목록 조회
     */
    public PaginatedArrayList listSplemnt(SplemntVO splemntVO, int currPage, int pageSize) {
        return pageList("Splemnt.listSplemnt", splemntVO, currPage, pageSize);
    }

    /**
     * 보완요청 목록 조회
     */
    public List listSplemnt(SplemntVO splemntVO) {
        return list("Splemnt.listSplemnt", splemntVO);
    }

    /**
     * 보완요청 상세 조회
     */
    public SplemntVO viewSplemnt(SplemntVO splemntVO) {
        return (SplemntVO)view("Splemnt.viewSplemnt", splemntVO);
    }

    /**
     * 보완요청 등록
     */
    public int regiSplemnt(SplemntVO splemntVO) {
        return insert("Splemnt.regiSplemnt", splemntVO);
    }

    /**
     * 보완요청 수정
     */
    public int updtSplemnt(SplemntVO splemntVO) {
        return update("Splemnt.updtSplemnt", splemntVO);
    }

    /**
     * 보완요청 삭제
     */
    public int deltSplemnt(SplemntVO splemntVO) {
        return delete("Splemnt.deltSplemnt", splemntVO);
    }

    /**
     * 보완요청 중인 최종건 상세 조회
     */
    public SplemntVO viewSplemntLast(SplemntVO splemntVO) {
        return (SplemntVO)view("Splemnt.viewSplemntLast", splemntVO);
    }
}