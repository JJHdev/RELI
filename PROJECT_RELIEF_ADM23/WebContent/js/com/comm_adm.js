/**
******************************************************************************************
*** 파일명    : comm_adm.js
*** 설명      : 관리자시스템 공통스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-07-15              shlee
******************************************************************************************
**/
$(document).ready(function() {
	// 사이드바 로딩
	loadSideAdmBar(
		'#sidebar', 
		getUrl('/com/cmm/listMenu.do'),{
			sysCd: SYSTEM.ADMIN['code']
		}
	);
	// 상단경로 표시
	showBreadcrumbs('#page-breadcrumbs');
	
	// TEXTAREA 자동높이조절
	$.eventUtil.autoTextarea();
	
	// 2022.12.05 검색영역 폴딩 기능 (DESIGN)
	$(".topBox .viewBtn").on("click", function(){
		$(this).parent().toggleClass("off");
		/*
		if ($('#searchTitle').length > 0 &&
		    $('#searchTitle').is(':visible'))
			$('#searchTitle').hide();
		else if ($('#searchTitle').length == 0)
			$(this).parent().append('<div id="searchTitle" class="boxTit type1"><h3>검색조건</h3></div>');
		else if (!$('#searchTitle').is(':visible'))
			$('#searchTitle').show();
		*/
	});
	// 2022.12.13 메뉴영역 폴딩 기능 (DESIGN)
	$(".sidebtn").on("click", function(){
		$(this).toggleClass("on");
		$(".sidenav").toggleClass("on");
		$(".page"   ).toggleClass("on");
		// 2023.01.11 메뉴 폴딩시 그리드 사이즈 재조정
		$(".sidenav").fadeToggle( function() {
			$('.container-fluid').trigger('resize');
		});
	});
});

//=======================================================================//
//사이드바 메뉴 AJAX 로딩 함수
//2021.07.08 LSH  최초작성
//2021.11.04 LSH  메뉴디자인적용
//-----------------------------------------------------------------------//
var loadSideAdmBar = function(divKey, url, params) {
	
	var div = $(divKey);
	if (!div)
		return;
	
	var first = div.data('first');

	var data  = $.ajaxUtil.ajaxDefault(url, params);
	if (data) {
		tree = buildTree(data, 0);
		div.append(tree);
	}
	
	$("#sidebar>ul>li ul").slideUp(0)
	$("#sidebar>ul>li label").on("click", function(){
		$(this).next("ul").slideToggle(300)
		$(this).toggleClass("on");
	})
	div.find('label').each(function() {
		let menu = $(this).data('menu');
		if (menu == first) {
			$(this).trigger('click');
		}
		// 개발샘플 메뉴에서 제외
		//if (menu == 'MU_ADM_DEV') {
		//	$(this).parent().hide();
		//	return true;
		//}
	});
};

var buildTree = function(list, level) {
	var ul = $('<ul></ul>');
	ul.addClass("nav nav-list");
	if (level == 0)
		ul.addClass("nav-menu-list-style");
	else
		ul.addClass("tree");
							
	$.each(list, function(i,o) {
		var ac = $('<a></a>');
		ac.append(o['menuNm']);
		ac.prop('href', getUrl(o['trgtUrl']));
		var li = $('<li></li>');
		// 2023.01.11 1레벨인 경우에만 label 처리
		// 레벨이 1보다 크고 하위메뉴가 없는 경우
		if (o['level'] > 1 && !o.children) {
			li.append(ac);
		}
		else {
			li.append('<label data-menu="'+o['menuId']+'"></label>');
			// 드롭다운 표시
			if (o.children) {
				li.find('label').addClass("tree-toggle nav-header");
				li.find('label').append(o['menuNm']);
				li.find('label').append('<span class="menu-collapsible-icon fa fa-caret-down"></span>');
				li.append(buildTree(o.children, o['level']));
			}
			else {
				li.find('label').append(ac);
				li.find('a').addClass("nav-header");
			}
		}
		ul.append(li);
	});
	return ul;
}

//=======================================================================//
//상단 우측 페이지 경로 표시
//2021.11.04 LSH  최초작성
//-----------------------------------------------------------------------//
var showBreadcrumbs = function(ulKey) {
	
	var ul = $(ulKey);
	if (!ul)
		return;

	//var menu = ul.data('menu');
	var path = ul.data('path');
	var home = '<li><img src="'+getUrl('/images/common/i-home.svg')+'" alt="" /></li>';
	
	ul.append(home);

	if (path == null)
		return;

	$.each(path.split(','), function(i,m) {
		ul.append('<li>'+m+'</li>');
	});
};

