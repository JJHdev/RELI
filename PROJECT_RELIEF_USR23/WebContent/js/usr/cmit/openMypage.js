/**
******************************************************************************************
*** 파일명 : openMypage.js
*** 설명글 : 온라인위원회 - 나의정보 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
******************************************************************************************
**/
$(function() {

	// 메뉴경로 숨김
	if ($('section.sub-visual'))
		$('section.sub-visual').hide();

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_FORM  = $('#registForm' ); // 등록폼 객체

	// 이메일 도메인 콤보박스
	$('#emlDomain').appComboBox({
		params: {upCdId: CODE.EMAIL},
		init:   COMBO.INIT_DIRECT,		
		// 콤보 값변경시 입력처리
		change: function() {
			let domain   = $('#emlAddr2');
			let newValue = $(this).find('option:checked').val();
			let newLabel = $(this).find('option:checked').text();
			if (newValue == '') {
				domain.val('');
				domain.prop('readonly', false);
			}
			else {
				domain.val(newLabel);
				domain.prop('readonly', true);
			}
		}
	});
	// 휴대전화번호 국번 콤보박스
	$('#mbtelNo1').appComboBox({
		params: {upCdId: CODE.MOBILE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 휴대전화번호 분리
			$.formUtil.splitData('mbtelNo','mobile');
		}
	});
	// 유선전화번호 국번 콤보박스
	$('#telno1').appComboBox({
		params: {upCdId: CODE.PHONE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 유선전화번호 분리
			$.formUtil.splitData('telno','phone');
		}
	});
	// 지급은행 콤보박스
	$('#bankCd').appComboBox({
		params: {upCdId: CODE.BANK},
		init: {code:'', text:'은행선택'}
	});
	
    // 휴대전화 정보수신 체크박스
	$('#appMbtelAgreAt').appSelectBox({
		type:'static',
		form:'checkbox',
		name:'mbtelRcptnAgreYn',
		rows:STORE['MBL_AT']
	});

    //========================================================//
    // 등록폼 VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_FORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
			mbtelNo2  : {required: true},
			mbtelNo3  : {required: true},
            mbtelNo   : {mobile: true},
			addr      : {required: true},
			emlAddr   : {email: true},
			actno     : {regx: /^[0-9\-]{11,30}$/}
        },
        // 검증메세지 정의
        messages: {
            mbtelNo2  : {required: '휴대전화번호를 입력하세요.'},
            mbtelNo3  : {required: '휴대전화번호를 입력하세요.'},
            mbtelNo   : {mobile: '휴대전화번호를 형식에 맞게 입력해 주세요.'},
            addr      : {required: '주소를 확인하세요.'},
            emlAddr   : {email: '이메일을 형식에 맞게 입력해 주세요.'},
            actno     : {regx: '계좌번호를 형식에 맞게 입력하세요.'}
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 사용자정보 저장하기
    //--------------------------------------------------------//
    function doSave() {

		//전자우편주소 병합
		$.formUtil.mergeData('emlAddr','email',2);
		//휴대전화번호 병합
		$.formUtil.mergeData('mbtelNo','mobile',3);
		//유선전화번호 병합
		$.formUtil.mergeData('telno','phone',3);

        // 등록폼의 VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (!P_FORM.valid())
            return false;

        $.commMsg.confirm("나의정보 수정을 진행하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_FORM.ajaxForm({
                url: getUrl('/usr/cmit/saveMypage.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '수정완료 되었습니다.', function() {
                        goUrl(getUrl('/usr/cmit/openMypage.do'));
					});
                }
            }).submit();
        });
        return false;
    }

    // 우편번호 검색(팝업)
    //--------------------------------------------------------//
    function doPost() {
		// 주소검색 OPENAPI 팝업 호출
		popup.openAddress(MODE.OPENAPI);
        return false;
    }

    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
	// 주소검색 클릭 이벤트 처리
	$('#btnPostCode').bind('click', doPost);
});

// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#zip'  ).val(data['zipNo']);
	$('#addr' ).val(data['roadAddrPart1']);
	$('#daddr').val(data['addrDetail']);
}
