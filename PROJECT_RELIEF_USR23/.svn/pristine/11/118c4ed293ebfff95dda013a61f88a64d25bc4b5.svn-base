/**
******************************************************************************************
*** 파일명    : comm_usr.js
*** 설명      : 사용자시스템 공통스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-07-15              LSH
******************************************************************************************
**/
$(document).ready(function() {
	// 상단네비바 로딩 (comm_biz.js 참고) 
	loadTopBar('.topnavbar', getUrl('/com/cmm/listMenu.do'), {sysCd: SYSTEM.USER['code']});
});

//=======================================================================//
// 2021.12.08 LSH 탭형 서브메뉴 표시
//-----------------------------------------------------------------------//
var createTabMenu = function(tabElm, tabList, tabCls) {

	if (!tabElm)
		return;
	let curl = $(location).attr("pathname");
	
	if (tabCls)
		tabElm.addClass(tabCls);
		
	let ul = $('<ul class="li-'+tabList.length+'"></ul>');	
	$.each(tabList, function(i,t) {
		let li = $('<li><a></a></li>');
		li.find('a').append(t.title);
		li.find('a').prop('href', t.url);
		if (t.url == curl)
			li.addClass("on");
		ul.append(li);
	});
	tabElm.append(ul);
};
