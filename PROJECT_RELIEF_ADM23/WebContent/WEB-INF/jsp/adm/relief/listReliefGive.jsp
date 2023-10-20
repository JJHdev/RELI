<%--
*******************************************************************************
***    명칭: listReliefGive.jsp
***    설명: 구제급여 지급
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
***    1.0      2021.10.28    LSH        디자인적용 / 화면개발
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
	<h3>의료급여 지급 대상 목록</h3>
</div>
<div class="app-space25"></div>


<div class="listWrap div2 box">
	<!-- 목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnExcel" class="btn">엑셀다운로드</a>
		</div>
		<div style="width:100%;height:600px">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	<!-- 목록 END // -->
	</div>
	<div class="formLayout">
	
		<!-- 개인정보 START // -->
		<div class="subTit type2">
			<h4>의료급여</h4>
		</div>
	
		<!-- 의료비 START // -->
		<form id="mcpForm" name="mcpForm" method="post" onsubmit="return false;" >
			<input type="hidden" name="mode"      />
			<input type="hidden" name="act"       />
			<input type="hidden" name="bizAreaCd" />
			<input type="hidden" name="bizOder"   />
			<input type="hidden" name="aplyNo"    />
			
			<div class="boxInner type3">
				<div class="formGroup col-md-12">
					<span class="label col-md-2">조사차수</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="exmnOder" name="exmnOder" value="" class="app-readonly w100" readonly />
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">조회기간</span>
					<div class="inputWrap col-md-10">
						<input type="text" id="rvwBgngYmd" name="rvwBgngYmd" maxlength="10" style="width:34%"/> ~
						<input type="text" id="rvwEndYmd"  name="rvwEndYmd"  maxlength="10" style="width:34%"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">기인정금액</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="lgcyRcognAmt" name="lgcyRcognAmt" maxlength="15" class="app-number w100">
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">중복금액</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="dpcnAmt" name="dpcnAmt" maxlength="15" class="app-number w100">
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">환수금액</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="rdmAmt" name="rdmAmt" maxlength="15" class="app-number w100">
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">회수필요금액</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="rtrvlNeedAmt" name="rtrvlNeedAmt" maxlength="15" class="app-number w100">
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">인정금액</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="rcognAmt" name="rcognAmt" maxlength="15" class="app-number w100">
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">환수입금일자</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="rdmDpstYmd" name="rdmDpstYmd" maxlength="10" class="w100"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">지급결정일자</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="giveDcsnYmd" name="giveDcsnYmd" maxlength="10" class="app-readonly w100" readonly />
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">지급일자</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="giveYmd" name="giveYmd" maxlength="10" class="w100"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">지급금액 (회수금액제외)</span>
					<div class="inputWrap col-md-4">
						<input type="text" id="giveAmt" name="giveAmt" maxlength="15" class="app-number app-readonly w100" readonly />
					</div>
				</div>
			</div>
			<div class="app-space10"></div>

			<div class="btnDiv">
				<a href="#void" id="btnMcpSave" class="btn blue">저장</a>
			</div>
		</form>
		<!-- 의료비 END // -->
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
