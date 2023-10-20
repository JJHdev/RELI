/**
*******************************************************************************
*** 파일명 : listMfcmm.js
*** 설명글 : 위원관리 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 1.0         2022.11.28    JWH
*** 1.0         2023.01.09    LSH 설계변경에 따른 재작업
*******************************************************************************
**/

// 탭 모듈 배열
let P_TABS   = [];
// 현재 선택한 탭 INDEX
let P_INDEX  = 0;
// 현재 선택한 행 데이터
let P_SELECT = false;

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
	        idField: 'mfcmmNo',
	        // 칼럼정의
	        columns: [[
	            {field:'mfcmmNm'      ,width:100,halign:'center',align:'center',title:'위원명'},
	            {field:'mfcmmOgdpNm'  ,width:150,halign:'center',align:'center',title:'소속'},
	            {field:'mfcmmRspofcNm',width:150,halign:'center',align:'center',title:'직책'},
	            {field:'mfcmmRlmNm'   ,width:150,halign:'center',align:'center',title:'분야'},
				{field:'viewBtn'      ,width:100,halign:'center',align:'center',title:'임기이력',
				 	formatter: function(v,r,ridx) {
						return '<a href="javascript:void(0);" class="app-docbtn-on app-btn-popup" title="임기이력" data-index="'+ridx+'" data-no="'+r['mfcmmNo']+'"></a>';
					}
				},
	        ]],
	        // 행선택시 상세조회
	        onSelect: this.doLoad,
			// 행선택한 정보가 있을 경우 해당정보 상세조회
			onLoadSuccess: function() {
				if (C_LIST.SROW) {
					C_LIST.GRID.datagrid('selectRecord', C_LIST.SROW);
				}
                let p = C_LIST.GRID.datagrid('getPanel');
                p.find('.app-btn-popup').each(function() {
					$(this).bind('click', C_LIST.doOpenView);
                });
			}
	    });

	    //========================================================//
	    // FORM ELEMENTS 정의
	    //--------------------------------------------------------//
		// 위원회 콤보박스
		$('#srchCmitSeCd').combobox({
			url: getUrl('/com/cmm/getComboCode.do'),
			queryParams: {upCdId: CODE.CMITSE},
			loadFilter: function(data) {
				data.unshift(COMBO.INIT_ALL);
				return data;
			}
		});
		// 분야 콤보박스
		$('#srchMfcmmRlmCd').combobox({
			url: getUrl('/com/cmm/getComboCode.do'),
			queryParams: {upCdId: CODE.MFCMMRLM},
			loadFilter: function(data) {
				data.unshift(COMBO.INIT_ALL);
				return data;
			}
		});
	    // 검색버튼 클릭시 이벤트 처리
	    $('#btnSearch').bind('click', this.doSearch);
	    // 리셋버튼 클릭시 이벤트처리
	    $('#btnReset' ).bind('click', this.doReset);
		// 엑셀다운로드버튼 클릭시 이벤트처리
	    $('#btnExcel').bind('click', this.doExcel);
	
	    // 위원명 검색어 입력 엔터 이벤트 처리
	    bindEnter($('#srchMfcmmNm'), $('#btnSearch'));

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
        C_LIST.GRID.datagrid('options')['url'] = getUrl('/adm/cmit/getListMfcmm.do');
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
            getUrl('/adm/cmit/downMfcmmExcel.do'), 
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
		C_LIST.SROW = row['mfcmmNo'];

		// 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/cmit/getMfcmm.do'), 
			{mfcmmNo : row['mfcmmNo']},
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
	},
	// 그리드에서 임기이력 버튼클릭시 팝업 오픈
    //-------------------------------//
	doOpenView: function() {
		// 그리드 행선택 처리
		C_LIST.GRID.datagrid('selectRow', $(this).data('index'));
		$.commModal.loadView(
			'임기 이력 조회 결과', 
			getUrl('/adm/cmit/modalMfcmmTenure.do')+'?mfcmmNo='+$(this).data('no'), 
			{sizeType:'large'}
		);
        return false;
	},
};

