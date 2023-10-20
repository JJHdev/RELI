/**
******************************************************************************************
*** 파일명    : comm_board.js
*** 설명      : 게시판용 공통 컴포넌트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-10-29              LSH
******************************************************************************************
**/

//===========================================================================//
// 게시판 페이징 목록 컨트롤
//===========================================================================//
$.fn.appBoard = function ( args ) {
	
	var options = $.extend(true, {
		// 일반(GEN), FAQ
		type: 'GEN',
		//목록 검색 AJAX URL
		url: false,
		//페이징 영역
		paging: false,
		//모바일 표시여부
		mobile: true,
		//칼럼 정의
		columns: [],
		//FAQ 칼럼명칭 정의
		faqField: {
			// 질문칼럼ID
			question: '',
			// 답변칼럼ID
			answer:   ''
		},
		//기본검색 조건
		params: {},
		//검색데이타
		rows: false,
		//검색페이징객체
		pagination: {
			page: 1,
			total: 0,
			pages: 0,
			display: 10
		},
		//데이터가 없을경우 메세지
		emptyText: '검색된 정보가 없습니다.',
		//검색후 처리함수
		callback: false,
		//행선택 이벤트
		select: false
	}, args);
	
	//현재객체
	var thisCmp = this;
	//목록테이블객체
	var thisElm = $(this);

	this.create = function() {
		
		thisElm.html('');
		
		let table = $('<table></table>');
		
		if (options.type == 'FAQ') {
			// COLGROUP 생성
			let cgroup = $('<colgroup></colgroup>');
			cgroup.append('<col style="width:10%;">');
			cgroup.append('<col style="width:90%;">');
			table.append(cgroup);
		}
		else {
			// 제목라인 생성
			table.append(thisCmp.createHeader());
		}
		table.append('<tbody></tbody>');

		thisElm.append(table);
	};

	//제목행 생성
	this.createHeader = function() {
		let thead = $('<thead></thead>');
		let tr = $('<tr></tr>');
		$.each(options.columns, function(i,c) {
			tr.append('<th>'+c.label+'</th>');
		});
		thead.append(tr);
		return thead;
	};
	 
	//데이터행 생성
	this.loadData = function(json, rowidx) {
		var row = $('<tr data-value='+json.nttNo+'></tr>');
		$.each(options.columns, function(colidx, c) {
			var value = json[c.name];
			var col = $('<td></td>');
			if (c.colspan) col.prop('colspan', c.colspan);
			if (c.rowspan) col.prop('rowspan', c.rowspan);
			if (c.width)   col.prop('width',c.width);
			if (c.align)   col.addClass(c.align);
			if (c.formatter)
				value = c.formatter(value, json, rowidx, colidx,options.pagination);
			if (c.mobile)
				value = '<span class="mobile">'
				      + c.label
                      + '</span>'
				      + value;  
			col.append(value);
			// 열포맷이 필요한 경우			
			if (c.formatCell)
				c.formatCell(col, json[c.name], json);

			// 행포맷이 필요한 경우			
			if (c.formatRow)
				c.formatRow(row, json);

			row.append(col);
		});
		//행선택이벤트
		if (options.select) {
			row.bind('click', function() {
				options.select(json, rowidx);
			});
			row.addClass('app-pointer');
		}
		thisElm.find('tbody').append(row);
	};
	
	//데이터 재생성
	this.loadRows = function() {
		//초기화
		thisElm.find('tbody').html('');
		if (options.rows.length == 0) {
			thisElm.find('tbody').append('<tr><td colspan="'+options.columns.length+'">'+options.emptyText+'</td></tr>');
			return;
		}
		$.each(options.rows, function(i,o) {
			//데이터행 로드
			thisCmp.loadData(o, i);
		});
		//데이터 로드후 CALLBACK
		if (options.callback)
			options.callback(thisCmp, thisElm, options.rows, options.pagination);
	}

	
	//FAQ 데이터 재생성
	this.loadFAQRows = function() {

		//초기화
		thisElm.find('tbody').html('');
		if (options.rows.length == 0) {
			thisElm.find('tbody').append('<tr><td colspan="2">'+options.emptyText+'</td></tr>');
			return;
		}
		
		$.each(options.rows, function(i,o) {
			let q = $('<tr class="question"></tr>');			
			let a = $('<tr class="answer"></tr>');
			
			q.append('<td class="align_c"><span class="qMark">Q.</span></td>');
			q.append('<td class="app-quest left"></td>');
			a.append('<td class="align_c"><span class="aMark">A.</span></td>');
			a.append('<td class="app-answer left"></td>');
			
			q.find('.app-quest' ).append(o[options.faqField.question]);
			a.find('.app-answer').append(o[options.faqField.answer]);
			
			thisElm.find('tbody').append(q);
			thisElm.find('tbody').append(a);
		});
		
		thisElm.find("tr.answer").slideUp(0)
		thisElm.find("tr.question").on("click",function(){
			$(this).next("tr.answer").slideToggle(0)
		})
		
		//데이터 로드후 CALLBACK
		if (options.callback)
			options.callback(thisCmp, thisElm, options.rows);
	}

	//데이터 로드
	this.load = function( params ) {
		
		$.extend(options.params, params);
		let p = options.params; 

		//확인검색시 페이지 리셋처리
		if (!p.page) {
			p.page = 1;
		}
		options.pagination['page'] = p.page;

        $.ajaxUtil.ajaxLoad(
            options.url, 
			p,
            function(data) {
                if (data) {
					if (data.page) {
						options.pagination = $.extend(options.pagination, {
							total: data.total,
							pages: data.pages,
							limit: data.limit
						});
						thisCmp.createPaging();
					}
					if (!data)
						return;
					if (!data.rows)
						return;
					options.rows = data.rows;
					
					//데이터 재생성
					if (options.type == 'FAQ')
						thisCmp.loadFAQRows();
					else
						thisCmp.loadRows();
                }
            }
        );
	};
	
	//페이징 생성
	this.createPaging = function() {

		if (!options.paging)
			return;
		
		var p = options.pagination;
		var o = $(options.paging);
		o.html('');
		
		let pc = $('<ul class="pc"></ul>');    // PC
		let mb = $('<ul class="mobile"></ul>');// 모바일
		
		let pprev  = $('<li class="prev"><a class="app-prev" href="#void"></a></li>');
		let pnext  = $('<li class="next"><a class="app-next" href="#void"></a></li>');
		let pfirst = $('<li class="first"><a class="app-first" href="#void"></a></li>');
		let plast  = $('<li class="last"><a class="app-last" href="#void"></a></li>');
		
		pfirst.find('a').data('page', '1');
		plast.find('a').data('page', p.pages);

		var i = 1, base = Math.floor((p.page - 1) / p.display) * p.display;
		if (p.page > 1) {
			pprev.find('a').data('page', (p.page-1));
		}
		if (p.page < p.pages) {
			pnext.find('a').data('page', (p.page+1));
		}
		pc.append(pfirst);
		pc.append(pprev);
		mb.append(pprev.clone(true));
		mb.append(pnext.clone(true));
		mb.append('<li><select></select></li>');
		
		for (i = 1; i <= p.pages; i++) {

			var pageNo = base + i;
			if (i > p.display || pageNo > p.pages)
				break;
			let no = $('<li><a class="app-page" href="#void"></a></li>');
			no.find('a').append(pageNo);
			no.find('a').data('page', pageNo);
			
			mb.find('select').append('<option>'+pageNo+'</option>');
			if (p.page == pageNo)
				no.addClass('active');
			pc.append(no);
		}
		pc.append(pnext);
		pc.append(plast);
		mb.find('select').val(p.page);
		
		pc.find('a').bind('click', function() {
			var page = $(this).data('page');
			if (page)
				thisCmp.load({page: page});
		});
		pc.find('select').bind('change', function() {
			var page = $(this).val();
			if (page)
				thisCmp.load({page: page});
		});
		let wrap = $('<div class="pageWrap"></div>');
		wrap.append(pc);
		if (options.mobile)
			wrap.append(mb);
		o.append(wrap);
	};
	
	//테이블생성
	this.create();
	
	return this;
};
