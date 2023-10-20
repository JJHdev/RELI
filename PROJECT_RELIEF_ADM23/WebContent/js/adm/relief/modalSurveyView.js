/**
******************************************************************************************
***	명칭: modalSurveyView.js
***	설명: 구제급여신청 - 온라인설문지 상세팝업
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------

******************************************************************************************
**/


$(function(){
	
	function doReport() {
		// 폼데이터의 객체화
		let data = $('#surveyForm').serializeObject();

		let params = {
			mode       : 'SURVEY',           // 리포트타입 (설문지)
			aplyNo     : data['aplyNo'    ], // 신청번호 
			rspnsMngNo : data['rspnsMngNo'], // 답변관리번호
			qstnnMngNo : data['qstnnMngNo'], // 설문관리번호
			signCn     : data['signCn'    ], // 서명파일명(경로포함)
			rgtrNm     : data['rgtrNm'    ], // 신청인이름
			regDate    : data['regDate'   ]  // 작성일자(yyyy년 mm월 dd일)
		};
		// 리포트뷰어 팝업 호출
		popup.openReportPopup(params);
		return false;
	}
	// 공문양식으로인쇄 클릭시 리포팅툴 이벤트처리
	$('#btnSurveyPrint').bind('click', doReport);
});