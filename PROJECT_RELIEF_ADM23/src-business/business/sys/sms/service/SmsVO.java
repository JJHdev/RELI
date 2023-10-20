package business.sys.sms.service;

import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - SMS이력 모델 클래스
 *
 * @class   : SmsVO
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
public class SmsVO extends BaseModel {

    // 일련번호
    private String sn;
    // SMS구분코드
    private String smsSeCd;
    private String smsSeNm;
    // 전송내용
    private String trsmCn;
    // 장문구분코드
    private String lngtSeCd;
    private String lngtSeNm;
    // 송신자명
    private String trnsterNm;
    // 송신자번호
    private String trnsterNo;
    // 수신자명
    private String rcvrNm;
    // 수신자번호
    private String rcvrNo;
    // 전송상태코드
    private String trsmStusCd;
    private String trsmStusNm;
    // 전송일시
    private String trsmDt;
    // 서버명
    private String srvrNm;
    // 시스템구분코드
    private String sysCd;
    
	// 검색조건
    private String srchType;
    private String srchText;
    
    // 세션사용자번호
    private String gsUserNo;
    
    // 저장처리용 데이터
    private List<SmsVO> smsList;
    
    
    /* ========== 2022.01.19 일괄전송조회 검색조건 ========== */
    // 기타조건 (신청번호,신청인명,피해자명,휴대전화번호,식별ID)
    private String srchTrgtText;
    // 신청일자
    private String srchAplyStdt;
    private String srchAplyEndt;
	// 접수일자
    private String srchRcptStdt;
    private String srchRcptEndt;
	// 신청종류 조건병합
    private String srchAplyKnd;
	// 피해지역
    private String srchBizArea;
	// 사업차수
    private String srchBizOder;
	// 신청구분(다중선택)
    private List<String> aplySeList;
    // 신청종류(다중선택)
    private List<String> aplyKndList;
	// 진행상태(다중선택)
    private List<String> prgreStusList;
     
}
