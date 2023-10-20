<%--
******************************************************************************************
*** 파일명 : login.jsp
*** 설명   : 관리자 로그인 화면
***
*** -----------------------------    Modified Log   --------------------------------------
*** 버전        수정일자            수정자                  내용
*** --------------------------------------------------------------------------------------
*** 1.0         2020-09-28          ntarget                 First Coding.
*** 1.0         2021-11-08          LSH                     식별아이디 인증 추가
*** 1.0         2022-01-05          LSH                     시스템분리 (사용자/관리자)
******************************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="title.sysname"/></title>
    
    <!-- Tiles RESOURCE -->
    <tiles:insertAttribute name="resource"/>

	<script type="text/javascript" src="<c:url value='/js/com/comm_login.js?version=${ver}'/>"></script>

</head>

<%-- 관리자 로그인 START ================================================== --%>
	<body class="adm-login">
		<div class="adm-login-wrap">
			<div class="cover"></div>
			<div class="login-bx">
				<h1 class="login-title">
					<img src="<c:url value='/images/common/logo.jpg'/>" alt="<spring:message code="title.sysname"/>">
					<span><spring:message code="title.sysname"/></span>
				</h1>
				<form id="loginForm" name="loginForm" method="post">
					<div class="write-div">
						<label for="username">ID</label>
						<input type="text" id="username" name="username" maxlength="20" placeholder="아이디 입력"/>
					</div>
					<div class="write-div">
						<label for="password">Password</label>
						<input type="password" id="password" name="password" maxlength="30" placeholder="비밀번호"/>
					</div>
					<a class="btn-submit" href="#" id="btnLogin">LOGIN</a>
				</form>
			</div>
		</div>
	</body>
<%-- 관리자 로그인 END ==================================================== --%>

</html>
