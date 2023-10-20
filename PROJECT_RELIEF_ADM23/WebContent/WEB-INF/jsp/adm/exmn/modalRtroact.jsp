<%--
*******************************************************************************
***	명칭: modalRtroact.jsp
***	설명: 요양생활수당 - 요양생활수당지급 - 소급결정내용 팝업
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2022.12.29    LSH        First Coding.
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
<form:form commandName="form" id="p_rtroactForm" name="rtroactForm" method="post" onsubmit="return false;">
	<form:hidden id="p_bizAreaCd"     path="bizAreaCd"     />
	<form:hidden id="p_bizOder"       path="bizOder"       />
	<form:hidden id="p_exmnOder"      path="exmnOder"      />
	<form:hidden id="p_aplyNo"        path="aplyNo"        />
	<form:hidden id="p_rtroactBgngYm" path="rtroactBgngYm" />
	<form:hidden id="p_rtroactEndYm"  path="rtroactEndYm"  />
	<form:hidden id="p_rtroactAmt"    path="rtroactAmt"    />
	<form:hidden id="p_giveSeCd"      path="giveSeCd"      />
	<form:hidden id="p_giveYr"        path="giveYr"        />
	<form:hidden id="p_giveMm"        path="giveMm"        />

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner formLayout">
		<div class="app-space25"></div>
		<div class="tableWrap type3">
			<table>
				<tr>
					<th>소급기간</th>
					<td><select id="p_rtroactBgngYm1" name="rtroactBgngYm1"></select>
						<select id="p_rtroactBgngYm2" name="rtroactBgngYm2"></select>
						~
						<select id="p_rtroactEndYm1"  name="rtroactEndYm1" ></select>
						<select id="p_rtroactEndYm2"  name="rtroactEndYm2" ></select>
						<a href="javascript:void(0);" class="app-calc-btn btn blue">소급산정</a>
					</td>
				</tr>
				<tr>
					<th>소급금액</th>
					<td><input type="text" id="p_rtroactAmtKor" name="rtroactAmtKor" readonly class="app-readonly"/></td>
				</tr>
				<tr>
					<th>지급일자</th>
					<td><input type="text" id="p_rtroactYmd" name="rtroactYmd" maxlength="10" class="easyui-datebox" /></td>
				</tr>
			</table>
		</div>
		<div class="btnWrap type3">
			<a href="javascript:void(0);" class="app-close-btn">취소</a>
			<a href="javascript:void(0);" class="app-save-btn">지급등록</a>
		</div>
	</div>
</div>

</form:form>
<%-- ############################# 내용 (종료) ############################# --%>
