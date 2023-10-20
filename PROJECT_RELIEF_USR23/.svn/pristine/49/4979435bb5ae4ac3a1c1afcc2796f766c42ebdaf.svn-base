<%--
*******************************************************************************
***    명칭: listBbs.jsp
***    설명: [게시판] 질의응답 화면
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


<!-- 내용 -->
<section class="contents board">
		<div class="contents-wrap notice">
		
		<!-- 자주하는질문 START // -->
			<h3 class="board-tit pc">자주하는 질문</h3>

			<!-- 검색 -->
			<div class="searchWrap type1 box">
				<%-- 자주하는질문 검색폼 설정 --%>
				<form id="faqForm" name="faqForm" method="post" onsubmit="return false;">
					<div class="inputWrap">
						<%-- 검색옵션 --%>
						<select id="srchFaqType" name="srchType"></select>
						<%-- 검색키워드 --%> 
						<input type="text" id="srchFaqText" name="srchText" placeholder="검색어를 입력하세요." />
					</div>
					<div class="inputWrap submit">
						<%-- 자주하는질문 검색버튼 --%>
						<button id="btnFaqSearch">검색</button>
					</div>
				</form>
			</div>

			<%-- 자주하는질문 목록 --%>
			<div class="tableWrap type1" id="appFaqBoard"></div>
			
			<%-- 자주하는질문 페이징 --%>
			<div class="pagenation" id="appFaqPage"></div>
		<!-- // 자주하는질문 END -->

		<!-- 질의응답 START // -->
			<h3 class="board-tit pc">질의응답</h3>

			<!-- 검색 -->
			<div class="searchWrap type1 box">
				<%-- 질의응답 검색폼 설정 --%>
				<form id="qnaForm" name="qnaForm" method="post" onsubmit="return false;">
					<div class="inputWrap">
						<%-- 검색옵션 --%>
						<select id="srchQnaType" name="srchType"></select>
						<%-- 검색키워드 --%> 
						<input type="text" id="srchQnaText" name="srchText" placeholder="검색어를 입력하세요." />
					</div>
					<div class="inputWrap submit">
						<%-- 질의응답 검색버튼 --%>
						<button id="btnQnaSearch">검색</button>
					</div>
				</form>
			</div>

			<!-- 검색결과 -->
			<div class="listResult type2">
				<%-- 질의응답 문의하기버튼 --%>
				<a href="${contextPath}/usr/bbs/writeQna.do" id="btnQnaRegist">문의하기</a>
			</div>

			<%-- 질의응답 목록 --%>
			<div class="tableWrap type1" id="appQnaBoard"></div>
			
			<%-- 질의응답 페이징 --%>
			<div class="pagenation" id="appQnaPage"></div>

			<%-- 질의응답 비밀번호확인 팝업 --%>
			<div id="appPopupQnaPswd"></div>

		<!-- // 질의응답 END -->
		</div>
</section>

<!-- //end 내용 -->

<%-- ############################# 내용 (종료) ############################# --%>
