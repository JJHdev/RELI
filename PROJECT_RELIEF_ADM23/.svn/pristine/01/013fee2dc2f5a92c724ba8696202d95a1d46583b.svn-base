<%--
*******************************************************************************
***    명칭: listRcperLvlh.jsp
***    설명: 구제급여지급 - 요양생활수당 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2022.12.26    LSH        First Coding.
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
							<span>사업차수</span>
							<select id="srchBizOder" name="srchBizOder" style="width:230px"></select>
						</div>
						<div class="inputWrap">
							<span>조사차수</span>
							<select id="srchExmnOder" name="srchExmnOder" style="width:230px"></select>
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
				<div class="formGroup">
					<p>신청종류</p>
					<div class="inputWrap">
						<div class="inputWrap">
							<select id="srchAplyKnd" name="srchAplyKnd" style="width:230px"></select>
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
	<h3>요양생활수당 지급 대상 목록</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">
	<!-- 목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnRcpForm"  class="btn">등록양식 다운로드</a>
			<a href="#void" id="btnRcpLoad"  class="btn blue">지급일자 일괄등록</a>
			<a href="#void" id="btnRcpExcel" class="btn">엑셀다운로드</a>
		</div>
		<div style="width:100%;height:650px">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	<!-- 목록 END // -->
	</div>
	<div class="boxWrap type1">
		<div class="tabWrap type3">
			<ul class="li-2 box">
				<li><a href="#void">피해등급 산정</a></li>
				<li><a href="#void">요양생활수당 지급</a></li>
			</ul>
		</div>
		<div class="app-space10"></div>
		<div class="tabInner formLayout">
			<ul>
				<li>
					<!-- 피해등급산정 START // -->
					<form id="grdForm" name="grdForm" method="post" onsubmit="return false;" >
						<input type="hidden" name="mode"      />
						<input type="hidden" name="bizAreaCd" />
						<input type="hidden" name="bizOder"   />
						<input type="hidden" name="exmnOder"  />
						<input type="hidden" name="aplyNo"    />
						<input type="hidden" name="lastDmgeGrdCd" />
						<input type="hidden" name="lastDmgeScre"  />

						<div class="app-space10"></div>

						<div class="formLayout boxInner type3">
							<div class="formGroup col-md-12">
								<span class="label col-md-2">기준년도</span>
								<div class="inputWrap col-md-10">
									<select id="dmgeGrdYr" name="dmgeGrdYr"></select>
								</div>
							</div>
							<div class="app-formType1" style="width:100%;min-height:300px">
								<table id="grdGrid"></table>
							</div>
							<div class="app-space10"></div>
							<div class="app-right">
								<a href="#void" id="btnAppend" class="app-plus"><i class="fa fa-plus"></i></a>
							</div>
						</div>
						<div class="app-space10"></div>
						<div class="btnDiv app-right">
							<a href="#void" id="btnProduce" class="btn blue">등급산출</a>
						</div>
						<div class="app-grade col-md-12">
							<div class="col-md-3 app-grade-label">최종 피해 등급</div>
							<div class="col-md-3 app-grade-value"><span id="lastDmgeGrdNm"></span>&nbsp;</div>
							<div class="col-md-3 app-grade-label">점수</div>
							<div class="col-md-3 app-grade-value"><span id="lastDmgeScreNm"></span>&nbsp;</div>
						</div>
						<div class="btnDiv app-right">
							<a href="#void" id="btnGrdSave" class="btn blue">저장</a>
						</div>
					</form>
					<!-- 피해등급산정 END // -->
				</li>
				<li>
					<!-- 요양생활수당 지급 START // -->
					<form id="rcpForm" name="rcpForm" method="post" onsubmit="return false;" >
						<input type="hidden" name="mode"         />
						<input type="hidden" name="bizAreaCd"    />
						<input type="hidden" name="bizOder"      />
						<input type="hidden" name="exmnOder"     />
						<input type="hidden" name="aplyNo"       />
						<input type="hidden" name="lumpSumAmt"   />
						<input type="hidden" name="rcperLvlhAmt" />
						<input type="hidden" name="giveBgngYmOrg" />
						<input type="hidden" name="giveEndYmOrg"  />
						
						<div class="app-space10"></div>
						<div class="subTit type2">
							<h4 class="app-left app-pt20">지급기한 결정내용</h4>
							<div class="btnDiv">
								<a href="#void" id="btnDeadline" class="btn blue">지급기한등록</a>
							</div>
						</div>
						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-12">
								<span class="label col-md-2">지급기간</span>
								<div class="inputWrap col-md-10">
									<input type="hidden" id="giveBgngYm" name="giveBgngYm" />
									<input type="hidden" id="giveEndYm"  name="giveEndYm"  />
									<select id="giveBgngYm1" name="giveBgngYm1"></select>
									<select id="giveBgngYm2" name="giveBgngYm2"></select>
									~
									<select id="giveEndYm1" name="giveEndYm1"></select>
									<select id="giveEndYm2" name="giveEndYm2"></select>
								</div>
							</div>

						</div>
						<div class="app-space10"></div>
						<div class="subTit type2">
							<h4 class="app-left app-pt20">개인별 지급일자 등록</h4>
							<div class="btnDiv">
								<a href="#void" id="btnRtroact" class="btn">소급결정내용</a>
							</div>
						</div>
						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-12">
								<span class="label col-md-2">지급구분</span>
								<div class="inputWrap col-md-10">
									<div id="appGiveSeCd"></div>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">지급일자</span>
								<div class="inputWrap col-md-10">
									<input type="text" id="giveYmd" name="giveYmd" maxlength="10" class="easyui-datebox" />
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">지급년월</span>
								<div class="inputWrap col-md-10">
									<select id="giveYr" name="giveYr"></select>
									<select id="giveMm" name="giveMm"></select>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2"><span id="giveSeLabel"></span></span>
								<div class="inputWrap col-md-10">
									<input type="text" id="monthlyGrd" name="monthlyGrd" class="app-readonly" readonly style="display:none; width:100px" />
									<input type="text" id="monthlyAmt" name="monthlyAmt" class="app-readonly" readonly style="display:none;" />
									<input type="text" id="giveAllAmt" name="giveAllAmt" class="app-readonly" readonly style="display:none;" />
								</div>
							</div>
						</div>
						<div class="app-space10"></div>
						<div class="btnDiv app-right">
				             <app:button id="btnRcpSave" title="지급등록" cls="btn blue"/>
						</div>

						<div class="app-both"></div>
						<div class="subTit type2">
							<h4>요양급여 지급현황</h4>
						</div>
						<div style="width:100%;min-height:200px">
							<table id="rcpGrid"></table>
						</div>
					</form>
					<!-- 요양생활수당 지급 END // -->
				</li>

			</ul>
		</div>
	</div>
</div>

<%-- 2022.12.29 소급결정내용 팝업 --%>
<div id="appPopupRtroact"></div>

<%-- ############################# 내용 (종료) ############################# --%>
