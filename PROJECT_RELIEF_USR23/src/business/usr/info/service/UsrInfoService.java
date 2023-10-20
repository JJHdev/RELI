package business.usr.info.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import business.sys.role.service.RoleVO;
import business.sys.user.service.UserInfoVO;
import commf.paging.PaginatedArrayList;
import common.file.FileInfo;
import common.user.UserInfo;

/**
 * [서비스인터페이스] - 개발탬플릿(개발자 표준 가이드용)
 *
 * @class   : MainService
 * @author  : ntarget
 * @since   : 2021.02.03
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */

@SuppressWarnings("all")
public interface UsrInfoService {

	public Integer JoinUserAgree(UserInfoVO userInfoVO)throws Exception;

	public boolean existUserId(String userId) throws Exception;

	public boolean checkJoinUser(Map paramMap) throws Exception;

}
