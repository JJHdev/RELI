package business.usr.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.sys.role.service.RoleVO;
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

@Repository("UsrInfoDAO")
@SuppressWarnings({"all"})
public class UsrInfoDAO extends BaseDAO {
	
	/**
	 * 회원가입 - 회원가입 정보 입력
	 * @return 
	 */
	public Integer JoinUserAgree(UserInfoVO userInfoVO) {
		return insert("JoinInfo.JoinUserAgree",userInfoVO);
	}
		
	/**
	 * 회원가입 - 아이디 중복 확인
	 */
	public boolean existUserId(String userId) {
		return (Integer)view("JoinInfo.existUserId", userId) > 0;
    }

	/**
	 * 회원가입 - 권한 확인
	 */
	public boolean existRoleUser(RoleVO roleVO) {
		 return (Integer)view("JoinInfo.existRoleUser", roleVO) > 0;
	}

	/**
	 * 회원가입 - 권한 등록
	 */
	public int regiRoleUser(RoleVO roleVO) {
		return insert("JoinInfo.regiRoleUser", roleVO);
	}

	/**
	 * 회원가입 - 비정상 접근 및 중복 확인
	 */
	public boolean checkJoinUser(Map paramMap) {
		return (Integer)view("JoinInfo.checkJoinUser",paramMap) > 0;
	}
	
}