/**
******************************************************************************************
*** 파일명    : comm_popup.js
*** 설명      : 공통 팝업 자바스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-02-10              ntarget
******************************************************************************************
**/
var popup = {
    //
    //  여기에 업무단위 팝업창 추가해서 사용하세요.
    //
    // 2021.12.23 자체DB 주소검색 팝업
	// mode = SEARCH: 자체DB 주소검색 (기본값)
	// mode = OPENAPI: 행안부 주소검색
    openAddress : function (mode) {
		mode = mode || 'OPENAPI';
		// 2022.01.27 CSLEE : TITLE에서 [mode] 부분 제거
		return $.commModal.loadView(
			//'주소검색['+mode+']',
            '주소검색',
			getUrl('/html/popupAddress.html'), {
				// 2022.12.20 배경클릭시 창닫힘 방지
				closeByBackdrop: false,
				func: function(dialog) {

					let P_JUSO = false;
					let P_FORMAT = function(v,r) {
						return v+'<br><small>'+r['jibunAddr']+'</small>';
					};
					$('#appAddressForm').hide();
					// (comm_board.js 참고)
					let m_grid = $('#addressGrid').appBoard({
						// 검색 URL
						url: getUrl("/com/cmm/getListAddress.do"),
						// 칼럼 정의
						columns: [
						  	{name:'roadAddr', label:'도로명주소', formatter:P_FORMAT},
						  	{name:'zipNo', label:'우편번호'}
						],
						// 페이징영역 ID
						paging: '#addressPage',
						pagination: {
							page:    1,
							total:   0,
							pages:   0,
							display: 3
						},
						// 기본검색조건
						params: {
							// 검색모드
							mode: mode,
							// 화면출력수
							rows: 5
						},
						select: function(json) {
							P_JUSO = json;
							$('#appAddressForm').show();
							$('#baseAddr').html(P_FORMAT(P_JUSO['roadAddr'], P_JUSO));
							$('#detailAddr').val('');
							$('#appAddressGrid').hide();
						}
					});
					// 2021.12.24 OPEN API 주소 검색시 필터링 함수
					// 특수문자, 특정문자열(sql예약어의 앞뒤공백포함) 제거
					let fnValidate = function(obj) {
						let v = obj.val();
						if (v.length == 0) {
							$.commMsg.alert('주소검색 키워드를 입력하세요.', function() {
								obj.focus();
							});
							return false;
						}
						//특수문자 제거
						let expText = /[%=><]/ ;
						if(expText.test(v) == true){
							$.commMsg.alert("특수문자를 입력 할수 없습니다.") ;
							obj.val(v.split(expText).join(""));
							return false;
						}
						//특정문자열(sql예약어의 앞뒤공백포함) 제거
						let keyCheck = true;
						$.each([
							//sql 예약어
							"SELECT", "INSERT" , "DELETE"  , "UPDATE",
							"CREATE", "DROP"   , "EXEC"    , "UNION" ,
							"FETCH" , "DECLARE", "TRUNCATE", "OR"
						], function(i, keywd) {
							let regex = new RegExp(keywd ,"gi") ;
							if (regex.test(v) ) {
							    $.commMsg.alert("\"" + keywd+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
								obj.val(v.replace(regex, ""));
								keyCheck = false;
								return false;
							}
						});
						return keyCheck;
					};
					// 검색 클릭
					$('#btnAdrsSearch').bind('click', function() {
						P_JUSO = false;
						$('#appAddressGrid').show();
						$('#appAddressForm').hide();
						let o = $('#addressText');
						if (!fnValidate(o))
							return false;
						m_grid.load({srchText: o.val(),page:1});
						return false;
					});
					// 확인 클릭
					$('#btnAdrsConfirm').bind('click', function() {
						if (!P_JUSO) {
							$.commMsg.alert('선택된 주소가 없습니다.');
							return false;
						}
						jusoCallBack({
							zipNo:         P_JUSO['zipNo'],
							roadAddrPart1: P_JUSO['roadAddr'],
							addrDetail:    $('#detailAddr').val()
						});
						dialog.close();
						return false;
					});
					// 취소 클릭
					$('#btnAdrsClose').bind('click', function() {
						dialog.close();
						return false;
					});
				}
			}
		);
    },
    // 2021.12.14 LSH 전자서명 팝업
	// 2023.01.17 LSH options 인자 추가
	openSignature: function( callback, saveData, options ) {
		
		let args = $.extend({
			title:   '전자서명',
			loadUrl: getUrl('/html/popupSignature.html'),
			saveUrl: getUrl('/com/cmm/saveSignature.do')
		}, options);

		return $.commModal.loadView(
			args.title,
			args.loadUrl, {
				closable: false,
				func: function(dialog) {
					var canvas = $("#signature-pad canvas")[0];
					var sign = new SignaturePad(canvas, {
						minWidth: 2,
						maxWidth: 5,
						penColor: "rgb(66, 133, 244)"
					});
					// 캔버스 크기 리사이즈
					function resizeCanvas(){
						var canvas    = $("#signature-pad canvas")[0];
						var ratio     = Math.max(window.devicePixelRatio || 1, 1);
						canvas.width  = canvas.offsetWidth  * ratio;
						canvas.height = canvas.offsetHeight * ratio;
						canvas.getContext("2d").scale(ratio, ratio);
					}
					resizeCanvas();

				   	// 2022.12.16 닫기버튼 클릭시 이벤트 처리
					$('#btnSignClose').bind('click', function() {
						dialog.close();
				   		return false;
					});
				   	// 지우기버튼 클릭시 이벤트 처리
					$('#btnSignClear').bind('click', function() {
						sign.clear();
				   		return false;
					});
				    // 확인버튼 클릭시 이벤트처리
					$('#btnSignSave').bind('click', function() {
						if (sign.isEmpty()) {
							$.commMsg.alert('사인해 주세요.');
							return false;
						}
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                args.saveUrl,
			                JSON.stringify({sign: sign.toDataURL()}),
			                function(ret) {
								callback(saveData, ret['FileName']);
								dialog.close();
			                }
			            );
				   		return false;
					});
				}
			}
		);
	},
    // 2021.12.13 LSH 관리자인증 팝업
	openAdminCert: function() {
		return $.commModal.loadView(
			'관리자 인증',
			getUrl('/html/popupAdminCert.html'), {
				func: function(dialog) {

					let message = $('#popupResult');
					let buttons = [{
						id: 'btnPopupOk',
						text: '예',
						click: function() {
							goUrl( getUrl('/usr/relief/openRelief.do') );
							return false;
						}
					},{
						id: 'btnPopupClose',
						text: '아니오',
						click: function() {
							dialog.close();
							return false;
						}
					}];

					// 인증처리
					$('#btnPopupCert').on("click", function() {

						message.empty();
						message.removeClass("fail");
						message.removeClass("success");

						if ($.commUtil.empty($('#adminNo').val())) {
							message.append('<p class="info">관리자번호를 입력하세요.</p>');
							message.addClass("fail");
							$('#adminNo').focus();
							return false;
						}
						let data = $('#popupAdminForm').serializeObject();
						let result = $.ajaxUtil.ajaxDefault(
							getUrl("/com/cmm/certifyAdmin.do"),
							data
						);
						message.empty();
						message.removeClass("fail");
						message.removeClass("success");

						if (result.Code == '0') {
							message.addClass("success");
							message.append('<p class="info">일치하는 관리자 번호입니다.</p>');
							message.append('<p>나에게 맞는 피해구제 제도찾기를 건너 뛰시겠습니까?</p>');

							let btn = $('<div class="btn"></div>');
							$.each(buttons, function(i,b) {
								let obj = $('<a href="javascript:void(0);"></a>');
								obj.prop('id', b.id);
								obj.append(b.text);
								obj.bind('click', b.click);
								btn.append(obj);
							});
							message.append(btn);
							return false;
						}
						else {
							message.addClass("fail");
							message.append('<p class="info">일치하지 않는 관리자 번호입니다.</p>');
							message.append('<p>관리자번호를 다시 확인해주세요</p>');

							let btn = $('<div class="btn"></div>');
							let obj = $('<a href="javascript:void(0);"></a>');
							obj.prop('id', 'btnPopupClose');
							obj.append('닫기');
							obj.bind('click', function() {
								dialog.close();
								return false;
							});
							btn.append(obj);
							message.append(btn);
							return false;
						}
					});
				}
			}
		);
	},

    // 2021.12.01 LSH 파일업로드 팝업
	openUpload: function( options ) {
		// options.url : 업로드 URL
		// options.extensions : 허용 확장자 배열
		// options.maxBytes   : 파일 제한 크기
		// options.callback   : 저장후 처리 함수
		// options.params     : 파일관련 변수
		// options.addParams  : 추가 변수
		// options.addContent : 추가 정보표시 함수
		// options.openInit   : 2022.12.06 팝업 로딩후 실행함수
		// options.validate   : 2022.12.06 저장시 검증 함수

		$.commModal.loadView(
			options.title || '파일업로드',
			getUrl('/html/popupFileUpload.html'), {
				cssClass: 'modal-w700 modal-center',
				closable: false,
				func: function(dialog) {
					let popTable = $('#popupContentTable');
					let popForm  = $('form[name="popupUploadForm"]');
					let bodyElm  = dialog.getModalBody();

					// 에러메세지 표시함수
					let _showError = function( msg ) {
						bodyElm.find('p.err').html( msg );
					};
					// 파일검증함수
					let _validate = function( fobj ) {
						// 파일빈값체크
						if ($.commUtil.empty( fobj.val() )) {
							_showError('파일을 선택해 주세요.');
							return false;
						}
						// 확장자 체크
						if (!$.fileUtil.checkExtension(fobj, options.extensions, _showError))
							return false;
						// 용량 체크
						if (!$.fileUtil.checkMaxbytes(fobj, options.maxBytes, _showError))
							return false;
						// 검증 성공
						_showError('');
						return true;
					};

					// 추가표시정보가 있는 경우
					if (options.addContent) {
						// 테이블에 컨텐츠를 추가하는 함수
						options.addContent(popTable);
					}


					if (options.params) {
						popForm.find('[name="fileType"]').val($.commUtil.nvlTrim(options.params['fileType'  ]));
						popForm.find('[name="fileNo"  ]').val($.commUtil.nvlTrim(options.params['fileNo'  ]));
						popForm.find('[name="docuCd"  ]').val($.commUtil.nvlTrim(options.params['docuCd'  ]));
						popForm.find('[name="needYn"  ]').val($.commUtil.nvlTrim(options.params['needYn'  ]));
					}
					if (options.addParams) {
						$.each(options.addParams, function(key, value) {
							popForm.append('<input type="hidden" name="'+key+'" value="'+value+'" />');
						});
					}
					if (popForm.find(".pop-filebox").length) {
						popForm.find(".pop-filebox .upload-hidden").on("change", function(){ //값이 변경되면
							if (window.FileReader){ //modern browser
								var filename = $(this)[0].files[0].name;
							}else { //old IE
								var filename = $(this).val().split("/").pop().split("\\").pop(); //파일명만 추출
							}
							//추출한 파일명 삽입
							$(this).siblings(".upload-name").val(filename);
							//파일검증
							_validate( $(this) );
						});
					};

					// 등록버튼 클릭
					$('#btnUploadSave').on("click", function() {
						// 첨부파일 업로드 VALIDATION
						let fobj = popForm.find('input[name="upfile"]');
						// 파일검증
						if (!_validate(fobj))
							return false;
						// 2022.12.06 저장시 검증함수
						if (options.validate && 
							options.validate(popForm) === false)
							return false;
						
						$.commMsg.confirm('등록하시겠습니까?', function() {
							// 폼을 AJAX로 저장처리
							popForm.ajaxForm({
								url: options.url,
								enctype : 'multipart/form-data',
								// 에러시 처리로직
								error: $.ajaxUtil.error,
				                // 저장후 처리로직
				                success: function(ret) {
									if (options.success) {
										options.success(ret, dialog);
									}
									else {
										$.commMsg.success(ret, '성공적으로 등록되었습니다.', function() {
											if (options.callback)
												options.callback(ret);
											dialog.close();
										});
									}
								}
							}).submit();
						});
						return false;
					});
					// 2022.12.06 팝업오픈 후 실행함수 
					if (options.openInit)
						options.openInit();
				}
			}
		);
	},

    // 2021.11.16 휴대폰본인인증 팝업
    // 레이어팝업이 아닌 윈도우 팝업임 (본인인증연동)
    openMobiliansPopup : function () {
		var pop = window.open(
			getUrl("/com/cmm/popupMobilians.do"),
			"pop",
			"width=480,height=880, scrollbars=yes, resizable=yes"
		);
    },

    // 2021.08.05 주소검색 팝업
    // 레이어팝업이 아닌 윈도우 팝업임 (오픈API 연동모듈)
    openAddressPopup : function () {

		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서
		// 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open(
			getUrl("/com/cmm/popupAddress.do"),
			"pop",
			"width=570,height=420, scrollbars=yes, resizable=yes"
		);
		// 모바일 웹인 경우,
		// 호출된 페이지(jusopopup.jsp)에서
		// 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    },
    // 2021.09.28 CLIP REPORT 뷰어 팝업
    // 레이어팝업이 아닌 윈도우 팝업임 (CLIP Report 연동)
    openReportPopup : function ( params ) {

		var popKey  = "popupReport";
		var popOpts = "width=860,height=1200, scrollbars=yes, resizable=yes";

		window.open("", popKey, popOpts);
		// 매개변수를 리포트에 넘길수있게 폼서브밋으로 처리함.
		// 2022.02.24 CSLEE : 이기종용 URL(/com/cmm/popupReportPost.do)는 추후 AP서버에 REPORT용 서버가 마련됐을 때 적용
		//  => 현재는 기존 URL(/com/cmm/popupReport.do)로 임시 사용
		//$.formUtil.submitForm(getUrl("/com/cmm/popupReportPost.do"), {
		$.formUtil.submitForm(getUrl("/com/cmm/popupReport.do"), {
			target: popKey,
			params: params
		});
    },

    // 팝업창 오픈
    open : function (params) {
        var reqGetParamStr = "";
        var paramDatas     = params.data;

        if (paramDatas) {
            for ( var key in paramDatas) {
                if (reqGetParamStr != "") {
                    reqGetParamStr += "&";
                }
                reqGetParamStr += (key + "=" + paramDatas[key]);
            }
        }
        if (reqGetParamStr != "") {
            params.url = params.url + "?" + reqGetParamStr;
        }

        // 옵션 값 재 설정
        var options = $.extend({}, params);

        // 사용자 정의 함수 수행 (팝업 load가 끝난 이후 수행할 내용임)
        options.func = function(){
            // TODO : 팝업의 WIDTH를 나중에 그릴때 문제가 있어서 확인후 각 페이지에서 하는 것으로 할 예정
            if (params && params.width) {
                $("#popup").each(function(){
                    // $(this).parents(".modal-content").css("width", params.width);
                    $(this).parents(".modal-dialog").css("width", params.width);
                });
            }

            if (params && params.onshownFn && $.type(params.onshownFn) == 'function') {
                params.onshownFn();
            }
        }

        //dialog 출력
        return $.commModal.loadView(options.title, options.url, options);
    },
    // 팝업창 닫기
    close : function () {
        $("#closeBtn").trigger("click");
    }
}

