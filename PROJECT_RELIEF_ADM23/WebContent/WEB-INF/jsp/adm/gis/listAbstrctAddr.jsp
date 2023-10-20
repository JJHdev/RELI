<%--
*******************************************************************************
***    명칭: listAbstrctAddr.jsp
***    설명: 초본 주소 등록
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2022.11.11    LSH        First Coding.
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
<div class="searchConditions boxWrap type1">
	<div id="searchPanel" class="topBox">
		<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
			<div class="searchConditions-wrap boxInner">
				<div class="boxTit type1">
					<h3>검색조건</h3>
				</div>
				<div class="searchForm formLayout">
					<div class="formGroup">
						<div class="inputWrap">
							<span>식별ID</span>
							<div class="inputWrap">
								<input type="text" id="srchIdntfcId" name="idntfcId"/>
							</div>
						</div>
						<div class="inputWrap">
							<span>피해자명</span>
							<div class="inputWrap">
								<input type="text" id="srchSufrerNm" name="sufrerNm"/>
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
</div>
<div class="app-space10"></div>

<div class="subTit type1">
	<h3>피해자 목록</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">
	
	<!-- 피해자 정보 목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="javascript:void(0);" id="btnAbstrctForm" class="btn">양식 다운로드</a>
			<a href="javascript:void(0);" id="btnAbstrctLoad" class="btn blue" style="">엑셀 업로드</a>
		</div>
		<div style="width:100%;height:900px;">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	</div>
	<!-- 피해자 정보 목록 END // -->
	
	<!-- 피해자 상세정보 START // -->
	<div class="">
		<form id="personForm" name="personForm" method="post" onsubmit="return false;">
		<div style="height:20px;"></div>
		<div class="subTit type2 col-md-12">
			<h4>피해자 정보</h4>
		</div>
		<div style="height:30px;"></div>
		<div class="tableWrap type3">
			<table>
				<tr>
					<th style="width:10%;">ID</th>
					<td id="s_idntfcId"></td>
					<th style="width:15%;">피해자명</th>
					<td id="s_sufrerNm"></td>
<!-- 					<th style="width:25%;">피해지역 거주기간</th> -->
<!-- 					<td id="s_live"></td> -->
				</tr>
			</table>
		</div>
		<div style="height:20px;"></div>
		<div class="subTit type2 col-md-12">
			<h4>피해지역 정보</h4>
		</div>
		<div style="height:30px;"></div>
		<div class="tableWrap type3">
			<table>
				<tr>
					<th style="width:40%;">피해지역</th>
					<td id="s_bizArea"></td>
				</tr>
				<tr>
					<th>최초 오염 발생 년도</th>
					<td id="s_frstPollutnOcrnYr"></td>
				</tr>
				<tr>
					<th>최종 오염 종료 년도</th>
					<td id="s_lastPollutnOcrnYr"></td>
				</tr>
				<tr>
					<th>피해지역 거주기간</th>
					<td id="s_resiWhlCn"></td>
				</tr>	
			</table>
		</div>
		</form>
		
		<form id="registForm" name="registForm" method="post" onsubmit="return false;">
			<input id="mode" name="mode" type="hidden" />
			<input id="sn" name="sn" type="hidden" />
			<input id="idntfcId" name="idntfcId" type="hidden" />
			<input id="dclrYr" name="dclrYr" type="hidden" />
			<div style="height:20px;"></div>
			<div class="subTit type2 col-md-12">
				<h4>피해자 초본정보</h4>
			</div>
			<div style="height:30px;"></div>
			
			<div class="tabInnerFrom box formLayout">
				<div class="formGroup col-md-12">
					<span class="label col-md-2" style="margin:7px 0 0 0;">주소</span>
					<div class="col-md-10">
						<input type="text" id="abstrctAddr" name="abstrctAddr" class="app-box" style="width:100%" />
					</div>
				</div>                
				<div class="formGroup col-md-12">
					<span class="label col-md-2" style="margin:7px 0 0 0;">신고년도</span>
					<div class="inputWrap col-md-3">
						<div id="dclrYrCmb">
							<select id="dclrYrCmb" name="dclrYrCmb" style="width:120px"></select>
						</div>
					</div>
					<span class="label col-md-2" style="text-align: right; margin:7px 0 0 0;">사유</span>
					<div class="col-md-5">
						<input type="text" id="dclrResn" name="dclrResn" class="app-box" style="width:100%" />
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2" style="margin:7px 0 0 0;">이격거리</span>
					<div class="col-md-2">
						<input type="text" id="gapDstnc" name="gapDstnc" class="app-box" style="width:55px;" />
						<span style="display:inline-block; width:25%">Km</span>
					</div>
					<span class="label col-md-2" style="text-align: right; margin:7px 0 0 0;">좌표정보</span>
					<div class="col-md-3">
						<span style="display:inline-block; width:10%">X</span>
						<input type="text" id="lot" name="lot" class="app-box" style="width:85%" />
					</div>
					<div class="col-md-3">
						<span style="display:inline-block; width:10%">Y</span>
						<input type="text" id="lat" name="lat" class="app-box" style="width:85%" />
					</div>
				</div>
			</div>
			<div id="" style="padding:5px;text-align: right">
				<a href='#void' id='btnRegist' class='btn btn-default btn-md' style=''>신규등록</a>
				<a href='#void' id='btnSave' class='btn blue btn-default btn-md' style=''>저장</a>
			</div>
		</form>
		
		<div style="height:30px;"></div>
		<div style="width:100%;height:300px;">
			<table id="abstrctGrid" class="easyui-datagrid"></table>
		</div>
		<div id="" style="padding:5px;text-align: right">
			<a href='#void' id='btnDel' class='btn btn-default btn-md' style=''>선택삭제</a>
		</div>
	</div>
	<!-- 피해자 상세정보 END // -->
</div>
<%-- ############################# 내용 (종료) ############################# --%>