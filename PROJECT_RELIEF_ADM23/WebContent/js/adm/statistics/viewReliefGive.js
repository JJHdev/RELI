/**
******************************************************************************************
*** 파일명 : viewReliefGive.js
*** 설명글 : 구제급여 지급현황 조회 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.11.10    LSH    파일명변경 및 화면개발
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_ITEM = {
		// 사업별
		AREA: {
			GRID: $('#areaGrid'       ),
			FORM: $('#searchAreaForm' ),

			doInit: function() {
				let mode = 'AREA';	
			    // 기본값 정의
				this.FORM.find('[name="mode"]').val(mode);
				this.FORM.find('[name="srchStYear"]').val('2018');
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
				this.FORM.find('[name="srchStYear"]').val('2018');
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
		},
		aplyCnt:   function(v,r) { return P_FORMAT.count(v, r['aplyFltCnt'  ]); },
		rcognCnt1: function(v,r) { return P_FORMAT.count(v, r['rcognFltCnt1']); },
		rcognCnt2: function(v,r) { return P_FORMAT.count(v, r['rcognFltCnt2']); },
		rcognCnt3: function(v,r) { return P_FORMAT.count(v, r['rcognFltCnt3']); },
		bizAreaNm: function(v,r) {
			if (r['bizAreaCd'] == 'TOTAL')
				return v;
			return r['bizAreaNm']+'<br>'+r['bizDtlArea'];
		}
	};

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_ITEM['AREA'].GRID.datagrid({
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
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 칼럼정의
        columns: [[
            {field:'bizAreaNm'  ,width:120,halign:'center',align:'center',title:'구분'        , hstyler: P_FORMAT.header},
            {field:'giveTot'    ,width:120,halign:'center',align: 'right',title:'계'          , formatter:$.commFormat.numberPos, hstyler: P_FORMAT.header},
            {field:'giveAmt1'   ,width:120,halign:'center',align: 'right',title:'의료비'      , formatter:$.commFormat.numberPos, hstyler: P_FORMAT.header},
            {field:'giveAmt2'   ,width:120,halign:'center',align: 'right',title:'요양생활수당', formatter:$.commFormat.numberPos, hstyler: P_FORMAT.header},
            {field:'giveAmt3'   ,width:120,halign:'center',align: 'right',title:'장의비'      , formatter:$.commFormat.numberPos, hstyler: P_FORMAT.header},
            {field:'giveAmt4'   ,width:120,halign:'center',align: 'right',title:'유족보상비'  , formatter:$.commFormat.numberPos, hstyler: P_FORMAT.header},
            {field:'giveAmt5'   ,width:120,halign:'center',align: 'right',title:'재산피해<br>보상비', formatter:$.commFormat.numberPos, hstyler: P_FORMAT.header},
        ]]
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
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 칼럼정의
        columns: [[]]
    });

    //========================================================//
    // 연도별 FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchStYearY').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchEnYearY').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});

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
	$('#srchBizArea').combobox({
		prompt: '신청지역',
		url: getUrl('/com/cmm/getComboBizMng.do'),
		queryParams:  {upCdId: CODE.PROGRESS},
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
		
		if (mode == 'AREA') {
			doGridSearch(item.GRID, obj);
			return false;			
		}
		else {
			if (obj['srchStYear'] > obj['srchEnYear']) {
				$.commMsg.alert('조회 시작년도를 종료년도보다 이전으로 선택하세요.');
				return false;
			}
			let col1 = [
				{rowspan:2, field:'bizAreaNm',width:120,halign:'center',align:'center',title:'구분', formatter: P_FORMAT.bizAreaNm},
				{colspan:2, title:'계'}
			];
			let col2 = [
				{field:'giveAllTot',width:120,halign:'center',align:'center',title:'인원'  , formatter: function(v,r) { return P_FORMAT.count(v, r['giveFltTot']); }},
				{field:'giveAmtTot',width:120,halign:'center',align: 'right',title:'지급액', formatter: $.commFormat.numberPos }
			];
			
			for (let i = obj['srchStYear']; i <= obj['srchEnYear']; i++) {
				col1.push({colspan:2, title: i+'년'});
				col2.push({field:'giveAllCnt'+i,width:120,halign:'center',align:'center',title:'인원'  , formatter: function(v,r) { return P_FORMAT.count(v, r['giveFltCnt'+i]);}});
				col2.push({field:'giveAllAmt'+i,width:120,halign:'center',align: 'right',title:'지급액', formatter: $.commFormat.numberPos});
			}
			doGridSearch(item.GRID, obj, [col1,col2]);
			return false;			
		}
    }
    
	function doGridSearch(grid, params, changeColumns) {
		// 선택된 항목 CLEAR
		grid.datagrid('clearSelections');
        // 그리드 목록조회 URL
        grid.datagrid('options')['url'] = getUrl('/adm/statistics/getListReliefGive.do');
		
		// 칼럼변경이 있는 경우
		if (changeColumns) {
			grid.datagrid('options')['queryParams'] = params;
			grid.datagrid({columns: changeColumns});
		}
		// 칼럼변경이 없는 경우
		else {
	        // 검색폼 그리드 검색
	        grid.datagrid('load', params);		
		}
	};

    // 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
		let item = P_ITEM[$(this).data('mode')];
        $.formUtil.submitForm(
            getUrl('/adm/statistics/downReliefGiveExcel.do'), 
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
	P_ITEM['AREA'].doInit();
	P_ITEM['YEAR'].doInit();

	// 탭 컨트롤 처리
	doControlTab();
});
