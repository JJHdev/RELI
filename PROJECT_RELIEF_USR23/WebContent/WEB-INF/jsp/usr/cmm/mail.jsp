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
	<div class="contents-wrap mail">

		<!-- <img src="../images/page/mail.jpg" alt="" /> -->

		<div class="head">
		<img src="<c:url value='/images/other/mail_icon.jpg'/>" alt="" />
			<p class="c_sky">환경오염피해구제시스템 웹사이트는 이메일주소 무단수집을 거부합니다</p>
		</div>

		<p class="boxType1">본 웹사이트에 게시된 이메일 주소가 전자우편 수집 프로그램이나 그밖의 기술적 장치를 이용하여 무단으로 수집되는 것을 거부하며, 이를 위반시 정보통신망법에 의해 형사처벌됨을 유념하시기 바랍니다.</p>

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
