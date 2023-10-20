package business.com.exmn.service;

import java.util.List;

import business.com.file.service.ExmnFileVO;
import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 본조사 모델 클래스
 *
 * @class   : MnsvyVO
 * @author  : LSH
 * @since   : 2021.11.29
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
public class MnsvyVO extends BaseModel {

    // 사업지역코드
    private String bizAreaCd;
    private String bizAreaNm;
    // 사업차수
    private String bizOder;
    private String bizOderNm;
    // 조사차수
    private String exmnOder;
    // 신청번호
    private String aplyNo;
    // 소급금액
    private String rtroactAmt;
    // 소급시작연월
    private String rtroactBgngYm;
    // 소급종료연월
    private String rtroactEndYm;
    // 지급시작연월
    private String giveBgngYm;
    // 지급종료연월
    private String giveEndYm;
    // 중증도피해등급코드
    private String svrtyDmgeGrdCd;
    // 최종피해등급코드
    private String lastDmgeGrdCd;
    // 장례비금액
    private String fnrlCstAmt;
    // 장례비내용
    private String fnrlCstCn;
    // 유족보상금액
    private String brvfmRwrdAmt;
    // 유족보상금내용
    private String brvfmRwmnyCn;
    // 재산피해보상금액
    private String prprtyDmgeRwrdAmt;
    // 재산피해보상내용
    private String prprtyDmgeRwrdCn;
    // 기존인정금액
    private String lgcyRcognAmt;
    // 중복금액
    private String dpcnAmt;
    // 회수필요금액
    private String rtrvlNeedAmt;
    // 환수금액
    private String rdmAmt;
    // 인정금액
    private String rcognAmt;
    // 지급금액
    private String giveAmt;
    // 사망일자
    private String dthYmd;
    // 사망연령
    private String dthAge;
    // 피해관련여부
    private String dmgeRelYn;
    // 장의비/유족보상비 지급결과구분
    private String fnrlCstGiveRsltCd;
    private String fnrlCstGiveRsltNm;
    // 사망직접사인내용
    private String dthDirectDthcsCn;
    // 사망원인내용1
    private String dthCauseCn1;
    // 사망원인내용2
    private String dthCauseCn2;
    // 사망원인내용3
    private String dthCauseCn3;
    // 사망원인내용4
    private String dthCauseCn4;
    // 사망원인결과내용
    private String dthCauseRsltCn;
    // 사망기타내용
    private String dthEtcCn;
    // 심의회결과코드
    private String dltncRsltCd;
    private String dltncRsltNm;
    // 심의회결과사유
    private String dltncRsltResn;
    // 지급은행명
    private String giveBankCd;
    private String giveBankNm;
    // 지급예금주명
    private String giveDpstrNm;
    // 지급계좌번호
    private String giveActno;
    // 환수입금일자
    private String rdmDpstYmd;
    // 지급결정일자
    private String giveDcsnYmd;
    // 지급일자
    private String giveYmd;
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
    // 검토시작일자
    private String rvwBgngYmd;
    // 검토종료일자
    private String rvwEndYmd;
    
    // 세션사용자번호
    private String gsUserNo;
    // 세션사용자권한
    private String gsRoleId;
    
    /* ========== 이력정보 ========== */
    // 이력저장용
    private String logTy;

    /* ========== 본조사계획정보 ========== */
    // 대상자수
    private int exmnCnt;
    // 조사내용
    private String exmnCn;
    // 조사시작일자
    private String exmnBgngYmd;
    // 조사종료일자
    private String exmnEndYmd;

    // [검색조건] 조사시작일
    private String srchExmnStdt;
    // [검색조건] 조사종료일
    private String srchExmnEndt;
    // [검색조건] 피해지역코드
    private String srchBizArea;
    // [검색조건] 사업차수
    private String srchBizOder;  
    // [검색조건] 조사차수
    private String srchExmnOder;  
    // [검색조건] 피해자명
    private String srchSufrerNm;  
    // [검색조건] 신청자명
    private String srchAplcntNm;  
	// [검색조건] 신청일자
    private String srchAplyStdt;
    private String srchAplyEndt;
	// [검색조건] 접수일자
    private String srchRcptStdt;
    private String srchRcptEndt;
    
    /* ========== 조사목록(신청정보) ========== */
    
    // [피해자정보] 식별ID
    private String idntfcId;
    // [피해자정보] 피해자번호
    private String sufrerNo;
    // [피해자정보] 피해자명
    private String sufrerNm;
    // [피해자정보] 피해자명 (마스킹처리)
    private String sufrerNmMask;
    // [피해자정보] 피해자휴대전화번호
    private String sufrerMbtelNo;
    // 신청일자
    private String aplyYmd;
    // 접수일자
    private String rcptYmd;
    // 진행상태코드
    private String prgreStusCd;
    private String prgreStusNm;
    // 신청구분코드
    private String aplySeCd;
    private String aplySeNm;
    // 신청종류코드
    private String aplyKndCd;
    private String aplyKndNm;
    // 신청자번호
    private String aplcntNo;
    // 신청자명
    private String aplcntNm;
    // 신청자명 (마스킹처리)
    private String aplcntNmMask;
    // 신청자휴대전화번호
    private String aplcntMbtelNo;
    // 피해자관계코드
    private String sufrerRelCd;
    private String sufrerRelNm;
    
    // 2021.12.09 인정질환정보 (마이페이지용) 
    // 신청기준 인정질환 대표 명칭
    private String rcognSckwndNm;

	// 선택된 목록
    private List<MnsvyVO> exmnList;
    
    // 저장용 첨부서류 목록
    private List<ExmnFileVO> fileList;

    // 2023.01.06 검색용 식별ID
    private String srchIdntfcId;
    
    // [2022.12.27 추가] 최종피해점수
    private String lastDmgeScre;
    // [2022.12.27 추가] 피해등급기준년도
    private String dmgeGrdYr;
    // [2022.12.28 추가] 최종피해등급에 해당하는 요양생활수당 월지급금액
    private String rcperLvlhAmt;
    
    // [2023.01.04 추가] 유족보상금지급일자
    private String brvfmRwmnyGiveYmd;
    // [2023.01.04 추가] 유족보상기준연도
    private String brvfmRwrdCrtrYr;
    // [2023.01.04 추가] 장례기준연도
    private String fnrlCrtrYr;
    // [2023.01.04 추가] 장례비지급일자
    private String fnrlCstGiveYmd;
    
    // [2023.01.04 추가] 의료비 합계
    private String mcpDtlsSum;
    // [2023.01.04 추가] 요양생활수당 합계
    private String rcperLvlhSum;
    
    // [2023.01.05 추가] 장의비 지급등록여부
    private String fnrlCstYn;
    // [2023.01.05 추가] 유족보상비 지급등록여부
    private String brvfmRwrdYn;
    // [2023.01.05 추가] 피해등급 필수등록여부
    private String lastDmgeGrdYn;
}
