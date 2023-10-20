/**
******************************************************************************************
*** 파일명 : viewReliefGive.js
*** 설명글 : 마이페이지 - 구제급여현황 - 구제급여지급현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.12.08    LSH
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
	const P_FORM = $('#selectForm');         // 조회폼 객체
	let P_PARAMS = P_FORM.serializeObject(); // 공통 파라메터
	let P_SELECT = false; // 지급총액 데이터 객체

	// 구제급여 지급현황 로드
    //--------------------------------------------------------//
	function doLoad() {

		P_SELECT = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/relief/getGiveSummary.do'),
			P_PARAMS);
		
		$('#mcpYmd').html('');
		$('#rcpYmd').html('');
		$('#mcpAmt').html('<span>0</span>원');
		$('#rcpAmt').html('<span>0</span>원');
		
		if (P_SELECT) {
			$('#mcpYmd').html($.formatUtil.toKorDate(P_SELECT['mcpYmd']));
			$('#rcpYmd').html($.formatUtil.toKorDate(P_SELECT['rcpYmd']));
			$('#mcpAmt').html('<span>'+$.formatUtil.toMoney(P_SELECT['mcpAmt'])+'</span>원');
			$('#rcpAmt').html('<span>'+$.formatUtil.toMoney(P_SELECT['rcpAmt'])+'</span>원');
		}
		
		// 의료비 추가신청 버튼 클릭
		$('#btnAplyAdd').bind('click', doOpenAplyAdd);
		// 의료비/요양생활수당 지급총액 클릭
		$('.appGiveDtls').bind('click', doOpenGiveAmount);
	}
	
	// 2021.11.09 의료비 추가 신청 팝업 오픈
    //--------------------------------------------------------//
	function doOpenAplyAdd() {

		$.commModal.loadView(
			'의료비 추가신청', 
			getUrl('/html/popupReliefAplyAdd.html'), {
				sizeType: 'large',
				func: function(dialog) {
					// 신청파일 설정					
					let file = $('#appAplyAddFile').appAplyFile({
						mode:  MODE.INSERT,
						title: false,
						// 서류목록조건
						params: {
							// 업무구분
							papeDtySeCd: P_PARAMS['papeDtySeCd'],
							// 신청구분
							aplySeCd:    P_PARAMS['aplySeCd'],
							// 서류그룹 (의료비)
							upPapeCd:    'D002',
							// 서류코드 (요양급여내역)
							papeCd:      'D00201',
							// (수정시) 문서번호
							dcmtNo:      null,
							// (수정시) 세부문서번호
							dtlDcmtNo:   null,
						}
					})					
					// 제출하기 클릭
					$('#btnPopSubmit').on("click", function() {

						let data = {
							mode:   MODE.ADD,
							aplyNo: P_PARAMS['aplyNo']
						};
						if ($.commUtil.empty(data.aplyNo)) {
							$.commMsg.alert('신청정보를 확인할 수 없습니다.');
							return false;
						}
						if (file.doValidate() === false) {
							return false;
						}
						let saveData = file.getSaveData();
						data['saveFiles'  ] = saveData['saveFiles'  ];
						data['removeFiles'] = saveData['removeFiles'];
						
				        $.commMsg.confirm("제출하시겠습니까?", function() {
				            $.ajaxUtil.ajaxSave(
				                getUrl('/usr/relief/saveReliefAdd.do'), 
				                JSON.stringify(data),
				                function(ret) {
				                    $.ajaxUtil.success(ret, function() {
										// 마이페이지로 이동처리
										$.commMsg.alert('성공적으로 제출되었습니다.', function() {
										    // 저장후 마이페이지로 이동처리
											goUrl( getUrl('/usr/mypage/viewRelief.do') );
											dialog.close();
										});
				                    });
				                }
				            );
				        });
				        return false;
					});
				}
			}
		);
	}

	// 2021.11.01 지급총액 클릭시 세부내역 팝업 오픈
    //--------------------------------------------------------//
	function doOpenGiveAmount() {
		let type   = $(this).data('code');
		let title  = (type == 'MCP' ? '의료비' : '요양생활수당');
		let params = {mode: type};
		// 지급세부내역 팝업
		$('#reliefGivePopup').appPopup({
			// 팝업 제목
			title: title+' 지급세부내역',
			// 팝업 스타일시트
			popupCls:'layerPop type2',
			// 제목 스타일시트
			titleCls: 'layerPop-title app-pb10',
			// 팝업 내용
			message: function() {
				let dom = $('<div></div>');
				dom.append('<div id="appGivePopupGrid" class="tableWrap type4"></div>');
				dom.append('<div id="appGivePopupPage" class="pagenation"></div>');
				dom.append('<div class="btnWrap type1"></div>');
				dom.find('.btnWrap').append('<a href="javascript:void(0);" id="appGivePopupClose">확인</a>');
				return dom;
			},
			onshown: function(cmp) {
				// 세부내역목록 (comm_board.js 참고)
				$('#appGivePopupGrid').appBoard({
					// 검색 URL
					url: getUrl("/usr/relief/getListGive.do"),
					// 칼럼 정의
					columns: [
					  	{name: 'giveYmd'    , label: '지급일자'},
					  	{name: 'giveBankNm' , label: '지급은행'},
					  	{name: 'giveActno'  , label: '지급계좌'},
					  	{name: 'giveDpstrNm', label: '예금주'},
					  	{name: 'giveAmt'    , label: '지급액', formatter: $.formatUtil.toKorMoney}
					],
					// 페이징영역 ID
					paging: '#appGivePopupPage',
					// 기본검색조건
					params: {
						// 처리모드
						mode: type,
						// 화면출력수
						rows: 5
					},
				}).load(params);
				// 확인 버튼 클릭시
				$('#appGivePopupClose').bind('click', function() {
					cmp.close();
					return false;
				});
			}
		});
	}
	
    // 팝업 중복 제거 처리
    //--------------------------------------------------------//	
    $(document).mouseup(function (e){
        var P_relief_POPUP = $("#reliefGivePopup");
        if(P_relief_POPUP.has(e.target).length === 0){
            P_relief_POPUP.removeClass("layerPop type2 on");
            $('#reliefGivePopup *').remove();
        }
    });	
	// 구제급여 지급현황 불러오기
	doLoad();
});
