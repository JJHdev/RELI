<%--
*******************************************************************************
***    명칭: listLwstPrgre.jsp
***    설명: 취약계층소송지원 - 소송개요현황
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>

<div class="boxWrap type1">
	<div class="boxInner">
		<div class="boxTit type1">
			<h3>검색조건</h3>
		</div>
		<div class="searchForm formLayout">
			<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
				<div class="formGroup" id="appAplyTermBox"></div>
				<div class="formGroup">
					<p>신청자명</p>
					<div class="inputWrap">
						<div class="inputWrap">
							<input type="text" id="srchAplcntNm" name="srchAplcntNm" />
						</div>
						<div class="inputWrap">
							<span>피해지역</span>
							<input type="text" id="srchDmgeArea" name="srchDmgeArea" />
						</div>
					</div>
				</div>
				<div class="formGroup" id="prgreStusCd"></div>
				<div class="formGroup" id="appSprtSeCdList"></div>
			</form>
		</div>
	</div>
	<div class="btnWrap type1">
		<a href="#void" id="btnSearch" class="blue">조회</a>
	</div>
</div>

<div style="height: 30px;"></div>

<div class="subTit type1">
	<h3>취약계층 소송지원 진행 현황</h3>
</div>
<div class="app-space25"></div>

<div class="listWrap div2 box">
	<!-- 신청접수목록 START // -->
	<div class="tableGroup">
		<div class="btnDiv">
			<a href="#void" id="btnExcel" class="btn">엑셀다운로드</a>
		</div>
		<div style="height: 900px">
			<table id="grid" class="easyui-datagrid"></table>
		</div>
		<!-- 신청접수목록 END // -->
	</div>

	<div class="formLayout">
		<!-- 개인정보 START // -->
		<div class="subTit type2">
			<h4>개인정보</h4>
		</div>
		<form id="registForm" name="registForm" method="post" onsubmit="return false;">
			<input id="mode" name="mode" type="hidden" />
			<input id="rgtrNo" name="rgtrNo" type="hidden" value="${model.rgtrNo}" />
			<input id="aplyNo" name="aplyNo" type="hidden" value="${model.aplyNo}" />
			<input id="rgtrNo" name="rgtrNo" type="hidden" value="${model.rgtrNo}" />
			<input id="incdntMngNo" name="incdntMngNo" type="hidden" value="${model.incdntMngNo}" />
			<div class="boxWrap">
				<div class="tabWrap type2">
					<ul>
						<li class="on" id="aplcntLwstInfo">
							<a href="#void">신청인 정보</a>
						</li>
						<li class="" id="respLwstInfo">
							<a href="#void">피신청인 정보</a>
						</li>
						<li class="" id="docuLwstInfo">
							<a href="#void">제출서류</a>
						</li>
						<li class="" id="addLwstInfo">
							<a href="#void">소송 지원 정보</a>
						</li>
					</ul>
				</div>
				<div class="tabInner formLayout" style="margin-top: -7px;">
					<ul>
						<li class="tabInnerFrom box on">
							<div class="formGroup col-md-6">
								<span class="label col-md-4">신청인 성명</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="aplcntNm" name="aplcntNm" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">생년월일</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="aplcntBrdt" name="aplcntBrdt" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">주 소</span>
								<div class="inputWrap col-md-10">
									<input type="text" id="aplcntAddrLwst" name="aplcntAddrLwst" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">유선전화</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="aplcntMbtelNo" name="aplcntMbtelNo" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">전자우편주소</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="aplcntEmlAddr" name="aplcntEmlAddr" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">신청 요건</span>
								<div class="inputWrap col-md-8">
									<select id="aplySeCd" name="aplySeCd" class="w100">
										<option selected>선택</option>
										<option value="R2201">장애인</option>
										<option value="R2202">고령자</option>
										<option value="R2203">국가보훈대상자</option>
										<option value="R2204">북한이탈주민</option>
										<option value="R2205">고용촉진지원금수급자</option>
										<option value="R2206">저소득층</option>
										<option value="R2299">기타</option>
									</select>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">신청 상태</span>
								<div class="inputWrap col-md-8">
									<select id="prgreStusCd" name="prgreStusCd" class="w100" >
										<option selected>선택</option>
										<option value="20">접수</option>
										<option value="03">지원검토</option>
										<option value="04">지원결정</option>
										<option value="06">소송진행중</option>
										<option value="07">소송완료</option>
									</select>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">피해 지역 </span>
								<div class="inputWrap col-md-8">
									<input type="text" id="etcDmgeArea" name="etcDmgeArea" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">
									사업자 등록번호<small>(법인·단체명)</small>
								</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="aplcntBrno" name="aplcntBrno" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">지원 구분</span>
								<div class="inputWrap col-md-10">
