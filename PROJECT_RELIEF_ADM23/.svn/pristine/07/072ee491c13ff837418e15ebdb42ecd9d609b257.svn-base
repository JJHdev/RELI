/**
******************************************************************************************
*** 파일명 : viewDissMcp.js
*** 설명글 : 인정질환별 의료비 현황 조회 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.12.20    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_FORM  = $('#searchForm');
	let P_GRID  = $('#appGrid'   );

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchStYear').appComboBox({
		type:'static', 
		init: COMBO.INITALL,
		rows: STORE.getYears(0, $.formatUtil.toYear)
	});
	$('#srchEnYear').appComboBox({
		type:'static', 
		rows: STORE.getYears(0, $.formatUtil.toYear)
	});
	
    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		fit: true,
		// 행단위 STRIPE 표시 안함
		striped: false,
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
            {rowspan:2,field:'clssClNm' ,width:300,halign:'center',align:'center',title:'계통분류<br>(한국표준질병사인분류)'},
            {colspan:2,title:'질환분류 (KOICD 4단위)'},
            {colspan:4,title:'피해인정자현황'},
			{colspan:4,title:'구제급여지급현황'}
		],[
            {field:'dissClNm',width:200,halign:'center',align:  'left',title:'질병명'}, 
            {field:'dissClCd',width: 80,halign:'center',align:'center',title:'코드'}, 
            {field:'cntTOTAL',width:100,halign:'center',align: 'right',title:  '계', formatter:$.commFormat.number}, 
            {field:'cntA0001',width:100,halign:'center',align: 'right',title:'대구', formatter:$.commFormat.number}, 
            {field:'cntA0002',width:100,halign:'center',align: 'right',title:'서천', formatter:$.commFormat.number}, 
            {field:'cntA0003',width:100,halign:'center',align: 'right',title:'김포', formatter:$.commFormat.number}, 
            {field:'amtTOTAL',width:100,halign:'center',align: 'right',title:  '계', formatter:$.commFormat.number}, 
            {field:'amtA0001',width:100,halign:'center',align: 'right',title:'대구', formatter:$.commFormat.number}, 
            {field:'amtA0002',width:100,halign:'center',align: 'right',title:'서천', formatter:$.commFormat.number}, 
            {field:'amtA0003',width:100,halign:'center',align: 'right',title:'김포', formatter:$.commFormat.number}, 
        ]],
		onLoadSuccess: function(data) {
			let txt = '';
			let idx = 0;
			let obj = {};
			let elm = $(this);
			$.each(data.rows, function(i,r) {
				if (txt == r['clssClNm']) {
					if (obj[txt]) {
						obj[txt]['rowspan']++;
					}
					else {
						obj[txt] = {index: idx, field: 'clssClNm', rowspan:2};
					}
				}
				txt = r['clssClNm'];
				idx = i;
			});
			$.each(obj, function(i,r) {
				// 행 병합 처리
                elm.datagrid('mergeCells',r);
			});
		}
    });

    // 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		let params = P_FORM.serializeObject();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/statistics/getListDissMcp.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', params);		
        return false;
	};

    // 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/statistics/downDissMcpExcel.do'), 
            {formId : P_FORM.prop('id')}
        );
        return false;
    }

    // 초기화
    //--------------------------------------------------------//
	function doInit() {

	    // 엑셀버튼 클릭시 이벤트 처리
	    $('#btnExcel' ).bind('click', doExcel);
	    // 검색버튼 클릭시 이벤트 처리
	    $('#btnSearch').bind('click', doSearch);
		$('#btnSearch').trigger('click');
		$('#btnSearch').hide();
		$('#searchForm').closest('.app-search-layout').hide();
	}
	
    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//
	doInit();
});
