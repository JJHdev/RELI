/**
******************************************************************************************
*** 파일명    : comm_login.js
*** 설명      : 사용자 로그인 기능 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.08.26              LSH
*** 1.0         2022.01.05              LSH     시스템분리 (관리자/사용자)
******************************************************************************************
**/
$(document).ready(function () {
	$('#loginForm').show();
	$('#identForm').hide();

	$('.login-sortation > a').bind('click', function() {
		let id = $(this).data('id');
		if (id == 'LOGIN') {
			$('#loginForm').show();
			$('#identForm').hide();
		}
		else {
			$('#loginForm').hide();
			$('#identForm').show();
			$('#identNm').focus();
		}
		return false;
	});
	$('.login-sortation > a').eq(0).trigger('click');

	// 회원 로그인
	Login.doInit();
});

var CHK_DAYS         = 90;  // 분기별] 비밀번호변경 체크 일수
var CHK_NEXT_DAYS    = 30;  // 다음에변경] 비밀번호변경 체크 일수

var Login = {
	doInit: function() {
		// Validation Rule 정의
		$('#loginForm').validate({
			debug: false,
			onfocusout: false,
			onsubmit: false,
			rules: {
				username: {required: true},
				password: {required: true}
			},
			messages: {
				username: {required: '아이디를 입력하세요.'},
				password: {required: '비밀번호를 입력하세요.'}
			},
			invalidHandler: validateHandler,
			errorPlacement: validatePlacement
		});
	    // 로그인 클릭
	    $("#btnLogin"  ).bind("click", Login.doLogin);
	    // 회원가입 클릭
	    $("#btnJoin"   ).bind("click", Login.doJoin);
	    // 아이디찾기 클릭
	    $("#btnFindId" ).bind("click", Login.doFindId);
	    // 비밀번호찾기 클릭
	    $("#btnFindPwd").bind("click", Login.doFindPwd);

		bindEnter($('#username'), $("#btnLogin"));
		bindEnter($('#password'), $("#btnLogin"));
		$('#username').focus();
	},
	//회원 로그인
	//--------------------------------------------------------//
	doLogin: function() {

		let loginForm = $('#loginForm');
		if (loginForm.validate().settings)
			loginForm.validate().settings.ignore = false;
		if (loginForm.valid() === false)
			return false;
	    var param   = loginForm.serializeObject();
	    var result  = $.ajaxUtil.ajaxDefault(getUrl('/com/cmm/loginCheck.do'), param, false);
	    var message = "";
	    if (result &&
	    	result != 'syserr') {
	        // 실패
	        if (result.failFlag &&
	        	result.failFlag.indexOf("E") >= 0) {
	            switch (result.failFlag) {
	                case "E1":
	                    $('#username').val('');
	                    message =    MSG_COMM_E001; break;          //"사용자정보가 올바르지 않습니다."   ;
	                case "E2":
	                    $('#username').val('');
	                    message =    MSG_COMM_E002; break;          //"사용자정보가 올바르지 않습니다."   ;
	                case "E3": message = MSG_COMM_E003; break;      //"사용중지된 사용자이거나 제한된 사용자입니다. 관리자에게 문의바랍니다."   ;
	                case "E5": message = MSG_COMM_E005; break;      //"접속 불가능한 IP 입니다. 관라자에게 문의 바랍니다."   ;
	                case "E7": message = MSG_COMM_E007; break;      //"해당 사용자는 로그인 5회이상 실패로 로그인을 할 수 없습니다."   ;
	            }
	            // 비밀번호 비우기
	            $('#password').val('');
	            // 에러 메시지
	            $.commMsg.alert(message);
	        }
	        // 성공
	        else {
				$(location).attr('href', getUrl("/com/cmm/loginSucc.do"));
	            return;
	        }
	    }
	},
	// 회원 가입 페이지로 이동
	//--------------------------------------------------------//
	doJoin: function() {
		goUrl(getUrl("/usr/cmm/joinAgree.do"));
		return false;
	},
	// 아이디 찾기 팝업
	//--------------------------------------------------------//
	doFindId: function() {
		$.commModal.loadView(
			'아이디 찾기',
			getUrl('/html/popupFindId.html'), {
				closable: false,
				func: function(dialog) {

					let popForm = $('#popupForm');
					let popRslt = $('#popupResult');
					let checkYn = false;

					// Validation Rule 정의
					popForm.validate({
						debug: false,
						onfocusout: false,
						onsubmit: false,
						// 검증룰 정의
						rules: {
							userNm   : {required: true, minlength: 2, maxlength: 10},
							brdt     : {required: true, birthNo:  true},
							mbtelNo  : {required: true, mobileNo: true}
						},
						// 검증메세지 정의
						messages: {
							userNm   : {
								required: '이름을 입력하세요.',
								minlength: $.validator.format( "이름을 {0}자 이상 입력하세요." ),
								maxlength: $.validator.format( "이름은 {0}자를 넘을 수 없습니다." )
							},
							brdt     : {
								required: '생년월일을 입력하세요.',
								birthNo:  '생년월일을 8자리 숫자로 정확하게 입력하세요(예: 20160101)'
							},
							mbtelNo  : {
								required: '휴대전화번호를 입력하세요.',
								mobileNo: '휴대전화번호를 숫자로만 정확하게 입력하세요.'
							}
						},
						invalidHandler: $.validateUtil.appendHandler,
						errorPlacement: $.validateUtil.appendPlacement
					});

					$('#btnFind').on("click", function() {

						if (checkYn) {
							dialog.close();
							return false;
						}
						popRslt.empty();
						popRslt.removeClass("fail");
						popRslt.removeClass("success");

						if (popForm.validate().settings)
							popForm.validate().settings.ignore = false;
						if (popForm.valid() === false)
							return false;

						let data = popForm.serializeObject();
						$.ajaxUtil.ajaxLoad(
							getUrl("/com/cmm/findId.do"),
							data,
							function(ret) {
								if (ret.Code != '0') {
									popRslt.addClass("fail");
									popRslt.append('<p class="info">'+ret.Message+'</p>');
								}
								else {
									popRslt.addClass("success");
									popRslt.append('<p class="info">'+ret.Message+'</p>');
									checkYn = true;
								}
							}
						);
					});
				}
			}
		);
		return false;
	},
	// 비밀번호 찾기 팝업
	//--------------------------------------------------------//
	doFindPwd: function() {

		let timer = false;

		$.commModal.loadView(
			'비밀번호 찾기',
			getUrl('/html/popupFindPassword.html'), {
				closable: false,
				onhide: function() {
					clearInterval(timer);
				},
				func: function(dialog) {

					const FORMS = [
						$('#popupForm1'),
						$('#popupForm2'),
						$('#popupForm3')
					];
					FORMS[0].validate({
						debug:      false,
						onsubmit:   false,
						onfocusout: false,
						invalidHandler: $.validateUtil.appendHandler,
						errorPlacement: $.validateUtil.appendPlacement,
						// 검증룰 정의
						rules: {
							userId   : {required: true, minlength: 2, maxlength: 20},
							userNm   : {required: true, minlength: 2, maxlength: 10},
							brdt     : {required: true, birthNo:  true},
							mbtelNo  : {required: true, mobileNo: true}
						},
						// 검증메세지 정의
						messages: {
							userId   : {
								required: '사용자ID를 입력하세요.',
								minlength: $.validator.format( "사용자ID를 {0}자 이상 입력하세요." ),
								maxlength: $.validator.format( "사용자ID는 {0}자를 넘을 수 없습니다." )
							},
							userNm   : {
								required: '이름을 입력하세요.',
								minlength: $.validator.format( "이름을 {0}자 이상 입력하세요." ),
								maxlength: $.validator.format( "이름은 {0}자를 넘을 수 없습니다." )
							},
							brdt     : {
								required: '생년월일을 입력하세요.',
								birthNo:  '생년월일을 8자리 숫자로 정확하게 입력하세요(예: 20160101)'
							},
							mbtelNo  : {
								required: '휴대전화번호를 입력하세요.',
								mobileNo: '휴대전화번호를 숫자로만 정확하게 입력하세요.'
							}
						}
					});
					FORMS[1].validate({
						debug:      false,
						onsubmit:   false,
						onfocusout: false,
						invalidHandler: $.validateUtil.appendHandler,
						errorPlacement: $.validateUtil.appendPlacement,
						// 검증룰 정의
						rules: {
							smsNo: {required: true}
						},
						// 검증메세지 정의
						messages: {
							smsNo: {required: '휴대전화에 전송된 인증번호를 입력하세요.'}
						}
					});
					FORMS[2].validate({
						debug:      false,
						onsubmit:   false,
						onfocusout: false,
						invalidHandler: $.validateUtil.appendHandler,
						errorPlacement: $.validateUtil.appendPlacement,
						// 검증룰 정의
						rules: {
				            pswd      : {
								required:  true,
				                minlength: 8,
				                maxlength: 30,
				                regx: /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,30}$/
							},
				            pswdCnfm  : {
								required: true,
				                equalTo: '#p_pswd'
							}
						},
						// 검증메세지 정의
						messages: {
				            pswd      : {
								required: '비밀번호를 입력하세요.',
				                minlength: $.validator.format('비밀번호를 {0}자 이상 입력하세요.'),
				                maxlength: $.validator.format('비밀번호는 {0}자를 넘을 수 없습니다.'),
				                regx: '영문,숫자,특수문자(!,@,#,$,%,^,&,*,?,_,~)를 포함하여 8자리 이상 설정하십시오.'
				            },
				            pswdCnfm  : {
								required: '비밀번호 확인을 입력하세요.',
				                equalTo:  '입력하신 비밀번호가 서로 일치하지 않습니다.'
				            }
						}
					});

					let fnResult = function(code, message) {
						let dom = $('#popupPasswordResult');
						dom.empty();
						dom.removeClass("fail");
						dom.removeClass("success");
						if (code == '0')
							dom.addClass("success");
						else
							dom.addClass("fail");
						dom.append('<p class="info">'+message+'</p>');
					};

					let fnValidate = function(form) {
						if (form.validate().settings)
							form.validate().settings.ignore = false;
						if (form.valid() === false)
							return false;
						return form.serializeObject();
					}

					// SMS 인증요청여부
					let SMS_YN  = false;
					// SMS 인증확인여부
					let CERT_YN = false;
					// SMS 인증번호박스
					$('#p_smsNo').prop('disabled', true);
					// 초단위 유효시간 (comm_const.js 참고)
					let limits = NUMBER.CERT_MINUTES*60;
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
									$('#btnSmsSend').prop("disabled", false);
									$('#btnSmsCert').prop("disabled", true);
									SMS_YN = 'N';
								});
					    	}
					  	}, 1000);
					};
					// SMS인증요청
					let fnSend = function() {
						let data = fnValidate(FORMS[0]);
						if (data == false) {
							return false;
						}
						$.extend(data, {mode: 'SMS'});
						$.ajaxUtil.ajaxLoad(
							getUrl("/com/cmm/findPswd.do"),
							data,
							function(ret) {
								if (ret.Code < 0) {
									fnResult(ret.Code, ret.Message);
								}
								else {
									// 인증요청완료
									SMS_YN = true;
									fnResult(ret.Code, ret.Message);
									fnShow( 1 );
									$('#btnSmsSend').prop("disabled", true);
									$('#btnSmsCert').prop("disabled", false);
									$('#p_smsNo'   ).prop('disabled', false);
									$('#p_userNo'  ).val(ret.userNo);
									// 타이머 시작
							  		doStartTimer(limits, $('#p_smsNo'));
								}
							}
						);
						return false;
					};
					// SMS인증확인
					let fnCert = function() {
						if (SMS_YN == false) {
							fnResult('-1', '휴대전화 인증요청이 실행되지 않았습니다.');
							return false;
						}
						let data = fnValidate(FORMS[1]);
						if (data == false) {
							return false;
						}
						$.extend(data, {mode: 'CERT'});
						$.ajaxUtil.ajaxLoad(
							getUrl("/com/cmm/findPswd.do"),
							data,
							function(ret) {
								if (ret.Code < 0) {
									fnResult(ret.Code, ret.Message);
								}
								else {
									// 인증확인완료
									CERT_YN = true;
									fnResult(ret.Code, ret.Message);
									fnShow( 2 );
									fnHide( 1 );
									fnHide( 0 );
								}
							}
						);
						return false;
					};

					// 비밀번호변경
					let fnSave = function() {
						if (SMS_YN == false) {
							fnResult('-1', '휴대전화 인증요청이 실행되지 않았습니다.');
							return false;
						}
						if (CERT_YN == false) {
							fnResult('-1', '휴대전화 인증확인이 완료되지 않았습니다.');
							return false;
						}
						let data = fnValidate(FORMS[2]);
						if (data == false) {
							return false;
						}
						$.extend(data, {userNo: $('#p_userNo').val()});
						$.commMsg.confirm('비밀번호를 재설정하시겠습니까?', function() {
							$.ajaxUtil.ajaxLoad(
								getUrl("/com/cmm/savePswd.do"),
								data,
								function(ret) {
									if (ret.Code < 0) {
										$.commMsg.alert(ret.Message);
									}
									else {
										$.commMsg.alert(ret.Message, function() {
											dialog.close();
										});
									}
								}
							);
							return false;
						});
						return false;
					};

					let fnShow = function( index ) {
						if (FORMS[index].hasClass('app-off')) {
							FORMS[index].removeClass('app-off');
							if      (index == 0) $('#btnSmsSend' ).on("click", fnSend); // SMS인증요청
							else if (index == 1) $('#btnSmsCert' ).on("click", fnCert); // SMS인증확인
							else if (index == 2) $('#btnPswdSave').on("click", fnSave); // 비밀번호변경
						}
					};
					let fnHide = function( index ) {
						if (FORMS[index].hasClass('app-off') === false) {
							FORMS[index].addClass('app-off');
						}
					};
					fnShow( 0 );
				}
			}
		);
		return false;
	}
};
