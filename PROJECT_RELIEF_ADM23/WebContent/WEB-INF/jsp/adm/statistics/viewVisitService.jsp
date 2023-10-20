<%--
*******************************************************************************
***    명칭: viewVisitService.jsp
***    설명: 찾아가는 서비스 통계
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
***    1.0      2021.11.11    LSH        화면개발
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

<div class="statWrap">
	<div class="tabWrap type4">
		<ul class="li-2 box">
			<li data-mode="YEAR"><a href="#">연도별실적</a></li>
			<li data-mode="RATE"><a href="#">서비스제공율</a></li>
		</ul>
	</div>
	<div class="tabInner">
		<ul>
			<li class="tabInnerFrom" style="padding: 20px;">
				<div class="formLayout app-search-layout">
					<form id="searchYearForm" name="searchYearForm" method="post" onsubmit="return false;">
						<input name="mode" type="hidden"/>
						<div class="formGroup">
							<div class="inputWrap">
								<select id="srchStYearY" name="srchStYear" style="width:100px"></select>
								~
								<select id="srchEnYearY" name="srchEnYear" style="width:100px"></select>
							</div>
						</div>
					</form>
				</div>
				<div class="btnWrap type1">
	                <app:button id="btnYearSearch" title="검색" cls="app-m3 blue" />
	                <app:button id="btnYearExcel"  title="엑셀" cls="app-m3" />
				</div>
				<div class="tableGroup">
					<p class="app-right">(단위: 건,명)</p>
				</div>
				<div class="app-both"></div>
				<div class="tableGroup" style="height:600px">
					<table id="yearGrid"></table>
				</div>
			</li>
			<li class="tabInnerFrom" style="padding: 20px;">
				<div class="formLayout app-search-layout">
					<form id="searchRateForm" name="searchRateForm" method="post" onsubmit="return false;">
						<input name="mode" type="hidden"/>
						<div class="formGroup">
							<div class="inputWrap">
								<select id="srchStYearR" name="srchStYear" style="width:100px"></select>
								~
								<select id="srchEnYearR" name="srchEnYear" style="width:100px"></select>
							</div>
						</div>
					</form>
				</div>
				<div class="btnWrap type1">
	                <app:button id="btnRateSearch" title="검색" cls="app-m3 blue" />
	                <app:button id="btnRateExcel"  title="엑셀" cls="app-m3" />
				</div>
				<div class="app-space25">
					<p class="app-right">(단위: %,명)</p>
				</div>
				<div class="app-both"></div>
				<div class="tableGroup" style="height:600px">
					<table id="rateGrid"></table>
				</div>
			</li>
		</ul>
	</div>
</div>
<%-- ############################# 내용 (종료) ############################# --%>
