/**
******************************************************************************************
*** 파일명    : main.js
*** 설명      : 사용자 메인 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.08.05              LSH
*** 2.0         2022.11.24              JDY				   메인화면 공지사항 팝업창
******************************************************************************************
**/
$(function() {

	// 처리결과 조회하기
	$(".searchIdNTFC").on("click", function(){
		let dom = $(this).closest('form').find('input.idntfcId');
		$.formUtil.submitForm( 
			getUrl('/com/cmm/login.do'),
			{params: { act: MODE.MAIN, idntfcId: dom.val() }}
		);
		return false;
	});

	const swiper = new Swiper('.swiper', {
		slidesPerView: 3,
		speed: 400,
		loop: true,
		spaceBetween: 10,
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
		pagination: {
			el: ".swiper-pagination",
			dynamicBullets: true,
		},
		breakpoints: {
			991: {
				slidesPerView: 1,
			},
		},
	});
	const swiper2 = new Swiper('.videoSwiper', {
		effect: 'fade',
		loop: true,
		pagination: {
          el: ".swiper-pagination",
          clickable: true,
          renderBullet: function (index, className) {
            return '<span class="' + className + '">' + (index + 1) + "</span>";
          },
        },
	});
	
	// 조회하기 처음에"온"
	$(".swiper-slide").on("mouseenter", function(){
		$(this).removeClass("on");
	})
	
	//팝업 구현
	openLayerPopup();
	//openWindowPopup();
});

function openLayerPopup(){
	
	// 팝업공지 전체 리스트 조회
	const strHtmlContents = $.ajaxUtil.ajaxHtml(getUrl('/usr/cmm/modalNotice.do'));
	$("#popupDiv").append(strHtmlContents);

	let n = 1;
	
	//1.for:모든 레이어 팝업 조회
	$(".divpopLayer").each(function(i, item) {
		
		var popSqr 	= item.dataset.popupSqr,
			popHght = item.dataset.popupHght;
		
		const cacheName = 'notToday_'+item.dataset.nttNo;
		
		// 2.if:캐시가 없는 레이어만 호출
		if($.getCookie(cacheName)!="Y" || $.getCookie(cacheName) == null) {
			$('#popup_box_'+item.dataset.nttNo).show();

			$(item).css("width", popSqr);
			//콘텐츠 높이에 맞게 팝업창 가변
			$(item).css("height","auto !important");
			//다중 팝업일 경우 계층 형태 margin-left:-100px;
			$(item).css('inset', (70+(n*40))+'px auto auto ' +(250+(n*40)) +'px');
			n++;

		} else {
			$('#popup_box_'+item.dataset.nttNo).hide();
		}

	});
	
	$('.divpopLayer').draggable({handle:'.window_topslice'});
}

function openWindowPopup() {

	// 팝업공지 전체 리스트 조회
	const data = $.ajaxUtil.ajaxDefault(getUrl('/usr/cmm/getPopupNoticeInfo.do'), null);
	
	data.result.forEach(function(item, i) {
		
		let nttNo = item.nttNo;

		if($.getCookie('notToday_'+item.nttNo)=="Y") {
			return;	
		}
		
		var w = screen.width;
		var h = screen.height;
		
		//팝업창 크기
		const xPos = item.popupSqr;
		const yPos = item.popupHght;
		
		var pop_name = 'popup'+nttNo;
		var popup = window.open(getUrl('/usr/cmm/viewPopupNotice.do?nttNo='+nttNo), pop_name, "width="+xPos+", height="+yPos+", left="+w/2.7+", top="+h/4+", menubar=yes, status=yes, alwaysReised=yes, titlebar=yes, resizable=yes");
	
		//팝업 최상단 노출
		pop_name = window.top;
	});
}

function closePopup(nttNo) {
	if ($("#ck_close_not_today_"+nttNo).is(":checked")) {
		$.setCookie('notToday_'+nttNo, 'Y', 1);
	} 
	$("#popup_box_"+nttNo).hide();
}


