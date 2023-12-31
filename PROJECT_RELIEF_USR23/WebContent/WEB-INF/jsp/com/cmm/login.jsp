<%--
******************************************************************************************
*** 파일명 : login.jsp
*** 설명   : 사용자 로그인 화면
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

<%-- 사용자 로그인 START ================================================== --%>
	<body>
	    <!-- Tiles HEADER -->
	    <tiles:insertAttribute name="header"/>

		<section class="contents member">
			<div class="contents-wrap login">
				<div class="login-wrap">
					<h2>로그인</h2> 
					<div class="login-inner">
						<div class="login-sortation btnWrap">
						<c:if test="${act == 'LOGIN'}">
							<a href="#void" data-id="LOGIN" class="hoverColor2 on">회원가입자</a>
						</c:if>
							<a href="#void" data-id="IDENT" class="hoverColor1">식별아이디</a>
							<a href="#void" data-id="CMIT"  class="hoverColor1">온라인위원</a>
						</div>
						<c:if test="${act == 'LOGIN'}">
							<form id="loginForm" name="loginForm" method="post">
							<div class="login-form">
								<input type="hidden"   id="lgnType"  name="lgnType" value="LOGIN"/>
								<input type="text"     id="username" name="username" maxlength="20" placeholder="아이디"/>
								<input type="password" id="password" name="password" maxlength="30" placeholder="비밀번호"/>
								<button type="button"  id="btnLogin">로그인</button>
							</div>
							</form>
						</c:if>
				        <script type="text/javascript" src="<c:url value='/js/com/comm_certify.js?version=${ver}'/>"></script>
						<form id="identForm" name="identForm" method="post">
						<div class="login-form">
							<input type="text" id="identNm" name="identNm" maxlength="20" placeholder="조회자 성명" />
							<input type="text" id="identId" name="identId" maxlength="20" placeholder="식별 아이디" value="${idntfcId}"/>
							<input type="hidden" id="cnfrmYn" name="cnfrmYn" value="N" />
							<input type="hidden" id="smsYn"   name="smsYn"   value="N" />
							
							<!-- 식별아이디보유자 일시 -->
							<div class="login-form-certi">
								<div class="inputWrap">
									<input type="radio" name="certType" id="certType1" value="PHONE" />
									<label for="certType1">휴대전화번호 인증</label>
								</div>
								<!-- 
								<div class="inputWrap">
									<input type="radio" name="certType" id="certType2" value="REGNO" />
									<label for="certType2">주민등록번호 인증</label>
								</div>
								 -->
							</div>
							<button type="button" id="btnCertify">인증</button>
						</div>
						</form>
						<c:if test="${act == 'LOGIN'}">
							<div class="login-menu">
								<a href="#void" id="btnFindId">아이디 찾기</a>
								<a href="#void" id="btnFindPwd">비밀번호 찾기</a>
								<a href="#void" id="btnJoin">회원가입</a>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</section>
	
		<!-- Tiles FOOTER -->
		<tiles:insertAttribute name="footer" />
	
	</body>
<%-- 사용자 로그인 END ==================================================== --%>

</html>
