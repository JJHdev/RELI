/**
******************************************************************************************
*** 파일명    : comm_element.js
*** 설명      : FORM ELEMENTS 사용자 정의 컴포넌트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-08-05              LSH
******************************************************************************************
**/
//===========================================================================//
//콤보박스
//===========================================================================//
$.fn.appComboBox = function ( args ) {
	
	var options = $.extend({
		
		//빈값여부
		init: false,
		
		//최초로딩
		first: true,
		
		//자동로딩여부
		autoload: true,

		//로딩완료여부
		loaded: false,
		
		//콤보표시방식
		//1 : 텍스트
		//2 : 코드
		//3 : 코드: 텍스트
		//4 : 텍스트 [코드]
		mode: 1,
		
		//콤보데이터 타입(static, dynamic)
		type: 'dynamic',
		
		//콤보데이터 검색 AJAX URL
		url: getUrl('/com/cmm/getComboCode.do'),
		
		//콤보데이터 조건 객체
		params: {},
		
		//콤보데이터 결과 배열
		rows: false,
		
		//콤보 선택값
		value: false,
		
		//콤보 선택이벤트
		change: false,
		
		//콤보생성 후 처리함수
		callback: false,
		
		//데이터 필터함수
		filter: false,
		
		//로딩 필터
		loadFilter: false,
	}, args);

	//현재객체
	var thisCmp = this;
	
	//콤보객체
	var thisElm = $(this);

	if (!$.commUtil.empty(thisElm.data('value')))
		options.value = thisElm.data('value');

	//콤보값객체 가져오기
	this.getOptionRow = function(index) {
		if (!options.rows)
			return false;
		if (options.rows.length == 0)
			return false;
		if (options.rows.length < index)
			return false;
		
		return options.rows[index];
	};

	//2022.12.07 선택된 콤보값객체 가져오기
	this.getSelectedRow = function() {
		if (!options.rows)
			return false;
		if (options.rows.length == 0)
			return false;
		let index = thisCmp.getSelectedIndex();
		return options.rows[index];
	};
	
	//2022.12.07 선택된 INDEX 반환
	this.getSelectedIndex = function() {
		let n = -1;
		thisElm.find('option').each(function(i) {
			if ($(this).prop('selected')) {
				n = i;
				return false;
			}
		});
		return n;
	};
	
	//2023.01.12 비활성 처리
	this.disable = function() {
		thisElm.val('');
		thisElm.removeClass('error');
		thisElm.attr("disabled", true);
	};

	//2023.01.12 활성 처리
	this.enable = function() {
		thisElm.removeAttr("disabled"); 
	};
	
	//콤보데이터 변경
	this.change = function(val) {
		thisElm.val(val);
		thisElm.trigger('change');
	};
	
	//콤보값 반환
	this.getValue = function() {
		return thisElm.val();
	};
	
	//콤보값 정의
	this.setValue = function(val) {
		this.change(val);
	};
	
	this.createOption = function(obj) {
		
		var val =(obj['code'] || '');
		var txt =(obj['text'] || '');
		if (options.mode == 2)
		    txt = val;
        else if (options.mode == 3)
		    txt = val + ' : ' + txt;
        else if (options.mode == 4)
		    txt = txt + ' ['+val+']';
		
		var opt = '<option '
		        + ' value="'+val+'">'
                + txt
                + '</option>';
		return opt;
	};
	
	//콤보데이터 생성
	this.create = function(rows) {
		
		if (options.loadFilter) {
			rows = options.loadFilter(rows);
		}
		
		//{code, text}
		options.rows = rows;
		
		//콤보옵션 초기화
		thisElm.html('');
		
		if (options.init && options.init['text'])
			thisElm.append(thisCmp.createOption(options.init));
		
		$.each(rows, function(i,o) {
			let chk = true;
			// 필터함수가 있으면
			if (options.filter) {
				chk = options.filter(o);
			}
			if (chk) {
				thisElm.append(thisCmp.createOption(o));
			}
		});
		if (options.init && options.init['text']) {
			options.rows.unshift(options.init);
		} 
			thisElm.append(thisCmp.createOption(options.init));
		
		if (options.change)
			thisElm.bind('change', options.change);

		if (options.callback)
			options.callback(thisCmp);

		// 빈값 로딩이거나 최초 로딩시 콤보 선택값이 있는 경우
		if ((options.init || options.first) && options.value) {
			thisElm.find('option').each(function(i) {
				if ($(this).prop('value') == options.value) {
					thisElm.val(options.value);
					//thisElm.trigger('change');
				}
			});
		}
	};
	
	this.addOption = function( data ) {
		thisElm.append(thisCmp.createOption(data));
	};
	
	//콤보데이터 AJAX 로딩
	this.load = function( addparams ) {
		
		//static 일 경우
		if (options.type == 'static') {
			thisCmp.create(options.rows);
			return;
		}
		
		var p = options.params;
		
		if (addparams)
			p = $.extend(p, addparams);
		$.ajax({
			url: options.url,
			dataType: 'json',
			type: 'post',
			data: p,
			traditional : true,
			success: function(data){
				if (!data)
					return;

				thisCmp.create(data);
				
				//빈값 로딩시엔 first 값 유지
				//if (options.init) {
				//	options.init = false;
				//}
				//else {
				//	options.first = false;
				//}
				//로딩완료여부 변경
				options.loaded = true;
			},
			error: function(){}
		});
	};
	
	if (options.autoload)
		this.load();
	
	// 2022.12.27
	$(this).data('widget', this);
	
	return this;
};

