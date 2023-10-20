/**
******************************************************************************************
*** 파일명 : writeQna.js
*** 설명글 : [게시판] 질의응답 등록 관리자  화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author       description
*** --------------------------------------------------------------------------------------
*** 1.0          2021.10.02              김주호
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	var P_VFORM     = $('#selectForm' ); // 질문폼 객체
	var A_FORM     = $('#answerForm' ); // 답변폼 객체
	
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
    
   	// 목록이동
	//--------------------------------------------------------//
   	function goList() {
	
   	     $.formUtil.submitForm(getUrl("/adm/bbs/listBbs.do"), {
   	        formId : "selectForm"
   	     });
            return false;
   	     }	


    // 저장버튼 클릭시 이벤트 처리
    //--------------------------------------------------------//
    $('#btnSave').bind('click', function() {
        // CKEDITOR 내용 업데이트
        CKEDITOR.instances['nttCn'].updateElement();

    	$.commMsg.confirm("질문의 답변을 등록하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            A_FORM.ajaxForm({
                url: getUrl('/adm/bbs/saveAnswer.do'),
                
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
                     $.commMsg.success(ret, '성공적으로 등록되었습니다.', function() {

						goUrl(getUrl('/adm/bbs/listBbs.do'));
					});
                }
            }).submit();
    	});
        return false;
	});	
	


   	//========================================================//
	// 버튼 이벤트 처리
	//--------------------------------------------------------//
   	// 목록버튼 클릭시 이벤트 처리
	$('#btnList'  ).bind('click', goList);
	
	});	
	
	