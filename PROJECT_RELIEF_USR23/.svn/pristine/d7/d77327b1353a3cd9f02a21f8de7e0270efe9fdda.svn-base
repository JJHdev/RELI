/**
******************************************************************************************
*** 파일명    : viewPopupNotice.js
*** 설명      : 메인화면 팝업 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2022.10.25              JDY
******************************************************************************************
**/

function getCookie(name) {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value? value[2] : null;
}

function setCookie(name, value, exp) {
  var date = new Date();
  date.setTime(date.getTime() + exp*24*60*60*1000);
  document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
}

function closePopup(nttNo){
	if($("#ck_close_not_today_"+nttNo).is(":checked")) {
		setCookie(`notToday_win_${nttNo}`,'Y', 1);
	}
		self.close();
}
