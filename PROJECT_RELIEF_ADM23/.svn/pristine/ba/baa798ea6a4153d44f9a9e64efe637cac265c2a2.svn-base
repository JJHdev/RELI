package business.sys.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import business.com.CommConst;
import business.sys.authority.AuthorityService;
import business.sys.log.service.AccessControlService;
import common.user.UserInfo;
import common.util.CommUtils;
import common.util.properties.ApplicationProperty;

/**
 * SecurityInterceptor - 권한체크 및 페이지 접속로그등록.
 *
 * @class   : SecurityInterceptor
 * @author  : ntarget
 * @since   : 2021.02.08
 * @version : 1.0
 *
 *   수정일         수정자                수정내용
 *  -------        --------      ---------------------------
 *
 */

@SuppressWarnings("all")
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    protected final Log logger = LogFactory.getLog(getClass());

    //protected static final String ACCESSDENIED_PAGE     = "/error/accessDenied.jsp";
    protected static final String MAIN_URL              = "index.do";
    protected static final String CHECK_FIX             = ".do";
    protected static final String LOGIN_URL             = "/com/cmm/login.do";
    // 2022.01.20 LSH 에러페이지 URL 패턴
    protected static final String ERROR_URL             = "/com/error/";

    @Autowired
    UserInfo userInfo;

    @Autowired
    private AuthorityService  authorityService;

    @Autowired
    private AccessControlService accessControlService;

    /**
     * 페이지 권한체크 처리
     * 페이지정보 세션 저장
     * 메뉴정보 세션저장
     * 접속로그 처리
     *
     * URL로 시스템접근정보
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 타 사이트 세션 끊김 방지
        // https인 경우 'secure' 추가
    	/*
        String requestURL = request.getRequestURL().toString();
        if(requestURL.startsWith("https")){
            response.setHeader("Set-Cookie", "JSESSIONID=" + request.getRequestedSessionId() + "; path=/; Secure;  SameSite=None");
        }else {
            response.setHeader("Set-Cookie", "JSESSIONID=" + request.getRequestedSessionId() + "; path=/; SameSite=None");
        }
		*/
    	
    	boolean isAjax	= true;
    	// Ajax 여부
        if (!"XMLHttpRequest".equals(request.getHeader("x-requested-with")) ) {
        	isAjax = false;
        }    	
    	
    	// 2021.11.06 현재 시스템코드 가져오기
        String sysCd = getSystemCode(request);

    	// 2021.08.27 LSH 화면 적용 변수 생성 (Template 변수)
        setPageAttribute(request, sysCd);
        
        // 2022.01.20 LSH 현재 URL
        String reqUrl = getRequestURL(request);

        // 2022.01.20 LSH 에러페이지 권한처리 SKIP
        if (reqUrl.startsWith(ERROR_URL))
        	return true;

        // 권한체크 사용여부
        if (CommConst.isAuthCheck()) {
            Map map = new HashMap();
            map.put("url",          reqUrl );
            map.put("roleId",       CommUtils.nvlTrim(userInfo.getRoleId()) );
            map.put("userNo",       CommUtils.nvlTrim(userInfo.getUserNo()) );
            map.put("sysCd",        CommUtils.nvlTrim(sysCd) );

            // 2021.10.01 LSH 추가
            // 사용자 ROLE이 없을 경우 제한된 사용자 ROLE로 맵핑 처리
            if (CommUtils.isEmpty((String)map.get("roleId"))) {
            	map.put("roleId", CommConst.ROLE_RESTRICTED);
            }

            Map authMap = null;
            
            // ajax 제외
            if (isAjax == false) {
            	authMap = authorityService.getAuthorityUser(map);
            }

            // null -> 권한대상이 아님.
            if (authMap == null) {
                 logger.info("Not subject to authorization.");

            // 권한체크
            } else {

                // 세션정보가 없을경우
                if (CommUtils.isEmpty(userInfo.getUserNo()) ) {
                	// 2021.10.01 LSH 추가 제한된 사용자에게 접근권한이 있는 경우 로그인 SKIP 처리
                	// 2021.11.08 LSH 변경 식별아이디 사용자에게 접근권한이 있는 경우 로그인 SKIP 처리
                    if ("O".equals(authMap.get("auth")) &&
                    	CommConst.ROLE_IDNTFC.equals(map.get("roleId"))) {
                    	// do nothing
                    }
                    else {
                        // 2021.08.27 LSH 추가
                        // 접근한 URL 정보를 세션에 담아 로그인시 구분조건으로 사용한다.
                        request.getSession().setAttribute(CommConst.SESS_ACCESS_URL, request.getRequestURI());
                        //response.sendRedirect(request.getContextPath() +"/"+CommUtils.toLower(SYS_CD)+"/cmm/"+LOGIN_URL);
                        response.sendRedirect(request.getContextPath() + LOGIN_URL);
                        return false;
                    }
                }
                // 권한 없을경우 에러페이지
                if (CommUtils.nvlTrim((String)authMap.get("auth")).equals("X") ) {
                	// 2022.01.13 LSH 에러코드 403 으로 처리
                	// web.xml에 정의된 에러페이지로 Forwarding 됨
                	response.sendError(HttpStatus.FORBIDDEN.value());
                    //response.sendRedirect(request.getContextPath() + ACCESSDENIED_PAGE);
                    return false;
                }
            }
        }

        // Access Log
        accessLog(request, sysCd);

        // ajax 제외
        if (!"XMLHttpRequest".equals(request.getHeader("x-requested-with")) ) {

            // Page 정보 세션정의
            setPageInfo(request, sysCd);

            // Menu 정보 세션정의 (2021.11.26 현재사용안함)
            //setMenuInfo(request, sysCd);
        }
        return true;
    }

    // 2021.11.06 URL을 통해 시스템코드 가져오기
    private String getSystemCode(HttpServletRequest request) {
    	// 2021.08.26 LSH URL 패턴을 통해 시스템 코드를 가져온다.
    	HttpSession session = request.getSession();
    	String url = getRequestURL(request);
    	if (url.startsWith("/com/")) 
    		return "COM";
		return ApplicationProperty.get("system.code");
    }

    // 2021.08.27 LSH 화면 적용 변수 생성 (Template 변수)
    private void setPageAttribute(HttpServletRequest request, String sysCd) {

    	String contextPath = request.getContextPath();
    	String requestUri  = request.getRequestURI();
        String scriptPage  = requestUri.substring(requestUri.lastIndexOf("/"));
        if (!"".equals(scriptPage) &&
        	!"/".equals(scriptPage) )
        	scriptPage = scriptPage.substring(0, scriptPage.lastIndexOf("."));

        String scriptPath = requestUri.substring(0, requestUri.lastIndexOf("/"));
        scriptPath = scriptPath.replaceAll("/WEB-INF/jsp/", "");
        // Context Path는 제외한다.
        scriptPath = scriptPath.substring(contextPath.length());

        request.setAttribute("sysCd"      , sysCd);
        request.setAttribute("scriptPage" , scriptPath + scriptPage);
        request.setAttribute("contextPath", request.getContextPath());
        request.setAttribute("ver"        , CommUtils.getCurrDateTime2());

        //logger.info("SECURITY_INTERCEPTOR ***** ["+SYS_CD+":"+requestUri+"]");
    }

    // 로그 등록
    private void accessLog(HttpServletRequest request, String sysCd) {
        String userNo  = "";
        if (userInfo != null)
            userNo = CommUtils.nvlTrim(userInfo.getUserNo());

        String url = getRequestURL(request);

        // Access Log Registration
        Map logMap = new HashMap();
        logMap.put("userNo",        CommUtils.nvlTrim(userNo, "guest") );
        logMap.put("ipAddr",        request.getRemoteAddr());
        logMap.put("progUrl",       url);
        logMap.put("srvrNm",        CommConst.SERVER_NAME+":"+request.getServerPort()+"__"+
                                    request.getLocalName()+"__"+
                                    request.getLocale().toString());
        logMap.put("sysCd",         CommUtils.nvlTrim(sysCd));

        accessControlService.regiAccessLog(logMap);

        // 2021.11.03 LSH 구제급여 상세조회인 경우 KEY값 추가정의
        // 개인정보보안에 따른 로깅처리로직 추가 (공통화 고민필요)
        // 2021.11.04 별도의 테이블(SYS_INFO_HST) 로 관리하기로 변경됨
        if (url.startsWith("/adm/relief/getRelief.do")) {
        	logMap.put("cntnSeNm", url);
        	logMap.put("key1"    , request.getParameter("aplyNo"));
	        accessControlService.regiInfoLog(logMap);
	    }
        // 2023.01.30 LSH 살생물제품 구제급여 상세조회인 경우 개인정보보안로그 등록
	    else if (url.startsWith("/adm/bio/getBioRelief.do")) {
        	logMap.put("cntnSeNm", url);
        	logMap.put("key1"    , request.getParameter("aplyNo"));
	        accessControlService.regiInfoLog(logMap);
        }
    }

    // 프로그램정보 세션정의
    private void setPageInfo(HttpServletRequest request, String sysCd) throws Exception {
        // Page Information
        Map pageMap = new HashMap();
        pageMap.put("url",          getRequestURL(request));
        pageMap.put("roleId",       CommUtils.nvlTrim(userInfo.getRoleId(), CommConst.ROLE_RESTRICTED));
        pageMap.put("sysCd",        CommUtils.nvlTrim(sysCd));

        if (getCurrPage(request).endsWith(CHECK_FIX)) {
            String excludeProg = CommUtils.nvlTrim(CommConst.EXCLUDE_ACC_PROG);

            if (excludeProg.indexOf(getCurrPage(request)) < 0 ) {
                Map progMap = authorityService.getProgInfo(pageMap);

                if (progMap != null)
                    pageMap.put("pageInfo", progMap);
            }
        }

        if (userInfo != null)
            pageMap.put("userInfo", userInfo);




        request.getSession().setAttribute(CommConst.SESS_PAGE_INFO, pageMap);
    }

    // 메뉴정보 세션정의
    private void setMenuInfo(HttpServletRequest request, String sysCd) throws Exception {

    	// 현재 화면 URL을 기준으로 해당 메뉴정보를 조회해 온다.


        // MAIN_URL 접속시 무조건 다시 메뉴조회 가능하게 메뉴정보를 삭제
        //if (getCurrPage(request).endsWith(MAIN_URL))
        //    request.getSession().removeAttribute(SESS_MENU_INFO);

        // 세션에 등록된 메뉴정보 있으면 메뉴조회 쿼리 수행 안함.
        //if (request.getSession().getAttribute(SESS_MENU_INFO) == null) {
        Map menuMap = new HashMap();
        menuMap.put("roleId",   CommUtils.nvlTrim(userInfo.getRoleId(), CommConst.ROLE_RESTRICTED));
        menuMap.put("sysCd",    CommUtils.nvlTrim(sysCd));

        // 메뉴목록을 조회하여 세션에 담는다. (TODO 세션의 메뉴목록 사용여부 확인필요)
        List menuList = authorityService.getMenuList(menuMap);
        request.getSession().setAttribute(CommConst.SESS_MENU_LIST, menuList);
    }

    private String getRequestURL(HttpServletRequest request) {
        String requestURL = request.getRequestURI();
        requestURL      = requestURL.substring(request.getContextPath().length());
        String pathInfo = request.getPathInfo();

        if ("/".equals(requestURL) && pathInfo != null) {
            requestURL = pathInfo;
        }
        return requestURL;
    }

    private String getCurrPage(HttpServletRequest request) {
        String currPage     = request.getRequestURI();
        return currPage.substring(currPage.lastIndexOf("/")+1);
    }


}