//===========================================================================//
// 2021.10.16 LSH 공통팝업
//===========================================================================//
$.fn.appPopup = function ( args ) {

	var options = $.extend({
		// 팝업타입 (base: 기본타입, pageload: 화면로드타입)
		type: 'base',
		// (선택) 팝업제목
		title: false,
		// (선택) 추가 스타일시트
		cls: false,
		// 팝업내용 (문자열 또는 함수)
		message: false,
		// 레이아웃 스타일시트
		popupCls: 'layerPop type3',
		// 감싸는영역 스타일시트
		wrapCls: 'layerPop-wrap',
		// Inner영역 스타일시트
		innerCls: 'layerPop-inner',
		// 제목영역 스타일시트
		titleCls: 'layerPop-title',
		// 내용영역 스타일시트
		bodyCls: 'layerPop-body',
		// 버튼영역 스타일시트
		buttonCls: 'layerPop-buttons',
		// 버튼 스타일시트
		btnCls: 'btnWrap type1',
		// 버튼 목록 배열 {id,code,text,click}
		buttons: false,
		// 팝업생성시 오픈여부
		autoopen: true,
		// 팝업오픈시 로드여부
		autoload: false,
		// 닫을때 객체삭제
		destroy:  true,
		// 오픈시 BODY에 추가
		appendBody: false,
		// 오픈후 콜백함수
		onshown:  false,
		// 닫기후 콜백함수
		onhidden: false,
		// 페이지로드후 콜백함수
		onloaded: false,

		// 페이지로드시 URL
		url: false,
		// 페이지로드시 조회조건
		params: false,

		innerWidth: false,

	}, args);

	// 페이지로드 타입인 경우 기본설정변경
	if (options.type == 'pageload') {
		$.extend(options, {
			popupCls:  'layerPop form type1',
			buttonCls: 'layerBtnWrap',
			btnCls:    false,
			autoload:  true,
			autoopen:  false,
			destroy:   true
		});
		$.extend(options, args);
	}

	//현재객체
	let thisCmp = this;
	//레이어객체
	let thisElm = $(this);
	let thisDialog = false;

	// 옵션정보 반환
	this.getOptions = function() {
		return options;
	};
	// 다이얼로그객체 반환
	this.getDialog = function() {
		return thisDialog;
	};
	// 팝업 버튼객체 반환
	this.getButton = function( btnId ) {
		return thisElm.find('[id="'+btnId+'"]');
	};
	// 팝업 내용객체 반환
	this.getMessage = function() {
		return options.message;
	};
	// 팝업 조회조건 반환
	this.getParams = function() {
		return options.params;
	};
	// 팝업 조회조건 반환
	this.getParseParams = function() {
		return JSON.parse(options.params);
	};
	// 2022.12.28 팝업 DOM ELEMENT 반환
	this.getElm = function() {
		return thisElm;
	};

	// 팝업 내용로드
	this.load = function( opts ) {
		if (opts) {
			if (opts.url)    options.url    = opts.url;
			if (opts.params) options.params = opts.params;
		}
		if (options.url &&
			options.params) {
			$.ajax({
				url: options.url,
				dataType : "html",
				type: 'post',
				async: false,
                contentType: 'application/json',
				data: options.params,
				success: thisCmp.loadHtml,
				error: function(){}
			});
		}
	};

	// 팝업 내용HTML 로딩
	this.loadHtml = function( html ) {

		if (!html)
			return;

		// 내용담기
		options.message = html;
		thisElm.find('.'+options.bodyCls).html('');
		thisElm.find('.'+options.bodyCls).html(html);

		if (options.onloaded)
			options.onloaded(thisCmp);
	};

	// 팝업 내용HTML 리셋
	this.resetHtml = function() {
		thisElm.find('.'+options.bodyCls).html('');
	};

	// 팝업레이아웃 생성
	this.createLayout = function( opts ) {

		if (opts) {
			$.extend(options, opts);
		}
		let inner = $('<div></div>');
		inner.addClass(options.innerCls);
		if (options.innerWidth)
			inner.css('width', options.innerWidth);

		// 제목생성
		if (options.title) {
			let title = $('<h3></h3>');
			title.addClass(options.titleCls);
			title.append(options.title);
			inner.append(title);
		}
		// 내용생성
		inner.append(thisCmp.createBody(options.message));

		// 버튼 생성
		if (options.buttons &&
			options.buttons.length > 0) {
			inner.append(thisCmp.createButtons(options.buttons));
		}
		let wrap = $('<div></div>');
		wrap.addClass(options.wrapCls);
		wrap.append('<button class="close"></button>');
		wrap.append(inner);

		thisElm.append('<div class="cover"></div>');
		thisElm.append(wrap);
		thisElm.addClass(options.popupCls);
		if (options.cls)
			thisElm.addClass(options.cls);

		thisElm.find('button.close').bind('click', thisCmp.bindClose);

		if (options.appendBody)
			thisElm.appendTo('body');
	};

	// 팝업내용 생성
	this.createBody = function( msg ) {

		let body = $('<div></div>');
		body.addClass(options.bodyCls);

		// 내용생성
        if ($.type(msg) == 'function')
            body.append(msg(thisCmp));
		else
			body.append(msg);

		return body;
	};

	// 버튼 생성
	this.createButtons = function( buttons ) {

		let div = $('<div></div>');
		div.addClass(options.buttonCls);

		$.each(buttons, function(i,b) {

			let obj = $('<a href="javascript:void(0);"></a>');
			obj.append(b['text']);
			if (b.cls)
				obj.addClass(b.cls);
			if (b['id'])
				obj.prop('id', b['id']);
			if (b['clickClose']) {
				obj.bind('click', function() {
					thisCmp.close();
				});
			}
			else if (b['click']) {
				obj.bind('click', function() {
					b['click'](thisCmp);
				});
			}
			obj.data('text', b['text']);
			if (b['code'])
				obj.data('code', b['code']);

			if (options.btnCls) {
				let btn = false;
				btn = $('<div></div>');
				btn.addClass(options.btnCls);
				btn.append(obj);
				div.append(btn);
			}
			else {
				div.append(obj);
			}
		});
		return div;
	};
	// 닫기 이벤트 핸들러
	this.bindClose = function() {
		thisElm.removeClass('on');
		if (options.onhidden)
			options.onhidden(thisCmp);
		if (options.destroy) {
			if (options.appendBody)
				thisElm.remove();
			else {
				thisElm.removeClass();
				thisElm.children().remove();
			}
		}
	};

	// 닫기 함수
	this.close = function() {
		thisElm.find('.close').trigger('click');
	};

	// 오픈 함수
	this.open = function( opts ) {
		thisCmp.createLayout( opts );
		// 팝업오픈시 로드
		if (options.autoload)
			thisCmp.load( opts );
		if (options.onshown)
			options.onshown(thisCmp);
		thisElm.addClass('on');
		/*
		if (opts) {
			if (opts.url)    options.url    = opts.url;
			if (opts.params) options.params = opts.params;
		}
		let message = options.message;
		if (options.url &&
			options.params) {
			$.ajax({
				url: options.url,
				dataType : "html",
				type: 'post',
				async: false,
                contentType: 'application/json',
				data: options.params,
				success: function(html) {
					message = $(html);
				},
				error: function(){}
			});
		}
		$.extend(options, {
            draggable: true,
            closable:  true,
			message:   message
		});
		if (options.onloaded) {
			if (options.onshown) {
				options.onshown = function(dialog) {
					options.onloaded(thisCmp);
					options.onshown(dialog);
				};
			}
			else {
				options.onshown = function(dialog) {
					options.onloaded(thisCmp);
				};
			}
		}
        thisDialog = BootstrapDialog.show(options);
		*/
		return thisCmp;
	};

	// 객체생성시 팝업오픈
	if (options.autoopen)
		this.open();

	return this;
};

