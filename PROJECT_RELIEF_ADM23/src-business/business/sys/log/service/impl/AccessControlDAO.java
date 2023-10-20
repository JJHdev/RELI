package business.sys.log.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import commf.dao.CommonMapperImpl;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 접속로그 등록(페이지접속, 로그인접속 로그 저장)
 *
 * @class   : AccessControlDAO
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("AccessControlDAO")
@SuppressWarnings({"all"})
public class AccessControlDAO extends BaseDAO {

    /**
     * 2021.11.04 LSH
     * 정보보안로그
     */
    public int regiInfoLog(Map paramMap) {
        return insert("AccessControl.regiInfoLog", paramMap);
    }

    /**
     * 접속로그
     */
    public int regiAccessLog(Map paramMap){
        return update("AccessControl.regiAccessLog", paramMap);
    }

    /**
     * 로그인로그
     */
    public void regiLoginLog(Map paramMap) {
        insert("AccessControl.regiLoginLog", paramMap);
    }

}
