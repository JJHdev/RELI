<%--
*******************************************************************************
***	명칭: modalSms.jsp
***	설명: SMS 내용 상세보기 팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2022.01.19    LSH        First Coding.
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

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner relief ">
		<div class="onlineDocWrap">
			<div class="tableWrap type5">
				<table>
					<colgroup>
						<col width="25%" >
						<col width="25%" >
						<col width="25%" >
						<col width="25%" >
					</colgroup>
					<tr>
						<th>SMS 구분</th>
						<td><c:out value="${form.smsSeNm}"/></td>
						<th>장문구분</th>
						<td><c:out value="${form.lngtSeNm}"/></td>
					</tr>
					<tr>
						<th>송신자명</th>
						<td><c:out value="${form.trnsterNm}"/></td>
						<th>송신번호</th>
						<td><c:out value="${form.trnsterNo}"/></td>
					</tr>
					<tr>
						<th>수신자명</th>
						<td><c:out value="${form.rcvrNm}"/></td>
						<th>수신번호</th>
						<td><c:out value="${form.rcvrNo}"/></td>
					</tr>
					<tr>
						<th>전송일시</th>
						<td colspan="3"><c:out value="${form.trsmDt}"/></td>
					</tr>
					<tr>
						<th>전송내용</th>
						<td colspan="3">
							<div class="app-sms-cn">
								<img src="<c:url value='/sys/sms/linkSmsLogo.do'/>" width="100%"/>
								<pre class="app-editor"><c:out value="${form.trsmCn}"/></pre>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div style="height:20px"></div>
		</div>
		<div class="btnWrap type3">
			<a href="javascript:void(0);" data-dismiss="modal">확인</a>
		</div>
	</div>
</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
