/**
*******************************************************************************
*** 파일명 : openBioReliefForm.js
*** 설명글 : 살생물제품 구제급여 신청 (정보입력) 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 2.0         2023.01.16    LSH
*******************************************************************************
**/

// 우편번호검색시 대상항목 PREFIX
let C_POST_PREFIX = '';

$(function() {
	
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    let P_FORM   = $('#registForm' ); // 등록폼 객체
	let P_PAPE   = $('#papeGroup'  ); // 신청서류영역 객체
	let P_USER   = false;             // 회원정보 데이터 객체
	let P_RELIEF = false;             // 신청정보 데이터 객체
	let P_LIST   = [];                // 신청서류 파일객체 배열
	let P_VALID  = [];                // 신청서류 유효한 파일객체
	let P_AKIND  = false;             // 신청종류 체크박스 객체

	const P_PDTY_CD   = $('#papeDtySeCd').val(); // 서류업무구분
	const P_APLY_CD   = $('#aplySeCd'   ).val(); // 신청구분
	const P_APLY_NO   = $('#aplyNo'     ).val(); // 신청번호
	const P_MODE      = $('#mode'       ).val(); // 저장모드
	const P_ADM_YN    = $('#admYn'      ).val(); // 대행여부
	
	// 신청자이메일 도메인 콤보박스
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
	// 신청인휴대전화번호 국번 콤보박스
	$('#aplcntMbtelNo1').appComboBox({
		params: {upCdId: CODE.MOBILE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 회원정보가 있으면
			if (P_USER || P_RELIEF) {
				// 휴대전화번호 분리
				$.formUtil.splitData('aplcntMbtelNo','mobile');
			}
		}
	});
	// 신청인유선전화번호 국번 콤보박스
	$('#aplcntTelno1').appComboBox({
		params: {upCdId: CODE.PHONE},
		//params: {arrUpCd: [CODE.PHONE,CODE.MOBILE]},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 회원정보가 있으면
			if (P_USER || P_RELIEF) {
				// 유선전화번호 분리
				$.formUtil.splitData('aplcntTelno','phone');
			}
		}
	});
	// 지급은행 콤보박스
	$('#bankCd').appComboBox({
		params: {upCdId: CODE.BANK},
		// 2022.12.16 초기값 설정
		init: {code:'', text:'은행선택'},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				$('#bankCd').val(P_RELIEF['bankCd']);
			}
		}
	});
	// 살생물 피해제품분류 콤보박스
	$('#dmgePrductCd').appComboBox({
		url: getUrl('/com/cmm/getComboBioPrduct.do'),
		params: {upCdId: CODE.BIO_PRDUCTCD},
		init: {code:'', text:'제품분류선택'},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				$('#dmgePrductCd').val(P_RELIEF['dmgePrductCd']);
			}
		}
	});
	// 살생물 제품제출여부 라디오박스
	let rdoSbmsn = $('#appPrductSbmsn').appSelectBox({
		wrapCls: 'inputWrap app-inline',
		form:    'radio',
		type:    'static', 
		name:    'prductSbmsnYn',
		rows:    STORE.SBMT_YN,
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				rdoSbmsn.setValue(P_RELIEF['prductSbmsnYn']);
			}
		}
	});
	// 살생물 제품사용구분 라디오박스
	let rdoUseSe = $('#appPrductUseSe').appSelectBox({
		wrapCls: 'inputWrap app-inline',
		form:    'radio',
		name:    'prductUseSe',
		params:  {upCdId: CODE.BIO_PRDUCTUSE},
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				rdoUseSe.setValue(P_RELIEF['prductUseSe']);
			}
		}
	});
	// 살생물 제품사용확인여부 라디오박스
	let rdoIdnty = $('#appPrductIdntyYn').appSelectBox({
		wrapCls: 'inputWrap app-inline',
		form:    'radio',
		type:    'static', 
		name:    'prductIdntyYn',
		rows:    STORE.YES_NO,
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				rdoIdnty.setValue(P_RELIEF['prductIdntyYn']);
			}
		}
	});
	// 살생물 제품사용시작시간 콤보박스
	$('#prductUseBgngHour').appComboBox({
		type: 'static', 
		init: {code:'',text:'시작시간'}, 
		rows: STORE.getHours(),
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				$('#prductUseBgngHour').val(P_RELIEF['prductUseBgngHour']);
			}
		}
	});	
	// 살생물 제품사용종료시간 콤보박스
	$('#prductUseEndHour').appComboBox({
		type: 'static', 
		init: {code:'',text:'종료시간'}, 
		rows: STORE.getHours(),
		// 콤보값 로딩후 실행함수
		callback: function() {
			// 신청정보가 있으면
			if (P_RELIEF) {
				$('#prductUseEndHour').val(P_RELIEF['prductUseEndHour']);
			}
		}
	});	
	
	// 대리신청인 경우
	if (P_APLY_CD != CODE.APLY_CD['SELF']) {
		// 피해자이메일 도메인 콤보박스
		$('#sufrerEmlDomain').appComboBox({
			params: {upCdId: CODE.EMAIL},
			init:   COMBO.INIT_DIRECT,		
			// 콤보 값변경시 입력처리
			change: function() {
				let domain   = $('#sufrerEmlAddr2');
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
		// 피해자휴대전화번호 국번 콤보박스
		$('#sufrerMbtelNo1').appComboBox({
			params: {upCdId: CODE.MOBILE},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 피해자정보가 있으면
				if (P_RELIEF) {
					// 유선전화번호 분리
					$.formUtil.splitData('sufrerMbtelNo','mobile');
				}
			}
		});
		// 피해자유선전화번호 국번 콤보박스
		$('#sufrerTelno1').appComboBox({
			params: {upCdId: CODE.PHONE},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 피해자정보가 있으면
				if (P_RELIEF) {
					// 유선전화번호 분리
					$.formUtil.splitData('sufrerTelno','phone');
				}
			}
		});
		// 피해자와의 관계 콤보박스
		$('#sufrerRelCd').appComboBox({
			params: {upCdId: CODE.RELATION},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 신청정보가 있으면
				if (P_RELIEF) {
					$('#sufrerRelCd').val(P_RELIEF['sufrerRelCd']);
				}
			}
		});
	}
	
	// 신규일 경우
	if (P_MODE == MODE.INSERT) {
		// 살생물제품 신청종류
		$('#layerAplyKnd').append('<span id="appAplyKndCd"></span>');
		// 살생물제품 신청종류 체크박스
		P_AKIND = $('#appAplyKndCd').appSelectBox({
			form: 'checkbox',
			name: 'aplyKndList', 
			params: {upCdId: CODE.BIO_APLYKIND},
			filter: function(obj) {
				// 신청종류에 따라 항목 필터링
				if (P_APLY_CD == CODE.APLY_CD['DPTH'])
					return ($.inArray(obj['code'], CODES.BIO_APLY_KIND_DPTH) >= 0);
				else
					return ($.inArray(obj['code'], CODES.BIO_APLY_KIND_LIVE) >= 0);
			},
			// 데이터 로딩후 실행함수
			callback: function(cmp) {
				// 신청정보가 있으면
				if (P_RELIEF) {
					cmp.select(P_RELIEF['aplyKndList']);
					// 서류그룹제어
					doChangePapeGroup(P_RELIEF['aplyKndList']);
				}
			},
			// 옵션클릭시 실행함수
			click: function() {
				// 서류그룹제어
				doChangePapeGroup(P_AKIND.getValue());
			}
		});
		
	}
	else if (P_MODE == MODE.UPDATE){
		// 수정시엔 신청종류 변경불가
		$('#layerAplyKnd').append('<span id="s_aplyKndNm"></span>');
	}
	
	// 숫자만입력
	bindOnlyNumber($("#aplcntRrno1"));
	bindOnlyNumber($("#aplcntRrno2"));
	bindOnlyNumber($("#sufrerRrno1"));
	bindOnlyNumber($("#sufrerRrno2"));
	bindOnlyNumber($("#aplcntMbtelNo2"));
	bindOnlyNumber($("#aplcntMbtelNo3"));
	bindOnlyNumber($("#aplcntTelno2"));
	bindOnlyNumber($("#aplcntTelno3"));
	bindOnlyNumber($("#sufrerMbtelNo2"));
	bindOnlyNumber($("#sufrerMbtelNo3"));
	bindOnlyNumber($("#sufrerTelno2"));
	bindOnlyNumber($("#sufrerTelno3"));
    // 신청일자 입력박스 하이픈(-) 자동삽입 이벤트
	bindDateHyphen( $('#aplyYmd').datebox('textbox') );
    // 살생물제품 사용시작일자 입력박스 하이픈(-) 자동삽입 이벤트
	bindDateHyphen( $('#prductUseBgngYmd').datebox('textbox') );
    // 살생물제품 사용종료일자 입력박스 하이픈(-) 자동삽입 이벤트
	bindDateHyphen( $('#prductUseEndYmd').datebox('textbox') );

    //========================================================//
    // 등록폼 VALIDATION RULE 정의
    //--------------------------------------------------------//
	let validations = {
        debug: false,
        onsubmit: false,
        onfocusout: false,
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement,
        // 검증룰 정의
        rules: {
            aplcntNm      : {required: true},
            aplcntRrno1   : {required: true, regNo1: true},
            aplcntRrno2   : {required: true, regNo2: true},
            aplcntZip     : {required: true},
            aplcntAddr    : {required: true},
            aplcntMbtelNo2: {required: true},
			aplcntMbtelNo3: {required: true},
			aplcntEmlAddr : {email: true, maxlength: 100}
		},
        // 검증메세지 정의
        messages: {
            aplcntNm      : {required: '성명은 필수 입력 사항입니다.'},
            aplcntRrno1   : {required: '주민등록번호 앞자리는 필수 입력 사항입니다.', regNo1: '주민등록번호 앞자리를 형식에 맞게 입력하세요.'},
            aplcntRrno2   : {required: '주민등록번호 뒷자리는 필수 입력 사항입니다.', regNo2: '주민등록번호 뒷자리를 형식에 맞게 입력하세요.'},
            aplcntZip     : {required: '우편번호는 필수 입력 사항입니다.'},
            aplcntAddr    : {required: '주소는 필수 입력 사항입니다.'},
            aplcntMbtelNo2: {required: '휴대전화번호는 필수 입력 사항입니다.'},
            aplcntMbtelNo3: {required: '휴대전화번호는 필수 입력 사항입니다.'},
            aplcntEmlAddr : {email: '전자우편주소를 형식에 맞게 입력하세요.', maxlength: $.validator.format('전자우편주소는 {0}자를 초과할 수 없습니다.')}
		}
	};
	
	// 본인신청
	if (P_APLY_CD == CODE.APLY_CD['SELF']) {
		$('#layerAplyTitle').append('피해자 본인 신청');
		$('#layerLeftTitle').append('<b>피해자 본인</b>(환경오염 피해자 본인)');
		$('#layerAplyAgent').addClass('app-display-off');
		$('#layerSufrerRel').addClass('app-display-off');
	} 
	// 대리신청
	else {
		$.extend(validations.rules, {
	        sufrerRelCd  : {required: true},
			sufrerNm     : {required: true},
            sufrerRrno1  : {required: true, regNo1: true},
            sufrerRrno2  : {required: true, regNo2: true},
            sufrerAddr   : {required: true},
			sufrerEmlAddr: {email: true, maxlength: 100}

		}, true);
		$.extend(validations.messages, {
	        sufrerRelCd  : {required: '피해자와의 관계를 선택해 주세요.'},
			sufrerNm     : {required: '피해자성명은 필수 입력 사항입니다.'},
            sufrerRrno1  : {required: '피해자주민등록번호 앞자리는 필수 입력 사항입니다.', regNo1: '피해자주민등록번호 앞자리를 형식에 맞게 입력하세요.'},
            sufrerRrno2  : {required: '피해자주민등록번호 뒷자리는 필수 입력 사항입니다.', regNo2: '피해자주민등록번호 뒷자리를 형식에 맞게 입력하세요.'},
            sufrerAddr   : {required: '피해자주소는 필수 입력 사항입니다.'},
            sufrerEmlAddr: {email: '피해자전자우편주소를 형식에 맞게 입력하세요.', maxlength: $.validator.format('피해자전자우편주소는 {0}자를 초과할 수 없습니다.')}
		}, true);

		// 대리(생)
		if (P_APLY_CD == CODE.APLY_CD['LIVE']) {
			$('#layerAplyTitle').append('대리인 신청 (피해자 생존)');
			$('#layerLeftTitle').append('<b>신청인</b>(환경오염 피해자의 대리인)');
			$.extend(validations.rules, {
		        sufrerMbtelNo2: {required: true},
			    sufrerMbtelNo3: {required: true}
			}, true);
			$.extend(validations.messages, {
		        sufrerMbtelNo2: {required: '피해자휴대전화번호는 필수 입력 사항입니다.'},
			    sufrerMbtelNo3: {required: '피해자휴대전화번호는 필수 입력 사항입니다.'}
			}, true);
		} 
		// 대리(사)
		else if (P_APLY_CD == CODE.APLY_CD['DPTH']) {
			$('#layerAplyTitle').append('대리인 신청 (피해자 사망)');
			$('#layerLeftTitle').append('<b>신청인</b>(환경오염 피해자의 대리인)');
			$('#layerSufrerMbtel').addClass('app-display-off');
			$('#layerSufrerTel'  ).addClass('app-display-off');
			$('#layerSufrerEml'  ).addClass('app-display-off');
		}
	}
	// 2022.12.02 제목수정
	if (P_ADM_YN == 'Y') {
		$('#layerAplyTitle').append('<br>(관리자대행)');
	}
	
	$.extend(validations.rules, {
		aplyYmd        : {required: true, dateHyphen: true},
        dmgePrductCd   : {required: true}, // 피해제품분류
        dmgePrductCn   : {required: true}, // 피해제품내용
        prductSbmsnYn  : {required: true}, // 피해제품제출여부
        prductSbmsnResn: {required: function() { return (P_FORM.find('input[name="prductSbmsnYn"]:checked').val() == 'N'); }}
	}, true);
	$.extend(validations.messages, {
		aplyYmd      : {
			required  : '신청일자를 입력해 주세요.', 
			dateHyphen: '신청일자를 형식에 맞게 입력해 주세요.(예. 2022-01-01)'
		},
        dmgePrductCd   : {required: '피해제품분류는 필수 선택 사항입니다.'},
        dmgePrductCn   : {required: '피해제품명칭은 필수 입력 사항입니다.'},
        prductSbmsnYn  : {required: '피해제품 제출여부는 필수 선택 사항입니다.'},
		prductSbmsnResn: {required: '피해제품 미제출사유는 필수 입력 사항입니다.'},
	}, true);
	
	// 수정시엔 피해지역 변경불가
	// 수정시엔 신청종류 변경불가
	if (P_MODE == MODE.INSERT) {
		$.extend(validations.rules, {
			etcDmgeArea  : {required: true},
			aplyKndList  : {required: true}
		}, true);
		$.extend(validations.messages, {
			etcDmgeArea  : {required: '피해지역을 입력해 주세요.'},
        	aplyKndList  : {required: '구제급여 신청내용을 하나 이상 선택해 주세요.'}
		}, true);
	}

	$.extend(validations.rules, {
        bankCd           : {required: true}, // 지급은행
        dpstrNm          : {required: true}, // 예금주명
        actno            : {required: true, regx: /^[0-9\-]{11,30}$/}, // 계좌번호
        prductUsePrps    : {required: true}, // 피해제품사용목적
        prductUseSe      : {required: true}, // 피해제품사용구분
        prductUsePlce    : {required: true}, // 피해제품사용장소
        prductUseMthd    : {required: true}, // 피해제품사용방법
        prductIdntyYn    : {required: true}, // 피해제품확인여부
        prductUseBgngYmd : {required: true, dateHyphen: true}, // 피해제품사용기간(시작일)
        prductUseEndYmd  : {required: true, dateHyphen: true}, // 피해제품사용기간(종료일)
        prductUseBgngHour: {required: true}, // 피해제품사용시간대(시작시간)
        prductUseEndHour : {required: true}, // 피해제품사용시간대(종료시간)
        prductUseCuntCn  : {required: true}, // 피해제품사용빈도(사용횟수)
        prductUsgqtyCn   : {required: true}, // 피해제품사용빈도(사용량)
        dmgeDissNm       : {required: true}, // 건강피해 및 질환명
	}, true);
	$.extend(validations.messages, {
        bankCd           : {required: '지급은행명은 필수 입력 사항입니다.'},
        dpstrNm          : {required: '예금주명은 필수 입력 사항입니다.'},
        actno            : {
			required: '계좌번호는 필수 입력 사항입니다.', 
			regx:     '계좌번호를 형식에 맞게 입력하세요.'
		},
        prductUsePrps    : {required: '피해제품 사용목적은 필수 입력 사항입니다.'},
        prductUseSe      : {required: '피해제품 사용구분은 필수 선택 사항입니다.'},
        prductUsePlce    : {required: '피해제품 사용장소는 필수 입력 사항입니다.'},
        prductUseMthd    : {required: '피해제품 사용방법은 필수 입력 사항입니다.'},
        prductIdntyYn    : {required: '피해제품 주의사항 확인여부는 필수 선택 사항입니다.'},
        prductUseBgngYmd : {
			required:   '피해제품 사용시작일은 필수 입력 사항입니다.', 
			dateHyphen: '피해제품 사용시작일을 형식에 맞게 입력하세요(예: 2023-01-01)'
		},
        prductUseEndYmd  : {
			required:   '피해제품 사용종료일은 필수 입력 사항입니다.', 
			dateHyphen: '피해제품 사용종료일을 형식에 맞게 입력하세요(예: 2023-01-01)'
		},
        prductUseBgngHour: {required: '피해제품 사용시작시간은 필수 선택 사항입니다.'},
        prductUseEndHour : {required: '피해제품 사용종료시간은 필수 선택 사항입니다.'},
        prductUseCuntCn  : {required: '피해제품 사용횟수는 필수 입력 사항입니다.'},
        prductUsgqtyCn   : {required: '피해제품 사용량은 필수 입력 사항입니다.'},
        dmgeDissNm       : {required: '건강피해 및 질환명은 필수 입력 사항입니다.'},
	}, true);

	
	if (P_ADM_YN == 'Y') {
		// 필수제외
		$.extend(validations.rules, {
            aplcntMbtelNo2: {required: false},
			aplcntMbtelNo3: {required: false},
	        sufrerMbtelNo2: {required: false},
		    sufrerMbtelNo3: {required: false}
		}, true);
	}
	else {
		// 필수표시
		$('.selmust').addClass("must");
	}
	
	P_FORM.validate(validations);

    // 최초 로딩시 정보가져오기
    //--------------------------------------------------------//
	function doInit() {
		if (P_MODE == MODE.INSERT) {
			// 회원정보 가져오기
			doLoadUserInfo();
			// 신청서류그룹
			doLoadPapeGroup();
			// 서류그룹제어
			doChangePapeGroup(P_AKIND.getValue());
		}
		else {
			// 신청정보 가져오기
			doLoadAplyInfo();
		}
	}
	
    // 신청정보 가져오기
    //--------------------------------------------------------//
	function doLoadAplyInfo() {
		
		// 신청정보 가져오기
		const result = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/relief/getBioRelief.do'),{
				aplyNo:   P_APLY_NO
			});
		if (result && 
			result.Data) {
			
			$.popupMsg.alert('임시저장한 신청서를 로드했습니다.<br>작성완료후 제출하기를 눌러 주세요.');
			
			P_RELIEF = result.Data;
			// 폼값 맵핑
			$.formUtil.toForm(P_RELIEF, P_FORM);
			// 주민등록번호 분리
			$.formUtil.splitData('aplcntRrno','rno');
			// 전자우편주소 분리
			$.formUtil.splitData('aplcntEmlAddr','email');
			// 유선전화번호 분리
			$.formUtil.splitData('aplcntTelno','phone');
			// 휴대전화번호 분리
			$.formUtil.splitData('aplcntMbtelNo','mobile');
			
			// 수정시엔 신청종류 텍스트표시
			$.formUtil.toForm({s_aplyKndNm  : P_RELIEF['aplyKndNm']});
			// 수정시엔 신청종류 HIDDEN값으로 정의
			$.each(P_RELIEF['aplyKndList'], function(i,s) {
				$('#layerAplyKnd').append('<input name="aplyKndList" type="hidden" value="'+s+'"/>');
			});
			// 신청일자 정의
			$('#aplyYmd').datebox('setValue', P_RELIEF['aplyYmd']);
			// 피해제품 사용시작일자 정의
			$('#prductUseBgngYmd').datebox('setValue', P_RELIEF['prductUseBgngYmd']);
			// 피해제품 사용종료일자 정의
			$('#prductUseEndYmd' ).datebox('setValue', P_RELIEF['prductUseEndYmd' ]);
			
			// 피해자정보 (대리인신청시)
			if ($.inArray(
					P_APLY_CD, [
						CODE.APLY_CD['LIVE'],
						CODE.APLY_CD['DPTH']
					]) >= 0) {
				// 피해자 주민등록번호 분리
				$.formUtil.splitData('sufrerRrno','rno');
				// 피해자 전자우편주소 분리
				$.formUtil.splitData('sufrerEmlAddr','email');
				// 피해자 유선전화번호 분리
				$.formUtil.splitData('sufrerTelno','phone');
				// 피해자 휴대전화번호 분리
				$.formUtil.splitData('sufrerMbtelNo','mobile');
			}
			// 신청서류그룹
			doLoadPapeGroup(P_RELIEF);
			// 서류그룹제어
			doChangePapeGroup(P_RELIEF['aplyKndList']);
		}
	}
		
	// 신청종류(진료비,장애일시보상금,장례비,미지급진료비) 선택에 따른 서류그룹 제어
    //--------------------------------------------------------//
	function doChangePapeGroup( kndLst ) {
		P_VALID   = [];
		let inMCP = ($.inArray(CODE.BIO_APLYKIND_CD.MCP, kndLst) >= 0);
		let inPRW = ($.inArray(CODE.BIO_APLYKIND_CD.PRW, kndLst) >= 0);
		let inDRW = ($.inArray(CODE.BIO_APLYKIND_CD.DRW, kndLst) >= 0);
		let inDTH = ($.inArray(CODE.BIO_APLYKIND_CD.DTH, kndLst) >= 0);
		let inUPD = ($.inArray(CODE.BIO_APLYKIND_CD.UPD, kndLst) >= 0);

		$('.usr-file-list').each(function() {
			let code = $(this).data('code');
			if      (code == CODE.BIO_PAPE_CD.MCP && !inMCP) $(this).hide();
			else if (code == CODE.BIO_PAPE_CD.PRW && !inPRW) $(this).hide();
			else if (code == CODE.BIO_PAPE_CD.DRW && !inDRW) $(this).hide();
			else if (code == CODE.BIO_PAPE_CD.DTH && !inDTH) $(this).hide();
			else if (code == CODE.BIO_PAPE_CD.UPD && !inUPD) $(this).hide();
			else {
				$(this).show();
				P_VALID.push(code);
			}
		});
	}

    // 회원정보 가져오기
    //--------------------------------------------------------//
	function doLoadUserInfo() {
		
		// 회원정보 가져오기
		const result = $.ajaxUtil.ajaxDefault(getUrl('/usr/relief/getBioUserInfo.do'));
		
		if (result && 
			result.Data) {
			P_USER = result.Data;
			// 폼값 맵핑
			$.formUtil.toForm({
				aplcntNo:      P_USER['userNo' ] || '',
				aplcntNm:      P_USER['userNm' ] || '',
				aplcntTelno:   P_USER['telno'  ] || '',
				aplcntMbtelNo: P_USER['mbtelNo'] || '',
				aplcntEmlAddr: P_USER['emlAddr'] || '',
				aplcntBrdt:    P_USER['brdt'   ] || '',
				aplcntZip:     P_USER['zip'    ] || '',
				aplcntAddr:    P_USER['addr'   ] || '',
				aplcntDaddr:   P_USER['daddr'  ] || ''
			}, P_FORM);
			
			// 주민등록번호 분리
			$.formUtil.splitData('aplcntRrno','rno');
			// 전자우편주소 분리
			$.formUtil.splitData('aplcntEmlAddr','email');
			// 유선전화번호 분리
			$.formUtil.splitData('aplcntTelno','phone');
			// 휴대전화번호 분리
			$.formUtil.splitData('aplcntMbtelNo','mobile');
		}
	}
	
    // 신청서류그룹 가져오기
    //--------------------------------------------------------//
	function doLoadPapeGroup( data ) {
		
		// 신청서류그룹 가져오기
		const rows = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/file/getListBioPapeGroup.do'), {
				papeDtySeCd: P_PDTY_CD,
				aplySeCd:    P_APLY_CD
			}
		);
		
		if (rows) {
			let mode      = (data ? MODE.UPDATE : MODE.INSERT);
			let dcmtNo    = (data ? data['aplyNo'  ] : null); 
			let dtlDcmtNo = '0';
			
			$.each(rows, function(i, row) {
				let div = $('<div class="usr-file-list"></div>');
				div.data('code', row['papeCd']);
				div.data('name', row['papeNm']);

				P_LIST.push(div.appBioAplyFile({
					mode:  mode,
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

    // 저장하기
    //--------------------------------------------------------//
    function doSave( act ) {

		//주민등록번호 병합
		$.formUtil.mergeData('aplcntRrno','rno',2);
		//전자우편주소 병합
		$.formUtil.mergeData('aplcntEmlAddr','email',2);
		//휴대전화번호 병합
		$.formUtil.mergeData('aplcntMbtelNo','mobile',3);
		//유선전화번호 병합
		$.formUtil.mergeData('aplcntTelno','phone',3);
		
		// 본인이 아닌 경우
		if (P_APLY_CD != CODE.APLY_CD['SELF']) {
			//주민등록번호 병합
			$.formUtil.mergeData('sufrerRrno','rno',2);
			// 대리(생)인 경우
			if (P_APLY_CD == CODE.APLY_CD['LIVE']) {
				//전자우편주소 병합
				$.formUtil.mergeData('sufrerEmlAddr','email',2);
				//휴대전화번호 병합
				$.formUtil.mergeData('sufrerMbtelNo','mobile',3);
				//유선전화번호 병합
				$.formUtil.mergeData('sufrerTelno','phone',3);
			}
		}

        // VALIDATION 기능 활성화
        if (P_FORM.validate().settings)
            P_FORM.validate().settings.ignore = false;
        
        // FORM VALIDATION
        if (P_FORM.valid() === false)
            return false;
		
		let data = P_FORM.serializeObject();
		
		// 신청종류(다중선택)를 배열로 변환
		if (data['aplyKndList'] && $.type(data['aplyKndList']) === 'string') {
			data['aplyKndList'] = [data['aplyKndList']];
		}
		$.extend(data, {
			mode:        P_MODE,
			act:         act,
			saveFiles:   [],
			removeFiles: []
		});

		// FILE VALIDATION
		let check = true;
		$.each(P_LIST, function(i, file) {
			// 유효성체크 SKIP파일인 경우			
			if ($.inArray(file.getPapeGroup(), P_VALID) < 0)
				return true;
			
			// 2022.01.18 제출시에만 파일필수 체크
			if (file.doValidate(act) === false) {
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
		
		// 이용동의 확인체크
		if ($('#agreeYn').is(":checked") === false) {
			$.commMsg.alert('개인정보 수집ㆍ이용 및 제공 동의를 확인해 주세요.');
			return false;
		}
		// 저장처리 내부함수
		let saveFn = function( saveData, signFile ) {
			if (signFile) {
				saveData['signCn'] = signFile;
			}
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/usr/relief/saveBioRelief.do'), 
                JSON.stringify(saveData),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
	
						if (act == MODE.SUBMIT) {
							let p = $('<div></div>').appMessage({
								message: '성공적으로 제출이 완료되었습니다.',
								buttons: [{
									id: 'btn_msg_main',
									text: '메인으로이동',
									click: function() {
										p.close();
										goMain();
									}
								}]			
							});
						}
						else {
							$.commMsg.alert('성공적으로 임시저장되었습니다.', goMain);
						}
                    });
                }
            );
		};
        $.commMsg.confirm((act == MODE.SUBMIT ? '제출' : '임시저장')+"하시겠습니까?", function() {
			if (act == MODE.SUBMIT) {
				// 전자서명 후 제출
				popup.openSignature(saveFn, data, {
					saveUrl: getUrl('/usr/relief/saveBioSignature.do')
				});
			}
			else {
				saveFn(data);
			}
        });
        return false;
    }

    // 제출후 마이페이지로 이동 처리
    //--------------------------------------------------------//
    function goMypage() {
		goUrl( getUrl('/usr/mypage/viewReliefPape.do') );
    }
    // 임시저장후 메인페이지로 이동 처리
    //--------------------------------------------------------//
    function goMain() {
		goUrl( getUrl('/usr/main/main.do') );
    }

    // 우편번호 검색(팝업)
    //--------------------------------------------------------//
    function doPost() {
		C_POST_PREFIX = $(this).data('prefix');
		// 주소검색을 수행할 팝업 페이지 호출
		popup.openAddress(MODE.OPENAPI);
        return false;
    }
    // 임시저장
    //--------------------------------------------------------//
    function doTmpSave() {
		doSave( MODE.TMPSAVE );
        return false;
    }
    // 제출하기
    //--------------------------------------------------------//
    function doSubmit() {
		doSave( MODE.SUBMIT );
        return false;
    }
    // 이용동의하기
    //--------------------------------------------------------//
    function doAgree() {
		$.commModal.loadView('', getUrl('/html/popupReliefAgree.html'), {
			 func: function(dialog) {
				
				let body = dialog.getModalBody();
				
				$('#agreeYn').prop('checked', false);
				// 전체동의 체크시
				body.find('input.agreeAll').on('click', function() {
					let chk = $(this).is(":checked"); 
					//전문보기 클릭하지 않으면 전체동의x
					let dislen = $('input[type="radio"].agreeItem:disabled').length;
					if(dislen == 0){
						$('input.agreeItem[value="Y"]').each(function() {
							$(this).prop('checked', chk);
						});						
					}
					if(dislen > 0){	
						$.commMsg.alert('모든 항목의 전문을 확인한 후에 전체 동의 할 수 있습니다.');
						return false;
					}
				});				
				body.find('a[id="btnPopupAgree"]').on("click", function() {
					let len = $('#popupAgree').find('input[type="radio"][value="Y"].agreeItem:checked').length;
					if (len != 6) {
						$.commMsg.alert('모든 항목에 동의하셔야 합니다.');
						return false;
					}
					$('#agreeYn').prop('checked', true);
					dialog.close();
					return false;
				});
				//전문보기 버튼
				$('input[class=agreeDocuCls]:checkbox').change(function(){
					var submenu = $(this).next().next(".inner");
			        if($(this).is(":checked")){			        	 
			        	submenu.slideDown();		        
			        	var element = $(this).prev().prev();
			        	var eleName = element.attr("name");		        	
			        	$("input[name='"+eleName+"']").each(function(){		        		
			        		$(this).prop('disabled', false);
			        	});
			        }else{			            
			            submenu.slideUp();
			        } 
				});
				
				
			}
		});
		return false;
    }
	// 주소검색 클릭 이벤트 처리
	$('.btnPost').bind('click', doPost);
	// 임시저장 클릭 이벤트 처리
	$('#btnTmpSave').bind('click', doTmpSave);
	// 제출하기 클릭 이벤트 처리
	$('#btnSubmit').bind('click', doSubmit);
	// 이용동의 클릭이벤트 처리
	$('#agreeYn').bind("click", doAgree);
	// 최초 데이터로딩
	doInit();
});

//2023.02.24 비동의 클릭 시 전체동의 체크해제
//--------------------------------------------------------//
function checkSelectAll() {
	const selectAll = document.querySelector('input[name=agreeAll]');
	
	let len = $('#popupAgree').find('input[type="radio"][value="Y"].agreeItem:checked').length;
	if (len != 6) {
		selectAll.checked = false;
	} else {
		selectAll.checked = true;
	}
}
// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#'+C_POST_PREFIX+'Zip'  ).val(data['zipNo']);
	$('#'+C_POST_PREFIX+'Addr' ).val(data['roadAddrPart1']);
	$('#'+C_POST_PREFIX+'Daddr').val(data['addrDetail']);
}
