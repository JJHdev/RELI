<%--
*******************************************************************************
***	명칭: listBioCmitMng.jsp
***	설명: 살생물제품 위원회관리 화면
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	2.0      2023.01.30    LSH        First Coding.
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
				<div class="formGroup" id="appSrchCmitSeCd"></div><%-- 위원회구분 --%>
				<div class="formGroup">
					<p>위원회 차수</p>
					<div class="inputWrap">
						<input type="text" id="srchCmitOder" name="srchCmitOder" style="width:210px"/>
					</div>
				</div>
				<div class="formGroup" id="appOpmtTermBox"></div><%-- 개최일자 --%>
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
	<h3>위원회 목록</h3>
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
			<ul class="li-2 box">
				<li><a href="#void">위원회 정보</a></li>
				<li><a href="#void">위원 조회</a></li>
			</ul>
		</div>
		<div class="app-space10"></div>
		<div class="tabInner formLayout">
			<ul>
				<li>
					<!-- 위원회정보 START // -->
					<div class="formLayout">
						<div class="boxInner type2 box">
							<form id="cmitForm" name="cmitForm" method="post" onsubmit="return false;" >
								<input type="hidden" name="mode"      />
								<input type="hidden" name="cmitMngNo" />
								<input type="hidden" name="dmgeSeCd"  />
								<input type="hidden" name="exmnSeCd"  />

								<div class="formGroup col-md-12">
									<span class="label col-md-2">위원회 구분</span>
									<div class="inputWrap col-md-10">
										<div id="appCmitSeCd"></div>
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">위원회 차수</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="cmitOder" name="cmitOder" maxlength="10" class="w100" />
									</div>
								</div>
								<div class="formGroup col-md-12">
									<span class="label col-md-2">위원회 개최일자</span>
									<div class="inputWrap col-md-10">
										<input type="text" id="opmtBgngYmd" name="opmtBgngYmd" maxlength="10" class="easyui-datebox" style="width:48%"/> ~
										<input type="text" id="opmtEndYmd"  name="opmtEndYmd"  maxlength="10" class="easyui-datebox" style="width:48%"/>
									</div>
								</div>
							</form>
						</div>
						<div class="btnWrap type1 app-right">
				             <app:button id="btnCmitRegist" title="신규등록" cls="app-m3"/>
				             <app:button id="btnCmitRemove" title="삭제" cls="app-m3 blue"/>
				             <app:button id="btnCmitSave"   title="저장" cls="app-m3 blue"/>
						</div>
					</div>
					<!-- 위원회정보 END // -->
				</li>
				<li>
					<!-- 위원조회 START // -->
					<div class="formLayout">
						<div class="boxInner type2 box">
							<form id="mfcmmForm" name="mfcmmForm" method="post" onsubmit="return false;" >
								<input type="hidden" name="mode"      />
								<input type="hidden" name="cmitMngNo" />
								<div class="app-formType1" style="width:100%;min-height:370px">
									<table id="mfcmmGrid"></table>
								</div>
							</form>
						</div>
						<div class="btnWrap type1 app-right">
				             <app:button id="btnMfcmmRegist" title="위원등록" cls="app-m3 blue"/>
				             <app:button id="btnMfcmmRemove" title="선택삭제" cls="app-m3 blue"/>
						</div>
					</div>
					<!-- 위원조회 END // -->
				</li>
			</ul>
		</div>
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
