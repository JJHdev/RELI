<%--
*******************************************************************************
***    명칭: viewRelief.jsp
***    설명: 마이페이지 - 구제급여현황
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

<!-- 내용 -->
<section class="contents member">
	<div class="contents-wrap relief">

		<%-- 마이페이지 구제급여현황 탭 --%>
		<div id="appReliefTab"></div>

		<form id="selectForm" name="selectForm" method="post" onsubmit="return false;">
			<input id="mode"        name="mode"        type="hidden" value="${model.mode}"        />
			<input id="aplyOder"    name="aplyOder"    type="hidden" value="${model.aplyOder}"    /><%-- 신청차수 --%>
			<input id="papeDtySeCd" name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
		</form>

		<%-- 마이페이지 신청목록 --%>
		<div id="appAplyList"></div>
		<div class="h50"></div>

		<%-- 신청목록이 있는 경우에만 표출 --%>
		<!-- 피해구제현황 START -->
		<div class="relief-tab-panel">
		
			<h3 class="type1">피해구제 신청현황</h3>
			<%-- 피해자 정보 --%>
			<div id="reliefSufrer" class="tableWrap type4"></div>
			
			<%-- 대리인 정보 : 대리인 신청시에만 표출할것 --%>
			<div id="aplcntInfo" class="app-relief-table off">
				<div class="h20"></div>
				<div id="reliefAplcnt" class="tableWrap type4"></div>
			</div>
			
			<div class="h50"></div>
			<h3 class="type1">피해구제 진행현황</h3>
			<div class="h20"></div>
			<div id="reliefProgress" class="flow1 box">
				<%-- 진행현황 스크립트 표시 --%>
			</div>
			
		<!-- 피해구제현황 END -->
		</div>
	
	</div>
</section>
<!-- //end 내용 -->

<%-- ############################# 내용 (종료) ############################# --%>
