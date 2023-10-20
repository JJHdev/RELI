/**
******************************************************************************************
*** 파일명 : main.js
*** 설명글 : 종합현황 메인 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.07.15    LSH
*** 1.0         2021.11.01    LSH   업무개발
*** 2.0         2022.12.12    LSH   상세정보를 탭형태로 확장
******************************************************************************************
**/
// 탭 모듈 배열
let P_TABS   = [];
// 현재 선택한 탭 INDEX
let P_INDEX  = 0;
// 현재 선택한 행 데이터
let P_SELECT = false;
// 현재 디자인적용 번호 (1 or 2)
let P_DESIGN = 2; 
// 현재 팝업 섹션
let P_POPUP_SECTIONS = {};
//============================================================================//
// 검색/목록영역 기능정의
//----------------------------------------------------------------------------//
const C_LIST = {

	FORM : false,
	GRID : false,
	// 진행상태영역
	STATUS: false,
	// 진행상태 체크박스
	PRGRE:  false,
	// 피해자정보 카드
	CARD :  false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		this.PRGRE  = false;
		this.STATUS = $('#appReliefStatus');
		this.FORM   = $('#searchForm');
		
		this.CARD   = $('#appCard');
	    this.GRID   = $('#appGrid').datagrid({
			fit: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',    
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // 그리드 페이징처리 여부
	        pagination:true,
	        // 그리드 행번호 표시여부
	        rownumbers:true,
	        // 한 페이지 출력수
	        pageSize: 30,
	        // 칼럼정의
			//식별ID , 피해자명 , 신청자명 , 신청구분 , 접수일자 , 진행현황 , 조사결과 , 피해지역 , 급여종류
	        columns: [[
	            {field:'aplyNo'       ,hidden: true, width:100,halign:'center',align:'center',title:'신청번호'},
	            {field:'idntfcId'     ,width:90 ,halign:'center',align:'center',title:'식별ID'},
				{field:'viewBtn'      ,width:90 ,halign:'center',align:'center',title:'개인기록카드',
				 	formatter: function(v,r) {
						if ($.commUtil.empty(r['idntfcId']))
							return '<a href="javascript:void(0);" class="app-docbtn"></a>';
						return '<a href="javascript:void(0);" class="app-docbtn-on app-btn-popup" title="개인기록카드" data-idntfcid="'+r['idntfcId']+'" data-aplyno="'+r['aplyNo']+'"></a>';
					}
				},
	            {field:'sufrerNmMask' ,width:80,halign:'center',align:'center',title:'피해자명'},
	            {field:'aplcntNmMask' ,width:80,halign:'center',align:'center',title:'신청자명'},
	            {field:'aplySeNm'     ,width:80,halign:'center',align:'center',title:'신청구분'},
	            {field:'rcptYmd'      ,width:100,halign:'center',align:'center',title:'접수일자'},
	            {field:'prgreStusNm'  ,width:80,halign:'center',align:'center',title:'진행현황'},
	            {field:'exmnDltncRsltNm'  ,width:100,halign:'center',align:'center',title:'예비조사결과'},
	            {field:'dltncRsltNm'  ,width:80,halign:'center',align:'center',title:'본조사결과'},
	            {field:'bizAreaNm'    ,width:90,halign:'center',align:'center',title:'피해지역'},
	            {field:'aplyKndNm'    ,width:150,halign:'center',align:'center',title:'급여종류'},
	            {field:'aplyYmd'      ,width:100,hidden: true, halign:'center',align:'center',title:'신청일자'},
	            {field:'rcognAmt'     ,width:100,halign:'center',align: 'right',title:'지급결정액', formatter: $.commFormat.number},
	            {field:'giveAmt'      ,width:100,halign:'center',align: 'right',title:'지급액'    , formatter: $.commFormat.number},
	        ]],
	        // 행선택시 상세조회
	        onSelect: this.doLoad,
			// 데이터 로딩후 실행함수
			onLoadSuccess: function() {
                let p = $(this).datagrid('getPanel');

                p.find('.app-btn-popup').each(function() {
					$(this).bind('click', C_LIST.doGridDetailView);
                });
			}
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
		// 2022.03.02 [ntarget] 상태처리 변경
		P_PRGRE = $('#appProgress').appSelectBox({
			label:   '진행현황',
			form:    'checkbox',
			name:    'prgreStusList', 
			params:  {upCdId: CODE.PROGRESS_LAST},
			// 2021.12.27 ADD (comm_const.js 참고)
			filter:  CODE_FILTER.RELIEF_PRGRE_LAST_STUS
		});
			
		$('#appBizArea').appSelectBox({
			label:   '지역구분',
			form:    'checkbox',
			name:    'bizAreaList', 
			url:     getUrl('/com/cmm/getComboBizMng.do'),
			params:  {upCdId: CODE.PROGRESS},
			// 데이터 로딩후 실행함수
			callback: function(cmp) {
				cmp.addOption({
					code: 'ETC',
					text: '기타'
				});
			}
		});
		// 2022.03.02 [ntarget] 예비조사 추가
		$('#appDltncRsltExmn').appSelectBox({
			label:   '예비조사결과',
			form:    'checkbox',
			name:    'dltncRsltExmnList', 
			params:  {upCdId: CODE.RESULT}
		});
		$('#appDltncRslt').appSelectBox({
			label:   '본조사결과',
			form:    'checkbox',
			name:    'dltncRsltList', 
			params:  {upCdId: CODE.RESULT}
		});
		$('#srchGiveYn').appComboBox({
			type:    'static',
			rows:    STORE.GIVE_YN,
			init:    COMBO.INIT_ALL
		});
		
	    // 검색어 입력 엔터 이벤트 처리
	    bindEnter($('#srchIdntfcId'), $('#btnSearch'));
	    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
	    bindEnter($('#srchAplcntNm'), $('#btnSearch'));

	    // 검색버튼 클릭시 이벤트 처리
	    $('#btnSearch').bind('click', this.doSearch);
	    // 리셋버튼 클릭시 이벤트처리
    	$('#btnReset' ).bind('click', this.doReset);
	    // 엑셀다운로드버튼 클릭시 이벤트처리
	    $('#btnExcel' ).bind('click', this.doExcel);

		// 진행상태로드
		this.doLoadStatus();

		// 초기검색실행
		this.doSearch();
	},
	// 검색
    //-------------------------------//
	doSearch: function() {
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택된 항목 CLEAR
		C_LIST.GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = C_LIST.FORM.serializeObject();
        // 그리드 목록조회 URL
        C_LIST.GRID.datagrid('options')['url'] = getUrl('/adm/main/getListReliefTotal.do');
        // 검색폼 그리드 검색
        C_LIST.GRID.datagrid('load', obj);
        return false;
	},
	// 검색리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
        // 검색폼 입력데이터 초기화
        C_LIST.FORM.form('reset');
        // 검색처리
        C_LIST.doSearch();
        return false;
	},
	// 피해자정보 카드 생성
    //-------------------------------//
	doCardCreate: function(data) {
		let dom = $('<div></div>');
		dom.append('<b>피해자정보</b>');
		dom.append('<div class="app-blue">'+$.commUtil.nvl(data['idntfcId'     ])+'</div>');
		dom.append('<div class="app-blue">'+$.commUtil.nvl(data['sufrerNm'     ])+'</div>');
		dom.append('<div class="app-blue">'+$.commUtil.nvl(data['sufrerSxdstNm'])+'</div>');
		dom.append('<div class="app-blue">'+$.commUtil.nvl($.formatUtil.toDashDate(data['sufrerBrdt']))+'</div>');
		this.CARD.append($('<div class="app-view"></div>').append(dom));
		this.CARD.append('<div class="app-space10"></div>');
        return false;
	},
	// 종합현황 엑셀다운로드
    //-------------------------------//
	doExcel: function() {
        $.formUtil.submitForm(
            getUrl('/adm/main/downReliefTotalExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
	},
    // 종합현황 진행상태 조회
	// 2022.03.02 [ntarget] 완료 추가
    //--------------------------------------------------------//
    doLoadStatus: function() {
		let status = [
			{icon:getUrl('/images/main/i-mainTab-01.svg'),text:'신청접수'},
			{icon:getUrl('/images/main/i-mainTab-03.svg'),text:'예비조사'},
			{icon:getUrl('/images/main/i-mainTab-05.svg'),text:'본조사'},
			{icon:getUrl('/images/main/i-mainTab-06.svg'),text:'지급'},
			{icon:getUrl('/images/main/i-mainTab-04.svg'),text:'완료'}
		];
		
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/main/getListReliefStatus.do'), 
			{},
            function(result) {
                var list = result.Data;
                if (list) {
					$.each(list, function(i,item) {
						let li = $('<li></li>');
						li.data('code', item.stusCd);
						li.append('<i><img src="'+status[i].icon+'"/></i>');
						li.append('<p class="txt">'+status[i].text+'</p>');
						li.append('<p class="num">'+item.stusCnt+'명</p>')
						C_LIST.STATUS.append(li);
					});
					
					C_LIST.STATUS.find('li').bind('click', function() {
						let code = $(this).data('code');
						$(this).parent().find("li").removeClass("on");
						$(this).addClass("on");
						C_LIST.PRGRE.setValue(code);
					});
                }
            }
        );
    },
	// 종합현황 상세리셋
    //-------------------------------//
	doClear: function() {
		// 상세조회 데이터 제거
		P_SELECT = false;
		// 피해자정보 카드 제거
		this.CARD.html('');
		// 모든 탭모듈의 초기화
		$.each(P_TABS, function(i,t) {
			t.doReset();
		});
        return false;
	},

	// 종합현황 상세조회
    //-------------------------------//
	doLoad: function(index, row) {
		let params = {
			aplyNo      : row['aplyNo'      ],
			bizAreaCd   : row['bizAreaCd'   ],
			bizOder     : row['bizOder'     ],
			exmnOder    : row['exmnOder'    ],
			prptBizOder : row['prptBizOder' ],
			prptExmnOder: row['prptExmnOder'],
		};
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/main/getReliefTotal.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 상세조회 데이터 담기
					P_SELECT = data;
					C_LIST.doCardCreate(data);
					// 모든 탭모듈의 데이터 로드
					$.each(P_TABS, function(i,t) {
						t.doLoad( data );
					});
                }
            }
        );
	},

	// 그리드에서 개인기록카드 버튼클릭시 팝업 오픈
    //-------------------------------//
	doGridDetailView: function() {
        let idntfcId = $(this).data('idntfcid');
        let aplyNo   = $(this).data('aplyno');
		let title    = '개인별 상세 기록 카드 ('+idntfcId+')';
		let url      = getUrl('/adm/relief/modalReliefView.do')
		             + '?idntfcId='+idntfcId
		             + '&aplyNo='+aplyNo;

		$.commModal.loadView(title, url, {sizeType:'large', width: '1200'});
        return false;
	},
};