//=======================================================================//
//네비게이션바 메뉴 AJAX 로딩 함수 (관리자화면)
//2021.07.19 LSH 대메뉴만 표시
//-----------------------------------------------------------------------//
var loadTopAdmBar = function(ulKey, url, params) {
	var ul = $(ulKey);
	if (!ul)
		return;
	var data = $.ajaxUtil.ajaxDefault(url, params);
	if (data) {
		$.each(data, function(i,o) {
			var ac = $('<a href="'+getUrl(o['trgtUrl'])+'">'+o['menuNm']+'</a>');
			ul.append( $('<li></li>').append(ac) );
		});
	}
};

//=======================================================================//
// 2021.11.17 LSH 탭형 서브메뉴 표시
//-----------------------------------------------------------------------//
var createTabMenu = function(tabElm, tabList) {

	if (!tabElm)
		return;
	let curl = $(location).attr("pathname");
	tabElm.append('<div class="tabWrap type1"></div>');
	tabElm.append('<div class="app-space10"></div>');
	tabElm.find('.tabWrap').append('<ul class="li-2 box"></ul>');
	
	$.each(tabList, function(i,t) {
		let li = $('<li><a></a></li>');
		li.find('a').append(t.title);
		li.find('a').prop('href', t.url);
		if (t.url == curl)
			li.addClass("on");
		tabElm.find('ul').append(li);
	});
};

