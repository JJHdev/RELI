package business.com.biz.service;

import java.util.List;

import business.com.file.service.AplyFileVO;
import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 보완요청 모델 클래스
 *
 * @class   : SplemntVO
 * @author  : LSH
 * @since   : 2021.10.24
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
public class SplemntVO extends BaseModel {

    // 일련번호
    private String sn;
    // 신청번호
    private String aplyNo;
    // 신청차수
    private String aplyOder;
    // 처리상태코드
    private String prcsStusCd;
    private String prcsStusNm;
    // 보완요청구분코드 (업무구분)
    private String splemntDmndSeCd;
    private String splemntDmndSeNm;
    // 보완요청일자
    private String splemntDmndYmd;
    // 보완기한일자
    private String splemntTermYmd;
    // 보완처리일자
    private String splemntPrcsYmd;
    // 보완요청내용
    private String splemntDmndCn;
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
    
	// 검색조건
    private String srchType;
    private String srchText;
    
    // 세션사용자번호
    private String gsUserNo;
    
	// 보완요청시 선택된 서류 목록
    private List<AplyFileVO> fileList;
    
    // [SMS 발송용] 발신번호
    private String trnsterNo;
    // [SMS 발송용] 수신번호
    private String rcvrNo;
    // [SMS 발송용] 수신자명
    private String rcvrNm;
}
