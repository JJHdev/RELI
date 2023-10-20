/**
******************************************************************************************
*** 파일명 : listLwstIncdnt.js
*** 설명글 : 취약계층소송지원 - 소송개요현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    한금주
******************************************************************************************
**/

 function inputNumberFormat(obj) {
     obj.value = comma(uncomma(obj.value));
 }

 function comma(str) {
     str = String(str);
     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
 }

 function uncomma(str) {
     str = String(str);
     return str.replace(/[^\d]+/g, '');
 }


$(function() {
	// 그리드 객체
	var P_GRID = $('#grid');
	// 검색폼 객체
	var P_SFORM  = $('#searchForm' );
	// 등록폼 객체
    var P_RFORM  = $('#registForm' );
	//업무 구분
	let P_DTY_CD  = CODE.DTY_CD.LWST;
	//이력 관리
	let P_HISTORY = false;
	//상세조회 데이터
	let P_SELECT  = false;
	// 2021.12.02 LSH 소송추가 팝업객체
	let P_POPUP   = false;
	// 2021.12.02 LSH 소송추가 팝업내 등록폼
	let P_POPFORM = false;
	// 2021.12.02 LSH 소송추가 팝업내 그리드
	let P_POPGRID = false;
	// 2021.12.02 LSH 소송추가 팝업내 그리드 편집INDEX
	let P_POPIDX  = undefined;
	// 향후기일 조회 그리드
	let P_DTLGRID   = $('#dtltGrid'       ); // 대상자조회 목록	
	
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
        idField:'incdntMngNo',
        // 칼럼정의
       columns: [[
		    {field:'chckId'       ,checkbox: true},
            {field:'incdntMngNo'  , hidden: true, width:50,halign:'center',align:'center',title:'사건 관리 번호'},
            {field:'incdntNo'  ,width:150,halign:'center',align:'center',title:'사건 번호'},
			{field:'incdntNm'  ,width:220,halign:'center',align:'center',title:'사건 명'},
            {field:'jdgmtDeptNm'  ,width:200,halign:'center',align:'center',title:'재판부'},
			{field:'lwstYmd'  ,width:100,halign:'center',align:'center',title:'소송일자'}
        ]],
		onSelect: doUpdate
    });

	P_DTLGRID.datagrid({
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
        idField:'sn',
        // 칼럼정의
        columns: [[
	        {field:'chckId'      ,checkbox: true},
            {field:'incdntMngNo'    , hidden: true, width:100,halign:'center',align:'center',title:'사건관리번호'},
            {field:'dudtYmd'   ,width:150,halign:'center',align:'center',title:'기일 일자'},
            {field:'dudtTm'   ,width:100,halign:'center',align:'center',title:'기일 시간'},
            {field:'dudtSeNm',width:120,halign:'center',align:'center',title:'기일구분'},
			{field:'dudtPlce',width:156,halign:'center',align:'center',title:'기일장소'},
			{field:'rsltNm',width:100,halign:'center',align:'center',title:'결과내용'}
        ]]
    });

    // 사용자정보 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstIncdnt.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj); 
        return false;
    }

    // 향후기일 그리드 검색처리
    //--------------------------------------------------------//
    function doDtlSearch() {
		let parmas = {
			incdntMngNo : P_SELECT['incdntMngNo']
		}; 
		// 선택된 항목 CLEAR
		P_DTLGRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_DTLGRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstIncdntDtl.do');
        // 검색폼 그리드 검색
        P_DTLGRID.datagrid('load', parmas); 
        return false;
    }

	// 사용자정보 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {
		var params = {
			incdntMngNo:   row['incdntMngNo'  ]
		};
		
		$.ajaxUtil.ajaxLoad(
            getUrl('/adm/support/viewListLwstIncdnt.do'), 
			params,
            function(result) {
                var data = result.Data;
				P_SELECT = data;
				// hidden값의 FORM 데이터 정의
			    data['lwstPricesAmt'] = comma(data['lwstPricesAmt']);
                data['papstmpAmt'] = comma(data['papstmpAmt']);     
				$.formUtil.toForm(data, P_RFORM);
				// EasyUI BOX의 FORM 데이터 정의
				P_RFORM.form('load', data);
				// 이력관리 목록로드
				doDtlSearch();
            }
        );
        return false;
    } 
	
	
	$('#appLwstTermBox').appTermBox({
		label:'소송일자',
		stName:'srchLwstStdt',
		enName:'srchLwstEndt'
	});
	
	
	// FORM 검증
	function doValidate() {	
	    //사건번호
	    var incdntNo = $('#p_incdntNo');
	    //사건명
	    var incdntNm = $('#p_incdntNm');
	    //소가
	    var lwstPricesAmt = $('#p_lwstPricesAmt');
	    //인지액
	    var papstmpAmt = $('#p_papstmpAmt');
	    //소송일
	    var lwstYmd1 = $('#p_lwstYmd1');
	    var lwstYmd2 = $('#p_lwstYmd2');
	    var lwstYmd3 = $('#p_lwstYmd3');
	    //재판부
	    var jdgmtDeptNm = $('#p_jdgmtDeptNm');
	
		var inval_Arr = false;	
	
	    //신청인 정보
	     if(incdntNo.val() == ''){
	        inval_Arr[0] = false;
	        alert('사건번호를 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[0] = true;
	     }
	
	     if(incdntNm.val() == ''){
	        inval_Arr[1] = false;
	        alert('사건명을 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[1] = true;
	     }
	
	     if(lwstPricesAmt.val() == ''){
	        inval_Arr[2] = false;
	        alert('소가를 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[2] = true;
	     }
	
	     if(papstmpAmt.val() == ''){
	        inval_Arr[3] = false;
	        alert('인지액을 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[3] = true;
	     }
	
	     if(lwstYmd1.val() == ''){
	        inval_Arr[4] = false;
	        alert('소송일을 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[4] = true;
	     }
	
	
	     if(lwstYmd2.val() == ''){
	        inval_Arr[5] = false;
	        alert('소송일을 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[5] = true;
	     }
	
	
	     if(lwstYmd3.val() == ''){
	        inval_Arr[6] = false;
	        alert('소송일을 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[6] = true;
	     }
	
	     if(jdgmtDeptNm.val() == ''){
	        inval_Arr[7] = false;
	        alert('재판부를 확인해주세요.');
	        return false;
	     } else {
	        inval_Arr[7] = true;
	     }
	
	
	     //전체 유효성 검사
	     var validAll = true;
	     for(var i = 0; i < inval_Arr.length; i++){
	        if(inval_Arr[i] == false){
	           validAll = false;
	        }
	     }
	
	     if(validAll == true){ // 유효성 모두 통과
	        return true;      
	     } else{
	        alert('정보를 다시 확인하세요.')
	        return false;
	     }
	}		
	//소송 저장
	function  doSaveLwst() {
		
	    //소송일자 병합
	    $('#p_lwstYmd').val('');
	    if ($('#p_lwstYmd1').val() != '' &&
	        $('#p_lwstYmd2').val() != '' &&
	        $('#p_lwstYmd3').val() != '' ) {
	        $('#p_lwstYmd').val(
	            $('#p_lwstYmd1').val() + 
	            $('#p_lwstYmd2').val() +
	            $('#p_lwstYmd3').val() 
	        );
		}
		
		$('#p_lwstPricesAmt').val(uncomma($('#p_lwstPricesAmt').val()));
		$('#p_papstmpAmt').val(uncomma($('#p_papstmpAmt').val()));

	    // FORM 검증결과가 실패이면
	    if (doValidate() === false) {
	        return false;
	    }
		// 폼데이터를 객체로 직렬화한다.
		let data = P_POPFORM.serializeObject();
		// 향후기일 목록데이터
		data['dtlsList'] = getRegiGridRows();
		
	    $.commMsg.confirm("소송을 저장하시겠습니까?", function() {
			// 목록데이터를 넘기기 위해서 ajaxSave를 사용한다.
	        $.ajaxUtil.ajaxSave(
	            getUrl('/adm/support/regiLwstIncdnt.do'), 
	            JSON.stringify(data),
	            function(ret) {
	                $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 저장되었습니다.', function() {
							// 팝업창 닫기
							P_POPUP.close();
		                    goUrl(getUrl('/adm/support/listLwstIncdnt.do'));
						});
	                });
	            }
	        );
	    });
	    return false;
	} 
	
	// 개요현황 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/support/downLwstIncdntExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }

	// 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);

	// 2021.12.02 LSH 소송 추가하기 버튼 클릭시 이벤트처리
    $('#btnRegiLwst' ).bind('click', doOpenRegiLwst);
	// 2021.12.03 LSH 소송 수정하기 버튼 클릭시 이벤트처리
    $('#btnUpdtLwst' ).bind('click', doOpenUpdtLwst);
	// 2021.12.03 LSH 소송 삭제하기 버튼 클릭시 이벤트처리
    $('#btnDeltLwst' ).bind('click', doDeltLwst);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);

    // 검색 실행
    doSearch();

    // 2021.12.03 LSH 소송삭제하기
    //--------------------------------------------------------//
    function doDeltLwst() {
	
    	const row = P_GRID.datagrid('getSelected');
    	if (row == null) {
    		$.commMsg.alert('삭제할 행을 선택하세요.');
    		return false;
    	}
    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			let data = {
				// 삭제 모드			
				mode: MODE.REMOVE,
				incdntMngNo: row['incdntMngNo']
			};
            // AJAX로 저장처리
        	$.ajaxUtil.ajaxSave(
                getUrl('/adm/support/regiLwstIncdnt.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 삭제되었습니다.', function() {
							// 목록 검색
	                        doSearch();
						});
                    });
                }
        	);
    	});
        return false;
	}

    // 2021.12.02 LSH 소송추가하기
    //--------------------------------------------------------//
    function doOpenRegiLwst() {
		// 팝업 오픈		
		doOpenLwst({
			title: '소송 등록',
			params: {
				mode: MODE.INSERT
			}
		});
	}
    // 2021.12.03 LSH 소송수정하기
    //--------------------------------------------------------//
    function doOpenUpdtLwst() {
	
    	const row = P_GRID.datagrid('getSelected');
    	if (row == null) {
    		$.commMsg.alert('수정할 행을 선택하세요.');
    		return false;
    	}
		// 팝업 오픈		
		doOpenLwst({
			title: '소송 수정',
			params: {
				mode: MODE.UPDATE,
				incdntMngNo: row['incdntMngNo']
			}
		});
	}

    // 2021.12.02 LSH 소송추가하기 팝업 오픈
    //--------------------------------------------------------//
    function doOpenLwst( args ) {

		// 팝업객체 변수에 담기
		P_POPUP = $('#appPopupLwst').appPopup({
			// 팝업화면 URL
			url:   getUrl('/adm/support/modalLwstIncdnt.do'),
			// 팝업로드 방식
			type:  'pageload',
			// 팝업제목
			title: args.title,
			// 팝업로딩 조회조건
			params: JSON.stringify(args.params),
			// 팝업로딩 후 실행 함수
			onloaded: function( pop ) {

				// 팝업폼 객체 변수에 담기
				P_POPFORM = $('#popupForm');
				
				// 소송일자 콤보박스
				$('#p_lwstYmd1').appComboBox({
					type: 'static',
					init: {code:'',text:'년'},
					rows: STORE.getYears()
				});
				$('#p_lwstYmd2').appComboBox({
					type: 'static',
					init: {code:'',text:'월'},
					rows: STORE.getMonths()
				});
				$('#p_lwstYmd3').appComboBox({
					type: 'static',
					init: {code:'',text:'일'},
					rows: STORE.getDays()
				});
				// 소송일자 날짜분리
				$.formUtil.splitData('p_lwstYmd','date');
				
				// 편집형 그리드 정의
				doInitRegiGrid();

				// 저장버튼 클릭					
				$('#btnSaveLwst').on("click", doSaveLwst);
				// 취소버튼 클릭					
				$('#btnCancelLwst').on("click", function() {
					P_POPUP.close();
					return false;
				});
				// 수정모드인 경우
				if (args.params.mode == MODE.UPDATE) {
					// 향후기일목록 조회
					doSearchDtlList(args.params);
				}
			}
		}).open();

        return false;
    }


	// 2021.12.03 LSH 향후기일 편집형 그리드 - 목록조회
    //--------------------------------------------------------//
    function doSearchDtlList( params ) {
		// 선택된 항목 CLEAR
		P_POPGRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_POPGRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstIncdntDtl.do');
        // 검색폼 그리드 검색
        P_POPGRID.datagrid('load', params); 
        return false;
    }

	// 2021.12.02 LSH 향후기일 편집형 그리드 - 편집종료처리
    //--------------------------------------------------------//
    function endEditing(){
        if (P_POPIDX == undefined){return true}
        if (P_POPGRID.datagrid('validateRow', P_POPIDX)){
            P_POPGRID.datagrid('endEdit', P_POPIDX);
            P_POPIDX = undefined;
            return true;
        } else {
            return false;
        }
    }
	// 2021.12.02 LSH 향후기일 편집형 그리드 - 저장목록 반환
    //--------------------------------------------------------//
	function getRegiGridRows() {
		// 향후기일 목록데이터
		endEditing();
		return P_POPGRID.datagrid('getChanges');
	}

	// 2021.12.02 LSH 향후기일 편집형 그리드 정의
    //--------------------------------------------------------//
	function doInitRegiGrid() {
		// 향후기일 그리드
		P_POPGRID = $('#p_popupGrid').datagrid({
			// 화면영역맞춤
			fit: true,
			striped: false,
			singleSelect: true,
			cls: 'app-edit-grid',
			// 행추가삭제 기준 KEY
			idField: 'idx',
			// 그리드 툴바
			toolbar: [
				// 행추가 버튼
				{text:'추가',iconCls:'fa fa-plus' ,
					handler: function() {
				        if (endEditing()){
				            P_POPGRID.datagrid('appendRow',{mode: MODE.INSERT});
				            P_POPIDX = P_POPGRID.datagrid('getRows').length-1;
				            P_POPGRID.datagrid('selectRow', P_POPIDX)
				                     .datagrid('beginEdit', P_POPIDX);
				        }
						return false;
					}
				},
				// 행삭제 버튼
				{text:'삭제',iconCls:'fa fa-minus',
					handler: function() {
				        if (P_POPIDX == undefined) { return false; }
						P_POPGRID.datagrid('getRows')[P_POPIDX]['mode'] = MODE.REMOVE;
				        P_POPGRID.datagrid('cancelEdit', P_POPIDX)
				                 .datagrid('deleteRow' , P_POPIDX);
				        P_POPIDX = undefined;
						return false;
					}
				}
			],
	        // 칼럼정의
	        columns: [[
	            {field:'dudtYmd',width:150,halign:'center',align:'center',title:'일자', 
					editor: {type:'datebox'}
				},
	            {field:'dudtTm',width:100,halign:'center',align:'center',title:'시각', 
					editor: {
						type:'combobox', 
						options: {
							mode: 'local',
							data: STORE.getHours($.commFormat.hour, $.commFormat.hour),
							panelHeight: 150
						}
					}
				},
	            {field:'dudtSeNm',width:150,halign:'center',align:'center',title:'기일구분', 
					editor: {
						type:'combobox', 
						options: {
							url: getUrl('/com/cmm/getComboCode.do'), 
							queryParams:{upCdId:'CT008'}
						}
					}
				},
	            {field:'dudtPlce',width:150,halign:'center',align:'center',title:'기일장소', maxlength: 3, 
					editor: {type:'textbox'}
				},
				{field:'rsltNm'  ,width:150,halign:'center',align:'center',title:'결과', 
					editor: {
						type:'combobox', 
						options: {
							url: getUrl('/com/cmm/getComboCode.do'), 
							queryParams:{upCdId:'CT024'}
						}
					}
				},
//					editor: {type:'textbox'}
//				},
			]],
			// 편집형 에디팅 종료 이벤트 처리
			onEndEdit: function(index, row) {
		        var ed1 = $(this).datagrid('getEditor', {index: index,field: 'dudtTm'});
				var ed2 = $(this).datagrid('getEditor', {index: index,field: 'dudtSeNm'});
				var ed3 = $(this).datagrid('getEditor', {index: index,field: 'rsltNm'});
		        row['dudtTm'  ] = $(ed1.target).combobox('getText');
				row['dudtSeNm'] = $(ed2.target).combobox('getText');
				row['dudtSeCd'] = $(ed2.target).combobox('getValue');
				row['rsltCn']	= $(ed3.target).combobox('getValue');
				row['rsltNm']	= $(ed3.target).combobox('getText');
			},
			// 편집형 에디팅 시작 이벤트 처리
	        onClickCell: function(index, field) {
				if (P_POPIDX == index)
					return;
				let g = $(this);
	            if (endEditing()){
	                g.datagrid('selectRow', index)
	                 .datagrid('beginEdit', index);
	                var ed = g.datagrid('getEditor', {index:index, field:field});
	                if (ed)
	                    ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
	                P_POPIDX = index;
	            } 
				else {
	                setTimeout(function(){ g.datagrid('selectRow', P_POPIDX); },0);
	            }
			},
		    // 결과데이터 로딩후 처리
			onLoadSuccess: function(data) {
				if (data.rows && data.rows.length > 0) {
					$.each(data.rows, function(i,r) {
						r['mode'] = MODE.UPDATE;
					});
				}
			}
		});
	}
});