//===========================================================================//
// 2021.10.27 LSH 공통메세지처리
//===========================================================================//
$.fn.appMessage = function ( args ) {

	var options = $.extend({
		// alert, confirm
		type: 'alert',
		// (선택) 추가 스타일시트
		cls: false,
		// 팝업내용 (문자열 또는 함수)
		message: false,
		// 레이아웃 스타일시트
		popupCls:  'app-message',
		// Inner영역 스타일시트
		innerCls:  'app-message-inner',
		// 내용영역 스타일시트
		wrapCls:   'app-message-wrap',
		// 버튼영역 스타일시트
		buttonCls: 'app-message-btnwrap',
		// 버튼 목록 배열 {id,code,text,click}
		buttons: false,
		// 오픈후 콜백함수
		onshown:  false,
		// 닫기후 콜백함수
		onhidden: false,
		// 확인버튼 클릭시 처리함수
		clickOk: false,
		// 취소버튼 클릭시 처리함수
		clickClose: false

	}, args);

	//현재객체
	let thisCmp = this;
	//레이어객체
	let thisElm = $(this);

	// 옵션정보 반환
	this.getOptions = function() {
		return options;
	};
	// 팝업 버튼객체 반환
	this.getButton = function( btnId ) {
		return thisElm.find('[id="'+btnId+'"]');
	};
	// 팝업 내용객체 반환
	this.getMessage = function() {
		return options.message;
	};

	if (!options.buttons) {
		if (options.type == 'confirm') {
			options.buttons = [{
				id: 'btn_msg_ok',
				text: '확인',
				click: function() {
					thisCmp.close();
					if (options.clickOk)
						options.clickOk(thisCmp);
				}
			},{
				id: 'btn_msg_close',
				text: '취소',
				click: function() {
					thisCmp.close();
					if (options.clickClose)
						options.clickClose(thisCmp);
				}
			}];
		}
		else {
			options.buttons = [{
				id: 'btn_msg_ok',
				text: '확인',
				click: function() {
					thisCmp.close();
					if (options.clickClose)
						options.clickClose(thisCmp);
				}
			}];
		}
	}

	// 팝업레이아웃 생성
	this.create = function() {

		let inner = thisCmp.createInner();
		let wrap  = thisCmp.createWrap();
		wrap.append(inner);
		thisElm.append('<div class="cover"></div>');
		thisElm.append(wrap);

		thisElm.addClass(options.popupCls);
		if (options.cls)
			thisElm.addClass(options.cls);

		thisElm.appendTo('body');
	};

	// 감싸는 영역 생성
	this.createWrap = function() {
		let wrap = $('<div></div>');
		wrap.addClass(options.wrapCls);
		return wrap;
	};

	// 내용 영역 생성
	this.createInner = function() {
		let inner = $('<div></div>');
		inner.addClass(options.innerCls);

		// 내용생성
        if ($.type(options.message) == 'function')
			inner.append(options.message(thisCmp));
		else
			inner.append(options.message);

		// 버튼 생성
		if (options.buttons &&
			options.buttons.length > 0) {
			inner.append(thisCmp.createButtons(options.buttons));
		}
		return inner;
	};

	// 버튼 생성
	this.createButtons = function( buttons ) {

		let div = $('<div></div>');
		div.addClass(options.buttonCls);

		$.each(buttons, function(i,b) {
			let obj = $('<a href="javascript:void(0);"></a>');
			obj.append(b.text);
			obj.data('text', b.text);
			if (b.code)       obj.data('code', b.code);
			if (b.cls)        obj.addClass(b.cls);
			if (b.id )        obj.prop('id', b.id);
			if (b.clickClose) obj.bind('click', function() { thisCmp.close(); return false; });
			else if (b.click) obj.bind('click', function() {b.click(thisCmp); return false; });
			div.append(obj);
		});
		return div;
	};

	// 닫기 함수
	this.close = function() {
		thisElm.removeClass('on');
		if (options.onhidden)
			options.onhidden(thisCmp);
		thisElm.remove();
	};
	// 오픈 함수
	this.open = function() {
		thisCmp.create();
		if (options.onshown)
			options.onshown(thisCmp);
		thisElm.addClass('on');
		$('#btn_msg_ok').focus();
		return thisCmp;
	};
	this.open();

	return this;
};

