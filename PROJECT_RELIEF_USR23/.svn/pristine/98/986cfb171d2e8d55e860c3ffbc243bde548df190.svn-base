<%--
*******************************************************************************
***	명칭: modalCmitOpinion.jsp
***	설명: 온라인위원회 - 위원회 의견서 작성 팝업
***
*** -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
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

<script type="text/javascript" src="<c:url value='/js/usr/cmit/modalCmitOpinion.js'/>"></script>

<form:form commandName="form" id="popupForm" name="popupForm" method="post" onsubmit="return false;">
	<form:hidden id="p_cmitMngNo" path="cmitMngNo" />
	<form:hidden id="p_tenureNo"  path="tenureNo"  />
	<form:hidden id="p_agndNo"    path="agndNo"    />
	<form:hidden id="p_mode"      path="mode"      />
	<form:hidden id="p_wrtSeCd"   path="wrtSeCd"   />

	<div class="layerWrap">
		<!-- 레이어내용 -->
		<div class="layerPop-inner relief ">
			<div class="onlineDocWrap">
				<div class="tableWrap type8">
					<table>
						<colgroup>
							<col style="width: 40%">
							<col style="width: 60%">
						</colgroup>
						<tbody>
							<tr><th colspan="2" class="left">안건명</th></tr>
							<tr><td colspan="2"><c:out value="${form.agndNm}"/></td></tr>
							<tr><td colspan="2"><div id="appCmitFile"></div></td></tr>
						<%-- 등록/수정인 경우 -----------------------------%>
						<c:if test="${form.mode eq 'I' || form.mode eq 'U'}">
							<tr><th colspan="2" class="left">심의의견</th></tr>
							<tr><td colspan="2"><form:textarea path="dlbrCn" cssStyle="width:100%;height:200px"/></td></tr>
							<tr><th colspan="2" class="left">의결사항</th></tr>
							<tr><td><div id="appDecsnMattr" data-value="<c:out value='${form.decsnMattrCd}'/>"></div></td>
								<td><form:input path="decsnMattrResn" maxLength="60" cssClass="w100"/></td>
							</tr>
							<tr><th colspan="2" class="left">총괄 의결서 전자서명 동의</th></tr>
							<tr><td colspan="2">
									<div class="inputWrap check">
										<form:checkbox 
											path="signAgreYn" 
											value="Y" 
											label="본 안건에 대한 총괄 의결사항에 대하여 사전 전자서명 처리함을 동의합니다."/>
									</div>
								</td>
							</tr>
						</c:if>
						<%-- 조회인 경우 ----------------------------------%>
						<c:if test="${form.mode eq 'VIEW'}">
							<tr><th colspan="2" class="left">심의의견</th></tr>
							<tr><td colspan="2"><c:out value="${form.dlbrCn}"/></td></tr>
							<tr><th colspan="2" class="left">의결사항</th></tr>
							<tr><td><c:out value="${form.decsnMattrNm}"/>
									<c:if test="${not empty form.decsnMattrResn}"> (<c:out value="${form.decsnMattrResn}"/>)</c:if>
								</td>
							</tr>
							<tr><th colspan="2" class="left">총괄 의결서 전자서명 동의</th></tr>
							<tr><td colspan="2"><c:if test="${form.signAgreYn eq 'Y'}">동의완료</c:if></td></tr>
						</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<div class="btnWrap type3">
			<%-- 등록/수정인 경우 -----------------------------%>
			<c:if test="${form.mode eq 'I' || form.mode eq 'U'}">
				<a href="javascript:void(0);" id="btnTmpSave">임시저장</a>
				<a href="javascript:void(0);" id="btnSubmit">제출하기</a>
			</c:if>
				<a href="javascript:void(0);" data-dismiss="modal">닫기</a>
			</div>
		</div>
	</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
