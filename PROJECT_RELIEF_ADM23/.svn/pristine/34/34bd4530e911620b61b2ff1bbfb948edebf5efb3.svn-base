/**
*******************************************************************************
*** 파일명 : modalBioCmitMng.js
*** 설명글 : 살생물제품 위원회관리 위원등록 팝업 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 2.0         2023.01.30    LSH      최초생성
*******************************************************************************
**/
$(function() {
	// 피해지역 콤보박스 생성
	let P_COMBO = $('#p_srchBizAreaCd').appComboBox({
		url:  getUrl('/com/cmm/getComboBizMng.do'),
		init: {code:'', text:'지역선택'}
	});
	let P_FORM = $('#p_popupForm');
	// 위원 목록
	let P_GRID = $('#p_popupGrid').datagrid({
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
        // 한 페이지 출력수
        pageSize: 30,
        // KEY값필드
        idField: 'tenureNo',
        // 칼럼정의
        columns: [[
	        {field:'chckId'       ,checkbox: true},
            {field:'mfcmmNm'      ,width:100,halign:'center',align:'center',title:'위원명'},
            {field:'mfcmmOgdpNm'  ,width:150,halign:'center',align:'center',title:'소속'},
            {field:'mfcmmRlmNm'   ,width:150,halign:'center',align:'center',title:'분야'},
            {field:'tenureOder'   ,width:150,halign:'center',align:'center',title:'임기차수'}
        ]]
    });
    // 조회 버튼 클릭시 이벤트처리
    $('#btnPopupSearch').bind('click', function() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/bio/getListBioMfcmmTarget.do');
        // 검색폼 그리드 검색
       	P_GRID.datagrid('load', P_FORM.serializeObject());
	});
    // 선택등록 버튼 클릭시 이벤트처리
    $('#btnPopupSave').bind('click', function() {
		let rows = P_GRID.datagrid('getSelections');
		if (rows.length == 0) {
			$.commMsg.alert('등록할 위원을 한명 이상 선택하세요.');
			return false;
		}
		// 선택 항목 중 중복 체크
		let dup = false;
		$.each(rows, function(i,r) {
			if (i < rows.length) {
				$.each(rows.slice(i+1), function(j,o) {
					if (r['mfcmmNo'] == o['mfcmmNo']) {
						dup = r['mfcmmNm'];
						return false;
					}
				});
			}
		});
		if (dup) {
			$.commMsg.alert('동일한 위원을 다중 선택하셨습니다.['+dup+']');
			return false;
		}
		let data = {
			cmitMngNo: P_FORM.find('input[name="cmitMngNo"]').val(),
			mode:      MODE.INSERT,
			saveList:  rows
		};
		$.commMsg.confirm(rows.length+"명의 위원을 등록하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/bio/saveBioCmitMng.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 등록되었습니다.', function() {
							if (C_LIST)
								C_LIST.doSearch();
							if (P_POPUP)
								P_POPUP.close();
						});
                    });
                }
            );
		});
        return false;
	});
    // 위원명 검색어 입력 엔터 이벤트 처리
    bindEnter($('#p_srchMfcmmNm'), $('#btnPopupSearch'));
    // 소  속 검색어 입력 엔터 이벤트 처리
    bindEnter($('#p_srchMfcmmOgdpNm'), $('#btnPopupSearch'));
    // 임기차수 검색어 입력 엔터 이벤트 처리
    bindEnter($('#p_srchTenureOder'), $('#btnPopupSearch'));
	// 전문위원회가 아닌 경우 피해지역 검색조건 비활성화
	if ($('#p_cmitSeCd').val() != CODE.CMIT_SE_CD.EXPERT)
		P_COMBO.disable();
	
	// 초기검색실행
	$('#btnPopupSearch').trigger('click');
});