/**
******************************************************************************************
*** 파일명 : joinAgree.js
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

});

function joinAgree(){
        
        //체크박스 체크여부 확인
		var useAgree = $('input:radio[id="useAgree"]')
		var essenAgree = $('input:radio[id="essenAgree"]')
		var indvInfoClctAgreYn = $('input:radio[name="indvInfoClctAgreYn"]')
		var thptyPvsnAgreYn = $('input:radio[name="thptyPvsnAgreYn"]')
		
		console.log(indvInfoClctAgreYn.val());
        
		 var inval_Arr = false;
	
		if(useAgree.is(":checked") == false) {
	        inval_Arr[0] = false;
 		alert('이용규정 동의 여부를 확인해주세요.');
	        return false;
	     } else {
			inval_Arr[0] = true;
	 	}
	
	    if(essenAgree.is(":checked") == false) {
	        inval_Arr[1] = false;
 			alert('개인정보 수집 및 이용 필수항목에 동의하지 않는경우 회원가입을 하실 수 없습니다.');
	        return false;
	     } else {
			inval_Arr[1] = true;
	 	}
	
		if(indvInfoClctAgreYn.is(":checked") == false) {
	        inval_Arr[2] = false;
 		alert('개인정보 수집 및 이용에 대한 선택항목 동의여부를 확인해주세요.');
	        return false;
	     } else {
			inval_Arr[2] = true;
	 	}
	
		if(thptyPvsnAgreYn.is(":checked") == false) {
	        inval_Arr[3] = false;
 		alert('개인정보의 제 3자 제공 동의 여부를 확인해주세요.');
	        return false;
	     } else {
			inval_Arr[3] = true;
	 	}

         //전체 유효성 검사
         var validAll = true;
         for(var i = 0; i < inval_Arr.length; i++){
            if(inval_Arr[i] == false){
               validAll = false;
            }
         }
    
         if(validAll == true){ // 유효성 모두 통과
          $.formUtil.submitForm(getUrl('/usr/cmm/openAgree.do'), {
//			params :{
				indvInfoClctAgreYn :$('.indvInfoClctAgreYn').val(),
				thptyPvsnAgreYn : $('.thptyPvsnAgreYn').val()
//			}
		});  
         } else{
            alert('정보를 다시 확인하세요.')
            return false;
         }
   }