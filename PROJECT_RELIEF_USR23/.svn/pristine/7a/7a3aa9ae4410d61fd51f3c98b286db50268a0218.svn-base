 package business.com.file.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.file.service.AplyFileVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 신청첨부파일을 관리하는 DAO 클래스
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
 * @class   : AplyFileDAO
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("AplyFileDAO")
@SuppressWarnings({"all"})
public class AplyFileDAO extends BaseDAO {

    /**
     * 신청첨부파일 페이징목록 조회
     */
    public PaginatedArrayList listAplyFile(AplyFileVO aplyFileVO, int currPage, int pageSize) {
        return pageList("AplyFile.listAplyFile", aplyFileVO, currPage, pageSize);
    }

    /**
     * 신청첨부파일 목록 조회
     */
    public List listAplyFile(AplyFileVO aplyFileVO) {
        return list("AplyFile.listAplyFile", aplyFileVO);
    }

    /**
     * 신청첨부파일 상세 조회
     */
    public AplyFileVO viewAplyFile(AplyFileVO aplyFileVO) {
        return (AplyFileVO)view("AplyFile.viewAplyFile", aplyFileVO);
    }

    /**
     * 2021.12.10 CSLEE
     * 다중 신청첨부파일 상세 조회
     */
    public List viewAplyFiles(AplyFileVO aplyFileVO) {
        return list("AplyFile.viewAplyFiles", aplyFileVO);
    }

    /**
     * 신청첨부파일 등록
     */
    public int regiAplyFile(AplyFileVO aplyFileVO) {
        return insert("AplyFile.regiAplyFile", aplyFileVO);
    }

    /**
     * 신청첨부파일 수정
     */
    public int updtAplyFile(AplyFileVO aplyFileVO) {
        return update("AplyFile.updtAplyFile", aplyFileVO);
    }

    /**
     * 신청첨부파일 삭제
     */
    public int deltAplyFile(AplyFileVO aplyFileVO) {
        return delete("AplyFile.deltAplyFile", aplyFileVO);
    }

    /**
     * 2021.10.08 LSH
     * 서류기준 신청파일 조회
     */
    public List listAplyFileByPape(Map paramMap) {
        return list("AplyFile.listAplyFileByPape", paramMap);    
    }

    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    public PaginatedArrayList listAplySubmitFile(AplyFileVO aplyFileVO, int currPage, int pageSize) {
    	return pageList("AplyFile.listAplySubmitFile", aplyFileVO, currPage, pageSize);
    }
    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회
     */
    public List listAplySubmitFile(AplyFileVO aplyFileVO) {
    	return list("AplyFile.listAplySubmitFile", aplyFileVO);
    }
    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류 상세 조회
     */
    public AplyFileVO viewAplySubmitFile(String sn) {
    	return (AplyFileVO)view("AplyFile.viewAplySubmitFile", sn);
    }
    
    /**
     * 2022.01.11 LSH
     * 특정날짜가 지난 임시저장 신청파일 조회
     * params.diffDay : 현재날짜 기준 +/- 날짜수
     */
    public List listAplyTempFile(Map params) {
    	return list("AplyFile.listAplyTempFile", params);
    }
}