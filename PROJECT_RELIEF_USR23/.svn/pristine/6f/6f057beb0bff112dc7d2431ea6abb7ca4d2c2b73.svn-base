package business.com.survey.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 설문응답 Service Interface
 * 
 * @class   : SurveyRspnsService
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface SurveyRspnsService {

    /**
     * 설문응답관리 페이징목록 조회
     */
    public PaginatedArrayList listRspnsMng(SurveyVO surveyVO, int currPage, int pageSize) throws Exception;

    /**
     * 설문응답관리 목록조회
     */
    public List listRspnsMng(SurveyVO surveyVO) throws Exception;

    /**
     * 설문응답관리 상세조회
     */
    public SurveyVO viewRspnsMng(SurveyVO surveyVO) throws Exception;
    
    /**
     * 설문응답관리 상세조회
     * - 신청번호기준 조회
     * - 설문응답목록 포함
     */
    public SurveyVO viewRspnsMngByAplyNo(String aplyNo) throws Exception;

    /**
     * 설문응답상세 목록조회
     */
    public List listRspnsItem(String rspnsMngNo) throws Exception;

    /**
     * 설문응답 저장처리
     */
    public String saveSurvey(SurveyVO surveyVO) throws Exception;

    /**
     * 설문응답 신청번호 업데이트
     */
    public int updtSurvey(SurveyVO surveyVO) throws Exception;

    /**
     * 2022.01.20 LSH
     * 설문응답 수정용도 상세조회 (설문응답상세 포함)
     */
    public Map viewRspnsMap(SurveyVO surveyVO) throws Exception;
}