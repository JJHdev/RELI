/**
*******************************************************************************
*** 파일명 : modalCmitOpinion.js
*** 설명글 : 온라인위원회 - 위원회 의견서 작성 팝업 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
***    ver      date          author                  description
*** ---------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
*******************************************************************************
**/

$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    let P_FORM = $('#popupForm'); // FORM 객체
	
    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	// 위원회의결 라디오박스
	$('#appDecsnMattr').appSelectBox({
		form:    'radio',
		name:    'decsnMattrCd', 
		params:  {upCdId: CODE.CMITDCSN}
	});
	// 기타서류목록 조회
	$('#appCmitFile').appCmitFile({
		mode:  MODE.LIST,
		params: {
			cmitMngNo: $('#p_cmitMngNo').val(),
			agndNo:    $('#p_agndNo'   ).val(),
			tenureNo:  $('#p_tenureNo' ).val()
		}
	});

    //========================================================//
    // FORM VALIDATION RULE 정의
    //--------------------------------------------------------//
	let validations = {
        debug: false,
        onsubmit: false,
        onfocusout: false,
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement,
        // 검증룰 정의
        rules: {
            dlbrCn         : {required: true},
			decsnMattrCd   : {required: true},
            decsnMattrResn : {required: function() {return $('input[name="decsnMattrCd"][value="'+CODE.DECISION.REJECT+'"]').is(':checked');}},
			signAgreYn     : {required: true}
		},
        // 검증메세지 정의
        messages: {
            dlbrCn         : {required: '심의의견은 필수 입력 사항입니다.'},
			decsnMattrCd   : {required: '의결사항은 필수 선택 사항입니다.'},
            decsnMattrResn : {required: '부결시 사유를 반드시 입력하셔야 합니다.'},
			signAgreYn     : {required: '총괄 의결서 전자서명에 반드시 동의하셔야 합니다.'}
		}
	};
	P_FORM.validate(validations);

    // 저장하기
    //--------------------------------------------------------//
    function doSave( act ) {

        // VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;
        
        // FORM VALIDATION
        if (P_FORM.valid() === false)
            return false;
		
		let title = (act == MODE.SUBMIT ? '제출' : '임시저장');
		let data  = P_FORM.serializeObject();
		$.extend(data, {act: act});

        $.commMsg.confirm(title+"하시겠습니까?", function() {
	            // AJAX로 저장처리
	            $.ajaxUtil.ajaxSave(
	                getUrl('/usr/cmit/saveCmitOpinion.do'), 
	                JSON.stringify(data),
	                function(ret) {
	                    $.ajaxUtil.success(ret, function() {
							$.commMsg.alert('성공적으로 '+title+'되었습니다.', function() {
								// 부모창의 콜백함수 호출
								opinionCallback();
							});
	                    });
	                }
	            );
		});
        return false;
    }

    // 임시저장
    //--------------------------------------------------------//
    function doTmpSave() {
		doSave( MODE.TMPSAVE );
        return false;
    }
    // 제출하기
    //--------------------------------------------------------//
    function doSubmit() {
		doSave( MODE.SUBMIT );
        return false;
    }
	// 임시저장 클릭 이벤트 처리
	$('#btnTmpSave').bind('click', doTmpSave);
	// 제출하기 클릭 이벤트 처리
	$('#btnSubmit').bind('click', doSubmit);
});
