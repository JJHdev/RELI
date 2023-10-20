/**
******************************************************************************************
*** 파일명 : viewDmgRcogn.js
*** 설명글 : 건강피해 인정현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.11.10    LSH    화면개발
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_ITEM = {
		// 1,2차통합
		TOTAL: {
			GRID: $('#totalGrid'       ),
			FORM: $('#searchTotalForm' ),

			doInit: function() {
				let mode = 'TOTAL';	
				this.FORM.find('[name="mode"]').val(mode);
			    $('#btnTotalSearch').data('mode', mode);
			    $('#btnTotalExcel' ).data('mode', mode);

			    // 검색버튼 클릭시 이벤트 처리
			    $('#btnTotalSearch').bind('click', doSearch);
			    // 엑셀버튼 클릭시 이벤트 처리
			    $('#btnTotalExcel' ).bind('click', doExcel);

				$('#btnTotalSearch').trigger('click');
				$('#btnTotalSearch').hide();
				$('#searchTotalForm').closest('.app-search-layout').hide();
			}
		},
		// 사업별
		AREA: {
			GRID: $('#areaGrid'       ),
			FORM: $('#searchAreaForm' ),

			doInit: function() {
				let mode = 'AREA';	
			    // 기본값 정의
				this.FORM.find('[name="mode"]').val(mode);
			    $('#btnAreaSearch').data('mode', mode);
			    $('#btnAreaExcel' ).data('mode', mode);
			    // 검색버튼 클릭시 이벤트 처리
			    $('#btnAreaSearch').bind('click', doSearch);
			    // 엑셀버튼 클릭시 이벤트 처리
			    $('#btnAreaExcel' ).bind('click', doExcel);

				$('#btnAreaSearch').trigger('click');
				$('#btnAreaSearch').hide();
				$('#searchAreaForm').closest('.app-search-layout').hide();
			}
		},
		// 연도별
		YEAR: {
			GRID: $('#yearGrid'       ),
			FORM: $('#searchYearForm' ),

			doInit: function() {
				let mode = 'YEAR';	
				this.FORM.find('[name="mode"]').val(mode);
			    $('#btnYearSearch').data('mode', mode);
			    $('#btnYearExcel' ).data('mode', mode);

			    // 검색버튼 클릭시 이벤트 처리
			    $('#btnYearSearch').bind('click', doSearch);
			    // 엑셀버튼 클릭시 이벤트 처리
			    $('#btnYearExcel' ).bind('click', doExcel);

				$('#btnYearSearch').trigger('click');
				$('#btnYearSearch').hide();
				$('#searchYearForm').closest('.app-search-layout').hide();
			}
		}
	};
	let P_FORMAT = {
		count: function(v1, v2) {
			if (v2 > 0)
				return $.commFormat.number(v1)
				     + '<br>('
                     + $.commFormat.number(v2)
                     + ')';
			return $.commFormat.numberPos(v1);
		}
	};

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_ITEM['TOTAL'].GRID.datagrid({
		fit: true,
		// 그리드 너비에 칼럼너비 맞춤
		fitColumns: true,
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
            {rowspan:3,field:'bizAreaNm',width:120,halign:'center',align:'center',title:'구분'},
            {rowspan:3,field:'reqCnt'   ,width:120,halign:'center',align:'center',title:'신청인원',formatter: $.commFormat.numberPos},
            {colspan:5,title:'판 정'}
		],[
			{colspan:3,title:'인 정'},
			{rowspan:2,field:'rjctCnt',width:120,halign:'center',align:'center',title:'미인정',formatter: $.commFormat.numberPos},
			{rowspan:2,field:'exmnCnt',width:120,halign:'center',align:'center',title:'조사중',formatter: $.commFormat.numberPos}
		],[
            {field:'apprCnt' ,width:110,halign:'center',align:'center',title:'계',formatter: $.commFormat.numberPos},
            {field:'liveCnt' ,width:110,halign:'center',align:'center',title:'생존',formatter: $.commFormat.numberPos},
            {field:'dthCnt'  ,width:110,halign:'center',align:'center',title:'사망',formatter: $.commFormat.numberPos},
        ]]
    });
    P_ITEM['AREA'].GRID.datagrid({
		fit: true,
		// 행단위 STRIPE 표시 안함
		striped: false,
		// 그리드 너비에 칼럼너비 맞춤
		fitColumns: true,
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
            {rowspan:2,field:'bizAreaNm'    ,width:120,halign:'center',align:'center',title:'구분'},
            {rowspan:2,field:'bizOderNm'    ,width:100,halign:'center',align:'center',title:'신청사업'},
            {rowspan:2,field:'aplyAllCnt'   ,width:120,halign:'center',align:'center',title:'신청인원<br>(중복인원제외)', 
				formatter: function(v,r) { return P_FORMAT.count(v, r['aplyFltCnt']); }
			},
            {colspan:3,title:'심의결과'}
		],[
            {field:'rcognAllCnt1' ,width:110,halign:'center',align:'center',title:'피해인정', 
				formatter: function(v,r) { return P_FORMAT.count(v, r['rcognFltCnt1']); }
			},
            {field:'rcognAllCnt2' ,width:110,halign:'center',align:'center',title:'미인정'  , 
				formatter: function(v,r) { return P_FORMAT.count(v, r['rcognFltCnt2']); }
			},
            {field:'rcognAllCnt3' ,width:110,halign:'center',align:'center',title:'조사중'  , 
				formatter: function(v,r) { return P_FORMAT.count(v, r['rcognFltCnt3']); }
			}
        ]],
		onLoadSuccess: function(data) {
			let txt = '';
			let idx = 0;
			let obj = {};
			let elm = $(this);
			$.each(data.rows, function(i,r) {
				if (txt == r['bizAreaNm']) {
					if (obj[txt]) {
						obj[txt]['rowspan']++;
					}
					else {
						obj[txt] = {index: idx, field: 'bizAreaNm', rowspan:2};
					}
				}
				txt = r['bizAreaNm'];
				idx = i;
			});
			$.each(obj, function(i,r) {
				// 행 병합 처리
                elm.datagrid('mergeCells',r);
			});
		}
    });
    P_ITEM['YEAR'].GRID.datagrid({
		fit: true,
		// 그리드 너비에 칼럼너비 맞춤
		fitColumns: true,
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
            {rowspan:2,field:'aplyYear',width:200,halign:'center',align:'center',title:'연도별', 
				formatter: function(v) {
					if ($.commUtil.nvl(v) == '')
						return '총계';
					return $.formatUtil.toYear(v);
				}
			},
            {colspan:2,title:'신청자수'},
			{colspan:2,title:'인정자수'}
		],[
            {field:'aplyDupCnt'  ,width:200,halign:'center',align:'center',title:'중복포함', formatter:$.commFormat.numberPos}, 
            {field:'aplyCnt'     ,width:200,halign:'center',align:'center',title:'중복제외', formatter:$.commFormat.numberPos}, 
            {field:'aplcntDupCnt',width:200,halign:'center',align:'center',title:'중복포함', formatter:$.commFormat.numberPos}, 
            {field:'aplcntCnt'   ,width:200,halign:'center',align:'center',title:'중복제외', formatter:$.commFormat.numberPos}, 
        ]]
    });

    //========================================================//
    // 연도별 FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchStYearY').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchEnYearY').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});

    //========================================================//
    // 1,2차통합 FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchStYearT').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchEnYearT').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});

    //========================================================//
    // 사업별 FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchType').appComboBox({type: 'static', rows: STORE.TERM_TYPE, change: function() {
			if ($(this).val() == 'Y') {
				$('#srchStMnth').hide();
				$('#srchEnMnth').hide();
			}
			else {
				$('#srchStMnth').show();
				$('#srchEnMnth').show();
			}
		}
	});
	$('#srchStYear').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchEnYear').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchStMnth').appComboBox({type:'static', rows: STORE.getMonths($.formatUtil.toMonth)});
	$('#srchEnMnth').appComboBox({type:'static', rows: STORE.getMonths($.formatUtil.toMonth)});
	$('#srchBizOder').combobox({
		prompt: '신청사업',
		data: STORE.BIZ_ODER,
		loadFilter: function(data) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		}
	});
	$('#srchBizArea').combobox({
		prompt: '신청지역',
		url: getUrl('/com/cmm/getComboBizMng.do'),
		queryParams:  {upCdId: CODE.PROGRESS},
		loadFilter: function(data) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		}
	});
	$('#srchRcognStus').combobox({
		prompt: '심의결과',
		url: getUrl('/com/cmm/getComboCode.do'),
		queryParams:  {upCdId: CODE.RCOGNSTUS},
		loadFilter: function(data) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		}
	});

    // 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		let mode = $(this).data('mode');
		let item = P_ITEM[mode];
		let obj  = item.FORM.serializeObject();
		if (mode == 'YEAR' &&
			obj['srchStYear'] > obj['srchEnYear']) {
			$.commMsg.alert('조회 시작년도를 종료년도보다 이전으로 선택하세요.');
			return false;
		}
		doGridSearch(item.GRID, obj);
        return false;
    }
    
	function doGridSearch(grid, params) {
		// 선택된 항목 CLEAR
		grid.datagrid('clearSelections');
        // 그리드 목록조회 URL
        grid.datagrid('options')['url'] = getUrl('/adm/statistics/getListDmgRcogn.do');
        // 검색폼 그리드 검색
        grid.datagrid('load', params);		
	};

    // 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
		let item = P_ITEM[$(this).data('mode')];
        $.formUtil.submitForm(
            getUrl('/adm/statistics/downDmgRcognExcel.do'), 
            {formId : item.FORM.prop('id')}
        );
        return false;
    }

    // 탭컨트롤
    //--------------------------------------------------------//
    function doControlTab() {
		// 탭클릭 이벤트 처리
		$(".statWrap > .tabWrap li").on("click", function() {
			var idx = $(this).index();
			var mode = $(this).data('mode');
			$(this).parent().find("li").removeClass("on");
			$(this).addClass("on");
			$('.tabInnerFrom').removeClass("on");
			$('.tabInnerFrom').eq(idx).addClass("on");
			
			P_ITEM[mode].GRID.datagrid('getPanel').panel('maximize');
			
		})
		// 첫번째 탭 클릭 이벤트 발생
		$(".statWrap > .tabWrap li").eq(0).trigger('click');
    }
	
    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//
	P_ITEM['TOTAL'].doInit();
	P_ITEM['AREA'].doInit();
	P_ITEM['YEAR'].doInit();

	// 탭 컨트롤 처리
	doControlTab();
});
