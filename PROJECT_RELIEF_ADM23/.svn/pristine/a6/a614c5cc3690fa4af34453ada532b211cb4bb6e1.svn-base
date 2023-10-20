/**
******************************************************************************************
***	명칭: modalReliefForm.js
***	설명: 종합현황 - 개인별 상세기록카드 조회팝업
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------

******************************************************************************************
**/

P_POPUP_FORMAT = {
	toDthNm: function(v) {
		if (v)
			return $.formatUtil.toDthNm(v);
		return '-';
	},
	toRcognNm: function(v) {
		if (v)
			return $.formatUtil.toRcognNm(v);
		return '-';
	},
	toKindCnt: function(v) {
		if (v)
			return $.formatUtil.toKindCnt(v);
		return '-';
	},
	toGrdNm: function(v) {
		if (v)
			return $.formatUtil.toGrdNm(v);
		return '-';
	},
	toScopeNm: function(v) {
		if (v)
			return $.formatUtil.toScopeNm(v);
		return '-';
	},
	toYearCnt: function(v) {
		if (v)
			return $.formatUtil.toYearCnt(v);
		return '-';
	},
};

P_POPUP_SECTIONS = {
    // 피해자 개요
    //--------------------------------------------------------//
	SUFRER  : {
		key   : 'appSufrerInfo',
		title : '피해자 개요',
		elm   : false,
		form  : false,
		doInit: function( form, params ) {
			
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1',
				titleTag:  'h3',
				tailSpace: true,				
				columns: [
					{name: 'sufrerNm'       , label: '피해자명'},
					{name: 'sufrerSxdstNm'  , label: '성별'},
					{name: 'sufrerAge'      , label: '나이'},
					{name: 'dthYn'          , label: '생존여부', formatter: P_POPUP_FORMAT.toDthNm},
					{name: 'bizAreaNm'      , label: '피해지역'},
					{name: 'dmgeRcognYn'    , label: '인정여부', formatter: P_POPUP_FORMAT.toRcognNm},
					{name: 'agentDesc'      , label: '대리신청'}
				]
			});
			
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getIdntfcInfo.do'), 
				params,
	            function(result) {
	                var data = result.Data;
	                if (data)
						p.elm.loadData([data]);
				}
			);
		}
	},
    // 피해구제 신청 및 인정현황(목록)
    //--------------------------------------------------------//
	RELIEF  : {
		key   : 'appReliefList',
		title : '피해구제 신청 및 인정현황',
		elm   : false,
		doInit: function( form, params ) {
			
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1',
				titleTag:  'h3',
				tailSpace: true,
				columns: [
					{name: 'aplySn'         , label: '신청횟수'},
					{name: 'aplyYmd'        , label: '신청일'},
					{name: 'dmgeRcognYn'    , label: '인정여부', formatter: P_POPUP_FORMAT.toRcognNm},
					{name: 'dltncOpmtYmd'   , label: '인정일'},
					{name: 'rcognDissCnt'   , label: '인정질환', formatter: P_POPUP_FORMAT.toKindCnt},
					{name: 'lastDmgeGrdCd'  , label: '피해등급', formatter: P_POPUP_FORMAT.toGrdNm},
					{name: 'bizOderNm'      , label: '사업구분'},
					{name: 'dltncRsltResn'  , label: '비고'}
				]
			});
			
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getListReliefRecogn.do'), 
				params,
	            function(result) {
	                var rows = result.rows;
	                if (rows)
						p.elm.loadData(rows);
				}
			);
		}
	},
    // 건강피해 인정현황(목록)
    //--------------------------------------------------------//
	SUMMARY : {
		key   : 'appMcpSummary',
		title : '건강피해 인정현황',
		elm   : false,
		doInit: function( form, params ) {
			
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1',
				titleTag:  'h3',
				tailSpace: true,
				columns: [
					{name: 'sn'       , label: '연번'},
					{name: 'sckwndCd' , label: '질병코드(3단위)'},
					{name: 'sckwndNm' , label: '질병명', cls: 'app-l'},
				]
			});
			
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getListDmgeRecogn.do'), 
				params,
	            function(result) {
	                var rows = result.rows;
	                if (rows)
						p.elm.loadData(rows);
				}
			);
		}
	},
    // 건강피해 상세현황(목록)
    //--------------------------------------------------------//
	MCPDTLS : {
		key   : 'appMcpDtls',
		title : '건강피해 상세현황',
		elm   : false,
		doInit: function( form, params ) {

			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1 app-subtit-noline',
				titleTag:  'h3',
				tailSpace: true,
				columns: [
					{name: 'sn'             , label: '연번'},
					{name: 'sckwndCd'       , label: '질병코드'},
					{name: 'sckwndNm'       , label: '질병명', cls: 'app-l'},
					{name: 'selfAlotm'      , label: '의료비', cls: 'app-r', formatter: $.formatUtil.toMoney},
					{name: 'rcperYmd'       , label: '첫통원일자'},
					{name: 'rcperCnt'       , label: '이용건수'},
				],
				prependHtml: function( elm ) {
					elm.append('<div class="formGroup app-subtit-topbar"></div>');
					elm.find('.app-subtit-topbar').append('<span class="label">- 조회기간</span>');
					elm.find('.app-subtit-topbar').append('<span class="mark"> : </span>');
					elm.find('.app-subtit-topbar').append('<div class="app-subtit-box" id="s_rvwBgngYmd"></div>');
					elm.find('.app-subtit-topbar').append('<span class="mark"> ~ </span>');
					elm.find('.app-subtit-topbar').append('<div class="app-subtit-box" id="s_rvwEndYmd"></div>');
				}
			});
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getListDmgeDetails.do'), 
				params,
	            function(result) {
	                var rows = result.rows;
					var data = result.Data;
	                if (rows)
						p.elm.loadData(rows);
					if (data) {
						$('#s_rvwBgngYmd').html(data['rvwBgngYmd']);
						$('#s_rvwEndYmd' ).html(data['rvwEndYmd' ]);
					}
				}
			);
		}
	},
    // 건강피해 영향범위 및 거주기간
    //--------------------------------------------------------//
	EXAMINE : {
		key   : 'appExamine',
		title : '건강피해 영향범위 및 거주기간',
		elm   : false,
		doInit: function( form, params ) {
			
			let _fnStyle = function(el, v, r, rowIdx) {
				if (rowIdx%2 == 1 && !$.commUtil.empty(v))
					el.addClass('app-bgonly-sky');
			};
			
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1',
				titleTag:  'h3',
				tailSpace: true,
				colgroup:  ['40%','20%','20%','20%'],
				columns:   [
					{name: 'label', label: '구분'},
					{name: 'item1', label: '대구', formatter: $.formatUtil.toDash, styler: _fnStyle},
					{name: 'item2', label: '서천', formatter: $.formatUtil.toDash, styler: _fnStyle},
					{name: 'item3', label: '김포', formatter: $.formatUtil.toDash, styler: _fnStyle}
				]
			});
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getDmgeAffcScope.do'), 
				params,
	            function(result) {
					var data = result.Data;
					if (data) {
						let lbls = [
							{key: 'affcScopeCn'    , cls: 'app-bgonly-gray', text: '영향범위'},
							{key: 'affcScopeResiYn', fmt: P_POPUP_FORMAT.toScopeNm, text: '영향범위 포함여부'},
							{key: 'expsrWhlCn'     , cls: 'app-bgonly-gray', text: '노출기간 기준(최초년도-종료년도)'},
							{key: 'expsrWhlCnt'    , fmt: P_POPUP_FORMAT.toYearCnt, text: '노출기간 중 거주기간'}
						];
						let rows = [];
						$.each(lbls, function(i,lbl) {
							let o = {};
							o['label'] = lbl['text'];
							o['item1'] = data[lbl['key']+'1'];
							o['item2'] = data[lbl['key']+'2'];
							o['item3'] = data[lbl['key']+'3'];
							
							if (lbl['fmt']) {
								o['item1'] = lbl['fmt'](o['item1']);
								o['item2'] = lbl['fmt'](o['item2']);
								o['item3'] = lbl['fmt'](o['item3']);
							}
							if (lbl['cls'])
								o['cls'] = lbl['cls'];


							rows.push(o);
						});
						p.elm.loadData(rows);
					}
				}
			);
		}
	},
    // 구제급여 지급현황
    //--------------------------------------------------------//
	RLFGIVE : {
		key   : 'appReliefGive',
		title : '구제급여 지급현황',
		elm   : false,
		doInit: function( form, params ) {
			
			let _fnStyle = function(el, v, r) {
				if (r['giveYr'] == 'TOTAL')
					el.addClass('app-bgonly-sky');
				
			};
			let _fnTotal = function(v) {
				return (v == 'TOTAL' ? '총계' : v+'년');
			};
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1 app-subtit-noline',
				titleTag:  'h3',
				tailSpace: true,
				colgroup:  ['20%','20%','20%','20%','20%'],
				columns: [
					{name: 'giveYr'       , label: '년도'                      , styler: _fnStyle, formatter: _fnTotal},
					{name: 'mcpAmt'       , label: '의료비'      , cls: 'app-r', styler: _fnStyle, formatter: $.formatUtil.toMoney},
					{name: 'rcpAmt'       , label: '요양생활수당', cls: 'app-r', styler: _fnStyle, formatter: $.formatUtil.toMoney},
					{name: 'fnrlCstAmt'   , label: '장례비'      , cls: 'app-r', styler: _fnStyle, formatter: $.formatUtil.toMoney},
					{name: 'brvfmRwrdAmt' , label: '유족보상비'  , cls: 'app-r', styler: _fnStyle, formatter: $.formatUtil.toMoney},
				],
				prependHtml: function( elm ) {
					elm.append('<div class="formGroup app-subtit-topbar"></div>');
					elm.find('.app-subtit-topbar').append('<span class="label">- 지급총액</span>');
					elm.find('.app-subtit-topbar').append('<span class="mark"> : </span>');
					elm.find('.app-subtit-topbar').append('<div class="app-subtit-box" id="s_totalAmt"></div>');
					elm.find('.app-subtit-topbar').append('<span class="mark"> 원 </span>');
				}
			});
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getListReliefGive.do'), 
				params,
	            function(result) {
	                var rows = result.rows;
					var data = result.Data;
	                if (rows)
						p.elm.loadData(rows);
					if (data) {
						$('#s_totalAmt').html( $.formatUtil.toMoney(data['totalAmt']));
					}
				}
			);
		}
	},
    // 민원응대 이력
    //--------------------------------------------------------//
	CMPLHST : {
		key   : 'appCmplHst',
		title : '민원응대 이력',
		elm   : false,
		doInit: function( form, params ) {
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1',
				titleTag:  'h3',
				tailSpace: true,
				colgroup:  ['20%','20%','20%','40%'],
				columns: [
					{name: 'sn'       , label: '연번'},
					{name: 'cmplYmd'  , label: '일자'},
					{name: 'cmplRel'  , label: '민원인(관계)'},
					{name: 'cmplCn'   , label: '민원내용'},
				]
			});
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getListCmplHst.do'), 
				params,
	            function(result) {
	                var rows = result.rows;
	                if (rows)
						p.elm.loadData(rows);
				}
			);
		}
	},
    // 피해등급
    //--------------------------------------------------------//
	DMGEGRD : {
		key   : 'appDmgeGrd',
		title : '피해등급',
		elm   : false,
		doInit: function( form, params ) {
			
			let p     = this;
			this.form = form;
			this.elm  = $('#'+this.key).appTableLayout({
				wrap:      true,
				wrapCls:   'tableWrap type5',
				title:     this.title,
				titleCls:  'subTit type1',
				titleTag:  'h3',
				tailSpace: true,
				colgroup:  ['60%','20%','20%'],
				columns: [
					{name: 'lastDmgeDiss'  , label: '피해등급 평가질환', cls: 'app-dmge-list'},
					{name: 'lastDmgeScre'  , label: '중증도 평가 점수'},
					{name: 'lastDmgeGrdCd' , label: '피해등급', formatter: $.formatUtil.toGrdNm},
				]
			});
			// 피해등급 조회
	        $.ajaxUtil.ajaxLoad(
	            getUrl('/adm/main/getDmgeGrd.do'), 
				params,
	            function(result) {
	                var data = result.Data;
	                var rows = result.rows;
	                if (data)
						p.elm.loadData([data]);
					if (rows)
						p.doLoadRows(rows);
				}
			);
		},
		// 평가질환 목록 표시
		doLoadRows: function(rows) {
			let dom = this.elm.getElement('app-dmge-list');
			dom.addClass('app-inner-table');
			let tbl = $('<table></table>');
			tbl.append('<tbody></tbody>');
			$.each(rows, function(i,r) {
				let tr = $('<tr></tr>');
				tr.append('<td>'+r['dissKndCd']+'</td>');
				tr.append('<td>'+r['dissKndNm']+'</td>');
				tr.append('<td>'+r['svrtyScre']+'</td>');
				tbl.find('tbody').append(tr);
			});
			dom.append(tbl);
		}
	}
};

