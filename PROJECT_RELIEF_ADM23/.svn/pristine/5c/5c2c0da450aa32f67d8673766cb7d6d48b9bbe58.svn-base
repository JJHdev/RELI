<%--
*******************************************************************************
***	명칭: adm/main.jsp
***	설명	: 관리자시스템 메인 페이지
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021-07-15    LSH        First Coding.
***	1.0      2021-10-19    LSH        디자인 적용
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
		
<!-- 탭 -->
<!-- ntarget : 2023-10-04 임시 숨김
<div class="main-tab tabWrap">
	<ul class="box" id="appReliefStatus"></ul>
</div>
 -->

<div class="app-space25"></div>
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
			<div class="formGroup" id="appAplySe"></div><%-- 신청구분 --%>
			<div class="formGroup" id="appAplyKnd"></div><%-- 신청종류 --%>
			<div class="formGroup" id="appProgress"></div><%-- 진행현황 --%>
			<div class="formGroup">
				<p>식별 ID</p>
				<div class="inputWrap">
					<div class="inputWrap">
						<input type="text" id="srchIdntfcId" name="srchIdntfcId"/>
						<!-- 
						<button class="btn">검색</button>
						 -->
					</div>
					<div class="inputWrap">
						<span>피해자명</span>
						<input type="text" id="srchSufrerNm" name="srchSufrerNm"/>
					</div>
					<div class="inputWrap">
						<span>신청자명</span>
						<input type="text" id="srchAplcntNm" name="srchAplcntNm"/>
					</div>
				</div>
			</div>
			<div class="formGroup" id="appBizArea"></div><%-- 지역구분 --%>
			<div class="formGroup">
				<span id="appDltncRslt"></span><%-- 조사결과 --%>
				<div class="inputWrap app-ml30">
					<span>지급여부</span>
					<div class="inputWrap">
						<select id="srchGiveYn" name="srchGiveYn"></select>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="btnWrap type1">
		<a href="#void" id="btnSearch" class="blue">조회</a>
		<a href="#void" id="btnReset">초기화</a>
		<a href="#void" id="btnExcel"  class="blue">엑셀다운로드</a>
	</div>
	</form>
</div>
<!-- 2022.12.05 SEARCHBOX TOGGLE END // -->
</div>
<div class="app-space50"></div>

