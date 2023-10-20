/**
******************************************************************************************
*** 파일명    : comm_component.js
*** 설명      : 사용자 정의 컴포넌트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-08-05              LSH
*** 1.1         2021-10-10              LSH   element와 sample로 분리
******************************************************************************************
**/

//===========================================================================//
// 피해조사파일 컨트롤
//===========================================================================//
$.fn.appExmnFile = function ( args ) {

	var options = $.extend({
		// [필수] 그리드영역
		gridId: '#appFileGrid',
		// [필수] 업로드폼 ID
		formId: '#uploadForm',
		// 첨부파일 다운로드 URL
		downloadUrl: getUrl('/adm/file/downloadExmnFile.do'),
		// 첨부파일 목록로드 URL
		loadUrl: getUrl('/adm/file/getListExmnFile.do'),
		// 첨부파일 삭제 URL
		removeUrl: getUrl('/adm/file/deltExmnFile.do'),
		// 첨부파일 이미지 미리보기 URL
		previewImgUrl: getUrl('/adm/file/linkExmnFile'),
		// 첨부파일 PDF/ HWP 미리보기 URL
		previewDocUrl: getUrl('/adm/file/linkExmnHwp'),
		// 사용가능 확장자 배열 (값이 있을경우에만 체크)
		extensions: COMMONS.FILE_EXTENSIONS,
		// 2022.01.18 LSH 압축파일 확장자 허용 추가
		// 알집 압축파일(alz)
		// 7ZIP 압축파일(7z)
		// 일반 압축파일(zip)
		//[	"txt" ,"pdf", "hwp", "doc", "docx", "ppt", "pptx", "xls","xlsx",
		//	"jpg", "jpeg", "png", "gif", "bmp",
		//	"zip", "alz", "7z"
		//],
		// 첨부파일 용량제한 (바이트단위: 200MB), 209715200
		maxBytes: NUMBER.FILE_MAXBYTES,
		// 2022.01.21 첨부파일 파일명 글자수 최대길이 (50자)
		maxLengthName: NUMBER.FILE_MAXLENGTH,
		// Selector Name
		selector: 'app-exmn-files',
		// 파일타입
		fileType: 'EXMN'

	}, args);

	//현재객체
	let thisCmp = this;

	//레이어객체
	let thisElm = $(this);
	
	//그리드객체
	let thisGrid = false;
	
	//폼객체	
	let thisForm = false;

	// 최초 실행
	this.init = function() {
		
		thisElm.addClass(options.selector);
		
		thisElm.empty();
		thisElm.children().unbind();
		thisForm = $(options.formId);
		thisGrid = $(options.gridId);
		
		this.createGrid();
		// 입력박스영역 정의
		thisElm.append(this.createWrap());
		
		// 첨부파일 파일선택 변경시 
		// 텍스트박스 파일명 표시 이벤트
		$.eventUtil.fileAttach('.'+options.selector+' > .file_wrap');
	};
	
	// 목록영역 리사이즈
	this.doResize = function() {
		thisGrid.datagrid('resize');
	}
	
	this.createWrap = function() {
		let div = $('<div class="file_wrap app-small-mpl"></div>');
		div.append('<input type="hidden" name="docuNo"   value=""  />');
		div.append('<input type="hidden" name="fileNo"   value=""  />');
		div.append('<input type="hidden" name="fileType" value="'+options.fileType+'"  />');
		div.append('<input type="hidden" name="filePath" value=""  />');
		div.append('<input type="hidden" name="needYn"   value="N" />');
		div.append('<input type="hidden" name="fileYn"   value="N" />');
		div.append('<input type="text"   name="fileName" value="" class="input_text" title="filebox" readonly />');
		div.append('<div class="box_wrap"></div>');
		div.find('.box_wrap').append('<input type="file" name="upfile" class="input_file">');
		div.find('.box_wrap').append('<button type="button" class="btn_file"></button>');
		div.append('<button type="button" class="btn_bst btn_add"><span class="fa fa-plus" ></span></button>');
		div.append('<button type="button" class="btn_bst btn_del"><span class="fa fa-minus"></span></button>');
		return div;
	};
	
	this.createGrid = function() {
		
		thisGrid.datagrid({
			fit: true,
			fitColumns: true,
	        // 그리드 결과데이터 타입
	        contentType: 'application/json',
	        // 그리드 결과데이터 타입
	        dataType: 'json',    
	        // 그리드 데이터연동 방식
	        method: 'POST',
	        // 그리드 단일행만 선택여부
	        singleSelect: true,
	        // 그리드 행번호 표시여부
	        rownumbers:true,
	        // 칼럼정의
	        columns: [[
	            {field:'fileNm' ,width:350,halign:'center',align:  'left',title:'첨부서류',
					formatter: function(v, r) {
						let pargs = [
							'href="javascript:void(0);"',
							'class="app-btn-remove detailicon"',
							'title="파일삭제"',
							'data-sn="'+r['sn']+'"'
						].join(' ');
	
						return v+'<a '+pargs+'><i class="fa fa-remove"></i></a>';
					}
				},
	            {field:'fileBtn',width:200,halign:'center',align:'center',title:' ',
					formatter: function(v, r) {
						let pargs = [
							'href="javascript:void(0);"',
							'class="app-btn-preview detail"',
							'title="미리보기"',
							'data-sn="'+r['sn']+'"',
							'data-type="'+$.fileUtil.getPreviewType(r['fileNm'])+'"',
						].join(' ');
	
						let dargs = [
							'href="javascript:void(0);"',
							'class="app-btn-download detail"',
							'title="다운로드"',
							'data-sn="'+r['sn']+'"'
						].join(' ');
	
						return [
							'<a '+pargs+'>미리보기</a>',
							'<a '+dargs+'>다운로드</a>'
						].join('');
					}
				}
	        ]],
		 	onLoadSuccess: function() {
				let p = $(this).datagrid('getPanel');
				p.find('.app-btn-remove').each(function() {
					$(this).bind('click', thisCmp.doRemove);
				});
				p.find('.app-btn-preview').each(function() {
					$(this).bind('click', thisCmp.doPreview);
				});
				p.find('.app-btn-download').each(function() {
					$(this).bind('click', thisCmp.doDownload);
				});
			}
		});
	};
	
	// 첨부서류 그리드 검색처리
    //--------------------------------------------------------//
	this.doSearch = function( params ) {
        thisGrid.datagrid('options')['url'] = options.loadUrl;
        thisGrid.datagrid('load', params);
	};
	
	// 첨부서류 그리드 리셋처리
    //--------------------------------------------------------//
	this.doReset = function() {
		thisGrid.datagrid('loadData', {"total":0,"rows":[]});
	};

    // 첨부서류 파일삭제
    //--------------------------------------------------------//
    this.doRemove = function() {
		let sn = $(this).data('sn');
		let p  = thisGrid.datagrid('options')['queryParams'];
		
		$.commMsg.confirm('정말 삭제하시겠습니까?', function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                options.removeUrl, 
                JSON.stringify({sn: sn}),
                function(ret) {
                    $.commMsg.success(ret, '성공적으로 삭제되었습니다.', function() {
						thisCmp.doSearch( p );
                    });
                }
            );
		});
		return false;
	};
	
    // 첨부서류 미리보기
    //--------------------------------------------------------//
	this.doPreview = function() {
		let sn   = $(this).data('sn');
		let type = $(this).data('type');
		let url  = false;
		if      (type == 'PDF') url = options.previewDocUrl+sn+".do";
		else if (type == 'HWP') url = options.previewDocUrl+sn+".do";
		else if (type == 'TXT') url = options.previewImgUrl+".do?sn="+sn;
		else if (type == 'IMG') url = options.previewImgUrl+".do?sn="+sn;
		
		// 파일미리보기 (comm_utils.js 참고)
		$.fileUtil.preview({type: type, url: url});
		return false;
	};
	
    // 첨부서류 다운로드
    //--------------------------------------------------------//
	this.doDownload = function() {
		let sn  = $(this).data('sn');
		let url = options.downloadUrl;
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: {sn: btoa(sn)},
			url   : url,
			log   : {params: {atchFileSn: sn, progUrl: url}}
		});
		return false;
	};
	
    // 첨부서류 검증
    //--------------------------------------------------------//
	this.doValidate = function() {
		
		// 파일 검증확인
		let check = true;
		// 첨부파일갯수만큼 LOOP
		thisForm.find('input[name="upfile"]').each(function(i) {
			// 파일객체의 선택값
			let nfile = $(this).val();
			// 이전파일의 저장값
			let ofile = thisForm.find('input[name="fileName"]').eq(i).val();
			// 파일의 필수여부
			let need  = thisForm.find('input[name="needYn"  ]').eq(i).val();
			// 파일의 업로드여부 설정
			thisForm.find('input[name="fileYn"]').eq(i).val(nfile==''?'N':'Y');

			// 이미 false 체크된 경우 SKIP
			if (check == false)
				return false;
			
			// 파일 필수체크가 'Y'인 경우
			if (need == 'Y') {
				// 파일의 필수 체크시 파일정보가 없는 경우
				if (nfile == '' && ofile == '') {
					$.commMsg.alert('파일을 선택해 주세요.');
					check = false;
					return false;
				}
			}
			// 파일이 있는 경우
			if (!$.commUtil.empty(nfile)) {
				// 파일명 길이체크
				if ($.fileUtil.checkMaxLength(ofile, options.maxLengthName, true) == false) {
					check = false;
					return false;
				}
				// 확장자 체크
				if (options.extensions && 
					$.fileUtil.checkExtension($(this), options.extensions, true) == false) {
					check = false;
					return false;
				}
				// 용량 체크
				if (options.maxBytes &&
				    $.fileUtil.checkMaxbytes($(this), options.maxBytes, true) == false) {
					check = false;
					return false;
				}
			}
		});
		return check;
	};
	
    // [2023.01.06] 첨부파일 전체 갯수
    //--------------------------------------------------------//
	this.getCountAll = function() {
		// 파일 갯수
		let cnt = 0;
		// 첨부파일갯수만큼 LOOP
		thisForm.find('input[name="upfile"]').each(function(i) {
			// 파일객체의 선택값
			let nfile = $(this).val();
			// 이전파일의 저장값
			let ofile = thisForm.find('input[name="fileName"]').eq(i).val();
			if (nfile != '' || ofile != '') {
				cnt++;
			}
		});
		return cnt;
	};
    // [2023.01.06] 첨부파일 업로드 갯수 반환
    //--------------------------------------------------------//
	this.getUploadCount = function() {
		// 파일 갯수
		let cnt = 0;
		// 첨부파일갯수만큼 LOOP
		thisForm.find('input[name="upfile"]').each(function(i) {
			// 파일객체의 선택값
			let nfile = $(this).val();
			if (nfile != '') {
				cnt++;
			}
		});
		return cnt;
	};

	this.init();

	return this;
};



