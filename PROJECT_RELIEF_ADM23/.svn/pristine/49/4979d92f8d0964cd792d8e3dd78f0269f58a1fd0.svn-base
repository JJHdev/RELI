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
 * [VO클래스] - 예비조사 모델 클래스
 *
 * @class   : PrptExmnVO
 * @author  : LSH
 * @since   : 2021.11.17
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
public class PrptExmnVO extends BaseModel {

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
    // 피해자직업명
    private String sufrerOccpNm;
    // 영향범위거주여부
    private String affcScopeResiYn;
    // 노출거주이력내용
    private String expsrResiHstCn;
    // 거주이력내용
    private String resiHstCn;
    // 거주기간수
    private String resiWhlCnt;
    // 노출기간수
    private String expsrWhlCnt;
    // 영상자료판독내용
    private String vidoDataReoutCn;
    // 인정질환보유여부
    private String rcognDissHoldYn;
    // 생체지표여부
    private String lbdyNdxYn;
    // 심의회결과코드
    private String dltncRsltCd;
    private String dltncRsltNm;
    // 심의회결과사유
    private String dltncRsltResn;
    // 심의회개최일자
    private String dltncOpmtYmd;
    // 조사일자
    private String exmnYmd;
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
    
    /* ========== 예비조사계획 ========== */
    // 영향범위(TB_BIZ_ODER)
    private String affcScopeCn;
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
    
    /* ========== 예비조사목록(신청정보) ========== */
    
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
    
	// 선택된 목록
    private List<PrptExmnVO> exmnList;
    
    // 저장용 거주이력 목록
    private List<ResiHstVO> resiList;
    // 저장용 생체지표 목록
    private List<LbdyNdxVO> lbdyList;
    // 저장용 첨부서류 목록
    private List<ExmnFileVO> fileList;
    
    /* ========== 이력정보 ========== */
    // 이력저장용
    private String logTy;
    
    // 2021.12.14 기인정조사차수
    private String lgcyExmnOder;

    // 2021.12.15 검색구분(PLAN / LIST)
    private String srchType;

    // 2023.01.06 검색용 신청자명
    private String srchAplcntNm;
    // 2023.01.06 검색용 피해자명
    private String srchSufrerNm;
    // 2023.01.06 검색용 식별ID
    private String srchIdntfcId;
}
