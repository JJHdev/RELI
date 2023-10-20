/**
******************************************************************************************
*** 파일명 : listBizMng.js
*** 설명글 : 사업관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02               LSH

***                                     김주호
******************************************************************************************
**/



$(function() {

	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//	
	let P_GRID = $('#appGrid'); //그리드 목록	
	let P_FORM = $('#searchForm'); // 검색폼	
	let P_RFORM = $('#registForm');// 등록폼 객체
	let P_SELECT = false; // 상세조회 데이터


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
		// 그리드 페이징처리 여부
		pagination: true,
		// 그리드 단일행만 선택여부
		singleSelect: true,
		// 그리드 행번호 표시여부
		rownumbers: true,
		// 체크박스 KEY값필드
		//idField: 'aplyNo',
		// 한 페이지 출력수
		pageSize: 10,
		// 칼럼정의
		columns: [[
//			{ field: 'bizAreaCd', width: 150, halign: 'center', align: 'center', title: '번호' },
			{ field: 'bizArea', width: 150, halign: 'center', align: 'center', title: ' 피해지역' },
			{ field: 'bizDtlArea', width: 280, halign: 'center', align: 'center', title: '피해상세지역' },
			{ field: 'bizOder', width: 120, halign: 'center', align: 'center', title: '차수' }
		]],
		// 행선택시 상세조회
		onSelect: doSelect,


	});

	//========================================================//
	// FORM ELEMENTS 정의
	//--------------------------------------------------------//

	// 검색용 피해지역/사업차수 단계형 콤보박스 정의
	// 검색용 피해지역/사업차수 단계형 콤보박스 정의
	let P_SEARCH_COMBO = {
		areaCombo: false,
		oderCombo: false,
		doInit: function() {
			let cmp = this;
			this.areaCombo = $('#srchBizArea').combobox({
				url      : getUrl('/com/cmm/getComboBizMng.do'),
				onChange : function() {
					cmp.doClearOder();
				},
				onLoadSuccess: function() {
					cmp.doClearOder();
				},
				prompt    : '피해지역 선택',
				iconWidth : 22,
				icons: [{
					iconCls : 'icon-clear',
					handler : function() {
						cmp.areaCombo.combobox('clear');
					}
				}]
			});
			this.oderCombo = $('#srchBizOder').combobox({
				url: getUrl('/com/cmm/getComboBizOder.do'),
				onBeforeLoad: function(param) {
					param['bizAreaCd'] = cmp.areaCombo.combobox('getValue');
				},
				prompt: '사업차수 선택',
				iconWidth: 22,
				icons: [{
					iconCls : 'icon-clear',
					handler : function() {
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

	doSearch();

	// 예비조사사업 목록 검색처리
	//--------------------------------------------------------//
	function doSearch() {
		// 상세조회 항목 CLEAR
		//	doRegist();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
		// 검색폼 데이터 객체화
		var obj = P_FORM.serializeObject();
		// 그리드 목록조회 URL
		P_GRID.datagrid('options')['url'] = getUrl('/adm/biz/getListBizMng.do');
		// 검색폼 그리드 검색
		P_GRID.datagrid('load', obj);

		return false;
	}
	// 사업정보 상세조회
	//--------------------------------------------------------//
	function doSelect(index, row) {

		let params = {
			bizAreaCd : row['bizAreaCd'],
			bizOder   : row['bizOder']
		};

		$.ajaxUtil.ajaxLoad(
			getUrl('/adm/biz/viewListBizMng.do'),
			params,
			function(result) {
				var data = result.Data;

				// hidden값의 FORM 데이터 정의
				$.formUtil.toForm(data, P_RFORM);
				// EasyUI BOX의 FORM 데이터 정의
				P_RFORM.form('load', data);
				$(".tabWrap li").eq(1).trigger('click');
				btnchek();
				$('#newBizOder').val("");
			}
		);
		return false;

	}

	P_RFORM.validate({
		// true일 경우 디버깅이 가능하도록 
		// 입력값이 유효해서 submit하지 않는다.
		debug: false,
		// true일 경우 포커스가 떠날때 유효성 검사를 한다.
		onfocusout: false,
		// true일 경우 유효성체크없이 무조건 submit한다.
		onsubmit: false,
		// 검증룰 정의
		rules: {
			
			newBizArea    : 'required', // 제목 필수입력
			newBizDtlArea : 'required', // 내용 필수입력
		},
		// 검증메세지 정의
		messages: {
			
			newBizArea    : '피해지역은 필수 입력 사항입니다.',
			newBizDtlArea : '피해상세지역은 필수 입력 사항입니다.'
		},
		// 에러발생시 에러메세지를 처리할 핸들러 (comm_biz.js에 정의)
		invalidHandler    : validateHandler,
		// 에러발생시 에러메세지를 표시할 위치처리 핸들러 (comm_biz.js에 정의)
		errorPlacement    : validatePlacement
	});


	// 등록된 사업지역 검증
	//--------------------------------------------------------//
	function chekArea(enrlArea, enrlDelArea ) {

		let area        =  document.querySelectorAll("td[field='bizArea']");
		let delArea     =  document.querySelectorAll("td[field='bizDtlArea']");
		let areaValid   =  enrlArea.val().replace(/^\s+|\s+$/gm, '');
		let arrArea     =  [];
		let arrDelArea  =  [];

		// 사업지역 배열 추가
		for (var i = 0; i < area.length; i++) {
			area[i].innerText.replace(/^\s+|\s+$/gm, '');
			arrArea.push(area[i].innerText);
		}
		// 사업상세지역 배열 추가 
		for (var i = 0; i < delArea.length; i++) {
			delArea[i].innerText.replace(/^\s+|\s+$/gm, '');
			arrDelArea.push(delArea[i].innerText);
		}

		// 사업지역 검증
		if (arrArea.includes(areaValid)) {

			// 사업 상세지역 검증
			if (arrDelArea[arrArea.indexOf(areaValid)] == (enrlDelArea.val().replace(/^\s+|\s+$/gm, ''))){

				$.commMsg.alert("이미 등록된 지역입니다. 확인해주세요 ");
				
				return false;
			}


		} else {
			
		}




	}

	// 신규 사업지역 등록
	//--------------------------------------------------------//
	$('#btnSave').bind('click', function() {
	let enrlArea   = $('#newBizArea');
    let enrlDelArea = $('#newBizDtlArea');
		// 등록폼의 VALIDATION 기능 활성화
		if (P_RFORM.validate().settings) {
			P_RFORM.validate().settings.ignore = false;
		}
		//FORM VALIDATION
		if (P_RFORM.valid() === false)
			return false;

		if (chekArea(enrlArea,enrlDelArea) === false) {
			return false;
		}

		$.commMsg.confirm("신규사업지역을 등록하시겠습니까?", function() {
			// 등록폼을 AJAX로 저장처리
			P_RFORM.ajaxForm({
				url     :  getUrl('/adm/biz/saveNewBiz.do'),
				// footer 하단에 위치한 Layer
				target  :  '#uploadFrame',
				// 오류시 처리로직
				error   :  $.ajaxUtil.error,
				// 저장후 처리로직
				success :  function(ret) {
					$.ajaxUtil.success(ret, function(data) {
						// 목록으로 이동
						$.commMsg.alert('성공적으로 등록되었습니다.', function() {
							
                            goUrl(getUrl('/adm/biz/listBizMng.do'));
							return false;
						});
					});
				}
			}).submit();
		});
		return false;

	});


	// 기존 지역 차수생성 버튼
	//--------------------------------------------------------//	
	function gernOder() {

		let params = {
			bizArea      : $('[name=bizArea]'     ).val(),
			bizDtlArea   : $('[name="bizDtlArea"]').val()
		};
		
		if(params.bizArea == ""||params.bizDtlArea==""){
			$.commMsg.alert('피해지역과 피해상세지역을 확인해주세요');
			return false;
		}
		$.ajaxUtil.ajaxLoad(
			getUrl('/adm/biz/searchGernOder.do'),
			params,
			function(result) {
				var data = result.Data;
				console.log(result.Data);
				console.log(result);
				
				$('#newBizOder').val(result.Data.bizOder);
			}
		);
		return false;
	}
	
	

	// 기존 지역 차수 추가등록 버튼
	//--------------------------------------------------------//	
	
	function addAreaOder(){
		
		let params = {
			bizArea      : $('[name=bizArea]'     ).val(),
			bizDtlArea   : $('[name="bizDtlArea"]').val(),
			bizOder      : $('[name="newBizOder"]').val()
		};
				
		
		if(params.bizArea == ""||params.bizDtlArea==""){
			$.commMsg.alert('피해지역과 피해상세지역을 확인해주세요');
			return false;
		}
		
		if(params.bizOder == ""){
			$.commMsg.alert('차수를 확인해주세요');
			return false;
		}
		

		$.commMsg.confirm("해당지역에 차수를 추가등록하시겠습니까?", function() {
			// 등록폼을 AJAX로 저장처리
			P_RFORM.ajaxForm({
				url     :  getUrl('/adm/biz/addAreaOder.do'),
				// footer 하단에 위치한 Layer
				target  :  '#uploadFrame',
				// 오류시 처리로직
				error   :  $.ajaxUtil.error,
				// 저장후 처리로직
				success :  function(ret) {
					$.ajaxUtil.success(ret, function(data) {
						// 목록으로 이동
						$.commMsg.alert('성공적으로 등록되었습니다.', function() {
							
                            goUrl(getUrl('/adm/biz/listBizMng.do'));
							return false;
						});
					});
				}
			}).submit();
		});		
	return false;
		
		
	}
    // 기존 지역 사업내용과 사업지역 수정  버튼
	//--------------------------------------------------------//	
	
	function modifyBiz(){
		
		let params = {
			bizArea      : $('[name=bizArea]'     ).val(),
			bizDtlArea   : $('[name="bizDtlArea"]').val(),
			bizOder      : $('[name="newBizOder"]').val()
		};
				
		
		if(params.bizArea == ""||params.bizDtlArea==""){
			$.commMsg.alert('피해지역과 피해상세지역을 확인해주세요');
			return false;
		}
		


		$.commMsg.confirm("수정하시겠습니까?", function() {
			// 등록폼을 AJAX로 저장처리
			P_RFORM.ajaxForm({
				url     :  getUrl('/adm/biz/modifyBiz.do'),
				// footer 하단에 위치한 Layer
				target  :  '#uploadFrame',
				// 오류시 처리로직
				error   :  $.ajaxUtil.error,
				// 저장후 처리로직
				success :  function(ret) {
					$.ajaxUtil.success(ret, function(data) {
						// 목록으로 이동
						$.commMsg.alert('성공적으로 수정되었습니다.', function() {
							
                            goUrl(getUrl('/adm/biz/listBizMng.do'));
							return false;
						});
					});
				}
			}).submit();
		});		
	return false;
		
		
	}	
	
    // 기존 지역 사업 차수 삭제 버튼
	//--------------------------------------------------------//	
	function deleteBiz(){
		
		let params = {
			bizArea      : $('[name=bizArea]'     ).val(),
			bizDtlArea   : $('[name="bizDtlArea"]').val(),
			bizOder      : $('[name="bizOder"]').val()
		};
				
		
		if(params.bizArea == ""||params.bizDtlArea==""){
			$.commMsg.alert('피해지역과 피해상세지역을 확인해주세요');
			return false;
		}
		
	

		$.commMsg.confirm("삭제하시겠습니까?", function() {
			// 등록폼을 AJAX로 저장처리
			P_RFORM.ajaxForm({
				url     :  getUrl('/adm/biz/deleteBiz.do'),
				// footer 하단에 위치한 Layer
				target  :  '#uploadFrame',
				// 오류시 처리로직
				error   :  $.ajaxUtil.error,
				// 저장후 처리로직
				success :  function(ret) {
					$.ajaxUtil.success(ret, function(data) {
						// 목록으로 이동
						$.commMsg.alert('성공적으로 삭제되었습니다.', function() {
							
                            goUrl(getUrl('/adm/biz/listBizMng.do'));
							return false;
						});
					});
				}
			}).submit();
		});		
	return false;
	
	}

    // 기존 지역 사업 전체 삭제 버튼
	//--------------------------------------------------------//	
	function allDeleteBiz(){

		let params = {
			bizArea      : $('[name=bizArea]'     ).val(),
			bizDtlArea   : $('[name="bizDtlArea"]').val(),
			bizOder      : $('[name="bizOder"]').val()
		};
				
		
		if(params.bizArea == ""||params.bizDtlArea==""){
			$.commMsg.alert('피해지역과 피해상세지역을 확인해주세요');
			return false;
		}
		

		
		$.commMsg.confirm("삭제하시겠습니까?", function() {
			// 등록폼을 AJAX로 저장처리
			P_RFORM.ajaxForm({
				url     :  getUrl('/adm/biz/allDeleteBiz.do'),
				// footer 하단에 위치한 Layer
				target  :  '#uploadFrame',
				// 오류시 처리로직
				error   :  $.ajaxUtil.error,
				// 저장후 처리로직
				success :  function(ret) {
					$.ajaxUtil.success(ret, function(data) {
						// 목록으로 이동
						$.commMsg.alert('성공적으로 삭제되었습니다.', function() {
							
                            goUrl(getUrl('/adm/biz/listBizMng.do'));
							return false;
						});
					});
				}
			}).submit();
		});		
	return false;
	
	}

    // 탭 바뀔떄 버튼도 바뀜 
    function btnchek(){
	
		if($('#newAreaTab').css('display') ==='block'){
		     $('#btnSave').show();
	         $('#btnAddOder').hide();
             $('#btnModify').hide();
             $('#btnDelete').hide();
			 $('#btnAllDelete').hide();
	
		}else if($('#addOderTab').css('display')==='block'){
		     $('#btnSave').hide();
	         $('#btnAddOder').show();		
             $('#btnModify').hide();
             $('#btnDelete').hide();
			 $('#btnAllDelete').hide();
		}else{
		     $('#btnSave').hide();
	         $('#btnAddOder').hide();		
             $('#btnModify').show();
             $('#btnDelete').show();
             $('#btnAllDelete').show();
		}
	}


// 탭클릭 이벤트 처리
$(".tabWrap li").on("click", function() {
	var idx = $(this).index()
	$(this).parent().find("li").removeClass("on");
	$(this).addClass("on");
	$('.tabInnerFrom').removeClass("on");
	$('.tabInnerFrom').eq(idx).addClass("on");
	btnchek();
	
});


// 질의응답 탭 클릭 이벤트 발생
$(".boxWrap > .tabWrap li").eq(0).trigger('click');


// 검색버튼 클릭시 이벤트 처리
$('#btnSearch').bind('click', doSearch);
$('#gernOder').bind('click' ,  gernOder);
$('#btnAddOder').bind('click' , addAreaOder);
$('#btnModify').bind('click' , modifyBiz);
$('#btnDelete').bind('click' , deleteBiz);
$('#btnAllDelete').bind('click' , allDeleteBiz);

});
