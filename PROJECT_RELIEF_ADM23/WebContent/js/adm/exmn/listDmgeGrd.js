/**
******************************************************************************************
*** 파일명 : listDmgeGrd.js
*** 설명글 : 피해등급관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2022.12.22    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 목록 GRID
	let P_FORM    = $('#registForm'     ); // 등록폼
	let P_IGRID   = $('#appItemGrid'    ); // 세부 목록 GRID
	let P_COUNT   = 5; // [2023.01.04 LSH] 세부목록의 항목 표시 건수

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
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
            {field:'dmgeGrdYr'    ,width:150,halign:'center',align:'center',title:'기준년도'},
            {field:'crtrIncomeAmt',width:250,halign:'center',align:'center',title:'2인가구 기준 중위소득', formatter: $.commFormat.number},
            {field:'dmgeGrdCnt'   ,width:100,halign:'center',align:'center',title:'세부등급'},
        ]],
        // 행선택시 상세조회
        onSelect: doSelect
    });

	let P_FORMAT = {
		formatGrd: function(v) {
			if (v == CODE.GRD_ETC_CD)
				return CODE.GRD_ETC_NM;
			return v;
		},
		inputScre: function(v,r) {
			return [
				'<input type="text" name="svrtyBgngScre" value="'+$.commUtil.nvl(r['svrtyBgngScre'])+'" maxlength="8" style="width:60px" class="app-score app-r"/>',
				'<input type="text" name="svrtyEndScre"  value="'+$.commUtil.nvl(r['svrtyEndScre' ])+'" maxlength="8" style="width:60px" class="app-score app-r"/>'
			].join(' ');
		},
		inputRate: function(v,r) {
			return '<input type="text" name="grdRate" value="'+$.commUtil.nvl(v)+'" maxlength="8" class="w100 app-ratio app-r"/>';
		},
		inputAmt: function(v,r) {
			return '<input type="text" name="grdAmt" value="'+$.commFormat.number($.commUtil.nvl(v))+'" maxlength="15" class="w100 app-r app-readonly" readonly />';
		},
		formatBtn: function(v,r,rowIndex) {
			if (v == MODE.INSERT && r['dmgeGrdCd'] != CODE.GRD_ETC_CD)
				return '<a href="javascript:void(0);" data-index="'+rowIndex+'" class="app-minus app-remove-btn" title="삭제"><i class="fa fa-minus"></i></a>';
			return ' ';
		}
	};

	P_IGRID.datagrid({
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
        // KEY값필드
        idField: 'dmgeGrdCd',
        // 칼럼정의
        columns: [[
            {rowspan:2,field:'dmgeGrdCd'    ,width: 80,halign:'center',align:'center', title: '등급', formatter: P_FORMAT.formatGrd},
			{rowspan:2,field:'svrtyBgngScre',width:150,halign:'center',align:'center', title: '중증도<br>점수기준', formatter: P_FORMAT.inputScre},
            {colspan:2,title:'요양생활수당'},
			// [2023.01.04] 현재 보류처리함 (권이사님요청)
			//{rowspan:2,field:'mode', width: 40, halign:'center',align:'center', title:'삭제', formatter: P_FORMAT.formatBtn}
		],[
            {field:'grdRate',width:100,halign:'center',align:  'left',title:'비율', formatter: P_FORMAT.inputRate}, 
            {field:'grdAmt' ,width:200,halign:'center',align:'center',title:'금액', formatter: P_FORMAT.inputAmt }
        ]],
		onLoadSuccess: function(data) {
			$.each(data.rows, function(i) {
				data.rows[i]['mode'] = MODE.UPDATE;
			});
			// [2023.01.04 LSH] 5개의 행이 보여지도록 처리
			let len = data.rows.length;
			if (len > 0 && len <= P_COUNT) {
				let index = len-1; // 추가할 INDEX
				for (let i = 0; i <= (P_COUNT-len); i++) {
					P_IGRID.datagrid('insertRow', {index: (index+i), row: getAppendRow()});
				}
			}
			// 그리드 이벤트 바인딩
			bindGridEvent();
		}
	});

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#dmgeGrdYr').appComboBox({
		type:'static',
		rows: STORE.getYears(0, $.formatUtil.toYear),
		// 값변경 이벤트
		change: function() {
			let mode  = P_FORM.find('input[name="mode"]').val();
			let orgYr = P_FORM.find('input[name="dmgeGrdYrOrg"]').val();
			let newYr = $(this).val();
			if (mode == MODE.UPDATE && 
				newYr != orgYr) {
				let msg = '수정 상태에선 기준년도 변경이 불가합니다.<br>'
				        + '다른 기준년도를 등록하시려면 신규등록을 클릭해 주세요.'
				$.commMsg.alert(msg, function() {
					$('#dmgeGrdYr').val(orgYr);
				});
				return false;
			}
			return true; 
		}
	});
	
    //========================================================//
    // FORM VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_FORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            dmgeGrdYr: {
				required: true
			},
            crtrIncomeAmt: {
				required: true
			}
        },
        // 검증메세지 정의
        messages: {
            dmgeGrdYr: {
				required: '기준년도는 필수 선택 사항입니다.'
			},
            crtrIncomeAmt: {
				required: '기준중위소득은 필수 입력 사항입니다.'
			},
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 피해등급관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListDmgeGrdGroup.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', {});
		// 신규등록
		doRegist();
        return false;
    }
	
    // 피해등급관리 상세리셋
    //--------------------------------------------------------//
    function doClear() {
        // 등록폼 리셋
        P_FORM.form('reset');
		// 등록폼 기본값 정의
		$.formUtil.toForm({
			mode: MODE.INSERT
		}, P_FORM);
		// 선택된 항목 CLEAR
		P_IGRID.datagrid('clearSelections');
		P_IGRID.datagrid('loadData', {"total":0,"rows":[]});

        return false;
    }

    // 피해등급관리 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {
        var params = {
            dmgeGrdYr: row['dmgeGrdYr']
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getDmgeGrdGroup.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
					// 이전기준년도 정의
					data['dmgeGrdYrOrg'] = data['dmgeGrdYr'];
					// 폼값 맵핑
					$.formUtil.toForm(data, P_FORM);
					// 선택된 항목 CLEAR
					P_IGRID.datagrid('clearSelections');
					// 피해등급목록 로드
			       	P_IGRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListDmgeGrd.do');
			       	P_IGRID.datagrid('load', params);
                }
            }
        );
        return false;
    }

    // 피해등급관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
		doClear();
		// [2023.01.04 LSH] 현재보류처리함 (권이사님요청)
		// 2개의 ROW가 등록됨
		//P_IGRID.datagrid('appendRow', getAppendRow());

		// [2023.01.04 LSH] 총 5개의 행이 표시되도록 처리
		for (let i = 0; i < P_COUNT; i++) {
			P_IGRID.datagrid('appendRow', getAppendRow());
		}
		// 등급외 항목
		P_IGRID.datagrid('appendRow', getAppendRow(CODE.GRD_ETC_CD));
        return false;
    }


    // 피해등급관리 등급추가하기
	// [2023.01.04 LSH] 현재보류처리함 (권이사님요청)
    //--------------------------------------------------------//
    function doAppend() {
		// 선택된 항목 CLEAR
		P_IGRID.datagrid('clearSelections');

		let index = 0; // 추가할 INDEX
		let count = 0; // 현재목록 건수

		// 현재 목록 데이터
		let rows = P_IGRID.datagrid('getRows');
		if (rows) {
			count = rows.length;
			// '등급외' 항목은 가장 하단에 위치하도록 중간에 삽입
			if (count > 1)
				index = count-1;
		}
		P_IGRID.datagrid('insertRow', {index: index, row: getAppendRow()});
		if (count == 0) {
			P_IGRID.datagrid('appendRow', getAppendRow(CODE.GRD_ETC_CD));
		}
		// 숫자입력 이벤트 바인딩
		bindGridEvent();
        return false;
    }

    // 피해등급관리 등급삭제하기
    //--------------------------------------------------------//
    function doRemove() {
	
		let index = $(this).data('index');
		let row   = P_IGRID.datagrid('getRows')[index];
		//let row = P_IGRID.datagrid('getSelected');
		if (row == null) {
			$.commMsg.alert('삭제할 항목을 선택하세요.');
			return false;
		}
		if (row['dmgeGrdCd'] == CODE.GRD_ETC_CD) {
			$.commMsg.alert('['+CODE.GRD_ETC_NM+'] 항목은 삭제하실 수 없습니다.');
			return false;
		}
		if (row['mode'] == MODE.UPDATE) {
			$.commMsg.alert('현재 추가한 항목만 삭제가 가능합니다.');
			return false;
		}
		// 추가된 행의 최대등급 가져오기
		let max = getMaxGrdCd();
		if (parseInt(row['dmgeGrdCd']) < max) {
			$.commMsg.alert('높은 등급부터 순서대로 삭제하세요.');
			return false;
		}
		// 행 삭제
		P_IGRID.datagrid('deleteRow', index);
		//P_IGRID.datagrid('deleteRow', P_IGRID.datagrid('getRowIndex', row));
        return false;
    }

    // 피해등급관리 저장하기
    //--------------------------------------------------------//
    function doSave() {

        // FORM VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;
        
        //FORM VALIDATION
        if (P_FORM.valid() === false)
            return false;
		
		//저장할 데이터 가져오기
		let data = getSaveData();

		//등급그리드 검증
		if (doValidate(data) === false)
			return false;

		if (data['dmgeGrdList'] == false)
			return false;
		
		$.commMsg.confirm("피해등급정보를 저장하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/saveDmgeGrd.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							doRegist();
							doSearch();
						});
                    });
                }
            );
		});
        return false;
    }

    // 추가된 등급의 최대값 가져오기
    //--------------------------------------------------------//
	function getMaxGrdCd() {
		let rows = P_IGRID.datagrid('getRows');
		if (rows && 
			rows.length > 0) {
			let max = 0;
			$.each(rows, function(i,r) {
				if (r['dmgeGrdCd'] == CODE.GRD_ETC_CD)
					return true;
				if (r['dmgeGrdCd'] > max)
					max = r['dmgeGrdCd'];
			});
			return parseInt(max);
		}
		return 0;
	}

    // 행 데이터 가져오기
    //--------------------------------------------------------//
	function getAppendRow( grdCd ) {
		let row = {
			mode         : MODE.INSERT,        
			dmgeGrdCd    : '1',
			svrtyBgngScre: '0',
			svrtyEndScre : '0',
			grdRate      : '0',
			grdAmt       : '0',
		};
		if (grdCd == CODE.GRD_ETC_CD) {
			row['dmgeGrdCd'] = grdCd;
		}
		else {
			// 추가된 행의 최대등급 가져오기
			row['dmgeGrdCd'] = getMaxGrdCd()+1;
		}
		return row;
	}
	
    // 그리드 이벤트 바인딩
    //--------------------------------------------------------//
    function bindGridEvent() {
		let p = P_IGRID.datagrid('getPanel');
		p.find('input.app-ratio').each(function() {
			// 비율값만 입력 이벤트
			$(this).inputmask("numeric", {allowMinus: false, digits: 3, max: 100});
			// 비율입력후 금액 계산 처리
			$(this).unbind('blur').bind('blur', doCalculate);
		});
		p.find('input.app-score').each(function() {
			// 점수만 입력 이벤트
			$(this).inputmask("numeric", {allowMinus: false, digits: 0, max: 100});
		});
		p.find('a.app-remove-btn').each(function() {
			// 삭제 버튼 클릭 이벤트
			$(this).unbind('click').bind('click', doRemove);
		});
	}
	
    // 비율 및 중위소득 입력시 금액 계산
    //--------------------------------------------------------//
    function doCalculate() {
		// 중위소득
		let camt = $.commUtil.nvlInt(P_FORM.find('input[name="crtrIncomeAmt"]').val());
		let rows = P_IGRID.datagrid('getRows');
		if (rows && 
			rows.length > 0) {
			$.each(rows, function(i,r) { 
				let rate = $.commUtil.nvlFloat(P_FORM.find('input[name="grdRate"]').eq(i).val());
				// 비율에 따른 금액 계산 (10단위 이하 버림)
				let gamt = Math.floor((camt * rate / 100) / 10) * 10;
				P_FORM.find('input[name="grdAmt"]').eq(i).val($.commFormat.number(gamt));
			});
		}
	}

    // 입력값 가져오기
    //--------------------------------------------------------//
    function getSaveData() {
		let fobj = P_FORM.serializeObject();
		let data = {
			mode:          fobj['mode'],
			dmgeGrdYr:     fobj['dmgeGrdYr'],
			dmgeGrdYrOrg:  fobj['dmgeGrdYrOrg'],
			crtrIncomeAmt: $.commUtil.nvlInt(fobj['crtrIncomeAmt']),
			dmgeGrdList:   []
		};		
		let gridRows = P_IGRID.datagrid('getRows');
		if (gridRows && 
			gridRows.length > 0) {
			$.each(gridRows, function(i,r) { 
				let row = {
					mode:          r['mode'     ],
					dmgeGrdCd:     r['dmgeGrdCd'],
					dmgeGrdNm:     r['dmgeGrdCd']+'등급',
					dmgeGrdYr:     data['dmgeGrdYr'    ],
					crtrIncomeAmt: data['crtrIncomeAmt'],
					svrtyBgngScre: $.commUtil.nvlInt  (P_FORM.find('input[name="svrtyBgngScre"]').eq(i).val()),
					svrtyEndScre:  $.commUtil.nvlInt  (P_FORM.find('input[name="svrtyEndScre" ]').eq(i).val()),
					grdRate:       $.commUtil.nvlFloat(P_FORM.find('input[name="grdRate"      ]').eq(i).val()),
					grdAmt:        $.commUtil.nvlInt  (P_FORM.find('input[name="grdAmt"       ]').eq(i).val()),
				};
				if (row['dmgeGrdCd'] == CODE.GRD_ETC_CD)
					row['dmgeGrdNm'] =  CODE.GRD_ETC_NM;
				data['dmgeGrdList'].push(row);
			});
		}
		return data;
	}

    // 입력값 검증하기
    //--------------------------------------------------------//
    function doValidate( data ) {
	
		// 수정모드에서 기준년도를 바꾼 경우
		if (data['mode'] == MODE.UPDATE &&
			data['dmgeGrdYr'] != data['dmgeGrdYrOrg']) {
			$.commMsg.alert('수정상태에선 기준년도 변경이 불가합니다.', function() {
				$('#dmgeGrdYr').val(data['dmgeGrdYrOrg']);
			});
			return false;
		}
		if ($.commUtil.empty(data['dmgeGrdList'])) {
			$.commMsg.alert('저장할 내용이 없습니다.');
			return false;
		}
		if (data['crtrIncomeAmt'] <= 0) {
			$.commMsg.alert('기준중위소득을 0보다 큰 값으로 입력하세요.');
			return false;
		}
		
		let valid = true;

		$.each(data['dmgeGrdList'], function(i,r1) { 
			let grdCd = r1['dmgeGrdCd'];
			let grdNm = r1['dmgeGrdNm'];
			let sScre = r1['svrtyBgngScre'];
			let eScre = r1['svrtyEndScre' ];
			
			// 점수범위 검증
			if (sScre >= eScre) {
				$.commMsg.alert('['+grdNm+'] 항목 중증도점수기준의 시작점수는 종료점수보다 낮아야 합니다.');
				valid = false;
				return false;
			}
			if (valid === false)
				return false;

			$.each(data['dmgeGrdList'], function(j,r2) { 
				
				if (valid === false)
					return false;

				let cgrdCd = r2['dmgeGrdCd'];
				let cgrdNm = r2['dmgeGrdNm'];
				let csScre = r2['svrtyBgngScre'];
				let ceScre = r2['svrtyEndScre' ];
				// 같은행은 SKIP
				if (grdCd == cgrdCd)
					return true;
				if (sScre >= ceScre && 
					eScre >  ceScre)
					return true;
				if (sScre <  csScre && 
					eScre <= csScre)
					return true;
				// 점수범위 중복검증
				$.commMsg.alert('['+cgrdNm+'] 항목 점수의 범위가 중복됩니다.');
				valid = false;
				return false;
			});
		});
		if (valid === false)
			return false;
		return true;
	}

    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//

    // 등록버튼 클릭시 이벤트처리
    $('#btnRegist').bind('click', doRegist);
    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
    // +버튼 클릭시 이벤트처리
	// [2023.01.04 LSH] 현재 보류처리함 (권이사님요청) 
    //$('#btnAppend').bind('click', doAppend);
	// 기준중위소득 숫자만 입력 이벤트
	$('#crtrIncomeAmt').inputmask("numeric", {allowMinus: false, rightAlign: true, autoGroup:true,groupSeparator:","});
	// 기준중위소득 입력후 금액 자동계산
	$('#crtrIncomeAmt').bind("blur", doCalculate);

	// 로딩시 검색실행
	doSearch();
});

