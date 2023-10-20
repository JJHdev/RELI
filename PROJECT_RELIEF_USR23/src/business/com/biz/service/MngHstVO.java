package business.com.biz.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 관리이력정보 모델 클래스
 *
 * @class   : MngHstVO
 * @author  : LSH
 * @since   : 2021.10.21
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
public class MngHstVO extends BaseModel {

    // 일련번호
    private String sn;
    // 신청번호
    private String aplyNo;
    // 업무구분코드
    private String dtySeCd;
    private String dtySeNm;
    // 이력구분코드
    private String hstSeCd;
    private String hstSeNm;
    // 이력내용
    private String hstCn;
    // 특이사항내용
    private String partclrMattrCn;
    // 등록자번호
    private String rgtrNo;
    private String rgtrNm;
    // 등록일시
    private String regDttm;
    // 등록일자
    private String regDate;
    // 수정자번호
    private String mdfrNo;
    private String mdfrNm;
    // 수정일시
    private String mdfDttm;
    // 수정일자
    private String mdfDate;
    
	// 검색조건
    private String srchType;
    private String srchText;
    
    // 세션사용자번호
    private String gsUserNo;
}
