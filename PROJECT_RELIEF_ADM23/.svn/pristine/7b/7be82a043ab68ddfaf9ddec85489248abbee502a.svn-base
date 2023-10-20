<%--
*******************************************************************************
***    명칭: listBbs.jsp
***    설명: [게시판] 질의응답관리 관리자 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
*******************************************************************************
--%>
<style type="text/css">

.tableWrap.type3 tr th{

	border-right:none;

}
.tableWrap.type3 tr {

    cursor: pointer;
}
</style>
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

<div style="height: 30px;"></div>
<div class="bbsWrap">
	<div class="tabWrap type4">
		<ul class="li-2 box">
			<li><a href="#void">질의응답</a></li>
			<li><a href="#void">자주하는질문</a></li>
		</ul>
	</div>
	<div class="tabInner">
        <input type="hidden" id="bbsSecd" name="bbsSeCd" value='<c:out value="${model.bbsSeCd }"/>'/>
		<ul>
			<li class="tabInnerFrom" style="padding: 50px;">
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
				<!-- 전체 QNA 수  -->
                <div class="listResult type1 totalCount" id="qnaTotalCount"></div>
				<div style="height:;"></div>
				<%-- 질의응답 목록 --%>
				<div class="tableWrap type3" id="appQnaBoard" style ="text-align:center;"></div>
				<%-- 질의응답 페이징 --%>
				<div class="pagenation" id="appQnaPage"></div>



			</li>
			<li class="tabInnerFrom" style="padding: 50px;">
				<!-- 검색 -->
				<div class="searchWrap type1 box">
                    <%-- 자주하는질문 검색폼 설정 --%>
					<form id="faqForm" name="faqForm" method="post"
						onsubmit="return false;">
						<div class="inputWrap">
							<%-- 검색옵션 --%>
							<select id="srchFaqType" name="srchType"></select>
							<%-- 검색키워드 --%>
							<input type="text" id="srchFaqText" name="srchText" placeholder="검색어를 입력하세요." />
						</div>
						<div class="inputWrap submit">
							<button id="btnFaqSearch">검색</button>
						</div>
					</form>
				</div>
				<div class="listResult type1 totalCount" id="faqTotalCount"></div>
				<div style="height:;"></div>
                <%-- 자주하는 질문 목록 --%>
				<div class="tableWrap type3" id="appFaqBoard" style ="text-align:center;"></div>
				<!-- 페이징 -->
				<div class="pagenation">
					<!-- PC -->
					<div class="pagenation" id="appFaqPage"></div>
					<a href=<c:url value="/adm/bbs/writeFaq.do"/> id="btnRegist" class="btn blue right">신규등록</a>
				</div>
			</li>
		</ul>
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