//=======================================================================//
//2021.10.20 LSH 개인정보 조회영역 (관리자화면공통)
//-----------------------------------------------------------------------//
$.fn.appPersonal = function ( args ) {
	
	let options = $.extend({
		// 스타일시트
		cls: "personnelWrap",
		
		// 폼명칭
		form: "personalForm",
		
		// 조회 URL
		url: getUrl('/adm/relief/getRelief.do'),

		// ID PREFIX
		prefix: 's_',
		
		// 히든칼럼
		hiddens: ['aplyNo','idntfcId','aplySeCd','prgreStusCd'],

		// 기본그룹CLS
		groupCls: "formGroup",
		
		// 레이블CLS
		labelCls: "label",
		
		// 입력박스CLS
		inputCls: "inputWrap",
		
		// 진행상태영역 대체함수
		formatStatus: false,
		
		// 2022.12.05 수정버튼
		//button: {id: 'btnUpdate', text: '수정'},
		button: false,
		// 탭구성
		tabs: [{
			code:    CODE.HST_CD.SUFRER,
			title:   '피해자정보',
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyNo'       , label: '신청번호'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'idntfcId'     , label: '식 별 ID'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplySeNm'     , label: '신청구분'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'bizAreaNm'    , label: '지　　역'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyYmd'      , label: '신청일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerBrdt'   , label: '생년월일', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerSxdstNm', label: '성　　별'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerAge'    , label: '현재연령'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerTelno'  , label: '유선전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerMbtelNo', label: '휴대전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerEmlAddr', label: '전자우편주소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name:['sufrerZip' ,'sufrerAddr','sufrerDaddr'], label: '주　　소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name:['bankNm'    ,'dpstrNm'   ,'actno'      ], label: '계좌번호(은행)'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dthYn'        , label: '생존여부', formatter: $.formatUtil.toDthNm},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dthYmd'       , label: '사망일자'}
			]
		},{
			code:    CODE.HST_CD.APLCNT,
			title:   '신청인정보',
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntBrdt'   , label: '생년월일'    , formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntTelno'  , label: '유선전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntMbtelNo', label: '휴대전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerRelNm'  , label: '피해자와의관계'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'aplcntEmlAddr', label: '전자우편주소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name:['aplcntZip','aplcntAddr','aplcntDaddr'], label: '주　　소'}
			]
		},{
			code:    CODE.HST_CD.DAMAGE,
			title:   '피해내용',
			columns: [
				{groupCls:'col-md-12' , labelCls: 'col-md-2', inputCls: 'col-md-10' , name: 'dmgeOcrnPlce'  , label: '피해발생의 일시∙장소'},
				{groupCls:'col-md-12' , labelCls: 'col-md-2', inputCls: 'col-md-10' , name: 'dmgeCauseCn'   , label: '피해의 원인'},
				{groupCls:'col-md-12' , labelCls: 'col-md-2', inputCls: 'col-md-10' , name: 'dmgeOcrnProcCn', label: '피해발생의 경과'},
				{groupCls:'col-md-12' , labelCls: 'col-md-2', inputCls: 'col-md-10' , name: 'dmgeCn'        , label: '피해의 내용 및 금액'},
				{groupCls:'col-md-12' , labelCls: 'col-md-2', inputCls: 'col-md-10' , name: 'aplyResn'      , label: '신청 사유'}
			]
		}]
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	//2022.12.05 현재선택탭의 코드
	let thisCode = CODE.HST_CD.SUFRER;
	
	//DOM 생성
	this.create = function() {
		//ELM 초기화
		thisElm.html('');
		thisElm.addClass(options.cls);
		thisElm.append(this.createForm());
		
		thisElm.find('.tabWrap > ul > li').on('click', function() {
			let idx = $(this).index();
			thisElm.find('.tabWrap > ul > li').each(function() {
				$(this).removeClass('on');
			});
			thisElm.find('.tabInner > ul > li').each(function() {
				$(this).removeClass('on');
			});
			$(this).addClass('on');
			thisElm.find('.tabInner > ul > li').eq(idx).addClass('on');
			thisCode = $(this).data('code');
			return false;
		});
	};
	
	this.createForm = function() {
		
		let form = $('<form method="post" onsubmit="return false;"></form>');
		form.prop('id'  , options.form);
		form.prop('name', options.form);
		
		$.each(options.hiddens, function(i,h) {
			let input = $('<input type="hidden" value=""/>');
			input.prop('id'  , h);
			input.prop('name', h);
			form.append(input);
		});
		form.append(thisCmp.createTabTitle());
		form.append(thisCmp.createTabLayout());
		// 2022.12.05 버튼 추가
		if (options.button)
			form.append(thisCmp.createButton());
		return form;
	};

	// 2022.12.05 버튼 추가
	this.createButton = function() {
		let dom = $('<div class="app-p5 align_r"></div>');
		dom.append('<a href="javascript:void(0);" id="'+options.button.id+'" class="btn btn-default btn-md">'+options.button.text+'</a>');
		return dom;
	};

	// 2022.12.05 현재탭의 코드 반환
	this.getTabCode = function() {
		return thisCode;
	};
	
	this.createTabTitle = function() {
		let dom = $('<div class="tabWrap type2"></div>');
		dom.append('<ul></ul>');
		let status = $('<p class="state"></p>');
		// 진행상태 대체함수가 있을 경우
		if (options.formatStatus) {
			status = options.formatStatus();
		}
		else {
			status.append('진행상태 : ');
			status.append('<span style="color:red;" id="s_stusNm"></span>');
		}
		dom.append(status);
		
		$.each(options.tabs, function(i,t) {
			let li = $('<li data-code="'+t.code+'"></li>');
			if (i == 0)
				li.addClass('on');
			li.append('<a href="javascript:void(0);">'+t.title+'</a>');
			dom.find('ul').append(li);
		});
		return dom;
	};
	
	this.createTabLayout = function() {
		
		let dom = $('<div class="tabInner formLayout" style="margin-top:-7px;"></div>');
		dom.append('<ul></ul>');
		
		$.each(options.tabs, function(i,t) {
			
			let li = $('<li class="box tabInnerFrom"></li>');
			if (i == 0)
				li.addClass('on');
		
			$.each(t.columns, function(j,c) {
				
				let div = $('<div></div>');
				div.addClass(options.groupCls);
				div.addClass(c.groupCls);
				
				let label = $('<span></span>');
				label.addClass(options.labelCls);
				label.addClass(c.labelCls);
				label.append(c.label);
				
				let input = $('<div></div>');
				input.addClass(options.inputCls);
				input.addClass(c.inputCls);
				
				if ($.type(c.name) == 'array') {
					let group = $('<div class="app-box"></div>');
					$.each(c.name, function(k,n) {
						group.append('<div class="app-left app-mr10" id="'+options.prefix+n+'"></div>');
					});
					input.append(group);
				}else {
					input.append('<div class="app-box" id="'+options.prefix+c.name+'"></div>');
				}
				div.append(label);
				div.append(input);
				li.append(div);
			});
			dom.find('ul').append(li);
		});
		return dom;
	};
	
	//데이터 AJAX 로딩
	this.load = function( params ) {
		$.ajax({
			url: options.url,
			dataType: 'json',
			type: 'post',
			data: params,
			success: thisCmp.loadData,
			error: function(){}
		});
	};
	
	//데이터 로딩
	this.loadData = function( data ) {
		if (!data)
			return;
		
		// 데이터의 포맷적용		
		$.each(options.tabs, function(i,t) {
			$.each(t.columns, function(j,c) {
				if (c.formatter) {
					data[c.name] = c.formatter(data[c.name]);
				}
			});
		});
		$.formUtil.toHtml(thisElm.find('form'), data, options.prefix);
		
		// 2023.03.08 연령항목의 제목 변경 (사망일자가 있을 경우)
		$('#'+options.prefix+'sufrerAge')
			.closest(".formGroup")
			.find(".label")
			.html($.commUtil.empty(data['dthYmd']) ? '현재나이' : "사망당시나이");
	};
	//데이터 리셋
	this.resetData = function() {
		$.formUtil.toHtmlReset(thisElm.find('form'), options.prefix);
	};
	
	//폼데이터 반환
	this.getFormData = function() {
		return thisElm.find('form').serializeObject();
	};

	this.create();
	
	return this;
};

