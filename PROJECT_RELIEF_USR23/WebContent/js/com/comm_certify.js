/**
******************************************************************************************
*** 파일명    : comm_certify.js
*** 설명      : 마이페이지 식별아이디인증 기능 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.08.26              LSH
*** 1.0         2021.11.08              LSH  기능 구현
******************************************************************************************
**/
$(document).ready(function () {
	// 식별아이디 인증
	Certify.doInit();
});

// 식별아이디 인증
var Certify = {
	doInit: function() {
		// Validation Rule 정의
		$('#identForm').validate({
			debug: false,
			onfocusout: false,
			onsubmit: false,
			rules: {
				identNm : {required: true},
				identId : {required: true},
				certType: {required: true},
				cnfrmYn : {required: function() {return $('#cnfmYn').val() != 'Y';}},
				smsYn   : {required: function() {return $('#smsYn' ).val() != 'Y' && $('#certType:checked').val() == 'PHONE';}}
			},
			messages: {
				identNm : {required: '조회자성명을 입력하세요.'},
				identId : {required: '식별아이디를 입력하세요.'},
				certType: {required: '인증방법을 선택하세요.'},
				cnfrmYn : {required: '인증이 완료되지 않았습니다.'},
				smsYn   : {required: 'SMS 인증이 완료되지 않았습니다.'}
			},
			invalidHandler: validateHandler,
			errorPlacement: validatePlacement
		});
		
		// 휴대전화번호 인증
		$('#certType1').bind('click', Certify.doOpenPhone);
		// 주민등록번호 인증
		$('#certType2').bind('click', Certify.doOpenResident);
		// 인증 처리
		$("#btnCertify").bind("click", Certify.doCertify);
		
		bindEnter($('#identNm'), $("#btnCertify"));
		bindEnter($('#identId'), $("#btnCertify"));
	},
	// 휴대전화번호인증
	doOpenPhone: function() {
		let timer = false;
		$.commModal.loadView(
			'휴대전화번호 인증', 
			getUrl('/html/popupLoginPhone.html'), {
				onhide: function() {
					clearInterval(timer);
				},
				func: function(dialog) {
					
					// 초단위 유효시간 (comm_const.js 참고)
					let limits  = NUMBER.CERT_MINUTES*60;

					let certBtn = $('#btnSmsCertify');
					let cnfmBtn = $('#btnPhoneConfirm');
					let certYn  = $('#popCertYn');
					let smsNo   = $('#popSmsNo');
					let popForm = $('#popupPhoneForm');
					
					// 부모창의 인증결과 RESET
					$('#cnfrmYn').val("N");
					$('#smsYn'  ).val("N");
					
					certYn.val('N');
					smsNo.prop('disabled', true);
					
				  	// 유효시간 설정
					let doStartTimer = function(count, display) {  
						count--;
						timer = setInterval(function () {
					    	var minutes = parseInt(count / 60, 10);
					    	var seconds = parseInt(count % 60, 10);
					    	minutes = minutes < 10 ? "0" + minutes : minutes;
							seconds = seconds < 10 ? "0" + seconds : seconds;
							display.prop('placeholder', minutes + ":" + seconds);
					    	// 타이머 끝
					    	if (--count < 0) {
					      		clearInterval(timer);
								$.commMsg.alert('인증번호 입력시간이 초과되었습니다.<br>다시 인증요청하세요.', function() {
					      			display.prop('placeholder', "전송된 인증번호를 입력하세요.");
									display.prop('disabled', true);
									certBtn.prop("disabled", false);
									certYn.val('N');
								});
					    	}
					  	}, 1000);
					};
					// Validation Rule 정의
					popForm.validate({
						debug: false,
						onfocusout: false,
						onsubmit: false,
						rules: {
							userNm  : {required: true},
							//brdt    : {required: true, birthNo: true},
							//sxdst   : {required: true},
							mbtelNo : {required: true, mobileNo: true}
						},
						messages: {
							userNm  : {required: '이름을 입력하세요.'},
							/*
							brdt: {
								required: '생년월일을 입력하세요.',
								birthNo:  '생년월일을 8자리 숫자로 입력하세요.'
							},
							sxdst   : {required: true},
							*/
							mbtelNo: {
								required: '휴대전화번호를 입력하세요.',
								mobileNo: '휴대전화번호를 숫자로만 정확하게 입력하세요.'
							}
						},
						invalidHandler: $.validateUtil.appendHandler,
						errorPlacement: $.validateUtil.appendPlacement
					});
					// SMS 인증요청
					certBtn.on("click", function() {
						if (popForm.validate().settings)
							popForm.validate().settings.ignore = false;
						if (popForm.valid() === false)
							return false;
						
						let data = popForm.serializeObject();

						let result = $.ajaxUtil.ajaxDefault(
							getUrl("/com/cmm/certify.do"), 
							$.extend({mode:'SMS'}, data)
						);
						if (result.Code == '0') {
							$.commMsg.alert('전송된 인증번호를 입력하세요.', function() {
								certBtn.prop("disabled", true);
								smsNo.prop('disabled', false);
								certYn.val('Y');
								// 타이머 시작
						  		doStartTimer(limits, smsNo);
							});
							return false;
						}
						else {
							$.commMsg.alert(result.Message);
							return false;
						}
					});
					// 확인버튼
					cnfmBtn.on("click", function() {
						if (popForm.validate().settings)
							popForm.validate().settings.ignore = false;
						if (popForm.valid() === false)
							return false;
						let data = popForm.serializeObject();
						if (data['certYn'] != 'Y') {
							$.commMsg.alert('인증요청을 먼저 실행하세요.');
							return false;
						}
						if ($.commUtil.empty(data['smsNo'])) {
							$.commMsg.alert('전송된 인증번호를 입력하세요.');
							return false;
						}
						let result = $.ajaxUtil.ajaxDefault(
							getUrl("/com/cmm/certify.do"), 
							$.extend({mode:'PHONE'}, data)
						);
						if (result.Code == '0') {
							$.commMsg.alert('성공적으로 인증되었습니다.', function() {
								$('#identNm').val(data['userNm']);
								$('#cnfrmYn').val("Y");
								$('#smsYn'  ).val("Y");
								$('#certType1').prop('checked', true);
								dialog.close();
							});
							return false;
						}
						else {
							$.commMsg.alert(result.Message || MSG_COMM_1004);
							return false;
						}
					});
				}
			}
		);
		return false;
	},
	// 주민등록번호인증
	doOpenResident: function() {
		$.commModal.loadView(
			'주민등록번호 인증', 
			getUrl('/html/popupLoginResident.html'), {
				func: function(dialog) {
					
					let cnfmBtn = $('#btnResidentConfirm');
					let popForm = $('#popupResidentForm');
					
					// 부모창의 인증결과 RESET
					$('#cnfrmYn').val("N");
					
					// Validation Rule 정의
					popForm.validate({
						debug: false,
						onfocusout: false,
						onsubmit: false,
						// 검증룰 정의
						rules: {
							userNm   : {required: true},
							identId  : {required: true},
							userRrno1: {required: true, regNo1: true},
							userRrno2: {required: true, regNo2: true}
						},
						// 검증메세지 정의
						messages: {
							userNm   : {required: '이름을 입력하세요.'},
							identId  : {required: '식별아이디를 입력하세요.'},
							userRrno1: {
								required: '주민등록번호 앞자리를 입력하세요.',
								regNo1  : '주민등록번호 앞자리를 형식에 맞게 입력하세요.'
							},
							userRrno2: {
								required: '주민등록번호 뒷자리를 입력하세요.',
								regNo2  : '주민등록번호 뒷자리를 형식에 맞게 입력하세요.'
							}
						},
						invalidHandler: $.validateUtil.appendHandler,
						errorPlacement: $.validateUtil.appendPlacement
					});

					cnfmBtn.on("click", function() {

						if (popForm.validate().settings)
							popForm.validate().settings.ignore = false;
						if (popForm.valid() === false)
							return false;
						
						let data   = popForm.serializeObject();
						
						let result = $.ajaxUtil.ajaxDefault(
							getUrl("/com/cmm/certify.do"), 
							$.extend({mode:'REGNO'}, data)
						);
						if (result.Code == '0') {
							$.commMsg.alert('성공적으로 인증되었습니다.', function() {
								$('#identNm').val(data['userNm']);
								$('#identId').val(data['identId']);
								$('#cnfrmYn').val("Y");
								$('#certType2').prop('checked', true);
								dialog.close();
							});
							return false;
						}
						else {
							$.commMsg.alert(result.Message || MSG_COMM_1004);
							return false;
						}
					});
				}
			}
		);
		return false;
	},
	// 인증하기
	doCertify: function() {
		var identForm = $('#identForm');
		if (identForm.validate().settings)
			identForm.validate().settings.ignore = false;

		if (identForm.valid() === false)
			return false;
	    var param  = identForm.serializeObject();
	    var result = $.ajaxUtil.ajaxDefault(getUrl('/com/cmm/loginCertify.do'), param, false);
	    if (result && result.Code == '0') {
            // 마이페이지 구제급여현황으로 이동처리
			goUrl(getUrl('/usr/mypage/viewRelief.do'));
            return false;
		}
		else {
            $.commMsg.alert(result.Message);
            return false;
		}
	}
};
	
	