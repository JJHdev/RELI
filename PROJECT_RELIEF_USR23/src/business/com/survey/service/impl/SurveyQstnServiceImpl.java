package business.com.survey.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.survey.service.SurveyQstnService;
import business.com.survey.service.SurveyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - 설문관리 Service 구현 클래스
 * 
 * @class   : SurveyQstnServiceImpl
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("SurveyQstnService")
@SuppressWarnings({"all"})
public class SurveyQstnServiceImpl extends BaseService implements SurveyQstnService {

    @Resource(name = "SurveyQstnDAO")
    private SurveyQstnDAO surveyQstnDAO;
	
    /**
     * 설문관리 페이징목록조회
     */
    @Override
    public PaginatedArrayList listQstnMng(SurveyVO surveyVO, int currPage, int pageSize) throws Exception {
    	return surveyQstnDAO.listQstnMng(surveyVO, currPage, pageSize);
    }

    /**
     * 설문관리 목록조회
     */
    @Override
    public List listQstnMng(SurveyVO surveyVO) throws Exception {
    	return surveyQstnDAO.listQstnMng(surveyVO);
    }

    /**
     * 설문관리 상세조회
     */
	@Override
	public SurveyVO viewQstnMng(SurveyVO surveyVO) throws Exception {
		return surveyQstnDAO.viewQstnMng(surveyVO);
	}

    /**
     * 설문관리 등록
     */
    private int regiQstnMng(SurveyVO surveyVO) throws Exception {
        return surveyQstnDAO.regiQstnMng(surveyVO);
    }

    /**
     * 설문관리 수정
     */
    private int updtQstnMng(SurveyVO surveyVO) throws Exception {
        return surveyQstnDAO.updtQstnMng(surveyVO);
    }

    /**
     * 설문관리 삭제
     */
    private int deltQstnMng(SurveyVO surveyVO) throws Exception {
        return surveyQstnDAO.deltQstnMng(surveyVO);
    }

    /**
     * 설문관리 등록,수정,삭제
     */
	@Override
	public String saveQstnMng(SurveyVO surveyVO) throws Exception {
		
		if (surveyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = surveyVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 설문관리 수정
			ret = updtQstnMng(surveyVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 설문관리 등록
			ret = regiQstnMng(surveyVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 설문관리 삭제
			ret = deltQstnMng(surveyVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
	
    /**
     * 설문문항관리 페이징목록조회
     */
    @Override
    public PaginatedArrayList listQstnItem(SurveyVO surveyVO, int currPage, int pageSize) throws Exception {
    	return surveyQstnDAO.listQstnItem(surveyVO, currPage, pageSize);
    }

    /**
     * 설문문항관리 목록조회
     */
    @Override
    public List listQstnItem(SurveyVO surveyVO) throws Exception {
    	return surveyQstnDAO.listQstnItem(surveyVO);
    }

    /**
     * 설문문항관리 상세조회
     */
	@Override
	public SurveyVO viewQstnItem(SurveyVO surveyVO) throws Exception {
		return surveyQstnDAO.viewQstnItem(surveyVO);
	}

    /**
     * 설문문항관리 등록
     */
    private int regiQstnItem(SurveyVO surveyVO) throws Exception {
        return surveyQstnDAO.regiQstnItem(surveyVO);
    }

    /**
     * 설문문항관리 수정
     */
    private int updtQstnItem(SurveyVO surveyVO) throws Exception {
        return surveyQstnDAO.updtQstnItem(surveyVO);
    }

    /**
     * 설문문항관리 삭제
     */
    private int deltQstnItem(SurveyVO surveyVO) throws Exception {
        return surveyQstnDAO.deltQstnItem(surveyVO);
    }

    /**
     * 설문문항관리 등록,수정,삭제
     */
	@Override
	public String saveQstnItem(SurveyVO surveyVO) throws Exception {
		
		if (surveyVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = surveyVO.getMode();
		
		if (CommConst.UPDATE.equals(mode)) {
			// 설문문항관리 수정
			ret = updtQstnItem(surveyVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 설문문항관리 등록
			ret = regiQstnItem(surveyVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 설문문항관리 삭제
			ret = deltQstnItem(surveyVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}