<%--
*******************************************************************************
***    명칭: listPrptExmn.jsp
***    설명: 예비조사 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
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
		
<%-- 상단 탭형태 서브메뉴 (필요한영역에만 표출) --%>
<div id="appTabSubMenu"></div>

<!-- 검색영역 -->
<div class="searchConditions boxWrap type1">
	<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
		<div class="searchConditions-wrap boxInner">
			<div class="boxTit type1">
				<h3>검색조건</h3>
			</div>
			<div class="searchForm formLayout">
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
					<p>피해자명</p><%-- 2023.01.06 피해자명 검색조건 추가 --%>
					<div class="inputWrap">
						<div class="inputWrap">
							<input type="text" id="srchSufrerNm" name="srchSufrerNm"/>
						</div>
						<div class="inputWrap">
							<span>신청자명</span><%-- 2023.01.06 신청자명 검색조건 추가 --%>
							<input type="text" id="srchAplcntNm" name="srchAplcntNm"/>
						</div>
						<div class="inputWrap"><%-- 2023.01.06 식별ID 검색조건 추가 --%>
							<span>식별 ID</span>
							<input type="text" id="srchIdntfcId" name="srchIdntfcId"/>
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
<div class="app-space10"></div>

<div class="subTit type1">
	<h3>예비조사 대상자 목록</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">

	<!-- 예비조사목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnExcel" class="btn">엑셀다운로드</a>
		</div>
		<div style="width:100%;height:650px">
			<table id="appGrid" class="easyui-datagrid"></table>
		</div>
	<!-- 예비조사목록 END // -->
	</div>

	<div class="boxWrap type1">
		<div class="tabWrap type3">
			<ul class="li-2 box">
				<li><a href="#void">예비조사내역</a></li>
				<li><a href="#void">심의회결과</a></li>
			</ul>
		</div>
		<div class="app-space10"></div>
		
		<div class="tabInner formLayout">
			<ul>
				<li>
					<!-- 예비조사내역 START // -->
					<form id="exmnForm" name="exmnForm" method="post" onsubmit="return false;" >
						<input type="hidden" name="mode"      />
						<input type="hidden" name="bizAreaCd" />
						<input type="hidden" name="bizOder"   />
						<input type="hidden" name="exmnOder"  />
						<input type="hidden" name="aplyNo"    />
					
						<!-- 개인정보 -->
						<div class="app-space10"></div>
						<div class="subTit type2">
							<h4>피해자 정보</h4>
						</div>
						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-6">
								<span class="label col-md-4">식 별 ID</span>
								<div class="inputWrap col-md-7">
									<div class="app-box" id="s_idntfcId"></div>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">피해자명</span>
								<div class="inputWrap col-md-8">
									<div class="app-box" id="s_sufrerNm"></div>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">피해지역</span>
								<div class="inputWrap col-md-7">
									<div class="app-box" id="s_bizAreaNm"></div>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">신청종류</span>
								<div class="inputWrap col-md-10">
									<div class="app-box" id="s_aplyKndNm"></div>
								</div>
							</div>
						</div>
	
						<!-- 조사정보 -->
						<div class="app-space10"></div>
						<div class="subTit type2">
							<h4>구제급여 지급 적합 여부 상세 정보</h4>
						</div>
						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-6">
								<span class="label col-md-4">영향범위</span>
								<div class="inputWrap col-md-7">
									<div class="app-box" id="s_affcScopeCn"></div>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">영향범위 내 거주여부</span>
								<div class="inputWrap col-md-8">
									<div id="appResiYn"></div>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">거주이력</span>
								<div class="inputWrap col-md-10">
									<div class="app-small-grid" style="width:100%;height:170px">
										<table id="resiGrid"></table>
									</div>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">거주기간</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="resiWhlCnt" name="resiWhlCnt" maxlength="3" class="app-w50 number">
									<span class="app-inline">년</span>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">노출기간</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="expsrWhlCnt" name="expsrWhlCnt" maxlength="3" class="app-w50 number">
									<span class="app-inline">년</span>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">영상자료<br>판독내용</span>
								<div class="inputWrap col-md-10">
									<textarea id="vidoDataReoutCn" name="vidoDataReoutCn" maxlength="1300" class="w100"></textarea>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">인정질환 보유여부</span>
								<div class="inputWrap col-md-10">
									<div id="appHoldYn"></div>
								</div>
							</div>
						</div>
						<div class="app-space10"></div>
	
						<!-- 생체지표 [2021.11.26 현재사용안함]
						<div class="subTit type2">
							<h4>생체지표</h4>
						</div>
						<div class="app-small-grid" style="width:100%;height:170px">
							<table id="lbdyGrid"></table>
						</div>
						<div id="lbdyToolbar" class="formGroup" style="padding:2px 2px;">
	        				<div id="appLbdyYn"></div>
	    				</div>
						<div class="app-space10"></div>
						 -->
	
						<div class="btnDiv">
							<a href="#void" id="btnExmnSave" class="btn right">저장</a>
						</div>
					</form>
					<!-- 예비조사내역 END // -->
				</li>
				<li>
					<!-- 심의회결과 START // -->
					<form id="rsltForm" name="rsltForm" method="post" enctype="multipart/form-data" onsubmit="return false;" >
						<input type="hidden" name="mode"      />
						<input type="hidden" name="bizAreaCd" />
						<input type="hidden" name="bizOder"   />
						<input type="hidden" name="exmnOder"  />
						<input type="hidden" name="aplyNo"    />
						<!-- 심의회결과 -->
						<div class="subTit type2">
							<h4>심의결과</h4>
						</div>
						<div class="formLayout tabInnerFrom box">
							<div class="formGroup col-md-12">
								<span class="label col-md-2">개최일자</span>
								<div class="inputWrap col-md-10">
									<input type="text" id="dltncOpmtYmd" name="dltncOpmtYmd" maxlength="10" class="easyui-datebox" />
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">심의회 결과</span>
								<div class="inputWrap col-md-10">
									<div id="appDltncRslt"></div>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">부적합 사유</span><%-- 2023.01.06 '적합/부적합 사유'를 '부적합 사유'로 변경 --%>
								<div class="inputWrap col-md-10">
									<textarea id="dltncRsltResn" name="dltncRsltResn" maxlength="65" class="w100"></textarea>
								</div>
							</div>
						</div>
						<div class="app-space10"></div>
	
						<!-- 서류조회 -->
						<div class="subTit type2">
							<h4>관련 서류 확인</h4>
						</div>
						<div class="app-small-grid" style="width:100%;height:200px">
							<table id="fileGrid"></table>
						</div>
						<div class="app-space10"></div>
	
						<!-- 추가서류등록 -->
						<div class="subTit type2">
							<h4>추가 서류 등록</h4>
						</div>
						<div class="formLayout tabInnerFrom box">
		     				<div class="formGroup col-md-12">
								<span class="col-md-2">첨부파일</span>
								<div class="inputWrap col-md-10">
									<%-- 첨부파일박스 영역 --%>
									<div id="appFileBox"></div>
								</div>
							</div>
						</div>
						<div class="app-space10"></div>
	
						<div class="btnDiv">
							<a href="#void" id="btnRsltSave" class="btn right">저장</a>
						</div>
					</form>
					<!-- 심의회결과 END // -->
				</li>
			</ul>
		</div>
				
	</div>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
