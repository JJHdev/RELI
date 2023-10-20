/**
******************************************************************************************
*** 파일명 : listLwstPrgre.js
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

function initLwstPrgre(){
   /*  기본 이벤트 등록 : 검색 버튼 키다운*/
   $(".adrBtn1").click(function(e){
	   var enterKeyCode = 13;
	   var element = $(this);
	   var elName = element.attr("name");
	   if(e.keyCode == enterKeyCode){
		   console.log(elName);
		   switch(elName){
		   case "keyword":
		   fnLwstNo();
		   break;
			   default:

		   }
	   }
   })
}


$(function() {

	 //========================================================//
   // 콤보박스 정의
   //--------------------------------------------------------//

   $('#appAplyTermBox').appTermBox({
	   label:'신청일자',
	   stName:'srchAplyStdt',
	   enName:'srchAplyEndt'
   });

   $('#prgreStusCd').appSelectBox({
   label:   '신청상태',
   form:    'checkbox',
   name:    'prgreStusList',
   params:  {upCdId: 'CT023'},
   filter: function(row) {
	   // 표출 항목
	   if (row['code'] == '10' || row['code'] == '20')
		   return true;
	   return false;
	   }
   });

//   $('#appSprtSeCd').appSelectBox({
//   form:    'checkbox',
//   name:    'sprtSeCdList',
//   params:  {upCdId: 'CT021'}
//   });

   $('#appSprtSeCdList').appSelectBox({
   label:   '지원구분',
   form:    'checkbox',
   name:    'sprtSeCdList',
   params:  {upCdId: 'CT021'}
   });


   //========================================================//
   // 화면 스크립트 내에 사용할 객체,상수 정의
   //--------------------------------------------------------//
   var P_GRID   = $('#grid'       ); // 그리드 객체
   var P_SFORM  = $('#searchForm' ); // 검색폼 객체
   var P_RFORM  = $('#registForm' ); // 등록폼 객체
   let P_DTY_CD  = CODE.DTY_CD.LWST;    // 업무구분(취약계층소송지원)
   let P_HISTORY = false;                 // 이력관리 목록
   let P_REGILWST = false;                 // 소송지원목록
   let P_SELECT  = false;                 // 상세조회 데이터
   let aplyNo = $('#aplyNo').val();
   let P_POPIDX  = undefined;
   let P_POPGRID = false;
   let P_FILES   = $('#appAplyFileList'); // 신청파일목록
   let P_APLY_ODER = '0';                 // 신청차수 기본값
   let P_DTLGRID   = $('#dtltGrid'       ); // 대상자조회 목록

   let P_FORMAT  = {
   // 진행상태 포맷처리
   stusNm: function(value, row) {
	   // 보완처리상태가 있으면
	   if (!$.commUtil.empty(row['spleStusCd'])) {
		   return row['spleStusNm'];
	   }
	   return value;
   }
};

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
		   {field:'aplcntAddrLwst'  ,width:350,halign:'center',align:'center',title:'신청인 주소'},
		   {field:'aplcntMbtelNo'  ,width:140,halign:'center',align:'center',title:'신청인 휴대전화번호'},
		   {field:'respdntNm'  ,width:140,halign:'center',align:'center',title:'피신청인 성명'},
		   {field:'prgreStusNm'  ,width:110,halign:'center',align:'center',title:'상태'},
			{field:'mdfcnYmd'  ,width:110,halign:'center',align:'center',title:'접수일자'}
	   ]],
	   // 행선택시 수정하기
	   onSelect: doUpdate
   });

	// 향후기일 그리드 검색처리
    //--------------------------------------------------------//
    function doDtlSearch( index, row ) {
		let parmas = {
			incdntMngNo : row['incdntMngNo']
		};
		// 선택된 항목 CLEAR
		P_DTLGRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_DTLGRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstIncdntDtl.do');
        // 검색폼 그리드 검색
        P_DTLGRID.datagrid('load', parmas);
        return false;
    }


   P_FILES = P_FILES.appAplyFile({
	   mode:   MODE.LIST,
	   system: SYSTEM.ADMIN['code']
   });

   // 소송 지원 그리드 검색처리
   //--------------------------------------------------------//
   function doSprtSearch() {
	   let parmas = {
		   incdntMngNo : P_SELECT['incdntMngNo']
	   };
	   // 선택된 항목 CLEAR
	   P_SPRTGRID.datagrid('clearSelections');
	   // 그리드 목록조회 URL
	   P_SPRTGRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstSprtAply.do');
	   // 검색폼 그리드 검색
	   P_SPRTGRID.datagrid('load', parmas);
	   return false;
   }

   // 이력관리영역 (comm_adm.js 참고)
   P_HISTORY = $('#appHistoryTable').appTableLayout({
	   cls:    'app-h200',
	   columns: [
		   {name: 'regDate', label: '접수일자'},
		   {name: 'hstCn'  , label: '이력내용', key: 'sn', dblclick: doSelectHistory},
		   {name: 'rgtrNm' , label: '작성자'}
	   ],
	   nodata: true
   });

