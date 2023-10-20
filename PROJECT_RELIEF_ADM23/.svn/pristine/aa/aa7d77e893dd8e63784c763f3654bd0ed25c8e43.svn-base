/**
******************************************************************************************
*** 파일명 : listSckwnd.js
*** 설명글 : 질병코드관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.23    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 목록 GRID
	let P_FORM    = $('#searchForm'     ); // 검색폼	
	let P_RFORM   = $('#registForm'     ); // 등록폼
	let P_HEADER  = $('#registTitle'    ); // 제목객체
	let P_TITLE   = '질병코드'           ; // 업무제목

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
        // 2022.01.09 LSH 데이터 건이 많으면 rownumbers 로 인해 느려져 주석처리함
        // 그리드 행번호 표시여부
        //rownumbers: true,
        // 트리의 ID 필드
		idField: 'id',
        // 트리의 트리표시 필드
		treeField: 'sckwndNm',
        // 칼럼정의
        columns: [[
            {field:'sckwndNm'     ,width:400,halign:'center',align:  'left',title:'상병명'},
            {field:'sckwndCd'     ,width:100,halign:'center',align:  'left',title:'질병코드'},
            {field:'upSckwndCd'   ,width:100,halign:'center',align:  'left',title:'상위코드', formatter:$.commFormat.rootNm},
            {field:'dissClCd'     ,width:100,halign:'center',align:  'left',title:'질환분류'},
            {field:'rcognDissSeNm',width:100,halign:'center',align:  'left',title:'인정질환구분'},
            {field:'etcCn'        ,width:200,halign:'center',align:  'left',title:'기타내용'},
            {field:'cdOrdr'       ,width:100,halign:'center',align:'center',title:'코드순서', formatter:$.commFormat.number},
            {field:'useYn'        ,width:100,halign:'center',align:'center',title:'사용여부', formatter:$.commFormat.useyn},
            {field:'rgtrNo'       ,width:120,halign:'center',align:'center',title:'등록자번호'},
            {field:'regDate'      ,width:120,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfrNo'       ,width:120,halign:'center',align:'center',title:'수정자번호'},
            {field:'mdfDate'      ,width:120,halign:'center',align:'center',title:'수정일자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
    // 상위코드 EasyUI Combo Tree
	let P_UPPER = $('#upSckwndCd').combotreegrid({
		url: getUrl('/sys/code/getListSckwnd.do'),
        idField:'id',
        treeField:'sckwndNm',
        panelWidth:500,
        columns:[[
            {field:'sckwndNm',title:'코드명칭',width:400},
            {field:'sckwndCd',title:'질병코드',width:100}
        ]]
	});
	// 인정질환구분 콤보박스
	$('#rcognDissSeCd').appComboBox({
		params: {upCdId: CODE.RCOGNDISS},
		init:    COMBO.INIT_NOT
	});
    // 사용여부 라디오박스	
	$('#appUseYn').appSelectBox({
		form: 'radio',
		name: 'useYn',
		type: 'static',
		rows: STORE['USE_YN']
	});

    //========================================================//
    // FORM VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            sckwndCd     : {required: true},
            upSckwndCd   : {required: function() {
							if (P_RFORM.find('[name="mode"]').val() == MODE.INSERT)
								return true;
							return false;
						   }},
            sckwndNm     : {required: true},
            dissClCd     : {required: true},
            rcognDissSeCd: {required: true},
            useYn        : {required: true}
        },
        // 검증메세지 정의
        messages: {
            sckwndCd     : {required: '질병코드는 필수 입력 사항입니다.'},
            upSckwndCd   : {required: '상위코드는 필수 선택 사항입니다.'},
            sckwndNm     : {required: '상병명은 필수 입력 사항입니다.'},
            dissClCd     : {required: '질환분류는 필수 입력 사항입니다.'},
            rcognDissSeCd: {required: '인정질환구분은 필수 선택 사항입니다.'},
            useYn        : {required: '사용여부는 필수 선택 사항입니다.'}
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 질병코드관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.treegrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.treegrid('options')['url'] = getUrl('/sys/code/getListSckwnd.do');
        // 검색폼 그리드 검색
        P_GRID.treegrid('load', obj);
        
        return false;
    }
    
    // 질병코드관리 검색리셋
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

    // 질병코드관리 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 질병코드관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 제목변경
		P_HEADER.html(P_TITLE+'등록');
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 상위코드 명칭 리셋
		$('#orgUpSckwndCdNm').html('');
        // 상위코드콤보 표시
		$('#upSckwndArea').show();
        // 상위코드콤보 리로드
		P_UPPER.combotreegrid('grid').treegrid('reload');		
        // 코드입력값 입력활성화
		$('#sckwndCd').prop('readonly', false);
		// 등록기본값 정의
        $.formUtil.toForm({
			mode   : MODE.INSERT,// 등록모드
			useYn  : 'Y'         // 사용여부(Y)
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();
        return false;
    }

    // 질병코드관리 수정하기
    //--------------------------------------------------------//
    function doUpdate(row) {
        var params = {
            sckwndCd  : row['sckwndCd'  ],
            upSckwndCd: row['upSckwndCd']
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/sys/code/getSckwnd.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 수정모드 정의
					data['mode'         ] = MODE.UPDATE;
					data['orgUpSckwndCd'] = data['upSckwndCd'];
					if (data['upSckwndCd'] == ROOT_CODE['code'])
						data['upSckwndNm'] =  ROOT_CODE['text'];
                    // 제목변경
					P_HEADER.html(P_TITLE+'수정');
                    // 폼데이터 셋팅
                    $.formUtil.toForm(data, P_RFORM);
					// 상위코드 명칭 표시
					$('#orgUpSckwndCdNm').html('['+data['upSckwndCd']+'] - '+data['upSckwndNm']);
                    // 상위코드 콤보 숨김
					$('#upSckwndArea').hide();
                    // 코드입력값 입력비활성화
					$('#sckwndCd').prop('readonly', true);
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                }
            }
        );
        return false;
    }

    // 질병코드관리 저장하기
    //--------------------------------------------------------//
    function doSave() {

        // FORM VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;
        
        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

        $.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/code/saveSckwnd.do'), 
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

    // 질병코드관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject( 
            P_RFORM, ['sckwndCd','upSckwndCd']);

        if ($.commUtil.empty(params['sckwndCd'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }
        
    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
	        $.formUtil.toForm({mode:MODE.REMOVE}, P_RFORM);
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/code/saveSckwnd.do'), 
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
	
    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//

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
    
    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'), $('#btnSearch'));

    // 등록폼 초기화
    doRegist();
    // 검색처리
    doSearch();
});
