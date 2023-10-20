<%--
*******************************************************************************
***    명칭: openBiocideInfo.jsp
***    설명: 살생물제품 피해구제란
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0   2023.01.10    김수현        First Coding.
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
	<div class="introWrap">
		
		<div class="tabWrap type4">
			<ul class="box">
				<li class="on"><a href="<c:url value='/usr/info/openBiocideInfo.do'/>">살생물제품 피해구제</a></li>
				<li><a href="<c:url value='/usr/info/openBiocideProduct.do'/>">살생물제품 안내</a></li>
			</ul>
		</div>
		
		<div class="intro-tit">
			<div class="intro-tit-wrap">
				<h2><span>살생물제품</span>피해구제란?</h2>
				<p>
					제조물의 결함*이 있는 살생물 제품에 노출되어 발생한 사람의 생명·건강상의 피해로서 <br class="pc" />
					아래 각 호의 어느 하나에 해당하여 원인자로부터 배상이 불가하거나 신속한 구제가 필요한 <br class="pc" />
					경우 구제급여를 지급합니다.<br />
					<small><span style="color: #26779B;">* 제조상‧설계상‧표시상 결함을 의미하며 제품 오남용은 해당되지 않습니다.</span></small>
				</p>
				<img src="<c:url value='/images/intro/intro-biorelief.jpg'/>" alt=""/>
				<div>
					<p>
						1. 살생물제품피해의 원인을 제공한 자가 무자력인 경우<br class="pc" />
						2. 집단적 피해가 발생하는 등 살생물제품피해 규모가 상당하여 신속한 조치가 필요한 경우<br class="pc" />
						3. 살생물제품피해가 심각하여 긴급한 치료나 요양이 필요한 경우<br class="pc" />
						4. 살생물제품피해의 원인이 가까운 시일 내에 완전하게 제거되지 않아 장래에도 피해가 지속될 것으로 판단되는 경우<br class="pc" />
						5. 그 밖에 환경부장관이 필요하다고 인정하는 경우
					</p>
				</div>
			</div>
		</div>
		
				<div class="intro-progress">
			<div class="intro-progress-wrap">
				<h3>피해구제의 절차는 어떻게 진행되나요?</h3>
				<div class="intro-progress-list">
					<ul>
						<li>
							<span style="color:#BCC7CA;">01</span>
							<dl>
								<dt>신청인</dt>
								<dd>한국환경산업기술원에 <br />피해구제 신청</dd>
							</dl>
						</li>
						<li>
							<span style="color:#ACCD92;">02</span>
							<dl>
								<dt>한국환경산업기술원</dt>
								<dd>서류검토 및 <br />피해조사 실시</dd>
							</dl>
						</li>
						<li>
							<span style="color:#82A0D9;">03</span>
							<dl>
								<dt>전문위원회 및 <br /> 관리위원회 </dt>
								<dd>피해 인정 여부 검토 </dd>
							</dl>
						</li>
						<li>
							<span style="color:#ACCD92;">04</span>
							<dl>
								<dt>한국환경산업기술원</dt>
								<dd>심사결과에 따른 <br />구제급여 지급</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="intro-find">
			<h3>살생물제품피해 구제급여 종류</h3>
			<div class="intro-find-list">
				<ul>
					<li>
						<h4 class="biocide-info">진료비</h4>
						<div >
							<p>요양기관에서 살생물제품 피해로 인한 상해 또는 질병의 치료비용 중 피해자가 부담하는 금액</p>
						</div>
					</li>
					<li>
						<h4 class="biocide-info">장례비</h4>
						<div>
							<p>살생물제품피해로 인하여 피해자가 사망한 경우에는 그 장례를 지낸 유족에게 지급</p>
							<p>중위소득의 89.7% (‘23년 기준, 약 310만원)</p>
						</div>
					</li>
					<li>
						<h4 class="biocide-info">사망일시 보상금</h4>
						<div>
							<p>구제급여 지급신청 당시 살생물 제품피해로 인해 사망한 피해자의 유족에게 지급</p>
							<p>장의비의 15배 (‘23년 기준, 약 4,365만원)</p>
						</div>
					</li>
					<li>
						<h4 class="biocide-info">장애일시 보상금</h4>
						<div>
							<p>살생물제품피해로 인해 부상을 당하거나 질병에 걸려 치유한 후 장애가 있는 피해자에게 피해등급에 따라 차등 <br />
							   지급 (대한의학회 장애평가 기준을 활용하여 피해등급 산정)
							</p>
							<p>*(1급)9,824만원 ~ (4급)2,432만원</p>
						</div>
					</li> 
				</ul>
			</div>
		</div>
	</div>
</section>

<!-- //end 내용 -->



<%-- ############################# 내용 (종료) ############################# --%>

