<%--
*******************************************************************************
***    명칭: writeMyQna.jsp
***    설명: [마이페이지] QNA 등록 화면
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

<section class="contents board">
	<div class="contents-wrap notice">
		<form id="registForm" name="registForm" method="post" enctype="multipart/form-data">
			<div class="viewWrap type1 formType2">
				<div class="viewData">
					<div>
						<b class="must">말머리</b>
						<div>
							<select id="qnaType" name="nttClCd" style="width: 300px;" class="bgWhite"></select>
						</div>
					</div>
					<div>
						<b class="must">제목</b>
						<div>
							<input type="text" id="nttSj" name="nttSj" style="width: 300px;" maxlength="100" />
							<div class="eheck_font" id="nttSj_check" ></div>
						</div>

					</div>
					<div>
						<b class="must">글 비밀번호</b>
						<div>
							<input type="password" id="nttPswd" name="nttPswd" style="width: 300px;" />
							<div class="eheck_font" id="pwd_check" ></div>
						</div>
					</div>
				</div>
				<div class="viewTextarea">
					<textarea id="nttCn" name="nttCn" placeholder="내용을 입력해주세요."></textarea>
				</div>
				<div class="btnWrap type3">
				    <a class="hoverColor2" href="#void" id="btnList">목록으로</a>
					<a class="hoverColor1" href="#void" id="popupQnaWriteSuccess">등록</a>
					
				</div>
			</div>
		</form>
	</div>
</section>
<%-- ############################# 내용 (종료) ############################# --%>