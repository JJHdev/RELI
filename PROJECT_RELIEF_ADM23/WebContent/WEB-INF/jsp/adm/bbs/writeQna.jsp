<%--
*******************************************************************************
***    명칭: viewQna.jsp
***    설명: [게시판] QNA 등록 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
*******************************************************************************
--%>
<%@ page language="java"     contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"    uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c"        uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"      uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app"      uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f"        uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn"       uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring"   uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"     uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"      uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>

<div class="viewWrap type1">
	    <%-- 질문폼 --%>

		<div class="viewTit">
			<h4><c:out value="${question.nttSj}" /></h4>
		</div>
	
		<div class="viewData">
			<div>
			    <b>작성자</b>
			    <div><span><c:out value="${question.rgtrNm}" /></span></div>
			    <b>등록일</b>
			    <div><span><c:out value="${question.regYmd}" /></span></div>
			    <b>상태</b>
			    <c:choose>
				    <c:when test="${empty question.status || question.status== null}">
				        <div><span>대기중</span></div>
				     </c:when>
				    <c:otherwise>
				        <div><span>답변완료</span></div>
				    </c:otherwise>
			    </c:choose>
			</div>
		</div>
		
		<div class="viewInner"><pre class="app-editor"><c:out value="${question.nttCn}"/></pre></div>
		<div class="line50 dotted"></div>
		
		
		<%-- 답변폼 --%>
		<form:form commandName="answer" id="answerForm" name="answerForm" method="post" onsubmit="return false;">
		<form:hidden path="bbsSeCd" />
		<form:hidden path="nttNo" id = "nttNoA" />
		<form:hidden path="status" id="statusA" value="${question.status}" />
		
		<h3 class="board-tit">관리자 답변</h3>
		
	    <div class="viewTextarea"><form:textarea path="nttCn"/></div>
		<div class="btnWrap type3">
			<a href="#" id="btnList">목록으로</a> 
			<a href="#" id="btnSave">수정완료</a> 
		</div>
		</form:form>
	
</div>
