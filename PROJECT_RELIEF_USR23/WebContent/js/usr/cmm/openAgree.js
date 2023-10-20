/**
******************************************************************************************
*** 파일명 : openAgree.js
*** 설명글 : 사용자정보 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0    gjhan     
******************************************************************************************
**/
$(function() {

	// 메뉴경로 숨김
	if ($('section.sub-visual'))
		$('section.sub-visual').hide();

});

//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식
var idJ = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
// 비밀번호 정규식
var pwJ =/^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{9,30}$/
// 이름 정규식
var nameJ = /^[가-힣]{2,8}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;

$(document).ready(function() {
	
	//아이디 확인
   $('#userId').blur(function() {
      if (idJ.test($('#userId').val())) {
         console.log('true');
         $('#id_check').text('');
		 $('#btnDuplicate').removeAttr('disabled');
      } else {
         console.log('false');
         $('#id_check').text('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
         $('#id_check').css('color', 'red');
		 $('#btnDuplicate').attr("disabled", true);
      }
   });

   $('#pswd').blur(function() {
      if (pwJ.test($('#pswd').val())) {
         console.log('true');
         $('#pw_check').text('');
      } else {
         console.log('false');
         $('#pw_check').text('영문,숫자,특수문자(!,@,#,$,%,^,&,*,?,_,~)를 포함하여 9자리 이상 설정하십시오.');
         $('#pw_check').css('color', 'red');
      }
   });

   //패스워드 일치 확인
   $('#pswdCnfm').blur(function() {
      if ($('#pswd').val() != $(this).val()) {
         $('#pw2_check').text('비밀번호가 일치하지 않습니다.');
         $('#pw2_check').css('color', 'red');
      } else {
         $('#pw2_check').text('');
      }
   });

   //이름에 특수문자 들어가지 않도록 설정
   $("#userNm").blur(function() {
      if (nameJ.test($(this).val())) {
	
         console.log(nameJ.test($(this).val()));
         $("#name_check").text('');
      } else {
         $('#name_check').text('휴대폰 인증을 진행해주세요.');
         $('#name_check').css('color', 'red');
      }
   });

});

$(function() {
	
	//========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_RFORM  = $('#registForm' ); // 등록폼 객체
	
    // 이메일 정보수신 체크박스
	$('#appEmlAt').appSelectBox({
		type:'static',
		form:'checkbox', 
		name:'emlAt', 
		rows:STORE['EML_AT']
	});
	
    // 휴대전화 정보수신 체크박스
	$('#appMblAt').appSelectBox({
		type:'static',
		form:'checkbox', 
		name:'mbtelRcptnAgreYn', 
		rows:STORE['MBL_AT']
	});
	
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
	
	// 신청인휴대전화번호 국번 콤보박스
	$('#mbtelNo1').appComboBox({
		params: {upCdId: CODE.MOBILE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 휴대전화번호 분리
			$.formUtil.splitData('mbtelNo','phone');
		}
	});
	
	// 신청인유선전화번호 국번 콤보박스
	$('#telno1').appComboBox({
		params: {upCdId: CODE.PHONE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 유선전화번호 분리
			$.formUtil.splitData('telno','mobile');
		}
	});
	
	// 생년월일 콤보박스
	$('#bryy').appComboBox({
		type: 'static',
		init: {code:'',text:'년'},
		rows: STORE.getYears(-10)
	});
	$('#brmm').appComboBox({
		type: 'static',
		init: {code:'',text:'월'},
		rows: STORE.getMonths()
	});
	$('#brdd').appComboBox({
		type: 'static',
		init: {code:'',text:'일'},
		rows: STORE.getDays()
	});
	
	// FORM 검증
	function doValidate() {
				
		var address  = $('#daddr');
		var mbtelNo2 = $('#mbtelNo2');
		var mbtelNo3 = $('#mbtelNo3');
		var addr = $('#addr');
	
        var inval_Arr = false;
	
	     if (idJ.test($('#userId').val())) {
	        inval_Arr[0] = true;   
	     } else {
	        inval_Arr[0] = false;
	        alert('아이디를  확인하세요.');
	        return false;
	     }
	
	     // 비밀번호가 같은 경우 && 비밀번호 정규식
	     if (($('#pswd').val() == ($('#pswdCnfm').val()))
	           && pwJ.test($('#pswd').val())) {
	        inval_Arr[1] = true;
	     } else {
	        inval_Arr[1] = false;
	        alert('비밀번호를 확인하세요.');
	        return false;
	     }
	
	     // 이름 정규식
	     if (nameJ.test($('#userNm').val())) {
	        inval_Arr[2] = true;   
	     } else {
	        inval_Arr[2] = false;
	        alert('이름을 확인하세요.');
	        return false;
	     }
	
	     //휴대폰번호
	     if(mbtelNo2.val() == ''){
	        inval_Arr[3] = false;
	        alert('휴대폰 번호를 입력하세요.');
	        return false;
	     } else {
	        inval_Arr[3] = true;
	 	}
	
	     //휴대폰번호
	     if(mbtelNo3.val() == ''){
	        inval_Arr[4] = false;
	        alert('휴대폰 번호를 입력하세요.');
	        return false;
	     } else {
	        inval_Arr[4] = true;
	 	}
	
		 //우편번호 확인
	     if(addr.val() == ''){
	        inval_Arr[5] = false;
	        alert('주소를 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[5] = true;
		}
	
	     //주소 확인
	     if(address.val() == ''){
	        inval_Arr[6] = false;
	        alert('주소를 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[6] = true;
		}
		
	     //전체 유효성 검사
	     var validAll = true;
	     for(var i = 0; i < inval_Arr.length; i++){
	        if(inval_Arr[i] == false){
	           validAll = false;
	        }
	     }
	
	     if(validAll == true){ // 유효성 모두 통과
	        //alert('회원가입이 완료되었습니다.');
			return true;      
	     } else{
	        alert('정보를 다시 확인하세요.')
			return false;
	     }
	}
	
	// 사용자정보 저장하기
    //--------------------------------------------------------//
    function doSave() {

		//생년월일 병합
		$('#brdt').val('');
		if ($('#bryy').val() != '' &&
			$('#brmm').val() != '' &&
			$('#brdd').val() != '' ) {
			$('#brdt').val(
				$('#bryy').val() + 
				$('#brmm').val() +
				$('#brdd').val() 
			);
		}
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

        $.commMsg.confirm("회원가입을 진행하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/usr/cmm/JoinUserAgree.do'), 
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {

					$.commMsg.success(ret, '성공적으로 가입되었습니다.', function() {
						// 저장완료 후 메인으로 로그인으로 이동처리
						goUrl(getUrl('/com/cmm/login.do'));
					});
                }
            }).submit();
        });
        return false;
    }

	// 아이디 중복확인
    //--------------------------------------------------------//
    function doDuplicate() {
        var params = $.formUtil.toObject(P_RFORM, ['userId']);
        if ($.commUtil.empty(params['userId'])) {
            alert('사용자ID를 입력하세요.');
            return false;
        }
		var check = $.ajaxUtil.ajaxDefault(
					getUrl('/usr/cmm/checkDuplicate.do'), 
					params);
		if (check) alert('사용가능한 아이디입니다.');
		else       alert('이미 등록된 아이디입니다.');
        return false;
    }


	// 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);

	// ID 중복확인 클릭 이벤트 처리
	$('#btnDuplicate').bind('click', doDuplicate);

	// 주소검색 클릭 이벤트 처리
	$('#btnPostCode').bind('click', doPostCode);

	// 휴대폰 인증 클릭 이벤트 처리
	$('#btnCertify').bind('click', doOpenCertify);
});

