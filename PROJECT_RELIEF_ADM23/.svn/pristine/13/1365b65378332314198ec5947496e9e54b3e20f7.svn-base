<%--
*******************************************************************************
***    명칭: viewQna.jsp
***    설명: [게시판] QNA 관리자 화면
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
	<form:form commandName="question" id="selectForm" name="selectForm" method="post" onsubmit="return false;">
    <form:hidden path="bbsSeCd" />
    <form:hidden path="nttNo"   />
    <form:hidden path="status"/>
    <h3 class="board-tit">사용자 문의</h3>
	<br>    
    
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
	<div class="viewInner"><pre class="app-editor"><c:out value="${question.nttCn}" /></pre></div>
	
     <!-- 관리자 답변  -->
	<div class="line50 dotted"></div>
	<h3 class="board-tit">관리자 답변</h3>
	<div class="viewInner"><c:out value="${answer.nttCn}" escapeXml="false"/></div>
	<div class="btnWrap type3">
			<a href="#" id="btnList"  >목록으로</a> 
			<a href="#" id="btnModify">수정하기</a> 
			<a href="#" id="btnRemove">삭제하기</a>
	</div>
	</form:form>
</div>


