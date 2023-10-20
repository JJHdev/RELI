package business.com.file.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 피해조사첨부파일 모델 클래스
 *
 * @class   : ExmnFileVO
 * @author  : LSH
 * @since   : 2021.11.23
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
public class ExmnFileVO extends BaseModel {

    // 일련번호
    private String sn;
    // 업무구분코드
    private String dtySeCd;
    // 업무분류코드
    private String dtyClCd;
    // 사업지역코드
    private String bizAreaCd;
    // 사업차수
    private String bizOder;
    // 조사차수
    private String exmnOder;
    // 신청번호
    private String aplyNo;
    // 파일경로
    private String filePath;
    // 저장파일명
    private String strgNm;
    // 파일명
    private String fileNm;
    // 파일크기
    private long fileSz;
    // 삭제여부
    private String delYn;
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

    private int fileIdx;
    
    // 임시파일여부
    private String tempYn;
}
