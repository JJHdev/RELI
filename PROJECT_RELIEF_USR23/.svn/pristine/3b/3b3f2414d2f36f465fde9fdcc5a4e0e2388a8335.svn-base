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

    
    // 저장용 연도별 목록
    private List<RcperLvlhVO> yearList;
    
    // 저장용 시작년도
    private int stYear;
    // 저장용 종료년도
    private int enYear;
}
