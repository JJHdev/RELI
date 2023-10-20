/**
******************************************************************************************
*** 파일명 : listBbs.js
*** 설명글 : [게시판] 질의응답 사용자 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    김주호
******************************************************************************************
**/


$(function() {

	
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	const P_FAQ_FORM  = $('#faqForm'); // FAQ 검색폼
    const P_QNA_FORM  = $('#qnaForm'); // QNA 검색폼
	const P_FAQ_CODE  = CODE.BBS.FAQ; // FAQ 구분 코드
	const P_QNA_CODE  = CODE.BBS.QNA; // QNA 구분 코드
	let   P_FAQ_GRID  = false; // FAQ 목록 객체
	let   P_QNA_GRID  = false; // QNA 목록 객체
	let   P_QNA_POPUP = false; // QNA 비밀번호팝업 객체



    //========================================================//
    // FORM ELEMENT 정의
    //--------------------------------------------------------//
	// FAQ 검색옵션 콤보박스
	// (comm_element.js 참고)
	$('#srchFaqType').appComboBox({
		type: 'static',
		rows: [
			{code:'3', text:'제목'},
			{code:'4', text:'내용'},
			{code:'5', text:'제목+내용'}
		]
	});
	// QNA 검색옵션 콤보박스
	// (comm_element.js 참고)
	$('#srchQnaType').appComboBox({
		type: 'static',
		rows: [
			{code:'3', text:'제목'},
			{code:'4', text:'내용'},
			{code:'5', text:'제목+내용'}
		]
	});
	// 검색키워드 엔터입력시 검색 이벤트 바인딩 
	// (comm_utils.js 참고)
	bindEnter($('#srchFaqText'), $('#btnFaqSearch'));
	bindEnter($('#srchQnaText'), $('#btnQnaSearch'));

    //========================================================//
    // GRID (목록) 정의
    //--------------------------------------------------------//

	// 자주하는질문 목록 (comm_board.js 참고)
	P_FAQ_GRID = $('#appFaqBoard').appBoard({
		// 표시 타입
		type: 'FAQ',
		// 검색 URL
		url: getUrl("/usr/bbs/listBbsJson.do"),
		// 기본검색조건
		params: {
			// 게시판구분조건
			bbsSeCd: P_FAQ_CODE,
			// 화면출력수
			rows: 5
		},
		// 페이징영역 ID
		paging: '#appFaqPage',
		// 칼럼 정의
		faqField: {
			question: 'nttSj',
			answer:   'nttCn'
		}
	});
	// 질의응답 목록 (comm_board.js 참고)
	P_QNA_GRID = $('#appQnaBoard').appBoard({
		// 검색 URL
		url: getUrl("/usr/bbs/listBbsJson.do"),
		// 페이징영역 ID
		paging: '#appQnaPage',
		// 기본검색조건
		params: {
			bbsSeCd: P_QNA_CODE
		},
		// 칼럼 정의
		columns: [
		  	{name: 'nttNo'  , label: '번호'  ,width: '10%',
	             formatter: function(value,json,rowidx,colidx,page) {
		
		           return page.total-rowidx-((page.page-1)*10);
	             }
             },
		  	{name: 'nttSj'  , label: '제목'  , align:'left',width: '50%'},
		  	{name: 'rgtrNm' , label: '작성자', mobile: true ,width: '10%'},
		  	{name: 'regYmd' , label: '등록일', mobile: true ,width: '10%'},
		  	{name: 'status' , label: '상태'  , mobile: true,width: '10%', 
	         	// 포맷된 데이터 반환
				formatter: function(value,json,rowidx,colidx,page) {
					if (value > 0) 
						return "답변완료";
					else
						return '대기중';
				 
				},
	         	// 칼럼셀의 포맷이 필요한 경우
				formatCell: function(elm, value) {
					if (value > 0) 
						elm.addClass('c_blue');
					else
						elm.addClass('c_red');
				},
				// 행단위 포맷이 필요한 경우
				formatRow: function(elm) {
					// 모두 비공개 표시
					elm.addClass('lock');
				}
			}
		],
		// 데이터 행 클릭시 실행함수
		select: function(row) {
			// 클릭한 사용자가 본인인지 확인
			$.ajaxUtil.ajaxLoad(
				getUrl('/usr/bbs/checkQnaWriter.do'),
				{
					nttNo : row['nttNo']
				},
				function(result) {
					console.log(result.Code == 0)
					if(result.Code == 0){
						$.formUtil.submitForm(getUrl("/usr/bbs/viewQna.do"), 
						{
							params: {nttNo : row['nttNo']}
						});
						P_QNA_POPUP.close();
					}else{
									
			            // 비밀번호 확인팝업 오픈
			            doOpenPassword(row['nttNo']);
					}
	
				}
			);

		},
		// 데이터로딩후 실행함수
		callback: function(cmp, elm, rows) {
		}
	});

    // 자주하는질문 검색처리
    //--------------------------------------------------------//
	function doFaqSearch() {
		// 검색조건 객체화 (comm_utils.js 참고)
		let params = P_FAQ_FORM.serializeObject();
		// 목록 데이터로드 (comm_board.js 참고)
		P_FAQ_GRID.load(params);
	}
	
    // 질의응답 검색처리
    //--------------------------------------------------------//
	function doQnaSearch() {
		// 검색조건 객체화 (comm_utils.js 참고)
		let params = P_QNA_FORM.serializeObject();
		// 목록 데이터로드 (comm_board.js 참고)
		P_QNA_GRID.load(params);
	}

    // 질의응답 문의하기 (페이지이동)
    //--------------------------------------------------------//
	function doQnaRegist() {
		$.formUtil.submitForm(getUrl("/usr/bbs/regiBbs.do"));
	}

    // 질의응답 비밀번호 확인 팝업
    //--------------------------------------------------------//
	function doOpenPassword( nttNo ) {
		// 비밀번호 확인 팝업
		P_QNA_POPUP = $('#appPopupQnaPswd').appPopup({
			// 팝업 제목
			title:   '비밀번호 확인',
			// 팝업 스타일시트
			popupCls:'layerPop form type3 listLock',
			// 팝업 내용
			message: function() {
				let dom = $('<div></div>');
				let inp = $('<input id="popPwd" type="password" placeholder="비밀번호를 입력해주세요" class="w100" />');
				let div = $('<div class="inputGroup"><div class="inputWrap one"></div></div>');
				div.find('.inputWrap').append(inp);
				dom.append(div);
				
				let btn = $('<div class="btnWrap type2 one pb40"></div>');
				btn.append('<a href="javascript:void(0);" class="app-popup-btn">확인</a>');
				dom.append(btn);
				dom.append('<p class="err"></p>');
				
				// 확인 버튼 클릭시
				dom.find('a.app-popup-btn').bind('click', function() {
					let pwd = $('#popPwd').val();
					
					// 비밀번호 입력 유효성 체크
					if ($.commUtil.empty(pwd)) {
						$.commMsg.alert('비밀번호를 입력하세요.');
						return false;
					}
					
					// 비밀번호 검증 (true / false)
					const chk = $.ajaxUtil.ajaxDefault(
						
						getUrl('/usr/bbs/checkPassword.do'),{
							nttNo:   nttNo,
							nttPswd: pwd
						});
						
					if (chk.Data) {
						// 상세조회로 이동
						$.formUtil.submitForm(getUrl("/usr/bbs/viewQna.do"), 
						{
							params: {nttNo : nttNo}
						});
						P_QNA_POPUP.close();	
					}
					else {
						// 에러메세지 표시
						let msg = '<small>비밀번호가 일치하지 않습니다</small>';
						$(P_QNA_POPUP).find('p.err').html(msg);
					}
				});
				// 비밀번호입력 엔터키 이벤트 처리
				bindEnter(dom.find('input'), dom.find('a.app-popup-btn'));
				
				return dom;
			},
			// 팝업오픈후 콜백함수
			onshown: function() {
				$('#popPwd').focus();

			}
		});
	}
    // 질의응답 비밀번호 팝업 중복 제거 처리
    //--------------------------------------------------------//	
    $(document).mouseup(function (e){
        var P_QNA_POPUP = $("#appPopupQnaPswd");
        if(P_QNA_POPUP.has(e.target).length === 0){
            P_QNA_POPUP.removeClass("layerPop form type3 listLock on");
            $('#appPopupQnaPswd *').remove();
        }
    });

	// 자주하는질문 검색버튼 클릭 이벤트 처리
	$('#btnFaqSearch').bind('click', doFaqSearch);
	// 질의응답 검색버튼 클릭 이벤트 처리
	$('#btnQnaSearch').bind('click', doQnaSearch);
	// 질의응답 문의하기버튼 클릭 이벤트 처리
	$('#btnQnaRegist').bind('click', doQnaRegist);
	
	// 초기검색실행
	doFaqSearch();
	doQnaSearch();
	


});



