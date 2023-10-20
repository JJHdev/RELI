/**
******************************************************************************************
*** 파일명 : listMenu.js
*** 설명글 : 메뉴관리 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.05    LSH
*** 1.0         2021.11.02    LSH   디자인적용 및 개발 수정
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	var P_TITLE  = '메뉴'           ; // 화면 제목
    var P_GRID   = $('#appGrid'    ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_RFORM  = $('#registForm' ); // 등록폼 객체

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.treegrid({
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
        // 트리의 ID 필드
		idField: 'menuId',
        // 트리의 트리표시 필드
		treeField: 'menuNm',
        // 칼럼정의
        columns: [[
            {field:'menuNm'       ,width:250,halign:'center',align:  'left',title:'메뉴명'},
            {field:'menuId'       ,width:150,halign:'center',align:  'left',title:'메뉴ID'},
            {field:'upMenuId'     ,width:150,halign:'center',align:  'left',title:'상위메뉴ID'},
            {field:'trgtUrl'      ,width:300,halign:'center',align:  'left',title:'타겟URL'},
            {field:'sysNm'        ,width:100,halign:'center',align:'center',title:'시스템'},
            {field:'useYn'        ,width: 60,halign:'center',align:'center',title:'사용여부', formatter:$.commFormat.useyn},
            {field:'popupYn'      ,width: 60,halign:'center',align:'center',title:'팝업여부', formatter:$.commFormat.popupyn},
            {field:'menuLvl'      ,width: 60,halign:'center',align: 'right',title:'메뉴레벨', formatter:$.commFormat.number},
            {field:'menuOrdr'     ,width: 60,halign:'center',align: 'right',title:'메뉴순서', formatter:$.commFormat.number},
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
	// 검색용 상위메뉴 EasyUI Combo Tree
	let S_UPPER = $('#srchUpId').combotree({
		editable: true,
		prompt: ' 상위메뉴 선택',
		iconWidth: 22,
        icons:[{
            iconCls:'icon-clear',
            handler: function() {
                $('#srchUpId').combotree('clear');
            }
        }]
	});
    // 등록용 상위메뉴 EasyUI Combo Tree
	let R_UPPER = $('#upMenuId').combotree();
    // 검색용 시스템구분 콤보박스
	$('#srchSysCd').appComboBox({
		params: {upCdId: CODE.SYSTEM},
		value:   SYSTEM.ADMIN['code'],
		change:  function() {
			doLoadComboTree(S_UPPER, {sysCd: $(this).val()});
		},
        callback: function() {
			doLoadComboTree(S_UPPER, {sysCd: $('#srchSysCd').val()});
            doSearch();
        }
	});
    // 등록용 시스템구분 콤보박스
	$('#sysCd').appComboBox({
		params: {upCdId: CODE.SYSTEM},
		value:   SYSTEM.ADMIN['code'],
		change:  function() {
			doLoadComboTree(R_UPPER, {sysCd: $(this).val()}, ROOT_MENU['code']);
		},
        callback: function() {
			doLoadComboTree(R_UPPER, {sysCd: $('#sysCd').val()}, ROOT_MENU['code']);
        }
	});
    // 사용여부 라디오박스
	$('#appUseYn').appSelectBox({
		form: 'radio',
		name: 'useYn',
		type: 'static',
		rows: STORE['USE_YN']
	});
    // 팝업여부 라디오박스
	$('#appPopupYn').appSelectBox({
		form: 'radio',
		name: 'popupYn',
		type: 'static',
		rows: STORE['POP_YN']
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
            sysCd      : {required: true},
            upMenuId   : {required: true},
            useYn      : {required: true},
            menuId     : {required: true},
            menuNm     : {required: true},
            menuLvl    : {required: true}
        },
        // 검증메세지 정의
        messages: {
            sysCd      : {required: '시스템은 필수 선택 사항입니다.'},
            upMenuId   : {required: '상위메뉴는 필수 선택 사항입니다.'},
            useYn      : {required: '사용여부는 필수 선택 사항입니다.'},
            menuId     : {required: '메뉴ID는 필수 입력 사항입니다.'},
            menuNm     : {required: '메뉴명은 필수 입력 사항입니다.'},
            menuLvl    : {required: '메뉴레벨은 필수 입력 사항입니다.'}
        },
        //invalidHandler: $.easyValidate.handler,
        //errorPlacement: $.easyValidate.placement
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

	// 메뉴 트리형태 콤보를 로드한다.
    //--------------------------------------------------------//
	function doLoadComboTree( combo, params, value ) {
		// 콤보의 선택값 초기화
		combo.combotree('clear');
		var data = $.ajaxUtil.ajaxDefault(getUrl('/com/cmm/listMenu.do'),
		    $.extend(params, {
				rootId:   ROOT_MENU['code'],
				rootText: ROOT_MENU['text']
			})
		);
		if (value) {
			if ($('#mode').val() == MODE.UPDATE) {
				// 선택된 메뉴 및 하위메뉴를 제거한다.
				data = $.easyUtils.removeNode(data, $('#menuId').val());
			}
		}
		combo.combotree('loadData', data);
		if (value) {
			combo.combotree('setValue', value);
		}
	}

    // 메뉴관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.treegrid('clearSelections');
        // 검색폼 데이터 객체화
        var frmobj = $.extend(P_SFORM.serializeObject(), {tree:'Y'});
        // 그리드 목록조회 URL
        P_GRID.treegrid('options')['url'] = getUrl('/sys/menu/getListMenu.do');
        // 검색폼 그리드 검색
        P_GRID.treegrid('load', frmobj);

        return false;
    }

    // 메뉴관리 검색리셋
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

    // 메뉴관리 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 메뉴관리 수정하기
    //--------------------------------------------------------//
    function doUpdate(row) {

        var params = {menuId: row['menuId']};

        $.ajaxUtil.ajaxLoad(
            getUrl('/sys/menu/getMenu.do'),
			params,
            function(result) {
                var data = result.Data;
                if (data) {
                    // 등록패널 제목변경
					$('#registTitle').html(P_TITLE+'수정');
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
                    // 폼데이터 셋팅
                    $.formUtil.toForm(data, P_RFORM);
                    // 상위메뉴 재로딩
					doLoadComboTree(R_UPPER, {sysCd: data['sysCd']}, data['upMenuId']);
                    // ID입력값 입력비활성화
					$('#menuId').prop('readonly', true);
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                }
            }
        );
        return false;
    }

    // 메뉴관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 등록패널 제목변경
    	$('#registTitle').html(P_TITLE+'등록');
        // 등록폼 리셋
        P_RFORM.form('reset');
        // ID입력값 입력활성화
		$('#menuId').prop('readonly', false);
		// 등록기본값 정의
        $.formUtil.toForm({
			mode   : MODE.INSERT,       // 등록모드
			useYn  : 'Y',               // 사용여부(Y)
			popupYn: 'Y',               // 팝업여부(N)
			upMenuId: ROOT_MENU['code'] // 상위메뉴
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();

        return false;
    }

    // 메뉴관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(P_RFORM, ['menuId']);
        if ($.commUtil.empty(params['menuId'])) {
            $.easyMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }

    	$.easyMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
			$.formUtil.toForm({mode:MODE.REMOVE}, P_RFORM);
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/menu/saveMenu.do'),
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

    // 메뉴관리 저장하기
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
                url: getUrl('/sys/menu/saveMenu.do'),
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
