/**
******************************************************************************************
*** 파일명 : listCode.js
*** 설명글 : 공통코드관리 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.02    LSH
*** 1.0         2021.11.02    LSH   디자인적용 및 개발 수정
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_GRID   = $('#appGrid'         ); // 그리드 객체
    var P_SFORM  = $('#searchForm'      ); // 검색폼 객체
    var P_RFORM  = $('#registForm'      ); // 등록폼 객체
	let P_HEADER  = $('#registTitle'    ); // 제목객체
	let P_TITLE   = '공통코드'           ; // 업무제목

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
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 그리드 페이징처리 여부
        pagination:true,
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 한 페이지 출력수
        pageSize: 30,
        // 칼럼정의
        columns: [[
            {field:'upCdNm'       ,width:150,halign:'center',align:  'left',title:'상위코드', formatter:$.commFormat.rootNm},
            {field:'cdId'         ,width:150,halign:'center',align:  'left',title:'코드ID'},
            {field:'useYn'        ,width:100,halign:'center',align:'center',title:'사용여부', formatter:$.commFormat.useyn},
            {field:'cdNm'         ,width:250,halign:'center',align:  'left',title:'코드명'},
            {field:'cdCn'         ,width:300,halign:'center',align:  'left',title:'코드내용'},
            {field:'vldBgngYmd'   ,width:120,halign:'center',align:'center',title:'유효시작일자'},
            {field:'vldEndYmd'    ,width:120,halign:'center',align:'center',title:'유효종료일자'},
            {field:'cdOrdr'       ,width:100,halign:'center',align: 'right',title:'코드순서'},
            {field:'rgtrNo'       ,width:120,halign:'center',align:'center',title:'등록자번호'},
            {field:'regDate'      ,width:120,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfrNo'       ,width:120,halign:'center',align:'center',title:'수정자번호'},
            {field:'mdfDate'      ,width:120,halign:'center',align:'center',title:'수정일자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
    let S_CODE = false; // 검색용 상위코드
    let R_CODE = false; // 등록용 상위코드

    // 검색용 상위코드 콤보박스 (EasyUI 콤보박스)
	S_CODE = $('#srchType').appComboBox({
		params: {upCdId: ROOT_CODE['code']},
		init:   {code:'', text:'::: 상위코드 전체 :::'},
		loadFilter : function(data) {
			data.unshift(ROOT_CODE);
			return data;
		} 
	});
    // 등록용 상위코드 콤보박스 (EasyUI 콤보박스)
	R_CODE = $('#upCdId').appComboBox({
		params: {upCdId: ROOT_CODE['code']},
		init:   ROOT_CODE
	});
    // 검색용 사용여부 라디오박스	
	$('#appSrchUseYn').appSelectBox({
		form: 'radio',
		name: 'srchUseYn',
		type: 'static',
		rows: STORE['USE_YN'],
		init: RADIO.INIT_ALL
	});
    // 등록용 사용여부 라디오박스	
	$('#appUseYn').appSelectBox({
		form: 'radio',
		name: 'useYn',
		type: 'static',
		rows: STORE['USE_YN']
	});

    //========================================================//
    // 등록폼 VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            cdId      : {required: true},
            upCdId    : {required: function() {
							if (P_RFORM.find('[name="mode"]').val() == MODE.INSERT)
								return true;
							return false;
						}},
            useYn     : {required: true},
            cdNm      : {required: true},
            vldBgngYmd: {yyyymmdd: true},
            vldEndYmd : {yyyymmdd: true}
        },
        // 검증메세지 정의
        messages: {
            cdId      : {required: '코드ID는 필수 입력 사항입니다.'},
            upCdId    : {required: '상위코드ID는 필수 입력 사항입니다.'},
            useYn     : {required: '사용여부는 필수 선택 사항입니다.'},
            cdNm      : {required: '코드명는 필수 입력 사항입니다.'},
            vldBgngYmd: {yyyymmdd: '유효시작일자를 yyyyMMdd 형식에 맞게 입력하세요.'},
            vldEndYmd : {yyyymmdd: '유효종료일자를 yyyyMMdd 형식에 맞게 입력하세요.'}
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });
	
    // 코드관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/sys/code/getListCode.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
        
        return false;
    }
    
    // 코드관리 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 등록폼 초기화
    	doRegist();
        // 검색폼 입력데이터 초기화
        P_SFORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // 코드관리 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 코드관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 제목변경
		P_HEADER.html(P_TITLE+'등록');
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 상위코드 명칭 리셋
		$('#upCdNm').html('');
        // 상위코드콤보 표시
		$('#upCdId').show();
        // 코드입력값 입력활성화
		$('#cdId').prop('readonly', false);
		// 등록기본값 정의
        $.formUtil.toForm({
			mode   : MODE.INSERT,// 등록모드
			useYn  : 'Y'         // 사용여부(Y)
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();
        // 콤보값 재로딩
		S_CODE.load();
		R_CODE.load();
        
        return false;
    }

    // 코드관리 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {
        var params = {
            cdId   : row['cdId'  ],
            upCdId : row['upCdId']
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/sys/code/getCode.do'), 
			params,
            function(result) {
                var data = result.Data;
                if (data) {
					// 수정모드 정의
					data['mode'     ] = MODE.UPDATE;
					data['orgUpCdId'] = data['upCdId'];
					if (data['upCdId'] == ROOT_CODE['code'])
						data['upCdNm'] =  ROOT_CODE['text'];

					let cdText = '[' + data['cdId'  ] + '] - ' + data['cdNm'  ];
					let upText = '[' + data['upCdId'] + '] - ' + data['upCdNm'];
                    // 제목변경
					P_HEADER.html(cdText +' 수정');
                    // 폼데이터 셋팅
                    $.formUtil.toForm(data, P_RFORM);
					// 상위코드 명칭 표시
					$('#upCdNm').html(upText);
                    // 상위코드 콤보 숨김
					$('#upCdId').hide();
                    // 코드입력값 입력비활성화
					$('#cdId').prop('readonly', true);
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                }
            }
        );
        return false;
    }

    // 코드관리 저장하기
    //--------------------------------------------------------//
    function doSave() {

        // 등록폼의 VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

        $.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/code/saveCode.do'), 
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '성공적으로 저장되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
        });
        return false;
    }
       
    // 코드관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject( 
            P_RFORM, ['cdId','upCdId']);
        
        if ($.commUtil.empty(params['cdId'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }
        
    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
	        $.formUtil.toForm({mode:MODE.REMOVE}, P_RFORM);
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/sys/code/saveCode.do'), 
                // 오류시 처리로직
                error: $.ajaxUtil.error,
                // 저장후 처리로직
                success: function(ret) {
					$.commMsg.success(ret, '성공적으로 삭제되었습니다.', function() {
                        doRegist();
                        doSearch();
					});
                }
            }).submit();
    	});
        return false;
    }

    // 코드관리 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/sys/code/downCodeExcel.do'), 
            {formId : "searchForm"}
        );
        return false;
    }
	
    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
    // 리셋버튼 클릭시 이벤트처리
    $('#btnReset' ).bind('click', doReset);
    // 등록버튼 클릭시 이벤트처리
    $('#btnRegist').bind('click', doRegist);
    // 엑셀버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);

    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave').bind('click'  , doSave);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);
    // 취소버튼 클릭시 이벤트처리
    $('#btnUndo').bind('click'  , doUndo);
    
    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'), $('#btnSearch'));

    // 등록폼 초기화
    doRegist();
    // 검색처리
    doSearch();

    //========================================================//
    // DRM 샘플로직 추가
    //--------------------------------------------------------//
	// 버튼추가
	let btnDRM = $('<a href="javascript:void(0);" id="btnDRM" style="color:#F2F6FA;">.</a>');
    // DRM샘플버튼 클릭시 이벤트처리
    btnDRM.bind('click', function() {
		let pop = $('<div></div>').appPopup({
			appendBody: true,
			title:   'DRM 샘플 테스트',
			popupCls:'layerPop form type3 listLock',
			message: function() {
				let dom = $('<form name="smplForm" style=""></form>');
				let btn = $('<div class="btnWrap type2" style="margin-bottom:10px"></div>');
				btn.append('<a href="javascript:void(0);" class="app-enc-btn" style="line-height:18px">암호화</a>');
				btn.append('<a href="javascript:void(0);" class="app-dec-btn" style="line-height:18px">복호화</a>');
				btn.append('<a href="javascript:void(0);" class="app-pre-btn" style="line-height:18px">파일보기</a>');
				dom.append(btn);

				let txt = $('<textarea id="smplRslt" name="smplRslt" class="w100" style="height:200px"></textarea>');
				let div = $('<div class="inputGroup"><div class="inputWrap one"></div></div>');
				div.find('.inputWrap').append(txt);
				dom.append(div);

				// 암호화 버튼 클릭시
				dom.find('a.app-enc-btn').bind('click', function() {
					// 저장처리
					$.ajaxUtil.ajaxSave(
			            getUrl('/sys/code/execSampleDRM.do'),
						JSON.stringify({mode:'ENCRYPT'}),
						function(ret) {
							let s = [
								'결과코드 : ' + ret['Code'],
								'결과메세지 : ' + ret['Message'],
								'오류번호(errorNum) : ' + ret['ErrorNum'],
								'오류메세지(errorStr) : ' + ret['ErrorStr']
							].join('\n');
							dom.find('[name="smplRslt"]').val(s);
						}
					);
					return false;
				});

				// 복호화 버튼 클릭시
				dom.find('a.app-dec-btn').bind('click', function() {
					// 저장처리
					$.ajaxUtil.ajaxSave(
			            getUrl('/sys/code/execSampleDRM.do'),
						JSON.stringify({mode:'DECRYPT'}),
						function(ret) {
							let s = [
								'결과코드 : ' + ret['Code'],
								'결과메세지 : ' + ret['Message'],
								'오류번호(errorNum) : ' + ret['ErrorNum'],
								'오류메세지(errorStr) : ' + ret['ErrorStr']
							].join('\n');
							dom.find('[name="smplRslt"]').val(s);
						}
					);
					return false;
				});

				// 파일보기 버튼 클릭시
				dom.find('a.app-pre-btn').bind('click', function() {
					// 파일미리보기 (comm_utils.js 참고)
					$.fileUtil.preview({type: 'IMG', url: getUrl('/sys/code/linkSampleDRM.do?time='+ new Date())});
					pop.close();
					return false;
				});
				return dom;
			}
		}).open();
		return false;
	});
	// TODO. DRM 샘플 버튼제외시 아래부분을 주석처리할것.
	$('div.listWrap').eq(0).append(btnDRM);
});
