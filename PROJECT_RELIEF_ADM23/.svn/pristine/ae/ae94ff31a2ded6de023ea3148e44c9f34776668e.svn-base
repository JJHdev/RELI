/**
*******************************************************************************
*** 파일명 : listBioCmitMng.js
*** 설명글 : 살생물제품 위원회관리 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 2.0         2023.01.30    LSH      최초생성
*******************************************************************************
**/
// 탭 모듈 배열
let P_TABS   = [];
// 현재 선택한 탭 INDEX
let P_INDEX  = 0;
// 현재 선택한 행 데이터
let P_SELECT = false;
// 현재 오픈된 팝업 객체 (팝업창에서 사용됨)
let P_POPUP = false;

//============================================================================//
// 검색/목록영역 기능정의 
//----------------------------------------------------------------------------//
const C_LIST = {
	FORM : false,
	GRID : false,
	// 선택된 행 ROW KEY
	SROW : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		this.FORM = $('#searchForm');
	    this.GRID = $('#appGrid').datagrid({
			// 패널영역 맞춤
			fit: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',    
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 페이징처리 여부
	        pagination:true,
	        // 그리드 행번호 표시여부
	        rownumbers:true,
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // 한 페이지 출력수
	        pageSize: 30,
	        // KEY값필드
	        idField: 'cmitMngNo',
	        // 칼럼정의
	        columns: [[
	            {rowspan:2,field:'dmgeSeNm'   ,width:150,halign:'center',align:'center',title:'구분'},
	            {rowspan:2,field:'cmitSeNm'   ,width:120,halign:'center',align:'center',title:'위원회'},
	            {rowspan:2,field:'cmitOder'   ,width:100,halign:'center',align:'center',title:'차수'},
				{colspan:2,title:'위원회 개최일'},
				{rowspan:2,field:'mfcmmCnt'   ,width: 80,halign:'center',align:'center',title:'위원수'}
			],[
	            {field:'opmtBgngYmd',width:100,halign:'center',align:'center',title:'시작'}, 
	            {field:'opmtEndYmd' ,width:100,halign:'center',align:'center',title:'종료'}
	        ]],
	        // 행선택시 상세조회
	        onSelect: this.doLoad,
			// 행선택한 정보가 있을 경우 해당정보 상세조회
			onLoadSuccess: function() {
				if (C_LIST.SROW) {
					C_LIST.GRID.datagrid('selectRecord', C_LIST.SROW);
				}
			}
	    });

	    //========================================================//
	    // FORM ELEMENTS 정의
	    //--------------------------------------------------------//
		// 위원회 구분 라디오박스
		$('#appSrchCmitSeCd').appSelectBox({
			label:   '위원회 구분',
			form:    'radio',
			name:    'srchCmitSeCd', 
			params:  {upCdId: CODE.BIO_CMITSE}
		});
		$('#appOpmtTermBox').appTermBox({
			label:'개최일자',
			stName:'srchOpmtStdt',
			enName:'srchOpmtEndt'
		});

	    // 검색버튼 클릭시 이벤트 처리
	    $('#btnSearch').bind('click', this.doSearch);
	    // 리셋버튼 클릭시 이벤트처리
	    $('#btnReset' ).bind('click', this.doReset);
		// 엑셀다운로드버튼 클릭시 이벤트처리
	    $('#btnExcel').bind('click', this.doExcel);
	
	    // 위원회차수 검색어 입력 엔터 이벤트 처리
	    bindEnter($('#srchCmitOder'), $('#btnSearch'));

		// 초기검색실행
		this.doSearch();
	},
	// 검색
    //-------------------------------//
	doSearch: function() {
		// 검색버튼 클릭인 경우 선택행 리셋
		if ($(this).attr('id')=='btnSearch')
			C_LIST.SROW = false;
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택된 항목 CLEAR
		C_LIST.GRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        C_LIST.GRID.datagrid('options')['url'] = getUrl('/adm/bio/getListBioCmitDmge.do');
        // 검색폼 그리드 검색
        C_LIST.GRID.datagrid('load', C_LIST.FORM.serializeObject());
        return false;
	},
	// 검색리셋
    //-------------------------------//
	doReset: function() {
		// 선택행 리셋
		C_LIST.SROW = false;
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
        // 검색폼 입력데이터 초기화
        C_LIST.FORM.form('reset');
        // 검색처리
        C_LIST.doSearch();
        return false;
	},
	// 엑셀 다운로드
    //-------------------------------//
	doExcel: function() {
        $.formUtil.submitForm(
            getUrl('/adm/bio/downBioCmitDmgeExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
	},
	// 상세리셋
    //-------------------------------//
	doClear: function() {
		// 상세조회 데이터 제거
		P_SELECT = false;
		
		// 모든 탭모듈의 초기화
		$.each(P_TABS, function(i,t) {
			t.doReset();
		});
        return false;
	},
	// 상세조회
    //-------------------------------//
	doLoad: function(index, row) {
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택행 설정
		C_LIST.SROW = row['cmitMngNo'];

		// 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/bio/getBioCmitDmge.do'), 
			{cmitMngNo : row['cmitMngNo']},
            function(result) {
                var data = result.Data;
                if (data) {
					P_SELECT = data;
					// 모든 탭모듈의 데이터 로드
					$.each(P_TABS, function(i,t) {
						t.doLoad( data );
					});
                }
            }
        );
	}
};

