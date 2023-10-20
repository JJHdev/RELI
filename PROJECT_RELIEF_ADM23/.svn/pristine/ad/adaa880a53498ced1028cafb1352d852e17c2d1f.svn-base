package business.com.exmn.service;

import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 요양생활수당관리 모델 클래스
 *
 * @class   : RcperLvlhVO
 * @author  : LSH
 * @since   : 2021.11.30
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
public class RcperLvlhVO extends BaseModel {

    // 사업지역코드
    private String bizAreaCd;
    // 사업차수
    private String bizOder;
    // 조사차수
    private String exmnOder;
    // 신청번호
    private String aplyNo;
    // [2022.12.28 추가] 식별ID
    private String idntfcId;

    
    /* ========== 요양생활수당 관리정보 ========== */
    
    // 요양생활수당금액
    private String rcperLvlhAmt;
    // 지급연도
    private String giveYr;

    
    /* ========== 요양생활수당 지급정보 ========== */
    // 지급월
    private String giveMm;
    // 지급일자
    private String giveYmd;
    // 지급여부
    private String giveYn;

    
    /* ========== 이력정보 ========== */
    // 이력저장용
    private String logTy;

    
    /* ========== 본조사정보 ========== */
    // 지급시작년월
    private String giveBgngYm;
    // 지급종료년월
    private String giveEndYm;
    
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
    
    // 저장용 시작년도
    private int stYear;
    // 저장용 종료년도
    private int enYear;

    // [2022.12.27 추가] 지급금액
    private String giveAmt;
    // [2022.12.27 추가] 지급구분코드
    private String giveSeCd;
    // [2022.12.27 추가] 지급구분명칭
    private String giveSeNm;

    // [2022.12.27 추가] 질환종류코드 (이전값)
    private String dissKndCdOrg;
    // [2022.12.27 추가] 질환종류코드 (요양생활수당평가등급)
    private String dissKndCd;
    // [2022.12.27 추가] 질환종류명칭 (요양생활수당평가등급)
    private String dissKndNm;
    // [2022.12.27 추가] 중증도점수 (요양생활수당평가등급)
    private String svrtyScre;

    // [2022.12.27 추가] 점수문자열 ex) 31.25|12.5|56.25|62.5|37.5
    private String svrtyScreStr;
    // [2022.12.27 추가] 최종평가점수
    private String lastDmgeScre;
    // [2022.12.27 추가] 최종피해등급
    private String lastDmgeGrdCd;
    // [2022.12.28 추가] 피해등급기준년도
    private String dmgeGrdYr;
    // [2022.12.28 추가] 소급기간 시작월
    private String rtroactBgngYm;
    // [2022.12.28 추가] 소급기간 종료월
    private String rtroactEndYm;
    // [2022.12.28 추가] 소급금액
    private String rtroactAmt;
    // [2022.12.28 추가] 일시금금액
    private String lumpSumAmt;
    // [2022.12.28 추가] 지급등록 가능여부
    private String giveUseYn;
    // [2022.12.28 추가] 일시지급 가능여부
    private String lumpSumYn;
    // [2023.01.02 추가] 지급년월
    private String giveYm;
    // [2023.01.05 추가] 피해등급 필수등록여부
    private String lastDmgeGrdYn;
    
    // [2022.12.27 추가] 저장용 피해등급 산정 목록
    private List<RcperLvlhVO> screList;
    
    // [2022.12.27 추가] 저장용 피해등급 삭제 목록
    private List<RcperLvlhVO> deltList;
}