//===========================================================================//
// 제출서류 컨트롤
//===========================================================================//
$.fn.appAplyFile = function ( args ) {

	var options = $.extend({
		// 조회조건
		params: {
			//업무구분
			papeDtySeCd: false,
			//신청구분
			aplySeCd: false,
			//서류그룹
			upPapeCd: false,
			// (수정시) 문서번호
			dcmtNo: false,
			// (수정시) 세부문서번호
			dtlDcmtNo: false,
			// 서류코드
			papeCd: false
		},
		// 모드 (등록,수정,조회)
		// MODE.INSERT:  신규신청
		// MODE.UPDATE:  수정신청
		// MODE.MYPAGE:  조회(마이페이지)
		// MODE.SPLEMNT: 조회(보완요청)
		// MODE.LIST:    조회(목록보기)
		mode: MODE.INSERT,
		// 시스템코드
		system: SYSTEM.USER['code'],
		// 서류목록 제목
		title: '',
		// 서류목록 우측버튼 객체 (2021.12.27 ADD)
		titleBtn: false,
		// 서류목록 조회 URL
		papeUrl: getUrl('/adm/file/getListPape.do'),
		// 첨부파일 조회 URL
		fileUrl: getUrl('/adm/file/getListAplyFileByPape.do'),
		// 첨부파일 업로드 URL
		uploadUrl: getUrl('/adm/file/uploadAplyFile.do'),
		// 첨부파일 다운로드 URL
		downloadUrl: getUrl('/adm/file/downloadAplyFile.do'),
		// 첨부파일 압축다운로드 URL
		downloadZipUrl: getUrl('/adm/file/downloadAplyFileZip.do'),
		// 양식파일 다운로드 URL
		downloadPapeUrl: getUrl('/adm/file/downloadPapeFile.do'),
		// 첨부파일 목록로드 URL
		listUrl: getUrl('/adm/file/getListAplySubmitFile.do'),
		
		// 첨부파일 이미지링크 URL
		previewImgUrl: getUrl('/adm/file/linkAplyFile'),
		// 첨부파일 문서링크 URL
		previewDocUrl: getUrl('/adm/file/linkAplyHwp'),
		
		// OCR 확인정보 조회 URL
		ocrCheckUrl: getUrl('/adm/ocr/checkOCR.do'),
		// OCR 엔진확인 URL
		ocrStatusUrl: getUrl('/adm/ocr/statusOCR.do'),
		// OCR 다운로드 URL
		ocrAnalyzeUrl: getUrl('/adm/ocr/analyzeOCR.do'),

		// 파일선택 버튼
		fileBtn: 'app-btn-fileopen',
		// 상세보기 버튼
		viewBtn: 'app-btn-fileview',
		// 파일삭제 버튼
		deltBtn: 'app-btn-filedelt',
		// 다운로드 버튼
		downBtn: 'app-btn-download',
		// 업로드 버튼
		upldBtn: 'app-btn-upload',
		// 양식파일 다운로드 버튼
		papeBtn: 'app-btn-papefile',

		// 제목영역 스타일시트
		titleCls: 'usr-title',
		// 테이블영역 스타일시트
		tableCls: 'relief-table tableWrap type4',
		// 제목 스타일시트
		titCls: 'type1', //'apply-tit',
		// 공백 스타일시트
		spaceCls: 'h50', //'apply-space',
		// 데이터영역 스타일시트
		bodyCls: 'usr-table-body',
		// 파일영역 스타일시트
		fileCls: 'app-file-wrap',
		// 처리상태 스타일시트
		stusCls: 'app-file-stus',
		// 처리상태 항목 스타일시트
		stimCls: 'app-file-stusitem',
		// 파일항목 스타일시트
		itemCls: 'app-file-item',
		// 파일영역 ID PREFIX
		fileKey: 'app-file-',
		// 2022.01.26 서류양식 ROW 스타일시트
		rowsCls: 'app-pape-rows',

		// 첨부파일 용량제한 (바이트단위: 200MB), 209715200
		maxBytes: NUMBER.FILE_MAXBYTES,
		// 2022.01.21 첨부파일 파일명 글자수 최대길이 (50자)
		maxLengthName: NUMBER.FILE_MAXLENGTH,
		// 첨부파일 확장자 제한 (허용하는 확장자 목록)
		extensions: COMMONS.FILE_EXTENSIONS,
		// OCR 분석 가능 확장자 배열
		ocrExtensions: COMMONS.OCR_EXTENSIONS,
		// 2022.01.18 LSH 압축파일 확장자 허용 추가
		// 알집 압축파일(alz)
		// 7ZIP 압축파일(7z)
		// 일반 압축파일(zip)
		//[	"txt" ,"pdf", "hwp", "doc", "docx", "ppt", "pptx", "xls","xlsx",
		//	"jpg", "jpeg", "png", "gif", "bmp",
		//	"zip", "alz", "7z"
		//],
		// 2022.01.07 파일명 마스킹 크기
		maskLen: 20,
		// 2022.01.28 파일테이블 하단 메세지
		tableMsg: '※ PDF 파일 혹은 이미지 파일(ex. JPG, PNG 등)로 첨부 부탁드립니다.',
		// 2022.01.28 파일테이블 추가 메세지
		footerMsg: false

	}, args);

	//현재객체
	let thisCmp = this;

	//레이어객체
	let thisElm = $(this);
	
	//팝업객체
	let thisDlg = false;
	
	//저장데이터
	let thisData = {
		// 신규등록 파일 (로드된파일 포함)
		append: {},
		// 수정등록 파일
		update: {},
		// 삭제대상 파일
		remove: {}
	};
	
	//로드데이터
	let thisList = false;
	
	// 최초 실행
	this.init = function() {
		
		// 관리자인 경우
		if (options.system == SYSTEM.ADMIN['code']) {
			$.extend(options, {
				// 서류목록 조회 URL
				papeUrl: getUrl('/adm/file/getListPape.do'),
				// 첨부파일 조회 URL
				fileUrl: getUrl('/adm/file/getListAplyFileByPape.do'),
				// 첨부파일 업로드 URL
				uploadUrl: getUrl('/adm/file/uploadAplyFile.do'),
				// 첨부파일 다운로드 URL
				downloadUrl: getUrl('/adm/file/downloadAplyFile.do'),
				// 첨부파일 압축다운로드 URL
				downloadZipUrl: getUrl('/adm/file/downloadAplyFileZip.do'),
				// 양식파일 다운로드 URL
				downloadPapeUrl: getUrl('/adm/file/downloadPapeFile.do'),
				// 첨부파일 목록로드 URL
				listUrl: getUrl('/adm/file/getListAplySubmitFile.do'),
				// 첨부파일 이미지링크 URL
				previewImgUrl: getUrl('/adm/file/linkAplyFile'),
				// 첨부파일 문서링크 URL
				previewDocUrl: getUrl('/adm/file/linkAplyHwp'),
			});
		}

		// 목록조회/보완요청인 경우
		if (options.mode == MODE.LIST ||
			options.mode == MODE.SPLEMNT) {
			thisElm.html('');
			thisElm.append(thisCmp.createList());
			thisCmp.resetList();
		}
		else {
			// 목록제목 생성
			if (options.title) {
				thisElm.append( this.createPapeTitle(options.title) );
			}
			// 서류양식로드
			thisCmp.loadPape();
			// 수정,마이페이지인경우
			if (options.mode == MODE.UPDATE ||
				options.mode == MODE.MYPAGE) {
				// 첨부파일로드
				thisCmp.loadFile();
			}
		}
	};
	
	// 현재 서류그룹코드 반환
	this.getPapeGroup = function() {
		return options.params['upPapeCd'];
	};
	
	// [보완요청] 선택된 보완요청 대상목록 반환
	this.getSplemntList = function() {
		let lst = [];
		thisElm.find('input:checkbox[name="splemntYn"]:checked').each(function() {
			let sn = $(this).data('sn');
			$.each(thisList, function(i,o) {
				if (o['sn'] == sn) {
					lst.push(o);
					return false;
				}
			});
		});
		return lst;
	};
	
	// [마이페이지] 보완필요 대상목록 반환
	this.getSplemntNeedList = function() {
		let lst = [];
		// 상태항목 LOOP
		thisElm.find('.'+options.stimCls).each(function() {
			let state = $(this);
			let d = {
				sn:     state.data('sn'  ),
				papeCd: state.data('code'),
				papeNm: state.data('text'),
				prcsStusCd: state.data('stus')
			};
			// 보완요청 상태이면			
			if (d.prcsStusCd == CODE.PRCS_STUS.SUPPLEMENT) {
				lst.push(d);
			}
		});
		return lst;
	};
	// [마이페이지] 보완제출 저장데이터 반환
	this.getSplemntSaveData = function() {
		let data = {};
		data['saveFiles'  ] = $.map(thisData['update'], function(v) { return [v];});
		data['removeFiles'] = $.map(thisData['remove'], function(v) { return [v];});
		return data;
	};
	
	// [마이페이지] 보완제출 VALIDATION 및 저장 데이터 반완
	this.doSplemntValidateData = function() {
		// 보완필요목록
		let needLst = $.map(thisCmp.getSplemntNeedList(), function(v) { return [v['sn']];}).sort();
		// 삭제목록
		let remvLst = $.map(thisData['remove'], function(v) { return [v['sn']];}).sort();
		if (needLst.length == 0) {
			return {
				saveFiles:   [],
				removeFiles: []
			};
		}
		if (needLst.length != remvLst.length) {
			$.commMsg.alert('수정되지 않은 서류가 있습니다.');
			return false;
		}
		let chk = true;
		$.each(needLst, function(i, need) {
			if (need != remvLst[i]) {
				chk = false;
				return false;
			}
		});
		if (chk == false) {
			$.commMsg.alert('수정되지 않은 서류가 있습니다.');
			return false;
		}
		// 저장데이터 반환
		return thisCmp.getSplemntSaveData();
	};
	
	// 파일정보 로드
	this.loadList = function( params ) {

		thisList = false;
		let data = thisCmp.getLoadList( params );
		let rows = data['rows'];
		thisElm.find('table > tbody').html('');
		
		if (rows && rows.length > 0) {
			
			thisList = rows;
			
			$.each(rows, function(i,row) {
				// 미리보기타입 (2021.11.25 fileUtil 로 함수 이동)
				let typ = $.fileUtil.getPreviewType( row['fileNm'] );
				// 미리보기버튼
				let pbtn = $('<a href="javascript:void(0);" class="detail app-right" title="미리보기"><i class="fa fa-eye"></i></a>');
				pbtn.data('code', row['papeCd']);
				pbtn.data('sn'  , row['sn']);
				pbtn.data('type', typ);
				if ($.fileUtil.enablePreview(typ))
					pbtn.bind('click', thisCmp.doFilePreview);
				// 다운로드버튼
				let dbtn = $('<a href="javascript:void(0);" class="detail app-right" title="다운로드"><i class="fa fa-download"></i></a>');
				dbtn.data('code', row['papeCd']);
				dbtn.data('sn'  , row['sn']);
				// 파일다운로드 이벤트 바인딩
				dbtn.bind('click', thisCmp.doFileDownload);
				// OCR 분석버튼
				let obtn = $('<a href="javascript:void(0);" class="detail app-right" title="OCR분석"><i class="fa fa-cogs"></i></a>');
				obtn.data('code', row['papeCd']);
				obtn.data('sn'  , row['sn']);
				obtn.data('file', row['fileNm']);
				obtn.bind('click', thisCmp.doFileAnalyzeOCR);
				
				let check = false;
				// 보완요청인 경우
				if (options.mode == MODE.SPLEMNT) {
					// 미제출/보완필요인 경우
					if (row['prcsStusCd'] != '01') {
						check = row['prcsStusNm'];
					}
					else {
						// 보안여부 체크박스
						check = $('<input type="checkbox" name="splemntYn" class="app-splemnt app-check"/>');
						check.data('code', row['papeCd']);
						check.data('sn'  , row['sn']);
					}
				}
				else {
					check = row['prcsStusNm'];
				}
				// 2022.12.06 관리자등록파일인 경우 관리자 표시
				let papeNm = (row['mngrRegYn'] == 'Y' ? '[관리자]' : '')+row['papeNm'];
				let td = $('<td class="app-l"></td>');
				td.append(papeNm+'<br>');
				td.append('<small class="app-gray">'+row['fileNm']+'</small>');
				td.append(pbtn);
				td.append(dbtn);
				td.append(obtn);
				
				let tdcheck = $('<td class="app-c"></td>');
				tdcheck.append(check);
				
				let tr = $('<tr></tr>');
				tr.append('<td class="app-c">'+$.commUtil.nvl(row['regDate'])+'</td>');
				tr.append(td);
				tr.append(tdcheck);
				
				thisElm.find('table > tbody').append(tr);
			});
		}
		else {
			thisElm.find('table > tbody').append('<tr></tr>');
			thisElm.find('table > tbody > tr').append('<td colspan="3">검색된 결과가 없습니다.</td>');
		}
	};
	
	this.resetList = function( dom ) {
		thisElm.find('table > tbody').html('');
		thisElm.find('table > tbody').append('<tr></tr>');
		thisElm.find('table > tbody > tr').append('<td colspan="3">검색된 결과가 없습니다.</td>');
	};
	
	this.createList = function() {
		
		let table = $('<table></table>');
		table.append('<thead></thead>');
		table.append('<tbody></tbody>');
		table.find('thead').append('<tr></tr>');
		table.find('thead > tr').append('<th>등록날짜</th>');
		table.find('thead > tr').append('<th>제목 / 파일</th>');
		if (options.mode == MODE.SPLEMNT)
			table.find('thead > tr').append('<th>보완여부</th>');
		else
			table.find('thead > tr').append('<th>제출여부</th>');
		return table;
	};
	
	// 서류양식 로드
	this.loadPape = function() {
		
		let rows = thisCmp.getLoadPapes();
		if (rows && 
			rows.length > 0) {
			thisElm.append(thisCmp.createPapeTable(rows));
		}
		// 양식파일 다운로드 이벤트 바인딩
		thisElm.find('.'+options.papeBtn).each(function() {
			$(this).bind('click', thisCmp.doPapeDownload);
		});
		// 파일업로드창 오픈 이벤트 바인딩
		thisElm.find('.'+options.fileBtn).each(function() {
			$(this).bind('click', thisCmp.doOpenUpload);
		});
	};
	
	// (수정시) 개별첨부파일 로드
	this.loadFile = function() {
		
		// 양식항목 LOOP
		thisElm.find('.'+options.fileCls).each(function(i) {
			let code  = $(this).data('code');
			let esntl = $(this).data('esntl');
			let limit = $(this).data('limit');
			let rows  = thisCmp.getLoadFiles(code);
			let file  = $(this);
			
			if (rows && rows.length > 0) {
				$(rows).each(function(i, data) {
					$.extend(data, {
						mode:     MODE.MYPAGE, 
						fileYn:   'N',
						upPapeCd: options.params['upPapeCd'] 
					});
					// 등록파일에 추가
					thisData['append'][data['sn']] = data;
					// 보완요청 상태이면 수정파일에 추가			
					if (data['prcsStusCd'] == CODE.PRCS_STUS.SUPPLEMENT) {
						thisData['update'][data['sn']] = data;
					}
					// 파일세부항목 생성
					file.append(thisCmp.createFile(data));

					// 마이페이지인 경우 제출여부 맵핑
					if (options.mode == MODE.MYPAGE) {
						// 상태코드
						let stus = data['prcsStusCd'];
						// 상태스타일
						let scls = "state_mod";
						if (stus == CODE.PRCS_STUS.SUPPLEMENT)
							scls = "state_sup";
						else if (stus == CODE.PRCS_STUS.SUBMIT)
							scls = "state_ok";

						// 제출여부 표시
						let state = $('<span></span>');
						state.addClass(scls);
						state.addClass(options.stimCls);
						state.append(data['prcsStusNm']);
						state.data('code' , data['papeCd'    ]); // 서류코드
						state.data('text' , data['papeNm'    ]);
						state.data('stus' , data['prcsStusCd']);
						state.data('sn'   , data['sn'        ]);
						file.closest('tr').find('.'+options.stusCls).append(state);
						
						// 보완필요인 경우
						if (stus == CODE.PRCS_STUS.SUPPLEMENT) {
							// 수정버튼 표시
							let btn = $('<a href="javascript:void(0);" class="'+options.fileBtn+'"></a>');
							btn.append('<span class="state_mod">수정</span>');
							btn.data('code' , code ); // 서류코드
							btn.data('esntl', esntl); // 필수여부
							btn.data('limit', limit); // 최대파일갯수
							btn.data('sn'   , data['sn']); // 이전번호
							btn.bind('click', thisCmp.doOpenUpload);
							file.closest('tr').find('.'+options.stusCls).append(btn);
						}
					}
				});
			}
		});
	};

	// [AJAX] 서류양식목록 조회
	this.getLoadPapes = function() {
		
		let params = {
			// 업무구분
			papeDtySeCd: options.params['papeDtySeCd'],
			// 신청구분
			aplySeCd:    options.params['aplySeCd'],
			// 서류그룹
			upPapeCd:    options.params['upPapeCd'],
			// 서류코드 (2021.11.09 추가)
			papeCd:      options.params['papeCd']
		};
		
		// 2022.01.19 마이페이지는 문서번호조건을 포함함.
		if (options.mode == MODE.MYPAGE) {
			$.extend(params, {
				// (수정시) 문서번호
				dcmtNo:     options.params['dcmtNo'],
				// (수정시) 세부문서번호
				dtlDcmtNo:  options.params['dtlDcmtNo'],
			});
		}
		return $.ajaxUtil.ajaxDefault(options.papeUrl, params);
	};

	// [AJAX] 첨부파일 조회
	this.getLoadFiles = function( papeCd ) {
		return $.ajaxUtil.ajaxDefault(options.fileUrl, {
			// 업무구분
			dtySeCd:    options.params['papeDtySeCd'],
			// (수정시) 문서번호
			dcmtNo:     options.params['dcmtNo'],
			// (수정시) 세부문서번호
			dtlDcmtNo:  options.params['dtlDcmtNo'],
			// 서류코드
			papeCd:     papeCd
		});
	};

	// [AJAX] 신청파일목록 조회
	this.getLoadList = function( params ) {
		return $.ajaxUtil.ajaxDefault(options.listUrl, $.extend({
			// 업무구분
			dtySeCd:   options.params['papeDtySeCd'],
			// 문서번호
			dcmtNo:    options.params['dcmtNo'],
			// 세부문서번호
			dtlDcmtNo: options.params['dtlDcmtNo']
		},params));
	};

	// 서류양식 목록제목 생성
	this.createPapeTitle = function(title) {
		let div = $('<div class="'+options.titleCls+'"></div>');
		div.append('<div class="'+options.spaceCls+'"></div>');
		div.append('<h3 class="'+options.titCls+'">'+title+'</h3>');
		
		// 2021.12.27 ADD 제목우측 버튼이 있으면
		if (options.titleBtn)
			div.find('h3').append(options.titleBtn);
		
		return div;
	};

	// 서류양식 테이블 생성
	this.createPapeTable = function( rows ) {
		let div = $('<div class="'+options.tableCls+'"></div>');

		let grp = $('<colgroup></colgroup>');
		grp.append('<col style="width:45%" />');
		grp.append('<col style="" />');
		grp.append('<col style="width:200px" />');

		let head = $('<thead></thead>');
		head.append('<tr></tr>');
		head.find('tr').append('<th>접수서류</th>');
		head.find('tr').append('<th>제출서류</th>');
		head.find('tr').append('<th>제출여부</th>');
		// 마이페이지가 아닌 경우
		if (options.mode != MODE.MYPAGE)
			head.find('tr > th:last').html('');

		let body = $('<tbody class="'+options.bodyCls+'"></tbody>');

		// 행만큼 선택박스 생성
		$.each(rows, function(i, row) {
			let tr = $('<tr></tr>');
			// 2022.01.26 서류양식 ROW 스타일시트 적용
			tr.addClass(options.rowsCls);
			// 2022.01.26 서류양식 ROW 서류코드값 맵핑
			tr.data('code', row['papeCd' ]);

			// 서류명칭
			tr.append('<td class="left"><span>'+$.commUtil.nvl(row['papeNm'])+'</span></td>');
			// 마이페이지가 아닌 경우
			if (options.mode != MODE.MYPAGE) {
				if (row['esntlYn'] == 'Y') {
					// 2022.12.20 필수항목 스타일 변경
					tr.find('td:last').addClass('app-must');
				}
				if (row['limtCnt'] > 1) {
					// 2022.12.20 최대가능갯수 표시
					tr.find('td:last').find('span:first').append(' (최대 '+row['limtCnt'] +'개)');
				}
			}
			// 양식파일영역
			//let pape = $('<a href="javascript:void(0);"></a>');
			//if ($.commUtil.nvl(row['fileNm']).length > 0) {
			//    pape.addClass(options.papeBtn);
			//    pape.data('upper', row['upPapeCd']);
			//    pape.data('code' , row['papeCd' ]);
			//    pape.append($.commUtil.nvl(row['fileNm']));
			//}
			//tr.append( $('<td class="left"></td>').append(pape));
			
			// 첨부파일영역
			let file = $('<div id="'+options.fileKey+row['papeCd']+'" class="'+options.fileCls+'"></div>');
			file.data('code' , row['papeCd' ]); // 서류코드
			file.data('esntl', row['esntlYn']); // 필수여부
			file.data('limit', row['limtCnt']); // 최대파일갯수
			file.data('title', row['papeNm' ]); // 서류명칭
			file.data('index', i);
			tr.append( $('<td class="left"></td>').append(file) );

			// 마이페이지인 경우
			if (options.mode == MODE.MYPAGE) {
				// 처리상태 표시
				tr.append('<td class="'+options.stusCls+' app-normal"></td>');
			}
			// 등록/수정인 경우
			else {
				// 첨부파일 버튼
				let btn = $('<button type="button" class="'+options.fileBtn+' filebtn"></button>');
				btn.append('첨부파일');
				btn.data('code' , row['papeCd' ]); // 서류코드
				btn.data('esntl', row['esntlYn']); // 필수여부
				btn.data('limit', row['limtCnt']); // 최대파일갯수
				btn.data('index', i);
				tr.append($('<td></td>').append(btn));
			}
			body.append(tr);
		});

		let tbl = $('<table></table>');
		tbl.append(grp);
		tbl.append(head);
		tbl.append(body);
		div.append(tbl);
		if (options.tableMsg) {
			div.append('<div>'+options.tableMsg+'</div>');
		}
		if (options.footerMsg) {
			div.append('<div>'+options.footerMsg+'</div>');
		}

		return div;
	};
	
	// 첨부파일 생성
	this.createFile = function(row) {
		
		let dom = $('<div class="'+options.itemCls+' app-both"></div>');
		dom.data('sn', row['sn']);
		
		let file = $('<a class="'+options.downBtn+'" href="javascript:void(0);"></a>');
		// 2022.01.07 파일명 마스킹 처리
		file.append($.fileUtil.getMaskName(row['fileNm'], options.maskLen));
		file.data('code', row['papeCd']);
		file.data('sn'  , row['sn']);
		// 파일다운로드 이벤트 바인딩
		file.bind('click', thisCmp.doFileDownload);
		dom.append(file);
		
		// 파일삭제 버튼
		let dbtn = $('<button type="button" class="'+options.deltBtn+' filedel">삭제</button>');
		dbtn.data('code', row['papeCd']);
		dbtn.data('sn'  , row['sn']);
		dbtn.bind('click', thisCmp.doFileRemove);
		dom.append(dbtn);

		// 마이페이지인 경우
		if (options.mode == MODE.MYPAGE) {
			// 삭제버튼 감춤
			dom.find('button.'+options.deltBtn).hide();
		}

		// 미리보기타입 (2021.11.25 fileUtil 로 함수 이동)
		let typ = $.fileUtil.getPreviewType( row['fileNm'] );

		// 상세보기 버튼
		let pbtn = $('<button type="button" class="'+options.viewBtn+' fileview">보기</button>');
		pbtn.data('code', row['papeCd']);
		pbtn.data('sn'  , row['sn']);
		pbtn.data('type', typ);
		if ($.fileUtil.enablePreview(typ))
			pbtn.bind('click', thisCmp.doFilePreview);
		else
			pbtn.prop('disabled', true);
		dom.append(pbtn);
		
		return dom;
	};
	
	// 양식파일 다운로드
	this.doPapeDownload = function() {
		// 파일 다운로드 실행
		$.formUtil.submitForm(options.downloadPapeUrl, {
			params: {
				upPapeCd: $(this).data('upper'),
				papeCd:   $(this).data('code')
			}
		});
		return false;
	};
	
	// 첨부파일 미리보기
	this.doFilePreview = function() {
		let url  = false;
		let sn   = $(this).data('sn');
		let type = $(this).data('type');
		if      (type == 'PDF') url = options.previewDocUrl+sn+".do";
		else if (type == 'HWP') url = options.previewDocUrl+sn+".do";
		else if (type == 'TXT') url = options.previewImgUrl+".do?sn="+sn;
		else if (type == 'IMG') url = options.previewImgUrl+".do?sn="+sn;
		
		// 파일미리보기 (comm_utils.js 참고)
		$.fileUtil.preview({type: type, url: url});

		return false;
	};
	
	// 첨부파일 다운로드
	this.doFileDownload = function() {
		
		let log = false;
		if (options.system == SYSTEM.ADMIN['code']) {
			log = {
				params: {
					atchFileSn: $(this).data('sn'),
					progUrl: getRealUrl(options.downloadUrl)
				}
			};
		}
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: {sn: btoa($(this).data('sn'))},
			url   : options.downloadUrl,
			log   : log
		});
		return false;
	};
	
	// 첨부파일 전체 압축 다운로드 (문서번호기준)
	this.doFileDownloadZip = function(params) {
		let log = false;
		if (options.system == SYSTEM.ADMIN['code']) {
			log = {
				params: {
					dtySeCd:   params['dtySeCd'  ],
					dcmtNo:    params['dcmtNo'   ],
					dtlDcmtNo: params['dtlDcmtNo'],
					progUrl:   getRealUrl(options.downloadZipUrl)
				}
			};
		}
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: (params || options.params),
			url   : options.downloadZipUrl,
			log   : log
		});
		return false;
	};
	
	// 첨부파일 삭제 
	// (실제삭제되지 않음)
	// (저장시 삭제처리가 완료됨)
	this.doFileRemove = function() {
		
		let dom = $(this);
		let sn = $(this).data('sn');
		
		$.commMsg.confirm('정말 삭제하시겠습니까?', function() {
			let aprow = thisData['append'][sn];
			if (aprow) {
				thisData['remove'][sn] = aprow;
				delete thisData['append'][sn];
			}
			// 해당 요소 모두 삭제
			dom.closest('.'+options.itemCls).remove();
		});
		return false;
	};
	
	// 업로드한 파일정보 추가
	this.addFile = function(data) {

		$.extend(data, {
			fileYn:   'Y',
			mode:     MODE.INSERT, 
			upPapeCd: options.params['upPapeCd'] 
		}); 
		let item = thisElm.find('[id="'+options.fileKey+data['papeCd']+'"]');
		// 이전 파일번호가 있는 경우 (수정기능)
		if (!$.commUtil.empty(data['orgSn'])) {
			// 이전 파일 삭제등록
			let orgSn = data['orgSn'];
			let aprow = thisData['update'][orgSn];
			if (aprow) {
				thisData['remove'][orgSn] = aprow;
				delete thisData['update'][orgSn];
			}
			// 수정파일 추가
			thisData['update'][data['sn']] = data;
			// 이전파일 객체삭제
			item.find('.'+options.itemCls).each(function() {
				if ($(this).data('sn') == orgSn) {
					$(this).remove();
					return false;
				}
			});
			// 상태감춤
			item.closest('tr').find('.'+options.stimCls).hide();
		}
		// 등록인 경우
		else {
			// 등록파일 추가
			thisData['append'][data['sn']] = data;
		}
		// 파일 객체생성
		let dom = thisCmp.createFile(data);
		// 파일객체 추가 
		item.append(dom);
	};
	
	// 첨부파일 저장 VALIDATION
	// 2022.01.18 제출시에만 필수 체크 처리
    //--------------------------------------------------------//
	this.doValidate = function( act ) {
		
		// 파일 필수검증여부
		let needYn = true;
		if (act && act != MODE.SUBMIT)
			needYn = false;

		// 필수체크, 최대갯수 체크
		let check = true;
		
		// 서류항목만큼 LOOP
		thisElm.find('.'+options.fileCls).each(function(i) {
			let esntl = $(this).data('esntl'); // 필수여부
			let limit = $(this).data('limit'); // 갯수제한
			let title = $(this).data('title'); // 서류명칭
			let item  = $(this).find('.'+options.itemCls);
			
			if (options.title)
				title = options.title+' - '+title;
			
			title = '['+title+']';
			
			// 파일 필수검증여부가 true인 경우
			// 파일 필수체크가 'Y'인 경우
			if (needYn && esntl == 'Y') {
				if (item.length == 0) {
					$.commMsg.alert(title+ ' 항목은 반드시 등록하셔야 합니다.');
					check = false;
					return false;
				}
			}
			// 파일갯수 체크
			if (item.length > limit) {
				$.commMsg.alert(title+ ' 항목은 '+limit+'개까지 등록 가능합니다.');
				check = false;
				return false;
			}
		});
		return check;
	};

	// 2022.01.24 서류코드 기준 첨부파일 업로드 여부 체크
    //--------------------------------------------------------//
	this.doExistFile = function( papeCd ) {
		
		// 업로드여부 체크
		let upload = false;
		// 서류항목만큼 LOOP
		thisElm.find('.'+options.fileCls).each(function(i) {
			let code = $(this).data('code'); // 서류코드
			let item = $(this).find('.'+options.itemCls);
			
			// 해당 서류코드의 파일정보가 있으면
			if (code == papeCd && item.length > 0) {
				upload = true;
				return false;
			}
		});
		return upload;
	};

	// 첨부파일 저장데이터 가져오기
	this.getSaveData = function() {
		
		let data = {};
		data['saveFiles'] = $.map(thisData['append'], function(v) {
    		return [v];
		});
		data['removeFiles'] = $.map(thisData['remove'], function(v) {
    		return [v];
		});
		return data;
	};
	
	// [팝업] 첨부파일업로드 팝업열기
	this.doOpenUpload = function() {
		
		// 수정인 경우엔 SKIP
		if ($.commUtil.empty($(this).data('sn'))) {
			// 2021.10.30 추가여부 체크
			// 서류코드
			let papeCd = $(this).data('code');
			// 제한갯수
			let limit  = $(this).data('limit');
			// 파일갯수
			let count = thisElm.find('[id="'+options.fileKey+papeCd+'"]')
							.find('.'+options.itemCls)
							.length;
			
			if (count == limit) {
				let msg = '해당 항목은 '+limit+'개까지 등록 가능합니다.'
				        + '수정하시려면 삭제 후에 등록해 주세요.'; 
				$.commMsg.alert(msg);
				return false;
			}
		}
		thisDlg = thisCmp.openModal({
			papeDtySeCd: options.params['papeDtySeCd'],
			aplySeCd:    options.params['aplySeCd'],
			papeCd:      $(this).data('code'),
			esntlYn:     $(this).data('esntl'),
			sn:          $(this).data('sn')
		});
		return false;
	};

	// [팝업] 첨부파일 업로드 팝업 오픈
	this.openModal = function( params ) {

		let ul = $('<ul></ul>');
		ul.append('<li class="on"></li>');
		ul.find('li').append('<h3>파일업로드</h3>');
		ul.find('li').append($('<p></p>').append(thisCmp.createForm(params)));

		let div = $('<div class="filePop"></div>');
		div.append('<div class="cover"></div>');
		div.append('<div class="filePop-wrap"></div>');
		div.find('.filePop-wrap').append('<button type="button" class="close"></button>');
		div.find('.filePop-wrap').append('<div class="filePop-inner app-file-popup"></div>');
		div.find('.filePop-inner').append(ul);
		div.addClass("on");
		div.appendTo('body');
		div.find(".close").on("click",function(){
			$(this).parents(".filePop").remove();
		})
		// 오픈시 파일선택창이 열린다.
		div.find(".upload-hidden").trigger('click');
		
		return div;
	};
		
	// [팝업] 첨부파일 업로드 생성
	this.createForm = function( params ) {
		
		let ubtn = $('<a href="javascript:void(0);" class="uploadbtn '+options.upldBtn+'">업로드</a>');
		ubtn.bind('click', this.doFileUpload);
		
		/* form은 반드시 onsubmit="return false;" 속성이 있어야 
		 * ajaxForm 실행시 두번씩 실행되지 않는다.
		 */
		let form = $('<form name="uploadForm" class="usr-form-group" method="post" onsubmit="return false;"></form>');
		form.append('<div class="inputWrap filebox"></div>');
		form.find('.filebox').append('<input name="fileName" class="upload-name" value="파일선택" readonly />');
		form.find('.filebox').append('<label for="ex_filename1">파일첨부</label>');
		form.find('.filebox').append('<input id="ex_filename1" type="file" name="upfile" class="upload-hidden">');
		form.find('.filebox').append(ubtn);
		form.append('<input type="hidden" name="filePath" value=""/>');
		form.append('<input type="hidden" name="fileNo"   value="'+$.commUtil.nvlTrim(params['sn'         ])+'"/>');
		form.append('<input type="hidden" name="fileType" value="'+$.commUtil.nvlTrim(params['papeDtySeCd'])+'"/>');
		form.append('<input type="hidden" name="docuCd"   value="'+$.commUtil.nvlTrim(params['papeCd'     ])+'"/>');
		form.append('<input type="hidden" name="needYn"   value="'+$.commUtil.nvlTrim(params['esntlYn'    ])+'"/>');
		form.append('<input type="hidden" name="fileYn"   value="Y"/>');
		
		if (form.find(".filebox").length){
			form.find(".filebox .upload-hidden").on("change", function(){ //값이 변경되면
				if (window.FileReader){ //modern browser
					var filename = $(this)[0].files[0].name;
				}else { //old IE
					var filename = $(this).val().split("/").pop().split("\\").pop(); //파일명만 추출
				} //추출한 파일명 삽입
				$(this).siblings(".upload-name").val(filename);
			});
		};
		return form;
	};

	// [팝업] 첨부파일 업로드 VALIDATION
	this.doUploadValidate = function( formElm ) {
		
		let fobj = formElm.find('input[name="upfile"]');
		if (fobj.val() == '') {
			$.commMsg.alert('파일을 선택해 주세요.');
			return false;
		}
		let fname = formElm.find('input[name="fileName"]').val();
		// 파일명 길이체크
		if ($.fileUtil.checkMaxLength(fname, options.maxLengthName, true) == false) {
			return false;
		}
		// 확장자 체크
		if ($.fileUtil.checkExtension(fobj, options.extensions, true) == false) {
			return false;
		}
		// 용량 체크
		if ($.fileUtil.checkMaxbytes(fobj, options.maxBytes, true) == false) {
			return false;
		}
		return true;
	};
	
	// [팝업] 첨부파일 업로드 처리
	this.doFileUpload = function() {
		let formElm = $('form[name="uploadForm"]');
		// 첨부파일 업로드 VALIDATION
		if (!thisCmp.doUploadValidate(formElm))
			return false;
		// 폼을 AJAX로 저장처리
		formElm.ajaxForm({
			url: options.uploadUrl,
			enctype : 'multipart/form-data',
			// 에러시 처리로직
			error: $.ajaxUtil.error,
			// 저장후 처리로직
			success: function(ret) {
				if (ret && ret['File']) {
					thisCmp.addFile(ret['File']);
				}
				thisDlg.find('.close').trigger('click');
			}
		}).submit();
	};
	
	// OCR 분석 (분석버튼 클릭시)
	// 2022.12.06 OCR 분석버튼은 요양급여내역서에만 보이도록 수정
	// 2022.12.13 우선 모두 보이도록 수정함(권이사님 요청)
	// 2023.02.02 분석결과 포맷을 선택하는 팝업 추가
	// 2023.02.06 PDF 인 경우 페이지 설정 팝업 추가
	// 2023.02.21 OCR 엔진상태를 체크한 후 진행하도록 추가
	this.doFileAnalyzeOCR = function() {
		
		let sn     = $(this).data('sn'  ); // 파일순번
		let fileNm = $(this).data('file'); // 파일명
		let fileEx = $.fileUtil.getExtension(fileNm);

		if ($.inArray(fileEx, options.ocrExtensions) == -1) {
			$.commMsg.alert([
				'해당 파일(<small class="app-red">'+fileNm+'</small>)은 OCR 분석대상 파일이 아닙니다.<br>',
				'OCR 분석가능 파일포맷: [<span class="app-blue">'+options.ocrExtensions.join()+'</span>]'
			].join(''));
			return false;
		}
		// OCR 엔진상태체크 (필요한 경우 사용할것)
		if (thisCmp.doCheckOcrEngine() == false)
			return;
		// PDF 경우
		if (fileEx == 'pdf') {
			thisCmp.doOpenOcrOption({
				params:   {sn: sn},
				callback: function( ocrParams ) {
					// 전체 페이지수
					const totalPages = parseInt(ocrParams['enPage']) 
					                 - parseInt(ocrParams['stPage']) 
                                     + 1;
					// 표시간격 1초로 설정 (comm_const.js 참고)
					const delayTime = NUMBER.OCR_DELAY_TIME; // 시간간격 1초로 설정 (comm_const.js 참고)
					// 예상실행시간 가져오기 (comm_const.js 참고)
					const totalTime = NUMBER.OCR_GET_TIME(totalPages);
					// 실행함수
					let timerId;
					// OCR 분석수행
					thisCmp.doFileOcr({
						url:    options.ocrAnalyzeUrl,
						params: ocrParams,
						// 시작시 호출함수
						fnStart: function( dialog ) {
							let div = $('<div class="app-ocr-view"></div>');
							div.append('<div><p>PDF 페이지 수를 기준으로 추정한 대략적인 예상 시간이며 오차가 있을 수 있습니다.</p></div>');
							div.append('<div class="app-col"></div>');
							div.find('.app-col').append('<b   style="width:15%">예상시간</b>');
							div.find('.app-col').append('<div style="width:19%"><span id="s_totalTime">'+$.formatUtil.toTime(totalTime)+'</span></div>');
							div.find('.app-col').append('<b   style="width:15%">남은시간</b>');
							div.find('.app-col').append('<div style="width:18%"><span id="s_remindTime" class="app-red"></span></div>');
							div.find('.app-col').append('<b   style="width:15%">진행시간</b>');
							div.find('.app-col').append('<div style="width:18%"><span id="s_runningTime" class="app-blue"></span></div>');
							dialog.getModalBody().find('.app-ocrtime').append('<p class="h30"></p>')
							dialog.getModalBody().find('.app-ocrtime').append(div);
							// 남은시간
							let remindTime  = totalTime;
							// 걸린시간
							let runningTime = 0;
							timerId = setTimeout(function request() {
								remindTime  -= delayTime;
								runningTime += delayTime;
								if (remindTime >= 0) {
									$('#s_remindTime' ).html($.formatUtil.toTime(remindTime ));	
								}
								$('#s_runningTime').html($.formatUtil.toTime(runningTime));	
								timerId = setTimeout(request, delayTime);
							}, delayTime);
						},
						// 종료시 호출함수
						fnEnd  : function() {
							clearTimeout(timerId);
						}
					});
				}
			});
		}
		// 그외
		else {
			$.commMsg.confirm([
					'OCR 분석 완료 후엔 <span class="app-blue">텍스트 파일</span>로 다운로드되며,<br>',
					'파일 용량에 따라 실행 시간이 오래 걸릴 수 있습니다.<br>',
					'[<small class="app-red">'+fileNm+'</small>]의 OCR 분석을 수행하시겠습니까?'
				].join(''), function() {
					thisCmp.doFileOcr({
						url:    options.ocrAnalyzeUrl,
						params: {sn:sn, ocrType:'TXT'}
					});
				}
			);
		}
	};
	
	// 2023.02.02 OCR 분석 설정 선택 팝업 (파일형식/페이지범위)
	this.doOpenOcrOption = function( openArgs ) {
		
		let url      = options.ocrCheckUrl;
		let params   = openArgs.params;
		let callback = openArgs.callback;
		
		let result = $.ajaxUtil.ajaxDefault(url, params);
		if (result && 
			result.Data) {
			
			let data    = result.Data;
			let sn      = data['sn'      ];
			let pageYn  = data['pageYn'  ];
			let typeYn  = data['typeYn'  ];
			let fileNm  = data['fileName'];
			let existYn = data['existYn' ];
			let maxPage = parseInt(data['maxPages']);
			let allPage = parseInt(data['allPages']);
			
			if (allPage < maxPage)
				maxPage = allPage;
			
			if (existYn != 'Y') {
				$.commMsg.alert('OCR 분석대상 파일이 존재하지 않습니다. [파일명: '+fileNm+']');
				return false;
			}
			// OCR 분석설정 팝업 오픈
			let ocrpop = $('#layerPopup').appPopup({
				title:   'OCR 분석설정',
				popupCls:'layerPop form type3 listLock',
				message: function() {
					
					let table = $('<table></table>');
					table.append('<tr></tr>');
					table.find('tr:last').append('<th class="app-l">OCR 분석대상 파일명</th>');
					table.find('tr:last').append('<td>'+fileNm+'</td>');
					table.append('<tr></tr>');
					table.find('tr:last').append('<th class="app-l">OCR 분석대상 전체 페이지수</th>');
					table.find('tr:last').append('<td> 총 '+allPage+' 페이지</td>');
					
					if (typeYn == 'Y') {
						table.append('<tr></tr>');
						table.find('tr:last').append('<th class="app-l">OCR 분석결과 파일 형식을 선택하세요.</th>');
						let input1 = $('<div class="inputWrap"></div>');
						input1.append('<input type="radio" name="ocrType" id="ocr_type1" value="TXT" checked /><label for="ocr_type1">텍스트</label>');
						input1.append('<input type="radio" name="ocrType" id="ocr_type2" value="XLS"/><label for="ocr_type2">엑셀</label>');
						table.find('tr:last').append('<td></td>');
						table.find('td:last').append(input1);
					}
					if (pageYn == 'Y') {
						table.append('<tr></tr>');
						table.find('tr:last').append([
							'<th class="app-l">',
							'OCR 분석 페이지 범위를 설정하세요.<br>',
							'(범위 : <span class="app-blue">1 ~ '+allPage+'</span>,',
							' 최대 : <span class="app-red">'+maxPage+'</span>)',
							'</th>'
						].join(''));

						let input2 = $('<div class="inputWrap"></div>');
						input2.append('<input type="text" name="stPage" id="ocr_stPage" class="app-number app-w60" value="1"/>');
						input2.append('<span class="mark">~</span>');
						input2.append('<input type="text" name="enPage" id="ocr_enPage" class="app-number app-w60" value="'+maxPage+'"/>');
						table.find('tr:last').append('<td></td>');
						table.find('td:last').append(input2);
					}
					let form = $('<form name="ocrForm"></form>');
					form.append('<input type="hidden" name="sn"       id="ocr_sn"       value="'+sn+'"/>');
					form.append('<input type="hidden" name="pageYn"   id="ocr_pageYn"   value="'+pageYn+'"/>');
					form.append('<input type="hidden" name="typeYn"   id="ocr_typeYn"   value="'+typeYn+'"/>');
					form.append('<input type="hidden" name="allPages" id="ocr_allPages" value="'+allPage+'"/>');
					form.append(table);
					
					let dom = $('<div></div>');
					dom.append('<div class="tableWrap type3"></div>');
					dom.append('<div class="btnWrap type3 one"></div>');
					dom.find('.tableWrap').append(form);
					dom.find('.btnWrap'  ).append('<a href="javascript:void(0);" class="app-popup-btn">확인</a>');
					// 숫자만 입력 이벤트				
					dom.find('.app-number').inputmask("numeric", {allowMinus: false, digits: 3, max: 1000});
	                // label 클릭 이벤트
					dom.find('label').on('click', function(){ dom.find("input[id='"+$(this).attr("for")+"']").click();});
					// 확인 버튼 클릭 이벤트
					dom.find('a.app-popup-btn').bind('click', function() {
						
						let p = dom.find('form[name="ocrForm"]').serializeObject();
						
						let typeNm  = '텍스트 파일';
						if (typeYn == 'Y') {
							let id = dom.find('input[name="ocrType"]:checked').attr("id");
							typeNm = dom.find('label[for="'+id+'"]').text() +' 파일';
						}
						let message = 'OCR 분석 완료 후엔 '
						            + '<span class="app-blue">'+typeNm+'</span>'
									+ '로 다운로드되며,<br>'
									+ 'OCR 분석대상 페이지는 ';
						
						if (pageYn == 'Y') {
							let stPage = p['stPage'];
							let enPage = p['enPage'];
							if ($.commUtil.empty(stPage)) {
								$.commMsg.alert('시작페이지를 입력하세요.');
								return false;
							}
							if ($.commUtil.empty(enPage)) {
								$.commMsg.alert('종료페이지를 입력하세요.');
								return false;
							}
							stPage = parseInt(stPage);
							enPage = parseInt(enPage);
							if (stPage < 1 || stPage > allPage) {
								$.commMsg.alert('시작페이지를 [<span class="app-blue">1 ~ '+allPage+'</span>] 범위 내의 값으로 입력하세요.');
								return false;
							}
							if (enPage < 1 || enPage > allPage) {
								$.commMsg.alert('종료페이지를 [<span class="app-blue">1 ~ '+allPage+'</span>] 범위 내의 값으로 입력하세요.');
								return false;
							}
							if (enPage < stPage) {
								$.commMsg.alert('종료페이지를 시작페이지보다 크거나 같은 값으로 입력하세요.');
								return false;
							}
							if ((enPage-stPage+1) > maxPage) {
								$.commMsg.alert('페이지 범위를 [<span class="app-red">'+maxPage+'</span>] 페이지 이내로 설정하세요.');
								return false;
							}
							message += '<span class="app-blue">'+stPage+' ~ '+enPage+'</span> 페이지 입니다.<br>';
							message += '<small class="app-green">[예상실행시간 : '+$.formatUtil.toTimeKor(NUMBER.OCR_GET_TIME(enPage-stPage+1))+']</small><br>';
						}
						else {
							message += '총 <span class="app-blue">'+allPage+'</span> 페이지 입니다.<br>';
							message += '<small class="app-green">[예상실행시간 : '+$.formatUtil.toTimeKor(NUMBER.OCR_GET_TIME(allPage))+']</small><br>';
						}
						message += '파일 용량에 따라 실행 시간이 오래 걸릴 수 있습니다.<br>';
						message += '[<small class="app-red">'+fileNm+'</small>]의<br>';
						message += 'OCR 분석을 수행하시겠습니까?';
						
						$.commMsg.confirm(message, function() {
							ocrpop.close();
							callback( p );
						});
						return false;
					});
					return dom;
				}
			}).open();
		}
	};
		
	// 2023.02.21 OCR 엔진 상태확인
	this.doCheckOcrEngine = function() {
		let ret = $.ajaxUtil.ajaxDefault(options.ocrStatusUrl, {});
		if (ret) {
			if (ret['Data'] == 'OK')
				return true;
			else if (ret['Message']) {
				$.commMsg.alert(ret['Message']);
				return false;
			}
		}
		$.commMsg.alert('OCR 서버 통신 중 오류가 발생하였습니다.');
		return false;
	};

	// OCR 분석 다운로드 실행
	this.doFileOcr = function( ocrArgs ) {
		
		// 2023.02.13 진행중 세션종료방지를 위해 세션연장처리
		if ($("#clickInfo").length > 0)
			$("#clickInfo").trigger('click');

		// 다운로드이력 등록
		popup.openDownLog({
			params: {
				atchFileSn: ocrArgs.params['sn'],
				progUrl: getRealUrl(ocrArgs.url)
			},
			saveCallback: function() {
				let pop;
				// 2023.01.10 LSH 진행상태 표시 다운로드 실행 (comm_utils.js 참고)
				$.fileUtil.downloadProgress({
					url:    ocrArgs.url,
					params: ocrArgs.params,
					progress: {
						start: function() {
							pop = $.commModal.openView({
								// 배경클릭시 창닫힘 방지
								closeByBackdrop: false,
								// 닫기제외
								closable: false,
								title:   'OCR 텍스트 인식중입니다',
								message: '<div class="app-ocrloading"></div><div class="app-ocrtime"></div>',
								onshown: function( dialog ) {
									if (ocrArgs.fnStart)
										ocrArgs.fnStart(dialog);
								}
							});
						},
						end: function() {
							if (ocrArgs.fnEnd)
								ocrArgs.fnEnd();
							pop.close();
						}
					}
				});
			}
		});
		return false;
	};

	this.init();

	return this;
};

