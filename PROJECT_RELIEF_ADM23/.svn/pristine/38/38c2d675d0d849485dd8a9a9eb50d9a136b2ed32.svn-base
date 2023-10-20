package business.com.file.service;

import java.util.ArrayList;
import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 서류양식관리 모델 클래스
 *
 * @class   : PapeMngVO
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
public class PapeMngVO extends BaseModel {

    // 서류코드
    private String papeCd;
    // 서류업무구분코드
    private String papeDtySeCd;
    // 신청구분코드
    private String aplySeCd;
    // 필수여부
    private String esntlYn;
    // 사용여부
    private String useYn;
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
    private String srchPapeDtySeCd;
    private String srchUpPapeCd;
    private String srchAplySeCd;

    // 세션사용자번호
    private String gsUserNo;

    private String aplySeNm;

    /* ========== 서류코드정보 ========== */
    // 서류명
    private String papeNm;
    // 상위서류코드
    private String upPapeCd;
    // 상위서류명
    private String upPapeNm;
    // 코드순서
    private String cdOrdr;
    // 파일명
    private String fileNm;
    // 저장명
    private String strgNm;
    // 파일경로
    private String filePath;
    // 다운로드수
    private String downCnt;
    // 제한수
    private String limtCnt;
    // 다운로드 대상여부
    private String downTrgtYn;

    // 2022.01.19 문서번호
    private String dcmtNo;

    // 대상파일목록
    private List<AplyFileVO> files;

    private List<String> reliefPapeCdList;
    private List<String> lwstPapeCdList;

    // 2022.01.25 CSLEE 추가 - 급여종류 다중 조건
    private List<String> upPapeCdList;

    public void addFiles(AplyFileVO aplyFileVO) {
    	if (files == null)
    		files = new ArrayList<AplyFileVO> ();
    	files.add(aplyFileVO);
    }

}