//	// 소송지원추가
   P_REGILWST = $('#appLwstSearchTable').appTableLayout({
	   cls:    'app-h200',
	   columns: [
		   {name: 'incdntMngNo',hidden: true,label: '사건관리번호',},
		   {name: 'incdntNo', label: '사건 번호'},
		   {name: 'lwstSprtNm', label: '지원 사항'},
		   {name: 'lwstSprtAmt'  , label: '지원 금액', formatter: $.commFormat.number},
		   {name: 'rgtrNm' , label: '등록자'}
	   ],
	   // 행선택시 소송지원 선택처리
       select: doIncdntSelect,
	   // 행로드후 처리함수
	   callback: function(cmp, data) {
		  if (data.length > 0)
		      P_REGILWST.selectRow(0);
	   },
	   nodata: true
   });

   // 소송지원 항목선택 처리
   //--------------------------------------------------------//
   function doIncdntSelect( index, row ) {
		// 사건정보 상세조회
		$.ajaxUtil.ajaxLoad(
			getUrl("/adm/support/viewListLwstIncdnt.do"),
			{incdntMngNo: row['incdntMngNo']},
			function(ret) {
				let data = ret.Data;
				$.formUtil.toForm({
					"incdntNo"      : data["incdntNo"     ],
					"incdntNm"      : data["incdntNm"     ],
					"aplcntNmCnt"   : data["aplcntNmCnt"     ],
					"respdntNm"     : data["respdntNm"    ],
					"lwstPricesAmt" : data["lwstPricesAmt"],
					"papstmpAmt"    : data["papstmpAmt"   ],
					"lwstYmd"       : data["lwstYmd"      ],
					"jdgmtDeptNm"   : data["jdgmtDeptNm"  ]
				}, P_RFORM);
			}
		);
		// 향후기일 그리드 검색 처리
		doDtlSearch(index, row);
   }

   // 소송지원내용 목록 검색
   //--------------------------------------------------------//
   function doSprtSearch( aplyNo ) {

	   // 향후기일 그리드 데이터 리셋
       P_DTLGRID.datagrid('loadData', {"total":0,"rows":[]});

	   P_REGILWST.load(
		   getUrl('/adm/support/getlistLwstSprtAply.do'), {
		   aplyNo:  P_SELECT['aplyNo'],
	   });
	   return false;
   }

	// [이력관리팝업] 이력관리 목록검색
   //--------------------------------------------------------//
   function doLoadHistory( aplyNo ) {
	   P_HISTORY.load(
		   getUrl('/adm/biz/getListMngHst.do'), {
		   dtySeCd: P_DTY_CD,
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


//--------------------------------------------------------//
   function doSupportLwst() {
	   const row = P_GRID.datagrid('getSelected');
	   if (row == null) {
		   $.commMsg.alert('수정할 행을 선택하세요.');
		   return false;
	   }

	   // 팝업 오픈
	   doOpenSupportLwst({
		   title: '소송지원 등록',
		   params: {
			   mode: MODE.INSERT,
			   aplyNo:  P_SELECT['aplyNo']
		   }
	   });
   }

   //--------------------------------------------------------//
   function doOpenSupportLwst( args ) {

	   // 팝업객체 변수에 담기
	   P_POPUP = $('#appPopupLwstSupport').appPopup({
		   // 팝업화면 URL
		   url:   getUrl('/adm/support/modalLwstAplyPrgre.do'),
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

			   // LSH. 사건명칭 EasyUI textbox 설정
			   $('#p_incdntNm').textbox();

			   // LSH. 사건번호 EasyUI combobox 설정
			   $('#p_incdntNo').combobox({
				   queryParams: {
					aplyNo:  P_SELECT['aplyNo']
					},
				   // 콤보박스 검색 URL
				   url: getUrl('/adm/support/searchLwstNo.do'),
				   // 콤보박스 옵션의 텍스트필드에 해당되는 칼럼명
				   textField:  'searchincdntNo',
				   // 콤보박스 옵션의 값필드에 해당되는 칼럼명
				   valueField: 'searchincdntNo',
				   // 콤보박스 데이터 로딩필터
				   loadFilter: function( data ) {
					   return data['Data'];
				   },
				   // 콤보박스 옵션값 선택 이벤트
				   onSelect: function( record ) {
					   $('#p_incdntNm').textbox('setValue', record['incdntNm'] );
					   $('#p_incdntMngNo').val(record['incdntMngNo']);
				   }
			   });

			   // 편집형 그리드 정의
			   doInitRegiLwstGrid();

			   // 저장버튼 클릭
			   $('#btnSaveAplyLwst').on("click", doSaveAplyLwst);
			   // 취소버튼 클릭
			   $('#btnCancelLwst').on("click", function() {
				   P_POPUP.close();
				   return false;
			   });
		   }
	   }).open();

	   return false;
   }

   // FORM 검증
   function doValidate() {
	   // LSH. EasyUI textbox로 정의된 사건명의 값을 가져온다.
	   var incdntNm = $('#p_incdntNm').textbox('getValue');
	   // LSH. EasyUI combobox로 정의된 사건번호의 값을 가져온다.
	   var incdntNo = $("#p_incdntNo").combobox('getValue');

		var inval_Arr = false;

	   // LSH. 빈값인지 체크 (null or undefined or "")
	   if($.commUtil.empty(incdntNm)){
		   inval_Arr[0] = false;
		   alert('사건 명을 확인해주세요.');
		   return false;
		} else {
		   inval_Arr[0] = true;
		}

	   // LSH. 빈값인지 체크 (null or undefined or "")
	   if($.commUtil.empty(incdntNo)){
		   inval_Arr[1] = false;
		   alert('사건 번호를 선택해주세요.');
		   return false;
		} else {
		   inval_Arr[1] = true;
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

   //소송지원 저장
   function doSaveAplyLwst() {
	   // FORM 검증결과가 실패이면
	   let isValid = doValidate();
	   console.log('isValid='+isValid);
	   if (isValid === false) {
		   return false;
	   }
	   // 폼데이터를 객체로 직렬화한다.
	   let data = P_POPFORM.serializeObject();
	   // 향후기일 목록데이터
	   data['lwstSprtList'] = getRegiGridRows();

	   $.commMsg.confirm("소송을 저장하시겠습니까?", function() {
		   // 목록데이터를 넘기기 위해서 ajaxSave를 사용한다.
		   $.ajaxUtil.ajaxSave(
			   getUrl('/adm/support/regiLwstAply.do'),
			   JSON.stringify(data),
			   function(ret) {
				   $.ajaxUtil.success(ret, function() {
					   $.commMsg.alert('성공적으로 저장되었습니다.', function() {
						   // 팝업창 닫기
						   P_POPUP.close();
						   goUrl(getUrl('/adm/support/listLwstPrgre.do'));
					   });
				   });
			   }
		   );
	   });
	   return false;
   }

function fnLwstNo(){

   var searchincdntNo = $('#searchincdntNo').val();

   // 조회결과 테이블객체
   var searchedTable = $('#searchedTable');
   var searchResult = $('#searchResult');
   // 조회결과 리셋
   searchedTable.find('tbody').html('');

   // 1. ajaxDefault를 사용할 경우 (동기식 처리)
   var result = $.ajaxUtil.ajaxDefault(
	   getUrl("/adm/support/searchLwstNo.do"), {
		   searchincdntNo : searchincdntNo
	   }
   );

   if (result && result.Data) {
	   var searchLwstNo = result.Data;
	   // 식별아이디가 없을 경우
	   if (searchLwstNo.length == 0) {
		   // 에러 메세지 표시
		   searchResult.append('<tr colspan="3">등록된 사건번호가 없습니다.</tr>');
	   }
	   // 식별아이디가 있을 경우
	   else {
		  $('#searchedTable > tbody').html('');
			// 데이터 배열 LOOP
			$.each(searchLwstNo, function(index, item) {

		   $('#searchedTable > tbody').append('<tr></tr>');
		   $('#searchedTable > tbody > tr:last').append('<td>'+item.incdntMngNo+'</td>');

		   // 버튼 객체정의
		   let btn = $('<button class="checkBtn"></button>');
		   btn.append(item.searchincdntNo);
		   btn.data('incdntMngNo',item.incdntMngNo);
		   btn.data('searchincdntNo',item.searchincdntNo);
		   // 버튼클릭시 selectLwstNo 이벤트 바인딩 처리
		   btn.bind('click', selectLwstNo);

		   let btnTd = $('<td></td>');
		   btnTd.append(btn);

		   $('#searchedTable > tbody > tr:last').append(btnTd);
		   $('#searchedTable > tbody > tr:last').append('<td>'+item.incdntNm+'</td>');
		});
	   }
   }
}

function selectLwstNo() {
   // 버튼에서 정의한 데이터를 가져온다.
   let incdntNo   = $(this).data('searchincdntNo');
   let incdntMngNo   = $(this).data('incdntMngNo');

   $('#incdntNo').val(incdntNo);
   $('#incdntMngNo').val(incdntMngNo);

   console.log(incdntNo);
   console.log(incdntMngNo)

   $('#popupLwstSearch').modal("hide");

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
				   aplyNo:  P_SELECT['aplyNo'],
				   sn:      $(this).data('key')
			   })
		   },
		   saveCallback: doLoadHistory
	   });
	   return false;
   }

   // 사용자정보 검색처리
   //--------------------------------------------------------//
   function doSearch() {
	   // 선택된 항목 CLEAR
	   P_GRID.datagrid('clearSelections');
	   // 검색폼 데이터 객체화
	   var obj = P_SFORM.serializeObject();
	   // 그리드 목록조회 URL
	   P_GRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwstPrgre.do');
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
		   getUrl('/adm/support/viewlistLwstPrgre.do'),
		   params,
		   function(result) {
			   var data = result.Data;
			   P_SELECT = data;
			   aplyNo = P_SELECT['aplyNo'];
			  incdntMngNo = P_SELECT['incdntMngNo'];
			   data['stusNm'] = P_FORMAT.stusNm(data['prgreStusNm'], data);
			   // hidden값의 FORM 데이터 정의
			   $.formUtil.toForm(data, P_RFORM);
			   // EasyUI BOX의 FORM 데이터 정의
			   P_RFORM.form('load', data);

			   P_FILES.loadList({
				   dtySeCd:   data['papeDtySeCd'],
				   dcmtNo:    data['aplyNo'],
				   dtlDcmtNo: P_APLY_ODER
			   });

			   // 보완요청중이 아닌 경우에만 버튼표시
			   if (data['spleStusCd'] != CODE.SPLE_STUS.APPLY) {
			   // 보완요청버튼 표시
			   $('#btnSplemnt').show();
		   }
			   // 이력관리 목록로드
			   doLoadHistory(data['aplyNo']);
			   doSprtSearch(data['aplyNo']);
		   }
	   );
	   return false;
   }
   // 2021.12.02 LSH 소송지원 편집형 그리드 - 편집종료처리
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

  function getRegiGridRows() {
	  // 향후기일 목록데이터
	  endEditing();
	  return P_POPGRID.datagrid('getChanges');
  }

   function doInitRegiLwstGrid() {
	   // 소송지원 그리드
	   P_POPGRID = $('#p_popupLwstGrid').datagrid({
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
			   {field:'lwstSprtNm',width:250,halign:'center',align:'center',title:'지원 내용',
				   editor: {
					   type:'combobox',
					   options: {
						   url: getUrl('/com/cmm/getComboCode.do'),
						   queryParams:{upCdId:'CT032'}
					   }
				   }
			   },
			   {field:'lwstSprtAmt',width:250,halign:'center',align:'center',title:'지원 금액',
					   editor: {type:'numberbox', options: {groupSeparator:','}},
					   formatter: $.commFormat.number
			   }
		   ]],
		   // 편집형 에디팅 종료 이벤트 처리
		   onEndEdit: function(index, row) {
			   // LSH. 지원내용 콤보박스 객체
			   var ed1 = $(this).datagrid('getEditor', {index: index,field: 'lwstSprtNm'});
			   // LSH. 지원내용 콤보박스 값,명칭맵핑
			   row['lwstSprtNm'] = $(ed1.target).combobox('getText');
			   row['lwstSprtCd'] = $(ed1.target).combobox('getValue');
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

   // FORM 검증
   function doReceiptValidate() {
	   var incdntNm = $('#incdntNm').val();
	   var incdntNo = $("#incdntNo").val();

		var inval_Arr = false;

	   // LSH. 빈값인지 체크 (null or undefined or "")
	   if($.commUtil.empty(incdntNm)){
		   inval_Arr[0] = false;
		   alert('사건 명을 확인해주세요.');
		   return false;
		} else {
		   inval_Arr[0] = true;
		}

	   // LSH. 빈값인지 체크 (null or undefined or "")
	   if($.commUtil.empty(incdntNo)){
		   inval_Arr[1] = false;
		   alert('사건 번호를 선택해주세요.');
		   return false;
		} else {
		   inval_Arr[1] = true;
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

   //--------------------------------------------------------//
   // 소송지원 접수하기
   //--------------------------------------------------------//
  function doOpenReceipt() {
   let rows = P_GRID.datagrid('getSelections');
	   if (rows.length == 0) {
		   $.commMsg.alert('진행현황에서 저장할 항목을 하나 이상 선택하세요.');
		   return false;
	   }

	   // FORM 검증결과가 실패이면
	   if (doReceiptValidate() === false) {
		   return false;
	   }
	   console.log(P_RFORM.serializeObject());
	  $.commMsg.confirm("취약계층 소송진행 현황을 저장하시겠습니까?", function() {
	   // 등록폼을 AJAX로 저장처리
	   P_RFORM.ajaxForm({
		   url: getUrl('/adm/support/updateLwstIncdnt.do'),
		   // 오류시 처리로직
		   error: $.ajaxUtil.error,
		   // 저장후 처리로직
		   success: function(ret) {
		  $.commMsg.success(ret, '성공적으로 저장되었습니다.', function() {
				  goUrl(getUrl('/adm/support/listLwstPrgre.do'));
		  });
		   }
	   }).submit();
   });
   return false;
}

function doCancel() {
	 let rows = P_GRID.datagrid('getSelections');
	 if (rows.length == 0) {
		$.commMsg.alert('신청현황에서 취소할 항목을 하나 이상 선택하세요.');
		return false;
	 }
	 $.commMsg.confirm("취약계층 소송지원 신청을 취소하시겠습니까?", function() {

	   // 이력관리팝업 (adm_popup.js)
	   popup.openMngHst({
		 noAlert: true,
		 element:'#appPopupCancel',
		 openArgs: {
		   title: '취소이력',
		   params: JSON.stringify({
			  mode:    MODE.INSERT,
			  dtySeCd: P_DTY_CD,
			  hstSeCd: 'H2',
			  aplyNo:  P_SELECT['aplyNo']
		   })
		 },
		 saveCallback: function() {
		   // 등록폼을 AJAX로 저장처리
		   P_RFORM.ajaxForm({
			  url: getUrl('/adm/support/cancelLwstIncdnt.do'),
			  // 오류시 처리로직
			  error: $.ajaxUtil.error,
			  // 저장후 처리로직
			  success: function(ret) {
			  $.commMsg.success(ret, '소송지원이 취소되었습니다.', function() {
					goUrl(getUrl('/adm/support/listLwstRtrcn.do'));
			  });
			  }
		   }).submit();
		 }
	   });
	 });
	 return false;
  }

   // 진행현황 엑셀다운로드
   //--------------------------------------------------------//
   function doExcel() {
	   $.formUtil.submitForm(
		   getUrl('/adm/support/downLwstPrgreExcel.do'),
		   {formId : "searchForm"}
	   );
	   return false;
   }


   // 검색버튼 클릭시 이벤트 처리
   $('#btnSearch').bind('click', doSearch);

	// 이력등록버튼 클릭시 이벤트처리
   $('#btnHistory').bind('click', doOpenHistory);

   // 진행저장버튼 클릭시 이벤트처리
   $('#btnReceipt').bind('click', doOpenReceipt);

   // 소송지원버튼 클릭시 이벤트
   $('#btnSupportLwst').bind('click', doSupportLwst);

   // 신청취소버튼 클릭시 이벤트처리
   $('#btnCancel').bind('click', doCancel);

   //엑셀 다운로드
   $('#btnExcel' ).bind('click', doExcel);


   // 검색 실행
   doSearch();

   bindEnter($('#srchAplcntNm'), $('#btnSearch'));

	// 초기실행여부
	let P_DTL_INIT = true;

   	//개인정보 탭
   	$(".tabWrap li").on("click", function() {
	   var idx = $(this).index()
	   $(this).parent().find("li").removeClass("on");
	   $(this).addClass("on");
	   $('.tabInnerFrom').removeClass("on");
	   $('.tabInnerFrom').eq(idx).addClass("on");

	   	// 소송지원정보탭인 경우
       	if (idx == 3) {
			// 초기실행이면 그리드 초기화
			if (P_DTL_INIT) {
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
			            {field:'incdntMngNo'    ,hidden: true,width:100,halign:'center',align:'center',title:'사건관리번호'},
			            {field:'dudtYmd'   ,width:200,halign:'center',align:'center',title:'기일 일자'},
			            {field:'dudtTm'   ,width:100,halign:'center',align:'center',title:'기일 시간'},
			            {field:'dudtSeNm',width:100,halign:'center',align:'center',title:'기일구분'},
						{field:'dudtPlce',width:100,halign:'center',align:'center',title:'기일장소'},
						{field:'rsltCn',width:100,halign:'center',align:'center',title:'결과내용'}
			        ]]
			    });
				// 초기실행여부 마킹 (1번만 실행되도록)
				P_DTL_INIT = false;
			}
			// 피해내용 감춤
			$('#dmgInfo').hide();
			// 이력관리 감춤
			$('#historyInfo').hide();
		}
		// 그외의 탭이면
		else {
			// 피해내용 표시
			$('#dmgInfo').show();
			// 이력관리 표시
			$('#historyInfo').show();
		}
   	});
	$(".tabWrap li").each(function() {
		$(this).trigger('click');
	});
	// 첫번째 탭 클릭 이벤트 발생
	$(".tabWrap li").eq(0).trigger('click');

});