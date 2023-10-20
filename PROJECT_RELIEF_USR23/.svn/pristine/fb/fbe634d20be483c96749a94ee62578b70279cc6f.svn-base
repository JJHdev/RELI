/**
******************************************************************************************
*** 파일명    : comm_survey.js
*** 설명      : 온라인설문지 공통 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2022.01.04              LSH
******************************************************************************************
**/

// 온라인 설문지 포맷 함수
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
let C_FORMAT = {
	// 텍스트박스
	textbox: function(name, placeholder, cls, prefix, postfix) {
		let s = '<input type="text" id="'+name+'" name="'+name+'" class="'+(cls || '')+'" placeholder="'+(placeholder || '')+'" maxlength="100">';
		if (prefix ) s = prefix + s;
		if (postfix) s = s + postfix;
		return s;
	},
	// 콤보박스
	combobox: function(id, name, cls, prefix, postfix) {
		let s = '<select id="'+(id || name)+'"  name="'+name+'" class="'+(cls || '')+'"></select>';
		if (prefix ) s = prefix + s;
		if (postfix) s = s + postfix;
		return s;
	},
	// 라디오박스
	radiobox: function(id, name, value, label, cls) { return '<input type="radio" id="'+(id || '')+'" name="'+name+'" value="'+value+'" class="'+(cls || '')+'"> <label for="'+id+'">'+label+'</label>'; },
	// 체크박스
	checkbox: function(id, name, value, label, cls) { return '<input type="checkbox" id="'+(id || '')+'" name="'+name+'" value="'+value+'" class="'+(cls || '')+'"> <label for="'+id+'">'+label+'</label>'; },
	// 체크박스
	rightcheckbox: function(id, name, value, label, cls) { return '<label for="'+id+'" class="right-check">'+label+'</label> <input type="checkbox" id="'+(id || '')+'" name="'+name+'" value="'+value+'" class="right-check '+(cls || '')+'">'; },
	// 히든박스
	hiddenbox: function(id, name, value) {
		return '<input type="hidden" id="'+id+'" name="'+name+'" value="'+(value || '')+'">';
	},
	// 텍스트기본박스
	textvbox: function(id, name, value) {
		return '<input type="text" id="'+id+'" name="'+name+'" value="'+(value || '')+'" maxlength="100">';
	},
	// 날짜박스
	datebox:  function(name) {
		return [
			'<input type="hidden" id="'+name+'" name="'+name+'" class="app-date"/>',
			C_FORMAT.combobox(false, name+'1', 'app-combo-year' )+' ',
			C_FORMAT.combobox(false, name+'2', 'app-combo-month')+' ',
			C_FORMAT.combobox(false, name+'3', 'app-combo-day'  )+' '
		].join('');
	},
	// 년도기간
	yearbox: function(name1, name2, cls) {
		return [
			C_FORMAT.combobox(false, name1, 'app-combo-year '+(cls || '')),
			'<span class="mark">-</span>',
			C_FORMAT.combobox(false, name2, 'app-combo-year '+(cls || ''))
		].join('');
	},
	// 총 년수
	totyear : function(name) {
		return C_FORMAT.combobox(name, name, 'app-combo-years app-w80', '총 ', ' 년');
	},
	// 년수
	yearcnt : function(name, cls) {
		return C_FORMAT.textbox(name,'년수', cls, false, ' 년');
	},
	// 개비
	cigaavg : function(name) {
		return C_FORMAT.combobox(name, name, 'app-combo-ciga app-w80', '하루 평균 ', ' 개비');
	},
	// 나이
	onlyage : function(name) {
		return C_FORMAT.combobox(name, name, 'app-combo-ages app-w80', '만 ', ' 세');
	},
	// 끊은시기
	stopage : function(name) {
		return C_FORMAT.combobox(name, name, 'app-combo-ages app-w80', '끊은 시기 : 만 ', ' 세');
	},
	// 주횟수
	weekcnt : function(name) {
		return C_FORMAT.combobox(name, name, 'app-combo-week app-w80', '주 ', ' 회');
	},
	// 잔수
	glsscnt : function(name) {
		return C_FORMAT.combobox(name, name, 'app-combo-glss app-w80', '하루 평균 소주 ', ' 잔');
	},
	// 예아니오
	yesno: function(name) {
		return [
			'<input type="radio" name="'+name+'" id="'+name+'Y" value="Y"><label for="'+name+'Y">예</label>',
			'<input type="radio" name="'+name+'" id="'+name+'N" value="N"><label for="'+name+'N">아니오</label>'
		].join('');
	},
	// 해당없음
	none: function(name) {
		return C_FORMAT.checkbox(name, name, 'Y', '해당없음');
	},
	// 세부질환명
	dissbox: function(name) {
		return C_FORMAT.textbox(name, '세부 질환명', 'app-w100');
	}
};

