<%--
*******************************************************************************
***    명칭: openReliefInfo.jsp
***    설명: 환경오염 피해구제란
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    한금주        First Coding.
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
<section class="contents intro">
	<div class="contents-wrap insurance">
		
		<div class="tabWrap type4">
			<ul class="box">
				<li><a href="<c:url value='/usr/info/openReliefInfo.do'/>">환경오염 피해구제</a></li>
				<li class="on"><a href="<c:url value='/usr/info/openInsurance.do'/>">환경책임 보험제도</a></li>
			</ul>
		</div>
		
		<div class="insuranceWrap">
			<ul>
				<li>
					<div class="insurance-tit">
						<h3>환경책임 <br class="pc">보험제도란?</h3>
					</div>
					<div class="insurance-inner">
						<p>환경책임 보험이란 일정조건의 시설을 설치 및 운영하는 사업자가 가입해야하는 의무보험으로 환경오염사고 발생으로 인한 타인의 신체 또는 재산 피해를 보상하는 보험입니다.</p>
						<p>환경오염 위험성이 특히 높은 시설에 대해서는 환경책임 보험에 의무적으로 가입하며, 환경오염사고 발생시 피해자는 보험회사로부터 신속한 피해 배상을 받을 수 있도록 하고 있습니다.</p>
					</div>
				</li>
				<li>
					<div class="insurance-tit">
						<h3>환경책임 보험보상 <br class="pc">대상</h3>
					</div>
					<div class="insurance-inner">
						<p>피보험자가 피해자에게 지급할 책임을 지는 법률상 손해배상금</p>
						<p>피보험자가 손해의 방지 또는 경감을 위하여 지출한 필요 또는 유익하였던 비용(다만, 사업장부지내의 오염정화비용은 보상하지 아니합니다.)</p>
						<p>피보험자가 제3자로부터 손해의 배상을 받을 수 있는 그 권리를 지키거나 행사하기 위하여 지출한 필요 또는 유익하였던 비용</p>
						<p>피보험자가 지급한 소송비용, 변호사비용, 중재, 화해 또는 조정에 관한 비용</p>
						<p>보험증권상의 보상한도액내의 금액에 대한 공탁보증보험료</p>
						<p>피보험자가 보험회사의 요구에 따르기 위하여 지출한 비용</p>
					</div>
				</li>
				<li>
					<div class="insurance-tit">
						<h3>환경책임 보험 가입 <br class="pc">대상시설</h3>
					</div>
					<div class="insurance-inner">
						<p>환경오염시설 적용 대상으로는 대기오염물질배출시설, 폐수배출시설 또는 폐수무방류배출시설, 폐기물처리시설 등이 해당됩니다. 환경책임 보험 또는 보장계약은「환경오염피해 배생책임 및 구제에 관한 법」제 17조 제1항 각호의 시설이 설치된 사업장별로 가입하여야 하며, 하나의 사업장에 법 제3조에 따른 시설이 함께 설치되어 있는 경우에는 보장범위에 해당시설로 인해 발생한 환경오염피해가 모두 포함되도록 하여야 합니다</p>
						<div class="insurance-inner-box">
							<div class="insurance-inner-box-list">
								<span class="icon">대기</span>
								<div class="text">
									<p>특정대기유해물질 배출시설(1종 ~ 5종)</p>
									<p>1종 사업장의 대기오염물질배출시설</p>
								</div>
							</div>
							<div class="insurance-inner-box-list">
								<span class="icon">수질</span>
								<div class="text">
									<p>특정수질유해물질 배출시설(1종 ~ 5종)</p>
									<p>1종 사업장의 폐수배출시설(무방류 배출시설 포함)</p>
								</div>
							</div>
							<div class="insurance-inner-box-list">
								<span class="icon">폐기물</span>
								<div class="text">
									<p>지정폐기물처리시설</p>
									<ul>
										<li>지정폐기물처리시설이 없는 지정폐기물수집운반업은 해당 없음</li>
									</ul>
								</div>
							</div>
							<div class="insurance-inner-box-list">
								<span class="icon">토양</span>
								<div class="text">
									<p>특정토양오염관리대상 시설 중 아래의 시설</p>
									<ul>
										<li>저장용량 1,000㎘ 이상인 석유류 제조·저장 시설</li>
										<li>위해관리계획서 작성 · 제출대상 중 유해화학물질 제조·저장시설</li>
										<li>「송유관 안전관리법」에 따른 송유관</li>
									</ul>
								</div>
							</div>
							<div class="insurance-inner-box-list">
								<span class="icon">유해<br>화학물질</span>
								<div class="text">
									<p>위해관리계획서 작성 제출대상 유해화학물질 취급시설</p>
									<ul>
										<li>사고대비물질 지정수량(제조, 사용수량(연간), 보관·저장수량) 이상 취급시 해당</li>
									</ul>
								</div>
							</div>
							<div class="insurance-inner-box-list">
								<span class="icon">해양</span>
								<div class="text">
									<p>해양시설 중 아래의 시설</p>
									<ul>
										<li>기름 및 유해액체물질 저장(비축포함)시설 중 합계 용량 300㎘ 이상인 시설</li>
										<li>오염물질저장시설 중 합계 용량 300㎘ 이상인 시설</li>
									</ul>
								</div>
							</div>
						</div>
						<p>환경책임 보험제도 정보는 <a class="goto" href="http://www.eilkorea.or.kr" target="_blank" title="새창열림">환경책임보험전산망</a>에서 확인 가능합니다.</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
</section>


<%-- ############################# 내용 (종료) ############################# --%>

