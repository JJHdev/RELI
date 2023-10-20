<%--
*******************************************************************************
***    명칭: listReliefRcpt.jsp
***    설명: 구제급여 접수
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
			<div class="formGroup" id="appProgress"   ></div><%-- 진행현황 --%>
			<div class="formGroup" id="appBizArea"    ></div><%-- 지역구분 --%>
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
					<div class="inputWrap"><%-- 2023.01.06 식별ID 검색조건 추가 --%>
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

	<!-- 신청접수목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnExcel"   class="btn">엑셀다운로드</a>
			<a href="#void" id="btnReceipt" class="btn blue">신청접수</a>
		</div>
		<div style="height: 800px">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	<!-- 신청접수목록 END // -->
	</div>
	
	<div class="">
	
		<!-- 개인정보 START // -->
		<div class="subTit type2">
			<h4>개인정보</h4>
		</div>
		<div class="personnelWrap" id="appPersonalWrap"></div>
		<!-- 개인정보 END // -->

		<!-- 제출서류목록 START // -->
		<div class="app-space25"></div>
		<div class="subTit type2">
			<h4 class="app-left app-pt20">제출서류목록</h4>
			<div class="app-right btnDiv">
				<a href="#void" id="btnAddfile" class="btn">추가등록</a>
				<a href="#void" id="btnSplemnt" class="btn blue">보완요청</a>
				<a href="#void" id="btnSurvey"  class="btn blue">설문지</a>
				<a href="#void" id="btnReport"  class="btn blue" data-aply-no="">신청서보기</a>
			</div>
			<div class="app-both"></div>
		</div>
		<div class="tableWrap type1 app-scroll" style="max-height:200px;" id="appAplyFileList"></div>
		<!-- 제출서류목록 END // -->
		

		<!-- 이력관리 START // -->
		<div class="app-space25"></div>
		<div class="subTit type2">
			<h4 class="app-left app-pt20">이력관리</h4>
			<div class="app-right btnDiv">
				<a href="#void" id="btnHistory" class="btn blue">이력등록</a>
			</div>
			<div class="app-both"></div>
		</div>
		<div class="tableWrap type1 app-scroll" id="appHistoryTable"></div>
		<!-- 이력관리 END // -->
		
	</div>
</div>

<div id="appPopupReceipt"></div><%-- 신청접수 팝업 --%>
<div id="appPopupSplemnt"></div><%-- 보완요청 팝업 --%>
<div id="appPopupHistory"></div><%-- 이력관리 팝업 --%>

<%-- ############################# 내용 (종료) ############################# --%>