//============================================================================//
// [1] 위원정보 탭 기능정의 
//----------------------------------------------------------------------------//
const C_MFCMM  = {
	FORM : false,
	INIT : false,
	DATA : false,
	// [탭필수함수] 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#mfcmmForm');
		
	    // FORM VALIDATION RULE 정의
	    this.FORM.validate({
	        debug: false,
	        onsubmit: false,
	        onfocusout: false,
	        // 검증룰 정의
	        rules: {
	            mfcmmNm      : {required: true},
	            mfcmmOgdpNm  : {required: true},
	            mfcmmRspofcNm: {required: true},
				mfcmmRlmCd   : {required: true},
				mfcmmEmlAddr : {email: true, maxlength: 100},
				mfcmmTelno   : {phone: true}
	        },
	        // 검증메세지 정의
	        messages: {
	            mfcmmNm      : {required: '위원명은 필수 입력 사항입니다.'},
	            mfcmmOgdpNm  : {required: '소속은 필수 입력 사항입니다.'},
	            mfcmmRspofcNm: {required: '직책은 필수 입력 사항입니다.'},
				mfcmmRlmCd   : {required: '분야는 필수 선택 사항입니다.'},
				mfcmmEmlAddr : {
					email: '이메일을 형식에 맞게 입력하세요.', 
					maxlength: $.validator.format('이메일은 {0}자를 초과할 수 없습니다.')
				},
				mfcmmTelno   : {
					phone: '연락처를 형식에 맞게 입력하세요.' 
				}
	        },
	        invalidHandler: validateHandler,
	        errorPlacement: validatePlacement
	    });
		// 분야 콤보박스 생성
		$('#mfcmmRlmCd').appComboBox({
			url: getUrl('/com/cmm/getComboCode.do'),
			params: {upCdId: CODE.MFCMMRLM}
		});
		// 연락처 하이픈삽입 이벤트처리
		bindPhoneHyphen($('#mfcmmTelno'));
	    // 신규등록버튼 클릭시 이벤트처리
	    $('#btnMfcmmRegist').bind('click', this.doRegist);
	    // 저장버튼 클릭시 이벤트 처리
		$('#btnMfcmmSave'  ).bind('click', this.doSave);
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
        $.formUtil.toForm({mode: MODE.INSERT}, this.FORM);
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
	},
	// 신규등록
    //-------------------------------//
	doRegist: function() {
		// 상세조회 리셋
		C_LIST.doClear();
		return false;
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function() {
		let f = C_MFCMM.FORM;
        // FORM VALIDATION 기능 활성화
        if (f.validate().settings)
            f.validate().settings.ignore = false;
        //FORM VALIDATION
        if (f.valid() === false)
            return false;
		
		$.commMsg.confirm("위원정보를 저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            f.ajaxForm({
                url: getUrl('/adm/cmit/saveMfcmm.do'),
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
// 편집형 그리드 탭 공통 함수 영역
//----------------------------------------------------------------------------//
const C_COMMON = {
	// 모든탭에서 사용하는 공통 그리드 포맷
	FORMAT: {
		// 피해지역 콤보박스
		inputBizArea: function(v,r,ridx) {
			return '<select id="bizAreaCd'+ridx+'" name="bizAreaCd" class="app-bizarea app-w80" data-value="'+$.commUtil.nvl(v)+'"></select>';
		},
		// 차수 입력박스
		inputTnrOder: function(v,r) {
			return '<input type="text" name="tenureOder" value="'+$.commUtil.nvl(v)+'" maxlength="10" class="w100"/>';
		},
		// 시작일자 날짜박스
		inputBgDate: function(v,r,ridx) {
			return '<input id="tenureBgngYmd'+ridx+'" name="tenureBgngYmd" value="'+$.commUtil.nvl(v)+'" maxlength="10" class="app-datebox1"/>';
		},
		// 종료일자 날짜박스
		inputEnDate: function(v,r,ridx) {
			return '<input id="tenureEndYmd'+ridx+'" name="tenureEndYmd" value="'+$.commUtil.nvl(v)+'" maxlength="10" class="app-datebox2"/>';
		},
		// 삭제버튼
		formatButton: function(v,r,ridx) {
			return '<a href="javascript:void(0);" data-index="'+ridx+'" class="app-minus app-remove-btn" title="삭제"><i class="fa fa-minus"></i></a>';
		}
	},
	// 초기화
    //-------------------------------//
	doInit: function( TOBJ ) {
		if (TOBJ.INIT)
			return;
			
		let columns = [
            {field:'tenureOder'   ,width:100,halign:'center',align:'center', formatter: this.FORMAT.inputTnrOder,title:'차수'},
            {field:'tenureBgngYmd',width:150,halign:'center',align:'center', formatter: this.FORMAT.inputBgDate ,title:'임기시작일자'},
            {field:'tenureEndYmd' ,width:150,halign:'center',align:'center', formatter: this.FORMAT.inputEnDate ,title:'임기종료일자'},
			{field:'mode'         ,width: 40,halign:'center',align:'center', formatter: this.FORMAT.formatButton,title:'삭제'}
		];
		// 피해지역 항목 포함인 경우
		if (TOBJ.BIZAREA) {
			// 칼럼앞에 추가
			columns.unshift({
				field:  'bizAreaCd',
				width:  150,
				halign: 'center',
				align:  'center',
				title:  '피해지역',
				formatter: this.FORMAT.inputBizArea
			});
		}
		// 상세폼 정의
		TOBJ.FORM = $(TOBJ.KEYS.FORM);
		// 그리드 정의
		TOBJ.GRID = $(TOBJ.KEYS.GRID).datagrid({
			// 패널 영역 맞춤
			fit: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',    
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // KEY값필드
	        idField: 'tenureNo',
	        // 칼럼정의
	        columns: [columns],
			onLoadSuccess: function(data) {
				$.each(data.rows, function(i) {
					data.rows[i]['mode'] = MODE.UPDATE;
				});
				// 그리드 이벤트 바인딩
				TOBJ.doBindEvent();
			}
		});
	    // 추가버튼 클릭시 이벤트 처리
		$(TOBJ.KEYS.APPEND).bind('click', TOBJ.doAppend);
	    // 저장버튼 클릭시 이벤트 처리
		$(TOBJ.KEYS.SAVE  ).bind('click', TOBJ.doSave);
		// 초기화 완료
		TOBJ.INIT = true;
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function( TOBJ ) {
		// 상세조회 데이터 리셋
		TOBJ.DATA = false;
		// 폼 초기화
		TOBJ.FORM.form('reset');
		// 기본값 정의
        $.formUtil.toForm({cmitSeCd: TOBJ.CODE}, TOBJ.FORM);
		// 선택된 항목 CLEAR
		TOBJ.GRID.datagrid('clearSelections');
		TOBJ.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 추가버튼 감춤
		$(TOBJ.KEYS.APPEND).hide();
		// 저장버튼 감춤
		$(TOBJ.KEYS.SAVE).hide();
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function( TOBJ , data ) {
		if (!data)
			return;
		// 기본값 정의
        $.extend(data, {
			mode:     MODE.UPDATE, 
			cmitSeCd: TOBJ.CODE
		});
		// 데이터 정의
		TOBJ.DATA = data;
		// 폼데이터 로드
		TOBJ.FORM.form('load', data);
		// 추가버튼 표시
		$(TOBJ.KEYS.APPEND).show();
		// 저장버튼 표시
		$(TOBJ.KEYS.SAVE).show();
		// 목록 조회
		TOBJ.doSearch();
	},
	// 목록조회
    //-------------------------------//
	doSearch: function( TOBJ ) {
		// 삭제ROWS 리셋
		TOBJ.ROWS = [];
		let obj = TOBJ.FORM.serializeObject();
		// 선택된 항목 CLEAR
		TOBJ.GRID.datagrid('clearSelections');
		// 목록 로드
       	TOBJ.GRID.datagrid('options')['url'] = getUrl('/adm/cmit/getListMfcmmTenure.do');
       	TOBJ.GRID.datagrid('load', {
			mfcmmNo  : obj['mfcmmNo' ],
			cmitSeCd : obj['cmitSeCd']
		});
	},
	// 저장데이터 반환
    //-------------------------------//
	getSaveData: function( TOBJ ) {
		let f = TOBJ.FORM;
		let g = TOBJ.GRID;
		//목록 데이터 정의
		let rows = [];
		$.each(g.datagrid('getRows'), function(i,r) {
			let row = {
				tenureOder:    f.find('input[name="tenureOder"   ]').eq(i).val(),
				tenureBgngYmd: f.find('input[name="tenureBgngYmd"]').eq(i).val(),
				tenureEndYmd:  f.find('input[name="tenureEndYmd" ]').eq(i).val(),
				tenureNo:      r['tenureNo'],
				mode:          r['mode'    ]
			};
			// 피해지역 항목 포함인 경우
			if (TOBJ.BIZAREA) {
				row['bizAreaCd'] = f.find('select[name="bizAreaCd"]').eq(i).val();
			}
			rows.push(row);
		});
		//저장할 데이터 가져오기
		let fobj = f.serializeObject();
		return {
			mode      : fobj['mode'    ],
			cmitSeCd  : fobj['cmitSeCd'],
			mfcmmNo   : fobj['mfcmmNo' ],
			saveList  : rows,
			removeList: TOBJ.ROWS
		};
	},
	// 데이터 검증하기
    //-------------------------------//
	doValidate: function( TOBJ, data ) {
		
		if (data['saveList'  ].length == 0 &&
			data['removeList'].length == 0) {
			$.commMsg.alert('저장할 내용이 없습니다.');
			return false;
		}
		let valid = true;
		$.each(data['saveList'], function(i,r) {
			let no = '['+(i+1)+'번] ';
			
			// 피해지역 항목 포함인 경우
			if (TOBJ.BIZAREA) {
				if ($.commUtil.empty(r['bizAreaCd'])) {
					$.commMsg.alert( no+'피해지역은 필수 선택 사항입니다.');
					valid = false; 
					return false;
				} 
			}
			if ($.commUtil.empty(r['tenureOder'])) {
				$.commMsg.alert( no+'임기차수는 필수 입력 사항입니다.');
				valid = false; 
				return false;
			} 
			if ($.commUtil.empty(r['tenureBgngYmd'])) {
				$.commMsg.alert( no+'임기시작일자는 필수 입력 사항입니다.');
				valid = false; 
				return false;
			}
			if ($.validateUtil.dateHyphen(r['tenureBgngYmd']) != 'TRUE') {
				$.commMsg.alert( no+'임기시작일자를 날짜형식에 맞게 입력하셔야 합니다.');
				valid = false; 
				return false;
			} 
			if (!$.commUtil.empty(r['tenureEndYmd']) &&
		     	$.validateUtil.dateHyphen(r['tenureEndYmd']) != 'TRUE') {
				$.commMsg.alert( no+'임기종료일자를 날짜형식에 맞게 입력하셔야 합니다.');
				valid = false; 
				return false;
			} 
			if (!$.commUtil.empty(r['tenureBgngYmd']) &&
				!$.commUtil.empty(r['tenureEndYmd' ]) &&
				r['tenureBgngYmd'] > r['tenureEndYmd']) {
				$.commMsg.alert( no+'임기종료일자를 임기시작일자보다 이후로 입력하셔야 합니다.');
				valid = false; 
				return false;
			}
			return true;
		});
		return valid;
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function( TOBJ ) {

		//저장할 데이터 가져오기
		let data = TOBJ.getSaveData();
		//검증
		if (TOBJ.doValidate(data) === false)
			return false;
		
		$.commMsg.confirm(TOBJ.TITLE+" 정보를 저장하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/cmit/saveMfcmmTenure.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							TOBJ.doSearch();
						});
					});
                },
				function() {
					TOBJ.doSearch();
				}
            );
		});
        return false;
    },
	// 행 추가하기
    //-------------------------------//
	doAppend: function( TOBJ ) {
		let g = TOBJ.GRID;
		// 선택된 항목 CLEAR
		g.datagrid('clearSelections');
		// 그리드 행추가
		g.datagrid('appendRow', {
			mode          : MODE.INSERT,        
			bizAreaCd     : '',
			tenureOder    : '',
			tenureBgngYmd : '',
			tenureEndYmd  : '',
		});
		let idx = g.datagrid('getRows').length-1;
		// 숫자입력 이벤트 바인딩
		TOBJ.doBindEvent(idx);
        return false;
    },
	// 행 삭제하기
    //-------------------------------//
	doRemove: function( TOBJ, thisObj ) {
		let g     = TOBJ.GRID;
		let p     = g.datagrid('getPanel');
		let index = thisObj.data('index');
		let row   = g.datagrid('getRows')[index];
		if (row == null) {
			$.commMsg.alert('삭제할 항목을 선택하세요.');
			return false;
		}
		if (row['mode'] == MODE.UPDATE) {
			// 삭제ROWS 추가
			TOBJ.ROWS.push(row);
		}
		// 행 삭제
		g.datagrid('deleteRow', index);

		// 삭제후 다른 삭제버튼의 index값을 변경한다.
		p.find('a.app-remove-btn').each(function(i) {
			$(this).data('index', i);
		});
        return false;
    },
	// 그리드 이벤트 바인딩
    //-------------------------------//
	doBindEvent: function( TOBJ, index ) {
		let g = TOBJ.GRID;
		let p = g.datagrid('getPanel');
		// 숫자만 입력 이벤트
		p.find('input.app-number').each(function() {
			$(this).inputmask("numeric", {allowMinus: false, digits: 3, max: 100});
		});
		if (index >= 0) {
			// 피해지역 콤보박스 생성
			p.find('select.app-bizarea').eq(index).appComboBox({url: getUrl('/com/cmm/getComboBizMng.do')});
			// 임기시작일자 날짜박스 생성
			p.find('input.app-datebox1').eq(index).datebox({width:150});
			// 임기종료일자 날짜박스 생성
			p.find('input.app-datebox2').eq(index).datebox({width:150});
		    // 임기시작일자 입력박스 하이픈(-) 자동삽입 이벤트
			bindDateHyphen( p.find('input.app-datebox1').eq(index).datebox('textbox') );
		    // 임기종료일자 입력박스 하이픈(-) 자동삽입 이벤트
			bindDateHyphen( p.find('input.app-datebox2').eq(index).datebox('textbox') );
		}
		else {
			p.find('select.app-bizarea').each(function() {
				// 피해지역 콤보박스 생성
				$(this).appComboBox({url: getUrl('/com/cmm/getComboBizMng.do')});
			});
			p.find('input.app-datebox1').each(function() {
				// 임기시작일자 날짜박스 생성
				$(this).datebox({width:150});
			    // 임기시작일자 입력박스 하이픈(-) 자동삽입 이벤트
				bindDateHyphen( $(this).datebox('textbox') );
			});
			p.find('input.app-datebox2').each(function() {
				// 임기종료일자 날짜박스 생성
				$(this).datebox({width:150});
			    // 임기종료일자 입력박스 하이픈(-) 자동삽입 이벤트
				bindDateHyphen( $(this).datebox('textbox') );
			});
		}
		p.find('a.app-remove-btn').each(function() {
			// 삭제버튼 클릭 이벤트
			$(this).unbind('click').bind('click', TOBJ.doRemove);
		});
	},
};

