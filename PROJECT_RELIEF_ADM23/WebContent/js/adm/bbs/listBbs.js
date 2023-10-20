/**
******************************************************************************************
*** 파일명 : listBbs.js
*** 설명글 : [게시판] 질의응답관리 관리자 스크립트
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
	const P_FAQ_FORM = $('#faqForm'); // FAQ 검색폼
	const P_QNA_FORM = $('#qnaForm'); // QNA 검색폼
	const P_QNA_CODE = CODE.BBS.QNA;  // QNA 구분 코드
	const P_FAQ_CODE = CODE.BBS.FAQ;  // FAQ 구분 코드
	let P_FAQ_GRID = false; // FAQ 목록 객체
	let P_QNA_GRID = false; // QNA 목록 객체


	//========================================================//
	// FORM ELEMENT 정의
	//--------------------------------------------------------//
	// FAQ 검색옵션 콤보박스
	// (comm_element.js 참고)
	$('#srchFaqType').appComboBox({
		type: 'static',
		rows: [
			{ code: '3', text: '제목' },
			{ code: '4', text: '내용' },
			{ code: '5', text: '제목+내용' }
		]
	});
	// QNA 검색옵션 콤보박스
	// (comm_element.js 참고)
	$('#srchQnaType').appComboBox({
		type: 'static',
		rows: [
			{ code: '3', text: '제목' },
			{ code: '4', text: '내용' },
			{ code: '5', text: '제목+내용' }
		]
	});
	// 검색키워드 엔터입력시 검색 이벤트 바인딩
	// (comm_utils.js 참고)
	bindEnter($('#srchFaqText'), $('#btnFaqSearch'));
	bindEnter($('#srchQnaText'), $('#btnQnaSearch'));

	//========================================================//
	// GRID (목록) 정의
	//--------------------------------------------------------//


		// 자주하는 질문 목록 (comm_board.js 참고)
	P_FAQ_GRID = $('#appFaqBoard').appBoard({
		// 검색 URL
		url: getUrl("/adm/bbs/listBbsJson.do"),
		// 모바일 표시안함
		mobile: false,
		// 페이징영역 ID
		paging: '#appFaqPage',
		// 기본검색조건
		params: {
			bbsSeCd: P_FAQ_CODE
		},
		// 칼럼 정의
		columns: [
			{ name: 'nttNo', label: '번호' ,

	             formatter: function(value,json,rowidx,colidx,page) {

		             return page.total-rowidx-((page.page-1)*10);
	             }
             },
			{ name: 'nttSj', label: '질문', align: 'left' },
			{ name: 'regYmd', label: '등록일' }
		],
		// 데이터 행 클릭시 실행함수
		select: function(row) {
			// 상세조회로 이동\

			$.formUtil.submitForm(getUrl('/adm/bbs/viewFaq.do'),
				{
					params: { nttNo: row.nttNo }
				}
			);

		},
		// 데이터로딩후 실행함수
		callback: function(cmp, elm, rows, page) {
			$('#faqTotalCount *').remove();
			let countForm = "faqTotalCount"
			totalCount(countForm, page.total);
		}
	});



	// 질의응답 목록 (comm_board.js 참고)
	P_QNA_GRID = $('#appQnaBoard').appBoard({
		// 검색 URL
		url: getUrl("/adm/bbs/listBbsJson.do"),
		// 모바일 표시안함
		mobile: false,
		// 페이징영역 ID
		paging: '#appQnaPage',
		// 기본검색조건
		params: {
			bbsSeCd: P_QNA_CODE
		},
		// 칼럼 정의
		columns: [
			{ name: 'nttNo', label: '번호',  width: '10%',
	             formatter: function(value,json,rowidx,colidx,page) {

		           return page.total-rowidx-((page.page-1)*10);
	             }
             },
			{ name: 'nttSj', label: '제목', align: 'left', width: '50%' },
			{ name: 'rgtrNm', label: '작성자', width: '10%' },
			{ name: 'regYmd', label: '등록일', width: '10%' },
			{
				name: 'status', label: '상태',width: '10%',
				// 포맷된 데이터 반환
				formatter: function(value) {
					if (value > 0)
						return '답변완료';
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
			// 상세조회로 이동\

			$.formUtil.submitForm(getUrl('/adm/bbs/viewQna.do'),
				{
					params: { nttNo:row.nttNo}
				}
			);

		},
		// 데이터로딩후 실행함수
		callback: function(cmp, elm, rows,page) {
			$('#qnaTotalCount *').remove();
			let countForm = "qnaTotalCount"
			totalCount(countForm, page.total);
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

	// 전체 수  입력
	//--------------------------------------------------------//
	function totalCount(qCount,total){

		let countForm =$("#"+qCount);
		let countRow = $('<p>총 <span>'+total+'</span>건</p>');
		countForm.append(countRow);

	}



	// 질의응답 검색버튼 클릭 이벤트 처리
	$('#btnQnaSearch').bind('click', doQnaSearch);
	// 자주하는 질문 검색버튼 클릭 이벤트 처리
	$('#btnFaqSearch').bind('click', doFaqSearch);


	// 탭클릭 이벤트 처리
	$(".tabWrap li").on("click", function() {
		var idx = $(this).index()
		$(this).parent().find("li").removeClass("on");
		$(this).addClass("on");
		$('.tabInnerFrom').removeClass("on");
		$('.tabInnerFrom').eq(idx).addClass("on");
	})

    // 2022.01.18 CSLEE 수정 : 게시판구분에 따라 활성화되는 TAB 설정
	var bbsSeCdVal   = $("#bbsSecd").val();
	var activeTabIdx = (P_FAQ_CODE == bbsSeCdVal)? 1:0;
	// 질의응답 탭 클릭 이벤트 발생
	$(".tabWrap li").eq( activeTabIdx ).trigger('click');

	// 초기검색실행
	doQnaSearch();
	doFaqSearch();

});
