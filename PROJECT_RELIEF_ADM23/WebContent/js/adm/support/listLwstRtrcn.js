/**
******************************************************************************************
*** 파일명 : listLwstRtrcn.js
*** 설명글 : 취약계층소송지원 - 소송취소현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    한금주
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_GRID   = $('#grid'       ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_RFORM  = $('#registForm' ); // 등록폼 객체
	let P_DTY_CD  = CODE.DTY_CD.LWST;    // 업무구분(취약계층소송지원)
	let P_HISTORY = false;                 // 이력관리목록
	let P_CanCel = false;                 // 이력관리목록
	let P_SELECT  = false;                 // 상세조회 데이터
	let P_FILES   = $('#appAplyFileList'); // 신청파일목록
	let P_APLY_ODER = '0';                 // 신청차수 기본값

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
        // 한 페이지 출력수
        pageSize: 30,
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 체크박스 KEY값필드
        idField:'aplyNo',
        // 칼럼정의
       columns: [[
		    {field:'chckId'       ,checkbox: true},
            {field:'aplyNo'  ,hidden: true, width:100,halign:'center',align:'center',title:'번호'},
            {field:'aplcntNm'  ,width:80,halign:'center',align:'center',title:'신청인 성명'},
            {field:'aplcntBrdt'  ,width:120,halign:'center',align:'center',title:'신청인 생년월일'},
			{field:'aplcntAddrLwst'  ,width:250,halign:'center',align:'center',title:'신청인 주소'},
            {field:'aplcntMbtelNo'  ,width:140,halign:'center',align:'center',title:'신청인 휴대전화번호'},
            {field:'respdntNm'  ,width:140,halign:'center',align:'center',title:'피신청인 성명'},
			{field:'prgreStusNm'  ,width:110,halign:'center',align:'center',title:'상태'},
         	{field:'rtrcnYmd'  ,width:110,halign:'center',align:'center',title:'취소일자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });
	// 이력관리영역 (comm_adm.js 참고)
	P_HISTORY = $('#appHistoryTable').appTableLayout({
		cls:    'app-h200',
		columns: [
			{name: 'regDate', label: '작성일자'},
			{name: 'hstCn'  , label: '이력내용', key: 'sn', dblclick: doSelectHistory},
			{name: 'rgtrNm' , label: '작성자'}
		],
		nodata: true
	});

	// 취소관리영역 (comm_adm.js 참고)
	P_CanCel = $('#appCanCelTable').appTableLayout({
		cls:    'app-h200',
		columns: [
			{name: 'regDate', label: '작성일자'},
			{name: 'hstCn'  , label: '이력내용', key: 'sn', dblclick: doCanCelHistory},
			{name: 'rgtrNm' , label: '작성자'}
		],
		nodata: true
	});

	// 신청파일목록영역 (comm_component.js 참고)
	P_FILES = P_FILES.appAplyFile({
		mode:   MODE.LIST,
		system: SYSTEM.ADMIN['code']
	});


	 // [이력관리팝업] 이력관리 목록검색
    //--------------------------------------------------------//
    function doLoadHistory( aplyNo ) {
		P_HISTORY.load(
			getUrl('/adm/biz/getListMngHst.do'), {
			dtySeCd: P_DTY_CD,
			hstSeCd: 'H1',
			aplyNo:  P_SELECT['aplyNo'],
		});
        return false;
    }

    // [이력관리팝업] 이력등록 팝업오픈
    //--------------------------------------------------------//
    function doOpenHistory() {
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupHistory',
			openArgs: {
				title: '이력등록',
				params: JSON.stringify({
					mode:    MODE.INSERT,
					dtySeCd: P_DTY_CD,
					hstSeCd: 'H1',
					aplyNo:  P_SELECT['aplyNo']
				})
			},
			saveCallback: doLoadHistory
		});
        return false;
    }

	 // [이력관리팝업] 이력조회 팝업오픈
    //--------------------------------------------------------//
    function doSelectHistory(aplyNo) {
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupHistory',
			openArgs: {
				title: '이력조회',
				params: JSON.stringify({
					mode:    MODE.VIEW,
					dtySeCd: P_DTY_CD,
					hstSeCd: 'H1',
					aplyNo:  P_SELECT['aplyNo'],
					sn:      $(this).data('key')
				})
			},
			saveCallback: doLoadHistory
		});
        return false;
    }

	// [취소이력관리팝업] 이력관리 목록검색
    //--------------------------------------------------------//
    function doLoadCanCelHistory( aplyNo ) {
		P_CanCel.load(
			getUrl('/adm/biz/getListMngHst.do'), {
			dtySeCd: P_DTY_CD,
            hstSeCd: 'H2',
			aplyNo:  P_SELECT['aplyNo'],
		});
        return false;
    }

    // [취소이력관리팝업] 이력등록 팝업오픈
    //--------------------------------------------------------//
    function doOpenCanCellHistory() {
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupCancel',
			openArgs: {
				title: '취소이력등록',
				params: JSON.stringify({
					mode:    MODE.INSERT,
					dtySeCd: P_DTY_CD,
					hstSeCd: 'H2',
					aplyNo:  P_SELECT['aplyNo']
				})
			},
			saveCallback: doLoadCanCelHistory
		});
        return false;
    }

	 // [취소이력관리팝업] 이력조회 팝업오픈
    //--------------------------------------------------------//
    function doCanCelHistory(aplyNo) {
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupCancel',
			openArgs: {
				title: '취소 이력조회',
				params: JSON.stringify({
					mode:    MODE.VIEW,
					dtySeCd: P_DTY_CD,
					hstSeCd: 'H2',
					aplyNo:  P_SELECT['aplyNo'],
					sn:      $(this).data('key')
				})
			},
			saveCallback: doLoadCanCelHistory
		});
        return false;
    }


    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//

	$('#appAplyTermBox').appTermBox({
		label:'신청일자',
		stName:'srchAplyStdt',
		enName:'srchAplyEndt'
	});

	$('#appCanCelTermBox').appTermBox({
		label:'취소일자',
		stName:'srchRtrcnStdt',
		enName:'srchRtrcnEndt'
	});

//	$('#appSprtSeCd').appSelectBox({
//	form:    'checkbox',
//	name:    'sprtSeCdList',
//	params:  {upCdId: 'CT021'}
//	});

	$('#appSprtSeCdList').appSelectBox({
	label:   '지원구분',
	form:    'checkbox',
	name:    'sprtSeCdList',
	params:  {upCdId: 'CT021'}
	});


    // 사용자정보 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstRtrcn.do');
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

    // 사용자정보 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {
		var params = {
			aplyNo:   row['aplyNo'  ]
		};

		$.ajaxUtil.ajaxLoad(
            getUrl('/adm/support/viewlistLwstRtrcn.do'),
			params,
            function(result) {
                var data = result.Data;
				P_SELECT = data;
				aplyNo = P_SELECT['aplyNo'];
				data['prgreStusCd'] = data['prgreStusCd'];
//				data['stusNm'] = P_FORMAT.stusNm(data['prgreStusNm'], data);
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
				// 이력관리 목록로드
				doLoadHistory(data['aplyNo']);
				// 취소관리 목록로드
				doLoadCanCelHistory(data['aplyNo']);
            }
        );
        return false;
    }

	// 취소현황 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/support/downlistLwstRtrcnExcel.do'),
            {formId : "searchForm"}
        );
        return false;
    }

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
 	// 이력등록버튼 클릭시 이벤트처리
    $('#btnHistory').bind('click', doOpenHistory);
 	// 취소이력등록버튼 클릭시 이벤트처리
    $('#btnCancelHistory').bind('click', doOpenCanCellHistory);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);


    // 검색 실행
    doSearch();

   	bindEnter($('#srchAplcntNm'), $('#btnSearch'));

	//개인정보 탭
	$(".tabWrap li").on("click", function() {
		var idx = $(this).index()
		$(this).parent().find("li").removeClass("on");
		$(this).addClass("on");
		$('.tabInnerFrom').removeClass("on");
		$('.tabInnerFrom').eq(idx).addClass("on");
	});

});