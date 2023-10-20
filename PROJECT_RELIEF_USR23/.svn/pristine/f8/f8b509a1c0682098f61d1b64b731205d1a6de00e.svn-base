/**
******************************************************************************************
*** 파일명 : openSelfCheck.js
*** 설명글 : 나에게 맞는 피해구제제도 찾기 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
******************************************************************************************
**/

let P_STEP = '1';
let P_URL = {
	SELF_CHECK: getUrl('/usr/relief/openSelfCheck.do'),
	// 중앙환경분쟁조정위원회
	DSPMDT: 'https://ecc.me.go.kr/',
	// 취약계층소송지원 신청
	LWST: getUrl('/usr/support/openLwst.do'),
	// 환경오염피해구제 신청
	RELIEF: getUrl('/usr/relief/openRelief.do'),
	// 환경오염피해구제란
	INFO: getUrl('/usr/info/openReliefInfo.do'),
	// 질문하기
	BBS: getUrl('/usr/bbs/listBbs.do')
};
	
function doSetStep(step) {
	P_STEP = step;
}

$(function() {

	// 메뉴경로 숨김
	if ($('section.sub-visual'))
		$('section.sub-visual').hide();

	// 1단계 없습니다 클릭 (종료)
	$('#step01A').bind("click", doStop);
	// 1단계 있습니다. 클릭 (2단계이동)
	$('#step01B').bind("click", function() {
		$.formUtil.submitForm( P_URL.SELF_CHECK, {params: {step: '2'}});
		return false;
	});
	// 2단계 없습니다 클릭 (종료)
	$('#step02A').bind("click", function() {
		$.formUtil.submitForm( P_URL.SELF_CHECK, {params: {step: '4'}});
		return false;
	});
	
	// 2단계 있습니다. 클릭 (3단계이동)
	$('#step02B').bind("click", function() {
		$.formUtil.submitForm( P_URL.SELF_CHECK, {params: {step: '3'}});
		return false;
	});
	
	let P_POPUPS = [];
	if (P_STEP == '3') {
		P_POPUPS = [
			{id: 'popupRelief', title: '환경오염 피해구제 제도란?'},
			{id: 'popupLwst'  , title: '취약계층 소송지원신청'    },
			{id: 'popupLocal' , title: '지자체 민원 신청이란?'    },
			{id: 'popupHealth', title: '건강영향조사 청원이란?'   },
			{id: 'popupDspMdt', title: '분쟁조정 신청이란?'       },
		];
	}
	else if (P_STEP == '4') {
		P_POPUPS = [
			{id: 'popupRelief', title: '환경오염 피해구제 제도란?'},
			{id: 'popupLocal' , title: '지자체 민원 신청이란?'    },
			{id: 'popupHealth', title: '건강영향조사 청원이란?'   },
		];
	}

	$(".system-list a").on("click", function(){
		var idx = $(this).index()
		$(this).parent().find('a').removeClass('on');
		$(this).addClass('on');
		$.commModal.openView({
			nl2br: false,
			title: P_POPUPS[idx]['title'],
			message: function() {
				return $('#'+P_POPUPS[idx]['id']).clone();
			},
			onshow: function(dialog) {
				dialog.getModalBody().find('.layerPop').removeClass('off');
				// 중앙환경분쟁조정위원회 버튼 클릭
				dialog.getModalBody().find('a[id="btnDspMdt"]').on("click", function() {
					window.open(P_URL.DSPMDT);
					return false;
				});
				// 취약계층소송지원신청 버튼 클릭
				dialog.getModalBody().find('a[id="btnLwst"]').on("click", function() {
					$.formUtil.submitForm(P_URL.LWST);
					return false;
				});
				// 구제급여신청 버튼 클릭
				dialog.getModalBody().find('a[id="btnRelief"]').on("click", function() {
					$.formUtil.submitForm(P_URL.RELIEF);
					return false;
				});
				// 질의응답 버튼 클릭
				dialog.getModalBody().find('a[id="btnBbs1"]').on("click", function() {
					$.formUtil.submitForm(P_URL.BBS);
					return false;
				});
				dialog.getModalBody().find('a[id="btnBbs2"]').on("click", function() {
					$.formUtil.submitForm(P_URL.BBS);
					return false;
				});
			}
		});
		return false;
	})
	
	function doStop() {
		$.commModal.openView({
			title: '나에게 맞는 피해구제제도찾기가 종료되었습니다.',
			nl2br: false,
			message: function() {
				return $('#popupStop').clone();
			},
			onshow: function(dialog) {
				dialog.getModalBody().find('.layerPop').removeClass('off');
				// 환경오염피해구제란 버튼 클릭
				dialog.getModalBody().find('a[id="btnReliefInfo"]').on("click", function() {
					$.formUtil.submitForm(P_URL.INFO);
					return false;
				});
				// 환경오염피해구제질문하기 클릭
				dialog.getModalBody().find('a[id="btnReliefBbs"]').on("click", function() {
					$.formUtil.submitForm(P_URL.BBS);
					return false;
				});
			}
		});
		return false;
	}
	// 2021.12.13 LSH 관리자인증
	$("#btnAdminCert").on("click", function() {
		// 관리자인증 팝업호출 (comm_popup.js)
		popup.openAdminCert();
		return false;
	})
});
