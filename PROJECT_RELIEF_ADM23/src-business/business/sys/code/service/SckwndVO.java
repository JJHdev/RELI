package business.sys.code.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 상병코드관리 모델 클래스
 *
 * @class   : SckwndVO
 * @author  : LSH
 * @since   : 2021.09.23
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
public class SckwndVO extends BaseModel {

    // 상병코드
    private String sckwndCd;
    // 상위상병코드
    private String upSckwndCd;
    // 상위상병코드 (수정시 PK조건)
    private String orgUpSckwndCd;
    // 상위상병명
    private String upSckwndNm;
    // 상병명
    private String sckwndNm;
    // 질환분류코드
    private String dissClCd;
    private String dissClNm;
    // 인정질환구분코드
    private String rcognDissSeCd;
    private String rcognDissSeNm;
    // 기타내용
    private String etcCn;
    // 코드순서
    private String cdOrdr;
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
    private String srchUseYn;
    
    // 세션사용자번호
    private String gsUserNo;

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
}
