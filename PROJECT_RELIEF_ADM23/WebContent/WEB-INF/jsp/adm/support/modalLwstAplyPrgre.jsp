<%--
*******************************************************************************
***	명칭: modalLwstIncdnt.jsp
***	설명: 소송개요현황 - 소송추가팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021.12.02    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>

<form id="popupForm" name="popupForm" method="post" onsubmit="return false;">
	<input id="p_mode" name="mode" type="hidden" value="${form.mode}" />
	<input id="p_rgtrNo" name="rgtrNo" type="hidden" value="${form.rgtrNo}" />
	<input id="p_aplyNo" name="aplyNo" type="hidden" value="${form.aplyNo}" />
	<input id="p_incdntMngNo" name="incdntMngNo" type="hidden" value="${form.incdntMngNo}" />
	<h4 align="center">소송 개요</h4>
	<div class="formType1 tableWrap type3">
		<table class="system-form">
			<tr>
				<td class="must">사건 번호</td>
				<td>
					<select id="p_incdntNo" name="incdntNo" style="width: 190px;"></select>
					<input  id="p_incdntNm" name="incdntNm"/>
				</td>
			</tr>
		</table>
	</div>
	<div style="height: 40px;"></div>
	<div style="width: 500px; height: 300px">
		<!-- 향후기일 그리드 -->
		<table id="p_popupLwstGrid"></table>
	</div>
	<div style="height: 20px;"></div>
	<div class="btnWrap type1">
		<a href="javascript:void(0);" id="btnSaveAplyLwst">저장</a>
		<a href="javascript:void(0);" id="btnCancelLwst">취소</a>
	</div>
</form>

<%-- ############################# 내용 (종료) ############################# --%>
