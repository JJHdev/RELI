<%--
*******************************************************************************
***    명칭: listHldy.jsp
***    설명: 공휴일관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2023.02.02    KSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                                <p>연도</p>
                                <select id="srchYear" name="srchYear" style="width:200px"></select>
                            </div>
                            <div class="inputWrap">
                                <p>공휴일명</p>
                                <input id="srchText" name="srchText" type="text" placeholder="공휴일명을 입력하세요." style="width:350px"/>
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
                    <h4 class="app-left app-pt20">공휴일목록</h4>
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
                    <h4 class="app-left app-pt20" id="registTitle">공휴일등록</h4>
                    <div class="btnDiv">
                        <app:button id="btnRegist" title="등록" cls="btn blue" />
                    </div>
                </div>
                <div class="boxInner type2 box">
                <form id="registForm" name="registForm" method="post" onsubmit="return false;">
                <input type="hidden" name="oldHldyYmd" id="oldHldyYmd"/>
                    <div class="formGroup col-md-12">
                        <span class="col-md-2 must">날짜</span>
                        <div class="inputWrap col-md-10">
                            <input id="hldyYmd"  name="hldyYmd" class="app-error"/>
                            <input id="mode"    name="mode"   type="hidden"/>
                        </div>
                    </div>
                    <div class="formGroup col-md-12">
                        <span class="col-md-2 must">공휴일명</span>
                        <div class="inputWrap col-md-10">
                            <input id="hldyNm"  name="hldyNm" type="text" class="w100" maxlength="20" />
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
