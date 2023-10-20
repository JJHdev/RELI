/**
******************************************************************************************
*** 파일명    : comm_biz.js
*** 설명      : 업무 공통기능 자바스크립트
***             팝업창 관리.
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-02-10              ntarget
******************************************************************************************
**/

$(document).ready(function() {
	$.extend( $.validator.messages, { 
		required: "필수 항목입니다.", 
		remote: "항목을 수정하세요.", 
		email: "유효하지 않은 E-Mail주소입니다.", 
		url: "유효하지 않은 URL입니다.", 
		date: "올바른 날짜를 입력하세요.", 
		dateISO: "올바른 날짜(ISO)를 입력하세요.", 
		number: "유효한 숫자가 아닙니다.", 
		digits: "숫자만 입력 가능합니다.", 
		creditcard: "신용카드 번호가 바르지 않습니다.", 
		equalTo: "같은 값을 다시 입력하세요.", 
		extension: "올바른 확장자가 아닙니다.", 
		maxlength: $.validator.format( "{0}자를 넘을 수 없습니다. " ), 
		minlength: $.validator.format( "{0}자 이상 입력하세요." ), 
		rangelength: $.validator.format( "문자 길이가 {0} 에서 {1} 사이의 값을 입력하세요." ), 
		range: $.validator.format( "{0} 에서 {1} 사이의 값을 입력하세요." ), 
		max: $.validator.format( "{0} 이하의 값을 입력하세요." ), 
		min: $.validator.format( "{0} 이상의 값을 입력하세요." ) 
	});
	//2022.01.19
	$('a[href="#"]').each(function() {
		$(this).prop('href','javascript:void(0);');
	}); 
	$('a[href="#void"]').each(function() {
		$(this).prop('href','javascript:void(0);');
	}); 
});

//=======================================================================//
//네비게이션바 메뉴 AJAX 로딩 함수 (사용자화면)
//2021.07.09 LSH  최초작성
//-----------------------------------------------------------------------//
var loadTopBar = function(ulKey, url, params) {
	var ul = $(ulKey);
	if (!ul)
		return;

	var data  = $.ajaxUtil.ajaxDefault(url, params);

	// 현재 화면의 URL
	var curl  = $(location).attr("pathname");
	if (data) {
		$.each(data, function(i,o) {
			var id = o['menuId'];
			var ac = $('<a href="'+getUrl(o['trgtUrl'])+'"><span>'+o['menuNm']+'</span></a>');
			var li = $('<li></li>');
			var dr = null;
			// 2단계 드롭다운 표시
			if (o.children) {
				ac = $('<a href="javascript:void(0);"><span>'+o['menuNm']+'</span></a>');
				li = $('<li></li>');
				dr = $('<ul></ul>');
				$.each(o.children, function(j,c) {
					var dc = $('<a href="'+getUrl(c['trgtUrl'])+'">'+c['menuNm']+'</a>');
					if (c['trgtUrl'] &&
						c['trgtUrl'].indexOf(curl) >= 0)
						dc.addClass('active');
					dr.append($('<li></li>').append(dc));
				});
				if (o['trgtUrl'] &&
					o['trgtUrl'].indexOf(curl) >= 0)
					ac.addClass('active');

				li.append(ac);
				li.append(dr);
			}
			else {
				li.append(ac);
			}
			ul.append(li);
		});
	}
};

// 구제급여관련 테이블 생성
function createReliefTable( title, columns ) {
	
	let tbl = $('<table></table>');
	tbl.append('<colgroup></colgroup>');
	tbl.append('<tbody class="alignL"></tbody>');
	
	if (title) {
		tbl.find('colgroup').append('<col style="width:20%;"/>');
		tbl.find('colgroup').append('<col style="width:30%;"/>');
	}
	else {
		tbl.find('colgroup').append('<col style="width:27%;"/>');
	}
	tbl.find('colgroup').append('<col style=""/>');
	
	$.each(columns, function(i,c) {
		let tr = $('<tr></tr>');
		if (i == 0) {
			if (title) {
				tr.append('<th rowspan="'+columns.length+'" class="alignC">'+title+'</th>');
			}
		}
		tr.append('<td>'+c['label']+'</td>');
		tr.append('<td>'+c['value']+'</td>');
		tbl.find('tbody').append(tr);
	});
	return tbl; 
}

