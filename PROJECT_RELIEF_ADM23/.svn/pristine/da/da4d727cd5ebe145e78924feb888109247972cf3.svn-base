/**
******************************************************************************************
*** 파일명 : listHldy.js
*** 설명글 : 공휴일 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.02.02    KSH
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	var P_TITLE  = '공휴일'           ; // 화면 제목
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
        url: getUrl('/sys/hldy/getListHldy.do'),
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 페이징 처리 여부
        pagination: true,
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 2022.01.09 LSH 데이터 건이 많으면 rownumbers 로 인해 느려져 주석처리함
        // 그리드 행번호 표시여부
        //rownumbers: true,
        // 칼럼정의
        columns: [[
            {field:'hldyYmd'     ,width:170,halign:'center',align: 'center',title:'날짜'},
            {field:'hldyNm'      ,width:220,halign:'center',align: 'left',title:'공휴일명'},
            {field:'regDate'     ,width:170,halign:'center',align:'center',title:'등록일자'},
            {field:'rgtrNo'      ,width:170,halign:'center',align:'center',title:'작성자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });

    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//
	$('#srchYear').appComboBox({
		url: getUrl('/com/cmm/getComboHldyYear.do'),
		queryParams: {},
		loadFilter: function(data) {
			data.unshift(COMBO.INIT_ALL);
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
        	hldyYmd    : {required: true},
        	hldyNm     : {required: true}
        },
        // 검증메세지 정의
        messages: {
        	hldyYmd    : {required: '날짜는 필수 입력 사항입니다.'},
        	hldyNm     : {required: '공휴일명은 필수 입력 사항입니다.'}
        },
        //invalidHandler: $.easyValidate.handler,
        //errorPlacement: $.easyValidate.placement
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 공휴일 검색처리
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

    // 공휴일관리 검색리셋
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

    // 공휴일관리 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }
 
    // 공휴일관리 수정하기
    //--------------------------------------------------------//
    $('#hldyYmd').datebox();
    function doUpdate(index, row) {
        var params = {
        	hldyYmd    : row['hldyYmd'  ]
        };        
        $.ajaxUtil.ajaxLoad(
            getUrl('/sys/hldy/getHldy.do'), params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
					data['oldHldyYmd'] = data.hldyYmd;
						
                    // 등록패널 제목변경
					$('#registTitle').html(P_TITLE+'수정');
                    // 폼데이터 셋팅			
                    $.formUtil.toForm(data, P_RFORM);
                    
                    if(!!data.hldyYmd){
                    	document.querySelector('[name="hldyYmd"]').previousElementSibling.value = data.hldyYmd;
                    	$('#hldyYmd').datebox('setValue', data.hldyYmd);
                    }                    
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                }
            }
        );
        return false;
    }
    
    
    // 공휴일관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 등록패널 제목 변경
		$('#registTitle').html(P_TITLE+'등록');
		// 등록폼 리셋
        P_RFORM.form('reset');
		// 등록기본값 정의
        $.formUtil.toForm({
			mode   : MODE.INSERT// 등록모드
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();

        return false;
    }

    // 공휴일관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(P_RFORM, ['hldyYmd']);
        if ($.commUtil.empty(params['hldyYmd'])) {
            $.easyMsg.alert('삭제할 대상을 선택하세요.');          
            return false;
        }

    	$.easyMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
	        $.formUtil.toForm({mode:MODE.REMOVE}, P_RFORM);
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/hldy/saveHldy.do'),
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

    // 공휴일관리 저장하기
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
                url: getUrl('/sys/hldy/saveHldy.do'),
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
