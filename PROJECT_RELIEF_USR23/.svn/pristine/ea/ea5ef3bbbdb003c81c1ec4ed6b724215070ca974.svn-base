<%--
*******************************************************************************
***    명칭: viewReliefPape.jsp
***    설명: 마이페이지 - 구제급여현황 - 구비서류조회
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

		<!-- 구비서류조회 START -->
		<div class="relief-tab-panel">
			<%-- 신청서류목록 --%>
			<div id="papeGroup" class="usr-file-group"></div>
			
			<%-- 추가신청서류목록 (있을경우에만 표시) --%>
			<div id="papeAddGroup" class="usr-file-group"></div>
			
			<!-- 버튼 -->
			<div class="btnWrap type2 app-btn-submit app-off">
				<a href="#" id="btnSubmit" class="blue">보완제출</a>
			</div>

		<!-- 구비서류조회 END -->
		</div>
	
	</div>
</section>
<!-- //end 내용 -->

<%-- ############################# 내용 (종료) ############################# --%>
