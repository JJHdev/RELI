<%--
*******************************************************************************
***    명칭: listRole.jsp
***    설명: 역할관리 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.09.06    LSH        First Coding.
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

		<div class="boxWrap type1">
			<div class="boxInner">
				<div class="boxTit type1">
					<h3>검색조건</h3>
				</div>
				<div class="searchForm formLayout">
		            <form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
						<div class="formGroup">
							<div class="inputWrap">
								<input  id="srchText" name="srchText" type="text" placeholder="역할ID,역할명을 입력하세요." style="width:350px"/>
							</div>
						</div>
		            </form>
				</div>
			</div>
			<div class="btnWrap type1">
                <app:button id="btnSearch" title="검색" cls="app-m3 blue" />
                <app:button id="btnReset"  title="리셋" cls="app-m3" />
			</div>
		</div>


		<div class="app-space40"></div>

		<div class="listWrap div2 box">
			<!-- 테이블 -->
			<div class="tableGroup">
				<div class="subTit type2">
					<h4 class="app-left app-pt20">역할목록</h4>
					<div class="btnDiv">
					</div>
				</div>
				<div style="height: 350px">
					<table id="appGrid" class="easyui-datagrid"></table>
				</div>
			</div>

			<!-- 테이블 -->
			<div class="formLayout">
				<div class="subTit type2">
					<h4 class="app-left app-pt20" id="registTitle">역할등록</h4>
					<div class="btnDiv">
						<app:button id="btnRegist" title="등록" cls="btn blue" />
					</div>
				</div>
				<div class="boxInner type2 box">
	            <form id="registForm" name="registForm" method="post" onsubmit="return false;">
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">상위역할</span>
						<div class="inputWrap col-md-10">
							<select id="upRoleId" name="upRoleId" class="w100"></select>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">역할ID</span>
						<div class="inputWrap col-md-10">
                            <input id="roleId"  name="roleId" type="text" class="w100" maxlength="20" />
                            <input id="mode"    name="mode"   type="hidden"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">역할명</span>
						<div class="inputWrap col-md-10">
                            <input id="roleNm"  name="roleNm" type="text" class="w100" maxlength="30" />
						</div>
					</div>
	            </form>
				</div>
				<div class="btnWrap type1">
		             <app:button id="btnSave"   title="저장" cls="app-m3 blue"/>
		             <app:button id="btnRemove" title="삭제" cls="app-m3 blue"/>
		             <app:button id="btnUndo"   title="취소" cls="app-m3"/>
				</div>

			</div>

		</div>

<%-- ############################# 내용 (종료) ############################# --%>
