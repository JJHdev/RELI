<%--
*******************************************************************************
***    명칭: listBizMng.jsp
***    설명: 사업관리 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
*******************************************************************************
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld"%>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags"%>

<%-- ############################# 내용 (시작) ############################# --%>

<div class="boxWrap type1">
<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
	<div class="boxInner">
		<div class="boxTit type1">
			<h3>검색조건</h3>
		</div>
		<div class="searchForm formLayout">
			<div class="formGroup">
				<p>지역구분</p>
				<div class="inputWrap cal">
					<select id="srchBizArea" name="srchBizArea" style="width: 230px"></select>
				</div>
				<div class="inputWrap">
					<span>사업차수</span> <select id="srchBizOder" name="srchBizOder"
						style="width: 230px"></select>
				</div>
			</div>
		</div>
	</div>
	<div class="btnWrap type1">
		<a href="#void" class="blue" id="btnSearch">조회</a>
	</div>
	</form>
</div>

<div class="subTit type1">
	<h3>지역목록</h3>
</div>

<div style="height: 26px;"></div>

<div class="listWrap div2 box">
	<!-- 왼쪽테이블(지역목록) -->

	<div class="tableGroup"><table id="appGrid" class="easyui-datagrid" style="height: 450px"></table></div>

	<!-- 오른쪽 정보 -->
	<div class="boxWrap">
		<div class="tabWrap type2">
			<ul>
				<li class="on"><a href="#void">신규지역등록</a></li>
				<li class=""><a href="#void">지역차수등록</a></li>
				<li class=""><a href="#void">사업관리</a></li>
			</ul>
		</div>
		<form id="registForm" name="registForm" method="post" onsubmit="return false;">
		<div class="tabInner formLayout" style="margin-top: -7px;">
			<ul>
			<!--  신규 지역 등록 탭 -->
				<li class="tabInnerFrom box on" id="newAreaTab">
				<!-- 피해지역 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3 must">피해지역</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="newBizArea" name="newBizArea" class="w100" maxlength="50" />
						</div>
					</div>
					<!-- 피해상세 지역 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3 must">피해상세지역</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="newBizDtlArea" name="newBizDtlArea" class="w100" maxlength="50" />
						</div>
					</div>
					<!-- 사업내용 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">사업내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="newBizCn" name="newBizCn" class="w100" maxlength="650" />
						</div>
					</div>
					<!-- 오염원내용 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">오염원내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="newPolusrcCn" name="newPolusrcCn" class="w100" maxlength="100" />
						</div>
					</div>
					<!-- 유해인자노출내용 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">유해인자노출내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="newHrmflns" name="newHrmflns" class="w100" maxlength="100" />
						</div>
					</div>
					<!-- 건강피해내용 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">건강피해내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="newHealthDmgeCn" name="newHealthDmgeCn" class="w100"maxlength="100"  />
						</div>
					</div>
					<!-- 영향범위내용 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">영향범위내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="newAffcScopeCn" name="newAffcScopeCn" class="w100" maxlength="100" />
						</div>
					</div>
					<!-- 노출기간내용 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">노출기간내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="newExpsrWhlCn1" name="newExpsrWhlCn1" class="w100"  maxlength="100" />
						</div>
					</div>
					<!-- 거주기간 -->
					<div class="formGroup col-md-12">
						<span class="label col-md-3">거주기간</span>
						<div class="inputWrap col-md-6">
						    <input type="text" id="newResiWhlCn" name="newResiWhlCn" class="w100" maxlength="100" />
						</div>
					</div>
				</li>
				<!--  지역 차수 추가 등록 탭 -->
				<li class="tabInnerFrom box" id="addOderTab">
				<input type="hidden" id="bizAreaCd" name = "bizAreaCd">
				<!-- 피해 지역-->
					<div class="formGroup col-md-12">
						<span class="label col-md-3 must">피해지역</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="bizArea" name="bizArea" class="w100" disabled/>
						</div>
					</div>
					<div class="formGroup col-md-12">
					<!-- 피해 상세 지역-->
						<span class="label col-md-3 must">피해상세지역</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="bizDtlArea" name="bizDtlArea" class="w100" disabled />
						</div>
					</div>
					<div class="formGroup col-md-12">
					<!-- 생성 차수 -->
						<span class="label col-md-3 must">생성차수</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="newBizOder" name="newBizOder" class="w100" readonly />
						</div>
						<a href="#void" id="gernOder" class="btn">차수생성</a>
					</div>
				</li>
				<!--  사업 관리 탭 -->
				<li class="tabInnerFrom box on" id="newAreaTab">
					<div class="formGroup col-md-12">
						<span class="label col-md-3 must">피해지역</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="bizArea" name="bizArea" class="w100" maxlength="50" readonly/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3 must">피해상세지역</span>
						<div class="inputWrap col-md-4">
							<input type="text" id="bizDtlArea" name="bizDtlArea" class="w100" maxlength="50" readonly/>
						</div>
						<span class="label col-md-1 must">차수</span>
						<div class="inputWrap col-md-2">
							<input type="text" id="bizOder" name="bizOder" class="w100" readonly/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3">오염원내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="polusrcCn" name="polusrcCn" class="w100" maxlength="100" />
						</div>

					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3">유해인자노출내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="hrmflns" name="hrmflns" class="w100" maxlength="100"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3">건강피해내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="healthDmgeCn" name="healthDmgeCn" class="w100" maxlength="100"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3">영향범위내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="affcScopeCn" name="affcScopeCn" class="w100" maxlength="100"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3">노출기간내용</span>
						<div class="inputWrap col-md-6">
							<input type="text" id="expsrWhlCn" name="expsrWhlCn" value="" class="w100" maxlength="100"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="label col-md-3">거주기간</span>
						<div class="inputWrap col-md-6">
						    <input type="text" id="resiWhlCn" name="resiWhlCn" class="w100" maxlength="100" />
						</div>
					</div>
				</li>

			</ul>
		</div>
		</form>
		<div style="height: 10px"></div>
		<div class="btnDiv">
			<a href="#void" id="btnSave" class="btn blue">저장</a>
			<a href="#void" id="btnAddOder" class="btn blue" style = "display : none" >저장</a>
			<a href="#void" id="btnModify" class="btn blue"  style = "display : none" >수정</a>
			<a href="#void" id="btnDelete" class="btn blue"  style = "display : none" >차수삭제</a>
			<a href="#void" id="btnAllDelete" class="btn blue"  style = "display : none" >사업삭제</a>
		</div>
	</div>
</div>
<%-- ############################# 내용 (종료) ############################# --%>