//=======================================================================//
//2021.10.20 LSH 구제급여 상세정보 조회영역 (관리자화면공통)
//-----------------------------------------------------------------------//
$.fn.appRelief = function ( args ) {
	
	let options = $.extend({
		// 스타일시트
		cls: "detailWrap mainInner",
		
		// 진행상태탭 스타일시트
		tabCls: "main-tab tabWrap type1 ",

		// 폼셀렉터명칭
		form: "personalForm",
		
		// 조회 URL
		url: getUrl('/adm/relief/getRelief.do'),

		// ID PREFIX
		prefix: 's_',
		
		// 서류업무구분 (구제급여)
		papeDtySeCd: CODE.PAPE_DTY_CD.RELIEF,
		
		// 진행상태
		status: [
			{code:'31', icon:getUrl('/images/main/i-mainTab-01.svg'),text:'신청접수'},
			{code:'32', icon:getUrl('/images/main/i-mainTab-03.svg'),text:'예비조사'},
			{code:'33', icon:getUrl('/images/main/i-mainTab-05.svg'),text:'본조사'},
			{code:'34', icon:getUrl('/images/main/i-mainTab-06.svg'),text:'지급'},
			{code:'35', icon:getUrl('/images/main/i-mainTab-04.svg'),text:'완료'}
		],
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	// 신청인정보
	let thisAplcnt = false;
	// 피해자정보
	let thisSufrer = false;
	// 신청정보
	let thisAply   = false;
	// 파일객체
	let thisFile   = false;
	// 데이터
	let thisData   = false;
	
	//DOM 생성
	this.create = function() {

		//ELM 초기화
		thisElm.html('');
		thisElm.addClass(options.cls);
		this.createStatus(thisElm);
		this.createAplcnt(thisElm);
		this.createSufrer(thisElm);
		this.createAply  (thisElm);
		this.createPaper (thisElm);
	};
	
	this.createStatus = function(elm) {
		
		let tab = $('<div></div>');
		tab.addClass(options.tabCls);
		tab.append('<ul id="appStatusTab"></ul>');
		tab.find('ul').addClass('box');
		
		$.each(options.status, function(i,o) {
			let li = $('<li></li>');
			li.data('code', o['code']);
			li.append('<i><img src="'+o['icon']+'"/></i>');
			li.append('<p class="txt">'+o['text']+'</p>');
			tab.find('ul').append(li);
		});
		
		elm.append(tab);
		elm.append('<div class="app-space25"></div>');
	};
	
	// 신청인정보 생성
	this.createAplcnt = function(elm) {

		elm.append('<div class="subTit type3"><h4>신청인 정보</h4></div>');
		elm.append('<div class="app-space10"></div>');
		elm.append('<div class="main-form formLayout box" id="appAplcnt"></div>');
		elm.append('<div class="app-space20"></div>');
		
		// 폼형태의 신청정보 표시
		thisAplcnt = $('#appAplcnt').appFormLayout({
			// 폼객체
			form: options.form,
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: options.prefix,
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplySeNm'     , label: '신청구분'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyNo'       , label: '신청번호'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplcntNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntBrdt'   , label: '생년월일'    , formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplcntTelno'  , label: '유선전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntMbtelNo', label: '휴대전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'aplcntEmlAddr', label: '전자우편주소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'aplcntAddrAll', label: '주    소'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'sufrerRelNm'  , label: '피해자와의관계'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dthYn'        , label: '피해자사망여부', formatter: $.formatUtil.toDthNm},
			]
		});
	};
	// 피해자정보 생성
	this.createSufrer = function(elm) {

		elm.append('<div class="subTit type3"><h4>피해자 정보</h4></div>');
		elm.append('<div class="app-space10"></div>');
		elm.append('<div class="main-form formLayout box" id="appSufrer"></div>');
		elm.append('<div class="app-space20"></div>');
		
		// 폼형태의 신청정보 표시
		thisSufrer = $('#appSufrer').appFormLayout({
			// 폼객체
			form: options.form,
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: options.prefix,
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'idntfcId'     , label: '식별ID'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerSxdstNm', label: '성    별'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'sufrerNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerBrdt'   , label: '생년월일', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'sufrerMbtelNo', label: '휴대전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerTelno'  , label: '유선전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerEmlAddr', label: '전자우편주소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerAddrAll', label: '주    소'},
			]
		});
	};
	// 신청정보 생성
	this.createAply = function(elm) {
		
		elm.append('<div class="subTit type3"><h4>신청 정보</h4></div>');
		elm.append('<div class="app-space10"></div>');
		elm.append('<div class="main-form formLayout box" id="appAply"></div>');
		elm.append('<div class="app-space20"></div>');
		
		// 폼형태의 신청정보 표시
		thisAply = $('#appAply').appFormLayout({
			// 폼객체
			form: options.form,
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: options.prefix,
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplyYmd'      , label: '신청일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'rcptYmd'      , label: '접수일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'bizAreaNm'    , label: '지　　역'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'bankNm'       , label: '지급은행'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'actno'        , label: '계좌번호'},
			]
		});
	};
	
	// 2022.12.12 제출서류제외 (사용안함)
	this.createPaper = function(elm) {
		
		let dom = $('<div class="subTit type3"></div>');
		dom.append('<h4 class="app-left app-pt20">제출서류목록</h4>');
		dom.append('<div class="app-right btnDiv"></div>');
		dom.append('<div class="app-both"></div>');
		dom.find('.btnDiv').append('<a href="javascript:void(0);" id="btnDownloadAll" class="btn">전체다운로드</a>');
		elm.append(dom);
		
		elm.append('<div class="app-space10"></div>');
		elm.append('<div class="tableWrap type1 app-scroll" style="max-height:200px;" id="appPapers"></div>');
		thisFile = $('#appPapers').appAplyFile({
			mode:   MODE.LIST,
			system: SYSTEM.ADMIN['code']
		});

		$('#btnDownloadAll').bind('click', function() {
			thisFile.doFileDownloadZip({
				dtySeCd:   options.papeDtySeCd,
				dcmtNo:    thisData['aplyNo'  ],
				dtlDcmtNo: '0'
			});
			return false;
		});
		$('#btnDownloadAll').hide();
	};

	// 데이터 AJAX 로딩
	this.load = function( params ) {
		$.ajax({
			url: options.url,
			dataType: 'json',
			type: 'post',
			data: params,
			success: thisCmp.loadData,
			error: function(){}
		});
	};
	
	//데이터 로딩
	this.loadData = function( data ) {
		if (!data)
			return;
		
		thisData = data;
		
		let stus = parseInt(data['prgreStusCd']);
		if (stus < 4)
			stus = 4;

		$('#appStatusTab').find('li').each(function() {
			$(this).removeClass('off');
			let code = parseInt($(this).data('code'));
			if (code <= stus) {
				$(this).addClass('off');
			}
		});	

		thisAplcnt.loadData(data);
		thisSufrer.loadData(data);
		thisAply.loadData(data);
		thisFile.loadList({
			dtySeCd:   options.papeDtySeCd,
			dcmtNo:    data['aplyNo'],
			dtlDcmtNo: '0'
		});
		$('#btnDownloadAll').show();
	};
	//데이터 리셋
	this.resetData = function() {
		// 2021.12.22 상태표시탭 리셋
		$('#appStatusTab').find('li').each(function() {
			$(this).removeClass('off');
		});
		// 2021.12.22 제출서류목록 리셋
		thisFile.resetList();
		$.formUtil.toHtmlReset($(options.form), options.prefix);
	};
	
	//폼데이터 반환
	this.getFormData = function() {
		return $(options.form).serializeObject();
	};

	this.create();
	
	return this;
};

