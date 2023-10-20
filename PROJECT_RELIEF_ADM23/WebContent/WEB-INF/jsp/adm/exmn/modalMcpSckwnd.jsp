<%--
*******************************************************************************
***	명칭: modalMcpSckwnd.jsp
***	설명: 본조사 - 의료비 - 세부의료비내역 팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2022.12.09    LSH        First Coding.
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
<form:form commandName="form" id="p_mcpDtlsForm" name="mcpDtlsForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode"      path="mode"/>
	<form:hidden id="p_bizAreaCd" path="bizAreaCd"/>
	<form:hidden id="p_bizOder"   path="bizOder"/>
	<form:hidden id="p_exmnOder"  path="exmnOder"/>
	<form:hidden id="p_aplyNo"    path="aplyNo"/>
	<form:hidden id="p_sckwndCd"  path="sckwndCd"/>
	<div class="app-view">
		<div>
			<b>상병코드</b>
			<div><span id="s_sckwndCd"><c:out value="${form.sckwndCd}"/></span></div>
		</div>
		<div>
			<b>상병명</b>
			<div><span id="s_sckwndNm"><c:out value="${form.sckwndNm}"/></span></div>
		</div>
		<div>
			<b>의료비 총액</b>
			<div><span id="s_selfAlotmTot"><fmt:formatNumber type="number" value="${form.mcpTotAmt}"/> 원</span></div>
		</div>
	</div>
</form:form>
	
<div class="app-space25"></div>
<div style="width:800px;height:300px">
	<table id="p_mcpDtlsGrid"></table>
</div>
<div class="btnWrap type3">
	<a href="javascript:void(0);" id="btnMcpDtlsDown">엑셀다운로드</a>
</div>


<%-- ############################# 내용 (종료) ############################# --%>
