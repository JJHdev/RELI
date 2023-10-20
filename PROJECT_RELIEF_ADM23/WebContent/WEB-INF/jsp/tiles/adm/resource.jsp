<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="common.user.UserInfo" %>
<%@ page import="common.util.CommUtils" %>
<%@ page import="common.util.properties.ApplicationProperty"%>
<%
    // UserInfo 세션
    ServletContext servletContext = this.getServletContext();
    WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    UserInfo userInfo = (UserInfo)wac.getBean("userInfo");
%>
<c:set var="userInfo" value="<%=userInfo%>"/>

<%--
##========================================================================================
## 관리자화면 공통 스타일시트, 자바스크립트
## [각 프로젝트별로 퍼블리싱 HTML 적용]
## Velocity Template layout_stylescript.vm과 동일
##========================================================================================
--%>

    <meta http-equiv="Content-Type"     content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible"  content="IE=edge">
    <meta name="viewport"               content="user-scalable=yes, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
    <meta name="title"                  content="<spring:message code="title.sysname"/>">
    <meta name="author"                 content="<spring:message code="title.sysname"/>">
    <meta name="keywords"               content="<spring:message code="title.sysname"/>">
    <meta name="subject"                content="<spring:message code="title.sysname"/>">
    <meta name="Description"            content="<spring:message code="title.sysname"/>">
    <meta name="classification"         content="">
	
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/adm/adm.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>" />
    <script>
    	<%-- 
    	서버의 CONTEXT PATH를 자바스크립트 전역변수로 설정 
    	(comm_utils.js, comm_sys.js 에서 사용한다.) 
    	--%>
    	var ROOT_PATH = '${pageContext.request.contextPath}'; 
        <%-- 페이지정보를 전역변수로 설정 --%>
        const PAGEINFO = {
        	PROG_ID:      '${PAGEINFO.pageInfo.progId}',
        	PROG_NM:      '${PAGEINFO.pageInfo.progNm}',
        	MENU_ID:      '${PAGEINFO.pageInfo.menuId}',
        	MENU_NM:      '${PAGEINFO.pageInfo.titleNm}',
        	FRST_MENU_ID: '${PAGEINFO.pageInfo.firstMenuId}',
            FRST_MENU_NM: '${PAGEINFO.pageInfo.firstMenuNm}',
        	SCND_MENU_ID: '${PAGEINFO.pageInfo.secondMenuId}',
            SCND_MENU_NM: '${PAGEINFO.pageInfo.secondMenuNm}',
        	THRD_MENU_ID: '${PAGEINFO.pageInfo.thirdMenuId}',
            THRD_MENU_NM: '${PAGEINFO.pageInfo.thirdMenuNm}',
            MENU_PATH:    '${PAGEINFO.pageInfo.menuPath}'
        };
    </script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/bootstrap/bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/bootstrap/bootstrap-dialog.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery.json-2.3.js'/>"></script>               <!-- jQuery FORM Plugin-->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.form.min-4.2.2.js'/>"></script>               <!-- jQuery FORM Plugin-->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.inputmask.bundle.js'/>"></script>             <!-- inputmask -->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.serializeObject.js'/>"></script>              <!-- serializeObject -->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.validate.js'/>"></script>                     <!-- jQuery Validation -->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.number.js'/>"></script>                       <!-- jQuery Format Number -->
    
    <script type="text/javascript" src="<c:url value='/jquery/design/adm/ebi.default.js'/>"></script>                  <!-- Design 관련 -->
    <script type="text/javascript" src="<c:url value='/jquery/design/adm/ebi.js'/>"></script>                          <!-- Design 관련 -->

    <script type="text/javascript" src="<c:url value='/js/message.js?version=${ver}'/>"></script>          <!-- Message Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_const.js?version=${ver}'/>"></script>            <!-- Constants Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_utils.js?version=${ver}'/>"></script>            <!-- Utils Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_popup.js?version=${ver}'/>"></script>            <!-- Popup Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/adm_popup.js?version=${ver}'/>"></script>            <!-- Admin Popup Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_validate.js?version=${ver}'/>"></script>         <!-- Validate Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_element.js'/>"></script>          <!-- User Define Component Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_component.js?version=${ver}'/>"></script>        <!-- User Define Component Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_board.js?version=${ver}'/>"></script>        <!-- User Define Component Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_biz.js?version=${ver}'/>"></script>              <!-- Business Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_sys.js?version=${ver}'/>"></script>              <!-- System Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_adm.js?version=${ver}'/>"></script>              <!-- System Function Script -->

    <script type="text/javascript" src="<c:url value='/plugins/easyui-1.9.15/jquery.easyui.min.js'/>"></script>      <!-- EasyUI Plugin -->
    <script type="text/javascript" src="<c:url value='/plugins/easyui-1.9.15/locale/easyui-lang-ko.js'/>"></script>  <!-- EasyUI Language Plugin -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_easyui.js?version=${ver}'/>"></script>           <!-- EasyUI Function Script -->

    <script type="text/javascript" src="<c:url value='/plugins/ckeditor/ckeditor.js?t=B37D54V'/>"></script>         <!-- CKEditor Plugin -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_ckeditor.js?version=${ver}'/>"></script>         <!-- CKEditor Function Script -->
	
    <script type="text/javascript" src="<c:url value='/plugins/pdfobject-2.2.5/pdfobject.js'/>"></script>           <!-- PDFObject Plugin -->

    <script type="text/javascript" src="<c:url value='/js/adm/gis/ol.js'/>"></script>          						 <!-- gis lib -->
    <script type="text/javascript" src="<c:url value='/js/adm/gis/html2canvas.js'/>"></script>          						 <!-- gis lib -->

<c:if test="${not empty scriptPage}">
    <!-- Business Script-->
    <script type="text/javascript" src="<c:url value='/js${scriptPage}.js?version=${ver}'/>"></script>
</c:if>
