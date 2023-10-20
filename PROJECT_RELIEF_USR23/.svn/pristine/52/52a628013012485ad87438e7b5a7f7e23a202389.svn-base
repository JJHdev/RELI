/**
******************************************************************************************
*** 파일명 : openMypage.js
*** 설명글 : 마이페이지 - 나의정보 (회원정보수정) 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
******************************************************************************************
**/
$(function() {

	// 메뉴경로 숨김
	if ($('section.sub-visual'))
		$('section.sub-visual').hide();

});

// 비밀번호 정규식
var pwJ =/^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{9,30}$/

$(document).ready(function() {
   //패스워드 확인
   $('#pswd').blur(function() {
      // 비밀번호 변경 상황일 때만 확인
      if(isChangePswd()){
         if (pwJ.test($('#pswd').val())) {
             console.log('true');
             $('#pw_check').text('');
          } else {
             console.log('false');
             $('#pw_check').text('영문,숫자,특수문자(!,@,#,$,%,^,&,*,?,_,~)를 포함하여 9자리 이상 설정하십시오.');
             $('#pw_check').css('color', 'red');
          }
      }

   });

   //패스워드 일치 확인
   $('#pswdCnfm').blur(function() {
      // 비밀번호 변경 상황일 때만 확인
      if(isChangePswd()){
          if ($('#pswd').val() != $(this).val()) {
             $('#pw2_check').text('비밀번호가 일치하지 않습니다.');
             $('#pw2_check').css('color', 'red');
          } else {
             $('#pw2_check').text('');
          }
      }
  	});

});

$(function() {

	//========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_RFORM  = $('#registForm' ); // 등록폼 객체


    // 신청자이메일 도메인 콤보박스
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

	// 신청인휴대전화번호 국번 콤보박스
	$('#mbtelNo1').appComboBox({
		params: {upCdId: CODE.MOBILE},
		// 콤보값 로딩후 실행함수
		callback: function() {
				// 휴대전화번호 분리
				$.formUtil.splitData('mbtelNo','mobile');
			}
	});

	// 신청인유선전화번호 국번 콤보박스
	$('#telno1').appComboBox({
		params: {upCdId: CODE.PHONE},
		// 콤보값 로딩후 실행함수
		callback: function() {
				// 유선전화번호 분리
				$.formUtil.splitData('telno','phone');
		}
	});

	// 휴대전화 정보수신 체크박스
	$('#appMbtelAgreAt').appSelectBox({
		type:'static',
		form:'checkbox',
		name:'mbtelRcptnAgreYn',
		rows:STORE['MBL_AT']
	});
	
   // 이메일 정보수신 체크박스
	$('#appEmlAt').appSelectBox({
		type:'static',
		form:'checkbox', 
		name:'emlAt', 
		rows:STORE['EML_AT']
	});

	// FORM 검증
	function doValidate() {

		var addr = $('#addr');
		var address  = $('#daddr');
		var mbtelNo2 = $('#mbtelNo2');
		var mbtelNo3 = $('#mbtelNo3');

        var inval_Arr = false;

        // 2022.01.04 CSLEE 비밀번호 변경 할 때만
        if(isChangePswd()){
            // 비밀번호가 같은 경우 && 비밀번호 정규식
             if (($('#pswd').val() == ($('#pswdCnfm').val()))
               && pwJ.test($('#pswd').val())) {
                inval_Arr[0] = true;
             } else {
                inval_Arr[0] = false;
                alert('비밀번호를 확인하세요.');
                return false;
             }
        }
        else {inval_Arr[0] = true;}

	     //휴대폰번호
	     if(mbtelNo2.val() == ''){
	        inval_Arr[1] = false;
	        alert('휴대폰 번호를 입력하세요.');
	        return false;
	     } else {
	        inval_Arr[1] = true;
	 	}

	     //휴대폰번호
	     if(mbtelNo3.val() == ''){
	        inval_Arr[2] = false;
	        alert('휴대폰 번호를 입력하세요.');
	        return false;
	     } else {
	        inval_Arr[2] = true;
	 	}

		 //우편번호 확인
	     if(addr.val() == ''){
	        inval_Arr[3] = false;
	        alert('주소를 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[3] = true;
		}

	     //주소 확인
	     if(address.val() == ''){
	        inval_Arr[4] = false;
	        alert('주소를 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[4] = true;
		}

	     //전체 유효성 검사
	     var validAll = true;
	     for(var i = 0; i < inval_Arr.length; i++){
	        if(inval_Arr[i] == false){
	           validAll = false;
	        }
	     }

	     if(validAll == true){ // 유효성 모두 통과
			return true;
	     } else{
	        alert('정보를 다시 확인하세요.')
			return false;
	     }
	}

	// 사용자정보 저장하기
    //--------------------------------------------------------//
    function update_myinfo() {
		//이메일 병합
		$('#emlAddr').val('');
		if ($('#emlAddr1').val() != '' &&
			$('#emlAddr2').val() != '' ) {
			$('#emlAddr' ).val(
				$('#emlAddr1').val() + '@' +
				$('#emlAddr2').val()
			);
		}
		//휴대전화 병합
		$('#mbtelNo').val('');
		if ($('#mbtelNo1').val() != '' &&
			$('#mbtelNo2').val() != '' &&
			$('#mbtelNo3').val() != '' ) {
			$('#mbtelNo').val(
				$('#mbtelNo1').val() +
				$('#mbtelNo2').val() +
				$('#mbtelNo3').val()
			);
		}
		//집/회사전화 병합
		$('#telno').val('');
		if ($('#telno1').val() != '' &&
			$('#telno2').val() != '' &&
			$('#telno3').val() != '' ) {
			$('#telno').val(
				$('#telno1').val() +
				$('#telno2').val() +
				$('#telno3').val()
			);
		}

		// FORM 검증결과가 실패이면
		if (doValidate() === false) {
			return false;
		}

        $.commMsg.confirm("나의정보 수정을 진행하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/usr/mypage/updateMyInfo.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {

					$.commMsg.success(ret, '수정완료 되었습니다.', function() {
						goUrl(getUrl('/usr/mypage/openMypage.do'));
					});
                }
            }).submit();
        });
        return false;
    }

	// 저장버튼 클릭시 이벤트 처리
    $('#btnSave').bind('click', update_myinfo);

    // 회원탈퇴 클릭시 이벤트 처리
    $('#btnWithdraw').bind('click', membWithdraw);

	// 주소검색 클릭 이벤트 처리
	$('#btnPostCode').bind('click', doPost);
});

