<%--
*******************************************************************************
***    명칭: listPapeMng.jsp
***    설명: [자료실] 신청서류양식
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    김주호        First Coding.
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

<section class="contents dataroom">
	<div class="contents-wrap doc">

		<div class="doc-tit">환경오염 피해구제급여</div>

        <form id="form1" name="form1" onsubmit="return false;">
		<div class="doc-form">
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>신청구분</p>
				</div>
				<div class="doc-form-input" id="layerAplySe">
					<!-- <div class="inputWrap">
						<input type="radio" id="apply1" name="apply" />
						<label for="apply1">본인신청</label>
						<input type="radio" id="apply2" name="apply" />
						<label for="apply2">대리인신청(피해자 생존)</label>
						<input type="radio" id="apply3" name="apply" />
						<label for="apply3">대리인신청(피해자 사망)</label>
					</div> -->
				</div>
			</div>
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>급여종류</p>
				</div>
				<div class="doc-form-input relief" id="layerUpPape">
				</div>
			</div>
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>제출서류</p>
				</div>
				<div class="doc-form-input" id="layerPape">
				</div>
			</div>
		</div>
        </form>

		<div class="btnWrap type2">
			<a href="#void" class="blue" id="reliefDownBtn">환경오염 피해구제급여 제출서류 다운로드</a>
		</div>

		<div class="doc-space"></div>
		
		
		<div class="doc-tit">살생물제품 피해구제급여</div>

        <form id="form3" name="form3" onsubmit="return false;">
		<div class="doc-form">
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>신청구분</p>
				</div>
				<div class="doc-form-input" id="layerAplySeBiocide">
					<!-- <div class="inputWrap">
						<input type="radio" id="apply1" name="apply" />
						<label for="apply1">본인신청</label>
						<input type="radio" id="apply2" name="apply" />
						<label for="apply2">대리인신청(피해자 생존)</label>
						<input type="radio" id="apply3" name="apply" />
						<label for="apply3">대리인신청(피해자 사망)</label>
					</div> -->
				</div>
			</div>
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>급여종류</p>
				</div>
				<div class="doc-form-input relief" id="layerUpPapeBiocide">
				</div>
			</div>
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>제출서류</p>
				</div>
				<div class="doc-form-input" id="layerPapeBiocide">
				</div>
			</div>
		</div>
        </form>

		<div class="btnWrap type2">
			<a href="#void" class="blue" id="biocideDownBtn">살생물오염 피해구제급여 제출서류 다운로드</a>
		</div>

		<div class="doc-space"></div>

		<div class="doc-tit">취약계층 소송지원</div>

        <form id="form2" name="form2" onsubmit="return false;">
		<div class="doc-form">
			<div class="doc-form-list">
				<div class="doc-form-tit">
					<p>제출서류</p>
				</div>

				<div class="doc-form-input" id="layerLwstPape">
					<!-- <div class="inputWrap checkBtn">
						<input type="checkbox" id="vulBtn1" name="vulBtn"/><label for="vulBtn1">개인정보 수집 이용 및 제공 동의서</label>
						<input type="checkbox" id="vulBtn2" name="vulBtn"/><label for="vulBtn2">소송지원 신청서</label>
					</div> -->
				</div>
			</div>
		</div>
        </form>

		<div class="btnWrap type2">
			<a href="#void" class="blue" id="lwstDownBtn">취약계층 소송지원 제출서류 다운로드</a>
		</div>


	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
