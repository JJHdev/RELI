/**
******************************************************************************************
*** 파일명 : listPrptExmnPlan.js
*** 설명글 : 예비조사 계획 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.11.17    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#exmnGrid'       ); // 예비조사사업 목록
	let P_FORM    = $('#searchForm'     ); // 검색폼
	let P_TFORM   = $('#selectForm'     ); // 조회폼
	let P_TGRID   = $('#trgtGrid'       ); // 대상자조회 목록
	let P_SELECT  = false; // 상세조회 데이터
	let P_FORMAT  = {
		// 조사일자 포맷처리
		exmnYmd: function(value, row) {
			return $.formatUtil.toDashDate(value) + ' ~ ' +
			       $.formatUtil.toDashDate(row['exmnEndYmd']);
		}
	};

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
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
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 칼럼정의
        columns: [[
            {field:'bizAreaNm'    ,width:100,halign:'center',align:'center',title:'피해지역'},
            {field:'exmnBgngYmd'  ,width:200,halign:'center',align:'center',title:'조사일자', formatter: P_FORMAT.exmnYmd},
            {field:'bizOderNm'    ,width:100,halign:'center',align:'center',title:'사업차수'},
            {field:'exmnOder'     ,width:100,halign:'center',align:'center',title:'예비조사차수'},
            {field:'exmnCnt'      ,width:150,halign:'center',align:'center',title:'대상자', formatter: $.commFormat.number}
        ]],
        // 행선택시 상세조회
        onSelect: doSelect
    });

    P_TGRID.datagrid({
		fit: true,
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 체크박스 KEY값필드
        idField:'idntfcId',
        // 칼럼정의
        columns: [[
	        {field:'chckId'      ,checkbox: true},
            {field:'aplyNo'      ,width:120,halign:'center',align:'center',title:'신청번호'},
            {field:'idntfcId'    ,width:120,halign:'center',align:'center',title:'식별아이디'},
            {field:'bizAreaNm'   ,width:200,halign:'center',align:'center',title:'피해지역'},
            {field:'bizOderNm'   ,width:100,halign:'center',align:'center',title:'사업차수'},
            {field:'sufrerNmMask',width:120,halign:'center',align:'center',title:'피해자명'}
        ]]
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	// [검색] 조사일자 기간박스
	$('#appExmnTermBox').appTermBox({
		label:  '조사일자',
		stName: 'srchExmnStdt',
		enName: 'srchExmnEndt'
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

	// 등록용 피해지역/사업차수 단계형 콤보박스 정의
	let P_REGIST_COMBO = {
		areaCombo: false,
		oderCombo: false,
		doInit: function() {
			let cmp = this;
			this.areaCombo = $('#bizAreaCmb').combobox({
				url: getUrl('/com/cmm/getComboBizMng.do'),
				onChange: function(newValue) {
					$('#bizAreaCd').val(newValue);
					cmp.doOderClear();
				},
		        onLoadSuccess: function() {
					cmp.doOderClear();
		        }
			});
			this.oderCombo = $('#bizOderCmb').combobox({
				url: getUrl('/com/cmm/getComboBizOder.do'),
				onBeforeLoad: function(param){
					param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
				},
				onChange: function(newValue) {
					$('#bizOder').val(newValue);
					if (newValue == '')
						return;
					let bizAreaCd = cmp.areaCombo.combobox('getValue');
					// 예비조사차수 정의
					$('#exmnOder').val( doLoadNextOder({
						bizAreaCd : bizAreaCd,
						bizOder   : newValue
					}) );
				},
				onSelect: function(rec) {
					// 영향범위 정의
					$('#affcScopeCn').val(rec['affcScopeCn']);
				}
			});
		},
		doOderClear: function() {
			P_TFORM.find('input[name="affcScopeCn"]').val("");
			P_TFORM.find('input[name="exmnOder"   ]').val("");
			P_REGIST_COMBO.oderCombo.combobox('clear');
			P_REGIST_COMBO.oderCombo.combobox('unselect');
			P_REGIST_COMBO.oderCombo.combobox('reload');
		},
		doReadonly: function() {
			$('#s_bizAreaNm' ).show();
			$('#s_bizOderNm' ).show();
			$('#s_bizAreaCmb').hide();
			$('#s_bizOderCmb').hide();
		},
		doReadable: function() {
			$('#s_bizAreaNm' ).hide();
			$('#s_bizOderNm' ).hide();
			$('#s_bizAreaCmb').show();
			$('#s_bizOderCmb').show();
		}
	};

	// 검색용 피해지역/사업차수 단계형 콤보박스 초기화
	P_SEARCH_COMBO.doInit();
	// 등록용 피해지역/사업차수 단계형 콤보박스 초기화
	P_REGIST_COMBO.doInit();

    //========================================================//
    // 등록폼 VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_TFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            bizAreaCd  : {required: true},
            bizOder    : {required: true},
            exmnBgngYmd: {required: true},
            exmnEndYmd : {required: true}
        },
        // 검증메세지 정의
        messages: {
            bizAreaCd  : {required: '피해지역은 필수 선택 사항입니다.'},
            bizOder    : {required: '사업차수는 필수 선택 사항입니다.'},
            exmnBgngYmd: {required: '조사시작일자는 필수 선택 사항입니다.'},
            exmnEndYmd : {required: '조사종료일자는 필수 입력 사항입니다.'}
        },
        //invalidHandler: $.easyValidate.handler,
        //errorPlacement: $.easyValidate.placement
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

	// 초기검색실행
	doSearch();

    // 예비조사사업 목록 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 항목 CLEAR
		doRegist();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListPrptExmnPlan.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);

        return false;
    }
	
    // 예비조사계획 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 상세조회 데이터 초기화
		doRegist();
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // 예비조사계획 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {
        var params = {
			bizAreaCd: row['bizAreaCd'],
			bizOder:   row['bizOder'  ],
			exmnOder:  row['exmnOder' ]
		};
    	// 상세조회 데이터 초기화
		doRegist();

        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getPrptExmnPlan.do'),
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					P_SELECT = data;
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
                    // 폼데이터 셋팅
                    $.formUtil.toForm(data, P_TFORM);
					P_TFORM.form('load', data);
					// 피해지역 명칭
					$('#s_bizAreaNm').html(data['bizAreaNm']);
					// 사업차수 명칭
					$('#s_bizOderNm').html(data['bizOderNm']);
			        // 콤보 READONLY
					P_REGIST_COMBO.doReadonly();
					// 삭제버튼 표시
					$('#btnRemove').show();
					// 예비조사 대상자 목록 검색
					doTargetSearch();
                }
            }
        );
    }

    // 예비조사계획 NEXT 본조사차수 가져오기
    //--------------------------------------------------------//
    function doLoadNextOder( params ) {
		var result = $.ajaxUtil.ajaxDefault(
						getUrl('/adm/exmn/getPrptExmnPlanNextOder.do'),
						params
					 );
		if (result.Data) {
			return result.Data;
		}
		return '0';
    }

    // 예비조사계획 신규등록
    //--------------------------------------------------------//
    function doRegist() {
		// 상세조회 데이터 제거
		P_SELECT = false;
        // 등록폼 리셋
        P_TFORM.form('reset');
        // 콤보 표시
		P_REGIST_COMBO.doReadable();
		// 피해지역 명칭 리셋
		$('#s_bizAreaNm').html('');
		// 사업차수 명칭 리셋
		$('#s_bizOderNm').html('');
		// 등록기본값 정의
        $.formUtil.toForm({
			mode      : MODE.INSERT, // 등록모드
			bizAreaCd : '',
			bizOder   : ''
		}, P_TFORM);
		// 예비조사대상자 목록 초기화
		P_TGRID.datagrid('loadData', {"total":0,"rows":[]});		
        // 삭제버튼 숨김
        $('#btnRemove').hide();
		return false;
    }
    // 예비조사계획 삭제 처리
    //--------------------------------------------------------//
    function doRemove() {

		if (!P_SELECT) {
			$.commMsg.alert('예비조사사업 목록에서 항목을 선택하세요.');
			return false;
		}
		if (P_SELECT['exmnCnt'] > 0) {
			$.commMsg.alert('예비조사 대상자가 있어 삭제할 수 없습니다.');
			return false;
		}
    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
			$.formUtil.toForm({mode:MODE.REMOVE}, P_TFORM);
            // 등록폼을 AJAX로 저장처리
            P_TFORM.ajaxForm({
                url: getUrl('/adm/exmn/savePrptExmnPlan.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.easyMsg.success(ret, '성공적으로 삭제되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
    	});
        return false;
    }
    // 예비조사계획 저장 처리
    //--------------------------------------------------------//
    function doSave() {

        // 등록폼의 VALIDATION 기능 활성화
        if (P_TFORM.validate().settings)
            P_TFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_TFORM.valid() === false)
            return false;

		var obj = P_TFORM.serializeObject();
		if ($.commUtil.compareDate(obj['exmnBgngYmd'], obj['exmnEndYmd']) > 0) {
			$.commMsg.alert('조사종료일을 조사시작일보다 이후 날짜로 입력하세요.');
			return false;
		}

        $.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_TFORM.ajaxForm({
                url: getUrl('/adm/exmn/savePrptExmnPlan.do'),
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.easyMsg.success(ret, '성공적으로 저장되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
        });
        return false;
    }

    // 예비조사대상자 목록 검색
    //--------------------------------------------------------//
    function doTargetSearch() {
		let params = {
			// 검색구분: 예비조사계획
			srchType  : 'PLAN',
			bizAreaCd : P_SELECT['bizAreaCd'],
			bizOder   : P_SELECT['bizOder'  ],
			exmnOder  : P_SELECT['exmnOder' ]
		};
		// 선택된 항목 CLEAR
		P_TGRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_TGRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListPrptExmn.do');
        // 검색폼 그리드 검색
        P_TGRID.datagrid('load', params);
        return false;
    }

    // 예비조사대상자 등록팝업 오픈
    //--------------------------------------------------------//
    function doTargetRegist() {
		if (!P_SELECT) {
			$.commMsg.alert('예비조사사업 목록에서 항목을 선택하세요.');
			return false;
		}
		let params = {
			bizAreaCd : P_SELECT['bizAreaCd'],
			bizOder   : P_SELECT['bizOder'  ],
			exmnOder  : P_SELECT['exmnOder' ]
		};

		// 대상자등록팝업 (adm_popup.js)
		popup.openExmn({
			title   : '예비조사 대상자 등록',
			element : '#appPopupTarget',
			srchType: 'PRPTEXMN',
			openArgs: { params: JSON.stringify(params) },
			openUrl : getUrl('/adm/exmn/modalPrptExmn.do'),
			saveUrl : getUrl('/adm/exmn/savePrptExmn.do'),
			saveCallback: doTargetSearch
		});
        return false;
    }

    // 예비조사대상자 선택삭제 처리
    //--------------------------------------------------------//
    function doTargetRemove() {
        // 목록의 선택 항목
        const rows = P_TGRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.commMsg.alert('삭제할 대상자를 선택하세요.');
            return false;
        }
        $.commMsg.confirm(rows.length+"명의 대상자를 삭제하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/savePrptExmn.do'),
                JSON.stringify({
                    mode:     MODE.REMOVE,
                    exmnList: rows
                }),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.easyMsg.inform('성공적으로 삭제되었습니다.');
                        doTargetSearch();
                    });
                }
            );
        });
        return false;
    }

    // 우측상세 탭 컨트롤 이벤트 바인딩
    //--------------------------------------------------------//
	function doControlTabs() {
		// 탭클릭 이벤트 처리
		$(".boxWrap > .tabWrap li").on("click",function(){
			var idx = $(this).index()
			$(this).parent().find("li").removeClass("on");
			$(this).addClass("on");
			$('.boxWrap > .tabInner > ul > li').removeClass("on");
			$('.boxWrap > .tabInner > ul > li').eq(idx).addClass("on");
			P_TGRID.datagrid('resize');
		})
		// 첫번째 탭 클릭 이벤트 발생
		$(".boxWrap > .tabWrap li").eq(0).trigger('click');
	}

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 신규등록버튼 클릭시 이벤트처리
    $('#btnRegist' ).bind('click', doRegist);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove' ).bind('click', doRemove);
    // 저장버튼 클릭시 이벤트처리
    $('#btnSave' ).bind('click', doSave);
    // 대상자등록버튼 클릭시 이벤트처리
    $('#btnTargetRegist' ).bind('click', doTargetRegist);
    // 선택삭제 클릭시 이벤트처리
    $('#btnTargetRemove' ).bind('click', doTargetRemove);
    // 조사기간 입력박스 하이픈(-) 자동삽입 이벤트
	bindDateHyphen( $('#exmnBgngYmd').datebox('textbox') );
	bindDateHyphen( $('#exmnEndYmd' ).datebox('textbox') );

	// 상세조회 탭컨트롤 이벤트
	doControlTabs();

	// 탭서브메뉴 생성 (comm_adm.js 참고)
	createTabMenu( $('#appTabSubMenu'), [
		{url:getUrl('/adm/exmn/listPrptExmnPlan.do'), title:'예비조사 계획'},
		{url:getUrl('/adm/exmn/listPrptExmn.do'    ), title:'예비조사 관리'}
	]);
});
