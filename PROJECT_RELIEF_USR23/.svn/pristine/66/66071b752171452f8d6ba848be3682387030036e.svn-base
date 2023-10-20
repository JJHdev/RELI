<%--
*******************************************************************************
***    명칭: viewLwst.jsp
***    설명: 마이페이지 - 취약계층 소송지원 현황
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

<section class="contents member">
    <div class="contents-wrap vulnerable">

        <h3 class="type1">소송지원</h3>
        <div class="tableWrap type6">
            <table>
                <colgroup>
                    <col style="width:16%">
                    <col style="">
                    <!-- <col style=""></col> -->
                </colgroup>
                <tbody class="alignL">
                    <tr>
                        <th>접수일자</th>
                        <td data-value='<c:out value="${row.rcptYmd}"/>' class="cusConvFormat convKorDate"></td>
                    </tr>
                    <tr>
                        <th>지원 금액</th>
                        <td>총 <c:out value="${row.lwstSprtAmtTot}"/> 원</td>
                    </tr>
                    <tr>
                        <th>지원 사항</td>
                        <td data-amtdetail='<c:out value="${row.lwstSprtAmtDetail}"/>' id="amtDetailData">
                            <!-- <span class="box">소송대리 10,000,000원</span>
                            <span class="box">인지액 송달료 3,000,000원</span> -->
                        </td>
                    </tr>
                    <tr>
                        <th>향후기일</th>
                        <td>
                            <div class="box col-md-8 col-sm-12">
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <span class="box">일자</span>
                                    <p class="inline"><c:out value="${row.dudtYmd}"/></p>
                                </div>
                                <div class="col-md-4 col-sm-6 col-xs-12">
                                    <span class="box">시각</span>
                                    <p class="inline"><c:out value="${row.dudtTm}"/></p>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <span class="box">장소</span>
                                    <p class="inline"><c:out value="${row.dudtPlce}"/></p>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <span class="box">내용</span>
                                    <p class="inline"><c:out value="${row.dudtSeNm}"/></p>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="h50"></div>

        <h3 class="type1">소송개요</h3>

        <div class="lawOutline box">
            <div class="lawOutline-list">
                <span>사건 번호</span>
                <p><c:out value="${row.incdntNo}"/></p>
            </div>
            <div class="lawOutline-list">
                <span>사건 명</span>
                <p><c:out value="${row.incdntNm}"/></p>
            </div>
            <div class="lawOutline-list">
                <span>원 고</span>
                <p><c:out value="${row.aplcntNmDesc}"/></p>
            </div>
            <div class="lawOutline-list">
                <span>피 고</span>
                <p><c:out value="${row.respdntNm}"/></p>
            </div>
            <div class="lawOutline-list">
                <span>소 가</span>
                <p><c:out value="${row.lwstPricesAmt}"/> 원</p>
            </div>
            <div class="lawOutline-list">
                <span>인지액</span>
                <p><c:out value="${row.papstmpAmt}"/> 원</p>
            </div>
            <div class="lawOutline-list">
                <span>소송일</span>
                <p><c:out value="${row.lwstYmd}"/></p>
            </div>
            <div class="lawOutline-list">
                <span>재판부</span>
                <p><c:out value="${row.jdgmtDeptNm}"/></p>
            </div>
        </div>
        <div class="h40"></div>

        <div class="tableWrap type4">
            <table>
                <thead>
                    <tr>
                        <th>일자</th>
                        <th>시각</th>
                        <th>기일장소</th>
                        <th>기일구분</th>
                        <th>결과</th>
                    </tr>
                </thead>
                <tbody id="detailTbody">
                    <c:forEach var="detail" items="${incdntDetailList}" varStatus="status">
                    <tr data-rsltcn=<c:out value="${detail.rsltCn}"/>>
                        <td><c:out value="${detail.dudtYmd}"/></td>
                        <td><c:out value="${detail.dudtTm}"/></td>
                        <td><c:out value="${detail.dudtPlce}"/></td>
                        <td><c:out value="${detail.dudtSeNm}"/></td>
                        <td><b class="cusRsltCnNm"><c:out value="${detail.rsltCnNm}"/></b></td>
                    </tr>
                    </c:forEach>
                    <c:if test="${fn:length(incdntDetailList) == 0}">
                        <tr><td colspan="5">내용이 없습니다.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <div class="btnWrap type3">
            <a href="javascript:void(0);" id="goListBtn">목록으로</a>
        </div>

    </div><!-- // contents-wrap -->
</section>

<%-- ############################# 내용 (종료) ############################# --%>
