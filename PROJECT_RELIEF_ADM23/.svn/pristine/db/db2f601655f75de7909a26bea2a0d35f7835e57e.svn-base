<%--
*******************************************************************************
***	명칭: modalReamtForm.jsp
***	설명: 사후관리(구상권) - 구상권 청구 지역 등록 팝업
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

<form:form commandName="form" id="registForm" name="registForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode"        path="mode"/>
	<form:hidden id="p_sn"          path="sn"/>
	<form:hidden id="p_reamtMngNo"  path="reamtMngNo"/>
	<div class="layerWrap">
		<div class="layerPop-inner">
			<div class="tableWrap type4">
				<table>
					<tr>
						<th>피해지역</th>
						<td><select id="p_bizAreaCd" name="bizAreaCd" style="width:209px"></select></td>
					</tr>
					<tr>
						<th>구상금 대상업체</th>
						<td><input id="p_trgtEntNm" name="trgtEntNm" maxlength="100" /></td>
					</tr>
					<tr>
						<th>구상금 납부고지일</th>
						<td><input id="p_payInfrmYmd" name="payInfrmYmd" maxlength="10"/></td>
					</tr>
					<tr>
						<th>구상금 납부최고일</th>
						<td><input id="p_pnopYmd" name="pnopYmd" maxlength="10"/></td>
					</tr>
					<tr>
						<th>구상금 납부고지액</th>
						<td><input id="p_payInfrmAmt" name="payInfrmAmt" maxlength="15"/></td>
					</tr>
					<tr>
						<th>구제급여총액</th>
						<td><input id="p_refbnfTotAmt" name="refbnfTotAmt" class="app-readonly"/></td>
					</tr>
				</table>
			</div>
			<div class="layerBtnWrap">
				<a href="javascript:void(0);" id="btnPopupSave">저장</a>
				<a href="javascript:void(0);" id="btnPopupClose">취소</a>
			</div>
		</div>
	</div>
</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
