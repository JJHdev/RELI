/**
******************************************************************************************
*** 파일명    : modifyNotice.js
*** 설명      : 자주하는 질문 수정화면 스크립트
***          
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.07              김주호
******************************************************************************************
**/
$(function() {
	
	
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_RFORM     = $('#registForm' );  // 등록폼 객체
    const P_FAQ_CODE = CODE.BBS.FAQ;  // FAQ 구분 코드

    //========================================================//
    // 웹에디터 정의 (ckeditor)
    //--------------------------------------------------------//
    // CKEDITOR 설정바인딩 (comm_ckeditor.js 참고)
    CKEDITOR.replace('nttCn', CKEDITOR_CONFIG);

    // 2022.10.26 ntarget 추가 (이미지업로드 탭 정의)
    CKEDITOR.on('dialogDefinition', function (ev) {
        var dialogName = ev.data.name;
        var dialog = ev.data.definition.dialog;
        var dialogDefinition = ev.data.definition;

        if (dialogName == 'image') {
            dialog.on('show', function (obj) {
                this.selectPage('Upload'); //업로드탭으로 시작
            });
            
            dialogDefinition.removeContents('advanced'); // 자세히탭 제거
            dialogDefinition.removeContents('Link'); // 링크탭 제거
        }
    });
    
    // 저장버튼 클릭시 이벤트 처리
    //--------------------------------------------------------//
    $('#btnSave').bind('click', function() {
	
        // CKEDITOR 내용 업데이트
        CKEDITOR.instances['nttCn'].updateElement();

    	$.commMsg.confirm("자주하는 질문을 수정하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/bbs/updateBbs.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
                	$.ajaxUtil.success(ret, function(data) {
                        // 목록으로 이동
                    	$.commMsg.alert('성공적으로 수정되었습니다.', function() {
							// 목록이동버튼 클릭 실행
							$('#btnList').trigger('click');
							return false;
						});
                	});
                }
            }).submit();
    	});
        return false;
	});
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
});