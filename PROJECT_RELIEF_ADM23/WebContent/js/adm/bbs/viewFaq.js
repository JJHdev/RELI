/**
******************************************************************************************
*** 파일명 : viewFaq.js
*** 설명글 : [게시판] 자주하는 질문 상세보기 관리자 화면
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02               김주호
******************************************************************************************
**/
$(function() {

	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	var P_VFORM     = $('#selectForm' ); // 상세폼 객체
    const P_FAQ_CODE = CODE.BBS.FAQ;  // FAQ 구분 코드



   	// 수정하기
	//--------------------------------------------------------//
   	function doModify() {
   	    $.formUtil.submitForm(getUrl("/adm/bbs/modifyFaq.do"), {
   	        formId : "selectForm"
   	    });
            return false;
   	}

   	// 삭제하기
	//--------------------------------------------------------//
   	function doRemove() {
    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
            // AJAX로 삭제처리
            $.ajaxUtil.ajaxLoad(
                getUrl('/adm/bbs/deleteBbs.do'),
                {nttNo: P_VFORM.find('input[name="nttNo"]').val()},
                function(ret) {
                	$.ajaxUtil.success(ret, function(data) {
                        // 목록으로 이동
                    	$.commMsg.alert('성공적으로 삭제처리되었습니다.', goList);
                	});
                }
            );
    	});
        return false;
   	}

   	// 목록이동
	//--------------------------------------------------------//
	// 2022.01.18 CSLEE 수정 : 게시판구분 파라미터 전달
   	function goList() {
   	    $.formUtil.submitForm(getUrl("/adm/bbs/listBbs.do"), {
   	        formId : "searchForm",
   	        params : {
                bbsSeCd : P_FAQ_CODE      // 자주하는 질문
            }
   	    });
            return false;
   	}


   	//========================================================//
	// 버튼 이벤트 처리
	//--------------------------------------------------------//
   	// 목록버튼 클릭시 이벤트 처리
	$('#btnList'  ).bind('click', goList);
        // 수정버튼 클릭시 이벤트처리
	$('#btnModify').bind('click', doModify);
        // 삭제버튼 클릭시 이벤트처리
	$('#btnRemove').bind('click', doRemove);
	});