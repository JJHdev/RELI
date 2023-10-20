<%--
*******************************************************************************
***    명칭: listDmgeGrd.jsp
***    설명: 피해등급관리 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2022.12.22    LSH        First Coding.
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

		<div class="listWrap div2 box">
			<!-- 테이블 -->
			<div class="tableGroup">
				<div class="subTit type2">
					<h4 class="app-left app-pt20">피해등급관리목록</h4>
					<div class="btnDiv"></div>
				</div>
				<table id="appGrid" class="easyui-datagrid" style="height: 550px"></table>
			</div>

			<!-- 테이블 -->	
			<div class="formLayout">
				<div class="subTit type2">
					<h4 class="app-left app-pt20">등급별 요양생활수당 지급금액</h4>
					<div class="btnDiv"></div>
				</div>
				<div class="boxInner type2 box">
		            <form id="registForm" name="registForm" method="post" onsubmit="return false;">
		            	<input id="mode"  name="mode" type="hidden"/>
						<div class="formGroup col-md-12">
							<span class="col-md-3">기준년도</span>
							<div class="inputWrap col-md-9">
								<input type="hidden" id="dmgeGrdYrOrg" name="dmgeGrdYrOrg" />
								<select id="dmgeGrdYr" name="dmgeGrdYr" style="width:250px"/></select>
							</div>
						</div>
	     				<div class="formGroup col-md-12">
							<span class="col-md-3">기준중위소득</span>
							<div class="inputWrap col-md-9">
								<input type="text" id="crtrIncomeAmt" name="crtrIncomeAmt" style="width:250px"/>
								<span>원</span>
							</div>
						</div>
						<div class="app-formType1" style="width:100%;min-height:370px">
							<table id="appItemGrid"></table>
						</div>
						<div class="app-space10"></div>
						<!-- [2023.01.04 LSH] 현재보류처리함 
						<div class="app-right">
							<a href="#void" id="btnAppend" class="app-plus"><i class="fa fa-plus"></i></a>
						</div>
						 -->
		            </form>
				</div>
				<div class="btnWrap type1 app-right">
		             <app:button id="btnRegist" title="신규등록" cls="app-m3"/>
		             <app:button id="btnSave"   title="저장" cls="app-m3 blue"/>
				</div>
			</div>
		</div>

<%-- ############################# 내용 (종료) ############################# --%>
