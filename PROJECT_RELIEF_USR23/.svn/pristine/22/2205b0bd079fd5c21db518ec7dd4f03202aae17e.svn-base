<%--
*******************************************************************************
***    명칭: openRelief.jsp
***    설명: 구제급여 신청 1단계
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
*******************************************************************************
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

<%-- ############################# 내용 (시작) ############################# --%>

<c:set var="txtAdmin" value="" />

<c:if test="${model.userInfo.admin}">
    <c:set var="txtAdmin" value=" <span style='font-size:20px; color:blue; margin-left:10px;'>(관리자대행)</span> " />
</c:if>

<section class="contents apply">
	<div class="contents-wrap relief">
		<div class="relief-select">
			<p>해당사항을 선택하여 <span>구제급여를 신청하세요.</span></p>
			<a href="#" data-code="R01" id="btnSufrerSlf" class="victim hoverColor2">피해자 본인 신청 <c:out value="${txtAdmin}" escapeXml="false"/></a>
			<!-- ntarget : 2023-10-04 임시 숨김 
            <a href="#" data-code="R02" id="btnAgncyLv"   class="agent hoverColor2">대리인 신청 <span class="survival">피해자 생존</span><c:out value="${txtAdmin}" escapeXml="false"/></a> 
            -->
			<a href="#" data-code="R03" id="btnAgncyDth"  class="agent hoverColor2">대리인 신청 <span class="death">피해자 사망</span><c:out value="${txtAdmin}" escapeXml="false"/></a>
		</div>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
