package business.bio.relief.service;

import java.util.List;

import business.com.CommConst;
import business.com.file.service.PapeMngVO;
import common.base.BaseModel;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 살생물제품 구제급여신청 모델 클래스
 *
 * @class   : BioReliefVO
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
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
public class BioReliefVO extends BaseModel {
    
    /* ========== 신청차수정보 ========== */
    // 신청일자
    private String aplyYmd;
    // 접수일자
    private String rcptYmd;
    // 진행상태코드
    private String prgreStusCd;
    private String prgreStusNm;
    // 보완상태코드
    private String spleStusCd;
    private String spleStusNm;

    /* ========== 신청정보 ========== */
    // 신청번호
    private String aplyNo;
    // 신청구분코드
    private String aplySeCd;
    private String aplySeNm;
    // 신청종류코드
    private String aplyKndCd;
    private String aplyKndNm;
    // 신청사유
    private String aplyResn;
    // 신청자번호
    private String aplcntNo;
    // 신청자명
    private String aplcntNm;
    // 신청자명 (마스킹처리)
    private String aplcntNmMask;
    // 신청자생년월일
    private String aplcntBrdt;
    // 신청자주민등록번호
    private String aplcntRrno;
    // 신청자전화번호
    private String aplcntTelno;
    // 신청자휴대전화번호
    private String aplcntMbtelNo;
    // 신청자이메일주소
    private String aplcntEmlAddr;
    // 신청자우편번호
    private String aplcntZip;
    // 신청자주소
    private String aplcntAddr;
    // 신청자상세주소
    private String aplcntDaddr;
    // 신청자전체주소
    private String aplcntAddrAll;
    // 피해자관계코드
    private String sufrerRelCd;
    private String sufrerRelNm;
    // 지급은행명
    private String bankCd;
    private String bankNm;
    // 예금주명
    private String dpstrNm;
    // 계좌번호
    private String actno;
    // 사망여부
    private String dthYn;
    // 사망일자
    private String dthYmd;
    // 피해인정여부
    private String dmgeRcognYn;
    // 선순위유족명
    private String priordBrvfmNm;
    // 선순위유족관계코드
    private String priordBrvfmRelCd;
    private String priordBrvfmRelNm;
    // 양도인명
    private String asgnrNm;
    // 양도인관계코드
    private String asgnrRelCd;
    private String asgnrRelNm;
    // 초본우편번호
    private String abstrctZip;
    // 초본주소
    private String abstrctAddr;
    // 초본상세주소
    private String abstrctDaddr;
    // 최초신청여부
    private String frstAplyYn;
    // 최초신청번호
    private String frstAplyNo;
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
    
    /* ========== 신청파일 ========== */
    // 저장대상 신청파일목록
    private List<BioAplyFileVO> saveFiles;
    // 삭제대상 신청파일목록
    private List<BioAplyFileVO> removeFiles;
    // 신청양식 서류목록
    private List<PapeMngVO> papeList;
    // 신청양식 업무구분
    private String papeDtySeCd;
    
    // 업무구분
    private String dtySeCd;
    
    /* ========== 피해자정보 ========== */
    // 피해자번호
    private String sufrerNo;
    // 피해자명
    private String sufrerNm;
    // 피해자명 (마스킹처리)
    private String sufrerNmMask;
    // 식별ID
    private String idntfcId;
    // 피해자생년월일
    private String sufrerBrdt;
    // 피해자주민등록번호
    private String sufrerRrno;
    // 피해자성별
    private String sufrerSxdst;
    private String sufrerSxdstNm;
    // 피해자우편번호
    private String sufrerZip;
    // 피해자주소
    private String sufrerAddr;
    // 피해자상세주소
    private String sufrerDaddr;
    // 피해자이메일주소
    private String sufrerEmlAddr;
    // 피해자전화번호
    private String sufrerTelno;
    // 피해자휴대전화번호
    private String sufrerMbtelNo;
    // 피해자연령
    private String sufrerAge;
    // 피해자전체주소
    private String sufrerAddrAll;

