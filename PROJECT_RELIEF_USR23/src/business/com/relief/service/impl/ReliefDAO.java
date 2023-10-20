 package business.com.relief.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.PrptExmnVO;
import business.com.relief.service.ReliefVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 구제급여신청을 관리하는 DAO 클래스
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
 * @class   : ReliefDAO
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("ReliefDAO")
@SuppressWarnings({"all"})
public class ReliefDAO extends BaseDAO {

    /**
     * 구제급여신청 페이징목록 조회
     */
    public PaginatedArrayList listRelief(ReliefVO reliefVO, int currPage, int pageSize) {
        return pageList("Relief.listRelief", reliefVO, currPage, pageSize);
    }

    /**
     * 구제급여신청 목록 조회
     */
    public List listRelief(ReliefVO reliefVO) {
        return list("Relief.listRelief", reliefVO);
    }
    
    /**
     * 2022.01.05 LSH
     * 예비조사/본조사 구제급여 대상자목록조회
     */
    public List listReliefTarget(ReliefVO reliefVO) {
        return list("Relief.listReliefTarget", reliefVO);
    }

    /**
     * 구제급여신청 상세 조회
     */
    public ReliefVO viewRelief(ReliefVO reliefVO) {
        return (ReliefVO)view("Relief.viewRelief", reliefVO);
    }

    /**
     * 2021.10.14 LSH
     * 구제급여신청 본인이 작성한 임시저장 KEY 조회
     * @param reliefVO.aplySeCd 신청구분
     * @param reliefVO.aplcntNo 신청인사용자번호
     * @param reliefVO.prgreStusCd 처리상태
     * @param reliefVO.rgtrNo 작성자번호
     */
    public ReliefVO viewReliefAplyLast(ReliefVO reliefVO) {
        return (ReliefVO)view("Relief.viewReliefAplyLast", reliefVO);
    }

    /**
     * 2022.12.01 LSH
     * 관리자대행으로 구제급여신청 임시저장한 최종 신청정보 KEY 조회
     * @param reliefVO.prgreStusCd 처리상태 (임시저장상태)
     * @param reliefVO.rgtrNo 작성자번호 (관리자번호)
     */
    public ReliefVO viewReliefAplyLastAdmn(ReliefVO reliefVO) {
        return (ReliefVO)view("Relief.viewReliefAplyLastAdmn", reliefVO);
    }

    /**
     * 2021.11.08 LSH
     * 마이페이지 - 식별아이디기준 최종 KEY 조회
     */
    public ReliefVO viewReliefByIdntfcId(String idntfcId) {
        return (ReliefVO)view("Relief.viewReliefByIdntfcId", idntfcId);
    }

    /**
     * 구제급여신청 등록
     */
    public int regiRelief(ReliefVO reliefVO) {
        return insert("Relief.regiRelief", reliefVO);
    }

    /**
     * 2021.10.14 LSH
     * 구제급여신청 이력저장
     */
    public int regiReliefHst(ReliefVO reliefVO) {
        return insert("Relief.regiReliefHst", reliefVO);
    }


    /**
     * 구제급여신청 수정
     */
    public int updtRelief(ReliefVO reliefVO) {
        return update("Relief.updtRelief", reliefVO);
    }

    /**
     * 구제급여신청 삭제
     */
    public int deltRelief(ReliefVO reliefVO) {
        return delete("Relief.deltRelief", reliefVO);
    }
    

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 페이징목록 조회
     */
    public PaginatedArrayList listReliefGive(ReliefVO reliefVO, int currPage, int pageSize) {
        return pageList("Relief.listReliefGive", reliefVO, currPage, pageSize);
    }

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 목록 조회
     */
    public List listReliefGive(ReliefVO reliefVO) {
        return list("Relief.listReliefGive", reliefVO);
    }

    /**
     * 2021.12.08 LSH
     * 마이페이지 구제급여신청 목록조회
     */
    public List listReliefMypage(ReliefVO reliefVO) {
        return list("Relief.listReliefMypage", reliefVO);
    }

    /**
     * 2021.12.07 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 의료비 총합 조회
     */
    public Map viewReliefGiveMCP(ReliefVO reliefVO) {
        return (Map)view("Relief.viewReliefGiveMCP", reliefVO);
    }
    /**
     * 2021.12.07 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 요양생활수당 총합 조회
     */
    public Map viewReliefGiveRCP(ReliefVO reliefVO) {
        return (Map)view("Relief.viewReliefGiveRCP", reliefVO);
    }

    /**
     * 2021.12.09 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 의료비 세부내역 목록조회
     */
    public PaginatedArrayList listReliefGiveMCPDtls(ReliefVO reliefVO, int currPage, int pageSize) {
        return pageList("Relief.listReliefGiveMCPDtls", reliefVO, currPage, pageSize);
    }    
    
    /**
     * 2021.12.09 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 요양생활수당 세부내역 목록조회
     */
    public PaginatedArrayList listReliefGiveRCPDtls(ReliefVO reliefVO, int currPage, int pageSize) {
        return pageList("Relief.listReliefGiveRCPDtls", reliefVO, currPage, pageSize);
    }
    
    /**
     * 2021.12.15 LSH
     * 예비조사 기인정자의 진행상태 일괄 업데이트
     */
    public int updtReliefStatusLgcyAll(ReliefVO reliefVO) {
        return update("Relief.updtReliefStatusLgcyAll", reliefVO);
    }
}