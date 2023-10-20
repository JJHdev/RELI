/**
*******************************************************************************
*** 파일명 : modalBioMfcmmTenure.js
*** 설명글 : 살생물제품 위원관리 임기이력조회 팝업 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 2.0         2023.01.30    LSH      최초생성
*******************************************************************************
**/
$(function() {

	let params = {
		mfcmmNo: $('#p_mfcmmNo').val()
	};
	// 위원 정보
	let P_INFO = $('#p_mfcmmInfo').appTableLayout({
		wrap:      true,
		wrapCls:   'tableWrap type5',
		headSpace: true,
		tailSpace: true,				
		colgroup:  ['25%','25%','25%','25%'],
		columns: [
			{name: 'mfcmmNm'      , label: '위원명'},
			{name: 'mfcmmOgdpNm'  , label: '소  속'},
			{name: 'mfcmmRspofcNm', label: '직  책'},
			{name: 'mfcmmRlmNm'   , label: '분  야'}
		]
	});
	// 임기 이력
	let P_GRID = $('#p_tenureGrid').appTableLayout({
		wrap:      true,
		wrapCls:   'tableWrap type5',
		title:     '위원회 임기 이력',
		titleCls:  'subTit type1',
		titleTag:  'h3',
		tailSpace: true,
		colgroup:  ['25%','25%','40%'],
		columns: [
			{name: 'cmitSeNm'     , label: '위원회 구분' },
			{name: 'tenureOder'   , label: '차수 및 기수'},
			{name: 'tenureBgngYmd', label: '임기 기간'   , cls: 'app-l',
				formatter: function(v,r) {
					return v + ' ~ ' + 
					      ($.commUtil.empty(r['tenureEndYmd']) ? '현재' : r['tenureEndYmd']);
				}
			},
		]
	});
		
	let data = $.ajaxUtil.ajaxDefault(getUrl('/adm/bio/getBioMfcmm.do'), params);
	if (data && data['Data']) {
		P_INFO.loadData([data['Data']]);
	    $.ajaxUtil.ajaxLoad(
	        getUrl('/adm/bio/getListBioMfcmmTenure.do'), 
			params,
	        function(result) {
	            var rows = result.rows;
	            if (rows)
					P_GRID.loadData(rows);
			}
		);
	}
});