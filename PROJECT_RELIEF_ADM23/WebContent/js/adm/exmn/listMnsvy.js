/**
******************************************************************************************
*** 파일명 : listMnsvy.js
*** 설명글 : 본조사 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.11.29    LSH   기능구현
*** 2.0         2023.01.06    LSH   식별ID 검색조건 추가 및 정렬 처리
*** 2.0         2023.01.06    LSH   심의결과 부적합시에만 사유필수입력 처리
*** 2.0         2023.01.06    LSH   장의비/유족보상비 탭 제거
*** 2.0         2023.01.06    LSH   요양생활수당의 최종피해등급 항목 제거
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
	// 초기화
    //-------------------------------//
	doInit: function() {
		this.FORM = $('#searchForm');
	    this.GRID = $('#appGrid').datagrid({
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
	        // 칼럼정의
	        columns: [[
	            {field:'bizAreaNm'    ,width:100,halign:'center',align:'center',title:'피해지역'},
	            {field:'bizOder'      ,width: 70,halign:'center',align:'center',title:'사업차수'},
	            {field:'exmnOder'     ,width: 70,halign:'center',align:'center',title:'조사차수'},
	            {field:'idntfcId'     ,width: 90,halign:'center',align:'center',title:'식별ID'},
	            {field:'aplyNo'       ,width:120,halign:'center',align:'center',title:'신청번호'},
	            {field:'sufrerNmMask' ,width:100,halign:'center',align:'center',title:'피해자명'},
	            {field:'aplcntNmMask' ,width:100,halign:'center',align:'center',title:'신청자명'},
	            {field:'dltncRsltNm'  ,width:100,halign:'center',align:'center',title:'인정여부'}
	        ]],
	        // 행선택시 상세조회
	        onSelect: this.doLoad
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
	    // 엑셀다운로드버튼 클릭시 이벤트처리
	    $('#btnExcel' ).bind('click', this.doExcel);

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
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택된 항목 CLEAR
		C_LIST.GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = C_LIST.FORM.serializeObject();
        // 그리드 목록조회 URL
        C_LIST.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListMnsvy.do');
        // 검색폼 그리드 검색
        C_LIST.GRID.datagrid('load', obj);
        return false;
	},
	// 검색리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
        // 검색폼 입력데이터 초기화
        C_LIST.FORM.form('reset');
        // 검색처리
        C_LIST.doSearch();
        return false;
	},
	// 엑셀
    //-------------------------------//
	doExcel: function() {
        $.formUtil.submitForm(
            getUrl('/adm/exmn/downMnsvyExcel.do'),
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

		// 본조사정보 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getMnsvy.do'),
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
// [1] 의료비 기능정의
//----------------------------------------------------------------------------//
const C_MCP = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#mcpForm');
		this.GRID = $('#mcpGrid').datagrid({
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
			// 그리드 스트라이프 표시안함
			striped: false,
			// 그리드 하단합계 표시
			showFooter: true,
	        // 칼럼정의
	        columns: [[
	            {rowspan:2, field:'dissClNm'     ,width:155,halign:'center',align:  'center',title:'질환분류'},
	            {rowspan:2, field:'sckwndNm'     ,width:200,halign:'center',align:  'center',title:'상병명'},
	            {rowspan:2, field:'sckwndCd'     ,width:80, halign:'center',align:'center',title:'상병코드'},
	            {rowspan:2, field:'mcpTotAmt'    ,width:100,halign:'center',align: 'right',title:'본인<br>부담금', formatter:$.commFormat.number},
	            {colspan:3, title:'년도별 내역'},
	            {rowspan:2, field:'mcpTotCnt'    ,width: 80,halign:'center',align: 'right',title:'진료<br>건수', formatter:$.commFormat.number},
			],[
	            {field:'mcpYear' ,width: 80,halign:'center',align:'center',title:'년도'},
	            {field:'mcpCnt'  ,width: 50,halign:'center',align: 'right',title:'횟수', formatter:$.commFormat.number},
	            {field:'mcpAmt'  ,width:100,halign:'center',align: 'right',title:'금액', formatter:$.commFormat.number}
	        ]],
			onLoadSuccess: function(data) {
				let txt = '';
				let idx = 0;
				let obj = {};
				let elm = $(this);
				$.each(data.rows, function(i,r1) {
					if (txt == r1['sckwndCd']) {
						if (obj[txt]) {
							obj[txt]['rowspan']++;
						}
						else {
							obj[txt] = {index: idx, rowspan:2};
						}
					}
					txt = r1['sckwndCd'];
					idx = i;
				});
				$.each(obj, function(key, row) {
					// 행 병합 처리
	                elm.datagrid('mergeCells', {index: row['index'], rowspan: row['rowspan'], field: 'dissClNm' });
	                elm.datagrid('mergeCells', {index: row['index'], rowspan: row['rowspan'], field: 'sckwndNm' });
	                elm.datagrid('mergeCells', {index: row['index'], rowspan: row['rowspan'], field: 'sckwndCd' });
	                elm.datagrid('mergeCells', {index: row['index'], rowspan: row['rowspan'], field: 'mcpTotCnt'});
					elm.datagrid('mergeCells', {index: row['index'], rowspan: row['rowspan'], field: 'mcpTotAmt'});
				});
			}
		});
		// 세부의료비 산정결과 양식 다운로드
		$('#btnMcpForm').bind('click', this.doDownForm);
		// 세부의료비 산정결과 일괄등록
		$('#btnMcpLoad').bind('click', this.doLoadExcel);
		// 세부의료비 산정결과 다운로드
		$('#btnMcpDown').bind('click', this.doDownExcel);
		// 2022.12.09 선택질환 세부의료비 보기
		$('#btnMcpSckwnd').bind('click', this.doOpenMcpSckwnd);

		this.INIT = true;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {

		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
	        // 목록조회
	        this.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListMcpDtlsSummary.do');
	        this.GRID.datagrid('load', {
				bizAreaCd : this.DATA['bizAreaCd'],
				bizOder   : this.DATA['bizOder'  ],
				exmnOder  : this.DATA['exmnOder' ],
				aplyNo    : this.DATA['aplyNo'   ]
			});
			// 일괄등록버튼 표시
			$('#btnMcpLoad').show();
			// 다운로드버튼 표시
			$('#btnMcpDown').show();
		}
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 그리드 데이터 리셋
		this.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 일괄등록버튼 감춤
		$('#btnMcpLoad').hide();
		// 다운로드버튼 감춤
		$('#btnMcpDown').hide();

	},
	// 세부의료비 산정결과 양식 다운로드
    //-------------------------------//
	doDownForm: function() {
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: {},
			url   : getUrl("/adm/exmn/downMcpDtlsForm.do")
		});
		return false;
	},
	// 세부의료비 산정결과 일괄등록
    //-------------------------------//
	doLoadExcel: function() {
		// 파일업로드 팝업 오픈
		popup.openUpload({
			// 업로드 URL
			url: getUrl("/adm/exmn/saveMcpDtls.do"),
			// 허용가능 확장자
			extensions: ['xlsx','xls'],
			// 팝업제목
			title: '세부의료비 산정결과 엑셀등록',
			// 기본변수
			params: {
				needYn: 'Y'
			},
			// 추가변수
			addParams: {
				idntfcId  : C_MCP.DATA['idntfcId' ],
				bizAreaCd : C_MCP.DATA['bizAreaCd'],
				bizOder   : C_MCP.DATA['bizOder'  ],
				exmnOder  : C_MCP.DATA['exmnOder' ],
				aplyNo    : C_MCP.DATA['aplyNo'   ]
			},
			// 추가표시정보
			addContent: function(table) {
				let tr = $('<tr></tr>');
				tr.append('<th>피해자정보</th>');
				tr.append('<td>ID :'      +C_MCP.DATA['idntfcId'   ]+'</td>');
				tr.append('<td>피해자명 :'+C_MCP.DATA['sufrerNmMask']+'</td>');
				// 테이블의 앞부분에 추가한다.
				table.prepend(tr);
			},
			// 결과처리
			success: function(ret) {
				let r = $(ret);
				if (r.find('.exception_detail_message') &&
					r.find('.exception_detail_message').length > 0) {
					$.commMsg.alert(r.find('.exception_detail_message').html());
					return false;
				}
				if (r.find('.exception_message') &&
					r.find('.exception_message').length > 0) {
					$.commMsg.alert(r.find('.exception_message').html());
					return false;
				}
				if (r['Code'] < 0) {
					$.commMsg.alert('[Code:' + r['Code'] + '] ' + r['Message']);
					return;
				}
				else {
					$.commMsg.alert('성공적으로 등록되었습니다.', function() {
						C_MCP.GRID.datagrid('reload');
						dialog.close();
					});
				}
            }
		});
		return false;
	},
	// 세부의료비 산정결과 다운로드
    //-------------------------------//
	doDownExcel: function() {
		if (C_MCP.DATA == false)
			return false;

		let p = {
			bizAreaCd : C_MCP.DATA['bizAreaCd'],
			bizOder   : C_MCP.DATA['bizOder'  ],
			exmnOder  : C_MCP.DATA['exmnOder' ],
			aplyNo    : C_MCP.DATA['aplyNo'   ]
		};
        $.formUtil.submitForm(
			getUrl('/adm/exmn/downMcpDtlsExcel.do'),
			{params: p}
		);
        return false;
	},
	// 2022.12.09 선택질환 세부의료비 보기 (팝업)
    //-------------------------------//
	doOpenMcpSckwnd: function() {

		let row = C_MCP.GRID.datagrid('getSelected');
		if (row == null) {
			$.commMsg.alert('의료비 목록에서 조회할 질환을 선택하세요.');
			return false;
		}
		let params = {
			bizAreaCd : C_MCP.DATA['bizAreaCd'],
			bizOder   : C_MCP.DATA['bizOder'  ],
			exmnOder  : C_MCP.DATA['exmnOder' ],
			aplyNo    : C_MCP.DATA['aplyNo'   ],
			sckwndCd  : row['sckwndCd']
		};
		// 세부 의료비내역 팝업
		$('#appPopupMcpSckwnd').appPopup({
			url:   getUrl('/adm/exmn/modalMcpSckwnd.do'),
			type:  'pageload',
			title: '세부 의료비 내역',
			onloaded: function( pop ) {
				// 대상자 목록
				$('#p_mcpDtlsGrid').datagrid({
					fit: true,
			        // 그리드 결과데이터 타입
			        contentType: 'application/json',
			        // 그리드 결과데이터 타입
			        dataType: 'json',
			        // 그리드 데이터연동 방식
			        method: 'POST',
			        // 체크박스 KEY값필드
			        idField:'sn',
					url: getUrl('/adm/exmn/getListMcpSckwnd.do'),
					queryParams: $('#p_mcpDtlsForm').serializeObject(),
			        // 칼럼정의
			        columns: [[
			            {field:'rcperPstgYmd',width:100,halign:'center',align:'center',title:'진료일'},
			            {field:'rcperInstNm' ,width:200,halign:'center',align:'center',title:'진료기관명'},
			            {field:'rcperSeNm'   ,width:100,halign:'center',align:'center',title:'구분'},
						{field:'selfAlotm'   ,width:100,halign:'center',align: 'right',title:'본인부담금', formatter:$.commFormat.number}
					]]
				});
			    // 엑셀다운로드 클릭
			    $('#btnMcpDtlsDown').bind('click', function() {
			        $.formUtil.submitForm(getUrl('/adm/exmn/downMcpSckwndExcel.do'), {params: $('#p_mcpDtlsForm').serializeObject()});
			        return false;
				});
			}

		}).open({ params: JSON.stringify(params) });
		
		return false;
	},
};

//============================================================================//
// [2] 요양생활수당 기능정의
//----------------------------------------------------------------------------//
const C_RCP = {
	FORM : false,
	FILE : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#rcpForm');
		this.FILE = $('#appRcpFileBox').appExmnFile({
			formId: '#rcpForm',
			gridId: '#appRcpFileGrid',
			selector: 'app-exmn-rcp-files'
		});
		// 저장버튼
		$('#btnRcpSave').bind('click', this.doSave);

		this.INIT = true;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
		}
		if (this.DATA) {
			// 첨부서류 목록조회
			this.FILE.doSearch( {
				bizAreaCd : this.DATA['bizAreaCd'],
				bizOder   : this.DATA['bizOder'  ],
				exmnOder  : this.DATA['exmnOder' ],
				aplyNo    : this.DATA['aplyNo'   ],
				dtySeCd   : '02'  , // 업무구분 (본조사)
				dtyClCd   : 'RCP' , // 업무분류 (요양생활수당)
			} );
			// 저장버튼 표시
			$('#btnRcpSave').show();
		}
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 상세조회 폼데이터 초기화
		this.FORM.form('reset');
		// 첨부서류 데이터 리셋
		this.FILE.doReset();
		// 저장버튼 감춤
		$('#btnRcpSave').hide();
	},
	// 요양생활수당 저장
    //-------------------------------//
	doSave: function() {

		let f = C_RCP.FORM;

		// 파일검증 실패시
		if (C_RCP.FILE.doValidate() == false)
			return false;
		// 파일갯수가 0인 경우
		if (C_RCP.FILE.getUploadCount() == 0) {
			$.commMsg.alert('저장할 첨부서류가 없습니다.');
			return false;
		}
    	$.commMsg.confirm("요양생활수당을 저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            f.ajaxForm({
                url: getUrl('/adm/exmn/saveMnsvyRcper.do'),
				// 파일업로드 타입
                enctype : 'multipart/form-data',
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '성공적으로 저장되었습니다.', function() {
						// 목록 검색
                        C_LIST.doSearch();
					});
                }
            }).submit();
    	});
		return false;
	},
};

//============================================================================//
// [4] 사망원인조사 기능정의
//----------------------------------------------------------------------------//
const C_DTH = {
	FORM : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#dthForm');
		// 피해관련여부
		$('#dmgeRelYn').combobox({
			mode: 'local',
			data: STORE.REL_YN
		});
		// 지급결과
		$('#fnrlCstGiveRsltCd').combobox({
			url: getUrl('/com/cmm/getComboCode.do'),
			queryParams:  {upCdId: CODE.FNRLGIVE}
		});

	    // FORM VALIDATION RULE 정의
	    this.FORM.validate({
	        debug: false,
	        onsubmit: false,
	        onfocusout: false,
	        // 검증룰 정의
	        rules: {
	            dmgeRelYn : {required: true},
	            giveRsltCd: {required: true},
	        },
	        // 검증메세지 정의
	        messages: {
	            dmgeRelYn : {required: '피해관련여부는 필수 선택 사항입니다.'},
	            giveRsltCd: {required: '장의비/유족보상비 지급결과는 필수 선택 사항입니다.'},
	        },
	        invalidHandler: validateHandler,
	        errorPlacement: validatePlacement
	    });

		// 저장버튼
		$('#btnDthSave').bind('click', this.doSave);
		// 인쇄버튼
		$('#btnDthPrint').bind('click', this.doPrint);

		this.INIT = true;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 2023.01.04 사망나이 포맷처리
			data['dthAgeNm'] = $.formatUtil.toAgeNm(data['dthAge']);
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
			// 저장버튼 표시
			$('#btnDthSave').show();
			// 인쇄버튼 표시
			$('#btnDthPrint').show();
		}
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 폼 초기화
		this.FORM.form('reset');
		// 저장버튼 감춤
		$('#btnDthSave').hide();
		// 인쇄버튼 감춤
		$('#btnDthPrint').hide();
	},
	// 사망원인조사 저장
    //-------------------------------//
	doSave: function() {

		let f = C_DTH.FORM;

        // FORM VALIDATION 기능 활성화
        if (f.validate().settings)
            f.validate().settings.ignore = false;
        // FORM VALIDATION
        if (f.valid() === false)
            return false;

		// 처리모드
		f.find('input[name="mode"]').val(MODE.UPDATE);
		f.find('input[name="act"]' ).val('DTH');

		let data = f.serializeObject();

    	$.commMsg.confirm("사망원인조사를 저장하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveMnsvy.do'),
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							// 목록 검색
	                        C_LIST.doSearch();
						});
                    });
                }
            );
    	});
		return false;
	},
	// 사망원인조사 인쇄하기 클릭 (리포팅툴)
    //-------------------------------//
	doPrint: function() {
		let data   = C_DTH.DATA;
		let params = {
			mode      : 'MNSVYDTH',
			bizAreaCd : data['bizAreaCd'],
			bizOder   : data['bizOder'  ],
			exmnOder  : data['exmnOder' ],
			aplyNo    : data['aplyNo'   ]
		};
		// 리포트뷰어 팝업 호출
		popup.openReportPopup(params);
		return false;
	},
};
//============================================================================//
// [5] 심의회결과 기능정의
//----------------------------------------------------------------------------//
const C_RSLT = {
	FORM : false,
	FILE : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#rsltForm');
		// 심의회결과
		$('#appDltncRslt').appSelectBox({
			form:    'radio',
			name:    'dltncRsltCd',
			params:  {upCdId: CODE.RESULT}
		});
	    // FORM VALIDATION RULE 정의
	    this.FORM.validate({
	        debug: false,
	        onsubmit: false,
	        onfocusout: false,
	        // 검증룰 정의
	        rules: {
	            giveDcsnYmd   : {required: true},
	            dltncRsltCd   : {required: true}
	        },
	        // 검증메세지 정의
	        messages: {
	            giveDcsnYmd   : {required: '개최일자는 필수 입력 사항입니다.'},
	            dltncRsltCd   : {required: '심의회 결과는 필수 선택 사항입니다.'}
	        },
	        invalidHandler: validateHandler,
	        errorPlacement: validatePlacement
	    });

		this.FILE = $('#appRsltFileBox').appExmnFile({
			formId: '#rsltForm',
			gridId: '#appRsltFileGrid',
			selector: 'app-exmn-rslt-files'
		});

	    // 개최일자 입력박스 하이픈(-) 자동삽입 이벤트
		bindDateHyphen( $('#giveDcsnYmd').datebox('textbox') );

		// 저장버튼
		$('#btnRsltSave').bind('click', this.doSave);

		this.INIT = true;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 상세조회 데이터 리셋
			this.DATA = data;
			// 심의회결과 폼데이터 로드
			this.FORM.form('load', data);
			// 첨부서류 목록조회
			this.FILE.doSearch( {
				bizAreaCd : data['bizAreaCd'],
				bizOder   : data['bizOder'  ],
				exmnOder  : data['exmnOder' ],
				aplyNo    : data['aplyNo'   ],
				dtySeCd   : '02'  , // 업무구분 (본조사)
				dtyClCd   : 'DLT' , // 업무분류 (심의회결과)
			} );
			// 저장버튼 표시
			$('#btnRsltSave').show();
		}
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 심의회결과 상세폼 초기화
		this.FORM.form('reset');
		// 첨부서류 목록 리셋
		this.FILE.doReset();
		// 저장버튼 감춤
		$('#btnRsltSave').hide();
	},
	// 데이터 저장
    //-------------------------------//
	doSave: function() {

		let f = C_RSLT.FORM;
		let d = C_RSLT.DATA;

        // FORM VALIDATION 기능 활성화
        if (f.validate().settings)
            f.validate().settings.ignore = false;
        // FORM VALIDATION
        if (f.valid() === false)
            return false;

		// [2023.01.06] 심의결과가 부적합일때만 사유 필수 입력하도록 수정
		if (f.find('input[name="dltncRsltCd"]:checked').val() == CODE.RSLT_CD.REJECT &&
			$.commUtil.empty(f.find('textarea[name="dltncRsltResn"]').val())) {
			$.commMsg.alert('부적합 사유는 필수 입력 사항입니다.');
			return false;
		}
		// 파일검증 실패시
		if (C_RSLT.FILE.doValidate() == false)
			return false;

		// 처리모드
		f.find('input[name="mode"]').val(MODE.UPDATE);

    	$.commMsg.confirm("본조사 심의결과를 저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            f.ajaxForm({
                url: getUrl('/adm/exmn/saveMnsvyRslt.do'),
				// 파일업로드 타입
                enctype : 'multipart/form-data',
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
                	$.ajaxUtil.success(ret, function() {
						// SMS 발송대상 텍스트
						let msg = [
							'<p>',
							'<b class="app-ps10">ID :     '+d['idntfcId'    ]+'</b>',
							'<b class="app-ps10">신청자 : '+d['aplcntNmMask']+'</b>',
							'</p>',
							'<p>님에게 본조사 심의결과를 SMS로 전송하시겠습니까?</p>'
						].join('');
						// SMS 발송처리
		    			$.commMsg.confirm(msg, C_RSLT.doSend, C_LIST.doSearch);
                	});
                }
            }).submit();
    	});
		return false;
	},
	// 심의결과 SMS 발송
    //-------------------------------//
	doSend: function() {
		let data = {
			bizAreaCd : C_RSLT.DATA['bizAreaCd'],
			bizOder   : C_RSLT.DATA['bizOder'  ],
			exmnOder  : C_RSLT.DATA['exmnOder' ],
			aplyNo    : C_RSLT.DATA['aplyNo'   ],
		};
        // AJAX로 저장처리
        $.ajaxUtil.ajaxSave(
            getUrl('/adm/exmn/sendMnsvyRsltSms.do'),
            JSON.stringify(data),
            function(ret) {
                $.ajaxUtil.success(ret, function() {
					$.commMsg.alert('본조사 심의회 결과를 SMS로 발송하였습니다.', C_LIST.doSearch);
                });
            }
        );
	},
};

$(function() {

	P_TABS   = [C_MCP, C_RCP, C_DTH, C_RSLT];
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

	// 탭서브메뉴 생성 (comm_adm.js 참고)
	createTabMenu( $('#appTabSubMenu'), [
		{url:getUrl('/adm/exmn/listMnsvyPlan.do'), title:'본조사 계획'},
		{url:getUrl('/adm/exmn/listMnsvy.do'    ), title:'본조사 관리'}
	]);
});
