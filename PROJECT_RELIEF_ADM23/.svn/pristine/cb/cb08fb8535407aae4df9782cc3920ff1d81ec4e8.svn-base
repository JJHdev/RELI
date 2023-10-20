package business.com.support.service;

import java.util.List;

import business.com.file.service.AplyFileVO;
import business.com.file.service.PapeMngVO;
import common.base.BaseModel;
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
public class LwstVO extends BaseModel {

	// 검색조건
	private String srchText;

	// 검색조건
	private String result;

	// 세션사용자번호
	private String gsUserNo;

	// 세션사용자이름
	private String gsUserNm;

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

	private String sprtSeNm;

	// 신청자번호
	private String aplcntNo;

	// 신청자명
	private String aplcntNm;
	private String aplcntNmCnt;


	private String aplcntNmR;

	private String aplcntMbtel;

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

	private String respdntNmR;

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

	private String aplySeNm;

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

	// 진행상태명
	private String prgreStusNm;

	// 등록자번호
	private String rgtrNo;

	// 등록일자
	private String regYmd;

	// 수정자번호
	private String mdfrNo;

	//신청인 전체주소
	private String aplcntAddrLwst;

	//피신청인 전체주소
	private String respdntAddrLwst;

	private String searchincdntNo;

	// 수정일자
	private String mdfcnYmd;

	//신청일자
    private String srchAplyStdt;
    private String srchAplyEndt;

    //신청 지역
    private String srchDmgeArea;

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
    private String srchRtrcnStdt;
    private String srchRtrcnEndt;

    //재판 부서명
    private String jdgmtDeptNm;

    //일련번호
    private int sn;

    // 사건 기일 일자
    private String dudtYmd;

    // 사건 기일 시각
    private String dudtTm;

    //사건 기일 구분 코드
    private String dudtSeCd;
    private String dudtSeNm;

    //사건 기일 장소
    private String dudtPlce;

    //사건 결과 내용
    private String rsltCn;
    private String rsltNm;

    //소송지원코드
    private String lwstSprtCd;

    // 소송지원내용
    private String lwstSprtCn;

    //소송지원금액
    private String lwstSprtAmt;

    //신청현황

    private List<String> prgreStusList;

    //신청구분 리스트
    private List<String> aplySeCdList;

    //지원구분 리스트
    private List<String> sprtSeCdList;

    //저장용 향후기일 목록데이터
    private List<LwstVO> dtlsList;

    private List<LwstVO> lwstList;

    //소송별 신청 목록
    private List<LwstVO> lwstAplyList;

    //소송 지원정보 목록
    private List<LwstVO> lwstSprtList;

    /* ========== 신청파일 ========== */
    // 저장대상 신청파일목록
    private List<AplyFileVO> saveFiles;
    // 삭제대상 신청파일목록
    private List<AplyFileVO> removeFiles;
    // 신청양식 서류목록
    private List<PapeMngVO> papeList;
    // 신청양식 업무구분
    private String papeDtySeCd;
    private String aplySeCdFile;
    private String aplyOder;

    // 2021.12.14 전자서명파일
    private String signCn;

    // 2021.12.17 CSLEE
    private String lwstSprtAmtTot;
    private String lwstSprtAmtDetail;
    private String aplcntNmDesc;
    private String rsltCnNm;
    
    // 2023.01.27 LSH 서류코드 (관리자 제출서류 추가등록시 필요)
    private String papeCd;
    // 2023.01.27 LSH 상위서류코드 (관리자 제출서류 추가등록시 필요)
    private String upPapeCd;
    // 2023.01.27 LSH 이력구분코드 (관리자 제출서류 추가등록시 필요)
    private String hstSeCd;
    // 2023.01.27 LSH 수정사유 (관리자 제출서류 추가등록시 필요)
    private String hstCn;

	public void rebuildProperties() {

 		// 세션사용자번호 정의
 		if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
 			setGsUserNo(getUserInfo().getUserNo());

 		}
 		if (CommUtils.isNotEmpty(getUserInfo().getUserNm())) {
 			setGsUserNm(getUserInfo().getUserNm());
 		}
 	}
}
