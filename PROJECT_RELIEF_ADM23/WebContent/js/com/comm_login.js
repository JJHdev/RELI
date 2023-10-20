/**
******************************************************************************************
*** 파일명    : comm_login.js
*** 설명      : 관리자 로그인 기능 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.08.26              LSH
*** 1.0         2022.01.05              LSH     시스템분리 (관리자/사용자)
******************************************************************************************
**/
$(document).ready(function () {
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
                // 관리자 경우에만
                if(result.user.roleId == 'ROLE_AUTH_ADM' || result.user.roleId == 'ROLE_AUTH_SYS'){
                    // TODO. 2021.09.27 LSH
                    // 개발 중이므로 비밀번호 변경페이지 이동 없이 바로 처리함.
                    // 운영 시에 해당 부분 테스트 후 주석을 제거할것
                    // 분기별] 비밀번호변경페이지 이동
                    /*if (result.user.diffDays >= CHK_DAYS) {
                        //$.commMsg.alert("비밀번호 변경페이지로 이동 > 비밀번호 변경");
                        $(location).attr('href', getUrl("/sys/user/viewChgPwd.do"));
                        return;
                    }
                    // 다음에변경] 비밀번호변경페이지 이동
                    if (result.user.diffNextDays >= CHK_NEXT_DAYS) {
                        //$.commMsg.alert("비밀번호 변경페이지로 이동 > 비밀번호 변경");
                        $(location).attr('href', getUrl("/sys/user/viewChgPwd.do"));
                        return;
                    }*/

                    // 2022.01.04 CSLEE 수정
                    if (    (result.user.diffDays > CHK_DAYS && result.user.diffNextDays < 0)
                         || (result.user.diffDays > CHK_DAYS && result.user.diffNextDays > CHK_NEXT_DAYS)
                       ) {
                        //비밀번호 변경페이지로 이동 > 비밀번호 변경 (또는 '다음에')
                        $(location).attr('href', getUrl("/sys/user/viewChgPwd.do"));
                        return;
                    }
                }

				$(location).attr('href', getUrl("/com/cmm/loginSucc.do"));
	            return;
	        }
	    }
	}
};
