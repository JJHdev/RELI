<%--
*******************************************************************************
***    명칭: listLwst.jsp
***    설명: 마이페이지 - 취약계층 소송지원 현황 목록 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.12.16    CSLEE        First Coding.
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

<section class="contents board">
    <div class="contents-wrap qna">
        <div class="tableWrap type1">
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>지역</th>
                        <th>원고</th>
                        <th>피고</th>
                        <th>피해발생장소</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${rows}" varStatus="st">
                        <tr class="btnView"
                            data-aplyno='<c:out value="${row.aplyNo}"/>'>
                            <td><span class="mobile">번호</span><c:out value="${st.count}"/></td>
                            <td><span class="mobile">지역</span><c:out value="${row.etcDmgeArea}"/></td>
                            <td><span class="mobile">원고</span><c:out value="${row.aplcntNm}"/></td>
                            <td><span class="mobile">피고</span><c:out value="${row.respdntNm}"/></td>
                            <td><span class="mobile">피해발생장소</span><c:out value="${row.dmgeOcrnPlce}"/></td>
                            <td><span class="mobile">상태</span><c:out value="${row.prgreStusNm}"/></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(rows) == 0}">
                        <tr><td colspan="6">내용이 없습니다.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <div class="" style="margin-top: 40px;">
            <p class="tit" style="font-weight: bold;">※ 대한민국 법원 대국민 서비스 (나의 사건검색) 이용 시 상세한 사항을 확인하실 수 있습니다.</p>
            <div>
                <a href=" https://www.scourt.go.kr/portal/information/events/search/search.jsp" target="_blank">
                    https://www.scourt.go.kr/portal/information/events/search/search.jsp
                </a>
            </div>
        </div>

    </div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
