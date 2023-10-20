<%--
*******************************************************************************
***    명칭: modifyNotice.jsp
***    설명: [게시판] 공지사항 수정 관리자 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
***    2.0      2022.11.24    JDY     	    Second Coding.
*******************************************************************************
--%>
<%@ page language="java"   contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>
<div class="viewWrap type1 formLayout">

	<form:form commandName="form" id="registForm" name="registForm" method="post" enctype="multipart/form-data" onsubmit="return false;">
		<form:hidden path="nttNo"   /><%-- 게시물번호 --%>
		<form:hidden path="bbsSeCd" /><%-- 게시물구분 --%>
	    <form:hidden path="mode"    /><%-- 처리모드 --%>
		<div class="viewData1">
			<div>
				<b class="must">제목</b>
				<div><span><form:input path="nttSj" maxlength="100" /></span></div>
			</div>
			<div>
				<b class="must">작성자</b>
				<div><span><form:input path="rgtrNm" readonly="true"/></span></div>
			</div>
			<div>
				<b>팝업여부</b>
				<div><form:checkbox path="popupYn" value="Y"/><span style="padding: 5px;">팝업</span></div>
			</div>
		 	<div class="cal">
				<b>게시기간</b>
				<div class="cal-date" >
					<input id="pstgBgngYmd" name="pstgBgngYmd" class="easyui-datebox" value="${form.pstgBgngYmd}" />					
					<span style="padding: 0 5px;">~</span> 
					<input id="pstgEndYmd"  name="pstgEndYmd" class="easyui-datebox" value="${form.pstgEndYmd}" />					
				</div>
			</div>  
	 		<div>
				<b>팝업창 가로</b>
				<span><form:input path="popupSqr" cssStyle="width:100px;text-align:right;"/></span>
				<b>팝업창 세로</b>
				<span><form:input path="popupHght" cssStyle="width:100px;text-align:right;"/></span>
			</div>
			<div>
				<b>첨부파일</b>
				<div><div id="attachFile"></div></div>
			</div>
		</div>
		<div class="viewTextarea"><form:textarea path="nttCn"/></div>
	</form:form>

	<div class="btnWrap type3">
		<a href="#" id="btnList">목록으로</a> 
		<a href="#" id="btnSave">수정</a>
	</div>
	<form:form commandName="form" id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<form:hidden path="page"    />
		<form:hidden path="bbsSeCd" id="s_bbsSeCd"/>
		<form:hidden path="srchType"/>
		<form:hidden path="srchText"/>
	</form:form>

</div>