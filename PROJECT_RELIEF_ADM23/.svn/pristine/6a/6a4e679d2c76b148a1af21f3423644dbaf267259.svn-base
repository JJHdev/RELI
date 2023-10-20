<%--
*******************************************************************************
***    명칭: listPrptExmn.jsp
***    설명: 예비조사 관리 화면
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
		
<%-- 상단 탭형태 서브메뉴 (필요한영역에만 표출) --%>
<div id="appTabSubMenu"></div>

<!-- 검색영역 -->
<div class="searchConditions boxWrap type1">
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<div class="searchConditions-wrap boxInner">
			<div class="boxTit type1">
				<h3>검색조건</h3>
			</div>
			<div class="searchForm formLayout">
				<div class="formGroup" id="appExmnTermBox"></div><%-- 조사일자 --%>
				<div class="formGroup">
					<p>피해지역</p>
					<div class="inputWrap">
						<select id="srchBizArea" name="srchBizArea" style="width:230px"></select>
					</div>
					<p>사업차수</p>
					<div class="inputWrap">
						<select id="srchBizOder" name="srchBizOder" style="width:230px"></select>
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
<div class="app-space10"></div>

<div class="subTit type1">
	<h3>예비조사 사업목록</h3>
</div>
<div class="app-space10"></div>

<div class="listWrap div2 box">

	<!-- 예비조사목록 START // -->
	<div class="tableGroup">
		<table id="exmnGrid" class="easyui-datagrid" style="height:230px"></table>
		<div class="app-space10"></div>
		<div class="btnDiv">
		</div>
	<!-- 예비조사목록 END // -->
	</div>

	<div class="boxWrap type1">
		<div class="tabWrap type3">
			<ul class="li-2 box">
				<li><a href="#void">예비조사계획</a></li>
				<li><a href="#void">대상자조회</a></li>
			</ul>
		</div>
		<div class="app-space25"></div>
		
		<div class="tabInner formLayout">
			<ul>
				<li>
				<!-- 예비조사계획 START // -->
					<div class="formLayout tabInnerFrom box">
						<form id="selectForm" name="selectForm" method="post" onsubmit="return false">
							<input id="mode"      name="mode"      type="hidden" />
							<input id="bizAreaCd" name="bizAreaCd" type="hidden" />
							<input id="bizOder"   name="bizOder"   type="hidden" />
							<div class="formGroup col-md-12">
								<span class="label col-md-2">피해지역</span>
								<div class="inputWrap col-md-6">
									<div id="s_bizAreaNm" class="app-box" style="width:230px"></div>
									<div id="s_bizAreaCmb">
										<select id="bizAreaCmb" name="bizAreaCmb" style="width:230px"></select>
									</div>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">사업차수</span>
								<div class="inputWrap col-md-6">
									<div id="s_bizOderNm" class="app-box" style="width:230px"></div>
									<div id="s_bizOderCmb">
										<select id="bizOderCmb" name="bizOderCmb" style="width:230px"></select>
									</div>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">예비조사차수</span>
								<div class="inputWrap col-md-6">
									<input type="text" id="exmnOder" name="exmnOder" value="" class="app-readonly" readonly />
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">영향범위</span>
								<div class="inputWrap col-md-6">
									<input type="text" id="affcScopeCn" name="affcScopeCn" value="" class="app-readonly" readonly />
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">조사기간</span>
								<div class="inputWrap col-md-10">
									<input type="text" id="exmnBgngYmd" name="exmnBgngYmd" value="" class="easyui-datebox" /> ~
									<input type="text" id="exmnEndYmd"  name="exmnEndYmd"  value="" class="easyui-datebox" />
								</div>
							</div>
						</form>
					</div>
					<div class="app-space10"></div>
					<div class="btnDiv">
						<a href="#void" id="btnRegist" class="btn right">신규등록</a>
						<a href="#void" id="btnRemove" class="btn blue right">삭제</a>
						<a href="#void" id="btnSave"   class="btn blue right">저장</a>
					</div>

				<!-- 예비조사계획 END // -->
				</li>
				<li>
				<!-- 대상자조회 START // -->
					<div class="formLayout tabInnerFrom box" style="height:270px">
						<table id="trgtGrid" class="easyui-datagrid"></table>
					</div>
					<div class="app-space10"></div>
					<div class="btnDiv">
						<a href="#void" id="btnTargetRegist" class="btn right">대상자등록</a>
						<a href="#void" id="btnTargetRemove" class="btn blue right">선택삭제</a>
					</div>

				<!-- 대상자조회 END // -->
				</li>
			</ul>
		</div>
		
	</div>
</div>
<div id="appPopupTarget"></div>
<%-- ############################# 내용 (종료) ############################# --%>
