package business.com.exmn.service;

import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 피해등급 모델 클래스
 *
 * @class   : DmgeGrdVO
 * @author  : LSH
 * @since   : 2022.12.22
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
public class DmgeGrdVO extends BaseModel {

    // 피해등급년도
    private String dmgeGrdYr;
    // 피해등급코드
    private String dmgeGrdCd;
    // 기준소득금액
    private String crtrIncomeAmt;
    // 중증도시작점수
    private String svrtyBgngScre;
    // 중증도종료점수
    private String svrtyEndScre;
    // 등급비율
    private String grdRate;
    // 등급금액
    private String grdAmt;
    // 년도별 세부등급갯수
    private String dmgeGrdCnt;
    // 피해등급년도 이전값
    private String dmgeGrdYrOrg;
	
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
    
    // 등급별 지급금액 목록 (저장데이터)
    private List<DmgeGrdVO> dmgeGrdList;
}
