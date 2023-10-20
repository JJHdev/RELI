/**
******************************************************************************************
*** 파일명 : viewRelief.js
*** 설명글 : 마이페이지 - 구제급여현황 - 피해구제현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.12.08    LSH 탭별로 프로그램 분리 / 신청목록 추가
******************************************************************************************
**/
$(function() {
	
	// 서브메뉴탭 생성
    //--------------------------------------------------------//
	createTabMenu($('#appReliefTab'), [
		{title:'구비서류 조회'    , url: getUrl('/usr/mypage/viewReliefPape.do') },
		{title:'피해구제 현황'    , url: getUrl('/usr/mypage/viewRelief.do'    ) },
		{title:'구제급여 지급현황', url: getUrl('/usr/mypage/viewReliefGive.do') }
	], 'relief-tab tabWrap type1');

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	let P_RELIEF = false;

    // 초기실행
    //--------------------------------------------------------//
	doLoad();

    // 신청목록 가져오기 (초기실행)
    //--------------------------------------------------------//
	function doLoad() {

		$.ajaxUtil.ajaxLoad(
			getUrl('/usr/relief/getListRelief.do'), {}, 
			function(data) {
				
				if (data.rows.length == 0) {
					// 데이터가 없는경우 탭패널 감춤
					$('.relief-tab-panel').hide();
				}
				// 신청목록 정의
				$('#appAplyList').appTableLayout({
					cls: 'app-h200',
					nodata: true,
					data: data.rows,
					// 칼럼목록
					columns: [
						{name: 'aplyNo'     , label: '신청번호'},
						{name: 'aplcntNm'   , label: '신청자명'},
						{name: 'sufrerNm'   , label: '피해자명'},
						{name: 'aplySeNm'   , label: '신청구분'},
						{name: 'aplyYmd'    , label: '신청일자'},
						{name: 'rcptYmd'    , label: '접수일자'},
						{name: 'prgreStusNm', label: '진행상태'},
					],
					// 행선택처리
					select: doSelect,
					// 행로드후 처리함수
					callback: function(cmp) {
						cmp.selectRow(0);
					}
				});
			}
		);
	}

    // 신청정보 가져오기
    //--------------------------------------------------------//
	function doSelect(index, row) {
		
		P_RELIEF = row;
		
		let tableCls = 'relief-table'; // 테이블공통CSS
		
		$('#reliefSufrer'  ).html('');
		$('#reliefAplcnt'  ).html('');
		$('#reliefProgress').html('');

		// 히든값에 맵핑
		$('#bizAreaCd').val(P_RELIEF['bizAreaCd']);
		$('#aplyYmd'  ).val(P_RELIEF['aplyYmd'  ]);
		
		$('#reliefSufrer').addClass(tableCls).append( 
			// comm_biz.js 참고
			createReliefTable('피해자',[
				{value: P_RELIEF['sufrerNm'  ], label: '성명'},
				{value: $.commUtil.nvlTrim    (P_RELIEF['idntfcId'  ]), label: '식별아이디'},
				{value: $.formatUtil.toKorDate(P_RELIEF['sufrerBrdt']), label: '생년월일'},
				{value: $.codeUtil.dthYn      (P_RELIEF['dthYn'     ]), label: '생사여부'}
			])
		);

		if (P_RELIEF['aplySeCd'] != CODE.APLY_CD.SELF) {
			$('#reliefAplcnt').addClass(tableCls).append( 
				// comm_biz.js 참고
				createReliefTable('대리인',[
					{value: P_RELIEF['aplcntNm'   ], label: '성명'},
					{value: P_RELIEF['sufrerRelNm'], label: '신청인과의 관계'}
				])
			);
			$('#aplcntInfo').removeClass('off');
		}
		// 진행단계표시
		doLoadProgress( $('#reliefProgress'), P_RELIEF );
	}

	// 피해구제 진행현황 표시
    //--------------------------------------------------------//
	function doLoadProgress( elm, data ) {
		
		let prgreStusCd = data['prgreStusCd'];

		let ul = $('<ul class="step"></ul>');
		let dv = $('<div class="step_info"></div>');
		
		$.each(STORE.RELIEF_PROGRESS, function(i,o) {
			
			let li = $('<li><p></p></li>');
			li.find('p').append(o['text']);
			li.find('p').append('<img src="'+getUrl(o['icon'])+'"/>');
			
			let pn = $('<ul class="step'+(i+1)+'"></ul>');
			pn.data('code', o['code']);

			if ($.inArray(prgreStusCd, o['stus']) >= 0) {
				li.addClass("on");
				pn.addClass("on");
			}
			ul.append(li);
			dv.append(pn);
		});
		elm.append(ul);
		elm.append(dv);

		// 단계에 따라 예비조사, 본조사, 지급현황 표시
		let step = parseInt( prgreStusCd );
		
		doLoadReceipt ( $('ul.step1'), step, data ); // 신청접수 LOAD
		doLoadPrptExmn( $('ul.step2'), step, data ); // 예비조사 LOAD
		doLoadMnsvy   ( $('ul.step3'), step, data ); // 본조사 LOAD
		
		// 단계클릭시 이벤트 처리
		$("ul.step > li").on("click",function(){
			var i = $(this).index()
			$(this).parent().find("li").removeClass("on");
			$(this).addClass("on");
			elm.find('div.step_info > ul').removeClass("on");
			elm.find('div.step_info > ul').eq(i).addClass("on");
		})
	}

	// 신청/접수 정보 정의
    //--------------------------------------------------------//
	function doLoadReceipt( elm, step, data ) {
		if (step < parseInt(CODE.PRGRE_STUS.APPLY))
			return;
		// comm_biz.js 참고
		createStepTable({
			element: elm,
			columns: [
				{value: data['prgreStusNm'], label: '진행상태'},
				{value: $.formatUtil.toKorDate(data['aplyYmd']), label: '신청일자'},
				{value: $.formatUtil.toKorDate(data['rcptYmd']), label: '접수일자'}
			]
		});
	}

	// 예비조사 정보 가져오기
    //--------------------------------------------------------//
	function doLoadPrptExmn( elm, step, params ) {
		
		if (step < parseInt(CODE.PRGRE_STUS.PRPTEXMN))
			return;

		// 예비조사정보 불러오기
		const result = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/relief/getListPrptExmn.do'), params);

		if (!result || 
			!result.rows)
			return;
		$.each(result.rows, function(i, row) {
			let cls = false;
			if      (row['dltncRsltCd'] == CODE.RSLT_CD.CONFIRM)
				cls = 'c_blue';
			else if (row['dltncRsltCd'] == CODE.RSLT_CD.REJECT)
				cls = 'c_red';
			
			// comm_biz.js 참고
			createStepTable({
				element: elm,
				columns: [
					{value: $.formatUtil.toKorDate(row['dltncOpmtYmd']), label: '심의회 개최일자'},
					{value: $.commUtil.nvlTrim(row['dltncRsltNm'  ]), label: '심의회 결과', ddCls:cls},
					{value: $.commUtil.nvlTrim(row['dltncRsltResn']), label: '심의회 결과사유'}
				]
			});
		});
	};

	// 본조사 정보 가져오기
    //--------------------------------------------------------//
	function doLoadMnsvy( elm, step, params ) {

		if (step < parseInt(CODE.PRGRE_STUS.MNSVY))
			return;

		// 본조사정보 불러오기
		const result = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/relief/getListMnsvy.do'), params);
		
		if (!result || 
			!result.rows)
			return;

		$.each(result.rows, function(i, row) {
			let cls = false;
			if      (row['dltncRsltCd'] == CODE.RSLT_CD.CONFIRM)
				cls = 'c_blue';
			else if (row['dltncRsltCd'] == CODE.RSLT_CD.REJECT)
				cls = 'c_red';
			
			// comm_biz.js 참고
			createStepTable({
				element: $(elm),
				columns: [
					{value: $.formatUtil.toKorDate(row['giveDcsnYmd']), label: '심의회 개최일자'},
					{value: row['dltncRsltNm'  ], label: '본조사 결과', ddCls:cls},
					{value: row['lastDmgeGrdCd'], label: '피해 등급'},
					{value: row['dltncRsltResn'], label: '본조사 결과사유', cls:'clear'},
					{value: row['rcognSckwndNm'], label: '인정 질환', cls:'clear'},
					{value: $.formatUtil.toKorMoney(row['rcognAmt']), label: '인정 금액', cls:'clear'}
				]
			});
		});
		// 구제급여지급 LOAD
		doLoadGive( $('ul.step4'), step, result.rows );
	};
	
	// 구제급여 지급정보 표시
    //--------------------------------------------------------//
	function doLoadGive( elm, step, rows ) {

		if (step < parseInt(CODE.PRGRE_STUS.GIVE))
			return;
		if (!rows)
			return;
		
		$.each(rows, function(i, row) {
			let obj = {
				bankNm :  row['giveBankNm'], // 지급은행
				actNo  :  row['giveActno' ], // 지급계좌
				giveYmd:  $.formatUtil.toKorDate(row['giveYmd']), // 지급일자
				giveAmt: '월 '+$.formatUtil.toKorMoney(row['giveAmt']) // 지급결정금액
			};
			// 지급기간 있을때만 표시
			if (!$.commUtil.empty(row['giveBgngYm']) && 
				!$.commUtil.empty(row['giveEndYm' ])) {
				obj['giveAmt'] += [
			          ' (', '지급기간 : ',
				      $.formatUtil.toKorMonth(row['giveBgngYm']), ' ~ ',
				      $.formatUtil.toKorMonth(row['giveEndYm' ]), ')'
				].join('');
			}
			// comm_biz.js 참고
			createStepTable({
				element: $(elm),
				columns: [
					{value: obj['bankNm' ], label: '지급 은행'},
					{value: obj['actNo'  ], label: '지급 계좌'},
					{value: obj['giveYmd'], label: '지급 일자'},
					{value: obj['giveAmt'], label: '지급 금액', cls:'clear'}
				]
			});
		});
	};

});
