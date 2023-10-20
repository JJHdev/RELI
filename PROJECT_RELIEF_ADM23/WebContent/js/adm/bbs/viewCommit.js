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
$(function() {

	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	var P_VFORM     = $('#selectForm' ); // 상세폼 객체
	var P_FILE_TYPE = 'BOARD';            // 첨부파일 종류
	var P_FILE_VIEW = $('#attachFile').appBbsFile();// 첨부파일 조회목록영역
	
    // 게시글 키정보
    var P_NO  = P_VFORM.find('input[name="nttNo"]').val();

	//========================================================//
	// 첨부파일 목록조회 (comm_component.js 참고)
	//--------------------------------------------------------//
    doSearchFile();

   	// 첨부파일 목록조회
	//--------------------------------------------------------//
   	function doSearchFile() {
        // 첨부파일 목록 생성
		P_FILE_VIEW.select({
            // 파일목록조회 조건객체
            params      : {
            	fileType: P_FILE_TYPE,
                dcmtNo:   P_NO
            },
            // 파일목록조회 URL
            loadUrl     : getUrl('/adm/bbs/listBbsFile.do'),
            // 파일다운로드 URL
            downloadUrl : getUrl('/adm/bbs/downBbsFile.do'),
            // 파일단일삭제 URL
            removeUrl   : getUrl('/adm/bbs/deleteBbsFile.do'),
            // 파일단일삭제 후 실행함수
            removeCallback: function() {
            	$.commMsg.alert('파일 삭제가 정상적으로 처리되었습니다.', doSearchFile);
            }
        });
        return false;
   	}

   	// 목록이동
	//--------------------------------------------------------//
   	function goList() {
   	    $.formUtil.submitForm(getUrl("/adm/bbs/listCommit.do"), {
   	        formId : "searchForm"
   	    });
            return false;
   	}
   	// 수정하기
	//--------------------------------------------------------//
   	function doModify() {
   	    $.formUtil.submitForm(getUrl("/adm/bbs/modifyCommit.do"), {
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
