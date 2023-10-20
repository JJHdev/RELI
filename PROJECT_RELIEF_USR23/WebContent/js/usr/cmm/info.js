/**
******************************************************************************************
*** 파일명 : info.js
*** 설명글 : 사용자정보 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0    gjhan     
******************************************************************************************
**/
$(function() {

	// 메뉴경로 숨김
	if ($('section.sub-visual'))
		$('section.sub-visual').hide();
		
   $("#PERSINFO01").on("click", function(){
      $.fileUtil.download({
         params: {fType:'T3'},
         url   : getUrl("/com/cmm/downFormFile.do")
      });
      return false;
      
   });

   $("#PERSINFO02").on("click", function(){
      $.fileUtil.download({
         params: {fType:'T4'},
         url   : getUrl("/com/cmm/downFormFile.do")
      });
      return false;
      
   });

   $("#PERSINFO01_1").on("click", function(){
      $.fileUtil.download({
         params: {fType:'T3'},
         url   : getUrl("/com/cmm/downFormFile.do")
      });
      return false;
      
   });

   $("#PERSINFO02_2").on("click", function(){
      $.fileUtil.download({
         params: {fType:'T4'},
         url   : getUrl("/com/cmm/downFormFile.do")
      });
      return false;
      
   });
   
   $("#PERSINFO03_1").on("click", function(){
      $.fileUtil.download({
         params: {fType:'T5'},
         url   : getUrl("/com/cmm/downFormFile.do")
      });
      return false;
      
   });

   $("#PERSINFO03_2").on("click", function(){
      $.fileUtil.download({
         params: {fType:'T6'},
         url   : getUrl("/com/cmm/downFormFile.do")
      });
      return false;
      
   });

   // 개인정보팝업
   $("[id^=popupPerinfo]").click(function() {
		var url = getUrl('/usr/pinfo/popupPerinfo.do');
		var pageinfo = $(this).data('pageinfo');
		url = url + "?pageinfo="+pageinfo;
		
		//var idVal  = $(this).attr('id');		
		//console.log('id = '+idVal );		
		//console.log('pageinfo = '+pageinfo);
		
		window.open(url,'_blank', 'width=800,height=600,toolbars=no,scrollbars=yes');
	});   
   
 }); 