// 온라인 설문지 콤보데이터 함수
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
let C_STORE = {
	getDesc: function(st, en, formatter) {
		var rows  = [];
		var idx   = st;
		while(true) {
			if (idx < en)
				break;
			rows.push({code: idx, text: (formatter ? formatter(idx) : idx)});
			idx--;
		}
		return rows;
	},
	getAsc: function(en, st, formatter) {
		var rows  = [];
		var idx   = st;
		while(true) {
			if (idx > en)
				break;
			rows.push({code: idx, text: (formatter ? formatter(idx) : idx)});
			idx++;
		}
		return rows;
	},
	// 거주년도, 경력년도, 수령년도
	getYears: function() {
		return C_STORE.getAsc(new Date().getFullYear(), 1900, $.formatUtil.toYear);
	},
	// 나이
	getAges: function() {
		// 2022.01.18 DESC -> ASC로 변경
		return C_STORE.getAsc(100, 1);
	},
	// 개비수
	getCiga: function() {
		// 2022.01.18 DESC -> ASC로 변경
		return C_STORE.getAsc(20, 1, function(v) { return v + (v==20 ? '이상' : '');});
	},
	// 잔수
	getGlss: function() {
		// 2022.01.18 DESC -> ASC로 변경
		return C_STORE.getAsc(20, 1, function(v) { return v + (v==20 ? '이상' : '');});
	},
	// 주당횟수
	getWeek: function() {
		// 2022.01.18 DESC -> ASC로 변경
		return C_STORE.getAsc(7, 1);
	},
};

// 온라인 설문지 가족관계 코드
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
let REL_CODES = [
	{code:'F', text:'1.부'},
	{code:'M', text:'2.모'},
	{code:'B', text:'3.형제/자매'},
	{code:'C', text:'4.자녀'}
];

