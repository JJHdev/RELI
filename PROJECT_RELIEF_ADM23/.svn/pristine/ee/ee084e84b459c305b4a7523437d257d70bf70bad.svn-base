<%--
*******************************************************************************
***	명칭: modalSplemnt.jsp
***	설명: 구제급여 접수현황 - 보완요청 팝업 모달창
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021-10-23    LSH        First Coding.
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

<form:form commandName="form" id="splemntForm" name="splemntForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode"     path="mode"/>
	<form:hidden id="p_aplyNo"   path="aplyNo"/>
	<form:hidden id="p_aplyOder" path="aplyOder"/>
	<form:hidden path="splemntDmndSeCd"/>
	<form:hidden path="splemntDmndYmd"/>

	<div id="modalSplemntInfo"></div>

	<div class="app-space25"></div>

	<div class="formLayout box">
		<div class="formGroup col-md-12">
			<span class="col-md-2">보완기간</span>
			<div class="inputWrap col-md-10 cal app-ml0">
				<div class="cal-date">
					<form:input path="splemntTermYmd" maxlength="10" />
				</div>
			</div>
		</div>
		<div class="formGroup col-md-12">
			<span class="col-md-2">발신번호</span>
			<div class="inputWrap col-md-10">
				<form:input id="p_trnsterNo" path="trnsterNo" maxlength="13" cssClass="w100" />
			</div>
		</div>
		<div class="formGroup col-md-12">
			<span class="col-md-2">요청내용</span>
			<div class="inputWrap col-md-10">
				<form:textarea path="splemntDmndCn" maxlength="650" cssClass="w100" cssStyle="min-height:130px;"/>
			</div>
		</div>
	</div>
	<div class="app-space25"></div>
	
	<h4 class="col-md-12">제출 서류 목록</h4>
	<div class="app-space25"></div>
	<div id="modalSplemntList"></div>

	<div class="app-space25"></div>

	<div class="layerBtnWrap right">
		<a href="javascript:void(0);" id="btnSplemntClose" class="left">취소</a>
		<a href="javascript:void(0);" id="btnSplemntSave">보완요청</a>
		<a href="javascript:void(0);" id="btnSplemntPrint" class="green">공문양식 프린트</a>
	</div>

</form:form>
			
<%-- ############################# 내용 (종료) ############################# --%>
