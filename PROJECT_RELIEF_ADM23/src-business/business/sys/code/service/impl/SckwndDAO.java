 package business.sys.code.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.sys.code.service.CodeVO;
import business.sys.code.service.SckwndVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 상병코드관리을 관리하는 DAO 클래스
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
 * @class   : SckwndDAO
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("SckwndDAO")
@SuppressWarnings({"all"})
public class SckwndDAO extends BaseDAO {

    /**
     * 상병코드관리 페이징목록 조회
     */
    public PaginatedArrayList listSckwnd(SckwndVO sckwndVO, int currPage, int pageSize) {
        return pageList("Sckwnd.listSckwnd", sckwndVO, currPage, pageSize);
    }

    /**
     * 상병코드관리 목록 조회
     */
    public List listSckwnd(SckwndVO sckwndVO) {
        return list("Sckwnd.listSckwnd", sckwndVO);
    }

    /**
     * 상병코드관리 계층형 목록 조회
     */
    public List listSckwndTree(SckwndVO sckwndVO) {
        return list("Sckwnd.listSckwndTree", sckwndVO);
    }

    /**
     * 상병코드관리 상세 조회
     */
    public SckwndVO viewSckwnd(SckwndVO sckwndVO) {
        return (SckwndVO)view("Sckwnd.viewSckwnd", sckwndVO);
    }

    /**
     * 상병코드 중복확인(코드)
     */
    public int confSckwnd(SckwndVO sckwndVO) {
        return (Integer)view("Sckwnd.confSckwnd", sckwndVO);
    }

    /**
     * 상병코드 중복확인(상위코드)
     */
    public int confUpperSckwnd(String sckwndCd) {
        return (Integer)view("Sckwnd.confUpperSckwnd", sckwndCd);
    }

    /**
     * 상병코드 중복확인(하위코드)
     */
    public int confLowerSckwnd(String sckwndCd) {
        return (Integer)view("Sckwnd.confLowerSckwnd", sckwndCd);
    }

    /**
     * 상병코드관리 등록
     */
    public int regiSckwnd(SckwndVO sckwndVO) {
        return save("Sckwnd.regiSckwnd", sckwndVO);
    }

    /**
     * 상병코드관리 수정
     */
    public int updtSckwnd(SckwndVO sckwndVO) {
        return save("Sckwnd.updtSckwnd", sckwndVO);
    }

    /**
     * 상병코드관리 삭제
     */
    public int deltSckwnd(SckwndVO sckwndVO) {
        return save("Sckwnd.deltSckwnd", sckwndVO);
    }

}