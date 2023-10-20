 package business.bio.relief.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.bio.relief.service.BioReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 구제급여신청을 관리하는 DAO 클래스
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
 * @class   : BioReliefDAO
 * @author  : LSH
 * @since   : 2023.01.16
 * @version : 2.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("BioReliefDAO")
@SuppressWarnings({"all"})
public class BioReliefDAO extends BaseDAO {

    /**
     * 구제급여신청 페이징목록 조회
     */
    public PaginatedArrayList listBioRelief(BioReliefVO bioReliefVO, int currPage, int pageSize) {
        return pageList("BioRelief.listBioRelief", bioReliefVO, currPage, pageSize);
    }

    /**
     * 구제급여신청 목록 조회
     */
    public List listBioRelief(BioReliefVO bioReliefVO) {
        return list("BioRelief.listBioRelief", bioReliefVO);
    }

    /**
     * 마이페이지 구제급여신청 목록조회
     */
    public List listBioReliefMypage(BioReliefVO bioReliefVO) {
        return list("BioRelief.listBioReliefMypage", bioReliefVO);
    }

    /**
     * 구제급여신청 상세 조회
     */
    public BioReliefVO viewBioRelief(BioReliefVO bioReliefVO) {
        return (BioReliefVO)view("BioRelief.viewBioRelief", bioReliefVO);
    }

    /**
     * 구제급여신청 본인이 작성한 임시저장 KEY 조회
     * @param bioReliefVO.aplySeCd 신청구분
     * @param bioReliefVO.aplcntNo 신청인사용자번호
     * @param bioReliefVO.prgreStusCd 처리상태
     * @param bioReliefVO.rgtrNo 작성자번호
     */
    public BioReliefVO viewBioReliefAplyLast(BioReliefVO bioReliefVO) {
        return (BioReliefVO)view("BioRelief.viewBioReliefAplyLast", bioReliefVO);
    }

    /**
     * 관리자대행으로 구제급여신청 임시저장한 최종 신청정보 KEY 조회
     * @param bioReliefVO.prgreStusCd 처리상태 (임시저장상태)
     * @param bioReliefVO.rgtrNo 작성자번호 (관리자번호)
     */
    public BioReliefVO viewBioReliefAplyLastAdmn(BioReliefVO bioReliefVO) {
        return (BioReliefVO)view("BioRelief.viewBioReliefAplyLastAdmn", bioReliefVO);
    }

    /**
     * 마이페이지 - 식별아이디기준 최종 KEY 조회
     */
    public BioReliefVO viewBioReliefByIdntfcId(String idntfcId) {
        return (BioReliefVO)view("BioRelief.viewBioReliefByIdntfcId", idntfcId);
    }

    /**
     * 구제급여신청 등록
     */
    public int regiBioRelief(BioReliefVO bioReliefVO) {
        return insert("BioRelief.regiBioRelief", bioReliefVO);
    }

    /**
     * 구제급여신청 이력저장
     */
    public int regiBioReliefHst(BioReliefVO bioReliefVO) {
        return insert("BioRelief.regiBioReliefHst", bioReliefVO);
    }


    /**
     * 구제급여신청 수정
     */
    public int updtBioRelief(BioReliefVO bioReliefVO) {
        return update("BioRelief.updtBioRelief", bioReliefVO);
    }

    /**
     * 구제급여신청 삭제
     */
    public int deltBioRelief(BioReliefVO bioReliefVO) {
        return delete("BioRelief.deltBioRelief", bioReliefVO);
    }
}