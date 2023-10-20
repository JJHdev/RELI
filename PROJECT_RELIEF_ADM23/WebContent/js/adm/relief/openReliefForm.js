/**
******************************************************************************************
*** 파일명 : openReliefForm.js
*** 설명글 : 관리자 - 구제급여 - 신청 (내용 등록)
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.18    LSH
******************************************************************************************
**/

// 우편번호검색시 대상항목 PREFIX
let C_POST_PREFIX = '';
// 온라인설문지 팝업
let C_POPUP_SURVERY = false;

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
	let P_TABS   = $('#appReliefTab');// 신청구분 탭

	const P_PDTY_CD   = $('#papeDtySeCd').val(); // 서류업무구분
	const P_APLY_CD   = $('#aplySeCd'   ).val(); // 신청구분
	const P_APLY_NO   = $('#aplyNo'     ).val(); // 신청번호
	const P_APLY_ODER = $('#aplyOder'   ).val(); // 신청차수
	const P_MODE      = $('#mode'       ).val(); // 저장모드
	
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
		
		// 신청종류
		$('#layerAplyKnd').append('<span id="appAplyKndCd"></span>');
		// 신청종류 체크박스
		P_AKIND = $('#appAplyKndCd').appSelectBox({
			form: 'checkbox',
			name: 'aplyKndList', 
			wrapCls: 'formType1',
			params: {upCdId: CODE.APLYKIND},
			filter: function(obj) {
				// 2021.12.20 재산피해보상비 항목 제외
				if (obj['code'] == CODE.APLY_KIND_CD.PRP)
					return false;
				// 신청종류에 따라 항목 필터링
				if (P_APLY_CD == CODE.APLY_CD['DPTH'])
					return ($.inArray(obj['code'], CODES.APLY_KIND_DPTH) >= 0);
				else
					return ($.inArray(obj['code'], CODES.APLY_KIND_LIVE) >= 0);
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

	// 2022.02.10 신청일자 달력박스 처리
	$('#aplyYmd').datebox({cls:'app-relief-datebox'});

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
            //aplcntMbtelNo2: {required: true},
			//aplcntMbtelNo3: {required: true},
			aplcntEmlAddr : {email: true, maxlength: 100}
		},
        // 검증메세지 정의
        messages: {
            aplcntNm      : {required: '성명은 필수 입력 사항입니다.'},
            aplcntRrno1   : {required: '주민등록번호 앞자리는 필수 입력 사항입니다.', regNo1: '주민등록번호 앞자리를 형식에 맞게 입력하세요.'},
            aplcntRrno2   : {required: '주민등록번호 뒷자리는 필수 입력 사항입니다.', regNo2: '주민등록번호 뒷자리를 형식에 맞게 입력하세요.'},
            aplcntZip     : {required: '우편번호는 필수 입력 사항입니다.'},
            aplcntAddr    : {required: '주소는 필수 입력 사항입니다.'},
            //aplcntMbtelNo2: {required: '휴대전화번호는 필수 입력 사항입니다.'},
            //aplcntMbtelNo3: {required: '휴대전화번호는 필수 입력 사항입니다.'},
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
			//$.extend(validations.rules, {
		    //    sufrerMbtelNo2: {required: true},
			//    sufrerMbtelNo3: {required: true}
			//}, true);
			//$.extend(validations.messages, {
		    //    sufrerMbtelNo2: {required: '피해자휴대전화번호는 필수 입력 사항입니다.'},
			//    sufrerMbtelNo3: {required: '피해자휴대전화번호는 필수 입력 사항입니다.'}
			//}, true);
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
	// 2022.02.10 신청일자 검증로직 추가
	$.extend(validations.rules,    { aplyYmd: {required: true, dateHyphen: true} }, true);
	$.extend(validations.messages, { aplyYmd: {
			required:   '신청일자를 입력해 주세요.',
			dateHyphen: '신청일자를 형식에 맞게 입력해 주세요.(예. 2022-01-01)'
		} 
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
        bankCd       : {required: true},
        dpstrNm      : {required: true},
        actno        : {required: true, regx: /^[0-9\-]{11,30}$/}
	}, true);
	$.extend(validations.messages, {
		bankCd       : {required: '지급은행명은 필수 입력 사항입니다.'},
		dpstrNm      : {required: '예금주명은 필수 입력 사항입니다.'},
		actno        : {
			required: '계좌번호는 필수 입력 사항입니다.',
			regx:     '계좌번호를 형식에 맞게 입력하세요.'
		}
	}, true);
	
    P_FORM.validate(validations);

    // 최초 로딩시 정보가져오기
    //--------------------------------------------------------//
	function doInit() {
		// 신청구분 탭 생성
		doCreateTabs();
		if (P_MODE == MODE.INSERT) {
			// 신청서류그룹
			doLoadPapeGroup();
			// 서류그룹제어
			doChangePapeGroup(P_AKIND.getValue());
		}
		else {
			// 신청정보 가져오기
			doLoadAplyInfo();
		}
		// 2023.03.29 이용동의 DEFAULT 체크
		$('#agreeYn').prop('checked', true);
		$('#indvInfoClctAgreYn').val('Y');
	}

	// 2021.11.05 신청구분 탭생성
    //--------------------------------------------------------//
	function doCreateTabs() {
		
		P_TABS.addClass("relief-tab tabWrap type1");
		
		let ul = $('<ul class="li-3"></ul>');
		
		$.each(STORE.APLY_CD, function(i,t) {
			let ac = $('<a href="javascript:void(0);">'+t.text+'</a>');
			ac.data('code', t.code);
			let li = $('<li></li>');
			li.append(ac);
			if (t.code == P_APLY_CD)
				li.addClass('on');
			ul.append(li);
		});
		P_TABS.append(ul);
		
		P_TABS.find('ul > li > a').bind('click', function() {
			let code = $(this).data('code');
			if (code == P_APLY_CD)
				return false;
			$.formUtil.submitForm(getUrl('/adm/relief/openReliefForm.do'), {
				params: { aplySeCd: code }
			});
			return false;
		});
	}
	
    // 신청정보 가져오기
    //--------------------------------------------------------//
	function doLoadAplyInfo() {
		
		// 신청정보 가져오기
		const result = $.ajaxUtil.ajaxDefault(
			getUrl('/adm/relief/getRelief.do'),{
				aplyNo:   P_APLY_NO
			});
		if (result && 
			result.Data) {
			
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
			
			$.formUtil.toForm({
				// 수정시엔 신청종류 텍스트표시
				s_aplyKndNm  : P_RELIEF['aplyKndNm']
			});
			// 수정시엔 신청종류 HIDDEN값으로 정의
			$.each(P_RELIEF['aplyKndList'], function(i,s) {
				$('#layerAplyKnd').append('<input name="aplyKndList" type="hidden" value="'+s+'"/>');
			});
			// 2022.02.10 신청일자 정의 (READONLY)
			$('#aplyYmd').datebox('setValue', P_RELIEF['aplyYmd']);
			$('#aplyYmd').datebox('readonly', true);
			
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
			
			if (!$.commUtil.empty(P_RELIEF['rspnsMngNo'])) {
				$('#btnSurveyView').show();
			}
			
		}
	}
		
	// 신청종류(의료비,요양생활수당,장의비,유족보상비) 선택에 따른 서류그룹 제어
    //--------------------------------------------------------//
	function doChangePapeGroup( kndLst ) {
		P_VALID   = [];
		let inMCP = ($.inArray(CODE.APLY_KIND_CD.MCP, kndLst) >= 0);
		let inRCP = ($.inArray(CODE.APLY_KIND_CD.RCP, kndLst) >= 0);
		let inDTH = ($.inArray(CODE.APLY_KIND_CD.DTH, kndLst) >= 0);
		let inBRV = ($.inArray(CODE.APLY_KIND_CD.BRV, kndLst) >= 0);

		$('.usr-file-list').each(function() {
			let code = $(this).data('code');
			
			if (code == CODE.PAPE_CD.MCP && !inMCP)
				$(this).hide();
			else if (code == CODE.PAPE_CD.RCP && !inRCP)
				$(this).hide();
			else if (code == CODE.PAPE_CD.DTH && !(inDTH || inBRV))
				$(this).hide();
			else {
				$(this).show();
				P_VALID.push(code);
			}
		});
	}
	
    // 신청서류그룹 가져오기
    //--------------------------------------------------------//
	function doLoadPapeGroup( data ) {
		
		// 신청서류그룹 가져오기
		const rows = $.ajaxUtil.ajaxDefault(
			getUrl('/adm/file/getListPapeGroup.do'), {
				papeDtySeCd: P_PDTY_CD,
				aplySeCd:    P_APLY_CD
			}
		);
		
		if (rows) {
			let mode      = (data ? MODE.UPDATE : MODE.INSERT);
			let dcmtNo    = (data ? data['aplyNo'  ] : null); 
			let dtlDcmtNo = P_APLY_ODER;
			let titleBtn  = '';
			titleBtn += '<a href="javascript:void(0);" class="app-btn-survey app-m3" id="btnSurvey">온라인 설문지 작성</a>';
			titleBtn += '<a href="javascript:void(0);" class="app-btn-survey app-m3" id="btnSurveyView">온라인 설문지 보기</a>';
			
			$.each(rows, function(i, row) {
				let div = $('<div class="usr-file-list"></div>');
				div.data('code', row['papeCd']);
				div.data('name', row['papeNm']);

				let msg = false;
				if (row['papeCd'] == 'D001') {
					msg = '※ 설문지는 \'온라인설문지\' 혹은 \'설문지(중복지급확인서 포함)\' 중 1개만 작성 후 제출';
				}

				P_LIST.push(div.appAplyFile({
					system: SYSTEM.ADMIN['code'],
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
					},
					// 2021.12.27 ADD 제목우측버튼 표시
					titleBtn: titleBtn,
					// 2022.01.28 ADD 테이블 추가 설명글
					footerMsg: msg
				}));
				P_PAPE.append(div);
				
				// 2022.01.26 대리(사)인 경우 위임장 메세지 표시 변경
				if (P_APLY_CD == CODE.APLY_CD['DPTH']) {
					// 서류양식 ROW LOOP
					P_PAPE.find('tr.app-pape-rows').each(function() {
						let code = $(this).data('code');
						if ($.inArray(code, ['D00501','D00502']) >= 0) {
							$(this).find('td:first').append([
								'<span class="app-small-text">',
								'해당 서류는 담당자(02-2284-1850)에게 문의 후 첨부해주세요',
								'</span>'
							].join(''));
						}
					});
				}
				titleBtn = false;
			});
			// 2021.12.27 온라인설문지 버튼이벤트
			$('#btnSurvey').bind('click', doOpenSurvey);			
			$('#btnSurveyView').bind('click', doOpenSurveyView);
			$('#btnSurvey').show();
			$('#btnSurveyView').hide();
		}
	}	

	// 설문지파일 업로드여부 체크
    //--------------------------------------------------------//
	function isUploadSurveyFile() {

		// 설문지파일 업로드여부
		let isSurveyFile = false;

		$.each(P_LIST, function(i, file) {
			// 유효성체크 SKIP파일인 경우			
			if ($.inArray(file.getPapeGroup(), P_VALID) < 0)
				return true;
			
			// 2022.01.24 설문지파일 업로드여부 체크
			if (file.doExistFile(CODE.SURVEY_PAPE_CD)) {
				isSurveyFile = true;
			}
		});
		return isSurveyFile;
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
			removeFiles: [],
			// 2023.03.29 이용동의여부 맵핑
			indvInfoClctAgreYn: ($('#agreeYn').is(":checked") ? 'Y':'N')
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

		// 2023.03.29 제출시에만 온라인설문지 필수 체크
		if (act == MODE.SUBMIT) {
			// 2022.01.24 설문지파일 업로드여부 체크
			let isSurveyFile = isUploadSurveyFile();
			// 2022.01.24 온라인설문지 작성여부 체크
			let isSurveyData = !$.commUtil.empty($('#rspnsMngNo').val());
			// 2022.01.24 온라인설문지/설문지파일 교차체크 (설문지파일의 필수여부가 제외되어야함)
			if (!isSurveyFile && !isSurveyData) {
				$.commMsg.alert('온라인 설문지 작성, 설문지 파일 업로드 둘 중 하나를 수행해 주세요.');
				return false;
			}
			// 온라인설문지나 설문지파일 둘중 하나가 있는지 확인
			if (isSurveyFile && isSurveyData) {
				$.commMsg.alert('이미 온라인설문지를 작성하셨습니다. 업로드한 설문지 파일은 삭제해 주세요.');
				return false;
			}
		}
		// 2023.03.29 제출시에만 이용동의 필수 체크
		if (act == MODE.SUBMIT) {
			// 이용동의 확인체크
			if ($('#agreeYn').is(":checked") === false) {
				$.commMsg.alert('개인정보 수집ㆍ이용 및 제공 동의를 확인해 주세요.');
				return false;
			}
		}

		let msg = (act == MODE.SUBMIT ? '제출' : '임시저장');

        $.commMsg.confirm(msg+"하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/adm/relief/saveRelief.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						// 마이페이지로 이동처리
						$.commMsg.alert('성공적으로 '+msg+'되었습니다.', function() {
							goNext();
						});
                    });
                }
            );
        });
        return false;
    }

    // 저장후 접수현황으로 이동 처리
    //--------------------------------------------------------//
    function goNext() {
		goUrl( getUrl('/adm/relief/listReliefRcpt.do') );
    }

    // 우편번호 검색(팝업)
    //--------------------------------------------------------//
    function doPost() {
		C_POST_PREFIX = $(this).data('prefix');
		// 2021.12.24 LSH 자체DB주소검색 팝업 호출
		//popup.openAddress(MODE.SEARCH);
		// 2022.01.18 LSH 주소검색 OPENAPI 팝업 호출
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

    // 2021.12.27 온라인 설문지 작성 (팝업)
    //--------------------------------------------------------//
    function doOpenSurvey() {
	
		// 2022.01.24 설문지파일 업로드여부 체크
		let isSurveyFile = isUploadSurveyFile();
		// 설문지파일이 업로드된 경우
		if (isSurveyFile) {
			$.commMsg.alert('이미 업로드한 설문지 파일이 있습니다.');
			return false;
		}

		let no  = $('#rspnsMngNo').val();
		let tit = '온라인 설문지 ' 
		        + ($.commUtil.empty(no) ? '등록' : '수정');
		let url = getUrl('/adm/relief/modalSurvey.do')
		        + ($.commUtil.empty(no) ? '' : '?rspnsMngNo='+no);;
		
		// 2021.12.27 LSH 온라인설문지 팝업 오픈
		C_POPUP_SURVERY= $.commModal.loadView(tit, url, {
			// 다이얼로그 크기
			sizeType: 'large', 
			// 배경클릭시 닫기기능 제외
			closeByBackdrop: false, 
			// ESC 누를시 닫기기능 제외
			closeByKeyboard: false
		});
        return false;
    }

    // 2022.01.19 온라인 설문지 상세 (팝업)
    //--------------------------------------------------------//
    function doOpenSurveyView() {
		let no = $('#rspnsMngNo').val();
		// 2021.12.27 LSH 온라인설문지 팝업 오픈
		C_POPUP_SURVERY= $.commModal.loadView(
			'온라인 설문지 상세보기', 
			getUrl('/adm/relief/modalSurveyView.do?rspnsMngNo='+no), 
			{sizeType: 'large'}
		);
        return false;
    }

	// 주소검색 클릭 이벤트 처리
	$('.btnPost').bind('click', doPost);
	// 임시저장 클릭 이벤트 처리
	$('#btnTmpSave').bind('click', doTmpSave);
	// 제출하기 클릭 이벤트 처리
	$('#btnSubmit').bind('click', doSubmit);
	
	// 최초 데이터로딩
	doInit();
});

// 우편번호검색 팝업창에서 호출하는 함수
function jusoCallBack( data ) {
	$('#'+C_POST_PREFIX+'Zip'  ).val(data['zipNo']);
	$('#'+C_POST_PREFIX+'Addr' ).val(data['roadAddrPart1']);
	$('#'+C_POST_PREFIX+'Daddr').val(data['addrDetail']);
}

// 온라인설문지 팝업창에서 호출하는 함수
function surveyCallBack( data ) {
	C_POPUP_SURVERY.close();
	$('#rspnsMngNo').val(data['rspnsMngNo']);

	$('#btnSurveyView').show();
}
