<%--
*******************************************************************************
***    명칭: openBiocideProduct.jsp
***    설명: 살생물제품 안내
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
	<div class="contents-wrap insurance">

		<div class="tabWrap type4">
			<ul class="box">
				<li><a href="<c:url value='/usr/info/openBiocideInfo.do'/>">살생물제품 피해구제</a></li>
				<li class="on"><a href="<c:url value='/usr/info/openBiocideProduct.do'/>">살생물제품 안내</a></li>
			</ul>
		</div>

		<div class="intro-bio">
			<h3>살생물제품의 유형</h3>
			<div class="intro-find-list">
				<ul>
					<li>
						<h4 class="biocide-list">1. 살균제류<br />(소독제류)</h4>
						<ul class="biocide-ul">
							<li>
								<h4 class="biocide-inner">가. 살균제</h4>
								<div class="biocide-div">
									<p>가정, 사무실, 다중이용시설 등 일상적인 생활공간 또는 그 밖의 공간에서 살균, 멸균, 소독, 항균
										등의 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">나. 살조제</h4>
								<div>
									<p>수영장 등 실내·실외 물놀이시설, 수족관, 어항 등 수중에 존재하는 조류의 생육을 억제하여 사멸하는
										용도로 사용하는 제품(공공수역에 사용하는 것은 제외한다)</p>
								</div>
							</li>
						</ul>
					</li>
					<li>
						<h4 class="biocide-list">2. 구제제류</h4>
						<ul class="biocide-ul">
							<li>
								<h4 class="biocide-inner">가. 살서제</h4>
								<div class="biocide-div">
									<p>쥐 등 설치류를 제거하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">나. 기타 척추동물 제거제</h4>
								<div class="biocide-div">
									<p>설치류를 제외한 그 밖에 유해한 척추동물을 제거하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">다. 살충제</h4>
								<div class="biocide-div">
									<p>파리, 모기, 개미, 바퀴벌레, 진드기 등 곤충을 제거하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">라. 기타 무척추동물 제거제</h4>
								<div class="biocide-div">
									<p>곤충을 제외한 그 밖에 유해한 무척추동물을 제거하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">마. 기피제</h4>
								<div>
									<p>기피의 방법을 이용하여 유해생물을 무해(無害)하게 하거나 억제하기 위한 용도로 사용하는 제품(인체에 직접 적용하는 것은 제외한다)</p>
								</div>
							</li>
						</ul>					
					</li>
					<li>
						<h4 class="biocide-list">3. 보존제류<br />(방부제류)</h4>
						<ul class="biocide-ul">
							<li>
								<h4 class="biocide-inner">가. 제품보존용 보존제</h4>
								<div class="biocide-div">
									<p>제품의 유통기한을 보장하기 위하여 제품의 보관 또는 보존을 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">나. 제품표면처리용 보존제</h4>
								<div class="biocide-div">
									<p>제품 표면의 초기 속성을 보호하기 위하여 제품 표면 또는 코팅을 보존하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">다. 섬유·가죽류용 보존제</h4>
								<div class="biocide-div">
									<p>섬유, 가죽, 고무 등을 보존하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">라. 목재용 보존제</h4>
								<div class="biocide-div">
									<p>목재 또는 목재 제품을 보존하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">마. 건축자재용 보존제</h4>
								<div class="biocide-div">
									<p>목재를 제외한 다른 건축자재, 석조, 복합 재료를 보존하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">바. 재료·장비용 보존제</h4>
								<div class="biocide-div">
									<p>다음의 재료ㆍ장비 등을 보존하기 위한 용도로 사용하는 제품<br />
									1) 산업공정에서 이용되는 재료ㆍ장비ㆍ구조물<br />
									2) 냉각 또는 처리 시스템에 사용되는 담수 등의 액체<br />
									3) 금속ㆍ유리 또는 그 밖의 재료를 가공하거나 자르거나 깎는 데 사용되는 유체(流體)</p>
								</div>
							</li>
							<li>
								<h4 class="biocide-inner">사. 사체·박제용 보존제</h4>
								<div>
									<p>인간 또는 동물의 사체나 그 일부를 보존하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>
						</ul>
					</li>
					<li>
						<h4 class="biocide-list" style="height: 70px;">4. 기타</h4>
						<ul>
							<li>
								<h4 class="biocide-inner">선박·수중 시설용 오염방지제</h4>
								<div class="biocide-p">
									<p>선박, 양식 장비, 그 밖의 수중용 구조물에 대한 유해생물의 생장 또는 정착을 억제하기 위한 용도로 사용하는 제품</p>
								</div>
							</li>	
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="intro-biocide-product">
			<h3>살생물제품 확인</h3>
			<div class="eco-life">살생물제품의 승인 여부는 <a href="https://ecolife.me.go.kr/ecolife/" target="_blank">
            <img src="<c:url value='/images/intro/h1_logo.png'/>" alt="" height="18px" /></a>에서 확인 가능합니다.</div>
		</div>
	</div>
</section>


<%-- ############################# 내용 (종료) ############################# --%>

