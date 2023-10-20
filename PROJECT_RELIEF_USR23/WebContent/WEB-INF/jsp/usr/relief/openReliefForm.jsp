<%--
*******************************************************************************
***    명칭: openReliefForm.jsp
***    설명: 구제급여 신청 2단계 (정보입력)
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.05    LSH        First Coding.
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

<!-- Signature Plugin -->
<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/signature_pad/css/signature-pad.css'/>" />
<script src="<c:url value='/plugins/signature_pad/js/signature_pad-3.0.0.umd.js'/>"></script>
<script src="<c:url value='/js/com/comm_survey.js'/>"></script>

<section class="contents apply">
	<div class="contents-wrap relief">
		
		<div class="app-relief-center">
			<div class="app-relief-subtitle" id="layerAplyTitle"></div>
		</div>
		
		<form id="registForm" name="registForm" method="post" onsubmit="return false;">
		
			<input id="mode"        name="mode"        type="hidden" value="${model.mode}"        />
			<input id="admYn"       name="admYn"       type="hidden" value="${model.admYn}"       />
			<input id="gsUserNo"    name="gsUserNo"    type="hidden" value="${model.gsUserNo}"    />
			<input id="papeDtySeCd" name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
			<input id="aplySeCd"    name="aplySeCd"    type="hidden" value="${model.aplySeCd}"    /><%-- 신청구분코드 --%>
			<input id="aplyNo"      name="aplyNo"      type="hidden" value="${model.aplyNo}"      /><%-- 신청번호 --%>
			<input id="aplyOder"    name="aplyOder"    type="hidden" value="${model.aplyOder}"    /><%-- 신청차수 --%>
			<input id="idntfcId"    name="idntfcId"    type="hidden" value=""  /><%-- 식별번호 --%>
			<input id="sufrerNo"    name="sufrerNo"    type="hidden" value=""  /><%-- 피해자번호 --%>
			<input id="prgreStusCd" name="prgreStusCd" type="hidden" value=""  /><%-- 진행상태코드 --%>
			<input id="aplcntBrdt"  name="aplcntBrdt"  type="hidden" value=""  /><%-- 신청인생년월일 --%>
			<input id="sufrerBrdt"  name="sufrerBrdt"  type="hidden" value=""  /><%-- 피해자생년월일 --%>
			<input id="sufrerSxdst" name="sufrerSxdst" type="hidden" value=""  /><%-- 피해자성별 --%>
			<input id="sufrerAge"   name="sufrerAge"   type="hidden" value=""  /><%-- 피해자연령 --%>
			<input id="frstAplyNo"  name="frstAplyNo"  type="hidden" value=""  /><%-- 최초신청번호 --%>
			<input id="frstAplyYn"  name="frstAplyYn"  type="hidden" value=""  /><%-- 최초신청여부 --%>
			<input id="rspnsMngNo"  name="rspnsMngNo"  type="hidden" value=""  /><%-- 온라인설문지번호 --%>
			<input id="indvInfoClctAgreYn" name="indvInfoClctAgreYn" type="hidden" value=""/><%-- 동의여부 --%>

			<h3 class="apply-tit">
				신청인 정보 입력
				<span style="font-size: 15px; float:right;" ><font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.</span>
			</h3>
			
			<div class="relief-table tableWrap type3">
				<table>
					<colgroup>
						<col style="width:10%;">
						<col style="width:17%;">
						<col style="width:73%;">
					</colgroup>
					<tr>
						<th rowspan="7" class="th" id="layerLeftTitle"></th>
						<td class="must"><span>성명</span></td>
						<td>
							<input id="aplcntNo" name="aplcntNo" type="hidden" />
							<input id="aplcntNm" name="aplcntNm" type="text"  maxlength="10" style="width:50%;"/>
							<span class="app-error"></span>
						</td>
					</tr>
					<tr>
						<td class="must"><span>주민등록번호</span></td>
						<td>
							<input id="aplcntRrno1" name="aplcntRrno1" type="text" maxlength="6" /> 
							<span class="mark">-</span>
							<input id="aplcntRrno2" name="aplcntRrno2" type="password" maxlength="7" />
							<input id="aplcntRrno"  name="aplcntRrno"  type="hidden"/>
							<span class="app-error"></span>
						</td>
					</tr>
					<tr>
						<td class="must"><span>주소</span></td>
						<td class="address">
							<div>
								<input id="aplcntZip" name="aplcntZip" type="text" placeholder="우편번호" maxlength="5" readonly/>
								<button type="button" data-prefix="aplcnt" class="btnPost">주소검색</button>
							</div>
							<div>
								<input id="aplcntAddr"  name="aplcntAddr"  type="text" style="width:50%;"placeholder="기본주소" maxlength="65" readonly />
								<input id="aplcntDaddr" name="aplcntDaddr" type="text" style="width:50%;"placeholder="상세주소" maxlength="65" />
							</div>
							<span class="app-error"></span>
						</td>
					</tr>
					<tr>
						<td class="selmust"><span>휴대전화번호</span></td>
						<td>
						    <select id="aplcntMbtelNo1" name="aplcntMbtelNo1"></select>
						    <input  id="aplcntMbtelNo2" name="aplcntMbtelNo2" type="text"  maxlength="4" style="width:100px"/>
						    <span class="mark">-</span>
						    <input  id="aplcntMbtelNo3" name="aplcntMbtelNo3" type="text"  maxlength="4" style="width:100px"/>
							<input  id="aplcntMbtelNo"  name="aplcntMbtelNo"  type="hidden"/>
							<span class="app-error"></span>
						</td>
					</tr>
					<tr>
						<td><span>유선전화번호</span></td>
						<td>
						    <select id="aplcntTelno1" name="aplcntTelno1"></select>
						    <input  id="aplcntTelno2" name="aplcntTelno2" type="text"  maxlength="4" style="width:100px"/>
						    <span class="mark">-</span>
						    <input  id="aplcntTelno3" name="aplcntTelno3" type="text"  maxlength="4" style="width:100px"/>
							<input  id="aplcntTelno"  name="aplcntTelno"  type="hidden"/>
							<span class="app-error"></span>
						</td>
					</tr>
					<tr>
						<td><span>전자우편주소</span></td>
						<td>
							<input  id="aplcntEmlAddr1"  name="aplcntEmlAddr1"  type="text" maxlength="30" />
							<span class="mark">@</span>
							<input  id="aplcntEmlAddr2"  name="aplcntEmlAddr2"  type="text" maxlength="50" />
							<select id="aplcntEmlDomain" name="aplcntEmlDomain"></select>
							<input  id="aplcntEmlAddr"   name="aplcntEmlAddr"   type="hidden" />
							<span class="app-error"></span>
						</td>
					</tr>
					<tr id="layerSufrerRel">
						<td class="must"><span>피해자와의 관계</span></td>
						<td>
							<select id="sufrerRelCd" name="sufrerRelCd"></select>
							<span class="app-error"></span>
						</td>
					</tr>
				</table>
			</div>
			<%-- 대리(생)/대리(사)인 경우 START --%>
			<div id="layerAplyAgent">
				<div class="apply-space"></div>
				<h3 class="apply-tit">
					피해자 정보 입력
					<span style="font-size: 15px; float:right;" ><font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.</span>
				</h3>
				<div class="relief-table tableWrap type3">
					<table>
						<colgroup>
							<col style="width:10%;">
							<col style="width:17%;">
							<col style="width:73%;">
						</colgroup>
						<tr>
							<th rowspan="7" class="th">
								<b>피해자</b>(환경오염 피해자)
							</th>
							<td class="must"><span>성명</span></td>
							<td><input id="sufrerNm" name="sufrerNm" type="text" maxlength="10" style="width:50%;" /><span class="app-error"></span></td>
						</tr>
						<tr>
							<td class="must"><span>주민등록번호</span></td>
							<td>
								<input id="sufrerRrno1" name="sufrerRrno1" type="text" maxlength="6" /> 
								<span class="mark">-</span>
								<input id="sufrerRrno2" name="sufrerRrno2" type="password" maxlength="7" />
								<input id="sufrerRrno"  name="sufrerRrno"  type="hidden"/>
								<span class="app-error"></span>
							</td>
						</tr>
						<tr>
							<td class="must"><span>주소</span></td>
							<td class="address">
								<div>
									<input id="sufrerZip" name="sufrerZip" type="text" placeholder="우편번호" maxlength="5" readonly/>
									<button type="button" data-prefix="sufrer" class="btnPost">주소검색</button>
								</div>
								<div>
									<input id="sufrerAddr"  name="sufrerAddr"  type="text" style="width:50%;"placeholder="기본주소" maxlength="65" readonly />
									<input id="sufrerDaddr" name="sufrerDaddr" type="text" style="width:50%;"placeholder="상세주소" maxlength="65" />
									<span class="app-error"></span>
								</div>
							</td>
						</tr>
						<tr id="layerSufrerMbtel">
							<td class="selmust"><span>휴대전화번호</span></td>
							<td>
							    <select id="sufrerMbtelNo1" name="sufrerMbtelNo1"></select>
							    <input  id="sufrerMbtelNo2" name="sufrerMbtelNo2" type="text"  maxlength="4" style="width:100px"/>
							    <span class="mark">-</span>
							    <input  id="sufrerMbtelNo3" name="sufrerMbtelNo3" type="text"  maxlength="4" style="width:100px"/>
								<input  id="sufrerMbtelNo"  name="sufrerMbtelNo"  type="hidden"/>
								<span class="app-error"></span>
							</td>
						</tr>
						<tr id="layerSufrerTel">
							<td><span>유선전화번호</span></td>
							<td>
							    <select id="sufrerTelno1" name="sufrerTelno1"></select>
							    <input  id="sufrerTelno2" name="sufrerTelno2" type="text"  maxlength="4" style="width:100px"/>
							    <span class="mark">-</span>
							    <input  id="sufrerTelno3" name="sufrerTelno3" type="text"  maxlength="4" style="width:100px"/>
								<input  id="sufrerTelno"  name="sufrerTelno"  type="hidden"/>
								<span class="app-error"></span>
							</td>
						</tr>
						<tr id="layerSufrerEml">
							<td><span>전자우편주소</span></td>
							<td>
								<input  id="sufrerEmlAddr1"  name="sufrerEmlAddr1"  type="text" maxlength="30" />
								<span class="mark">@</span>
								<input  id="sufrerEmlAddr2"  name="sufrerEmlAddr2"  type="text" maxlength="50" />
								<select id="sufrerEmlDomain" name="sufrerEmlDomain"></select>
								<input  id="sufrerEmlAddr"   name="sufrerEmlAddr"   type="hidden" />
								<span class="app-error"></span>
							</td>
						</tr>
					</table>
				</div>			
			<%-- 대리(생)/대리(사)인 경우 END --%>
			</div>

			<div class="apply-space"></div>
			<h3 class="apply-tit">
				신청 내용
				<span style="font-size: 15px; float:right;" ><font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.</span>
			</h3>
			<div class="relief-table tableWrap type3">
				<table>
					<colgroup>
						<col style="width:27%;">
						<col style="width:73%;">
					</colgroup>
					<tr>
						<td class="must"><span>신청일자</span></td>
						<td><input id="aplyYmd" name="aplyYmd"/><span class="app-error"></span></td>
					</tr>
					<tr>
						<td class="must"><span>피해지역</span></td>
						<td><input id="etcDmgeArea" name="etcDmgeArea" type="text" maxlength="50" style="width:50%;"/><span class="app-error"></span></td>
					</tr>
					<tr>
						<td class="must"><span>구제급여 신청내용</span></td>
						<td><div id="layerAplyKnd"></div><span class="app-error"></span></td>
					</tr>
					<tr>
						<td class="must"><span>지급은행 및 계좌번호</span></td>
						<td>
							<select id="bankCd"     name="bankCd"     ></select>
							<input  id="dpstrNm"    name="dpstrNm"    style="width:100px" type="text" maxlength="30" placeholder="예금주"/>
							<input  id="actno"      name="actno"      style="width:320px" type="text" maxlength="50" placeholder="계좌번호"/>
							<span class="app-error"></span>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="apply-space"></div>
			<h3 class="apply-tit">
				피해내용
			</h3>
			<div class="relief-table tableWrap type3">
				<table>
					<colgroup>
						<col style="width:27%;">
						<col style="width:73%;">
					</colgroup>
					<tr>
						<td><span>피해발생의 일시 ∙ 장소</span></td>
						<td><input id="dmgeOcrnPlce" name="dmgeOcrnPlce" type="text" maxlength="100" style="width:100%;"/><span class="app-error"></span></td>
					</tr>
					<tr>
						<td><span>피해의 원인</span></td>
						<td><input id="dmgeCauseCn" name="dmgeCauseCn" type="text" maxlength="200" style="width:100%;"/><span class="app-error"></span></td>
					</tr>
					<tr>
						<td><span>피해발생의 경과</span></td>
						<td><input id="dmgeOcrnProcCn" name="dmgeOcrnProcCn" type="text" maxlength="200" style="width:100%;"/><span class="app-error"></span></td>
					</tr>
					<tr>
						<td><span>피해의 내용 및 금액</span></td>
						<td><input id="dmgeCn" name="dmgeCn" type="text" maxlength="200" style="width:100%;"/><span class="app-error"></span></td>
					</tr>
				</table>
			</div>
			
			<div class="apply-space"></div>
			<h3 class="apply-tit">
				신청 사유
			</h3>
			<div class="relief-table tableWrap type3">
				<table>
					<colgroup>
						<col style="width:27%;">
						<col style="width:73%;">
					</colgroup>
					<tr>
						<td><span>신청 사유</span></td>
						<td><input id="aplyResn" name="aplyResn" type="text" maxlength="100" style="width:100%;"/><span class="app-error"></span></td>
					</tr>
				</table>
			</div>
			
		</form>
		
		<!-- 파일목록 -->
		<div id="papeGroup" class="usr-file-group"></div>

		<div class="apply-space"></div>
		
		<h3 class="apply-tit">개인정보 수집·이용 및 제공 동의서</h3>
		<div class="relief-agree individual">
			<p>
				한국환경산업기술원에서는 환경오염피해 구제급여 (선)지급 신청에 따른 
				피해조사와 관련하여 아래와 같이 귀하의 개인정보를 수집ㆍ이용 및 
				제3자에게 제공하고자 합니다.<br/>
				내용을 자세히 읽으신 후 동의 여부를 결정하여 주십시오.
			</p>
			<div class="">
				<input type="checkbox" id="agreeYn" name="agreeYn" value="Y" />
				<label for="agreeYn">동의</label>
			</div>
		</div>
		
		<!-- 버튼 -->
		<div class="btnWrap type2">
			<a href="#" id="btnTmpSave">임시저장</a>
			<a href="#" id="btnSubmit" class="blue">제출하기</a>
		</div>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
