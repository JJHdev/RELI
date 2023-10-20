/**
******************************************************************************************
*** 파일명    : comm_chart.js
*** 설명      : CHART 관련 사용자 정의 컴포넌트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-11-12              LSH
******************************************************************************************
**/
//===========================================================================//
// 이미지 그래프 표시
//===========================================================================//
$.fn.appImageGraph = function ( args ) {

	let options = $.extend({
		// 그래프 ID
		id:  '',
		// 그래프 지역구분
		mode:  'A0003',
		// 그래프 데이터
		data: {},
		// 그래프 제목
		labels: false,
		// 그래프 제목
		title: '',
		// 그래프 제목 좌표
		titlePos: {left:400, top: 30},
		// 그래프 제목 폰트
		titleFont: 'bold 24px Pretendard',

		// 기본 폰트
		font: 'bold 16px Pretendard',
		// 그래프 배경 이미지
		//image: getUrl('/images/adm/venn_graph_02.png'),
		image: getUrl('/images/adm/venn_graph_design03.png'),
		// 데이터 포맷터
		formatter: {
			men: function(v) {
				return v+'명';
			},
			bracketMen: function(v) {
				return '('+v+'명)';
			}
		},
		// 칼럼설정
		columns: []
	}, args);

	let thisCmp    = this;
	let thisElm    = $(this);
	let thisCanvas = thisElm[0];
	let thisCtx    = thisCanvas.getContext('2d');

	options.columns = [
			{field: 'aplyT01',    title: '1차 사업',            hleft: 300, htop:  75, left: 370, top:  75, font: 'bold 20px Pretendard', formatter: options.formatter.bracketMen}, // [L]상단제목
			{field: 'aplyT02',    title: '2차 사업',            hleft: 600, htop:  75, left: 670, top:  75, font: 'bold 20px Pretendard', formatter: options.formatter.bracketMen}, // [R]상단제목
			{field: 'aplyD01',    title: '1차 불인정',          hleft: 175, htop: 355, left: 195, top: 325, formatter: options.formatter.men}, // [L]상단
			{field: 'aplyR01',    title: '1차 인정',            hleft: 175, htop: 580, left: 195, top: 550, formatter: options.formatter.men}, // [L]하단
			{field: 'aplyD02',    title: '2차 불인정',          hleft: 780, htop: 355, left: 800, top: 325, formatter: options.formatter.men}, // [R]상단
			{field: 'aplyR02',    title: '2차 인정',            hleft: 780, htop: 580, left: 800, top: 550, formatter: options.formatter.men}, // [R]하단
			{field: 'aplyD01D02', title: '1차,2차 모두 불인정', hleft: 430, htop: 250, left: 480, top: 220, formatter: options.formatter.men}, // [C]1단
			{field: 'aplyD01R02', title: '1차 불인정,2차 인정', hleft: 430, htop: 380, left: 480, top: 350, formatter: options.formatter.men}, // [C]2단
			{field: 'aplyR01D02', title: '1차 인정,2차 불인정', hleft: 430, htop: 510, left: 480, top: 480, formatter: options.formatter.men}, // [C]3단
			{field: 'aplyR01R02', title: '1차,2차 모두 인정',   hleft: 430, htop: 650, left: 480, top: 620, formatter: options.formatter.men}, // [C]5단
	];

	this.init = function() {
		// 새로운 이미지 객체를 만듬
		let image = new Image();
		image.onload = function() {
			// 캔버스에 이미지 DRAW
			thisCtx.drawImage(image, 10, 90);
			// 그래프 제목 표시
			thisCtx.font = options.titleFont;
			thisCtx.fillText(options.title, options.titlePos['left'], options.titlePos['top']);
			// 데이터 표시
			thisCmp.loadData(options.data);
		}
		// 이미지 URL
		image.src = options.image;
	}

	this.loadData = function(data) {

		$.each(options.columns, function(i,c) {
			let value = data[c.field];
			let title = c.title;
			//if (options.labels) {
			//	title = options.labels[c.field];
			//}
			if (c.formatter)
				value = c.formatter(value);
			// 제목 생성
			thisCtx.font = (c.font ? c.font : options.font);
			thisCtx.fillText(title, c.hleft, c.htop);
			// 내용 생성
			thisCtx.font = (c.font ? c.font : options.font);
			thisCtx.fillText($.commUtil.nvl(value), c.left, c.top);
		});
	}

	this.createImage = function(name) {
		return $.fileUtil.createCanvasImage(thisCanvas, name);
	};

	this.init();

	return thisCmp;
/*
			element = document.getElementById(P_CKEY+mode);
			var ctx = element.getContext('2d');
			// 새로운 이미지 객체를 만듬
			var image = new Image();
			image.onload = function(){
				ctx.drawImage(image, 10, 10);
				// [L]상단제목
				ctx.font = 'bold 24px Pretendard';
				ctx.fillText('1차 사업',300, 67); // 1차사업
				ctx.font = 'bold 24px Pretendard';
				ctx.fillText('(300명)', 400, 67); // 1차사업
				// [R]상단제목
				ctx.font = 'bold 24px Pretendard';
				ctx.fillText('2차 사업',600, 67); // 2차사업
				ctx.font = 'bold 24px Pretendard';
				ctx.fillText('(200명)', 700, 67); // 2차사업
				// [L]상단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차 불인정)', 175, 355); // 1차불인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('59명', 195, 325); // 1차불인정
				// [L]하단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차 인정)', 190, 580); // 1차인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('20명', 205, 550); // 1차인정
				// [R]전체
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(2차 인정)', 720, 465); // 2차인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('15명', 740, 435); // 2차인정
				// [R]상단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(2-1차 불인정)', 850, 420); // 2-1차불인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('14명', 880, 390); // 2-1차불인정
				// [R]하단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(2-2차 조사중)', 850, 565); // 2-2차조사중
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('13명', 880, 535); // 2-2차조사중
				// [C]1단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차 불인정+2-2차 조사중)', 420, 270); // 1차불인정+2-2차조사중
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('12명', 500, 240); // 1차불인정+2-2차조사중
				// [C]2단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차 불인정+2차 인정)', 430, 380); // 1차불인정+2차인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('11명', 500, 350); // 1차불인정+2차인정
				// [C]3단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차,2차 모두 인정)', 452, 490); // 1차인정+2차인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('10명', 500, 460); // 1차인정+2차인정
				// [C]4단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차 인정+2-1차 불인정)', 438, 555); // 1차인정+2-1차불인정
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('9명', 500, 525); // 1차인정+2-1차불인정
				// [C]5단
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('(1차 인정+2-1차 불인정+2-2차 조사중)', 385, 620); // 1차인정+2-1차불인정+2-2차조사중
				ctx.font = 'bold 16px Pretendard';
				ctx.fillText('8명', 500, 590); // 1차인정+2-1차불인정+2-2차조사중
			}
			// 이미지 URL
			image.src = getUrl('/images/adm/venn_graph.png');
 */
};

