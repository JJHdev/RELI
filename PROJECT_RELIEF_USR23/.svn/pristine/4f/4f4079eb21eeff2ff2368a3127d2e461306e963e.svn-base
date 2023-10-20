 package business.com.survey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.survey.service.SurveyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 설문관리을 관리하는 DAO 클래스
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
 * @class   : SurveyQstnDAO
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("SurveyQstnDAO")
@SuppressWarnings({"all"})
public class SurveyQstnDAO extends BaseDAO {

    /**
     * 설문관리 페이징목록 조회
     */
    public PaginatedArrayList listQstnMng(SurveyVO surveyVO, int currPage, int pageSize) {
        return pageList("SurveyQstn.listQstnMng", surveyVO, currPage, pageSize);
    }

    /**
     * 설문관리 목록 조회
     */
    public List listQstnMng(SurveyVO surveyVO) {
        return list("SurveyQstn.listQstnMng", surveyVO);
    }

    /**
     * 설문관리 상세 조회
     */
    public SurveyVO viewQstnMng(SurveyVO surveyVO) {
        return (SurveyVO)view("SurveyQstn.viewQstnMng", surveyVO);
    }

    /**
     * 설문관리 등록
     */
    public int regiQstnMng(SurveyVO surveyVO) {
        return insert("SurveyQstn.regiQstnMng", surveyVO);
    }

    /**
     * 설문관리 수정
     */
    public int updtQstnMng(SurveyVO surveyVO) {
        return update("SurveyQstn.updtQstnMng", surveyVO);
    }

    /**
     * 설문관리 삭제
     */
    public int deltQstnMng(SurveyVO surveyVO) {
        return delete("SurveyQstn.deltQstnMng", surveyVO);
    }

    /**
     * 설문문항관리 페이징목록 조회
     */
    public PaginatedArrayList listQstnItem(SurveyVO surveyVO, int currPage, int pageSize) {
        return pageList("SurveyQstn.listQstnItem", surveyVO, currPage, pageSize);
    }

    /**
     * 설문문항관리 목록 조회
     */
    public List listQstnItem(SurveyVO surveyVO) {
        return list("SurveyQstn.listQstnItem", surveyVO);
    }

    /**
     * 설문문항관리 상세 조회
     */
    public SurveyVO viewQstnItem(SurveyVO surveyVO) {
        return (SurveyVO)view("SurveyQstn.viewQstnItem", surveyVO);
    }

    /**
     * 설문문항관리 등록
     */
    public int regiQstnItem(SurveyVO surveyVO) {
        return insert("SurveyQstn.regiQstnItem", surveyVO);
    }

    /**
     * 설문문항관리 수정
     */
    public int updtQstnItem(SurveyVO surveyVO) {
        return update("SurveyQstn.updtQstnItem", surveyVO);
    }

    /**
     * 설문문항관리 삭제
     */
    public int deltQstnItem(SurveyVO surveyVO) {
        return delete("SurveyQstn.deltQstnItem", surveyVO);
    }

}