$.popupMsg = {
	alert: function(msg, clickClose) {
		$('<div></div>').appMessage({
			type: 'alert',
			message: msg,
			clickClose: clickClose
		});
	},
    confirm: function (msg, clickOk, clickClose) {
		$('<div></div>').appMessage({
			type: 'confirm',
			message: msg,
			clickOk: clickOk,
			clickClose: clickClose
		});
    }
};

/**
 * 2021.10.20 LSH comm_utils.js -> comm_element.js 로 이동처리
 *
 * 공통 Alert, Confirm
 *
 * $.commMsg.alert('message');
 */
$.commMsg = {
    alert: function (msg, clickClose) {
		// 2021.10.20 LSH BootstrapDialog 사용제외
		$.popupMsg.alert(msg, clickClose);
        //BootstrapDialog.alert(msg, function(){
        //    if (clickClose)
        //        clickClose();
        //});
    },
    confirm: function (msg, clickOk, clickClose) {

		// 2021.10.20 LSH BootstrapDialog 사용제외
		$.popupMsg.confirm(msg, clickOk, clickClose);
        //BootstrapDialog.confirm(msg, function(result){
        //    if (result) {
        //        if (clickOk)
        //            clickOk();
        //    }
        //    else {
        //        if (clickClose)
        //            clickClose();
        //    }
        //});
    },
    /**
     * AJAX 통신 결과 공통 처리 로직
     * ajax의 success 함수에서 필요시 사용한다.
     * 성공 메세지
     * 2021.10.26 LSH ADD
     */
    success: function(data, msg, callback) {
		// 2022.02.07 결과메세지 공통처리
		$.ajaxUtil.result(data, function() {
			if (msg) {
				$.commMsg.alert(msg, function() {
					if (callback)
		            	callback(data);
				});
			}
			else {
				if (callback)
	            	callback(data);
			}
		})
    },
    /**
     * AJAX 통신 오류 발생시 공통 처리 로직
     * ajax의 error 함수에 맵핑하여 사용한다.
     * 2021.10.26 LSH ADD
     */
    error: function(request, status, error) {
		// 2022.02.07 결과메세지 공통처리
		$.ajaxUtil.error(request, status, error);
    },
    /**
     * 엑셀로드 결과 공통 처리 로직
     * 2022.01.28 LSH ADD
     */
    successLoad: function(data, msg, callback) {
		// 2022.02.07 결과메세지 공통처리
		$.ajaxUtil.result(data, function() {
			$.commMsg.alert(msg, callback);
		})
    },
}

