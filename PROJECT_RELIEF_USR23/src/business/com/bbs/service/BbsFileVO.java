package business.com.bbs.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 게시판 첨부파일 모델 클래스
 *
 * @class   : BbsFileVO
 * @author  : LSH
 * @since   : 2021.11.18
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
public class BbsFileVO extends BaseModel {

    // 일련번호
    private String sn;
    // 문서번호
    private String dcmtNo;
    // 파일타입
    private String fileType;
    // 파일전체경로
    private String fullPath;
    // 파일경로
    private String filePath;
    // 저장파일명
    private String saveName;
    // 파일명
    private String fileName;
    // 파일크기
    private long fileSize;
    // 파일순서
    private int fileIdx;
    // 파일확장자
    private String fileExt;
    // 다운로드수
    private String downCnt;
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
}
