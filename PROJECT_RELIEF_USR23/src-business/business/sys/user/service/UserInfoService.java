package business.sys.user.service;

import java.util.List;
import java.util.Map;

import commf.paging.PaginatedArrayList;
import common.user.UserInfo;

/**
 * [인터페이스클래스] - 사용자정보 조회
 *
 * @class   : UserInfoService
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 */

@SuppressWarnings({"all"})
public interface UserInfoService {

    /* 사용자정보 조회 */
    public UserInfo getUserInfo(Map user) throws Exception;

    /* 패스워드 실패시 카운터 업데이트 및 잠금시간 등록 */
    public int updtPswdErrCnt(Map userMap) throws Exception;

    /* 로그인시간 등록 */
    public int updtLoginTime(Map userMap) throws Exception;


    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 페이징목록 조회
     */
    public PaginatedArrayList listUserInfo(Map userMap, int currPage, int pageSize) throws Exception;

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 목록조회
     */
    public List listUserInfo(Map userMap) throws Exception;

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 상세조회
     */
    public UserInfoVO viewUserInfo(String userNo) throws Exception;

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 등록,수정,삭제
     */
    public String saveUserInfo(UserInfoVO userInfoVO) throws Exception;

    /**
     * 2021.09.17 LSH 추가
     * 사용자아이디 중복체크
     */
    public boolean existUserId(String userId) throws Exception;

    /**
     * 2021.09.29 LSH 추가
     * 사용자아이디,비밀번호 일치여부 확인
     * (D'Amo 단방향 암호화[SHA256] 함수 사용)
     */
    public boolean existUserPswd(String userId, String password) throws Exception;

    UserInfoVO openMypage(String userNo) throws Exception;

	public int updateMyInfo(UserInfoVO userInfoVO) throws Exception;

    /**
     * 2023.10.20 LSH 추가
     * 온라인위원회 - 나의정보 수정처리
     */
	public int updateMyCmit(UserInfoVO userInfoVO) throws Exception;

    /**
     * 2021.12.13 LSH 추가
     * 관리자인증 - 관리자번호 일치여부 체크
     */
    public boolean existAdminNo(String adminNo) throws Exception;

	/**
	 * 2021.12.17 LSH 추가
	 * 사용자 아이디 찾기
	 * @param paramMap.userNm  사용자 이름
	 * @param paramMap.brdt    사용자 생년월일(숫자만)
	 * @param paramMap.mbtelNo 사용자 휴대전화번호(숫자만)
	 * @return 조건에 맞는 사용자ID
	 */
	public String findUserId(Map paramMap) throws Exception;

	/**
	 * 2021.12.17 LSH 추가
	 * 비밀번호 찾기조건 일치여부 확인
	 * @param paramMap.userId  사용자 ID
	 * @param paramMap.userNm  사용자 이름
	 * @param paramMap.brdt    사용자 생년월일(숫자만)
	 * @param paramMap.mbtelNo 사용자 휴대전화번호(숫자만)
	 * @return 조건에 맞는 사용자번호
	 */
	public String validateUserNo(Map paramMap) throws Exception;

	/**
	 * 2021.12.17 LSH 추가
	 * 사용자 비밀번호 재설정 (변경처리)
	 * @param paramMap.userNo  사용자 번호
	 * @param paramMap.pswd    사용자 비밀번호
	 */
	public int updtUserPswd(Map paramMap) throws Exception;

	/**
     * 2021.01.03 CSLEE 추가
     * 사용자 비밀번호를 다음에 저장한다고 할때 날짜저장 처리.
     * @param paramMap
     * @return
     */
    public int updtUserPswdNext(Map paramMap) throws Exception;

    /**
     * 2022.01.03 추가
     * 사용자 번호호의 특정 비밀번호가 일치하는 개수 (특정 사용자 번호(userNo)의 현재 비밀번호와 동일한지 확인용)
     * (D'Amo 단방향 암호화[SHA256] 함수 사용)
     * @param paramMap userNo와 pswd 항목 필수
     * @return
     */
    public boolean checkUserPswdDupl(Map paramMap) throws Exception;

	/**
	 * 2022.11.09 ntarget
	 * 회원탈퇴
	 * @param UserInfoVO.userNo  사용자 번호
	 */
	public int updtMembWithdraw(UserInfoVO userInfoVO) throws Exception;
	
}