// 온라인 설문지 항목별 포맷함수
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
let ITEM_FORMAT = {
	// 활성화
	doEnable: function(type, elm, items, reverse) {
		let chk = false;
		if (type == 'text')
			chk = $.commUtil.empty(elm.val());
		else {
			chk = !elm.is(':checked');
		}
		// 활성화
		$.each(items, function(j,itm) {
			let c = (reverse ? !chk : chk);
			if (c) {
				if ($('#'+itm).is(':checkbox'))
					$('#'+itm).prop('checked', false);
				else if ($('#'+itm).is(':radio'))
					$('#'+itm).prop('checked', false);
				else
					$('#'+itm).val('');
			}
			$('#'+itm).prop('disabled', c);
		});
	},
	// 해당없음 활성화이벤트
	doNoneEnable: function(elm, items) {
		let chk = elm.is(':checked');
		// 해당없음 체크시 비활성화
		// 해당없음 체크해제시 활성화
		$.each(items, function(j,itm) {
			if (chk) {
				// 입력값 비우기
				if      ($('#'+itm).is(':checkbox')) $('#'+itm).prop('checked', false);
				else if ($('#'+itm).is(':radio'   )) $('#'+itm).prop('checked', false);
				else                                 $('#'+itm).val('');
			}
			$('#'+itm).prop('disabled', chk);
		});
	},
	// 문항정의
	addQstn: function(tbody, no, cn) {
		tbody.append('<tr><th colspan="4" class="left">'+no+'.'+cn+'</th></tr>');
	},
	// 항목정의
	addLine: function(tbody, cn) {
		tbody.append('<tr><td colspan="4">'+cn+'</td></tr>');
	},
	// 텍스트목록
	addRows : function(info, tbody, rows) {
		$.each(rows, function(j,r) {
			let tr = $('<tr></tr>');
			tr.addClass('app-data-tr');
			tr.data('qmngno', info['qstnnMngNo']);
			tr.data('qstmno', info['qesitmNo'  ]);
			tr.data('itemno', info['itemNo'    ]);
			tr.data('qstnty', info['qestnTy'   ]);

			tr.append('<td>'+r['text']+'</td>');
			tr.append('<td colspan="3" data-title="'+r['text']+'">'+r['html']+'</td>');
			tbody.append(tr);
		});
	},
	addCols : function(info, tbody, cols) {
		let tr = $('<tr></tr>');
		tr.addClass('app-data-tr');
		tr.data('qmngno', info['qstnnMngNo']);
		tr.data('qstmno', info['qesitmNo'  ]);
		tr.data('itemno', info['itemNo'    ]);
		tr.data('qstnty', info['qestnTy'   ]);

		$.each(cols, function(j,c) {
			let div = $('<div class="inputWrap"></div>');
			div.append(c['formatter'](j,c));
			tr.append('<td colspan="2"></td>');
			// 4-1, 5-1 항목의 "끊은 시기" 내용 줄바꿈 방지 (강제코딩)
            if(div.html().indexOf("끊은 시기") >= 0){
                div.css("white-space", "nowrap");
            }
			tr.find('td:last').append(div);
		})
		tbody.append(tr);
	},
	addList : function(info, tbody, config) {

		let div = $('<div class="tableWrap type7"></div>');
		div.append('<table></table>');
		div.find('table').append('<thead></thead>');
		div.find('table').append('<tbody></tbody>');
		div.find('thead').append('<tr></tr>');

		$.each(config.columns, function(j,c) {
			div.find('thead > tr').append('<th>'+c['header']+'</th>');
		});
		$.each(config.rows, function(j,row) {
			// 그룹형이면
			if (config.group) {
				$.each(REL_CODES, function(l, rel) {
					let ckey = row['qesitmNo']+'_'+row['itemNo'];
					let tr = $('<tr></tr>');
					tr.addClass('app-data-tr');
					tr.data('qmngno', row['qstnnMngNo']);
					tr.data('qstmno', row['qesitmNo'  ]);
					tr.data('itemno', row['itemNo'    ]);
					tr.data('qstnty', row['qestnTy'   ]);
					if (l == 0) {
						tr.append('<th rowspan="4">'+row['text']+'</th>');
						// 기타항목
						if (row['qestnTy'] == 'A081')
							tr.find('th:last').append(['<br>(',C_FORMAT.textvbox(ckey+'_ansCn1',ckey+'_ansCn1', row['text']),')'].join(''));
						else
							tr.find('th:last').append(C_FORMAT.hiddenbox(ckey+'_ansCn1', ckey+'_ansCn1', row['text']));
						tr.append('<td rowspan="4"></td>');
						tr.find('td:last').append(C_FORMAT.none(ckey+'_ansCn6'));
					}
					tr.append('<td></td>');
					tr.find('td:last').append(C_FORMAT.rightcheckbox(ckey+'_ansRel'+rel['code'], ckey+'_ansRel'+rel['code'],'Y',rel['text']));
					tr.append('<td></td>');
					tr.find('td:last').append(C_FORMAT.onlyage(ckey+'_ansAge'+rel['code'])+' ');
					tr.find('td:last').append(C_FORMAT.checkbox(ckey+'_ansNon'+rel['code'], ckey+'_ansNon'+rel['code'],'Y','모름'));
					div.find('tbody').append(tr);
				});
			}
			// 일반형이면
			else {
				let tr = $('<tr></tr>');
				tr.addClass('app-data-tr');
				tr.data('qmngno', row['qstnnMngNo']);
				tr.data('qstmno', row['qesitmNo'  ]);
				tr.data('itemno', row['itemNo'    ]);
				tr.data('qstnty', row['qestnTy'   ]);
				$.each(config.columns, function(k,col) {
					let ckey = row['qesitmNo']+'_'+row['itemNo']+'_'+col.name;
					if (k == 0) {
						tr.append('<th>'+row['qesitmCn']+'</th>');
						tr.find('th:last').append(C_FORMAT.hiddenbox(ckey, ckey, row['qesitmCn']));
					}
					else {
						tr.append('<td style="white-space:nowrap;">'+col.formatter(ckey)+'</td>');
					}
				});
				div.find('tbody').append(tr);
			}
		});
		let htr = $('<tr></tr>');
		htr.addClass('app-data-tr');
		htr.data('qmngno', info['qstnnMngNo']);
		htr.data('qstmno', info['qesitmNo'  ]);
		htr.data('itemno', info['itemNo'    ]);
		htr.data('qstnty', info['qestnTy'   ]);
		htr.append('<td colspan="4"></td>');
		// 문항정보 등록을 위해 추가함
		let hkey = info['qesitmNo']+'_'+info['itemNo']+'_ansCn1';
		htr.find('td').append(C_FORMAT.hiddenbox(hkey, hkey, ''));
		htr.find('td').append(div);
		tbody.append(htr);
	}
};

// 총거주기간 계산
//--------------------------------------------------------//
function doCalculate() {
	let syear = $('.app-calc-year').eq(0).val();
	let eyear = $('.app-calc-year').eq(1).val();
	$('.app-total-year').val('0');
	if ($.commUtil.empty(syear) ||
	    $.commUtil.empty(eyear)) {
		return;
	}
	let years = parseInt(eyear)-parseInt(syear)+1;
	if (years <= 0) {
		$.commMsg.alert('종료년도를 시작년도 이후로 선택하세요.', function() {
			$('.app-calc-year').eq(1).focus();
		});
		return false;
	}
	// 총년수 정의
	$('.app-total-year').val(years);
}

