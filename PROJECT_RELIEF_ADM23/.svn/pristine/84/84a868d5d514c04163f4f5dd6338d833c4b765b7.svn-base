$(document).ready(function(){
	layerClose()
	layerOpen()
	setTabs()// 탭방식 셋팅
})
// 탭방식 셋팅
function setTabs(){
    $('#tt').tabs({
        plain: $('#plain').is(':checked'),
        narrow: $('#narrow').is(':checked'),
        pill: $('#pill').is(':checked'),
        justified: $('#justified').is(':checked')
    })
}

//레이어창 열기
function layerOpen2(ClassName){
	$(".layerPop."+ClassName).addClass("on")
}

//레이어창 닫기
function layerClose(){
	$(".layerPop .close").on("click",function(){
		$(this).parents(".layerPop").removeClass("on");
	})
}

//레이어창 열기
function layerOpen(){
	
	// 환경오염피해 구제신청 끝
	$("#popupReliefApplyEnd").on("click",function(){
		
		var params = {
			title : '나에게 맞는 피해구제제도찾기가 종료되었습니다.',
			url : '/WebContent/popupReliefApplyEnd.html',
		};
		popup.open(params);
	});
	// 환경오염피해 구제신청
	$("#popupReliefInfo_01").click(function(){
		
		var params = {
			title : '환경오염 피해구제 제도란?',
			url : '/WebContent/popupReliefInfo_01.html',
		};
		popup.open(params);
	});
	//취약계층 소송지원신청
	$("#popupReliefInfo_02").click(function(){
		
		var params = {
			title : '취약계층 소송지원신청',
			url : '/WebContent/popupReliefInfo_02.html',
		};
		popup.open(params);
	});
	//지자체 민원 신청이란?
	$("#popupReliefInfo_03").click(function(){
		
		var params = {
			title : '지자체 민원 신청이란?',
			url : '/WebContent/popupReliefInfo_03.html',
		};
		popup.open(params);
	});
	//건강영향조사 청원이란?
	$("#popupReliefInfo_04").click(function(){
		
		var params = {
			title : '건강영향조사 청원이란?',
			url : '/WebContent/popupReliefInfo_04.html',
		};
		popup.open(params);
	});
	//분쟁조정 신청이란?
	$("#popupReliefInfo_05").click(function(){
		
		var params = {
			title : '분쟁조정 신청이란?',
			url : '/WebContent/popupReliefInfo_05.html',
		};
		popup.open(params);
	});
	
	// 개인정보동의
	$("#popupReliefApply_01").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupReliefApply_01.html',
		};
		popup.open(params);
	});
	// 
	$("#popupReliefApply_02").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupReliefApply_02.html',
		};
		popup.open(params);
	});
	// 구제 제출완료
	$("#popupReliefApply_03").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupReliefApply_03.html',
		};
		popup.open(params);
	});
	
	$("#popupVulnerable_01").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupVulnerable_01.html',
		};
		popup.open(params);
	});
	
	$("#popupVulnerable_02").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupVulnerable_02.html',
		};
		popup.open(params);
	});
	$("#popupVulnerable_03").click(function(){
		
		var params = {
			title : '해당요건 먼저 선택해주세요',
			url : '/WebContent/popupVulnerable_03.html',
		};
		popup.open(params);
	});
	$("#popupQnaLock").click(function(){
		
		var params = {
			title : '비밀번호 입력',
			url : '/WebContent/popupQnaLock.html',
		};
		popup.open(params);
	});
	$("#popupMemberQnaLock").click(function(){
		
		var params = {
			title : '비밀번호 입력',
			url : '/WebContent/popupMemberQnaLock.html',
		};
		popup.open(params);
	});
	$("#popupQnaSuccess").click(function(){
		
		var params = {
			title : '게시글 등록이 완료되었습니다.',
			url : '/WebContent/popupQnaSuccess.html',
		};
		popup.open(params);
	});
	$("#popupQnaWriteSuccess").click(function(){
		
		var params = {
			title : '질의응답 등록이 완료되었습니다.',
			url : '/WebContent/popupQnaWriteSuccess.html',
		};
		popup.open(params);
	});
	$("#popupMemberQnaWriteSuccess").click(function(){
		
		var params = {
			title : '질의응답 등록이 완료되었습니다.',
			url : '/WebContent/popupMemberQnaWriteSuccess.html',
		};
		popup.open(params);
	});
	
	$("#popupLoginPhone").click(function(){
		
		var params = {
			title : '휴대전화 인증',
			url : '/WebContent/popupLoginPhone.html',
		};
		popup.open(params);
	});
	
	$("#popupLoginResident").click(function(){
		
		var params = {
			title : '주민등록번호 인증',
			url : '/WebContent/popupLoginResident.html',
		};
		popup.open(params);
	});
	
	$("#popupMemberEnd_01").click(function(){
		
		var params = {
			title : '회원탈퇴 확인',
			url : '/WebContent/popupMemberEnd_01.html',
		};
		popup.open(params);
	});
	$("#popupMemberEnd_02").click(function(){
		
		var params = {
			title : '탈퇴가 완료되었습니다.',
			url : '/WebContent/popupMemberEnd_02.html',
		};
		popup.open(params);
	});
	$("#popupMemberReliefPay").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupMemberReliefPay.html',
		};
		popup.open(params);
	});
	$("#popupMemberInterLock_01").click(function(){
		
		var params = {
			title : '식별아이디 찾기',
			url : '/WebContent/popupMemberInterLock_01.html',
		};
		popup.open(params);
	});
	$("#popupMemberInterLock_02").click(function(){
		
		var params = {
			title : '',
			url : '/WebContent/popupMemberInterLock_02.html',
		};
		popup.open(params);
	});
	// 회원가입::아이디체크
	$("#idCheck").click(function(){
		
		var params = {
			title : '아이디 중복확인',
			url : '/WebContent/idCheck.html',
		};
		popup.open(params);
	});
	// 회원가입::완료
	$("#joinSuccess").click(function(){
		
		var params = {
			title : '회원가입이 완료되었습니다.',
			url : '/WebContent/joinSuccess.html',
		};
		popup.open(params);
	});
	
	/***********************************************************************/
	/*********** 관리자 *****************************************************/
	/***********************************************************************/
	// 등록파일 상세보기
	$("#popupAdminMain").on("click",function(){
		
		var params = {
			title : '구제급여 신청서.pdf',
			url : '/WebContent/popupAdminMain.html',
		};
		popup.open(params);
	});
	
	// 선택 접수
	$("#popupAdminReceipt_01").on("click",function(){
		
		var params = {
			title : '선택 접수',
			url : '/WebContent/popupAdminReceipt_01.html',
		};
		popup.open(params);
	});
	// 이력 등록
	$("#popupAdminReceipt_02").on("click",function(){
		
		var params = {
			title : '이력 등록',
			url : '/WebContent/popupAdminReceipt_02.html',
		};
		popup.open(params);
	});
	// 이력 조회
	$("#popupAdminReceipt_03").on("click",function(){
		
		var params = {
			title : '이력 조회',
			url : '/WebContent/popupAdminReceipt_03.html',
		};
		popup.open(params);
	});
	// 개별 보완 요청
	$("#popupAdminReceipt_04").on("click",function(){
		
		var params = {
			title : '개별 보완 요청',
			url : '/WebContent/popupAdminReceipt_04.html',
		};
		popup.open(params);
	});
	// 개별 접수
	$("#popupAdminReceipt_05").on("click",function(){
		
		var params = {
			title : '개별 접수',
			url : '/WebContent/popupAdminReceipt_05.html',
		};
		popup.open(params);
	});
	// 예비조사 등록
	$("#popupPrptExmn").on("click",function(){
		
		var params = {
			title : '예비조사 등록',
			url : '/WebContent/popupPrptExmn.html',
		};
		popup.open(params);
	});
	
	// 구제급여 총계
	$("#popupAdminReliefPayback_01").on("click",function(){
		
		var params = {
			title : '구제급여 총계',
			url : '/WebContent/popupAdminReliefPayback_01.html',
		};
		popup.open(params);
	});
	// 구상권 및 환수 정보 상세 및 수정
	$("#popupAdminReliefPayback_02").on("click",function(){
		
		var params = {
			title : '구상권 및 환수 정보 상세 및 수정',
			url : '/WebContent/popupAdminReliefPayback_02.html',
		};
		popup.open(params);
	});
	// 삭제하시겠습니까?
	$("#popupAdminReliefPayback_03").on("click",function(){
		
		var params = {
			title : '삭제하시겠습니까?',
			url : '/WebContent/popupAdminReliefPayback_03.html',
		};
		popup.open(params);
	});
	// 소송지원::지역검색
	$("#popupAdminSearchArea").on("click",function(){
		var params = {
			title : '삭제하시겠습니까?',
			url : '/WebContent/popupAdminSearchArea.html',
		};
		popup.open(params);
	});
	
	// 소송지원::신청인검색
	$("#popupAdminSearchPeople").on("click",function(){
		
		var params = {
			title : '삭제하시겠습니까?',
			url : '/WebContent/popupAdminSearchPeople.html',
		};
		popup.open(params);
	});
	
	
	
	
	
	

}

