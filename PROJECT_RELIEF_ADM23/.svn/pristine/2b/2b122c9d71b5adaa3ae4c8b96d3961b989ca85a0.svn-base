package business.com.exmn.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 의료비내역 모델 클래스
 *
 * @class   : McpDtlsVO
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
public class McpDtlsVO extends BaseModel {

    // 일련번호
    private String sn;
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
    
    private String idntfcId;

    // 요양기관명
    private String rcperInstNm;
    // 요양구분코드
    private String rcperSeCd;
    private String rcperSeNm;
    // 질환분류코드
    private String dissClCd;
    private String dissClNm;
    // 상병코드
    private String sckwndCd;
    // 상병명
    private String sckwndNm;
    // 본인부담금
    private String selfAlotm;
    // 인정상태코드
    private String rcognStusCd;
    private String rcognStusNm;
    // 사유
    private String resn;
    // 요양게시일자
    private String rcperPstgYmd;
    // 요양종료일자
    private String rcperEndYmd;
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
    
    /* ========== 이력정보 ========== */
    // 이력저장용
    private String logTy;
    // 2022.12.09 의료비총액
    private String mcpTotAmt;
    // 2022.12.12 의료비검색총액
    private String mcpSrchAmt;
    
    // 2022.12.12 검색조건 보유질환명
    private String srchSckwndText;
    // 2022.12.12 검색조건 진료일자 시작일
    private String srchRcperStdt;
    // 2022.12.12 검색조건 진료일자 종료일
    private String srchRcperEndt;
}
