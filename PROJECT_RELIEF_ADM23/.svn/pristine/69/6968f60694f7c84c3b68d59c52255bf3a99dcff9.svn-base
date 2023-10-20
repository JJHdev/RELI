/**
******************************************************************************************
*** 파일명 : listReliefGive.js
*** 설명글 : 구제급여 지급 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.10.27    LSH   디자인적용 및 화면작업
*** 1.0         2021.12.06    LSH   설계변경에 따른 개발작업
******************************************************************************************
**/

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
	        // 칼럼정의
	        columns: [[
	            {field:'bizAreaNm'    ,width: 80,halign:'center',align:'center',title:'피해지역'},
	            {field:'bizOder'      ,width: 80,halign:'center',align:'center',title:'사업차수'},
	            {field:'exmnOder'     ,width: 80,halign:'center',align:'center',title:'조사차수'},
	            {field:'idntfcId'     ,width: 90,halign:'center',align:'center',title:'식별ID'},
	            {field:'sufrerNmMask' ,width:100,halign:'center',align:'center',title:'피해자명'},
	            {field:'giveAmt'      ,width:120,halign:'center',align: 'right',title:'지급금액', formatter: $.commFormat.number},
				{field:'rcognAmt'     ,width:120,halign:'center',align: 'right',title:'인정금액', formatter: $.commFormat.number},
				{field:'rcperLvlhAmt' ,width:120,halign:'center',align: 'right',title:'요양생활수당합계', formatter: $.commFormat.number},
	            {field:'giveDcsnYmd'  ,width:120,halign:'center',align:'center',title:'지급결정일자'}
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
        C_LIST.GRID.datagrid('options')['url'] = getUrl('/adm/relief/getListReliefGive.do');
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
            getUrl('/adm/relief/downReliefGiveExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
	},
	// 상세리셋
    //-------------------------------//
	doClear: function() {
		// 상세조회 데이터 제거
		P_SELECT = false;
		C_MCP.doReset();
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
					// 데이터 로드
					C_MCP.doLoad( data );
                }
            }
        );
	},
};

