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

        <!-- <img src="../images/page/info.jpg" alt="" />
        위 스타일 반영하여 한국환경산업기술원 footer 개인정보처리방침 내용 작성 -->

        <div class="head">
            <img src="<c:url value='/images/other/info_icon.jpg'/>" alt="" />
            <!--            <img src="../images/other/info_icon.jpg" alt=""> -->
            <p class="c_green">
                <span class="c_black">한국환경산업기술원(이하 "기술원"이라 함)이 취급하는 모든 개인정보는</span>
                관련법령에 근거하거나 정보주체의 동의에 의하여 수집·보유 및 처리되고 있습니다.
            </p>
        </div>

        <ul class="boxType1 ball1">
            <li>본 방침은 2022년 2월 11일부터 시행됩니다.</li>
            <li>한국환경산업기술원은 개인정보처리방침을 개정하는 경우 웹사이트 공지사항을 통하여 공지하고 있습니다.</li>
            <li>기술원은 법령의 규정에 따라 수집·보유 및 처리하는 개인정보를 공공업무의 적절한 수행과 정보주체의 권익을 보호하기 위하여 적법하고 적정하게 취급할 것입니다. 또한, 기술원은 관련 법령에서 규정한 바에 따라 기술원에서 보유하고 있는 개인정보에 대한 열람청구권 및 정정청구권 등 이용자의 권익을 존중하며, 여러분은 이러한 법령상 권익의 침해 등에 대하여 행정심판법에서 정하는바에 따라 행정심판을 청구할 수 있으며, 개인정보분쟁조정위원회, 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 기술원의 개인정보 처리방침은 현행 「개인정보보호법」에 근거를 두고 있습니다. 개인정보를 처리하는 우리원 소관 홈페이지의 경우 해당 홈페이지에 별도의 개인정보처리방침을 정하여 운영하고 있습니다.</li>
        </ul>

        <div class="h50"></div>
        <h3 class="icon_tit">개인정보 처리목적</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원은 업무 수행 및 민원 처리 등 다음의 목적을 위하여 최소한으로 개인정보를 수집하고 있습니다.
                    <dl class="padT20 c_gray">
                        <dt class="c_eme">• 환경오염피해구제 서비스 제공에 따른 피해자 식별, 본인인증, 각종 고지·통지 및 서비스 제공 등을 목적으로 개인정보를 처리합니다.</dt>
                        <dt class="c_eme">• 기타 : 업무 연락 및 업무 협의, 게시글 입력 등을 목적으로 개인정보를 처리합니다.</dt>
                    </dl>
                </li>
            </ul>
        </div>

        <div class="h50"></div>
        <h3 class="icon_tit">개인정보의 처리 및 보유기간</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원의 소관 업무 수행 및 민원처리 등의 목적으로 수집되는 개인정보는 처리목적이 달성되거나 해당 서비스가 폐지되면 지체 없이 파기합니다. 다만, 다른 법령에 따라 보존하여야 하는 경우 그러하지 않습니다.
                    <br>
                    <p class="c_gray">• 개인정보 처리 및 보유기간 : 10년</p>
                    <p class="c_gray">• 자동수집항목 중 IP주소 : 1년</p>
                </li>
            </ul>
        </div>

        <div class="h50"></div>
        <h3 class="icon_tit">개인정보의 제3자 제공에 관한 사항</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원이 수집·보유하고 있는 개인정보는 이용자의 동의 없이는 제3자에게 제공하지 않으며, 다음의 경우에는 개인정보를 제3자에게 제공 할 수 있습니다.
                    <br>
                    <p class="c_gray">1. 정보주체로부터 별도의 동의를 받은 경우</p>
                    <p class="c_gray">2. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</p>
                    <p class="c_gray">3. 정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보 주체 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</p>
                    <p class="c_gray">4. 통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로써 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우</p>
                    <p class="c_gray">5. 개인정보를 목적 외의 용도로 이용하거나 이를 제3자에게 제공하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우로서 보호위원회의 심의·의결을 거친 경우</p>
                    <p class="c_gray">6. 조약, 그 밖의 국제협정의 이행을 위하여 외국정부 또는 국제기구에 제공하기 위하여 필요한 경우</p>
                    <p class="c_gray">7. 범죄의 수사와 공소의 제기 및 유지를 위하여 필요한 경우</p>
                    <p class="c_gray">8. 법원의 재판업무 수행을 위하여 필요한 경우</p>
                    <p class="c_gray">9. 형(刑) 및 감호, 보호처분의 집행을 위하여 필요한 경우</p>
                    <br>
                    기술원이 개인정보를 제3자에게 제공하는 경우 다음의 항목을 정보 주체에게 알린 후 동의를 받겠습니다.
                    <br>
                    <p class="c_gray">• 제공받는 자의 성명(법인 또는 단체인 경우에는 그 명칭)과 연락처 : 국민건강보험공단(1577-1000), 근로복지공단(1588-0075), 서천군청(041-950-4114) 등 피해지역 관할 지자체</p>
                    <p class="c_gray">• 제공받는 자의 개인정보 이용 목적 : 환경오염피해 조사, 구제급여 해당 여부 및 해당 금액, 타 법에 따른 지원금 수급여부 조회</p>
                    <p class="c_gray">• 제공하는 개인정보의 항목 : 성명, 주소, 성별, 휴대전화번호, 주민등록번호, 질환정보</p>
                    <p class="c_gray">• 개인정보를 제공받는 자의 개인정보 보유 및 이용 기간 : 10년</p>
                    <p class="c_gray">• 동의를 거부할 권리가 있다는 사실 및 동의 거부에 따른 불이익이 있는 경우에는 그 불이익의 내용 : 개인정보보호법에 의거 개인정보의 제공에 따른 동의를 거부할 수 있으나, 미 동의시 당 사이트 서비스의 이용에 제한됩니다.</p>
                </li>
            </ul>
        </div>

        <div class="h50"></div>
        <h3 class="icon_tit">개인정보 처리의 위탁에 관한 사항</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원은 원칙적으로 이용자의 동의없이 해당 개인정보의 처리를 타인에게 위탁하지 않습니다. 다만, 기술원이 제3자에게 개인정보의 처리업무를 위탁하는 경우에는 위탁 업무의 내용과 수탁자를 해당 서비스의 홈페이지에 게시합니다.
                    <dl class="padT20 c_gray">
                        <dt class="c_eme">위탁업체 : (주)선도소프트</dt>
                        <dt class="c_eme">위탁기간 : 2022/02/11~2023/02/12</dt>
                        <dt class="c_eme">위탁항목 : 환경오염피해구제 시스템 구축 및 운영</dt>
                        <dt class="c_eme">위탁사업 명 : 환경오염피해구제 시스템</dt>
                    </dl>
                </li>
            </ul>
        </div>

        <div class="h50"></div>
        <h3 class="icon_tit">정보주체와 법정대리인의 권리·의무 및 행사방법</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    이용자는 정보주체(혹은 해당 주체의 법정대리인)로서 다음과 같은 권리를 행사할 수 있습니다.
                    <br>
                    <p class="c_gray">1. 개인정보 열람 요구</p>
                    <p class="c_gray">2. 개인정보 정정·삭제 요구</p>
                    <p class="c_gray">3. 개인정보 처리정지 요구</p>
                    <br>
                    위 사항에 따른 권리 행사는 개인정보보호법 시행규칙 별지 제8호 서식에 따라 작성 후 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며, 기관은 이에 대해 지체 없이 조치하겠습니다. 정보주체가 개인정보의 오류 등에 대한 정정 또는 삭제를 요구한 경우에는 정정 또는 삭제를 완료할 때까지 당해 개인정보를 이용하거나 제공하지 않습니다. 개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다. 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다. 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.
                    <br>
                    <p class="c_gray">
                        <a id="PERSINFO01" href="#">
                            <font style="font-weight: bold;">[개인정보보호법 시행규칙 별지 제8호] 개인정보 열람정정삭제등 요구서 (다운로드)</font>
                        </a>
                    </p>
                    위 사항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.
                    <br>
                    <p class="c_gray">
                        <a id="PERSINFO02" href="#">
                            <font style="font-weight: bold;">[개인정보보호법 시행규칙 별지 제11호] 위임장 (다운로드)</font>
                        </a>
                    </p>
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">개인정보보호책임자 및 담당자, 개인정보 고충처리 부서의 연락처</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    개인정보보호 및 처리와 관련한 문의는 환경지식정보실(02-2284-1175) 및 각 부서의 홈페이지 서비스 담당자에게 문의하시어 상담 받으실 수 있습니다.
                    <div class="tableWrap type4">
                        <table>
                            <thead>
                                <tr>
                                    <th>구분</th>
                                    <th>부서명</th>
                                    <th>성명 또는 직위</th>
                                    <th>연락처</th>
                                </tr>
                            </thead>
                            <tbody class="align_L">
                                <tr>
                                    <td>개인정보 보호책임자</td>
                                    <td>환경지식정보실</td>
                                    <td>김무겸 실장</td>
                                    <td>02-2284-1160</td>
                                </tr>
                                <tr>
                                    <td>개인정보 담당자</td>
                                    <td>환경지식정보실</td>
                                    <td>임수정 연구원</td>
                                    <td>02-2284-1188</td>
                                </tr>
                                <tr>
                                    <td>본 홈페이지 담당자</td>
                                    <td>환경오염피해구제실</td>
                                    <td>김정아 연구원</td>
                                    <td>02-2284-1848</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </li>
            </ul>
        </div>

        <div class="h50"></div>
        <h3 class="icon_tit">개인정보 자동 수집 장치의 설치․운영 및 그 거부에 관한 사항</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>환경오염피해구제 시스템은 정보주체의 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용하지 않습니다.</li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">처리하는 개인정보 항목</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원은 원활한 민원처리를 위해 다음의 개인정보 항목을 처리하고 있습니다.
                    <dl class="padT20 c_gray">
                        <dt class="c_eme">• 가. 필수항목: 성명, 생년월일, 휴대전화번호, 주소</dt>
                        <dt class="c_eme">• 나. 선택항목: 유선전화번호, 이메일주소</dt>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">개인정보의 파기</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원은 원칙적으로 개인정보의 보유기간이 경과했거나 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 다만, 다른 법률에 따라 보존하여야 하는 경우에는 그러하지 않습니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.
                    <dl class="padT20 c_gray">
                        <dt class="c_eme">1. 파기절차 및 기한</dt>
                        <dd>• 이용자가 입력한 정보는 보유기간이 경과했거나 처리목적이 달성된 후 내부 방침 및 관련 법령에 따라 파기합니다.</dd>
                        <dt class="c_eme">2. 파기방법</dt>
                        <dd>기술원에서 처리하는 개인정보를 파기할 때에는 다음의 방법으로 파기 합니다.</dd>
                        <dd>• 전자적 파일 형태인 경우 : 복원이 불가능한 방법으로 영구삭제</dd>
                        <dd>• 전자적 파일의 형태 외의 기록물, 인쇄물, 서면, 그 밖의 기록매체인 경우 : 파쇄 또는 소각</dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">개인정보의 안전성 확보 조치</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    기술원은 다음과 같이 안전성 확보에 필요한 기술적, 관리적, 물리적 조치를 하고 있습니다.
                    <dl class="padT20 c_gray">
                        <dt class="c_eme">1. 내부관리계획의 수립 및 시행</dt>
                        <dd>• 기술원은 ‘개인정보의 안전성 확보조치 기준’에 의거하여 내부관리계획을 수립 및 시행합니다.</dd>
                        <dt class="c_eme">2. 개인정보취급자 지정의 최소화 및 교육</dt>
                        <dd>• 개인정보취급자의 지정을 최소화하고 정기적인 교육을 시행하고 있습니다.</dd>
                        <dt class="c_eme">3. 개인정보에 대한 접근 제한</dt>
                        <dd>• 개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근을 통제하고, 침입차단시스템과 침입방지시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</dd>
                        <dt class="c_eme">4. 접속기록의 보관 및 위변조 방지</dt>
                        <dd>• 개인정보처리시스템에 접속한 기록(웹 로그, 요약정보 등)을 최소 6개월 이상 보관, 관리하고 있습니다.</dd>
                        <dt class="c_eme">5. 개인정보의 암호화</dt>
                        <dd>• 이용자의 개인정보는 암호화 되어 저장 및 관리되고 있습니다. 또한 중요한 데이터는 저장 및 전송 시 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</dd>
                        <dt class="c_eme">6. 해킹 등에 대비한 기술적 대책</dt>
                        <dd>• 기술원은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신·점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적, 물리적으로 감시 및 차단하고 있습니다.</dd>
                        <dt class="c_eme">7. 비인가자에 대한 출입 통제</dt>
                        <dd>• 개인정보를 보관하고 있는 개인정보시스템의 물리적 보관 장소를 별도로 두고 이에 대해 출입통제 절차를 수립, 운영하고 있습니다.</dd>
                    </dl>
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">정보주체의 권익침해 구제방법</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회, 한국인터넷기술원 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기 바랍니다.
                    <p class="c_gray">
                        1. 개인정보분쟁조정위원회 : 02-2100-2499
                        <a href="https://www.kopico.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(www.kopico.go.kr)</a>
                    </p>
                    <p class="c_gray">
                        2. 대검찰청 사이버범죄수사단 : 02-3480-3571
                        <a href="http://www.spo.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(http://www.spo.go.kr/)</a>
                    </p>
                    <p class="c_gray">
                        3. 경찰청 사이버안전국 : (국번없이) 182
                        <a href="http://cyberbureau.police.go.kr/" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(http://cyberbureau.police.go.kr/)</a>
                    </p>
                    <br>
                    「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의 정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대 하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다. ※ 행정심판에 대해 자세한 사항은 법제처
                    <a href="http://www.moleg.go.kr" onclick="window.open(this.href,'_blank', 'width=700,height=600,toolbars=no,scrollbars=no'); return false;">(http://www.moleg.go.kr)</a>
                    </p>
                    홈페이지를 참고하시기 바랍니다.
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">개인정보의 열람청구를 접수 및 처리하는 부서</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    개인정보보호 및 처리와 관련한 문의는 환경지식정보실(02-2284-1175) 및 각 부서의 홈페이지 서비스 담당자에게 문의하시어 상담 받으실 수 있습니다.
                    <div class="tableWrap type4">
                        <table>
                            <thead>
                                <tr>
                                    <th>구분</th>
                                    <th>부서명</th>
                                    <th>성명 또는 직위</th>
                                    <th>연락처</th>
                                </tr>
                            </thead>
                            <tbody class="align_L">
                                <tr>
                                    <td>개인정보 보호책임자</td>
                                    <td>환경지식정보실</td>
                                    <td>김무겸 실장</td>
                                    <td>02-2284-1160</td>
                                </tr>
                                <tr>
                                    <td>개인정보 담당자</td>
                                    <td>환경지식정보실</td>
                                    <td>임수정 연구원</td>
                                    <td>02-2284-1188</td>
                                </tr>
                                <tr>
                                    <td>본 홈페이지 담당자</td>
                                    <td>환경오염피해구제실</td>
                                    <td>김정아 연구원</td>
                                    <td>02-2284-1848</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <p class="c_gray">
                        <a id="PERSINFO01_1" href="#">
                            <font style="font-weight: bold;">[개인정보보호법 시행규칙 별지 제8호] 개인정보 열람정정삭제등 요구서 (다운로드)</font>
                        </a>
                    </p>
                    <p class="c_gray">
                        <a id="PERSINFO02_2" href="#">
                            <font style="font-weight: bold;">[개인정보보호법 시행규칙 별지 제11호] 위임장 (다운로드)</font>
                        </a>
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">정보주체의 동의 없이 추가적인 이용 또는 제공 기준</h3>
        <div class="padL40">
            <ul class="ball1">
                <li>
                    우리원은 개인정보보호법 제15조제3항 또는 제17조제4항에 따라 개인정보를 제공하는 경우, 정보주체의 동의 없이 개인정보를 추가적인 이용 또는 제공할 때의 판단기준은 다음과 같습니다.
                    <p class="c_gray">1. 당초 수집 목적과 관련성이 있는지 여부</p>
                    <p class="c_gray">2. 개인정보를 수집한 정황 또는 처리 관행에 비추어 볼 때 개인정보의 추가적인 이용 또는 제공에 대한 예측 가능성이 있는지 여부</p>
                    <p class="c_gray">3. 정보주체의 이익을 부당하게 침해하는지 여부</p>
                    <p class="c_gray">4. 가명처리 또는 암호화 등 안전성 확보에 필요한 조치를 하였는지 여부</p>
                </li>
            </ul>
        </div>
        <div class="h50"></div>
        <h3 class="icon_tit">개인정보 처리방침의 변경사항</h3>
        <div class="padL40">

        </div>
    </div>
    <script>
    $(function() {

    	// 메뉴경로 숨김
    	if ($('section.sub-visual'))
    		$('section.sub-visual').hide();
    		
       $("#PERSINFO01").on("click", function(){
          $.fileUtil.download({
             params: {fType:'T1'},
             url   : getUrl("/com/cmm/downFormFile.do")
          });
          return false;
          
       });

       $("#PERSINFO02").on("click", function(){
          $.fileUtil.download({
             params: {fType:'T2'},
             url   : getUrl("/com/cmm/downFormFile.do")
          });
          return false;
          
       });

       $("#PERSINFO01_1").on("click", function(){
          $.fileUtil.download({
             params: {fType:'T1'},
             url   : getUrl("/com/cmm/downFormFile.do")
          });
          return false;
          
       });

       $("#PERSINFO02_2").on("click", function(){
          $.fileUtil.download({
             params: {fType:'T2'},
             url   : getUrl("/com/cmm/downFormFile.do")
          });
          return false;
          
       });

       // 개인정보팝업
       $("[id^=popupPerinfo]").click(function() {
    		var url = getUrl('/usr/pinfo/popupPerinfo.do');
    		var pageinfo = $(this).data('pageinfo');
    		url = url + "?pageinfo="+pageinfo;
    		
    		//var idVal  = $(this).attr('id');		
    		//console.log('id = '+idVal );		
    		//console.log('pageinfo = '+pageinfo);
    		
    		window.open(url,'_blank', 'width=800,height=600,toolbars=no,scrollbars=yes');
    	});   
       
     });
    
    </script>
</section>

<%-- ############################# 내용 (종료) ############################# --%>
