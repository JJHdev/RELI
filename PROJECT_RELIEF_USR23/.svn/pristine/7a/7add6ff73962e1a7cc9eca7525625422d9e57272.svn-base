<%--
*******************************************************************************
***    명칭: listStatute.jsp
***    설명: [자료실] 법규정 화면
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

<section class="contents board">
	<div class="contents-wrap law">
		<form:form commandName="model" id="model1" name="model1" method="post"
			onsubmit="return false;">
			<form:hidden path="page" />
			<form:hidden path="nttNo" />
			<%-- 게시판구분값이 Controller에서 넘어옴 --%>
			<form:hidden path="bbsSeCd" />

			<img src="../images/page/notice.jpg" alt="" />
			<h3 class="board-tit pc">법 &middot; 규정</h3>

			<!-- 검색 -->
			<div class="searchWrap type1 box">
				<div class="inputWrap">
					<form:select path="srchType">
			            <form:option value="" label="::: 전체 :::" />
	                    <form:option value="3" label="제목" />
	                    <form:option value="4" label="내용" />
	                    <form:option value="5" label="제목+내용" />
					</form:select>
					<form:input path="srchText" placeholder="검색어를 입력하세요." />
				</div>
				<div class="inputWrap submit">
					<app:button id="srchBtn" title="검색" type="button" />
				</div>
			</div>
		</form:form>
		<!-- 검색결과 -->
		<div class="listResult type1">
			<p>
				총 <span><c:out value="${totalSize}" /></span>건
			</p>
		</div>


		<div class="tableWrap type1">
			<table>

				<thead>
					<tr>
						<th>번호</th>
						<th class="left">제목</th>
						<th>등록일</th>
						<th>조회수</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody>
				    <c:if test="${startRevNo< 1}" >
			            <td colspan = "5"> 내용이 없습니다.</td>
				    </c:if>	
				    <c:if test="${startRevNo>=1}">				
					<c:forEach var="row" items="${rows}" varStatus="st">
						<tr  class="btnView" data-seq="<c:out value="${row.nttNo}"/>" >
							<td width=10%  class="pc"><c:out value="${startRevNo-st.index}" /></td>
							<td width=60%  class="left" >
							    <span class="mobile">제목</span> <c:out value="${row.nttSj}" />
							</td>
							<td width=15% ><span class="mobile">등록일</span> <c:out value="${row.regYmd}" /></td>
							<td width=5% ><span class="mobile">조회수</span> <c:out value="${row.inqCnt}" /></td>
							<c:if test="${row.fileYN > 0 }">
							<td  width=10% ><span class="mobile">첨부파일</span><img src="../../images/common/upload_file_icon.png"/></td>
							</c:if>
							<c:if test="${ row.fileYN == 0 }">
							<td  width=10% ><span class="mobile">첨부파일</span></td>
							</c:if> 
						</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<app:paging name="rows" jsFunction="Search" />
		<!-- 페이징 -->
		<!-- 	 	 <div class="pagenation">
			PC
			<div class="pageWrap">
				<ul class="pc">
					<li class="prev"><a href="#void"></a></li>
					<li class="active"><a href="#void">1</a></li>
					<li><a href="#void">2</a></li>
					<li><a href="#void">3</a></li>
					<li><a href="#void">4</a></li>
					<li><a href="#void">5</a></li>
					<li><a href="#void">6</a></li>
					<li class="next"><a href="#void"></a></li>
				</ul>

				모바일
				<ul class="mobile">
					<li class="prev"><a href="#void"></a></li>
					<li class="next"><a href="#void"></a></li>
					<li><select>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
					</select></li>
				</ul>
			</div>
		</div>  -->

	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
