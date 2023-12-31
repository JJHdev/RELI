package business.sys.user.service;

import common.base.BaseModel;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 사용자정보 모델 클래스
 *
 * @class : UserInfoVO
 * @author : 한금주
 * @since : 2021.10.07
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 ------- -------- ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true)
public class UserInfoVO extends BaseModel {

	// 시스템 공통정보
	private String userId; // 사용자아이디
	private String userNm; // 사용자명
	private String pswd; // 비밀번호
	private String roleId; // 권한롤
	private String indvInfoClctAgreYn;
	private String thptyPvsnAgreYn;
    // 세션사용자권한
    private String gsRoleId;
	private String roleNm; // 권한롤명칭
	private String ipAddr; // IP주소
	private String useYn; // 사용여부
	private String enabled;

	/* 사용자정보 */
	private String userNo; // 사용자번호
	private String useIp; // 사용IP
	private String emlAddr; // 이메일
	private String telno; // 전화번호
	private String mbtelNo; // 휴대전화번호
	private String brdt; // 생년월일
	private String sxdst; // 성별
	private String deptCd; // 부서코드
	private String deptNm; // 부서명
	private String zip; // 우편번호
	private String addr; // 주소1
	private String daddr; // 주소2
	private String joinYmd; // 가입일자
	private String pswdLockYmd; // 비밀번호잠금일자
	private String pswdErrCnt; // 비밀번호오류횟수
	private String pswdChgYmd; // 비밀번호변경일자
	private String pswdNextYmd; // 비밀번호다음일자
	private String lstLgnDt; // 마지막로그인일시
	private String useStusCd; // 사용상태
	private String testUseYn; // 테스트사용여부
	private String rgtrNo; // 등록자번호
	private String regDttm; // 등록일시
	private String regDate; // 등록일자
	private String mdfrNo; // 수정자번호
	private String mdfDttm; // 수정일시
	private String mdfDate; // 수정일자
	private String mbtelRcptnAgreYn; // 휴대전화수신동의여부
	
	private String eml;
	private String domain;
	private String bryy;
	private String brmm;
	private String brdd;
	
    private String mfcmmNo; // 위원번호 (2023.10.19 추가)

	// 검색조건
	private String srchText;

	// 세션사용자번호
	private String gsUserNo;

	public void rebuildProperties() {

		// 세션사용자번호 정의
		if (CommUtils.isNotEmpty(getUserInfo().getUserNo())) {
			setGsUserNo(getUserInfo().getUserNo());
		}
	}

}
