/**
******************************************************************************************
*** 파일명 : listPrptExmn.js
*** 설명글 : 예비조사 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 2.0         2023.01.06    LSH   식별ID 검색조건 추가 및 정렬 처리
*** 2.0         2023.01.06    LSH   피해자명,신청자명 검색 조건 추가
*** 2.0         2023.01.06    LSH   심의결과 부적합시에만 사유필수입력 처리
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 예비조사 목록	
	let P_FORM    = $('#searchForm'     ); // 예비조사 검색폼	

	let P_RGRID   = $('#resiGrid'       ); // 거주이력 그리드
	let P_LGRID   = $('#lbdyGrid'       ); // 생체지표 그리드
	let P_EFORM   = $('#exmnForm'       ); // 조사정보 등록폼
	let P_RFORM   = $('#rsltForm'       ); // 심의결과 등록폼
	let P_EFILE   = $('#appFileBox'     ); // 심의결과 첨부서류
	let P_SELECT  = false                ; // 상세조회 데이터

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		fit: true,
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 페이징처리 여부
        pagination:true,
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 한 페이지 출력수
        pageSize: 30,
        // 칼럼정의
        columns: [[
            {field:'idntfcId'     ,width: 90,halign:'center',align:'center',title:'식별아이디'},
            {field:'aplySeNm'     ,width: 90,halign:'center',align:'center',title:'신청구분'},
            {field:'sufrerNmMask' ,width:100,halign:'center',align:'center',title:'피해자명'},
            {field:'aplcntNmMask' ,width:100,halign:'center',align:'center',title:'신청자명'},
            {field:'bizAreaNm'    ,width:100,halign:'center',align:'center',title:'피해지역'},
            {field:'aplyKndNm'    ,width:150,halign:'center',align:'center',title:'신청종류'},
            {field:'aplyYmd'      ,width:100,halign:'center',align:'center',title:'신청일자'}
        ]],
        // 행선택시 상세조회
        onSelect: doSelect
    });

	// 거주이력 그리드
	P_RGRID.datagrid({
		fit: true,
		fitColumns: true,
        // 그리드 단일행만 선택여부
        singleSelect: true,
		idField: 'idx',
		toolbar: [
			{text:'추가',iconCls:'fa fa-plus' ,handler: doResiAppend},'-',
			{text:'삭제',iconCls:'fa fa-minus',handler: doResiRemove}
		],
        // 칼럼정의
        columns: [[
            {field:'resiTerm',width:140,halign:'center',align:'left',title:'거주기간',
				formatter: function(v,r) {
					return [
						'<input type="text" name="resiBgngYmd" value="'+$.commUtil.nvl(r['resiBgngYmd'])+'" maxlength="8" style="width:60px"/>',
						' ~ ',
						'<input type="text" name="resiEndYmd"  value="'+$.commUtil.nvl(r['resiEndYmd' ])+'" maxlength="8" style="width:60px"/>'
					].join("");
				}
			},
            {field:'resiAddr',width:400,halign:'center',align:'center',title:'주소',
				formatter: function(v,r) {
					return '<input type="text" name="resiAddr" value="'+$.commUtil.nvl(r['resiAddr'])+'" maxlength="65" class="w100"/>';
				}
			}
        ]]
	});
	
	// [2021.11.26 현재사용안함] 생체지표 그리드
	/*
	P_LGRID.datagrid({
		fit: true,
		fitColumns: true,
        // 그리드 단일행만 선택여부
        singleSelect: true,
		idField: 'sn',
		toolbar: '#lbdyToolbar',
        // 칼럼정의
        columns: [[
            {field:'lbdyNdxNm'    ,width:130,halign:'center',align:'center',title:'생체지표'},
            {field:'lbdyNdxRsltCn',width:400,halign:'center',align:'center',title:'결과',
				formatter: function(v,r) {
					return '<input type="text" name="lbdyNdxRsltCn" value="'+$.commUtil.nvl(v)+'" class="w100"/>';
				}
			}
        ]]
	});
	*/
	
	// 첨부서류 영역 초기화
	P_EFILE.appExmnFile({
		formId: '#rsltForm',
		gridId: '#fileGrid'
	});

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//

	// 검색용 피해지역/사업차수/조사차수 단계형 콤보박스 정의
	let P_SEARCH_COMBO = {
		areaCombo: false,
		oderCombo: false,
		exmnCombo: false,
		doInit: function() {
			let cmp = this;
			// 피해지역 콤보
			this.areaCombo = $('#srchBizArea').combobox({
				url: getUrl('/com/cmm/getComboBizMng.do'),
				onChange: function() {
					cmp.doClearExmn();
					cmp.doClearOder();
				},
		        onLoadSuccess: function() {
					cmp.doClearExmn();
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
			// 사업차수 콤보
			this.oderCombo = $('#srchBizOder').combobox({
				url: getUrl('/com/cmm/getComboBizOder.do'),
				onBeforeLoad: function(param){
					param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
				},
				onChange: function() {
					cmp.doClearExmn();
				},
		        onLoadSuccess: function() {
					cmp.doClearExmn();
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
			// 조사차수 콤보
			this.exmnCombo = $('#srchExmnOder').combobox({
				url: getUrl('/com/cmm/getComboPrptOder.do'),
				onBeforeLoad: function(param){
					param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
					param['bizOder'  ] = cmp.oderCombo.combobox('getValue');
				},
				prompt: '조사차수 선택',
				iconWidth: 22,
		        icons:[{
		            iconCls:'icon-clear',
		            handler: function() {
		                cmp.exmnCombo.combobox('clear');
		            }
		        }]
			});
		},
		doClearOder: function() {
			P_SEARCH_COMBO.oderCombo.combobox('clear');
			P_SEARCH_COMBO.oderCombo.combobox('unselect');
			P_SEARCH_COMBO.oderCombo.combobox('reload');
		},
		doClearExmn: function() {
			P_SEARCH_COMBO.exmnCombo.combobox('clear');
			P_SEARCH_COMBO.exmnCombo.combobox('unselect');
			P_SEARCH_COMBO.exmnCombo.combobox('reload');
		}
	};
	
	// [검색폼] 단계별 콤보 초기화
	P_SEARCH_COMBO.doInit();

	// [상세폼] 영향범위 내 거주여부
	$('#appResiYn').appSelectBox({
		type:    'static',
		form:    'radio',
		name:    'affcScopeResiYn', 
		rows:    STORE['OX_DST']
	});
	// [상세폼] 인정질환 보유여부
	$('#appHoldYn').appSelectBox({
		type:    'static',
		form:    'radio',
		name:    'rcognDissHoldYn',
		rows:    STORE['RCOGN_YN'] 
	});
	// [2021.11.26 현재사용안함] [상세폼] 생체지표여부
	/*
	$('#appLbdyYn').appSelectBox({
		label:   '생체지표여부',
		type:    'static',
		form:    'radio',
		name:    'lbdyNdxYn', 
		rows:    STORE['YES_NO']
	});
	*/
	
	// [상세폼] 심의회결과
	$('#appDltncRslt').appSelectBox({
		form:    'radio',
		name:    'dltncRsltCd', 
		params:  {upCdId: CODE.RESULT}
	});

    //========================================================//
    // FORM VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            dltncOpmtYmd  : {required: true},
            dltncRsltCd   : {required: true}
        },
        // 검증메세지 정의
        messages: {
            dltncOpmtYmd  : {required: '개최일자는 필수 입력 사항입니다.'},
            dltncRsltCd   : {required: '심의회 결과는 필수 선택 사항입니다.'}
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });
	
    //========================================================//
    // 기능 처리 함수 정의
    //--------------------------------------------------------//

	// 거주이력 그리드 행추가
    //--------------------------------------------------------//
	function doResiAppend() {
		P_RGRID.datagrid('appendRow', {mode:'I'});
	}
	// 거주이력 그리드 행삭제
    //--------------------------------------------------------//
	function doResiRemove() {
		let row = P_RGRID.datagrid('getSelected');
		if (row == null) {
			$.commMsg.alert('거주이력에서 삭제할 행을 선택하세요.');
			return false;
		}
		let idx = P_RGRID.datagrid('getRowIndex', row);
		P_RGRID.datagrid('deleteRow', idx);
	}
	// 거주이력 그리드 검색처리
    //--------------------------------------------------------//
	function doResiSearch( params ) {
       	P_RGRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListResiHst.do');
       	P_RGRID.datagrid('load', params);
	}
	// 거주이력 그리드 리셋처리
    //--------------------------------------------------------//
	function doResiReset() {
       	P_RGRID.datagrid('loadData', {"total":0,"rows":[]});
	}
    // 거주이력 목록데이터 가져오기 (검증포함)
    //--------------------------------------------------------//
    function doResiGetRows() {
		let panel = P_RGRID.datagrid('getPanel');
		
		let rows = [];
		
		// 목록데이터
		let addrows = P_RGRID.datagrid('getRows');
		
		let check = true;
		$.each(addrows, function(i,r) {
			let dom = {
				resiBgngYmd : panel.find('input[name="resiBgngYmd"]').eq(i),
				resiEndYmd  : panel.find('input[name="resiEndYmd" ]').eq(i),
				resiAddr    : panel.find('input[name="resiAddr"   ]').eq(i)
			};
			let row = {
				sn          : r['sn'],
				mode        : ($.commUtil.empty(r['sn']) ? MODE.INSERT : MODE.UPDATE),
				resiBgngYmd : dom["resiBgngYmd"].val(),
				resiEndYmd  : dom["resiEndYmd" ].val(),
				resiAddr    : dom["resiAddr"   ].val()
			};
			
			// 검증처리 필요
			if ($.commUtil.empty(row['resiBgngYmd'])) {
				$.easyMsg.alert('[거주이력] 거주기간 시작년도를 입력하세요.', function() {
					dom["resiBgngYmd"].focus();
				});
				check = false;
				return false;
			}
			if ($.validateUtil.year(row['resiBgngYmd']) != 'TRUE') {
				$.easyMsg.alert('[거주이력] 거주기간 시작년도를 정확하게 입력하세요.', function() {
					dom["resiBgngYmd"].focus();
				});
				check = false;
				return false;
			}
			if ($.commUtil.empty(row['resiEndYmd'])) {
				$.easyMsg.alert('[거주이력] 거주기간 종료년도를 입력하세요.', function() {
					dom["resiEndYmd"].focus();
				});
				check = false;
				return false;
			}
			if ($.validateUtil.year(row['resiEndYmd']) != 'TRUE') {
				$.easyMsg.alert('[거주이력] 거주기간 종료년도를 정확하게 입력하세요.', function() {
					dom["resiEndYmd"].focus();
				});
				check = false;
				return false;
			}
			if ($.commUtil.empty(row['resiAddr'])) {
				$.easyMsg.alert('[거주이력] 주소를 입력하세요.', function() {
					dom["resiAddr"].focus();
				});
				check = false;
				return false;
			}
			rows.push(row);
		});
		if (check == false)
			return false;
			
		// 삭제된데이터
		let delrows = P_RGRID.datagrid('getChanges', 'deleted');
		$.each(delrows, function(i,r) {
			rows.push(
				$.extend(r, {mode: MODE.REMOVE})
			);
		});
		return rows;
	}
	// [2021.11.26 현재사용안함] 생체지표 그리드 검색처리
    //--------------------------------------------------------//
	function doLbdySearch( params ) {
        P_LGRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListLbdyNdx.do');
        P_LGRID.datagrid('load', params);
	}
	// [2021.11.26 현재사용안함] 생체지표 그리드 리셋처리
    //--------------------------------------------------------//
	function doLbdyReset() {
        P_LGRID.datagrid('loadData', {"total":0,"rows":[]});
	}
    // [2021.11.26 현재사용안함] 생체지표 목록데이터 가져오기
    //--------------------------------------------------------//
    function doLbdyGetRows() {
		
		let panel = P_LGRID.datagrid('getPanel');
		// 목록데이터
		let rows  = P_LGRID.datagrid('getRows');
		if (rows.length == 0)
			return null;
		$.each(rows, function(i,r) {
			$.extend(r, {
				mode: ($.commUtil.empty(r['sn']) ? MODE.INSERT : MODE.UPDATE),
				lbdyNdxRsltCn: panel.find('input[name="lbdyNdxRsltCn"]').eq(i).val()
			});
		});
		return rows;
	}

    // 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 항목 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListPrptExmn.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
         
        return false;
    }
	
    // 검색리셋
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
	
    // 상세리셋
    //--------------------------------------------------------//
    function doClear() {
		
		// 조사정보 상세정보 초기화
		$.formUtil.toHtmlReset(P_EFORM, 's_');
		// 조사정보 상세폼 초기화
		P_EFORM.form('reset');

		// 거주이력 그리드 리셋처리
		doResiReset();
		// [2021.11.26 현재사용안함] 생체지표 그리드 리셋처리
		//doLbdyReset();

		// 심의회결과 상세정보 초기화
		$.formUtil.toHtmlReset(P_RFORM, 's_');
		// 심의회결과 상세폼 초기화
		P_RFORM.form('reset');
		// 서류목록 초기화
		P_EFILE.doReset();
		// 상세조회 데이터 제거
		P_SELECT = false;
		// 저장버튼 감춤
		$('#btnExmnSave').hide();
		$('#btnRsltSave').hide();
        return false;
    }


    // 예비조사정보 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {
	
    	// 상세조회 데이터 초기화
		doClear();

		// 신청정보 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/relief/getRelief.do'), 
			{
				aplyNo: row['aplyNo']
			},
            function(result) {
                var data = result.Data;
                if (data) {
					// 피해자정보 데이터로드
					$.formUtil.toHtml(P_EFORM, {
						idntfcId : data['idntfcId' ],
						sufrerNm : data['sufrerNm' ],
						bizAreaNm: data['bizAreaNm'],
						aplyKndNm: data['aplyKndNm']
					}, 's_');
					// 예비조사정보 상세조회
					doSelectPrptExmn(row);
                }
            }
        );
    }
    // 예비조사정보 상세조회
    //--------------------------------------------------------//
    function doSelectPrptExmn(row) {
	
		let params = {
			bizAreaCd : row['bizAreaCd'],
			bizOder   : row['bizOder'  ],
			exmnOder  : row['exmnOder' ],
			aplyNo    : row['aplyNo'   ]
		};

		// 예비조사정보 상세조회
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getPrptExmn.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					P_SELECT = data;
			    	// 조사정보 데이터 로드
					$.formUtil.toHtml(P_EFORM, data, 's_');

					// P_EFORM 폼데이터 로드
					P_EFORM.form('load', data);
					// 심의회결과 폼데이터 로드
					P_RFORM.form('load', data);

					// 거주이력 목록조회
					doResiSearch( params );
					// [2021.11.26 현재사용안함] 생체지표 목록조회
					//doLbdySearch( params );
					// 첨부서류 목록조회
					P_EFILE.doSearch({
						bizAreaCd : params['bizAreaCd'],
						bizOder   : params['bizOder'  ],
						exmnOder  : params['exmnOder' ],
						aplyNo    : params['aplyNo'   ],
						dtySeCd   : '01'  , // 업무구분 (예비조사)
						dtyClCd   : 'DLT' , // 업무분류 (심의회결과)
					});

					// 저장버튼 표시
					$('#btnExmnSave').show();
					$('#btnRsltSave').show();
                }
            }
        );
    }

    // 예비조사정보 저장처리
    //--------------------------------------------------------//
    function doExmnSave() {

		if (P_SELECT == false) {
			$.commMsg.alert('대상자 목록에서 항목이 선택되지 않았습니다.');
			return false;
		}
		// 예비조사정보 데이터
		let data = P_EFORM.serializeObject();
		$.extend(data, {
			// 처리모드: 예비조사정보 수정처리
			mode     : MODE.UPDATE,
			// 거주이력 목록
			resiList : doResiGetRows(),
			// [2021.11.26 현재사용안함] 생체지표 목록
			//lbdyList : doLbdyGetRows()
		});
		if (data['resiList'] == false)
			return false;
		//if (data['lbdyList'] == false)
		//	return false;

        $.commMsg.confirm("예비조사정보를 저장하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/exmn/savePrptExmn.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							doSearch();
						});
                    });
                }
            );
        });
        return false;
    }

    // 심의결과 저장처리
    //--------------------------------------------------------//
    function doRsltSave() {

		if (P_SELECT == false) {
			$.commMsg.alert('대상자 목록에서 항목이 선택되지 않았습니다.');
			return false;
		}
        // FORM VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;
        // FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;
		// [2023.01.06] 심의결과가 부적합일때만 사유 필수 입력하도록 수정
		if (P_RFORM.find('input[name="dltncRsltCd"]:checked').val() == CODE.RSLT_CD.REJECT &&
			$.commUtil.empty(P_RFORM.find('textarea[name="dltncRsltResn"]').val())) {
			$.commMsg.alert('부적합 사유는 필수 입력 사항입니다.');
			return false;
		}
		// 파일검증 실패시
		if (P_EFILE.doValidate() == false)
			return false;
			
		// 처리모드
		P_RFORM.find('input[name="mode"]').val(MODE.UPDATE);
		
    	$.commMsg.confirm("심의회 결과를 저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/exmn/savePrptExmnRslt.do'),
				// 파일업로드 타입
                enctype : 'multipart/form-data',
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					let msg = [
						'정상적으로 처리되었습니다.<br>',
						'대상자에게 예비조사 심의회 결과를 SMS로 발송하였습니다.'
					].join('');
					$.commMsg.success(ret, msg, function() {
						// 목록 검색
                        doSearch();
					});
                }
            }).submit();
    	});
		return false;
    }

    // 예비조사현황 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/exmn/downPrptExmnExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }

    //========================================================//
    // EVENT BINDING
    //--------------------------------------------------------//

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);
    // 예비조사 저장버튼 클릭시 이벤트처리
    $('#btnExmnSave' ).bind('click', doExmnSave);
    // 심의결과 저장버튼 클릭시 이벤트처리
    $('#btnRsltSave' ).bind('click', doRsltSave);

    // 개최일자 입력박스 하이픈(-) 자동삽입 이벤트
	bindDateHyphen( $('#dltncOpmtYmd').datebox('textbox') );
	// 2023.01.06 피해자명 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
	// 2023.01.06 신청자명 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchAplcntNm'), $('#btnSearch'));
	// 2023.01.06 식별ID 검색어 입력 엔터 이벤트 처리
	bindEnter($('#srchIdntfcId'), $('#btnSearch'));

	// 상세내역 탭클릭 이벤트
	$.eventUtil.tabClick('.boxWrap', 0, function(elm, index) {
		if (index == 1) {
			P_EFILE.doResize();
		}
		else {
			P_RGRID.datagrid('resize');
			P_LGRID.datagrid('resize');
		}
	});

	// 상단 탭 서브메뉴 생성 (comm_adm.js 참고)
	createTabMenu( $('#appTabSubMenu'), [
		{url:getUrl('/adm/exmn/listPrptExmnPlan.do'), title:'예비조사 계획'},
		{url:getUrl('/adm/exmn/listPrptExmn.do'    ), title:'예비조사 관리'}
	]);
	// 초기검색실행
	doSearch();

});
