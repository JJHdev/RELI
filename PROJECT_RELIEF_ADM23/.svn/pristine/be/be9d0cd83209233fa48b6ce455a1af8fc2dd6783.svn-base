/**
******************************************************************************************
*** 파일명 : viewDissRcogn.js
*** 설명글 : 인정질환별 인정자 현황 조회 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.11.10    LSH    파일명변경 및 화면개발 (칼럼차트 사용)
*** 1.0         2021.11.11    LSH    Chartjs의 Bar Chart 사용
******************************************************************************************
**/
$(function() {
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_WRAP  = $('.statWrap'  );
	let P_FORM  = $('#searchForm');
	let P_CKEY  = 'appChart'      ;
	let P_BKEY  = 'btnDownload'   ;
	let P_TITLE = '인정질환별 인정자 현황';
	let P_CHART = {};

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchStYear').appComboBox({
		type:'static',
		init: COMBO.INITALL,
		rows: STORE.getYears(0, $.formatUtil.toYear)
	});
	$('#srchEnYear').appComboBox({
		type:'static',
		rows: STORE.getYears(0, $.formatUtil.toYear)
	});

    // 검색처리
    //--------------------------------------------------------//
    function doSearch() {
		let obj = P_FORM.serializeObject();

		if (obj['srchStYear'] > obj['srchEnYear']) {
			$.commMsg.alert('조회 시작년도를 종료년도보다 이전으로 선택하세요.');
			return false;
		}
		let ontab = $(".tabWrap li.on");
		let mode  = ontab.data('mode');
		let title = ontab.find('a').html();
		// 차트로드
		doSearchChart( mode, title);
        return false;
    }

    // 차트검색처리
    //--------------------------------------------------------//
    function doSearchChart( mode, title ) {

		let params = P_FORM.serializeObject();
		params['bizAreaCd'] = mode;

		$('#'+P_CKEY+mode).empty();

		$.ajaxUtil.ajaxLoad(
			getUrl('/adm/statistics/getListDissRcogn.do'),
			params,
			function(result) {
				if (result &&
					result.Data) {
					doDrawChart( mode, title, result.Data );
				}
			}
		);
        return false;
    }
    // 차트생성
    //--------------------------------------------------------//
	function doDrawChart( mode, title, result ) {

		// Chart Configuration
		let config  = {
		    type: 'bar',
		    data: {
		        labels: result['labels'],
		        datasets: [{
		        	label:           '인정질환',
		            data:            result['values'],
		            backgroundColor: result['colors'],
		        }]
		    },
		    options: {
	            title: {
					display:    true,
					text:       title+ ' ' + P_TITLE,
					fontSize:   16,
					fontStyle:  'bold'
				},
		    	plugins: {
		            title: {
						text: title+ ' ' + P_TITLE,
						display: true,
						font: {size:16, weight:'bold'}
					},
					datalabels: {
        				color: 'black',
        				display: true,
        				font: {size:14, weight: 'bold'},
						anchor: 'end',
                        align: 'start',
						offset: -20
      				}
		        },
		        legend: {
		        	display: true
		        }
  			}
		};
		// Chart Canvas Element
		let element = document.getElementById(P_CKEY+mode);

		if (CHARTJS_VERSION > 3) {
			// DataLabels 플러그인 Register
			Chart.register(ChartDataLabels);
		}
		else {
			Chart.plugins.register(ChartDataLabels);
		}

		// 이전 Chart 객체 Destroy
		if (P_CHART[mode])
			P_CHART[mode].destroy();

		// Chartjs 의 Bar Chart 생성
		P_CHART[mode] = new Chart(element, config);
	}

    // 초기화 (지역탭 생성)
    //--------------------------------------------------------//
	function doInit() {

		// 유효한 지역코드 (대구,서천,김포)
		//const BIZ_AREA_CDS = ['A0001','A0002','A0003'];
		const BIZ_AREA_CDS = ['A0002','A0003'];

		// 지역목록 검색
		let list  = $.ajaxUtil.ajaxDefault(getUrl("/com/cmm/getComboBizMng.do"), {});
		let tab   = $('<div class="tabWrap type4"><ul class="li-2 box"></ul></div>');
		let pnl   = $('<div class="tabInner"><ul></ul></div>');

		// 기준 날짜
        let basisDateString = $("#basisDateString").val();

		$.each(list, function(i,o) {

			if ($.inArray(o['code'], BIZ_AREA_CDS) < 0)
				return true;

			let head = $('<li><a href="javascript:void(0);">'+o['text'] +' ' +o['desc']+'</a></li>');
			let body = $('<li class="tabInnerFrom" style="padding: 50px; padding-left: 200px; padding-right: 200px;"></li>');
			head.data('mode', o['code']);
			body.data('mode', o['code']);
			// 2022.02.15 add : 기준일자추가
            if(basisDateString){
                body.append('<div class="app-space25"><p class="app-right">(\''+basisDateString+' 기준)</p></div>');
            }
			body.append('<canvas id="'+P_CKEY+o['code']+'"></canvas>');
			body.append('<div class="btnWrap type1"></div>');
			let btn = $('<a href="javascript:void(0);" class="'+P_BKEY+'">다운로드</a>');
			btn.data('mode', o['code']);
			btn.data('name', o['text']+'_'+P_TITLE);
			body.find('.btnWrap').append(btn);
			tab.find('ul').append(head);
			pnl.find('ul').append(body);
		});
		P_WRAP.append(tab);
		P_WRAP.append(pnl);

		$('.'+P_BKEY).bind('click', function() {
			let mode  = $(this).data('mode');
			let name  = $(this).data('name');
			let image = P_CHART[mode].toBase64Image();
			// 이미지 다운로드 (comm_utils.js 참고)
			$.fileUtil.downloadBase64Image( image, name+'.png' );
			return false;
		});

		// 탭클릭 이벤트 처리
		$(".tabWrap li").on("click", function() {
			let idx  = $(this).index();
			let mode = $(this).data('mode');
			$(this).parent().find("li").removeClass("on");
			$(this).addClass("on");
			$('.tabInnerFrom').removeClass("on");
			$('.tabInnerFrom').eq(idx).addClass("on");

			let title = $(this).find('a').html();
			// 차트로드
			doSearchChart( mode, title);
		})
		// 첫번째 탭 클릭 이벤트 발생
		$(".statWrap > .tabWrap li").eq(0).trigger('click');
		$('#searchForm').closest('.app-search-layout').hide();
	}

	// 검색버튼 클릭시 이벤트 처리
	$('#btnSearch').bind('click', doSearch);
	// 초기화
	doInit();
});
