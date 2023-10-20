<%--
*******************************************************************************
***    명칭: listMfcmm.jsp
***    설명: 위원관리
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2022.11.28    JWH        First Coding.
***    1.0      2023.01.09    LSH        설계변경에 따른 재작업
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
				<div class="formGroup">
					<p>위원회</p>
					<div class="inputWrap">
						<div class="inputWrap">
							<select id="srchCmitSeCd" name="srchCmitSeCd" style="width:230px"></select>
						</div>
						<div class="inputWrap">
							<span>분  야</span>
							<select id="srchMfcmmRlmCd" name="srchMfcmmRlmCd" style="width:230px"></select>
						</div>
						<div class="inputWrap">
							<span>위원명</span>
							<input type="text" id="srchMfcmmNm" name="srchMfcmmNm"/>
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
	<h3>위원 목록</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">
	<!-- 목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnExcel" class="btn">엑셀다운로드</a>
		</div>
		<div style="width:100%;height:500px">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	<!-- 목록 END // -->
	</div>
	<div class="boxWrap type1">
		<div class="tabWrap type3">
			<ul class="li-4 box">
				<li><a href="#void">위원정보</a></li>
				<li><a href="#void">전문위원회</a></li>
				<li><a href="#void">심의위원회</a></li>
				<li><a href="#void">심사위원회</a></li>
			</ul>
		</div>
		<div class="app-space10"></div>
		<div class="tabInner formLayout">
			<ul>
				<li>
					<!-- 위원정보 START // -->
					<div class="formLayout">
						<div class="boxInner type2 box">
							<form id="mfcmmForm" name="mfcmmForm" method="post" onsubmit="return false;" >
								<input type="hidden" name="mode"    />
								<input type="hidden" name="mfcmmNo" />

								<div class="formGroup col-md-12">
									<span class="label col-md-2">위원명</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="mfcmmNm" name="mfcmmNm" maxlength="20" class="w100" />
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">소  속</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="mfcmmOgdpNm" name="mfcmmOgdpNm" maxlength="30" class="w100" />
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">직  책</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="mfcmmRspofcNm" name="mfcmmRspofcNm" maxlength="30" class="w100" />
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">분  야</span>
									<div class="inputWrap col-md-10">
										<select id="mfcmmRlmCd" name="mfcmmRlmCd" class="w100"></select>
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">이메일</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="mfcmmEmlAddr" name="mfcmmEmlAddr" maxlength="100" class="w100" />
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">연락처</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="mfcmmTelno" name="mfcmmTelno" maxlength="13" class="w100" />
									</div>
								</div>
							</form>
						</div>
						<div class="btnWrap type1 app-right">
				             <app:button id="btnMfcmmRegist" title="신규등록" cls="app-m3"/>
				             <app:button id="btnMfcmmSave" title="저장" cls="app-m3 blue"/>
						</div>
					</div>
					<!-- 위원정보 END // -->
				</li>
				<li>
					<!-- 전문위원회 START // -->
					<div class="formLayout">
						<div class="boxInner type2 box">
							<form id="expertForm" name="expertForm" method="post" onsubmit="return false;" >
								<input type="hidden" name="mode"     />
								<input type="hidden" name="mfcmmNo"  />
								<input type="hidden" name="cmitSeCd" />
								<div class="app-formType1" style="width:100%;min-height:370px">
									<table id="expertGrid"></table>
								</div>
								<div class="app-space10"></div>
								<div class="app-right">
									<a href="#void" id="btnExpertAppend" class="app-plus"><i class="fa fa-plus"></i></a>
								</div>
							</form>
						</div>
						<div class="btnWrap type1 app-right">
				             <app:button id="btnExpertSave" title="저장" cls="app-m3 blue"/>
						</div>
					</div>
					<!-- 전문위원회 END // -->
				</li>
				<li>
					<!-- 심의위원회 START // -->
					<div class="formLayout">
						<div class="boxInner type2 box">
							<form id="reviewForm" name="reviewForm" method="post" onsubmit="return false;" >
								<input type="hidden" name="mode"     />
								<input type="hidden" name="mfcmmNo"  />
								<input type="hidden" name="cmitSeCd" />
								<div class="app-formType1" style="width:100%;min-height:370px">
									<table id="reviewGrid"></table>
								</div>
								<div class="app-space10"></div>
								<div class="app-right">
									<a href="#void" id="btnReviewAppend" class="app-plus"><i class="fa fa-plus"></i></a>
								</div>
							</form>
						</div>
						<div class="btnWrap type1 app-right">
				             <app:button id="btnReviewSave" title="저장" cls="app-m3 blue"/>
						</div>
					</div>
					<!-- 심의위원회 END // -->
				</li>
				<li>
					<!-- 심사위원회 START // -->
					<div class="formLayout">
						<div class="boxInner type2 box">
							<form id="judgeForm" name="judgeForm" method="post" onsubmit="return false;" >
								<input type="hidden" name="mode"     />
								<input type="hidden" name="mfcmmNo"  />
								<input type="hidden" name="cmitSeCd" />
								<div class="app-formType1" style="width:100%;min-height:370px">
									<table id="judgeGrid"></table>
								</div>
								<div class="app-space10"></div>
								<div class="app-right">
									<a href="#void" id="btnJudgeAppend" class="app-plus"><i class="fa fa-plus"></i></a>
								</div>
							</form>
						</div>
						<div class="btnWrap type1 app-right">
				             <app:button id="btnJudgeSave" title="저장" cls="app-m3 blue"/>
						</div>
					</div>
					<!-- 심사위원회 END // -->
				</li>
			</ul>
		</div>
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
