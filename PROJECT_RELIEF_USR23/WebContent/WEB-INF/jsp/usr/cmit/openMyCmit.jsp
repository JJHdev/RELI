<%--
*******************************************************************************
***    명칭: openMyCmit.jsp
***    설명: 온라인위원회 - 위원회 현황
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

<section class="contents member">
	<div class="contents-wrap relief">

		<form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
			<div class="tableWrap type6">
			
				<table>
					<colgroup>
						<col style="width:60%"/>
						<col style="width:60%"/>
					</colgroup>
					<tr>
						<td>
							<div class="doc-form-list app-pb0">
								<div class="doc-form-tit"><p>위원회 구분</p></div>
								<div class="doc-form-input relief" id="appCmitSeCd"></div>
							</div>
						</td>
						<td>
							<div class="doc-form-list app-pb0">
								<div class="doc-form-tit"><p>지역구분</p></div>
								<div class="doc-form-input" id="appBizAreaCd"></div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="doc-form-list app-pb0">
								<div class="doc-form-tit"><p>위원회 개최일자</p></div>
								<div class="doc-form-input">
									<div class="inputWrap" style="width:90%">
										<input type="text" id="srchOpmtStdt" name="srchOpmtStdt" maxlength="10" class="easyui-datebox" style="width:48%"/> ~
										<input type="text" id="srchOpmtEndt" name="srchOpmtEndt" maxlength="10" class="easyui-datebox" style="width:48%"/>
									</div>
								</div>
							</div>
						</td>
						<td>
							<div class="doc-form-list app-pb0">
								<div class="doc-form-tit"><p>진행상태</p></div>
								<div class="doc-form-input relief" id="appStusCd"></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<div class="btnWrap type2">
			<a href="javascript:void(0);" class="blue" id="btnSearch">검색</a>
		</div>
		
		<div class="relief-tab-panel">
			<div class="h30"></div>
			<h3 class="type1">나의 온라인 위원회 목록</h3>

			<div id="appCmitGrid" class="tableWrap type1"></div>
			<div id="appCmitPage" class="pagenation"></div>
		</div>
	
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
