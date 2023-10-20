<%--
*******************************************************************************
***    명칭: openInfoIntrlck.jsp
***    설명: 마이페이지 - 정보연동
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.02    LSH        First Coding.
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
	<div class="contents-wrap interlock">

		<h3>정보연동</h3>

		<div class="interlock-wrap">
			<div class="interlock-tit">
				<p>
					‘정보연동’은 환경오염피해구제 관리시스템에 가입한 회원이 사전에 피해인정 접수(우편접수, 방문접수, 찾아가는 서비스 등)를 진행 후 <b>식별 아이디를 보유하고 있을 경우 각종 정보를 마이페이지에서 확인하기 위한 절차입니다.</b>
				</p>
				<p class="strong">식별아이디 정보와 회원정보가 연동되면 피해인정 접수했던 정보 (신청현황, 심의결과, 지급현황,서류 현황 등)를 조회할 수 있습니다.</p>
			</div>
			<form id="registForm" method="post" role="form" name="regiInfoIntrlck" onsubmit="return false;">
				<div align="right">
					<span style="font-size: 15px;">
						<font color="#ff0000">*</font>&nbsp;은 필수 입력 항목입니다.
					</span>
				</div>
				<input id="aplcntNo" name="aplcntNo" type="hidden" value="${model.aplcntNo}" />
				<input id="papeDtySeCd" name="papeDtySeCd" type="hidden" value="PP03" />
				<input id="aplySeCd" name="aplySeCd" type="hidden" value="ALL" />
				<div class="tableWrap type6 formType2">
					<table class="align_l">
						<colgroup>
							<col style="width: 20%;" />
							<col style="width: 80%;" />
						</colgroup>
						<tr>
							<th class="must">연동대상</th>
							<td>
								<div class="inputWrap">
									<input type="radio" id="R11" name="intrlckSeCd" value="R11" />
									<label for="R11">본인</label>
									<input type="radio" id="R12" name="intrlckSeCd" value="R12" />
									<label for="R12">대리인</label>
								</div>
							</td>
						</tr>
						<tr>
							<th class="must">연동대상자 성명</th>
							<td>
								<input type="text" class="col-md-5" id="trprNm" name="trprNm" placeholder="성명 입력 또는 식별아이디 찾기 진행" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th class="must">연동대상자 식별아이디</th>
							<td>
								<div class="inputWrap idWrap">
									<input type="text" class="col-md-3" id="trprIdntfcId" name="trprIdntfcId" placeholder="식별아이디 찾기 진행"  maxlength="20" />
									<button type="button" class="col-md-2" data-toggle="modal" data-target="#infoMemberId">식별아이디 찾기</button>
								</div>
							</td>
						</tr>
						<tr>
							<th class="must">연동대상자 휴대전화번호</th>
							<td>
								<input type="text" class="col-md-5" id="mbtelNo" name="mbtelNo" placeholder="휴대전화번호 입력 또는식별아이디 찾기 진행" maxlength="30"/>
							</td>
						</tr>
						<tr>
							<th class="must">연동대상자 생년월일</th>
							<td>
								<div>
									<input id="trprBrdt" name="trprBrdt" type="hidden" />
									<input type="text" class="call" style="width: 100px" id="bryy" name="bryy" placeholder="예시) 2021" maxlength="4" />
									<input type="text" class="call" style="width: 100px" id="brmm" name="brmm" placeholder="예시) 01" maxlength="2"/>
									<input type="text" class="call" style="width: 100px" id="brdd" name="brdd" placeholder="예시) 01" maxlength="2"/>
									<div class="eheck_font" id="birth_check"></div>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="space type1"></div>
				<div id="papeGroup" class="usr-file-group"></div>
			</form>
		</div>

		<div class="btnWrap type3">
			<a class="blue">
				<button id="btnSave">정보연동 신청</button>
			</a>
		</div>
	</div>
</section>

<!-- 식별아이디 찾기 -->
<div class="modal fade layerPop form type4" id="infoMemberId" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="layerPop-inner">
					<div class="inputGroup">
						<p class="must">성명</p>
						<div class="inputWrap">
							<input type="text" placeholder="성명을 입력하세요." name="sufrerNm" id="sufrerNm" maxlength="10"/>
						</div>
					</div>
					<div class="inputGroup">
						<p class="must">연동대상 주민등록번호</p>
						<div class="inputWrap two">
							<input id="sufrerRrno" name="sufrerRrno" type="hidden" />
							<input type="text" id="sufrerRrno1" name="sufrerRrno1" placeholder="주민등록번호 앞 6자리" maxlength="6"/>
							<span class="mark">-</span>
							<input type="password" id="sufrerRrno2" name="sufrerRrno2" placeholder="주민등록번호 뒤 7자리" maxlength="7"/>
						</div>
					</div>
					<div class="inputGroup">
						<p class="must">연동대상자 등록된 연락처</p>
						<div class="inputWrap call">
							<input type="text" placeholder="등록된 휴대전화 번호를 입력해주세요" id="sufrerMbtelNo" name="sufrerMbtelNo" maxlength="30"/>
							<button id="adrBtn1" onclick="javascript:fnIdMember();">조회</button>
						</div>
					</div>
					<table id="searchedTable" class="table table-striped table-project-tasks">
						<tbody></tbody>
					</table>
				</div>
				<div class="modal-footer">
					<a href="#void" alt="확인" data-dismiss="modal">
						<button class="btn-layerSub">확인</button>
					</a>
					<button type="button" class="btn-layerSub" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(".modal-dialog").draggable({
		handle : ".modal-header"
	})<!-- make modal draggable -->
</script>

<%-- ############################# 내용 (종료) ############################# --%>