//============================================================================//
// [1] 신청정보 기능정의
//----------------------------------------------------------------------------//
const C_APLY = {
	AREA : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.INIT = true;
		// 신청정보 영역정의 (comm_adm.js 참고)
		this.AREA = $('#appRelief').appReliefMain({form:'#selectForm'});
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 신청정보 초기화
		this.AREA.resetData();
		// 상세조회 데이터 리셋
		this.DATA = false;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 데이터 로드
			this.AREA.loadData(data);
		}
	},
};

//============================================================================//
// [2] 제출서류 기능정의
//----------------------------------------------------------------------------//
const C_FILE = {
	FILE : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.INIT = true;
		// 신청파일목록영역 (comm_component.js 참고)
		this.FILE = $('#appAplyFileList').appAplyFile({
			mode:   MODE.SPLEMNT,
			system: SYSTEM.ADMIN['code']
		});
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 서류목록 초기화
		this.FILE.resetList();
		// 상세조회 데이터 리셋
		this.DATA = false;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 데이터 로드
	    	// 첨부파일목록 데이터로드
	    	this.FILE.loadList({
				dtySeCd:   CODE.PAPE_DTY_CD.RELIEF,
				dcmtNo:    data['aplyNo'],
				dtlDcmtNo: '0'
			});
		}
	},
};

