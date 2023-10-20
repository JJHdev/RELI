/**
******************************************************************************************
*** 파일명 : openLwst.js
*** 설명글 : 취약계층 소송지원 신청 1단계 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    한금주
******************************************************************************************
**/
$(function() {
	
	//========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_RFORM  = $('#registForm' ); // 등록폼 객체
	const P_APLY_NO   = $('#aplyNo').val(); // 신청번호
	const P_PDTY_CD   = $('#papeDtySeCd').val(); // 서류업무구분
	const P_APLY_CD   = $('#aplySeCd'   ).val(); // 신청구분
	const P_APLY_ODER = $('#aplyOder'   ).val(); // 신청차수
	let P_LIST   = [];                // 신청서류 파일객체 배열
	let P_VALID  = [];                // 신청서류 유효한 파일객체
	let P_PAPE   = $('#papeGroup'  ); // 신청서류영역 객체

	// 신청인유선전화번호 국번 콤보박스 
	$('#aplcntTelNo1').appComboBox({
		params: {upCdId: CODE.PHONE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 유선전화번호 분리
			$.formUtil.splitData('aplcntTelNo','phone');
		}
	});
	
	// 신청인휴대전화번호 국번 콤보박스
	$('#aplcntMbtelNo1').appComboBox({
		params: {upCdId: CODE.MOBILE},
		callback: function() {
			$.formUtil.splitData('aplcntMbtelNo','mobile');
		}
	});
	
	// 생년월일 콤보박스
	$('#aplcntBrdt1').appComboBox({
		type: 'static',
		init: {code:'',text:'년'},
		rows: STORE.getYears(-10),
		callback: function() {
			$.formUtil.splitData('aplcntBrdt','date');
		}
	});
	
	$('#aplcntBrdt2').appComboBox({
		type: 'static',
		init: {code:'',text:'월'},
		rows: STORE.getMonths(),
		callback: function() {
			$.formUtil.splitData('aplcntBrdt','date');
		}
	});
	
	$('#aplcntBrdt3').appComboBox({
		type: 'static',
		init: {code:'',text:'일'},
		rows: STORE.getDays(),
		callback: function() {
			$.formUtil.splitData('aplcntBrdt','date');
		}
	});

	// 이메일 도메인 콤보박스
	$('#aplcntEmlDomain').appComboBox({
		params: {upCdId: CODE.EMAIL},
		init:   COMBO.INIT_DIRECT,		
		// 콤보 값변경시 입력처리
		change: function() {
			let domain   = $('#aplcntEmlAddr2');
			let newValue = $(this).find('option:checked').val();
			let newLabel = $(this).find('option:checked').text();
			if (newValue == '') {
				domain.val('');
				domain.prop('readonly', false);
			}
			else {
				domain.val(newLabel);
				domain.prop('readonly', true);
			}
		}
	});
	
	// 피신청인이메일 도메인 콤보박스
	$('#respdntEmlDomain').appComboBox({
		params: {upCdId: CODE.EMAIL},
		init:   COMBO.INIT_DIRECT,		
		// 콤보 값변경시 입력처리
		change: function() {
			let domain   = $('#respdntEmlAddr2');
			let newValue = $(this).find('option:checked').val();
			let newLabel = $(this).find('option:checked').text();
			if (newValue == '') {
				domain.val('');
				domain.prop('readonly', false);
			}
			else {
				domain.val(newLabel);
				domain.prop('readonly', true);
			}
		}
	});
	
	// 피신청인유선전화번호 국번 콤보박스
	$('#respdntTelno1').appComboBox({
		params: {upCdId: CODE.PHONE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 유선전화번호 분리
			$.formUtil.splitData('respdntTelno','mobile');
		}
	});
	
	// 피신청인 생년월일 콤보박스
	$('#respdntBrdt1').appComboBox({
		type: 'static',
		init: {code:'',text:'년'},
		rows: STORE.getYears(-10),
		callback: function() {
			$.formUtil.splitData('respdntBrdt','date');
		}
	});
	
	$('#respdntBrdt2').appComboBox({
		type: 'static',
		init: {code:'',text:'월'},
		rows: STORE.getMonths(),
		callback: function() {
			$.formUtil.splitData('respdntBrdt','date');
		}
	});
	
	$('#respdntBrdt3').appComboBox({
		type: 'static',
		init: {code:'',text:'일'},
		rows: STORE.getDays(),
		callback: function() {
			$.formUtil.splitData('respdntBrdt','date');
		}
	});

	//사업자등록번호 해당없음 체크 시 input 비활성화	
	$(function(){
		$("#no").change(function(){
			if($("#no").prop("checked")){
				$("#aplcntBrno").attr("disabled", true);
			}else{
				$("#aplcntBrno").attr("disabled", false);
			}
		});
			$("#no2").change(function(){
			if($("#no2").prop("checked")){
				$("#respdntBrno").attr("disabled", true);
			}else{
				$("#respdntBrno").attr("disabled", false);
			}
		});
    });
		
	// FORM 검증
    function doValidate() {
	
        //신청인 정보
        var aplcntNm = $('#aplcntNm');
        var aplcntBrdt1 = $('#aplcntBrdt1');
        var aplcntBrdt2 = $('#aplcntBrdt2');
        var aplcntBrdt3 = $('#aplcntBrdt3');
        var aplcntZip = $('#aplcntZip');
        var aplcntAddr = $('#aplcntAddr');
        var aplcntDaddr = $('#aplcntDaddr');
        var aplcntMbtelNo1 = $('#aplcntMbtelNo1');
        var aplcntMbtelNo2 = $('#aplcntMbtelNo2');
        var aplcntMbtelNo3 = $('#aplcntMbtelNo3');
        
        //피신청인 정보
        var respdntNm = $('#respdntNm');
        var respdntZip = $('#respdntZip');
        var respdntAddr = $('#respdntAddr');
        var respdntDaddr = $('#respdntDaddr');
		
		//법률 조항 동의 여부
		var agree = $('input:radio[id="agree1"]:checked')
        
        var inval_Arr = false;

        //신청인 정보
         if(aplcntNm.val() == ''){
            inval_Arr[0] = false;
            alert('신청인의 성명을 확인해주세요.');
            return false;
         } else {
            inval_Arr[0] = true;
         }
    
         if(aplcntBrdt1.val() == ''){
            inval_Arr[1] = false;
            alert('신청인의 생년월일을 확인해주세요.');
            return false;
         } else {
            inval_Arr[1] = true;
         }

         if(aplcntBrdt2.val() == ''){
            inval_Arr[2] = false;
            alert('신청인의 생년월일을 확인해주세요.');
            return false;
         } else {
            inval_Arr[2] = true;
         }

         if(aplcntBrdt3.val() == ''){
            inval_Arr[3] = false;
            alert('신청인의 생년월일을 확인해주세요.');
            return false;
         } else {
            inval_Arr[3] = true;
         }

         if(aplcntZip.val() == ''){
            inval_Arr[4] = false;
            alert('신청인의 주소를 확인해주세요.');
            return false;
         } else {
            inval_Arr[4] = true;
         }

         if(aplcntAddr.val() == ''){
            inval_Arr[5] = false;
            alert('신청인의 주소를 확인해주세요.');
            return false;
         } else {
            inval_Arr[5] = true;
         }

         if(aplcntDaddr.val() == ''){
            inval_Arr[6] = false;
            alert('신청인의 주소를 확인해주세요.');
            return false;
         } else {
            inval_Arr[6] = true;
         }

         if(aplcntMbtelNo1.val() == ''){
            inval_Arr[7] = false;
            alert('신청인의 휴대전화번호를 확인해주세요.');
            return false;
         } else {
            inval_Arr[7] = true;
         }

         if(aplcntMbtelNo2.val() == ''){
            inval_Arr[8] = false;
            alert('신청인의 휴대전화번호를 확인해주세요.');
            return false;
         } else {
            inval_Arr[8] = true;
         }

         if(aplcntMbtelNo3.val() == ''){
            inval_Arr[9] = false;
            alert('신청인의 휴대전화번호를 확인해주세요.');
            return false;
         } else {
            inval_Arr[9] = true;
         }
        
         // 피신청인 정보
         if(respdntNm.val() == ''){
            inval_Arr[10] = false;
            alert('피신청인의 성명을 확인해주세요.');
            return false;
         } else {
            inval_Arr[10] = true;
         }

         if(respdntZip.val() == ''){
            inval_Arr[11] = false;
            alert('피신청인의 주소를 확인해주세요.');
            return false;
         } else {
            inval_Arr[11] = true;
         }

         if(respdntAddr.val() == ''){
            inval_Arr[12] = false;
            alert('피신청인의 주소를 확인해주세요.');
            return false;
         } else {
            inval_Arr[12] = true;
         }

         if(respdntAddr.val() == ''){
            inval_Arr[13] = false;
            alert('피신청인의 주소를 확인해주세요.');
            return false;
         } else {
            inval_Arr[13] = true;
         }
		
		// 법률조항 동의	
		if(agree.is(":checked") == false) {
	        inval_Arr[14] = false;
 		alert('「환경오염피해 배상책임 및 구제에 관한 법률」에 동의해주세요.');
	        return false;
	     } else {
			inval_Arr[14] = true;
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

	function doSave() {

    //신청인 생년월일 병합
    $('#aplcntBrdt').val('');
    if ($('#aplcntBrdt1').val() != '' &&
        $('#aplcntBrdt2').val() != '' &&
        $('#aplcntBrdt3').val() != '' ) {
        $('#aplcntBrdt').val(
            $('#aplcntBrdt1').val() + 
            $('#aplcntBrdt2').val() +
            $('#aplcntBrdt3').val() 
        );
    }

    //신청인 이메일 병합
    $('#aplcntEmlAddr').val('');
    if ($('#aplcntEmlAddr1').val() != '' &&
        $('#aplcntEmlAddr2').val() != '' ) {
        $('#aplcntEmlAddr' ).val(
            $('#aplcntEmlAddr1').val() + '@' + 
            $('#aplcntEmlAddr2').val() 
        );
    }
    
    //신청인 휴대전화 병합
    $('#aplcntMbtelNo').val('');
    if ($('#aplcntMbtelNo1').val() != '' &&
        $('#aplcntMbtelNo2').val() != '' &&
        $('#aplcntMbtelNo3').val() != '' ) {
        $('#aplcntMbtelNo').val(
            $('#aplcntMbtelNo1').val() + 
            $('#aplcntMbtelNo2').val() +
            $('#aplcntMbtelNo3').val() 
        );
    }

	//신청인 유선전화 병합
    $('#aplcntTelNo').val('');
    if ($('#aplcntTelNo1').val() != '' &&
        $('#aplcntTelNo2').val() != '' &&
        $('#aplcntTelNo3').val() != '' ) {
        $('#aplcntTelNo').val(
            $('#aplcntTelNo1').val() + 
            $('#aplcntTelNo2').val() +
            $('#aplcntTelNo3').val() 
        );
    }

    //피신청인 생년월일 병합
    $('#respdntBrdt').val('');
    if ($('#respdntBrdt1').val() != '' &&
        $('#respdntBrdt2').val() != '' &&
        $('#respdntBrdt3').val() != '' ) {
        $('#respdntBrdt').val(
            $('#respdntBrdt1').val() + 
            $('#respdntBrdt2').val() +
            $('#respdntBrdt3').val() 
        );
    }
    //신청인 이메일 병합
    $('#respdntEmlAddr').val('');
    if ($('#respdntEmlAddr1').val() != '' &&
        $('#respdntEmlAddr2').val() != '' ) {
        $('#respdntEmlAddr' ).val(
            $('#respdntEmlAddr1').val() + '@' + 
            $('#respdntEmlAddr2').val() 
        );
    }
    
    //피신청인 유선전화 병합
    $('#respdntTelno').val('');
    if ($('#respdntTelno1').val() != '' &&
        $('#respdntTelno2').val() != '' &&
        $('#respdntTelno3').val() != '' ) {
        $('#respdntTelno').val(
            $('#respdntTelno1').val() + 
            $('#respdntTelno2').val() +
            $('#respdntTelno3').val() 
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

		// 저장처리 내부함수
		let saveFn = function( saveData, signFile ) {
			if (signFile) {
				saveData['signCn'] = signFile;
			}
	        // AJAX로 저장처리 
	        // (파일데이터를 넘기기 위해 ajaxSave로 처리)
	        $.ajaxUtil.ajaxSave(
	            getUrl('/usr/support/saveUserLwst.do'), 
	            JSON.stringify(saveData),
	            function(ret) {
	                $.commMsg.success(ret, '성공적으로 신청되었습니다.', function() {
	                    goUrl(getUrl('/usr/mypage/listLwst.do'));
	                });
	            }
	        );
		};
        $.commMsg.confirm("취약계층 소송지원을 신청하시겠습니까?", function() {
			// 전자서명 후 제출
			popup.openSignature(saveFn, data);
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
			let mode      = (data ? MODE.UPDATE : MODE.INSERT);
			let dcmtNo    = (data ? data['aplyNo'  ] : null); 
			let dtlDcmtNo = P_APLY_ODER;
			
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
						dcmtNo:  dcmtNo,
						// (수정시) 세부문서번호
						dtlDcmtNo:  dtlDcmtNo
					}
				}));
				P_PAPE.append(div);
			});
		}
	}	
	// 우편번호 검색(팝업)
	function doPostCode() {
		C_POST_PREFIX = $(this).data('prefix');
		// 주소검색을 수행할 팝업 페이지 호출
		//popup.openAddressPopup();
		// 2021.12.24 LSH 주소검색 OPENAPI 팝업 호출
		popup.openAddress(MODE.OPENAPI);
  		  return false;
	}
	// 최초 데이터로딩
	doInit();
	
	// 저장버튼 클릭시 이벤트 처리
    $('#btnSubmit'  ).bind('click', doSave);
	
	// 주소검색 클릭 이벤트 처리
	$('.btnPost').bind('click', doPostCode);
	
});	

// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#'+C_POST_PREFIX+'Zip'  ).val(data['zipNo']);
	$('#'+C_POST_PREFIX+'Addr' ).val(data['roadAddrPart1']);
	$('#'+C_POST_PREFIX+'Daddr').val(data['addrDetail']);
}

