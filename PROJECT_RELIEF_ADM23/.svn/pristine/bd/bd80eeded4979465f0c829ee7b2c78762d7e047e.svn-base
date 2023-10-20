package business.com.survey.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommBizUtils;
import business.com.CommConst;
import business.com.survey.service.SurveyRspnsService;
import business.com.survey.service.SurveyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.util.CommUtils;

/**
 * [서비스클래스] - 설문응답상세 Service 구현 클래스
 * 
 * @class   : SurveyRspnsServiceImpl
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("SurveyRspnsService")
@SuppressWarnings({"all"})
public class SurveyRspnsServiceImpl extends BaseService implements SurveyRspnsService {

    @Resource(name = "SurveyRspnsDAO")
    private SurveyRspnsDAO surveyRspnsDAO;
	
    /**
     * 설문응답관리 페이징목록조회
     */
    @Override
    public PaginatedArrayList listRspnsMng(SurveyVO surveyVO, int currPage, int pageSize) throws Exception {
    	return surveyRspnsDAO.listRspnsMng(surveyVO, currPage, pageSize);
    }

    /**
     * 설문응답관리 목록조회
     */
    @Override
    public List listRspnsMng(SurveyVO surveyVO) throws Exception {
    	return surveyRspnsDAO.listRspnsMng(surveyVO);
    }

    /**
     * 설문응답관리 상세조회
     */
	@Override
	public SurveyVO viewRspnsMng(SurveyVO surveyVO) throws Exception {
		return surveyRspnsDAO.viewRspnsMng(surveyVO);
	}

    /**
     * 설문응답관리 상세조회
     * - 신청번호기준 조회
     * - 설문응답목록 포함
     */
	@Override
	public SurveyVO viewRspnsMngByAplyNo(String aplyNo) throws Exception {
		// 신청번호기준 상세조회
		SurveyVO ret = surveyRspnsDAO.viewRspnsMngByAplyNo(aplyNo);
		if (ret == null)
			return null;
        // 설문응답목록 조회
        List<SurveyVO> items = listRspnsItem(ret.getRspnsMngNo());
        if (items != null) {
    		// 2단계 계층구조생성
    		ret.setItems(CommBizUtils.buildTreeSurvey(items));
        }
		return ret;
	}

    /**
     * 설문응답관리 등록
     */
    private int regiRspnsMng(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.regiRspnsMng(surveyVO);
    }

    /**
     * 설문응답관리 수정 (신청번호 업데이트)
     */
    private int updtRspnsMng(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.updtRspnsMng(surveyVO);
    }

    /**
     * 설문응답관리 서명파일수정
     */
    private int updtRspnsMngSign(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.updtRspnsMngSign(surveyVO);
    }

    /**
     * 설문응답관리 삭제
     */
    private int deltRspnsMng(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.deltRspnsMng(surveyVO);
    }

    /**
     * 설문응답상세 목록조회
     */
    @Override
    public List listRspnsItem(String rspnsMngNo) throws Exception {
    	return surveyRspnsDAO.listRspnsItem(rspnsMngNo);
    }

    /**
     * 설문응답상세 등록
     */
    private int regiRspnsItem(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.regiRspnsItem(surveyVO);
    }

    /**
     * 2022.01.21 LSH
     * 설문응답상세 수정
     * 
     * @param surveyVO.rspnsMngNo 답변관리번호
     * @param surveyVO.qesitmNo   문항번호
     * @param surveyVO.itemNo     항목번호
     */
    private int updtRspnsItem(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.updtRspnsItem(surveyVO);
    }

    /**
     * 설문응답상세 삭제
     */
    private int deltRspnsItem(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.deltRspnsItem(surveyVO);
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
    private boolean existRspnsItem(SurveyVO surveyVO) throws Exception {
        return surveyRspnsDAO.existRspnsItem(surveyVO);
	}

    /**
     * 설문응답 저장처리
     * 2022.01.21 LSH 수정처리 추가함
     */
	@Override
	public String saveSurvey(SurveyVO surveyVO) throws Exception {
		
		if (surveyVO == null)
			throw processException("error.comm.notTarget");
		
		List<SurveyVO> items = surveyVO.getItems();
		
		if (items == null || items.size() == 0)
			throw processException("error.comm.notTarget");
		
		String mode = surveyVO.getMode();
		
		int ret = 0;
		
		// 수정인 경우 (2022.01.21 추가)
		if (CommConst.UPDATE.equals(mode)) {
			
			// 설문응답관리 상세조회 
			SurveyVO obj = viewRspnsMng(surveyVO);
			
			if (obj == null)
				throw processException("error.comm.notTarget");
			
			for (SurveyVO item : items) {
				// 설문응답관리번호 맵핑
				item.setRspnsMngNo(surveyVO.getRspnsMngNo());
				// 세션사용자번호 맵핑
				item.setGsUserNo(surveyVO.getGsUserNo());
				
				// 답변항목이 있는 경우 수정
				if (existRspnsItem(item))
					ret += updtRspnsItem(item);
				// 답변항목이 없는 경우 등록
				else
					ret += regiRspnsItem(item);
			}
			
			// 서명파일 경로정보
			FileDirectory d = FileDirectory.SIGNATURE;
			// 이전서명파일
			String orgSignCn = obj.getSignCn();
			// 신규서명파일
			String newSignCn = surveyVO.getSignCn();

			// 서명파일이 다른 경우 
			if (!CommUtils.isEmpty(newSignCn) &&
				!CommUtils.isEqual(orgSignCn, newSignCn)) {
				
				// 서명파일 수정
				if (updtRspnsMngSign(surveyVO) > 0) {
					// 이전서명파일이 있는 경우
					if (CommUtils.isNotEmpty(orgSignCn)) {
						// 실제 경로에 저장된 파일을 
						// 삭제된 파일 저장경로로 이동처리한다.
						d.moveToRemoved(d.getPath(), orgSignCn);

						// 이전서명파일 물리적 삭제할 경우 (현재사용안함)
						//FileUtils.deleteFile(d.getRealName(orgSignCn));
					}
				}
			}
			return surveyVO.getRspnsMngNo();
		}
		// 등록인 경우
		else {
			// 설문응답관리 등록
			if (regiRspnsMng(surveyVO) > 0) {
				for (SurveyVO item : items) {
					// 설문응답관리번호 맵핑
					item.setRspnsMngNo(surveyVO.getRspnsMngNo());
					// 세션사용자번호 맵핑
					item.setGsUserNo(surveyVO.getGsUserNo());
					// 설문응답항목 등록
					ret += regiRspnsItem(item);
				}
				return surveyVO.getRspnsMngNo();
			}
		}
		return null;
	}
	
    /**
     * 설문응답 신청번호 업데이트 (구제급여신청에서 호출함)
     */
	@Override
	public int updtSurvey(SurveyVO surveyVO) throws Exception {
		
		if (surveyVO == null)
			throw processException("error.comm.notTarget");
		
		return updtRspnsMng(surveyVO);
	}

    /**
     * 2022.01.20 LSH
     * 설문응답 수정용도 상세조회 (설문응답상세 포함)
     */
	@Override
	public Map viewRspnsMap(SurveyVO surveyVO) throws Exception {

        SurveyVO result = viewRspnsMng(surveyVO);

        if (result == null)
        	return null;
        
        Map data = new HashMap();
        data.put("rspnsMngNo", result.getRspnsMngNo());
        data.put("qstnnMngNo", result.getQstnnMngNo());
        data.put("aplyNo"    , result.getAplyNo());
        data.put("signCn"    , result.getSignCn());
        data.put("regDate"   , CommUtils.toKorDate(result.getRegDate()));
        data.put("rgtrNm"    , result.getRgtrNm());
        data.put("rgtrNo"    , result.getRgtrNo());
        
        // 설문응답목록 조회
        List<SurveyVO> items = listRspnsItem(surveyVO.getRspnsMngNo());
        if (items == null)
        	return data;
        
    	for (SurveyVO item : items) {
    		String key  = item.getQesitmNo() + "_" + item.getItemNo();
    		String type = item.getQestnTy(); 
    		
    		// 가족관계목록인 경우
    		if ("A082".equals(type) || "A081".equals(type)) {
    			
    			// 질환명 항목정의
    			data.put(key+"_ansCn1", item.getAnsCn1());
    			// 해당없음 항목정의
    			data.put(key+"_ansCn6", item.getAnsCn6());
    			
    			List<Map> relations = item.getRelations();
    			
    			for (Map rel : relations) {
    				String code = (String)rel.get("ansCd");
    				data.put(key+"_ansRel"+code, rel.get("ansRel"));
    				data.put(key+"_ansAge"+code, rel.get("ansAge"));
    				data.put(key+"_ansNon"+code, rel.get("ansNon"));
    			}
    		}
    		// 그외의 경우
    		else {
	    		List<String> answers = item.getAnswers();
	    		int i = 1;
	    		for (String ansCn : answers) {
	    			data.put(key+"_ansCn"+i, ansCn);
	    			i++;
	    		}
	    		// 배상금 수령정보인 경우 날짜분리
	    		if ("A090".equals(type)) {
	    			String dt = item.getAnsCn2(); // 20220101
	    			if (CommUtils.isNotEmpty(dt)) {
		    			data.put(key+"_ansCn21", dt.substring(0,4)); // 년도
		    			data.put(key+"_ansCn22", dt.substring(4,6)); // 월
		    			data.put(key+"_ansCn23", dt.substring(6,8)); // 일
	    			}
	    		}
    		}
    	}
		return data;
	}
}