/**
******************************************************************************************
*** 파일명 : listUserInfo.js
*** 설명글 : 사용자정보 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.13    LSH
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	var P_TITLE  = '사용자'        ; // 화면 제목
    var P_GRID   = $('#grid'       ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_RFORM  = $('#registForm' ); // 등록폼 객체

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		// 화면영역 맞춤
		fit: true,
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
            {field:'userNo'       , hidden: true, width: 80,halign:'center',align:'center',title:'번호'},
            {field:'userNm'       ,width:100,halign:'center',align:  'center',title:'성명'},
            {field:'userId'       ,width:100,halign:'center',align:  'left',title:'아이디'},
            {field:'brdt'         ,width:100,halign:'center',align:'center',title:'생년월일'},
            {field:'sxdst'        ,width: 60,halign:'center',align:'center',title:'성별',formatter:$.commFormat.sxdst},
            {field:'mbtelNo'      ,width:110,halign:'center',align:  'left',title:'휴대전화', formatter:$.formatUtil.toPhone},
            {field:'telno'        ,width:110,halign:'center',align:  'left',title:'전화번호', formatter:$.formatUtil.toPhone},
            {field:'emlAddr'      ,width:150,halign:'center',align:  'left',title:'E-mail'},
            {field:'joinYmd'      ,width: 80,halign:'center',align:'center',title:'가입일자'},
            {field:'roleNm'       ,width:100,halign:'center',align:  'left',title:'권한'},
            {field:'useStusCd'    ,width: 60,halign:'center',align:'center',title:'사용상태',formatter:$.commFormat.usestts},
            {field:'lstLgnDt'     ,width:120,halign:'center',align:'center',title:'최종로그인'},
            {field:'regDate'      ,width:120,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfDate'      ,width:120,halign:'center',align:'center',title:'수정일자'}
        ]],
   		
        // 행선택시 수정하기
        onSelect: doUpdate
    });
    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//
	// 검색용 권한
	$('#srchRoleId').appComboBox({
		url: getUrl('/com/cmm/getComboRole.do'),
		prompt: '사용자 권한',
		loadFilter: function(data) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		},
		filter: function(row) {
		// 표출 항목
		if (row['code'] == 'ROLE_USR_RESTRICTED' || row['code']== 'ROLE_AUTH_IDN')
			return false;
		return true;
		}
	});

    // 휴대전화 정보수신 체크박스
	$('#appMblAt').appSelectBox({
		type:'static',
		form:'checkbox',
		name:'mbtelRcptnAgreYn',
		rows:STORE['MBL_AT']
	});

    // 성별 라디오박스
	$('#appSxdst').appSelectBox({
		type:'static',
		form:'radio',
		name:'sxdst',
		rows:STORE['SX_DST']
	});
    // 사용상태 라디오박스
	$('#appUseStusCd').appSelectBox({
		type:'static',
		form:'radio',
		name:'useStusCd',
		rows:STORE['USE_STTS']
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
	// 휴대전화 국번 콤보박스
	$('#mbtelNo1').appComboBox({
		params: {upCdId: CODE.MOBILE}
	});
	// 전화번호 국번 콤보박스
	$('#telno1').appComboBox({
		params: {upCdId: CODE.PHONE}
	});
	// 생년월일 콤보박스
	$('#bryy').appComboBox({type:'static', rows: STORE.getYears(-10)});
	$('#brmm').appComboBox({type:'static', rows: STORE.getMonths()});
	$('#brdd').appComboBox({type:'static', rows: STORE.getDays()});
	// 권한
	$('#roleId').appComboBox({
		url: getUrl('/com/cmm/getComboRole.do') ,
		filter: function(row) {
		// 표출 항목
		if (row['code'] == 'ROLE_USR_RESTRICTED' || row['code']== 'ROLE_AUTH_IDN')
			return false;
		return true;
		}
	});

    //========================================================//
    // 등록폼 VALIDATION RULE 정의
    //--------------------------------------------------------//
	// 아이디 중복체크용
	var fnRemote = {
	    type: 'post',
		url: getUrl('/sys/user/checkDuplicate.do'),
		data: {
			userId: function() {
				if ($('#mode').val() == MODE.UPDATE)
					return '';
				return $('#userId').val();
			}
		}
	};

    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            userId    : {required: true,
                         minlength: 4,
                         maxlength: 20,
                         alphanumeric: true,
                         remote: fnRemote},// 아이디 중복 체크
            userNm    : {required: true},
            pswd      : {
				required: function() {
					// 등록시에만 필수
					if ($('#mode').val() == MODE.INSERT &&
						$('#pswd').val() != null)
						return true;
					return false;
				},
                minlength: 9,
                maxlength: 30,
                regx: /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/
			},
            pswdCnfm  : {
				required: function() {
					// 등록시에만 필수
					if ($('#mode').val() == MODE.INSERT &&
						$('#pswdCnfm').val() != null)
						return true;
					return false;
				},
                equalTo: '#pswd'
			},
//			emlAddr   : {required: true,
//                         email: true},
            mbtelNo   : {required: true,
                         mobile: true},
            useStusCd : {required: true}
        },
        // 검증메세지 정의
        messages: {
            userId    : {
	            required: '아이디는 필수 입력 사항입니다.',
                minlength: jQuery.validator.format('최소 {0}자 이상'),
                maxlength: jQuery.validator.format('최대 {0}자 이하'),
                alphanumeric: jQuery.validator.format('알파벳과 숫자만 사용 가능합니다.'),
				remote: '이미 등록된 아이디입니다.'
            },
            userNm    : {required: '성명은 필수 입력 사항입니다.'},
            pswd      : {
				required: '비밀번호는 필수 입력 사항입니다.',
                minlength: jQuery.validator.format('최소 {0}자 이상'),
                maxlength: jQuery.validator.format('최대 {0}자 이하'),
                regx: '비밀번호 형식이 잘못되었습니다.'
            },
            pswdCnfm  : {
				required: '비밀번호 확인은 필수 입력 사항입니다.',
                equalTo: '입력한 비밀번호가 서로 일치하지 않습니다.'
            },
//            emlAddr   : {
//				required: '이메일는 필수 입력 사항입니다.',
//				email:    '이메일을 형식에 맞게 입력해 주세요.'
//			},
            mbtelNo   : {
				required: '휴대전화는 필수 입력 사항입니다.',
				email:    '휴대전화 형식이 잘못되었습니다.'
			},
            useStusCd : {required: '사용상태는 필수 입력 사항입니다.'}
        },
        //invalidHandler: $.easyValidate.handler,
        //errorPlacement: $.easyValidate.placement
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 사용자정보 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/sys/user/getListUserInfo.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);

        return false;
    }

    // 사용자정보 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 등록폼 초기화
    	doRegist();
        // 검색폼 입력데이터 초기화
        P_SFORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // 사용자정보 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 사용자정보 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {

		var params = {userNo: row['userNo']};
		P_RFORM.form('reset');
		$.ajaxUtil.ajaxLoad(
            getUrl('/sys/user/getUserInfo.do'),
			params,
            function(result) {
                var data = result.Data;
                if (data) {
                    // 등록패널 제목변경
					$('#registTitle').html(P_TITLE+' 수정');
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
					
					// 생년월일 분리
					if (data['brdt'] &&
						data['brdt'].length == 10) {
						data['bryy'] = data['brdt'].substring(0,4);
						data['brmm'] = data['brdt'].substring(5,7);
						data['brdd'] = data['brdt'].substring(8);
					}
					if (data['sxdst'] == null) {
						$("input:radio[name='sxdst']").prop("checked",false);
					}
					if (data['emlAddr'] == null) {
						$('#emlAddr1').val('');
						$('#emlAddr2').val('');
					}
					if (data['mbtelNo'] == null) {
						$('#mbtelNo2').val('');
						$('#mbtelNo3').val('');
					}

					if (data['telno'] == null) {
						$('#telno2').val('');
						$('#telno3').val('');
					}
					if(data['mbtelRcptnAgreYn'] == null && data['mbtelRcptnAgreYn'] == 'N'){
						$("input:checkbox[name='mbtelRcptnAgreYn']").prop("checked",false);
					}

					// 폼값 맵핑
					$.formUtil.toForm(data, P_RFORM);
					// 전자우편주소 분리
					$.formUtil.splitData('emlAddr','email');
					// 유선전화번호 분리
					$.formUtil.splitData('telno','phone');
					// 휴대전화번호 분리
					$.formUtil.splitData('mbtelNo','mobile');

                    // 삭제버튼 표시
                    //$('#btnRemove').show();
                    // 2022.01.10 CSLEE 수정 : 임시로 삭제버튼 보이지 않게 함, 삭제 기능 오류 발생중. 추후 수정 필요
                    $('#btnRemove').hide();
					// ID 중복확인 제외
					$('#btnDuplicate').hide();
					// 비밀번호 초기화 표시
					$('#btnRestPswd').show();
                }
				//alert("userNo:"+data.userNo);
            }
        );
        return false;
    }

    // 사용자정보 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 등록패널 제목변경
		$('#registTitle').html(P_TITLE+' 등록');
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 폼값 맵핑
		$.formUtil.toForm({
			mode     : MODE.INSERT,// 등록모드
			telno1   : '02',
			mbtelNo1 : '010',
			useStusCd: 'Y'
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();
		// ID 중복확인 표시
		$('#btnDuplicate').show();
		// 비밀번호 초기화 숨김
		$('#btnRestPswd').hide();

        return false;
    }

    // 사용자정보 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/sys/user/downUserInfoExcel.do'),
            {formId : "searchForm"}
        );
        return false;
    }

    // 사용자정보 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(P_RFORM, ['userNo']);
        if ($.commUtil.empty(params['userNo'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }

    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
            P_RFORM.form('load', {mode : MODE.REMOVE});
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/user/saveUserInfo.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '성공적으로 삭제되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
    	});
        return false;
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

        // 등록폼의 VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

        $.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/user/saveUserInfo.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '성공적으로 저장되었습니다.', function() {
                        doRegist();
                        doSearch();
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
            $.commMsg.alert('사용자ID를 입력하세요.');
            return false;
        }
		var check = $.ajaxUtil.ajaxDefault(
					getUrl('/sys/user/checkDuplicate.do'),
					params);
		if (check) $.commMsg.alert('사용가능한 아이디입니다.');
		else       $.commMsg.alert('이미 등록된 아이디입니다.');
        return false;
    }

    // 우편번호 검색(팝업)
    //--------------------------------------------------------//
    function doPost() {
		// 주소검색을 수행할 팝업 페이지 호출
		//popup.openAddressPopup();
		// 2021.12.24 LSH 자체DB주소검색 팝업 호출
		//popup.openAddress(MODE.SEARCH);
		// 2022.01.19 LSH 주소검색 OPENAPI 팝업 호출
		popup.openAddress(MODE.OPENAPI);
        return false;
    }

    // 비밀번호 초기화
    //--------------------------------------------------------//
    function doRestPswd() {
		var params = {
			userNo: 	$('#userNo'		).val(),
			userNm:		$('#userNm'		).val(),
			mbtelNo:	$('#mbtelNo'	).val()
		}
						
		$.commMsg.confirm("임시 비밀번호를 SMS로 전송하시겠습니까?", function() {
	        // AJAX로 저장처리
	        $.ajaxUtil.ajaxSave(
	            getUrl('/sys/user/restPswd.do'), 
	            JSON.stringify(params),
	            function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 전송되었습니다.', function() {
							return;
						});
                    });	            	
	            }
	        );		
		});
    }
    
    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 등록버튼 클릭시 이벤트처리
    $('#btnRegist').bind('click', doRegist);
    // 엑셀버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);

    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);
    // 취소버튼 클릭시 이벤트처리
    $('#btnUndo'  ).bind('click', doUndo);

	// ID 중복확인 클릭 이벤트 처리
	$('#btnDuplicate').bind('click', doDuplicate);
	// 주소검색 클릭 이벤트 처리
	$('#btnPost').bind('click', doPost);
	// 비밀번호 초기화 클릭 이벤트 처리
	$('#btnRestPswd').bind('click', doRestPswd);

    // 등록폼 초기화
    doRegist();

    // 검색 실행
    doSearch();

    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'), $('#btnSearch'));

});

// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#zip'  ).val(data['zipNo']);
	$('#addr' ).val(data['roadAddrPart1']);
	$('#daddr').val(data['addrDetail']);
}