<!-- 									<div id="appSprtSeCd"></div> -->
								<input type="checkbox" id="RW1" name="sprtSeCdList" value="RW1">
								<label for="RW1">법률자문</label>								
								<input type="checkbox" id="RW2" name="sprtSeCdList" value="RW2">
								<label for="RW2">서류검토</label>								
								<input type="checkbox" id="RW3" name="sprtSeCdList" value="RW3">
								<label for="RW3">소송대리</label>
								</div>
							</div>
						</li>
						<li class="tabInnerFrom box">
							<div class="formGroup col-md-6">
								<span class="label col-md-4">피신청인 성명</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="respdntNm" name="respdntNm" class="w100"  readonly/>
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">생년월일</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="respdntBrdt" name="respdntBrdt" class="w100"  readonly/>
								</div>
							</div>
							<div class="formGroup col-md-12">
								<span class="label col-md-2">주 소</span>
								<div class="inputWrap col-md-10">
									<input type="text" id="respdntAddrLwst" name="respdntAddrLwst" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">유선전화</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="respdntTelno" name="respdntTelno" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">전자우편주소</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="respdntEmlAddr" name="respdntEmlAddr" class="w100" readonly />
								</div>
							</div>
							<div class="formGroup col-md-6">
								<span class="label col-md-4">
									사업자 등록번호<small>(법인·단체명)</small>
								</span>
								<div class="inputWrap col-md-8">
									<input type="text" id="respdntBrno" name="respdntBrno" class="w100" readonly />
								</div>
							</div>
						</li>
						<li class="tabInnerFrom box">
							<div class="subTit type2">
								<h4 class="app-left app-pt">제출서류목록</h4>
								<div class="app-both"></div>
							</div>
							<div class="tableWrap type1 app-scroll" style="max-height: 200px;" id="appAplyFileList"></div>
						</li>

						<li class="tabInnerFrom box" id="addLwstInfo" >
							<div class="subTit type2">
								<h4 class="app-left app-pt">소송 지원</h4>
								<div class="app-right btnDiv">
									<a href="#void" id="btnSupportLwst" class="btn blue">지원 등록</a>
								</div>
								<div class="app-both"></div>
							</div>
							<div class="tableWrap type1 app-scroll" id="appLwstSearchTable" style="height: 100px;"></div>
							<div style="height: 18px;"></div>
							<div class="formLayout boxWrap type1" id="viewListLwstIncdnt">
								<div class="formList boxInner">
									<div class="formGroup box">
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>사건 번호</span>
											<input type="text" id="incdntNo" name="incdntNo" class="w100" readonly />
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;" >
											<span>사건 명</span>
											<input type="text" id="incdntNm" name="incdntNm" class="w100"  readonly/>
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>원 고</span>
											<input type="text" id="aplcntNmCnt" name="aplcntNmCnt" class="w100" readonly/>
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>피 고</span>
											<input type="text" id="respdntNm" name="respdntNm" class="w100" readonly />
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>소 가</span>
											<input type="text" id="lwstPricesAmt" name="lwstPricesAmt" class="w100" readonly />
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>인지액</span>
											<input type="text" id="papstmpAmt" name="papstmpAmt" class="w100" readonly />
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>소송일</span>
											<input type="text" id="lwstYmd" name="lwstYmd" class="w100" readonly />
										</div>
										<div class="inputWrap" style="margin-left: 5px; margin-right: 5px;">
											<span>재판부</span>
											<input type="text" id="jdgmtDeptNm" name="jdgmtDeptNm" class="w100" readonly />
										</div>
									</div>
								</div>
								<div style="height: 18px;"></div>
								<div class="subTit type2">
									<h4>향후기일</h4>
								</div>
								<div class="formLayout tabInnerFrom box" style="height: 270px">
									<table id="dtltGrid" class="easyui-datagrid"></table>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>



			<div style="height: 18px;"></div>

			<div class="boxInner type3" id="dmgInfo">
				<div class="formGroup col-md-12">
					<span class="label col-md-2">피해내용</span>
					<div class="inputWrap col-md-10">
						<div class="inputWrap inLabel col-md-12">
							<label for="">
								피해발생
								<br>
								(일시·장소)
							</label>
							<input type="text" id="dmgeOcrnPlce" name="dmgeOcrnPlce" class="w100" style="height: 55px;" />
						</div>
						<div class="inputWrap inLabel col-md-12" style="margin-top: 10px;">
							<label for="">내용 및 금액</label>
							<input type="text" id="dmgeCn" name="dmgeCn" class="w100" />
						</div>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">
						소송지원
						<br>
						신청내용
					</span>
					<div class="inputWrap col-md-10">
						<div class="inputWrap inLabel col-md-12" style="margin-top: 10px;">
							<label for="">취지 및 이유</label>
							<input type="text" id="aplyObjetCn" name="aplyObjetCn" class="w100" />
						</div>
						<div class="inputWrap inLabel col-md-12" style="margin-top: 10px;">
							<label for="">
								내용 및 지원
								<br>
								신청금액
							</label>
							<input type="text" id="aplyCn" name="aplyCn" class="w100" style="height: 55px;" />
						</div>
					</div>
				</div>
			</div>
		</form>

		<!-- 이력관리 START // -->
		<div class="app-space25"></div>
		<div class="subTit type2" id="historyInfo">
			<h4 class="app-left app-pt20">이력관리</h4>
			<div class="app-right btnDiv">
				<a href="#void" id="btnHistory" class="btn blue">이력등록</a>
			</div>
			<div class="app-both"></div>
			<div class="tableWrap type1 app-scroll" id="appHistoryTable" style="height: 100px;"></div>
		</div>
		<!-- 이력관리 END // -->

		<div style="height: 20px;"></div>


	</div>
</div>

<div class="app-space10"></div>
<div class="app-right btnDiv">
	<a href="#void" id="btnCancel" class="btn">신청 취소</a>
	<a href="#void" id="btnReceipt" class="btn blue">진행 저장</a>
</div>

<%-- 신청접수 팝업 --%>
<div id="appPopupReceipt"></div>
<%-- 보완요청 팝업 --%>
<div id="appPopupSplemnt"></div>
<%-- 이력관리 팝업 --%>
<div id="appPopupHistory"></div>
<%-- 취소 팝업 --%>
<div id="appPopupCancel"></div>
<%-- 소송지원 팝업 --%>
<div id="appPopupLwstSupport"></div>

<%-- ############################# 내용 (종료) ############################# --%>
