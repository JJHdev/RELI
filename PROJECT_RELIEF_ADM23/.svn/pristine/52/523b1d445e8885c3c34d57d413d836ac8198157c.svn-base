/**
******************************************************************************************
*** 파일명 : listReliefClaim.js
*** 설명글 : 구제급여 사후관리 (구상권/환수) 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'   ); // 구상권/환수현황 목록	
	let P_FORM    = $('#searchForm'); // 구상권/환수현황 검색폼	

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		// 화면영역 맞춤
		fit: true,
		// 그리드 칼럼너비 영역맞춤
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
            {field:'trgtEntNm'    ,width:200,halign:'center',align:'center',title:'구상금<br>대상업체'},
            {field:'payInfrmYmd'  ,width:100,halign:'center',align:'center',title:'구상금<br>납부고지일',
				editor: {type:'datebox'}
			},
            {field:'pnopYmd'      ,width:100,halign:'center',align:'center',title:'구상금<br>납부최고일',
				editor: {type:'datebox'}
			},
            {field:'payInfrmAmt'  ,width:100,halign:'center',align:'center',title:'구상금<br>납부고지액',
				formatter: $.commFormat.number,
				editor: {type:'numberbox'}
			},
            {field:'refbnfTotAmt' ,width:100,halign:'center',align:'center',title:'구제급여<br>총계',
				formatter: $.commFormat.number,
				styler: function() {
					return {class:'app-underline'}
				}
			}
        ]],
		// 셀클릭시 (상세팝업)
		onClickCell: doClickCell
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchBizArea').combobox({
		url: getUrl('/com/cmm/getComboBizMng.do'),
		// 콤보박스 데이터 로딩필터
		loadFilter: function( data ) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		}
	});
	$('#srchGiveYear').combobox({
		panelHeight: '200',
		data: STORE.getYears(0, $.formatUtil.toYear),
		// 콤보박스 데이터 로딩필터
		loadFilter: function( data ) {
			data.unshift(COMBO.INIT_ALL);
			return data;
		}
	});
	// 초기검색실행
	doSearch();

    // 구상권/환수현황 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/relief/getListReamtPay.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
         
        return false;
    }

    // 구상권/환수현황 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/relief/downReamtPayExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }

    // 구상권/환수현황 셀클릭 이벤트
    //--------------------------------------------------------//
    function doClickCell(index, field) {
		let row = P_GRID.datagrid('getRows')[index];
		// 구제급여 총계이면
		if (field == 'refbnfTotAmt') {
			// 구제급여 상세내역 팝업 오픈
			doOpenReamtView({
				sn         : row['sn'],
				reamtMngNo : row['reamtMngNo']
			});
		}
		return false;
    }

    // 구상권/환수현황 등록
    //--------------------------------------------------------//
    function doRegist() {
		// 구상권정보 등록 팝업 오픈
		doOpenReamtForm({
			mode : MODE.INSERT
		}, '구상권 청구 등록');
		return false;
    }

    // 구상권/환수현황 수정
    //--------------------------------------------------------//
    function doUpdate() {
		// 선택된 행
		let row = P_GRID.datagrid('getSelected');
		if (row == null) {
			$.commMsg.alert('목록에서 수정할 항목을 선택하세요.');
			return false;
		}
		// 구상권정보 수정 팝업 오픈
		doOpenReamtForm({
			mode       : MODE.UPDATE,
			sn         : row['sn'],
			reamtMngNo : row['reamtMngNo']
		}, '구상권 청구 수정');
		return false;
    }

    // 구상권/환수현황 삭제
    //--------------------------------------------------------//
    function doRemove() {
		// 선택된 행
		let row = P_GRID.datagrid('getSelected');
		if (row == null) {
			$.commMsg.alert('목록에서 삭제할 항목을 선택하세요.');
			return false;
		}
		let data = {
			mode       : MODE.REMOVE,
			sn         : row['sn'],
			reamtMngNo : row['reamtMngNo']
		};
        $.commMsg.confirm('정말 삭제하시겠습니까?', function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/relief/saveReamtPay.do'),
                JSON.stringify(data),
                function(ret) {
					$.commMsg.success(ret, '성공적으로 삭제되었습니다.', function() {
						doSearch();
					});
                }
            );
        });
		return false;
    }

    // 구상권정보 등록/수정 팝업 오픈
    //--------------------------------------------------------//
    function doOpenReamtForm( params, title ) {

		$('#appPopupReamtForm').appPopup({
			url:    getUrl('/adm/relief/modalReamtForm.do'),
			title:  title,
			params: JSON.stringify(params), 
			type:  'pageload',
			onhidden: function() {
				// EasyUI datebox 객체 제거
				$('#p_payInfrmYmd').datebox('destroy');
				$('#p_pnopYmd'    ).datebox('destroy');
			},
			onloaded: function( pop ) {
				
				// 구제급여 총계로드
				let fnLoadTotal = function( bizAreaCd ) {
					$.ajaxUtil.ajaxLoad(
						getUrl('/adm/relief/getReliefTotal.do'), 
						{bizAreaCd: bizAreaCd},
						function(ret) {
							let d = (ret ? ret.Data : null);
							$('#p_refbnfTotAmt').numberbox('setValue', $.commUtil.nvlInt(d));
						}
					);
				};
				// 상세조회
				let fnLoadData = function() {
					if ($('#p_mode').val() != MODE.UPDATE)
						return;
					$.ajaxUtil.ajaxLoad(
						getUrl('/adm/relief/getReamtPay.do'), 
						{reamtMngNo: $('#p_reamtMngNo').val(),
						 sn: $('#p_sn').val()},
						function(ret) {
							let d = (ret ? ret.Data : null);
							if (d) {
								$('#registForm').form('load', {
									sn           : d['sn'           ],
									reamtMngNo   : d['reamtMngNo'   ],
									trgtEntNm    : d['trgtEntNm'    ],
									bizAreaCd    : d['bizAreaCd'    ],
									payInfrmAmt  : d['payInfrmAmt'  ],
									payInfrmYmd  : d['payInfrmYmd'  ],
									pnopYmd      : d['pnopYmd'      ],
									refbnfTotAmt : d['refbnfTotAmt' ]
								});
								fnLoadTotal(d['bizAreaCd']);
								$('#p_bizAreaCd').combobox('readonly', true);
							}
						}
					);
				};
				// EasyUI datebox 객체 생성
				$('#p_trgtEntNm'   ).textbox();
				$('#p_refbnfTotAmt').numberbox({groupSeparator:',',formatter:$.commFormat.number, readonly: true, cls:'app-readonly'});
				$('#p_payInfrmAmt' ).numberbox({groupSeparator:',',formatter:$.commFormat.number});
				$('#p_payInfrmYmd' ).datebox();
				$('#p_pnopYmd'     ).datebox();
				$('#p_bizAreaCd'   ).combobox({url: getUrl('/com/cmm/getComboBizMng.do'), onChange: fnLoadTotal});
			    // 일자 입력박스 하이픈(-) 자동삽입 이벤트
				bindDateHyphen( $('#p_payInfrmYmd').datebox('textbox') );
				bindDateHyphen( $('#p_pnopYmd'    ).datebox('textbox') );
				// 데이터 로드
				fnLoadData();
				
				// Validation Rule 정의
				$('#registForm').validate({
					debug: false,
					onfocusout: false,
					onsubmit: false,
					// 검증룰 정의
					rules: {
						bizAreaCd   : {required: true},
						trgtEntNm   : {required: true},
						payInfrmYmd : {required: true, dateHyphen: true},
						pnopYmd     : {required: true, dateHyphen: true},
						payInfrmAmt : {required: true}
					},
					// 검증메세지 정의
					messages: {
						bizAreaCd   : {required: '피해지역은 필수 선택 사항입니다.'},
						trgtEntNm   : {required: '구상금 대상업체는 필수 입력 사항입니다.'},
						payInfrmYmd : {
							required:   '구상금 납부고지일은 필수 입력 사항입니다.',
							dateHyphen: '구상금 납부고지일을 형식에 맞게 입력하세요.'
						},
						pnopYmd     : {
							required:   '구상금 납부최고일은 필수 입력 사항입니다.',
							dateHyphen: '구상금 납부최고일을 형식에 맞게 입력하세요.'
						},
						payInfrmAmt : {required: '구상금 납부고지액은 필수 입력 사항입니다.'}
					},
					invalidHandler: $.validateUtil.popupHandler,
					errorPlacement: validatePlacement
				});			
				
			    // 저장 클릭
			    $('#btnPopupSave').bind('click', function() {
					let frm = $('#registForm');
					// FORM VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;
					// FORM VALIDATION
					if (frm.valid() === false)
						return false;

					let obj = frm.serializeObject();
			        $.popupMsg.confirm('저장하시겠습니까?', function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/relief/saveReamtPay.do'),
			                JSON.stringify(obj),
			                function(ret) {
								$.commMsg.success(ret, '성공적으로 저장되었습니다.', function() {
									doSearch();
			                		pop.close();
								});
			                }
			            );
			        });
				});
			    // 취소 클릭
			    $('#btnPopupClose').bind('click', pop.close);
			}
		}).open();
    }

    // 구제급여 상세내역 팝업 오픈
    //--------------------------------------------------------//
    function doOpenReamtView( params ) {
		$('#appPopupReamtView').appPopup({
			url:    getUrl('/adm/relief/modalReamtView.do'),
			params: JSON.stringify(params), 
			type:  'pageload',
			title: '구제급여 상세내역',
			onloaded: function( pop ) {
				let arrAmt = [
					 "p_mcpAmt"       
					,"p_rcperAmt"     
					,"p_fnrlAmt"      
					,"p_brvfmAmt"     
					,"p_prprtyAmt"    
					,"p_refbnfTotAmt" 
					,"p_payInfrmAmt"  
					,"p_refbnfDiffAmt"
				];
				// 금액 포맷처리
				$.each(arrAmt, function(i,key) {
					let v = $.commUtil.nvlInt( $('#'+key).html() );
					$('#'+key).html( $.commFormat.number(v) );
				});
			    // 확인 클릭
			    $('#btnPopupClose').bind('click', pop.close);
			}
		}).open();
		return false;
    }

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 등록버튼 클릭시 이벤트처리
    $('#btnRegist' ).bind('click', doRegist);
    // 수정버튼 클릭시 이벤트처리
    $('#btnUpdate' ).bind('click', doUpdate);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove' ).bind('click', doRemove);

    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);

});
