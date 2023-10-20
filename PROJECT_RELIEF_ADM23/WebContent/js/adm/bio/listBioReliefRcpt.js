/**
*******************************************************************************
*** 파일명 : listBioReliefRcpt.js
*** 설명글 : 살생물제품 구제급여접수 화면 스크립트
***
*** -----------------------------    Modified Log   ---------------------------
*** ver             date                author                  description
*** ---------------------------------------------------------------------------
*** 2.0         2023.01.25    LSH      최초생성
*******************************************************************************
**/
// 탭 모듈 배열
let P_TABS   = [];
// 현재 선택한 탭 INDEX
let P_INDEX  = 0;
// 현재 선택한 행 데이터
let P_SELECT = false;
// 정보수정팝업(modalBioReliefForm.js)에서 사용되는 변수
let P_POST_PREFIX = false; // 주소PREFIX
// 정보수정팝업(modalBioReliefForm.js)에서 사용되는 변수
let P_POPUP_FORM  = false; // 팝업폼

//============================================================================//
// 검색/목록영역 기능정의
//----------------------------------------------------------------------------//
const C_LIST = {
	FORM : false,
	GRID : false,
	// 이력관리
	HIST : false,
	// 제품단계콤보박스
	COMBO: false,
	// 초기화
    //-------------------------------//
	doInit: function() {
		this.FORM   = $('#searchForm');
	    this.GRID   = $('#appGrid').datagrid({
			fit: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',    
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // 그리드 행선택시 체크여부
			checkOnSelect: false,
	        // 그리드 체크시 행선택여부
			selectOnCheck: false,
	        // 그리드 페이징처리 여부
	        pagination:true,
	        // 그리드 행번호 표시여부
	        rownumbers:true,
	        // 한 페이지 출력수
	        pageSize: 30,
	        // 행선택시 상세조회
	        onSelect: this.doLoad,
	        // 체크박스 KEY값필드
	        idField:'aplyNo',
	        // 칼럼정의
	        columns: [[
		        {field:'chckId'       ,checkbox: true},
	            {field:'aplyNo'       ,width:100,halign:'center',align:'center',title:'신청번호'},
	            {field:'aplySeNm'     ,width:100,halign:'center',align:'center',title:'신청구분'},
	            {field:'idntfcId'     ,width:100,halign:'center',align:'center',title:'식별ID'},
	            {field:'aplcntNmMask' ,width:100,halign:'center',align:'center',title:'신청자명'},
	            {field:'dmgePrductNm' ,width:200,halign:'center',align:'left'  ,title:'제품유형'},
	            {field:'dmgePrductCn' ,width:200,halign:'center',align:'left'  ,title:'제품명'},
	            {field:'aplyKndNm'    ,width:200,halign:'center',align:'center',title:'신청종류'},
	            {field:'prgreStusNm'  ,width:100,halign:'center',align:'center',title:'진행상태'},
	            {field:'aplyYmd'      ,width:100,halign:'center',align:'center',title:'신청일자'},
	            {field:'mngrNm'       ,width:100,halign:'center',align:'center',title:'대행관리자'}
	        ]]
	    });

		// 이력관리영역 (comm_biz.js 참고)
		this.HIST = $('#appMngHst').appTableLayout({
			cls:    'app-h200',
			columns: [
				{name: 'regDate', label: '수정일자'},
				{name: 'rgtrNm' , label: '수정자'},
				{name: 'hstSeNm', label: '수정항목'},
				{name: 'hstCn'  , label: '수정내용', key: 'sn', dblclick: this.doOpenHist},
			],
			nodata: true
		});

	    //========================================================//
	    // FORM ELEMENTS 정의
	    //--------------------------------------------------------//
		$('#appAplyTermBox').appTermBox({
			label:'신청일자',
			stName:'srchAplyStdt',
			enName:'srchAplyEndt'
		});
		$('#appRcptTermBox').appTermBox({
			label:'접수일자',
			stName:'srchRcptStdt',
			enName:'srchRcptEndt'
		});
		$('#appAplySe').appSelectBox({
			label:   '신청구분',
			form:    'checkbox',
			name:    'aplySeList', 
			params:  {upCdId: CODE.APLYSE}
		});
		$('#appAplyKnd').appSelectBox({
			label:   '신청종류',
			form:    'checkbox',
			name:    'aplyKndList', 
			params:  {upCdId: CODE.BIO_APLYKIND}
		});
		// 제품유형 단계형 콤보박스 정의
		let p = this;
		this.COMBO = {
			typeCombo: false,
			codeCombo: false,
			doInit: function() {
				// 제품분류 콤보
				this.typeCombo = $('#srchPrductTy').combobox({
					iconWidth:     22,
					prompt:        '제품분류 선택',
					url:           getUrl('/com/cmm/getComboCode.do'),
			        icons:         [{iconCls:'icon-clear', handler: function() { 
						p.COMBO.typeCombo.combobox('clear');
						p.COMBO.doClearCode();
					}}],
					onBeforeLoad:  function(param) {
						param['upCdId'] = CODE.BIO_PRDUCTCD;
					},
					onChange:      p.COMBO.doClearCode,
			        onLoadSuccess: p.COMBO.doClearCode
				});
				// 제품유형 콤보
				this.codeCombo = $('#srchPrductCd').combobox({
					iconWidth:     22,
					prompt:        '제품유형 선택',
					url:           getUrl('/com/cmm/getComboCode.do'),
			        icons:         [{iconCls:'icon-clear', handler: function() { 
						p.COMBO.codeCombo.combobox('clear'); 
					}}],
					onBeforeLoad:  function(param) {
						let cdId = p.COMBO.typeCombo.combobox('getValue');
						param['upCdId'] = ($.commUtil.empty(cdId) ? CODE.NODATA : cdId);
					}
				});
			},
			doClearCode: function() {
				p.COMBO.codeCombo.combobox('clear');
				p.COMBO.codeCombo.combobox('unselect');
				p.COMBO.codeCombo.combobox('reload');
			}
		};
		// 제품유형 단계콤보 초기화
		this.COMBO.doInit();

	    // 검색어 입력 엔터 이벤트 처리
	    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
	    bindEnter($('#srchAplcntNm'), $('#btnSearch'));
	    bindEnter($('#srchIdntfcId'), $('#btnSearch'));
	    bindEnter($('#srchPrductCn'), $('#btnSearch'));

	    // 검색버튼 클릭시 이벤트 처리
	    $('#btnSearch').bind('click', this.doSearch);
	    // 리셋버튼 클릭시 이벤트처리
    	$('#btnReset' ).bind('click', this.doReset);
	    // 엑셀다운로드버튼 클릭시 이벤트처리
	    $('#btnExcel' ).bind('click', this.doExcel);
	    // 신청접수버튼 클릭시 이벤트처리
	    $('#btnReceipt').bind('click', this.doOpenReceipt);

		// 초기검색실행
		this.doSearch();
	},
	// 검색
    //-------------------------------//
	doSearch: function() {
		let grd = C_LIST.GRID;
		let frm = C_LIST.FORM;
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택된 항목 CLEAR
		grd.datagrid('clearSelections');
		// 체크된 항목 CLEAR
		grd.datagrid('clearChecked');
        // 검색폼 데이터 객체화
        var obj = frm.serializeObject();
        // 그리드 목록조회 URL
        grd.datagrid('options')['url'] = getUrl('/adm/bio/getListBioRelief.do');
        // 검색폼 그리드 검색
        grd.datagrid('load', obj);
        return false;
	},
	// 검색 후 이전 선택항목 선택처리
    //-------------------------------//
	doReload: function() {
		let grd = C_LIST.GRID;
		let frm = C_LIST.FORM;
		let row = grd.datagrid('getSelected');
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
		// 선택된 항목 CLEAR
		grd.datagrid('clearSelections');
		// 체크된 항목 CLEAR
		grd.datagrid('clearChecked');
        // 검색폼 데이터 객체화
        var obj = frm.serializeObject();
        // 그리드 목록조회 URL
        grd.datagrid('options')['url'] = getUrl('/adm/bio/getListBioRelief.do');
        // 검색폼 그리드 검색
        grd.datagrid('load', obj);
		if (row) {
			grd.datagrid('selectRecord', row['aplyNo']);
		}
        return false;
	},
	// 검색리셋
    //-------------------------------//
	doReset: function() {
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
        // 검색폼 입력데이터 초기화
        C_LIST.FORM.form('reset');
        // 검색처리
        C_LIST.doSearch();
        return false;
	},
	// 상세리셋
    //-------------------------------//
	doClear: function() {
		// 상세조회 데이터 제거
		P_SELECT = false;
		// 이력관리 초기화
		C_LIST.HIST.resetData();
		// 모든 탭모듈의 초기화
		$.each(P_TABS, function(i,t) {
			t.doReset();
		});
        return false;
	},
	// 엑셀다운로드
    //-------------------------------//
	doExcel: function() {
        $.formUtil.submitForm(
            getUrl('/adm/bio/downBioReliefExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
	},
	// 상세조회
    //-------------------------------//
	doLoad: function(index, row) {
		// 상세조회 항목 CLEAR
		C_LIST.doClear();
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/bio/getBioRelief.do'), 
			{aplyNo : row['aplyNo']},
            function(result) {
                var data = result.Data;
                if (data) {
					// 상세조회 데이터 담기
					P_SELECT = data;
					// 이력관리 목록조회
					C_LIST.doLoadHist( data['aplyNo'] );
					// 모든 탭모듈의 데이터 로드
					$.each(P_TABS, function(i,t) {
						t.doLoad( data );
					});
                }
            }
        );
	},
	// 이력관리 목록검색 (신청정보 상세조회시)
    //-------------------------------//
	doLoadHist: function( aplyNo ) {
		C_LIST.HIST.load(
			getUrl('/adm/bio/getListBioMngHst.do'), {
			dtySeCd: CODE.DTY_CD.BIO,
			aplyNo:  aplyNo
		});
        return false;
	},
	// 이력조회 팝업오픈 (이력목록 더블클릭시)
    //-------------------------------//
	doOpenHist: function() {
		$.commModal.loadView(
			'이력조회', 
			getUrl('/adm/bio/modalBioMngHst.do')
	          + '?mode='+MODE.VIEW
	          + '&sn='+$(this).data('key')
		);
        return false;
	},
	// 신청접수 팝업오픈
    //-------------------------------//
	doOpenReceipt: function() {
		let rows = C_LIST.GRID.datagrid('getChecked');
		if (rows.length == 0) {
			$.commMsg.alert('신청접수현황에서 접수할 항목을 하나 이상 선택하세요.');
			return false;
		}
		for (let r of rows) {
			if (r['prgreStusCd'] != CODE.PRGRE_STUS.APPLY) {
				$.commMsg.alert('제출된 신청서만 접수가 가능합니다.');
				return false;
			}
		}
		let url   = getUrl('/adm/bio/modalBioReceipt.do');
		let title = '신청접수';
		let form  = '#receiptForm';
		
		// 신청접수 팝업정의
		$.commModal.loadView(title, url, {
			sizeType: 'large',
			onhidden: function() {
				// 접수일자 EasyUI datebox 객체 제거
				$('#p_rcptYmd').datebox('destroy');
			},
			func: function(dialog) {
				// 다중항목인 경우
				if (rows.length > 1) {
					// 목록형태의 신청정보 표시
					$('#modalReceiptInfo').appTableLayout({
						data: rows,
						cls: 'app-h200 app-scroll',
						colgroup: ['100','100','100','150','100','130','130'],
						columns: [
				            {name:'aplyNo'       ,label:'신청번호'},
				            {name:'aplySeNm'     ,label:'신청구분'},
				            {name:'sufrerNm'     ,label:'피해자명'}, 
				            {name:'aplyKndNm'    ,label:'신청종류'},
				            {name:'aplcntNm'     ,label:'신청자명'}, 
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
						data: rows[0],
						// 폼객체
						form: form,
						// 폼생성안함
						formCreate: false,
						// ID도 함께 정의
						nameOnly: false,
						// 칼럼구성
						columns: [
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplySeNm'     , label: '신청구분', input: {type:'text',readonly: true, cls:'app-readonly'}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerNm'     , label: '피해자명', input: {type:'text',readonly: true, cls:'app-readonly'}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류', input: {type:'text',readonly: true, cls:'app-readonly'}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dmgePrductNm' , label: '제품유형', input: {type:'text',readonly: true, cls:'app-readonly'}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dmgePrductCn' , label: '제품명'  , input: {type:'text',readonly: true, cls:'app-readonly'}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntNm'     , label: '신청자명', input: {type:'text',readonly: true, cls:'app-readonly'}},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyYmd'      , label: '신청일자', input: {type:'text',readonly: true, cls:'app-readonly'}, formatter: $.formatUtil.toDashDate},
							{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntMbtelNo', label: '수신번호', input: {type:'text',readonly: true, cls:'app-readonly'}, formatter: $.formatUtil.toPhone}
						]
					}).loadData({
						'aplySeNm'     : rows[0]['aplySeNm'     ],
						'sufrerNm'     : rows[0]['sufrerNm'     ], 
						'aplyKndNm'    : rows[0]['aplyKndNm'    ],
						'dmgePrductNm' : rows[0]['dmgePrductNm' ],
						'dmgePrductCn' : rows[0]['dmgePrductCn' ],
						'aplyYmd'      : rows[0]['aplyYmd'      ],
						'aplcntMbtelNo': rows[0]['aplcntMbtelNo']
					});
				}
				// 접수일자 EasyUI datebox 객체 생성
				$('#p_rcptYmd').datebox({
					// 접수일자 변경시 발신내용 자동수정 이벤트
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
			    // 접수일자 입력박스 하이픈(-) 자동삽입 이벤트
				bindDateHyphen( $('#p_rcptYmd').datebox('textbox') );
				// 검증룰 정의
				$(form).validate({
					debug: false,
					onfocusout: false,
					onsubmit: false,
					rules: {
						rcptYmd      : {required: true, dateHyphen: true},
						trnsterNo    : {required: true, phone: true},
						trsmCn       : {required: true}
					},
					messages: {
						rcptYmd: {
							required:   '접수일자는 필수 입력 사항입니다.',
							dateHyphen: '접수일자를 형식에 맞게 입력하세요.'
						},
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
					dialog.close();
			        return false;
				});
			    // 저장하기 클릭
			    $('#btnReceiptSave').bind('click', function() {
					let frm = $(form);
					// 등록폼의 VALIDATION 기능 활성화
					if (frm.validate().settings)
						frm.validate().settings.ignore = false;

					//FORM VALIDATION
					if (frm.valid() === false)
						return false;

					let params = frm.serializeObject();
					$.extend(params, {receiptList: rows});

					let len = rows.length;
					let msg = (len>1?len+"건의 ":"")+"신청서를 접수하시겠습니까?";

			        $.popupMsg.confirm(msg, function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/bio/saveBioReceipt.do'),
			                JSON.stringify(params),
			                function(ret) {
								$.commMsg.success(ret, '성공적으로 접수되었습니다.', function() {
			                        C_LIST.doSearch();
			                		dialog.close();
								});
			                }
			            );
			        });
			        return false;
				});

			}
		});
        return false;
	},
	// 정보수정 팝업오픈
    //-------------------------------//
	doOpenUpdate: function() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		let tab   = P_TABS [ P_INDEX ];
		let code  = tab['CODE' ];
		let title = tab['TITLE']+'수정';
		let url   = getUrl('/adm/bio/modalBioReliefForm.do')
		          + '?aplyNo=' +P_SELECT['aplyNo' ]
       			  + '&hstSeCd='+code;

		// 개인정보수정 팝업
		$.commModal.loadView(title, url, {
			closable: false,
			sizeType: 'large',
			func: function(dialog) {

				// 등록버튼 클릭 이벤트 처리
				$('#btnReliefSave').on("click", function() {
					// 피해자정보수정인 경우
					if      (code == CODE.HST_CD.SUFRER) {
						//생년월일 병합
						$.formUtil.mergeData('p_sufrerBrdt','date',3);
						//전자우편주소 병합
						$.formUtil.mergeData('p_sufrerEmlAddr','email',2);
						//휴대전화번호 병합
						$.formUtil.mergeData('p_sufrerMbtelNo','mobile',3);
						//유선전화번호 병합
						$.formUtil.mergeData('p_sufrerTelno','phone',3);
					}
					// 신청정보수정인 경우
					else if (code == CODE.HST_CD.APLCNT) {
						//생년월일 병합
						$.formUtil.mergeData('p_aplcntBrdt','date',3);
						//전자우편주소 병합
						$.formUtil.mergeData('p_aplcntEmlAddr','email',2);
						//휴대전화번호 병합
						$.formUtil.mergeData('p_aplcntMbtelNo','mobile',3);
						//유선전화번호 병합
						$.formUtil.mergeData('p_aplcntTelno','phone',3);
					}
			        // VALIDATION 기능 활성화
			        if (P_POPUP_FORM.validate().settings)
			            P_POPUP_FORM.validate().settings.ignore = false;

			        // FORM VALIDATION
			        if (P_POPUP_FORM.valid() === false)
			            return false;
					
					let data = P_POPUP_FORM.serializeObject();
			
					// 피해자정보수정인 경우
					if (code == CODE.HST_CD.SUFRER) {
						// 신청종류(다중선택)를 배열로 변환
						if (data['aplyKndList'] && $.type(data['aplyKndList']) === 'string') {
							data['aplyKndList'] = [data['aplyKndList']];
						}
					}
					$.extend(data, {mode: MODE.UPDATE});

			        $.commMsg.confirm("저장하시겠습니까?", function() {
			            // AJAX로 저장처리
			            $.ajaxUtil.ajaxSave(
			                getUrl('/adm/bio/saveBioReliefModify.do'), 
			                JSON.stringify(data),
			                function(ret) {
			                    $.ajaxUtil.success(ret, function() {
									C_LIST.doReload();
									dialog.close();
			                    });
			                }
			            );
			        });
				});
			}
		});
        return false;
	},
	// 서류추가등록 팝업오픈
    //-------------------------------//
	doOpenAddfile: function() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		let cmb = false;
		// 제출서류 추가등록 팝업 오픈
		popup.openUpload({
			// 업로드 URL
			url: getUrl("/adm/bio/saveBioReliefAddfile.do"),
			// 허용가능 확장자
			extensions: COMMONS.FILE_EXTENSIONS,
			// 팝업제목
			title: '관리자 제출서류 추가등록',
			// 기본변수
			params: {
				needYn: 'Y'
			},
			// 추가변수
			addParams: {
				aplyNo:   P_SELECT['aplyNo'],
				upPapeCd: ''
			},
			// 추가표시정보
			addContent: function(table) {
				let tr2 = $('<tr></tr>');
				tr2.append('<td>추가등록대상서류</td>');
				tr2.append('<td colspan="2"><select id="p_papeCd" name="papeCd" ></select></td>');
				// 테이블의 앞부분에 추가한다.
				table.prepend(tr2);
				let tr1 = $('<tr></tr>');
				tr1.append('<th>피해자정보</th>');
				tr1.append('<td>ID :'      +$.commUtil.nvl(P_SELECT['idntfcId'    ])+'</td>');
				tr1.append('<td>피해자명 :'+$.commUtil.nvl(P_SELECT['sufrerNmMask'])+'</td>');
				// 테이블의 앞부분에 추가한다.
				table.prepend(tr1);
				let tr3 = $('<tr></tr>');
				tr3.append('<th>추가등록사유</th>');
				tr3.append('<td colspan="2"><textarea id="p_hstCn" name="hstCn" class="w100" maxlength="650" style="min-height:83px;"></textarea></td>');
				// 테이블의 하단에 추가한다.
				table.append(tr3);
				table.addClass('formLayout');
			},
			// 팝업로딩 후 실행함수
			openInit: function() {
				//  추가등록대상서류 콤보박스
				cmb = $('#p_papeCd').appComboBox({
					url: getUrl('/com/cmm/getComboReliefPape.do'),
					// 살생물제품 서류상위코드
					params: {papeCd: CODE.PAPE_CD.BIO}
				});
			},
			// 저장시 검증함수
			validate: function(f) {
				if (f.find('[name="papeCd"]').val() == '') {
					$.commMsg.alert('추가등록대상서류를 선택해주세요.', function(){
						f.find('[name="papeCd"]').focus();
					});
					return false;
				}
				if (f.find('[name="hstCn"]').val() == '') {
					$.commMsg.alert('추가등록사유를 입력해주세요.', function(){
						f.find('[name="hstCn"]').focus();
					});
					return false;
				}
				// 추가등록대상서류 선택값의 상위서류코드를 맵핑한다.
				let selRow = cmb.getSelectedRow();
				f.find('[name="upPapeCd"]').val(selRow['upPapeCd']);
				return true;
			},
			// 결과처리
			success: function(ret, dialog) {
				$.ajaxUtil.result(ret, function() {
					$.commMsg.alert('성공적으로 등록되었습니다.', function() {
						C_LIST.doReload();
						dialog.close();
					});
				});
            }
		});
		return false;
	},
};

//============================================================================//
// [1] 피해자정보 기능정의
//----------------------------------------------------------------------------//
const C_SUFRER = {
	FORM : false,
	INIT : false,
	DATA : false,
	AREA : false,
	FRMID: '#sufrerForm',
	BTNID: '#btnSufrerUpdate',
	TITLE: '피해자정보',
	CODE : CODE.HST_CD.SUFRER,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.INIT = true;
		this.FORM = $(this.FRMID);
		this.AREA = $('#appSufrer').appFormLayout({
			// 폼객체
			form:  this.FRMID,
			// 폼생성안함
			formCreate: false,
			// 상단여백여부
			headSpace: true,
			// 여백스타일시트
			spaceCls: 'h40',
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: 'suf_',
			// 감싸는 레이어 사용여부
			wrap: true,
			// 감싸는 레이어 KEY
			wrapKey: "appSufrerLayout",
			// 스타일시트
			cls: "formLayout tabInnerFrom box",
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyNo'       , label: '신청번호'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'idntfcId'     , label: '식 별 ID'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplySeNm'     , label: '신청구분'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerNm'     , label: '성　　명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerBrdt'   , label: '생년월일', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyKndNm'    , label: '신청종류'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerSxdstNm', label: '성　　별'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerAge'    , label: '연　　령'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerTelno'  , label: '유선전화', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerMbtelNo', label: '휴대전화', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'sufrerEmlAddr', label: '전자우편'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name:['sufrerZip' ,'sufrerAddr','sufrerDaddr'], label: '주　　소'},
			]
		});
		// 수정버튼 클릭시 이벤트처리
		$(this.BTNID).bind('click', C_LIST.doOpenUpdate);
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 피해자정보 초기화
		this.AREA.resetData();
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 수정버튼 숨김
		$(this.BTNID).hide();
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 데이터 로드
			this.AREA.loadData(data);
			// 수정버튼 표시
			$(this.BTNID).show();
		}
	},
}; 
//============================================================================//
// [2] 신청정보 기능정의
//----------------------------------------------------------------------------//
const C_APLCNT = {
	FORM : false,
	INIT : false,
	DATA : false,
	AREA : false,
	FRMID: '#aplcntForm',
	BTNID: '#btnAplcntUpdate',
	TITLE: '신청정보',
	CODE : CODE.HST_CD.APLCNT,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.INIT = true;
		this.FORM = $(this.FRMID);
		this.AREA = $('#appAplcnt').appFormLayout({
			// 폼객체
			form: this.FRMID,
			// 폼생성안함
			formCreate: false,
			// 상단여백여부
			headSpace: true,
			// 여백스타일시트
			spaceCls: 'h40',
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: 'apl_',
			// 감싸는 레이어 사용여부
			wrap: true,
			// 감싸는 레이어 KEY
			wrapKey: "appAplcntLayout",
			// 스타일시트
			cls: "formLayout tabInnerFrom box",
			// 칼럼구성
			columns: [
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplyYmd'      , label: '신청일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntNm'     , label: '신청자명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntBrdt'   , label: '생년월일', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'sufrerRelNm'  , label: '피해자와의관계'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dthYn'        , label: '피해자사망여부', formatter: $.formatUtil.toDthNm},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dthYmd'       , label: '사망일자', formatter: $.formatUtil.toDashDate},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntTelno'  , label: '유선전화', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'aplcntMbtelNo', label: '휴대전화', formatter: $.formatUtil.toPhone},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'aplcntEmlAddr', label: '전자우편'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name:['aplcntZip'    ,'aplcntAddr','aplcntDaddr'], label: '주　　소'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name:['bankNm'       ,'dpstrNm'   ,'actno'      ], label: '계좌번호'},
			]
		});
		// 수정버튼 클릭시 이벤트처리
		$(this.BTNID).bind('click', C_LIST.doOpenUpdate);
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 신청정보 초기화
		this.AREA.resetData();
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 수정버튼 숨김
		$(this.BTNID).hide();
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 데이터 로드
			this.AREA.loadData(data);
			// 수정버튼 표시
			$(this.BTNID).show();
		}
	},
}; 
//============================================================================//
// [3] 피해내용 기능정의
//----------------------------------------------------------------------------//
const C_PRDUCT = {
	FORM : false,
	INIT : false,
	DATA : false,
	AREA : false,
	FRMID: '#prductForm',
	BTNID: '#btnPrductUpdate',
	TITLE: '피해내용',
	CODE : CODE.HST_CD.DAMAGE,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.INIT = true;
		this.FORM = $(this.FRMID);
		this.AREA = $('#appPrduct').appFormLayout({
			// 폼객체
			form: this.FRMID,
			// 폼생성안함
			formCreate: false,
			// 상단여백여부
			headSpace: true,
			// 여백스타일시트
			spaceCls: 'h40',
			// ID도 함께 정의
			nameOnly: false,
			// 칼럼ID의 공통 prefix
			prefix: 'prd_',
			// 감싸는 레이어 사용여부
			wrap: true,
			// 감싸는 레이어 KEY
			wrapKey: "appPrductLayout",
			// 스타일시트
			cls: "formLayout tabInnerFrom box",
			// 칼럼구성
			columns: [
				//{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'dmgePrductNm'    , label: '제품유형'}, 
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'dmgePrductUpNm'  , label: '제품유형'},
				{groupCls:'col-md-6' ,                       inputCls: 'col-md-12', name: 'dmgePrductCdNm'  , label: false},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'dmgePrductCn'    , label: '제품명'},
				{groupCls:'col-md-6' , labelCls: 'col-md-4', inputCls: 'col-md-8' , name: 'prductSbmsn'     , label: '제품제출여부', 
					appendHtml: function() {
						let dom = $('<div class="app-radio"></div>');
						dom.append('<input type="radio" name="prductSbmsnYn" id="prd_prductSbmsnYn1" value="Y" onclick="return(false);" />');
						dom.append('<label for="prd_prductSbmsnYn1"> 제출 </label>');
						dom.append('<input type="radio" name="prductSbmsnYn" id="prd_prductSbmsnYn2" value="N" onclick="return(false);" />')
						dom.append('<label for="prd_prductSbmsnYn2"> 미제출 </label>');
						return dom;
					}
				},
				{groupCls:'col-md-6' ,                       inputCls: 'col-md-12', name: 'prductSbmsnResn' , label: false},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'prductUsePrps'   , label: '사용목적'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'prductUseNm'     , label: '사용구분'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'prductUsePlce'   , label: '사용장소'},
				{groupCls:'col-md-8' , labelCls: 'col-md-3', inputCls: 'col-md-9' , name: 'prductUseMthd'   , label: '사용방법'},
				{groupCls:'col-md-4' , labelCls: 'col-md-3 app-blue', inputCls: 'col-md-9', name: 'prductIdnty', label: '주의사항',
					appendHtml: function() {
						let dom = $('<div class="app-radio"></div>');
						dom.append('<input type="radio" name="prductIdntyYn" id="prd_prductIdntyYn1" value="Y" onclick="return(false);" />');
						dom.append('<label for="prd_prductIdntyYn1"> 확인 </label>');
						dom.append('<input type="radio" name="prductIdntyYn" id="prd_prductIdntyYn2" value="N" onclick="return(false);" />')
						dom.append('<label for="prd_prductIdntyYn2"> 미확인 </label>');
						return dom;
					}
				},
				{groupCls:'col-md-8' , labelCls: 'col-md-3', inputCls: 'col-md-9' , name: 'prductUseYmd'    , label: '사용기간'},
				{groupCls:'col-md-4' ,                       inputCls: 'col-md-12', name: 'prductUseHour'   , label: false},
				{groupCls:'col-md-8' , labelCls: 'col-md-3', inputCls: 'col-md-9' , name: 'prductUseCuntCn' , label: '사용빈도'},
				{groupCls:'col-md-4' ,                       inputCls: 'col-md-12', name: 'prductUsgqtyCn'  , label: false},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'dmgeDissNm'      , label: '건강피해내용'},
				{groupCls:'col-md-12', labelCls: 'col-md-2', inputCls: 'col-md-10', name: 'etcOpinionCn'    , label: '기타내용'}
			],
			// 데이터 로드전 Callback
			beforeLoadCallback: function(data) {
				// 사용기간 데이터 포맷 정의
				data['prductUseYmd' ] = [data['prductUseBgngYmd' ],' ~ ', data['prductUseEndYmd' ]].join('');
				data['prductUseHour'] = [data['prductUseBgngHour'],' ~ ', data['prductUseEndHour']].join('');
			}
		});
		// 수정버튼 클릭시 이벤트처리
		$(this.BTNID).bind('click', C_LIST.doOpenUpdate);
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 피해내용 초기화
		this.AREA.resetData();
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 수정버튼 숨김
		$(this.BTNID).hide();
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
			// 데이터 로드
			this.AREA.loadData(data);
			// 수정버튼 표시
			$(this.BTNID).show();
		}
	},
};