//============================================================================//
// [1] 위원회정보 탭 기능정의 
//----------------------------------------------------------------------------//
const C_CMIT  = {
	FORM : false,
	INIT : false,
	DATA : false,
	KEYS : {
		FORM  : '#cmitForm',
		REGIST: '#btnCmitRegist',
		REMOVE: '#btnCmitRemove',
		SAVE  : '#btnCmitSave'
	},
	// [탭필수함수] 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;

		this.FORM = $(this.KEYS.FORM);
		
	    // FORM VALIDATION RULE 정의
	    this.FORM.validate({
	        debug: false,
	        onsubmit: false,
	        onfocusout: false,
	        // 검증룰 정의
	        rules: {
	            cmitSeCd     : {required: true},
	            cmitOder     : {required: true},
				opmtBgngYmd  : {
					required:   true, 
					dateHyphen: true
				},
				opmtEndYmd  : {
					required:   true, 
					dateHyphen: true
				}
	        },
	        // 검증메세지 정의
	        messages: {
	            cmitSeCd     : {required: '위원회 구분은 필수 선택 사항입니다.'},
	            cmitOder     : {required: '위원회 차수는 필수 입력 사항입니다.'},
				opmtBgngYmd  : {
					required:   '위원회 개최시작일자는 필수 입력 사항입니다.', 
					dateHyphen: '위원회 개최시작일자를 형식에 맞게 입력하세요'
				},
				opmtEndYmd  : {
					required:   '위원회 개최종료일자는 필수 입력 사항입니다.', 
					dateHyphen: '위원회 개최종료일자를 형식에 맞게 입력하세요'
				}
	        },
	        invalidHandler: validateHandler,
	        errorPlacement: validatePlacement
	    });
		// 위원회 구분 라디오박스
		$('#appCmitSeCd').appSelectBox({
			form:    'radio',
			name:    'cmitSeCd', 
			params:  {upCdId: CODE.BIO_CMITSE}
		});
	    // 신규등록버튼 클릭시 이벤트처리
	    $(this.KEYS.REGIST).bind('click', this.doRegist);
	    // 저장버튼 클릭시 이벤트 처리
		$(this.KEYS.SAVE  ).bind('click', this.doSave);
	    // 삭제버튼 클릭시 이벤트 처리
		$(this.KEYS.REMOVE).bind('click', this.doRemove);
	    // 위원회 개최시작일자 입력박스 하이픈(-) 자동삽입 이벤트
		bindDateHyphen( $('#opmtBgngYmd').datebox('textbox') );
	    // 위원회 개최종료일자 입력박스 하이픈(-) 자동삽입 이벤트
		bindDateHyphen( $('#opmtEndYmd').datebox('textbox') );
		// 초기화 완료
		this.INIT = true;
	},
	// [탭필수함수] 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 폼 초기화
		this.FORM.form('reset');
		// 등록기본값 정의
        $.formUtil.toForm({
			mode: MODE.INSERT,
			// 피해구분 기본값: 살생물제품피해구제
			dmgeSeCd: CODE.DMGE_SE_CD.BIO
		}, this.FORM);
	    // 삭제버튼 감춤
		$(this.KEYS.REMOVE).hide();
	},
	// [탭필수함수] 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (!data)
			return;
		// 수정모드 처리
		data['mode'] = MODE.UPDATE;
		// 데이터 정의
		this.DATA = data;
		// 폼데이터 로드
		this.FORM.form('load', data);
	    // 삭제버튼 표시
		$(this.KEYS.REMOVE).show();
	},
	// 신규등록
    //-------------------------------//
	doRegist: function() {
		// 상세조회 리셋
		C_LIST.doClear();
		return false;
	},
	// 데이터 삭제
    //-------------------------------//
	doRemove: function() {
		let f = C_CMIT.FORM;
		let d = f.serializeObject();
		if (d['mode'] != MODE.UPDATE) {
			$.commMsg.alert('삭제할 위원회 정보가 선택되지 않았습니다.');
			return false;
		}
		$.commMsg.confirm("정말 위원회 정보를 삭제하시겠습니까?", function() {
			// 삭제모드 설정
			f.find('input[name="mode"]').val(MODE.REMOVE);
            // 등록폼을 AJAX로 저장처리
            f.ajaxForm({
                url: getUrl('/adm/bio/saveBioCmitDmge.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.easyMsg.success(ret, '성공적으로 저장되었습니다.', function() {
                        C_LIST.doSearch();
					});
                }
            }).submit();
		});
        return false;
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function() {
		let f = C_CMIT.FORM;
        // FORM VALIDATION 기능 활성화
        if (f.validate().settings)
            f.validate().settings.ignore = false;
        //FORM VALIDATION
        if (f.valid() === false)
            return false;
		let d = f.serializeObject();
		
		if (!$.commUtil.empty( d['opmtBgngYmd']) &&
			!$.commUtil.empty( d['opmtEndYmd' ]) &&
			d['opmtBgngYmd'] > d['opmtEndYmd' ]) {
			$.commMsg.alert('위원회 개최일자의 종료일자를 시작일자보다 이후로 선택하세요.');
			return false;
		}
		
		$.commMsg.confirm("위원회 정보를 저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            f.ajaxForm({
                url: getUrl('/adm/bio/saveBioCmitDmge.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.easyMsg.success(ret, '성공적으로 저장되었습니다.', function() {
                        C_LIST.doSearch();
					});
                }
            }).submit();
		});
        return false;
	},
};

