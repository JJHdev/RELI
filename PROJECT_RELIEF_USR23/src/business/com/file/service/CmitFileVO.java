package business.com.file.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 위원회첨부파일 모델 클래스
 *
 * @class   : CmitFileVO
 * @author  : LSH
 * @since   : 2023.10.26
 * @version : 3.0
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
public class CmitFileVO extends BaseModel {

    /* ========== 위원회첨부파일 ========== */
    // PK: sn / FK: cmitMngNo, agndNo
    
    // 위원회관리번호
    private String cmitMngNo;
    // 안건번호
    private String agndNo;


    // 일련번호
    private String sn;
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
    // 다운로드수
    private String downCnt;
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
