<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**  @(#)500.jsp
**  @author     : ntarget
**  @version    : 1.0
**  @since      : 2021/02/21
**                2022/01/12 LSH error controller 적용
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<!DOCTYPE html>
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
<html lang="ko">
<head>
  	<title><spring:message code="title.sysname"/></title>

	<meta charset="utf-8">
    <meta http-equiv="Content-Type"     content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible"  content="IE=edge">
    <meta name="viewport"               content="user-scalable=yes, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
    <meta name="title"                  content="<spring:message code="title.sysname"/>">
    <meta name="author"                 content="<spring:message code="title.sysname"/>">
    <meta name="keywords"               content="<spring:message code="title.sysname"/>">
    <meta name="subject"                content="<spring:message code="title.sysname"/>">
    <meta name="Description"            content="<spring:message code="title.sysname"/>">
    <meta name="classification"         content="">

	<c:if test="${sysCd == 'ADM'}"><link rel="stylesheet" type="text/css" href="<c:url value='/css/adm/adm.css'  />" /></c:if>
	<c:if test="${sysCd != 'ADM'}"><link rel="stylesheet" type="text/css" href="<c:url value='/css/usr/style.css'/>" /></c:if>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/error.css'/>" />

    <script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/bootstrap/bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/bootstrap/bootstrap-dialog.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery-ui.min.js'/>"></script>

</head>
<body>

<c:if test="${sysCd == 'ADM'}">
<%-- 관리자 에러페이지 START ============================================== --%>
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- LOGO -->
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value='/${sysCdLower}/main/main.do'/>" title="<spring:message code="title.sysname"/>"><img src="<c:url value='/images/common/logo.png'/>" alt="<spring:message code="title.sysname"/>"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<div class="userInfo"></div>
			</div>
		</div>
	</nav>
	<div class="container-fluid" style="padding-top:80px; margin-bottom:100px;">
		<div class="row">
			<div class="col-12" style="padding:150px;">

				<div class="app-error-adm">
					<div class="error-title">
						<h2>${errorTitle}</h2>
					</div>
					
					<div class="error-wrap">
						<p class="exception_message">
							${errorMessage}
							<c:if test="${ exceptionMessage != null }">
								<br>
								( <span class="exception_detail_message">${exceptionMessage}</span> )
							</c:if>
						</p>
					</div>
				</div>
				<div class="btnWrap type3">
					<a href="javascript:history.back();" class="blue" title="이전페이지로 이동">이전페이지로 이동</a>
				</div>
			</div>
		</div>
	</div>
	<footer class="container-fluid navbar-fixed-bottom text-center" id="footer">
		<spring:message code="title.com.footer.copyright"/>
	</footer>

<%-- 관리자 에러페이지 END ================================================ --%>
</c:if>
<c:if test="${sysCd != 'ADM'}">
<%-- 사용자 에러페이지 START ============================================== --%>
	
	<header>
		<div class="head-wrap">
			<h1 class="logo">
				<a href="<c:url value='/${sysCdLower}/main/main.do'/>" title="HOME">
					<img src="<c:url value='/images/common/logo.jpg'/>" alt="로고">
				</a>
			</h1>
			<div class="searchBox pc" style="min-width: 450px"></div>
		</div>
		<nav class="pc box">
			<div class="navbg pc">
				<div class="dep1"></div>
				<div class="dep2"></div>
			</div>
			<div class="gnb gnb-wrap box" data-orgH="84" data-gap="" data-leftPos="">
				<ul class="topnavbar"></ul>
			</div>
		</nav>
		<div class="gnbCover mobile"></div>
		<nav class="mobile box" data-arrow="right">
			<div class="closeWrap"><a href="javascript:void(0);" class="gnbClose"></a><aside></aside></div>
			<div class="searchWrap"></div>
		</nav>
	</header>
	<div class="headerH"></div>
	
	<section class="contents">
		<div class="contents-wrap">
		
			<div class="app-error-usr">
				<div class="error-title">
					<h2>${errorTitle}</h2>
				</div>
				
				<div class="error-wrap">
					<p class="exception_message">
						${errorMessage}
						<c:if test="${ exceptionMessage != null }">
							<br>
							( <span class="exception_detail_message">${exceptionMessage}</span> )
						</c:if>
					</p>
				</div>
			</div>
		
			<div class="btnWrap type3">
				<a href="javascript:history.back();" class="blue" title="이전페이지로 이동">이전페이지로 이동</a>
			</div>
			
		</div>
	</section>

	<footer>
		<div class="footer-wrap box">
			<div class="footer-logo"><img src="<c:url value='/images/common/f_logo.jpg'/>" alt="로고"></div>
			<div class="footer-info">
				<div class="footer-info-menu">
					<a href="<c:url value='/usr/cmm/policy.do'/>">저작권정책</a>
					<a href="<c:url value='/usr/cmm/mail.do'/>">이메일무단수집거부</a>
					<a href="<c:url value='/usr/cmm/info.do'/>"><font color="3364FF" style="font-weight:bold;">개인정보처리방침</font></a>
<%-- 					<a href="<c:url value='/usr/cmm/video.do'/>">영상정보 처리기기 운영방침</a> --%>
				</div>
				<address>
					<p>
						<span><spring:message code="title.com.footer.zip"/></span>
						<span><spring:message code="title.com.footer.addr"/></span>
						<span><spring:message code="title.com.footer.tel"/></span>
					</p>
					<p class="copy"><spring:message code="title.com.footer.copyright"/></p>
				</address>
			</div>
		</div>
	</footer>

<%-- 사용자 에러페이지 END ================================================ --%>
</c:if>

</body>
</html>
