<%--
*******************************************************************************
***    명칭: listMyBbs.jsp
***    설명: 마이페이지 - 나의질의현황
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<%-- ############################# 내용 (시작) ############################# --%>
<!-- 내용 -->
<section class="contents board">
	<div class="contents-wrap qna">
	<form:form commandName="model" id="model1" name="model1" method="post" onsubmit="return false;">
			<form:hidden path="page" />
			<form:hidden path="nttNo" />
			<form:hidden path="bbsSeCd" />
			
	
		<!-- 검색 -->
		<div class="searchWrap type1 box">
			<div class="inputWrap">
			<form:select path="srchType">
				<form:option value="3" label="제목" />
				<form:option value="4" label="내용" />
				<form:option value="5" label="제목+내용" />
			</form:select>
				<form:input path="srchText"  placeholder="검색어를 입력하세요."/>
			</div>
			<div class="inputWrap submit">
				<app:button id="srchBtn" title="검색"  type="button" /> 
			</div>
		</div>
		
		<!-- 검색결과 -->
		<div class="listResult type2">
			<a href="#" id='btnQnaRegist'>문의하기</a>
		</div>
		</form:form>
		<div class="tableWrap type1">
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
				    <c:if test="${startRevNo< 1}" >
	                     <td colspan = "5"> 내용이 없습니다.</td>
		            </c:if>							
		            <c:if test="${startRevNo>=1}">				
					<c:forEach var="row" items="${rows}" varStatus="st">
						<tr  class="btnView" data-seq="<c:out value="${row.nttNo}"/>">
							<td class="pc"><c:out value="${startRevNo-st.index}" /></td>
							<td class="left"><span class="mobile">제목</span> <c:out value="${row.nttSj}" /></td>
							<td><span class="mobile">작성자</span><c:out value="${row.rgtrNm}"/></td>
							<td><span class="mobile">등록일</span><c:out value="${row.regYmd}"/></td>
							<c:set var="checkSt" value="${row.status}"/>
							<c:if test="${checkSt > 0 }">
							<td><span class="mobile">상태</span><p style ="color : blue;">답변완료</p></td>
							</c:if>
							<c:if test="${checkSt == null }">
							<td><span class="mobile">상태</span><p style ="color : red;">대기중</p></td>
							</c:if>
						</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<app:paging name="rows" jsFunction="Search" />
		<!-- 페이징 -->
		<!-- <div class="pagenation">
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
					</select>
					</li>
				</ul>
			</div>
		</div> -->
		
	</div>
</section>
<%-- ############################# 내용 (종료) ############################# --%>
