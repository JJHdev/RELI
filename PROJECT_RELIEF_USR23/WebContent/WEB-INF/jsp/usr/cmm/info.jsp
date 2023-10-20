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
	<div class="contents-wrap info">

		<div class="head">
			<img src="<c:url value='/images/other/info_icon.jpg'/>" alt="" />
			<p class="c_green">
				<span class="c_black">한국환경산업기술원(이하 "기술원"이라 함)이 취급하는 모든 개인정보는</span>
				관련법령에 근거하거나 정보주체의 동의에 의하여 수집·보유 및 처리되고 있습니다.
			</p>
		</div>

		<ul class="boxType1 ball1">
			<li>본 개인정보 처리방침은 2023년 9월 8일부터 시행됩니다.</li>
			<li>한국환경산업기술원 환경오염피해구제관리시스템(ehtis.or.kr/relief)은 정보주체의 자유와 권리 보호를 위해 ｢개인정보 보호법｣ 및 관계 법령이 정한 바를 준수하여, 적법하게 개인정보를 처리하고 안전하게 관리하고 있습니다.</li>
			<li>이에 ｢개인정보 보호법｣ 제30조에 따라 정보주체에게 개인정보 처리에 관한 절차 및 기준을 안내하고, 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.</li>
		</ul>
		<div class="h50"></div>
		<h3 class="icon_tit">주요 개인정보 처리 표시(라벨링)</h3>
		<div class="tableWrap type5">
			<table>
				<colgroup>
					<col width="33%"> 
					<col width="33%">
					<col width="34%">
				</colgroup>
				<tbody class="align_L" style="text-align: center;">
					<tr>
						<th>개인정보 처리목적</th>
						<th>개인정보 처리항목</th>
						<th>개인정보의 보유기간</th>
					</tr>
					<tr>
						<td>
							<span> 
								<div class="labelIcon lblIcon1"></div>
							</span>
						</td>
						<td>
							<span> 
								<div class="labelIcon lblIcon2"></div>
							</span>
						</td>
						<td>
							<span> 
								<div class="labelIcon lblIcon3"></div>
							</span>
						</td>
					</tr>
					<tr>
						<td>1. 구제급여 신청 및 지급에 관한 사무<br>2. 손해배상 및 다른 구제와의 관계<br>※ 세부사항은 개인정보 처리방침 본문 확인</td>
						<td>이름, 주소, 주민등록번호, 휴대폰번호, 이메일, 건강정보, 소송이력정보 등<br>※ 세부사항은 개인정보 처리방침 본문 확인</td>
						<td>이용목적 완료 시 까지<br>※일부 개인정보는 관련 법령에 따라 보관</td>
					</tr>
					<tr>
						<th>개인정보의 제공</th>
						<th>개인정보 처리위탁</th>
						<th>개인정보 고충처리부서</th>
					</tr>
					<tr>
						<td>
							<span> 
								<div class="labelIcon lblIcon7"></div>
							</span>
						</td>
						<td>
							<span> 
								<div class="labelIcon lblIcon5"></div>
							</span>
						</td>
						<td>
							<span> 
								<div class="labelIcon lblIcon6"></div>
							</span>
						</td>
					</tr>
					<tr>
						<td>제공받는자 : 국민건강보험공단, 근로복지공단, 피해지역 관할 지자체<br>제공목적 : 피해조사, 구제에 관한 사항 심의, 의결<br>※ 세부사항은 개인정보 처리방침 본문 확인</td>
						<td>환경오염피해구게관리시스템 유지관리<br>※ 세부사항은 개인정보 처리방침 본문 확인</td>
						<td>환경오염피해구제실<br>02-2284-1848<br>pass2212@keiti.re.kr</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="h50"></div>
		<div class="head">
			<p class="c_green">
				<span class="c_black">목 차</span>※ 목차의 조항 클릭 시 해당 조항으로 이동합니다.
			</p>
		</div>
		<div class="tableWrap type5">
			<table>
				<colgroup>
					<col width="5%"> 
					<col width="45%">
					<col width="5%">
					<col width="45%">
				</colgroup>
				<tbody class="align_L">
					<tr>
						<td onclick="location.href='#prv1';"><div class="s_labelIcon s_lblIcon1"></div></td>
						<td onclick="location.href='#prv1';">제1조 (개인정보의 처리목적)</td>
						<td onclick="location.href='#prv2';"><div class="s_labelIcon s_lblIcon2"></div></td>
						<td onclick="location.href='#prv2';">제2조 (개인정보의 처리 및 보유기간)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv3';"><div class="s_labelIcon s_lblIcon3"></div></td>
						<td onclick="location.href='#prv3';">제3조 (처리하는 개인정보 항목)</td>
						<td onclick="location.href='#prv4';"><div class="s_labelIcon s_lblIcon4"></div></td>
						<td onclick="location.href='#prv4';">제4조 (개인정보의 파기절차 및 파기방법)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv5';"><div class="s_labelIcon s_lblIcon5"></div></td>
						<td onclick="location.href='#prv5';">제5조 (정보주체와 법정대리인의 권리· 의무 및 행사방법)</td>
						<td onclick="location.href='#prv6';"><div class="s_labelIcon s_lblIcon6"></div></td>
						<td onclick="location.href='#prv6';">제6조 (개인정보의 안전성 확보조치에 관한 사항)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv7';"><div class="s_labelIcon s_lblIcon7"></div></td>
						<td onclick="location.href='#prv7';">제7조 (개인정보 보호책임자의 성명 또는 개인정보보호업무 및 관련 고충사항을 처리하는부서의 명칭과 전화번호 등 연락처)</td>
						<td onclick="location.href='#prv8';"><div class="s_labelIcon s_lblIcon8"></div></td>
						<td onclick="location.href='#prv8';">제8조 (개인정보 열람청구를 접수처리하는 부서)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv9';"><div class="s_labelIcon s_lblIcon9"></div></td>
						<td onclick="location.href='#prv9';">제9조 (정보주체의 권익침해에 대한 구제방법)</td>
						<td onclick="location.href='#prv10';"><div class="s_labelIcon s_lblIcon10"></div></td>
						<td onclick="location.href='#prv10';">제10조 (개인정보파일 등록 현황)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv11';"><div class="s_labelIcon s_lblIcon11"></div></td>
						<td onclick="location.href='#prv11';">제11조 (개인정보의 제3자 제공에 관한 사항)</td>
						<td onclick="location.href='#prv12';"><div class="s_labelIcon s_lblIcon12"></div></td>
						<td onclick="location.href='#prv12';">제12조 (개인정보처리의 위탁에 관한 사항)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv13';"><div class="s_labelIcon s_lblIcon13"></div></td>
						<td onclick="location.href='#prv13';">제13조 (인터넷 접속정보파일 등 개인정보를 자동으로 수집하는 장치의 설치운영 및 그 거부에 관한 사항)</td>
						<td onclick="location.href='#prv14';"><div class="s_labelIcon s_lblIcon14"></div></td>
						<td onclick="location.href='#prv14';">14조 (개인정보의 추가적인 이용‧제공)</td>
					</tr>
					<tr>
						<td onclick="location.href='#prv15';"><div class="s_labelIcon s_lblIcon15"></div></td>
						<td onclick="location.href='#prv15';">제15조 (개인정보 관리수준평가 결과)</td>
						<td onclick="location.href='#prv16';"><div class="s_labelIcon s_lblIcon16"></div></td>
						<td onclick="location.href='#prv16';">제16조 (개인정보 처리방침 변경에 관한 사항)</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv1">제1조(개인정보의 처리목적)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>은 환경오염 피해구제 및 급여지급, 환경오염피해 취약계층 소송지원을 위한 신청인 관리 목적으로 필요에 의한 최소한의 개인정보를 처리하고 있습니다.
				</li>
				<li class="padT20">
					② <환경오염피해구제관리시스템>은 다음과 같이 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.<br>
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>개인정보파일 명칭</th>
									<th>운영근거<br>(주민등록번호 수집 법적근거)</th>
									<th>처리목적</th>
									<th>개인정보파일에 기록되는 개인정보의 항목</th>
									<th>보유기간</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>환경오염피해구제 일반 회원정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체동의</td>
									<td>환경오염 피해구제 및 급여지급 신청을 위한 회원가입</td>
									<td>(필수) 성명, 생년월일, 휴대전화번호, 주소<br>(선택) 유선전화, 이메일</td>
									<td>이용목적 완료시까지</td>
								</tr>
								<tr>
									<td>구제급여 신청자 정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체동의</td>
									<td>피해조사, 구제에 관한 사항을 심의, 의결</td>
									<td>(일반정보) 성명, 주소, 생년월일 휴대폰번호, 전화번호<br>(고유식별정보) 주민등록번호 또는 여권번호<br>(민감정보) 질환정보(의료진료기록, 치료관련기록, 약품조제기록), 의료비 내역, 기타 국가 및 지장자치단체가 제공하는 공공부조 및 사회서비스 수혜이력</td>
									<td>이용목적 완료시까지</td>
								</tr>
								<tr>
									<td>환경오염피해 소송지원제도 신청자 정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체동의</td>
									<td>환경오염피해 취약계층 소송지원 신청인 관리</td>
									<td>(일반정보) 성명, 주소, 생년월일 휴대폰번호, 전화번호<br>(고유식별정보) 주민등록번호 또는 여권번호<br>(민감정보) 질환정보(의료진료기록, 치료관련기록, 약품조제기록), 의료비 내역, 기타 국가 및 지장자치단체가 제공하는 공공부조 및 사회서비스 수혜이력</td>
									<td>이용목적 완료시까지</td>
								</tr>
							</tbody>
						</table>
					</div> 
				</li>
				<li class="padT20">
					③ 상세한 개인정보파일에 대한 사항은 개인정보 보호포털<a href="https://www.privacy.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.privacy.go.kr)</a> → 개인서비스 → 정보주체 권리행사 → 개인정보 열람등 요구 → 개인정보파일 목록검색에서 (파일명: 구제급여 신청자 정보, 소송지원제도 신청자 정보, 환경오염피해구제 일반회원정보)에서 확인할 수 있습니다.
				</li>
			</ul>
		</div>

		<div class="h50"></div>
		<h3 class="icon_tit" id="prv2">제2조(개인정보의 처리 및 보유기간)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.
				</li>
				<li class="padT20">
					② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>개인정보파일의 명칭</th>
									<th>운영근거</th>
									<th>보유기간</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>환경오염피해구제 일반 회원정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체 동의</td>
									<td>이용목적 완료시까지</td>
								</tr>
								<tr>
									<td>구제급여 신청자 정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체 동의</td>
									<td>이용목적 완료시까지</td>
								</tr>
								<tr>
									<td>환경오염피해 소송지원제도 신청자 정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체 동의</td>
									<td>이용목적 완료시까지</td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv3">제3조(처리하는 개인정보의 항목)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>은 다음의 개인정보 항목을 처리하고 있습니다.
				</li>
				<li class="padT20">
					② 인터넷 서비스 이용과정에서 아래 개인정보 항목이 자동으로 생성되어 수집될 수 있습니다.<br>IP주소, 쿠키, MAC주소, 서비스 이용기록, 방문기록, 불량 이용기록
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>개인정보파일의 명칭</th>
									<th>필수항목</th>
									<th>선택항목</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>환경오염피해구제 일반 회원정보</td>
									<td>성명, 생년월일, 휴대전화번호, 주소</td>
									<td>유선전화, 이메일</td>
								</tr>
								<tr>
									<td>구제급여 신청자 정보</td>
									<td>성명, 주소, 생년월일 휴대폰번호, 전화번호, 주민등록번호 또는 여권번호, 질환정보(의료진료기록, 치료관련기록, 약품조제기록), 의료비 내역, 기타 국가 및 지장자치단체가 제공하는 공공부조 및 사회서비스 수혜이력</td>
									<td></td>
								</tr>
								<tr>
									<td>환경오염피해 소송지원제도 신청자 정보</td>
									<td>성명, 주소, 생년월일 휴대폰번호, 전화번호, 주민등록번호 또는 여권번호, 질환정보(의료진료기록, 치료관련기록, 약품조제기록), 의료비 내역, 기타 국가 및 지장자치단체가 제공하는 공공부조 및 사회서비스 수혜이력</td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv4">제4조 (개인정보의 파기절차 및 파기방법)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.
				</li>
				<li class="padT20">
					② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보(또는 개인정보파일)를 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.
				</li>
				<li class="padT20">
					③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.
					<br>
					<p class="c_gray">1. 파기절차<br><환경오염피해구제관리시스템>는 파기하여야 하는 개인정보(또는 개인정보파일)에 대해 개인정보 파기계획을 수립하여 파기합니다. 파기 사유가 발생한 개인정보(또는 개인정보파일)를 선정하고, 개인정보보호책임자의 승인을 받아 개인정보(또는 개인정보파일)를 파기합니다.</p>
					<p class="c_gray">2. 파기방법<br><환경오염피해구제관리시스템>는 전자적 파일 형태로 기록·저장된 개인정보는 기록을 재생할 수 없도록 파기하며, 종이 문서에 기록·저장된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다.</p>
				</li>
			</ul>
		</div>

		<div class="h50"></div>
		<h3 class="icon_tit" id="prv5">제５조(정보주체와 법정대리인의 권리· 의무 및 행사방법)</h3>
		<div class="padL40">
			<ul>
				<li>
					① 정보주체는 <환경오염피해구제관리시스템>에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.
				</li>
				<li class="padT20">