//============================================================================//
// [2] 전문위원회 탭 기능정의 
//----------------------------------------------------------------------------//
const C_EXPERT = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 위원회 구분코드
	CODE : CODE.CMIT_SE_CD.EXPERT,
	// 피해지역 항목존재여부
	BIZAREA: true,
	// 탭 명칭
	TITLE : '전문위원회',  
	// KEYS
	KEYS: {
		FORM  : '#expertForm',
		GRID  : '#expertGrid',
		APPEND: '#btnExpertAppend',
		SAVE  : '#btnExpertSave'
	},
	// 삭제행목록
	ROWS : [],
	// [탭필수함수] 초기화
    //-------------------------------//
	doInit: function() {
		C_COMMON.doInit( C_EXPERT );
	},
	// [탭필수함수] 데이터 리셋
    //-------------------------------//
	doReset: function() {
		C_COMMON.doReset( C_EXPERT );
	},
	// [탭필수함수] 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		C_COMMON.doLoad( C_EXPERT, data );
	},
	// 목록조회
    //-------------------------------//
	doSearch: function() {
		C_COMMON.doSearch( C_EXPERT );
	},
	// 저장데이터 반환
    //-------------------------------//
	getSaveData: function() {
		return C_COMMON.getSaveData( C_EXPERT );
	},
	// 데이터 검증하기
    //-------------------------------//
	doValidate: function(data) {
		return C_COMMON.doValidate( C_EXPERT, data );
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function() {
		return C_COMMON.doSave( C_EXPERT );
    },
	// 행 추가하기
    //-------------------------------//
	doAppend: function() {
		return C_COMMON.doAppend( C_EXPERT );
    },
	// 행 삭제하기
    //-------------------------------//
	doRemove: function() {
		return C_COMMON.doRemove( C_EXPERT, $(this) );
    },
	// 그리드 이벤트 바인딩
    //-------------------------------//
	doBindEvent: function(index) {
		C_COMMON.doBindEvent( C_EXPERT, index );
	},
};

