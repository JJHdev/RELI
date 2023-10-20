package business.bio.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.bio.cmit.service.BioCmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 위원회피해조사을 관리하는 DAO 클래스
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
 * @class   : BioCmitDmgeDAO
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Repository("BioCmitDmgeDAO")
@SuppressWarnings({"all"})
public class BioCmitDmgeDAO extends BaseDAO {

    /**
     * 위원회피해조사 페이징목록 조회
     */
    public PaginatedArrayList listBioCmitDmge(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) {
        return pageList("BioCmitDmge.listBioCmitDmge", bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회피해조사 목록 조회
     */
    public List listBioCmitDmge(BioCmitMngVO bioCmitMngVO) {
        return list("BioCmitDmge.listBioCmitDmge", bioCmitMngVO);
    }

    /**
     * 위원회피해조사 상세 조회
     */
    public BioCmitMngVO viewBioCmitDmge(BioCmitMngVO bioCmitMngVO) {
        return (BioCmitMngVO)view("BioCmitDmge.viewBioCmitDmge", bioCmitMngVO);
    }

    /**
     * 위원회피해조사 등록
     */
    public int regiBioCmitDmge(BioCmitMngVO bioCmitMngVO) {
        return insert("BioCmitDmge.regiBioCmitDmge", bioCmitMngVO);
    }

    /**
     * 위원회피해조사 수정
     */
    public int updtBioCmitDmge(BioCmitMngVO bioCmitMngVO) {
        return update("BioCmitDmge.updtBioCmitDmge", bioCmitMngVO);
    }

    /**
     * 위원회피해조사 삭제
     */
    public int deltBioCmitDmge(BioCmitMngVO bioCmitMngVO) {
        return delete("BioCmitDmge.deltBioCmitDmge", bioCmitMngVO);
    }

}