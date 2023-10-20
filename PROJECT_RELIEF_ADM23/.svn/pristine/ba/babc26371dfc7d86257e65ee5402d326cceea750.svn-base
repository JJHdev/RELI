<%--
*******************************************************************************
***    명칭: modifyBbs.jsp
***    설명: [게시판] 관리자 공통 게시판 수정 
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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




<div class="viewWrap type1">
	<form:form commandName="model" id="model1" name="model1" method="post" onsubmit="return false;">
			<form:hidden path="nttNo" id='nttNo' name ='nttNo' />
			<form:hidden path="bbsSeCd" id='bbsSeCd' name='bbsSeCd' />
		<div class="viewData">
			<div>
				<b>제목</b>
				<div>
					<span>
					<form:input path="nttSj" id='nttSj' name='nttSj'/>
					</span>
					<div class="eheck_font" id="nttSj_check"></div>
				</div>

			</div>

			<div>
				<b>작성자</b>
				<div>
					<span>관리자</span>
				</div>
			</div>
			<div>
				<b>조회수</b>
				<div>
					<span>
					<c:out value='${model.inqCnt}'/>
					</span>
				</div>
			</div>
			<div class="file">
				<b>첨부파일</b>
				<div>
					<span><input type="file" value="" /></span> 
					<span><input type="file" value="" /></span> 
					<span><input type="file" value="" /></span>
				</div>
			</div>
		</div>
		<div class="viewTextarea">
			<form:textarea path="nttCn" id='nttCn' name='nttCn'/>
		</div>
		<div class="btnWrap type3">
			<a id='btnBbsBack'>취소하기</a>
		    <a id='btnBbsModify'>저장하기</a>

		</div>
	</form:form>
</div>