//============================================================================//
// [3] 의료비내역 기능정의
//----------------------------------------------------------------------------//
const C_MCP = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#mcpForm');
		this.GRID = $('#mcpGrid').datagrid({
			// 패널 영역 맞춤
			fit: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // 그리드 페이징처리 여부
	        pagination:true,
	        // 그리드 행번호 표시여부
	        rownumbers:true,
	        // 한 페이지 출력수
	        pageSize: 30,
	        // 칼럼정의
	        columns: [[
	            {field:'rcperPstgYmd',width:100,halign:'center',align:'center',title:'진료일'},
	            {field:'rcperInstNm' ,width:150,halign:'center',align:'center',title:'진료기관명'},
	            {field:'sckwndCd'    ,width:100,halign:'center',align:'center',title:'질병코드'},
	            {field:'sckwndNm'    ,width:200,halign:'center',align:'center',title:'질병명'},
				{field:'selfAlotm'   ,width:100,halign:'center',align: 'right',title:'본인부담금', formatter:$.commFormat.number}
	        ]]
		});
	    //========================================================//
	    // FORM ELEMENTS 정의
	    //--------------------------------------------------------//
	    // 검색버튼 클릭시 이벤트 처리
		$('#btnMcpSearch').bind('click', this.doSearch);
	    // 리셋버튼 클릭시 이벤트처리
		$('#btnMcpReset').bind('click', this.doClear);
	    // 엑셀다운로드버튼 클릭시 이벤트처리
		$('#btnMcpExcel').bind('click', this.doExcel);
	    // 진료일자 입력박스 하이픈(-) 자동삽입 이벤트
		bindDateHyphen( $('#srchRcperStdt').datebox('textbox') );
		bindDateHyphen( $('#srchRcperEndt').datebox('textbox') );
	    // 검색어 입력 엔터 이벤트 처리
	    bindEnter($('#srchSckwndText'), $('#btnMcpSearch'));
		this.INIT = true;
	},
	// 의료비 합계 조회
    //-------------------------------//
	doSummary: function() {
		
		$('#s_mcpTotAmt' ).html('');
		$('#s_mcpSrchAmt').html('');
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/exmn/getMcpDtlsSummary.do'), 
			C_MCP.FORM.serializeObject(),
            function(result) {
                if (result.Data) {
					$('#s_mcpTotAmt' ).html($.formatUtil.toKorMoney(result.Data['mcpTotAmt' ]));
					$('#s_mcpSrchAmt').html($.formatUtil.toKorMoney(result.Data['mcpSrchAmt']));
                }
            }
        );
	},
	// 검색
    //-------------------------------//
	doSearch: function() {
		// 선택된 항목 CLEAR
		C_MCP.GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = C_MCP.FORM.serializeObject();

		if ($.commUtil.empty(obj['bizAreaCd']) ||
			$.commUtil.empty(obj['bizOder'  ]) ||
			$.commUtil.empty(obj['exmnOder' ]) ||
			$.commUtil.empty(obj['aplyNo'   ]))
			return false;

        // 그리드 목록조회 URL
        C_MCP.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListMcpDtls.do');
        // 검색폼 그리드 검색
        C_MCP.GRID.datagrid('load', obj);
		// 의료비 합계 조회
		C_MCP.doSummary();
        return false;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
			// 인정상태만 조회
			this.FORM.find('[name="rcognStusCd"]').val('01');
	        // 목록조회
			this.doSearch();
		}
	},
	// 클리어
    //-------------------------------//
	doClear: function() {
		// 그리드 데이터 리셋
		C_MCP.GRID.datagrid('loadData', {"total":0,"rows":[]});
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 그리드 데이터 리셋
		C_MCP.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 상세조회 데이터 리셋
		C_MCP.DATA = false;
	},
	// TODO. 엑셀 다운로드
    //-------------------------------//
	doExcel: function() {
		if (C_MCP.DATA == false)
			return false;
		let obj = C_MCP.FORM.serializeObject();
		if ($.commUtil.empty(obj['bizAreaCd']) ||
			$.commUtil.empty(obj['bizOder'  ]) ||
			$.commUtil.empty(obj['exmnOder' ]) ||
			$.commUtil.empty(obj['aplyNo'   ]))
			return false;
		
        $.formUtil.submitForm(
			getUrl('/adm/exmn/downMcpDtlsExcel.do'),
			{params: obj}
		);
        return false;
	},
};

