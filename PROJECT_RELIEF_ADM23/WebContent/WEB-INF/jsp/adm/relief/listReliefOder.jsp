<%--
*******************************************************************************
***    명칭: listReliefOder.jsp
***    설명: 구제급여 접수 - 추가의료비
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.12.14    LSH        First Coding.
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
				<div class="formGroup">
					<p>피해지역</p>
					<div class="inputWrap">
						<div class="inputWrap">
							<select id="srchBizArea" name="srchBizArea" style="width:230px"></select>
						</div>
						<div class="inputWrap">
							<span>신청차수</span>
							<select id="srchAplyOder" name="srchAplyOder" style="width:230px"></select>
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
	<h3>추가의료비신청현황</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">

	<!-- 추가의료비신청현황목록 START // -->
	<div class="tableGroup">
		<div style="height: 600px">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	<!-- 추가의료비신청현황목록 END // -->
	</div>
	
	<div class="">
	
		<!-- 개인정보 START // -->
		<div class="subTit type2">
			<h4>개인정보</h4>
		</div>
		<form id="selectForm" name="selectForm" method="post" onsubmit="return false;" >
			<input type="hidden" name="bizAreaCd" />
			<input type="hidden" name="aplyNo"    />
			<input type="hidden" name="aplyOder"  />
			<div class="formLayout tabInnerFrom box">
				<div class="formGroup col-md-6">
					<span class="label col-md-4">식 별 ID</span>
					<div class="inputWrap col-md-7">
						<div class="app-box" id="s_idntfcId"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">피해자명</span>
					<div class="inputWrap col-md-8">
						<div class="app-box" id="s_sufrerNm"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">피해지역</span>
					<div class="inputWrap col-md-7">
						<div class="app-box" id="s_bizAreaNm"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">신청차수</span>
					<div class="inputWrap col-md-8">
						<div class="app-box" id="s_aplyOder"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">신청번호</span>
					<div class="inputWrap col-md-7">
						<div class="app-box" id="s_aplyNo"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">신청일자</span>
					<div class="inputWrap col-md-8">
						<div class="app-box" id="s_aplyYmd"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">진행상태</span>
					<div class="inputWrap col-md-7">
						<div class="app-box" id="s_prgreStusNm"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">접수일자</span>
					<div class="inputWrap col-md-8">
						<div class="app-box" id="s_rcptYmd"></div>
					</div>
				</div>
			</div>
		</form>
		<!-- 개인정보 END // -->

		<!-- 제출서류목록 START // -->
		<div class="app-space25"></div>
		<div class="subTit type2">
			<h4>추가의료비목록</h4>
		</div>
		<div class="tableWrap type1 app-scroll" style="max-height:200px;" id="appAplyFileList"></div>
		<!-- 제출서류목록 END // -->
		
		<div class="app-space10"></div>
		<div class="btnDiv">
			<a href="#void" id="btnReceipt" class="btn blue">접수</a>
		</div>
		
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
