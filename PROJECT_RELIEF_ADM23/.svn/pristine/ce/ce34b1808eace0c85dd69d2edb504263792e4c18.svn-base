/**
******************************************************************************************
*** 파일명    : modifyCommit.js
*** 설명      : 공지사항 수정화면 스크립트
***          
***             - 첨부파일 업로드 적용
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
    var P_FILE_TYPE = 'BOARD';            // 첨부파일 종류
	var P_FILE_BOX  = false;              // 첨부파일 컨트롤 객체
    var P_RFORM     = $('#registForm' );  // 등록폼 객체
    var P_NTT_NO    = $('#nttNo').val();  // 게시글 키정보

    //========================================================//
    // 첨부파일 초기화 (comm_files.js 참고)
    //--------------------------------------------------------//
	P_FILE_BOX = $('#attachFile').appBbsFile({
		maxCount:  3, 
		initCount: 1
	}).init({fileType: P_FILE_TYPE});
    //========================================================//
    // 첨부파일 수정목록조회 (comm_files.js 참고)
    //--------------------------------------------------------//
	P_FILE_BOX.loadBox(getUrl('/adm/bbs/listBbsFile.do'), {
		fileType: P_FILE_TYPE,
		dcmtNo:   P_NTT_NO
	});

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
    
    //========================================================//
    // VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        // true일 경우 디버깅이 가능하도록 
        // 입력값이 유효해서 submit하지 않는다.
        debug: false,
        // true일 경우 포커스가 떠날때 유효성 검사를 한다.
        onfocusout: false,
        // true일 경우 유효성체크없이 무조건 submit한다.
        onsubmit: false,
        // 검증룰 정의
        rules: {
                nttSj    : 'required', // 제목 필수입력
                nttCn    : 'required', // 내용 필수입력
        },
        // 검증메세지 정의
        messages: {
                nttSj    : '제목은 필수 입력 사항입니다.',
                nttCn    : '내용은 필수 입력 사항입니다.'
        },
        // 에러발생시 에러메세지를 처리할 핸들러 (comm_biz.js에 정의)
        invalidHandler: validateHandler,
        // 에러발생시 에러메세지를 표시할 위치처리 핸들러 (comm_biz.js에 정의)
        errorPlacement: validatePlacement
    });

    // 저장버튼 클릭시 이벤트 처리
    //--------------------------------------------------------//
    $('#btnSave').bind('click', function() {
        // CKEDITOR 내용 업데이트
        CKEDITOR.instances['nttCn'].updateElement();

        // 등록폼의 VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

		// 첨부파일 업로드 VALIDATION (comm_component.js)
		var fvalidate = P_FILE_BOX.validate({
			isAlert: true,
			isExt:   true,
			isLimit: true
		});
		
        if (fvalidate === false)
            return false;

    	$.commMsg.confirm("위원회게시물을 수정하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/bbs/saveBbs.do'),
                enctype : 'multipart/form-data',
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
                	$.ajaxUtil.success(ret, function() {
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
	
    // 목록버튼 클릭시 이벤트처리
    //--------------------------------------------------------//
    $('#btnList').bind('click', function() {
        $.formUtil.submitForm(getUrl("/adm/bbs/listCommit.do"), {
            formId: "searchForm"
        });
        return false;
	});
});
	
