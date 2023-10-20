package business.com.file.service;

import java.util.List;
import java.util.Map;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 서류양식관리 Service Interface
 *
 * @class   : PapeMngService
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface PapeMngService {

    /**
     * 서류양식관리 페이징목록 조회
     */
    public PaginatedArrayList listPapeMng(PapeMngVO papeMngVO, int currPage, int pageSize) throws Exception;

    /**
     * 서류양식관리 목록조회
     */
    public List listPapeMng(PapeMngVO papeMngVO) throws Exception;

    /**
     * 서류양식관리 상세조회
     */
    public PapeMngVO viewPapeMng(PapeMngVO papeMngVO) throws Exception;

    /**
     * 서류양식관리 등록,수정,삭제
     */
    public String savePapeMng(PapeMngVO papeMngVO) throws Exception;

    /**
     * 2021.10.08 LSH
     * 신청서류 리스트 조회
     */
    public List getListPape(Map paramMap) throws Exception;

    /**
     * 2021.10.08 LSH
     * 신청서류그룹 리스트 조회
     */
    public List getListPapeGroup(Map paramMap) throws Exception;

    /**
     * 2021.12.06 CSLEE
     * [구제급여] 모든(All) 급여종류 목록 조회
     */
    public List listPapeMngUpPapeAll(PapeMngVO papeMngVO) throws Exception;

    /**
     * UpPape(급여종류:공통서류/의료비...)에 속하는 모든 제출서류 코드 목록 조회
     */
    public List listPapeMngPapeCodeByUpPape(PapeMngVO papeMngVO) throws Exception;

    /**
     * ===============================
     * * [USR] 신청서류 양식 화면 관련
     * 2021.12.03 CSLEE 추가
     * ===============================
     */

    /**
     * [구제급여] 신청구분 목록 조회
     */
    public List listPapeMngAplySe(PapeMngVO papeMngVO) throws Exception;

    /**
     * [구제급여]급여 종류 목록 조회
     */
    public List listPapeMngUpPape(PapeMngVO papeMngVO) throws Exception;

    /**
     * [구제급여] 제출서류 (공통서류/추가서류 모두 포함)
     */
    public List listPapeMngPape(PapeMngVO papeMngVO) throws Exception;

    /**
     * 다운로드 할 양식 정보 목록 조회
     */
    public List listPapeMngDownFile(PapeMngVO papeMngVO) throws Exception;

    /**
     * 2022.01.11 CSLEE 추가
     * 다운로드 카운트 증가 저장
     */
    public int updtPapeMngDownCount(PapeMngVO papeMngVO) throws Exception;
}