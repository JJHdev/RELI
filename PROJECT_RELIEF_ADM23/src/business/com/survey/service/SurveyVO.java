package business.com.survey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.com.CommConst;
import common.base.BaseModel;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 설문지 모델 클래스
 *
 * @class   : SurveyVO
 * @author  : LSH
 * @since   : 2021.12.29
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class SurveyVO extends BaseModel {

    /* ========== 설문관리정보 (QstnnMng) ========== */
    // 설문관리번호
    private String qstnnMngNo;
    // 설문내용
    private String qstnnCn;
    // 설문시작일자
    private String qstnnBgngYmd;
    // 설문종료일자
    private String qstnnEndYmd;

    /* ========== 설문문항정보 (QesitmMng) ========== */
    // 설문관리번호 (FK)
    // 문항번호
    private String qesitmNo;
    // 항목번호
    private String itemNo;
    // 문항내용
    private String qesitmCn;
    // 질문유형
    private String qestnTy;
	
    /* ========== 설문응답정보 (RspnsMng) ========== */
    // 설문관리번호 (FK)
    
    // 설문응답번호 (PK)
    private String rspnsMngNo;
    // 신청번호
    private String aplyNo;
    // 서명내용
    private String signCn;
	
    /* ========== 설문답변정보 (RspnsDtls) ========== */

    // 설문응답번호 (FK)
    // 문항번호 (FK)
    // 항목번호 (FK)
    
    // 일련번호 (PK)
    private String sn;

    // 답변내용1
    private String ansCn1;
    // 답변내용2
    private String ansCn2;
    // 답변내용3
    private String ansCn3;
    // 답변내용4
    private String ansCn4;
    // 답변내용5
    private String ansCn5;
    // 답변내용6
    private String ansCn6;
	
    /* ========== 공통항목 ========== */
    // 등록자번호
    private String rgtrNo;
    // 등록일시
    private String regDttm;
    // 등록일자
    private String regDate;
    // 수정자번호
    private String mdfrNo;
    // 수정일시
    private String mdfDttm;
    // 수정일자
    private String mdfDate;
    
    // 세션사용자번호
    private String gsUserNo;
    // 세션사용자권한
    private String gsRoleId;
    
    // 문항목록
    private List<SurveyVO> items;

    // 신청인성명
    private String rgtrNm;
    // 작성년도
    private String rgtrYear;
    // 작성월
    private String rgtrMonth;
    // 작성일
    private String rgtrDay;
    
    /**
     * 가족질병의 가족관계별 응답목록 반환
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getRelations() {
    	
    	String[] arrNames = CommConst.SURVEY_REL_NAMES;
    	String[] arrCodes = CommConst.SURVEY_REL_CODES;
    	String[] arrItems = {ansCn2, ansCn3, ansCn4, ansCn5};
    	
    	List<Map> list = new ArrayList<Map> ();
    	
    	for (int i = 0; i < arrItems.length; i++) {
    		Map item = new HashMap();
    		item.put("ansTit", arrNames[i]);
    		item.put("ansCd" , arrCodes[i]);
    		String[] arr = CommUtils.split(arrItems[i], ",");
    		if (arr != null && arr.length == 3) {
    			item.put("ansRel", arr[0]);
    			item.put("ansAge", arr[1]);
    			item.put("ansNon", arr[2]);
    		}
    		list.add(item);
    	}
    	return list;
    }

	/**
	 * 답변 목록
	 */
	public List<String> getAnswers() {
    	
    	List<String> list = new ArrayList<String> ();
    	list.add(ansCn1);
    	list.add(ansCn2);
    	list.add(ansCn3);
    	list.add(ansCn4);
    	list.add(ansCn5);
    	list.add(ansCn6);
    	
    	return list;
    }
}
