<%--
*******************************************************************************
***    명칭: openLwst.jsp
***    설명: 취약계층 소송지원 신청 1단계
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
<div class="tableWrap type9">
	<table>
		<thead>
			<tr>
				<th colspan="3">접수서류</th>
				<th colspan="3">지원내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>요건</th>
				<th>기준</th>
				<th>확인방법</th>
				<th>법률자문</th>
				<th>서류검토</th>
				<th>소송대리</th>
			</tr>
			<tr>
				<td class="b">저소득층</td>
				<td class="left">
					가구의 월평균 소득이 전국가구 월 평균 소득의 60% 이하
					<br>
					※ 소득 기준은 통계청 발표자료 참고
				</td>
				<td>건강보험료, 납입고지서, 국세청자료 등</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
			</tr>
			<tr>
				<td class="b">고령자</td>
				<td class="left">65세 이상인사람</td>
				<td>주민등록증, 운전면허증, 여권 등</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="b">장애인</td>
				<td class="left">「장애인고용촉진 및 직업재활법」 제2조 제1호에 따른 장애인</td>
				<td>장애인등록증, 전문의가 발행한 장애진단서 등</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
			<tr>
				<td rowspan="2" class="b">고용촉진 지원금 수급자</td>
				<td class="left">「청년고용촉진 특별법」 에 따른 청년 중 「고용보험법 시행령」제26조에 따른 고용촉진 지원금 수급자</td>
				<td>해당 지역 고용센터에 의뢰하여 확인</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="left">「경력단절여성등의 경제활동 촉진법」 에 따른 경력단절여성 중 「고용보험법 시행령」 제26조에 따른 고용촉진 지원금 수급자</td>
				<td>해당 지역 고용센터에 의뢰하여 확인</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="b">북한이탈주민</td>
				<td class="left">「북한이탈주민의 보호 및 정착지원에 관한 법률」 제2조 제1호에 따른 북한 이탈 주민</td>
				<td>북한이탈주민등록확인서</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="b">국가보훈대상자</td>
				<td class="left">「국가보훈 기본법」 제3조 제2호에 따른 국가보훈대상자</td>
				<td>보훈대상자 증명서</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="b">기타</td>
				<td class="left">환경부장관이 소송 지원이 필요하다고 인정하는 사람</td>
				<td>환경부 확인</td>
				<td>
					<span>무료</span>
				</td>
				<td>
					<span>무료</span>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
