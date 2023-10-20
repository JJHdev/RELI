<%--
*******************************************************************************
***	명칭: modalReliefForm.jsp
***	설명: 구제급여접수 - 개인정보 수정팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2022.12.02    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<%-- ############################# 내용 (시작) ############################# --%>

<script type="text/javascript" src="<c:url value='/js/adm/relief/modalReliefForm.js'/>"></script>

<form:form commandName="form" id="reliefForm" name="reliefForm" method="post" onsubmit="return false;">
<input id="p_mode"        name="mode"        type="hidden" value="${form.mode}"        />
<input id="p_gsUserNo"    name="gsUserNo"    type="hidden" value="${form.gsUserNo}"    />
<input id="p_aplySeCd"    name="aplySeCd"    type="hidden" value="${form.aplySeCd}"    /><%-- 신청구분코드 --%>
<input id="p_aplyNo"      name="aplyNo"      type="hidden" value="${form.aplyNo}"      /><%-- 신청번호 --%>
<input id="p_sufrerNo"    name="sufrerNo"    type="hidden" value="${form.sufrerNo}"    /><%-- 피해자번호 --%>
<input id="p_aplcntNo"    name="aplcntNo"    type="hidden" value="${form.aplcntNo}"    /><%-- 신청자번호 --%>
<input id="p_hstSeCd"     name="hstSeCd"     type="hidden" value="${form.hstSeCd}"     /><%-- 이력구분(개인정보수정) --%>

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner">
		<div class="personnelWrap">
			
			<c:if test="${form.hstSeCd == 'H3'}"><%-- 피해자정보수정인 경우 --%>

			<!-- 피해자정보 START // -->
			<div class="tabInner formLayout sufrerWrap">
				<div class="formGroup col-md-6">
					<span class="label col-md-4">신청번호</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly " id="ps_aplyNo"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">식 별 ID</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly" id="ps_idntfcId"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">신청구분</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly" id="ps_aplySeNm"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">성　　명</span>
					<div class="inputWrap col-md-8">
						<input id="p_sufrerNm" name="sufrerNm" type="text"  maxlength="10" style="width:200px"/>
					</div>
				</div>
				<div class="formGroup col-md-6 app-both">
					<span class="label col-md-4">지　　역</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly" id="ps_bizAreaNm"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">생년월일</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly" id="ps_sufrerBrdt"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">성　　별</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly" id="ps_sufrerSxdstNm"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">연　　령</span>
					<div class="inputWrap col-md-8">
						<div class="app-box app-readonly" id="ps_sufrerAge"></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">신청일자</span>
					<div class="inputWrap col-md-8">
						<div style="width:180px"><input id="p_aplyYmd" name="aplyYmd" style="width:180px"/></div>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">접수일자</span>
					<div class="inputWrap col-md-8">
						<div style="width:180px"><input id="p_rcptYmd" name="rcptYmd" style="width:180px"/></div>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">신청종류</span>
					<div class="inputWrap col-md-10">
						<div id="p_appAplyKndCd"></div><%-- 체크박스 --%>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">유선전화번호</span>
					<div class="inputWrap col-md-8">
					    <select id="p_sufrerTelno1" name="sufrerTelno1" style="width:75px" class="app-select"></select>
					    <input  id="p_sufrerTelno2" name="sufrerTelno2" type="text"  maxlength="4" style="width:62px"/>
					    <span class="app-mark">-</span>
					    <input  id="p_sufrerTelno3" name="sufrerTelno3" type="text"  maxlength="4" style="width:62px"/>
						<input  id="p_sufrerTelno"  name="sufrerTelno"  type="hidden"/>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">휴대전화번호</span>
					<div class="inputWrap col-md-8">
					    <select id="p_sufrerMbtelNo1" name="sufrerMbtelNo1" style="width:75px" class="app-select"></select>
					    <input  id="p_sufrerMbtelNo2" name="sufrerMbtelNo2" type="text"  maxlength="4" style="width:62px"/>
					    <span class="app-mark">-</span>
					    <input  id="p_sufrerMbtelNo3" name="sufrerMbtelNo3" type="text"  maxlength="4" style="width:62px"/>
						<input  id="p_sufrerMbtelNo"  name="sufrerMbtelNo"  type="hidden"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">전자우편주소</span>
					<div class="inputWrap col-md-10">
						<input  id="p_sufrerEmlAddr1"  name="sufrerEmlAddr1"  type="text" maxlength="30" style="width:200px"/>
						<span class="app-mark">@</span>
						<input  id="p_sufrerEmlAddr2"  name="sufrerEmlAddr2"  type="text" maxlength="50" style="width:200px"/>
						<select id="p_sufrerEmlDomain" name="sufrerEmlDomain"></select>
						<input  id="p_sufrerEmlAddr"   name="sufrerEmlAddr"   type="hidden" />
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">주　　소</span>
					<div class="inputWrap col-md-10">
						<div class="address">
							<div>
								<input id="p_sufrerZip" name="sufrerZip" type="text" style="width:80px" placeholder="우편번호" maxlength="5" readonly/>
								<button data-prefix="p_sufrer" class="btnPost">주소검색</button>
							</div>
							<div>
								<input id="p_sufrerAddr"  name="sufrerAddr"  type="text" style="width:100%;" class="app-mt10" placeholder="기본주소" maxlength="65" readonly />
								<input id="p_sufrerDaddr" name="sufrerDaddr" type="text" style="width:100%;" class="app-mt10" placeholder="상세주소" maxlength="65" />
							</div>
						</div>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">계좌번호(은행)</span>
					<div class="inputWrap col-md-10">
						<select id="p_bankCd"     name="bankCd"     style="width:150px" ></select>
						<input  id="p_dpstrNm"    name="dpstrNm"    style="width:100px" type="text" maxlength="30" placeholder="예금주"/>
						<input  id="p_actno"      name="actno"      style="width:320px" type="text" maxlength="50" placeholder="계좌번호"/>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">생존여부</span>
					<div class="inputWrap col-md-8">
					    <select id="p_dthYn"  name="dthYn" style="width:100%" ></select>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">사망일자</span>
					<div class="inputWrap col-md-8">
						<div style="width:180px"><input id="p_dthYmd" name="dthYmd" style="width:180px"/></div>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">수정 사유</span>
					<div class="inputWrap col-md-10">
						<input id="p_hstCn1" name="hstCn" type="text" maxlength="100" style="width:100%;"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2"></span>
					<div class="inputWrap col-md-10"></div>
				</div>
			</div>
			<!-- 피해자정보 END // -->
			</c:if>

			<c:if test="${form.hstSeCd == 'H4'}"><%-- 신청인정보수정인 경우 --%>
			<!-- 신청인정보 START // -->
			<div class="tabInner formLayout aplcntWrap">
				<div class="formGroup col-md-6">
					<span class="label col-md-4">성　　명</span>
					<div class="inputWrap col-md-8">
						<input id="p_aplcntNm" name="aplcntNm" type="text"  maxlength="10" style="width:200px"/>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">생년월일</span>
					<div class="inputWrap col-md-8">
					    <input  id="p_aplcntBrdt"  name="aplcntBrdt"  type="hidden"/>
					    <select id="p_aplcntBrdt1" name="aplcntBrdt1" class="app-select" style="width:86px"></select>
					    <select id="p_aplcntBrdt2" name="aplcntBrdt2" class="app-select" style="width:62px"></select>
					    <select id="p_aplcntBrdt3" name="aplcntBrdt3" class="app-select" style="width:62px"></select>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">피해자와의관계</span>
					<div class="inputWrap col-md-10">
						<select id="p_sufrerRelCd" name="sufrerRelCd"></select>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">유선전화번호</span>
					<div class="inputWrap col-md-8">
					    <select id="p_aplcntTelno1" name="aplcntTelno1" style="width:75px" class="app-select"></select>
					    <input  id="p_aplcntTelno2" name="aplcntTelno2" type="text"  maxlength="4" style="width:62px"/>
					    <span class="app-mark">-</span>
					    <input  id="p_aplcntTelno3" name="aplcntTelno3" type="text"  maxlength="4" style="width:62px"/>
						<input  id="p_aplcntTelno"  name="aplcntTelno"  type="hidden"/>
					</div>
				</div>
				<div class="formGroup col-md-6">
					<span class="label col-md-4">휴대전화번호</span>
					<div class="inputWrap col-md-8">
					    <select id="p_aplcntMbtelNo1" name="aplcntMbtelNo1" style="width:75px" class="app-select"></select>
					    <input  id="p_aplcntMbtelNo2" name="aplcntMbtelNo2" type="text"  maxlength="4" style="width:62px"/>
					    <span class="app-mark">-</span>
					    <input  id="p_aplcntMbtelNo3" name="aplcntMbtelNo3" type="text"  maxlength="4" style="width:62px"/>
						<input  id="p_aplcntMbtelNo"  name="aplcntMbtelNo"  type="hidden"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">전자우편주소</span>
					<div class="inputWrap col-md-10">
						<input  id="p_aplcntEmlAddr1"  name="aplcntEmlAddr1"  type="text" maxlength="30" style="width:200px"/>
						<span class="app-mark">@</span>
						<input  id="p_aplcntEmlAddr2"  name="aplcntEmlAddr2"  type="text" maxlength="50" style="width:200px"/>
						<select id="p_aplcntEmlDomain" name="aplcntEmlDomain"></select>
						<input  id="p_aplcntEmlAddr"   name="aplcntEmlAddr"   type="hidden" />
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">주　　소</span>
					<div class="inputWrap col-md-10">
						<div class="address">
							<div>
								<input id="p_aplcntZip" name="aplcntZip" type="text" style="width:80px" placeholder="우편번호" maxlength="5" readonly/>
								<button data-prefix="p_aplcnt" class="btnPost">주소검색</button>
							</div>
							<div>
								<input id="p_aplcntAddr"  name="aplcntAddr"  type="text" style="width:100%;" class="app-mt10" placeholder="기본주소" maxlength="65" readonly />
								<input id="p_aplcntDaddr" name="aplcntDaddr" type="text" style="width:100%;" class="app-mt10" placeholder="상세주소" maxlength="65" />
							</div>
						</div>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">수정 사유</span>
					<div class="inputWrap col-md-10">
						<input id="p_hstCn2" name="hstCn" type="text" maxlength="100" style="width:100%;"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2"></span>
					<div class="inputWrap col-md-10"></div>
				</div>
			</div>
			<!-- 신청인정보 END // -->
			</c:if>

			<c:if test="${form.hstSeCd == 'H5'}"><%-- 피해내용수정인 경우 --%>
			<!-- 피해내용 START // -->
			<div class="tabInner formLayout damageWrap">
				<div class="formGroup col-md-12">
					<span class="label col-md-2">피해발생의 일시∙장소</span>
					<div class="inputWrap col-md-10">
						<input id="p_dmgeOcrnPlce" name="dmgeOcrnPlce" type="text" maxlength="100" style="width:100%;"/><span class="app-error"></span>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">피해의 원인</span>
					<div class="inputWrap col-md-10">
						<input id="p_dmgeCauseCn" name="dmgeCauseCn" type="text" maxlength="200" style="width:100%;"/><span class="app-error"></span>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">피해발생의 경과</span>
					<div class="inputWrap col-md-10">
						<input id="p_dmgeOcrnProcCn" name="dmgeOcrnProcCn" type="text" maxlength="200" style="width:100%;"/><span class="app-error"></span>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">피해의 내용 및 금액</span>
					<div class="inputWrap col-md-10">
						<input id="p_dmgeCn" name="dmgeCn" type="text" maxlength="200" style="width:100%;"/><span class="app-error"></span>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">신청 사유</span>
					<div class="inputWrap col-md-10">
						<input id="p_aplyResn" name="aplyResn" type="text" maxlength="100" style="width:100%;"/><span class="app-error"></span>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2">수정 사유</span>
					<div class="inputWrap col-md-10">
						<input id="p_hstCn3" name="hstCn" type="text" maxlength="100" style="width:100%;"/>
					</div>
				</div>
				<div class="formGroup col-md-12">
					<span class="label col-md-2"></span>
					<div class="inputWrap col-md-10"></div>
				</div>
			</div>
			<!-- 피해내용 END // -->
			</c:if>
		</div>

		<div class="btnWrap type3">
			<a href="javascript:void(0);" data-dismiss="modal">취소</a>
			<a href="javascript:void(0);" id="btnReliefSave">저장</a>
		</div>
	</div>
</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