//============================================================================//
// [4] 요양생활수당 기능정의
//----------------------------------------------------------------------------//
const C_RCP = {
	FORM : false,
	GRID : false,
	INIT : false,
	DATA : false,
	AREA : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#rcpForm');
		this.GRID = $('#rcpGrid').datagrid({
			// 패널 영역 맞춤
			fit: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',    
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // 칼럼정의
	        columns: [[
	            {field:'giveYr' ,width:200,halign:'center',align:'center',title:'지급연도',formatter: $.commFormat.year},
	            {field:'giveMm' ,width:200,halign:'center',align:'center',title:'지급월'  ,formatter: $.commFormat.month},
	            {field:'giveYmd',width:200,halign:'center',align:'center',title:'지급일자'}
	        ]]
		});
		
		// 폼형태의 요양생활수당 정보
		let infoArgs = {
			// 제목
			title: '요양급여 지급정보',
			// 폼객체
			form: '#rcpForm',
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: 'rcp_',
		};
		if (P_DESIGN == 1) {
			$.extend(infoArgs, {
				// 감싸는 레이어 사용여부
				wrap: true,
				// 감싸는 레이어 KEY
				wrapKey: "appRcpLayout",
				// 스타일시트
				cls: "formLayout tabInnerFrom box",
				// 칼럼구성
				columns: [
					{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'lastDmgeGrdCd'     , label: '피해등급', formatter: $.formatUtil.toGrdNm},
					{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'rcperLvlhAllwncAmt', label: '지급총액', formatter: $.formatUtil.toTotalMoney},
					{groupCls:'col-md-4' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'giveBgngYm'        , label: '지급시작', formatter: $.formatUtil.toKorMonth},
					{groupCls:'col-md-4' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'giveDcsnYm'        , label: '지급결정', formatter: $.formatUtil.toKorMonth},
					{groupCls:'col-md-4' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'giveEndYm'         , label: '지급종료', formatter: $.formatUtil.toKorMonth}
				]
			}, true);
			this.AREA = $('#appRcpInfo').appFormLayout(infoArgs);
		}
		else {
			$.extend(infoArgs, {
				// 열단위 너비목록,
				colgroup: ['14%','20%','13%','20%','13%','20%'],
				// 행단위 칼럼목록
				rows: [
					[{name: 'lastDmgeGrdCd'      , colspan: 5, head: {label: '피해등급'}, formatter: $.formatUtil.toGrdNm}],
					[{name: 'rcperLvlhAllwncAmt' , colspan: 5, head: {label: '지급총액'}, formatter: $.formatUtil.toTotalMoney}],
					[{name: 'giveBgngYm'         , head: {label: '지급시작'}, formatter: $.formatUtil.toKorMonth},
					 {name: 'giveDcsnYm'         , head: {label: '지급결정'}, formatter: $.formatUtil.toKorMonth},
					 {name: 'giveEndYm'          , head: {label: '지급종료'}, formatter: $.formatUtil.toKorMonth}]
				]
			}, true);
			this.AREA = $('#appRcpInfo').appTableFormLayout(infoArgs);
		}
		this.INIT = true;
	},
	// 검색
    //-------------------------------//
	doSearch: function() {
		// 선택된 항목 CLEAR
		C_RCP.GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = C_RCP.FORM.serializeObject();
		if ($.commUtil.empty(obj['bizAreaCd']) ||
			$.commUtil.empty(obj['bizOder'  ]) ||
			$.commUtil.empty(obj['exmnOder' ]) ||
			$.commUtil.empty(obj['aplyNo'   ]))
			return false;
        // 그리드 목록조회 URL
        C_RCP.GRID.datagrid('options')['url'] = getUrl('/adm/exmn/getListRcperLvlhDtls.do');
        // 검색폼 그리드 검색
        C_RCP.GRID.datagrid('load', obj);
        return false;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);
			this.AREA.loadData(data);
	        // 목록조회
			this.doSearch();
		}
	},
	// 클리어
    //-------------------------------//
	doClear: function() {
		// 그리드 데이터 리셋
		C_RCP.GRID.datagrid('loadData', {"total":0,"rows":[]});
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 그리드 데이터 리셋
		C_RCP.GRID.datagrid('loadData', {"total":0,"rows":[]});
		// 상세조회 폼데이터 초기화
		C_RCP.FORM.form('reset');
		// 상세조회 데이터 리셋
		C_RCP.DATA = false;
	},
};

