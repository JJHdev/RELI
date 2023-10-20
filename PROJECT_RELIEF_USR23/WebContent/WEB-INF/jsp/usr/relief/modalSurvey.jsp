<%--
*******************************************************************************
***	명칭: modalSurvey.jsp
***	설명: 구제급여신청 - 온라인설문지
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021.12.27    LSH        First Coding.
***	1.0      2022.01.21    LSH        수정기능 추가
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

<script type="text/javascript" src="<c:url value='/js/usr/relief/modalSurvey.js'/>"></script>

<form:form commandName="form" id="surveyForm" name="surveyForm" method="post" onsubmit="return false;">
<form:hidden id="p_qstnnMngNo" path="qstnnMngNo" />
<form:hidden id="p_signCn"     path="signCn"     />
<form:hidden id="p_mode"       path="mode"       />
<form:hidden id="p_rspnsMngNo" path="rspnsMngNo" />

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner relief ">
		<div class="onlineDocWrap">
			<div class="tableWrap type5">
				<table>
					<colgroup>
						<col width="50%" >
						<col width="50%" >
					</colgroup>
					<tr>
						<th>피해 신청인 성명</th>
						<td><span id="p_rgtrNm"><c:out value="${form.rgtrNm}"/></span></td>
					</tr>
				</table>
			</div>
			<div style="height:20px"></div>
			
			<div class="tableWrap type8">
				<table id="appSurveyTable">
					<colgroup>
						<col style="width:22%"/>
						<col style="width:36%"/>
						<col style="width:20%"/>
						<col style="width:22%"/>
					</colgroup>
				</table> 
			</div>
			
			<div class="signArea type1">
				<p>상기 답변사항은 사실과 다름이 없으며 이에 서명 날인합니다.</p>
				<p>※ 상기 내용이 사실에 부합함을 확인하며, 
				     사실과 다른 내용을 제출하여 구제급여를 부당하게 지급받은 경우에는 
				     부당이득 환수 등의 불이익 처분을 받음에 이의가 없음을 확인합니다.
				</p>
				<p id="p_regDate">
					<span><c:out value="${form.rgtrYear}"/> 년</span>
					<span style="margin-left:5px"><c:out value="${form.rgtrMonth}"/> 월</span>
					<span style="margin-left:5px"><c:out value="${form.rgtrDay}"/> 일</span>
				</p>
				<p class="right">
					성명 : <span id="p_signNm"><c:out value="${form.rgtrNm}"/></span>
					<a href="javascript:void(0);" class="sign" id="btnSurveySign">전자서명</a>
				</p>
			</div>
		</div>
		<div class="btnWrap type3">
			<a href="javascript:void(0);" id="btnSurveySubmit">제출</a>
			<a href="javascript:void(0);" data-dismiss="modal">취소</a>
		</div>
	</div>
</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
