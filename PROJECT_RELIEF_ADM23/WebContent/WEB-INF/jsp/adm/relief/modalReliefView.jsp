<%--
*******************************************************************************
***	명칭: modalReliefView.jsp
***	설명: 종합현황 - 개인별 상세기록카드 조회팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2022.12.14    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<%-- ############################# 내용 (시작) ############################# --%>

<script type="text/javascript" src="<c:url value='/js/adm/relief/modalReliefView.js'/>"></script>

<form:form commandName="form" id="popupForm" name="popupForm" method="post" onsubmit="return false;">
<input id="p_mode"        name="mode"        type="hidden" value="${form.mode}"        />
<input id="p_gsUserNo"    name="gsUserNo"    type="hidden" value="${form.gsUserNo}"    />
<input id="p_idntfcId"    name="idntfcId"    type="hidden" value="${form.idntfcId}"    /><%-- 식별ID --%>

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner formLayout">
	
		<!-- 피해자 개요 -->
		<div id="appSufrerInfo"></div>

		<!-- 피해구제 신청 및 인정현황(목록) -->
		<div id="appReliefList"></div>
	
		<!-- 건강피해 인정현황(목록) -->
		<div id="appMcpSummary"></div>
	
		<!-- 건강피해 상세현황(목록) -->
		<div id="appMcpDtls"></div>
	
		<!-- 건강피해 영향범위 및 거주기간 -->
		<div id="appExamine"></div>
	
		<!-- 구제급여 지급현황 -->
		<div id="appReliefGive"></div>
	
		<!-- 민원응대 이력 -->
		<div id="appCmplHst"></div>
	
		<!-- 피해등급 -->
		<div id="appDmgeGrd"></div>
		
		<div class="btnWrap type3">
			<a href="javascript:void(0);" data-dismiss="modal">확인</a>
			<a href="javascript:void(0);" id="btnPrint">인쇄하기</a>
		</div>
	</div>
</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
