 package business.com.file.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.file.service.PapeMngVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 서류양식관리을 관리하는 DAO 클래스
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
 * @class   : PapeMngDAO
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("PapeMngDAO")
@SuppressWarnings({"all"})
public class PapeMngDAO extends BaseDAO {

    /**
     * 서류양식관리 페이징목록 조회
     */
    public PaginatedArrayList listPapeMng(PapeMngVO papeMngVO, int currPage, int pageSize) {
        return pageList("PapeMng.listPapeMng", papeMngVO, currPage, pageSize);
    }

    /**
     * 서류양식관리 목록 조회
     */
    public List listPapeMng(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMng", papeMngVO);
    }

    /**
     * 서류양식관리 상세 조회
     */
    public PapeMngVO viewPapeMng(PapeMngVO papeMngVO) {
        return (PapeMngVO)view("PapeMng.viewPapeMng", papeMngVO);
    }

    /**
     * 서류양식관리 등록
     */
    public int regiPapeMng(PapeMngVO papeMngVO) {
        return insert("PapeMng.regiPapeMng", papeMngVO);
    }

    /**
     * 서류양식관리 수정
     */
    public int updtPapeMng(PapeMngVO papeMngVO) {
        return update("PapeMng.updtPapeMng", papeMngVO);
    }

    /**
     * 서류양식관리 삭제
     */
    public int deltPapeMng(PapeMngVO papeMngVO) {
        return delete("PapeMng.deltPapeMng", papeMngVO);
    }

    /**
     * 2021.10.08 LSH
     * 신청서류 리스트 조회
     */
    public List getListPape(Map paramMap) {
        return list("PapeMng.getListPape", paramMap);
    }

    /**
     * 2021.10.08 LSH
     * 신청서류그룹 리스트 조회
     */
    public List getListPapeGroup(Map paramMap) {
        return list("PapeMng.getListPapeGroup", paramMap);
    }

    /**
     * ===============================
     * * [USR] 신청서류 양식 화면 관련
     * 2021.12.03 CSLEE 추가
     * ===============================
     */

    /**
     * [구제급여] 신청구분 목록 조회
     */
    public List listPapeMngAplySe(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMngAplySe", papeMngVO);
    }
    /**
     * [구제급여]급여 종류 목록 조회
     */
    public List listPapeMngUpPape(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMngUpPape", papeMngVO);
    }

    /**
     * [구제급여] 모든(All) 급여종류 목록 조회
     */
    public List listPapeMngUpPapeAll(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMngUpPapeAll", papeMngVO);
    }

    /**
     * UpPape(급여종류:공통서류/의료비...)에 속하는 모든 제출서류 코드 목록 조회
     */
    public List listPapeMngPapeCodeByUpPape(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMngPapeCodeByUpPape", papeMngVO);
    }

    /**
     * [구제급여] 제출서류 (공통서류/추가서류 모두 포함)
     */
    public List listPapeMngPape(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMngPape", papeMngVO);
    }



    /**
     * 다운로드 할 양식 정보 목록 조회
     */
    public List listPapeMngDownFile(PapeMngVO papeMngVO) {
        return list("PapeMng.listPapeMngDownFile", papeMngVO);
    }

    /**
     * 2022.01.11 CSLEE 추가
     * 다운로드 카운트 증가 저장
     */
    public int updtPapeMngDownCount(PapeMngVO papeMngVO) {
        return update("PapeMng.updtPapeMngDownCount", papeMngVO);
    }

}