//============================================================================//
// [2] 위원조회 탭 기능정의 
//----------------------------------------------------------------------------//
const C_MFCMM = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// KEYS
	KEYS: {
		FORM  : '#mfcmmForm',
		GRID  : '#mfcmmGrid',
		REMOVE: '#btnMfcmmRemove',
		REGIST: '#btnMfcmmRegist'
	},
	// 삭제행목록
	ROWS : [],
	// [탭필수함수] 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		// 상세폼 정의
		this.FORM = $(this.KEYS.FORM);
		// 그리드 정의
		this.GRID = $(this.KEYS.GRID).datagrid({
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
	        // 체크박스 선택시 행 선택여부
			checkOnSelect: false,
	        // 행선택시 체크박스 선택여부
			selectOnCheck: false,
	        // 그리드 페이징처리 여부
	        pagination:true,
	        // 그리드 행번호 표시여부
	        rownumbers:true,
	        // 한 페이지 출력수
	        pageSize: 30,
	        // 체크박스 KEY값필드
	        idField: 'tenureNo',
	        // 칼럼정의
	        columns: [[
		        {field:'chckId'       ,checkbox: true},
	            {field:'mfcmmNm'      ,width:100,halign:'center',align:'center',title:'위원명'},
	            {field:'mfcmmOgdpNm'  ,width:150,halign:'center',align:'center',title:'소속'},
	            {field:'mfcmmRlmNm'   ,width:150,halign:'center',align:'center',title:'분야'},
	            {field:'tenureOder'   ,width:100,halign:'center',align:'center',title:'임기차수'},
	        ]]
		});
	    // 선택삭제버튼 클릭시 이벤트 처리
		$(this.KEYS.REMOVE).bind('click', this.doRemove);
	    // 위원등록버튼 클릭시 이벤트 처리
		$(this.KEYS.REGIST).bind('click', this.doRegist);
		// 초기화 완료
		this.INIT = true;
	},
	// [탭필수함수] 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 폼 초기화
		this.FORM.form('reset');
		// 선택된 항목 CLEAR
		this.GRID.datagrid('clearSelections');
		// 체크된 항목 CLEAR
		this.GRID.datagrid('clearChecked');
		// 그리드 데이터 리셋
		this.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 선택삭제버튼 감춤
		$(this.KEYS.REMOVE).hide();
		// 위원등록버튼 감춤
		$(this.KEYS.REGIST).hide();
	},
	// [탭필수함수] 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (!data)
			return;
		// 기본값 정의
        $.extend(data, {mode: MODE.UPDATE});
		// 데이터 정의
		this.DATA = data;
		// 폼데이터 로드
		this.FORM.form('load', data);
		// 목록 조회
		this.doSearch();
		// 선택삭제버튼 표시
		$(this.KEYS.REMOVE).show();
		// 위원등록버튼 표시
		$(this.KEYS.REGIST).show();
	},
	// 목록조회
    //-------------------------------//
	doSearch: function() {
		let c   = C_MFCMM;
		let obj = c.FORM.serializeObject();
		// 선택된 항목 CLEAR
		c.GRID.datagrid('clearSelections');
		// 목록 로드
       	c.GRID.datagrid('options')['url'] = getUrl('/adm/bio/getListBioCmitMng.do');
       	c.GRID.datagrid('load', {cmitMngNo: obj['cmitMngNo']});
	},
	// 선택삭제하기
    //-------------------------------//
	doRemove: function() {
		let f     = C_MFCMM.FORM;
		let rows  = C_MFCMM.GRID.datagrid('getChecked');
		if (rows.length == 0) {
			$.commMsg.alert('삭제할 항목을 하나 이상 선택하세요.');
			return false;
		}
		let data = {
			mode:       MODE.REMOVE,
			cmitMngNo:  f.find('input[name="cmitMngNo"]').val(),
			removeList: rows
		};
		$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/bio/saveBioCmitMng.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 삭제되었습니다.', function() {
							C_LIST.doSearch();
						});
                    });
                }
            );
		});
        return false;
    },
	// 위원등록하기
    //-------------------------------//
	doRegist: function() {
		let r = C_LIST.GRID.datagrid('getSelected');
		if (r == null) {
			$.commMsg.alert('위원회 목록에서 위원등록할 위원회를 선택하세요.');
			return false;
		}
		P_POPUP = $.commModal.loadView(
			r['cmitSeNm']+' 위원 등록', 
			getUrl('/adm/bio/modalBioCmitMng.do')+'?cmitMngNo='+r['cmitMngNo'], 
			{sizeType:'large'}
		);
        return false;
	}
};

$(function() {
	
	P_TABS   = [C_CMIT, C_MFCMM];
	P_INDEX  = 0;
	P_SELECT = false;

	// 상세내역 탭클릭 이벤트
	$.eventUtil.tabClick('.boxWrap', 0, function(elm, index) {
		P_INDEX = index;
		// 탭모듈별 초기화
		P_TABS[index].doInit();
	}, true);
	
	// 목록 초기화
	C_LIST.doInit();
});
