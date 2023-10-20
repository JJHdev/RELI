<%--
*******************************************************************************
***    명칭: updateMypage.jsp
***    설명: 마이페이지 - 나의정보 (회원정보수정)
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
<section class="contents apply">
	<div class="contents-wrap relief">
		<form id="registForm" name="registForm" method="post" onsubmit="return false;">
			<input id="mode" name="mode" class="easyui-textbox" />
			<input id="userNo" name="userNo" class="easyui-textbox" value="${model.userNo}" />

			<div class="relief-table tableWrap type3">
				<table>
					<colgroup>
						<col style="width: 10%;">
						<col style="width: 17%;">
						<col style="width: 73%;">
					</colgroup>
					<tr>
						<th rowspan="11" class="th" id="layerLeftTitle">
							<b>회원정보 수정</b>
						</th>
						<td class="must">
							<span>성명</span>
						</td>
						<td><c:out value="${model.userNm}"/></td>
					</tr>
					<tr>
						<td class="must">
							<span>회원 아이디</span>
						</td>

						<td><c:out value="${model.userId }"/></td>
					</tr>
					<tr>
					<tr>
						<td class="must">
							<span>비밀번호</span>
						</td>

						<td>
							<input type="password" class="form-control" id="pswd" name="pswd" placeholder="비밀번호">
							<div class="eheck_font" id="pw_check"></div>
						</td>
					</tr>
					<tr>
					<tr>
						<td class="must">
							<span>비밀번호 확인</span>
						</td>

						<td>
							<input type="password" class="form-control" id="pswdCnfm" name="pswdCnfm" placeholder="비밀번호 확인">
							<div class="eheck_font" id="pw2_check"></div>
						</td>
					</tr>
					<tr>
						<td>
							<span>생년월일</span>
						</td>
						<td><c:out value="${model.bryy}"/>년 <c:out value="${model.brmm}"/>월 <c:out value="${model.brdd}"/>일</td>
					</tr>

					<tr>
						<td class="must">
							<span>주소</span>
						</td>
						<td class="address">
							<p>
								<input id="zip" name="zip" class="easyui-textbox" data-options="readonly:true" maxlength="5" style="width: 60px" />
								<app:button id="btnPost" title="주소검색" cls="btn btn-default btn-sm" />
							</p>
							<p>
								<input id="addr" name="addr" class="easyui-textbox" data-options="readonly:true" maxlength="100" style="width: 300px" />
							</p>
							<p>
								<input id="daddr" name="daddr" class="easyui-textbox" maxlength="100" style="width: 300px" />
							</p>
							<span class="app-error"></span>
						</td>
					</tr>
					<tr>
						<td class="must">
							<span>휴대전화번호</span>
						</td>
						<td><c:out value="${model.mbtelNo}"/></td>
					</tr>
					<tr>
						<td>
							<span>유선전화번호</span>
						</td>
						<td>

							<input id="telno" name="telno" class="easyui-textbox" />
							<select id="telno1" name="telno1" style="width: 60px"></select>
							-
							<input id="telno2" name="telno2" class="easyui-textbox" style="width: 60px" />
							-
							<input id="telno3" name="telno3" class="easyui-textbox" style="width: 60px" />
						</td>
					</tr>
					<tr>
						<td>
							<span>전자우편주소</span>
						</td>
						<td>
							<input id="emlAddr" name="emlAddr" class="easyui-textbox" />
							<input id="emlAddr1" name="emlAddr1" class="easyui-textbox" style="width: 100px" />
							@
							<input id="emlAddr2" name="emlAddr2" class="easyui-textbox" style="width: 120px" />
							<select id="emlDomain" name="emlDomain" style="width: 130px"></select>
							<div id="appEmlAt"></div>
						</td>
					</tr>
				</table>
			</div>
			<br>
			<div class="form-group" align="center">
				<input type="hidden" id="userNo" name="userNo" value="${model.userNo}">
				<input type="button" value="수정" class="btn btn-primary" onClick="doUpdate();">
			</div>
		</form>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
