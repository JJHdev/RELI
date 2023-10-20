<%--
*******************************************************************************
***    명칭: openReliefInfo.jsp
***    설명: 환경오염 피해구제란
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    한금주        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>


<!-- 내용 -->
<section class="contents intro">
	<div class="introWrap">
		<div class="tabWrap type4">
			<ul class="box">
				<li class="on">
					<a href="<c:url value='/usr/info/openProcessRelief.do'/>">구제급여 신청 절차</a>
				</li>
				<li>
					<a href="<c:url value='/usr/info/openProcessLwst.do'/>">취약계층 소송지원 신청 절차</a>
				</li>
			</ul>
		</div>

		<div class="intro-find" align="center">
			<img src="<c:url value='/images/intro/reliefProcess.png'/>" alt="" />
		</div>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>

