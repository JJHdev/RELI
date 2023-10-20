/**
******************************************************************************************
*** 파일명 : listReliefRcpt.js
*** 설명글 : 구제급여 접수 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.10.20    LSH   화면디자인 적용
*** 1.0         2021.10.25    LSH   레이어팝업 공통적용
******************************************************************************************
**/

// 우편번호검색시 대상항목 PREFIX (modalReliefForm.js에서 사용됨)
let P_POST_PREFIX = '';
// 개인정보수정 팝업에서 사용할 FORM 객체
let P_POPUP_FORM  = false;

$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 신청/접수현황 목록	
	let P_FORM    = $('#searchForm'     ); // 신청/접수현황 검색폼	
	let P_PERSON  = $('#appPersonalWrap'); // 개인정보영역
	let P_FILES   = $('#appAplyFileList'); // 신청파일목록
	let P_DTY_CD  = CODE.DTY_CD.RELIEF;    // 업무구분(구제급여)
	let P_APLY_ODER = '0';                 // 신청차수 기본값
	let P_SELECT  = false;                 // 상세조회 데이터
	let P_HISTORY = false;                 // 이력관리목록 
	let P_FORMAT  = {
		// 진행상태 포맷처리
		stusNm: function(value, row) {
			// 보완처리상태가 있으면
			if (!$.commUtil.empty(row['spleStusCd'])) {
				return row['spleStusNm'];
			}
			return value;
		}
	}; 

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
		// 화면영역 맞춤
		fit: true,
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',    
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 페이징처리 여부
        pagination:true,
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 한 페이지 출력수
        pageSize: 30,
        // 체크박스 KEY값필드
        idField:'aplyNo',
        // 칼럼정의
        columns: [[
	        {field:'chckId'       ,checkbox: true},
            {field:'aplyNo'       ,width:100,halign:'center',align:'center',title:'신청번호'},
            {field:'aplySeNm'     ,width:100,halign:'center',align:'center',title:'신청구분'},
			// 2023.01.06 식별ID 항목 추가
            {field:'idntfcId'     ,width:100,halign:'center',align:'center',title:'식별ID'},
			// 2023.01.06 피해자명 항목 추가
            {field:'sufrerNmMask' ,width:100,halign:'center',align:'center',title:'피해자명'},
            {field:'aplcntNmMask' ,width:100,halign:'center',align:'center',title:'신청자명'},
            {field:'bizAreaNm'    ,width:100,halign:'center',align:'center',title:'피해지역'},
            {field:'aplyKndNm'    ,width:150,halign:'center',align:'center',title:'신청종류'},
            {field:'prgreStusNm'  ,width:100,halign:'center',align:'center',title:'진행상태', formatter: P_FORMAT.stusNm},
            {field:'aplyYmd'      ,width:100,halign:'center',align:'center',title:'신청일자'},
            {field:'mngrNm'       ,width:100,halign:'center',align:'center',title:'대행관리자'}
        ]],
        // 행선택시 상세조회
        onSelect: doSelect,
		// 행선택해제시 리셋
		//onUnselect: doClear,
		// 행더블클릭시 신청서작성(임시저장만)
		onDblClickRow: doApply,
		checkOnSelect: false,
		selectOnCheck: false,
		singleSelect: true
    });
	
	// 개인정보영역 생성 (comm_adm.js 참고)
	P_PERSON = P_PERSON.appPersonal({
		// 2022.12.05 수정버튼 정의 추가
		button: {id:'btnUpdate', text:'수정'}
	});
	
	// 이력관리영역 (comm_adm.js 참고)
	P_HISTORY = $('#appHistoryTable').appTableLayout({
		cls:    'app-h200',
		columns: [
			{name: 'regDate', label: '수정일자'},
			{name: 'rgtrNm' , label: '수정자'},
			{name: 'hstSeNm', label: '수정항목'},
			{name: 'hstCn'  , label: '수정내용', key: 'sn', dblclick: doSelectHistory},
		],
		nodata: true
	});
	// 신청파일목록영역 (comm_component.js 참고)
	P_FILES = P_FILES.appAplyFile({
		mode:   MODE.SPLEMNT,
		system: SYSTEM.ADMIN['code']
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
		params:  {upCdId: CODE.APLYKIND}
	});
	$('#appProgress').appSelectBox({
		label:   '진행현황',
		form:    'checkbox',
		name:    'prgreStusList', 
		params:  {upCdId: CODE.PROGRESS},
		// 2021.12.27 ADD (comm_const.js 참고)
		filter:  CODE_FILTER.RELIEF_PRGRE_STUS
	});
	$('#appBizArea').appSelectBox({
		label:   '지역구분',
		form:    'checkbox',
		name:    'bizAreaList', 
		url:     getUrl('/com/cmm/getComboBizMng.do'),
		params:  {upCdId: CODE.PROGRESS},
		// 데이터 로딩후 실행함수
		callback: function(cmp) {
			cmp.addOption({
				code: 'ETC',
				text: '기타'
			});
		}
	});
    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchSufrerNm'), $('#btnSearch'));
    bindEnter($('#srchAplcntNm'), $('#btnSearch'));
	// 2023.01.06 식별ID 검색어 입력 엔터 이벤트 처리
	bindEnter($('#srchIdntfcId'), $('#btnSearch'));
	// 초기검색실행
	doSearch();

    // 신청접수현황 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 항목 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
		// 체크된 항목 CLEAR
		P_GRID.datagrid('clearChecked');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/relief/getListRelief.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
         
        return false;
    }
	
    // 신청접수현황 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 상세조회 데이터 초기화
		doClear();
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }
	
    // 신청접수현황 상세리셋
    //--------------------------------------------------------//
    function doClear() {
    	// 개인정보 초기화
    	P_PERSON.resetData();
		// 서류목록 초기화
		P_FILES.resetList();
		// 이력관리 초기화
		P_HISTORY.resetData();
		// 상세조회 데이터 제거
		P_SELECT = false;
		// 2022.12.06 추가등록버튼 숨김
		$('#btnAddfile').hide();
		// 보완요청버튼 숨김
		$('#btnSplemnt').hide();
		// 이력등록버튼 숨김
		$('#btnHistory').hide();
		// 설문지버튼 숨김
		$('#btnSurvey').hide();
		// 신청서보기버튼 숨김
		$('#btnReport').hide();
		// 2022.12.05 수정버튼 숨김
		$('#btnUpdate').hide();
		
        return false;
    }

    // 신청접수현황 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {

        var params = {
			aplyNo:   row['aplyNo'  ]
		};
		doClear();

        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/relief/getRelief.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 상세조회 데이터 담기
					P_SELECT = data;
					// 상태명칭
					data['stusNm'] = P_FORMAT.stusNm(data['prgreStusNm'], data);
			    	// 개인정보 데이터로드
			    	P_PERSON.loadData(data);

			    	// 첨부파일목록 데이터로드
			    	P_FILES.loadList({
						dtySeCd:   data['papeDtySeCd'],
						dcmtNo:    data['aplyNo'],
						dtlDcmtNo: P_APLY_ODER
					});
					
					// 이력관리 목록로드
					doLoadHistory(data['aplyNo']);
					
					// 보완요청중이 아닌 경우에만 버튼표시
					// 2022.01.08 LSH 제출된 신청서만 버튼표시
					if (data['prgreStusCd'] == CODE.PRGRE_STUS.APPLY &&
						data['spleStusCd' ] != CODE.SPLE_STUS.APPLY ) {
						// 보완요청버튼 표시
						$('#btnSplemnt').show();
					}
					// 설문지번호가 있는 경우에만 버튼표시
					if (!$.commUtil.empty(data['rspnsMngNo'])) {
						// 설문지버튼 표시
						$('#btnSurvey').show();
					}
					// 2022.12.13 이력등록버튼 현재 감춤처리함
					// 이력등록버튼 표시
					//$('#btnHistory').show();
					
					// 신청서보기버튼 표시
					$('#btnReport').show();
					
					// 리포트를 출력할 신청번호로 변경
					$('#btnReport').data('aplyNo', data['aplyNo']);
					
					// 2022.12.05 수정버튼 표시
					$('#btnUpdate').show();
					// 2022.12.07 임시저장이 아닌 경우에만 추가등록버튼 표시
					if (data['prgreStusCd'] != CODE.PRGRE_STUS.TMPSAVE) {
						// 2022.12.06 추가등록버튼 표시
						$('#btnAddfile').show();
					}
                }
            }
        );
    }

    // 신청접수현황 신청서 작성하기
    //--------------------------------------------------------//
    function doApply(index, row) {
		// 임시저장된 신청서인 경우 신청서 작성하기로 이동
		if (row['prgreStusCd'] == CODE.PRGRE_STUS.TMPSAVE) {
			let msg = '임시 저장중인 신청서입니다.<br>'
			        + '신청서 작성으로 이동하시겠습니까?';
			$.commMsg.confirm(msg, function() {
				$.formUtil.submitForm(getUrl('/adm/relief/openReliefForm.do'), {
					params: {
						aplySeCd: row['aplySeCd'],
						aplyNo  : row['aplyNo'  ]
					}
				});
			});
		}
		return false;
    }

    // 신청접수현황 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/relief/downReliefExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }

    // [신청접수팝업] 신청접수 팝업오픈
    //--------------------------------------------------------//
    function doOpenReceipt() {
	
		let rows = P_GRID.datagrid('getChecked');
		if (rows.length == 0) {
			$.commMsg.alert('신청접수현황에서 접수할 항목을 하나 이상 선택하세요.');
			return false;
		}
		let chk = true;
		$.each(rows, function(i,r) {
			if (r['prgreStusCd'] != CODE.PRGRE_STUS.APPLY &&
				r['prgreStusCd'] != CODE.PRGRE_STUS.REAPPLY) {
				$.commMsg.alert('제출된 신청서만 접수가 가능합니다.');
				chk = false;
				return false;
			}
		});
		if (!chk)
			return false;
		
		// 신청접수팝업 (adm_popup.js)
		popup.openReliefReceipt({
			element:'#appPopupReceipt', 
			openArgs: {
				params: JSON.stringify({
					receiptList: rows
				})
			},
			saveCallback: doSearch
		});
        return false;
    }

    // [이력관리팝업] 이력관리 목록검색
    //--------------------------------------------------------//
    function doLoadHistory( aplyNo ) {
		P_HISTORY.load(
			getUrl('/adm/biz/getListMngHst.do'), {
			dtySeCd: P_DTY_CD,
			aplyNo:  aplyNo
		});
        return false;
    }

    // [이력관리팝업] 이력등록 팝업오픈
    //--------------------------------------------------------//
    function doOpenHistory() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupHistory', 
			openArgs: {
				title: '이력등록',
				params: JSON.stringify({
					mode:    MODE.INSERT,
					dtySeCd: P_DTY_CD,
					aplyNo:  P_SELECT['aplyNo']
				})
			},
			saveCallback: doLoadHistory
		});
        return false;
    }
    // [이력관리팝업] 이력조회 팝업오픈
    //--------------------------------------------------------//
    function doSelectHistory() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupHistory', 
			openArgs: {
				title: '이력조회',
				params: JSON.stringify({
					mode:    MODE.VIEW,
					dtySeCd: P_DTY_CD,
					aplyNo:  P_SELECT['aplyNo'],
					sn:      $(this).data('key')
				})
			},
			saveCallback: doLoadHistory
		});
        return false;
    }
    // [보완요청팝업] 팝업오픈
    //--------------------------------------------------------//
    function doOpenSplemnt() {

		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		if (P_SELECT['prgreStusCd'] != CODE.PRGRE_STUS.APPLY &&
			P_SELECT['prgreStusCd'] != CODE.PRGRE_STUS.REAPPLY) {
			$.commMsg.alert('제출된 신청서만 보완요청이 가능합니다.');
			return false;
		}
		let rows = P_FILES.getSplemntList();
		if (rows.length == 0) {
			$.commMsg.alert('제출서류목록에서 보안요청할 서류를 하나 이상 선택하세요.');
			return false;
		}
		let obj  = $.extend({}, P_SELECT);
		$.extend(obj, {
			aplyOder:        P_SELECT['aplyOder'] || P_APLY_ODER,
			splemntDmndSeCd: P_DTY_CD,
			fileList: rows
		});
		
		// 보완요청팝업 (adm_popup.js)
		popup.openSplemnt({
			element:'#appPopupSplemnt', 
			openArgs: {
				params: JSON.stringify(obj)
			},
			saveCallback: doSearch
		});
        return false;
    };

    // 2022.01.05 LSH [설문지팝업] 팝업오픈
    //--------------------------------------------------------//
    function doOpenSurvey() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		$.commModal.loadView(
			'온라인 설문지 상세보기', 
			getUrl('/adm/relief/modalSurveyView.do?rspnsMngNo='+P_SELECT['rspnsMngNo']), 
			{sizeType: 'large'}
		);
        return false;
    };
    

    // 2022.09.21 JWH [리포트] 구제급여 선지금 신청서 인쇄하기 (리포팅툴)
    //--------------------------------------------------------//
    function doReport() {
		let params = {
			mode	: 'ADVNCPYMNTFRM',
			aplyNo	: $('#btnReport').data('aplyNo')
		};
		// 리포트뷰어 팝업 호출
		popup.openReportPopup(params);
		return false;
    };

    // 2022.12.06 검색 후 이전 선택항목 선택처리
    //--------------------------------------------------------//
    function doReload() {
		let row = P_GRID.datagrid('getSelected');
		// 상세조회 항목 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
		// 체크된 항목 CLEAR
		P_GRID.datagrid('clearChecked');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/relief/getListRelief.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
		if (row) {
			P_GRID.datagrid('selectRecord', row['aplyNo']);
		}
        return false;
    }

    // 2022.12.02 LSH [개인정보수정] 팝업오픈
    //--------------------------------------------------------//
    function doOpenPersonal() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		let code  = P_PERSON.getTabCode();
		let url   = getUrl('/adm/relief/modalReliefForm.do');
		let title = '';
		if      (code == CODE.HST_CD.SUFRER) title = '피해자정보수정';
		else if (code == CODE.HST_CD.APLCNT) title = '신청인정보수정';
		else if (code == CODE.HST_CD.DAMAGE) title = '피해내용수정';
		
		url += '?aplyNo='+P_SELECT['aplyNo'];
		url += '&hstSeCd='+code;
		
		// 개인정보수정 팝업
		$.commModal.loadView(title, url, {
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
					// 신청인정보수정인 경우
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
			                getUrl('/adm/relief/saveReliefModify.do'), 
			                JSON.stringify(data),
			                function(ret) {
			                    $.ajaxUtil.success(ret, function() {
									doReload();
									dialog.close();
			                    });
			                }
			            );
			        });
				});
					
			}
		});
        return false;
    };

    // 2022.12.06 LSH [추가등록] 팝업오픈
    //--------------------------------------------------------//
    function doOpenAddfile() {
		if (!P_SELECT) {
			$.commMsg.alert('신청접수현황에서 항목을 선택하세요.');
			return false;
		}
		let cmb = false;
		// 2022.12.06 LSH 제출서류 추가등록 팝업 오픈
		// 파일업로드 팝업 오픈
		popup.openUpload({
			// 업로드 URL
			url: getUrl("/adm/relief/saveReliefAddfile.do"),
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
				cmb = $('#p_papeCd').appComboBox({url: getUrl('/com/cmm/getComboReliefPape.do')});
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
				let r = $(ret);
				if (r.find('.exception_detail_message') &&
					r.find('.exception_detail_message').length > 0) {
					$.commMsg.alert(r.find('.exception_detail_message').html());
					return false;
				}
				if (r.find('.exception_message') &&
					r.find('.exception_message').length > 0) {
					$.commMsg.alert(r.find('.exception_message').html());
					return false;
				}
				if (r['Code'] < 0) {
					$.commMsg.alert('[Code:' + r['Code'] + '] ' + r['Message']);
					return;
				}
				else {
					$.commMsg.alert('성공적으로 등록되었습니다.', function() {
						doReload();
						dialog.close();
					});
				}
            }
		});

        return false;
    };


    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);

    // 신청접수버튼 클릭시 이벤트처리
    $('#btnReceipt').bind('click', doOpenReceipt);
    // 보완요청버튼 클릭시 이벤트처리
    $('#btnSplemnt').bind('click', doOpenSplemnt);
    // 이력등록버튼 클릭시 이벤트처리
    $('#btnHistory').bind('click', doOpenHistory);
	// 설문지버튼 클릭시 이벤트처리
	$('#btnSurvey').bind('click', doOpenSurvey);
	// 신청서보기 클릭시 리포팅툴 이벤트처리
	$('#btnReport').bind('click', doReport);
	// 2022.12.02 LSH 수정 클릭시 이벤트처리
	$('#btnUpdate').bind('click', doOpenPersonal);
	// 2022.12.06 추가등록버튼 클릭시 이벤트처리
	$('#btnAddfile').bind('click', doOpenAddfile);

});
