<%--
*******************************************************************************
***	명칭: modalBioReceipt.jsp
***	설명: 살생물제품 구제급여접수 신청접수 팝업 모달창
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	2.0      2023.01.25    LSH        First Coding.
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

<form:form commandName="form" id="receiptForm" name="receiptForm" method="post" onsubmit="return false;">

	<div id="modalReceiptInfo"></div>
	
	<div class="app-space25"></div>

	<div class="formLayout box">
		<div class="formGroup col-md-12">
			<span class="col-md-2">접수일자</span>
			<div class="inputWrap col-md-4">
				<div style="width:100%"><input id="p_rcptYmd" name="rcptYmd" style="width:100%" value="${form.rcptYmd}"/></div>
			</div>
		</div>
		<div class="formGroup col-md-12">
			<span class="col-md-2">발신번호</span>
			<div class="inputWrap col-md-4">
				<form:input id="trnsterNo" path="trnsterNo" maxlength="13" cssClass="w100" />
			</div>
		</div>
		<div class="formGroup col-md-12">
			<span class="col-md-2">발신내용</span>
			<div class="inputWrap col-md-10">
				<form:textarea id="trsmCn" path="trsmCn" rows="5" cols="50" cssClass="w100" cssStyle="min-height:130px;" maxlength="500" />
			</div>
		</div>
	</div>

	<div class="layerBtnWrap">
		<a href="javascript:void(0);" id="btnReceiptSave">확인</a>
		<a href="javascript:void(0);" id="btnReceiptClose">취소</a>
	</div>
	
</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
