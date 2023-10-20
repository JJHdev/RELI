<%--
*******************************************************************************
***    명칭: listUserInfo.jsp
***    설명: 사용자정보 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.13    gjhan        First Coding.
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
<!-- 내용 -->
<section class="contents other">
	<div class="contents-wrap policy">

		<!-- <img src="../images/page/policy.jpg" alt="" /> -->

		<h3 class="icon_tit">한국환경산업기술원 저작권 보호 정책입니다.</h3>
		<ul class="con">
			<li>
				본 홈페이지에서 제공하는 모든 자료는 저작권법에 의하여 보호받는 저작물로서 이에 대한 무단 복제 및 배포를 원칙적으로 금합니다.
				<br>
				이를 무단 복제 배포하는 경우 저작권법 제136조, 137조, 138조에 의한 권리의 침해죄, 부정발행 등의 죄, 출처명시위반의 죄 등에 해당될 수 있습니다.
				<br>
				<span class="c_black">단, 자유이용이 가능한 저작물은 "공공저작물 자유이용허락 표시 기준(공공누리,KOGL)"을 부착하여 개방하고 있으므로 공공누리 표시가 부착된 저작물인지를 확인한 이후에 자유이용하시기 바랍니다. 자유이용의 경우에는 반드시 저작물의 출처를 구체적으로 표시하여야 합니다.</span>
				<br>
				<span class="c_blue">※ 저작권법 제24조의2(공공저작물의 자유이용)에 따라 본 홈페이지에서 제공하는 자료 중에서 한국환경산업기술원이 저작재산권의 전부를 보유한 저작물의 경우에는 별도의 이용허락없이 자유이용이 가능합니다.</span>
			</li>

			<li>
				공공데이터법에 근거하여 본 홈페이지에서 제공하는 공공데이터는 누구나 이용가능하고, 영리 목적의 이용을 포함한 자유로운 활용이 보장됩니다.
				<br>
				(공공데이터법 제1조, 제3조)
				<br>
				<span class="c_black">단, 정보공개법 제9조의 비공개대상정보와 저작권법 및 그 밖의 다른 법령에서 보호하고 있는 제3자의 권리가 포함된 것으로 해당 법령에 따른 정당한 이용허락을 받지 아니한 정보는 제공 대상에서 제외됩니다.(공공데이터법 제17조)</span>
			</li>

			<li>
				본 홈페이지에는 한국환경산업기술원이 저작권 전부를 갖고 있지 아니한 자료도 제공되고 있습니다. 또한 우리 환경에 대한 관심과 애정, 환경보전활동에 동참하고자 개인이나 기관, 단체 등에서 무상으로 제공한 자료들도 있으므로 이러한 자료를 자유롭게 이용하기 위해서는 반드시 해당 저작권자의 허락을 받으셔야 합니다.
				<br>
				<span class="c_black">즉, 공공누리가 부착되지 않은 자료들을 사용하고자 할 경우에는 담당자와 사전에 협의한 이후에 이용하여 주시기 바랍니다.</span>
			</li>

			<li>다른 인터넷 사이트 상의 화면에서 한국환경산업기술원 홈페이지의 저작물을 직접 링크시킬 경우에는 링크 이용자가 본 인터넷 저작권 정책을 간과할 수 있으므로 본 인터넷 저작권 정책도 함께 링크해 주시기 바랍니다.</li>

			<li>
				본 홈페이지에서 개방 중인 자료 중 한국환경산업기술원이 저작권 전부를 갖고 있지 아니한 자료(다른 저작자와 저작권을 공유한 자료 등)의 경우에는 저작권 침해의 소지가 있으므로 단순 열람 외에 무단 변경, 복제·배포, 개작 등의 이용은 금지되며 이를 위반할 경우 관련법에 의거 법적 처벌을 받을 수 있음을 알려드립니다.
				<br>
				한국환경산업기술원은 모든 국민이 안전하게 환경관련 정보를 활용하실 수 있도록 지속적으로 노력하겠습니다.
			</li>
		</ul>

		<ul class="btn-wrap">
			<li>
				<a href="https://www.law.go.kr/%EB%B2%95%EB%A0%B9/%EA%B3%B5%EA%B3%B5%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%9D%98%EC%A0%9C%EA%B3%B5%EB%B0%8F%EC%9D%B4%EC%9A%A9%ED%99%9C%EC%84%B1%ED%99%94%EC%97%90%EA%B4%80%ED%95%9C%EB%B2%95%EB%A5%A0" target="_blank" title="새창열림"> 국가법령정보센터 공공데이터법 바로가기</a>
			</li>
			<li>
				<a href="https://www.law.go.kr/%EB%B2%95%EB%A0%B9/%EC%A0%80%EC%9E%91%EA%B6%8C%EB%B2%95" target="_blank" title="새창열림"> 국가법령정보센터 저작권법 바로가기</a>
			</li>
		</ul>
	</div>
</section>

<script>
	$(function() {

		// 메뉴경로 숨김
		if ($('section.sub-visual'))
			$('section.sub-visual').hide();

	});
</script>

<%-- ############################# 내용 (종료) ############################# --%>
