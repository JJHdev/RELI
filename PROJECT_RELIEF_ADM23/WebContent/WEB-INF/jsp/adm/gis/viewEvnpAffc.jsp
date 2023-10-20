<%--
*******************************************************************************
***    명칭: viewEvnpAffc.jsp
***    설명: 초본 주소 등록
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2022.12.20    CDH        First Coding.
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


<div id="page-body" class="gis-wrap">
	<div class="side">
		<ul>
<!-- 			<li class="reset"><a href="#void"></a><span>refresh</span></li>	 -->
			<li class="base on"><a href="#void"></a><span>배경지도</span></li>		
			<li class="ruler"><a href="#void"></a><span>거리</span></li>
			<li class="radar"><a href="#void"></a><span>반경</span></li>
			<li class="crop"><a href="#void"></a><span>면적</span></li>
			<li class="save"><a href="#void"></a><span>저장</span></li>
			<li class="plus"><a href="#void"></a><span>확대</span></li>
			<li class="minus"><a href="#void"></a><span>축소</span></li>
		</ul>
	</div>
	<div class="gis-menu">
		<div class="gis-search">
			<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
				<h3>검색조건</h3>
				<div class="gis-search-form">
					<div class="inputWrap">
						<select id="loadListAREA" class="easyui-combobox" data-options="label:'피해지역'" name="bizArea" style="width:100%;">
							<option value="00">전체</option>
							<c:forEach var="area" items="${area}">
								<option value="${area.bizAreaCd}">${area.bizArea}</option>
							</c:forEach>
						</select>
					</div>
					<div style="height:10px"></div>
					<div class="inputWrap">
						<input type="text" class="easyui-textbox" id="srchidntfcId" name="idntfcId" data-options="label:'식별 ID'" style="width:100%" />
					</div>
					<div style="height:10px"></div>
					<div class="inputWrap">
						<input type="text" class="easyui-textbox" id="srchSufrerNm" name="sufrerNm" data-options="label:'피해자명'" style="width:100%" />
					</div>
					<div style="height:10px"></div>
					<div class="btnWrap type0">
						<a href="#void" id="btnSearch" class="blue">조회</a>
						<a href="#void" id="btnReset" class="">초기화</a>
					</div>
				</div>
			</form>
		</div>
		<div class="gis-result">
			<h3>조회결과</h3>
			<div class="tableGroup type4" style="width:100%;height:356px;">
				<table id="appGrid" class="easyui-datagrid">
				</table>
			</div>
		</div>
	</div>
	
	<div class="gis-map">
<!-- 		<a class="gis-map-layerBtn" href="#void" id="openAdminGps">레이어띄우기</a> -->
		<div id="map"></div>
		
		<div class="">
			<form id="personForm" name="personForm" method="post" onsubmit="return false;">
				<div class="gis-map-layer on">
					<div class="gis-map-layer-wrap">
						<div class="tableWrap type3">
							<table>
								<tr>
									<th style="width:10%;">ID</th>
									<td id="s_idntfcId"></td>
									<th style="width:15%;">피해자명</th>
									<td id="s_sufrerNm"></td>
									<th style="width:25%;">피해지역 거주기간</th>
									<td id="s_live"></td>
								</tr>
							</table>
						</div>
						<div class="tableGroup type4" style="width:100%;height:133px;">
							<table id="abstrctGrid" class="easyui-datagrid">
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="popup" class="ol-popup" style="display:none;">
      <a href="#" id="popup-closer" class="ol-popup-closer"></a>
      <div id="popup-content"></div>
    </div>
	
</div>



<%-- ############################# 내용 (종료) ############################# --%>