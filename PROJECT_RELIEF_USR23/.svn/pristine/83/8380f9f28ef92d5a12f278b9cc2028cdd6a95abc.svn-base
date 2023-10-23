<%--
*******************************************************************************
***    명칭: openMypage.jsp
***    설명: 온라인위원회 - 나의정보
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
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
<section class="contents member">
	<div class="contents-wrap modify">
		<form:form commandName="model" id="registForm" name="registForm" method="post" onsubmit="return false;">
			<form:hidden path="mode" />
			<form:hidden path="userNo" />

			<h3>나의정보</h3>

			<div class="modify-wrap">
				<div class="modify-tit">
					<h4>회원정보 수정</h4>
					<p>개인정보는 개인정보보호정책에 의해 철저하게 보호됩니다.</p>
				</div>
				<div class="tableWrap type6 formType2">
					<table>
						<colgroup>
							<col style="width: 15%;" />
							<col style="width: 85%;" />
						</colgroup>
						<tr>
							<td><span>이름</span></td>
							<td><c:out value="${model.userNm}"/></td>
						</tr>
						<tr>
							<td><span>소속</span></td>
							<td><c:out value="${model.ogdpNm}"/></td>
						</tr>
						<tr>
							<td><span>회원아이디</span></td>
							<td><c:out value="${model.userId }"/></td>
						</tr>
						<tr>
							<td><span>생년월일</span></td>
							<td><c:out value="${model.bryy}"/>년&nbsp;&nbsp;<c:out value="${model.brmm}"/>월&nbsp;&nbsp;<c:out value="${model.brdd}"/>일</td>
						</tr>
						<tr>
							<td><span>연락처</span></td>
							<td>
								<div class="inputWrap callWrap">
									<span>휴대전화번호</span>
									<select id="mbtelNo1" name="mbtelNo1" class="call" disabled></select>
									<input  id="mbtelNo2" name="mbtelNo2" type="text" class="call" maxlength="4" style="width: 100px" disabled />
									<input  id="mbtelNo3" name="mbtelNo3" type="text" class="call" maxlength="4" style="width: 100px" disabled />
									<form:hidden path="mbtelNo" />
									<%-- 임시칼럼 --%>
									<span id="appMbtelAgreAt" data-value="${model.mbtelRcptnAgreYn}"></span>
								</div>
								<div class="inputWrap callWrap">
									<span>집(회사)전화</span>
									<select id="telno1" name="telno1" class="call"></select>
									<input  id="telno2" name="telno2" type="text" class="call" maxlength="4" style="width: 100px" />
									<input  id="telno3" name="telno3" type="text" class="call" maxlength="4" style="width: 100px" />
									<form:hidden path="telno" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="must"><span>주소</span></td>
							<td class="address">
								<div>
									<input placeholder="우편번호" name="zip" id="zip" type="text" value="${model.zip}">
									<button type="button" id="btnPostCode">주소검색</button>
								</div>
								<div>
									<input style="width: 30%;" placeholder="도로명 주소" name="addr" id="addr" type="text" value="${model.addr}" />
									<input placeholder="상세주소" name="daddr" id="daddr" type="text" maxlength="100" value="${model.daddr}" />
								</div>
							</td>
						</tr>
						<tr>
							<td><span>이메일주소</span></td>
							<td>
								<div class="inputWrap mailWrap">
									<input type="text" class="mail" id="emlAddr1" name="emlAddr1" value="${model.eml }" />
									<span class="mark">@</span>
									<input type="text" class="mail" id="emlAddr2" name="emlAddr2" value="${model.domain }" />
									<select id="emlDomain" name="emlDomain" class="bgWhite"></select>
									<form:hidden path="emlAddr" />
								</div>
							</td>
						</tr>
						<tr>
							<td><span>계좌번호</span></td>
							<td>
								<div class="inputWrap">
									<select id="bankCd" name="bankCd" style="width:160px" data-value="${model.bankCd}"></select>
									<input  id="actno"  name="actno" style="width:297px" value="${model.actno }" type="text" maxlength="50" placeholder="계좌번호"/>
								</div>
							</td>
						</tr>
					</table>
					<p align="left"><font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.</p>
				</div>
			</div>
			<div class="btnWrap type3">
                <a href="#" id="btnSave" class="blue">수정</a>
    		</div>
		</form:form>
	</div>
</section>


<%-- ############################# 내용 (종료) ############################# --%>
