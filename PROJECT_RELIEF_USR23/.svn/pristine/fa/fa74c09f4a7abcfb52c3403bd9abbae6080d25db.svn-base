package business.usr.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.sys.role.service.RoleUserService;
import business.sys.role.service.RoleVO;
import business.sys.user.service.UserInfoService;
import business.sys.user.service.UserInfoVO;
import business.usr.info.service.UsrInfoService;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스 클래스] - 사용자정보 조회
 *
 * @class : UsrInfoServiceImpl
 * @author : ntarget
 * @since : 2021.02.08
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 ------- -------- ---------------------------
 *
 */

@Service("UsrInfoService")
@SuppressWarnings({ "all" })
public class UsrInfoServiceImpl extends BaseService implements UsrInfoService {

	@Resource(name = "UsrInfoDAO")
	private UsrInfoDAO usrInfoDAO;

	@Resource(name = "RoleUserService")
	private RoleUserService roleUserService;

	/**
	 * 회원가입 - 회원가입 정보 입력
	 * 
	 * @return
	 */
	@Override
	public Integer JoinUserAgree(UserInfoVO userInfoVO) throws Exception {

		// 사용자정보 저장처리
		int ret = usrInfoDAO.JoinUserAgree(userInfoVO);
		if (ret > 0) {
			// 사용자권한 저장처리
			saveRoleUser(userInfoVO);
		}
		return ret;
	}

	/**
	 * 회원가입 - 권한 설정
	 */
	private int saveRoleUser(UserInfoVO userInfoVO) throws Exception {
		RoleVO roleVO = RoleVO.builder().userNo(userInfoVO.getUserNo()).roleId(userInfoVO.getRoleId())
				.gsUserNo(userInfoVO.getGsUserNo()).build();

		// 등록이 되어있을 시
		if (usrInfoDAO.existRoleUser(roleVO))
			return 0;
		return usrInfoDAO.regiRoleUser(roleVO);
	}

	/**
	 * 회원가입 - 아이디 중복 확인
	 */
	@Override
	public boolean existUserId(String userId) throws Exception {
		return usrInfoDAO.existUserId(userId);
	}

	/**
	 * 회원가입 - 중복 가입자 확인
	 */
	@Override
	public boolean checkJoinUser(Map paramMap) throws Exception {
//		boolean checkJoinUser = usrInfoDAO.checkJoinUser(userInfoVO.getUserInfo().getUserId()());
//		if (checkJoinUser) {
//			throw new IllegalStateException("이미 존재하는 아이디입니다.");
//		}
		return usrInfoDAO.checkJoinUser(paramMap);
	}
}