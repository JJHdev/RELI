/**
******************************************************************************************
*** 파일명    : adm_popup.js
*** 설명      : 관리자용 공통 팝업 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-10-25              ntarget
******************************************************************************************
**/
$.extend(popup, {
    //
    //  여기에 업무단위 관리자용 팝업창 추가해서 사용하세요.
    //
    // 2021.10.25 신청접수 레이어 팝업 (modalReceipt.jsp)
	// args.element  : 팝업레이어 영역 Element ( ex. '#popup')
	// args.openArgs : 팝업오픈시 설정 Arguments
	// args.saveCallback: 저장처리 후 콜백함수 function()
	//--------------------------------------------------------//
    openReliefReceipt : function ( args ) {
		// 신청접수 팝업정의
		return $(args.element).appPopup({
			url: getUrl('/adm/relief/modalReceipt.do'),
			type:  'pageload',
			title: '신청접수',
			innerWidth: '750px',
			onhidden: function() {
				// 2022.12.08 접수일자 EasyUI datebox 객체 제거
				$('#p_rcptYmd').datebox('destroy');
			},
			onloaded: function( pop ) {

				// 보완요청폼 객체
			    const m_form   = '#receiptForm';
				// 입력받은 조건데이터
				const m_params = pop.getParseParams();
				// 신청서 데이터 목록
				const m_list   = m_params['receiptList'];
				// 다중항목인 경우
				if (m_list.length > 1) {
					// 목록형태의 신청정보 표시
					$('#modalReceiptInfo').appTableLayout({
						data: m_list,
						cls: 'app-h200 app-scroll',
						columns: [
				            {name:'aplyNo'       ,label:'신청번호'},
				            {name:'aplySeNm'     ,label:'신청구분'},
							// 2023.01.06 신청자명(aplcntNm)을 피해자명(sufrerNm)으로 변경
				            {name:'sufrerNm'     ,label:'피해자명'}, 
				            {name:'bizAreaNm'    ,label:'피해지역'},
				            {name:'aplyKndNm'    ,label:'신청종류'},
				            {name:'aplyYmd'      ,label:'신청일자', formatter: $.formatUtil.toDashDate},
				            {name:'aplcntMbtelNo',label:'수신번호', formatter: $.formatUtil.toPhone}
						]
					});
				}
				// 단일항목인 경우
				else {
					// 폼형태의 신청정보 표시
					$('#modalReceiptInfo').appFormLayout({
						// 데이터
						data: m_list[0],
						// 폼객체
						form: m_form,
						// 폼생성안함
						formCreate: false,
						// ID도 함께 정의
						nameOnly: false,
						// 칼럼구성
						columns: [
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplySeNm'     , label: '신청구분', input: {type:'text',readonly: true}},
							// 2023.01.06 신청자명(aplcntNm)을 피해자명(sufrerNm)으로 변경
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerNm'     , label: '피해자명', input: {type:'text',readonly: true}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'bizAreaNm'    , label: '지　　역', input: {type:'text',readonly: true}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류', input: {type:'text',readonly: true}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , name: 'aplyYmd'      , label: '신청일자', input: {type:'text',readonly: true}, formatter: $.formatUtil.toDashDate},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntMbtelNo', label: '수신번호', input: {type:'text',readonly: true}, formatter: $.formatUtil.toPhone}
						]
					}).loadData({
						'aplySeNm'     : m_list[0]['aplySeNm'     ],
						// 2023.01.06 신청자명(aplcntNm)을 피해자명(sufrerNm)으로 변경
						'sufrerNm'     : m_list[0]['sufrerNm'     ], 
						'bizAreaNm'    : m_list[0]['bizAreaNm'    ],
						'aplyKndNm'    : m_list[0]['aplyKndNm'    ],
						'aplyYmd'      : m_list[0]['aplyYmd'      ],
						'aplcntMbtelNo': m_list[0]['aplcntMbtelNo']
					});
				}
				// 2022.12.08 접수일자 EasyUI datebox 객체 생성
				$('#p_rcptYmd').datebox({
					// 2022.12.08 접수일자 변경시 발신내용 자동수정 이벤트
					onChange: function(newVal, oldVal) {
						if (newVal == oldVal)
							return;
						let _tt = '접수일자 : ';
						let _ot = _tt + oldVal.replace(/-/gi, ".");
						let _nt = _tt + newVal.replace(/-/gi, ".");
						let _cn = $('#trsmCn').val();
						_cn = $.commUtil.replaceAll(_cn, _ot, _nt);
						$('#trsmCn').val(_cn);
					}
				});
			    // 2022.12.08 접수일자 입력박스 하이픈(-) 자동삽입 이벤트
				bindDateHyphen( $('#p_rcptYmd').datebox('textbox') );
				
				$('#p_bizAreaCd').appSelectBox({
					form: 'radio',
					name: 'bizAreaCd',
					url:  getUrl('/com/cmm/getComboBizMng.do')
				});
				// Validation Rule 정의
				$(m_form).validate({
					debug: false,
					onfocusout: false,
					onsubmit: false,
					// 검증룰 정의
					rules: {
						// 2022.12.08 접수일자 추가
						rcptYmd      : {required: true, dateHyphen: true},
						bizAreaCd    : {required: true},
						trnsterNo    : {required: true, phone: true},
						trsmCn       : {required: true}
					},
					// 검증메세지 정의
					messages: {
						// 2022.12.08 접수일자 추가
						rcptYmd: {
							required:   '접수일자는 필수 입력 사항입니다.',
							dateHyphen: '접수일자를 형식에 맞게 입력하세요.'
						},
						bizAreaCd: {required: '피해지역은 필수 선택 사항입니다.'},
						trnsterNo: {
							required: '발신번호는 필수 입력 사항입니다.',
							phone:    '발신번호를 형식에 맞게 입력하세요.'
						},
						trsmCn: {required: '발신내용은 필수 입력 사항입니다.'}
					},
					invalidHandler: $.validateUtil.popupHandler,
					errorPlacement: validatePlacement
				});

			    // 취소버튼 클릭
			    $('#btnReceiptClose').bind('click', function() {
					pop.close();
			        return false;
				});
			    // 저장하기 클릭
			    $('#btnReceiptSave').bind('click', function() {
					let frm = $(m_form);
					// 등록폼의 VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;

					//FORM VALIDATION
					if (frm.valid() === false)
						return false;

					let params = frm.serializeObject();
					$.extend(params, {receiptList: m_params['receiptList']});

					let len = params['receiptList'].length;
					let msg = (len>1?len+"건의 ":"")+"신청서를 접수하시겠습니까?";

			        $.popupMsg.confirm(msg, function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/relief/saveReceipt.do'),
			                JSON.stringify(params),
			                function(ret) {
								$.commMsg.success(ret, '성공적으로 접수되었습니다.', function() {
			                        // 부모창의 함수 호출
									if (args.saveCallback)
										args.saveCallback();
			                		pop.close();
								});
			                }
			            );
			        });
			        return false;
				});
			}
		}).open(args.openArgs);
    },
    // 2021.10.25 보완요청 레이어 팝업 (modalSplemnt.jsp)
	// args.element  : 팝업레이어 영역 Element ( ex. '#popup')
	// args.openArgs : 팝업오픈시 설정 Arguments
	// args.saveCallback: 저장처리 후 콜백함수 function()
	//--------------------------------------------------------//
    openSplemnt : function ( args ) {
		// 보완요청 팝업정의
		return $(args.element).appPopup({
			url: getUrl('/adm/biz/modalSplemnt.do'),
			type:  'pageload',
			title: '보완요청',
			innerWidth: '750px',
			onhidden: function() {
				// EasyUI datebox 객체 제거
				$('#splemntTermYmd').datebox('destroy');
			},
			onloaded: function( pop ) {
				// 입력받은 조건데이터
				const m_params = pop.getParseParams();
				// 보완요청폼 객체
			    const m_form   = '#splemntForm';
				// 신청정보 내용
				$('#modalSplemntInfo').appFormLayout({
					// 폼객체
					form: m_form,
					// 폼생성안함
					formCreate: false,
					// ID도 함께 정의
					nameOnly: false,
					// 칼럼구성
					columns: [
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , id: 'p_aplySeNm'     , name: 'aplySeNm'     , label: '신청구분', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , id: 'p_aplcntNm'     , name: 'aplcntNm'     , label: '성　　명', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , id: 'p_bizAreaNm'    , name: 'bizAreaNm'    , label: '지　　역', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , id: 'p_aplyKndNm'    , name: 'aplyKndNm'    , label: '신청종류', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , id: 'p_aplyYmd'      , name: 'aplyYmd'      , label: '신청일자', input: {type:'text',readonly: true}, formatter: $.formatUtil.toDashDate},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , id: 'p_aplcntMbtelNo', name: 'aplcntMbtelNo', label: '수신번호', input: {type:'text',readonly: true}, formatter: $.formatUtil.toPhone}
					]
				}).loadData({
					'aplySeNm'     : m_params['aplySeNm'     ],
					'aplcntNm'     : m_params['aplcntNm'     ],
					'bizAreaNm'    : m_params['bizAreaNm'    ],
					'aplyKndNm'    : m_params['aplyKndNm'    ],
					'aplyYmd'      : m_params['aplyYmd'      ],
					'aplcntMbtelNo': m_params['aplcntMbtelNo']
				});

				// 보완요청 서류목록
				$('#modalSplemntList').addClass("formGroup checkboxWrap col-md-12");
				$.each(m_params['fileList'], function(i,f) {
					let dom = $('<div class="inputWrap col-md-6"></div>');
					dom.append('<label class="app-dot">'+f['papeNm']+'</label>');
					$('#modalSplemntList').append(dom);
				});
				// EasyUI datebox 객체 생성
				$('#splemntTermYmd').datebox();
				// Validation Rule 정의
				$(m_form).validate({
					debug: false,
					onfocusout: false,
					onsubmit: false,
					// 검증룰 정의
					rules: {
						splemntTermYmd: {required: true, dateHyphen: true},
						trnsterNo     : {required: true, phone: true},
						splemntDmndCn : {required: true}
					},
					// 검증메세지 정의
					messages: {
						splemntTermYmd: {
							required: '보완기간은 필수 입력 사항입니다.',
							dateHyphen: '보완기간을 형식에 맞게 입력하세요.'
						},
						trnsterNo: {
							required: '발신번호는 필수 입력 사항입니다.',
							phone:    '발신번호를 형식에 맞게 입력하세요.'
						},
						splemntDmndCn: {required: '발신내용은 필수 입력 사항입니다.'}
					},
					invalidHandler: $.validateUtil.popupHandler,
					errorPlacement: validatePlacement
				});
			    // 취소버튼 클릭
			    $('#btnSplemntClose').bind('click', function() {
					pop.close();
			        return false;
				});
			    // 보완요청하기 클릭
			    $('#btnSplemntSave').bind('click', function() {
					let frm = $(m_form);
					// FORM VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;
					// FORM VALIDATION
					if (frm.valid() === false)
						return false;

					let obj = frm.serializeObject();

					let params = {
						mode:            MODE.INSERT,
						aplyNo:          obj['aplyNo'         ],
						aplyOder:        obj['aplyOder'       ],
						rcvrNo:          obj['aplcntMbtelNo'  ],
						rcvrNm:          obj['aplcntNm'       ],
						splemntDmndYmd:  obj['splemntDmndYmd' ],
						splemntDmndSeCd: obj['splemntDmndSeCd'],
						splemntTermYmd:  obj['splemntTermYmd' ],
						trnsterNo:       obj['trnsterNo'      ],
						splemntDmndCn:   obj['splemntDmndCn'  ],
						fileList:        m_params['fileList'  ]
					};

					let msg = "신청서를 보완요청하시겠습니까?";

			        $.popupMsg.confirm(msg, function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/biz/saveSplemnt.do'),
			                JSON.stringify(params),
			                function(ret) {
								$.commMsg.success(ret, '성공적으로 보완요청되었습니다.', function() {
			                        // 부모창의 함수 호출
									if (args.saveCallback)
										args.saveCallback();
			                		pop.close();
								});
			                }
			            );
			        });
			        return false;
				});
			    // 공문양식 프린트버튼 클릭 (리포팅툴)
				// 나중에 해당 리포트 정의가 필요함
			    $('#btnSplemntPrint').bind('click', function() {

					let frm = $(m_form);
					// FORM VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;
					// FORM VALIDATION
					if (frm.valid() === false)
						return false;
					let obj = frm.serializeObject();

					let params = {
						mode:            'SPLEMNT',
						aplcntNm:        m_params['aplcntNm'  ],
						sufrerNm:        m_params['sufrerNm'  ],
						idntfcId:        m_params['idntfcId'  ],
						aplyNo:          obj['aplyNo'         ],
						splemntDmndYmd:  obj['splemntDmndYmd' ],
						splemntDmndSeCd: obj['splemntDmndSeCd'],
						splemntTermYmd:  obj['splemntTermYmd' ],
						splemntDmndCn:   obj['splemntDmndCn'  ]
					};
					// 리포트뷰어 팝업 호출
					popup.openReportPopup(params);
					//pop.close();
			        return false;
				});
			}
		}).open(args.openArgs);
    },
	/////////////////////////
    // 2021.10.25 보완요청 레이어 팝업 (modalSplemnt.jsp)
	// args.element  : 팝업레이어 영역 Element ( ex. '#popup')
	// args.openArgs : 팝업오픈시 설정 Arguments
	// args.saveCallback: 저장처리 후 콜백함수 function()
	//--------------------------------------------------------//
    // 취약계층 보완요청 레이어 팝업 (modalSplemnt.jsp)
	// args.element  : 팝업레이어 영역 Element ( ex. '#popup')
	// args.openArgs : 팝업오픈시 설정 Arguments
	// args.saveCallback: 저장처리 후 콜백함수 function()
	//--------------------------------------------------------//
    openLwstSplemnt : function ( args ) {
		// 보완요청 팝업정의
		return $(args.element).appPopup({
			url: getUrl('/adm/biz/modalSplemnt.do'),
			type:  'pageload',
			title: '보완요청',
			innerWidth: '750px',
			onhidden: function() {
				// EasyUI datebox 객체 제거
				$('#splemntTermYmd').datebox('destroy');
			},
			onloaded: function( pop ) {
				// 입력받은 조건데이터
				const m_params = pop.getParseParams();
				// 보완요청폼 객체
			    const m_form   = '#splemntForm';
				// 신청정보 내용
				$('#modalSplemntInfo').appFormLayout({
					// 폼객체
					form: m_form,
					// 폼생성안함
					formCreate: false,
					// ID도 함께 정의
					nameOnly: false,
					// 칼럼구성
					columns: [
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , id: 'p_aplySeCd'     , name: 'aplySeCd'     , label: '신청요건', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , id: 'p_aplcntNm'     , name: 'aplcntNm'     , label: '성　　명', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , id: 'p_etcDmgeArea'    , name: 'etcDmgeArea'    , label: '지　　역', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , id: 'p_appSprtSeCd'    , name: 'appSprtSeCd'    , label: '신청종류', input: {type:'text',readonly: true}},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-7' , id: 'p_aplyYmd'      , name: 'aplyYmd'      , label: '신청일자', input: {type:'text',readonly: true}, formatter: $.formatUtil.toDashDate},
						{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , id: 'p_aplcntMbtelNo', name: 'aplcntMbtelNo', label: '수신번호', input: {type:'text',readonly: true}, formatter: $.formatUtil.toPhone}
					]
				}).loadData({
					'aplySeCd'     : m_params['aplySeCd'     ],
					'aplcntNm'     : m_params['aplcntNm'     ],
					'etcDmgeArea'    : m_params['etcDmgeArea'    ],
					'appSprtSeCd'    : m_params['appSprtSeCd'    ],
					'aplyYmd'      : m_params['aplyYmd'      ],
					'aplcntMbtelNo': m_params['aplcntMbtelNo']
				});

				// 보완요청 서류목록헉
				$('#modalSplemntList').addClass("formGroup checkboxWrap col-md-12");
				$.each(m_params['fileList'], function(i,f) {
					let dom = $('<div class="inputWrap col-md-6"></div>');
					dom.append('<label class="app-dot">'+f['papeNm']+'</label>');
					$('#modalSplemntList').append(dom);
				});
				// EasyUI datebox 객체 생성
				$('#splemntTermYmd').datebox();
				// Validation Rule 정의
				$(m_form).validate({
					debug: false,
					onfocusout: false,
					onsubmit: false,
					// 검증룰 정의
					rules: {
						splemntTermYmd: {required: true, dateHyphen: true},
						trnsterNo     : {required: true, phone: true},
						splemntDmndCn : {required: true}
					},
					// 검증메세지 정의
					messages: {
						splemntTermYmd: {
							required: '보완기간은 필수 입력 사항입니다.',
							dateHyphen: '보완기간을 형식에 맞게 입력하세요.'
						},
						trnsterNo: {
							required: '발신번호는 필수 입력 사항입니다.',
							phone:    '발신번호를 형식에 맞게 입력하세요.'
						},
						splemntDmndCn: {required: '발신내용은 필수 입력 사항입니다.'}
					},
					invalidHandler: $.validateUtil.popupHandler,
					errorPlacement: validatePlacement
				});
			    // 취소버튼 클릭
			    $('#btnSplemntClose').bind('click', function() {
					pop.close();
			        return false;
				});
			    // 보완요청하기 클릭
			    $('#btnSplemntSave').bind('click', function() {
					let frm = $(m_form);
					// FORM VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;
					// FORM VALIDATION
					if (frm.valid() === false)
						return false;

					let obj = frm.serializeObject();

					let params = {
						mode:            MODE.INSERT,
						aplyNo:          obj['aplyNo'         ],
						aplyOder:        obj['aplyOder'       ],
						rcvrNo:          obj['aplcntMbtelNo'  ],
						rcvrNm:          obj['aplcntNm'       ],
						splemntDmndYmd:  obj['splemntDmndYmd' ],
						splemntDmndSeCd: obj['splemntDmndSeCd'],
						splemntTermYmd:  obj['splemntTermYmd' ],
						trnsterNo:       obj['trnsterNo'      ],
						splemntDmndCn:   obj['splemntDmndCn'  ],
						fileList:        m_params['fileList'  ]
					};

					let msg = "신청서를 보완요청하시겠습니까?";

			        $.popupMsg.confirm(msg, function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/biz/saveSplemnt.do'),
			                JSON.stringify(params),
			                function(ret) {
								$.commMsg.success(ret, '성공적으로 보완요청되었습니다.', function() {
			                        // 부모창의 함수 호출
									if (args.saveCallback)
										args.saveCallback();
			                		pop.close();
								});
			                }
			            );
			        });
			        return false;
				});
			    // 공문양식 프린트버튼 클릭 (리포팅툴)
			    $('#btnSplemntPrint').bind('click', function() {

					let frm = $(m_form);
					// FORM VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;
					// FORM VALIDATION
					if (frm.valid() === false)
						return false;
					let obj = frm.serializeObject();

					let params = {
						mode:            'SPLEMNT',
						aplcntNm:        m_params['aplcntNm'  ],
						sufrerNm:        m_params['sufrerNm'  ],
						aplyNo:          obj['aplyNo'         ],
						splemntDmndYmd:  obj['splemntDmndYmd' ],
						splemntDmndSeCd: obj['splemntDmndSeCd'],
						splemntTermYmd:  obj['splemntTermYmd' ],
						splemntDmndCn:   obj['splemntDmndCn'  ]
					};
					// 리포트뷰어 팝업 호출
					popup.openReportPopup(params);
					//pop.close();
			        return false;
				});
			}
		}).open(args.openArgs);
    },
	///////////////
    // 2021.10.25 이력관리 레이어 팝업 (modalMngHst.jsp)
	// args.element  : 팝업레이어 영역 Element ( ex. '#popup')
	// args.openArgs : 팝업오픈시 설정 Arguments
	// args.saveCallback: 저장처리 후 콜백함수 function(aplyNo)
	// args.noAlert: true일 경우 confirm/alert메세지 없이 처리한다.
	//--------------------------------------------------------//
    openMngHst : function ( args ) {
		// 이력관리 팝업정의
		return $(args.element).appPopup({
			url: getUrl('/adm/biz/modalMngHst.do'),
			type:  'pageload',
			title: '이력관리',
			onloaded: function( pop ) {
				// 폼객체
			    const m_form = '#historyForm';
				// 모드
				let p_mode = $(m_form).find('[name="mode"]').val();

				if (p_mode == MODE.VIEW) {
					$('#btnMngHstSave').hide();
					$('#p_hstCn').hide();
					$('#s_hstCn').show();
				}
				else {
					$('#btnMngHstSave').show();
					$('#p_hstCn').show();
					$('#s_hstCn').hide();
				}

				// Validation Rule 정의
				$(m_form).validate({
					debug: false,
					onfocusout: false,
					onsubmit: false,
					// 검증룰 정의
					rules:    {hstCn: {required: true}},
					// 검증메세지 정의
					messages: {hstCn: {required: '내용을 입력하세요.'}},
					invalidHandler: $.validateUtil.popupHandler,
					errorPlacement: validatePlacement
				});

			    // 수정버튼 클릭시 이벤트처리 (현재사용안함)
			    //$('#btnMngHstUpdate').bind('click', function() {
				//	let frm = $(m_form);
				//	$('#btnMngHstSave'  ).show();
				//	$('#btnMngHstUpdate').hide();
				//	$('#p_hstCn').show();
				//	$('#s_hstCn').hide();
				//	frm.find('[name="mode"]').val(MODE.UPDATE);
				//	frm.closest('.layerPop-inner').find('.layerPop-title').html('이력수정');
			    //    return false;
				//});

			    // 확인버튼 클릭시 이벤트처리
			    $('#btnMngHstSave').bind('click', function() {
					let frm = $(m_form);
					// 등록폼의 VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;

					//FORM VALIDATION
					if (frm.valid() === false)
						return false;

					let params = frm.serializeObject();

					let fnCallback = function() {
                        // 부모창의 함수 호출
						if (args.saveCallback)
							args.saveCallback(params['aplyNo']);
                		pop.close();
					};

					let fnSave = function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/biz/saveMngHst.do'),
			                JSON.stringify(params),
			                function(ret) {
			                    $.ajaxUtil.success(ret, function() {
									if (args.noAlert) {
										fnCallback();
									}
									else {
										$.commMsg.alert('성공적으로 저장되었습니다.', fnCallback);
									}
			                    });
			                }
			            );
					};
					if (args.noAlert) {
						fnSave();
					}
					else {
				        $.popupMsg.confirm("저장하시겠습니까?", fnSave);
					}
			        return false;
				});
			    // 취소버튼 클릭시 이벤트처리
			    $('#btnMngHstClose').bind('click', function() {
					pop.close();
			        return false;
				});
			}
		}).open(args.openArgs);
    },
    // 2021.11.04 파일다운로드 이력팝업
	// args.params   : 다운로드 이력팝업 저장데이터
	// args.saveCallback: 저장처리 후 콜백함수 function()
	//--------------------------------------------------------//
    openDownLog : function ( args ) {
		// 파일다운로드 이력팝업정의
		let pop = $('#layerPopup').appPopup({
			// 팝업 제목
			title:   '파일다운로드 사유등록',
			// 팝업 스타일시트
			popupCls:'layerPop form type3 listLock',
			// 팝업 내용
			message: function() {
				let dom = $('<form name="resnForm"></form>');
				let inp = $('<textarea id="downResn" name="downResn" class="w100" rows="5" maxlength="65" style="height:100px" readonly="readonly"></textarea>');
				inp.prop('placeholder', '파일 다운로드 사유를 입력해 주세요.');
				let div = $('<div class="inputGroup"><div class="inputWrap one" style="text-align:left;"></div></div>');
				div.find('.inputWrap').append('<input type="radio" name="downResnRadio" id="downResnRadio1" value="1" data-value="신청 정보 확인"/>  <label for="downResnRadio1">신청 정보 확인</label><br/>');
				div.find('.inputWrap').append('<input type="radio" name="downResnRadio" id="downResnRadio2" value="2" data-value="환경오염 피해조사"/><label for="downResnRadio2">환경오염 피해조사</label><br/>');
				div.find('.inputWrap').append('<input type="radio" name="downResnRadio" id="downResnRadio4" value="4" data-value="신청파일 OCR분석"/><label for="downResnRadio4">신청파일 OCR 분석</label><br/>');
				div.find('.inputWrap').append('<input type="radio" name="downResnRadio" id="downResnRadio3" value="3" data-value="기타"/><label for="downResnRadio3">기타</label><br/>');
				div.find('.inputWrap').append(inp);
				div.find('.inputWrap').append('<p style="text-align:center; padding-top:20px;">확인을 누르시면 다운로드가 진행됩니다.</p>');
				dom.append(div);

				let btn = $('<div class="btnWrap type2 one"></div>');
				btn.append('<a href="javascript:void(0);" class="app-popup-btn">확인</a>');
				dom.append(btn);
				// readonly 배경색
				inp.prop("readonly", true).css("background", "#efefef");

				// 확인 버튼 클릭시
				dom.find('a.app-popup-btn').bind('click', function() {
					let resn = dom.find('[name="downResn"]').val();
					// 입력 유효성 체크
					if ($.commUtil.empty(resn)) {
						$.commMsg.alert('파일다운로드 사유를 입력하세요.');
						return false;
					}
					let params = $.extend({
						downResn: resn
					}, args.params);

					// 저장처리
					$.ajaxUtil.ajaxSave(
			            getUrl('/sys/log/saveDownLog.do'),
						JSON.stringify(params),
						function() {
							args.saveCallback();
							pop.close();
						}
					);
					return false;
				});
                // label 클릭 이벤트
				dom.find('label').on('click', function(){
                    dom.find("input[id='"+$(this).attr("for")+"']").click();
                });
                // radio 클릭 할 때 이벤트
                dom.find('input[type="radio"]').on("click", function(){
                    let selVal = $(this).val();
                    if(selVal == '1' || selVal == '2' || selVal == '4'){
                        inp.val($(this).data("value"));
                        inp.prop("readonly", true).css("background", "#efefef");
                    }else if(selVal == '3'){
                        inp.val("");
                        inp.prop("readonly", false).css("background", "");
                    }
                });
				return dom;
			}
		}).open();
		return pop;
    },
    // 2021.11.18 예비조사계획/본조사계획 대상자 등록 레이어 팝업
	// args.element  : 팝업레이어 영역 Element ( ex. '#popup')
	// args.openArgs : 팝업오픈시 설정 Arguments
	// args.title    : 팝업 타이틀
	// args.srchType : 검색조건 구분타입 ('PRPTEXMN' or 'MNSVY')
	// args.openUrl  : 팝업오픈시 로딩 페이지 URL
	// args.saveUrl  : 대상등록시 저장 페이지 URL
	// args.saveCallback: 저장처리 후 콜백함수 function()
	// 2021.12.14 예비조사/본조사 팝업 분리
	//--------------------------------------------------------//
    openExmn : function ( args ) {
		// 조사계획 대상자 등록 팝업정의
		return $(args.element).appPopup({
			url:   args.openUrl,
			type:  'pageload',
			title: args.title,
			onshown : function( pop) {
				$('#btnTargetSearch').trigger('click');
			},
			onloaded: function( pop ) {
				$('#p_srchType').val( args.srchType );
				// EasyUI datebox 객체 생성
				$('#srchRcptStdt').datebox();
				$('#srchRcptEndt').datebox();
				// 2023.01.11 식별ID 검색어 입력 엔터 이벤트 처리
				bindEnter($('#srchIdntfcId'), $('#btnTargetSearch'));

				// 그리드 칼럼목록
				let m_columns = [
			        {field:'chckId'      ,checkbox: true},
		            {field:'aplyNo'      ,width:110,halign:'center',align:'center',title:'신청번호'},
		            {field:'idntfcId'    ,width:100,halign:'center',align:'center',title:'식별아이디'},
		            {field:'bizAreaNm'   ,width:200,halign:'center',align:'center',title:'피해지역'},
		            {field:'sufrerNmMask',width:100,halign:'center',align:'center',title:'피해자명'},
					{field:'aplcntNmMask',width:100,halign:'center',align:'center',title:'신청자명'}
				];
				// 본조사인 경우
				if (args.srchType == 'MNSVY') {
					m_columns.splice(3, 0, {
						field:'addYn',
						width:100,
						halign:'center',
						align:'center',
						title:'추가신청'
					});
				}
				// 예비조사인 경우
				else if (args.srchType == 'PRPTEXMN') {
					m_columns.splice(3, 0, {
						field:'exmnOder',
						width:100,
						halign:'center',
						align:'center',
						title:'기인정여부',
						formatter: function(v,r,i) {
							if ($.commUtil.empty(v))
								return '';
							if (v == 0)
								return '';
							return '<input type="checkbox" class="app-lgcy-check" value="'+v+'" data-index="'+i+'"/>';
						}
					});
				}

				// 등록폼 객체
			    let m_form = $('#targetForm');
				// 대상자 목록
				let m_grid = $('#p_targetGrid').datagrid({
					fit: true,
			        // 그리드 결과데이터 타입
			        contentType: 'application/json',
			        // 그리드 결과데이터 타입
			        dataType: 'json',
			        // 그리드 데이터연동 방식
			        method: 'POST',
			        // 체크박스 KEY값필드
			        idField:'idntfcId',
			        // 칼럼정의
			        columns: [ m_columns ],
					// 로딩 후 콜백함수
					onLoadSuccess: function() {
						// 기인정여부 체크시 데이터맵핑
						let p = m_grid.datagrid('getPanel');
						p.find('.app-lgcy-check').each(function() {
							$(this).on('click', function() {
								let n = $(this).data('index');
								if ($(this).is(':checked'))
									m_grid.datagrid('getRows')[n]['lgcyExmnOder'] = $(this).val();
								else
									m_grid.datagrid('getRows')[n]['lgcyExmnOder'] = null;
							});
						});
					}
				});
				// 조회 클릭
				$('#btnTargetSearch').bind('click', function() {
					// 선택된 항목 CLEAR
					m_grid.datagrid('clearSelections');
			        // 검색폼 데이터 객체화
			        var obj = m_form.serializeObject();
			        // 그리드 목록조회 URL
			        m_grid.datagrid('options')['url'] = getUrl('/adm/relief/getListReliefTarget.do');
			        // 검색폼 그리드 검색
			        m_grid.datagrid('load', obj);
					return false;
				});
			    // 선택등록 클릭
			    $('#btnTargetSave').bind('click', function() {
					let obj = m_form.serializeObject();
					let lst = m_grid.datagrid('getSelections');
					if (lst.length == 0) {
						$.commMsg.alert('대상자를 한명 이상 선택하세요.');
						return false;
					}
					let params = $.extend(obj, {
						mode: MODE.INSERT,
						exmnList: lst
					});
			        $.popupMsg.confirm(lst.length+"명의 대상자를 등록하시겠습니까?", function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                args.saveUrl,
			                JSON.stringify(params),
			                function(ret) {
								$.commMsg.success(ret, '성공적으로 등록되었습니다.', function() {
			                        // 부모창의 함수 호출
									if (args.saveCallback)
										args.saveCallback();
			                		pop.close();
								});
			                }
			            );
			        });
			        return false;
				});
			}

		}).open(args.openArgs);
    },

});
