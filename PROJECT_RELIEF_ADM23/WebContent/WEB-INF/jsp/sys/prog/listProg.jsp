<%--
*******************************************************************************
***    명칭: listProg.jsp
***    설명: 프로그램관리 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.09.05    LSH        First Coding.
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
			                    <select id="srchSysCd" name="srchSysCd" style="width:250px"></select>
							</div>
							<div class="inputWrap">
			                    <select id="srchMenuId" name="srchMenuId" style="width:250px"></select>
							</div>
							<div class="inputWrap">
								<input  id="srchText" name="srchText" type="text" placeholder="프로그램 ID,명칭,URL 등을 입력하세요." style="width:350px"/>
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
					<h4 class="app-left app-pt20">프로그램목록</h4>
					<div class="btnDiv">
					</div>
				</div>
				<div style="height: 600px">
					<table id="appGrid" class="easyui-datagrid"></table>
				</div>
			</div>

			<!-- 테이블 -->
			<div class="formLayout">
				<div class="subTit type2">
					<h4 class="app-left app-pt20" id="registTitle">프로그램등록</h4>
					<div class="btnDiv">
						<app:button id="btnRegist" title="등록" cls="btn blue" />
					</div>
				</div>
				<div class="boxInner type2 box">
	            <form id="registForm" name="registForm" method="post" onsubmit="return false;">
	            	<input id="mode"  name="mode" type="hidden"/>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">시스템구분</span>
						<div class="inputWrap col-md-5">
							<select id="sysCd" name="sysCd" class="w100"></select>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">프로그램ID</span>
						<div class="inputWrap col-md-10">
                            <input id="progId" name="progId" type="text" class="w100" maxlength="20" />
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">프로그램명</span>
						<div class="inputWrap col-md-10">
							<input id="progNm" name="progNm" type="text" class="w100" maxlength="100"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">프로그램URL</span>
						<div class="inputWrap col-md-10">
							<input id="progUrl" name="progUrl" type="text" class="w100" maxlength="150"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2">연결메뉴</span>
						<div class="inputWrap col-md-10">
							<select id="menuId" name="menuId" class="w100"></select>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2">순　　서</span>
						<div class="inputWrap col-md-5">
							<input id="progOrdr" name="progOrdr" type="text" class="w100" maxlength="3"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2">타    입</span>
						<div class="inputWrap col-md-5">
							<select id="progTy" name="progTy" class="w100"></select>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">사용여부</span>
						<div class="inputWrap col-md-5">
							<div id="appUseYn"></div>
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