//=======================================================================//
//2022.12.13 LSH 구제급여 종합현황 상세 신청정보
//-----------------------------------------------------------------------//
$.fn.appReliefMain = function ( args ) {
	
	let options = $.extend({
		// 스타일시트
		cls: "formLayout tabInnerFrom box",
		
		// 제목 스타일시트
		titleCls: "subTit type2 app-type2",

		// 폼셀렉터명칭
		form: "personalForm",
		
		// 조회 URL
		url: getUrl('/adm/relief/getRelief.do'),

		// ID PREFIX
		prefix: 's_',
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	// 신청인정보
	let thisAplcnt = false;
	// 피해자정보
	let thisSufrer = false;
	// 신청정보
	let thisAply   = false;
	
	//DOM 생성
	this.create = function() {

		//ELM 초기화
		thisElm.html('');
		this.createAplcnt(thisElm);
		this.createSufrer(thisElm);
		this.createAply  (thisElm);
		
		thisElm.find('.app-subtitle').addClass(options.titleCls);
		thisElm.find('.app-formbody').addClass(options.cls);
	};
	
	// 신청인정보 생성
	this.createAplcnt = function(elm) {

		elm.append('<div class="app-subtitle"><h4>신청인 정보</h4></div>');
		elm.append('<div class="app-formbody" id="appAplcnt"></div>');
		
		// 폼형태의 신청정보 표시
		thisAplcnt = $('#appAplcnt').appFormLayout({
			// 폼객체
			form: options.form,
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: options.prefix,
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplySeNm'     , label: '신청구분'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyNo'       , label: '신청번호'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplcntNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntBrdt'   , label: '생년월일'    , formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplcntTelno'  , label: '유선전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntMbtelNo', label: '휴대전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'aplcntEmlAddr', label: '전자우편주소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'aplcntAddrAll', label: '주    소'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'sufrerRelNm'  , label: '피해자와의관계'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dthYn'        , label: '피해자사망여부', formatter: $.formatUtil.toDthNm},
			]
		});
	};
	// 피해자정보 생성
	this.createSufrer = function(elm) {

		elm.append('<div class="app-subtitle"><h4>피해자 정보</h4></div>');
		elm.append('<div class="app-formbody" id="appSufrer"></div>');
		
		// 폼형태의 신청정보 표시
		thisSufrer = $('#appSufrer').appFormLayout({
			// 폼객체
			form: options.form,
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: options.prefix,
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'idntfcId'     , label: '식별ID'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerSxdstNm', label: '성    별'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'sufrerNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerBrdt'   , label: '생년월일', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'sufrerMbtelNo', label: '휴대전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerTelno'  , label: '유선전화번호', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerEmlAddr', label: '전자우편주소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerAddrAll', label: '주    소'},
			]
		});
	};
	// 신청정보 생성
	this.createAply = function(elm) {
		
		elm.append('<div class="app-subtitle"><h4>신청 정보</h4></div>');
		elm.append('<div class="app-formbody" id="appAply"></div>');

		// 폼형태의 신청정보 표시
		thisAply = $('#appAply').appFormLayout({
			// 폼객체
			form: options.form,
			// 폼생성안함
			formCreate: false,
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: options.prefix,
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplyYmd'      , label: '신청일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'rcptYmd'      , label: '접수일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'bizAreaNm'    , label: '지　　역'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'bankNm'       , label: '지급은행'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'actno'        , label: '계좌번호'},
			]
		});
	};
	
	// 데이터 AJAX 로딩
	this.load = function( params ) {
		$.ajax({
			url: options.url,
			dataType: 'json',
			type: 'post',
			data: params,
			success: thisCmp.loadData,
			error: function(){}
		});
	};
	
	//데이터 로딩
	this.loadData = function( data ) {
		if (!data)
			return;
		thisAplcnt.loadData(data);
		thisSufrer.loadData(data);
		thisAply.loadData(data);
	};
	//데이터 리셋
	this.resetData = function() {
		$.formUtil.toHtmlReset($(options.form), options.prefix);
	};
	//폼데이터 반환
	this.getFormData = function() {
		return $(options.form).serializeObject();
	};

	this.create();
	
	return this;
};
