/**
******************************************************************************************
*** 파일명 : listFnrlCst.js
*** 설명글 : 구제급여지급 - 장의비 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2023.01.04    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 목록 GRID
	let P_FORM    = $('#searchForm'     ); // 검색폼
	let P_RFORM   = $('#registForm'     ); // 등록폼
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
            {field:'idntfcId'      ,width:100,halign:'center',align:'center',title:'식별ID'},
            {field:'aplyNo'        ,width:100,halign:'center',align:'center',title:'신청번호'},
            {field:'sufrerNmMask'  ,width:100,halign:'center',align:'center',title:'피해자명'},
            {field:'dthYmd'        ,width:120,halign:'center',align:'center',title:'사망일자'},
            {field:'fnrlCstGiveYmd',width:120,halign:'center',align:'center',title:'지급일자'}
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
    //========================================================//
    // REGIST FORM ELEMENTS 정의
    //--------------------------------------------------------//
	// 기준년도 콤보박스
	$('#fnrlCrtrYr').appComboBox({
		type:'static', 
		init: {code:'', text:'기준년도 선택'},
		rows: STORE.getYears(0, $.formatUtil.toYear),
		// 기준년도 선택시 확정금액 가져오기
		change: doChangeYear
	});

    // 장의비 검색처리
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
        P_GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListFnrlCst.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', P_FORM.serializeObject());
        return false;
    }

    // 장의비 검색리셋
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
    // 장의비 상세리셋
    //--------------------------------------------------------//
    function doClear() {
		// 상세조회 데이터 리셋
		P_DATA = false;
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 장의비 색상 클래스 리셋
		P_RFORM.find('input[name="fnrlCstAmtKor"]').removeClass('app-red');
		// 등록폼 기본값 정의
		$.formUtil.toForm({
			mode: MODE.INSERT
		}, P_RFORM);
		// 지급등록버튼 감춤
		$('#btnSave').hide();
        return false;
    }
    // 장의비 엑셀 다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/exmn/downFnrlCstExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }
    // 장의비 상세조회
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
					// 장의비 포맷 정의
					if (!$.commUtil.empty(data['fnrlCstAmt']))
						data['fnrlCstAmtKor'] = $.formatUtil.toKorMoney(data['fnrlCstAmt']);

					// 상세조회 데이터 정의
					P_DATA = data;
					// 폼데이터 로드
					P_RFORM.form('load', data);

					// 지급등록버튼 표시
					$('#btnSave').show();
                }
            }
        );
        return false;
    }
    // 장의비 지급등록하기
    //--------------------------------------------------------//
    function doSave() {

		//저장할 데이터 가져오기
		let data = P_RFORM.serializeObject();

		if ($.commUtil.empty(data['fnrlCrtrYr'])) {
			$.commMsg.alert('지급 기준년도는 필수 선택 사항입니다.');
			return false;
		}
		if ($.commUtil.empty(data['fnrlCstGiveYmd'])) {
			$.commMsg.alert('장의비 지급일자는 필수 입력 사항입니다.');
			return false;
		}
		let msg = '장의비 지급등록을 하시겠습니까?';
		if (P_DATA['fnrlCstYn' ] == 'Y' &&
			P_DATA['fnrlCstAmt'] != data['fnrlCstAmt']) {
			msg = '장의비 확정금액이 '
			    + '<span class="app-blue">'+$.formatUtil.toKorMoney(P_DATA['fnrlCstAmt']) + '</span>에서 '
				+ '<span class="app-red" >'+$.formatUtil.toKorMoney(data['fnrlCstAmt'  ]) + '</span>으로 '
				+ '변경되었습니다.<br>'
				+ '변경된 금액으로 장의비 지급을 변경하시겠습니까?';
		}
		$.commMsg.confirm(msg, function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveFnrlCst.do'), 
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
	
    // 지급기준년도 선택시 장의비 조회
    //--------------------------------------------------------//
    function doChangeYear() {
		P_RFORM.find('input[name="fnrlCstAmt"   ]').val('');
		P_RFORM.find('input[name="fnrlCstAmtKor"]').val('');
		// 장의비 조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getFnrlCstAmt.do'), {
				fnrlCrtrYr: $(this).val()
			},
            function(result) {
				let amt = $.commUtil.nvlInt(result.Data);
				P_RFORM.find('input[name="fnrlCstAmt"   ]').val( amt );
				P_RFORM.find('input[name="fnrlCstAmtKor"]').val( $.formatUtil.toKorMoney(amt) );
				if (P_DATA['fnrlCstAmt'] != amt)
					P_RFORM.find('input[name="fnrlCstAmtKor"]').addClass('app-red');
				else
					P_RFORM.find('input[name="fnrlCstAmtKor"]').removeClass('app-red');
				$.commMsg.alert('장의비 확정금액이 조회되었습니다.');
			}
		);
		return true;
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
	bindDateHyphen( $('#fnrlCstGiveYmd').datebox('textbox') );

	// 로딩시 검색실행
	doSearch();
});
