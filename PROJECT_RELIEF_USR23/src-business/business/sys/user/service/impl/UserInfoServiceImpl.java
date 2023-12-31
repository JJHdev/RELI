package business.sys.user.service.impl;

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
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.user.UserInfo;
import common.util.CommUtils;

/**
 * [서비스 클래스] - 사용자정보 조회
 *
 * @class   : UserInfoServiceImpl
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */

@Service("UserInfoService")
@SuppressWarnings({"all"})
public class UserInfoServiceImpl extends BaseService implements UserInfoService {

    @Resource(name = "UserInfoDAO")
    private UserInfoDAO userInfoDAO;

    @Resource(name = "RoleUserService")
    private RoleUserService roleUserService;

    /**
     *  사용자정보 조회
     */
    public UserInfo getUserInfo(Map paramMap) throws Exception {
        return userInfoDAO.getUserInfo(paramMap);
    }

    /**
     * 패스워드 실패시 카운터 업데이트 및 잠금시간 등록
     */
    public int updtPswdErrCnt(Map userMap) throws Exception {
        return userInfoDAO.updtPswdErrCnt(userMap);
    }

    /**
     * 로그인시간 등록
     */
    public int updtLoginTime(Map userMap) throws Exception {
        return userInfoDAO.updtLoginTime(userMap);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 페이징목록조회
     */
    @Override
    public PaginatedArrayList listUserInfo(Map userMap, int currPage, int pageSize) throws Exception {
    	return userInfoDAO.listUserInfo(userMap, currPage, pageSize);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 목록조회
     */
    @Override
    public List listUserInfo(Map userMap) throws Exception {
    	return userInfoDAO.listUserInfo(userMap);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 상세조회
     */
	@Override
	public UserInfoVO viewUserInfo(String userNo) throws Exception {
		return userInfoDAO.viewUserInfo(userNo);
	}

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 등록
     */
    private int regiUserInfo(UserInfoVO userInfoVO) throws Exception {
        return userInfoDAO.regiUserInfo(userInfoVO);
    }

    /**
     * 2021.09.09 LSH 추가
     * - 사용자정보 수정
     * 2022.01.04 CSLEE 수정
     * - 비밀번호 변경 내용 추가
     */
    private int updtUserInfo(UserInfoVO userInfoVO) throws Exception {

        int res = userInfoDAO.updtUserInfo(userInfoVO);

        // 2022.01.04 CSLEE 수정 (정보 변경과 비밀번호 변경 내용 분리)
        // 수정이 처리됐고 비밀번호 입력 내용이 존재하면 비밀번호 변경 로직 수행
        String pswd = userInfoVO.getPswd();
        if( res > 0 && !CommUtils.isEmpty(pswd)) {
            Map paramMap = new HashMap();
            paramMap.put("userNo" , userInfoVO.getUserNo());
            paramMap.put("pswd"   , pswd);
            // 비밀번호 변경 처리
            userInfoDAO.updtUserPswd(paramMap);
        }

        return res;
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 삭제
     */
    private int deltUserInfo(String userNo) throws Exception {
        return userInfoDAO.deltUserInfo(userNo);
    }

    /**
     * 2021.09.09 LSH 추가
     * 사용자정보 등록,수정,삭제
     * 2021.09.17 권한 처리 추가
     */
	@Override
	public String saveUserInfo(UserInfoVO userInfoVO) throws Exception {

		if (userInfoVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = userInfoVO.getMode();

		if (CommConst.INSERT.equals(mode)) {
			// 사용자정보 등록
			ret = regiUserInfo(userInfoVO);

			if (ret > 0) {
				// 사용자권한 설정
				saveRoleUser(userInfoVO);
			}
		}
		else if (CommConst.UPDATE.equals(mode)) {

			Map userMap = new HashMap();
			userMap.put("userId", userInfoVO.getUserId());

			UserInfo org = getUserInfo(userMap);

			if (org == null)
				throw processException("error.comm.notTarget");

			// 사용자정보 수정
			ret = updtUserInfo(userInfoVO);

			if (ret > 0) {
				// 권한이 변경되었다면
				if (!CommUtils.isEqual(org.getRoleId(), userInfoVO.getRoleId())) {
					// 해당 사용자의 권한 삭제
					roleUserService.deltRoleUserByUserNo(userInfoVO.getUserNo());
					// 사용자권한 설정
					saveRoleUser(userInfoVO);
				}
			}
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 사용자정보 삭제
			ret = deltUserInfo(userInfoVO.getUserNo());
			if (ret > 0) {
				// 해당 사용자의 권한 삭제
				roleUserService.deltRoleUserByUserNo(userInfoVO.getUserNo());
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

	// 사용자 권한 설정
	private int saveRoleUser(UserInfoVO userInfoVO) throws Exception {
		// 사용자권한 설정
		RoleVO roleVO = RoleVO.builder()
				.userNo(userInfoVO.getUserNo())
				.roleId(userInfoVO.getRoleId())
				.gsUserNo(userInfoVO.getGsUserNo())
				.build();

		// 이미 등록되어 있으면
		if (roleUserService.existRoleUser(roleVO))
			return 0;

		return roleUserService.regiRoleUser(roleVO);
	}

    /**
     * 2021.09.17 LSH 추가
     * 사용자아이디 중복체크
     */
	@Override
    public boolean existUserId(String userId) throws Exception {
		return userInfoDAO.existUserId(userId);
	}

    /**
     * 2021.09.29 LSH 추가
     * 사용자아이디,비밀번호 일치여부 확인
     * (D'Amo 단방향 암호화[SHA256] 함수 사용)
     */
	@Override
    public boolean existUserPswd(String userId, String password) {
		return userInfoDAO.existUserPswd(userId, password);
    }

//	@Override
//	public String openMypage(UserInfoVO userInfoVO) throws Exception {
//		return userInfoDAO.openMypage(userInfoVO);
//	}

	@Override
	public UserInfoVO openMypage(String userNo) throws Exception {
		return userInfoDAO.openMypage(userNo);
	}

	/**
	 * 마이페이지 > 나의 정보 변경 처리
	 */
	@Override
	public int updateMyInfo(UserInfoVO userInfoVO) throws Exception {
	    // 사용자 정보 수정 처리
	    int res = userInfoDAO.updateMyInfo(userInfoVO);

	    // 2022.01.04 CSLEE 수정 (정보 변경과 비밀번호 변경 내용 분리)
	    // 수정이 처리됐고 비밀번호 입력 내용이 존재하면 비밀번호 변경 로직 수행
	    String pswd = userInfoVO.getPswd();
	    if( res > 0 && !CommUtils.isEmpty(pswd)) {
	        Map paramMap = new HashMap();
	        paramMap.put("userNo" , userInfoVO.getUserNo());
	        paramMap.put("pswd"   , pswd);
	        // 비밀번호 변경 처리
	        userInfoDAO.updtUserPswd(paramMap);
	    }

		return res;
	}

    /**
     * 2023.10.20 LSH 추가
     * 온라인위원회 - 나의정보 수정처리
     */
	@Override
	public int updateMyCmit(UserInfoVO userInfoVO) throws Exception {
	    // 사용자 정보 수정 처리
	    return userInfoDAO.updateMyCmit(userInfoVO);
	}

    /**
     * 2021.12.13 LSH 추가
     * 관리자인증 - 관리자번호 일치여부 확인
     */
	@Override
    public boolean existAdminNo(String adminNo) {
		return userInfoDAO.existAdminNo(adminNo);
    }

	/**
	 * 2021.12.17 LSH 추가
	 * 사용자 아이디 찾기
	 * @param paramMap.userNm  사용자 이름
	 * @param paramMap.brdt    사용자 생년월일(숫자만)
	 * @param paramMap.mbtelNo 사용자 휴대전화번호(숫자만)
	 * @return 조건에 맞는 사용자ID
	 */
	@Override
	public String findUserId(Map paramMap) throws Exception {
		return userInfoDAO.findUserId(paramMap);
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
	@Override
	public String validateUserNo(Map paramMap) throws Exception {
		return userInfoDAO.validateUserNo(paramMap);
	}

	/**
	 * 2021.12.17 LSH 추가
	 * 사용자 비밀번호 재설정 (변경처리)
	 * @param paramMap.userNo  사용자 번호
	 * @param paramMap.pswd    사용자 비밀번호
	 */
	@Override
	public int updtUserPswd(Map paramMap) throws Exception {
		return userInfoDAO.updtUserPswd(paramMap);
	}

	/**
     * 2021.01.03 CSLEE 추가
     * 사용자 비밀번호를 다음에 저장한다고 할때 날짜저장 처리.
     * @param paramMap
     * @return
     */
    public int updtUserPswdNext(Map paramMap) throws Exception {
        return userInfoDAO.updtUserPswdNext(paramMap);
    }

    /**
     * 2022.01.03 추가
     * 사용자 번호호의 특정 비밀번호가 일치하는 개수 (특정 사용자 번호(userNo)의 현재 비밀번호와 동일한지 확인용)
     * (D'Amo 단방향 암호화[SHA256] 함수 사용)
     * @param paramMap userNo와 pswd 항목 필수
     * @return
     */
    public boolean checkUserPswdDupl(Map paramMap) throws Exception {
        return userInfoDAO.checkUserPswdDupl(paramMap);
    }
    

	/**
     * 2022.11.09 ntarget
     * 회원탈퇴
     * @param UserInfoVO
     * @return
     */
    public int updtMembWithdraw(UserInfoVO userInfoVO) throws Exception {
        return userInfoDAO.updtMembWithdraw(userInfoVO);
    }    
}