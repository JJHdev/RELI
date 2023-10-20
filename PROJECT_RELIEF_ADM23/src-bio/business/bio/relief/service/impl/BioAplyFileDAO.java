 package business.bio.relief.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.bio.relief.service.BioAplyFileVO;
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
 * @since   : 2023.01.25
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
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    public PaginatedArrayList listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO, int currPage, int pageSize) {
    	return pageList("BioAplyFile.listBioAplySubmitFile", bioAplyFileVO, currPage, pageSize);
    }
    /**
     * 공통사용 - 제출서류목록조회
     */
    public List listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO) {
    	return list("BioAplyFile.listBioAplySubmitFile", bioAplyFileVO);
    }
    /**
     * 공통사용 - 제출서류 상세 조회
     */
    public BioAplyFileVO viewBioAplySubmitFile(String sn) {
    	return (BioAplyFileVO)view("BioAplyFile.viewBioAplySubmitFile", sn);
    }
    /**
     * 신청파일 상세 조회
     */
    public BioAplyFileVO viewBioAplyFile(BioAplyFileVO bioAplyFileVO) {
    	return (BioAplyFileVO)view("BioAplyFile.viewBioAplyFile", bioAplyFileVO);
    }

    /**
     * 서류기준 신청파일 조회
     */
    public List listBioAplyFileByPape(Map paramMap) {
        return list("BioAplyFile.listBioAplyFileByPape", paramMap);    
    }

    /**
     * 신청첨부파일 등록
     */
    public int regiBioAplyFile(BioAplyFileVO bioAplyFileVO) {
        return insert("BioAplyFile.regiBioAplyFile", bioAplyFileVO);
    }

    /**
     * 신청첨부파일 수정
     */
    public int updtBioAplyFile(BioAplyFileVO bioAplyFileVO) {
        return update("BioAplyFile.updtBioAplyFile", bioAplyFileVO);
    }

    /**
     * 신청첨부파일 삭제
     */
    public int deltBioAplyFile(BioAplyFileVO bioAplyFileVO) {
        return delete("BioAplyFile.deltBioAplyFile", bioAplyFileVO);
    }
    
    /**
     * 2022.01.11 LSH
     * 특정날짜가 지난 임시저장 신청파일 조회
     * params.diffDay : 현재날짜 기준 +/- 날짜수
     */
    public List listAplyTempFile(Map params) {
    	return list("BioAplyFile.listAplyTempFile", params);
    }
}