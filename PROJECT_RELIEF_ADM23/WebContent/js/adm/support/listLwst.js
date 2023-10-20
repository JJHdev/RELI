/**
******************************************************************************************
*** 파일명 : listUserInfo.js
*** 설명글 : 사용자정보 관리 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.09.13    LSH
******************************************************************************************
**/
$(function() {

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
    var P_GRID   = $('#grid'       ); // 그리드 객체
    var P_SFORM  = $('#searchForm' ); // 검색폼 객체
    var P_RFORM  = $('#registForm' ); // 등록폼 객체
	let P_DTY_CD  = CODE.DTY_CD.LWST;    // 업무구분(취약계층소송지원)
	let P_HISTORY = false;                 // 이력관리목록
	let P_SELECT  = false;                 // 상세조회 데이터
	let aplyNo = $('#aplyNo').val();
	let P_FILES   = $('#appAplyFileList'); // 신청파일목록
	let P_APLY_ODER = '0';                 // 신청차수 기본값
	let P_FORMAT  = {
		// 진행상태 포맷처리
		stusNm: function(value, row) {
			return value;
		}
	};

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
        // 그리드 단일행만 선택여부
        singleSelect: true,
        // 체크박스 KEY값필드
        idField:'aplyNo',
        // 칼럼정의
       columns: [[
		    {field:'chckId'       ,checkbox: true},
            {field:'aplyNo' ,width:100,halign:'center', hidden: true, align:'center',title:'번호'},
            {field:'aplcntNm'  ,width:80,halign:'center',align:'center',title:'신청인 성명'},
            {field:'aplcntBrdt'  ,width:120,halign:'center',align:'center',title:'신청인 생년월일'},
			{field:'aplcntAddrLwst'  ,width:250,halign:'center',align:'center',title:'신청인 주소'},
            {field:'aplcntMbtelNo'  ,width:140,halign:'center',align:'center',title:'신청인 휴대전화번호'},
            {field:'respdntNm'  ,width:140,halign:'center',align:'center',title:'피신청인 성명'},
			{field:'prgreStusNm'  ,width:110,halign:'center',align:'center',title:'상태', formatter: P_FORMAT.stusNm},
         	{field:'aplyYmd'  ,width:110,halign:'center',align:'center',title:'신청일자'}
        ]],
        // 행선택시 수정하기
        onSelect: doUpdate
    });
	// 이력관리영역 (comm_adm.js 참고)
	P_HISTORY = $('#appHistoryTable').appTableLayout({
		cls:    'app-h200',
		columns: [
			{name: 'regDate', label: '작성일자'},
			{name: 'hstCn'  , label: '이력내용', key: 'sn', dblclick: doSelectHistory},
			{name: 'rgtrNm' , label: '작성자'}
		],
		nodata: true
	});
	P_FILES = P_FILES.appAplyFile({
		mode:   MODE.LIST,
		system: SYSTEM.ADMIN['code']
	});

	 // [이력관리팝업] 이력관리 목록검색
    //--------------------------------------------------------//
    function doLoadHistory( aplyNo ) {
		P_HISTORY.load(
			getUrl('/adm/biz/getListMngHst.do'), {
			dtySeCd: P_DTY_CD,
			aplyNo:  P_SELECT['aplyNo'],
		});
        return false;
    }

    // [이력관리팝업] 이력등록 팝업오픈
    //--------------------------------------------------------//
    function doOpenHistory() {
		// 이력관리팝업 (adm_popup.js)
		popup.openMngHst({
			element:'#appPopupHistory',
			openArgs: {
				title: '이력등록',
				params: JSON.stringify({
					mode:    MODE.INSERT,
					dtySeCd: P_DTY_CD,
					hstSeCd: 'H1',
					aplyNo:  P_SELECT['aplyNo']
				})
			},
			saveCallback: doLoadHistory
		});
        return false;
    }

	 // [이력관리팝업] 이력조회 팝업오픈
    //--------------------------------------------------------//
    function doSelectHistory(aplyNo) {
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

    //========================================================//
    // 콤보박스 정의
    //--------------------------------------------------------//

	$('#appAplyTermBox').appTermBox({
		label:'신청일자',
		stName:'srchAplyStdt',
		enName:'srchAplyEndt'
	});

	$('#appPrgreStusCd').appSelectBox({
	label:   '신청상태',
	form:    'checkbox',
	name:    'prgreStusList',
	params:  {upCdId: 'CT023'},
	filter: function(row) {
		// 표출 항목
		if (row['code'] == '10' || row['code'] == '20')
			return true;
		return false;
		}
	});

	$('#appSprtSeCdList').appSelectBox({
	label:   '지원구분',
	form:    'checkbox',
	name:    'sprtSeCdList',
	params:  {upCdId: 'CT021'}
	});


    // 사용자정보 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
        // 검색폼 데이터 객체화
        var obj = P_SFORM.serializeObject();
        // 그리드 목록조회 URL
        P_GRID.datagrid('options')['url'] = getUrl('/adm/support/getlistLwst.do');
        // 검색폼 그리드 검색
        P_GRID.datagrid('load', obj);
        return false;
    }

    P_RFORM.validate({
        debug: false,
        onsubmit: false,
        onfocusout: false,
        // 검증룰 정의
        rules: {
            mbtelNo   : {required: true,
                         mobile: true},
        },
        invalidHandler: validateHandler,
        errorPlacement: validatePlacement
    });

    // 사용자정보 수정하기
    //--------------------------------------------------------//
    function doUpdate(index, row) {
		var params = {
			aplyNo:   row['aplyNo'  ]
		};

		$.ajaxUtil.ajaxLoad(
            getUrl('/adm/support/viewListLwst.do'),
			params,
            function(result) {
                var data = result.Data;
				P_SELECT = data;
				aplyNo = P_SELECT['aplyNo'];
				// hidden값의 FORM 데이터 정의
				$.formUtil.toForm(data, P_RFORM);
				// EasyUI BOX의 FORM 데이터 정의
				P_RFORM.form('load', data);

		    	P_FILES.loadList({
					dtySeCd:   data['papeDtySeCd'],
					dcmtNo:    data['aplyNo'],
					dtlDcmtNo: P_APLY_ODER
				});
				// 이력관리 목록로드
				doLoadHistory(data['aplyNo']);
            }
        );
        return false;
    }

	// FORM 검증
	function doValidate() {
      var aplySeCd = $('#aplySeCd').val();
	  var prgreStusCd = $('#prgreStusCd').val();
	  var sprtArray =  [];
		$('input:checkbox[name="sprtSeCdList"]:checked').each(function(i){
			sprtArray.push($(this).val());
		});

		var inval_Arr = false;

		if(aplySeCd == null){
            inval_Arr[0] = false;
            alert('신청인 정보의 신청 요건을 선택해주세요.');
            return false;
         } else {
            inval_Arr[0] = true;
         }

		if(sprtArray.length ==  0){
            inval_Arr[1] = false;
            alert('신청인 정보의 지원 구분을 선택해주세요.');
            return false;
         } else {
            inval_Arr[1] = true;
         }

	     //전체 유효성 검사
	     var validAll = true;
	     for(var i = 0; i < inval_Arr.length; i++){
	        if(inval_Arr[i] == false){
	           validAll = false;
	        }
	     }

	     if(validAll == true){ // 유효성 모두 통과
			return true;
	     } else{
	        alert('정보를 다시 확인하세요.')
			return false;
	     }
	}

    //--------------------------------------------------------//
	// 소송지원 접수하기
    //--------------------------------------------------------//
   function doOpenReceipt() {
	let rows = P_GRID.datagrid('getSelections');
		if (rows.length == 0) {
			$.commMsg.alert('신청현황에서 접수할 항목을 하나 이상 선택하세요.');
			return false;
		}

		// FORM 검증결과가 실패이면
		if (doValidate() === false) {
			return false;
		}
       $.commMsg.confirm("취약계층 소송지원 신청을 접수하시겠습니까?", function() {
        // 등록폼을 AJAX로 저장처리
        P_RFORM.ajaxForm({
            url: getUrl('/adm/support/updateLwstIncdnt.do'),
            // 오류시 처리로직
            error: $.ajaxUtil.error,
            // 저장후 처리로직
            success: function(ret) {
           $.commMsg.success(ret, '성공적으로 접수되었습니다.', function() {
                   goUrl(getUrl('/adm/support/listLwst.do'));
           });
            }
        }).submit();
    });
    return false;
}

   //--------------------------------------------------------//
   // 소송지원 취소하기
   //--------------------------------------------------------//
   function doCancel() {
      let rows = P_GRID.datagrid('getSelections');
      if (rows.length == 0) {
         $.commMsg.alert('신청현황에서 취소할 항목을 하나 이상 선택하세요.');
         return false;
      }
      $.commMsg.confirm("취약계층 소송지원 신청을 취소하시겠습니까?", function() {

        // 이력관리팝업 (adm_popup.js)
        popup.openMngHst({
		  noAlert: true,
          element:'#appPopupCancel',
          openArgs: {
            title: '취소이력',
            params: JSON.stringify({
               mode:    MODE.INSERT,
               dtySeCd: P_DTY_CD,
               hstSeCd: 'H2',
               aplyNo:  P_SELECT['aplyNo']
            })
          },
          saveCallback: function() {
            // 등록폼을 AJAX로 저장처리
            P_RFORM.ajaxForm({
               url: getUrl('/adm/support/cancelLwstIncdnt.do'),
               // 오류시 처리로직
               error: $.ajaxUtil.error,
               // 저장후 처리로직
               success: function(ret) {
               $.commMsg.success(ret, '소송지원이 취소되었습니다.', function() {
                     goUrl(getUrl('/adm/support/listLwstRtrcn.do'));
               });
               }
            }).submit();
          }
        });
      });
      return false;
   }

    // 신청접수현황 엑셀다운로드
    //--------------------------------------------------------//
    function doExcel() {
        $.formUtil.submitForm(
            getUrl('/adm/support/downLwstExcel.do'),
            {formId : "searchForm"}
        );
        return false;
    }

    // 취약계층소송지원 신청서 인쇄하기 클릭 (리포팅툴)
    //--------------------------------------------------------//
    function doReport() {
		// 선택된 상세정보가 있는지 확인
		if (!P_SELECT) {
			$.commMsg.alert('신청현황에서 항목을 선택하세요.');
			return false;
		}
		// 선택된 상세정보
		let data = P_SELECT;

		let params = {
			mode : 'LWST',
			aplyNo : data['aplyNo'],
			aplcntNm : data['aplcntNm'],
			aplcntBrno : data['aplcntBrno'],
			aplcntAddrLwst : data['aplcntAddrLwst'],
			aplcntBrdt : data['aplcntBrdt'],
			aplcntTelNo : data['aplcntTelNo'],
			aplcntMbtelNo : data['aplcntMbtelNo'],
			aplcntEmlAddr : data['aplcntEmlAddr'],
			respdntNm : data['respdntNm'],
			respdntBrno : data['respdntBrno'],
			respdntAddrLwst : data['respdntAddrLwst'],
			respdntBrdt : data['respdntBrdt'],
			respdntTelno : data['respdntTelno'],
			respdntMbtelNo : data['respdntMbtelNo'],
			respdntEmlAddr : data['respdntEmlAddr'],
			dmgeOcrnPlce : data['dmgeOcrnPlce'],
			dmgeYmd : data['dmgeYmd'],
			dmgeCn : data['dmgeCn'],
			dmgeAmt : data['dmgeAmt'],
			aplyObjetCn : data['aplyObjetCn'],
			aplyCn : data['aplyCn'],
			aplyAmt : data['aplyAmt'],
			aplyYmd : data['aplyYmd'],
			prgreStusNm : data['prgreStusNm'],
			aplySeCd : data['aplySeCd'],
			sprtSeCd : data['sprtSeCd'],
			sprtSeNm : data['sprtSeNm'],
			signCn : data['signCn'],
			aplyYmdReport : data['aplyYmdReport']
		};
		// 리포트뷰어 팝업 호출
		popup.openReportPopup(params);
		return false;
    }

    // 2023.01.27 LSH [추가등록] 팝업오픈
    //--------------------------------------------------------//
    function doOpenAddfile() {
		if (!P_SELECT) {
			$.commMsg.alert('신청현황에서 항목을 선택하세요.');
			return false;
		}
		let cmb = false;
		// 파일업로드 팝업 오픈
		popup.openUpload({
			// 업로드 URL
			url: getUrl("/adm/support/saveLwstAddfile.do"),
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
				tr1.append('<th>신청인정보</th>');
				tr1.append('<td>신청인명 :'+$.commUtil.nvl(P_SELECT['aplcntNm'  ])+'</td>');
				tr1.append('<td>생년월일 :'+$.commUtil.nvl(P_SELECT['aplcntBrdt'])+'</td>');
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
					// 취약계층소송지원 서류상위코드
					params: {papeCd: CODE.PAPE_CD.LST}
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
						doSearch();
						dialog.close();
					});
				});
            }
		});

        return false;
    };

    // 검색버튼 클릭시 이벤트 처리
    $('#btnSearch').bind('click', doSearch);
 	// 이력등록버튼 클릭시 이벤트처리
    $('#btnHistory').bind('click', doOpenHistory);
	// 신청접수버튼 클릭시 이벤트처리
    $('#btnReceipt').bind('click', doOpenReceipt);
	// 신청취소버튼 클릭시 이벤트처리
    $('#btnCancel').bind('click', doCancel);
    // 엑셀다운로드버튼 클릭시 이벤트처리
    $('#btnExcel' ).bind('click', doExcel);
	// 신청서보기 클릭시 리포팅툴 이벤트처리
	$('#btnReport' ).bind('click', doReport);
	// 2023.01.27 추가등록버튼 클릭시 이벤트처리
	$('#btnAddfile').bind('click', doOpenAddfile);

   	bindEnter($('#srchAplcntNm'), $('#btnSearch'));

    // 검색 실행
    doSearch();

	//개인정보 탭
	$(".tabWrap li").on("click", function() {
		var idx = $(this).index()
		$(this).parent().find("li").removeClass("on");
		$(this).addClass("on");
		$('.tabInnerFrom').removeClass("on");
		$('.tabInnerFrom').eq(idx).addClass("on");
	});

});
