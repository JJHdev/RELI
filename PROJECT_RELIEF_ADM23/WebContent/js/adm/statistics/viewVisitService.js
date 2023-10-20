/**
******************************************************************************************
*** 파일명 : viewVisitService.js
*** 설명글 : 찾아가는 서비스 통계 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.11.11    LSH    화면개발
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_ITEM = {
		// 연도별실적
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
		},
		// 서비스제공율
		RATE: {
			GRID: $('#rateGrid'       ),
			FORM: $('#searchRateForm' ),

			doInit: function() {
				let mode = 'RATE';	
			    // 기본값 정의
				this.FORM.find('[name="mode"]').val(mode);
				this.FORM.find('[name="srchStYear"]').val('2018');
			    $('#btnRateSearch').data('mode', mode);
			    $('#btnRateExcel' ).data('mode', mode);
			    // 검색버튼 클릭시 이벤트 처리
			    $('#btnRateSearch').bind('click', doSearch);
			    // 엑셀버튼 클릭시 이벤트 처리
			    $('#btnRateExcel' ).bind('click', doExcel);

				$('#btnRateSearch').trigger('click');
				$('#btnRateSearch').hide();
				$('#searchRateForm').closest('.app-search-layout').hide();
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
		header:    function() { return {css:'app-h100'}; }
	};

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
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
            {rowspan:2,field:'servYear' ,width:120,halign:'center',align:'center',title:'연  도'},
            {colspan:3,title:'구제급여'},
            {colspan:3,title:'소송지원'},
            {colspan:3,title:'합    계'}
		],[
            {field:'servOutCntPP01' ,width:110,halign:'center',align:'center',title:'현장지원<br>건수', formatter:$.commFormat.numberPos},
            {field:'servAllCntPP01' ,width:110,halign:'center',align:'center',title:'지원건수', formatter:$.commFormat.numberPos},
            {field:'servAllMenPP01' ,width:110,halign:'center',align:'center',title:'지원인원', formatter:$.commFormat.numberPos},
            {field:'servOutCntPP02' ,width:110,halign:'center',align:'center',title:'현장지원<br>건수', formatter:$.commFormat.numberPos},
            {field:'servAllCntPP02' ,width:110,halign:'center',align:'center',title:'지원건수', formatter:$.commFormat.numberPos},
            {field:'servAllMenPP02' ,width:110,halign:'center',align:'center',title:'지원인원', formatter:$.commFormat.numberPos},
            {field:'totalOutCnt'    ,width:110,halign:'center',align:'center',title:'현장지원<br>건수', formatter:$.commFormat.numberPos},
            {field:'totalAllCnt'    ,width:110,halign:'center',align:'center',title:'지원건수', formatter:$.commFormat.numberPos},
            {field:'totalAllMen'    ,width:110,halign:'center',align:'center',title:'지원인원', formatter:$.commFormat.numberPos},
        ]],
		onLoadSuccess: function(data) {
		}
    });
    P_ITEM['RATE'].GRID.datagrid({
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
            {rowspan:2,field:'servYear' ,width:120,halign:'center',align:'center',title:'연  도'},
            {colspan:3,title:'서비스 제공자'},
            {colspan:3,title:'피해구제 신청인'},
            {rowspan:2,field:'servRate' ,width:200,halign:'center',align:'center',title:'서비스 제공율', formatter:$.commFormat.rate}
		],[
            {field:'servSplyCntPP01' ,width:110,halign:'center',align:'center',title:'피해구제', formatter:$.commFormat.numberPos},
            {field:'servSplyCntPP02' ,width:110,halign:'center',align:'center',title:'소송지원', formatter:$.commFormat.numberPos},
            {field:'servSplyTot'     ,width:110,halign:'center',align:'center',title:'소    계', formatter:$.commFormat.numberPos},
            {field:'servAplyCntPP01' ,width:110,halign:'center',align:'center',title:'피해구제', formatter:$.commFormat.numberPos},
            {field:'servAplyCntPP02' ,width:110,halign:'center',align:'center',title:'소송지원', formatter:$.commFormat.numberPos},
            {field:'servAplyTot'     ,width:110,halign:'center',align:'center',title:'소    계', formatter:$.commFormat.numberPos},
        ]]
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchStYearR').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchEnYearR').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchStYearY').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});
	$('#srchEnYearY').appComboBox({type:'static', rows: STORE.getYears(0, $.formatUtil.toYear)});

    // 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		let mode = $(this).data('mode');
		let item = P_ITEM[mode];
		let obj  = item.FORM.serializeObject();
		if (obj['srchStYear'] > obj['srchEnYear']) {
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
        grid.datagrid('options')['url'] = getUrl('/adm/statistics/getListVisitService.do');
        // 검색폼 그리드 검색
        grid.datagrid('load', params);		
	};

    // 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
		let item = P_ITEM[$(this).data('mode')];
        $.formUtil.submitForm(
            getUrl('/adm/statistics/downVisitServiceExcel.do'), 
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
	P_ITEM['YEAR'].doInit();
	P_ITEM['RATE'].doInit();

	// 탭 컨트롤 처리
	doControlTab();
});
