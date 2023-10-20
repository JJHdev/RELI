<%--
*******************************************************************************
***    명칭: openLwst.jsp
***    설명: 취약계층 소송지원 신청 1단계
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

<!-- Signature Plugin -->
<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/signature_pad/css/signature-pad.css'/>" />
<script src="<c:url value='/plugins/signature_pad/js/signature_pad-3.0.0.umd.js'/>"></script>

<section class="contents apply">
	<div class="contents-wrap vulnerable">
		<form:form commandName="model" id="registForm" name="registForm" method="post" onsubmit="return false;">
			<form:hidden path="aplcntNo" />
			<form:hidden path="aplyNo" />
			<form:hidden path="papeDtySeCd" value="PP02" />
			<form:hidden path="aplySeCd" value="ALL" />
			<%-- 			<form:hidden path="aplyOder" value="1"/> --%>

			<h3 class="apply-tit">취약계층 소송지원 대상 안내</h3>
			<%@ include file="../support/vulnerable.jsp"%>
			<div class="apply-space"></div>
			<h3 class="apply-tit">
				정보입력<span style="font-size: 15px; float:right;" ><font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.</span>
			</h3>
			<div class="tableWrap type3 formType1">
				<table>
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 17%;">
						<col style="width: 73%;">
					</colgroup>
					<tr>
						<th rowspan="7" class="th pc">
							<b>신청인</b>(환경오염 피해자)
						</th>
						<td class="must">
							<span>성명</span>
							<small>(법인·단체명)</small>
						</td>
						<td>
							<input type="text" id="aplcntNm" name="aplcntNm" value="${model.aplcntNm }" style="width: 50%;"  maxlength="10"/>
						</td>
					</tr>
					<tr>
						<td class="must">
							<span>생년월일</span>
						</td>
						<td>
							<form:hidden path="aplcntBrdt" />
							<select id="aplcntBrdt1"></select>
							<select id="aplcntBrdt2"></select>
							<select id="aplcntBrdt3"></select>
						</td>
					</tr>
					<tr>
						<td class="must">
							<span>주소</span>
						</td>
						<td class="address">
							<div>
								<input placeholder="우편번호" name="aplcntZip" id="aplcntZip" type="text" value="${model.aplcntZip}">
								<button type="button" data-prefix="aplcnt" class="btnPost">주소검색</button>
							</div>
							<div>
								<input style="width: 50%;" placeholder="도로명 주소" name="aplcntAddr" id="aplcntAddr" type="text" value="${model.aplcntAddr}" />
								<input style="width: 50%;" placeholder="상세주소" name="aplcntDaddr" id="aplcntDaddr" type="text" value="${model.aplcntDaddr}" maxlength="100" />
							</div>
						</td>
					</tr>
					<tr>
						<td class="must">
							<span>휴대전화번호</span>
						</td>
						<td>
							<form:hidden path="aplcntMbtelNo" />
							<select id="aplcntMbtelNo1" name="aplcntMbtelNo1"></select>
							<input id="aplcntMbtelNo2" name="aplcntMbtelNo2" type="text" style="width: 100px" maxlength="4" />
							<span class="mark">-</span>
							<input id="aplcntMbtelNo3" name="aplcntMbtelNo3" type="text" style="width: 100px" maxlength="4" />
						</td>
					</tr>
					<tr>
						<td>
							<span>유선전화번호</span>
						</td>
						<td>
							<form:hidden path="aplcntTelNo" />
							<select id="aplcntTelNo1" name="aplcntTelNo1"></select>
							<input id="aplcntTelNo2" name="aplcntTelNo2" type="text" style="width: 100px" maxlength="4" />
							<span class="mark">-</span>
							<input id="aplcntTelNo3" name="aplcntTelNo3" type="text" style="width: 100px" maxlength="4" />
						</td>
					</tr>
					<tr>
						<td>
							<span>전자우편주소</span>
						</td>
						<td>
							<input id="aplcntEmlAddr" name="aplcntEmlAddr" type="hidden" />
							<input id="aplcntEmlAddr1" name="aplcntEmlAddr1" type="text" value="${model.aplcntEmlAddr}" maxlength="20"/>
							<span class="mark">@</span>
							<input id="aplcntEmlAddr2" name="aplcntEmlAddr2" type="text" value="${model.aplcntEmlDomain}" />
							<select id="aplcntEmlDomain" name="aplcntEmlDomain" style="width: 130px"></select>
						</td>
					</tr>
					<tr>
						<td>
							<span>사업자등록번호</span>
							<small>(법인·단체명)</small>
						</td>
						<td>
							<input type="text" id="aplcntBrno" name="aplcntBrno" placeholder="입력하세요" maxlength="15"/>
							<span class="width-space w5"></span>
							<input type="checkbox" id="no" name="no" />
							<label for="no">해당사항 없음</label>
						</td>
					</tr>
				</table>
			</div>

			<div class="apply-space"></div>

			<!-- 피신청인 -->
			<div class="tableWrap type3 formType1">
				<table>
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 17%;">
						<col style="width: 73%;">
					</colgroup>
					<tr>
						<th rowspan="7" class="th pc">
							<b>피신청인</b>(환경오염 피해원인)
						</th>
						<td class="must">
							<span>성명</span>
							<small>(법인·단체명)</small>
						</td>
						<td>
							<input type="text" id="respdntNm" name="respdntNm" style="width: 50%;" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td>
							<span>생년월일</span>
						</td>
						<td>
							<input id="respdntBrdt" name="respdntBrdt" type="hidden" />
							<select id="respdntBrdt1"></select>
							<select id="respdntBrdt2"></select>
							<select id="respdntBrdt3"></select>
						</td>
					</tr>
					<tr>
						<td class="must">
							<span>주소</span>
						</td>
						<td class="address">
							<div>
								<input placeholder="우편번호" name="respdntZip" id="respdntZip" type="text" readonly="readonly">
								<button type="button" data-prefix="respdnt" class="btnPost">주소검색</button>
							</div>
							<div>
								<input style="width: 50%;" placeholder="도로명 주소" name="respdntAddr" id="respdntAddr" type="text" readonly="readonly" />
								<input style="width: 50%;" placeholder="상세주소" name="respdntDaddr" id="respdntDaddr" type="text" maxlength="100" maxlength="65"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<span>유선전화번호</span>
						</td>
						<td>
							<input type="hidden" id="respdntTelno" name="respdntTelno">
							<select id="respdntTelno1" name="respdntTelno1"></select>
							<input id="respdntTelno2" name="respdntTelno2" style="width: 100px" maxlength="4"/>
							<span class="mark">-</span>
							<input id="respdntTelno3" name="respdntTelno3" style="width: 100px" maxlength="4"/>
						</td>
					</tr>
					<tr>
						<td>
							<span>전자우편주소</span>
						</td>
						<td>
							<input id="respdntEmlAddr" name="respdntEmlAddr" type="hidden" />
							<input id="respdntEmlAddr1" name="respdntEmlAddr1" maxlength="20"/>
							<span class="mark">@</span>
							<input id="respdntEmlAddr2" name="respdntEmlAddr2" />
							<select id="respdntEmlDomain" name="respdntEmlDomain" style="width: 130px"></select>
						</td>
					</tr>
					<tr>
						<td>
							<span>사업자등록번호</span>
							<small>(법인·단체명)</small>
						</td>
						<td>
							<input type="text" id="respdntBrno" name="respdntBrno" placeholder="입력하세요" maxlength="15"/>
							<span class="width-space w5"></span>
							<input type="checkbox" id="no2" name="no2" />
							<label for="no2">해당사항 없음</label>
						</td>
					</tr>
				</table>
			</div>
			<div class="apply-space"></div>

			<div class="tableWrap type3 formType1">
				<table>
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 17%;">
						<col style="width: 73%;">
					</colgroup>
					<tr>
						<th rowspan="2" class="th pc">
							<b>피해내용</b>
						</th>
						<td>
							<span>피해발생(일시·장소)</span>
						</td>
						<td>
							<input type="text" id="dmgeOcrnPlce" name="dmgeOcrnPlce" placeholder="예시 ) 1990년 경 부터 현재까지 /  집 근처 공장" style="width: 100%;" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<td>
							<span>피해내용 및 금액</span>
						</td>
						<td>
							<input type="text" id="dmgeCn" name="dmgeCn" placeholder="예시 ) 건강피해 / 추후 산정 금액" style="width: 100%;" maxlength="1300"/>
						</td>
					</tr>
					<tr>
						<th rowspan="2" class="th pc">
							<b>소송지원<br>신청내용
							</b>
						</th>
						<td>
							<span>신청취지 및 이유</span>
						</td>
						<td>
							<input type="text" id="aplyObjetCn" name="aplyObjetCn" placeholder="예시 ) 1990년 경 부터 현재까지. 집 근처 공장" style="width: 100%;" maxlength="1300"/>
						</td>
					</tr>
					<tr>
						<td>
							<span>신청내용 및 지원신청 금액</span>
						</td>
						<td>
							<input type="text" id="aplyCn" name="aplyCn" placeholder="예시 ) 건강피해/추후 산정 금액" style="width: 100%;" maxlength="650"/>
						</td>
					</tr>
				</table>
			</div>

			<div class="apply-space"></div>
			<div id="papeGroup" class="usr-file-group"></div>

			<div class="apply-space"></div>

			<div class="apply-agree">
				<p class="tit">본인은 「환경오염피해 배상책임 및 구제에 관한 법률」 제42조제1항 및 같은 법 시행규칙 제22조제2항에 따라 위와 같이 소송지원을 신청합니다.</p>
				<div class="inputWrap" align="right">
					<input type="radio" id="agree1" name="agree" value="yes" />
					<label for="agree1">예</label>
					<input type="radio" id="agree2" name="agree" value="no" />
					<label for="agree2">아니오</label>
				</div>
			</div>

			<!-- 버튼 -->
			<div class="btnWrap type2">
				<!-- 				<a href="#" id="btnTmpSave">임시저장</a> -->
				<a href="#" id="btnSubmit" class="blue">제출하기</a>
			</div>
		</form:form>
	</div>
</section>
<%-- ############################# 내용 (종료) ############################# --%>
