/**
******************************************************************************************
*** 파일명 : listPapeCode.js
*** 설명글 : 서류코드관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.07    LSH
*** 1.0         2021.11.05    LSH  디자인적용
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID    = $('#appGrid'        ); // 목록 GRID
	let P_FORM    = $('#searchForm'     ); // 검색폼
	let P_RFORM   = $('#registForm'     ); // 등록폼
	let P_HEADER  = $('#registTitle'    ); // 제목객체
	let P_TITLE   = '서류코드'           ; // 업무제목

    var P_FILE_VIEW = $('#attachFile');  // 첨부파일 조회목록영역

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.treegrid({
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
        // 2022.01.09 LSH 데이터 건이 많으면 rownumbers 로 인해 느려져 주석처리함
        // 그리드 행번호 표시여부
        //rownumbers: true,
        // 트리의 ID 필드
		idField: 'papeCd',
        // 트리의 트리표시 필드
		treeField: 'papeNm',
        // 칼럼정의
        columns: [[
            {field:'papeNm'     ,hidden:false  ,width:400,halign:'center',align:  'left',title:'서류명'},
            {field:'papeCd'     ,hidden:false  ,width:100,halign:'center',align:  'left',title:'서류코드'},
            {field:'upPapeCd'   ,hidden:false  ,width:100,halign:'center',align:  'left',title:'상위코드', formatter:$.commFormat.rootNm},
            //{field:'fileNm'   ,hidden:false    ,width:200,halign:'center',align:  'left',title:'파일명'},
            //{field:'filePath' ,hidden:false    ,width:200,halign:'center',align:  'left',title:'파일경로'},
            {field:'limtCnt'    ,hidden:false  ,width:100,halign:'center',align: 'right',title:'제한수', formatter:$.commFormat.number},
            //{field:'downCnt'  ,hidden:false    ,width:100,halign:'center',align: 'right',title:'다운수', formatter:$.commFormat.number},
            {field:'cdOrdr'     ,hidden:false  ,width:100,halign:'center',align: 'right',title:'코드순서', formatter:$.commFormat.number},
            {field:'downTrgtYn' ,hidden:false  ,width:100,halign:'center',align:'center',title:'다운로드대상여부', formatter:$.commFormat.downTrgtYn},
            {field:'useYn'      ,hidden:false  ,width:100,halign:'center',align:'center',title:'사용여부', formatter:$.commFormat.useyn},
            {field:'rgtrNm'     ,hidden:true   ,width:100,halign:'center',align:'center',title:'등록자'},
            {field:'regDate'    ,hidden:true   ,width:120,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfrNm'     ,hidden:true   ,width:100,halign:'center',align:'center',title:'수정자'},
            {field:'mdfDate'    ,hidden:false  ,width:120,halign:'center',align:'center',title:'수정일자'}
        ]],
        // 행선택시 상세조회
        onSelect: doUpdate,
		onLoadSuccess: function() {
			P_GRID.treegrid('expandAll');
		}
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
    // 상위코드 EasyUI Combo Tree
	let P_UPPER = $('#upPapeCd').combotreegrid({
		url: getUrl('/adm/file/getListPapeCodeTree.do'),
        idField:'papeCd',
        treeField:'papeNm',
        panelWidth:500,
        columns:[[
            {field:'papeNm',title:'코드명칭',width:400},
            {field:'papeCd',title:'서류코드',width:100}
        ]],
		onLoadSuccess: function() {
			$('#upPapeCd').combotreegrid('grid').treegrid('expandAll');
		}
	});

    // 사용여부 라디오박스
	$('#appUseYn').appSelectBox({
		form: 'radio',
		name: 'useYn',
		type: 'static',
		rows: STORE['USE_YN']
	});
    // 다운로드대상여부 라디오박스
    $('#appDownTrgtYn').appSelectBox({
        form: 'radio',
        name: 'downTrgtYn',
        type: 'static',
        rows: STORE['DOWNTRGT_YN']
    });
    //========================================================//
    // FORM VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            papeCd    : {required: true},
            upPapeCd  : {required: function() {
							if (P_RFORM.find('[name="mode"]').val() == MODE.INSERT)
								return true;
							return false;
						}},
            papeNm    : {required: true},
            useYn     : {required: true}
        },
        // 검증메세지 정의
        messages: {
            papeCd    : {required: '서류코드는 필수 입력 사항입니다.'},
            upPapeCd  : {required: '상위코드는 필수 선택 사항입니다.'},
            papeNm    : {required: '서류명은 필수 입력 사항입니다.'},
            useYn     : {required: '사용여부는 필수 선택 사항입니다.'}
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 서류코드관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.treegrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.treegrid('options')['url'] = getUrl('/adm/file/getListPapeCodeTree.do');
        // 검색폼 그리드 검색
        P_GRID.treegrid('load', obj);

        return false;
    }

    // 서류코드관리 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 등록폼 초기화
    	doRegist();
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');
        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // 서류코드관리 등록취소
    //--------------------------------------------------------//
    function doUndo() {
        doRegist();
        return false;
    }

    // 서류코드관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
        // 제목변경
		P_HEADER.html(P_TITLE+'등록');
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 상위코드 명칭 리셋
		$('#orgUpPapeNm').html('');
        // 상위코드콤보 표시
		$('#upPapeArea').show();
		// 상위코드콤보 리로드
		P_UPPER.combotreegrid('grid').treegrid('reload');
        // 코드입력값 입력활성화
		$('#papeCd').prop('readonly', false);
		// 등록기본값 정의
        $.formUtil.toForm({
			mode   : MODE.INSERT,// 등록모드
			useYn  : 'Y'         // 사용여부(Y)
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();
        // 첨부파일 부분 제거
        P_FILE_VIEW.html("");
        return false;
    }

    // 서류코드관리 수정하기
    //--------------------------------------------------------//
    function doUpdate(row) {
        var params = {
            papeCd    : row['papeCd'  ],
            upPapeCd  : row['upPapeCd']
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/file/getPapeCode.do'),
			params,
            function(result) {
                var data = result.Data;
                if (data) {
                    // 초기화 (등록모드 설정)
                    doUndo();

					// 수정모드 정의
					data['mode'        ] = MODE.UPDATE;
					data['orgUpPapeCd' ] = data['upPapeCd'];
					if (data['upPapeCd'] == ROOT_CODE['code'])
						data['upPapeNm'] =  ROOT_CODE['text'];
                    // 제목변경
					P_HEADER.html(P_TITLE+'수정');
                    // 폼데이터 셋팅
                    $.formUtil.toForm(data, P_RFORM);
                    //다운로드 대상여부
                    console.log("data : ", data);
					// 상위코드 명칭 표시
					$('#orgUpPapeNm').html('['+data['upPapeCd']+'] - '+data['upPapeNm']);
                    // 상위코드 콤보 숨김
					$('#upPapeArea').hide();
                    // 코드입력값 입력비활성화
					$('#papeCd').prop('readonly', true);
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                    // 첨부된 파일 표시
                    P_FILE_VIEW.html('');
                    if(data.downFileNm){
                        var downFileName = data.downFileNm;
                        P_FILE_VIEW.append('<a href="javascript:void(0);" data-papecd="'+data.papeCd+'" data-uppapecd="'+data.upPapeCd+'">'+downFileName+'</a>');
                    }
                    // '관련 서류 등록' 부분' 출력 설정s
                    if(data['state'] == 'closed'){
                        $(".layerAttachFile").hide();
                    }
                    else{
                        $(".layerAttachFile").show();
                    }
                }
            }
        );
        return false;
    }

    // 서류코드관리 저장하기
    //--------------------------------------------------------//
    function doSave() {

        // FORM VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

        // 파일 검증확인
        let check = true;
        // 첨부파일갯수만큼 LOOP
        P_RFORM.find('input[name="upfile"]').each(function(i) {
            // 파일객체의 선택값
            let nfile = $(this).val();
            // 이전파일의 저장값
            let ofile = P_RFORM.find('input[name="fileName"]').eq(i).val();
            // 파일의 필수여부
            let need  = P_RFORM.find('input[name="needYn"  ]').eq(i).val();
			// 파일 객체
			let fobj  = $(this);

            // 파일업로드여부 설정
            if (nfile === '')
                P_RFORM.find('input[name="fileYn"]').eq(i).val('N');
            else
                P_RFORM.find('input[name="fileYn"]').eq(i).val('Y');

            //이미 false 체크된 경우 SKIP
            if (check == false)
                return false;
            // 파일정보가 없는 경우
            if (nfile == '' && ofile == '') {
                //필수 체크가 아닌 경우 SKIP
                if (need != 'Y')
                    return true;
                $.commMsg.alert('파일을 선택해 주세요.');
                check = false;
                return false;
            }
			// 파일이 있는 경우
			if (!$.commUtil.empty(nfile)) {
				// 파일명 길이체크
				if (!$.fileUtil.checkMaxLength(ofile, NUMBER.FILE_MAXLENGTH, true)) {
					check = false;
					return false;
				}
				// 확장자 체크
				if (!$.fileUtil.checkExtension(fobj, COMMONS.FILE_EXTENSIONS, true)) {
					check = false;
					return false;
				}
				// 용량 체크
				if (!$.fileUtil.checkMaxbytes(fobj, NUMBER.FILE_MAXBYTES, true)) {
					check = false;
					return false;
				}
			}
        });
        // 파일검증 실패시
        if (check == false)
            return false;

        $.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/file/savePapeCode.do'),
                // 파일업로드 타입
                enctype : 'multipart/form-data',
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

    // 서류코드관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(
            P_RFORM, ['papeCd','upPapeCd']);

        if ($.commUtil.empty(params['papeCd'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }

    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
	        $.formUtil.toForm({mode:MODE.REMOVE}, P_RFORM);
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/file/savePapeCode.do'),
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

    // 첨부파일 다운로드하기
    //--------------------------------------------------------//
    function doDownload() {

        $.formUtil.submitForm(
            getUrl('/adm/file/downloadPapeCode.do'),
            {params: {
                papeCd   : btoa(P_FILE_VIEW.find('a').data('papecd')),
                upPapeCd : btoa(P_FILE_VIEW.find('a').data('uppapecd'))
             }
            }
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

    // 저장버튼 클릭시 이벤트 처리
    $('#btnSave'  ).bind('click', doSave);
    // 삭제버튼 클릭시 이벤트처리
    $('#btnRemove').bind('click', doRemove);
    // 취소버튼 클릭시 이벤트처리
    $('#btnUndo'  ).bind('click', doUndo);

    // 검색어 입력 엔터 이벤트 처리
    bindEnter($('#srchText'), $('#btnSearch'));

    // 첨부파일 파일선택 변경시 텍스트박스에 파일명을 표시해주는 이벤트
    $('.file_wrap').on('change','.input_file', function() {
        // 파일명만 추출한다.
        var fname = $(this).val().split("\\").pop();
        // 텍스트박스에 셋팅한다.
        $(this).closest('.file_wrap').find('.input_text').val(fname);
    });

    // 첨부파일 다운로드 이벤트
    P_FILE_VIEW.on("click", 'a', doDownload);

    //========================================================//
    // 최초 수행 내용
    //--------------------------------------------------------//

    // 등록폼 초기화
    doRegist();
    // 검색처리
    doSearch();
});
