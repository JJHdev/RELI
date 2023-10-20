<%--
*******************************************************************************
***    명칭: listUserInfo.jsp
***    설명: 사용자정보 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.09.13    LSH        First Coding.
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
			                    <select id="srchRoleId" name="srchRoleId"></select>
							</div>
							<div class="inputWrap">
			                    <input  id="srchText" name="srchText" type="text" placeholder="아이디,이름,연락처,이메일,주소 등을 입력하세요." style="width:350px"/>
							</div>
						</div>
		            </form>
				</div>
			</div>
			<div class="btnWrap type1">
                <app:button id="btnSearch" title="조회" cls="app-m3 blue" />
			</div>
		</div>

		<div class="app-space40"></div>

		<div class="listWrap div2 box">
			<!-- 테이블 -->
			<div class="tableGroup">
				<div class="subTit type2">
					<h4>사용자목록</h4>
				</div>
				<div style="height: 800px">
					<table id="grid" class="easyui-datagrid"></table>
				</div>
			</div>
			<!-- 테이블 -->
			<div class="formLayout">
				<div class="subTit type2">
					<h4 id="registTitle">사용자등록</h4>
				</div>
				<div class="boxInner type2 box">
		            <form id="registForm" name="registForm" method="post" onsubmit="return false;">
						<div class="formGroup">
							<p class="must">사용자 ID&nbsp;</p>
							<div class="inputWrap">
							    <input id="mode"   name="mode"   type="hidden" />
							    <input id="userNo" name="userNo" type="hidden" />
								<input id="userId" name="userId" type="text"
									placeholder="20자 이내로 입력"
									maxlength="20"
									style="width:300px"/>
							    <button type="button" id="btnDuplicate" class="btn">중복확인</button>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">성명&nbsp;</p>
							<div class="inputWrap">
							    <input id="userNm" name="userNm" type="text" maxlength="20" style="width:300px" placeholder="성명" />
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">비밀번호&nbsp;</p>
							<div class="inputWrap">
							    <input id="pswd" name="pswd" type="password" placeholder="9자리 이상 대문자,특수문자,숫자 포함" maxlength="30" style="width:300px"/>
							    <button type="button" id="btnRestPswd" class="btn">비밀번호 초기화</button> <!-- data-toggle="modal" data-target="#infoMemberId" -->
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">비밀번호 확인&nbsp;</p>
							<div class="inputWrap">
							    <input id="pswdCnfm" name="pswdCnfm"
									type="password"
									placeholder="비밀번호 확인"
									maxlength="30"
									style="width:300px"/>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">생년월일&nbsp;</p>
							<div class="inputWrap">
							    <input  id="brdt" name="brdt" type="hidden"/>
							    <select id="bryy" name="bryy" style="width:100px"></select>
							    <select id="brmm" name="brmm" style="width: 95px"></select>
							    <select id="brdd" name="brdd" style="width: 95px"></select>
							</div>
						</div>
						<div class="formGroup">
	                        <p>E-mail</p>
							<div class="inputWrap">
							    <input id="emlAddr"    name="emlAddr"   type="hidden"/>
								<input id="emlAddr1"   name="emlAddr1"  type="text" style="width:100px" maxlength="30"/>
								<span class="bar">@</span>
								<input id="emlAddr2"   name="emlAddr2"  type="text" style="width:150px" maxlength="30"/>
							    <select id="emlDomain" name="emlDomain" style="width:150px"></select>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">휴대전화&nbsp;</p>
							<div class="inputWrap">
							    <input  id="mbtelNo"  name="mbtelNo"  type="hidden"/>
							    <select id="mbtelNo1" name="mbtelNo1" style="width:100px"></select>
							    <input  id="mbtelNo2" name="mbtelNo2" type="text" style="width:100px" maxlength="4"/>
							    <input  id="mbtelNo3" name="mbtelNo3" type="text" style="width:100px" maxlength="4"/>
							    <div id="appMblAt"></div>
							</div>
						</div>
						<div class="formGroup">
	                        <p>집/회사전화</p>
							<div class="inputWrap">
							    <input  id="telno"  name="telno"  type="hidden"/>
							    <select id="telno1" name="telno1" style="width:100px"></select>
							    <input  id="telno2" name="telno2" type="text" style="width:100px" maxlength="4"/>
							    <input  id="telno3" name="telno3" type="text" style="width:100px" maxlength="4"/>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">주소&nbsp;</p>
							<div class="inputWrap">
								<div>
								    <input id="zip" name="zip" type="text" maxlength="5" style="width:100px" readonly />
								    <button type="button" id="btnPost" class="btn">주소검색</button>
								</div>
								<div class="app-pt10">
								    <input id="addr"  name="addr"  type="text" maxlength="50" style="width:400px" readonly />
								</div>
								<div class="app-pt10">
								    <input id="daddr" name="daddr" type="text" maxlength="50" style="width:400px"/>
								</div>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="">성별&nbsp;</p>
							<div class="inputWrap">
							    <div id="appSxdst"></div>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">사용상태&nbsp;</p>
							<div class="inputWrap">
							    <div id="appUseStusCd"></div>
							</div>
						</div>
						<div class="formGroup">
	                        <p class="must">권한&nbsp;</p>
							<div class="inputWrap">
								<select id="roleId" name="roleId" style="width:200px"></select>
							</div>
						</div>
	            	</form>
				</div>
				<div class="btnWrap type1">
		             <app:button id="btnSave"   title="저장" cls="app-m3 blue" />
		             <app:button id="btnRemove" title="삭제" cls="app-m3 blue" />
		             <app:button id="btnUndo"   title="취소" cls="app-m3" />
				</div>
        	</div>
		</div>

<%-- ############################# 내용 (종료) ############################# --%>
