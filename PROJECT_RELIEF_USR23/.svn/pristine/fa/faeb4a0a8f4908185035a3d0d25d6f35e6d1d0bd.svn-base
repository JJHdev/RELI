/**
*******************************************************************************
*** 파일명 : openBioRelief.js
*** 설명글 : 살생물제품 구제급여 신청 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 2.0         2023.01.16    LSH
*******************************************************************************
**/
$(function() {

    // 구제급여 신청하기
    //--------------------------------------------------------//
    function doApply() {
		
		let code = $(this).data('code');
		
		$.formUtil.submitForm(getUrl('/usr/relief/openBioReliefForm.do'), {
			params: { aplySeCd: code }
		});
	}
	
    // 피해자본인신청 클릭시 이벤트 처리
    $('#btnSufrerSlf').bind('click', doApply);
    // 대리인신청(피해자생존) 클릭시 이벤트 처리
    $('#btnAgncyLv'  ).bind('click', doApply);
    // 대리인신청(피해자사망) 클릭시 이벤트 처리
    $('#btnAgncyDth' ).bind('click', doApply);
});
