/**
******************************************************************************************
*** 파일명 : viewStatute.js
*** 설명글 : [자료실] 법규정 상세화면 스크립트
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
	var P_VFORM = $('#selectForm');       // 상세폼 객체
	var P_FILE_TYPE = 'BOARD';            // 첨부파일 종류
	var P_FILE_VIEW = $('#attachFile').appBbsFile();// 첨부파일 조회목록영역

	// 게시글 키정보
	var P_NO = P_VFORM.find('input[name="nttNo"]').val();

	//========================================================//
	// 첨부파일 목록조회 (comm_component.js 참고)
	//--------------------------------------------------------//
	doSearchFile();

	// 첨부파일 목록조회
	//--------------------------------------------------------//
	function doSearchFile() {
		// 첨부파일 목록 생성
		P_FILE_VIEW.select({
			// 파일목록조회 조건객체
			params: {
				fileType: P_FILE_TYPE,
				dcmtNo: P_NO
			},
			// 파일목록조회 URL
			loadUrl: getUrl('/usr/bbs/listBbsFile.do'),
			// 파일다운로드 URL
			downloadUrl: getUrl('/usr/bbs/downBbsFile.do'),
		});
		return false;
	}
	// 목록이동
	//--------------------------------------------------------//
	function goList() {
		$.formUtil.submitForm(getUrl("/usr/bbs/listStatute.do"), {
			formId: "searchForm"
		});
		return false;
	}
	//========================================================//
	// 버튼 이벤트 처리
	//--------------------------------------------------------//
	// 목록버튼 클릭시 이벤트 처리
	$('#btnList').bind('click', goList);

});