//===========================================================================//
// 게시판첨부파일 컨트롤
//===========================================================================//
$.fn.appBbsFile = function ( args ) {

	var options = $.extend({

		// 생성시 기본 데이터
		initData: {},
		// 컨트롤 전체를 감싸는 영역
		controlWrap: 'file_control_wrap',
		// 업로드 버튼을 감싸는 영역
		buttonWrap: 'file_button_wrap',
		// 파일박스 전체를 감싸는 영역
		fileWrap: 'file_wrap',
		// 파일선택박스를 감싸는 영역
		boxWrap: 'box_wrap',
		// 버튼을 감싸는 영역
		btnWrap: 'file_btn_wrap',
		// 파일선택박스 객체
		fileBox: 'input_file',
		// 파일텍스트박스 객체
		textBox: 'input_text',
		// 파일선택 버튼
		fileBtn: 'btn_file',
		// 파일추가 버튼
		addBtn: 'btn_add',
		// 파일삭제 버튼
		delBtn: 'btn_del',
		// 첨부파일 용량제한 (바이트단위: 200MB), 209715200
		maxBytes: NUMBER.FILE_MAXBYTES,
		// 2022.01.21 첨부파일 파일명 글자수 최대길이 (50자)
		maxLengthName: NUMBER.FILE_MAXLENGTH,
		// 첨부파일 확장자 제한 (허용하는 확장자 목록)
		extensions: COMMONS.FILE_EXTENSIONS,
		//[	"txt" ,"pdf", "hwp", "doc", "docx", "ppt", "pptx", "xls","xlsx",
		//	"jpg", "jpeg", "png", "gif", "bmp",
		//	"zip", "alz", "7z"
		//],
		// 초기 표시 갯수
		initCount: 1,
		// 추가 최대 갯수
		maxCount: 5,
		// 추가,삭제 가능 여부
		multiple: true,
		// 메세지 alert 여부
		isAlert: false,
		// 확장자 체크 여부
		isExt:   false,
		// 용량 제한 체크 여부
		isLimit: false,

		// 파일 타입
		fileType: false,

		// [업로드모달창] 업로드창 제목
		title:    '첨부파일 업로드',
		// [업로드모달창] 업로드폼 명칭
		formId:   'uploadForm',
		// [업로드모달창] 파일영역 레이어 명칭
		attachId: 'file-attach',
		// [업로드모달창] 업로드창 추가 EL
		appendEl: false,
		
		// 업로드 URL
		uploadUrl  : getUrl('/usr/bbs/uploadBbsFile.do'),
		// 파일 목록 조회 URL
		loadUrl    : getUrl('/usr/bbs//searchBbsFile.do'),
		// 파일 단일 삭제 URL
		removeUrl  : getUrl('/usr/bbs/deleteBbsFile.do'),
		// 파일 다운로드 URL
		downloadUrl: getUrl('/usr/bbs/downBbsFile.do'),
		// 압축파일 다운로드 URL
		downloadZipUrl: getUrl('/usr/bbs/downloadZipBbsFile.do')

	}, args);

	if (options.fileType)
		options.initData['fileType'] = options.fileType;

	//현재객체
	var thisCmp = this;

	//레이어객체
	var thisElm = $(this);

	// 첨부파일박스 생성
	this.init = function( data, elm ) {

		options.initData = (data || options.initData);

		if (elm)
			thisElm = elm;

		// 이전데이터 제거
		this.destroy();

		if (thisElm.find('.'+options.controlWrap).length == 0)
			thisElm.append('<div class="'+options.controlWrap+'"></div>');
		if (thisElm.find('.'+options.buttonWrap).length == 0)
			thisElm.append('<div class="'+options.buttonWrap +'"></div>');

		// 첨부파일 선택박스 생성
		this.initBox();
		//this.createBox(thisElm.find('.'+options.controlWrap), options.initData);

		return this;
	};
	
	this.initBox = function() {
		for (let i = 0; i <options.initCount; i++) {
			// 첨부파일 선택박스 생성
			this.createBox(thisElm.find('.'+options.controlWrap), options.initData);
		}		
	};
	
	// 이전 데이터 제거
	this.destroy = function() {
		thisElm.find("."+options.fileWrap).each(function() {
			$(this).remove();
		});
		thisElm.find("."+options.btnWrap).each(function() {
			$(this).remove();
		});
	};

	// 파일박스 리셋
	this.reset = function() {
		this.init();
	};

	// 파일선택박스 이벤트 바인딩
	this.bindBox = function(elm) {

		var wrp  = '.'+ options.fileWrap;
		var fadd = '.'+ options.addBtn;
		var fdel = '.'+ options.delBtn;
		var fbox = '.'+ options.fileBox;
		var tbox = '.'+ options.textBox;

		// 첨부파일 파일선택 변경시
		// 텍스트박스에 파일명을 표시해주는 이벤트
		elm.on('change', fbox, function() {
			// 파일명만 추출한다.
			var fname = $(this).val().split("\\").pop();
			// 텍스트박스에 셋팅한다.
			$(this).closest(wrp).find(tbox).val(fname);
		});
		// 파일선택 추가 이벤트
		elm.on("click", fadd, function() {
			var oform = $(this).closest(wrp);
			// 최대갯수 체크
			if (thisElm.find(fbox).length >= options.maxCount) {
				$.commMsg.alert('추가할 최대 갯수는 '+options.maxCount+'개 입니다.');
				return false;
			}
			// 객체복사
			var cform = oform.clone(true).hide();
			cform.find('input[name="fileNo"]'  ).val("");
			cform.find('input[name="fileName"]').val("");
			cform.end().find(fbox).off("change");
			cform.insertAfter(oform);
			cform.fadeIn(200);
			return false;
		});
		// 파일선택 삭제 이벤트
		elm.on("click", fdel, function() {
			var oform = $(this).closest(wrp);
			var title = oform.find(tbox).attr("title");
			var len   = oform.parent().find(tbox+"[title='" + title + "']").length;
			var obox  = oform.find(tbox);
			if (len <= 1) {
				$.commMsg.alert('파일은 최소 1개 이상 등록되어야 합니다.');
				return false;
			}
			// 화면에서 파일항목을 삭제한다.
			var fnDelete = function() {
				oform.fadeOut(200, function() { oform.remove(); });
				return false;
			};
			if (obox.val() === "") {
				fnDelete();
				return false;
			}
			if (confirm('파일을 삭제하시겠습니까?')) {
				fnDelete();
				return false;
			}
			return false;
		});
	};

	// 파일선택박스 생성
	this.createBox = function(elm, data) {
		let dom = $('<div class="'+options.fileWrap+'"></div>');
		dom.append('<input type="hidden" name="docuNo"   value="'+$.commUtil.nvlTrim(data['dcmtNo'  ])+'"/>');
		dom.append('<input type="hidden" name="fileType" value="'+$.commUtil.nvlTrim(data['fileType'])+'"/>');
		dom.append('<input type="hidden" name="filePath" value="'+$.commUtil.nvlTrim(data['filePath'])+'"/>');
		dom.append('<input type="hidden" name="fileNo"   value="'+$.commUtil.nvlTrim(data['sn'      ])+'"/>');
		dom.append('<input type="hidden" name="needYn"   value="'+$.commUtil.nvlTrim(data['needYn'  ])+'"/>');
		dom.append('<input type="hidden" name="fileYn"   value="N"/>');
		dom.append('<input type="text"   name="fileName" value="'+$.commUtil.nvlTrim(data['fileName'])+'" class="'+options.textBox+'" title="filebox" readonly="readonly"/>');
		dom.append('<div class="'+options.boxWrap+'"></div>');
		dom.find("."+options.boxWrap).append('<input type="file" name="upfile" class="'+options.fileBox+'">');
		dom.find("."+options.boxWrap).append('<button type="button" class="btn btn-default '+options.fileBtn+'"></button>');

		// 다중갯수 허용이면
		if (options.multiple) {
			dom.append('<button type="button" class="btn btn-default '+options.addBtn+'"><span class="fa fa-plus"  aria-hidden="true"></span></button>');
			dom.append('<button type="button" class="btn btn-default '+options.delBtn+'"><span class="fa fa-minus" aria-hidden="true"></span></button>');
		}
		thisCmp.bindBox(dom);

		elm.append(dom);
	};

	// 첨부파일 데이터 로드 (수정시에 호출)
	this.loadBox = function( url, params ) {
		// 첨부파일 목록 초기화
		this.destroy();
		$.ajaxUtil.ajaxLoad(url, params, function(result) {
			var rows = result.rows;
			if (rows &&
				rows.length &&
				rows.length > 0) {
				// 행만큼 선택박스 생성
				$.each(rows, function(i, row) {
					thisCmp.createBox(thisElm.find('.'+options.controlWrap), row);
				});
			}
			else {
				// 기본선택박스 생성
				thisCmp.createBox(thisElm.find('.'+options.controlWrap), params);
			}
		});
	};

	// 첨부파일 목록조회 (상세조회시에 호출)
	// dom: 첨부파일목록이 표시될 레이어 객체
	// args.isEasyUI: EasyUI 그리드 사용여부
	// args.loadUrl: 첨부파일 목록조회 URL
	// args.params: 첨부파일 목록조회 조건
	// args.downloadUrl: 첨부파일 다운로드 URL
	// args.removeUrl: 첨부파일 단일삭제 URL
	// args.removeCallback: 첨부파일 단일삭제후 실행할 함수
	this.select = function(args) {

		var params         = args.params;
		var loadUrl        = args.loadUrl || options.loadUrl;
		//var removeUrl      = args.removeUrl || options.removeUrl;
		var downloadUrl    = args.downloadUrl || options.downloadUrl;
		//var removeCallback = args.removeCallback;
		
		$.ajaxUtil.ajaxLoad(loadUrl, params, function(result) {
			var rows = result.rows;
			if (rows &&
				rows.length &&
				rows.length > 0) {

				var table = $('<table class="file-table"></table>');
				table.append('<tbody></tbody>');

				$.each(rows, function(i,row) {
					var keys = ' data-type="'+row['fileType']+'"'
							 + ' data-no="'  +row['dcmtNo']+'"'
							 + ' data-seq="' +row['sn']+'"';

					var tr = $('<tr></tr>');
					tr.append('<td></td>');
					//tr.append('<td></td>');
					tr.find('td:first').append('<a href="javascript:void(0);"'+keys+' class="icon-anchor btnFileDown">'+row['fileName']+'</a>');
					// 2021.11.23 LSH 첨부파일 상세에서 삭제하는 로직 제외
					//tr.find('td:last').append('<button '+keys+' class="icon-anchor btnFileDelt"><i class="fa fa-remove" aria-hidden="true"></i></button>');
					table.find('tbody').append(tr);
				});
				thisElm.html('');
				thisElm.append(table);
				thisElm.find(".btnFileDown").on('click', function() {
					thisCmp.download(downloadUrl, {
						fileType: $(this).data('type'),
						dcmtNo:   $(this).data('no'),
						sn:       $(this).data('seq')
					});
				});
				/* 2021.11.23 LSH 첨부파일 상세에서 삭제하는 로직 제외
				thisElm.find(".btnFileDelt").on('click', function() {
					thisCmp.remove(removeUrl, {
						fileType: $(this).data('type'),
						dcmtNo:   $(this).data('no'),
						sn:       $(this).data('seq')
					}, removeCallback);
				});
				*/
			}
		});
	};

	// 첨부파일 업로드 VALIDATION
	// args.isAlert : 메세지 alert 표시 여부
	// args.isExt   : 확장자 체크 여부
	// args.extensions : 허용가능 확장자 배열 (확장자 체크시에만 필수)
	// args.isLimit : 용량 체크 여부
	// args.maxBytes : 허용가능 바이트단위 용량크기 (용량 체크시에만 필수)
	this.validate = function(args) {
		let check = true;
		let extensions = (args.extensions || options.extensions);
		let maxBytes   = (args.maxBytes   || options.maxBytes);
		let isAlert    = (args.isAlert    || options.isAlert);
		let isExt      = (args.isExt      || options.isExt);
		let isLimit    = (args.isLimit    || options.isLimit);

		thisElm.find('input[name="upfile"]').each(function(i) {
			let fobj  = $(this);
			let nfile = fobj.val();
			let ofile = thisElm.find('input[name="fileName"]').eq(i).val();
			let need  = thisElm.find('input[name="needYn"]'  ).eq(i).val();

			if (nfile === '')
				thisElm.find('input[name="fileYn"]').eq(i).val('N');
			else
				thisElm.find('input[name="fileYn"]').eq(i).val('Y');

			//이미 false 체크된 경우 SKIP
			if (check == false)
				return;

			if (nfile == '' && ofile == '') {
				//필수 체크가 아닌 경우 SKIP
				if (need != 'Y')
					return;
				if (isAlert)
					$.commMsg.alert('파일을 선택해 주세요.');
				check = false;
				return;
			}
			// 파일이 있는 경우
			if (!$.commUtil.empty(nfile)) {
				// 파일명 길이체크
				if ($.fileUtil.checkMaxLength(ofile, options.maxLengthName, true) == false) {
					check = false;
					return false;
				}
				// 확장자 체크
				if (isExt) {
					if ($.fileUtil.checkExtension(fobj, extensions, isAlert) == false) {
						check = false;
						return;
					}
				}
				// 용량 체크
				if (isLimit) {
					if ($.fileUtil.checkMaxbytes(fobj, maxBytes, isAlert) == false) {
						check = false;
						return;
					}
				}
			}
		});
		return check;
	};

	// 첨부파일 삭제
	// url : 첨부파일 삭제 URL
	// seq : 첨부파일 고유번호
	this.remove = function( url, params, callback ) {
		
		url = (url || options.removeUrl);
		
		$.commMsg.confirm('정말 삭제하시겠습니까?', function() {
	   		$.ajaxUtil.ajaxLoad(url, params, callback);
		});
		return false;
	};

	// 첨부파일 다운로드 (링크 클릭시 처리)
	this.download = function( url, params ) {
		url = (url || options.downloadUrl);
		// 파일 다운로드 실행
		$.formUtil.submitForm(url, {params: params});
		return false;
	};

	// 2021.08.03 첨부파일 압축 다운로드 (링크 클릭시 처리)
	this.downloadZip = function( url, params ) {
		url = (url || options.downloadZipUrl);
		// 파일 압축 다운로드 실행
		$.formUtil.submitForm(url, {params: params});
		return false;
	};

	return this;
};

