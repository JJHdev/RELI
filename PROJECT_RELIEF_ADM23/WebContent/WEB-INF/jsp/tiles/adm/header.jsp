<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
##========================================================================================
## 화면 상단 HTML 공통 영역
##
##========================================================================================
 --%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>

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

String cookLatestTime      = ApplicationProperty.get("COOK.LATEST.TIME");
String cookExpireTime      = ApplicationProperty.get("COOK.EXPIRE.TIME");
%>

<!-- 2020.10.04 세션시간관리[ntarget] -->
<script type="text/javascript" >
    var isLogin           = <%=userInfo.isLogin()%>;
    var COOK_LATEST_TIME  = '<%=cookLatestTime%>';
    var COOK_EXPIRE_TIME  = '<%=cookExpireTime%>';
</script>
<script type="text/javascript" src="<c:url value='/js/com/comm_session.js?version=${ver}'/>"></script>


<c:set var="userInfo" value="<%=userInfo%>"/>

<!-- TOP HEADER NAVBAR // -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<!-- LOGO -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/adm/main/main.do'/>" title="<spring:message code="title.sysname"/>"><img src="<c:url value='/images/common/logo.png'/>" alt="<spring:message code="title.sysname"/>"></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<div class="userInfo">
    			<c:if test="${userInfo.login}">
    				<p class="usr"><b><c:out value="${userInfo.userNm}"/></b> (<c:out value="${userInfo.roleNm}"/>)</p>
    	           	<p><a href="#" id="logoutBtn">로그아웃</a></p>
    			</c:if>
			</div>
            <div class="sessionInfo">
                남은시간은 <span class="leftTimeInfo" id='leftTimeInfo'>00:00:00</span> 입니다.<!-- 세션 남은시간 -->
                <a id="clickInfo" href="#">[시간연장]</a>
            </div>              
		</div>
	</div>
</nav>
<!-- // TOP HEADER NAVBAR -->
