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

<form:form commandName="form" id="popupForm" name="popupForm" method="post" onsubmit="return false;">
	<form:hidden id="p_mode" path="mode" />
	<form:hidden id="p_rgtrNo" path="rgtrNo" />
	<form:hidden id="p_incdntMngNo" path="incdntMngNo" />
	<h4>소송 개요</h4>
	<div class="boxInner type2 formLayout">
		<table class="system-form" style="margin-left: 50px;">
			<tr>
				<td class="must">사건 번호</td>
				<td>
					<form:input id="p_incdntNo" path="incdntNo" placeholder="사건 번호를 입력해주세요" />
				</td>
				<td class="must">사건 명</td>
				<td>
					<form:input id="p_incdntNm" path="incdntNm" placeholder="사건 명를 입력해주세요" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td class="must">소가</td>
				<td>
					<form:input id="p_lwstPricesAmt" onKeyUp="removeChar(event);inputNumberFormat(this);" path="lwstPricesAmt" maxlength="22" placeholder="소가를 입력해주세요" />
				</td>
				<td class="must">인지액</td>
				<td>
					<form:input id="p_papstmpAmt" onKeyUp="removeChar(event);inputNumberFormat(this);" path="papstmpAmt" maxlength="22" placeholder="인지액를 입력해주세요" />
				</td>
			</tr>
			<tr>
				<td class="must">소송일자</td>
				<td>
					<form:hidden id="p_lwstYmd" path="lwstYmd" />
					<select id="p_lwstYmd1" style="width: 90px"></select>
					<select id="p_lwstYmd2" style="width: 75px"></select>
					<select id="p_lwstYmd3" style="width: 75px"></select>
				</td>
				<td class="must">재판부</td>
				<td>
					<form:input id="p_jdgmtDeptNm" path="jdgmtDeptNm" placeholder="재판부를 입력해주세요" maxlength="100"/>
				</td>
			</tr>
		</table>

	</div>
	<div style="height: 40px;"></div>
	<h4>향후 기일</h4>
	<div style="width: 800px; height: 300px">
		<!-- 향후기일 그리드 -->
		<table id="p_popupGrid"></table>
	</div>
	<div style="height: 20px;"></div>
	<div class="btnWrap type1">
		<a href="javascript:void(0);" id="btnSaveLwst">저장</a>
		<a href="javascript:void(0);" id="btnCancelLwst">취소</a>
	</div>
</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