//===========================================================================//
//라디오/체크박스
//===========================================================================//
$.fn.appSelectBox = function ( args ) {
	
	var options = $.extend({
		
		//폼타입 ('radio', 'checkbox')
		form: 'radio',

		//첫번째값
		init: false,
		
		//데이터 타입(static, dynamic)
		type: 'dynamic',
		
		//데이터 검색 AJAX URL
		url: getUrl('/com/cmm/getComboCode.do'),
		
		//데이터 조건 객체
		params: {},
		
		//데이터 결과 배열
		rows: false,
		
		//2022.12.06 박스 id prefix
		prefix: false,
		
		//박스 name (필수)
		name: false,

		// 레이블
		label: false,
		// 레이블태그
		labelTag: 'p', // p, span, label
		
		//선택값 (배열도 가능)
		value: false,
		
		// 스타일시트
		cls: false,
		
		// 영역 스타일시트
		wrapCls: "inputWrap",
		
		//클릭이벤트
		click: false,
		
		//생성 후 처리함수
		callback: false,
		
		//데이터 필터함수
		filter: false
	}, args);

	//현재객체
	var thisCmp = this;
	
	//박스객체
	var thisElm = $(this);

	if (!$.commUtil.empty(thisElm.data('value')))
		options.value = thisElm.data('value');

	//데이터값객체 가져오기
	this.getRow = function(index) {
		if (!options.rows)
			return false;
		if (options.rows.length == 0)
			return false;
		if (options.rows.length < index)
			return false;
		
		return options.rows[index];
	};
	
	//선택값 반환
	this.getValue = function() {
		let v = [];		
		thisElm.find('input[name="'+options.name+'"]').each(function() {
			if ($(this).is(':checked')) {
				v.push($(this).val());
			}
		});
		if (options.type == 'radio')
			return v[0];
		return v;
	};

	//값 단일선택처리
	this.setValue = function(value) {
		thisElm.find('input[name="'+options.name+'"]').each(function() {
			$(this).prop('checked', false);
			if (value == $(this).val())
				$(this).prop('checked', true);
		});
	};

	//2022.12.28 특정값의 옵션 비활성 처리
	this.disable = function(value) {
		thisElm.find('input[name="'+options.name+'"]').each(function() {
			if (value == $(this).val()) {
				$(this).prop('checked', false);
				$(this).prop('disabled', true);
				$(this).addClass('app-disable');
			}
		});
	};
	//2022.12.28 특정값의 옵션 활성 처리
	this.enable = function(value) {
		thisElm.find('input[name="'+options.name+'"]').each(function() {
			if (value == $(this).val()) {
				$(this).prop('disabled', false);
				$(this).removeClass('app-disable');
			}
		});
	};
		
	//박스생성
	this.createBox = function(index, value, label) {
		return '<input type="'+options.form+'"'
		        + ' name="'+options.name+'" '
                + ' id="'+(options.prefix ? options.prefix : '')+options.name+index+'" '
                + ' value="'+value+'" '
                + ' class="'+(options.cls ? options.cls : '')+'"'
                + '> '
                + '<label for="'+(options.prefix ? options.prefix : '')+options.name+index+'" '
				+ '> '+label+' </label>';
	}
	
	//값 선택처리
	this.select = function(value) {
		
		thisElm.find('input[name="'+options.name+'"]').each(function() {
			let f = $(this);
			if ($.isArray(value)) {
				$.each(value, function(i,v) {
					if (f.val() == v) {
						f.prop('checked', true);
					}
				});
			}
			else {
				if (f.val() == value) {
					f.prop('checked', true);
				}
			}
		});
	}
	
	//클릭이벤트 바인딩
	this.clickBind = function(callback) {
		thisElm.find('input[name="'+options.name+'"]').bind('click', callback);
	}
	//클릭이벤트 실행
	this.triggerClick = function(value) {
		thisElm.find('input[name="'+options.name+'"]').each(function() {
			let f = $(this);
			if (f.val() == value)
				f.trigger('click');
		});
	}
	
	//데이터 생성
	this.create = function(rows) {
		//{code, text}
		options.rows = rows;
		
		//ELM 초기화
		thisElm.html('');
		
		var idx = 0;
		
		// 레이블이 있으면
		if (options.label) {
			thisElm.append('<'+options.labelTag+'>'+options.label+'</'+options.labelTag+'>');
		}
		
		let wrap = $('<div></div>');
		wrap.addClass(options.wrapCls);
		if (options.form == 'checkbox') {
			wrap.addClass("check");
		}
		if (options.init && 
		    options.init['text']) {
			idx++;
			wrap.append( thisCmp.createBox(idx, options.init['code'], options.init['text']) );
		}
		$.each(rows, function(i,o) {
			o['code'] = (o['code'] || '');
			let chk = true;
			// 필터함수가 있으면
			if (options.filter) {
				chk = options.filter(o);
			}
			if (chk) {
				idx++;
				wrap.append( thisCmp.createBox(idx, o['code'], o['text']) );
			}
		});
		thisElm.append(wrap);
		
		if (options.value) {
			thisCmp.select(options.value);
		}
		if (options.click)
		    thisCmp.clickBind(options.click);

		if (options.callback)
			options.callback(thisCmp);
	};
	
	this.addOption = function( data ) {
		let idx = thisElm.find('input[type="'+options.form+'"]').length;
		thisElm.find('.'+options.wrapCls).append(
			thisCmp.createBox(idx+1, data['code'], data['text'])
		);
	};
	
	//데이터 AJAX 로딩
	this.load = function( addparams ) {
		
		//static 일 경우
		if (options.type == 'static') {
			thisCmp.create(options.rows);
			return;
		}
		
		var p = options.params;
		
		if (addparams)
			p = $.extend(p, addparams);
		
		$.ajax({
			url: options.url,
			dataType: 'json',
			type: 'post',
			data: p,
			success: function(data){
				if (!data)
					return;
				thisCmp.create(data);
			},
			error: function(){}
		});
	};
	this.load();
	
	// 2022.12.27
	$(this).data('widget', this);
	
	return this;
};