// 진행현황 테이블 생성
function createStepTable( args ) {
	
	$.each(args.columns, function(i,c) {
		let li = $('<li></li>');
		li.append('<dl></dl>');
		li.find('dl').append('<dt>'+c.label+'</dt>');
		li.find('dl').append('<dd>'+c.value+'</dd>');
		
		if (c.cls)
			li.addClass(c.cls);
		if (c.dtCls)
			li.find('dt').addClass(c.dtCls);
		if (c.ddCls)
			li.find('dd').addClass(c.ddCls);
		if (c.click)
			li.bind('click', c.click);
		if (c.data) {
			for (let p in c['data']) {
				li.data(p, c['data'][p]);
			}
		}
		args.element.append(li);
	});
}


//===========================================================================//
//목록테이블
//===========================================================================//
$.fn.appTableLayout = function ( args ) {
	
	let options = $.extend({

		// 스타일시트
		cls: false,
		
		// 감싸는영역 스타일시트
		wrapCls: "tableWrap type1 app-usr-table",
		
		// 테이블 스타일시트
		tableCls: false,
		
		// 칼럼목록 (name, text, rowspan, colspan, cls, formatter, head: {cls, rowspan, colspan})
		columns: [],
		
		// 데이터목록
		data: false,
		
		// 데이터없음 표시유무
		nodata: false,
		
		// 데이터없음 메세지
		nodataMsg: '검색된 결과가 없습니다.',
		
		// 행선택
		select: false,
		
		// 생성 후 처리함수
		callback: false,

		// [2022.12.14 추가] 감싸는 레이어 사용여부
		wrap: false,
		// [2022.12.14 추가] 감싸는 레이어 KEY
		wrapKey: "app-usr-wrap",
		
		// [2022.12.14 추가] 제목
		title: false,
		// [2022.12.14 추가] 제목 스타일시트
		titleCls: "subTit type2 app-type2",
		// [2022.12.14 추가] 제목 레이어 KEY
		titleKey: "form-title",
		// [2022.12.14 추가] 제목 태그
		titleTag: "h4",
		
		// [2022.12.19 추가] 테이블 상단 추가컨텐츠 (함수)
		prependHtml: false,
		
		// [2022.12.19 추가] 테이블 하단 추가컨텐츠 (함수)
		appendHtml: false,
		
		// [2022.12.19 추가] 열너비설정 목록 (배열)
		colgroup: false,
		
		// [2022.12.19 추가] 상단 공백여부 (10px)
		headSpace: false,
		
		// [2022.12.19 추가] 하단 공백여부 (10px)
		tailSpace: false,
		
		// [2022.12.19 추가] 공백레이어 스타일시트 (10px)
		spaceCls: 'h10',
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	
	//DOM 생성
	this.create = function() {
		//ELM 초기화
		thisElm.html('');

		// [2022.12.19] 상단공백이 true인 경우
		if (options.headSpace)
			thisElm.append('<div class="'+options.spaceCls+'"></div>');

		// 감싸는 레이어 사용인 경우
		if (options.wrap) {
			if (options.title) {
				thisElm.append(this.createTitle());
			}
			thisElm.append('<div class="'+options.wrapKey+'"></div>');
			this.createLayer( thisElm.find('.'+options.wrapKey) );
		}		
		else {
			this.createLayer( thisElm );
		}
		if (options.data)
			this.loadData(options.data);
		
		// [2022.12.19] 하단공백이 true인 경우
		if (options.tailSpace)
			thisElm.append('<div class="'+options.spaceCls+'"></div>');
	};
	
	// [2022.12.14 추가] 레이어 생성
	this.createLayer = function(elm) {
		elm.addClass(options.wrapCls);
		if (options.cls)
			elm.addClass(options.cls);

		if (options.prependHtml) {
			options.prependHtml(elm);
		}
		elm.append(this.createTable());
		
		if (options.appendHtml) {
			options.appendHtml(elm);
		}
	};

	// [2022.12.14 추가] 제목 생성
	this.createTitle = function() {
		let div = $('<div></div>');
		div.addClass(options.titleCls);
		div.append('<'+options.titleTag+' class="'+options.titleKey+'"></'+options.titleTag+'>');
		div.find(options.titleTag).append(options.title);
		return div;
	};
	
	// [2022.12.14 추가] 제목 변경
	this.loadTitle = function( title ) {
		if (options.title &&
			thisElm.find('.'+options.titleKey).length > 0)
			thisElm.find('.'+options.titleKey).html(title);
	};
	
	// [2022.12.14 추가] 제목 리셋
	this.resetTitle = function() {
		if (options.title &&
			thisElm.find('.'+options.titleKey).length > 0)
			thisElm.find('.'+options.titleKey).html(options.title);
	};
	//2022.12.20 현재의 DOM ELEMENT 반환
	this.getElement = function( clsName ) {
		return (clsName ? thisElm.find('.'+clsName) : thisElm);
	};
	
	this.createTable = function() {
		let table = $('<table></table>');
		if (options.tableCls)
			table.addClass(options.tableCls);
		
		if (options.colgroup && 
			options.colgroup.length > 0) {
			let cg = $('<colgroup></colgroup>');
			$.each(options.colgroup, function(i,col) {
				cg.append('<col width="'+col+'"/>');
			});
			table.append(cg);
		}
		table.append(thisCmp.createHead());
		table.append('<tbody></tbody>');
		return table;
	};
	
	this.createHead = function() {
		let dom = $('<thead></thead>');
		let tr = $('<tr></tr>');
		$.each(options.columns, function(i,c) {
			let th = $('<th></th>');
			th.append(c.label);
			if (c.head) {
				if (c.head['cls'    ]) th.addClass(c.head['cls']);
				if (c.head['rowspan']) th.prop('rowspan', c.head['rowspan']);
				if (c.head['colspan']) th.prop('colspan', c.head['colspan']);
			}
			tr.append(th);
		});
		dom.append(tr);
		return dom;
	};
	
	this.createRow = function(index, row) {
		if (!row)
			return null;
			
		let tr = $('<tr></tr>');
		if (row['cls'])
			tr.addClass(row['cls']);
		
		$.each(options.columns, function(i,c) {
			let td = $('<td></td>');
			td.data('index', index);
			if (c.key)
				td.data('key', row[c.key]);
			if (c.styler)
				c.styler(td, row[c.name], row, index, i);
			
			td.append('<span class="mobile">'+c.label+'</span>');
			
			if (c.formatter)
				td.append(c.formatter(row[c.name], row));
			else
				td.append(row[c.name]);
				
			if (c.cls)
				td.addClass(c.cls);
			if (c.rowspan)
				td.prop('rowspan', c.rowspan);
			if (c.colspan)
				td.prop('colspan', c.colspan);
			if (c.click)
				td.bind("click", c.click);
			if (c.dblclick)
				td.bind("dblclick", c.dblclick);
				
			tr.append(td);
		});
		if (options.select) {
			tr.bind('click', function() {
				options.select(index, row);
				return false;
			});
		}
		return tr;
	};
	
	this.resetData = function() {
		thisElm.find('table > tbody').html('');
		if (options.nodata) {
			let td = $('<td></td>');
			if (options.columns.length > 1) {
				td.prop('colspan', options.columns.length);
			}
			td.append(options.nodataMsg);

			thisElm.find('table > tbody').append('<tr></tr>');
			thisElm.find('table > tbody > tr').append(td);
		}
	}

	//데이터 로딩
	this.loadData = function( data ) {
		
		if (data && 
			data.length > 0) {
			options.data = data;
			let dom = thisElm.find('tbody');
			dom.html('');
			
			$.each(options.data, function(i,row) {
				dom.append (thisCmp.createRow(i,row));
			});
			if (options.callback)
				options.callback(thisCmp, data);
		}
		else {
			thisCmp.resetData();
		}
	};
	
	//데이터 AJAX 로딩
	this.load = function( url, params ) {
		
		$.ajax({
			url: url,
			dataType: 'json',
			type: 'post',
			data: params,
			success: function(data) {
				if (!data)
					return;
				if (data.rows)
					thisCmp.loadData(data.rows);
				else
					thisCmp.loadData(data);
			},
			error: function(){}
		});
	};
	
	// 데이터 반환
	this.getData = function() {
		return options.data;
	};
	
	// 데이터 행단위 반환
	this.getDataRow = function(index) {
		return options.data[index];
	};

	//데이터 행선택
	this.selectRow = function(index) {
		let dom = thisElm.find('tbody');
		if (dom.find('tr').length > 0)
			dom.find('tr').eq(index).trigger('click');
	};

	this.create();
	
	return this;
};

//=======================================================================//
//2021.10.22 LSH 폼레이아웃
//-----------------------------------------------------------------------//
$.fn.appFormLayout = function ( args ) {
	
	let options = $.extend({
		
		// 스타일시트
		cls: "formLayout box",
		
		// 조회 URL
		url: false,
		
		// 조회조건
		params: false,
		
		// 폼데이터
		data: false,

		// 명칭만 정의
		nameOnly: true,
		
		// 폼명칭
		form: false,
		
		// 폼생성여부
		formCreate: true,
		
		// 기본그룹CLS
		groupCls: "formGroup",
		
		// 레이블CLS
		labelCls: "label",
		
		// 입력박스CLS
		inputCls: "inputWrap",
		
		// 칼럼ID의 공통 prefix
		prefix: false,
		
		// 칼럼구성
		columns: [
			// 예제
			// {groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplySeNm'     , label: '신청구분'},
			// {groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerNm'     , label: '성　　명'},
			// {groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'bizAreaNm'    , label: '지　　역'},
			// {groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류'},
			// {groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplyYmd'      , label: '신청일자', formatter: $.formatUtil.toDashDate},
			// {groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerMbtelNo', label: '수신번호', formatter: $.formatUtil.formatUtil}
		],
		// 생성후 콜백함수
		createCallback: false,
		// 데이터로드전 콜백함수
		beforeLoadCallback: false, // function(data, cmp, elm)
		// 데이터로드후 콜백함수
		afterLoadCallback: false,  // function(data, cmp, elm)
		
		// [2022.12.14 추가] 감싸는 레이어 사용여부
		wrap: false,
		// [2022.12.14 추가] 감싸는 레이어 KEY
		wrapKey: "appLayout",
		// [2022.12.14 추가] 감싸는 레이어 스타일시트
		wrapCls: "formLayout box tabInnerFrom",
		
		// [2022.12.14 추가] 제목
		title: false,
		// [2022.12.14 추가] 제목 스타일시트
		titleCls: "subTit type2 app-type2",
		// [2022.12.14 추가] 제목 레이어 KEY
		titleKey: "form-title",
		
		// [2022.12.19 추가] 상단 공백여부 (10px)
		headSpace: false,
		
		// [2022.12.19 추가] 하단 공백여부 (10px)
		tailSpace: false,
		
		// [2022.12.19 추가] 공백레이어 스타일시트 (10px)
		spaceCls: 'h10',
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	//폼객체
	let thisForm = false;
	
	//DOM 생성
	this.create = function() {
		
		//ELM 초기화
		thisElm.html('');

		// [2022.12.19] 상단공백이 true인 경우
		if (options.headSpace)
			thisElm.append('<div class="'+options.spaceCls+'"></div>');

		// 감싸는 레이어 사용인 경우
		if (options.wrap) {
			if (options.title) {
				thisElm.append(this.createTitle());
			}
			thisElm.append('<div class="'+options.wrapKey+'"></div>');
			thisElm.find('.'+options.wrapKey).addClass(options.wrapCls);
			this.createLayer( thisElm.find('.'+options.wrapKey) );
		}		
		else {
			this.createLayer( thisElm );
		}
		if (options.data)
			this.loadData(options.data);
		// [2022.12.19] 하단공백이 true인 경우
		if (options.tailSpace)
			thisElm.append('<div class="'+options.spaceCls+'"></div>');

		if (options.createCallback)
			options.createCallback(thisCmp);
	};
	
	// [2022.12.14 추가] 제목 생성
	this.createTitle = function() {
		let div = $('<div></div>');
		div.addClass(options.titleCls);
		div.append('<h4 class="'+options.titleKey+'"></h4>');
		div.find('h4').append(options.title);
		return div;
	};
	
	// [2022.12.14 추가] 제목 변경
	this.loadTitle = function( title ) {
		if (options.title &&
			thisElm.find('.'+options.titleKey).length > 0)
			thisElm.find('.'+options.titleKey).html(title);
	};
	
	// [2022.12.14 추가] 제목 리셋
	this.resetTitle = function() {
		if (options.title &&
			thisElm.find('.'+options.titleKey).length > 0)
			thisElm.find('.'+options.titleKey).html(options.title);
	};
	
	// [2022.12.14 추가] 폼레이어 생성
	this.createLayer = function(elm) {
		elm.addClass(options.cls);
		if (options.formCreate) {
			let form = thisCmp.createForm();
			thisCmp.createLayout(form);
			elm.append(form);
			thisForm = elm.find('form');
		}
		else {
			thisCmp.createLayout(elm);
			thisForm = $(options.form);
		}
		return elm;
	};
	
	this.createForm = function() {
		let form = $('<form method="post" onsubmit="return false;"></form>');
		form.prop('name', options.form);
		if (!options.nameOnly)
			form.prop('id', options.form);
		
		$.each(options.hiddens, function(i,h) {
			let input = $('<input type="hidden" value=""/>');
			input.prop('name', h);
			if (!options.nameOnly)
				input.prop('id', h);
			form.append(input);
		});
		return form;
	};

	this.createLayout = function( dom ) {
		
		$.each(options.columns, function(i,c) {
				
			let div = $('<div></div>');
			div.addClass(options.groupCls);
			div.addClass(c.groupCls);
			
			let input = thisCmp.createColumn(c);
			
			// 레이블값이 있을 경우에만 추가
			if (c.label) {
				let label = $('<span></span>');
				label.addClass(options.labelCls);
				label.addClass(c.labelCls);
				label.append(c.label);
				div.append(label);
			}
			div.append(input);
			dom.append(div);
		});
	};
	
	this.createColumn = function( c ) {
		let input = $('<div></div>');
		input.addClass(options.inputCls);
		input.addClass(c.inputCls);
		
		if (c.prefix) {
			let pr = $('<span></span>');
			pr.append(c.prefix.text);
			if (c.prefix.cls)
				pr.addClass(c.prefix.cls);
			input.append(pr);
		}
		if ($.type(c.name) == 'array') {
			let group = $('<div class="app-box"></div>');
			$.each(c.name, function(k,n) {
				group.append( thisCmp.createElement({name:n, cls: 'app-left app-mr10'}) );
			});
			input.append(group);
		}
		else {
			let inp = thisCmp.createElement(c);
			input.append( inp );
		}
		if (c.postfix) {
			let po = $('<span></span>');
			po.append(c.postfix.text);
			if (c.postfix.cls)
				po.addClass(c.postfix.cls);
			input.append(po);
		}
		return input;
	}
	
	this.createElement = function( c ) {
		let elm = false;
		let id  = (options.prefix ? options.prefix : '')+c.name;
		if (c.appendHtml) {
			elm = $('<div></div>');
			elm.append(c.appendHtml());
			return elm;
		}
		else if (c.html) {
			elm = $('<div></div>');
			//elm.prop('id', id);
			if ($.type(c.html) === 'function')
				elm.append(c.html());
			else
				elm.append(c.html);
			return elm;
		}
		else if (c.input) {
			let typ = c.input.type;
			if (typ == 'textarea') {
				elm = $('<textarea></textarea>');
			}
			else if (typ == 'select'){
				elm = $('<select></select>');
			}
			else {
				elm = $('<input/>');
				elm.prop('type', typ);
			}
			elm.prop('name', c.name);
			if (c.input.readonly)
				elm.prop('readonly', 'true');
			if (c.input.cls)
				elm.addClass(c.input.cls);
			if (!options.nameOnly) {
				if (c.id) elm.prop('id', c.id);
				else      elm.prop('id', c.name);
			}
			return elm;
		}
		else {
			elm = $('<div></div>');
			if (c.cls)
				elm.addClass(c.cls);
			else
				elm.addClass('app-box');
			elm.prop('id', id);
			return elm;
		}
	};
	
	//데이터 AJAX 로딩
	this.load = function( opts ) {
		
		if (opts) {
			if (opts.url)    options.url    = opts.url;
			if (opts.params) options.params = opts.params;
		}
		
		$.ajax({
			url: options.url,
			data: options.params,
			dataType: 'json',
			type: 'post',
			success: thisCmp.loadData,
			error: function(){}
		});
	};
	
	//데이터 로딩
	this.loadData = function( data ) {
		if (!data)
			return;
		
		// 데이터로드전 콜백함수가 있으면
		if (options.beforeLoadCallback) {
			options.beforeLoadCallback(data, thisCmp, thisElm);
		}
		options.data = data;
		// 데이터의 포맷적용		
		$.each(options.columns, function(i,c) {
			if (c.formatter) {
				data[c.name] = c.formatter(data[c.name], data);
			}
		});
		$.formUtil.toForm(data, thisForm);
		if (options.prefix) {
			$.formUtil.toHtml(thisForm, data, options.prefix);
		}
		// 데이터로드후 콜백함수가 있으면
		if (options.afterLoadCallback) {
			options.afterLoadCallback(data, thisCmp, thisElm);
		}
	};
	//데이터 리셋
	this.resetData = function() {
		options.data = false;
		thisForm.form('reset');
		if (options.prefix) {
			$.formUtil.toHtmlReset(thisForm, options.prefix);
		}
	};
		
	//폼데이터 반환
	this.getFormData = function() {
		return thisForm.serializeObject();
	};
	
	// 데이터 반환
	this.getData = function() {
		return options.data;
	};
	
	// 현재 DOM객체 반환
	this.getDom = function() {
		return thisElm;
	};
	
	// 현재 폼객체 반환
	this.getForm = function() {
		return thisForm;
	};

	this.create();
	
	return this;
};

//===========================================================================//
//2022.12.14 테이블폼 형태의 레이아웃
//===========================================================================//
$.fn.appTableFormLayout = function ( args ) {
	
	let options = $.extend({
		
		// 제목
		title: false,
		
		// 제목레이어 태그
		titleTag: "h4", // h3, h2

		// 제목 스타일시트
		titleCls: "subTit type2 app-type2",
		
		// 제목레이어 명칭 (cls)
		titleKey: "form-title",
		
		// 테이블 레이어명칭 (cls)
		layerKey: "form-table",
		
		// 테이블 레이어 스타일시트
		layerCls: "tableWrap type3",
		
		// 테이블 스타일시트
		tableCls: false,
		
		// 스타일시트
		cls: false,  // "formLayout box tabInnerFrom"
		
		// 조회 URL
		url: false,
		
		// 조회 조건
		params: false,
		
		// 조회 데이터
		data: false,

		// 명칭만 정의
		nameOnly: true,
		
		// 폼명칭
		form: false,
		
		// 폼생성여부
		formCreate: true,
		
		// 폼히든박스 목록 (명칭 배열)
		hiddens: false,
		
		// 칼럼ID의 공통 prefix
		prefix: false,
		
		// 행단위 칼럼목록 (name, text, rowspan, colspan, cls, formatter, head: {cls, rowspan, colspan})
		rows: [[]],
		
		// 열너비설정 목록 (배열)
		colgroup: false,
		
		// 생성후 콜백함수
		createCallback: false
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	//폼객체
	let thisForm = false;
	//내부사용 포맷함수
	let thisFormat = {};


	//DOM 생성
	this.create = function() {
		
		if (options.cls)
			thisElm.addClass(options.cls);
		
		//ELM 초기화
		thisElm.html('');
		
		if (options.title)
			thisElm.append(thisCmp.createTitle());
		
		thisElm.append('<div class="'+options.layerKey+'"></div>');
		
		if (options.layerCls)
			thisElm.find('.'+options.layerKey).addClass(options.layerCls);

		if (options.formCreate) {
			let form = thisCmp.createForm();
			form.append(thisCmp.createTable());
			thisElm.find('.'+options.layerKey).append(form);
			thisForm = thisElm.find('form');
		}
		else {
			thisElm.find('.'+options.layerKey).append(thisCmp.createTable());
			thisForm = $(options.form);
		}
		if (options.data)
			thisCmp.loadData(options.data);
		
		if (options.createCallback)
			options.createCallback(thisCmp);
	};
	
	this.createTitle = function() {
		let div = $('<div></div>');
		if (options.titleCls)
			div.addClass(options.titleCls);
		div.append('<'+options.titleTag+'></'+options.titleTag+'>');
		div.find(options.titleTag).addClass(options.titleKey);
		div.find(options.titleTag).append(options.title);
		return div;
	};

	this.createForm = function() {
		let form = $('<form method="post" onsubmit="return false;"></form>');
		form.prop('name', options.form);
		if (!options.nameOnly)
			form.prop('id', options.form);
		
		$.each(options.hiddens, function(i,h) {
			let input = $('<input type="hidden" value=""/>');
			input.prop('name', h);
			if (!options.nameOnly)
				input.prop('id', h);
			form.append(input);
		});
		return form;
	};
	
	this.createTable = function() {
		let table = $('<table></table>');
		if (options.tableCls)
			table.addClass(options.tableCls);
			
		if (options.colgroup && 
			options.colgroup.length > 0) {
			let cg = $('<colgroup></colgroup>');
			$.each(options.colgroup, function(i,col) {
				cg.append('<col width="'+col+'"/>');
			});
			table.append(cg);
		}
		table.append('<tbody></tbody>');
		// 행단위 LOOP
		$.each(options.rows, function(i,r) {
			table.find('tbody').append(thisCmp.createRow(i,r));
		});
		return table;
	};
	
	this.createRow = function(index, row) {
		let tr = $('<tr></tr>');
		tr.data('index', index);
		$.each(row, function(j,c) {
			
			if (c.head) {
				let th = $('<th></th>');
				th.append(c.head['label']);
				if (c.head['cls'    ]) th.addClass(c.head['cls']);
				if (c.head['rowspan']) th.prop('rowspan', c.head['rowspan']);
				if (c.head['colspan']) th.prop('colspan', c.head['colspan']);
				tr.append(th);
			}
			
			if (c.name) {
				let td = $('<td></td>');
				if (c.cls)
					td.addClass(c.cls);
				if (c.rowspan)
					td.prop('rowspan', c.rowspan);
				if (c.colspan)
					td.prop('colspan', c.colspan);
				if (c.click)
					td.bind("click", c.click);
				if (c.dblclick)
					td.bind("dblclick", c.dblclick);
	
				td.append( thisCmp.createElement(c) );
				tr.append( td );
			}
		});
		return tr;
	};
	
	this.createElement = function( c ) {
		
		let elm = false;
		let id  = (options.prefix ? options.prefix : '')+c.name;
		
		if (c.html) {
			elm = $('<div></div>');
			elm.prop('id', id);
			
			if ($.type(c.html) === 'function')
				elm.append(c.html());
			else
				elm.append(c.html);
		}
		else if (c.input) {
			let typ = c.input.type;
			if (typ == 'textarea') {
				elm = $('<textarea></textarea>');
			}
			else if (typ == 'select'){
				elm = $('<select></select>');
			}
			else {
				elm = $('<input/>');
				elm.prop('type', typ);
			}
			elm.prop('name', c.name);
			if (c.input.readonly)
				elm.prop('readonly', 'true');
			if (c.input.cls)
				elm.addClass(c.input.cls);
			if (!options.nameOnly) {
				if (c.id) elm.prop('id', c.id);
				else      elm.prop('id', c.name);
			}
		}
		else {
			elm = $('<div></div>');
			if (c.cls)
				elm.addClass(c.cls);
			//else
			//	elm.addClass('app-box');
			elm.prop('id', id);
		}
		if (c.formatter) {
			thisFormat[c.name] = c.formatter; 
		}
		return elm;
	};

	//데이터 AJAX 로딩
	this.load = function( opts ) {
		
		if (opts) {
			if (opts.url)    options.url    = opts.url;
			if (opts.params) options.params = opts.params;
		}
		
		$.ajax({
			url: options.url,
			data: options.params,
			dataType: 'json',
			type: 'post',
			success: function(result) {
				if (!result)
					return;
				if (!result.Data)
					return;
				thisCmp.loadData(result.Data);
			},
			error: function(){}
		});
	};
	//제목변경
	this.loadTitle = function(title) {
		if (thisElm.find('.'+options.titleKey) &&
			thisElm.find('.'+options.titleKey).length > 0)
			thisElm.find('.'+options.titleKey).html(title);
	};
	
	//제목리셋
	this.resetTitle = function() {
		if (thisElm.find('.'+options.titleKey) &&
			thisElm.find('.'+options.titleKey).length > 0)
			thisElm.find('.'+options.titleKey).html(options.title);
	};
	
	//데이터 로딩
	this.loadData = function( data ) {

		if (!data)
			return;

		options.data = data;
		
		thisCmp.resetData();

		// 데이터의 포맷적용
		$.each(thisFormat, function( name, formatter ) {
			data[name] = formatter(data[name]);
		});
		$.formUtil.toForm(data, thisForm);
		if (options.prefix) {
			$.formUtil.toHtml(thisForm, data, options.prefix);
		}
	};
	
	this.resetData = function() {
		options.data = false;
		thisForm.form('reset');
		if (options.prefix) {
			$.formUtil.toHtmlReset(thisForm, options.prefix);
		}
	};
	
	// 데이터 반환
	this.getData = function() {
		return options.data;
	};
	
	//폼데이터 반환
	this.getFormData = function() {
		return thisForm.serializeObject();
	};
	this.create();
	return this;
};


