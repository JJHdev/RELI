<%--
*******************************************************************************
***    명칭: listSms.jsp
***    설명: SMS이력 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.09.23    LSH        First Coding.
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
	
		<div class="smsWrap">
			<div class="tabWrap type4">
				<ul>
					<li class="on"><a href="#void">SMS전송</a></li>
					<li class=""><a href="#void">SMS전송이력</a></li>
				</ul>
			</div>
			<div class="tabInner smsInner">
					<ul>
						<!-- SMS전송 -->
						<li class="tabInnerFrom box app-p50 on">
				            <form id="smsForm" name="smsForm" method="post" onsubmit="return false;">
								<div class="smsItem col-md-6">
									<div class="boxTit type2">
										<h4>발신내용</h4>
									</div>
									<div class="app-space15"></div>
									<div class="smsInput">
										<textarea id="trsmCn" name="trsmCn" maxlength="650" placeholder="내용을 입력하세요."></textarea>
									</div>
									<div class="inputWrap" style="margin-top:20px;">
										<span>발송번호</span>
										<input type="text" id="trnsterNo" name="trnsterNo" maxlength="15" placeholder="휴대전화번호" style="width:200px" value="${model.trnsterNo}" />
										<button type="button" id="btnSend">전송</button>
									</div>
								</div>
								<div class="smsItem col-md-6">
									<div class="boxTit type2">
										<h4>수신자 번호</h4>
									</div>
									<div class="app-space15"></div>
									<div class="smsRecipient box">
										<div class="smsRecipientNum" style="width:100%">
											<b class="app-left app-pt10 app-blue">총 수신자수 : <span id="rcvrCnt">0명</span></b>
											<div class="app-right btnDiv">
												<a href="#void" id="btnRcvrRemove" class="btn right">선택삭제</a>
											</div>
											<div class="app-both"></div>
											<table id="rcvrGrid" class="easyui-datagrid" style="width:100%;height:230px"></table><!-- style="width:320px;height:200px" -->
										</div>
									</div>
									<div class="inputWrap" style="margin-top:20px;">
										<span>수신자추가</span>
										<input type="text" id="rcvrNm" name="rcvrNm" maxlength="10" placeholder="성명" style="width:120px" />
										<input type="text" id="rcvrNo" name="rcvrNo" maxlength="15" placeholder="휴대전화번호" style="width:200px" />
										<button type="button" id="btnAppend" >추가</button>
									</div>
								</div>
			            	</form>
						</li>
						<!-- SMS전송이력 -->
						<li class="tabInnerFrom box app-p50 on">
				            <form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
								<div class="smsItem col-md-12">
									<div class="app-left inputWrap">
										<input type="text" id="srchText" name="srchText" placeholder="수신자명,수신자번호를 입력하세요." style="width: 350px"/>
										<button type="button" id="btnSearch">검색</button>
									</div>
									<div class="app-right btnDiv">
										<a href="#void" id="btnRemove" class="btn right">선택삭제</a>
									</div>
									<div class="app-both"></div>
								</div>
								<div style="width:100%;height:800px">
									<table id="grid" class="easyui-datagrid" data-options="fit:true"></table>
								</div>
				            </form>
						</li>
			            	
					</ul>	            
			</div>
		</div>
		
		<div class="app-space40"></div>
		
		<div class="trgtWrap">
			<div class="boxWrap type1 box">
				<div class="boxInner">
					<div class="boxTit type1">
						<h3>일괄전송 조회</h3>
					</div>
					<div class="searchForm formLayout box">
			            <form id="trgtForm" name="trgtForm" method="post" onsubmit="return false;">

						<div class="formGroup" id="appAplyTermBox"></div><%-- 신청일자 --%>
						<div class="formGroup" id="appRcptTermBox"></div><%-- 접수일자 --%>
						<div class="formGroup" id="appAplySe"     ></div><%-- 신청구분 --%>
						<div class="formGroup" id="appAplyKnd"    ></div><%-- 신청종류 --%>
						<div class="formGroup" id="appProgress"   ></div><%-- 진행현황 --%>
						<div class="formGroup">
							<p>피해지역</p>
							<div class="inputWrap">
								<select id="srchBizArea" name="srchBizArea" style="width:230px"></select>
							</div>
							<p>사업차수</p>
							<div class="inputWrap">
								<select id="srchBizOder" name="srchBizOder" style="width:230px"></select>
							</div>
						</div>
						<div class="formGroup">
							<p>기타조건</p>
							<div class="inputWrap">
								<div class="inputWrap">
									<input type="text" id="srchTrgtText" name="srchTrgtText" style="width:620px" placeholder="신청번호, 신청인명, 피해자명, 식별아이디, 전화번호 등을 입력하세요."/>
								</div>
							</div>
						</div>
			            </form>
					</div>
				</div>
			</div>
			<div class="btnWrap type1">
				<a href="javascript:void(0);" id="btnTrgtSearch" class="blue">조회</a>
				<a href="javascript:void(0);" id="btnTrgtReset">초기화</a>
			</div>
			<div class="app-space10"></div>
			<div class="btnDiv">
				<a href="#void" id="btnTrgtAppend" class="btn right">선택추가</a>
			</div>
			<table id="trgtGrid" class="easyui-datagrid" style="height:400px"></table>
		</div>


<%-- ############################# 내용 (종료) ############################# --%>