② 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 “개인정보 처리 방법에 관한 고시” 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.
<p class="c_gray">※ 만 14세 미만 아동에 관한 개인정보의 열람 등 요구는 법정대리인이 직접 해야 하며, 만 14세 이상의 미성년자인 정보주체는 정보주체의 개인정보에 관하여 미성년자 본인이 권리를 행사하거나 법정대리인을 통하여 권리를 행사할 수도 있습니다.
				</li>
				<li class="padT20">
③ 제1항에 따른 권리 행사는 <환경오염피해구제관리시스템>에 대해 개인정보 보호법 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며, <환경오염피해구제관리시스템>는 이에 대해 지체없이 조치하겠습니다.
				</li>
				<li class="padT20">
④ 또한 1항에 따른 권리 행사는 개인정보보호 포털<a href="https://www.privacy.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.privacy.go.kr)</a> → 민원마당 → 개인정보 열람 등 요구 신청을 통해서도 하실 수 있으며 <환경오염피해구제관리시스템>는 이에 대해 지체없이 조치하겠습니다.
				</li>
				<li class="padT20">
⑤ 개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.
				</li>
				<li class="padT20">
⑥ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.
				</li>
				<li class="padT20">
⑦ <환경오염피해구제관리시스템>는 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.
				</li>
				<li>
					<p class="c_gray">※ 서식 다운로드</p>
					<p class="c_gray">
						<a id="PERSINFO03_1" href="#">
							<font style="font-weight: bold;">[개인정보처리방법에 관한 고시 별지 제8호] 개인정보열람 요구서</font>
						</a>
					</p>
					<p class="c_gray">
						<a id="PERSINFO03_2" href="#">
							<font style="font-weight: bold;">[개인정보처리방법에 관한 고시 별지 제11호] 위임장</font>
						</a>
					</p>
				</li>
			</ul>
		</div>

		<div class="h50"></div>
		<h3 class="icon_tit" id="prv6">제6조(개인정보의 안전성 확보조치)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.
					<br>
					<p class="c_gray">1. 관리적 조치 : 내부관리계획 수립·시행, 정기적 직원 교육</p>
					<p class="c_gray">2. 기술적 조치 : 개인정보처리시스템 등의 접근권한 관리, 접근통제시스템 설치, 접속기록 관리, 고유식별정보 등의 암호화, 보안프로그램 설치</p>
					<p class="c_gray">3. 물리적 조치 : 전산실 등의 접근통제</p>
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv7">제7조 (개인정보 보호책임자의 성명 또는 개인정보보호업무 및 관련 고충사항을 처리하는 부서의 명칭과 전화번호 등 연락처)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>구분</th>
									<th>부서명</th>
									<th>성명</th>
									<th>연락처</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>개인정보 보호책임자</td>
									<td>환경지식정보실</td>
									<td>김무겸 실장</td>
									<td>전화 : 02-2284-1160<br>이메일 : mgkim@keiti.re.kr</td>
								</tr>
								<tr>
									<td>개인정보 보호 분야별 책임자</td>
									<td>환경오염피해구제실</td>
									<td>황의득 실장</td>
									<td>전화 : 02-2284-1840<br>이메일 : edhwang@keiti.re.kr</td>
								</tr>
								<tr>
									<td rowspan="4">개인정보 보호담당자</td>
									<td rowspan="2">환경오염피해구제실</td>
									<td>이주현</td>
									<td>전화 : 02-2284-1848<br>이메일 : pass2212@keiti.re.kr</td>
								</tr>
								<tr>
									<td>조인근</td>
									<td>전화 : 02-2284-1853<br>이메일 : ikcho@keiti.re.kr</td>
								</tr>
								<tr>
									<td rowspan="2">환경지식정보실</td>
									<td>임수정</td>
									<td>전화 : 02-2284-1188<br>이메일 : rgnko@keiti.re.kr</td>
								</tr>
								<tr>
									<td>김경란</td>
									<td>전화 : 02-2284-1183<br>이메일 : krkim01@keiti.re.kr</td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
				<li class="padT20">
				② 정보주체께서는 <환경오염피해구제관리시스템>의 서비스(또는 사업)를 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. <환경오염피해구제관리시스템>는 정보주체의 문의에 대해 지체없이 답변 및 처리해드릴 것입니다.
				</li>
			</ul>
		</div>

		<div class="h50"></div>
		<h3 class="icon_tit" id="prv8">제8조(개인정보 열람청구를 접수·처리하는 부서)</h3>
		<div class="padL40">
			<ul>
				<li>① 정보주체는 개인정보 보호법 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다. <br><환경오염피해구제관리시스템>는 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다.<br>‣ 개인정보 열람청구 접수요구처리 부서</li>
				<li>
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>부서명</th>
									<th>담당자</th>
									<th>연락처</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>환경오염피해구제실</td>
									<td>이주현</td>
									<td>전화 : 02-2284-1848<br>이메일 : pass2212@keiti.re.kr</td>
								</tr>
								<tr>
									<td>환경오염피해구제실</td>
									<td>조인근</td>
									<td>전화 : 02-2284-1853<br>이메일 : ikcho@keiti.re.kr</td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
				<li class="padT20">
					② 정보주체께서는 제1항의 열람청구 접수·처리부서 이외에, 개인정보보호포털<a href="https://www.privacy.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.privacy.go.kr)</a>를 통하여서도 개인정보 열람청구를 하실 수 있습니다.<br>개인정보보호 포털<a href="https://www.privacy.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.privacy.go.kr)</a> → 개인서비스 → 정보주체 권리행사 → 개인정보 열람등 요구(본인인증 필요)  
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv9">제9조(정보주체의 권익침해에 대한 구제방법)</h3>
		<div class="padL40">
			<ul>
				<li>
				① &lt;환경오염피해구제관리시스템&gt;는 정보주체의 개인정보자기결정권을 보장하고, 개인정보 침해로 인한 상담 및 피해 구제를 위해 노력하고 있으며, 신고나 상담이 필요한 경우 아래의 담당부서로 연락해 주시기 바랍니다.<br>
				 ‣ 개인정보보호 관련 고객 상담 및 신고<br>
				   부서명 : 환경오염피해구제실<br>
				   담당자 : 이주현<br>
				   연락처 : &lt;전화: 02-2284-1848&gt;, &lt;이메일: pass2212@keiti.re.kr&gt;<br>
				</li>
				<li class="padT20">
				② 아래의 기관은 한국환경산업기술원과는 별개의 기관으로서, &lt;환경오염피해구제관리시스템&gt;의 자체적인 개인정보 불만처리, 피해구제 결과에 만족하지 못하시거나 보다 자세한 도움이 필요하시면 문의하여 주시기 바랍니다.<br>
					<dl class="padT20 c_gray">
						<dt class="c_eme">1. 개인정보 침해신고센터 (한국인터넷진흥원 운영)</dt>
						<dd>소관업무 : 개인정보 침해사실 신고, 상담 신청</dd>
						<dd>홈페이지 : <a href="privacy.kisa.or.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(privacy.kisa.or.kr)</a></dd>
						<dd>전화 : (국번없이) 118</dd>
						<dd>주소 : (58324) 전남 나주시 진흥길 9(빛가람동 301-2) 3층 개인정보침해신고센터</dd>
						<dt class="c_eme">2. 개인정보 분쟁조정위원</dt>
						<dd>소관업무 : 개인정보 분쟁조정신청, 집단분쟁조정 (민사적 해결)</dd>
						<dd>홈페이지 : <a href="https://www.kopico.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.kopico.go.kr)</a></dd>
						<dd>전화 : (국번없이) 1833-6972</dd>
						<dd>주소 : (03171) 서울특별시 종로구 세종대로 209 정부서울청사 12층</dd>
						<dt class="c_eme">3. 대검찰청 : (국번없이) 1301 <a href="https://www.spo.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.spo.go.kr)</a></dt>
						<dt class="c_eme">4. 경찰청 : (국번없이) 182 <a href="https://ecrm.police.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(ecrm.cyber.go.kr)</a></dt>
					</dl>
				</li>
				<li class="padT20">
					③ 「개인정보 보호법」 제35조(개인정보의 열람), 제36조(개인정보의 정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 「행정심판법」이 정하는 바에 따라 행정심판을 청구할 수 있습니다.<br>
‣ 중앙행정심판위원회 : (국번없이) 110 <a href="https://www.simpan.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.simpan.go.kr)</a><br>
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv10">제10조(개인정보파일 등록 현황)</h3>
		<div class="padL40">
			<ul>
				<li>
					<환경오염피해구제관리시스템>이(가) 「개인정보 보호법」 제32조에 따라 등록·공개하는 개인정보파일의 처리목적·보유기간 및 항목은 아래와 같습니다.
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>개인정보파일의 명칭</th>
									<th>운영근거</th>
									<th>처리목적</th>
									<th>개인정보파일에 기록되는 개인정보의 항목</th>
									<th>보유기간</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>환경오염피해구제 일반 회원정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체동의</td>
									<td>환경오염 피해구제 및 급여지급 신청을 위한 회원가입</td>
									<td>(필수) 성명, 생년월일, 휴대전화번호, 주소<br>(선택) 유선전화, 이메일</td>
									<td>이용목적 완료시까지</td>
								</tr>
								<tr>
									<td>구제급여 신청자 정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체동의</td>
									<td>피해조사, 구제에 관한 사항을 심의, 의결</td>
									<td>(일반정보) 성명, 주소, 생년월일 휴대폰번호, 전화번호<br>(고유식별정보) 주민등록번호 또는 여권번호<br>(민감정보) 질환정보(의료진료기록, 치료관련기록, 약품조제기록), 의료비 내역, 기타 국가 및 지장자치단체가 제공하는 공공부조 및 사회서비스 수혜이력</td>
									<td>이용목적 완료시까지</td>
								</tr>
								<tr>
									<td>환경오염피해 소송지원제도 신청자 정보</td>
									<td>1. 환경오염피해 배상책임 및 구제에 과한 법률 시행령 제34조<br>2. 환경오염피해 배상책임 및 구제에 과한 법률 시행규칙 제2항<br>3. 정보주체동의</td>
									<td>환경오염피해 취약계층 소송지원 신청인 관리</td>
									<td>(일반정보) 성명, 주소, 생년월일 휴대폰번호, 전화번호<br>(고유식별정보) 주민등록번호 또는 여권번호<br>(민감정보) 질환정보(의료진료기록, 치료관련기록, 약품조제기록), 의료비 내역, 기타 국가 및 지장자치단체가 제공하는 공공부조 및 사회서비스 수혜이력</td>
									<td>이용목적 완료시까지</td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
			</ul>
		</div>
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv11">제11조(개인정보의 제3자 제공에 관한 사항)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 정보주체의 개인정보를 수집·이용 목적으로 명시한 범위를 초과하여 제3자에게 제공하지 않습니다. 다만 다음의 경우와 같이 정보주체의 동의, 법률의 특별한 규정 등 「개인정보 보호법」 제17조 및 제18조에 해당하는 경우에 개인정보를 제3자에게 제공합니다.
				</li>
				<li class="padT20">
					② <환경오염피해구제관리시스템>는 원활한 서비스 제공을 위해 다음의 경우 정보주체의 동의를 얻어 필요 최소한의 범위로만 제공합니다.
				</li>
				<li>
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>제공받는 자</th>
									<th>제공목적</th>
									<th>제공항목</th>
									<th>보유 및 이용기간</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>국민건강보험공단, 근로복지공단, 피해관할 지역자치단체</td>
									<td>피해조사, 구제에 관한 사항을 심의, 의결</td>
									<td>이름, 주소, 주민등록번호</td>
									<td>이용목적 완료시까지</td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv12">제12조(개인정보 처리업무의 위탁에 관한 사항)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.
				</li>
				<li>
					<div class="tableWrap type4">
						<table>
							<thead>
								<tr>
									<th>위탁시스템</th>
									<th>위탁받는 자(수탁자)</th>
									<th>위탁업무</th>
									<th>위탁기간</th>
								</tr>
							</thead>
							<tbody class="align_L">
								<tr>
									<td>환경오염피해구제관리시스템</td>
									<td>수탁업체명 : ㈜ 선도소프트<br>주소 : 서울시 구로구<br>전화번호 : 02-856-1900</td>
									<td>환경오염피해구제관리시스템 개선 및 유지관리</td>
									<td>2023-06-01<br>~2023-12-31</td>
								</tr>
							</tbody>
						</table>
					</div>
				</li>
				<li class="padT20">
				② <환경오염피해구제관리시스템>는 위탁계약 체결 시 「개인정보 보호법」 제26조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 안전성 확보조치, 재위탁 제한, 수탁자에 대한 관리·감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.
				</li>
				<li class="padT20">
				③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv13">제13조(인터넷 접속정보파일 등 개인정보를 자동으로 수집하는 장치의 설치운영 및 그 거부에 관한 사항)</h3>
		<div class="padL40">
			<ul>
				<li>
					① <환경오염피해구제관리시스템>는 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.
				</li>
				<li class="padT20">
					② 쿠키는 웹사이트를 운영하는데 이용되는 서버(https)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.<br>
1. 쿠키의 사용목적: 이용자가 방문한 각 서비스와 웹사이트들에 대한 방문 및 이용형태, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.<br>
2. 쿠키의 설치·운영 및 거부: 브라우저 옵션 설정을 통해 쿠키 허용, 쿠키 차단 등의 설정을 할 수 있습니다.<br>
- Edge: 웹브라우저 우측 상단의 설정 메뉴 > 쿠키 및 사이트 권한 > 쿠키 및 사이트 데이터 관리 및 삭제<br>
- Chrome: 웹브라우저 우측 상단의 설정 메뉴 > 개인정보 및 보안 > 쿠키 및 기타 사이트 데이터<br>
- Whale : 웹브라우저 우측 상단의 설정 메뉴 > 개인정보 보호 > 쿠키 및 기타 사이트 데이터<br>
3. 쿠키 저장을 거부할 경우 일부 서비스 이용에 어려움이 발생할 수 있습니다.
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv14">14조 (개인정보의 추가적인 이용‧제공)</h3>
		<div class="padL40">
			<ul>
				<li>
					① 「개인정보 보호법」 제15조제3항, 제17조제4항 및 개인정보 보호법 시행령 제14조의2에 따른 사항을 고려하여 정보주체의 동의 없이 개인정보를 추가적인 이용 또는 제공할 수 있습니다.
				</li>
				<li class="padT20">
					② <환경오염피해구제관리시스템>는 개인정보를 추가적으로 이용·제공하는 사항이 없으며, 다음과 같은 판단기준을 두고 있습니다.
					<p class="c_gray">1. 개인정보를 추가적으로 이용·제공하려는 목적이 당초 수집 목적과 관련성이 있는지 여부</p>
					<p class="c_gray">2. 개인정보를 수집한 정황 또는 처리 관행에 비추어 볼 때 추가적인 이용·제공에 대한 예측 가능성이 있는지 여부</p>
					<p class="c_gray">3. 개인정보의 추가적인 이용·제공이 정보주체의 이익을 부당하게 침해하는지 여부</p>
					<p class="c_gray">4. 가명처리 또는 암호화 등 안전성 확보에 필요한 조치를 하였는지 여부</p>
				</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv15">제15조(개인정보 관리수준진단 결과)</h3>
		<div class="padL40">
			<ul>
				<li>① 한국환경산업기술원은 정보주체의 개인정보를 안전하게 관리하기 위해 「개인정보 보호법」 제11조에 따라 매년 개인정보보호위원회에서 실시하는 “공공기관 개인정보 관리수준진단”을 받고 있습니다.</li>
				<li class="padT20">② 한국환경산업기술원은 2022년도 개인정보 관리수준진단 평가에서 ‘A’등급을 획득하였습니다.<br>※ S : 90점 이상, A : 90점~80점, B : 80점~70점, C : 80점~70점, D : 80점~70점</li>
			</ul>
		</div>
		
		<div class="h50"></div>
		<h3 class="icon_tit" id="prv16">제16조(개인정보 처리방침 변경)</h3>
		<div class="padL40">
			<ul>
				<li>① 이 개인정보 처리방침은 2023. 9. 8.부터 적용됩니다.
				</li>
				<li class="padT20">② 이전의 개인정보 처리방침은 아래에서 확인하실 수 있습니다.
				</li>
				<p class="c_gray">
					<a href="#" id="popupPerinfo-03" data-pageinfo="03">
						<font style="font-weight: bold;">2022.10.21.~2023.9.7. 적용</font>
					</a>
                </p>
                <p>
					<a href="#" id="popupPerinfo-02" data-pageinfo="02">
						<font style="font-weight: bold;">2022.02.11.~2022.10.20. 적용</font>
					</a>
				</p>
                <p class="c_gray">
					<a href="#" id="popupPerinfo-01" data-pageinfo="01">
						<font style="font-weight: bold;">최초시행~2022.02.11. 적용</font>
					</a>
                </p>
			</ul>
		</div>
	</div>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