//============================================================================//
// [3] 심의위원회 탭 기능정의 
//----------------------------------------------------------------------------//
const C_REVIEW = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 위원회 구분코드
	CODE : CODE.CMIT_SE_CD.REVIEW,
	// 피해지역 항목존재여부
	BIZAREA: false,
	// 탭 명칭
	TITLE : '심의위원회',  
	// KEYS
	KEYS: {
		FORM  : '#reviewForm',
		GRID  : '#reviewGrid',
		APPEND: '#btnReviewAppend',
		SAVE  : '#btnReviewSave'
	},
	// 삭제행목록
	ROWS : [],
	// [탭필수함수] 초기화
    //-------------------------------//
	doInit: function() {
		C_COMMON.doInit( C_REVIEW );
	},
	// [탭필수함수] 데이터 리셋
    //-------------------------------//
	doReset: function() {
		C_COMMON.doReset( C_REVIEW );
	},
	// [탭필수함수] 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		C_COMMON.doLoad( C_REVIEW, data );
	},
	// 목록조회
    //-------------------------------//
	doSearch: function() {
		C_COMMON.doSearch( C_REVIEW );
	},
	// 저장데이터 반환
    //-------------------------------//
	getSaveData: function() {
		return C_COMMON.getSaveData( C_REVIEW );
	},
	// 데이터 검증하기
    //-------------------------------//
	doValidate: function(data) {
		return C_COMMON.doValidate( C_REVIEW, data );
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function() {
		return C_COMMON.doSave( C_REVIEW );
    },
	// 행 추가하기
    //-------------------------------//
	doAppend: function() {
		return C_COMMON.doAppend( C_REVIEW );
    },
	// 행 삭제하기
    //-------------------------------//
	doRemove: function() {
		return C_COMMON.doRemove( C_REVIEW, $(this) );
    },
	// 그리드 이벤트 바인딩
    //-------------------------------//
	doBindEvent: function(index) {
		C_COMMON.doBindEvent( C_REVIEW, index );
	},
};

