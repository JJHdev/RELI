package business.com.relief.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 피해자정보 모델 클래스
 *
 * @class   : IdntfcVO
 * @author  : LSH
 * @since   : 2021.10.05
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
public class IdntfcVO extends BaseModel {

    // 식별ID
    private String idntfcId;
    // 피해자번호 (PK)
    private String sufrerNo;
    // 피해자명
    private String sufrerNm;
    // 피해자명 (마스킹처리)
    private String sufrerNmMask;
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
    // 피해자전체주소
    private String sufrerAddrAll;
    // 피해자전화번호
    private String sufrerTelno;
    // 피해자휴대전화번호
    private String sufrerMbtelNo;
    // 피해자연령
    private String sufrerAge;
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
    // 초본등록 여부
    private String abstrctYn;
    // 피해지역
    private String bizArea;
    // 최초 오염 발생 연도
    private String frstPollutnOcrnYr;
    // 최종 오염 종료 연도
    private String lastPollutnOcrnYr;
    // 피해지역 거주기간
    private String resiWhlCn;

	// 검색조건
    private String srchType;
    private String srchText;
    
    // 세션사용자번호
    private String gsUserNo;
    
    // 이력저장용
    private String hstNo;
    private String logTy;
    
    // 2022.12.15 피해자개요 관련 칼럼추가
    private String aplyNo;
    private String aplySeCd;
    private String aplySeNm;
    private String aplcntNo;
    private String aplcntNm;
    private String sufrerRelCd;
    private String sufrerRelNm;
    private String agentDesc; // 대리신청
    private String dthYn;
    private String bizAreaCd;
    private String bizAreaNm;
    private String dmgeRcognYn; // 예비조사 최종건의 인정여부
}