    // 추가신청여부
    private String addYn;
    // 전자서명파일
    private String signCn;
    // 진행상태 문자열
    private String prgreStusStr;
    // 관리자여부
    private String admYn;
    // 관리자등록여부
    private String mngrRegYn;
    // 등록관리자명
    private String mngrNm;
    // 서류코드 (관리자 제출서류 추가등록시 필요)
    private String papeCd;
    // 상위서류코드 (관리자 제출서류 추가등록시 필요)
    private String upPapeCd;
    
    /* ========== 이력정보 ========== */
    // 이력저장용
    private String hstNo;
    private String logTy;
    // 이력구분코드
    private String hstSeCd;
    // 이력수정사유
    private String hstCn;
    
    /* ========== 검색조건 ========== */
	// 신청일자
    private String srchAplyStdt;
    private String srchAplyEndt;
	// 접수일자
    private String srchRcptStdt;
    private String srchRcptEndt;
	// 신청종류 조건병합
    private String srchAplyKnd;
	// 식별ID
    private String srchIdntfcId;
	// 피해자명
    private String srchSufrerNm;
	// 신청자명
    private String srchAplcntNm;
	// 추가신청여부
    private String srchAddYn;
	// 사망여부 (2023.01.05 LSH)
    private String srchDthYn;
	// 살생물제품 제품유형 (2023.01.26 LSH)
    private String srchPrductTy;
	// 살생물제품 제품분류 (2023.01.26 LSH)
    private String srchPrductCd;
	// 살생물제품 제품내용 (2023.01.26 LSH)
    private String srchPrductCn;
    
	// 신청구분(다중선택)
    private List<String> aplySeList;
    // 신청종류(다중선택) - 저장시에도 사용됨
    private List<String> aplyKndList;
	// 진행상태(다중선택)
    private List<String> prgreStusList;
    
    /* ========== 접수정보 ========== */
	// [접수현황] 선택된 목록
    private List<BioReliefVO> receiptList;
    // [접수현황] 신청접수 발신번호
    private String trnsterNo;
    // [접수현황] 신청접수 발신내용
    private String trsmCn;

    /* ========== 살생물제품 관련 항목 ========== */
    // 피해제품코드
    private String dmgePrductCd;
    private String dmgePrductNm;
    private String dmgePrductUpNm; // 제품분류명칭
    private String dmgePrductCdNm; // 제품유형명칭
    // 피해제품내용
    private String dmgePrductCn;
    // 제품제출여부
    private String prductSbmsnYn;
    // 제품제출사유
    private String prductSbmsnResn;
    // 제품사용목적
    private String prductUsePrps;
    // 제품사용구분
    private String prductUseSe;
    private String prductUseNm;
    // 제품사용장소
    private String prductUsePlce;
    // 제품사용방법
    private String prductUseMthd;
    // 제품확인여부
    private String prductIdntyYn;
    // 제품사용시작일자
    private String prductUseBgngYmd;
    // 제품사용종료일자
    private String prductUseEndYmd;
    // 제품사용시작시
    private String prductUseBgngHour;
    // 제품사용종료시
    private String prductUseEndHour;
    // 제품사용횟수내용
    private String prductUseCuntCn;
    // 제품사용량내용
    private String prductUsgqtyCn;
    // 피해질환명
    private String dmgeDissNm;
    // 기타의견내용
    private String etcOpinionCn;

    /**
     * 구제급여신청 저장시 데이터를 항목에 맞게 REBUILD 한다.
     */
    public void rebuildProperties() {
    	
    	// 세션사용자번호 정의
    	if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
    		setGsUserNo(getUserInfo().getUserNo());
    		setGsRoleId(getUserInfo().getRoleId());
    	}
    	
		// 신청구분
		String _aplyCd = getAplySeCd();
		// 저장구분
		String _act = getAct();
    	
        // -------------------- 자동입력값 정리 -----------------------//
		
		// 본인신청인 경우 피해자정보와 신청자정보를 동일하게 적용
		if (CommConst.RELIEF_APLY_SELF.equals(_aplyCd)) {
		    setSufrerNm      (getAplcntNm      ());
		    setSufrerRrno    (getAplcntRrno    ());
		    setSufrerTelno   (getAplcntTelno   ());
		    setSufrerMbtelNo (getAplcntMbtelNo ());
		    setSufrerEmlAddr (getAplcntEmlAddr ());
		    setSufrerZip     (getAplcntZip     ());
		    setSufrerAddr    (getAplcntAddr    ());
		    setSufrerDaddr   (getAplcntDaddr   ());
		}