// 우편번호 검색(팝업)
function doPost() {
	// 주소검색을 수행할 팝업 페이지 호출
	//popup.openAddressPopup();
	// 2021.12.24 LSH 주소검색 OPENAPI 팝업 호출
	popup.openAddress(MODE.OPENAPI);
    return false;
}

// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#zip'  ).val(data['zipNo']);
	$('#addr' ).val(data['roadAddrPart1']);
	$('#daddr').val(data['addrDetail']);
}

// 2022.01.04 CSLEE CNRK
// 비밀번호 변경 여부 : 비밀번호/비밀번호 확인 하나라도 입력하면 TRUE
function isChangePswd(){
    if($('#pswd').val() != '' || $('#pswdCnfm').val() != ''){
        return true;
    }
    else return false;
}

// 회원탈퇴 처리
function membWithdraw() {
	let membForm  = $('form[name="registForm"]');
	let msg = "<font color='blue'>알려드립니다.</font><br>" +
			"회원탈퇴시 홈페이지 서비스 이용은 중지되나<br>" +
			"환경오염피해 배상책임 및 구제에 관한 법률에 따라 신청시 제출하신 개인정보는 10년 간 보관됩니다.";
		
    $.commMsg.confirm(msg, function() {
        // 등록폼을 AJAX로 저장처리
    	membForm.ajaxForm({
            url: getUrl('/usr/mypage/updtMembWithdraw.do'),
            // 오류시 처리로직
            error: $.ajaxUtil.error,
            // 저장후 처리로직
            success: function(ret) {
				$.commMsg.success(ret, '회원탈퇴가 완료 되었습니다.', function() {
					goUrl(getUrl('/com/cmm/logout.do'));
				});
            }
        }).submit();
    });
    
    return false;
}