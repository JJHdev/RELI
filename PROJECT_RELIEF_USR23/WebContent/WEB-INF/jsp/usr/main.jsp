<%--
*******************************************************************************
***	명칭: usr/main.jsp
***	설명	: 사용자시스템 메인 페이지
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021-07-09    LSH        First Coding.
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

<link rel="stylesheet" type="text/css" href="<c:url value='/css/usr/main.css'/>" />

<!-- 메인 컨텐츠 전체영역 -->
<div class="main">

	<section class="visual">
		<div class="visual-wrap">
			<h2>환경오염피해구제법</h2>
			<p>
				가해자 무과실책임 부과 및 배상책임한도 설정 &middot; 책임대상 시설 및 피해배상 범위 &middot; 피해자
				<br class="pc" />
				입증부담 경감 &middot; 환경책임보험 가입 &middot; 환경오염피해구제계정 설치
			</p>
			<div class="swiper">
				<div class="swiper-wrapper sameH">
					<div class="swiper-slide">
						<dl>
							<dt>환경오염 피해구제란?</dt>
							<dd>환경오염으로 인해 피해를 받고 있는 사람들에게 도움을 전달하여 피해를 보상받을 수 있습니다.</dd>
						</dl>
						<a href="<c:url value='/usr/info/openReliefInfo.do'/>" class="bgc1">자세히 보기</a>
						<i>
							<img src="<c:url value='/images/main/i-swiper-01.png'/>" alt="" />
						</i>
					</div>
					<div class="swiper-slide">
						<form>
							<dl style="padding-bottom: 80px;">
								<dt>처리결과 조회하기</dt>
								<dd></dd>
								<!-- 								<dd>기존 환경오염피해 인정을 받으신 분들 중 식별아이디를 보유하고 계시면 처리결과 조회가 가능합니다.</dd> -->
								<dd>
									<input type="text" class="idntfcId" placeholder="식별 인증번호 입력" />
								</dd>
								<dd></dd>
							</dl>
							<a href="#" class="bgc1 searchIdNTFC">조회하기</a>
						</form>
						<i>
							<img src="<c:url value='/images/main/i-swiper-02.png'/>" alt="" />
						</i>
					</div>
					<div class="swiper-slide">
						<dl>
							<dt>나에게 맞는 피해구제 제도 찾기</dt>
							<dd>자가진단을 통해 나의 기준에 맞는 제도를 찾아 피해신청을 할 수 있도록 한국환경산업 기술원이 도와드립니다.</dd>
						</dl>
						<a href="<c:url value='/usr/relief/openSelfCheck.do'/>" class="bgc3">자가진단</a>
						<i>
							<img src="<c:url value='/images/main/i-swiper-03.png'/>" alt="" />
						</i>
					</div>

				</div>
				<!-- If we need navigation buttons -->
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>

				<div class="swiper-pagination"></div>
			</div>

		</div>
	</section>


	<section class="info">
		<div class="info-wrap">

			<div class="info-left">

				<div class="info-left-list">
					<div class="info-left-list-item">
						<p>피해구제 절차</p>
						<a href="<c:url value='/usr/info/openProcessRelief.do'/>" class="btn">상세보기</a>
						<i>
							<img src="<c:url value='/images/main/i-info-02.png'/>" alt="" />
						</i>
					</div>
					<div class="info-left-list-item">
						<p>신청서류양식 다운로드</p>
						<a href="<c:url value='/usr/file/listPapeMng.do'/>" class="btn">바로가기</a>
						<i>
							<img src="<c:url value='/images/main/i-info-03.png'/>" alt="" />
						</i>
					</div>
				</div>

				<div class="info-video videoSwiper">
					<div class="swiper-wrapper">
						<div class="swiper-slide" style="background-image:url(<c:url value='/images/main/video_img.jpg'/>)">
<!-- 							<p> -->
<!-- 								신속하고 공정한 <b>환경피해 구제제도</b> -->
<!-- 							</p> -->
							<i>
								<img src="<c:url value='/images/main/i-play.svg'/>" alt="" />
							</i>
							<div class="cover"></div>
						</div>
					</div>
				</div>

			</div>

			<div class="info-right box">

				<div class="info-call">
					<a href="tel:02-2284-1850">
						<span>환경오염 피해구제</span>
						콜센터
						<br />
						02-2284-1850
					</a>
					<p>
						영업시간
						<span>영업시간 월~금 09:00 ~ 18:00</span>
						<br />
						주말 및 공휴일 휴일
					</p>
					<i>
						<img src="<c:url value='/images/main/call-icon.png'/>" alt="" />
					</i>
				</div>

				<div class="info-link">
					<div class="link">
						<a href="https://www.healthrelief.or.kr/home/main.do" target="_blank" title="새창열림">
							가습기 살균제
							<br />
							피해지원 종합포털
						</a>
					</div>
					<div class="link">
						<a href="https://www.adrc.or.kr/user/main.do" target="_blank" title="새창열림">
							석면피해 구제
							<br />
							지원 종합포털
						</a>
					</div>
				</div>
				<div class="banner">
                    <a href="https://www.clean.go.kr/" target="_blank" title="새창열림"></a>
				</div>

			</div>

		</div>
	</section>

	<div class="mainLayer">
		<div class="cover"></div>
		<div class="mainLayer-wrap">
			<div class="mainLayer-tit">
				<p>신속하고 공정한 환경피해 구제제도</p>
				<button class="close"></button>
			</div>
			<div class="mainLayer-inner">
				<video muted controls>
<%-- 					<source src="<c:url value='/images/video/mainVideo.wmv'/>" /> --%>
					<source src="<c:url value='/images/video/mainVideo.mp4'/>" />
				</video>
			</div>
		</div>
	</div>



</div>
<!-- //메인 컨텐츠 전체영역 -->

<%-- ############################# 내용 (종료) ############################# --%>
