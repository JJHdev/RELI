package business.com.cmit.service;

import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 위원회관리 모델 클래스
 *
 * @class   : CmitMngVO
 * @author  : LSH
 * @since   : 2023.10.19
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
public class CmitMngVO extends BaseModel {

    /* ========== 위원회정보 ========== */
    // 위원회관리번호
    private String cmitMngNo;

    /* ========== 위원정보 ========== */
    // 위원번호
    private String mfcmmNo;
    // 위원명
    private String mfcmmNm;
    // 위원소속명
    private String mfcmmOgdpNm;
    // 위원분야코드
    private String mfcmmRlmCd;
    private String mfcmmRlmNm;
    // 위원직책명
    private String mfcmmRspofcNm;
    // 위원전화번호
    private String mfcmmTelno;
    // 위원생년월일
    private String mfcmmBrdt;
    // 위원이메일주소
    private String mfcmmEmlAddr;

    /* ========== 위원회 피해조사정보 ========== */
    // 사업지역코드
    private String bizAreaCd;
    private String bizAreaNm;
    // 피해구분코드
    private String dmgeSeCd;
    private String dmgeSeNm;
    // 위원회구분코드
    private String cmitSeCd;
    private String cmitSeNm;
    // 조사구분코드
    private String exmnSeCd;
    private String exmnSeNm;
    // 위원회차수
    private String cmitOder;
    // 위원회명
    private String cmitNm;
    // 개최시작일자
    private String opmtBgngYmd;
    // 개최종료일자
    private String opmtEndYmd;

    /* ========== 위원임기차수정보 ========== */
    // 임기번호
    private String tenureNo;
    // 임기차수
    private String tenureOder;
    // 임기시작일자
    private String tenureBgngYmd;
    // 임기종료일자
    private String tenureEndYmd;
    
    
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

    /* ========== 검색조건 ========== */
    // 위원회
    private String srchCmitSeCd;
    // 소속
    private String srchMfcmmOgdpNm;
    // 분야
    private String srchMfcmmRlmCd;
    // 위원명
    private String srchMfcmmNm;
    // 피해지역
    private String srchBizAreaCd;
    // 임기차수
    private String srchTenureOder;
    // 위원회차수
    private String srchCmitOder;
    // 개최일자
    private String srchOpmtStdt;
    private String srchOpmtEndt;
    // 위원등록대상 검색여부
    private String srchMngYn;
    
    // 저장목록
    private List<CmitMngVO> saveList;
    // 삭제목록
    private List<CmitMngVO> removeList;
    
    // 위원회 소속 위원수
    private String mfcmmCnt;
}
