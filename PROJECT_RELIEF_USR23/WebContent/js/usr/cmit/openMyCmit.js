/**
*******************************************************************************
*** 파일명 : openMyCmit.js
*** 설명글 : 온라인위원회 - 위원회 현황 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
***    ver      date          author                  description
*** ---------------------------------------------------------------------------
***    3.0      2023.10.19    LSH
*******************************************************************************
**/
$(function() {
	
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	const P_FORM  = $('#searchForm'); // 검색폼
	let   P_GRID  = false; // 목록 객체
	const FORMAT  = {
		// 위원구분
		charmnYn: function(v) {
			return (v == 'Y' ? '위원장' : '일반');
		}
	};

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	// 위원회구분 체크박스
	$('#appCmitSeCd').appSelectBox({
		form:    'checkbox',
		name:    'cmitSeList', 
		params:  {upCdId: CODE.CMITSE}
	});
	// 지역구분 체크박스
	$('#appBizAreaCd').appSelectBox({
		form:    'checkbox',
		name:    'bizAreaList', 
		url: getUrl('/com/cmm/getComboBizMng.do'),
		callback: function(cmp) {
			// 기타 항목 추가
			cmp.addOption({code:'ETC',text:'기타'});
		}
	});
	// 진행상태 라디오박스
	$('#appStusCd').appSelectBox({
		form:    'radio',
		name:    'srchStusCd',
		type:    'static',
		rows:    STORE.PRGS_YN,
		init:    RADIO.INIT_ALL
	});
	// 날짜입력 자동하이픈 처리
	bindDateHyphen($('#srchOpmtStdt').datebox('textbox'));
	bindDateHyphen($('#srchOpmtEndt').datebox('textbox'));
	
	// 위원회목록 (comm_board.js 참고)
	P_GRID = $('#appCmitGrid').appBoard({
		// 검색 URL
		url: getUrl("/usr/cmit/getListMyCmit.do"),
		// 칼럼 정의
		columns: [
			{name: 'cmitSeNm'    , label: '위원회 구분'},
			{name: 'cmitOder'    , label: '위원회 차수'},
			{name: 'charmnYn'    , label: '위원구분', formatter: FORMAT.charmnYn},
			{name: 'opmtBgngYmd' , label: '시작일'},
			{name: 'opmtEndYmd'  , label: '종료일'},
			{name: 'agndCnt'     , label: '안건수'},
			{name: 'prgreStusNm' , label: '진행상태'},
		],
		// 페이징영역 ID
		paging: '#appCmitPage',
		// 기본검색조건
		params: {rows: 10},
		// 행선택처리
		select: doSelect
	});

    // 초기실행
    //--------------------------------------------------------//
	doSearch();
	
    // 위원회목록 검색처리
    //--------------------------------------------------------//
	function doSearch() {
		// 검색조건 객체화 (comm_utils.js 참고)
		let params = P_FORM.serializeObject();
		// 목록 데이터로드 (comm_board.js 참고)
		P_GRID.load(params);
	}
	
    // 위원회정보 가져오기
    //--------------------------------------------------------//
	function doSelect(row) {
		$.formUtil.submitForm(getUrl("/usr/cmit/viewMyCmit.do"), {
			params: {
				cmitMngNo : row['cmitMngNo'],
				tenureNo  : row['tenureNo' ]
			}
		});
	}
	
	// 검색버튼 클릭 이벤트 처리
	$('#btnSearch').bind('click', doSearch);
	
});
