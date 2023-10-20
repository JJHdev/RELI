<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
##========================================================================================
## 화면 상단 HTML 공통 영역
##
##========================================================================================
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com"%>

<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="common.user.UserInfo"%>
<%@ page import="common.util.CommUtils"%>
<%@ page import="common.util.properties.ApplicationProperty"%>
<%
	// UserInfo 세션
ServletContext servletContext = this.getServletContext();
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
UserInfo userInfo = (UserInfo) wac.getBean("userInfo");

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


<c:set var="userInfo" value="<%=userInfo%>" />

<header>
	<div class="head-wrap">
		<h1 class="logo">
			<a href="<c:url value='/usr/main/main.do'/>" title="HOME">
				<img src="<c:url value='/images/common/logo_2.png'/>" alt="로고">
<!-- 				<span>환경오염피해구제시스템</span> -->
			</a>
		</h1>
		<div class="searchBox pc" style="min-width: 450px">
			<!-- 
			<form id="headerForm" name="headerForm" action="">
				<div class="inputWrap">
					<input  type="text" id="searchText" name="searchText" placeholder="검색어를 입력하세요." />
					<button type="submit"><i><img src="<c:url value='/images/common/i-search.jpg'/>" alt="" /></i></button>
				</div>
			</form>
			 -->
            <c:if test="${userInfo.login}">
            <div class="sessionInfo">
                남은시간은 <span class="leftTimeInfo" id='leftTimeInfo'>00:00:00</span> 입니다.<!-- 세션 남은시간 -->
                <a id="clickInfo" href="#">[시간연장]</a>
            </div>
            </c:if>              
		</div>
		<aside class="pc">
			<ul>
				<c:if test="${userInfo.login}">
					<li>
						<a href="<c:url value='/com/cmm/logout.do'/>">로그아웃</a>
					</li>
					<li>
						<a href="<c:url value='/usr/cmm/sitemap.do'/>">사이트맵</a>
					</li>
				</c:if>
				<c:if test="${!userInfo.login}">
					<li>
						<a href="<c:url value='/com/cmm/login.do'/>">로그인</a>
					</li>
					<li>
						<a href="<c:url value='/usr/cmm/joinAgree.do'/>">회원가입</a>
					</li>
					<li>
						<a href="<c:url value='/usr/cmm/sitemap.do'/>">사이트맵</a>
					</li>
				</c:if>
			</ul>
		</aside>
		<a class="gnbView mobile right" href="javascript:void(0)" title="전체메뉴보기"></a>
	</div>

	<nav class="pc box">

		<div class="navbg pc">
			<div class="dep1"></div>
			<div class="dep2"></div>
		</div>

		<!-- orgH:1차메뉴 높이 / leftgap:왼쪽에서 여백 px,% 사용가능 -->
		<div class="gnb gnb-wrap box" data-orgH="84" data-gap="" data-leftPos="">
			<!-- TOP NAVIGATION -->
			<ul class="topnavbar"></ul>
		</div>
	</nav>
	<!-- //nav -->

	<div class="gnbCover mobile"></div>
	<nav class="mobile box" data-arrow="right">
		<div class="closeWrap">
			<a href="#void" class="gnbClose"></a>
			<aside>
				<a href="<c:url value='/usr/main/main.do'/>">HOME</a>
				<c:if test="${userInfo.login}">
					<a class="second" href="<c:url value='/com/cmm/logout.do'/>">로그아웃</a>
					<a class="second" href="<c:url value='/usr/cmm/sitemap.do'/>">사이트맵</a>
				</c:if>
				<c:if test="${!userInfo.login}">
					<a class="second" href="<c:url value='/com/cmm/login.do'/>">로그인</a>
					<a class="second" href="<c:url value='/usr/cmm/joinAgree.do'/>">회원가입</a>
					<a class="second" href="<c:url value='/usr/cmm/sitemap.do'/>">사이트맵</a>
				</c:if>
			</aside>
		</div>
		<div class="searchWrap">
			<!-- 
			<form id="headerForm" name="headerForm" action="">
				<div class="inputWrap">
					<input  type="text" id="searchText" name="searchText" placeholder="검색어를 입력하세요." />
					<button type="submit"><i><img src="<c:url value='/images/common/i-search.jpg'/>" alt="" /></i></button>
				</div>
			</form>
			 -->
		</div>
	</nav>

</header>
<!-- // header -->

<div class="headerH"></div>