//===========================================================================//
//기간데이트박스
//===========================================================================//
$.fn.appTermBox = function ( args ) {
	
	var options = $.extend({

		//기본값
		init: {
			stValue: false,
			enValue: false
		},
		//기본값을 현재날짜로 설정할 경우
		initToday: false,
	
		// 시작일자 명칭
		stName: 'stdt',
		// 종료일자 명칭
		enName: 'endt',

		// 레이블
		label: false,
		// 레이블태그
		labelTag: 'p', // p, span, label
		
		//선택값 (배열도 가능)
		value: false,
		
		//생성 후 처리함수
		callback: false,
		
		buttons: [
			// 2021.12.27 기간변경
			// 오늘/1주일/1개월/3개월 -> 30일/60일/90일/1년/3년/5년
			//{cls:'app-term-btn', id:'today', code: '0D', text:'오늘'},
			//{cls:'app-term-btn', id:'week',  code: '7D', text:'1주일'},
			{cls:'app-term-btn', id:'month1', code: '1M', text:'30일'},
			{cls:'app-term-btn', id:'month2', code: '2M', text:'60일'},
			{cls:'app-term-btn', id:'month3', code: '3M', text:'90일'},
			{cls:'app-term-btn', id:'year1',  code: '1Y', text:'1년'},
			{cls:'app-term-btn', id:'year3',  code: '3Y', text:'3년'},
			{cls:'app-term-btn', id:'year5',  code: '5Y', text:'5년'}
		]
	}, args);

	//현재객체
	var thisCmp = this;
	
	//콤보객체
	var thisElm = $(this);

	//DOM 생성
	this.create = function() {
		
		//ELM 초기화
		thisElm.html('');
		
		// 레이블이 있으면
		if (options.label) {
			thisElm.append('<'+options.labelTag+'>'+options.label+'</'+options.labelTag+'>');
		}
		
		let stdt = $('<input type="text" />');
		stdt.prop('id'  , options.stName);
		stdt.prop('name', options.stName);
		let endt = $('<input type="text" />');
		endt.prop('id'  , options.enName);
		endt.prop('name', options.enName);
		
		// 기본값을 현재날짜로 설정할 경우
		if (options.initToday) {
			stdt.val( $.commUtil.toFormatDate(new Date(),'-') );
			endt.val( $.commUtil.toFormatDate(new Date(),'-') );
		}
		else if (options.init) {
			if (options.init.stValue)
				stdt.val(options.init.stValue);
			if (options.init.enValue)
				endt.val(options.init.enValue);
		}

		let stdom = $('<div class="cal-date"></div>');
		stdom.append(stdt);
		//stdom.append('<a href="javascript:void(0);"></a>');

		let endom = $('<div class="cal-date"></div>');
		endom.append(endt);
		//endom.append('<a href="javascript:void(0);"></a>');

		let dom = $('<div class="inputWrap cal"></div>');
		dom.append(stdom);
		dom.append('<div class="cal-span app-m3">~</div>');
		dom.append(endom);
		
		let btns = $('<div class="btnCheck"></div>');
		
		$.each(options.buttons, function(i,b) {
			let btn = $('<input type="radio" name="cal"/>');
			btn.prop('id'   , options.stName+b.id);
			btn.prop('name' , options.stName+'Btn');
			btn.prop('value', b.code);
			btn.data('code' , b.code);
			btn.data('text' , b.text);
			btn.data('stdt' , options.stName);
			btn.data('endt' , options.enName);
			btn.bind('click', thisCmp.clickBind);
			btn.addClass(b.cls);
			btns.append(btn);
			btns.append('<label for="'+options.stName+b.id+'">'+b.text+'</label>');
		});
		dom.append(btns);
		thisElm.append(dom);
		// 2021.10.25 EasyUI 적용
		$('#'+options.stName).datebox({});
		$('#'+options.enName).datebox({});
		// 2021.12.07 하이픈(-) 자동삽입 이벤트
		bindDateHyphen($('#'+options.stName).datebox('textbox') );
		bindDateHyphen($('#'+options.enName).datebox('textbox') );

		if (options.callback)
			options.callback(thisCmp);
	};
	
	//클릭이벤트 바인딩
	// 2021.10.25 EasyUI 적용
	this.clickBind = function() {
		let code = $(this).data('code');
		let stdt = $(this).data('stdt');
		let endt = $(this).data('endt');
		//let stval = $('#'+stdt).val();
		let stval = null;
		let enval = $('#'+endt).datebox('getValue');
		
		if (enval.length > 0)
			enval = $.commUtil.toDate(enval);
		else
			enval = new Date();
			
		if (code == '0D') {
			stval = new Date();
			enval = new Date();
		}
		else if (code == '7D') {
			stval = $.commUtil.addDays(enval, -7);
		}
		else if (code == '1M') {
			stval = $.commUtil.addDays(enval, -30);
		}
		else if (code == '2M') {
			stval = $.commUtil.addDays(enval, -60);
		}
		else if (code == '3M') {
			stval = $.commUtil.addDays(enval, -90);
		}
		else if (code == '1Y') {
			stval = $.commUtil.addYears(enval, -1);
			stval = $.commUtil.addDays(stval, 1);
		}
		else if (code == '3Y') {
			stval = $.commUtil.addYears(enval, -3);
			stval = $.commUtil.addDays(stval, 1);
		}
		else if (code == '5Y') {
			stval = $.commUtil.addYears(enval, -5);
			stval = $.commUtil.addDays(stval, 1);
		}
		//$('#'+stdt).val( $.commUtil.toFormatDate(stval,'-') );
		//$('#'+endt).val( $.commUtil.toFormatDate(enval,'-') );
		$('#'+stdt).datebox('setValue', $.commUtil.toFormatDate(stval,'-') );
		$('#'+endt).datebox('setValue', $.commUtil.toFormatDate(enval,'-') );
		
	}
	this.create();
	
	return this;
};
