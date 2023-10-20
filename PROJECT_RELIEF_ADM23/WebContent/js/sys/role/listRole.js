/**
******************************************************************************************
*** 파일명 : listRole.js
*** 설명글 : 역할관리 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.06    LSH
*** 1.0         2021.11.02    LSH   디자인적용 및 개발 수정
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	var P_TITLE  = '역할'           ; // 화면 제목
    var P_GRID   = $('#appGrid'    ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_RFORM  = $('#registForm' ); // 등록폼 객체

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		// 화면영역 맞춤
		fit: true,
        // 그리드 목록조회 URL
        url: getUrl('/sys/role/getListRole.do'),
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 2022.01.09 LSH 데이터 건이 많으면 rownumbers 로 인해 느려져 주석처리함
        // 그리드 행번호 표시여부
        //rownumbers: true,
        // 칼럼정의
        columns: [[
            {field:'roleId'       ,width:200,halign:'center',align:  'left',title:'역할ID'},
            {field:'roleNm'       ,width:200,halign:'center',align:  'left',title:'역할명'},
            {field:'upRoleId'     ,width:200,halign:'center',align:  'left',title:'상위역할ID'},
            {field:'rgtrNo'       ,width:120,halign:'center',align:'center',title:'등록자번호'},
            {field:'regDate'      ,width:120,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfrNo'       ,width:120,halign:'center',align:'center',title:'수정자번호'},
            {field:'mdfDate'      ,width:120,halign:'center',align:'center',title:'수정일자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });

    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//
	$('#upRoleId').appComboBox({
		url: getUrl('/com/cmm/getComboRole.do'),
		value: SYSTEM.ADMIN['code'],
		loadFilter: function(data) {
			// 최상위역할 옵션 첫번쨰항목으로 추가
			data.unshift(ROOT_ROLE);
			return data;
		}
	});

    //========================================================//
    // 등록폼 VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            roleId     : {required: true},
            roleNm     : {required: true},
            upRoleId   : {required: true}
        },
        // 검증메세지 정의
        messages: {
            roleId     : {required: '역할ID는 필수 입력 사항입니다.'},
            roleNm     : {required: '역할명은 필수 입력 사항입니다.'},
            upRoleId   : {required: '상위역할은 필수 선택 사항입니다.'}
        },
        //invalidHandler: $.easyValidate.handler,
        //errorPlacement: $.easyValidate.placement
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 역할관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var frmobj = P_SFORM.serializeObject();
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', frmobj);

        return false;
    }

    // 역할관리 검색리셋
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

    // 역할관리 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 역할관리 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {
        var params = {
            roleId    : row['roleId'  ]
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/sys/role/getRole.do'), params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
                    // 등록패널 제목변경
					$('#registTitle').html(P_TITLE+'수정');
                    // 폼데이터 셋팅
                    $.formUtil.toForm(data, P_RFORM);
                    // ID입력값 입력비활성화
					$('#roleId').prop('readonly', true);
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                }
            }
        );
        return false;
    }

    // 역할관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 등록패널 제목 변경
		$('#registTitle').html(P_TITLE+'등록');
		// 등록폼 리셋
        P_RFORM.form('reset');
        // ID입력값 입력활성화
		$('#roleId').prop('readonly', false);
		// 등록기본값 정의
        $.formUtil.toForm({
			mode   : MODE.INSERT,// 등록모드
			upRoleId: ROOT_ROLE['code'] // 상위역할
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();

        return false;
    }

    // 역할관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(P_RFORM, ['roleId']);
        if ($.commUtil.empty(params['roleId'])) {
            $.easyMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }

    	$.easyMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
	        $.formUtil.toForm({mode:MODE.REMOVE}, P_RFORM);
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/role/saveRole.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.easyMsg.success(ret, '성공적으로 삭제되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
    	});
        return false;
    }

    // 역할관리 저장하기
    //--------------------------------------------------------//
    function doSave() {

        // 등록폼의 VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

        $.easyMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/role/saveRole.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.easyMsg.success(ret, '성공적으로 저장되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
        });
        return false;
    }


    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 등록버튼 클릭시 이벤트처리
    $('#btnRegist').bind('click', doRegist);

    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);
    // 취소버튼 클릭시 이벤트처리
    $('#btnUndo'  ).bind('click', doUndo);

    // 등록폼 초기화
    doRegist();
    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'), $('#btnSearch'));
});
