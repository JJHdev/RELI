package business.com.cmit.service;

import java.util.List;

import business.com.CommConst;
import common.base.BaseModel;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 위원회관리 모델 클래스
 *
 * @class   : CmitMngVO
 * @author  : LSH
 * @since   : 2023.10.19
 * @version : 3.0
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
public class CmitMngVO extends BaseModel {

    /* ========== 공통칼럼 ========== */
	// 저장모드
	private String saveMode;
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
    
    private String rgtrYear;
    private String rgtrMonth;
    private String rgtrDay;
    private String rgtrNm;
	
    // 세션사용자번호
    private String gsUserNo;
    // 세션사용자권한
    private String gsRoleId;

    /* ========== 검색조건 ========== */
    // 위원회
    private String srchCmitSeCd;
    // 소속
    private String srchMfcmmOgdpNm;
    // 분야
    private String srchMfcmmRlmCd;
    // 위원명
    private String srchMfcmmNm;
    // 피해지역
    private String srchBizAreaCd;
    // 임기차수
    private String srchTenureOder;
    // 위원회차수
    private String srchCmitOder;
    // 개최일자
    private String srchOpmtStdt;
    private String srchOpmtEndt;
    // 위원등록대상 검색여부
    private String srchMngYn;
    // [2023.10.23 추가] 진행상태 (진행/종료)
    private String srchStusCd;
    
    // 저장목록
    private List<CmitMngVO> saveList;
    // 삭제목록
    private List<CmitMngVO> removeList;
    
    // 위원회 소속 위원수
    private String mfcmmCnt;
    
	// 지역구분(다중선택)
    private List<String> bizAreaList;
	// 위원회구분(다중선택)
    private List<String> cmitSeList;

    // 안건목록
    private List<CmitMngVO> agndList;
    
    /* ========== 위원회정보 ========== */
    // 위원회관리번호
    private String cmitMngNo;
    
    // [2023.10.23 추가] 안건수
    private String agndCnt;
    // [2023.10.23 추가] 위원구분 (일반/위원장)
    private String charmnYn;
    private String charmnNm;

    // [2023.10.25 추가] 의견서 작성여부
    private String opinionYn;
    // [2023.10.25 추가] 의결서 서명여부
    private String decisionYn;
    // [2023.10.25 추가] 수당지 작성여부
    private String pensionYn;

    /* ========== 위원정보 ========== */
    // 위원번호
    private String mfcmmNo;
    // 위원명
    private String mfcmmNm;
    // 위원소속명
    private String mfcmmOgdpNm;
    // 위원분야코드
    private String mfcmmRlmCd;
    private String mfcmmRlmNm;
    // 위원직책명
    private String mfcmmRspofcNm;
    // 위원전화번호
    private String mfcmmTelno;
    // 위원생년월일
    private String mfcmmBrdt;
    // 위원이메일주소
    private String mfcmmEmlAddr;
    // [2023.10.23 추가] 사용자번호
    private String userNo;
    // [2023.10.23 추가] 사용자ID
    private String userId;
    // [2023.10.23 추가] 서명파일
    private String signCn;
    // [2023.10.23 추가] 서명파일여부
    private String signYn;

    /* ========== 위원회 피해조사정보 ========== */
    // 사업지역코드
    private String bizAreaCd;
    private String bizAreaNm;
    // 피해구분코드
    private String dmgeSeCd;
    private String dmgeSeNm;
    // 위원회구분코드
    private String cmitSeCd;
    private String cmitSeNm;
    // 조사구분코드
    private String exmnSeCd;
    private String exmnSeNm;
    // 위원회차수
    private String cmitOder;
    // 위원회명
    private String cmitNm;
    // 개최시작일자
    private String opmtBgngYmd;
    // 개최종료일자
    private String opmtEndYmd;
    // [2023.10.23 추가] 진행상태
    private String prgreStusCd;
    private String prgreStusNm;
    // [2023.10.31 추가] 제출일자
    private String sbmsnYmd;

    /* ========== 위원임기차수정보 ========== */
    // 임기번호
    private String tenureNo;
    // 임기차수
    private String tenureOder;
    // 임기시작일자
    private String tenureBgngYmd;
    // 임기종료일자
    private String tenureEndYmd;

    /* ========== 위원회피해조사안건 ========== */
    // PK: cmitMngNo, agndNo

    // 안건번호
    private String agndNo;
    // 안건구분코드
    private String agndSeCd;
    private String agndSeNm;
    // 안건명
    private String agndNm;

    /* ========== 위원회위원평가결과 ========== */
    // PK: cmitMngNo, agndNo, tenureNo

    // 작성구분코드
    private String wrtSeCd;
    private String wrtSeNm;
    // 심의내용
    private String dlbrCn;
    // 의결사항코드
    private String decsnMattrCd;
    private String decsnMattrNm;
    // 의결사항사유
    private String decsnMattrResn;
    // 의결주문내용
    private String decsnOdrCn;
    // 의결주문사유
    private String decsnOdrResn;
    // 의결주요내용
    private String decsnMainCn;
    // 서명동의여부
    private String signAgreYn;
    // 진행상태코드 (prgreStusCd)
    private String decsnStusCd;
    private String decsnStusNm;
    
    /* ========== 위원회위원수당 ========== */
    // PK: sn
    // FK: cmitMngNo, tenureNo

    // 일련번호
    private String sn;
    // 수당자명
    private String ernrNm;
    // 수당자소속명
    private String ernrOgdpNm;
    // 수당자주민등록번호
    private String ernrRrno;
    // 수당자주소
    private String ernrAddr;
    private String ernrZip;
    // 은행명
    private String bankNm;
    private String bankCd;
    // 예금주명
    private String dpstrNm;
    // 계좌번호
    private String actno;
    // 개인정보수집동의여부
    private String indvInfoClctAgreYn;
    // 원천징수동의여부
    private String wthtxAgreYn;
    // 출장비지급여부
    private String btrpsGiveYn;
    // 소득구분코드
    private String incomeSeCd;
    private String incomeSeNm;
    
    /**
     * 2023.10.31 LSH
     * 위원회 의견서,의결서,수당지 저장시 
     * 데이터를 항목에 맞게 REBUILD 한다.
     */
    public void rebuildProperties() {
    	
    	// 세션사용자번호 정의
    	if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
    		setGsUserNo(getUserInfo().getUserNo());
    		setGsRoleId(getUserInfo().getRoleId());
    	}
		// 저장구분
		String _act = getAct();

		// 진행상태코드 (임시저장 / 신청)
		setDecsnStusCd(CommConst.SUBMIT.equals(_act)
			? CommConst.PRGRE_APPLY // 제출하기
			: CommConst.PRGRE_SAVE  // 임시저장
		);
		
		// 제출시에 제출일자 정의
		if (CommConst.SUBMIT.equals(_act))
			setSbmsnYmd(CommUtils.getCurDateString());
		else
			setSbmsnYmd(CommUtils.toShortDate(getSbmsnYmd(),"-"));
    }
}