//============================================================================//
// [1] 의료비 탭 기능정의 
//----------------------------------------------------------------------------//
const C_MCP = {
	FORM : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#mcpForm');
		
	    // FORM VALIDATION RULE 정의
	    this.FORM.validate({
	        debug: false,
	        onsubmit: false,
	        onfocusout: false,
	        // 검증룰 정의
	        rules: {
	            rvwBgngYmd   : {required: true, dateHyphen:true}, // 조회기간 시작일자
	            rvwEndYmd    : {required: true, dateHyphen:true}, // 조회기간 종료일자
				lgcyRcognAmt : {minFormat: 0, maxFormat: 999999999999999}, // 기인정금액
				dpcnAmt      : {minFormat: 0, maxFormat: 999999999999999}, // 중복금액
				rdmAmt       : {minFormat: 0, maxFormat: 999999999999999}, // 환수금액
				rtrvlNeedAmt : {minFormat: 0, maxFormat: 999999999999999}, // 회수필요금액
				rcognAmt     : {minFormat: 0, maxFormat: 999999999999999}, // 인정금액
	            rdmDpstYmd   : {dateHyphen:true}, // 환수입금일자
	            giveYmd      : {required: true, dateHyphen:true}, // 지급일자
	        },
	        // 검증메세지 정의
	        messages: {
	            rvwBgngYmd   : {
					required:   '조회기간 시작일자는 필수 입력 사항입니다.',
					dateHyphen: '조회기간 시작일자를 형식에 맞게 입력하세요(예: 2022-01-01).'
				},
	            rvwEndYmd    : {
					required:   '조회기간 종료일자는 필수 입력 사항입니다.',
					dateHyphen: '조회기간 종료일자를 형식에 맞게 입력하세요(예: 2022-01-01).'
				},
	            lgcyRcognAmt : {
					minFormat:  $.validator.format("기인정금액에 {0} 이상의 값을 입력하세요."),
					maxFormat:  $.validator.format("기인정금액에 {0} 이하의 값을 입력하세요."),
				},
	            dpcnAmt      : {
					minFormat:  $.validator.format("중복금액에 {0} 이상의 값을 입력하세요."),
					maxFormat:  $.validator.format("중복금액에 {0} 이하의 값을 입력하세요."),
				},
	            rdmAmt       : {
					minFormat:  $.validator.format("환수금액에 {0} 이상의 값을 입력하세요."),
					maxFormat:  $.validator.format("환수금액에 {0} 이하의 값을 입력하세요."),
				},
	            rtrvlNeedAmt : {
					minFormat:  $.validator.format("회수필요금액에 {0} 이상의 값을 입력하세요."),
					maxFormat:  $.validator.format("회수필요금액에 {0} 이하의 값을 입력하세요."),
				},
	            rcognAmt     : {
					minFormat:  $.validator.format("인정금액에 {0} 이상의 값을 입력하세요."),
					maxFormat:  $.validator.format("인정금액에 {0} 이하의 값을 입력하세요."),
				},
	            rdmDpstYmd   : {
					dateHyphen: '환수입금일자를 형식에 맞게 입력하세요(예: 2022-01-01).'
				},
	            giveYmd    : {
					required:   '지급일자는 필수 입력 사항입니다.',
					dateHyphen: '지급일자를 형식에 맞게 입력하세요(예: 2022-01-01).'
				}
	        },
	        invalidHandler: validateHandler,
	        errorPlacement: validatePlacement
	    });

		$('#rvwBgngYmd').datebox({prompt:'시작일자'});
		$('#rvwEndYmd' ).datebox({prompt:'종료일자'});
		$('#rdmDpstYmd').datebox();
		$('#giveYmd'   ).datebox();

	    // 날짜 입력박스 하이픈(-) 자동삽입 이벤트
		bindDateHyphen( $('#rvwBgngYmd').datebox('textbox') );
		bindDateHyphen( $('#rvwEndYmd' ).datebox('textbox') );
		bindDateHyphen( $('#rdmDpstYmd').datebox('textbox') );
		bindDateHyphen( $('#giveYmd'   ).datebox('textbox') );

	    // 금액 숫자만입력/포맷처리 이벤트
		$('input.app-number').inputmask("numeric", {autoGroup:true, groupSeparator:","})

	    // 금액 입력시 자동계산 이벤트
		$('#lgcyRcognAmt').bind('blur', this.doCalculate); // 기인정금액
		$('#dpcnAmt'     ).bind('blur', this.doCalculate); // 중복금액
		$('#rdmAmt'      ).bind('blur', this.doCalculate); // 환수금액
		$('#rtrvlNeedAmt').bind('blur', this.doCalculate); // 회수필요금액
		$('#rcognAmt'    ).bind('blur', this.doCalculate); // 인정금액
		// 저장버튼
		$('#btnMcpSave').bind('click', this.doSave);
		// 초기화 완료
		this.INIT = true;
	},
	// 금액 입력시 자동계산
    //-------------------------------//
	doCalculate: function() {
		let amt = {
			lgcyRcognAmt : $.commUtil.nvlInt($('#lgcyRcognAmt').val()), // 기인정금액
			rtrvlNeedAmt : $.commUtil.nvlInt($('#rtrvlNeedAmt').val()), // 회수필요금액
			dpcnAmt      : $.commUtil.nvlInt($('#dpcnAmt'     ).val()), // 중복금액
			rdmAmt       : $.commUtil.nvlInt($('#rdmAmt'      ).val()), // 환수금액
			rcognAmt     : $.commUtil.nvlInt($('#rcognAmt'    ).val()), // 인정금액
		};
		let calcAmt = amt.rcognAmt - (
					+ amt.lgcyRcognAmt
					+ amt.rtrvlNeedAmt
					+ amt.dpcnAmt     
					+ amt.rdmAmt );
		// 지급총액 계산 (마이너스는 0으로 치환)
		$('#giveAmt').val( calcAmt < 0 ? 0 : calcAmt);
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
			$('#rvwBgngYmd').datebox({value: data['rvwBgngYmd']});
			$('#rvwEndYmd' ).datebox({value: data['rvwEndYmd' ]});
			$('#rdmDpstYmd').datebox({value: data['rdmDpstYmd']});
			$('#giveYmd'   ).datebox({value: data['giveYmd'   ]});

			// 저장버튼 표시
			$('#btnMcpSave').show();
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
		$('#btnMcpSave').hide();
	},
	// 의료비 저장
    //-------------------------------//
	doSave: function() {

		let f = C_MCP.FORM;
		
        // FORM VALIDATION 기능 활성화
        if (f.validate().settings)
            f.validate().settings.ignore = false;
        // FORM VALIDATION
        if (f.valid() === false)
            return false;

		// 처리모드
		f.find('input[name="mode"]').val(MODE.UPDATE);
		f.find('input[name="act"]' ).val('MCP');
		
		let data = f.serializeObject();
		
    	$.commMsg.confirm("의료비를 저장하시겠습니까?", function() {
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
	}
};

$(function() {
	
	P_SELECT = false;
	
	// 의료급여 초기화
	C_MCP.doInit();
	
	// 목록 초기화
	C_LIST.doInit();
});