//===========================================================================//
// Chartjs 플러그인 DoughnutLabel : 가운데 텍스트 표시
//===========================================================================//
const DoughnutLabel  = {
	id: 'DoughnutLabel',
  	beforeDraw: function (chart) {
		if (!chart.config.options.plugins.DoughnutLabel)
			return;
	  	if (!chart.config.options.plugins.DoughnutLabel.center)
			return;

		//Get ctx from string
		var ctx = chart.ctx;

		//Get options from the center object in options
		var centerConfig = chart.config.options.plugins.DoughnutLabel.center;
		var fontSize  = centerConfig.fontSize || '50';
		var fontStyle = centerConfig.fontStyle || 'Arial';
		var txt = centerConfig.text;
		var color = centerConfig.color || '#000';
		var sidePadding = centerConfig.sidePadding || 20;
		var sidePaddingCalculated = (sidePadding/100) * (chart.innerRadius * 2)
		var lineHeight  = centerConfig.lineHeight || 1.2;

		//Start with a base font of 30px
		ctx.font = fontSize + "px " + fontStyle;

		//Get the width of the string and also the width of the element minus 10 to give it 5px side padding
		var stringWidth = ctx.measureText(txt).width;
		var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;

		// Find out how much the font can grow in width.
		var widthRatio = elementWidth / stringWidth;
		var newFontSize = Math.floor(30 * widthRatio);
		var elementHeight = (chart.innerRadius * 0.7);

		// Pick a new font size so it will not be larger than the height of label.
		var fontSizeToUse = Math.min(newFontSize, elementHeight);

		//Set font settings to draw it correctly.
		ctx.textAlign = 'center';
		ctx.textBaseline = 'middle';
		var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
		var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);

		//반도넛일 경우
		if (chart.config.options.rotation === Math.PI &&
			chart.config.options.circumference === Math.PI) {
		  	centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 1.3);
		}
		ctx.font = fontSizeToUse+"px " + fontStyle;
		ctx.fillStyle = color;
		//Draw text in center
		//ctx.fillText(txt, centerX, centerY);

		drawTextBox(ctx, txt, centerX, centerY, stringWidth, lineHeight)

	}
};
const COLORS = {
	BASIC:   ['#4dc9f6','#f67019','#f53794','#537bc4','#acc236','#166a8f','#00a950','#58595b','#8549ba'],
	STANDARD:['#0047BD','#0288D9','#009543','#00AB38','#9AF000','#FFB300','#FFCE00','#FFE63B','#EA0034','#FD4703','#FF822A','#8200AC','#B610BF','#CC72F5']
}

