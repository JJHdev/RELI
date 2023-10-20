<%--
*******************************************************************************
***    명칭: listPapeMng.jsp
***    설명: 서류양식관리 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
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

<style>
#registForm input:disabled {background-color: #efefef}
</style>

<%-- ############################# 내용 (시작) ############################# --%>

		<div class="boxWrap type1">
			<div class="boxInner">
				<div class="boxTit type1">
					<h3>검색조건</h3>
				</div>
				<div class="searchForm formLayout">
		            <form id="searchForm" name="searchForm" method="post" onsubmit="return false;">

                        <%-- 서류업무구분코드 --%>
                        <input type="hidden" id="srchUpPapeCd" name="srchUpPapeCd" />

						<div class="formGroup">

                            <div class="inputWrap">
                                <p>업무구분</p>
                                <select id="srchPapeDtySeCd" name="srchPapeDtySeCd"></select>
                            </div>
                            <div class="inputWrap" id="srchAplySeArea">
                                <p>신청구분</p>
                                <select id="srchAplySeCd" name="srchAplySeCd"></select>
                            </div>

                            <!--
							<%-- 서류코드 --%>
							<div class="inputWrap">
								<input type="text" id="srchPapeCd" name="srchPapeCd" />
							</div>
							<%-- 신청구분코드 --%>
							<div class="inputWrap">
								<input type="text" id="srchAplySeCd" name="srchAplySeCd" />
							</div>
							<%-- 필수여부 --%>
							<div class="inputWrap">
								<input type="text" id="srchEsntlYn" name="srchEsntlYn" />
							</div>
							<%-- 사용여부 --%>
							<div class="inputWrap">
								<input type="text" id="srchUseYn" name="srchUseYn" />
							</div>
                             -->
						</div>
		            </form>
				</div>
			</div>
			<div class="btnWrap type1">
                <app:button id="btnSearch" title="검색" cls="app-m3 blue" />
                <app:button id="btnReset"  title="리셋" cls="app-m3" />
			</div>
		</div>

        <div class="tabWrap type3">
            <ul class="li-8 box" id="tabUpPape">
                <c:forEach var="item" items="${listUpPape}" varStatus="stats">
                    <li class="" data-code="<c:out value="${item.code}" />">
                        <a href="javascript:void(0);" data-code="<c:out value="${item.code}" />">
                            <c:out value="${item.text}" /></a></li>
                </c:forEach>
                <!-- <li class="on"><a href="#void">의료비</a></li>
                <li><a href="#void">요양생활수당</a></li>
                <li><a href="#void">장의비/유족보상비</a></li>
                <li><a href="#void">사망원인조사</a></li>
                <li><a href="#void">심의회결과</a></li> -->
            </ul>
        </div>

		<div class="app-space40"></div>

		<div class="listWrap div2 box">
			<!-- 테이블 -->
			<div class="tableGroup">
				<div class="subTit type2">
					<h4 class="app-left app-pt20">서류양식목록</h4>
					<div class="btnDiv">
					</div>
				</div>
				<div style="height: 500px">
					<table id="appGrid" class="easyui-datagrid"></table>
				</div>
			</div>

			<!-- 테이블 -->
			<div class="formLayout">
				<div class="subTit type2">
					<h4 class="app-left app-pt20" id="registTitle">서류양식등록</h4>
					<div class="btnDiv">
						<app:button id="btnRegist" title="등록" cls="btn blue" />
					</div>
				</div>
				<div class="boxInner type2 box">
	            <form id="registForm" name="registForm" method="post" onsubmit="return false;">
	            	<input id="mode"  name="mode" type="hidden"/>
					<div class="formGroup col-md-12">
						<span class="col-md-3 must">서류명</span>
						<div class="inputWrap col-md-9">
							<select id="papeCd" name="papeCd" style="width: 100%;"></select>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-3 must">업무구분</span>
						<div class="inputWrap col-md-9">
                            <input type="text" id="papeDtySeCdText" name="papeDtySeCdText" class="w100" disabled="disabled"/>
							<input type="hidden" id="papeDtySeCd" name="papeDtySeCd"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-3 must">신청구분</span>
						<div class="inputWrap col-md-9">
                            <select id="aplySeCd" name="aplySeCd"></select>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-3 must">필수여부</span>
						<div class="inputWrap col-md-9">
                            <div id="appEsntlYn"></div>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-3 must">사용여부</span>
						<div class="inputWrap col-md-9">
							<!-- <input type="text" id="useYn" name="useYn" class="w100" /> -->
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
