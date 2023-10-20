 package business.bio.cmit.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.bio.cmit.service.BioCmitMngVO;
import business.com.cmit.service.CmitMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 위원회 소속 위원을 관리하는 DAO 클래스
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
 * @class   : BioCmitMngDAO
 * @author  : LSH
 * @since   : 2023.01.30
 * @version : 2.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Repository("BioCmitMngDAO")
@SuppressWarnings({"all"})
public class BioCmitMngDAO extends BaseDAO {

    /**
     * 위원회 소속 위원 페이징목록 조회
     */
    public PaginatedArrayList listBioCmitMng(BioCmitMngVO bioCmitMngVO, int currPage, int pageSize) {
        return pageList("BioCmitMng.listBioCmitMng", bioCmitMngVO, currPage, pageSize);
    }

    /**
     * 위원회 소속 위원 목록 조회
     */
    public List listBioCmitMng(BioCmitMngVO bioCmitMngVO) {
        return list("BioCmitMng.listBioCmitMng", bioCmitMngVO);
    }

    /**
     * 위원회 소속 위원 등록
     */
    public int regiBioCmitMng(BioCmitMngVO bioCmitMngVO) {
        return insert("BioCmitMng.regiBioCmitMng", bioCmitMngVO);
    }

    /**
     * 위원회 소속 위원 삭제
     */
    public int deltBioCmitMng(BioCmitMngVO bioCmitMngVO) {
        return delete("BioCmitMng.deltBioCmitMng", bioCmitMngVO);
    }

    /**
     * 위원회 소속 위원 전체삭제
     */
    public int deltBioCmitMngAll(BioCmitMngVO bioCmitMngVO) {
        return delete("BioCmitMng.deltBioCmitMngAll", bioCmitMngVO);
    }

    /**
     * 해당 위원회에 동일한 위원이 존재하는지 체크
     * @param bioCmitMngVO.cmitMngNo 위원회 번호
     * @param bioCmitMngVO.mfcmmNo   위원 번호
     */
    public boolean existBioCmitMng(BioCmitMngVO bioCmitMngVO) {
        return (Boolean)view("BioCmitMng.existBioCmitMng", bioCmitMngVO);
    }
    
    /**
     * 2023.02.13 LSH
     * 조건에 해당하는 위원회 건수 조회
     */
    public int getBioCmitMngCountForTenure(BioCmitMngVO bioCmitMngVO) {
        return (Integer)view("BioCmitMng.listBioCmitMngCount", bioCmitMngVO);
    }
}