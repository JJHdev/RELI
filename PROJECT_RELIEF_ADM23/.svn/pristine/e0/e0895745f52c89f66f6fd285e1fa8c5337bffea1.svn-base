<%--
*******************************************************************************
***	명칭: modalReamtView.jsp
***	설명: 사후관리(구상권) - 구제급여 상세내역 팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021.12.16    LSH        First Coding.
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

<form:form commandName="form" id="popupForm" name="popupForm" method="post" onsubmit="return false;">
	<form:hidden id="p_sn"          path="sn"/>
	<form:hidden id="p_reamtMngNo"  path="reamtMngNo"/>
	<div class="layerWrap">
		<div class="layerPop-inner formType1">
			<div class="tableWrap type4">
				<table>
					<tr>
						<th colspan="2">피해지역</th>
						<td><div class="app-box" id="p_bizAreaNm"><c:out value="${form.bizAreaNm}"/></div></td>
					</tr>
					<tr>
						<th colspan="2">구상금 대상업체</th>
						<td><div class="app-box" id="p_trgtEntNm"><c:out value="${form.trgtEntNm}"/></div></td>
					</tr>
					<tr>
						<th colspan="2">구상금 납부고지일</th>
						<td><div class="app-box" id="p_payInfrmYmd"><c:out value="${form.payInfrmYmd}"/></div></td>
					</tr>
					<tr>
						<th rowspan="5">구제급여</th>
						<th>의료비</th>
						<td><div class="app-box app-r" id="p_mcpAmt"><c:out value="${form.mcpAmt}"/></div></td>
					</tr>
					<tr>
						<th>요양생활수당</th>
						<td><div class="app-box app-r" id="p_rcperAmt"><c:out value="${form.rcperAmt}"/></div></td>
					</tr>
					<tr>
						<th>장의비</th>
						<td><div class="app-box app-r" id="p_fnrlAmt"><c:out value="${form.fnrlAmt}"/></div></td>
					</tr>
					<tr>
						<th>유족보상비</th>
						<td><div class="app-box app-r" id="p_brvfmAmt"><c:out value="${form.brvfmAmt}"/></div></td>
					</tr>
					<tr>
						<th>재산피해보상금</th>
						<td><div class="app-box app-r" id="p_prprtyAmt"><c:out value="${form.prprtyAmt}"/></div></td>
					</tr>
					<tr>
						<th colspan="2">구제급여 총액 (A)</th>
						<td><div class="app-box app-r" id="p_refbnfTotAmt"><c:out value="${form.refbnfTotAmt}"/></div></td>
					</tr>
					<tr>
						<th colspan="2">구상금 납부 고지액 (B)</th>
						<td><div class="app-box app-r" id="p_payInfrmAmt"><c:out value="${form.payInfrmAmt}"/></div></td>
					</tr>
					<tr>
						<th colspan="2">차이 (A-B)</th>
						<td><div class="app-box app-r" id="p_refbnfDiffAmt"><c:out value="${form.refbnfDiffAmt}"/></div></td>
					</tr>
				</table>
			</div>
			<div class="layerBtnWrap">
				<a href="javascript:void(0);" id="btnPopupClose">확인</a>
			</div>
		</div>
	</div>
</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
		