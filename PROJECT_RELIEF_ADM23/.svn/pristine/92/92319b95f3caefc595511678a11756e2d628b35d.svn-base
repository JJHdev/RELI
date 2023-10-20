package business.com.cmm.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 인증관련 공통 모델 클래스
 *
 * @class   : LoginVO
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *  2021.11.08  LSH        인증관련 항목추가
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class LoginVO extends BaseModel {
	
	// 사용자비밀번호
	private String pswd;
	// 사용자번호
	private String userNo;
	// 사용자ID
	private String userId;
	// 이름
	private String userNm;
	// 식별 아이디
	private String identId;
	// 식별 아이디 인증용 이름
	private String identNm;
	// 주민등록번호
	private String userRrno;
	// 주민등록번호 앞자리
	private String userRrno1;
	// 주민등록번호 뒷자리
	private String userRrno2;
	// 생년월일(8자리)
	private String brdt;
	// 성별
	private String sxdst;
	// 휴대전화번호
	private String mbtelNo;
	// SMS 인증번호
	private String smsNo;
	// 식별 아이디 인증용 인증방법
	private String certType;
	// 식별 아이디 인증용 인증여부
	private String cnfrmYn;
	// 식별 아이디 인증용 SMS인증여부
	private String smsYn;
	
	// 2021.12.13 LSH 관리자번호
	private String adminNo;
}