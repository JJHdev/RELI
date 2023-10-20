/**
******************************************************************************************
*** 파일명 : viewNotice.js
*** 설명글 : [게시판] 공지사항 상세보기 관리자 화면
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    김주호
******************************************************************************************
**/

$(document).ready(function() {
	$('#nttSj').keyup(function() {

		if (!$('#nttSj').val()) {
			$('#nttSj_check').text('제목을 입력해주세요.');
			$('#nttSj_check').css('color', 'red');

		} else {
			$('#nttSj_check').text('');

		}
	});
});




$(function() {


	function doValidate() {

		var inval = false;

		if (!$('#nttSj').val()) {
			inval = false;
			alert("제목을 입력해주세요.");
		} else {
			inval = true;
		}

		if (inval == true) {
			return true;
		} else {
			return false;
		}

	}

	function doUpdate() {

		var P_REGIST = $('#model1');
		var bbsSeCd = $('#bbsSeCd').val();
		const bbsUrl = new Map();
		bbsUrl.set("10", "/adm/bbs/viewNotice.do");
		bbsUrl.set("20", "");
		bbsUrl.set("30", "");
		bbsUrl.set("40", "/adm/bbs/viewStatute.do");
		bbsUrl.set("50", "");
		bbsUrl.set("60", "/adm/bbs/viewCommit.do");
		bbsUrl.set("70", "");



		if (doValidate() === false) {
			return false;
		}

		$.commMsg.confirm("게시물을 수정하시겠습니까?", function() {
			P_REGIST.ajaxForm({
				url: getUrl('/adm/bbs/updateBbs.do'),
				// 오류시 처리로직
				error: $.ajaxUtil.error,
				// 저장 후 처리로직
				success: function(ret) {
					$.commMsg.success(ret, '성공적으로 수정되었습니다.', function() {

						$.formUtil.submitForm(ROOT_PATH +bbsUrl.get(bbsSeCd), {
							formId: 'model1',
							method: 'post'

						});
					});


				}

			}).submit();
		});

		return false;


	}

	function btnBbsBack() {
		var test = $('#bbsSeCd').val();
		console.log(test);
	}


	$('#btnBbsModify').bind('click', doUpdate);
	$('#btnBbsBack').bind('click', btnBbsBack);


});