// 항목별 이벤트 및 콤보값 정의
//--------------------------------------------------------//
function doInitItems( form, readonlys, disables ) {
	// 읽기전용항목 설정
	$.each(readonlys, function(i,s) {
		form.find('[name="'+s+'"]').prop('readonly', true);
	});
	// 활성/비활성 이벤트 처리
	$.each(disables, function(i,d) {
		let key   = d['key'  ];
		let dkey  = d['dkey' ];
		let typ   = d['type' ];
		let items = d['items'];
		if (typ == 'text') {
			// 2022.01.14 blur가 아닌 keyup으로 이벤트변경
			$('#'+key).bind('keyup', function() {
				ITEM_FORMAT.doEnable(typ, $(this), items);
			});
		}
		else if (typ == 'checkbox') {
			$('#'+key).bind('click', function() {
				ITEM_FORMAT.doEnable(typ, $(this), items);
			});
		}
		// 2022.01.19 해당없음 체크박스 추가
		else if (typ == 'checknone') {
			$('#'+key).bind('click', function() {
				ITEM_FORMAT.doNoneEnable($(this), items);
			});
		}
		else {
			$('#'+key).bind('click', function() {
				ITEM_FORMAT.doEnable(typ, $(this), items);
			});
			$('#'+dkey).bind('click', function() {
				ITEM_FORMAT.doEnable(typ, $(this), items, true);
			});
		}
		if (typ == 'checknone')
			ITEM_FORMAT.doNoneEnable($(this), items);
		else
			ITEM_FORMAT.doEnable(typ, $(this), items);
	});

	$('.app-mask-num').each(function() {
		$(this).inputmask("numeric");
	});
	$('.app-combo-year').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'년도'}, rows: C_STORE.getYears()});
	});
	$('.app-combo-month').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'월'}, rows: STORE.getMonths($.formatUtil.toMonth)});
	});
	$('.app-combo-day').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'일'}, rows: STORE.getDays($.formatUtil.toDate)});
	});
	$('.app-combo-years').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'년수'}, rows: C_STORE.getAges()});
	});
	$('.app-combo-ages').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'나이'}, rows: C_STORE.getAges()});
	});
	$('.app-combo-ciga').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'개비'}, rows: C_STORE.getCiga()});
	});
	$('.app-combo-glss').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'잔수'}, rows: C_STORE.getGlss()});
	});
	$('.app-combo-week').each(function() {
		$(this).appComboBox({ type: 'static', init: {code:'',text:'횟수'}, rows: C_STORE.getWeek()});
	});
	$('.app-calc-year').each(function() {
		$(this).bind('change', doCalculate);
	});
}

// 폼검증룰 저의
//--------------------------------------------------------//
function doInitValidate( form, itemRules ) {
	let P_VALD = {
        debug         : false,
        onsubmit      : false,
        onfocusout    : false,
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement,
        rules         : {},
        messages      : {}
	};
	// 검증룰 정렬 및 정의
	let ruleKeys = Object.keys( itemRules );
	ruleKeys.sort();
	$.each(ruleKeys, function(i, key) {
		P_VALD.rules[key]    = {};
		P_VALD.messages[key] = {};

		let rule      = itemRules[key];
		let required  = rule.required;
		let afterYear = rule.afterYear;
		if ($.type(required) === 'string') {
			$.extend(P_VALD.rules   [key], {required: true}, true);
			$.extend(P_VALD.messages[key], {required: required}, true);
		}
		else {
			$.extend(P_VALD.rules   [key], {required: required['fn']}, true);
			$.extend(P_VALD.messages[key], {required: required['message']}, true);
		}
		if (afterYear) {
			$.extend(P_VALD.rules   [key], {afterYear: afterYear['param']}, true);
			$.extend(P_VALD.messages[key], {afterYear: afterYear['message']}, true);
		}
	});
    form.validate(P_VALD);
}

// 온라인설문지 설정정보 가져오기 (초기실행)
// args.params   : 설정정보 검색조건 {qstnnMngNo}
// args.form     : 폼 객체
// args.table    : 테이블 객체
// args.callback : 설정완료 후 콜백함수 (선택)
//--------------------------------------------------------//
function doConfigLoad( args ) {
	$.ajaxUtil.ajaxLoad(
		getUrl('/com/cmm/getListSurvey.do'), args.params,
		function(data) {
			doDraw( args.form, args.table, data);

			// 2022.01.20 콜백함수가 있을 경우
			if (args.callback) {
				args.callback();
			}
		}
	);
}

