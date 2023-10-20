<%--
*******************************************************************************
***	명칭: modalMngHst.jsp
***	설명: 이력등록/조회 팝업 모달창
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

<form:form commandName="form" id="historyForm" name="historyForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode"    path="mode" />
	<form:hidden id="p_aplyNo"  path="aplyNo"/>
	<form:hidden id="p_sn"      path="sn"/>
	<form:hidden id="p_dtySeCd" path="dtySeCd"/>
	<form:hidden id="p_hstSeCd" path="hstSeCd"/>

	<div class="formLayout box">
		<div class="formGroup col-md-12">
			<span class="col-md-2">작성자</span>
			<div class="inputWrap col-md-10">
				<div id="p_rgtrNm" class="app-box"><c:out value="${form.rgtrNm}"/></div>
			</div>
		</div>
		<div class="formGroup col-md-12">
			<span class="col-md-2">작성날짜</span>
			<div class="inputWrap col-md-10">
				<div id="p_regDate" class="app-box"><c:out value="${form.regDate}"/></div>
			</div>
		</div>
		<div class="formGroup col-md-12">
			<span class="col-md-2">내용</span>
			<div class="inputWrap col-md-10">
				<form:textarea id="p_hstCn" path="hstCn" maxlength="650" cssClass="w100" cssStyle="min-height:133px;" />
				<div id="s_hstCn" class="w100 app-box" style="min-height:200px"><c:out value="${form.hstCn}"/></div>
			</div>
		</div>
	</div>

	<div class="layerBtnWrap">
		<a href="javascript:void(0);" id="btnMngHstSave"  >확인</a>
		<a href="javascript:void(0);" id="btnMngHstClose" >취소</a>
	</div>
	
</form:form>


<%-- ############################# 내용 (종료) ############################# --%>
