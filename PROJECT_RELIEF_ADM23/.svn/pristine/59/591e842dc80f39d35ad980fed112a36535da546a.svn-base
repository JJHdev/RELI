package business.sys.log.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.sys.log.service.AccessControlService;
import common.base.BaseService;

/**
 * [서비스클래스] - 접속로그 등록(페이지접속, 로그인접속 로그 저장)
 * 
 * SecurityInterceptor 에서 접근하는 공통 서비스
 *
 * @class   : AccessControlServiceImpl
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 */

@Service("AccessControlService")
@SuppressWarnings({"all"})
public class AccessControlServiceImpl extends BaseService implements AccessControlService {

    @Resource(name = "AccessControlDAO")
    private AccessControlDAO accessControlDAO;

    /**
     * 2021.11.04 LSH
     * 정보보안로그
     */
    public int regiInfoLog(Map paramMap) {
        return accessControlDAO.regiInfoLog(paramMap);
    }

    /**
     * 접속로그
     */
    public int regiAccessLog(Map paramMap) {
        return accessControlDAO.regiAccessLog(paramMap);
    }

    /**
     * 로그인로그
     */
    public String regiLoginLog(Map paramMap) {
        accessControlDAO.regiLoginLog(paramMap);

        String keyNo = (String)paramMap.get("keyNo");

        return keyNo;
    }

}
