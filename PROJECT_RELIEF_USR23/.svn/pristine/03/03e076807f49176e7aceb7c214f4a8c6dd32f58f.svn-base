package business.com.biz.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 사업관리 모델 클래스
 *
 * @class   : BizMngVO
 * @author  : 김주호
 * @since   : 2021.10.02
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
public class BizMngVO extends BaseModel {
    
    // TB_BIZ_MNG 
	private String bizAreaCd;    // 사업지역코드
	private String bizArea;      // 사업지역
	private String bizDtlArea;   // 사업상세지역
	private String bizCn;        // 사업내용
	private char useYN;          // 사용여부
	private String rgtrNo;       // 등록자번호
	private String regYmd;       // 등록일자
	private String mdfrNo;       // 수정자번호
	private String mdfcnYmd;     // 수정일자
	
	
	// TB_BIZ_ODER
	private String bizOder;      // 사업 차수
	private String bizOderNm;    // 사업차수명
	private String bizBgngYmd;   // 사업시작일자
	private String bizEndYmd;    // 사업종료일자
	private String polusrcCn;    // 오염원내용
	private String hrmflns;      // 유해인자노출내용
	private String healthDmgeCn; // 건강피해내용
	private String affcScopeCn;  // 영향범위내용
	private String expsrWhlCn;   // 노출기간내용
	private String resiWhlCn;    // 거주기간
	private String bizUseYn;     // 사업사용여부
	
	private int oderCount;    // 사업차수 갯수 확인
	
	//TB_PRPT_EMXN
	private int prptCount;       // 예비조사 계획 확인
	
	
	// 검색조건
    private String srchText;
    private String srchBizArea;  // 지역
    private String srchBizOder;  // 차수
    
    // 세션사용자번호
    private String gsUserNo;
    
}
