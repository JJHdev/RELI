<%--
*******************************************************************************
***	명칭: modalPrptExmn.jsp
***	설명: 예비조사 계획 - 대상자 등록 팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021.11.19    LSH        First Coding.
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
<form:form commandName="form" id="targetForm" name="targetForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode"      path="mode"/>
	<form:hidden id="p_bizAreaCd" path="bizAreaCd"/>
	<form:hidden id="p_bizOder"   path="bizOder"/>
	<form:hidden id="p_exmnOder"  path="exmnOder"/>
	<input       id="p_srchType"  name="srchType" type="hidden" />

	<div class="app-view">
		<div>
			<b>접수기간</b>
			<div>
				<input type="text" id="srchRcptStdt" name="srchRcptStdt" style="width:150px"/> ~
				<input type="text" id="srchRcptEndt" name="srchRcptEndt" style="width:150px" />
			</div>
		</div>
		<div>
			<b>식별ID</b>
			<div>
				<input type="text" id="srchIdntfcId" name="srchIdntfcId" />
			</div>
		</div>
		<div>
			<b>피해지역</b>
			<div><span id="s_bizAreaNm"><c:out value="${form.bizAreaNm}"/></span></div>
			<b>사업차수</b>
			<div><span id="s_bizOderNm"><c:out value="${form.bizOderNm}"/></span></div>
			<b>예비조사차수</b>
			<div><span id="s_exmnOder"><c:out value="${form.exmnOder}"/></span></div>
		</div>
	</div>
	<div class="btnWrap type1">
		<a href="javascript:void(0);" id="btnTargetSearch" class="blue">조회</a>
	</div>
</form:form>
	
<div class="app-space25"></div>
<h4 class="col-md-12">대상자 목록</h4>
<div class="app-space25"></div>
<div style="width:800px;height:300px">
	<table id="p_targetGrid"></table>
</div>
<div class="btnWrap type3">
	<a href="javascript:void(0);" id="btnTargetSave">선택등록</a>
</div>


<%-- ############################# 내용 (종료) ############################# --%>
