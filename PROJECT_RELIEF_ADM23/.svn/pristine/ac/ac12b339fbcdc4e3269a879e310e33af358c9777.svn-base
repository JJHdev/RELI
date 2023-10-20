/**
******************************************************************************************
*** 파일명 : listBrvfmRwrdExcel.js
*** 설명글 : 구제급여지급 - 유족보상비 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.01.05    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 목록 GRID
	let P_FORM    = $('#searchForm'     ); // 검색폼
	let P_RFORM   = $('#registForm'     ); // 등록폼
	let P_TABLE   = $('#appRegistTable' ); // 구제급여 집계 테이블
	let P_SROW    = false;                 // 선택행 KEY정보
	let P_DATA    = false;                 // 상세조회 데이터

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
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
            {field:'idntfcId'         ,width:100,halign:'center',align:'center',title:'식별ID'},
            {field:'aplyNo'           ,width:100,halign:'center',align:'center',title:'신청번호'},
            {field:'sufrerNmMask'     ,width:100,halign:'center',align:'center',title:'피해자명'},
            {field:'lastDmgeGrdCd'    ,width:100,halign:'center',align:'center',title:'피해등급', formatter: $.formatUtil.toGrdNm},
            {field:'brvfmRwmnyGiveYmd',width:120,halign:'center',align:'center',title:'지급일자'}
        ]],
        // 행선택시 상세조회
        onSelect: doSelect,
		// 행선택한 정보가 있을 경우 해당정보 상세조회
		onLoadSuccess: function() {
			if (P_SROW) {
				P_GRID.datagrid('selectRecord', P_SROW);
			}
		}
    });

	// 구제급여 집계 테이블 정의
	P_TABLE = P_TABLE.appTableLayout({
		wrapCls:   'tableWrap type5',
		wrap:      true,
		headSpace: true,
		tailSpace: true,
		columns: [
			{name: 'giveText', label: '지급구분'},
			{name: 'giveAmt' , label: '총액', formatter: $.commFormat.number},
		]
	});

    //========================================================//
    // SEARCH FORM ELEMENTS 정의
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

    // 유족보상비 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 검색버튼 클릭인 경우 선택행 리셋
		if ($(this).attr('id')=='btnSearch')
			P_SROW = false;
		// 상세조회 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListBrvfmRwrd.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', P_FORM.serializeObject());
        return false;
    }

    // 유족보상비 검색리셋
    //--------------------------------------------------------//
    function doReset() {
		// 선택행 리셋
		P_SROW = false;
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');
        // 검색처리
        doSearch();
        return false;
    }
    // 유족보상비 상세리셋
    //--------------------------------------------------------//
    function doClear() {
		// 상세조회 데이터 리셋
		P_DATA = false;
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 장의비 색상 클래스 리셋
		P_RFORM.find('input[name="brvfmRwrdAmtKor"]').removeClass('app-red');
		// 등록폼 기본값 정의
		$.formUtil.toForm({
			mode: MODE.INSERT
		}, P_RFORM);
		// 구제급여집계 테이블 리셋
		doResetTable();
		// 지급등록버튼 감춤
		$('#btnSave').hide();
        return false;
    }
    // 유족보상비 엑셀 다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/exmn/downBrvfmRwrdExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }
    // 유족보상비 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {
        var params = {
			bizAreaCd : row['bizAreaCd'],
			bizOder   : row['bizOder'  ],
			exmnOder  : row['exmnOder' ],
			aplyNo    : row['aplyNo'   ]
        };
		// 상세조회 항목 CLEAR
		doClear();
		// 선택행 설정
		P_SROW = row['aplyNo'];

		// 본조사정보 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getMnsvy.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
					// 유족보상비 포맷 정의
					if (!$.commUtil.empty(data['brvfmRwrdAmt']))
						data['brvfmRwrdAmtKor'] = $.formatUtil.toKorMoney(data['brvfmRwrdAmt']);
						
					// 상세조회 데이터 정의
					P_DATA = data;
					// 폼데이터 로드
					P_RFORM.form('load', data);

					// 피해등급이 있는 경우 구제급여 집계 조회 (단,잔액계산 제외)
					if (!$.commUtil.empty(data['lastDmgeGrdCd']))
						doLoadTable();

					// 지급등록버튼 표시
					$('#btnSave').show();
                }
            }
        );
        return false;
    }
    // 유족보상비 지급등록하기
    //--------------------------------------------------------//
    function doSave() {

		//저장할 데이터 가져오기
		let data = P_RFORM.serializeObject();

		// 장의비 등록이 되지 않은 경우 지급등록 불가
		if (P_DATA['fnrlCstYn'] != 'Y') {
			$.commMsg.alert('장의비 지급등록을 먼저 수행하셔야 합니다.');
			return false;
		}
		if ($.commUtil.empty(data['lastDmgeGrdCd'])) {
			$.commMsg.alert('피해등급은 필수 입력 사항입니다.');
			return false;
		}
		if ($.commUtil.empty(data['brvfmRwmnyGiveYmd'])) {
			$.commMsg.alert('유족보상비 지급일자는 필수 입력 사항입니다.');
			return false;
		}
		let msg = '유족보상비 지급등록을 하시겠습니까?';
		if (!$.commUtil.empty(P_DATA['lastDmgeGrdCd']) &&
			P_DATA['lastDmgeGrdCd'] != data['lastDmgeGrdCd']) {
			msg = '피해등급을 '
			    + '<span class="app-blue">'+$.formatUtil.toGrdNm(P_DATA['lastDmgeGrdCd']) + '</span>에서 '
				+ '<span class="app-red" >'+$.formatUtil.toGrdNm(data['lastDmgeGrdCd'  ]) + '</span>으로 '
				+ '수정하셨습니다.<br>'
				+ '수정된 피해등급으로 유족보상비 지급등록을 하시겠습니까?';
		}
		$.commMsg.confirm(msg, function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveBrvfmRwrd.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							doSearch();
						});
                    });
                }
            );
		});
        return false;
    }
    // 구제급여 집계 테이블 데이터 로드
    //--------------------------------------------------------//
    function doLoadTable() {

		let params = P_RFORM.serializeObject();

    	// 유족보상비 구제급여 집계 내역 조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getBrvfmRwrdAmt.do'), 
			params,
            function(result) {
                let data = result.Data;
                if (data) {
					// 구제급여집계 테이블 데이터 로드
					P_TABLE.loadData([
						{giveText:'유족보상비'  , giveAmt: data['brvfmRwrdAmt']},
						{giveText:'의료비'      , giveAmt: data['mcpDtlsSum'  ]},
						{giveText:'요양생활수당', giveAmt: data['rcperLvlhSum']}
					]);
					// 유족보상비 잔액 계산
					let amt = $.commUtil.nvlInt(data['brvfmRwrdAmt'])
					        - $.commUtil.nvlInt(data['mcpDtlsSum'  ])
					        - $.commUtil.nvlInt(data['rcperLvlhSum']);
					// 잔액이 음수인 경우 0으로 치환
					if (amt < 0)
						amt = 0;
					P_RFORM.find('input[name="brvfmRwrdAmt"   ]').val( amt );
					P_RFORM.find('input[name="brvfmRwrdAmtKor"]').val( $.formatUtil.toKorMoney(amt) );
					if (P_DATA['brvfmRwrdAmt'] != amt)
						P_RFORM.find('input[name="brvfmRwrdAmtKor"]').addClass('app-red');
					else
						P_RFORM.find('input[name="brvfmRwrdAmtKor"]').removeClass('app-red');
					//$.commMsg.alert('유족보상비 잔액이 조회되었습니다.');
				}
			}
		);
	}
    // 구제급여집계 테이블 데이터 리셋
    //--------------------------------------------------------//
    function doResetTable() {
		P_TABLE.loadData([
			{giveText:'유족보상비'  , giveAmt: 0},
			{giveText:'의료비'      , giveAmt: 0},
			{giveText:'요양생활수당', giveAmt: 0},
		]);
		P_RFORM.find('input[name="brvfmRwrdAmt"   ]').val('');
		P_RFORM.find('input[name="brvfmRwrdAmtKor"]').val('');
	}

    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);
    // 지급등록버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
	
    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
    bindEnter($('#srchAplcntNm'), $('#btnSearch'));
	// 2023.01.06 식별ID 검색어 입력 엔터 이벤트 처리
	bindEnter($('#srchIdntfcId'), $('#btnSearch'));
    // 날짜 입력박스 하이픈(-) 자동삽입 이벤트
	bindDateHyphen( $('#brvfmRwmnyGiveYmd').datebox('textbox') );
	// 피해등급 입력시 구제급여 집계 로드	
	$('#lastDmgeGrdCd').bind('blur', doLoadTable);
	// 로딩시 검색실행
	doSearch();
});
