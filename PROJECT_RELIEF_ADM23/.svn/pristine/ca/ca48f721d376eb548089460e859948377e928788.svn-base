/**
******************************************************************************************
*** 파일명 : viewReliefAply.js
*** 설명글 : 지역별 피해구제 신청자 현황 조회 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
*** 1.0         2021.11.10    LSH    파일명변경 및 화면개발 (이미지그래프 사용)
*** 1.0         2021.11.12    LSH    Chartjs의 Doughnut Chart / Pie Chart 사용
*** 1.0         2021.11.13    LSH    appImageGraph 사용 (comm_chart.js)
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
	let P_TITLE = '지역별 피해구제 신청인 현황';
	let P_CHART = {};

    //========================================================//
    // FORM ELEMENTS 정의
    //--------------------------------------------------------//
	$('#srchType').appComboBox({
		type: 'static',
		rows: STORE.TERM_TYPE,
		change: function() {
			if ($(this).val() == 'Y') {
				$('#srchStMnth').hide();
				$('#srchEnMnth').hide();
			}
			else {
				$('#srchStMnth').show();
				$('#srchEnMnth').show();
			}
		}
	});
	$('#srchStYear').appComboBox({
		type:'static',
		rows: STORE.getYears(0, $.formatUtil.toYear)
	});
	$('#srchEnYear').appComboBox({
		type:'static',
		rows: STORE.getYears(0, $.formatUtil.toYear)
	});
	$('#srchStMnth').appComboBox({
		type:'static',
		rows: STORE.getMonths($.formatUtil.toMonth)
	});
	$('#srchEnMnth').appComboBox({
		type:'static',
		rows: STORE.getMonths($.formatUtil.toMonth)
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

		$.ajaxUtil.ajaxLoad(
			getUrl('/adm/statistics/getListReliefAply.do'),
			params,
			function(result) {
				if (result &&
					result.Code == '0') {
					if (CHARTJS_VERSION > 3) {
						Chart.register(DoughnutLabel);
						// DataLabels 플러그인 Register
						Chart.register(ChartDataLabels);
					}
					else {
						Chart.plugins.register(DoughnutLabel);
						Chart.plugins.register(ChartDataLabels);
					}
					// 차트 생성
					doDrawChart({
						mode:  mode,
						title: title,
						data:  result
					});
				}
			}
		);
        return false;
    }

    // 차트생성
    //--------------------------------------------------------//
	function doDrawChart( args ) {
		// 지역에 따라 차트의 종류가 달라진다.
		switch (args.mode) {
			case 'A0001': doPieChart     (args); break; // 대구(A0001) : Pie Chart
			case 'A0003': doDoughnutChart(args); break; // 김포(A0003) : Doughnut Chart
			case 'A0002': doImageGraph   (args); break; // 서천(A0002) : Image Graph
		}
	}

    // 김포(A0003) : Doughnut Chart 생성
    //--------------------------------------------------------//
	function doDoughnutChart( args ) {

		let data   = args.data;
		let mode   = args.mode;
		let header = data['header'];
		let labels = data['labels']; // ['2차 인정','2차 미인정','2차 조사중']
		let values = data['values']; // [171,9,3]
		let colors = shuffleColors(COLORS.STANDARD); // ['#D395D0','#FFC000','#8FD9B6']
		let title  = args.title+' 신청자 '+header['valueT0T']+'명 현황';
		let label  = header['labelT02'   ]+'('+ header['valueT02'] +'명)';
		let circle = header['labelT01'   ]+'('+ header['valueT01'] +'명)\n\n'
			       + header['valueCricle']+'명\n('+ header['labelCricle'] +')';

		// Chart Canvas Element
		let element = document.getElementById(P_CKEY+mode);

		// Chart Configuration
		let config  = {
			type: 'doughnut',
		    data: {
				labels:    labels,
				datasets: [{
					label: label,
					data:  values,
					backgroundColor: colors,
					// 데이터셋레이블 옵션 (comm_chart.js 참고)
					datalabels: $.chartUtil.getLabelSetOptions(),
				}]
		    },
		    options: {
				responsive: false,
  				cutoutPercentage: 60,
				// 제목 옵션 (comm_chart.js 참고)
	            title:      $.chartUtil.getTitleOptionsOldVersion(title),
		    	plugins: {
					// legend 옵션 (comm_chart.js 참고)
		            legend:     $.chartUtil.getLegendOptions(),
					// 제목 옵션 (comm_chart.js 참고)
		            title:      $.chartUtil.getTitleOptions(title),
					// 데이터레이블 옵션 (comm_chart.js 참고)
					datalabels: $.chartUtil.getLabelOptions(),
					DoughnutLabel: {
						center: {
							text:      circle,
							color:     'black',
							fontSize:  '25',
							fontStyle: 'Pretendard',
							sidePadding: 10,
							lineWidth:  300
						}
					}
		        }
  			}
		};
		// 이전 Chart 객체 Destroy
		if (P_CHART[mode])
			P_CHART[mode].destroy();

		// Chartjs 의 Doughnut Chart 생성
		P_CHART[mode] = new Chart(element, config);
	}

    // 대구(A0001) : Pie Chart 생성
    //--------------------------------------------------------//
	function doPieChart( args ) {

		let data   = args.data;
		let mode   = args.mode;
		let header = data['header'];
		let labels = data['labels']; // ['2차 인정','2차 미인정','2차 조사중']
		let values = data['values']; // [171,9,3]
		let colors = shuffleColors(COLORS.STANDARD); // ['#69A8F8','#FEDA60']
		let title  = args.title+' 신청자 '+header['valueT0T']+'명 현황';
		let label  = header['labelT01']+'('+ header['valueT01'] +'명)';

		// Chart Canvas Element
		let element = document.getElementById(P_CKEY+mode);

		// Chart Configuration
		let config  = {
			type: 'pie',
		    data: {
				labels:    labels,
				datasets: [{
					label: label,
					data:  values,
					backgroundColor: colors,
					// 데이터셋레이블 옵션 (comm_chart.js 참고)
					datalabels: $.chartUtil.getLabelSetOptions()
				}]
		    },
		    options: {
				responsive: false,
				// 제목 옵션 (comm_chart.js 참고)
	            title:      $.chartUtil.getTitleOptionsOldVersion(title + ' ['+label+']'),
		    	plugins: {
					// legend 옵션 (comm_chart.js 참고)
		            legend:     $.chartUtil.getLegendOptions(),
					// 제목 옵션 (comm_chart.js 참고)
		            title:      $.chartUtil.getTitleOptions(title),
					// 소제목 옵션 (comm_chart.js 참고)
					subtitle:   $.chartUtil.getSubtitleOptions(label),
					// 데이터레이블 옵션 (comm_chart.js 참고)
					datalabels: $.chartUtil.getLabelOptions(),
		        }
  			}
		};
		// 이전 Chart 객체 Destroy
		if (P_CHART[mode])
			P_CHART[mode].destroy();
		// Chartjs 의 Pie Chart 생성
		P_CHART[mode] = new Chart(element, config);
	}

    // 서천(A0002) : Image Graph
    //--------------------------------------------------------//
	function doImageGraph( args ) {

		let data   = args.data;
		let mode   = args.mode;
		let header = data['header'];
		let title  = args.title+' 신청자 '+header['valueT0T']+'명 현황';

		P_CHART[mode] = $('#'+P_CKEY+mode).appImageGraph({
			id:    P_CKEY+mode,
			mode:  mode,
			data:  data['values'],
			labels:data['labels'],
			title: title
		});
	}

    // 초기화 (지역탭 생성)
    //--------------------------------------------------------//
	function doInit() {

		// 유효한 지역코드 (대구,서천,김포)
		const BIZ_AREA_CDS = ['A0001','A0002','A0003'];

		// 지역목록 검색
		let list  = $.ajaxUtil.ajaxDefault(getUrl("/com/cmm/getComboBizMng.do"), {});
		let tab   = $('<div class="tabWrap type4"><ul class="li-2 box"></ul></div>');
		let pnl   = $('<div class="tabInner"><ul></ul></div>');

		// 기준 날짜
		let basisDateString = $("#basisDateString").val();

		$.each(list, function(i,o) {

			if ($.inArray(o['code'], BIZ_AREA_CDS) < 0)
				return true;

			let head = $('<li><a href="javascript:void(0);">'+o['text']+'</a></li>');
			let body = $('<li class="tabInnerFrom app-c" style="padding: 50px;"></li>');
			head.data('mode', o['code']);
			body.data('mode', o['code']);
			// 2022.02.15 add : 기준일자추가
			if(basisDateString){
                // chart가 가운데로 위치하게 왼쪽 span을 '기준'부분 길이만큼 추가
			    body.append('<div class="app-space25"><p class="app-left"><span style="display: inline-block; width : 98px;"></span></p><p class="app-right"><span>(\''+basisDateString+' 기준)</span></p></div>');
            }
			body.append('<canvas id="'+P_CKEY+o['code']+'" width="1000" height="780"></canvas>');
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
			let name  = $(this).data('name')+'.png';
			if (mode == 'A0002') {
				let image = P_CHART[mode].createImage(name);
				// 이미지 다운로드 (comm_utils.js 참고)
				$.fileUtil.downloadBase64Image( image, name);
			}
			else {
				let image = P_CHART[mode].toBase64Image();
				// 이미지 다운로드 (comm_utils.js 참고)
				$.fileUtil.downloadBase64Image( image, name);
			}
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
