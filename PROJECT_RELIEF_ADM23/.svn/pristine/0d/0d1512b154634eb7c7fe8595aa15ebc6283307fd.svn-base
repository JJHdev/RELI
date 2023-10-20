/**
******************************************************************************************
*** 파일명 : listRoleMenu.js
*** 설명글 : 역할별메뉴관리 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.06    LSH
*** 1.0         2021.11.02    LSH   디자인적용 및 개발 수정
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_RGRID  = $('#roleGrid'   ); // 역할별 메뉴 그리드 객체
    var P_MGRID  = $('#menuGrid'   ); // 메뉴 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_FORMAT = {
	    roleId: function(value, row) {
		    return row['roleNm']+' ['+value+']';
        }
    };

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_RGRID.datagrid({
		// 화면영역 맞춤
		fit: true,
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 2022.01.09 LSH 데이터 건이 많으면 rownumbers 로 인해 느려져 주석처리함
        // 그리드 행번호 표시여부
        //rownumbers: true,
        // 체크박스 KEY값필드
        idField:'menuId',
        // 칼럼정의
        columns: [[
	        {field:'chckId',checkbox: true},
            {field:'roleId',width:300,halign:'center',align:'left',title:'역할', formatter: P_FORMAT.roleId},
            {field:'menuNm',width:300,halign:'center',align:'left',title:'메뉴명'},
            {field:'menuId',width:180,halign:'center',align:'left',title:'메뉴ID'}
        ]]
    });
    P_MGRID.datagrid({
		// 화면영역 맞춤
		fit: true,
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 체크박스 KEY값필드
        idField:'menuId',
        // 칼럼정의
        columns: [[
            {field:'chckId',checkbox: true},
			{field:'menuNm',width:300,halign:'center',align:'left',title:'메뉴명'},
            {field:'menuId',width:180,halign:'center',align:'left',title:'메뉴ID'}
        ]]
    });

    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//
	$('#srchRoleId').appComboBox({
		url: getUrl('/com/cmm/getComboRole.do'),
		// 콤보 값변경시 검색 처리
        change:   doSearch,
		// 콤보 값로딩후 실행 처리
		callback: doSearch

	});
    $('#srchSysCd').appComboBox({
		params: {upCdId: CODE.SYSTEM},
		// 콤보 값변경시 검색 처리
        change:   doSearch,
		// 콤보 값로딩후 실행 처리
		callback: doSearch
    });
    
    // 역할별프로그램관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
        doSearchRole();
        doSearchMenu();
    }
	
    // 역할별메뉴목록 검색처리
    //--------------------------------------------------------//
    function doSearchRole() {
		// 선택된 항목 CLEAR
		P_RGRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_RGRID.datagrid('options')['url'] = getUrl('/sys/role/getListRoleMenu.do');
        // 검색폼 그리드 검색
        P_RGRID.datagrid('load', obj);
    }
    
    // 추가할메뉴목록 검색처리
    //--------------------------------------------------------//
    function doSearchMenu() {
		// 선택된 항목 CLEAR
		P_MGRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_MGRID.datagrid('options')['url'] = getUrl('/sys/menu/getListMenu.do');
        // 검색폼 그리드 검색
        P_MGRID.datagrid('load', obj);
    }

    // 역할별메뉴관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        // 역할별메뉴목록의 선택 항목
        const rows = P_RGRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.easyMsg.alert('제외할 메뉴를 선택하세요.');
            return false;
        }
        
        $.easyMsg.confirm(rows.length+"개의 메뉴를 제외하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/sys/role/saveRoleMenu.do'), 
                JSON.stringify({
                    mode: MODE.REMOVE,
                    roleList: rows
                }),
                function(ret) {
                    $.easyMsg.success(ret, '성공적으로 제외되었습니다.', doSearch); 
                }
            );
        });
        return false;
    }

    // 역할별메뉴관리 추가하기
    //--------------------------------------------------------//
    function doAppend() {
        // 추가할메뉴목록의 선택 항목
        const rows = P_MGRID.datagrid('getSelections');
        // 역할ID
        const roleId = $('#srchRoleId').val();

        if (rows.length == 0) {
	        $.easyMsg.alert('추가할 메뉴를 선택하세요.');
            return false;
        }
        if ($.commUtil.empty(roleId)) {
            $.easyMsg.alert('역할ID를 확인할 수 없습니다.');
            return false;
        }
        
        $.easyMsg.confirm(rows.length+"개 메뉴를 추가하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/sys/role/saveRoleMenu.do'), 
                JSON.stringify({
	                mode: MODE.INSERT,
                    roleId: roleId,
                    roleList: rows
                }),
                function(ret) {
					$.easyMsg.success(ret, '성공적으로 추가되었습니다.', doSearch);
                }
            );
        });
        return false;
    }

    // 추가 클릭시 이벤트 처리
    $('#btnAppend').bind('click', doAppend);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);
});
