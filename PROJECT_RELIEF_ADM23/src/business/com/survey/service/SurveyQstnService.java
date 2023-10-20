package business.com.survey.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 설문관리 Service Interface
 * 
 * @class   : SurveyQstnService
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface SurveyQstnService {

    /**
     * 설문관리 페이징목록 조회
     */
    public PaginatedArrayList listQstnMng(SurveyVO surveyVO, int currPage, int pageSize) throws Exception;

    /**
     * 설문관리 목록조회
     */
    public List listQstnMng(SurveyVO surveyVO) throws Exception;

    /**
     * 설문관리 상세조회
     */
    public SurveyVO viewQstnMng(SurveyVO surveyVO) throws Exception;

    /**
     * 설문관리 등록,수정,삭제
     */
    public String saveQstnMng(SurveyVO surveyVO) throws Exception;

    /**
     * 설문문항관리 페이징목록 조회
     */
    public PaginatedArrayList listQstnItem(SurveyVO surveyVO, int currPage, int pageSize) throws Exception;

    /**
     * 설문문항관리 목록조회
     */
    public List listQstnItem(SurveyVO surveyVO) throws Exception;

    /**
     * 설문문항관리 상세조회
     */
    public SurveyVO viewQstnItem(SurveyVO surveyVO) throws Exception;

    /**
     * 설문문항관리 등록,수정,삭제
     */
    public String saveQstnItem(SurveyVO surveyVO) throws Exception;

}