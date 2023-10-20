package business.sys.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.sys.user.service.UserInfoVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;
import common.user.UserInfo;

/**
 * [DAO 클래스] - 사용자정보 조회
 *
 * @class   : UserInfoDAO
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 * 2021.09.09   LSH        사용자관리 기능 추가
 */

@Repository("UserInfoDAO")
@SuppressWarnings({"all"})
public class UserInfoDAO extends BaseDAO {

    /**
     *  사용자정보 조회
     */
    public UserInfo getUserInfo(Map paramMap) throws Exception {
        return (UserInfo)view("UserInfo.getUserInfo", paramMap);
    }

    /**
     * 패스워드 실패시 카운터 업데이트 및 잠금시간 등록
     */
    public int updtPswdErrCnt(Map userMap) throws Exception {
        return update("UserInfo.updtPswdErrCnt", userMap);
    }

    /**
     * 로그인시간 등록
     */
    public int updtLoginTime(Map userMap) throws Exception {
        return update("UserInfo.updtLoginTime", userMap);
    }


    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 페이징목록 조회
     */
    public PaginatedArrayList listUserInfo(Map userMap, int currPage, int pageSize) {
        return pageList("UserInfo.listUserInfo", userMap, currPage, pageSize);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 목록 조회
     */
    public List listUserInfo(Map userMap) {
        return list("UserInfo.listUserInfo", userMap);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 상세 조회
     */
    public UserInfoVO viewUserInfo(String userNo) {
        return (UserInfoVO)view("UserInfo.viewUserInfo", userNo);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 등록
     */
    public int regiUserInfo(UserInfoVO userInfoVO) {
        return insert("UserInfo.regiUserInfo", userInfoVO);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 수정
     */
    public int updtUserInfo(UserInfoVO userInfoVO) {
        return update("UserInfo.updtUserInfo", userInfoVO);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 삭제
     */
    public int deltUserInfo(String userNo) {
        return delete("UserInfo.deltUserInfo", userNo);
    }

    /**
     * 2021.09.17 LSH 추가
     * 사용자아이디 중복체크
     */
    public boolean existUserId(String userId) {
    	return (Integer)view("UserInfo.existUserId", userId) > 0;
    }

    /**
     * 2021.09.29 LSH 추가
     * 사용자아이디,비밀번호 일치여부 확인
     * (D'Amo 단방향 암호화[SHA256] 함수 사용)
     */
    public boolean existUserPswd(String userId, String password) {
    	Map params = new HashMap();
    	params.put("userId"  , userId);
    	params.put("password", password);
    	return (Integer)view("UserInfo.existUserPswd", params) > 0;
    }

	public UserInfoVO openMypage(String userNO) {
		return selectOne("JoinInfo.openMypage",userNO);
	}

	public int updateMyInfo(UserInfoVO userInfoVO) {
		return update("JoinInfo.updateMyInfo",userInfoVO);
	}

	/**
	 * 2021.12.13 LSH 추가
	 * 관리자인증 - 관리자번호 일치여부 확인
	 *
	 */
	public boolean existAdminNo(String adminNo) {
		return (Boolean)view("UserInfo.existAdminNo", adminNo);
	}

	/**
	 * 2021.12.17 LSH 추가
	 * 사용자 아이디 찾기
	 * @param paramMap.userNm  사용자 이름
	 * @param paramMap.brdt    사용자 생년월일(숫자만)
	 * @param paramMap.mbtelNo 사용자 휴대전화번호(숫자만)
	 * @return 조건에 맞는 사용자ID
	 */
	public String findUserId(Map paramMap) {
		return (String)view("UserInfo.findUserId", paramMap);
	}

	/**
	 * 2021.12.17 LSH 추가
	 * 비밀번호 찾기조건 일치여부 확인
	 * @param paramMap.userId  사용자 ID
	 * @param paramMap.userNm  사용자 이름
	 * @param paramMap.brdt    사용자 생년월일(숫자만)
	 * @param paramMap.mbtelNo 사용자 휴대전화번호(숫자만)
	 * @return 조건에 맞는 사용자번호
	 */
	public String validateUserNo(Map paramMap) {
		return (String)view("UserInfo.validateUserNo", paramMap);
	}

	/**
	 * 2021.12.17 LSH 추가
	 * 사용자 비밀번호 재설정 (변경처리)
	 * @param paramMap.userNo  사용자 번호
	 * @param paramMap.pswd    사용자 비밀번호
	 */
	public int updtUserPswd(Map paramMap) {
		return update("UserInfo.updtUserPswd", paramMap);
	}

	/**
	 * 2022.01.03 CSLEE 추가
	 * 사용자 비밀번호를 다음에 저장한다고 할때 날짜저장 처리.
	 * @param paramMap
	 * @return
	 */
	public int updtUserPswdNext(Map paramMap) {
	    return update("UserInfo.updtUserPswdNext", paramMap);
	}

	/**
	 * 2022.01.03 추가
     * 사용자 번호호의 특정 비밀번호가 일치하는 개수 (특정 사용자 번호(userNo)의 현재 비밀번호와 동일한지 확인용)
     * (D'Amo 단방향 암호화[SHA256] 함수 사용)
	 * @param paramMap userNo와 pswd 항목 필수
	 * @return
	 */
    public boolean checkUserPswdDupl(Map paramMap) {
        return (Integer)view("UserInfo.checkUserPswdDupl", paramMap) > 0;
    }
}