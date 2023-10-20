/**
******************************************************************************************
*** 파일명 : listUserInfo.js
*** 설명글 : 사용자정보 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.13    LSH
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_GRID   = $('#grid'       ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_RFORM  = $('#registForm' ); // 등록폼 객체
	let P_FILES   = $('#appAplyFileList'); // 신청파일목록
	let P_APLY_ODER = '0';                 // 신청차수 기본값

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		// 화면영역 맞춤
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
        // 체크박스 KEY값필드
        idField:'aplyNo',
        // 칼럼정의
       columns: [[
		    {field:'chckId'       ,checkbox: true},
            {field:'aplyNo'  ,width:100,halign:'center',align:'center',title:'번호', hidden: true},
            {field:'userNm'  ,width:80,halign:'center',align:'center',title:'신청인'},
            {field:'trprNm'  ,width:80,halign:'center',align:'center',title:'연동대상자'},
            {field:'trprIdntfcId'  ,width:140,halign:'center',align:'center',title:'연동대상 식별아이디'},
            {field:'trprBrdt'  ,width:140,halign:'center',align:'center',title:'연동대상자 생년월일'},
            {field:'intrlckSeCd'  ,width:90,halign:'center',align:'center',title:'연동대상'},
            {field:'prgreStusCd'  ,width:90,halign:'center',align:'center',title:'연동상태'},
			{field:'mbtelNo'  ,width:120,halign:'center',align:'center',title:'연락처'},
         	{field:'aplyYmd'  ,width:110,halign:'center',align:'center',title:'신청일자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });

	// 신청파일목록영역 (comm_component.js 참고)
	P_FILES = P_FILES.appAplyFile({
		mode:   MODE.LIST,
		system: SYSTEM.ADMIN['code']
	});

    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//
	$('#intrlckSeCd').appSelectBox({
		label:   '연동대상',
		form:    'checkbox',
		name:    'intrlckSeList',
		params:  {upCdId: 'CT014'}
	});

	$('#prgreStusCd').appSelectBox({
		label:   '연동상태',
		form:    'checkbox',
		name:    'prgreStusList',
		params:  {upCdId: 'CT031'},
		filter: function(row) {
			// "반려" 항목 제외
			if (row['code'] == '09')
				return false;
			return true;
		}
	});

	$('#appAplyTermBox').appTermBox({
		label:'신청일자',
		stName:'srchAplyStdt',
		enName:'srchAplyEndt'
	})


    // 사용자정보 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/sys/user/getListInfoIntrlck.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
        return false;
    }


    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            mbtelNo   : {required: true,
                         mobile: true},
        },
        //invalidHandler: $.easyValidate.handler,
        //errorPlacement: $.easyValidate.placement
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 사용자정보 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 등록폼 초기화
    	doRegist();
        // 검색폼 입력데이터 초기화
        P_SFORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // 사용자정보 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 사용자정보 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {

		var params = {aplyNo: row['aplyNo']};
		$.ajaxUtil.ajaxLoad(
            getUrl('/sys/user/viewIntrlckList.do'),
			params,
            function(result) {
                var data = result.Data;

				// hidden값의 FORM 데이터 정의
				$.formUtil.toForm(data, P_RFORM);
				// EasyUI BOX의 FORM 데이터 정의
				P_RFORM.form('load', data);

		    	// 첨부파일목록 데이터로드
		    	P_FILES.loadList({
					dtySeCd:   data['papeDtySeCd'],
					dcmtNo:    data['aplyNo'],
					dtlDcmtNo: P_APLY_ODER
				});
            }
        );
        return false;
    }
    // 사용자정보 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/sys/user/downUserInfoExcel.do'),
            {formId : "searchForm"}
        );
        return false;
    }

    // 사용자정보 저장하기
    //--------------------------------------------------------//
    function doSave() {
		let rows = P_GRID.datagrid('getSelections');
		if (rows.length == 0) {
			$.commMsg.alert('정보연동을 진행할 항목을 하나 이상 선택하세요.');
			return false;
		}
        $.commMsg.confirm("정보연동을 진행하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/user/updateIntrlckList.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '성공적으로 연동되었습니다.', function() {
                       goUrl(getUrl('/sys/user/listInfoIntrlck.do'));
					});
                }
            }).submit();
        });
        return false;
    }

   // 현황 엑셀다운로드
   //--------------------------------------------------------//
   function doExcel() {
	   $.formUtil.submitForm(
		   getUrl('/sys/user/downlistInfoIntrlckExcel.do'),
		   {formId : "searchForm"}
	   );
	   return false;
   }

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 엑셀버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);

    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
    // 엑셀다운로드버튼 클릭 시 이벤트 처리
    $('#btnExcel'  ).bind('click', doExcel);


    // 검색 실행
    doSearch();

   	bindEnter($('#srchAplcntNm'), $('#btnSearch'));
    bindEnter($('#srchIdntfcId'), $('#btnSearch'));
	doSearch();

});
