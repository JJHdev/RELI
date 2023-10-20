 package business.bio.file.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.bio.file.service.BioAplyFileVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 살생물제품 신청첨부파일을 관리하는 DAO 클래스
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
 * @class   : BioAplyFileDAO
 * @author  : LSH
 * @since   : 2023.01.16
 * @version : 2.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("BioAplyFileDAO")
@SuppressWarnings({"all"})
public class BioAplyFileDAO extends BaseDAO {

    /**
     * 신청첨부파일 페이징목록 조회
     */
    public PaginatedArrayList listBioAplyFile(BioAplyFileVO aplyFileVO, int currPage, int pageSize) {
        return pageList("BioAplyFile.listBioAplyFile", aplyFileVO, currPage, pageSize);
    }

    /**
     * 신청첨부파일 목록 조회
     */
    public List listBioAplyFile(BioAplyFileVO aplyFileVO) {
        return list("BioAplyFile.listBioAplyFile", aplyFileVO);
    }
    /**
     * 서류기준 신청파일 조회
     */
    public List listBioAplyFileByPape(Map paramMap) {
        return list("BioAplyFile.listBioAplyFileByPape", paramMap);    
    }

    /**
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    public PaginatedArrayList listBioAplySubmitFile(BioAplyFileVO aplyFileVO, int currPage, int pageSize) {
    	return pageList("BioAplyFile.listBioAplySubmitFile", aplyFileVO, currPage, pageSize);
    }
    /**
     * 공통사용 - 제출서류목록조회
     */
    public List listBioAplySubmitFile(BioAplyFileVO aplyFileVO) {
    	return list("BioAplyFile.listBioAplySubmitFile", aplyFileVO);
    }

    /**
     * 신청첨부파일 상세 조회
     */
    public BioAplyFileVO viewBioAplyFile(BioAplyFileVO aplyFileVO) {
        return (BioAplyFileVO)view("BioAplyFile.viewBioAplyFile", aplyFileVO);
    }

    /**
     * 공통사용 - 제출서류 상세 조회
     */
    public BioAplyFileVO viewBioAplySubmitFile(String sn) {
    	return (BioAplyFileVO)view("BioAplyFile.viewBioAplySubmitFile", sn);
    }

    /**
     * 신청첨부파일 등록
     */
    public int regiBioAplyFile(BioAplyFileVO aplyFileVO) {
        return insert("BioAplyFile.regiBioAplyFile", aplyFileVO);
    }

    /**
     * 신청첨부파일 수정
     */
    public int updtBioAplyFile(BioAplyFileVO aplyFileVO) {
        return update("BioAplyFile.updtBioAplyFile", aplyFileVO);
    }

    /**
     * 신청첨부파일 삭제
     */
    public int deltBioAplyFile(BioAplyFileVO aplyFileVO) {
        return delete("BioAplyFile.deltBioAplyFile", aplyFileVO);
    }
}