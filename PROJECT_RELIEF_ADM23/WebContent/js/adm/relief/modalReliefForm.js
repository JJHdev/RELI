/**
******************************************************************************************
***	명칭: modalReliefForm.js
***	설명: 구제급여접수 - 개인정보 수정팝업
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------

******************************************************************************************
**/

$(function() {

	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let   P_POP_RELIEF  = false;             // 신청정보 데이터 객체
	const P_POP_APLY_CD = $('#p_aplySeCd'   ).val(); // 신청구분
	const P_POP_APLY_NO = $('#p_aplyNo'     ).val(); // 신청번호
	const P_POP_HST_CD  = $('#p_hstSeCd'    ).val(); // 이력구분

    // 최초 로딩
    //--------------------------------------------------------//
	function doInit() {
		
		// 등록폼 객체
		P_POPUP_FORM = $('#reliefForm'); 
		
		if      (P_POP_HST_CD == CODE.HST_CD.SUFRER) doInitSufrer();
		else if (P_POP_HST_CD == CODE.HST_CD.APLCNT) doInitAplcnt();
		else if (P_POP_HST_CD == CODE.HST_CD.DAMAGE) doInitDamage();
	}

    // 피해자정보수정 초기로딩
    //--------------------------------------------------------//
	function doInitSufrer() {
			
		// 피해자이메일 도메인 콤보박스
		$('#p_sufrerEmlDomain').appComboBox({
			params: {upCdId: CODE.EMAIL},
			init:   COMBO.INIT_DIRECT,		
			// 콤보 값변경시 입력처리
			change: function() {
				let domain   = $('#p_sufrerEmlAddr2');
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
		$('#p_sufrerMbtelNo1').appComboBox({
			params: {upCdId: CODE.MOBILE},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 피해자정보가 있으면
				if (P_POP_RELIEF) {
					// 유선전화번호 분리
					$.formUtil.splitData('p_sufrerMbtelNo','mobile');
				}
			}
		});
		// 피해자유선전화번호 국번 콤보박스
		$('#p_sufrerTelno1').appComboBox({
			params: {upCdId: CODE.PHONE},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 피해자정보가 있으면
				if (P_POP_RELIEF) {
					// 유선전화번호 분리
					$.formUtil.splitData('p_sufrerTelno','phone');
				}
			}
		});
		// 지급은행 콤보박스
		$('#p_bankCd').appComboBox({
			params: {upCdId: CODE.BANK},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 신청정보가 있으면
				if (P_POP_RELIEF) {
					$('#p_bankCd').val(P_POP_RELIEF['bankCd']);
				}
			}
		});
		// 신청종류 체크박스
		$('#p_appAplyKndCd').appSelectBox({
			form: 'checkbox',
			prefix: 'p_',
			name: 'aplyKndList', 
			wrapCls: 'formType1',
			params: {upCdId: CODE.APLYKIND},
			filter: function(obj) {
				// 2021.12.20 재산피해보상비 항목 제외
				if (obj['code'] == CODE.APLY_KIND_CD.PRP)
					return false;
				// 신청종류에 따라 항목 필터링
				if (P_POP_APLY_CD == CODE.APLY_CD['DPTH'])
					return ($.inArray(obj['code'], CODES.APLY_KIND_DPTH) >= 0);
				else
					return ($.inArray(obj['code'], CODES.APLY_KIND_LIVE) >= 0);
			},
			// 데이터 로딩후 실행함수
			callback: function(cmp) {
				// 신청정보가 있으면
				if (P_POP_RELIEF) {
					cmp.select(P_POP_RELIEF['aplyKndList']);
				}
			},
			// 옵션클릭시 실행함수
			click: function() {
			}
		});
		
		// 2022.12.16 사망일자 달력박스 활성함수
		let _fnActive = function(v) {
			if (v == 'Y') {
				$('#p_dthYmd').datebox('enable');
				$('#p_dthYmd').next().removeClass('app-readonly');
			}
			else {
				$('#p_dthYmd').datebox('setValue', '');
				$('#p_dthYmd').datebox('disable');
				$('#p_dthYmd').next().addClass('app-readonly');
			}
		};
		
		// 신청일자 달력박스 처리
		$('#p_aplyYmd').datebox({cls:'app-relief-datebox'});
		// 접수일자 달력박스 처리
		$('#p_rcptYmd').datebox({cls:'app-relief-datebox'});
		// 사망일자 달력박스 처리
		$('#p_dthYmd').datebox({cls:'app-relief-datebox'});

		// 2022.12.16 피해자사망여부 콤보박스
		$('#p_dthYn').appComboBox({
			type: 'static',
			rows: STORE['DTH_YN'],
			// 콤보 값변경시 입력처리
			change: function() {
				let newValue = $(this).find('option:checked').val();
				// 사망일자 달력박스 활성/비활성 처리
				_fnActive(newValue);
			}
		});
		// 2022.12.16 달력박스 자동 하이픈(-) 삽입처리
		bindDateHyphen($('.app-relief-datebox').find('input[type="text"]'));
		// 숫자만입력
		bindOnlyNumber($("#p_sufrerMbtelNo2"));
		bindOnlyNumber($("#p_sufrerMbtelNo3"));
		bindOnlyNumber($("#p_sufrerTelno2"));
		bindOnlyNumber($("#p_sufrerTelno3"));
	
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
		        sufrerRelCd  : {required: true},
				sufrerNm     : {required: true},
		        sufrerAddr   : {required: true},
				sufrerEmlAddr: {email: true, maxlength: 100},
				aplyYmd      : {required: true, dateHyphen: true},
		        bankCd       : {required: true},
		        dpstrNm      : {required: true},
		        actno        : {required: true, regx: /^[0-9\-]{11,30}$/},
				hstCn        : {required: true},
				rcptYmd      : {dateHyphen: true},
				dthYmd       : {dateHyphen: true},
			},
	        // 검증메세지 정의
	        messages: {
		        sufrerRelCd  : {required: '피해자와의 관계를 선택해 주세요.'},
				sufrerNm     : {required: '피해자성명은 필수 입력 사항입니다.'},
		        sufrerAddr   : {required: '피해자주소는 필수 입력 사항입니다.'},
		        sufrerEmlAddr: {email: '피해자전자우편주소를 형식에 맞게 입력하세요.', maxlength: $.validator.format('피해자전자우편주소는 {0}자를 초과할 수 없습니다.')},
				aplyYmd      : {required: '신청일자를 입력해 주세요.', dateHyphen: '신청일자를 형식에 맞게 입력해 주세요.(예. 2022-01-01)'},
				bankCd       : {required: '지급은행명은 필수 입력 사항입니다.'},
				dpstrNm      : {required: '예금주명은 필수 입력 사항입니다.'},
				actno        : {required: '계좌번호는 필수 입력 사항입니다.', regx: '계좌번호를 형식에 맞게 입력하세요.'},
				hstCn        : {required: '수정사유는 필수 입력 사항입니다.'},
				rcptYmd      : {dateHyphen: '접수일자를 형식에 맞게 입력해 주세요.(예. 2022-01-01)'},
				dthYmd       : {dateHyphen: '사망일자를 형식에 맞게 입력해 주세요.(예. 2022-01-01)'},
			}
		};
	    P_POPUP_FORM.validate(validations);

		// 신청정보 가져오기
		const result = doLoadAplyInfo();
		if (result && 
			result.Data) {
			P_POP_RELIEF = result.Data;
			// 폼값 맵핑
			$.formUtil.toForm(P_POP_RELIEF, P_POPUP_FORM);
			// 텍스트 표시
			$.formUtil.toHtml(P_POPUP_FORM, P_POP_RELIEF, 'ps_');
			// 피해자 전자우편주소 분리
			$.formUtil.splitData('p_sufrerEmlAddr','email');
			// 피해자 유선전화번호 분리
			$.formUtil.splitData('p_sufrerTelno','phone');
			// 피해자 휴대전화번호 분리
			$.formUtil.splitData('p_sufrerMbtelNo','mobile');
			// 신청일자 정의
			$('#p_aplyYmd').datebox('setValue', P_POP_RELIEF['aplyYmd']);
			// 접수일자 정의
			$('#p_rcptYmd').datebox('setValue', P_POP_RELIEF['rcptYmd']);
			// 2022.12.16 사망일자 정의
			$('#p_dthYmd').datebox('setValue', P_POP_RELIEF['dthYmd']);
			// 로딩후 이력구분 초기값으로 맵핑처리
			$('#p_hstSeCd').val(P_POP_HST_CD);
			// 2022.12.16 사망일자 달력박스 활성/비활성처리
			_fnActive(P_POP_RELIEF['dthYn']); 
			// 진행상태에 따라 접수일자 READONLY 처리
			if (P_POP_RELIEF['prgreStusCd'] >= CODE.PRGRE_STUS.RECEIPT) {
				$('#p_rcptYmd').datebox('readonly', false);
			}
			else {
				$('#p_rcptYmd').datebox('readonly', true);
				$('#p_rcptYmd').datebox({cls:'app-readonly'});
			}
		}
	}
	
    // 신청인정보수정 초기로딩
    //--------------------------------------------------------//
	function doInitAplcnt() {
		
		// 신청자이메일 도메인 콤보박스
		$('#p_aplcntEmlDomain').appComboBox({
			params: {upCdId: CODE.EMAIL},
			init:   COMBO.INIT_DIRECT,		
			// 콤보 값변경시 입력처리
			change: function() {
				let domain   = $('#p_aplcntEmlAddr2');
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
		$('#p_aplcntMbtelNo1').appComboBox({
			params: {upCdId: CODE.MOBILE},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 회원정보가 있으면
				if (P_POP_RELIEF) {
					// 휴대전화번호 분리
					$.formUtil.splitData('p_aplcntMbtelNo','mobile');
				}
			}
		});
		// 신청인유선전화번호 국번 콤보박스
		$('#p_aplcntTelno1').appComboBox({
			params: {upCdId: CODE.PHONE},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 회원정보가 있으면
				if (P_POP_RELIEF) {
					// 유선전화번호 분리
					$.formUtil.splitData('p_aplcntTelno','phone');
				}
			}
		});
		// 신청인생년월일 콤보박스
		$('#p_aplcntBrdt1').appComboBox({type:'static', rows: STORE.getYears(-10)});
		$('#p_aplcntBrdt2').appComboBox({type:'static', rows: STORE.getMonths()});
		$('#p_aplcntBrdt3').appComboBox({type:'static', rows: STORE.getDays()});
		// 피해자와의 관계 콤보박스
		$('#p_sufrerRelCd').appComboBox({
			params: {upCdId: CODE.RELATION},
			// 콤보값 로딩후 실행함수
			callback: function() {
				// 신청정보가 있으면
				if (P_POP_RELIEF) {
					$('#p_sufrerRelCd').val(P_POP_RELIEF['sufrerRelCd']);
				}
			}
		});
		// 숫자만입력
		bindOnlyNumber($("#p_aplcntMbtelNo2"));
		bindOnlyNumber($("#p_aplcntMbtelNo3"));
		bindOnlyNumber($("#p_aplcntTelno2"));
		bindOnlyNumber($("#p_aplcntTelno3"));
	
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
	            aplcntZip     : {required: true},
	            aplcntAddr    : {required: true},
				aplcntEmlAddr : {email: true, maxlength: 100},
				hstCn         : {required: true}
			},
	        // 검증메세지 정의
	        messages: {
	            aplcntNm      : {required: '성명은 필수 입력 사항입니다.'},
	            aplcntZip     : {required: '우편번호는 필수 입력 사항입니다.'},
	            aplcntAddr    : {required: '주소는 필수 입력 사항입니다.'},
	            aplcntEmlAddr : {email: '전자우편주소를 형식에 맞게 입력하세요.', maxlength: $.validator.format('전자우편주소는 {0}자를 초과할 수 없습니다.')},
				hstCn         : {required: '수정사유는 필수 입력 사항입니다.'},
			}
		};
	    P_POPUP_FORM.validate(validations);

		// 신청정보 가져오기
		const result = doLoadAplyInfo();
		if (result && 
			result.Data) {
			P_POP_RELIEF = result.Data;
			// 폼값 맵핑
			$.formUtil.toForm(P_POP_RELIEF, P_POPUP_FORM);
			// 텍스트 표시
			$.formUtil.toHtml(P_POPUP_FORM, P_POP_RELIEF, 'ps_');
			// 신청인 생년월일 분리
			$.formUtil.splitData('p_aplcntBrdt','date');
			// 신청인 전자우편주소 분리
			$.formUtil.splitData('p_aplcntEmlAddr','email');
			// 신청인 유선전화번호 분리
			$.formUtil.splitData('p_aplcntTelno','phone');
			// 신청인 휴대전화번호 분리
			$.formUtil.splitData('p_aplcntMbtelNo','mobile');
			// 로딩후 이력구분 초기값으로 맵핑처리
			$('#p_hstSeCd').val(P_POP_HST_CD);
		}
	}
	
    // 피해내용수정 초기로딩
    //--------------------------------------------------------//
	function doInitDamage() {
	
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
				hstCn : {required: true}
			},
	        // 검증메세지 정의
	        messages: {
				hstCn : {required: '수정사유는 필수 입력 사항입니다.'}
			}
		};
	    P_POPUP_FORM.validate(validations);

		// 신청정보 가져오기
		const result = doLoadAplyInfo();
		if (result && 
			result.Data) {
			P_POP_RELIEF = result.Data;
			// 폼값 맵핑
			$.formUtil.toForm(P_POP_RELIEF, P_POPUP_FORM);
			// 텍스트 표시
			$.formUtil.toHtml(P_POPUP_FORM, P_POP_RELIEF, 'ps_');
			// 로딩후 이력구분 초기값으로 맵핑처리
			$('#p_hstSeCd').val(P_POP_HST_CD);
		}
	}

    // 신청정보 가져오기
    //--------------------------------------------------------//
	function doLoadAplyInfo() {
		return $.ajaxUtil.ajaxDefault(
			getUrl('/adm/relief/getRelief.do'),{
				aplyNo: P_POP_APLY_NO
			});
	}

    // 우편번호 검색(팝업)
    //--------------------------------------------------------//
    function doPost() {
		P_POST_PREFIX = $(this).data('prefix');
		// 주소검색 OPENAPI 팝업 호출
		popup.openAddress(MODE.OPENAPI);
        return false;
    }
	// 주소검색 클릭 이벤트 처리
	$('.btnPost').bind('click', doPost);
	
	// 최초 데이터로딩
	doInit();
});

// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#'+P_POST_PREFIX+'Zip'  ).val(data['zipNo']);
	$('#'+P_POST_PREFIX+'Addr' ).val(data['roadAddrPart1']);
	$('#'+P_POST_PREFIX+'Daddr').val(data['addrDetail']);
}
