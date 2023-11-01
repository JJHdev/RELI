/**
*******************************************************************************
*** 파일명 : viewMyCmit.js
*** 설명글 : 온라인위원회 - 위원회 현황 - 세부정보 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
***    ver      date          author                  description
*** ---------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
*******************************************************************************
**/

// 의견서작성 팝업
let C_POPUP_OPINION  = false;
// 의결서작성 팝업
let C_POPUP_DECISION = false;
// 수당지작성 팝업
let C_POPUP_PENSION  = false;

$(function() {
	
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	const P_FORM  = $('#selectForm');
	const P_CMNO  = $('#cmitMngNo').val();
	const P_TNNO  = $('#tenureNo' ).val();
	let   P_DATA  = false;

    // 초기실행
    //--------------------------------------------------------//
	doLoad();

    // 세부정보 가져오기 (초기실행)
    //--------------------------------------------------------//
	function doLoad() {

		$.ajaxUtil.ajaxLoad(
			getUrl('/usr/cmit/getMyCmit.do'), {
				cmitMngNo : P_CMNO,
				tenureNo  : P_TNNO
			}, 
			function(data) {
				let obj = data.Data;
				// 위원회 개최기간 정의
				obj['opmtYmdNm'] = obj['opmtBgngYmd']+' ~ '+ obj['opmtEndYmd'];
				// 위원회 안건 정의
				obj['agntCntNm'] = '총 '+obj['agndCnt']+'건';

				P_DATA = obj;

				// 상세조회 항목데이터 맵핑
				$.formUtil.toHtml(P_FORM, P_DATA, 's_');
				
				if (obj['charmnYn'] == 'Y') {
					$('#appCmitMember').html('');
					$('#appCmitMember').append('<span>위원장</span>');
					$('#appCmitMember').addClass('cmit-roundbox');
				}

				if (obj.agndList) {
					let dom = $('#appCmitTable');
					$.each(obj.agndList, function(i,agn) {

						let r = $('<div class="doc-form-list app-pb0"></div>');
						r.append('<div class="doc-form-tit"><p>안건-'+agn['agndNo']+'</p></div>');
						r.append('<div class="doc-form-input">'+agn['agndNm']+'</div>');
						
						let tr = $('<tr><td colspan="2"></td><td></td><td></td></tr>');
						tr.find('td:eq(0)').append(r);
						// 의견서 버튼
						tr.find('td:eq(1)').append(getButtonElement('O', agn));
						// 의결서 버튼
						tr.find('td:eq(2)').append(getButtonElement('D', agn));
						dom.find('tbody').append(tr);
					});
				}
				// 수당지작성 버튼 처리
				$('#appButtons').html('');
				$('#appButtons').append(getButton('P'));
			}
		);
	}
	
    // 버튼객체 반환
    // type = 'O' : 의견서
    // type = 'D' : 의결서
    // type = 'P' : 수당지
    //--------------------------------------------------------//
	function getButtonElement(type, agenda) {
		return $('<div class="btnWrap type4"></div>').append(getButton(type, agenda));
	}

    // 버튼객체 반환
    // type = 'O' : 의견서
    // type = 'D' : 의결서
    // type = 'P' : 수당지
    //--------------------------------------------------------//
	function getButton(type, agenda) {
		/**
		위원인 경우
		1) 위원회개최 또는 의견서작성상태일 경우 의견서 작성버튼 활성
		1) 의견서 작성여부에 따라 작성버튼 활성/비활성 처리
		2) 의결서작성상태일 경우 의결서 조회버튼 활성 그외 비활성 처리
		3) 의결서 서명여부에 따라 조회버튼 활성/비활성 처리
		
		위원장인 경우
		1) 의결서작성상태일 경우 의견서 제출완료 활성
		2) 의결서 작성여부에 따라 작성버튼 활성/비활성 처리
		3) 수당지작성상태일 경우 의결서 제출 버튼 활성 처리 
		
		의견서 제출완료 (버튼활성 / 클릭불가)
		의견서 작성 (버튼활성,비활성 / 클릭시 작성팝업)
		
		의결서 조회 (버튼활성,비활성 / 클릭시 서명팝업)
		의결서 제출완료 (버튼활성 / 클릭불가)
		
		수당지 작성 (버튼활성,비활성 / 클릭시 작성팝업)
		수당지 작성완료 (버튼활성 / 클릭불가)
		
		전문위원회는 의견서만 작성
		 */
		// 의원회구분
		let cmitse   =  P_DATA['cmitSeCd'];
		// 진행상태
		let status   =  P_DATA['prgreStusCd'];
		// 위원장여부
		let charmn   = (P_DATA['charmnYn'] == 'Y');
		// 수당지작성여부
		let pension  = (P_DATA['pensionYn'] == 'Y');
		// 의견서작성여부
		let opinion  = (agenda ? agenda['opinionYn' ] == 'Y' : false);
		// 의결서작성여부
		let decision = (agenda ? agenda['decisionYn'] == 'Y' : false);
		
		let btn   = $('<a href="javascript:void(0);"></a>');
		let mode  = MODE.VIEW;
		let title = '';
		let color = '';

		// 의견서인 경우
		if (type == 'O') {
			title = '의견서 제출완료';
			color = (opinion ? 'green' : 'gray');
			// 개최 또는 의견작성 상태이면서 작성전인 경우
			if ($.inArray(status, ['01','02']) >= 0 && !opinion) {
				color = 'blue';
				title = '의견서 작성';
				mode  = MODE.INSERT;
				// 의견서 작성 팝업
				btn.on('click', doOpenOpinion);
			}
			// 의견서작성완료인 경우
			else if (opinion) {
				mode  = MODE.VIEW;
				// 의견서 조회 팝업
				btn.on('click', doOpenOpinion);
			}
		}
		// 의결서인 경우
		else if (type == 'D') {
			// 전문위원회인 경우
			if (cmitse == 'C1') {
				return '&nbsp;';
			}
			title = '의결서 조회';
			color = (decision ? 'green' : 'gray');
			// 의결작성 상태이면서 위원장이고 작성전인 경우
			if (status == '03' && charmn && !decision) {
				// 의결서 작성
				color = 'blue';
				title = '의결서 작성';
				mode  = MODE.INSERT;
				// 의결서 작성 팝업
				btn.on('click', doOpenDecision);
			}
			if (decision) {
				title = '의결서 서명완료';
				if (charmn) {
					title = '의결서 작성완료';
				}
				mode  = MODE.VIEW;
				// 의결서 조회 팝업
				btn.on('click', doOpenDecision);
			}
		}
		// 수당지인 경우
		else if (type == 'P') {
			title = '수당지 작성완료'; 
			color = 'gray';
			if (!pension) {
				title = '수당지 작성';
				// 수당작성 상태이면서 작성전인 경우
				if (status == '04') {
					color = 'blue';
					mode  = MODE.INSERT;
					// TODO 수당지 팝업
					btn.on('click', doOpenPension);
				}
			}
		}
		btn.data('title',P_DATA['cmitNm'   ]);
		btn.data('cno',  P_DATA['cmitMngNo']);
		btn.data('tno',  P_DATA['tenureNo' ]);
		btn.data('ano',  agenda ? agenda['agndNo'] : '');
		btn.data('mode', mode);
		btn.addClass(color);
		btn.append(title);

		return btn;
	}

    // 2023.10.26 의견서 작성 (팝업)
    //--------------------------------------------------------//
    function doOpenOpinion() {
	
		let tit = '환경오염 피해구제 심의회 의견서'; 
		let url = getUrl('/usr/cmit/modalCmitOpinion.do')
		        + '?cmitMngNo='+$(this).data('cno')
		        + '&tenureNo=' +$(this).data('tno')
		        + '&agndNo='   +$(this).data('ano')
		        + '&mode='     +$(this).data('mode');

		C_POPUP_OPINION = $.commModal.loadView(tit, url, {
			// 다이얼로그 크기
			sizeType: 'large', 
			// 배경클릭시 닫기기능 제외
			closeByBackdrop: false, 
			// ESC 누를시 닫기기능 제외
			closeByKeyboard: false
		});
        return false;
    }

    // 2023.10.26 의결서 서명 (팝업)
    //--------------------------------------------------------//
    function doOpenDecision() {
	
		let tit = '환경오염 피해구제 심의회 의결서'; 
		let url = getUrl('/usr/cmit/modalCmitDecision.do')
		        + '?cmitMngNo='+$(this).data('cno')
		        + '&tenureNo=' +$(this).data('tno')
		        + '&agndNo='   +$(this).data('ano')
		        + '&mode='     +$(this).data('mode');
		
		C_POPUP_DECISION = $.commModal.loadView(tit, url, {
			// 다이얼로그 크기
			sizeType: 'large', 
			// 배경클릭시 닫기기능 제외
			closeByBackdrop: false, 
			// ESC 누를시 닫기기능 제외
			closeByKeyboard: false
		});
        return false;
    }

    // 2023.10.26 수당지 작성 (팝업)
    //--------------------------------------------------------//
    function doOpenPension() {
	
		let tit = $(this).data('title'); 
		let url = getUrl('/usr/cmit/modalCmitPension.do')
		        + '?cmitMngNo='+$(this).data('cno')
		        + '&tenureNo=' +$(this).data('tno')
		        + '&mode='     +$(this).data('mode');
		
		C_POPUP_PENSION = $.commModal.loadView(tit, url, {
			// 다이얼로그 크기
			sizeType: 'large', 
			// 배경클릭시 닫기기능 제외
			closeByBackdrop: false, 
			// ESC 누를시 닫기기능 제외
			closeByKeyboard: false
		});
        return false;
    }
});

// 현재페이지 재로딩
function viewReload() {
    $.formUtil.submitForm(
        getUrl('/usr/cmit/viewMyCmit.do'),
        {formId : "selectForm"}
    );
}

// 의견서작성 팝업창에서 호출하는 함수
function opinionCallback() {
	C_POPUP_OPINION.close();
	viewReload();
}

// 의결서작성 팝업창에서 호출하는 함수
function decisionCallback() {
	C_POPUP_DECISION.close();
	viewReload();
}

// 수당지작성 팝업창에서 호출하는 함수
function pensionCallback() {
	C_POPUP_PENSION.close();
	viewReload();
}
