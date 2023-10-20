/**
******************************************************************************************
*** 파일명 : writeQna.js
*** 설명글 : [게시판] QNA 등록 폼
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    김주호
******************************************************************************************
**/


var pwJ = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{5,30}$/;

$(document).ready(function() {

	// QNA 제목 확인
	$('#nttSj').blur(function() {

		if (!$('#nttSj').val()) {
			$('#nttSj_check').text('제목을 입력해주세요');
			$('#nttSj_check').css('color', 'red');

		} else {
			$('#nttSj_check').text('');

		}
	});
	// QNA 비밀번호 확인
	$('#nttPswd').blur(function() {
		if (!$('#nttPswd').val()) {

			$('#pwd_check').text('글 비밀번호를 입력해주세요');
			$('#pwd_check').css('color', 'red');

		} else if (pwJ.test($('#nttPswd').val())) {

			$('#pw_check').text('영문,숫자,특수문자(!,@,#,$,%,^,&,*,?,_,~)를 포함하여 5자리 이상 설정하십시오.');
			$('#pw_check').css('color', 'red');
		} else {

			$('#pwd_check').text('');
		}
	});


});




$(function() {
	

//========================================================//
// 화면 스크립트 내에 사용할 객체,상수 정의
//--------------------------------------------------------//
	var P_REGIST = $("#registForm");
	

	
	

	function doSave() {

		if (doValidate() === false) {
			return false;
		}


		$.commMsg.confirm("질의응답을 등록하시겠습니까?", function() {
			P_REGIST.ajaxForm({
				url: getUrl('/usr/bbs/saveQna.do'),
				// 오류시 처리로직
				error: $.ajaxUtil.error,
				// 저장 후 처리로직
				success: function(ret) {
					$.commMsg.success(ret, '성공적으로 등록되었습니다.', function() {

						goUrl(getUrl('/usr/bbs/listBbs.do'));
					});


				}

			}).submit();
		});
		return false;
	}


	function doValidate() {
		var inval_Arr = new Array();

		// 제목 검증
		if (!$('#nttSj').val()) {
			inval_Arr[0] = false;
			$.commMsg.alert('제목을 입력해주세요.');
			return false;

		} else {
			inval_Arr[0] = true;
		}
		// 비빌번호 검증

		if (!$('#nttPswd').val()) {
			inval_Arr[1] = false;
			$.commMsg.alert('비밀번호를 입력해주세요.');
			return false;
		} else {
			console.log("success");
			inval_Arr[1] = true;
		}

		// 전체 유효성 검사
		var validAll = true;
		for (var i = 0; i < inval_Arr.length; i++) {
			if (inval_Arr[i] == false) {
				validAll = false;
			}
		}

		if (validAll == true) { // 유효성 모두 통과
			return true;
		} else {
			return false;
		}

	}


	//========================================================//
	// FORM ELEMENT 정의
	//--------------------------------------------------------//
	// Qna 검색옵션 콤보박스
	// (comm_element.js 참고)
	
	$('#qnaType').appComboBox({
		type: 'dynamic',
		mode: '1',
		rows: [
			{ code: '2', text: '--- 말머리 선택 ---' },
			{ code: '1', text: '환경오염 피해구제 ' },		
		],
		url : getUrl('/com/cmm/getComboCode.do'),
		params : {
				upCdId  :'CT041'
		}
	});

  	// 목록이동
	//--------------------------------------------------------//
   	function goList() {
   	    $.formUtil.submitForm(getUrl("/usr/bbs/listBbs.do"), {
   	        formId : "selectForm"
   	    });
            return false;
   	}


	$('#btnList').bind('click', goList);
	$('#popupQnaWriteSuccess').bind('click', doSave);









});