//============================================================================//
// [4] 심사위원회 탭 기능정의 
//----------------------------------------------------------------------------//
const C_JUDGE  = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 위원회 구분코드
	CODE : CODE.CMIT_SE_CD.JUDGE,
	// 피해지역 항목존재여부
	BIZAREA: false,
	// 탭 명칭
	TITLE : '심사위원회',  
	// KEYS
	KEYS: {
		FORM  : '#judgeForm',
		GRID  : '#judgeGrid',
		APPEND: '#btnJudgeAppend',
		SAVE  : '#btnJudgeSave'
	},
	// 삭제행목록
	ROWS : [],
	// [탭필수함수] 초기화
    //-------------------------------//
	doInit: function() {
		C_COMMON.doInit( C_JUDGE );
	},
	// [탭필수함수] 데이터 리셋
    //-------------------------------//
	doReset: function() {
		C_COMMON.doReset( C_JUDGE );
	},
	// [탭필수함수] 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		C_COMMON.doLoad( C_JUDGE, data );
	},
	// 목록조회
    //-------------------------------//
	doSearch: function() {
		C_COMMON.doSearch( C_JUDGE );
	},
	// 저장데이터 반환
    //-------------------------------//
	getSaveData: function() {
		return C_COMMON.getSaveData( C_JUDGE );
	},
	// 데이터 검증하기
    //-------------------------------//
	doValidate: function(data) {
		return C_COMMON.doValidate( C_JUDGE, data );
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function() {
		return C_COMMON.doSave( C_JUDGE );
    },
	// 행 추가하기
    //-------------------------------//
	doAppend: function() {
		return C_COMMON.doAppend( C_JUDGE );
    },
	// 행 삭제하기
    //-------------------------------//
	doRemove: function() {
		return C_COMMON.doRemove( C_JUDGE, $(this) );
    },
	// 그리드 이벤트 바인딩
    //-------------------------------//
	doBindEvent: function(index) {
		C_COMMON.doBindEvent( C_JUDGE, index );
	},
};

$(function() {
	
	P_TABS   = [C_MFCMM, C_EXPERT, C_REVIEW, C_JUDGE];
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
