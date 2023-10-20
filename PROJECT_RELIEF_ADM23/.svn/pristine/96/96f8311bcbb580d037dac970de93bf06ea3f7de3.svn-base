/**
******************************************************************************************
*** 파일명 : listRcperLvlh.js
*** 설명글 : 구제급여지급 - 요양생활수당 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2022.12.26    LSH
******************************************************************************************
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
	        idField: 'aplyNo',
	        // 칼럼정의
	        columns: [[
	            {rowspan:2,field:'idntfcId'     ,width:100,halign:'center',align:'center',title:'식별ID'},
	            {rowspan:2,field:'aplyNo'       ,width:100,halign:'center',align:'center',title:'신청번호'},
	            {rowspan:2,field:'sufrerNmMask' ,width:100,halign:'center',align:'center',title:'피해자명'},
				{colspan:2,title:'지급기간'},
				{rowspan:2,field:'lastDmgeGrdCd',width:100,halign:'center',align:'center',title:'피해등급', formatter: $.formatUtil.toGrdNm}
			],[
	            {field:'giveBgngYm',width:100,halign:'center',align:'center',title:'시작', formatter: $.formatUtil.toDotMonth}, 
	            {field:'giveEndYm' ,width:100,halign:'center',align:'center',title:'종료', formatter: $.formatUtil.toDotMonth}
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
		$('#appAplyTermBox').appTermBox({
			label:'신청일자',
			stName:'srchAplyStdt',
			enName:'srchAplyEndt'
		});
		$('#appRcptTermBox').appTermBox({
			label:'접수일자',
			stName:'srchRcptStdt',
			enName:'srchRcptEndt'
		});
		// 신청종류 콤보박스
		$('#srchAplyKnd').combobox({
			url: getUrl('/com/cmm/getComboCode.do'),
			queryParams: {upCdId: CODE.APLYKIND},
			loadFilter: function(data) {
				data.unshift(COMBO.INIT_ALL);
				return data;
			}
		});
		// 검색용 피해지역/사업차수/조사차수 단계형 콤보박스 정의
		let P_SEARCH_COMBO = {
			areaCombo: false,
			oderCombo: false,
			exmnCombo: false,
			doInit: function() {
				let cmp = this;
				// 피해지역 콤보
				this.areaCombo = $('#srchBizArea').combobox({
					url: getUrl('/com/cmm/getComboBizMng.do'),
					onChange: function() {
						cmp.doClearExmn();
						cmp.doClearOder();
					},
			        onLoadSuccess: function() {
						cmp.doClearExmn();
						cmp.doClearOder();
			        },
					prompt: '피해지역 선택',
					iconWidth: 22,
			        icons:[{
			            iconCls:'icon-clear',
			            handler: function() {
			                cmp.areaCombo.combobox('clear');
			            }
			        }]
				});
				// 사업차수 콤보
				this.oderCombo = $('#srchBizOder').combobox({
					url: getUrl('/com/cmm/getComboBizOder.do'),
					onBeforeLoad: function(param){
						param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
					},
					onChange: function() {
						cmp.doClearExmn();
					},
			        onLoadSuccess: function() {
						cmp.doClearExmn();
			        },
					prompt: '사업차수 선택',
					iconWidth: 22,
			        icons:[{
			            iconCls:'icon-clear',
			            handler: function() {
			                cmp.oderCombo.combobox('clear');
			            }
			        }]
				});
				// 조사차수 콤보
				this.exmnCombo = $('#srchExmnOder').combobox({
					url: getUrl('/com/cmm/getComboMnsvyOder.do'),
					onBeforeLoad: function(param){
						param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
						param['bizOder'  ] = cmp.oderCombo.combobox('getValue');
					},
					prompt: '조사차수 선택',
					iconWidth: 22,
			        icons:[{
			            iconCls:'icon-clear',
			            handler: function() {
			                cmp.exmnCombo.combobox('clear');
			            }
			        }]
				});
			},
			doClearOder: function() {
				P_SEARCH_COMBO.oderCombo.combobox('clear');
				P_SEARCH_COMBO.oderCombo.combobox('unselect');
				P_SEARCH_COMBO.oderCombo.combobox('reload');
			},
			doClearExmn: function() {
				P_SEARCH_COMBO.exmnCombo.combobox('clear');
				P_SEARCH_COMBO.exmnCombo.combobox('unselect');
				P_SEARCH_COMBO.exmnCombo.combobox('reload');
			}
		};
		
		// 피해지역/사업차수/조사차수 단계콤보 초기화
		P_SEARCH_COMBO.doInit();

	    // 검색버튼 클릭시 이벤트 처리
	    $('#btnSearch').bind('click', this.doSearch);
	    // 리셋버튼 클릭시 이벤트처리
	    $('#btnReset' ).bind('click', this.doReset);

	    // 등록양식 다운로드버튼 클릭시 이벤트처리
	    $('#btnRcpForm').bind('click', this.doDownForm);
	    // 지급일자 일괄등록버튼 클릭시 이벤트처리
	    $('#btnRcpLoad').bind('click', this.doLoadExcel);
		// 엑셀다운로드버튼 클릭시 이벤트처리
	    $('#btnRcpExcel').bind('click', this.doDownExcel);
	
	    // 검색어 입력 엔터 이벤트 처리
	    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
	    bindEnter($('#srchAplcntNm'), $('#btnSearch'));
		// 2023.01.06 식별ID 검색어 입력 엔터 이벤트 처리
		bindEnter($('#srchIdntfcId'), $('#btnSearch'));

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
        C_LIST.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListRcperLvlh.do');
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
	// 등록양식 다운로드
    //-------------------------------//
	doDownForm: function() {
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: {},
			url   : getUrl("/adm/exmn/downRcperLvlhForm.do")
		});
		return false;
	},
	// 지급일자 일괄등록 (엑셀로드)
    //-------------------------------//
	doLoadExcel: function() {
		// 파일업로드 팝업 오픈
		popup.openUpload({
			// 업로드 URL
			url: getUrl("/adm/exmn/loadRcperLvlhDtls.do"),
			// 허용가능 확장자
			extensions: ['xlsx','xls'],
			// 팝업제목
			title: '지급일자 일괄등록 엑셀업로드',
			// 결과처리
			success: function(ret, dialog) {
				$.ajaxUtil.result(ret, function() {
					$.commMsg.alert('성공적으로 등록되었습니다.', function() {
						C_LIST.doSearch();
						dialog.close();
					});
				});
            }
		});
		return false;
	},
	// 엑셀 다운로드
    //-------------------------------//
	doDownExcel: function() {
        $.formUtil.submitForm(
            getUrl('/adm/exmn/downRcperLvlhExcel.do'), 
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
		let params = {
			bizAreaCd : row['bizAreaCd'],
			bizOder   : row['bizOder'  ],
			exmnOder  : row['exmnOder' ],
			aplyNo    : row['aplyNo'   ]
		};
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택행 설정
		C_LIST.SROW = row['aplyNo'];

		// 요양생활수당 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getRcperLvlh.do'), 
			params,
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
};
//============================================================================//
// [1] 피해등급 산정 탭 기능정의 
//----------------------------------------------------------------------------//
const C_GRD = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 점수변경 여부 (점수변경시 등급재산출)
	CHNG : false,
	// 삭제 ROWS
	ROWS : [],
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
			
		let P_FORMAT = {
			inputDiss: function(v,r,ridx) {
				return '<select id="dissKndCd'+ridx+'" name="dissKndCd" class="app-dissknd app-w80" data-value="'+$.commUtil.nvl(v)+'"></select>';
			},
			inputText: function(v,r,ridx) {
				return '<input type="hidden" name="dissKndNm" value="'+$.commUtil.nvl(r['dissKndNm'])+'"><span id="s_dissKndNm'+ridx+'" class="s_dissKndNm">'+v+'</span>';
			},
			inputScre: function(v,r) {
				return '<input type="text" name="svrtyScre" value="'+$.commUtil.nvl(r['svrtyScre'])+'" maxlength="10" style="width:100px" class="app-score app-r"/>';
			},
			formatBtn: function(v,r,ridx) {
				return '<a href="javascript:void(0);" data-index="'+ridx+'" class="app-minus app-remove-btn" title="삭제"><i class="fa fa-minus"></i></a>';
			}
		};
		this.FORM = $('#grdForm');
		this.GRID = $('#grdGrid').datagrid({
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
	        idField: 'dissKndCd',
	        // 칼럼정의
	        columns: [[
	            {field:'dissKndCd',width:100,halign:'center',align:'center', title:'상병코드', formatter: P_FORMAT.inputDiss},
				{field:'dissKndNm',width:300,halign:'center',align:'left'  , title:'질환명', formatter: P_FORMAT.inputText},
				{field:'svrtyScre',width:150,halign:'center',align:'center', title:'중증도 점수', formatter: P_FORMAT.inputScre},
				{field:'mode'     ,width: 40,halign:'center',align:'center', title:'삭제', formatter: P_FORMAT.formatBtn}
	        ]],
			onLoadSuccess: function(data) {
				$.each(data.rows, function(i) {
					data.rows[i]['mode'] = MODE.UPDATE;
				});
				// 그리드 이벤트 바인딩
				C_GRD.doBindEvent();
			}
		});
		// 기준년도 콤보박스
		$('#dmgeGrdYr').appComboBox({
			type:'static', 
			init: {code:'', text:'기준년도 선택'},
			rows: STORE.getYears(0, $.formatUtil.toYear),
			change: function() {
				C_GRD.CHNG = true;
				return true;
			}
		});
	    // +버튼 클릭시 이벤트처리
	    $('#btnAppend').bind('click', this.doAppend);
	    // 등급산출버튼 클릭시 이벤트처리
	    $('#btnProduce').bind('click', this.doProduce);
	    // 저장버튼 클릭시 이벤트 처리
		$('#btnGrdSave').bind('click', this.doSave);
		// 초기화 완료
		this.INIT = true;
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 그리드 데이터 리셋
		this.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 변경여부 리셋
		this.CHNG = false;
		// 삭제ROWS 리셋
		this.ROWS = [];
		// 폼 초기화
		this.FORM.form('reset');
		$('#lastDmgeGrdNm' ).html('&nbsp;');
		$('#lastDmgeScreNm').html('&nbsp;');
		$('#lastDmgeGrdNm' ).removeClass('app-red');
		$('#lastDmgeScreNm').removeClass('app-red');
		// 추가버튼 감춤
		$('#btnAppend' ).hide();
		// 등급산출버튼 감춤
		$('#btnProduce').hide();
		// 저장버튼 감춤
		$('#btnGrdSave').hide();
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
			$('#lastDmgeGrdNm' ).html($.formatUtil.toGrdNm  (data['lastDmgeGrdCd']));
			$('#lastDmgeScreNm').html($.formatUtil.toScoreNm(data['lastDmgeScre' ]));
	        // 목록조회
			this.doSearch({
				bizAreaCd : this.DATA['bizAreaCd'],
				bizOder   : this.DATA['bizOder'  ],
				exmnOder  : this.DATA['exmnOder' ],
				aplyNo    : this.DATA['aplyNo'   ]
			});
			// 추가버튼 표시
			$('#btnAppend' ).show();
			// 등급산출버튼 표시
			$('#btnProduce').show();
			// 저장버튼 표시
			$('#btnGrdSave').show();
		}
	},
	// 평가등급목록 조회
    //-------------------------------//
	doSearch: function(data) {
        // 목록조회
        this.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListRcperLvlhGrd.do');
        this.GRID.datagrid('load', data);
	},
	// 피해등급 저장
    //-------------------------------//
	doSave: function() {
		
		let data  = C_GRD.getSaveData();
		let valid = C_GRD.doValidate(data);
		if (!valid)
			return false;
		
		// 변경된 점수가 있으면
		if (C_GRD.CHNG) {
			$.commMsg.alert('점수가 변경되었거나 항목을 삭제한 경우 저장전 [등급산출]을 수행하셔야 합니다.');
			return false;
		}
		
		// 삭제한 행이 있는 경우
		if (C_GRD.ROWS.length > 0) {
			data['deltList'] = C_GRD.ROWS;
		}
		
		$.commMsg.confirm("최종평가결과를 저장하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveRcperLvlhGrd.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							C_LIST.doSearch();
						});
                    });
                }
            );
		});
        return false;
	},
	// 등급산출 처리
    //-------------------------------//
	doProduce: function() {
		
		let f      = C_GRD.FORM;
		let params = C_GRD.getSaveData();
		let valid  = C_GRD.doValidate(params);
		if (!valid)
			return false;

		$.formUtil.toForm({
			lastDmgeGrdCd: '',
			lastSvrtyScre: ''
		}, f);
		$('#lastDmgeGrdNm' ).html('&nbsp;');
		$('#lastDmgeScreNm').html('&nbsp;');

		// 평가점수 / 최종피해등급 산출
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getRcperLvlhGrd.do'), 
			{
				svrtyScreStr: params['svrtyScreStr'],
				dmgeGrdYr:    params['dmgeGrdYr'   ]
			},
            function(result) {
                var data = result.Data;
                if (data) {
					$.formUtil.toForm({
						lastDmgeGrdCd: data['lastDmgeGrdCd'],
						lastDmgeScre:  data['lastDmgeScre' ]
					}, f);
					$('#lastDmgeGrdNm' ).html($.formatUtil.toGrdNm  (data['lastDmgeGrdCd']));
					$('#lastDmgeScreNm').html($.formatUtil.toScoreNm(data['lastDmgeScre' ]));
					$('#lastDmgeGrdNm' ).addClass('app-red');
					$('#lastDmgeScreNm').addClass('app-red');
					$.commMsg.alert('등급산출이 완료되었습니다.');
					// 변경여부 해제
					C_GRD.CHNG = false;
				}
			}
		);
		return false;
	},
	// 저장데이터 반환
    //-------------------------------//
	getSaveData: function() {
		let f    = this.FORM;
		let fobj = this.FORM.serializeObject();
		let data = {
			mode         : fobj["mode"         ],
			bizAreaCd    : fobj["bizAreaCd"    ],
			bizOder      : fobj["bizOder"      ],
			exmnOder     : fobj["exmnOder"     ],
			aplyNo       : fobj["aplyNo"       ],
			lastDmgeGrdCd: fobj["lastDmgeGrdCd"],
			lastDmgeScre : fobj["lastDmgeScre" ],
			dmgeGrdYr    : fobj["dmgeGrdYr"    ],
			// 2023.01.05 피해등급 필수등록여부
			lastDmgeGrdYn: 'Y',
			svrtyScreStr : '',
			screList     : []
		};
		let rows = this.GRID.datagrid('getRows');
		if (rows && 
			rows.length > 0) {
			$.each(rows, function(i,r) {
				let row = {
					mode:       r['mode'     ],
					dissKndOrg: r['dissKndCd'],
					dissKndCd:  f.find('select[name="dissKndCd"]').eq(i).val(),
					dissKndNm:  f.find('input[name="dissKndNm"]').eq(i).val(),
					svrtyScre:  $.commUtil.nvlFloat(f.find('input[name="svrtyScre"]').eq(i).val())
				};
				data['screList'].push(row);
			});
			data['svrtyScreStr'] = data['screList'].map(function(item) {
				return item['svrtyScre'];
			}).join('|');
		}
		return data;
	},
	// 데이터 검증하기
    //-------------------------------//
    doValidate: function( data ) {
		
		if ($.commUtil.empty(data['dmgeGrdYr'])) {
			$.commMsg.alert('기준년도를 선택하세요.');
			return false;
		}
		let rows = data['screList'];
		if ($.commUtil.empty(rows)) {
			$.commMsg.alert('처리할 항목이 없습니다.');
			return false;
		}
		// 빈값체크
		let non = false;
		// 중복체크
		let dup = false;
		$.each(rows, function(i,r) {
			
			if ($.commUtil.empty(r['dissKndCd'])) {
				non = true;
				return false;
			}
			if (i < rows.length) {
				$.each(rows.slice(i+1), function(j,o) {
					if (r['dissKndCd'] == o['dissKndCd']) {
						dup = r['dissKndCd'];
						return false;
					}
				});
			}
		});
		if (non) {
			$.commMsg.alert('상병코드를 선택하세요.');
			return false;
		}
		if (dup) {
			// 상병코드 중복인 경우
			$.commMsg.alert('상병코드가 중복됩니다. ['+dup+']');
			return false;
		}
		return true;
	},
	// 그리드 이벤트 바인딩
    //-------------------------------//
	doBindEvent: function(index) {
		let g = C_GRD.GRID;
		let p = g.datagrid('getPanel');
		p.find('input.app-score').each(function() {
			// 점수(소수점포함)만 입력 이벤트
			$(this).inputmask("numeric", {allowMinus: false, digits: 3, max: 100});
			// 점수변경시 변경여부 마킹
			$(this).unbind('blur').bind('blur', C_GRD.doChangeScore);
		});
		p.find('a.app-remove-btn').each(function() {
			// 삭제 버튼 클릭 이벤트
			$(this).unbind('click').bind('click', C_GRD.doRemove);
		});
		if (index >= 0) {
			// 상병코드 콤보박스 생성
			C_GRD.doApplyCombo( p.find('select.app-dissknd').eq(index) );
		}
		else {
			p.find('select.app-dissknd').each(function(i) {
				// 상병코드 콤보박스 생성
				C_GRD.doApplyCombo( $(this) );
			});
		}
	},
	// 점수변경시 변경여부 마킹
    //-------------------------------//
	doChangeScore: function() {
		let val = $(this).val();
		let idx = $(this).index();
		let row = C_GRD.GRID.datagrid('getRows')[idx];
		if (row['svrtyScre'] != val) {
			C_GRD.CHNG = true;
		}
	},
	// 상병코드 콤보박스 설정
    //-------------------------------//
	doApplyCombo: function( obj ) {
		obj.appComboBox({
			url: getUrl('/com/cmm/getComboCode.do'),
			params: {upCdId: CODE.DISSKND},
			init: {code:'', text:'선택'},
			mode: 2,
			change: function() {
				let g   = C_GRD.GRID;
				let p   = g.datagrid('getPanel');
				let e   = $(this);
				let row = g.datagrid('getSelected');
				let idx = g.datagrid('getRowIndex', row); 
				let wgt = $(this).data('widget');
				let val = $(this).val();
				if (row['mode'] == MODE.UPDATE) {
					$.commMsg.alert('수정항목은 상병코드 변경이 불가합니다.', function() {
						e.val(row['dissKndCd']);
						return true;
					});
					return false;
				}
				if (val == '') {
					p.find('span.s_dissKndNm'       ).eq(idx).html('');
					p.find('input[name="dissKndNm"]').eq(idx).val('');
				}
				else {
					let sel = wgt.getSelectedRow();
					p.find('span.s_dissKndNm'       ).eq(idx).html(sel['text']);
					p.find('input[name="dissKndNm"]').eq(idx).val(sel['text']);
				}
				return true;
			}
		});
	},
	// 그리드 항목추가
    //-------------------------------//
	doAppend: function() {
		let g = C_GRD.GRID;
		// 선택된 항목 CLEAR
		g.datagrid('clearSelections');
		// 그리드 행추가
		g.datagrid('appendRow', {
			mode     : MODE.INSERT,        
			dissKndCd: '',
			dissKndNm: '',
			svrtyScre: '0'
		});
		let idx = g.datagrid('getRows').length-1;
		// 숫자입력 이벤트 바인딩
		C_GRD.doBindEvent(idx);
        return false;
    },
	// 그리드 항목삭제
    //-------------------------------//
	doRemove: function() {
	
		let g     = C_GRD.GRID;
		let index = $(this).data('index');
		let row   = g.datagrid('getRows')[index];
		if (row == null) {
			$.commMsg.alert('삭제할 항목을 선택하세요.');
			return false;
		}
		if (row['mode'] == MODE.UPDATE) {
			// 삭제ROWS 추가
			C_GRD.ROWS.push(row);
		}
		// 행 삭제
		g.datagrid('deleteRow', index);
		// 삭제시 점수 재계산이 필요함
		C_GRD.CHNG = true;
        return false;
    },
};
//============================================================================//
// [2] 요양생활수당 지급 탭 기능정의 
//----------------------------------------------------------------------------//
const C_RCP = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 지급구분 라디오박스
	RADIO: false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#rcpForm');
		this.GRID = $('#rcpGrid').datagrid({
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
	        // 칼럼정의
	        columns: [[
	            {field:'giveYr'  ,width:100,halign:'center',align:'center',title:'지급연도',formatter: $.commFormat.year},
	            {field:'giveMm'  ,width:100,halign:'center',align:'center',title:'지급월'  ,formatter: $.commFormat.month},
	            {field:'giveYmd' ,width:150,halign:'center',align:'center',title:'지급일자'},
				{field:'giveSeNm',width:100,halign:'center',align:'center',title:'지급구분'},
				{field:'giveAmt' ,width:150,halign:'center',align:'right' ,title:'지급금액',formatter: $.commFormat.number}
	        ]]
		});
		// 지급기간 콤보박스 설정
		this.FORM.find('select[name="giveBgngYm1"]').appComboBox({
			type:'static', 
			init: {code:'', text:'시작년도'},
			rows: STORE.getYears(5, $.formatUtil.toYear),
			change: this.doChangeGiveYm
		});
		this.FORM.find('select[name="giveEndYm1" ]').appComboBox({
			type:'static', 
			init: {code:'', text:'종료년도'},
			rows: STORE.getYears(5, $.formatUtil.toYear),
			change: this.doChangeGiveYm
		});
		this.FORM.find('select[name="giveBgngYm2"]').appComboBox({
			type:'static', 
			init: {code:'', text:'시작월'},
			rows: STORE.getMonths($.formatUtil.toMonth),
			change: this.doChangeGiveYm
		});
		this.FORM.find('select[name="giveEndYm2" ]').appComboBox({
			type:'static', 
			init: {code:'', text:'종료월'},
			rows: STORE.getMonths($.formatUtil.toMonth),
			change: this.doChangeGiveYm
		});
		// 지급년월 콤보박스
		this.FORM.find('select[name="giveYr"]').appComboBox({
			type:'static', 
			init: {code:'', text:'년도'},
			rows: STORE.getYears(5, $.formatUtil.toYear)
		});
		this.FORM.find('select[name="giveMm"]').appComboBox({
			type:'static', 
			init: {code:'', text:'월'},
			rows: STORE.getMonths($.formatUtil.toMonth)
		});

		// 지급구분 라디오박스 설정
		this.RADIO = $('#appGiveSeCd').appSelectBox({
			form:   'radio',
			name:   'giveSeCd',
			params: {upCdId: CODE.GIVESE},
			filter: function(o) {
				// 소급 항목 제외
				return (o['code'] != CODE.GIVE_SE_CD.RTROACT);
			},
			click: function() {
				// 라디오 클릭시 해당항목 표시
				C_RCP.doClickGiveSeCd( $(this).val() );
			}
		});
		// 지급일자 선택시 지급년월 자동선택
		$('#giveYmd').datebox({
			onChange: function(newVal, oldVal) {
				if (newVal == oldVal)
					return;
				$('#giveYr').val($.commUtil.getYearStr (newVal));
				$('#giveMm').val($.commUtil.getMonthStr(newVal));
			}
		});
	    // 날짜 입력박스 하이픈(-) 자동삽입 이벤트
		bindDateHyphen( $('#giveYmd').datebox('textbox') );
		// 지급기한등록버튼 클릭시 이벤트
		$('#btnDeadline').bind('click', this.doSaveDeadline);
		// 소급결정내용버튼 클릭시 이벤트
		$('#btnRtroact').bind('click', this.doOpenRtroact);
		// 지급등록버튼 클릭시 이벤트
		$('#btnRcpSave').bind('click', this.doSave);
		// 초기화 완료
		this.INIT = true;
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 그리드 데이터 리셋
		this.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 폼 초기화
		this.FORM.form('reset');
		// 지급기한등록버튼 감춤
		$('#btnDeadline').hide();
		// 소급결정내용버튼 감춤
		$('#btnRtroact').hide();
		// 지급등록버튼 감춤
		$('#btnRcpSave').hide();
	},
	// 요양급여 지급현황 목록 조회
    //-------------------------------//
	doSearch: function(data) {
        // 목록조회
        this.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListRcperLvlhDtls.do');
        this.GRID.datagrid('load', data);
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 지급기간 이전데이터 설정
			data['giveBgngYmOrg'] = data['giveBgngYm'];
			data['giveEndYmOrg' ] = data['giveEndYm' ];
			// 지급구분 기본값 설정
			if ($.commUtil.empty(data['giveSeCd']))
				data['giveSeCd' ] = CODE.GIVE_SE_CD.MONTHLY;
			// 지급등록 가능시 월지급금액 설정
			if (data['giveUseYn'] == 'Y') {
				data['monthlyGrd'] = $.formatUtil.toGrdNm   (data['lastDmgeGrdCd']);
				data['monthlyAmt'] = $.formatUtil.toKorMoney(data['rcperLvlhAmt' ]);
				// 일시지급 가능시 일시금금액 설정
				if (data['lumpSumYn'] == 'Y') {
					data['giveAllAmt'] = $.formatUtil.toKorMoney(data['lumpSumAmt']);
				}
			}
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
			// 지급기간 년월 분리
			$.formUtil.splitData('giveBgngYm', 'month');
			$.formUtil.splitData('giveEndYm' , 'month');
	        // 목록조회
			this.doSearch({
				bizAreaCd : this.DATA['bizAreaCd'],
				bizOder   : this.DATA['bizOder'  ],
				exmnOder  : this.DATA['exmnOder' ],
				aplyNo    : this.DATA['aplyNo'   ]
			});
			// 지급기한등록 버튼 표시
			$('#btnDeadline').show();
			// 소급결정내용 버튼 표시
			$('#btnRtroact').show();
			// 지급등록 버튼 표시
			$('#btnRcpSave').show();
			// 일시지급이 불가한 경우
			if (data['lumpSumYn'] != 'Y')
				this.RADIO.disable(CODE.GIVE_SE_CD.LUMPSUM); // 일시지급 비활성처리
			else
				this.RADIO.enable(CODE.GIVE_SE_CD.LUMPSUM); // 일시지급 활성처리
			// 지급구분 클릭시 해당 항목 표시
			this.doClickGiveSeCd( data['giveSeCd'] );
			// 지급기간에 따른 지급일자 선택범위설정
			this.doChangeGiveYm();
		}
	},
	// 저장데이터 반환
    //-------------------------------//
	getSaveData: function() {
		// 지급기간 년월 병합처리
		$.formUtil.mergeData('giveBgngYm', 'month', 2);
		$.formUtil.mergeData('giveEndYm' , 'month', 2);

		let dobj = this.DATA;
		let fobj = this.FORM.serializeObject();
		return {
			mode         : MODE.INSERT,
			bizAreaCd    : fobj["bizAreaCd"    ],
			bizOder      : fobj["bizOder"      ],
			exmnOder     : fobj["exmnOder"     ],
			aplyNo       : fobj["aplyNo"       ],
			giveSeCd     : fobj["giveSeCd"     ],
			rcperLvlhAmt : fobj["rcperLvlhAmt" ], // 월지급금액
			lumpSumAmt   : fobj["lumpSumAmt"   ], // 일시불금액
			giveBgngYm   : fobj["giveBgngYm"   ],
			giveBgngYmOrg: fobj["giveBgngYmOrg"],
			giveEndYm    : fobj["giveEndYm"    ],
			giveEndYmOrg : fobj["giveEndYmOrg" ],
			giveUseYn    : dobj["giveUseYn"    ],
			lumpSumYn    : dobj["lumpSumYn"    ],
			giveYmd      : fobj["giveYmd"      ],
			giveYr       : fobj["giveYr"       ],
			giveMm       : fobj["giveMm"       ],
			giveYm       : fobj["giveYr"]+fobj["giveMm"]
		};
	},
	// 데이터 검증하기
    //-------------------------------//
    doValidate: function( data ) {
		if (data['giveUseYn'] != 'Y') {
			$.commMsg.alert('피해등급 산정이 완료되어야 지급등록이 가능합니다.');
			return false;
		}		
		// 지급기한이 등록전인 경우
		if ($.commUtil.empty(data['giveBgngYmOrg'])) {
			$.commMsg.alert('지급기한을 먼저 등록하셔야 합니다.');
			return false;
		}
		if (data['lumpSumYn'] != 'Y' && 
			data['giveSeCd'] == CODE.GIVE_SE_CD.LUMPSUM) {
			$.commMsg.alert('일시지급은 불가합니다. 월지급을 선택하세요.');
			return false;
		}		
		if ($.commUtil.empty(data['giveBgngYm']) ||
			$.commUtil.empty(data['giveEndYm' ])) {
			$.commMsg.alert('지급기간이 선택되지 않았습니다.');
			return false;
		}
		if (data['giveBgngYm'] > data['giveEndYm']) {
			$.commMsg.alert('지급기간의 종료년월을 시작년월보다 이후로 선택하세요.');
			return false;
		}
		if (data['giveBgngYm'] != data['giveBgngYmOrg'] ||
			data['giveEndYm' ] != data['giveEndYmOrg' ] ) {
			$.commMsg.alert('지급기간이 변경되었습니다. [지급기한등록]을 먼저 실행하세요.');
			return false;
		}
		//if ($.commUtil.nvlInt(data['lumpSumAmt']) == 0 && 
		//	data['giveSeCd'] == CODE.GIVE_SE_CD.LUMPSUM) {
		//	$.commMsg.alert('지급할 일시지급액이 0원입니다.');
		//	return false;
		//}		
		//if ($.commUtil.nvlInt(data['rcperLvlhAmt']) == 0 && 
		//	data['giveSeCd'] == CODE.GIVE_SE_CD.MONTHLY) {
		//	$.commMsg.alert('지급할 월지급액이 0원입니다.');
		//	return false;
		//}		
		if ($.commUtil.empty(data['giveYmd'])) {
			$.commMsg.alert('지급일자를 입력하세요.');
			return false;
		}
		if ($.commUtil.empty(data['giveYr']) ||
			$.commUtil.empty(data['giveMm' ])) {
			$.commMsg.alert('지급년월이 선택되지 않았습니다.');
			return false;
		}
		if (data['giveYm'] < data['giveBgngYm'] || 
			data['giveYm'] > data['giveEndYm' ]) {
			$.commMsg.alert('지급년월을 지급기간 이내로 선택하세요.');
			return false;
		}
		if (C_RCP.doDuplicate(data)) {
			$.commMsg.alert('해당 지급년월에 지급된 내역이 있습니다.');
			return false;
		}
		return true;
	},
	// 지급년월 중복체크
    //-------------------------------//
    doDuplicate: function(data) {
		let ret = $.ajaxUtil.ajaxDefault(
			getUrl('/adm/exmn/checkRcperLvlhDtlsDupl.do'), {
				bizAreaCd:  data['bizAreaCd'],
				bizOder:    data['bizOder'  ],
				exmnOder:   data['exmnOder' ],
				aplyNo:     data['aplyNo'   ],
				giveYr:     data['giveYr'   ],
				giveMm:     data['giveMm'   ],
			});
		if (ret && ret.Data == 'Y')
			return true;
		return false;
	},
	// 지급기한등록 저장
    //-------------------------------//
	doSaveDeadline: function() {
		// 지급기간 년월 병합처리
		$.formUtil.mergeData('giveBgngYm', 'month', 2);
		$.formUtil.mergeData('giveEndYm' , 'month', 2);

		let fobj = C_RCP.FORM.serializeObject();
		let data = {
			bizAreaCd    : fobj["bizAreaCd"    ],
			bizOder      : fobj["bizOder"      ],
			exmnOder     : fobj["exmnOder"     ],
			aplyNo       : fobj["aplyNo"       ],
			giveBgngYm   : fobj["giveBgngYm"   ],
			giveEndYm    : fobj["giveEndYm"    ],
		};
		if ($.commUtil.empty(data['giveBgngYm']) ||
			$.commUtil.empty(data['giveEndYm' ])) {
			$.commMsg.alert('지급기간이 선택되지 않았습니다.');
			return false;
		}
		if (data['giveBgngYm'] > data['giveEndYm']) {
			$.commMsg.alert('지급기간의 종료년월을 시작년월보다 이후로 선택하세요.');
			return false;
		}
		$.commMsg.confirm("지급기한을 등록하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveRcperLvlhDeadline.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 등록되었습니다.', function() {
							C_LIST.doSearch();
						});
                    });
                }
            );
		});
        return false;
	},
	// 지급등록 저장
    //-------------------------------//
	doSave: function() {
		let data  = C_RCP.getSaveData();
		let valid = C_RCP.doValidate(data);
		if (!valid)
			return false;
		
		$.commMsg.confirm("지급등록하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveRcperLvlhDtls.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 등록되었습니다.', function() {
							// 2023.02.20 요양생활지급 탭 리로드
							C_RCP.doReset();
							C_RCP.doLoad( P_SELECT );
						});
                    });
                }
            );
		});
        return false;
	},
	// 지급구분 클릭시 이벤트 처리
    //-------------------------------//
	doClickGiveSeCd: function( giveSeCd ) {
		// 월지급 클릭시
		if (giveSeCd == CODE.GIVE_SE_CD.MONTHLY) {
			// 레이블표시
			$('#giveSeLabel').html('월지급금액확인');			
			// 월지급등급 표시
			$('#monthlyGrd').show();
			// 월지급금액 표시
			$('#monthlyAmt').show();
			// 일시불금액 감춤
			$('#giveAllAmt').hide();
		}
		// 일시금지급 클릭시
		else if (giveSeCd == CODE.GIVE_SE_CD.LUMPSUM) {
			// 레이블표시
			$('#giveSeLabel').html('일시금지급금액');			
			// 일시지급금액 조회
			let lumpSum = C_RCP.doLoadLumpSum();
			$('#lumpSumAmt').val(lumpSum);
			$('#giveAllAmt').val($.formatUtil.toKorMoney(lumpSum));
			// 월지급등급 감춤
			$('#monthlyGrd').hide();
			// 월지급금액 감춤
			$('#monthlyAmt').hide();
			// 일시불금액 표시
			$('#giveAllAmt').show();
		}
	},
	// 지급기간 변경시 지급일자 선택범위설정
    //-------------------------------//
	doChangeGiveYm: function() {
		// 일시지급금액 조회
		let lumpSum = C_RCP.doLoadLumpSum();
		$('#lumpSumAmt').val(lumpSum);
		$('#giveAllAmt').val($.formatUtil.toKorMoney(lumpSum));
	},
	// 일시지급금액 조회
    //-------------------------------//
	doLoadLumpSum: function() {

		if (C_RCP.DATA['lumpSumYn'] != 'Y')
			return '0';
		
		// 지급기간 년월 병합처리
		$.formUtil.mergeData('giveBgngYm', 'month', 2);
		$.formUtil.mergeData('giveEndYm' , 'month', 2);
		// 일시지급금액 조회		
		let ret = $.ajaxUtil.ajaxDefault(
			getUrl('/adm/exmn/getRcperLvlhLumpSum.do'), {
				bizAreaCd:  C_RCP.DATA['bizAreaCd'],
				bizOder:    C_RCP.DATA['bizOder'  ],
				exmnOder:   C_RCP.DATA['exmnOder' ],
				aplyNo:     C_RCP.DATA['aplyNo'   ],
				giveBgngYm: $('#giveBgngYm').val(),
				giveEndYm:  $('#giveEndYm' ).val()
			});
		if (ret && ret.Data) {
			return ret.Data;
		}
		return '0';
	},
	// 소급결정내용 팝업 오픈
    //-------------------------------//
	doOpenRtroact: function() {

		let params = {
			bizAreaCd    : C_RCP.DATA['bizAreaCd'    ],
			bizOder      : C_RCP.DATA['bizOder'      ],
			exmnOder     : C_RCP.DATA['exmnOder'     ],
			aplyNo       : C_RCP.DATA['aplyNo'       ],
			rtroactBgngYm: C_RCP.DATA['rtroactBgngYm'],
			rtroactEndYm : C_RCP.DATA['rtroactEndYm' ],
			giveUseYn    : C_RCP.DATA['giveUseYn'    ],
			giveBgngYm   : C_RCP.DATA['giveBgngYmOrg'],
			giveEndYm    : C_RCP.DATA['giveEndYmOrg' ],
			giveSeCd     : CODE.GIVE_SE_CD.RTROACT,
		};
		
		// 2023.01.17 지급기한등록여부, 지급등록가능여부 체크
		// 지급등록이 불가한 경우
		if (params['giveUseYn'] != 'Y') {
			$.commMsg.alert('피해등급 산정이 완료되어야 소급결정 지급등록이 가능합니다.');
			return false;
		}
		// 지급기한이 등록전인 경우
		if ($.commUtil.empty(params['giveBgngYm'])) {
			$.commMsg.alert('지급기한을 먼저 등록하셔야 합니다.');
			return false;
		}
		// 소급결정내용역 팝업
		$('#appPopupRtroact').appPopup({
			url:   getUrl('/adm/exmn/modalRtroact.do'),
			type:  'pageload',
			title: '소급 결정 내용',
			onloaded: function( pop ) {
				
				let CHG = false;
				let frm = $('#p_rtroactForm');
				let dom = pop.getElm();
				let fnChangeGiveYm = function() {
					CHG = true;
				};
				// 소급산정 실행필요여부
				CHG = true;
				// 소급기간 콤보박스 설정
				dom.find('select[name="rtroactBgngYm1"]').appComboBox({
					type:'static', 
					init: {code:'',text:'시작년도'},
					rows: STORE.getYears(5, $.formatUtil.toYear),
					change: fnChangeGiveYm
				});
				dom.find('select[name="rtroactBgngYm2"]').appComboBox({
					type:'static', 
					init: {code:'',text:'시작월'},
					rows: STORE.getMonths($.formatUtil.toMonth),
					change: fnChangeGiveYm
				});
				dom.find('select[name="rtroactEndYm1"]').appComboBox({
					type:'static', 
					init: {code:'',text:'종료년도'},
					rows: STORE.getYears(5, $.formatUtil.toYear),
					change: fnChangeGiveYm
				});
				dom.find('select[name="rtroactEndYm2"]').appComboBox({
					type:'static', 
					init: {code:'',text:'종료월'},
					rows: STORE.getMonths($.formatUtil.toMonth),
					change: fnChangeGiveYm
				});
				// 지급일자 선택시 지급년월 자동선택
				$('#p_rtroactYmd').datebox({
					onChange: function(newVal, oldVal) {
						if (newVal == oldVal)
							return;
						$('#p_giveYr').val($.commUtil.getYearStr (newVal));
						$('#p_giveMm').val($.commUtil.getMonthStr(newVal));
					}
				});
				// 날짜 입력박스 하이픈(-) 자동삽입 이벤트
				bindDateHyphen( $('#p_rtroactYmd').datebox('textbox') );
				// 폼데이터 로드
				frm.form('load', params);
				// 소급기간 년월 분리
				$.formUtil.splitData('p_rtroactBgngYm', 'month');
				$.formUtil.splitData('p_rtroactEndYm' , 'month');
				
				// 소급기간 검증 및 데이터 반환
				let fnValidateData = function() {
					// 소급기간 년월 병합처리
					$.formUtil.mergeData('p_rtroactBgngYm', 'month', 2);
					$.formUtil.mergeData('p_rtroactEndYm' , 'month', 2);
					// 폼데이터
					let fobj = frm.serializeObject();
					let data = {
						bizAreaCd    : fobj["bizAreaCd"    ],
						bizOder      : fobj["bizOder"      ],
						exmnOder     : fobj["exmnOder"     ],
						aplyNo       : fobj["aplyNo"       ],
						rtroactBgngYm: fobj["rtroactBgngYm"],
						rtroactEndYm : fobj["rtroactEndYm" ],
						giveSeCd     : fobj["giveSeCd"     ],
						giveYmd      : fobj["rtroactYmd"   ],
						giveYr       : fobj["giveYr"       ],
						giveMm       : fobj["giveMm"       ],
						rtroactAmt   : fobj["rtroactAmt"   ], // 소급금액
					};
					if (params['giveUseYn'] != 'Y') {
						$.commMsg.alert('피해등급 산정이 완료되어야 소급산정이 가능합니다.');
						return false;
					}
					if ($.commUtil.empty(data['rtroactBgngYm']) ||
						$.commUtil.empty(data['rtroactEndYm' ])) {
						$.commMsg.alert('소급기간이 선택되지 않았습니다.');
						return false;
					}
					if (data['rtroactBgngYm'] > data['rtroactEndYm']) {
						$.commMsg.alert('소급기간의 종료년월을 시작년월보다 이후로 선택하세요.');
						return false;
					}
					if (data['rtroactBgngYm'] < params['giveBgngYm']) {
						$.commMsg.alert(
							'소급기간의 시작년월을 지급기간의 시작년월보다 이후로 선택하세요.<br>'+
							'[소급시작년월: '+data['rtroactBgngYm']+
							',지급시작년월: '+params['giveBgngYm' ]+']'
						);
						return false;
					}
					if (data['rtroactEndYm'] > params['giveEndYm']) {
						$.commMsg.alert(
							'소급기간의 종료년월을 지급기간의 종료년월보다 이전으로 선택하세요.<br>'+
							'[소급종료년월: '+data['rtroactEndYm']+
							',지급종료년월: '+params['giveEndYm' ]+']'
						);
						return false;
					}
					return data;
				};
				
				// 소급산정버튼 클릭시
				dom.find('a.app-calc-btn').bind('click', function() {

					frm.find('input[name="rtroactAmt"   ]').val('');
					frm.find('input[name="rtroactAmtKor"]').val('');
					// 소급기간 검증 데이터
					let data = fnValidateData();
					if (!data)
						return false;
					// 소급금액 계산
			        $.ajaxUtil.ajaxLoad(
			            getUrl('/adm/exmn/getRtroactSum.do'), data,
			            function(result) {
							let rtroactAmt = $.commUtil.nvlInt(result.Data);
							frm.find('input[name="rtroactAmt"   ]').val( rtroactAmt );
							frm.find('input[name="rtroactAmtKor"]').val( $.formatUtil.toKorMoney(rtroactAmt) );
							$.commMsg.alert('소급산정이 완료되었습니다.');
							CHG = false;
						}
					);
			        return false;
				});
			    // 취소버튼 클릭시
			    dom.find('a.app-close-btn').bind('click', function() {
					pop.close();
			        return false;
				});				
				// 지급등록버튼 클릭시
				dom.find('a.app-save-btn').bind('click', function() {
					// 소급기간 검증 데이터
					let data = fnValidateData();
					if (!data)
						return false;
					// 이미 소급된 내역이 있는지 확인 (소급은 한번만 가능)
					let chk = $.ajaxUtil.ajaxDefault(getUrl('/adm/exmn/checkRcperLvlhRtroact.do'), {
						bizAreaCd:  data['bizAreaCd'],
						bizOder:    data['bizOder'  ],
						exmnOder:   data['exmnOder' ],
						aplyNo:     data['aplyNo'   ]
					});
					if (chk && chk.Data == 'Y') {
						$.commMsg.alert('이미 소급 지급된 내역이 있습니다.');
						return false;
					}
					// 소급산정 실행여부 확인
					if (CHG || $.commUtil.empty(frm.find('input[name="rtroactAmt"]').val())) {
						$.commMsg.alert('소급산정을 먼저 실행하세요.');
						return false;
					}
					//if ($.commUtil.nvlInt(data['rtroactAmt']) == 0) {
					//	$.commMsg.alert('지급할 소급금액이 0원입니다.');
					//	return false;
					//}
					if ($.commUtil.empty(data['giveYmd'])) {
						$.commMsg.alert('지급일자를 입력하세요.');
						return false;
					}
					// 지급일자 범위 확인
					let gvDt = $.commUtil.toDate(data['giveYmd']);
					let stDt = $.commUtil.toDate(params['giveBgngYm']+'01');
					let enDt = $.commUtil.lastDay($.commUtil.toDate(params['giveEndYm' ]+'01'));
					if (gvDt < stDt || gvDt > enDt) {
						$.commMsg.alert(
							'지급일자를 지급기간 이내로 입력하세요.<br>'+
							'[지급기간: '+params['giveBgngYm']+' ~ '+params['giveEndYm']+']'
						);
						return false;
					}
					// 지급년월에 지급된 내역이 있는지 확인
					let dup = $.ajaxUtil.ajaxDefault(getUrl('/adm/exmn/checkRcperLvlhDtlsDupl.do'), {
						bizAreaCd:  data['bizAreaCd'],
						bizOder:    data['bizOder'  ],
						exmnOder:   data['exmnOder' ],
						aplyNo:     data['aplyNo'   ],
						giveYr:     data['giveYr'   ],
						giveMm:     data['giveMm'   ],
					});
					if (dup && dup.Data == 'Y') {
						$.commMsg.alert('해당 지급년월에 지급된 항목이 있습니다. 지급일자를 변경하세요.');
						return false;
					}
					$.commMsg.confirm("지급등록하시겠습니까?", function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/exmn/saveRcperLvlhDtls.do'), 
			                JSON.stringify(data),
			                function(ret) {
			                    $.ajaxUtil.success(ret, function() {
									$.commMsg.alert('성공적으로 등록되었습니다.', function() {
										pop.close();
										// 2023.02.20 요양생활지급 탭 리로드
										C_RCP.doReset();
										C_RCP.doLoad( P_SELECT );
									});
			                    });
			                }
			            );
					});
			        return false;
				});
			}
		}).open({ params: JSON.stringify(params) });
	},
};

$(function() {
	
	P_TABS   = [C_GRD, C_RCP];
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