		// 신청자 주민등록번호
    	String _aplcntRrno = getAplcntRrno();
    	// 피해자 주민등록번호
    	String _sufrerRrno = getSufrerRrno();

    	// 신청자 생년월일
		setAplcntBrdt(CommUtils.getBrdtByRno(_aplcntRrno));
		// 피해자 생년월일
		setSufrerBrdt(CommUtils.getBrdtByRno(_sufrerRrno));
		// 피해자 성별
		setSufrerSxdst(CommUtils.getSxdstByRno(_sufrerRrno));
		// 피해자 연령
		setSufrerAge(CommUtils.getAgeByRno(_sufrerRrno)+"");
		// 최초신청여부
		setFrstAplyYn("Y");
		// 사망여부 (대리(사)인 경우)
		setDthYn(CommConst.RELIEF_APLY_DPTH.equals(_aplyCd) 
			? CommConst.YES 
			: CommConst.NO
		);
		// 진행상태코드 (임시저장 / 신청)
		setPrgreStusCd(CommConst.SUBMIT.equals(_act)
			? CommConst.PRGRE_APPLY // 제출하기
			: CommConst.PRGRE_SAVE  // 임시저장
		);
		
		// 2021.10.13 제출시에 신청일자 정의
		// 2021.11.01 임시저장시 신청일자 정의로 변경
		if (CommUtils.isEmpty(getAplyYmd()))
			setAplyYmd(CommUtils.getCurDateString());
		else
			setAplyYmd(CommUtils.toShortDate(getAplyYmd(),"-"));
		
		// 신청종류 (다중선택항목)를 ","로 묶여진 문자열로 변환
		setAplyKndCd(CommUtils.mergeString(getAplyKndList(), ","));
		
		// 2022.12.05 관리자의 경우 대행등록여부 Y로 정의
    	if (CommConst.isAdminRole(getUserInfo().getRoleId()))
    		setMngrRegYn(CommConst.YES);
    	else
    		setMngrRegYn(CommConst.NO);
    }

    
    /**
     * 구제급여신청 조회시 데이터를 항목에 맞게 REBUILD 한다.
     */
    public void rebuildView() {
    	// 콤마(,)로 구분된 신청종류를 List로 변환
    	setAplyKndList(CommUtils.splitToList(getAplyKndCd(), ","));
    }

    
    /**
     * 구제급여접수 - 피해자정보 수정처리 데이터 REBUILD
     * 구제급여접수 - 신청인정보 수정처리 데이터 REBUILD
     * 구제급여접수 - 피해내용 수정처리 데이터 REBUILD 
     */
    public void rebuildModifyProperties() {
    	
    	// 세션사용자번호 정의
    	if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
    		setGsUserNo(getUserInfo().getUserNo());
    		setGsRoleId(getUserInfo().getRoleId());
    	}
    	
		// 이력구분
		String _hstCd  = getHstSeCd();

		// -------------------- 자동입력값 정리 -----------------------//
		// 피해자정보 수정시
		if      (CommConst.HST_SUFRER.equals(_hstCd)) {
			// 신청종류 (다중선택항목)를 ","로 묶여진 문자열로 변환
			setAplyKndCd(CommUtils.mergeString(getAplyKndList(), ","));
		}
		// 신청인정보 수정시
		else if (CommConst.HST_APLCNT.equals(_hstCd)) {
			// 신청일자 포맷제거
			if (CommUtils.isNotEmpty(getAplyYmd()))
				setAplyYmd(CommUtils.toShortDate(getAplyYmd(),"-"));
			// 접수일자 포맷제거
			if (CommUtils.isNotEmpty(getRcptYmd()))
				setRcptYmd(CommUtils.toShortDate(getRcptYmd(),"-"));
			// 사망일자 포맷제거
			if (CommUtils.isNotEmpty(getDthYmd()))
				setDthYmd(CommUtils.toShortDate(getDthYmd(),"-"));
		}
		// 피해내용 수정시
		else if (CommConst.HST_DAMAGE.equals(_hstCd)) {
		}
    }
}

