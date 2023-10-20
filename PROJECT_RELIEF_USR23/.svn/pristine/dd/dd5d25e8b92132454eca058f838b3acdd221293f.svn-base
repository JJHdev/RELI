/**
******************************************************************************************
*** 파일명 : openAgree.js
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

function initIdmember(){
	/*  기본 이벤트 등록 : 검색 버튼 키다운*/
	$(".adrBtn1").click(function(e){
		var enterKeyCode = 13;		
		var element = $(this);
		var elName = element.attr("name");
		if(e.keyCode == enterKeyCode){
			console.log(elName);
			switch(elName){
			case "keyword":	
			fnIdMember();
			break;
				default:
				
			}
		}
	})
}

function fnIdMember(){
	
	var sufrerNm = $('#sufrerNm').val();
	var sufrerMbtelNo = $('#sufrerMbtelNo').val();
	var sufrerRrno = $('#sufrerRrno').val();
	var sufrerRrno1 = $('#sufrerRrno1').val();
	var sufrerRrno2 = $('#sufrerRrno2').val();
	
	if(sufrerNm.trim() == ""){
		alert("이름을 확인해주세요.");
		$('#sufrerNm').focus();
		return false;
	}
	else if(sufrerRrno1.trim() == ""){
		alert("주민등록번호를 확인해주세요.");
		$('#sufrerRrno1').focus();
		return false;
	}
	
	else if(sufrerRrno2.trim() == ""){
		alert("주민등록번호를 확인해주세요.");
		$('#sufrerRrno2').focus();
		return false;
	}
	else if(sufrerMbtelNo.trim() == ""){
		alert("휴대전화 번호를 확인해주세요.");
		$('#sufrerMbtelNo').focus();
		return false;
	}
		
	// 조회결과 테이블객체
	var searchedTable = $('#searchedTable');
	// 조회결과 리셋
	searchedTable.find('tbody').html('');
		
	//주민등록번호 병합
	$('#sufrerRrno').val('');	
	if ($('#sufrerRrno1').val() != '' &&
		$('#sufrerRrno2').val() != '' ) {
		$('#sufrerRrno').val(
			$('#sufrerRrno1').val() +
			$('#sufrerRrno2').val() 
		);
	}

	// 1. ajaxDefault를 사용할 경우 (동기식 처리)
	var result = $.ajaxUtil.ajaxDefault(
		getUrl("/usr/mypage/searchInfoIntrlck.do"), {
			sufrerNm : sufrerNm,
			sufrerMbtelNo : sufrerMbtelNo,
			sufrerRrno :  $('#sufrerRrno').val()
		}
	);
		
	if (result && result.Data) {
		var listInfoIntrlck = result.Data;
		// 식별아이디가 없을 경우
		if (listInfoIntrlck.length == 0) {
			// 에러 메세지 표시
			searchedTable.find('tbody').append('<p class="err pt40">등록된 식별 아이디가 없습니다.</p>');
		}
		// 식별아이디가 있을 경우
		else {
			// 데이터 배열 LOOP
			$.each(listInfoIntrlck, function(index, item) {
				
				// 버튼 객체정의
				let btn = $('<button class="checkBtn"></button>');
				// 식별아이디를 버튼의 텍스트로 넣는다.
				btn.append(item.idntfcId);
				// 버튼에 데이터정의 (KEY는 반드시 소문자로만 되어있어야함)
//				btn.data('brdt'  , item.sufrerBrdt); // 생년월일
				
				btn.data('bry'  , item.bryy); // 생년월일
				btn.data('brm'  , item.brmm); // 생년월일
				btn.data('brd'  , item.brdd); // 생년월일
				btn.data('name'  , item.sufrerNm  ); // 성명
				btn.data('idntfc', item.idntfcId  ); // 식별ID
				btn.data('mbtelNo', item.sufrerMbtelNo  ); // 휴대전화번호
				// 버튼클릭시 selectIdMember로 이벤트 바인딩 처리
				btn.bind('click', selectIdMember);

				// 단락정의
				let p = $('<p class="pt40" align="center"></p>');
				// 객체단위로 append되어야 한다.
				p.append('등록된 식별 아이디는 ');
				p.append(btn);
				p.append(' 입니다');
				
				// 행단위정의
				let tr = $('<tr></tr>');
				tr.append(p);
				searchedTable.find('tbody').append(tr);
			});			
		}
	}
}
	
function selectIdMember() {
	// 버튼에서 정의한 데이터를 가져온다.
	let bryy   = $(this).data('bry');
	let brmm   = $(this).data('brm');
	let brdd   = $(this).data('brd');
	
	let sufrerNm   = $(this).data('name');
	let idntfcId   = $(this).data('idntfc');
	let sufrerMbtelNo   = $(this).data('mbtelNo');
	
	
	$('#trprIdntfcId').val(idntfcId);
	$('#trprNm').val($('#sufrerNm').val());
	$('#mbtelNo').val($('#sufrerMbtelNo').val());
	
	$('#bryy').val(bryy);
	$('#brmm').val(brmm);
	$('#brdd').val(brdd);
	
	console.log(bryy);
	console.log(brmm);
	console.log(brdd);
	
	$('#infoMemberId').modal("hide");
	
}


