/**
******************************************************************************************
*** 파일명 : listMyBbs.js
*** 설명글 : 마이페이지 - 나의질의현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
******************************************************************************************
**/
$(document).ready(function (){

    // 조회 버튼
    $("#srchBtn").click(function(){
        // 조회는 무조건 page 1
        Search(1);
    });
    
    // ajaxBtn 리스트 조회 버튼
    $("#ajaxBtn").click(function(){
        getSearchList();
    });

    // 상세페이지
	$('.btnView').click(function(){		
		$('#nttNo').val($(this).data('seq'));
   
   	    $.formUtil.submitForm(getUrl("/usr/mypage/viewMyQna.do"), {
   	        formId : "model1"
   	    });

		return false;

	});
	
    

    // 엔터
    $('#srchText').on("keypress", function(e) {
        if (e.keyCode == 13) {
            Search(1);
        }
    });    
});   

/**
 * 조회
 * @returns
 */
function Search(pageNum){
    // 이동 페이지 설정
    if (pageNum && pageNum != null) {
        $("#page").val(pageNum);
    }
     $.formUtil.submitForm(getUrl("/usr/mypage/listMyBbs.do"), {
        formId : "model1"
    });

}


$(function(){
	
	// 질의응답 문의하기 (페이지이동)
    //--------------------------------------------------------//
	function doQnaRegist() {
		$.formUtil.submitForm(getUrl("/usr/mypage/writeMyQna.do"));
	}
	
	$('#btnQnaRegist').bind('click', doQnaRegist);
});



 