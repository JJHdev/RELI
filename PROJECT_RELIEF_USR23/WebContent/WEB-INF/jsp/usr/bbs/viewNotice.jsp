<%--
*******************************************************************************
***    명칭: viewNotice.jsp
***    설명: [게시판] 공지사항 상세보기 사용자 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>



<section class="contents board">
	<div class="contents-wrap notice">
<form:form commandName="model" id="selectForm" name="selectForm" method="post" onsubmit="return false;">
    <form:hidden path="bbsSeCd" />
    <form:hidden path="nttNo"   />
		<div class="viewWrap type1">
			<div class="viewTit">
				<h4>
					<c:out value="${model.nttSj}" />
				</h4>
			</div>
			<div class="viewData">
				<div>
                    <%--
					<b>작성자</b>
					<div><span><c:out value="${model.rgtrNm}" /></span></div>
                    --%>
					<b>조회수</b>
					<div><span><c:out value="${model.inqCnt}" /></span></div>
					<b>등록일</b>
					<div><span><c:out value="${model.regYmd}" /></span></div>
				</div>
				<div>
					<b>첨부파일</b>
					<div><div id="attachFile"></div></div>
				</div>
			</div>
			<div class="viewInner"><c:out value="${model.nttCn}" escapeXml="false"/></div>
			<div class="btnWrap type3">
				<a href="#" id= "btnList" >목록으로</a>
			</div>
		</div>
</form:form>
	</div>
</section>
<%-- ############################# 내용 (종료) ############################# --%>