/**
 * 2021.10.20 LSH comm_utils.js -> comm_element.js 로 이동처리
 *
 * 공통 modal (bootstrap dialog) 처리
 */
$.commModal = {
    // remote page
    // $.commModal.loadView(title, url);
    loadView: function (title, url, options) {
		var args = $.extend({
            title: title,
            draggable: true,
            closable: true,
			size: BootstrapDialog.SIZE_WIDE,
            message: function (dialog) {
                var $message = $('<div class="loadView"></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);

                return $message;
            },
            data: {
                'pageToLoad': url
            },
            onshown: function (dialog) {
                $('.modal-dialog :input[type="text"]:enabled:visible:first').focus();

                // modal width 옵션 : large/small (입력하지 않으면 normal)
                if(options && options.sizeType) {
                    var modalSize = "";
                    if(options.sizeType == "large"){
                        modalSize = 'modal-lg';
                    } else if(options.sizeType == "small"){
                        modalSize = 'modal-sm';
                    }
                    $('.modal-dialog').addClass(modalSize);
                }
				// 2022.12.19 너비설정 추가
	            if (options && options.width) {
                    $(".modal-dialog").css("width", options.width);
	            }
                // 'func' 속성의 함수가 존재하면 수행
                if (options && options.func && $.type(options.func) == 'function') {
                    options.func(dialog);
                }
            }
		}, options);
        return BootstrapDialog.show(args);
    },
    // $.commModal.loadFullView(title, url);
    loadFullView: function (title, url) {
        BootstrapDialog.show({
            title: title,
            draggable: true,
            closable: true,
            size: BootstrapDialog.SIZE_WIDE,
            message: function (dialog) {
                var $message = $('<div class="loadFullView"></div>');
                var pageToLoad = dialog.getData('pageToLoad');
                $message.load(pageToLoad);

                return $message;
            },
            data: {
                'pageToLoad': url
            },
            onshown: function (dialog) {
                $('.modal-dialog :input[type="text"]:enabled:visible:first').focus();
            }
        });
    },
    // 컨텐츠 모달창 오픈
    // $.commModal.openView(options);
    openView: function (options) {

		let args = $.extend({
            title: options.title,
            draggable: true,
            closable: true,
            message: options.message
		}, options, true);

        // 'func' 속성의 함수가 존재하면 수행
        if (options &&
            options.func &&
            $.type(options.func) == 'function') {
            args.onshown = options.func;
        }
        return BootstrapDialog.show(args);
    },
    // $.commModal.close();
    close: function () {
        $.each(BootstrapDialog.dialogs, function (id, dialog) {
            dialog.close();
        });
    },
    // $.commModal.idClose(obj);
    idClose: function (obj) {
        $.each(BootstrapDialog.dialogs, function (id, dialog) {
            if (id == obj)
                dialog.close();
        });
    }
}
