 package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.LbdyNdxVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 생체지표정보을 관리하는 DAO 클래스
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
 * @class   : LbdyNdxDAO
 * @author  : LSH
 * @since   : 2021.11.22
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("LbdyNdxDAO")
@SuppressWarnings({"all"})
public class LbdyNdxDAO extends BaseDAO {

    /**
     * 생체지표정보 목록 조회
     */
    public List listLbdyNdx(LbdyNdxVO lbdyNdxVO) {
        return list("LbdyNdx.listLbdyNdx", lbdyNdxVO);
    }

    /**
     * 생체지표정보 상세 조회
     */
    public LbdyNdxVO viewLbdyNdx(LbdyNdxVO lbdyNdxVO) {
        return (LbdyNdxVO)view("LbdyNdx.viewLbdyNdx", lbdyNdxVO);
    }

    /**
     * 생체지표정보 등록
     */
    public int regiLbdyNdx(LbdyNdxVO lbdyNdxVO) {
        return insert("LbdyNdx.regiLbdyNdx", lbdyNdxVO);
    }

    /**
     * 생체지표정보 수정
     */
    public int updtLbdyNdx(LbdyNdxVO lbdyNdxVO) {
        return update("LbdyNdx.updtLbdyNdx", lbdyNdxVO);
    }

    /**
     * 생체지표정보 삭제
     */
    public int deltLbdyNdx(LbdyNdxVO lbdyNdxVO) {
        return delete("LbdyNdx.deltLbdyNdx", lbdyNdxVO);
    }

}