<%--
*******************************************************************************
***	명칭: modalBioCmitMng.jsp
***	설명: 살생물제품 위원회관리 위원등록 모달팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	2.0      2023.01.30    LSH        First Coding.
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

<script type="text/javascript" src="<c:url value='/js/adm/bio/modalBioCmitMng.js'/>"></script>
<div class="layerPop-type1 layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner formLayout">
		<!-- 검색 -->
		<form:form commandName="form" id="p_popupForm" name="popupForm" method="post" onsubmit="return false;">
			<form:hidden id="p_mode"      path="mode"/>
			<form:hidden id="p_cmitMngNo" path="cmitMngNo"/>
			<form:hidden id="p_cmitSeCd"  path="cmitSeCd" />
			<form:hidden id="p_srchMngYn" path="srchMngYn"/>
			
			<div class="boxInner type3">
				<table class="system-form w100">
					<tbody>
						<tr>
							<td>위원명</td>
							<td class="app-l"><input type="text" id="p_srchMfcmmNm" name="srchMfcmmNm" class="w100"/></td>
						</tr>
						<tr>
							<td>소  속</td>
							<td class="app-l"><input type="text" id="p_srchMfcmmOgdpNm" name="srchMfcmmOgdpNm" class="w100"/></td>
						</tr>
						<tr>
							<td>임기차수</td>
							<td class="app-l"><input type="text" id="p_srchTenureOder" name="srchTenureOder" class="w100"/></td>
						</tr>
					</tbody>
				</table>
				<div class="btnWrap type0">
					<a href="javascript:void(0);" id="btnPopupSearch" class="blue">조회</a>
				</div>
			</div>
		</form:form>

		<!-- 위원목록 -->
		<div class="app-space25"></div>
		<h4>위원 목록</h4>
		<div class="boxInner type3" style="height:300px">
			<table id="p_popupGrid"></table>
		</div>
		<div class="btnWrap type1">
			<a href="javascript:void(0);" id="btnPopupSave">선택등록</a>
			<a href="javascript:void(0);" data-dismiss="modal">취소</a>
		</div>
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
