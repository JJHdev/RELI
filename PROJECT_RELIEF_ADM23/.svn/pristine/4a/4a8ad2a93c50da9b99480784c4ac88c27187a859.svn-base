<%--
*******************************************************************************
***    명칭: listUserInfo.jsp
***    설명: 사용자정보 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.09.13    LSH        First Coding.
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
				<div class="formGroup" id="appAplyTermBox"></div>
				<div class="formGroup">
					<p>신청자명</p>
					<div class="inputWrap">
						<div class="inputWrap">
							<input type="text" id="srchAplcntNm" name="srchAplcntNm" />
						</div>
						<div class="inputWrap">
							<span>식별아이디</span>
							<input type="text" id="srchIdntfcId" name="srchIdntfcId" />
						</div>
					</div>
				</div>

				<%-- 연동대상 --%>
				<div class="formGroup" id="intrlckSeCd"></div>
				<%-- 연동상태 --%>
				<div class="formGroup" id="prgreStusCd"></div>
			</form>
		</div>
	</div>
	<div class="btnWrap type1">
		<app:button id="btnSearch" title="검색" cls="app-m3 blue" />
	</div>
</div>

<div style="height: 30px;"></div>

<div class="subTit type1">
	<h3>정보연동 신청인 현황</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">
	<!-- 신청접수목록 START // -->
	<div class="tableGroup">
			<div class="btnDiv">
			<a href="#void" id="btnExcel" class="btn">엑셀다운로드</a>
		</div>
		<div style="height: 500px">
			<table id="grid" class="easyui-datagrid"></table>
		</div>
		<!-- 신청접수목록 END // -->
	</div>

	<div class="formLayout">
		<!-- 개인정보 START // -->
		<div class="subTit type2">
			<h4>개인정보</h4>
		</div>
		<form id="registForm" name="registForm" method="post" onsubmit="return false;">
				<input id="mode" name="mode" type="hidden" />
				<input id="rgtrNo" name="rgtrNo" type="hidden" value="${model.rgtrNo}" />
				<input id="aplyNo" name="aplyNo" type="hidden" value="${model.aplyNo}" />
			<div class="boxWrap">
				<div class="tabWrap type2">
					<ul>
						<li class="on">
							<a href="#void">연동신청자 정보</a>
						</li>
					</ul>
				</div>
				<div class="tabInner formLayout" style="margin-top: -7px;">
					<ul>
						<li class="tabInnerFrom box on">
							<div class="formGroup col-md-6">
								<span class="label col-md-4">연동대상</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="intrlckSeCd" name="intrlckSeCd" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">연동대상 성명</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="trprNm" name="trprNm" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">연동대상자 식별 아이디</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="trprIdntfcId" name="trprIdntfcId" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">연동대상자 생년월일</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="trprBrdt" name="trprBrdt" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">연동대상자 연락처</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="mbtelNo" name="mbtelNo" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">연동상태</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="prgreStusCd" name="prgreStusCd" class="w100" readonly />
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</form>

		<!-- 제출서류목록 START // -->
		<div class="app-space25"></div>
		<div class="subTit type2">
			<h4>제출서류목록</h4>
		</div>
		<div class="tableWrap type1 app-scroll" style="max-height:200px;" id="appAplyFileList"></div>
		<!-- 제출서류목록 END // -->
		
		<div class="app-space10"></div>
		<div class="app-right btnDiv">
			<a href="#void" id="btnSave" class="btn blue">신청접수</a>
		</div>
		
	</div>
</div>

<%-- 신청접수 팝업 --%>
<div id="appPopupReceipt"></div>

<%-- ############################# 내용 (종료) ############################# --%>