//============================================================================//
// [5] 피해조사 기능정의
//----------------------------------------------------------------------------//
const C_EXMN = {
	FORM : false,
	INIT : false,
	DATA : false,
	MNSY : false,
	PRPT : false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.FORM = $('#exmnForm');

		// 예비조사 폼설정정보
		let prptArgs = {
			// 제목
			title: '예비조사',
			// 폼객체
			form: '#exmnForm',
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: 'prpt_',
		};
		// 본조사 폼설정정보
		let mnsyArgs = {
			// 제목
			title: '본조사',
			// 폼객체
			form: '#exmnForm',
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: 'mnsy_',
		};
		if (P_DESIGN == 1) {
			$.extend(prptArgs, {
				// 감싸는 레이어 사용여부
				wrap: true,
				// 감싸는 레이어 KEY
				wrapKey: "appPrptLayout",
				// 칼럼구성
				columns: [
					{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'bizAreaNm'       , label: '피해지역'},
					{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'expsrWhlCnt'     , label: '노출기간', formatter: $.formatUtil.toYearCnt},
					{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'       , label: '구제급여'},
					{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'rcognDissHoldNm' , label: '인정질환<br>보유여부', formatter: $.formatUtil.toRcognNm},
					{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'exmnDltncOpmtYmd', label: '심의회일시'},
					{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'exmnDltncRsltNm' , label: '심의회결과'},
					{groupCls:'col-md-12', labelCls: 'col-md-12  app-pb10',inputCls: 'col-md-12', name: 'exmnDltncRsltResn', label: '심의회 의견 (비고 및 부적합 사유)'}
				]
			}, true);
			$.extend(mnsyArgs, {
				// 감싸는 레이어 사용여부
				wrap: true,
				// 감싸는 레이어 KEY
				wrapKey: "appMnsyLayout",
				// 칼럼구성
				columns: [
					{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'giveDcsnYmd'    , label: '심의회일시'},
					{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'dltncRsltNm'    , label: '심의회결과'},
					{groupCls:'col-md-12', labelCls: 'col-md-12  app-pb10',inputCls: 'col-md-12', name: 'dltncRsltResn'  , label: '심의회 의견 (비고 및 부적합 사유)'}
				]
			}, true);
			this.PRPT = $('#appPrptExmn').appFormLayout(prptArgs);
			this.MNSY = $('#appMnsvy'   ).appFormLayout(mnsyArgs);
		}
		else {
			$.extend(prptArgs, {
				// 열단위 너비목록,
				colgroup: ['15%','35%','15%','35%'],
				// 행단위 칼럼목록
				rows: [
					[{name: 'bizAreaNm', head: {label: '피해지역'}},{name: 'expsrWhlCnt', head: {label: '노출기간'}, formatter: $.formatUtil.toYearCnt}],
					[{name: 'aplyKndNm', head: {label: '구제급여'}}, {name: 'rcognDissHoldYn', head: {label: '인정질환<br>보유여부'}, formatter: $.formatUtil.toRcognNm}],
					[{name: 'exmnDltncOpmtYmd', colspan: 3, head: {label: '심의회일시'}}],
					[{name: 'exmnDltncRsltNm' , colspan: 3, head: {label: '심의회결과'}}],
					[{head: {label: '심의회 의견 (비고 및 부적합 사유)', colspan: 4}}],
					[{name: 'exmnDltncRsltResn', colspan: 4}]
				]
			}, true);
			$.extend(mnsyArgs, {
				// 열단위 너비목록,
				colgroup: ['15%','35%','15%','35%'],
				// 행단위 칼럼목록
				rows: [
					[{name: 'giveDcsnYmd' , colspan: 3, head: {label: '심의회일시'}}],
					[{name: 'dltncRsltNm' , colspan: 3, head: {label: '심의회결과'}}],
					[{head: {label: '심의회 의견 (비고 및 부적합 사유)', colspan: 4}}],
					[{name: 'dltncRsltResn', colspan: 4}]
				]
			}, true);
			this.PRPT = $('#appPrptExmn').appTableFormLayout(prptArgs);
			this.MNSY = $('#appMnsvy'   ).appTableFormLayout(mnsyArgs);
		}
		this.INIT  = true;
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		this.doReset();
		if (data) {
			// 상세조회 데이터 리셋
			this.DATA = data;
			// 폼데이터 로드
			this.FORM.form('load', data);

			// 예비조사 로드
			if (data['prptBizOder']) {
				this.PRPT.loadTitle('예비조사 '+data['prptBizOderNm']);
				this.PRPT.loadData(data);
			}
			// 본조사 로드
			if (data['bizOder']) {
				console.log(data);
				this.MNSY.loadTitle('본조사 '+data['bizOderNm']);
				this.MNSY.loadData(data);
			}
		}
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 상세폼 초기화
		this.FORM.form('reset');
		// 상세화면 초기화
		this.PRPT.resetTitle();
		this.PRPT.resetData();
		this.MNSY.resetTitle();
		this.MNSY.resetData();
	}
};

$(function() {

	P_TABS   = [C_APLY, C_FILE, C_MCP, C_RCP, C_EXMN];
	P_INDEX  = 0;
	P_SELECT = false;

	// 상세내역 탭클릭 이벤트
	$.eventUtil.tabClick('.boxWrap', 0, function(elm, index) {
		P_INDEX = index;
		// 탭모듈별 초기화
		P_TABS[index].doInit();
	}, true);

	// 목록 초기화
	C_LIST.doInit();
});