//===========================================================================//
// TODO 2023.01.16 살생물제품 제출서류 컨트롤
//===========================================================================//
$.fn.appBioAplyFile = function ( args ) {

	var options = $.extend({
		// 조회조건
		params: {
			//업무구분
			papeDtySeCd: false,
			//신청구분
			aplySeCd: false,
			//서류그룹
			upPapeCd: false,
			// (수정시) 문서번호
			dcmtNo: false,
			// (수정시) 세부문서번호
			dtlDcmtNo: false,
			// 서류코드
			papeCd: false
		},
		// 모드 (등록,수정,조회)
		// MODE.INSERT:  신규신청
		// MODE.UPDATE:  수정신청
		// MODE.LIST:    조회(목록보기)
		mode: MODE.INSERT,
		// 서류목록 제목
		title: '',
		// 서류목록 우측버튼 객체 (2021.12.27 ADD)
		titleBtn: false,
		// 서류목록 조회 URL
		papeUrl: getUrl('/adm/bio/getListBioPape.do'),
		// 첨부파일 조회 URL
		fileUrl: getUrl('/adm/bio/getListBioAplyFileByPape.do'),
		// 첨부파일 업로드 URL
		uploadUrl: getUrl('/adm/bio/uploadBioAplyFile.do'),
		// 첨부파일 다운로드 URL
		downloadUrl: getUrl('/adm/bio/downloadBioAplyFile.do'),
		// 첨부파일 압축다운로드 URL
		downloadZipUrl: getUrl('/adm/bio/downloadBioAplyFileZip.do'),
		// 양식파일 다운로드 URL
		downloadPapeUrl: getUrl('/adm/bio/downloadBioPapeFile.do'),
		// 첨부파일 목록로드 URL
		listUrl: getUrl('/adm/bio/getListBioAplySubmitFile.do'),
		
		// 첨부파일 이미지링크 URL
		previewImgUrl: getUrl('/adm/bio/linkBioAplyFile'),
		// 첨부파일 문서링크 URL
		previewDocUrl: getUrl('/adm/bio/linkBioAplyHwp'),

		// 파일선택 버튼
		fileBtn: 'app-btn-fileopen',
		// 상세보기 버튼
		viewBtn: 'app-btn-fileview',
		// 파일삭제 버튼
		deltBtn: 'app-btn-filedelt',
		// 다운로드 버튼
		downBtn: 'app-btn-download',
		// 업로드 버튼
		upldBtn: 'app-btn-upload',
		// 양식파일 다운로드 버튼
		papeBtn: 'app-btn-papefile',

		// 제목영역 스타일시트
		titleCls: 'usr-title',
		// 테이블영역 스타일시트
		tableCls: 'relief-table tableWrap type4',
		// 제목 스타일시트
		titCls: 'type1', //'apply-tit',
		// 공백 스타일시트
		spaceCls: 'h50', //'apply-space',
		// 데이터영역 스타일시트
		bodyCls: 'usr-table-body',
		// 파일영역 스타일시트
		fileCls: 'app-file-wrap',
		// 처리상태 스타일시트
		stusCls: 'app-file-stus',
		// 처리상태 항목 스타일시트
		stimCls: 'app-file-stusitem',
		// 파일항목 스타일시트
		itemCls: 'app-file-item',
		// 파일영역 ID PREFIX
		fileKey: 'app-file-',
		// 2022.01.26 서류양식 ROW 스타일시트
		rowsCls: 'app-pape-rows',

		// 첨부파일 용량제한 (바이트단위: 200MB), 209715200
		maxBytes: NUMBER.FILE_MAXBYTES,
		// 2022.01.21 첨부파일 파일명 글자수 최대길이 (50자)
		maxLengthName: NUMBER.FILE_MAXLENGTH,
		// 첨부파일 확장자 제한 (허용하는 확장자 목록)
		extensions: COMMONS.FILE_EXTENSIONS,
		// 2022.01.18 LSH 압축파일 확장자 허용 추가
		// 알집 압축파일(alz)
		// 7ZIP 압축파일(7z)
		// 일반 압축파일(zip)
		//[	"txt" ,"pdf", "hwp", "doc", "docx", "ppt", "pptx", "xls","xlsx",
		//	"jpg", "jpeg", "png", "gif", "bmp",
		//	"zip", "alz", "7z"
		//],
		// 2022.01.07 파일명 마스킹 크기
		maskLen: 20,
		// 2022.01.28 파일테이블 하단 메세지
		tableMsg: '※ PDF 파일 혹은 이미지 파일(ex. JPG, PNG 등)로 첨부 부탁드립니다.',
		// 2022.01.28 파일테이블 추가 메세지
		footerMsg: false

	}, args);

	//현재객체
	let thisCmp = this;

	//레이어객체
	let thisElm = $(this);
	
	//팝업객체
	let thisDlg = false;
	
	//저장데이터
	let thisData = {
		// 신규등록 파일 (로드된파일 포함)
		append: {},
		// 수정등록 파일
		update: {},
		// 삭제대상 파일
		remove: {}
	};
	
	//로드데이터
	let thisList = false;
	
	// 최초 실행
	this.init = function() {

		// 목록조회인 경우
		if (options.mode == MODE.LIST) {
			thisElm.html('');
			thisElm.append(thisCmp.createList());
			thisCmp.resetList();
		}
		else {
			// 목록제목 생성
			if (options.title) {
				thisElm.append( this.createPapeTitle(options.title) );
			}
			// 서류양식로드
			thisCmp.loadPape();
			// 수정인경우
			if (options.mode == MODE.UPDATE) {
				// 첨부파일로드
				thisCmp.loadFile();
			}
		}
	};
	
	// 현재 서류그룹코드 반환
	this.getPapeGroup = function() {
		return options.params['upPapeCd'];
	};
	
	// 파일정보 로드
	this.loadList = function( params ) {

		thisList = false;
		let data = thisCmp.getLoadList( params );
		let rows = data['rows'];
		thisElm.find('table > tbody').html('');
		
		if (rows && rows.length > 0) {
			
			thisList = rows;
			
			$.each(rows, function(i,row) {
				// 미리보기타입 (2021.11.25 fileUtil 로 함수 이동)
				let typ = $.fileUtil.getPreviewType( row['fileNm'] );
				// 미리보기버튼
				let pbtn = $('<a href="javascript:void(0);" class="detail app-right" title="미리보기"><i class="fa fa-eye"></i></a>');
				pbtn.data('code', row['papeCd']);
				pbtn.data('sn'  , row['sn']);
				pbtn.data('type', typ);
				if ($.fileUtil.enablePreview(typ))
					pbtn.bind('click', thisCmp.doFilePreview);
				// 다운로드버튼
				let dbtn = $('<a href="javascript:void(0);" class="detail app-right" title="다운로드"><i class="fa fa-download"></i></a>');
				dbtn.data('code', row['papeCd']);
				dbtn.data('sn'  , row['sn']);
				// 파일다운로드 이벤트 바인딩
				dbtn.bind('click', thisCmp.doFileDownload);
				
				let check = row['prcsStusNm'];

				let td = $('<td class="app-l"></td>');
				td.append((row['mngrRegYn'] == 'Y' ? '[관리자]' : '')+row['papeNm']+'<br>');
				td.append('<small class="app-gray">'+row['fileNm']+'</small>');
				td.append(pbtn);
				td.append(dbtn);
				
				let tdcheck = $('<td class="app-c"></td>');
				tdcheck.append(check);
				
				let tr = $('<tr></tr>');
				tr.append('<td class="app-c">'+$.commUtil.nvl(row['regDate'])+'</td>');
				tr.append(td);
				tr.append(tdcheck);
				
				thisElm.find('table > tbody').append(tr);
			});
		}
		else {
			thisElm.find('table > tbody').append('<tr></tr>');
			thisElm.find('table > tbody > tr').append('<td colspan="3">검색된 결과가 없습니다.</td>');
		}
	};
	
	this.resetList = function( dom ) {
		thisElm.find('table > tbody').html('');
		thisElm.find('table > tbody').append('<tr></tr>');
		thisElm.find('table > tbody > tr').append('<td colspan="3">검색된 결과가 없습니다.</td>');
	};
	
	this.createList = function() {
		
		let table = $('<table></table>');
		table.append('<thead></thead>');
		table.append('<tbody></tbody>');
		table.find('thead').append('<tr></tr>');
		table.find('thead > tr').append('<th>등록날짜</th>');
		table.find('thead > tr').append('<th>제목 / 파일</th>');
		table.find('thead > tr').append('<th>제출여부</th>');
		return table;
	};
	
	// 서류양식 로드
	this.loadPape = function() {
		
		let rows = thisCmp.getLoadPapes();
		if (rows && 
			rows.length > 0) {
			thisElm.append(thisCmp.createPapeTable(rows));
		}
		// 양식파일 다운로드 이벤트 바인딩
		thisElm.find('.'+options.papeBtn).each(function() {
			$(this).bind('click', thisCmp.doPapeDownload);
		});
		// 파일업로드창 오픈 이벤트 바인딩
		thisElm.find('.'+options.fileBtn).each(function() {
			$(this).bind('click', thisCmp.doOpenUpload);
		});
	};
	
	// (수정시) 개별첨부파일 로드
	this.loadFile = function() {
		
		// 양식항목 LOOP
		thisElm.find('.'+options.fileCls).each(function(i) {
			let code  = $(this).data('code');
			let esntl = $(this).data('esntl');
			let limit = $(this).data('limit');
			let rows  = thisCmp.getLoadFiles(code);
			let file  = $(this);
			
			if (rows && rows.length > 0) {
				$(rows).each(function(i, data) {
					$.extend(data, {
						mode:     MODE.LIST, 
						fileYn:   'N',
						upPapeCd: options.params['upPapeCd'] 
					});
					// 등록파일에 추가
					thisData['append'][data['sn']] = data;
					// 파일세부항목 생성
					file.append(thisCmp.createFile(data));
				});
			}
		});
	};

	// [AJAX] 서류양식목록 조회
	this.getLoadPapes = function() {
		
		let params = {
			// 업무구분
			papeDtySeCd: options.params['papeDtySeCd'],
			// 신청구분
			aplySeCd:    options.params['aplySeCd'],
			// 서류그룹
			upPapeCd:    options.params['upPapeCd'],
			// 서류코드 (2021.11.09 추가)
			papeCd:      options.params['papeCd']
		};
		return $.ajaxUtil.ajaxDefault(options.papeUrl, params);
	};

	// [AJAX] 첨부파일 조회
	this.getLoadFiles = function( papeCd ) {
		return $.ajaxUtil.ajaxDefault(options.fileUrl, {
			// 업무구분
			dtySeCd:    options.params['papeDtySeCd'],
			// (수정시) 문서번호
			dcmtNo:     options.params['dcmtNo'],
			// (수정시) 세부문서번호
			dtlDcmtNo:  options.params['dtlDcmtNo'],
			// 서류코드
			papeCd:     papeCd
		});
	};

	// [AJAX] 신청파일목록 조회
	this.getLoadList = function( params ) {
		return $.ajaxUtil.ajaxDefault(options.listUrl, $.extend({
			// 업무구분
			dtySeCd:   options.params['papeDtySeCd'],
			// 문서번호
			dcmtNo:    options.params['dcmtNo'],
			// 세부문서번호
			dtlDcmtNo: options.params['dtlDcmtNo']
		},params));
	};

	// 서류양식 목록제목 생성
	this.createPapeTitle = function(title) {
		let div = $('<div class="'+options.titleCls+'"></div>');
		div.append('<div class="'+options.spaceCls+'"></div>');
		div.append('<h3 class="'+options.titCls+'">'+title+'</h3>');
		
		// 2021.12.27 ADD 제목우측 버튼이 있으면
		if (options.titleBtn)
			div.find('h3').append(options.titleBtn);
		
		return div;
	};

	// 서류양식 테이블 생성
	this.createPapeTable = function( rows ) {
		let div = $('<div class="'+options.tableCls+'"></div>');

		let grp = $('<colgroup></colgroup>');
		grp.append('<col style="width:45%" />');
		grp.append('<col style="" />');
		grp.append('<col style="width:200px" />');

		let head = $('<thead></thead>');
		head.append('<tr></tr>');
		head.find('tr').append('<th>접수서류</th>');
		head.find('tr').append('<th>제출서류</th>');
		head.find('tr').append('<th>제출여부</th>');
		head.find('tr > th:last').html('');

		let body = $('<tbody class="'+options.bodyCls+'"></tbody>');

		// 행만큼 선택박스 생성
		$.each(rows, function(i, row) {
			let tr = $('<tr></tr>');
			// 2022.01.26 서류양식 ROW 스타일시트 적용
			tr.addClass(options.rowsCls);
			// 2022.01.26 서류양식 ROW 서류코드값 맵핑
			tr.data('code', row['papeCd' ]);

			// 서류명칭
			tr.append('<td class="left"><span>'+$.commUtil.nvl(row['papeNm'])+'</span></td>');
			if (row['esntlYn'] == 'Y') {
				// 2022.12.20 필수항목 스타일 변경
				tr.find('td:last').addClass('app-must');
			}
			if (row['limtCnt'] > 1) {
				// 2022.12.20 최대가능갯수 표시
				tr.find('td:last').find('span:first').append(' (최대 '+row['limtCnt'] +'개)');
			}
			// 첨부파일영역
			let file = $('<div id="'+options.fileKey+row['papeCd']+'" class="'+options.fileCls+'"></div>');
			file.data('code' , row['papeCd' ]); // 서류코드
			file.data('esntl', row['esntlYn']); // 필수여부
			file.data('limit', row['limtCnt']); // 최대파일갯수
			file.data('title', row['papeNm' ]); // 서류명칭
			file.data('index', i);
			tr.append( $('<td class="left"></td>').append(file) );
			// 첨부파일 버튼
			let btn = $('<button type="button" class="'+options.fileBtn+' filebtn"></button>');
			btn.append('첨부파일');
			btn.data('code' , row['papeCd' ]); // 서류코드
			btn.data('esntl', row['esntlYn']); // 필수여부
			btn.data('limit', row['limtCnt']); // 최대파일갯수
			btn.data('index', i);
			tr.append($('<td></td>').append(btn));
			body.append(tr);
		});

		let tbl = $('<table></table>');
		tbl.append(grp);
		tbl.append(head);
		tbl.append(body);
		div.append(tbl);
		if (options.tableMsg) {
			div.append('<div>'+options.tableMsg+'</div>');
		}
		if (options.footerMsg) {
			div.append('<div>'+options.footerMsg+'</div>');
		}

		return div;
	};
	
	// 첨부파일 생성
	this.createFile = function(row) {
		
		let dom = $('<div class="'+options.itemCls+' app-both"></div>');
		dom.data('sn', row['sn']);
		
		let file = $('<a class="'+options.downBtn+'" href="javascript:void(0);"></a>');
		// 2022.01.07 파일명 마스킹 처리
		file.append($.fileUtil.getMaskName(row['fileNm'], options.maskLen));
		file.data('code', row['papeCd']);
		file.data('sn'  , row['sn']);
		// 파일다운로드 이벤트 바인딩
		file.bind('click', thisCmp.doFileDownload);
		dom.append(file);
		
		// 파일삭제 버튼
		let dbtn = $('<button type="button" class="'+options.deltBtn+' filedel">삭제</button>');
		dbtn.data('code', row['papeCd']);
		dbtn.data('sn'  , row['sn']);
		dbtn.bind('click', thisCmp.doFileRemove);
		dom.append(dbtn);

		// 미리보기타입 (2021.11.25 fileUtil 로 함수 이동)
		let typ = $.fileUtil.getPreviewType( row['fileNm'] );

		// 상세보기 버튼
		let pbtn = $('<button type="button" class="'+options.viewBtn+' fileview">보기</button>');
		pbtn.data('code', row['papeCd']);
		pbtn.data('sn'  , row['sn']);
		pbtn.data('type', typ);
		if ($.fileUtil.enablePreview(typ))
			pbtn.bind('click', thisCmp.doFilePreview);
		else
			pbtn.prop('disabled', true);
		dom.append(pbtn);
		
		return dom;
	};
	
	// 양식파일 다운로드
	this.doPapeDownload = function() {
		// 파일 다운로드 실행
		$.formUtil.submitForm(options.downloadPapeUrl, {
			params: {
				upPapeCd: $(this).data('upper'),
				papeCd:   $(this).data('code')
			}
		});
		return false;
	};
	
	// 첨부파일 미리보기
	this.doFilePreview = function() {
		let url  = false;
		let sn   = $(this).data('sn');
		let type = $(this).data('type');
		if      (type == 'PDF') url = options.previewDocUrl+sn+".do";
		else if (type == 'HWP') url = options.previewDocUrl+sn+".do";
		else if (type == 'TXT') url = options.previewImgUrl+".do?sn="+sn;
		else if (type == 'IMG') url = options.previewImgUrl+".do?sn="+sn;
		
		// 파일미리보기 (comm_utils.js 참고)
		$.fileUtil.preview({type: type, url: url});

		return false;
	};
	
	// 첨부파일 다운로드
	this.doFileDownload = function() {
		
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: {sn: btoa($(this).data('sn'))},
			url   : options.downloadUrl,
			log   : {
				params: {
					atchFileSn: $(this).data('sn'),
					progUrl: getRealUrl(options.downloadUrl)
				}
			}
		});
		return false;
	};
	
	// 첨부파일 전체 압축 다운로드 (문서번호기준)
	this.doFileDownloadZip = function(params) {
		// 파일다운로드 (comm_utils.js 참고)
		$.fileUtil.download({
			params: (params || options.params),
			url   : options.downloadZipUrl,
			log   : {
				params: {
					dtySeCd:   params['dtySeCd'  ],
					dcmtNo:    params['dcmtNo'   ],
					dtlDcmtNo: params['dtlDcmtNo'],
					progUrl:   getRealUrl(options.downloadZipUrl)
				}
			}
		});
		return false;
	};
	
	// 첨부파일 삭제 
	// (실제삭제되지 않음)
	// (저장시 삭제처리가 완료됨)
	this.doFileRemove = function() {
		
		let dom = $(this);
		let sn = $(this).data('sn');
		
		$.commMsg.confirm('정말 삭제하시겠습니까?', function() {
			let aprow = thisData['append'][sn];
			if (aprow) {
				thisData['remove'][sn] = aprow;
				delete thisData['append'][sn];
			}
			// 해당 요소 모두 삭제
			dom.closest('.'+options.itemCls).remove();
		});
		return false;
	};
	
	// 업로드한 파일정보 추가
	this.addFile = function(data) {

		$.extend(data, {
			fileYn:   'Y',
			mode:     MODE.INSERT, 
			upPapeCd: options.params['upPapeCd'] 
		}); 
		let item = thisElm.find('[id="'+options.fileKey+data['papeCd']+'"]');
		// 이전 파일번호가 있는 경우 (수정기능)
		if (!$.commUtil.empty(data['orgSn'])) {
			// 이전 파일 삭제등록
			let orgSn = data['orgSn'];
			let aprow = thisData['update'][orgSn];
			if (aprow) {
				thisData['remove'][orgSn] = aprow;
				delete thisData['update'][orgSn];
			}
			// 수정파일 추가
			thisData['update'][data['sn']] = data;
			// 이전파일 객체삭제
			item.find('.'+options.itemCls).each(function() {
				if ($(this).data('sn') == orgSn) {
					$(this).remove();
					return false;
				}
			});
			// 상태감춤
			item.closest('tr').find('.'+options.stimCls).hide();
		}
		// 등록인 경우
		else {
			// 등록파일 추가
			thisData['append'][data['sn']] = data;
		}
		// 파일 객체생성
		let dom = thisCmp.createFile(data);
		// 파일객체 추가 
		item.append(dom);
	};
	
	// 첨부파일 저장 VALIDATION
	// 2022.01.18 제출시에만 필수 체크 처리
    //--------------------------------------------------------//
	this.doValidate = function( act ) {
		
		// 파일 필수검증여부
		let needYn = true;
		if (act && act != MODE.SUBMIT)
			needYn = false;

		// 필수체크, 최대갯수 체크
		let check = true;
		
		// 서류항목만큼 LOOP
		thisElm.find('.'+options.fileCls).each(function(i) {
			let esntl = $(this).data('esntl'); // 필수여부
			let limit = $(this).data('limit'); // 갯수제한
			let title = $(this).data('title'); // 서류명칭
			let item  = $(this).find('.'+options.itemCls);
			
			if (options.title)
				title = options.title+' - '+title;
			
			title = '['+title+']';
			
			// 파일 필수검증여부가 true인 경우
			// 파일 필수체크가 'Y'인 경우
			if (needYn && esntl == 'Y') {
				if (item.length == 0) {
					$.commMsg.alert(title+ ' 항목은 반드시 등록하셔야 합니다.');
					check = false;
					return false;
				}
			}
			// 파일갯수 체크
			if (item.length > limit) {
				$.commMsg.alert(title+ ' 항목은 '+limit+'개까지 등록 가능합니다.');
				check = false;
				return false;
			}
		});
		return check;
	};

	// 2022.01.24 서류코드 기준 첨부파일 업로드 여부 체크
    //--------------------------------------------------------//
	this.doExistFile = function( papeCd ) {
		
		// 업로드여부 체크
		let upload = false;
		// 서류항목만큼 LOOP
		thisElm.find('.'+options.fileCls).each(function(i) {
			let code = $(this).data('code'); // 서류코드
			let item = $(this).find('.'+options.itemCls);
			
			// 해당 서류코드의 파일정보가 있으면
			if (code == papeCd && item.length > 0) {
				upload = true;
				return false;
			}
		});
		return upload;
	};

	// 첨부파일 저장데이터 가져오기
	this.getSaveData = function() {
		
		let data = {};
		data['saveFiles'] = $.map(thisData['append'], function(v) {
    		return [v];
		});
		data['removeFiles'] = $.map(thisData['remove'], function(v) {
    		return [v];
		});
		return data;
	};
	
	// [팝업] 첨부파일업로드 팝업열기
	this.doOpenUpload = function() {
		
		// 수정인 경우엔 SKIP
		if ($.commUtil.empty($(this).data('sn'))) {
			// 2021.10.30 추가여부 체크
			// 서류코드
			let papeCd = $(this).data('code');
			// 제한갯수
			let limit  = $(this).data('limit');
			// 파일갯수
			let count = thisElm.find('[id="'+options.fileKey+papeCd+'"]')
							.find('.'+options.itemCls)
							.length;
			
			if (count == limit) {
				let msg = '해당 항목은 '+limit+'개까지 등록 가능합니다.'
				        + '수정하시려면 삭제 후에 등록해 주세요.'; 
				$.commMsg.alert(msg);
				return false;
			}
		}
		thisDlg = thisCmp.openModal({
			papeDtySeCd: options.params['papeDtySeCd'],
			aplySeCd:    options.params['aplySeCd'],
			papeCd:      $(this).data('code'),
			esntlYn:     $(this).data('esntl'),
			sn:          $(this).data('sn')
		});
		return false;
	};

	// [팝업] 첨부파일 업로드 팝업 오픈
	this.openModal = function( params ) {

		let ul = $('<ul></ul>');
		ul.append('<li class="on"></li>');
		ul.find('li').append('<h3>파일업로드</h3>');
		ul.find('li').append($('<p></p>').append(thisCmp.createForm(params)));

		let div = $('<div class="filePop"></div>');
		div.append('<div class="cover"></div>');
		div.append('<div class="filePop-wrap"></div>');
		div.find('.filePop-wrap').append('<button type="button" class="close"></button>');
		div.find('.filePop-wrap').append('<div class="filePop-inner app-file-popup"></div>');
		div.find('.filePop-inner').append(ul);
		div.addClass("on");
		div.appendTo('body');
		div.find(".close").on("click",function(){
			$(this).parents(".filePop").remove();
		})
		// 오픈시 파일선택창이 열린다.
		div.find(".upload-hidden").trigger('click');
		
		return div;
	};
		
	// [팝업] 첨부파일 업로드 생성
	this.createForm = function( params ) {
		
		let ubtn = $('<a href="javascript:void(0);" class="uploadbtn '+options.upldBtn+'">업로드</a>');
		ubtn.bind('click', this.doFileUpload);
		
		/* form은 반드시 onsubmit="return false;" 속성이 있어야 
		 * ajaxForm 실행시 두번씩 실행되지 않는다.
		 */
		let form = $('<form name="uploadForm" class="usr-form-group" method="post" onsubmit="return false;"></form>');
		form.append('<div class="inputWrap filebox"></div>');
		form.find('.filebox').append('<input name="fileName" class="upload-name" value="파일선택" readonly />');
		form.find('.filebox').append('<label for="ex_filename1">파일첨부</label>');
		form.find('.filebox').append('<input id="ex_filename1" type="file" name="upfile" class="upload-hidden">');
		form.find('.filebox').append(ubtn);
		form.append('<input type="hidden" name="filePath" value=""/>');
		form.append('<input type="hidden" name="fileNo"   value="'+$.commUtil.nvlTrim(params['sn'         ])+'"/>');
		form.append('<input type="hidden" name="fileType" value="'+$.commUtil.nvlTrim(params['papeDtySeCd'])+'"/>');
		form.append('<input type="hidden" name="docuCd"   value="'+$.commUtil.nvlTrim(params['papeCd'     ])+'"/>');
		form.append('<input type="hidden" name="needYn"   value="'+$.commUtil.nvlTrim(params['esntlYn'    ])+'"/>');
		form.append('<input type="hidden" name="fileYn"   value="Y"/>');
		
		if (form.find(".filebox").length){
			form.find(".filebox .upload-hidden").on("change", function(){ //값이 변경되면
				if (window.FileReader){ //modern browser
					var filename = $(this)[0].files[0].name;
				}else { //old IE
					var filename = $(this).val().split("/").pop().split("\\").pop(); //파일명만 추출
				} //추출한 파일명 삽입
				$(this).siblings(".upload-name").val(filename);
			});
		};
		return form;
	};

	// [팝업] 첨부파일 업로드 VALIDATION
	this.doUploadValidate = function( formElm ) {
		
		let fobj = formElm.find('input[name="upfile"]');
		if (fobj.val() == '') {
			$.commMsg.alert('파일을 선택해 주세요.');
			return false;
		}
		let fname = formElm.find('input[name="fileName"]').val();
		// 파일명 길이체크
		if ($.fileUtil.checkMaxLength(fname, options.maxLengthName, true) == false) {
			return false;
		}
		// 확장자 체크
		if ($.fileUtil.checkExtension(fobj, options.extensions, true) == false) {
			return false;
		}
		// 용량 체크
		if ($.fileUtil.checkMaxbytes(fobj, options.maxBytes, true) == false) {
			return false;
		}
		return true;
	};
	
	// [팝업] 첨부파일 업로드 처리
	this.doFileUpload = function() {
		let formElm = $('form[name="uploadForm"]');
		// 첨부파일 업로드 VALIDATION
		if (!thisCmp.doUploadValidate(formElm))
			return false;
		// 폼을 AJAX로 저장처리
		formElm.ajaxForm({
			url: options.uploadUrl,
			enctype : 'multipart/form-data',
			// 에러시 처리로직
			error: $.ajaxUtil.error,
			// 저장후 처리로직
			success: function(ret) {
				if (ret && ret['File']) {
					thisCmp.addFile(ret['File']);
				}
				thisDlg.find('.close').trigger('click');
			}
		}).submit();
	};

	this.init();

	return this;
};