// 온라인설문지 항목설정
//--------------------------------------------------------//
function doDraw( form, dom, qstns ) {

	// 검증룰
	let P_RULES     = {};
	// 읽기전용항목
	let P_READONLYS = [];
	// 비활성항목
	let P_DISABLES  = [];
	// 테이블 BODY
	let tbody = $('<tbody></tbody>');
	// 문항별 LOOP
	$.each(qstns, function(i,qstn) {

		let qtype = qstn['qestnTy'];
		let qkey  = qstn['qesitmNo']+'_'+qstn['itemNo']
		let qsno  = qstn['qesitmNo'];
		let qtit  = qsno + '번 문항';

		// 문항 정의
		ITEM_FORMAT.addQstn(tbody, qstn['qesitmNo'], qstn['qesitmCn']);

		// A020 (직업경력) 타입인 경우 문항정보 등록을 위해 추가함
		if (qtype == 'A020') {
			// 문항정보 등록을 위해 추가함
			let hkey = qstn['qesitmNo']+'_'+qstn['itemNo']+'_ansCn1';
			tbody.find('th:last').append(C_FORMAT.hiddenbox(hkey, hkey, ''));
		}
		// 1.거주기간
		if (qtype == 'A010') {
			ITEM_FORMAT.addRows(qstn, tbody, [
				{text: '거주기간'   , html: C_FORMAT.yearbox(qkey+'_ansCn1', qkey+'_ansCn2', 'app-calc-year') },
				{text: '총 거주기간', html: C_FORMAT.yearcnt(qkey+'_ansCn3', 'app-total-year') }
			]);
			P_RULES[qkey+'_ansCn1'] = {required: qtit+' 시작년도를 선택해 주세요.'};
			P_RULES[qkey+'_ansCn2'] = {required: qtit+' 종료년도를 선택해 주세요.',afterYear: {message: qtit+' 종료년도를 시작년도 이후로 선택하세요.', param: '#'+qkey+'_ansCn1'}};
			P_READONLYS.push(qkey+'_ansCn3');
		}
		// 6.질병/병원명
		else if (qtype == 'A060') {
			ITEM_FORMAT.addRows(qstn, tbody, [
				{text: '신체증상(질병명 등)', html: C_FORMAT.textbox(qkey+'_ansCn1', '신체증상(질병명)을 입력해 주세요. (예: 당뇨병, 고혈압, 피부질환 등)', 'w100') },
				{text: '주 내원 병원'       , html: C_FORMAT.textbox(qkey+'_ansCn2', '병원명을 입력해주세요. (예: 가나다요양병원)', 'w100') }
			]);
			P_RULES[qkey+'_ansCn1'] = {required: qtit+' 신체증상(질병명)을 입력해 주세요.'};
			P_RULES[qkey+'_ansCn2'] = {required: qtit+' 주 내원 병원명을 입력해 주세요.'};
		}
		// 9.배상금
		else if (qtype == 'A090') {
			ITEM_FORMAT.addCols(qstn, tbody, [
				{ key: qkey, formatter: function(i,c) { return C_FORMAT.radiobox(c['key']+'_ansCn1Y', c['key']+'_ansCn1', 'Y', '예'); } },
				{ key: qkey, formatter: function(i,c) { return C_FORMAT.radiobox(c['key']+'_ansCn1N', c['key']+'_ansCn1', 'N', '아니오'); } }
			]);
			P_RULES[qkey+'_ansCn1'] = {required: qtit+'의 예/아니오를 선택해 주세요.'};

			// 부가문항 정의
			qtit = qstn['qesitmNo']+'-1 번 문항';
			ITEM_FORMAT.addQstn(tbody, qstn['qesitmNo']+'-1', '보상 또는 배상금 수령 내역 (9번에 \'예\'를 체크 하셨을 경우만 해당)');
			ITEM_FORMAT.addRows(qstn, tbody, [
				{text: '수령일자'             , html: C_FORMAT.datebox(qkey+'_ansCn2') },
				{text: '수령금액'             , html: C_FORMAT.textbox(qkey+'_ansCn3', '금액을 입력해 주세요.', 'w100') },
				{text: '지급한 자 또는 지급처', html: C_FORMAT.textbox(qkey+'_ansCn4', '지급처를 입력해 주세요.', 'w100') },
			]);

			P_RULES[qkey+'_ansCn21'] = {required: {message: qtit+' 수령일자를 선택해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
			P_RULES[qkey+'_ansCn22'] = {required: {message: qtit+' 수령일자를 선택해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
			P_RULES[qkey+'_ansCn23'] = {required: {message: qtit+' 수령일자를 선택해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
			P_RULES[qkey+'_ansCn3' ] = {required: {message: qtit+' 수령금액을 입력해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
			P_RULES[qkey+'_ansCn4' ] = {required: {message: qtit+' 지급처를 입력해 주세요.',   fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
			P_DISABLES.push({
				type: 'radio',
				key:  qkey+'_ansCn1Y',
				dkey: qkey+'_ansCn1N',
				items:[
					qkey+'_ansCn21',
					qkey+'_ansCn22',
					qkey+'_ansCn23',
					qkey+'_ansCn3',
					qkey+'_ansCn4'
				]
			});
		}
		// 3.농약/4.흡엽/5.음주
		else if ($.inArray(qtype, ['A030','A040','A050']) >= 0) {
			ITEM_FORMAT.addCols(qstn, tbody, [
				{ 	key: qkey, type: qtype, formatter: function(i,c) {
						let t = c['key'];
						let a = [];
						a.push(C_FORMAT.radiobox(t+'_ansCn1Y', t+'_ansCn1', 'Y', '예'));
						a.push('(');
						a.push(C_FORMAT.totyear(t+'_ansCn2'));
						if (c['type'] == 'A040') {
							a.push(', ');
							a.push(C_FORMAT.cigaavg(t+'_ansCn3'));
						}
						else if (c['type'] == 'A050') {
							a.push(', ');
							a.push(C_FORMAT.weekcnt(t+'_ansCn3'));
							a.push(', ');
							a.push(C_FORMAT.glsscnt(t+'_ansCn4'));
						}
						a.push(')');
						return a.join('');
					}
				},
				{ 	key: qkey, formatter: function(i,c) { return C_FORMAT.radiobox(c['key']+'_ansCn1N', c['key']+'_ansCn1', 'N', '아니오'); } }
			]);

			let p1Y = {type:'radio', key: qkey+'_ansCn1Y', dkey: qkey+'_ansCn1N', items:[]};
			let p2N = {type:'radio', key: qkey+'_ansCn5N', dkey: qkey+'_ansCn5Y', items:[]};
			P_RULES[qkey+'_ansCn1'] = {required: qtit+' 예/아니오를 선택해 주세요.'};
			P_RULES[qkey+'_ansCn2'] = {required: {message: qtit+' 총 년수를 선택해 주세요.',fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
			p1Y['items'].push(qkey+'_ansCn2');
			if (qtype == 'A040') {
				P_RULES[qkey+'_ansCn3'] = {required: {message: qtit+' 하루 평균 흡연 개비수를 선택해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
				p1Y['items'].push(qkey+'_ansCn3');
			}
			else if (qtype == 'A050') {
				P_RULES[qkey+'_ansCn3'] = {required: {message: qtit+' 주당 음주 횟수를 선택해 주세요.'     , fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
				P_RULES[qkey+'_ansCn4'] = {required: {message: qtit+' 하루 평균 소주 잔수를 선택해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
				p1Y['items'].push(qkey+'_ansCn3');
				p1Y['items'].push(qkey+'_ansCn4');
			}

			if ($.inArray(qtype, ['A040','A050']) >= 0) {
				qtit = qstn['qesitmNo']+'-1 번 문항';
				// 부가문항 정의
				ITEM_FORMAT.addQstn(tbody, qstn['qesitmNo']+'-1', '현재 '+(qtype == 'A040' ? '흡연' : '음주')+' 여부 '+ '('+qstn['qesitmNo']+'번에 \'예\'를 체크 하셨을 경우만 해당)');
				ITEM_FORMAT.addCols(qstn, tbody, [
					{ 	key: qkey, formatter: function(i,c) { return  C_FORMAT.radiobox(c['key']+'_ansCn5Y', c['key']+'_ansCn5', 'Y', '예'); } },
					{ 	key: qkey, formatter: function(i,c) { return [C_FORMAT.radiobox(c['key']+'_ansCn5N', c['key']+'_ansCn5', 'N', '아니오'),'(',C_FORMAT.stopage(c['key']+'_ansCn6'),')'].join('');}
					}
				]);
				P_RULES[qkey+'_ansCn5'] = {required: {message: qtit+' 예/아니오를 선택해 주세요.', fn: function() { return $('#'+qkey+'_ansCn1Y').is(":checked");}}};
				P_RULES[qkey+'_ansCn6'] = {required: {message: qtit+' 끊은시기를 선택해 주세요.',  fn: function() { return $('#'+qkey+'_ansCn5N').is(":checked");}}};
				p1Y['items'].push(qkey+'_ansCn5Y');
				p1Y['items'].push(qkey+'_ansCn5N');
				p2N['items'].push(qkey+'_ansCn6');
			}
			if (p1Y.items.length > 0) P_DISABLES.push(p1Y);
			if (p2N.items.length > 0) P_DISABLES.push(p2N);
		}
		// 2. 직업경력 (하위항목존재)
		else if (qtype == 'A020') {
			let items = qstn['items'];
			$.each(items, function(i, item) {
				let ikey = item['qesitmNo']+'_'+item['itemNo'];
				let itit = item['qesitmCn'];
				item['text'] = item['qesitmCn'];
				item['html'] = [
					C_FORMAT.textbox(ikey+'_ansCn1'),
					'<span class="mark">,</span>',
					C_FORMAT.yearbox(ikey+'_ansCn2',ikey+'_ansCn3')
				].join('');

				P_RULES[ikey+'_ansCn2'] = {required: {message: qtit+' '+itit+' 시작년도를 선택해 주세요.', fn: function() { return !$.commUtil.empty($('#'+ikey+'_ansCn1').val()); }}};
				P_RULES[ikey+'_ansCn3'] = {required: {message: qtit+' '+itit+' 종료년도를 선택해 주세요.', fn: function() { return !$.commUtil.empty($('#'+ikey+'_ansCn1').val()); }},
					afterYear: {message: qtit+' '+itit+' 종료년도를 시작년도 이후로 선택하세요.', param: '#'+ikey+'_ansCn2'}
				};
				P_DISABLES.push({
					type: 'text',
					key:  ikey+'_ansCn1',
					items:[
						ikey+'_ansCn2',
						ikey+'_ansCn3'
					]
				});
			});
			ITEM_FORMAT.addRows(qstn, tbody, items);
		}
		// 7. 본인질병 (하위항목존재)
		else if (qtype == 'A070') {
			let items = qstn['items'];
			$.each(items, function(i, item) {
				item['code'] = item['itemNo'];
				item['text'] = item['qesitmCn'];
				item['ckey'] = item['qesitmNo']+'_'+item['itemNo'];
				let ikey = item['qesitmNo']+'_'+item['itemNo'];
				let itit = item['qesitmCn'];
				P_RULES[ikey+'_ansCn2'] = {required: {message: qtit+' '+itit+' 병원진단여부를 선택해 주세요.',fn: function() { return !$('#'+ikey+'_ansCn6').is(":checked");}}};
				P_RULES[ikey+'_ansCn3'] = {required: {message: qtit+' '+itit+' 최초발병시기를 선택해 주세요.',fn: function() { return $('#'+ikey+'_ansCn2Y').is(":checked");}}};
				P_DISABLES.push({
					type: 'radio',
					key:  ikey+'_ansCn2Y',
					dkey: ikey+'_ansCn2N',
					items:[
						ikey+'_ansCn3',
						ikey+'_ansCn4'
					]
				});
				// 해당없음 활성화이벤트 추가
				P_DISABLES.push({
					type: 'checknone',
					key:  ikey+'_ansCn6',
					items:[
						ikey+'_ansCn2Y',
						ikey+'_ansCn2N',
						ikey+'_ansCn3',
						ikey+'_ansCn4'
					]
				});
			});
			ITEM_FORMAT.addList(qstn, tbody, {
				type:    qtype,
				rows:    items,
				columns: [
					{header: '질환명'       , name: 'ansCn1', field:'qesitmCn'},
					{header: '해당 여부'    , name: 'ansCn6', formatter: C_FORMAT.none},
					{header: '병원 진단여부', name: 'ansCn2', formatter: C_FORMAT.yesno},
					{header: '최초 발병시기', name: 'ansCn3', formatter: C_FORMAT.onlyage},
					{header: '세부 질환명'  , name: 'ansCn4', formatter: C_FORMAT.dissbox}
				],
			});
		}
		// 8. 가족질병 (하위항목존재)
		else if (qtype == 'A080') {
			let items = qstn['items'];
			$.each(items, function(i, item) {
				item['code'] = item['itemNo'];
				item['text'] = item['qesitmCn'];
				item['ckey'] = item['qesitmNo']+'_'+item['itemNo'];
				let ikey = item['qesitmNo']+'_'+item['itemNo'];
				let itit = item['qesitmCn'];
				let itms = [];
				$.each(REL_CODES, function(l, rel) {

					itms.push(ikey+'_ansRel'+rel['code']);
					itms.push(ikey+'_ansAge'+rel['code']);
					itms.push(ikey+'_ansNon'+rel['code']);

					P_RULES[ikey+'_ansRel'+rel['code']] = {
						required: {
							message: qtit+' '+itit+' 관계를 선택해 주세요.',
							fn: function() {
								let c = !$('#'+ikey+'_ansCn6').is(":checked");
								$('input[id^="'+ikey+'_ansRel"]').each(function() {
									if ($(this).is(':checked')) {
										c = false;
										return false;
									}
								});
								return c;
							}
						}
					};
					P_RULES[ikey+'_ansAge'+rel['code']] = {
						required: {
							message: qtit+' '+itit+' '+rel['text']+' 최초진단시기를 선택해 주세요.',
							fn: function() {
								return $('#'+ikey+'_ansRel'+rel['code']).is(":checked") && !$('#'+ikey+'_ansNon'+rel['code']).is(":checked");
							}
						}
					};
					P_DISABLES.push({
						type:  'checkbox',
						key:   ikey+'_ansRel'+rel['code'],
						items:[
							ikey+'_ansAge'+rel['code'],
							ikey+'_ansNon'+rel['code']
						]
					});
				});

				// 해당없음 활성화이벤트 추가
				P_DISABLES.push({
					type:  'checknone',
					key:   ikey+'_ansCn6',
					items: itms
				});

			});
			ITEM_FORMAT.addList(qstn, tbody, {
				type:    qtype,
				rows:    items,
				group:   true,
				columns: [
					{header: '질환명'},
					{header: '해당 여부'},
					{header: '본인과의 관계'},
					{header: '최초 진단시기'}
				],
			});
		}
	});
	ITEM_FORMAT.addLine(tbody, [
		'※ ｢환경오염피해 배상책임 및 구제에 관한 법률｣ 제34조에 따라,',
		'환경오염피해에 대하여 손해배상을 받을 수 있거나 ',
		'다른 법령에 따른 구제를 받은 경우에는 ',
		'그 금액의 한도에서 구제급여를 지급하지 않습니다.',
		'만약 사실과 다른 내용을 기재하여, ',
		'환경오염피해 구제급여를 부당하게 지급받은 경우에는 ',
		'｢환경오염피해 배상책임 및 구제에 관한 법률｣ 제37조제4항에 따라 ',
		'부당지급 구제급여 액수 2배를 납입해야 하는 등의 ',
		'불이익 처분을 받게 되오니 사실대로 기재해야 합니다.'
	].join(''));

	// 항목HTML 추가
	dom.append(tbody);

	// 항목별 이벤트 및 콤보값 정의
	doInitItems( form, P_READONLYS, P_DISABLES );

	// 검증룰 정렬 및 정의
	doInitValidate( form, P_RULES);
}

// 저장데이터 구조생성
//--------------------------------------------------------//
function doSaveRows(form) {
	// 저장데이터
	let saveData = {
		qstnnMngNo: '',
		signCn:     '',
		items:      []
	};
	// 폼데이터
	let formData = form.serializeObject();
	// 관리정보 키배열
	let dataKeys = ['qstnnMngNo','signCn','rspnsMngNo','mode'];

	let rkeyMap  = {};
	$.each(formData, function(key, value) {
		// 관리정보인 경우
		if ($.inArray(key, dataKeys) >= 0) {
			saveData[key] = value;
			return true;
		}
		let arr = key.split('_');
		let obj = {};
		let opk = '';
		if (arr.length > 2) {
			obj['qesitmNo'] = arr[0];
			obj['itemNo'  ] = arr[1];
			opk = arr[0]+'_'+arr[1];
			obj[arr[2]] = value;
		}
		if (opk in rkeyMap) {
			$.extend(rkeyMap[opk], obj);
		}
		else {
			rkeyMap[opk] = obj;
			saveData['items'].push(rkeyMap[opk]);
		}
	});
	// 가족질환/배상금일자 데이터 병합
	$.each(saveData['items'], function(i,r) {
		// 가족질환
		if (r['qesitmNo'] == '8') {
			//ansAge / ansNon / ansRel
			$.each(REL_CODES, function(l, rel) {
				r['ansCn'+(l+2)] = [
					$.commUtil.nvl(r['ansRel'+rel['code']]),
					$.commUtil.nvl(r['ansAge'+rel['code']]),
					$.commUtil.nvl(r['ansNon'+rel['code']])
				].join(',');
			});
		}
		// 9.배상금
		else if (r['qesitmNo'] == '9') {
			// 년도가 있으면
			if (!$.commUtil.empty(r['ansCn21'])) {
				r['ansCn2'] = [
					$.commUtil.nvl(r['ansCn21']),
					$.commUtil.nvl(r['ansCn22']),
					$.commUtil.nvl(r['ansCn23'])
				].join('');
			}
		}
	});
	return saveData;
}

// 2022.01.21 이전설문지 정보 로드
// args.form    폼객체 $('#surveyForm')
// args.params  조회조건 {rspnsMngNo: ''}
// args.url     조회URL
//--------------------------------------------------------//
function doDataLoad( args ) {

	// 답변정보 가져오기
	const result = $.ajaxUtil.ajaxDefault(args.url, args.params);

	if (!result)
		return false;

	if (!result.Data)
		return false;

	// 답변데이터
	let P_SURVEY = result.Data;
	// 입력폼객체
	let P_FORM   = args.form;

	$('#p_rgtrNm' ).html(P_SURVEY['rgtrNm']);
	$('#p_signNm' ).html(P_SURVEY['rgtrNm']);
	$('#p_regDate').html(P_SURVEY['regDate']);

	// 폼값 맵핑
    for (var p in P_SURVEY) {
        var key = p;
        var val = P_SURVEY[p];
        var obj = P_FORM.find('[name="'+key+'"]');
        if (!obj || !obj.length)
            continue;
		if ($.commUtil.empty(val))
			continue;
        if (obj.is("input:checkbox")) {
        	obj.each(function() {
        		if ($(this).val() == val) {
					$(this).trigger('click');
				}
        	});
        }
        else if (obj.is("input:radio")) {
        	obj.each(function() {
        		if ($(this).val() == val) {
					$(this).prop("checked", true).trigger('click');
				}
        	});
        }
        else if (obj.is("input:text")) {
            obj.val(val);
			obj.trigger('keyup');
        }
        else {
            obj.val(val);
        }
    }
	$.popupMsg.alert('이전에 작성한 온라인 설문지 정보를 로드했습니다.');

    return false;
}
