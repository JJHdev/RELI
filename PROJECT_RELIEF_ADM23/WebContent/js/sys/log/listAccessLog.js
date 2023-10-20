/**
******************************************************************************************
*** 파일명 : listAccessLog.js
*** 설명글 : 프로그램접속이력 관리 화면 스크립트
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
    var P_GRID   = $('#appGrid'    ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
		
    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
        // 그리드 목록조회 URL
        url: getUrl('/sys/log/getListAccessLog.do'),
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 페이징처리 여부
        pagination:true,
        // 한 페이지 출력수
        pageSize: 50,
        // 체크박스 KEY값필드
        idField:'sn',
        // 칼럼정의
        columns: [[
	        //{field:'chckId'       ,checkbox: true},
            {field:'sn'           ,width:120,halign:'center',align:'center',title:'번호'},
            {field:'progUrl'      ,width:400,halign:'center',align:  'left',title:'프로그램URL'},
            {field:'userNo'       ,width:120,halign:'center',align:'center',title:'사용자번호'},
            {field:'userNm'       ,width:120,halign:'center',align:'center',title:'사용자명'},
            {field:'cntnSeCd'     ,width:120,halign:'center',align:'center',title:'접속구분', hidden: true},
            {field:'cntnDt'       ,width:250,halign:'center',align:'center',title:'접속일시'},
            {field:'cntnYr'       ,width: 80,halign:'center',align:'center',title:'연도', hidden: true},
            {field:'cntnMm'       ,width: 65,halign:'center',align:'center',title:'월', hidden: true},
            {field:'cntnDd'       ,width: 65,halign:'center',align:'center',title:'일', hidden: true},
            {field:'ipAddr'       ,width:250,halign:'center',align:  'center',title:'IP주소'},
            {field:'srvrNm'       ,width:300,halign:'center',align:  'left',title:'서버명', hidden: true},
            {field:'sysNm'        ,width:150,halign:'center',align:'center',title:'시스템'}
        ]]
    });
    //========================================================//
    // 검색항목 Form Element
    //--------------------------------------------------------//
	$('#appLogTermBox').appTermBox({
		label: '접속기간',
		stName:'srchStDt',
		enName:'srchEnDt'
	});

    // 프로그램접속이력 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
        
        return false;
    }
	
    // 프로그램접속이력 검색리셋
    //--------------------------------------------------------//
    function doReset() {
        // 검색폼 입력데이터 초기화
        P_SFORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }
       
    // 프로그램접속이력 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        // 목록의 선택 항목
        const rows = P_GRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.commMsg.alert('삭제할 항목을 선택하세요.');
            return false;
        }
        $.easyMsg.confirm(rows.length+"개의 항목을 삭제하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/sys/log/saveAccessLog.do'), 
                JSON.stringify({
                    mode: MODE.REMOVE,
                    logList: rows
                }),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.easyMsg.inform('성공적으로 삭제되었습니다.');
                        doSearch();
                    });
                }
            );
        });
        return false;
    }

    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/sys/log/downAccessLogExcel.do'),
            {formId : "searchForm"}
        );
        return false;
    }

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);
    
    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'), $('#btnSearch'));
});
