<%--
*******************************************************************************
***    명칭: listLwstIncdnt.jsp
***    설명: 취약계층소송지원 - 소송개요현황
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%-- ############################# 내용 (시작) ############################# --%>


<div class="boxWrap type1">
	<div class="boxInner">
		<div class="boxTit type1">
			<h3>검색조건</h3>
		</div>
		<div class="searchForm formLayout">
			<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
				<div class="formGroup" id="appLwstTermBox"></div>
				<div class="formGroup">
					<p>사건명 검색</p>
					<div class="inputWrap">
						<input type="text" id="srchIncdntNm" name="srchIncdntNm" />
					</div>
					<div class="inputWrap">
						<span class="label">사건번호 검색</span>
						<input type="text" id="srchIncdntNo" name="srchIncdntNo" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="btnWrap type1">
		<a href="#void" id="btnSearch" class="blue">조회</a>
	</div>
</div>

<div style="height: 30px;"></div>

<div class="subTit type1">
	<h3>소송개요 현황</h3>
</div>

<div style="height: 26px;"></div>

<div class="listWrap div2 box">
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnRegiLwst" class="btn blue">소송추가</a>
			<a href="#void" id="btnUpdtLwst" class="btn blue">소송수정</a>
			<a href="#void" id="btnDeltLwst" class="btn blue">소송삭제</a>
			<a href="#void" id="btnExcel" class="btn right">엑셀다운로드</a>
		</div>
		<div style="height: 550px">
			<table id="grid" class="easyui-datagrid"></table>
		</div>
	</div>

	<!-- 오른쪽 -->
	<div class="formLayout boxWrap type1">
		<div class="formList boxInner">
			<div class="formGroup box">
				<form id="registForm" name="registForm" method="post" onsubmit="return false;">
					<input id="mode" name="mode" type="hidden" />
					<input id="rgtrNo" name="rgtrNo" type="hidden" value="${model.rgtrNo}" />
					<input id="incdntMngNo" name="incdntMngNo" type="hidden" value="${model.incdntMngNo}" />

					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>사건 번호</span>
						<input type="text" id="incdntNo" name="incdntNo" class="w100" readonly/>
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>사건 명</span>
						<input type="text" id="incdntNm" name="incdntNm" class="w100" readonly/>
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>원 고</span>
						<input type="text" id="aplcntNmCnt" name="aplcntNmCnt" class="w100" readonly/>
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>피 고</span>
						<input type="text" id="respdntNm" name="respdntNm" class="w100" readonly/>
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>소 가</span>
						<input type="text" id="lwstPricesAmt" name="lwstPricesAmt" class="w100" readonly/>
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>인지액</span>
						<input type="text" id="papstmpAmt" name="papstmpAmt" class="w100" readonly/>
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>소송일</span>
						<input type="text" id="lwstYmd" name="lwstYmd" class="w100" readonly />
					</div>
					<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
						<span>재판부</span>
						<input type="text" id="jdgmtDeptNm" name="jdgmtDeptNm" class="w100" readonly />
					</div>
				</form>
			</div>
		</div>

		<div style="height: 20px;"></div>

		<div class="subTit type2">
			<h4>향후기일</h4>
		</div>
		<div class="formLayout tabInnerFrom box" style="height: 270px">
			<table id="dtltGrid" class="easyui-datagrid"></table>
		</div>
	</div>
</div>

<div id="appPopupLwst"></div>
<%-- 소송등록/수정 팝업 --%>

<%-- ############################# 내용 (종료) ############################# --%>
