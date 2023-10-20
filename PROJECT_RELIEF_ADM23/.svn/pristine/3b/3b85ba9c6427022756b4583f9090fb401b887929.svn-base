<%--
*******************************************************************************
***	명칭: modalMfcmmTenure.jsp
***	설명: 위원관리 - 임기이력조회 모달팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	2.0      2023.01.11    LSH        First Coding.
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

<script type="text/javascript" src="<c:url value='/js/adm/cmit/modalMfcmmTenure.js'/>"></script>

<form:form commandName="form" id="p_tenureForm" name="tenureForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode"      path="mode"/>
	<form:hidden id="p_mfcmmNo"   path="mfcmmNo"/>

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner formLayout">
		<!-- 위원정보 -->
		<div id="p_mfcmmInfo"></div>
		<!-- 위원회 임기이력 -->
		<div id="p_tenureGrid"></div>

		<div class="btnWrap type3">
			<a href="javascript:void(0);" data-dismiss="modal">확인</a>
		</div>
	</div>
</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
