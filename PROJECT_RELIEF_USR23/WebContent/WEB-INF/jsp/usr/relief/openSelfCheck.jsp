<%--
*******************************************************************************
***    명칭: openSelfCheck.jsp
***    설명: 나에게 맞는 피해구제제도 찾기
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

<script> doSetStep('<c:out value="${model.step}"/>'); </script>

<section class="contents apply">

	<a href="#void" id="btnAdminCert" class="adminBtn">관리자 인증</a>
	<div class="contents-wrap system">

	<%-- 1단계 --%>
	<c:if test="${model.step == '1'}">
		<div class="system-tit">
			<div class="qNum"><span>Q1</span></div>
			<p>
				환경오염으로 인한<br />
				<span>건강피해 또는 재산피해</span>가 있나요?
			</p>
		</div>

		<div class="system-inner">
			<div class="ox">
				<a href="#void" id="step01A" class="no">없습니다</a>
				<a href="#void" id="step01B" class="yes">있습니다</a>
			</div>
		</div>
	</c:if>
	<%-- 2단계 --%>
	<c:if test="${model.step == '2'}">
		<div class="system-tit">
			<div class="qNum"><span>Q2</span></div>
			<p>
				환경오염피해의 <span>원인 시설<br />
				(환경배출시설, 인근공장 등)</span>이 있나요?
			</p>
		</div>

		<div class="system-inner">
			<div class="ox">
				<a href="#void" id="step02A" class="no">없습니다</a>
				<a href="#void" id="step02B" class="yes">있습니다</a>
			</div>
		</div>
	</c:if>
	<%-- 3단계 --%>
	<c:if test="${model.step == '3'}">
		<div class="system-tit">
			<div class="qNum"><span>Q3</span></div>
			<p>
				<span>환경오염 피해</span>와 관련하여 <br />
				어떤 지원을 원하시나요?
			</p>
		</div>

		<div class="system-inner">
			<div class="system-list box">
				<a href="#void" class="on">환경오염피해 <br />구제신청</a>
				<a href="#void">취약계층 <br />소송지원신청</a>
				<a href="#void">지자체 <br />민원</a>
				<a href="#void">건강영향조사 <br />청원</a>
				<a href="#void">분쟁조정 <br />신청</a>
			</div>
		</div>
	</c:if>
	<%-- 3단계 --%>
	<c:if test="${model.step == '4'}">
		<div class="system-tit">
			<div class="qNum"><span>Q4</span></div>
			<p>
				<span>환경오염 피해</span>와 관련하여 <br />
				어떤 지원을 원하시나요?
			</p>
		</div>

		<div class="system-inner">
			<div class="system-list box">
				<a href="#void" class="on">환경오염피해 <br />구제신청</a>
				<a href="#void">지자체 <br />민원</a>
				<a href="#void">건강영향조사 <br />청원</a>
			</div>
		</div>
	</c:if>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>

<!-- 환경오염피해 구제신청 -->
<div id="popupStop" class="layerPop type2 off">
    <div class="layerWrap">
        <!-- 레이어내용 -->
		<div>
			<div class="btnWrap type3">
				<a href="#void" id="btnReliefInfo" class="hoverColor1">환경오염 피해구제란?</a>
				<a href="#void" id="btnReliefBbs"  class="hoverColor2">환경오염 피해구제 질문하기</a>
			</div>
		</div>
    </div>
</div>

<!-- 환경오염피해 구제신청 -->
<div id="popupRelief" class="layerPop off">
    <div class="layerWrap">
        <!-- 레이어내용 -->
        <div class="layerPop-inner">
			<div class="layerSystem">
				<p>원인자가 존재하지 않거나 배상 능력이 없어 <br/>환경오염 피해의 전부 또는 <br/>일부를 배상 받지 못하는 피해자에 대해 <br/>의료비 등 구제급여를 지급 하는 제도입니다</p>
				<div class="btnWrap">
					<a href="#void" id="btnRelief" class="hoverColor2">환경오염 피해구제 신청 바로가기</a>
					<a href="#void" id="btnBbs1"   class="hoverColor1">환경오염 피해구제 질문하기</a>
				</div>
				<span class="call">한국환경산업기술원 환경오염피해구제실 <b>02)2284-1850</b></span>
			</div>
		</div>
    </div>
</div>

<!-- 취약계층 소송지원신청 -->
<div id="popupLwst" class="layerPop off">
    <div class="layerWrap">
        <!-- 레이어내용 -->
        <div class="layerPop-inner">
			<div class="layerSystem">
				<p>
					환경오염피해를 입은 취약계층이 손해배상청구에<br />
					필요한 <span>법률서비스 지원</span> 받을 수 있는 제도입니다.<br />
					취약계층은 저소득층, 고령자, 장애인, 고용지원<br />
					대상자, 북한이탈주민, 보훈대상자 등을 말합니다.
				</p>
				<div class="btnWrap">
					<a href="#void" id="btnLwst" class="hoverColor2">취약계층 소송지원 신청하기</a>
					<a href="#void" id="btnBbs2" class="hoverColor1">환경오염 피해구제 질문하기</a>
				</div>
				<span class="call">한국환경산업기술원 환경오염피해구제실 <b>02)2284-1850</b></span>
			</div>
		</div>
    </div>
</div>

<!-- 지자체 민원 신청이란? -->
<div id="popupLocal" class="layerPop off">
    <div class="layerWrap">
        <!-- 레이어내용 -->
        <div class="layerPop-inner">
			<div class="layerSystem">
				<p>
					환경피해의 원인사업자(공장,공사 업체 등)에게<br />
					직접 저감조치를 할 것을 요청할 수 있습니다.<br />
					별도의 조치가 없거나 계속하여 환경피해가 발생하는 경우<br />
					<span>해당 지역의 지자체(시ㆍ군ㆍ구청 환경과ㆍ건축과 등)</span>에<br />
					환경피해 발생을 신고하시면 됩니다.
				</p>
				<span class="call">한국환경산업기술원 환경오염피해구제실</span>
			</div>
		</div>
    </div>
</div>
<!-- 건강영향조사 청원이란? -->
<div id="popupHealth" class="layerPop off">
    <div class="layerWrap">
        <!-- 레이어내용 -->
        <div class="layerPop-inner">
			<div class="layerSystem">
				<p>
					환경오염으로 인하여 건강상 피해가 발생하였거나<br />
					우려되는 경우, 건강에 미치는 영향을 조사하여<br />
					줄 것을 요청할 수 있는 제도입니다.<br />
					도움이 필요하시면 하단 연락처로 연락 바랍니다.
				</p>
				<span class="call">환경부 민원 대표 전화 <b>1577-8866</b></span>
			</div>
		</div>
    </div>
</div>
<!-- 분쟁조정 신청이란? -->
<div id="popupDspMdt" class="layerPop off">
    <div class="layerWrap">
        <!-- 레이어내용 -->
        <div class="layerPop-inner">
			<div class="layerSystem">
				<p>
					환경분쟁조정제도는<br />
					크고 작은 <span>환경 분쟁을 복잡한 소송 절차를 통하지<br />
					않고 신속히 해결</span>하기 위해 마련한 제도 입니다.
				</p>
				<div class="btnWrap">
					<a href="#void" id="btnDspMdt" class="hoverColor1">중앙환경분쟁조정위원회 바로가기</a>
				</div>
				<span class="call">중앙환경분쟁조정위원회 <b>044)201-7969</b></span>
				<span class="mail">우편 및 방문접수 <b>세종특별자치시 도움6로 11 정부세종청사 6동 472호</b></span>
			</div>
		</div>
    </div>
</div>
