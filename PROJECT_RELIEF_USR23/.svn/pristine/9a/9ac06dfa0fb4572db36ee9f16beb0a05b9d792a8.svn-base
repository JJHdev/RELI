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
				ac = $('<a href="'+getUrl(o['trgtUrl'])+'"><span>'+o['menuNm']+'</span></a>');
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
		
		//생성 후 처리함수
		callback: false
		
	}, args);

	//현재객체
	let thisCmp = this;
	//DOM객체
	let thisElm = $(this);
	
	//DOM 생성
	this.create = function() {
		//ELM 초기화
		thisElm.html('');
		thisElm.addClass(options.wrapCls);
		
		if (options.cls)
			thisElm.addClass(options.cls);
			
		thisElm.append(thisCmp.createTable());
		thisCmp.loadData(options.data);
	};	
	
	this.createTable = function() {
		let table = $('<table></table>');
		if (options.tableCls)
			table.addClass(options.tableCls);
			
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
		let tr = $('<tr></tr>');
		$.each(options.columns, function(i,c) {
			let td = $('<td></td>');
			td.data('index', index);
			if (c.key)
				td.data('key', row[c.key]);
			
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
		createCallback: false
		
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
		thisElm.addClass(options.cls);
		
		if (options.formCreate) {
			let form = thisCmp.createForm();
			thisCmp.createLayout(form);
			thisElm.append(form);
			thisForm = thisElm.find('form');
		}
		else {
			thisCmp.createLayout(thisElm);
			thisForm = $(options.form);
		}
		if (options.data)
			thisCmp.loadData(options.data);
		
		if (options.createCallback)
			options.createCallback(thisCmp);
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
			
			let label = $('<span></span>');
			label.addClass(options.labelCls);
			label.addClass(c.labelCls);
			label.append(c.label);
			
			let input = thisCmp.createColumn(c);
			
			div.append(label);
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
			input.append( thisCmp.createElement(c) );
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
			else
				elm.addClass('app-box');
			elm.prop('id', id);
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
			success: thisCmp.loadData,
			error: function(){}
		});
	};
	
	//데이터 로딩
	this.loadData = function( data ) {
		if (!data)
			return;
		options.data = data;
		// 데이터의 포맷적용		
		$.each(options.columns, function(i,c) {
			if (c.formatter) {
				data[c.name] = c.formatter(data[c.name]);
			}
		});
		$.formUtil.toForm(data, thisForm);
		if (options.prefix) {
			$.formUtil.toHtml(thisForm, data, options.prefix);
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

	this.create();
	
	return this;
};

