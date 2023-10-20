<%--
*******************************************************************************
***	명칭: listBioReliefRcpt.jsp
***	설명: 살생물제품 구제급여접수 화면
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	2.0      2023.01.25    LSH        First Coding.
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

<!-- 2022.12.05 SEARCHBOX TOGGLE START // -->
<div class="topBox"><a href="#void" class="viewBtn"></a>

<!-- 검색영역 -->
<div class="searchConditions boxWrap type1">
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
	<div class="searchConditions-wrap boxInner">
		<div class="boxTit type1">
			<h3>검색조건</h3>
		</div>
		<div class="searchForm formLayout">
			<div class="formGroup" id="appAplyTermBox"></div><%-- 신청일자 --%>
			<div class="formGroup" id="appRcptTermBox"></div><%-- 접수일자 --%>
			<div class="formGroup" id="appAplySe"     ></div><%-- 신청구분 --%>
			<div class="formGroup" id="appAplyKnd"    ></div><%-- 신청종류 --%>
			<div class="formGroup">
				<p>살생물제품</p>
				<div class="inputWrap">
					<div class="inputWrap">
						<select id="srchPrductTy" name="srchPrductTy" style="width:230px"></select>
						<select id="srchPrductCd" name="srchPrductCd" style="width:230px"></select>
						<input type="text" id="srchPrductCn" name="srchPrductCn" placeholder="제품명"/>
					</div>
				</div>
			</div>
			<div class="formGroup">
				<p>피해자명</p>
				<div class="inputWrap">
					<div class="inputWrap">
						<input type="text" id="srchSufrerNm" name="srchSufrerNm"/>
					</div>
					<div class="inputWrap">
						<span>신청자명</span>
						<input type="text" id="srchAplcntNm" name="srchAplcntNm"/>
					</div>
					<div class="inputWrap">
						<span>식별 ID</span>
						<input type="text" id="srchIdntfcId" name="srchIdntfcId"/>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="btnWrap type1">
		<a href="#void" id="btnSearch" class="blue">조회</a>
		<a href="#void" id="btnReset">초기화</a>
	</div>
	</form>
</div>
<!-- 2022.12.05 SEARCHBOX TOGGLE END // -->
</div>
<div class="app-space10"></div>

<div class="subTit type1">
	<h3>신청/접수현황</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">
	<!-- 신청접수목록 -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnExcel"   class="btn">엑셀다운로드</a>
			<a href="#void" id="btnReceipt" class="btn blue">신청접수</a>
		</div>
		<!-- 종합현황목록 -->
		<table id="appGrid" class="easyui-datagrid" style="height:600px"></table>
	</div>
	<div class="boxWrap type1">
		<div class="tabWrap type3">
			<ul class="li-4 box">
				<li><a href="#void">피해자정보</a></li>
				<li><a href="#void">신청정보</a></li>
				<li><a href="#void">피해내용</a></li>
				<li><a href="#void">제출서류</a></li>
			</ul>
		</div>
		<div class="tabInner formLayout">
			<ul>
				<li>
					<!-- 피해자정보 START // -->
					<form id="sufrerForm" name="sufrerForm" method="post" onsubmit="return false;">
						<input name="mode"        type="hidden" value="" />
						<input name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
						<input name="aplySeCd"    type="hidden" value="${model.aplySeCd}"    /><%-- 신청구분코드 --%>
						<input name="aplyNo"      type="hidden" value="${model.aplyNo}"      /><%-- 신청번호 --%>
						<div id="appSufrer" style="max-height:600px"></div>
					</form>
					<div class="h10"></div>
					<div class="btnDiv">
						<a href="#void" id="btnSufrerUpdate" class="btn">수정</a>
					</div>
					<!-- 피해자정보 END   // -->
				</li>
				<li>
					<!-- 신청정보 START // -->
					<form id="aplcntForm" name="aplcntForm" method="post" onsubmit="return false;">
						<input name="mode"        type="hidden" value="" />
						<input name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
						<input name="aplySeCd"    type="hidden" value="${model.aplySeCd}"    /><%-- 신청구분코드 --%>
						<input name="aplyNo"      type="hidden" value="${model.aplyNo}"      /><%-- 신청번호 --%>
						<div id="appAplcnt" style="max-height:600px"></div>
					</form>
					<div class="h10"></div>
					<div class="btnDiv">
						<a href="#void" id="btnAplcntUpdate" class="btn">수정</a>
					</div>
					<!-- 신청정보 END   // -->
				</li>
				<li>
					<!-- 피해내용 START // -->
					<form id="prductForm" name="prductForm" method="post" onsubmit="return false;" >
						<input name="mode"        type="hidden" value="" />
						<input name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
						<input name="aplySeCd"    type="hidden" value="${model.aplySeCd}"    /><%-- 신청구분코드 --%>
						<input name="aplyNo"      type="hidden" value="${model.aplyNo}"      /><%-- 신청번호 --%>
						<div id="appPrduct" style="max-height:600px"></div>
					</form>
					<div class="h10"></div>
					<div class="btnDiv">
						<a href="#void" id="btnPrductUpdate" class="btn">수정</a>
					</div>
					<!-- 피해내용 END   // -->
				</li>
				<li>
					<!-- 제출서류 START // -->
					<div class="h40"></div>
					<div class="tableWrap type1 app-scroll" id="appAplyFileList" style="max-height:300px;"></div>
					<div class="h10"></div>
					<div class="btnDiv">
						<a href="#void" id="btnAddfile" class="btn">추가등록</a>
					</div>
					<!-- 제출서류 END   // -->
				</li>
			</ul>
		</div>	
		<div class="app-space10"></div>

		<!-- 이력관리 START // -->
		<div class="subTit type2">
			<h4>이력관리</h4>
		</div>
		<div id="appMngHst"></div>
		<!-- 이력관리 END   // -->
	</div>
</div>

<div id="appPopupReceipt"></div><%-- 신청접수 팝업 --%>
<div id="appPopupHistory"></div><%-- 이력관리 팝업 --%>

<%-- ############################# 내용 (종료) ############################# --%>
