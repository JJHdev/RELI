<%--
*******************************************************************************
***    명칭: viewRelief.jsp
***    설명: 마이페이지 - 구제급여현황
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

<!-- 내용 -->
<section class="contents member">
	<div class="contents-wrap relief">

		<form id="selectForm" name="selectForm" method="post" onsubmit="return false;">
			<input id="mode"        name="mode"        type="hidden" value="${model.mode}"      />
			<input id="aplyNo"      name="aplyNo"      type="hidden" value="${model.aplyNo}"    /><%-- 신청번호 --%>
			<input id="aplcntNo"    name="aplcntNo"    type="hidden" value="${model.aplcntNo}"  /><%-- 신청자번호 --%>
			<input id="idntfcId"    name="idntfcId"    type="hidden" value="${model.idntfcId}"  /><%-- 식별아이디 --%>
			<input id="aplySeCd"    name="aplySeCd"    type="hidden" value="${model.aplySeCd}"  /><%-- 신청구분코드 --%>
			<input id="papeDtySeCd" name="papeDtySeCd" type="hidden" value="${model.papeDtySeCd}" /><%-- 서류업무구분 --%>
		</form>

		<%-- 마이페이지 구제급여현황 탭 --%>
		<div id="appReliefTab"></div>

		<!-- 구제급여지급현황 START -->
		<div class="relief-tab-panel">
			<%-- 신청종류별 지급현황 --%>
			<div>

				<div class="payWrap">

					<h3 class="type2">의료비 지급</h3>
					<ul class="box_green">
						<li>
							<dl>
								<dt>최근 지급일자</dt>
								<dd id="mcpYmd"></dd>
							</dl>
						</li>
						<li class="appGiveDtls" data-code="MCP">
							<dl>
								<dt>지급 총액</dt>
								<dd id="mcpAmt"></dd>
							</dl>
						</li>
					</ul>

					<%-- 2021.11.09 의료비추가신청 추가 (신청정보가 있는 경우에만 표출) --%>
					<c:if test="${model.aplyNo != null}">
						<div class="h30"></div>
						<div class="payApplyAdd">
							<h4>의료비 추가신청</h4>
							<div class="payApplyAdd-inner">
								<p>환경오염피해로 인한 상해 또는 질병으로
								   주기적으로 치료 받는 경우
								   피해자가 지출한 금액을
								   청구할 수 있습니다.
								</p>
								<a href="#void" id="btnAplyAdd">신청하기</a>
							</div>
						</div>
					</c:if>

					<div class="h50"></div>
					<h3 class="type2">요양생활수당</h3>
					<ul class="box_blue">
						<li>
							<dl>
								<dt>최근 지급일자</dt>
								<dd id="rcpYmd"></dd>
							</dl>
						</li>
						<li class="appGiveDtls" data-code="RCP">
							<dl>
								<dt>지급 총액</dt>
								<dd id="rcpAmt"></dd>
							</dl>
						</li>
					</ul>

				</div>

			</div>
		<!-- 구제급여지급현황 END -->
		</div>

	</div>
</section>
<!-- //end 내용 -->


<%-- 지급세부내역 팝업--%>
<div id="reliefGivePopup"></div>

<%-- ############################# 내용 (종료) ############################# --%>
