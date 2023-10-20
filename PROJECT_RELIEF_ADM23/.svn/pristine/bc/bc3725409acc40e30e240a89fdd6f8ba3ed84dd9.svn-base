<%--
*******************************************************************************
***    명칭: viewDissMcp.jsp
***    설명: 인정질환별 의료비 현황 조회 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.12.20    LSH        First Coding.
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

<div class="btnWrap type1">
	<app:button id="btnExcel" title="엑셀"/>
</div>

<div class="app-space25">
	<p class="app-right">('<c:out value="${model.basisDateString }"/> 기준, 단위: 명)</p>
</div>
<div class="app-both"></div>
<div class="tableGroup customRowHight" style="height:600px">
	<table id="appGrid"></table>
</div>


<%-- ############################# 내용 (종료) ############################# --%>
