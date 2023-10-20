<%--
*******************************************************************************
***	명칭: modalSurveyView.jsp
***	설명: 구제급여신청 - 온라인설문지 상세팝업 (사용자)
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2022.01.19    LSH        First Coding.
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

<form:form commandName="form" id="surveyForm" name="surveyForm" method="post" onsubmit="return false;">
<form:hidden id="p_aplyNo"     path="aplyNo"     />
<form:hidden id="p_rspnsMngNo" path="rspnsMngNo" />
<form:hidden id="p_qstnnMngNo" path="qstnnMngNo" />

<div class="layerWrap">
	<!-- 레이어내용 -->
	<div class="layerPop-inner relief ">
		<div class="onlineDocWrap">
			<div class="tableWrap type5">
				<table>
					<colgroup>
						<col width="50%" >
						<col width="50%" >
					</colgroup>
					<tr>
						<th>피해 신청인 성명</th>
						<td><c:out value="${form.rgtrNm}"/></td>
					</tr>
				</table>
			</div>
			<div style="height:20px"></div>
			
			<div class="tableWrap type8">
				<table id="appSurveyTable">
					<colgroup>
						<col style="width:22%"/>
						<col style="width:33%"/>
						<col style="width:20%"/>
						<col style="width:25%"/>
					</colgroup>
					<tbody>
					<c:forEach var="item" items="${form.items}" varStatus="state">
						<tr><%-- 문항내용 --%>
							<th colspan="4" class="left">${item.qesitmNo}. ${item.qesitmCn}</th>
						</tr>
						<%-- 1.거주기간 --------------------------------------%>
						<c:if test="${item.qestnTy == 'A010'}">
							<tr>
								<td>거주기간</td>
								<td colspan="3">
									<span>${item.ansCn1} 년</span>
									<span class="mark">-</span>
									<span>${item.ansCn2} 년</span>
								</td>
							</tr>
							<tr>
								<td>총 거주 기간</td>
								<td colspan="3"><span>${item.ansCn3}</span> 년</td>
							</tr>
						</c:if>
						<%-- 6.질병/병원명 -----------------------------------%>
						<c:if test="${item.qestnTy == 'A060'}">
							<tr>
								<td>신체증상(질병명 등)</td>
								<td colspan="3">
									<span>${item.ansCn1}</span>
								</td>
							</tr>
							<tr>
								<td>주 내원 병원</td>
								<td colspan="3">
									<span>${item.ansCn2}</span>
								</td>
							</tr>
						</c:if>
						<%-- 9.배상금 ----------------------------------------%>
						<c:if test="${item.qestnTy == 'A090'}">
							<tr>
								<td colspan="4">
									<c:if test="${item.ansCn1 == 'Y'}">예</c:if>
									<c:if test="${item.ansCn1 == 'N'}">아니오</c:if>
								</td>
							</tr>
							<tr><%-- 부가문항 --%>
								<th colspan="4" class="left">${item.qesitmNo}-1. 보상 또는 배상금 수령 내역 (${item.qesitmNo}번에 '예'를 체크 하셨을 경우만 해당)</th>
							</tr>
							<tr>
								<td>수령일자</td>
								<td colspan="3">${f:toKorDate(item.ansCn2)}</td>
							</tr>
							<tr>
								<td>수령금액</td>
								<td colspan="3">
									<c:if test="${item.ansCn3 != null}">
										<fmt:formatNumber value="${item.ansCn3}" groupingUsed="true"></fmt:formatNumber> 원
									</c:if>
								</td>
							</tr>
							<tr>
								<td>지급한 자 또는 지급처</td>
								<td colspan="3">${item.ansCn4}</td>
							</tr>
						</c:if>
						<%-- 3.농약/4.흡엽/5.음주 ----------------------------%>
						<c:if test="${item.qestnTy == 'A030' || item.qestnTy == 'A040' || item.qestnTy == 'A050'}">
							<tr>
								<td colspan="4">
									<c:if test="${item.ansCn1 == 'Y'}">
										예 
										<c:if test="${item.qestnTy == 'A030'}">
											(총 ${item.ansCn2} 년)
										</c:if>
										<c:if test="${item.qestnTy == 'A040'}">
											(총 ${item.ansCn2} 년, 하루 평균 ${item.ansCn3} 개비)
										</c:if>
										<c:if test="${item.qestnTy == 'A050'}">
											(총 ${item.ansCn2} 년, 주 ${item.ansCn3} 회, 하루 평균 소주 ${item.ansCn4} 잔)
										</c:if>
									</c:if>
									<c:if test="${item.ansCn1 == 'N'}">
										아니오
									</c:if>
								</td>
							</tr>
							<c:if test="${item.qestnTy == 'A040' || item.qestnTy == 'A050'}">
								<tr><%-- 부가문항 --%>
									<th colspan="4" class="left">
										${item.qesitmNo}-1. 
										현재
										<c:if test="${item.qestnTy == 'A040'}">흡연</c:if>
										<c:if test="${item.qestnTy == 'A050'}">음주</c:if>
										여부 
										(${item.qesitmNo}번에 '예'를 체크 하셨을 경우만 해당)
									</th>
								</tr>
								<tr>
									<td colspan="4">
										<c:if test="${item.ansCn5 == 'Y'}">예</c:if>
										<c:if test="${item.ansCn5 == 'N'}">아니오 (끊은시기: 만 ${item.ansCn6} 세)</c:if>
									</td>
								</tr>
							</c:if>
						</c:if>
						<%-- 2. 직업경력 (하위항목존재) ----------------------%>
						<c:if test="${item.qestnTy == 'A020'}">
							<c:forEach var="child" items="${item.items}" varStatus="stateChild">
								<tr>
									<td>${child.qesitmCn}</td>
									<td colspan="3">
										<c:if test="${child.ansCn1 != null}">
										    ${child.ansCn1} 
										    <span class="mark">,</span>
										    ${child.ansCn2} 년 
										    <span class="mark">~</span>
										    ${child.ansCn3} 년
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<%-- 7. 본인질병 (하위항목존재) ----------------------%>
						<c:if test="${item.qestnTy == 'A070'}">
							<tr>
								<td colspan="4">
									<div class="tableWrap type7">
										<table>
											<thead>
												<tr>
													<th>질환명</th>
													<th>해당 여부</th>
													<th>병원 진단여부</th>
													<th>최초 발병시기</th>
													<th>세부 질환명</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="child" items="${item.items}" varStatus="stateChild">
												<tr>
													<th>${child.qesitmCn}</th>
													<td>
														<c:if test="${child.ansCn6 == 'Y'}">해당없음</c:if>
													</td>
													<td>
														<c:if test="${child.ansCn2 == 'Y'}">예</c:if>
														<c:if test="${child.ansCn2 == 'N'}">아니오</c:if>
													</td>
													<td><c:if test="${child.ansCn3 != null}">만 ${child.ansCn3} 세</c:if></td>
													<td>${child.ansCn4}</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</c:if>
						<%-- 8. 가족질병 (하위항목존재) ----------------------%>
						<c:if test="${item.qestnTy == 'A080'}">
							<tr>
								<td colspan="4">
									<div class="tableWrap type7">
										<table>
											<thead>
												<tr>
													<th>질환명</th>
													<th>해당 여부</th>
													<th>본인과의 관계</th>
													<th>최초 진단시기</th>
												</tr>
											</thead>
											<tbody>
											<%-- 질환별 LOOP --%>
											<c:forEach var="child" items="${item.items}" varStatus="chdSts">
												<%-- 관계별 LOOP --%>
												<c:forEach var="rel" items="${child.relations}" varStatus="relSts">
													<tr>
														<c:if test="${relSts.index == 0}">
															<th rowspan="4">${child.ansCn1}</th>
															<td rowspan="4">
																<c:if test="${child.ansCn6 == 'Y'}">해당없음</c:if>
															</td>
														</c:if>
														<td>${rel.ansTit}<c:if test="${rel.ansRel == 'Y'}"> (예)</c:if></td>
														<td><c:if test="${rel.ansAge != ''}">만 ${rel.ansAge} 세</c:if>
															<c:if test="${rel.ansNon == 'Y'}">모름</c:if>
														</td>
													</tr>
												</c:forEach>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</c:if>
					</c:forEach>
						<tr>
							<td colspan="4">
								※ ｢환경오염피해 배상책임 및 구제에 관한 법률｣ 제34조에 따라, 
								환경오염피해에 대하여 손해배상을 받을 수 있거나 다른 법령에 따른 
								구제를 받은 경우에는 그 금액의 한도에서 구제급여를 지급하지 않습니다. 
								만약 사실과 다른 내용을 기재하여, 환경오염피해 구제급여를 부당하게 
								지급받은 경우에는 ｢환경오염피해 배상책임 및 구제에 관한 법률｣ 제37조제4항에 따라 
								부당지급 구제급여 액수 2배를 납입해야 하는 등의 
								불이익 처분을 받게 되오니 사실대로 기재해야 합니다.
							</td>
						</tr>

					</tbody>
				</table> 
			</div>
			<div class="signArea type1">
				<p>상기 답변사항은 사실과 다름이 없으며 이에 서명 날인합니다.</p>
				<p>※ 상기 내용이 사실에 부합함을 확인하며, 
				     사실과 다른 내용을 제출하여 구제급여를 부당하게 지급받은 경우에는 
				     부당이득 환수 등의 불이익 처분을 받음에 이의가 없음을 확인합니다.
				</p>
				<p>
					<span><c:out value="${form.rgtrYear}"/> 년</span>
					<span style="margin-left:5px"><c:out value="${form.rgtrMonth}"/> 월</span>
					<span style="margin-left:5px"><c:out value="${form.rgtrDay}"/> 일</span>
				</p>
				<p class="right app-sign-area">
					<span class="app-sign-name">성명 : <c:out value="${form.rgtrNm}"/></span>
					<c:if test="${!empty form.signCn}">
						<span class="app-sign-file">
							<img src="<c:url value='/usr/relief/linkSurveySign.do?rspnsMngNo='/>${form.rspnsMngNo}" width="150"/>
						</span>
					</c:if>
				</p>
			</div>
		</div>
		<div class="btnWrap type3">
			<a href="javascript:void(0);" data-dismiss="modal">확인</a>
		</div>
	</div>
</div>

</form:form>

<%-- ############################# 내용 (종료) ############################# --%>