<div class="listWrap div2 box">
	<div class="tableGroup">
		<!-- 종합현황목록 -->
		<table id="appGrid" class="easyui-datagrid" style="height:1120px"></table>
	</div>
	
	<div class="boxWrap type1">
		<div class="tabWrap type3">
			<ul class="li-5 box">
				<li><a href="#void">신청정보</a></li>
				<li><a href="#void">제출서류</a></li>
				<li><a href="#void">의료비내역</a></li>
				<li><a href="#void">요양생활수당</a></li>
				<li><a href="#void">피해조사</a></li>
			</ul>
		</div>
		<div class="app-space10"></div>
		<div id="appCard"></div>
		<div class="tabInner formLayout">
			<ul>
				<li>
					<!-- 신청정보 START // -->
					<form id="selectForm" name="selectForm" method="post" onsubmit="return false;">
						<input id="mode"        name="mode"        type="hidden" value=""        />
						<input id="gsUserNo"    name="gsUserNo"    type="hidden" value="${model.gsUserNo}"    />
						<input id="papeDtySeCd" name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
						<input id="aplySeCd"    name="aplySeCd"    type="hidden" value="${model.aplySeCd}"    /><%-- 신청구분코드 --%>
						<input id="aplyNo"      name="aplyNo"      type="hidden" value="${model.aplyNo}"      /><%-- 신청번호 --%>
						<input id="idntfcId"    name="idntfcId"    type="hidden" value=""  /><%-- 식별번호 --%>
						<input id="sufrerNo"    name="sufrerNo"    type="hidden" value=""  /><%-- 피해자번호 --%>
						<input id="prgreStusCd" name="prgreStusCd" type="hidden" value=""  /><%-- 진행상태코드 --%>
						<input id="aplyYmd"     name="aplyYmd"     type="hidden" value=""  /><%-- 신청일자 --%>
						<div id="appRelief" style="max-height:1250px"></div>
					</form>
					<!-- 신청정보 END // -->
				</li>
				<li>
					<!-- 제출서류 START // -->
					<div class="app-space40"></div>
					<div class="tableWrap type1 app-scroll" style="max-height:100%;" id="appAplyFileList"></div>
					<!-- 제출서류 END // -->
				</li>
				<li>
					<!-- 의료비내역 START // -->
					<form id="mcpForm" name="mcpForm" method="post" onsubmit="return false;" >
						<input type="hidden" name="mode"      />
						<input type="hidden" name="bizAreaCd" />
						<input type="hidden" name="bizOder"   />
						<input type="hidden" name="exmnOder"  />
						<input type="hidden" name="aplyNo"    />
						<input type="hidden" name="rcognStusCd" />  
						<div class="app-space40"></div>
						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-12">
								<span class="label col-md-2">보유질환</span>
								<div class="inputWrap col-md-10">
									<input  id="srchSckwndText" name="srchSckwndText" type="text" placeholder="질병코드, 질병명 등을 입력하세요." style="width:100%"/>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">진료일자</span>
								<div class="inputWrap col-md-10">
									<input type="text" id="srchRcperStdt" name="srchRcperStdt" value="" class="easyui-datebox" /> ~
									<input type="text" id="srchRcperEndt" name="srchRcperEndt" value="" class="easyui-datebox" />
								</div>
							</div>
						</div>
						<div class="app-space10"></div>
						<div class="btnDiv">
							<a href="#void" id="btnMcpSearch" class="btn blue">조회</a>
							<a href="#void" id="btnMcpReset"  class="btn">초기화</a>
							<a href="#void" id="btnMcpExcel"  class="btn">엑셀다운로드</a>
						</div>
						<div class="app-space10"></div>

						<div style="width:100%;height:400px">
							<table id="mcpGrid"></table>
						</div>
						<div class="app-space10"></div>

						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-12 app-r">
								<span class="label col-md-6 app-pt10">의료비 총 지급액 : </span>
								<div class="inputWrap col-md-6">
									<div class="app-box app-r" id="s_mcpTotAmt"></div>
								</div>
							</div>
							<div class="formGroup col-md-12 app-r">
								<span class="label col-md-6 app-pt10">검색대상 의료비 합계 : </span>
								<div class="inputWrap col-md-6">
									<div class="app-box app-r" id="s_mcpSrchAmt"></div>
								</div>
							</div>
						</div>
					</form>
					<!-- 의료비내역 END // -->
				</li>
				<li>
					<!-- 요양생활수당 START // -->
					<form id="rcpForm" name="rcpForm" method="post" onsubmit="return false;" >
						<input type="hidden" name="mode"      />
						<input type="hidden" name="bizAreaCd" />
						<input type="hidden" name="bizOder"   />
						<input type="hidden" name="exmnOder"  />
						<input type="hidden" name="aplyNo"    />
						<div id="appRcpInfo"></div>
						<div class="subTit type2 app-type2">
							<h4>월별 요양급여 지급일</h4>
						</div>
						<div class="app-small-grid" style="width:100%;height:200px">
							<table id="rcpGrid"></table>
						</div>
					</form>
					<!-- 요양생활수당 END // -->
				</li>
				<li>
					<!-- 피해조사 START // -->
					<form id="exmnForm" name="exmnForm" method="post" onsubmit="return false;" >
						<input type="hidden" name="mode"      />
						<input type="hidden" name="bizAreaCd" />
						<input type="hidden" name="bizOder"   />
						<input type="hidden" name="exmnOder"  />
						<input type="hidden" name="aplyNo"    />
						<div id="appPrptExmn"></div>
						<div id="appMnsvy"></div>
					</form>
					<!-- 피해조사 END // -->
				</li>
			</ul>
		</div>	
	</div>
	
	<div class="detailGroup">
	</div>
</div>


<%-- ############################# 내용 (종료) ############################# --%>
