 package business.com.survey.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.survey.service.SurveyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 설문응답관리을 관리하는 DAO 클래스
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
 * @class   : SurveyRspnsDAO
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("SurveyRspnsDAO")
@SuppressWarnings({"all"})
public class SurveyRspnsDAO extends BaseDAO {

    /**
     * 설문응답관리 페이징목록 조회
     */
    public PaginatedArrayList listRspnsMng(SurveyVO surveyVO, int currPage, int pageSize) {
        return pageList("SurveyRspns.listRspnsMng", surveyVO, currPage, pageSize);
    }

    /**
     * 설문응답관리 목록 조회
     */
    public List listRspnsMng(SurveyVO surveyVO) {
        return list("SurveyRspns.listRspnsMng", surveyVO);
    }

    /**
     * 설문응답관리 상세 조회
     */
    public SurveyVO viewRspnsMng(SurveyVO surveyVO) {
        return (SurveyVO)view("SurveyRspns.viewRspnsMng", surveyVO);
    }
    /**
     * 설문응답관리 상세 조회 (신청번호기준)
     */
    public SurveyVO viewRspnsMngByAplyNo(String aplyNo) {
        return (SurveyVO)view("SurveyRspns.viewRspnsMngByAplyNo", aplyNo);
    }
    
    /**
     * 설문응답관리 등록
     */
    public int regiRspnsMng(SurveyVO surveyVO) {
        return insert("SurveyRspns.regiRspnsMng", surveyVO);
    }

    /**
     * 설문응답관리 수정 (신청번호 업데이트)
     */
    public int updtRspnsMng(SurveyVO surveyVO) {
        return update("SurveyRspns.updtRspnsMng", surveyVO);
    }

    /**
     * 2022.01.21 LSH
     * 설문응답관리 수정 (서명파일 업데이트)
     */
    public int updtRspnsMngSign(SurveyVO surveyVO) {
        return update("SurveyRspns.updtRspnsMngSign", surveyVO);
    }

    /**
     * 설문응답관리 삭제
     */
    public int deltRspnsMng(SurveyVO surveyVO) {
        return delete("SurveyRspns.deltRspnsMng", surveyVO);
    }

    /**
     * 설문응답상세 목록 조회
     */
    public List listRspnsItem(String rspnsMngNo) {
        return list("SurveyRspns.listRspnsItem", rspnsMngNo);
    }

    /**
     * 설문응답상세 등록
     */
    public int regiRspnsItem(SurveyVO surveyVO) {
        return insert("SurveyRspns.regiRspnsItem", surveyVO);
    }

    /**
     * 2022.01.21 LSH
     * 설문응답상세 수정
     * 
     * @param surveyVO.rspnsMngNo 답변관리번호
     * @param surveyVO.qesitmNo   문항번호
     * @param surveyVO.itemNo     항목번호
     */
    public int updtRspnsItem(SurveyVO surveyVO) {
        return update("SurveyRspns.updtRspnsItem", surveyVO);
    }

    /**
     * 설문응답상세 삭제
     */
    public int deltRspnsItem(SurveyVO surveyVO) {
        return delete("SurveyRspns.deltRspnsItem", surveyVO);
    }
    
	/**
     * 2022.01.21 LSH
	 * 답변관리번호/문항번호/항목번호 기준 
	 * 일치하는 답변항목이 있는지 확인 (true / false)
	 * 
     * @param surveyVO.rspnsMngNo 답변관리번호
     * @param surveyVO.qesitmNo   문항번호
     * @param surveyVO.itemNo     항목번호
	 * */
	public boolean existRspnsItem(SurveyVO surveyVO) {
		return (Boolean)view("SurveyRspns.existRspnsItem", surveyVO);
	}
}