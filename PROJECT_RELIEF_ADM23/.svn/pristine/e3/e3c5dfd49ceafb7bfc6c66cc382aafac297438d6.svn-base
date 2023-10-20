/**
******************************************************************************************
*** 파일명 : listAplyFile.js
*** 설명글 : 신청첨부파일 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    김주호
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
	let P_TITLE   = '신청첨부파일';        // 업무제목

    let P_DTYSEOBJ   = $('#srchDtySeCd');       // 업무구분
    let P_UPPAPEOBJ  = $('#srchUpPapeCd');      // 상위서류분류
    let P_PAPEOBJ    = $('#srchPapeCd');        // 하위서류분류
    let P_BIZAREAOBJ = $('#srchBizAreaCd');     // (구제급여) 피해지역 COMBO

    let P_ISINIT     = true;                    // 최초 화면 로딩 여부
    let P_DTYSECD    = CODE.PAPE_DTY_CD.RELIEF; // 업무구분 값(기본값 : 구제급여)
    let P_UPAPECD    = false;                   // 상위 서류분류 값
    let P_ISDOWNBTN  = true;                    // '다운로드' 버튼 표시 여부

    //========================================================//
    // 목록 GRID 정의 (EasyUI datagrid)
    //--------------------------------------------------------//
    P_GRID.datagrid({
        //fit: true,
        fitColumns: true,
        // 그리드 결과데이터 타입
        contentType: 'application/json',
        // 그리드 결과데이터 타입
        dataType: 'json',
        // 그리드 데이터연동 방식
        method: 'POST',
        // 그리드 단일행만 선택여부
        singleSelect: false,
        // 그리드 페이징처리 여부
        pagination:true,
        // 그리드 행번호 표시여부
        rownumbers:true,
        // 한 페이지 출력수
        pageSize: 30,
        // 칼럼정의
        columns: [[]],
        // 행선택시 상세조회
        onSelect: doSelect
    });

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	//$('#appTermBox').appTermBox({
	//	label:'기간',
	//	stName:'srchStdt',
	//	enName:'srchEndt'
	//});
	//$('#appCheckBox').appSelectBox({
	//	label:   '구분',
	//	form:    'checkbox',
	//	name:    'checkList',
	//	params:  {upCdId: CODE.APLYSE}
	//});

    //========================================================//
    // FORM VALIDATION RULE 정의
    //--------------------------------------------------------//
    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            sn        : {required: true},
            dtySeCd   : {required: true},
            dcmtNo    : {required: true},
            dtlDcmtNo : {required: true},
            papeCd    : {required: true},
            filePath  : {required: true},
            strgFileNm: {required: true},
            fileNm    : {required: true},
            fileSz    : {required: true},
            prcsStusCd: {required: true},
            prcsCn    : {required: true},
            delYn     : {required: true},
            rgtrNo    : {required: true},
            regYmd    : {required: true},
            mdfrNo    : {required: true},
            mdfcnYmd  : {required: true}
        },
        // 검증메세지 정의
        messages: {
            sn        : {required: '일련번호는 필수 입력 사항입니다.'},
            dtySeCd   : {required: '업무구분코드는 필수 입력 사항입니다.'},
            dcmtNo    : {required: '문서번호는 필수 입력 사항입니다.'},
            dtlDcmtNo : {required: '세부문서번호는 필수 입력 사항입니다.'},
            papeCd    : {required: '서류코드는 필수 입력 사항입니다.'},
            filePath  : {required: '파일경로는 필수 입력 사항입니다.'},
            strgFileNm: {required: '저장파일명는 필수 입력 사항입니다.'},
            fileNm    : {required: '파일명는 필수 입력 사항입니다.'},
            fileSz    : {required: '파일크기는 필수 입력 사항입니다.'},
            prcsStusCd: {required: '처리상태코드는 필수 입력 사항입니다.'},
            prcsCn    : {required: '처리내용는 필수 입력 사항입니다.'},
            delYn     : {required: '삭제여부는 필수 입력 사항입니다.'},
            rgtrNo    : {required: '등록자번호는 필수 입력 사항입니다.'},
            regYmd    : {required: '등록일자는 필수 입력 사항입니다.'},
            mdfrNo    : {required: '수정자번호는 필수 입력 사항입니다.'},
            mdfcnYmd  : {required: '수정일자는 필수 입력 사항입니다.'}
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//
    // 접수일자 조건 구성
    $('#appRcptTermBox').appTermBox({
        label:'접수일자',
        stName:'srchRcptStdt',
        enName:'srchRcptEndt'
    });
    // 업무구분 콤보 구성
    P_DTYSEOBJ.appComboBox({
        params: {upCdId: 'CT034'},
        // 콤보 값변경시 검색 처리
        change:   doChangeDtySeCombo,
        // 콤보 값로딩후 실행 처리
        callback: doChangeDtySeCombo
    });
    // 업무구분 콤보 change 이벤트
    function doChangeDtySeCombo(){
        // 선택된 업무구분 코드값 변수 설정
        P_DTYSECD = P_DTYSEOBJ.val();

        // 업무구분에 따른 검색 조건 변경
        var vwTypeObjs = P_FORM.find(".vwType");
        vwTypeObjs.hide();
        vwTypeObjs.find(":input").val('');
        P_FORM.find(".vwType.vwType"+P_DTYSECD).show();

        // '서류분류' 콤보 구성
        P_UPPAPEOBJ.appComboBox({
            init  : COMBO.INIT_ALL,
            url   : getUrl('/adm/file/getPapeMngUpPapeList.do'),
            params: {srchPapeDtySeCd: P_DTYSECD},
            // 콤보 값변경시 검색 처리
            change:   doChangeUpPapeCombo,
            // 콤보 값로딩후 실행 처리
            callback: doChangeUpPapeCombo
        });

        if(P_ISINIT){
            // 로딩시 초기화 및 검색실행
            doSearch();
            // 최초 화면 로딩시만 한번 수행되게 false 처리
            P_ISINIT = false;
        }
    }
    // 서류분류 콤보 change 이벤트
    function doChangeUpPapeCombo(){
        // 상위서류분류 값 변수 설정
        P_UPAPECD = P_UPPAPEOBJ.val();

        // 하위 서류분류 콤보 구성
        P_PAPEOBJ.appComboBox({
            init  : COMBO.INIT_ALL,
            url   : getUrl('/adm/file/listPapeMngPapeCodeByUpPape.do'),
            params: {upPapeCd : P_UPAPECD}
        });
    }
    // [구제급여] 피해지역 콤보 구성
    P_BIZAREAOBJ.appComboBox({
        init  : COMBO.INIT_ALL,
        url : getUrl('/com/cmm/getComboBizMng.do'),
    });

    //========================================================//
    // 신청첨부파일 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 CLEAR
		doClear();

        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();

        var cols = [];
        cols.push( {field:'chckId'       ,checkbox: true});
        cols.push( {field:'sn'           ,width:100,halign:'center',align:'center',title:'일련번호', hidden:true}    );
        cols.push( {field:'dcmtNo'       ,width:110,halign:'center',align:'center',title:'접수번호'}    );
        cols.push( {field:'dtySeNm'      ,width:150,halign:'center',align:'center',title:'업무구분'}    );
        cols.push( {field:'upPapeNm'     ,width:150,halign:'center',align:'center',title:'서류분류'}    );
        cols.push( {field:'papeNm'       ,width:400,halign:'center',align:'left'  ,title:'세부분류'}    );

        // 구제급여
        if(P_DTYSECD == CODE.PAPE_DTY_CD.RELIEF){
            cols.push( {field:'rcptYmd'  ,width:100,halign:'center',align:'center',title:'접수일자'}    );
            cols.push( {field:'bizArea'  ,width:100,halign:'center',align:'center',title:'피해지역'}    );
            cols.push( {field:'idntfcId' ,width:100,halign:'center',align:'center',title:'식별아이디'}  );
            cols.push( {field:'sufrerNm' ,width:100,halign:'center',align:'center',title:'피해자명'}    );
        }
        // 취약계층 소송지원
        else if(P_DTYSECD == CODE.PAPE_DTY_CD.LWST){
            cols.push( {field:'rcptYmd'  ,width:100,halign:'center',align:'center',title:'접수일자'}    );
            cols.push( {field:'bizArea'  ,width:100,halign:'center',align:'center',title:'지역'}        );
            cols.push( {field:'sufrerNm' ,width:100,halign:'center',align:'center',title:'신청자명'}    );
        }
        // 정보연동
        else if(P_DTYSECD == CODE.PAPE_DTY_CD.INTRLCK){
            cols.push( {field:'rcptYmd'  ,width:100,halign:'center',align:'center',title:'접수일자'}    );
            cols.push( {field:'sufrerNm' ,width:100,halign:'center',align:'center',title:'대상자명'}    );
        }

        // 맨 마지막에 파일 관련 cell 추가
        cols.push({
            field:'fileBtn',width:250,halign:'center',align:'center',title:' ',
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
        });


		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/file/getListAplyFile.do');
		// 그리드 검색조건 맵핑
		P_GRID.datagrid('options')['queryParams'] = obj;
        // 그리드 칼럼변경, 옵션추가 및 로딩 처리
        P_GRID.datagrid({
            columns: [cols],
            onLoadSuccess: function() {
                let p = $(this).datagrid('getPanel');
                p.find('.app-btn-preview').each(function() {
                    $(this).bind('click', doGirdPreview);
                });
                p.find('.app-btn-download').each(function() {
                    $(this).bind('click', doGridDownload);
                });
            }
        });
        // 검색폼 그리드 검색
        //P_GRID.datagrid('load', obj);
        return false;
    }

    // 첨부서류 미리보기
    // comm_component.js $.fn.appAplyFile>>doPreview() 참조
    //--------------------------------------------------------//
    function doGirdPreview(){
        let url  = false;
        let sn   = $(this).data('sn');
        let type = $(this).data('type');
        if      (type == 'PDF') url = getUrl('/adm/file/linkAplyHwp')+sn+".do";
        else if (type == 'HWP') url = getUrl('/adm/file/linkAplyHwp')+sn+".do";
        else if (type == 'TXT') url = getUrl('/adm/file/linkAplyFile')+".do?sn="+sn;
        else if (type == 'IMG') url = getUrl('/adm/file/linkAplyFile')+".do?sn="+sn;

        // 파일미리보기 (comm_utils.js 참고)
        $.fileUtil.preview({type: type, url: url});

        return false;
    }

    // 첨부서류 다운로드
    // comm_component.js $.fn.appAplyFile>>doDownload() 참조
    //--------------------------------------------------------//
    function doGridDownload(){
        const downloadUrl = getUrl('/adm/file/downloadAplyFile.do');
        // 파일다운로드 (comm_utils.js 참고)
        var snVal = $(this).data('sn');
        $.fileUtil.download({
            params: {sn: btoa(snVal)},
            url   : downloadUrl,
            log   : {
                params: {
                    atchFileSn: snVal,
                    progUrl   : getRealUrl(downloadUrl)
                }
            }
        });
        return false;
    }
    // 파일 다운로드 다중 선택
    function doGridMultiDownload(){

        let rows = P_GRID.datagrid('getSelections');
        if (rows.length == 0) {
            $.commMsg.alert('다운로드할 대상을 선택하세요.');
            return false;
        }

        // 관리자용 다운로드
        const downloadUrl = getUrl('/adm/file/downloadAdmAplyFileZip.do');
        // 배열로 전달
        var sns = [];
        $.each(rows, function(index, item){
            sns.push(item.sn.toString());
        });

        $.fileUtil.download({
            params: { sn : sns },
            url   : downloadUrl,
            log   : {
                params: {
                    atchFileSns: sns,
                    progUrl    : getRealUrl(downloadUrl)
                }
            }
        });
        return false;
    }

    // 신청첨부파일 검색리셋
    //--------------------------------------------------------//
    function doReset() {
    	// 상세조회 초기화
    	doClear();
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');

        //업무구분 change 이벤트 적용
        P_DTYSEOBJ.trigger('change');

        // 검색폼 그리드 검색 처리
        doSearch();

        return false;
    }

    // 신청첨부파일 상세리셋
    //--------------------------------------------------------//
    function doClear() {
        // 제목변경
		P_HEADER.html(P_TITLE+'등록');
        // 등록폼 리셋
        P_RFORM.form('reset');
		// 등록폼 기본값 정의
		$.formUtil.toForm({
			mode: MODE.INSERT
		}, P_RFORM);
        // 삭제버튼 숨김
        $('#btnRemove').hide();
        return false;
    }

    // 신청첨부파일 등록하기
    //--------------------------------------------------------//
    function doRegist() {
		doClear();
        return false;
    }

    // 신청첨부파일 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {
        var params = {
            sn        : row['sn'      ]
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/file/getAplyFile.do'),
			params,
            function(result) {
                var data = result.Data;
                if (data) {
                    // 제목변경
					P_HEADER.html(P_TITLE+'수정');
					// 수정모드 정의
					data['mode'] = MODE.UPDATE;
					// 폼값 맵핑
					$.formUtil.toForm(data, P_RFORM);
                    // 삭제버튼 표시
                    $('#btnRemove').show();
                }
            }
        );
        return false;
    }

    // 신청첨부파일 저장하기
    //--------------------------------------------------------//
    function doSave() {

        // FORM VALIDATION 기능 활성화
        if (P_RFORM.validate().settings)
            P_RFORM.validate().settings.ignore = false;

        //FORM VALIDATION
        if (P_RFORM.valid() === false)
            return false;

        $.commMsg.confirm("저장하시겠습니까?", function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/file/saveAplyFile.do'),
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

    // 신청첨부파일 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(P_RFORM, [
            'sn'
        ]);
        if ($.commUtil.empty(params['sn'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }

    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
            P_RFORM.form('load', {mode : MODE.REMOVE});
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/file/saveAplyFile.do'),
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
    // 신청첨부파일 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/file/downAplyFileExcel.do'),
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
    // 다운로드 클리시 이벤트처리
    $("#btnDownload").bind('click', doGridMultiDownload);
//    // 등록버튼 클릭시 이벤트처리
//    $('#btnRegist').bind('click', doRegist);
//    // 엑셀다운로드버튼 클릭시 이벤트처리
//    $('#btnExcel' ).bind('click', doExcel);
//
//    // 저장버튼 클릭시 이벤트 처리
//    $('#btnSave'  ).bind('click', doSave);
//    // 삭제버튼 클릭시 이벤트처리
//    $('#btnRemove').bind('click', doRemove);
//    // 취소버튼 클릭시 이벤트처리
//    $('#btnUndo'  ).bind('click', doReset);

	// 로딩시 초기화 및 검색실행
	//doReset();
	//doSearch();

    // 검색어 입력 엔터 이벤트 처리
    //bindEnter($('#srchText'), $('#btnSearch'));

    if(P_ISDOWNBTN){ $("#btnDownload").show(); }
    else           { $("#btnDownload").hide(); }
});
