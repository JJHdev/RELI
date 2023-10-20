package business.com.support.service;

import common.base.BaseModel;
import java.util.List;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 취약계층소송지원 모델 클래스
 *
 * @class : LwstVO
 * @author : 한금주
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 ------- -------- ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DtlVO extends BaseModel {

	// 검색조건
	private String srchText;

	// 세션사용자번호
	private String gsUserNo;

	// 신청번호
	private String aplyNo;

	// 사업지역코드
	private String bizAreaCd;

	// 기타피해지역
	private String etcDmgeArea;

	// 신청구분코드
	private String aplySeCd;

	// 지원구분코드
	private String sprtSeCd;

	// 신청자번호
	private String aplcntNo;

	// 신청자명
	private String aplcntNm;

	// 신청자사업자등록번호
	private String aplcntBrno;

	// 신청자생년월일
	private String aplcntBrdt;

	// 신청자우편번호
	private String aplcntZip;

	// 신청자주소
	private String aplcntAddr;

	// 신청자상세주소
	private String aplcntDaddr;

	// 신청자전화번호
	private String aplcntTelNo;

	// 신청자휴대전화번호
	private String aplcntMbtelNo;

	// 신청자이메일주소
	private String aplcntEmlAddr;
	
	//	신청자 이메일 도메인
	private String aplcntEmlDomain;

	// 피신청인명
	private String respdntNm;

	// 피신청인사업자등록번호
	private String respdntBrno;

	// 피신청인생년월일
	private String respdntBrdt;
	
	// 피신청인우편번호
	private String respdntZip;

	// 피신청인주소
	private String respdntAddr;

	// 피신청인상세주소
	private String respdntDaddr;

	// 피신청인전화번호
	private String respdntTelno;

	// 피신청인휴대전화번호
	private String respdntMbtelNo;

	// 피신청인이메일주소
	private String respdntEmlAddr;

	// 피해 발생 장소
	private String dmgeOcrnPlce;

	// 피해일자
	private String dmgeYmd;

	// 피해내용
	private String dmgeCn;

	// 피해금액
	private int dmgeAmt;

	// 신청취지내용
	private String aplyObjetCn;

	// 신청내용
	private String aplyCn;

	// 신청금액
	private int aplyAmt;

	// 취소내용
	private String rtrcnCn;

	// 취소일자
	private String rtrcnYmd;

	// 신청일자
	private String aplyYmd;

	// 접수일자
	private String rcptYmd;

	// 소송접수일자
	private String lwstRcptYmd;

	// 진행상태코드
	private String prgreStusCd;

	// 등록자번호
	private String rgtrNo;

	// 등록일자
	private String regYmd;

	// 수정자번호
	private String mdfrNo;

	// 수정일자
	private String mdfcnYmd;
	
	//신청일자
    private String srchAplyStdt;
    private String srchAplyEndt;
	
	//신청자명
    private String srchAplcntNm;
    
    /////소송 사건관리
    //사건관리번호
    private int incdntMngNo;
    
    //사건번호
    private String incdntNo;
    
    //사건번호 검색
    private String srchIncdntNo;
    
    //사건명
    private String incdntNm;
    
    //사건명 검색
    private String srchIncdntNm;
    
    //소송금액
    private String lwstPricesAmt;
    
    //인지금액
    private String papstmpAmt;
    
    //소송일자
    private String lwstYmd;
    
    //소송일자 검색
    private String srchLwstStdt;
    private String srchLwstEndt; 
    
    //취소일자 검색
    private String srchMdfcnStdt;
    private String srchLMdfcnEndt; 
    
    //재판 부서명
    private String jdgmtDeptNm;
    
    //일련번호
    private int sN;
    
    // 사건 기일 일자
    private String dudtYmd;
    
    // 사건 기일 시각
    private String dudtTm;
    
    //사건 기일 구분 코드
    private String dudtSeCd;
    
    //사건 기일 장소
    private String dudtPlce;
    
    //사건 결과 내용
    private String rsltCn;
    
    //소송지원코드
    private String lwstSprtCd;
    
    // 소송지원내용
    private String lwstSprtCn;
    
    //소송지원금액
    private String lwstSprtAmt;  
    
    //신청현황
    private List<String> prgreStusList;
    
    //저장용 향후기일 목록데이터
    private List<DtlVO> dtlsList;
    
    private List<DtlVO> lwstList;
    
	public void rebuildProperties() {

 		// 세션사용자번호 정의
 		if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
 			setGsUserNo(getUserInfo().getUserNo());
 			
 		}
 	}

}
