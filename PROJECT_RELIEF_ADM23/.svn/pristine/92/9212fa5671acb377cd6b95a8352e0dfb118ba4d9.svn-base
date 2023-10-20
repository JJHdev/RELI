/**
******************************************************************************************
*** 파일명 : listPapeMng.js
*** 설명글 : 서류양식관리 화면 스크립트
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
	let P_GRID       = $('#appGrid'        ); // 목록 GRID
	let P_FORM       = $('#searchForm'     ); // 검색폼
	let P_RFORM      = $('#registForm'     ); // 등록폼
	let P_HEADER     = $('#registTitle'    ); // 제목객체
	let P_TITLE      = '서류양식'; // 업무제목'
    let P_TABOBJLYR  = $('#tabUpPape');

    let P_DTYSEOBJ   = $('#srchPapeDtySeCd'); // 업무구분
    let P_SRCHAPLYSEOBJ  = $('#srchAplySeCd');      // 신청구분
    let P_PAPEROBJ   = $('#papeCd');          // 서류양식 입력 form > 서류코드
    let P_APLYSEROBJ = $('#aplySeCd');        // 서류양식 입력 form > 신청구분

    let P_UP_PAPE_CD     = "";
    let P_PAPE_DTY_SE_CD = "";

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
            {field:'papeCd'       ,hidden:true  ,width:80, halign:'center',align:'center',title:'서류코드'},
            {field:'papeNm'       ,hidden:false ,width:200,halign:'center',align:'left'  ,title:'서류명'},
            {field:'papeDtySeCd'  ,hidden:true  ,width:80, halign:'center',align:'center',title:'업무구분'},
            {field:'papeDtySeNm'  ,hidden:false ,width:150,halign:'center',align:'left'  ,title:'업무명'},
            {field:'aplySeCd'     ,hidden:true  ,width:80, halign:'center',align:'center',title:'신청구분'},
            {field:'aplySeNm'     ,hidden:false ,width:150,halign:'center',align:'left'  ,title:'신청구분명'},
            {field:'esntlYn'      ,hidden:false ,width:80, halign:'center',align:'center',title:'필수여부', formatter:$.commFormat.esntlYn},
            {field:'useYn'        ,hidden:false ,width:80, halign:'center',align:'center',title:'사용여부', formatter:$.commFormat.useyn},
            {field:'rgtrNm'       ,hidden:true  ,width:100,halign:'center',align:'center',title:'등록자'},
            {field:'regDate'      ,hidden:true  ,width:100,halign:'center',align:'center',title:'등록일자'},
            {field:'mdfrNm'       ,hidden:true  ,width:100,halign:'center',align:'center',title:'수정자'},
            {field:'mdfDate'      ,hidden:false ,width:100,halign:'center',align:'center',title:'수정일자'}
        ]],
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
            papeCd    : {required: true},
            papeDtySeCd: {required: true},
            aplySeCd  : {required: true},
            esntlYn   : {required: true},
            useYn     : {required: true},
            rgtrNo    : {required: true},
            regYmd    : {required: true},
            mdfrNo    : {required: true},
            mdfcnYmd  : {required: true}
        },
        // 검증메세지 정의
        messages: {
            papeCd    : {required: '서류코드는 필수 입력 사항입니다.'},
            papeDtySeCd: {required: '서류업무구분코드는 필수 입력 사항입니다.'},
            aplySeCd  : {required: '신청구분코드는 필수 입력 사항입니다.'},
            esntlYn   : {required: '필수여부는 필수 입력 사항입니다.'},
            useYn     : {required: '사용여부는 필수 입력 사항입니다.'},
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
    P_DTYSEOBJ.appComboBox({
        params: {upCdId: 'CT034'},
        // 콤보 값변경시 검색 처리
        change:   doChangePapeDtySeCombo,
        // 콤보 값로딩후 실행 처리
        callback: doChangePapeDtySeCombo
    });

    // 신청구분 조회 조건 combo 구성
    P_SRCHAPLYSEOBJ.appComboBox({
        init  : COMBO.INIT_ALL,
        params: {upCdId: 'CT010'},
    });

    // 등록용 필수여부 라디오박스
    $('#appEsntlYn').appSelectBox({
        form: 'radio',
        name: 'esntlYn',
        type: 'static',
        rows: STORE['ESNTL_YN']
    });

    // 등록용 사용여부 라디오박스
    $('#appUseYn').appSelectBox({
        form: 'radio',
        name: 'useYn',
        type: 'static',
        rows: STORE['USE_YN']
    });

    //========================================================//
    // 서류양식관리 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 상세조회 CLEAR
		doClear();
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_FORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/file/getListPapeMng.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);

        return false;
    }

    // 서류양식관리 최초 화면 loading용 검색처리
    //--------------------------------------------------------//
    function doInitSearch(){
        // '공통서류' tab 기본 설정
        setUpPape(0);
        // 조회
        doSearch();
    }

    //========================================================//
    // Tab객체 구성 (급여종류 항목 tab)
    //--------------------------------------------------------//
    function doLoadTab(){

        P_TABOBJLYR.html("");

        let paramData = P_FORM.serializeObject();
        const result = $.ajaxUtil.ajaxDefault(
            getUrl('/adm/file/getPapeMngUpPapeList.do'),
            paramData
        );

        var dataList = result;
        if(dataList && dataList.length > 0){

            $.each(dataList, function(i,item){
                let li = $('<li></li>');
                li.data('code', item.upPapeCd);
                li.append('<a href="javascript:void(0);">'+item.upPapeNm+'</a>');

                P_TABOBJLYR.append(li);
            });

            // 초기화 조회
            doInitSearch();
        }
    }

    // 선택 급여종류 설정
    //--------------------------------------------------------//
    function setUpPape(index){
        let tabObj = $("#tabUpPape li").eq(index);
        if(tabObj){
            P_UP_PAPE_CD = tabObj.data("code");
            P_FORM.find("[name=srchUpPapeCd]").val(P_UP_PAPE_CD);
        }
        tabObj.siblings().removeClass("on");
        tabObj.addClass("on");

        // 선택된 급여종류(tab)에 해당하는 설정 수행

        // 입력항목 '서류코드' 콤보 구성
        doLoadPapeCdCombo();
    }

    // 업무구분 change 이벤트 처리 함수
    //--------------------------------------------------------//
    function doChangePapeDtySeCombo(){
        // 검색조건 '신청구분' 선택값을 초기화
    	// ntarget 2022.12.13 살생물 추가
        P_SRCHAPLYSEOBJ.val('');
        if(P_DTYSEOBJ.val() == 'PP01' || P_DTYSEOBJ.val() == 'PP04') 
        	$("#srchAplySeArea").show();
        else                           
        	$("#srchAplySeArea").hide();

        // 선택된 업무구분 값 저장
        P_PAPE_DTY_SE_CD = P_DTYSEOBJ.val();
        // 업무구분에 해당하는 tab 구성
        doLoadTab();
        // 입력항목 '신청구분' 콤보 구성
        doLoadAplySeCdCombo();
    }

    // 선택 급여종류에 맞는 입력항목 '서류코드' 콤보 구성
    //--------------------------------------------------------//
    function doLoadPapeCdCombo(){
        P_PAPEROBJ.appComboBox({
            init  : {code : '', text : ' '},
            url   : getUrl('/adm/file/listPapeMngPapeCodeByUpPape.do'),
            params: {upPapeCd : P_UP_PAPE_CD}
        });
    }

    // '업무구분'에 따른 입력항목 '신청구분' 콤보 구성
    //--------------------------------------------------------//
    function doLoadAplySeCdCombo(){

        var options = {
            init    : {code : '', text : ' '}
        }
        // 구제급여일 때
        // ntarget 2022.12.13 살생물 추가
        if(P_PAPE_DTY_SE_CD == 'PP01' || P_PAPE_DTY_SE_CD == 'PP04') {
            options.params = {upCdId: 'CT010'};
        }
        // 구제급여가 아닐 때
        else {
            options.type = 'static';
            options.rows = [
                {code : 'ALL', text : 'ALL'}
            ];
        }

        P_APLYSEROBJ.appComboBox(options);
    }

    // 서류양식관리 검색리셋
    //--------------------------------------------------------//
    function doReset() {
        // '공통서류' tab 기본 설정
        setUpPape(0);

    	// 상세조회 초기화
    	doClear();
        // 검색폼 입력데이터 초기화
        P_FORM.form('reset');

        // 업무구분 항목 change 이벤트 발행
        P_DTYSEOBJ.trigger("change");

        return false;
    }

    // 서류양식관리 상세리셋
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

        // 저장 화면의 항목 설정
        // 업무구분
        P_RFORM.find('input[name=papeDtySeCd]').val(P_DTYSEOBJ.find('option:selected').val());
        P_RFORM.find('input[name=papeDtySeCdText]').val(P_DTYSEOBJ.find('option:selected').text());

        return false;
    }

    // 서류양식관리 등록하기
    //--------------------------------------------------------//
    function doRegist() {
		doClear();
        return false;
    }

    // 서류양식관리 상세조회
    //--------------------------------------------------------//
    function doSelect(index, row) {
        var params = {
            papeCd    : row['papeCd'  ],
            papeDtySeCd: row['papeDtySeCd'],
            aplySeCd  : row['aplySeCd']
        };
        $.ajaxUtil.ajaxLoad(
            getUrl('/adm/file/getPapeMng.do'),
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

    // 서류양식관리 저장하기
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
                url: getUrl('/adm/file/savePapeMng.do'),
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

    // 서류양식관리 삭제하기
    //--------------------------------------------------------//
    function doRemove() {
        var params = $.formUtil.toObject(P_RFORM, [
            'papeCd',
            'papeDtySeCd',
            'aplySeCd'
        ]);
        if ($.commUtil.empty(params['papeCd'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }
        if ($.commUtil.empty(params['papeDtySeCd'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }
        if ($.commUtil.empty(params['aplySeCd'])) {
            $.commMsg.alert('삭제할 대상을 선택하세요.');
            return false;
        }

    	$.commMsg.confirm("정말 삭제하시겠습니까?", function() {
			// 삭제모드 정의
            P_RFORM.form('load', {mode : MODE.REMOVE});
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
                url: getUrl('/adm/file/savePapeMng.do'),
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
    $('#btnUndo'  ).bind('click', doReset);
    // Tab 버튼 클리시 이벤트 처리
    P_TABOBJLYR.on('click', 'li', function(){
        let letObj = $(this);
        let tabIndex = $("#tabUpPape li").index(letObj);
        // 급여종류 tab 기본 설정
        setUpPape(tabIndex);
        // 조회
        doSearch();
    });

	// 로딩시 초기화 및 검색실행
	doReset();
	//doSearch();

    // 검색어 입력 엔터 이벤트 처리
    //bindEnter($('#srchText'), $('#btnSearch'));
});
