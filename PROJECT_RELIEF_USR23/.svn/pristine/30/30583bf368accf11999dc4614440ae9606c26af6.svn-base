/**
******************************************************************************************
*** 파일명 : updateMypage.js
*** 설명글 : 마이페이지 - 나의정보 (회원정보수정) 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
******************************************************************************************
**/

// 비밀번호 정규식
var pwJ =/^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,30}$/

$(document).ready(function() {
   //패스워드 확인
   $('#pswd').blur(function() {
      if (pwJ.test($('#pswd').val())) {
         console.log('true');
         $('#pw_check').text('');
      } else {
         console.log('false');
         $('#pw_check').text('8자리 이상 대문자,특수기호,숫자 포함 사용 가능합니다.');
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
 });

$(function() {
	$("#mode"    ).textbox('textbox').parent().hide();
	$("#userNo"  ).textbox('textbox').parent().hide();
	$("#emlAddr" ).textbox('textbox').parent().hide();
	$("#telno"   ).textbox('textbox').parent().hide();
	
	 var P_GRID   = $('#grid'       ); // 그리드 객체
	 var P_RFORM  = $('#registForm' ); // 등록폼 객체

    // 그리드 포맷함수
    var P_FORMAT = {
    };

	 P_GRID.datagrid({
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 그리드 페이징처리 여부
        pagination:true,
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 한 페이지 출력수
        pageSize: 30,
        // 칼럼정의
        columns: [[
            {field:'userNo'       ,width: 80,halign:'center',align:'center',title:'번호'},
            {field:'userNm'       ,width:100,halign:'center',align:  'left',title:'성명'},
            {field:'userId'       ,width:100,halign:'center',align:  'left',title:'아이디'},
            {field:'roleNm'       ,width:100,halign:'center',align:  'left',title:'권한'},
            {field:'useStusCd'    ,width: 60,halign:'center',align:'center',title:'사용상태',formatter:$.commFormat.usestts},
            {field:'joinYmd'      ,width: 80,halign:'center',align:'center',title:'가입일자'},
            {field:'emlAddr'      ,width:150,halign:'center',align:  'left',title:'E-mail'},
            {field:'telno'        ,width:110,halign:'center',align:  'left',title:'전화번호'},
            {field:'mbtelNo'      ,width:110,halign:'center',align:  'left',title:'휴대전화'},
            {field:'brdt'         ,width:100,halign:'center',align:'center',title:'생년월일'},
            {field:'sxdst'        ,width: 60,halign:'center',align:'center',title:'성별',formatter:$.commFormat.sxdst},
            {field:'addr'         ,width:250,halign:'center',align:  'left',title:'주소',formatter:$.commFormat.address},
            {field:'lstLgnDt'     ,width:120,halign:'center',align:'center',title:'최종로그인'},
            {field:'testUseYn'    ,width: 60,halign:'center',align:'center',title:'테스트<br>사용여부',formatter:$.commFormat.useyn},
            {field:'rgtrNo'       ,width:100,halign:'center',align:'center',title:'등록자번호'},
            {field:'regDttm'      ,width:120,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfrNo'      ,width:100,halign:'center',align:'center',title:'수정자번호'},
            {field:'mdfDttm'      ,width:120,halign:'center',align:'center',title:'수정일자'}
        ]]
	});
		$('#srchRoleId').combobox({
		url: getUrl('/com/cmm/getComboRole.do'), 
		prompt: '사용자 권한',
		loadFilter: function(data) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		}
	});
	
    // 이메일 정보수신 체크박스
	$('#appEmlAt').appSelectBox({
		type:'static',
		form:'checkbox', 
		name:'emlAt', 
		rows:STORE['EML_AT']
	});
	
	// 전화번호 국번 콤보박스
	$('#telno1').combobox({
		data: STORE['TEL_CC']
	});	
	
	// 이메일 도메인 콤보박스
	$('#emlDomain').combobox({
		data: STORE['EMAIL'],
		// 콤보 첫번째 옵션 선택 처리
        onLoadSuccess: $.easyUtils.selectFirst,
		// 콤보 값변경시 입력처리
		onChange: function(newValue) {
			let domain = $('#emlAddr2');
			if (newValue == '') {
				domain.textbox('setValue', '');
				domain.textbox('readonly', false);
			}
			else {
				domain.textbox('setValue', newValue);
				domain.textbox('readonly', true);
			}
		}
	});
	// 전화번호 국번 콤보박스
	$('#telno1').combobox({data: STORE['TEL_CC']});
	// 생년월일 콤보박스
	$('#bryy').combobox({data: STORE.getYears(-10)});
	$('#brmm').combobox({data: STORE.getMonths()});
	$('#brdd').combobox({data: STORE.getDays()});
	// 권한
	$('#roleId').combobox({
		url: getUrl('/com/cmm/getComboRole.do'), 
		onLoadSuccess: $.easyUtils.selectFirst
	});
	
	

	// 주소검색 클릭 이벤트 처리
	$('#btnPost').bind('click', doPost);

});
	    // 우편번호 검색(팝업)
    //--------------------------------------------------------//
    function doPost() {
		// 주소검색을 수행할 팝업 페이지 호출
		popup.openAddressPopup();
        return false;
    }
	
	// 우편번호검색 팝업창에서 호출하는 함수
	function jusoCallBack( data ) {
	$('#zip'  ).val(data['zipNo']);
	$('#addr' ).val(data['roadAddrPart1']);
	$('#daddr').val(data['addrDetail']);
 }