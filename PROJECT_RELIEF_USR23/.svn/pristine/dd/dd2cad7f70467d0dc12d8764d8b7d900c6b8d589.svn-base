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
	<form id="registForm" name="registForm" method="post" role="form" id="usercheck" name="JoinUserAgree" onsubmit="return false;">
		<input type="hidden" id="appUseStusCd" name="appUseStusCd" value="1">
		<input type="hidden" id="certifyYn" name="certifyYn" value="N">
		<input type="hidden" id="indvInfoClctAgreYn" name="indvInfoClctAgreYn" value="<c:out value="${model.indvInfoClctAgreYn}"/>">
		<input type="hidden" id="thptyPvsnAgreYn" name="thptyPvsnAgreYn" value="<c:out value="${model.thptyPvsnAgreYn}"/>">
		<div class="contents-wrap join">
			<div class="join-wrap">
				<!-- 휴대전화인증 -->
				<h4>본인인증</h4>
				<div class="join-phone">
					<ul>
						<li>ㆍ회원가입을 하기 위해서는 반드시 본인인증을 해야합니다. 아래의 휴대전화 인증을 하시기 바랍니다.</li>
						<li>ㆍ휴대전화로 본인인증하시면 별도의 연동 절차 없이 편리하게 서비스를 이용하실 수 있습니다.</li>
						<li>ㆍ만 14세 미만 아동은 회원가입 시, 보호자의 동의가 함께 진행되어야 합니다.</li>
						<li>ㆍ개인정보보호법 제22조 6항 및 정보통신망법 제31조 1항에 따라, 만 14세 미만 아동은 회원가입 시 보호자(법정대리인)의 동의 없이는 회원가입을 할 수 없습니다</li>
					</ul>
					<div class="btnWrap type3">
						<a href="#void" id="btnCertify">휴대폰 인증</a>
					</div>
				</div>

				<!-- 정보등록 -->
				<h4>정보등록</h4>
				<div class="h20">
					<p align="right">
						<font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.
					</p>
				</div>
				<div class="formType1 tableWrap type3" style="padding-top: 10px;">
					<table>
						<colgroup>
							<col style="width: 17%;">
							<col style="width: 83%;">
						</colgroup>
						<tr>
							<td class="must">
								<span>성명</span>
							</td>
							<td>
								<input type="text" id="userNm" name="userNm" readonly="readonly" placeholder="성명" maxlength="10">
								<div class="eheck_font" id="name_check"></div>
							</td>
						</tr>
						<tr>
							<td class="must">
								<span>회원 아이디</span>
							</td>
							<td>
								<input id="mode" name="mode" type="hidden" />
								<input id="userNo" name="userNo" type="hidden" />
								<input type="text" id="userId" name="userId" placeholder="회원 아이디" maxlength="20">
								<button class="app-small-btn" id="btnDuplicate" disabled="disabled">중복확인</button>
								<div class="eheck_font" id="id_check"></div>
							</td>
						</tr>
						<tr>
							<td class="must">
								<span>비밀번호</span>
							</td>
							<td>
								<input type="password" id="pswd" name="pswd" placeholder="비밀번호" maxlength="30">
								<div class="formDesc" id="pw_check">영문,숫자,특수문자(!,@,#,$,%,^,&amp;,*,?,_,~)를 포함하여 9자리 이상 설정하십시오</div>
							</td>

						</tr>
						<tr>
							<td class="must">
								<span>비밀번호 확인</span>
							</td>
							<td>
								<input type="password" id="pswdCnfm" name="pswdCnfm" placeholder="비밀번호 확인" maxlength="30">
								<div class="formDesc" id="pw2_check"></div>
							</td>
						</tr>
						<tr>
							<td class="must">
								<span>생년월일</span>
							</td>
							<td>
								<div>
									<input id="brdt" name="brdt" type="hidden" />
									<select id="bryy" disabled></select>
									<select id="brmm" disabled></select>
									<select id="brdd" disabled></select>
									<div class="eheck_font" id="birth_check"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="must">
								<span>휴대전화번호</span>
							</td>
							<td>
								<input id="mbtelNo" name="mbtelNo" type="hidden" />
								<select id="mbtelNo1" name="mbtelNo1" disabled></select>
								<span class="mark">-</span>
								<input id="mbtelNo2" name="mbtelNo2" style="width: 100px" disabled maxlength="4" />
								<span class="mark">-</span>
								<input id="mbtelNo3" name="mbtelNo3" style="width: 100px" disabled maxlength="4" />
								<div class="formDesc">
									<div id="appMblAt"></div>
									<div class="eheck_font" id="phone_check"></div>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<span>유선전화번호</span>
							</td>
							<td>
								<input id="telno" name="telno" type="hidden" />
								<select id="telno1" name="telno1"></select>
								<span class="mark">-</span>
								<input id="telno2" name="telno2" style="width: 100px" maxlength="4" />
								<span class="mark">-</span>
								<input id="telno3" name="telno3" style="width: 100px" maxlength="4" />
							</td>
						</tr>
						<tr>
							<td>
								<span>전자우편주소</span>
							</td>
							<td>
								<div>
									<input id="emlAddr" name="emlAddr" type="hidden" />
									<input id="emlAddr1" name="emlAddr1" maxlength="30" />
									<span class="mark">@</span>
									<input id="emlAddr2" name="emlAddr2" maxlength="30" />
									<select id="emlDomain" name="emlDomain" style="width: 130px"></select>
									<div class="formDesc">
										<!-- 										<div id="appEmlAt"></div> -->
										<div class="eheck_font" id="email_check"></div>
									</div>
								</div>
								<!-- 								<p class="formDesc" style="width: 650px;">※ 처리결과안내 등의 알림 서비스를 받고자 하시는 분은 [이메일 정보 수신 동의] 선택하여 주시기 바랍니다.</p> -->
						</tr>
						<tr>
							<td class="must">
								<span>주소</span>
							</td>
							<td class="address">
								<div>
									<input placeholder="우편번호" name="zip" id="zip" type="text" readonly="readonly">
									<button type="button" id="btnPostCode">주소검색</button>
								</div>
								<div>
									<input style="width: 50%;" placeholder="도로명 주소" name="addr" id="addr" type="text" readonly="readonly" />
									<input style="width: 50%;" placeholder="상세주소" name="daddr" id="daddr" type="text" maxlength="100" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!-- 버튼 -->
				<div class="btnWrap type3">
					<button id="btnSave" type="submit" class="btn btn-primary">회원가입</button>
				</div>
			</div>
		</div>
	</form>
</section>
<%-- ############################# 내용 (종료) ############################# --%>
