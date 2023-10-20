/**
******************************************************************************************
*** 파일명 : modalSurvey.js
*** 설명글 : 구제급여신청 - 온라인설문지 기능 스크립트
***          /js/com/comm_survey.js 참고
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.12.27    LSH
*** 1.0         2022.01.21    LSH   수정기능 추가
******************************************************************************************
**/

$(function() {
	
	// 서명파일 
	let P_SIGN = ''; 

	// 제출하기
    //--------------------------------------------------------//
	function doSurveySubmit() {

		let P_FORM = $('#surveyForm');

        // VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;
        // FORM VALIDATION
        if (P_FORM.valid() === false)
            return false;
		
		if ($.commUtil.empty(P_SIGN)) {
			$.commMsg.alert('전자서명을 먼저 하셔야 합니다.');
			return false;
		}
		// 저장데이터 구조생성
		let saveData = doSaveRows(P_FORM);

        $.commMsg.confirm("제출하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/usr/relief/saveSurvey.do'), 
                JSON.stringify(saveData),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						// 부모창의 함수 호출
						surveyCallBack({rspnsMngNo: ret['Data']});
                    });
                }
            );
        });
        return false;
	}
	
	// 전자서명하기
    //--------------------------------------------------------//
	function doSurveySign() {
		// 전자서명
		popup.openSignature(function(saveData, signFile) {
			if (signFile) {
				P_SIGN = signFile;
				$('#p_signCn').val(signFile);
				$('#btnSurveySign').hide();				
			}
		}, {});
	}

	// 수정정보 로드
    //--------------------------------------------------------//
	function doSurveyLoad() {
		let rspnsMngNo = $('#p_rspnsMngNo').val();
		if ($.commUtil.empty(rspnsMngNo))
			return false;

		// 이전 설문지정보 로드
		doDataLoad({
			form   : $('#surveyForm'),
			params : {rspnsMngNo: rspnsMngNo},
			url    : getUrl('/usr/relief/getSurvey.do')
		});
        return false;
	}
	
    // 초기실행
    //--------------------------------------------------------//
	// 제출하기 버튼 클릭	
	$('#btnSurveySubmit').bind('click', doSurveySubmit);
	// 전자서명 버튼 클릭	
	$('#btnSurveySign').bind('click', doSurveySign);

    // 문항설정정보 로드
	doConfigLoad({
		form:  $('#surveyForm'),
		table: $('#appSurveyTable'),
		params: {
			qstnnMngNo: $('#qstnnMngNo').val()
		},
		callback: doSurveyLoad
	});
});
