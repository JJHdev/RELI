package business.com.file.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 서류코드관리 모델 클래스
 *
 * @class   : PapeCodeVO
 * @author  : LSH
 * @since   : 2021.10.07
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
public class PapeCodeVO extends BaseModel {

    // 서류코드
    private String papeCd;
    // 서류명
    private String papeNm;
    // 상위서류코드
    private String upPapeCd;
    // 상위서류코드 (수정시 PK조건)
    private String orgUpPapeCd;
    // 상위서류명
    private String upPapeNm;
    // 코드순서
    private String cdOrdr;
    // 사용여부
    private String useYn;
    // 저장명
    private String strgNm;
    // 파일명
    private String fileNm;
    // 파일경로
    private String filePath;
    // 다운로드수
    private String downCnt;
    // 제한수
    private String limtCnt;
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
    private String srchUseYn;

    // 세션사용자번호
    private String gsUserNo;

    /**
     * 2021.12.07 CSLEE
     * 첨부파일 확장명 저장
     */
    private String fileExt;
    private String downFileNm;

    /**
     * 2021.11.05 LSH
     * EasyUI Treegrid에서 Dynamic Loading시 KEY값
     */
    private String id;

    /**
     * 2021.11.05 LSH
     * EasyUI Treegrid에서 노드상태를 표시하기 위한 값
     */
    private String state;

    /**
     * 2021.12.10 CSLEE
     * 컬럼 추가 : 다운로드대상여부
     */
    private String downTrgtYn;
}
