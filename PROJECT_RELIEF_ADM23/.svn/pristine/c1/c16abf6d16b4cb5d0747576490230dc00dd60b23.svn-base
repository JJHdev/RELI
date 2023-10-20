package business.com.relief.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 구상금납부내역 모델 클래스
 *
 * @class   : ReamtPayVO
 * @author  : LSH
 * @since   : 2021.12.16
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
public class ReamtPayVO extends BaseModel {

    /* ========== 구상금관리정보 ========== */
    // 구상금관리번호
    private String reamtMngNo;
    // 사업지역코드
    private String bizAreaCd;
    private String bizAreaNm;
    // 사업차수
    private String bizOder;
    // 대상업체명
    private String trgtEntNm;
    // 구제급여총금액
    private String refbnfTotAmt;
    // 환수금액
    private String rdmAmt;
    // 환수일자
    private String rdmYmd;

    /* ========== 구상금납부정보 ========== */
    // 일련번호
    private String sn;
    // 납부고지금액
    private String payInfrmAmt;
    // 납부고지일자
    private String payInfrmYmd;
    // 납부최고일자
    private String pnopYmd;
    
    /* ========== 구제급여정보 ========== */
    // 의료비
    private String mcpAmt;
    // 장례비
    private String fnrlAmt;
    // 유족보상금
    private String brvfmAmt;
    // 재산피해보상금
    private String prprtyAmt;
    // 요양생활수당
    private String rcperAmt;
    // 차액 (구제급여총액 - 구상금납부고지액)
    private String refbnfDiffAmt;
    
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
    
    /* ========== 검색조건 ========== */
    private String srchBizArea;
    private String srchGiveYear;
}