const CHART_COLORS = {
	red:    'rgb(255, 99, 132)',
	orange: 'rgb(255, 159, 64)',
	yellow: 'rgb(255, 205, 86)',
	green:  'rgb(75, 192, 192)',
	blue:   'rgb(54, 162, 235)',
	purple: 'rgb(153, 102, 255)',
	grey:   'rgb(201, 203, 207)'
};
function color(index, code) {
	return COLORS[code][index % COLORS[code].length];
};

function shuffleColors(array) {
	array.sort(function(num1, num2) {
		return Math.random() - 0.5;
	});
	return array;
}

function drawTextBox(ctx, text, x, y, fieldWidth, spacing) {
  var line = "";
  var fontSize = parseFloat(ctx.font);
  var currentY = y;
  ctx.textBaseline = "top";
  for(var i=0; i<text.length; i++) {
    var tempLine = line + text[i];
    var tempWidth = ctx.measureText(tempLine).width;

    if (tempWidth < fieldWidth && text[i] != '\n') {
      line = tempLine;
    }
    else {
      ctx.fillText(line, x, currentY);
      if(text[i] != '\n') line = text[i];
      else line = "";
      currentY += fontSize*spacing;
    }
  }
  ctx.fillText(line, x, currentY);
  ctx.stroke();
}

//===========================================================================//
// Chartjs 공통 유틸 함수
//===========================================================================//
$.chartUtil = {
	// 공통 데이터레이블 옵션
	getLabelOptions: function() {
		return {
			display: true,
			/*
			color:   'black',
			anchor:  'end',
            align:   'start',
			font:    {size:14, weight: 'bold'}
			*/
	        color:   'white',
	        font:    {size:14, weight: 'bold'},
	        offset:  0,
            anchor:  'center',
	        padding: 0
		};
	},
	// 공통 데이터셋레이블 옵션
	getLabelSetOptions: function() {
		return {
			labels: {
				name: {
				  align: 'bottom',
				  font: {size: 14},
				  padding: 10,
				  formatter: function(value, ctx) {
				    return ctx.chart.data.labels[ctx.dataIndex];
				  }
				},
				value: {
				  align: 'top',
				  backgroundColor: 'white',
				  borderColor:     'white',
				  borderWidth:  2,
				  borderRadius: 2,
				  padding:      2,
				  color: function(ctx) {
				    return ctx.dataset.backgroundColor;
				  },
				  formatter: function(value, ctx) {
				    return value+'명';
				  }
				}
			}
		};
	},
	// 공통 타이틀 옵션 (2.9 버전용)
	getTitleOptionsOldVersion: function(title) {
		return {
			display:    true,
			text:       title,
			fontSize:   24,
			fontStyle:  'bold',
			fontFamily: 'Pretendard'
		};
	},
	// 공통 타이틀 옵션
	getTitleOptions: function(title) {
		return {
			display: true,
			text:    title,
			font:    {size:24, weight:'bold', family: 'Pretendard'}
		};
	},
	// 공통 서브타이틀 옵션
	getSubtitleOptions: function(subtitle) {
		return {
			display: true,
	        text:    subtitle,
			padding: {bottom: 10},
			font:    {size:20, weight:'bold', family: 'Pretendard'}
		};
	},
	// 공통 Legend 옵션
	getLegendOptions: function() {
		return {
			display: true,
	        position: 'top',
			labels: {
				font:    {size:16, weight:'normal', family: 'Pretendard'}
			}
		};
	},
};


(function(){
    /**
     *  Extensions of CanvasRenderingContext2D functions
     *  1. Override CanvasRenderingContext2D.filltext function with lineBreak(\n) support.
     *  2. Override CanvasRenderingContext2D.measureText function to multiLines support.
     *
     *  sanguneo on 16. 5. 10.
    if(CanvasRenderingContext2D.prototype.sanguneo) return;
    var orig_fillText = CanvasRenderingContext2D.prototype.fillText;
    CanvasRenderingContext2D.prototype.fillText = function(){
        if(typeof arguments[0] === 'number'){
            arguments[0] += '';
        }
        var fontSize = parseInt(this.font);
        var textSplit = arguments[0].split('\n');
        arguments[2] -= fontSize;
        for (var idx=0; idx< textSplit.length; idx++){
            arguments[0] = textSplit[idx];
            arguments[2] += fontSize;
            orig_fillText.apply(this, arguments);
        }
    }
    var orig_measureText = CanvasRenderingContext2D.prototype.measureText;
    CanvasRenderingContext2D.prototype.measureText = function(){
        if(typeof arguments[0] === 'number'){
            arguments[0] += '';
        }
        var textSplit = arguments[0].split('\n');
        var textMax = '';
        for (var idx=0; idx< textSplit.length; idx++){
            textMax = textSplit[idx].length > textMax.length ? textSplit[idx] : textMax;
        }
        arguments[0] = textMax;
        return orig_measureText.apply(this, arguments); // return longest text width
    }

    CanvasRenderingContext2D.prototype.sanguneo = true;
     */
})();
