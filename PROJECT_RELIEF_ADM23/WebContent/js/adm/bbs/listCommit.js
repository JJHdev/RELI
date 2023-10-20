/**
******************************************************************************************
*** 파일명 : listCommit.js
*** 설명글 : [게시판] 관리자 위원회 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    김주호
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
        console.log($(this).data('seq'));
   	    $.formUtil.submitForm(getUrl("/adm/bbs/viewCommit.do"), {
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
     $.formUtil.submitForm(getUrl("/adm/bbs/listCommit.do"), {
        formId : "model1"
    });

}