// 우편번호 검색(팝업)
function doPostCode() {
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

// 2021.11.16 LSH 휴대폰 인증(팝업)
function doOpenCertify() {
	// 주소검색을 수행할 팝업 페이지 호출
	popup.openMobiliansPopup();
    return false;
}

// 2021.11.16 LSH
// TODO.휴대폰 인증 팝업창에서 호출하는 함수
function certifyCallback( data ) {
	console.log(data['Result']); // 결과 (SUCCESS)
	console.log(data['Name']); // 이름
	console.log(data['No']); // 휴대전화번호
	console.log(data['Socialno']); // 생년월일
	
	let nm 	= data['Name'];
	let no	= data['No'];
	let so	= data['Socialno'];
	
	if (data['Result'] == 'SUCCESS') {
		$.commMsg.alert('성공적으로 휴대폰 본인인증이 완료되었습니다.', function() {
			$('#userNm' ).val(nm);
			$('#brdt'   ).val(so);
			$('#mbtelNo').val(no);
			if (so.length == 8) {
				$('#bryy').val(so.substring(0,4));
				$('#brmm').val(so.substring(4,6));
				$('#brdd').val(so.substring(6,8));
			}
			if (no.length >= 10) {
				// 전화번호 분리맵핑
				$.formUtil.splitData('mbtelNo', 'mobile');
			}
			// 휴대폰인증 완료여부
			$('#certifyYn').val('Y');

		});
	}
}
