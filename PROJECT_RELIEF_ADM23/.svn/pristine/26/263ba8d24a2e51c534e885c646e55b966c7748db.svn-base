<%--
*******************************************************************************
***    명칭: listReliefClaim.jsp
***    설명: 구제급여 사후관리 (구상권/환수)
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
***    1.0      2021.12.16    LSH        기능 구현
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

<!-- 검색영역 -->
<div class="searchConditions boxWrap type1">
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<div class="searchConditions-wrap boxInner">
			<div class="boxTit type1">
				<h3>검색조건</h3>
			</div>
			<div class="searchForm formLayout">
				<div class="formGroup">
					<p>피해지역</p>
					<div class="inputWrap">
						<div class="inputWrap">
							<select id="srchBizArea" name="srchBizArea" style="width:230px"></select>
						</div>
						<div class="inputWrap">
							<span>구상금 납부년도</span>
							<select id="srchGiveYear" name="srchGiveYear" style="width:230px"></select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="btnWrap type1">
			<a href="javascript:void(0);" id="btnSearch" class="blue">조회</a>
		</div>
	</form>
</div>
<div class="app-space10"></div>

<div class="subTit type1">
	<h3>${PAGEINFO.pageInfo.progNm} 현황</h3>
	<div class="btnDiv">
		<a href="javascript:void(0);" id="btnExcel"  class="btn right">엑셀로 다운받기</a>
		<a href="javascript:void(0);" id="btnRegist" class="btn blue right">등록</a>
		<a href="javascript:void(0);" id="btnUpdate" class="btn blue right">수정</a>
		<a href="javascript:void(0);" id="btnRemove" class="btn blue right">삭제</a>
	</div>
</div>
<div class="app-space25"></div>

<div style="height: 500px">
	<table id="appGrid" class="easyui-datagrid"></table>
</div>

<div id="appPopupReamtForm"></div><%-- 구상권 등록/수정 팝업 --%>
<div id="appPopupReamtView"></div><%-- 구제급여 상세내역 팝업 --%>

<%-- ############################# 내용 (종료) ############################# --%>
