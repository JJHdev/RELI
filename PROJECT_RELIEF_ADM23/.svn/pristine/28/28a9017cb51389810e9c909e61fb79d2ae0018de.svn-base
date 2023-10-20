/**
******************************************************************************************
*** 파일명 : listSms.js
*** 설명글 : SMS이력 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.23    LSH
*** 1.0         2021.11.03    LSH   디자인적용 및 개발 수정
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_SFORM  = $('#searchForm' ); // SMS전송이력목록 검색폼 객체
	var P_TFORM  = $('#trgtForm'   ); // SMS일괄전송목록 검색폼 객체
	var P_RFORM  = $('#smsForm'    ); // SMS발송정보 폼 객체

    var P_GRID   = $('#grid'       ); // SMS전송이력 그리드 객체
    var P_RGRID  = $('#rcvrGrid'   ); // SMS전송수신자 그리드 객체
    var P_TGRID  = $('#trgtGrid'   ); // SMS일괄전송 그리드 객체
	var P_TABS   = $('#pageTabs'   ); // 화면 탭 객체

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 페이징처리 여부
        pagination:true,
        // 2022.01.09 LSH 데이터 건이 많으면 rownumbers 로 인해 느려져 주석처리함
        // 그리드 행번호 표시여부
        //rownumbers:true,
        // 한 페이지 출력수
        pageSize: 50,
        // 체크박스 KEY값필드
        idField:'sn',
        // 칼럼정의
        columns: [[
	        {field:'chckId'       ,checkbox: true},
            {field:'sn'           ,width: 80,halign:'center',align:'center',title:'번호'},
            {field:'smsSeNm'      ,width:130,halign:'center',align:'center',title:'SMS구분'},
            {field:'trsmCn'       ,width:220,halign:'center',align:  'left',title:'전송내용'},
            {field:'lngtSeNm'     ,width: 80,halign:'center',align:'center',title:'장문구분'},
            {field:'trnsterNm'    ,width:120,halign:'center',align:  'left',title:'송신자명'},
            {field:'trnsterNo'    ,width:130,halign:'center',align:  'left',title:'송신번호', formatter:$.formatUtil.toPhone},
            {field:'rcvrNm'       ,width: 80,halign:'center',align:  'left',title:'수신자명'},
            {field:'rcvrNo'       ,width:130,halign:'center',align:  'left',title:'수신번호', formatter:$.formatUtil.toPhone},
            {field:'trsmStusNm'   ,width: 80,halign:'center',align:'center',title:'전송상태'},
            {field:'trsmDt'       ,width:170,halign:'center',align:'center',title:'전송일시'},
            {field:'fileBtn'      ,width:140,halign:'center',align:  'left',title:'상세보기',
				formatter: function(v, r) {
					return [
						'<a href="javascript:void(0);"',
						' class="app-btn-select detail"',
						' title="상세보기"',
						' data-sn="'+r['sn']+'"',
						'>상세보기</a>'
					].join('');
				}
			}
        ]],
	 	onLoadSuccess: function() {
			let p = $(this).datagrid('getPanel');
			p.find('.app-btn-select').each(function() {
				$(this).bind('click', doOpenSelect);
			});
		}
    });

	// 수신번호 그리드
    P_RGRID.datagrid({
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 행번호 표시여부
        rownumbers:true,
		// 그리드 툴바 표시
		toolbar: '#rcvrToolbar',
        // 체크박스 KEY값필드
        idField:'rcvrNo',
        // 칼럼정의
        columns: [[
	        {field:'chckId',checkbox: true},
            {field:'rcvrNm',width:150,halign:'center',align:'center',title:'수신자명'},
            {field:'rcvrNo',width:200,halign:'center',align:'center',title:'휴대전화번호'}
        ]]
    });
	// 일괄전송 그리드
    P_TGRID.datagrid({
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 행번호 표시여부
        rownumbers: true,
        // 그리드 페이징처리 여부
        pagination:true,
        // 한 페이지 출력수
        pageSize: 30,
		// 그리드 툴바 표시
		toolbar: '#trgtToolbar',
        // 체크박스 KEY값필드
        idField: 'aplyNo',
        // 칼럼정의
        columns: [[
	        {field:'chckId'       ,checkbox: true},
            {field:'aplyNo'       ,width:120,halign:'center',align:'center',title:'신청번호'},
            {field:'bizAreaNm'    ,width:100,halign:'center',align:'center',title:'피해지역'},
            {field:'aplcntNm'     ,width:100,halign:'center',align:'center',title:'신청인'},
            {field:'sufrerNm'     ,width:100,halign:'center',align:'center',title:'피해자'},
            {field:'idntfcId'     ,width:120,halign:'center',align:'center',title:'식별번호'},
            {field:'aplcntMbtelNo',width:140,halign:'center',align:  'left',title:'휴대전화번호', formatter:$.formatUtil.toPhone}
        ]]
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
	$('#appAplySe').appSelectBox({
		label:   '신청구분',
		form:    'checkbox',
		name:    'aplySeList', 
		params:  {upCdId: CODE.APLYSE}
	});
	$('#appAplyKnd').appSelectBox({
		label:   '신청종류',
		form:    'checkbox',
		name:    'aplyKndList', 
		params:  {upCdId: CODE.APLYKIND}
	});
	$('#appProgress').appSelectBox({
		label:   '진행현황',
		form:    'checkbox',
		name:    'prgreStusList', 
		params:  {upCdId: CODE.PROGRESS},
		// 2021.12.27 ADD (comm_const.js 참고)
		filter:  CODE_FILTER.RELIEF_PRGRE_STUS
	});

	// 검색용 피해지역/사업차수 단계형 콤보박스 정의
	let P_SEARCH_COMBO = {
		areaCombo: false,
		oderCombo: false,
		doInit: function() {
			let cmp = this;
			this.areaCombo = $('#srchBizArea').combobox({
				url: getUrl('/com/cmm/getComboBizMng.do'),
				onChange: function() {
					cmp.doClearOder();
				},
		        onLoadSuccess: function() {
					cmp.doClearOder();
		        },
				prompt: '피해지역 선택',
				iconWidth: 22,
		        icons:[{
		            iconCls:'icon-clear',
		            handler: function() {
		                cmp.areaCombo.combobox('clear');
		            }
		        }]
			});
			this.oderCombo = $('#srchBizOder').combobox({
				url: getUrl('/com/cmm/getComboBizOder.do'),
				onBeforeLoad: function(param){
					param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
				},
				prompt: '사업차수 선택',
				iconWidth: 22,
		        icons:[{
		            iconCls:'icon-clear',
		            handler: function() {
		                cmp.oderCombo.combobox('clear');
		            }
		        }]
			});
		},
		doClearOder: function() {
			P_SEARCH_COMBO.oderCombo.combobox('clear');
			P_SEARCH_COMBO.oderCombo.combobox('unselect');
			P_SEARCH_COMBO.oderCombo.combobox('reload');
		}
	};
	// 검색용 피해지역/사업차수 단계형 콤보박스 초기화
	P_SEARCH_COMBO.doInit();

    // SMS이력 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/sys/sms/getListSms.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
        
        return false;
    }
	
    // SMS이력 검색리셋
    //--------------------------------------------------------//
    function doReset() {
        // 검색폼 입력데이터 초기화
        P_SFORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // SMS이력 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
		// 선택한 행 목록
        const rows = P_GRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.commMsg.alert('삭제할 항목을 선택하세요.');
            return false;
        }
        $.commMsg.confirm(rows.length+"개의 항목을 삭제하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/sys/sms/saveSms.do'), 
                JSON.stringify({
                    mode: MODE.REMOVE,
                    smsList: rows
                }),
                function(ret) {
                    $.commMsg.success(ret, '성공적으로 삭제되었습니다.', doSearch); 
                }
            );
        });
        return false;
    }
	
    // SMS전송 대상자 검색처리
    //--------------------------------------------------------//
    function doTrgtSearch() {
		// 선택된 항목 CLEAR
		P_TGRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_TFORM.serializeObject();
        // 그리드 목록조회 URL
        P_TGRID.datagrid('options')['url'] = getUrl('/sys/sms/getListTarget.do');
        // 검색폼 그리드 검색
        P_TGRID.datagrid('load', obj);
	
        return false;
    }
    // SMS전송 대상자 리셋처리
    //--------------------------------------------------------//
    function doTrgtReset() {
        // 검색폼 입력데이터 초기화
        P_TFORM.form('reset');
        // 검색폼 그리드 검색 처리
        doTrgtSearch();
	
        return false;
    }
    // SMS전송 대상자 선택추가처리
    //--------------------------------------------------------//
    function doTrgtAppend() {

        if (P_TGRID.datagrid('getRows').length == 0) {
            $.commMsg.alert('일괄전송목록에 검색된 대상자가 없습니다.');
            return false;
        }
		// 선택한 행 목록
        const rows = P_TGRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.commMsg.alert('추가할 대상자를 일괄전송목록에서 선택하세요.');
            return false;
        }
		let chk   = true;			
		let rcvrs = [];
		$.each(rows, function(i,row) {
			let d = {
				rcvrNo: $.formatUtil.toPhone(row['aplcntMbtelNo']),
				rcvrNm: row['aplcntNm']
			};
			if (doValidateAppend(d) === false) {
				chk = false;
				return false;
			}
			$.each(rows, function(j,diff) {
				if (i == j)
					return true;
				let no = $.formatUtil.toPhone(diff['aplcntMbtelNo']);
				if (d['rcvrNo'] == no) {
					$.commMsg.alert('동일한 번호['+no+']가 있습니다. 중복된 번호 중 하나만 선택하세요.');
					chk = false;
					return false;
				}
			});
			if (chk == false)
				return false;

			rcvrs.push(d);
		});
		if (chk == false)
			return false;

        $.commMsg.confirm(rcvrs.length+"명을 추가하시겠습니까?", function() {
			$.each(rcvrs, function(i,row) {
				// 수신목록에 추가
				P_RGRID.datagrid('appendRow', row);
			});
			// 총 수신자수 표시
			doSetRcvrCount();
        });
        return false;
    }

    // SMS전송 발송처리
    //--------------------------------------------------------//
    function doSend() {
		var params = {
			trsmCn:    $('#trsmCn'   ).val(),
			trnsterNo: $('#trnsterNo').val(),
			smsList:   P_RGRID.datagrid('getRows')
		};

		if (params['smsList'].length == 0) {
			$.commMsg.alert('수신 대상이 선택되지 않았습니다.');
			return false;
		}
		if (params['trsmCn'].length == 0) {
			$.commMsg.alert('발신 내용이 입력되지 않았습니다.');
			return false;
		}
		if (params['trnsterNo'].length == 0) {
			$.commMsg.alert('발송 번호가 입력되지 않았습니다.');
			return false;
		}
		var validate = $.validateUtil.phone(params['trnsterNo']);
		if (validate != 'TRUE') {
			$.commMsg.alert(validate);
			return false;
		}
        $.commMsg.confirm(params['smsList'].length+"명의 수신자에게 SMS를 전송하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/sys/sms/sendSms.do'), 
                JSON.stringify(params),
                function(ret) {
                    $.commMsg.success(ret, '성공적으로 전송되었습니다.', function() {
						// SMS 전송이력 탭을 활성화 한다.
						P_TABS.tabs('select', 1);
						// SMS 발송정보를 리셋한다.
						P_RFORM.form('reset');
						// SMS 수신자목록을 클리어한다.
						P_RGRID.datagrid('loadData',[]);
						// SMS 전송이력 목록을 검색한다.
						doSearch();
					}); 
                }
            );
        });
        return false;
    }
    // 2022.01.09 수신추가검증
    //--------------------------------------------------------//
    function doValidateAppend( data, isFocus ) {

		if ($.commUtil.empty(data['rcvrNm'])) {
			$.commMsg.alert('수신자 성명이 입력되지 않았습니다.', function() {
				if (isFocus)
					$("#rcvrNm").focus();
			});
			return false;
		}
		if ($.commUtil.empty(data['rcvrNo'])) {
			$.commMsg.alert('수신자 휴대전화번호가 입력되지 않았습니다.', function() {
				if (isFocus)
					$("#rcvrNo").focus();
			});
			return false;
		}
		var validate = $.validateUtil.mobile(data['rcvrNo']);
		if (validate != 'TRUE') {
			$.commMsg.alert(validate, function() {
				if (isFocus)
					$("#rcvrNo").focus();
			});
			return false;
		}
		var rows = P_RGRID.datagrid('getRows');
		var chk  = true;
		$.each(rows, function(i,row) {
			if (row['rcvrNo'] == data['rcvrNo']) {
				$.commMsg.alert('이미 추가된 번호['+data['rcvrNo']+']입니다.');
				chk = false;
				return false;
			}
		});
		if (chk == false)
			return false;

		return true;		
    }

    // SMS전송 수신추가처리
    //--------------------------------------------------------//
    function doAppend() {
		var data = {
			rcvrNm: $("#rcvrNm").val(),
			rcvrNo: $("#rcvrNo").val()
		};
		// 2022.01.09 수신추가검증
		let chk = doValidateAppend(data, true);
		if (chk == false)
			return false;
		
		// 수신목록에 추가
		P_RGRID.datagrid('appendRow', data);
		// 총 수신자수 표시
		doSetRcvrCount();
        return false;
    }
    // 총 수신자수 표시
    //--------------------------------------------------------//
    function doSetRcvrCount() {
		let cnt = P_RGRID.datagrid('getRows').length;
		$('#rcvrCnt').html(cnt+'명');
    }

    // SMS전송 수신목록 선택삭제처리
    //--------------------------------------------------------//
    function doRcvrRemove() {
		// 선택한 행 목록
        const rows = P_RGRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.commMsg.alert('삭제할 수신자를 수신번호목록에서 선택하세요.');
            return false;
        }
        $.commMsg.confirm(rows.length+"명을 삭제하시겠습니까?", function() {
			let indexes = [];
			$.each(rows, function(i,row) {
				indexes.push(P_RGRID.datagrid('getRowIndex', row));
			});
			// 내림차순 정렬
			indexes.sort(function(a, b) {
			    return b - a;
			});
			$.each(indexes, function(i,v) {
				// 수신목록에서 삭제
				P_RGRID.datagrid('deleteRow', v);
			});
			// 수신목록 선택 CLEAR
			P_RGRID.datagrid('clearSelections');
			// 총 수신자수 표시
			doSetRcvrCount();
        });
        return false;
    }

    // 2022.01.19 SMS 상세보기 팝업
    //--------------------------------------------------------//
    function doOpenSelect() {
		let sn = $(this).data('sn');
		$.commModal.loadView(
			'SMS 상세보기', 
			getUrl('/sys/sms/modalSms.do?sn='+sn), 
			{ sizeType: 'large' }
		);
        return false;
    }

    // [SMS전송] 전송버튼 클릭시 이벤트 처리
    $('#btnSend'  ).bind('click', doSend);
    // [SMS전송] 추가버튼 클릭시 이벤트 처리
    $('#btnAppend').bind('click', doAppend);
    // [SMS전송] 추가버튼 클릭시 이벤트 처리
    $('#btnRcvrRemove').bind('click', doRcvrRemove);

    // [SMS전송] 검색버튼 클릭시 이벤트 처리
    $('#btnTrgtSearch').bind('click', doTrgtSearch);
    // [SMS전송] 리셋버튼 클릭시 이벤트 처리
    $('#btnTrgtReset' ).bind('click', doTrgtReset);
    // [SMS전송] 선택추가버튼 클릭시 이벤트 처리
    $('#btnTrgtAppend').bind('click', doTrgtAppend);
    
    // [SMS전송이력] 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // [SMS전송이력] 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // [SMS전송이력] 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);

    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'    ), $('#btnSearch'    ));
    bindEnter($('#srchTrgtText'), $('#btnTrgtSearch'));
	// 휴대전화번호 자동 하이픈 입력 이벤트 바인딩
	bindPhoneHyphen ($("#trnsterNo"));
	bindMobileHyphen($("#rcvrNo"   ));

	$(".smsWrap li").on("click",function(){
		var idx = $(this).index()
		$(this).parent().find("li").removeClass("on");
		$(this).addClass("on");
		$(this).closest('.smsWrap').find('.smsInner > ul > li').removeClass("on");
		$(this).closest('.smsWrap').find('.smsInner > ul > li').eq(idx).addClass("on");
		
		if (idx == 0)
			$('.trgtWrap').removeClass("off");
		else
			$('.trgtWrap').addClass("off");
	})
	$(".smsWrap li").eq(0).trigger('click');

	doSearch();
});
