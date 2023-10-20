$(document).ready(function(){
	header()
	main()
	layerClose()
	btnWrap()
	tabWrap()
})

function header(){
	$("header .head-wrap a.gnbView").on("click", function(){
		// $("html, body").css({overflow:"hidden"})
		$("nav.sitemap").addClass("on")
	})
	$("nav.sitemap .gnbClose").on("click", function(){
		// $("html, body").css({overflow:""})
		$("nav.sitemap").removeClass("on")
	})
	$(window).resize(function(){
		var winW = $(window).width();
		if(winW<991){
			$("nav.sitemap .gnb-wrap>ul>li>a").attr("href","#void");
		}
	})
}

//레이어창 닫기
function layerClose(){
	$(".layerPop .close").on("click",function(){
		$(this).parents(".layerPop").removeClass("on");
	})
}

//메인
function main(){
	video()
	
	function video() {
		//레이어창
		$(".videoSwiper .swiper-slide").on("click",function(){
			$(".mainLayer").addClass("on");
		})
		$(".mainLayer .close").on("click",function(){
			$(this).parents(".mainLayer").removeClass("on");
		})
	
	}
	
}

// 버튼
function btnWrap(){
	
	$(".btnWrap a").on("click", function(){
		var This = $(this);
		$(".btnWrap a").removeClass("on")
		$(this).addClass("on")
	})
	
	
}

function tabWrap(){
	
	$(".tabWrap li").on("click",function(){
		var idx = $(this).index()
		$(this).addClass("on")
		$(".tabWrap li").removeClass("on")
		$(".tabInner li").removeClass("on")
		$(".tabInner li").eq(idx).addClass("on")
	})
	
}








