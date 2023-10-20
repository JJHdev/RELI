/**
******************************************************************************************
*** 파일명 : listReliefOder.js
*** 설명글 : 구제급여 접수 - 추가의료비 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.12.14    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 추가의료비신청현황 목록	
	let P_FORM    = $('#searchForm'     ); // 추가의료비신청현황 검색폼
	let P_RFORM   = $('#selectForm'     ); // 추가의료비신청현황 상세폼	
	let P_PERSON  = $('#appPersonalWrap'); // 개인정보영역
	let P_FILES   = $('#appAplyFileList'); // 추가의료비신청파일목록

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
        // 칼럼정의
        columns: [[
            {field:'bizAreaNm'    ,width: 80,halign:'center',align:'center',title:'피해지역'},
            {field:'idntfcId'     ,width: 90,halign:'center',align:'center',title:'식별ID'  },
            {field:'sufrerNmMask' ,width:100,halign:'center',align:'center',title:'피해자명'},
            {field:'aplcntNmMask' ,width:100,halign:'center',align:'center',title:'신청자명'},
            {field:'aplyOder'     ,width: 80,halign:'center',align:'center',title:'신청차수'},
            {field:'aplyYmd'      ,width:100,halign:'center',align:'center',title:'신청일자'},
            {field:'prgreStusNm'  ,width:100,halign:'center',align:'center',title:'진행상태'}
        ]],
        // 행선택시 상세조회
        onSelect: doSelect
    });
	
	// 개인정보영역 생성 (comm_adm.js 참고)
	P_PERSON = P_PERSON.appPersonal({});

	// 신청파일목록영역 (comm_component.js 참고)
	P_FILES = P_FILES.appAplyFile({
		mode:   MODE.LIST,
		system: SYSTEM.ADMIN['code']
	});

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#appAplyTermBox').appTermBox({
		label:'신청일자',
		stName:'srchAplyStdt',
		enName:'srchAplyEndt'
	});
	$('#appRcptTermBox').appTermBox({
		label:'접수일자',
		stName:'srchRcptStdt',
		enName:'srchRcptEndt'
	});
	// 피해지역 콤보
	$('#srchBizArea').combobox({
		url: getUrl('/com/cmm/getComboBizMng.do'),
		prompt: '피해지역 선택',
		iconWidth: 22,
        icons:[{
            iconCls:'icon-clear',
            handler: function() {
                $('#srchBizArea').combobox('clear');
            }
        }]
	});
	// 신청차수 콤보
	$('#srchAplyOder').combobox({
		url: getUrl('/com/cmm/getComboReliefOder.do'),
		prompt: '신청차수 선택',
		iconWidth: 22,
        icons:[{
            iconCls:'icon-clear',
            handler: function() {
                $('#srchAplyOder').combobox('clear');
            }
        }]
	});
    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 접수버튼 클릭시 이벤트처리
    $('#btnReceipt').bind('click', doReceipt);

    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
    bindEnter($('#srchAplcntNm'), $('#btnSearch'));
	// 2023.01.06 식별ID 검색어 입력 엔터 이벤트 처리
	bindEnter($('#srchIdntfcId'), $('#btnSearch'));
	// 초기검색실행
	doSearch();

    // 추가의료비신청현황 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 항목 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/relief/getListReliefOder.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
         
        return false;
    }
	
    // 추가의료비신청현황 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 상세조회 데이터 초기화
		doClear();
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }
	
    // 추가의료비신청현황 상세리셋
    //--------------------------------------------------------//
    function doClear() {
    	// 개인정보 초기화
		$.formUtil.toHtmlReset(P_RFORM, 's_');
		P_RFORM.form('reset');
		// 서류목록 초기화
		P_FILES.resetList();
		// 상세조회 데이터 제거
		P_SELECT = false;
		// 접수버튼 숨김
		$('#btnReceipt').hide();

        return false;
    }

    // 추가의료비신청현황 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {

        var params = {
			aplyNo   : row['aplyNo'  ],
			aplyOder : row['aplyOder']
		};
		// 상세초기화
		doClear();

        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/relief/getReliefOder.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 개인정보 데이터로드
					$.formUtil.toHtml(P_RFORM, {
						idntfcId    : data['idntfcId'   ],
						sufrerNm    : data['sufrerNm'   ],
						bizAreaNm   : data['bizAreaNm'  ],
						aplyOder    : data['aplyOder'   ],
						aplyNo      : data['aplyNo'     ],
						aplyYmd     : data['aplyYmd'    ],
						prgreStusNm : data['prgreStusNm'],
						rcptYmd     : data['rcptYmd'    ]
					}, 's_');
			    	// 첨부파일목록 데이터로드
			    	P_FILES.loadList({
						dtySeCd:   data['papeDtySeCd'],
						dcmtNo:    data['aplyNo'     ],
						dtlDcmtNo: data['aplyOder'   ]
					});
					// 제출된 신청서만 접수버튼 표시
					if (data['prgreStusCd'] == CODE.PRGRE_STUS.APPLY) {
						// 접수버튼 표시
						$('#btnReceipt').show();
					}
                }
            }
        );
    }

    // 접수하기
    //--------------------------------------------------------//
    function doReceipt() {
	
		let row = P_GRID.datagrid('getSelected');
		if (row == null) {
			$.commMsg.alert('추가의료비신청현황에서 접수할 항목을 선택하세요.');
			return false;
		}
		if (row['prgreStusCd'] != CODE.PRGRE_STUS.APPLY &&
			row['prgreStusCd'] != CODE.PRGRE_STUS.REAPPLY) {
			$.commMsg.alert('제출된 신청서만 접수가 가능합니다.');
			return false;
		}
		
    	$.commMsg.confirm("추가의료비신청을 접수하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/relief/saveReliefOder.do'), 
                JSON.stringify(row),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 접수되었습니다.', doSearch);
                    });
                }
            );
    	});
        return false;
    }
});
