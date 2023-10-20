<%--
*******************************************************************************
***    명칭: viewDissRcogn.jsp
***    설명: 인정질환별 인정자 현황 조회 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
***    1.0      2021.11.10    LSH        파일명변경 및 화면개발
***    1.0      2021.11.11    LSH        JUI Column Chart 적용
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

<input type='hidden' id='basisDateString' value='<c:out value="${model.basisDateString }"/>'/>

<!-- Chart.js Chart Plugin -->
<c:set var="userAgent" value="${header['User-Agent']}" />
<c:choose>
	<c:when test='${userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0}'>
		<script src="<c:url value='/plugins/charts/chartjs-2.9.3/chart.js'/>"></script>
		<script src="<c:url value='/plugins/charts/chartjs-2.9.3/chartjs-plugin-datalabels.js'/>"></script>
		<script> const CHARTJS_VERSION = 2.9; </script>
	</c:when>
	<c:otherwise>
		<script src="<c:url value='/plugins/charts/chartjs-3.5.0/chart.js'/>"></script>
		<script src="<c:url value='/plugins/charts/chartjs-3.5.0/datalabels/chartjs-plugin-datalabels.js'/>"></script>
		<script> const CHARTJS_VERSION = 3.5; </script>
	</c:otherwise>
</c:choose>
<script src="<c:url value='/js/com/comm_chart.js'/>"></script>

<div class="formLayout app-search-layout">
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<input name="mode" type="hidden"/>
		<div class="formGroup">
			<div class="inputWrap">
				<select id="srchStYear" name="srchStYear" style="width:100px"></select>
				~
				<select id="srchEnYear" name="srchEnYear" style="width:100px"></select>
			</div>
			<a href="#" class="btn blue" id="btnSearch">검색</a>
		</div>
	</form>
</div>

<div class="statWrap"></div>

<%-- ############################# 내용 (종료) ############################# --%>
