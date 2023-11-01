<%--
*******************************************************************************
***    명칭: viewMyCmit.jsp
***    설명: 온라인위원회 - 위원회 현황 - 세부정보
***
*** -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
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

<!-- 내용 -->
<section class="contents member">
	<div class="contents-wrap relief">
		
		<div id="appCmitMember"></div>
		<form:form commandName="model" id="selectForm" name="selectForm" method="post" onsubmit="return false;">
			<form:hidden path="cmitMngNo"/>
			<form:hidden path="tenureNo" />
			<div class="tableWrap type3">
				<table id="appCmitTable">
					<colgroup>
						<col style="width: 20%;" />
						<col style="width: 60%;" />
						<col style="width: 250px;" />
						<col style="width: 250px;" />
					</colgroup>
					<tbody class="alignL">
						<tr>
							<td colspan="4" class="app-px50 app-py20"><h3 class="apply-tit" id="s_cmitNm"></h3></td>
						</tr>
						<tr>
							<th>위원회 차수</th>
							<td colspan="3"><span id="s_cmitOder"></span></td>
						</tr>
						<tr>
							<th>위원회 개최일자</th>
							<td colspan="3"><span id="s_opmtYmdNm"></span></td>
						</tr>
						<tr>
							<th>위원회 안건조회</th>
							<td colspan="3"><span id="s_agntCntNm"></span></td>
						</tr>
					</tbody>
				</table>
			</div>
		</form:form>
		<div class="btnWrap type3" id="appButtons"></div>
	</div>
</section>
<!-- //end 내용 -->

<%-- ############################# 내용 (종료) ############################# --%>