$(function() {

	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//

    // 최초 로딩
    //--------------------------------------------------------//
	function doInit() {
		
		$.each(P_POPUP_SECTIONS, function(key, section) {
			section.doInit($('#popupForm'), {
				idntfcId: $('#p_idntfcId').val() // 식별ID
			});
		}); 
	}
	
    // 개인별 상세기록카드 인쇄 팝업
    //--------------------------------------------------------//
	function doOpenPrint() {
		let pop = $('#layerPopup').appPopup({
			// 팝업 제목
			title:   '개인별 상세기록카드 인쇄',
			// 팝업 스타일시트
			popupCls:'layerPop form type3 listLock',
			// 팝업 내용
			message: function() {
				let dom = $('<form id="p_printForm" method="post" name="printForm"></form>');
				dom.append('<div class="layerWrap"></div>');
				dom.find('.layerWrap').append('<div class="layerPop-inner"></div>');
				dom.find('.layerPop-inner').append('<div class="tableWrap type5"></div>');
				dom.find('.layerPop-inner').append('<div class="layerBtnWrap"></div>'); // '<div class="btnWrap type2 one"></div>'
				dom.find('.layerBtnWrap').append('<a href="javascript:void(0);" class="app-close-btn">취소</a>');
				dom.find('.layerBtnWrap').append('<a href="javascript:void(0);" class="app-print-btn">인쇄</a>');
				
				let tbl = $('<table></table>');
				tbl.append('<tr></tr>');
				tbl.find('tr:last').append('<th>선택</th>');
				tbl.find('tr:last').append('<th>인쇄항목</th>');
				
				let rows = [
					{code: '01', text: '필수', label: '피해자 개요'},
					{code: '02', text: '필수', label: '피해구제 신청 및 인정현황'},
					{code: '03', text: '필수', label: '건강피해 인정현황'},
					{code: '04', text: '필수', label: '건강피해 상세현황', checkbox: '1'},
					{code: '05', text: '필수', label: '건강피해 영향범위 및 거주기간', checkbox: '2'},
					{code: '06', text: '필수', label: '구제급여 지급현황'},
					{code: '07', text: '필수', label: '민원응대 이력'},
					{code: '08', text: '필수', label: '피해등급'},
				];
				$.each(rows, function(i,r) {
					let tr = $('<tr></tr>');
					tr.append('<td></td>');
					tr.append('<td></td>');
					if (r['checkbox']) {
						let chk = $('<input type="checkbox" id="itemCd'+i+'" name="itemCd" value="'+r['checkbox']+'" />');
						tr.find('td:first').append(chk);
					}
					else {
						tr.addClass('app-bgonly-gray');
						tr.find('td:first').append(r['text']);
					}
					tr.find('td:last').append('<label for="itemCd'+i+'">'+r['label']+'</label>');
					tbl.append(tr);
				});
				dom.find('.tableWrap').append(tbl);

                // label 클릭시
				dom.find('label').on('click', function(){
                    dom.find("input[id='"+$(this).attr("for")+"']").click();
                });
			    // 취소버튼 클릭시
				dom.find('a.app-close-btn').bind('click', function() {
					pop.close();
					return false;
				});
				// 인쇄버튼 클릭시 (리포팅툴)
				dom.find('a.app-print-btn').bind('click', function() {
					let params = {
						mode      : 'PDCARD',
						idntfcId  : $('#p_idntfcId'  ).val(),  // 식별ID
						rvwBgngYmd: $('#s_rvwBgngYmd').html(), // 조회기간 시작일
						rvwEndYmd : $('#s_rvwEndYmd' ).html(), // 조회기간 종료일
						totalAmt  : $('#s_totalAmt'  ).html(), // 지급총액
						itemCd1   : 'N',
						itemCd2   : 'N'
					};
					dom.find('input[name="itemCd"]:checked').each(function(i) {
						params['itemCd'+$(this).val()] = 'Y';
					});
					// 리포트뷰어 팝업 호출
					popup.openReportPopup(params);
			        return false;
				});
				return dom;
			}
		}).open();
	}
	
    // 인쇄하기 버튼 클릭시 이벤트 처리
    $('#btnPrint').bind('click', doOpenPrint);
	
	// 최초 데이터로딩
	doInit();
});