$(function() {
	
	//========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	const P_PDTY_CD   = $('#papeDtySeCd').val(); // 서류업무구분
	const P_APLY_CD   = $('#aplySeCd'   ).val(); // 신청구분
	let P_LIST   = [];                // 신청서류 파일객체 배열
	let P_VALID  = [];                // 신청서류 유효한 파일객체
	let P_PAPE   = $('#papeGroup'  ); // 신청서류영역 객체
	
	// FORM 검증
	function doValidate() {

        var intrlckSeCd = $('input:radio[name="intrlckSeCd"]');
		var trprNm  = $('#trprNm');
		var trprIdntfcId = $('#trprIdntfcId');
		var mbtelNo = $('#mbtelNo');
		var bryy = $('#bryy');
		var brmm = $('#brmm');
		var brdd = $('#brdd');
		var message = "";
		
        var inval_Arr = false;

	     //연동대상
	     if(intrlckSeCd.is(":checked") == false) {
	        inval_Arr[0] = false;
	       alert('연동대상을 선택해주세요');
	        return false;
	     } else {
			inval_Arr[0] = true;
	 	}
	
	     //연동대상 성명
	     if(trprNm.val() == ''){
	        inval_Arr[1] = false;
	        alert('연동대상자 성명을 확인하세요.');
	        return false;
	     } else {
	        inval_Arr[1] = true;
	 	}
	
		 //연동대상 식별아이디 확인
	     if(trprIdntfcId.val() == ''){
	        inval_Arr[2] = false;
	        alert('연동대상자 식별아이디를 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[2] = true;
		}
	
	     //연동대상자 휴대전화번호 확인
	     if(mbtelNo.val() == ''){
	        inval_Arr[3] = false;
	        alert('연동대상자 휴대전화번호를 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[3] = true;
		}
		
		 //생년월일 확인
	     if(bryy.val() == ''){
	        inval_Arr[4] = false;
	        alert('연동대상자 생년월일을 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[4] = true;
		}
		
		 //생년월일 확인
	     if(brmm.val() == ''){
	        inval_Arr[5] = false;
	        alert('연동대상자 생년월일을 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[5] = true;
		}
		
		 //주소 확인
	     if(brdd.val() == ''){
	        inval_Arr[6] = false;
	        alert('연동대상자 생년월일을 확인하세요.');
	        return false;
	     }else {
	        inval_Arr[6] = true;
		}
	
	     //전체 유효성 검사
	     var validAll = true;
	     for(var i = 0; i < inval_Arr.length; i++){
	        if(inval_Arr[i] == false){
	           validAll = false;
	        }
	     }
	
	     if(validAll == true){ // 유효성 모두 통과
			return true;      
	     } else{
	        alert('정보를 다시 확인하세요.')
			return false;
	     }
	}
	
	// 정보연동 신청
    //--------------------------------------------------------//
    function doSave() {

		//생년월일 병합
		$('#trprBrdt').val('');
		if ($('#bryy').val() != '' &&
			$('#brmm').val() != '' &&
			$('#brdd').val() != '' ) {
			$('#trprBrdt').val(
				$('#bryy').val() + 
				$('#brmm').val() +
				$('#brdd').val() 
			);
		}

		
		// FORM 검증결과가 실패이면
		if (doValidate() === false) {
			return false;
		}

		// 저장데이터 객체화
		let data = $('#registForm').serializeObject();
		$.extend(data, {
			saveFiles:   [],
			removeFiles: []
		});
		// FILE VALIDATION
		let check = true;
		$.each(P_LIST, function(i, file) {
			if (file.doValidate() === false) {
				check = false;
				return false;
			}
			let saveData = file.getSaveData();
			// 저장대상파일 배열 합치기
			$.merge(data['saveFiles'  ], saveData['saveFiles'  ]);
			// 삭제대상파일 배열 합치기
			$.merge(data['removeFiles'], saveData['removeFiles']);
		});
		if (check === false)
			return false;

        $.commMsg.confirm("정보연동신청을 진행하시겠습니까?", function() {
            // AJAX로 저장처리 
            // (파일데이터를 넘기기 위해 ajaxSave로 처리)
            $.ajaxUtil.ajaxSave(
                getUrl('/usr/mypage/regiInfoIntrlck.do'), 
                JSON.stringify(data),
                function(ret) {
					$.commMsg.success(ret, '연동신청이 완료되었습니다.', function() {
						// 저장완료 후 메인으로 마이페이지로 이동처리
						goUrl(getUrl('/usr/mypage/openInfoIntrlck.do'));
					});
                }
            );
        });
        return false;
    }

	
    // 최초 로딩시 정보가져오기
    //--------------------------------------------------------//
	function doInit() {
			doLoadPapeGroup();
	}
	
	
	function doLoadPapeGroup( data ) {
		
		// 신청서류그룹 가져오기
		const rows = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/file/getListPapeGroup.do'), {
				papeDtySeCd: P_PDTY_CD,
				aplySeCd:    P_APLY_CD
			}
		);
		
		if (rows) {
			$.each(rows, function(i, row) {
				let div = $('<div class="usr-file-list"></div>');
				div.data('code', row['papeCd']);
				div.data('name', row['papeNm']);
				P_LIST.push(div.appAplyFile({
					mode:  MODE.INSERT,
					title: row['papeNm'],
					// 서류목록조건
					params: {
						// 업무구분
						papeDtySeCd: P_PDTY_CD,
						// 신청구분
						aplySeCd: P_APLY_CD,
						// 서류그룹
						upPapeCd: row['papeCd'],
						// (수정시) 문서번호
						dcmtNo:     null,
						// (수정시) 세부문서번호
						dtlDcmtNo:  null
					}
				}));
				P_PAPE.append(div);
			});
		}
	}	

	// 최초 데이터로딩
	doInit();
	// 저장버튼 클릭시 이벤트 처리
    $('#btnSave').bind('click', doSave);

});
