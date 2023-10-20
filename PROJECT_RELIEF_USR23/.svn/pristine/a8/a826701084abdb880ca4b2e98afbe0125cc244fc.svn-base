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
<section class="contents member">
	<form name="frmJoin">
		<div class="contents-wrap join">
			<h3>회원가입</h3>
			<div class="h50"></div>
			<div class="join-wrap">
				<p>이용규정</p>
				<div class="agree">
					<%@ include file="../cmm/regiInfo1.jsp"%>
				</div>
				<div class="h10"></div>
				<div class="inputWrap">
					<input type="radio" id="useAgree" name="useAgree" />
					<label for="useAgree">
						<font style="font-weight: bold;" color="#1E7BA9" size="4px">이용규정에 동의합니다.</font>
					</label>
					<a href="#void" data-toggle="modal" data-target="#joinAgree">자세히 보기</a>
				</div>

				<div class="h50"></div>
				<p>개인정보 수집 및 이용에 대한 안내</p>
				<div class="agree">
					<%@ include file="../cmm/regiInfo2.jsp"%>
				</div>
				<div class="h10"></div>
				<div class="inputWrap">
					<label for="essenAgree" style="padding-right: 20px;">
						<font style="font-weight: bold;" color="#1E7BA9" size="4px">필수항목 : </font>
					</label>
					<input type="radio" id="essenAgree" name="essenAgree" value="Y" />
					<label for="essenAgree">동의</label>

					<label for="essenAgree" style="padding-right: 20px;">
						<font style="font-weight: bold;" color="#1E7BA9" size="4px">선택항목 : </font>
					</label>
					<input type="radio" id="indvInfoClctAgreYnY" name="indvInfoClctAgreYn" value="Y" />
					<label for="indvInfoClctAgreYnY">동의</label>
					<input type="radio" id="indvInfoClctAgreYnN" name="indvInfoClctAgreYn" value="N" />
					<label for="indvInfoClctAgreYnN">미동의</label>
					<a href="#void" data-toggle="modal" data-target="#joinAgree2">자세히 보기</a>
				</div>

				<div class="h50"></div>
				<p>개인정보의 제 3자 제공 동의</p>
				<div class="tableWrap type4">
					<%@ include file="../cmm/regiInfo3.jsp"%>
				</div>
				<div class="h10"></div>
				<p>※ 개인정보보호법에 의거 개인정보의 제공에 따른 동의를 거부할 수 있으나, 미 동의시 당사이트 서비스의 이용에 제한됩니다.</p>

				<div class="inputWrap"  align="right">
					<label for="" style="padding-right: 20px;">
						<font style="font-weight: bold;" color="#1E7BA9" size="4px">개인정보의 제 3자 제공 동의</font>
					</label>
					<input type="radio" id="thptyPvsnAgreYnY" name="thptyPvsnAgreYn" value="Y" />
					<label for="thptyPvsnAgreYnY">동의</label>
					<input type="radio" id="thptyPvsnAgreYnN" name="thptyPvsnAgreYn" value="N" />
					<label for="thptyPvsnAgreYnN">미동의</label>
				</div>

				<div class="btnWrap type3">
					<ul>
						<li>
							<a href="javascript:joinAgree();" class="">회원가입하러가기</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</form>
</section>

<div class="modal fade" id="joinAgree" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header" style="padding-top: 20px;">
				<h4>이용규정</h4>
			</div>
			<div class="modal-body" style="overflow: auto; height: 400px;">
				<%@ include file="../cmm/regiInfo1.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="joinAgree2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header" style="padding-top: 20px;">
				<h4>개인정보의 제 3자 제공 동의</h4>
			</div>
			<div class="modal-body" style="overflow: auto; height: 400px;">
				<%@ include file="../cmm/regiInfo2.jsp"%>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>



<%-- ############################# 내용 (종료) ############################# --%>