//============================================================================//
// [4] 제출서류 기능정의
//----------------------------------------------------------------------------//
const C_FILE = {
	FILE : false,
	INIT : false,
	DATA : false,
	BTNID: '#btnAddfile',
	TITLE: '제출서류',
	CODE : CODE.HST_CD.PAPER,
	// 초기화
    //-------------------------------//
	doInit: function() {
		if (this.INIT)
			return;
		this.INIT = true;
		// 신청파일목록영역 (comm_component.js 참고)
		this.FILE = $('#appAplyFileList').appBioAplyFile({mode: MODE.LIST});
		// 추가등록버튼 클릭시 이벤트처리
		$(this.BTNID).bind('click', C_LIST.doOpenAddfile);
	},
	// 데이터 리셋
    //-------------------------------//
	doReset: function() {
		// 서류목록 초기화
		this.FILE.resetList();
		// 상세조회 데이터 리셋
		this.DATA = false;
		// 추가등록버튼 숨김
		$(this.BTNID).hide();
	},
	// 상세조회 로드
    //-------------------------------//
	doLoad: function(data) {
		if (data) {
			// 데이터 정의
			this.DATA = data;
	    	// 첨부파일목록 데이터로드
	    	this.FILE.loadList({
				dtySeCd:   CODE.PAPE_DTY_CD.BIO,
				dcmtNo:    data['aplyNo'],
				dtlDcmtNo: '0'
			});
			// 임시저장이 아닌 경우 추가등록버튼 표시
			if (data['prgreStusCd'] != CODE.PRGRE_STUS.TMPSAVE) {
				// 추가등록버튼 표시
				$(this.BTNID).show();
			}
		}
	},
};

$(function() {
	P_TABS   = [C_SUFRER, C_APLCNT, C_PRDUCT, C_FILE];
	P_INDEX  = 0;
	P_SELECT = false;

	// 상세내역 탭클릭 이벤트
	$.eventUtil.tabClick('.boxWrap', 0, function(elm, index) {
		P_INDEX = index;
		// 탭모듈별 초기화
		P_TABS[index].doInit();
	}, true);

	// 목록 초기화
	C_LIST.doInit();
});
