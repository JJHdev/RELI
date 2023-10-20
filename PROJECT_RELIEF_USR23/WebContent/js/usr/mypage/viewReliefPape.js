/**
******************************************************************************************
*** 파일명 : viewReliefPape.js
*** 설명글 : 마이페이지 - 구제급여현황 - 구비서류조회 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.12.08    LSH 탭별로 프로그램 분리 / 신청목록 추가
******************************************************************************************
**/
$(function() {
	
	// 서브메뉴탭 생성
    //--------------------------------------------------------//
	createTabMenu($('#appReliefTab'), [
		{title:'구비서류 조회'    , url: getUrl('/usr/mypage/viewReliefPape.do') },
		{title:'피해구제 현황'    , url: getUrl('/usr/mypage/viewRelief.do'    ) },
		{title:'구제급여 지급현황', url: getUrl('/usr/mypage/viewReliefGive.do') }
	], 'relief-tab tabWrap type1');

    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//
	let P_RELIEF    = false; // 선택한 신청정보
	let P_PAPE_LIST = [];    // 신청서류 파일객체 배열
	let P_PDTY_CD   = $('#papeDtySeCd').val();
	let P_APLY_ODER = '0'; //$('#aplyOder').val();

    // 초기실행
    //--------------------------------------------------------//
	// 보완제출 버튼 클릭시 이벤트 처리
	$('#btnSubmit').bind('click', doSubmit);
	// 신청목록 로드
	doLoad();

    // 신청목록 가져오기 (초기실행)
    //--------------------------------------------------------//
	function doLoad() {

		$.ajaxUtil.ajaxLoad(
			getUrl('/usr/relief/getListRelief.do'), {}, 
			function(data) {
				
				if (data.rows.length == 0) {
					// 데이터가 없는경우 탭패널 감춤
					$('.relief-tab-panel').hide();
				}
				// 신청목록 정의
				$('#appAplyList').appTableLayout({
					cls: 'app-h200',
					nodata: true,
					data: data.rows,
					// 칼럼목록
					columns: [
						{name: 'aplyNo'     , label: '신청번호'},
						{name: 'aplcntNm'   , label: '신청자명'},
						{name: 'sufrerNm'   , label: '피해자명'},
						{name: 'aplySeNm'   , label: '신청구분'},
						{name: 'aplyYmd'    , label: '신청일자'},
						{name: 'rcptYmd'    , label: '접수일자'},
						{name: 'prgreStusNm', label: '진행상태'},
					],
					// 행선택처리
					select: doSelect,
					// 행로드후 처리함수
					callback: function(cmp) {
						cmp.selectRow(0);
					}
				});
			}
		);
	}

    // 신청정보 가져오기
    //--------------------------------------------------------//
	function doSelect(index, row) {
		$.ajaxUtil.ajaxLoad(
			getUrl('/usr/relief/getRelief.do'), 
			{aplyNo: row['aplyNo']}, 
			function(data) {
				if (data.Code == '0') {
					P_RELIEF = data['Data'];
					// 신청서류그룹
					doLoadPapeGroup({
						papeDtySeCd: P_PDTY_CD,
						aplySeCd:    P_RELIEF['aplySeCd'   ],
						aplyNo:      P_RELIEF['aplyNo'     ],
						aplyKndList: P_RELIEF['aplyKndList']
					});
					// 2021.12.13 LSH 의료비 추가신청서류그룹
					doLoadPapeAddGroup({
						papeDtySeCd: P_PDTY_CD,
						aplySeCd:    P_RELIEF['aplySeCd'],
						aplyNo:      P_RELIEF['aplyNo'  ],
						aplyOder:    P_RELIEF['aplyOder']
					});
				}
			}
		);
	}

    // 신청서류그룹 가져오기
    //--------------------------------------------------------//
	function doLoadPapeGroup( data ) {

		// 신청서류그룹 리셋		
		P_PAPE_LIST = [];
		$('#papeGroup').empty();
		
		// 신청서류그룹 가져오기
		const rows = $.ajaxUtil.ajaxDefault(
			getUrl('/usr/file/getListPapeGroup.do'), {
				papeDtySeCd: data['papeDtySeCd'],
				aplySeCd:    data['aplySeCd'   ]
			}
		);
		
		if (rows) {
			// 신청종류 목록
			let arr = data['aplyKndList'];
			// 의료비유무
			let inMCP = ($.inArray(CODE.APLY_KIND_CD.MCP, arr) >= 0);
			// 요양생활수당유무
			let inRCP = ($.inArray(CODE.APLY_KIND_CD.RCP, arr) >= 0);
			// 보안제출여부
			let isSubmit = false;

			$.each(rows, function(i, row) {
				if (CODE.PAPE_CD.MCP == row['papeCd'] && !inMCP) {
					return true;
				}
				if (CODE.PAPE_CD.RCP == row['papeCd'] && !inRCP) {
					return true;
				}
				let div = $('<div class="usr-file-list"></div>');
				div.data('code', row['papeCd']);
				div.data('name', row['papeNm']);
				let wgt = div.appAplyFile({
					mode:  MODE.MYPAGE,
					title: row['papeNm'],
					// 서류목록조건
					params: {
						// 업무구분
						papeDtySeCd: data['papeDtySeCd'],
						// 신청구분
						aplySeCd: data['aplySeCd'],
						// 서류그룹
						upPapeCd: row['papeCd'],
						// (수정시) 문서번호
						dcmtNo: data['aplyNo'],
						// (수정시) 세부문서번호
						dtlDcmtNo: P_APLY_ODER
					}
				});
				// 보완요청 항목이 있는 경우
				if (wgt.getSplemntNeedList().length > 0) {
					// 보완제출 버튼 표시
					isSubmit = true;
				}
				P_PAPE_LIST.push(wgt);
				$('#papeGroup').append(div);
			});
			if (isSubmit) 
				$('.app-btn-submit').removeClass('app-off');
			else
				$('.app-btn-submit').addClass('app-off');
		}
	}

	// 2021.12.13 LSH 추가
    // 추가신청서류목록 가져오기 (있을경우에만 표시)
    //--------------------------------------------------------//
	function doLoadPapeAddGroup( data ) {

		$('#papeAddGroup').empty();
		// 의료비 추가신청 최종차수가 있으면
		if (!$.commUtil.empty(data['aplyOder'])) {
			let div = $('<div class="usr-file-list"></div>');
			div.appAplyFile({
				// 마이페이지 모드
				mode: MODE.MYPAGE,
				// 서류제목
				title: '의료비 추가신청서류',
				// 서류목록조건
				params: {
					// 업무구분
					papeDtySeCd: data['papeDtySeCd'],
					// 신청구분
					aplySeCd:    data['aplySeCd'],
					// 서류그룹 (의료비)
					upPapeCd:    'D002',
					// 서류코드 (요양급여내역)
					papeCd:      'D00201',
					// (수정시) 문서번호
					dcmtNo:      data['aplyNo'],
					// (수정시) 세부문서번호
					dtlDcmtNo:   data['aplyOder']
				}
			});
			$('#papeAddGroup').append(div);
		}
	}
	
    // 보완제출하기
    //--------------------------------------------------------//
    function doSubmit() {
		
		let data = {
			aplyNo:      P_RELIEF['aplyNo'],
			aplyOder:    P_RELIEF['aplyOder'] || P_APLY_ODER,
			saveFiles:   [],
			removeFiles: []
		};
		// FILE VALIDATION
		let check = true;
		$.each(P_PAPE_LIST, function(i, file) {
			// 파일 VALIDATION 및 저장 데이터
			let saveData = file.doSplemntValidateData();
			if (saveData === false) {
				check = false;
				return false;
			}
			// 저장대상파일 배열 합치기
			$.merge(data['saveFiles'  ], saveData['saveFiles'  ]);
			// 삭제대상파일 배열 합치기
			$.merge(data['removeFiles'], saveData['removeFiles']);
		});
		if (check === false)
			return false;
		
		if (data['saveFiles'].length == 0) {
			$.commMsg.alert('보완처리할 대상이 없습니다.');
			return false;
		}

        $.commMsg.confirm("보완제출하시겠습니까?", function() {
            // AJAX로 저장처리
            $.ajaxUtil.ajaxSave(
                getUrl('/usr/relief/saveReliefSplemnt.do'), 
                JSON.stringify(data),
                function(ret) {
                    $.ajaxUtil.success(ret, function() {
						$.commMsg.alert('성공적으로 제출되었습니다.', function() {
						    // 저장후 마이페이지로 이동처리
							goUrl( getUrl('/usr/mypage/viewReliefPape.do') );
						});
                    });
                }
            );
        });
        return false;
    }
});
