/**
******************************************************************************************
*** 파일명 : viewEvnpAffc.js
*** 설명글 : GIS 환경 오염 영향분석 화면 스크립트
***
*** -----------------------------	Modified Log   --------------------------------------
*** ver			 date				author				  description
*** --------------------------------------------------------------------------------------
*** 1.0			 2023.01.02		  	DHC						
******************************************************************************************
**/


// 지도 정보 (크롬 console창 map.getLayers() 등 사용가능.)
var map;
var innerPopup;

// 팝업 상세정보 표출
//--------------------------------------------------------//
function popupDetail(id, name, addr, dclr, gapDstnc, lon, lat){
	
	const container = document.getElementById('popup');
	const content = document.getElementById('popup-content');
	const closer = document.getElementById('popup-closer');
	
	// 팝업 초기화
	content.innerHTML = '';  
	// 팝업 영역
    content.innerHTML += '<p class="ol-popup-header"> ID: '+id+'</p>'+
    '<div>' +
       '<p> <span class="popupBorder">피해자</span>: '+name+'</p>'+
       '<p> <span class="popupBorder">초본주소</span>: '+addr+'</p>'+
       '<p> <span class="popupBorder">신고년도</span>: '+dclr+''+'</p>'+
       '<p> <span class="popupBorder">이격거리</span>: '+gapDstnc+'</p>'+
       '<p> <span class="popupBorder">피해범위 대상 여부</span>: '+''+'</p>'+
    '</div>';
    content.innerHTML += '<a style="cursor:pointer" onclick="popupList('+ lon +',' + lat + ')">목록</a>';
    
}

// 팝업 목록 표출
//--------------------------------------------------------//
function popupList(lon, lat){
	
	const container = document.getElementById('popup');
	const content = document.getElementById('popup-content');
	const closer = document.getElementById('popup-closer');
	
	 var params = {
			 lon: lon,
			 lat: lat
	 };
	
	 // 클릭한 팝업 list에서 상세정보 -> 목록 클릭시
	 $.ajaxUtil.ajaxLoad(
			 getUrl('/adm/gis/getCoordinateByOnclick.do'), 
			 params,
			 function(result) {
				 //목록이 없으면 return
				 if(result.Data == null) return;
				 // 포인트 곂치지 않을때 피해자 1명 팝업표출
				 if(result.Data.length == 1){ 
					 var data = result.Data[0];
				               
				     content.innerHTML = '<p class="ol-popup-header"> ID: '+feature.N.id+'</p>'+
				     	'<div>' +
				     		'<p> <span class="popupBorder">피해자</span>: '+data.sufrerNm+'</p>'+
				            '<p> <span class="popupBorder">초본주소</span>: '+data.abstrctAddr+'</p>'+
				            '<p> <span class="popupBorder">신고년도</span>: '+data.dclrYr+'년'+'</p>'+
				            '<p> <span class="popupBorder">이격거리</span>: '+data.gapDstnc+'km'+'</p>'+
				            '<p> <span class="popupBorder">피해범위 대상 여부</span>: '+''+'</p>'+
				        '</div>';
				     
				 } else { //포인트 곂칠시 피해자 목록 list
					 var data = result.Data;
					 innerPopup = '<div><p class="ol-popup-header">피해자 목록</p>';
					 
					 innerPopup += '<div style="overflow-y: auto; height:200px;">';
					 
				      data.forEach(function(el, i){
				          var sn = el.sn;
				          innerPopup += '<div id="'+el.sn+'" style="cursor:pointer;" onclick="popupDetail(\'' + el.idntfcId + '\',\'' + el.sufrerNm + '\',\'' +  el.abstrctAddr + '\',\'' + el.dclrYr + '\',\'' + el.gapDstnc + '\',\'' + lon + '\',\'' + lat + '\');"><span class="victim">피해자</span>: '+
				          	el.sufrerNm+ '<br><span class="abstrAddr">초본주소</span>: '+ el.abstrctAddr +'</div>' + '<hr class="popupHr"/>';
				      })      
				      innerPopup += '</div></div>';
				      content.innerHTML = innerPopup;
				}
			 }
	 );
}

$(function() {
	
	// 조회결과 - 현재선택한 idntfcId
	var currIdntfcId;
	
	// 팝업영역 HTML
	const container = document.getElementById('popup');
	const content = document.getElementById('popup-content');
	const closer = document.getElementById('popup-closer');
	
	// 팝업창 overlay
	const overlay = new ol.Overlay({
		  element: container
	});
	
	// 팝업창 'X' 클릭시
	closer.onclick = function () {
		overlay.setPosition(undefined);
		closer.blur();
		return false;
	};
	
	// 일반지도 (기본 화면)
	var colorMap = new ol.source.XYZ({ 
		url:'https://api.vworld.kr/req/wmts/1.0.0/54B684CE-EFD5-3582-9EED-7CBDDFEA0980/Base/{z}/{y}/{x}.png',
		crossOrigin: 'anonymous'
	});

	// 흑백지도
	var grayMap = new ol.source.XYZ({
		url:'https://api.vworld.kr/req/wmts/1.0.0/54B684CE-EFD5-3582-9EED-7CBDDFEA0980/white/{z}/{y}/{x}.png',
		crossOrigin: 'anonymous',
	});

	// 위성지도(우측 첫번째 버튼 클릭시)
	var satelliteMap = new ol.source.XYZ({
		url: 'https://api.vworld.kr/req/wmts/1.0.0/54B684CE-EFD5-3582-9EED-7CBDDFEA0980/Satellite/{z}/{y}/{x}.jpeg',
		crossOrigin: 'anonymous',
	})
	
	// 기본 지도 설정
	const analyMap =  new ol.layer.Tile({
		name:'analyMap',
		title:'analyMap',
		layerId:'analyMap',
		visible:true,
		source : new ol.source.XYZ({
			//일반지도 url
			url: 'https://api.vworld.kr/req/wmts/1.0.0/54B684CE-EFD5-3582-9EED-7CBDDFEA0980/Base/{z}/{y}/{x}.png',
			crossOrigin: 'anonymous'
		})
	});

	// map 기본설정 
	map = new ol.Map({
		target : 'map',
		view : new ol.View({
			//좌표계
			projection : 'EPSG:3857',
			//로드시 center
			center : new ol.geom.Point([127.803955078125, 36.66412353515625]).transform('EPSG:4326', 'EPSG:3857').getCoordinates(),
			zoom : 8,
			minZoom : 7,
			maxZoom : 19,
		}),
		//기본 지도 layer
		layers : [analyMap],
		//팝업 overlay
		overlays: [overlay]	
	});

	// 피해지역 원 객체
	const circleLayer = new ol.layer.Vector({
		name: 'pollCircleLayer',
		style: [
			new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: '#4AA8D8',
					width: 2
				}),
				fill: new ol.style.Fill({
					color: 'rgba(74, 168, 216, 0.4)'
				}),
		})]
	});
	
	// 피해자 위치
	const pointLayer = new ol.layer.Vector({
		name: 'pollPointLayer',
	})
	
	// 팝업창 검색
	var select = new ol.interaction.Select({
	    layers: [pointLayer],
	});
	
	// select interaction 추가
	map.addInteraction(select);
	
	// point 클릭시 event
	select.getFeatures().on("add", function (e) {

		var feature = e.element;
		var coordi =  feature.getGeometry().getCoordinates();
		var x = coordi[0];
		var y = coordi[1];

		//style reset
		resetPointStyle();
		selectPointById(currIdntfcId);   
         //zoom 확대 좌표이동
         //map.getView().setZoom(12);
         //map.getView().setCenter(cordi);
         
         //클릭시 빨간색 색상전환
		 feature.setStyle(new ol.style.Style({ 
			 image: new ol.style.Circle(({ 
				 radius: 8,
		         fill: new ol.style.Fill({
		        	 color: '#ff0000'
		          }),
		         stroke: new ol.style.Stroke({
		         color: 'white',
		         width: 1,
		         })
		     })),
		     zIndex: Infinity
		 }));
		        
		 var lonlat = ol.proj.transform(coordi, 'EPSG:3857', 'EPSG:4326');
   
		 var lon = Number(lonlat[0]).toFixed(5);
		 var lat = Number(lonlat[1]).toFixed(5);
		 var sufrerNo= feature.N.name;
        
		 var params = {
				 lon: lon,
				 lat: lat,
				 sufrerNo: sufrerNo
		 };
		        
		 // 클릭한 곳 피해자 정보 불러오기
		 $.ajaxUtil.ajaxLoad(
				 getUrl('/adm/gis/getCoordinateByOnclick.do'), 
				 params,
				 function(result) {
							
					 if(result.Data == null) return;
							
					 if(result.Data.length == 1){ // 포인트 곂치지 않을때 피해자 1명 팝업표출
						 var data = result.Data[0];
						 
						 //팝업 초기화
						 content.innerHTML = '';
						 //팝업 영역
					     content.innerHTML = '<p class="ol-popup-header"> ID: '+feature.N.id+'</p>'+
					     	'<div>' +
					     		'<p> <span class="popupBorder">피해자</span>: '+data.sufrerNm+'</p>'+
					            '<p> <span class="popupBorder">초본주소</span>: '+data.abstrctAddr+'</p>'+
					            '<p> <span class="popupBorder">신고년도</span>: '+data.dclrYr+'년'+'</p>'+
					            '<p> <span class="popupBorder">이격거리</span>: '+data.gapDstnc+'km'+'</p>'+
					            '<p> <span class="popupBorder">피해범위 대상 여부</span>: '+''+'</p>'+
					        '</div>';
					     $("#popup").css("display", 'block');
					     //팝업 좌표 설정
					     overlay.setPosition(coordi);
					 } else { //포인트 곂칠시 피해자 목록 list
						 //팝업 초기화
						 content.innerHTML = '';    
						 
						 var data = result.Data;
						 innerPopup = '<div><p class="ol-popup-header">피해자 목록</p>';
						 innerPopup += '<div style="overflow-y: auto; height:200px;">';
						 
					     data.forEach(function(el, i){
					          var sn = el.sn;
					          innerPopup += '<div id="'+el.sn+'" style="cursor:pointer;" onclick="popupDetail(\'' + feature.N.id + '\',\'' + el.sufrerNm + '\',\'' +  el.abstrctAddr + '\',\'' + el.dclrYr + '\',\'' + el.gapDstnc + '\',\'' + lon + '\',\'' + lat + '\');"><span class="victim">피해자</span>: '+
					          	el.sufrerNm+ '<br><span class="abstrAddr">초본주소</span>: '+ el.abstrctAddr +'</div>' + '<hr class="popupHr"/>';
					     })      
					     innerPopup += '</div></div>';
					     content.innerHTML = innerPopup;
					     $("#popup").css("display", 'block');
					     overlay.setPosition(coordi);
					     
					}
				}
		 	);
	});
	
	// 이전 circle 레이어 있는지 확인. 있을시 remove
	//--------------------------------------------------------//
	function checkCircleLayer(){
		//circle Layer 있는지 확인.
		map.getLayers().forEach(function(el) {
			if(el != undefined){
				if (el.get('name') === 'pollCircleLayer') {
					map.removeLayer(circleLayer);
			    }
			}
		})
	}
	
	// 이전 point 레이어 있는지 확인. 있을시 remove
	//--------------------------------------------------------//
	function checkPointLayer(){
		//point layer 있는지 확인.
		map.getLayers().forEach(function(el) {
			if(el != undefined){
				if(el.get('name') === 'pollPointLayer'){  
					map.removeLayer(pointLayer);
				}
			}
		})
	}
	
	// 피해지역기준 원그리기
	//--------------------------------------------------------//
	function drawPollCircle(bizArea, idntfcId, sufrerNm){
		//이전 circleLayer 확인, 있을시 DELETE
		checkCircleLayer();
		
		var pollCenterLon;
		var pollCenterLat;
		var pollCenterAll = [];
		
		var params = null;
		
		// features를 담을 source 선언
		var circleSource = new ol.source.Vector({projection: 'EPSG:3857'});
		
		//전체일 경우
		if(bizArea == '00'){
			$.ajaxUtil.ajaxLoad(
				getUrl('/adm/gis/getEnvpAffcPollLoc.do'), 
				params,
				function(result) {
					
					result.Data.forEach(function(e, i){
						
						var affcScopeCn; //최대 영향 범위
						var section = [];
						
						affcScopeCn = Number((e.affcScopeCn.replace("Km", ""))*1000/8)*10;
						
						
						section.push(affcScopeCn);   
						section.push(affcScopeCn/2);
						
						var pollCenterObj={
								lon : '',
								lat : '',
						}
						pollCenterObj['lon'] = e.lon;
						pollCenterObj['lat'] = e.lat;

						pollCenterAll.push(pollCenterObj);
						
						
						var pnt = new ol.geom.Point([e.lon, e.lat]).transform('EPSG:4326', 'EPSG:3857');

						var changePoints = pnt.getCoordinates();

						for(let k of section){
							var circle = new ol.geom.Circle(changePoints, k);
					    	var lowpoly = ol.geom.Polygon.fromCircle(
					    	    /* WGS84 Sphere */
					    	    circle,
					    	    60,
					    	    90
					    	    );
					    	var circleFeature = new ol.Feature(lowpoly);
					    	
					    	circleFeature.setStyle(new ol.style.Style({ 
					            stroke: new ol.style.Stroke({
					                color: '#50bcdf',
					                width: 4
					            }),
					            text: new ol.style.Text({
					                font: '12px Verdana',
					                scale: 1,
					                text: (k/10*8/1000).toString()+"km",
					                placement: 'line',
					                fill: new ol.style.Fill({ color: 'black' }),
					                stroke: new ol.style.Stroke({ color: 'white', width: 3 })
					            })
							 }));
					    	
					    	
							circleSource.addFeatures(([circleFeature]));
						}		
					})
				}
			);
		} else{ //전체검색 아닐경우 지역별 한개의 오염원 좌표를 가짐
			
			params = {
				bizArea: bizArea
			};
			$.ajaxUtil.ajaxLoad(
				getUrl('/adm/gis/getEnvpAffcOnePollLoc.do'), 
				params,
				function(result) {	
					
					var affcScopeCn = Number((result.Data[0].affcScopeCn.replace("Km", ""))*1000/8)*10;
					
/*					affcScopeCn = Number((e.affcScopeCn.replace("Km", ""))*1000/8)*10;
					
					section = [affcScopeCn, affcScopeCn/2];
					*/
					
					var section = [affcScopeCn, affcScopeCn/2];
					
					pollCenterLon = result.Data[0].lon;
					pollCenterLat = result.Data[0].lat;
					
					
					var pnt = new ol.geom.Point([pollCenterLon, pollCenterLat]).transform('EPSG:4326', 'EPSG:3857');
					var changePoints = pnt.getCoordinates();
					
					for(let i of section){
						var circle = new ol.geom.Circle(changePoints, i);  //좌표, 반경 넓이
						
				    	var lowpoly = ol.geom.Polygon.fromCircle(
					    	    /* WGS84 Sphere */
					    	    circle,
					    	    60,
					    	    90
					    	    );
						
						var circleFeature = new ol.Feature(lowpoly);
						
						circleFeature.setStyle(new ol.style.Style({ 
				            stroke: new ol.style.Stroke({
				                color: '#50bcdf',
				                width: 4
				            }),
				            text: new ol.style.Text({
				                font: '12px Verdana',
				                scale: 1,
				                text: (i/10*8/1000).toString()+"km",
				                placement: 'line',
				                fill: new ol.style.Fill({ color: 'black' }),
				                stroke: new ol.style.Stroke({ color: 'white', width: 3 })
				            })
						 }));
						
						circleSource.addFeatures(([circleFeature]));
					}
				}
			);
		} 
		
		
		//전체일 경우
		if(bizArea == '00'){
			//지도 좌표 및 center 설정
			map.getView().setZoom(8);
			map.getView().setCenter(new ol.geom.Point([127.803955078125, 36.66412353515625]).transform('EPSG:4326', 'EPSG:3857').getCoordinates());
		
		} else if(bizArea != '00'){
			//지도 좌표 및 center 설정
			map.getView().setZoom(15);
			map.getView().setCenter(new ol.geom.Point([pollCenterLon, pollCenterLat]).transform('EPSG:4326', 'EPSG:3857').getCoordinates());
		

		}
		circleLayer.setSource(circleSource);
		map.addLayer(circleLayer);
		//point 그리는 함수 호출
		drawPollPoint(bizArea, idntfcId, sufrerNm);
	}
		
	// 피해자 위치 점찍기
	//--------------------------------------------------------//
	function drawPollPoint(bizArea, idntfcId, sufrerNm){
		
		//조회한 피해자 목록
		var params = {bizArea: bizArea
				, idntfcId: idntfcId
				, sufrerNm: sufrerNm
		};
		
		$.ajaxUtil.ajaxLoad(
			getUrl('/adm/gis/getListEnvpAffcLoc.do'), 
			params,
			function(result) {
					
				if(!result.Data) return;	
			
				var pointSource = new ol.source.Vector({
					projection: 'EPSG:3857',  
				});
					
					
				$.each(result.Data, function(index, p){
					var pointFeature = new ol.Feature({
						id: p.idntfcId,
				        name: p.sufrerNo,
				        title: p.sn,
				    	projection : 'EPSG:3857',	
				    	geometry: new ol.geom.Point([p.lon, p.lat]).transform('EPSG:4326', 'EPSG:3857'),
				    });
				     
				     pointFeature.setStyle(new ol.style.Style({ 
				    	 image: new ol.style.Circle(({ 
					    	 radius: 8,
					    	 fill: new ol.style.Fill({
					    		 color: '#ffd400'
					    	 }),
					    	 stroke: new ol.style.Stroke({
					    		 color: 'white',
					    	     width: 1,
					    	 })
				    	 })),
				     }));
				     pointSource.addFeature(pointFeature);
				})	
					
				//레이어 추가
				checkPointLayer()
				pointLayer.setSource(pointSource);
				map.addLayer(pointLayer);
			}
		);
	}
	
	// point style 전체 초기화.
	//--------------------------------------------------------//
	function resetPointStyle(){
		
		var features = pointLayer.getSource().getFeatures();
		
		features.forEach(function(e, i){
			e.setStyle(new ol.style.Style({ 
				image: new ol.style.Circle(({ 
					radius: 8,
	    	        fill: new ol.style.Fill({
	    	        	color: '#ffd400'
	    	        }),
	    	     stroke: new ol.style.Stroke({
	    	    	 color: 'white',
	    	         width: 1,
	    	     })
	    	    })),
			}));
		})
	}
	
	// 검색조건 -> GIS 버튼 클릭시 (파란색)
	//--------------------------------------------------------//
	function selectPointById(idntfcId){
		//색상 초기화
		resetPointStyle();
		var features = pointLayer.getSource().getFeatures();
		
		features.forEach(function(e, i){		
			//조회결과 gis선택 -> 색상변경할 feature
			if(e.N.id == idntfcId){
				e.setStyle(new ol.style.Style({ 
					image: new ol.style.Circle(({ 
						radius: 8,
						fill: new ol.style.Fill({
							color: '#0067a3'
		    	        }),
		    	        stroke: new ol.style.Stroke({
		    	        	color: 'white',
		    	        	width: 1,
		    	        })
					})),
		    	    zIndex: 200
				}));
			}
		})
	}
	
	// 검색조건 -> GIS 버튼 클릭시 좌표 이동
	//--------------------------------------------------------//
	function centerPointById(idntfcId){
		
		var features = pointLayer.getSource().getFeatures();
		
		features.forEach(function(e, i){		
			// 조회결과 gis선택 -> 색상변경할 feature
			if(e.N.id == idntfcId){
				// zoom 확대, 좌표이동
				var changeCenter = e.getGeometry().getCoordinates();
				map.getView().setZoom(15);
				map.getView().setCenter(changeCenter);
				return;
			}
		})
	}
	
	//========================================================//
	// 화면 스크립트 내에 사용할 객체,상수 정의
	//--------------------------------------------------------//
	let P_GRID   = $('#appGrid');			// 그리드 객체
	let P_SFORM  = $('#searchForm');		// 검색폼 객체
	let P_PFORM  = $('#personForm');		// 피해자 정보 영역
	let P_AGRID  = $('#abstrctGrid');		// 대상자조회 목록
	let GIS_SELECT = false;
	let GIS_SELECT2 = false;                 // 피해지역 정보 gis 버튼
	
	P_GRID.datagrid({
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
		// 그리드 단일행만 선택여부
		singleSelect: true,
		// 한 페이지 출력수
		pageSize: 30,
		// 칼럼정의
		columns: [[
			{field:'idntfcId',width:100,halign:'center',align:'center',title:'식별ID'},
			{field:'sufrerNm',width:70,halign:'center',align:'center',title:'성명'},
			{field:'live',width:70,halign:'center',align:'center',title:'거주이력'},
			{field:'gis',width:60,halign:'center',align:'center',title:'GIS'}
		]],
		
		onLoadSuccess: function(data){
			
			if(data.total != 0){

				var gisIcon = $(".gis-result .datagrid-body td:nth-child(4)");
				
				gisIcon.css("background-image", "url('../../images/gis/i-gis.svg')");
				gisIcon.css("background-position", "center");
				gisIcon.css("background-repeat", "no-repeat");
				gisIcon.css("cursor", "pointer");
				
				gisIcon.on("click", function () {
					var tr = $(this).parent();
					var idntfcId;
					
					tr.children().each(function(index, item){			
						if($(item).attr("field") == "idntfcId"){
							idntfcId = $(item).text();
							return false;
						}
					});
					
					//idntfcId (식별ID로 색상 변화 할 point들 검색)
					selectPointById(idntfcId);
					//좌표 이동
					centerPointById(idntfcId);
					GIS_SELECT = true;
					
				});	
			}
			
		},
		onSelect: doSelect
	});
	
	P_AGRID.datagrid({
		fit: true,
		// 그리드 결과데이터 타입
		contentType: 'application/json',
		// 그리드 결과데이터 타입
		dataType: 'json',
		// 그리드 데이터연동 방식
		method: 'POST',
		// 그리드 행번호 표시여부
		rownumbers:true,
		// 그리드 단일행만 선택여부
		singleSelect: true,
		// 체크박스 KEY값필드
		idField:'sn',
		// 칼럼정의
		columns: [[
			{field:'abstrctAddr',width:440,halign:'center',align:'left',title:'주소'},
			{field:'dclrYr',width:176,halign:'center',align:'center',title:'신고년도'},
			{field:'dclrResn',width:146,halign:'center',align:'center',title:'사유'},
			{field:'gapDstnc',width:238,halign:'center',align:'right',title:'이격거리(km)'},
			{field:'gis',width:149,halign:'center',align:'right',title:'GIS'},
		]],
		onLoadSuccess: function(data){
			var gisIcon = $(".gis-map-layer-wrap .datagrid-row td:nth-child(5)");
		
			gisIcon.css("background-image", "url('../../images/gis/i-gis.svg')");
			gisIcon.css("background-position", "center");
			gisIcon.css("background-repeat", "no-repeat");
			gisIcon.css("cursor", "pointer");
			
			gisIcon.on("click", function () {
				GIS_SELECT2 = true;
			});
			
			
		},
		// 행선택시 상세조회
		onSelect: doAbstrctSelect
	});

	// 피해자 상세조회
	//-------------------------------//
	function doSelect(index, row){
		var params = {
				sufrerNo: row['sufrerNo']
		};	
		$.ajaxUtil.ajaxLoad(
			getUrl('/adm/gis/getIdntfc.do'), 
			params,
			function(result) {
				var data = result.Data;
				P_SELECT = data;
				if (data) {
					currIdntfcId = data['idntfcId'];
						
					// 개인정보 데이터로드
					$.formUtil.toHtml(P_PFORM, {
						idntfcId : data['idntfcId'],
						sufrerNm : data['sufrerNm'],
						live     : data['live'],
						bizArea  : data['bizArea'],
						frstPollutnOcrnYr: data['frstPollutnOcrnYr'] + "년",
						lastPollutnOcrnYr: data['lastPollutnOcrnYr'] + "년",
							
					}, 's_');
						
					//초본 리스트 조회
					doAbstrctSearch();
				}
			}
		);
	}
	
	// 피해자 초본주소 상세조회
	//-------------------------------//
	function doAbstrctSelect(index, row) {

		if(GIS_SELECT2 === true){

			content.innerHTML = '';
  
			var idntfcId = $('#s_idntfcId').text();
			var dclrYr = row.dclrYr; //신고년도
			var lon = row.lot;
			var lat = row.lat;
			
			var pnt = new ol.geom.Point([lon, lat]).transform('EPSG:4326', 'EPSG:3857');
			var changePoints = pnt.getCoordinates();
			
			var features = pointLayer.getSource().getFeatures();
		
			features.forEach(function(e, i){

				var lonlat = ol.proj.transform(e.getGeometry().getCoordinates(), 'EPSG:3857', 'EPSG:4326');
				
				if(e.N.title == row.sn){
				
					resetPointStyle();
					
					if(GIS_SELECT == true){
						selectPointById(idntfcId);	
					}
					
					//zoom 확대 좌표이동
					map.getView().setZoom(15);
					map.getView().setCenter(changePoints);
					
					e.setStyle(new ol.style.Style({ 
						image: new ol.style.Circle(({ 
			    	    radius: 8,
			    	    fill: new ol.style.Fill({
			    	    	color: '#ff0000'
			    	    }),
			    	    stroke: new ol.style.Stroke({
			    	    	color: 'white',
			    	        width: 1,
			    	     })
						})),
						zIndex: Infinity
			    	}));
					
					sufrerNm = $('#s_sufrerNm').text();
					content.innerHTML = '<p class="ol-popup-header"> ID: '+idntfcId+'</p>'+
				  		'<div>' +
				  			'<p> <span class="popupBorder">피해자</span>: '+sufrerNm+'</p>'+
					  		'<p> <span class="popupBorder">초본주소</span>: '+row.abstrctAddr+'</p>'+
					  		'<p> <span class="popupBorder">신고년도</span>: '+row.dclrYr+'</p>'+
					  		'<p> <span class="popupBorder">이격거리</span>: '+row.gapDstnc+'</p>'+
					  		'<p> <span class="popupBorder">피해범위 대상 여부</span>: '+''+'</p>'+
				  		'</div>';
					$("#popup").css("display", 'block');
					overlay.setPosition(changePoints);
				
				}
			})	
		}
		GIS_SELECT2 = false; 
	}
	
	// 피해자 상세정보 리셋
	//--------------------------------------------------------//
	function doClear() {
		// 피해자정보 초기화
		$.formUtil.toHtmlReset(P_PFORM, 's_');
		P_PFORM.form('reset');
		// 상세조회 데이터 제거
		P_SELECT = false;
		//doAbstrctAddrClear();
		return false;
	}
	
	// 피해자정보 검색처리
	//--------------------------------------------------------//
	function doSearch() {
		// 선택된 항목 CLEAR
		P_GRID.datagrid('clearSelections');
		// 검색폼 데이터 객체화
		var obj = P_SFORM.serializeObject();
		// 그리드 목록조회 URL
		P_GRID.datagrid('options')['url'] = getUrl('/adm/gis/getlistIdntBizArea.do');
		// 검색폼 그리드 검색
		P_GRID.datagrid('load', obj);
		
		drawPollCircle(obj.bizArea, obj.idntfcId, obj.sufrerNm);
		
		GIS_SELECT = false;
		return false;
	}

	// 피해자정보 검색리셋
	//--------------------------------------------------------//
	function doReset() {
		// 상세조회 데이터 초기화
		doClear();
		// 초본주소 목록 초기화
		doAbstrctSearch();
		// 검색폼 입력데이터 초기화
		P_SFORM.form('reset');
		//레이어 초기화
		checkCircleLayer();
		checkPointLayer();
		// 검색폼 그리드 검색 처리
		doSearch();
		
		return false;
	}
	
	// 초본 리스트 조회
	//--------------------------------------------------------//
	function doAbstrctSearch() {
		let params = {
			sufrerNo : P_SELECT['sufrerNo'],
		};
		// 선택된 항목 CLEAR
		P_AGRID.datagrid('clearSelections');
		// 그리드 목록조회 URL
		P_AGRID.datagrid('options')['url'] = getUrl('/adm/gis/getlistAbstrctAddr.do');
		// 검색폼 그리드 검색
		P_AGRID.datagrid('load', params);
		return false;
	}

	// 초기검색실행
	doSearch();
	
	// 피해자 조회
	$('#btnSearch').bind('click', doSearch);
	// 피해자 조회 검색 리셋
	$('#btnReset').bind('click', doReset);

	sideReset();
	sideTool();
	mapZoomControl();
	saveMap();
	//레이어 추가
	map.addLayer(layerVectorLine);
	map.addLayer(layerVectorCircle);
	map.addLayer(layerVectorPolygon);
	
	// 우측 첫번째 버튼 - 지도 변경함수
	//--------------------------------------------------------//
	function mapChange() {
		$(".side .base").on("click", function() {
			$(this).toggleClass("on");
			if($(this).hasClass("on")) {
				analyMap.setSource(colorMap);
				
			} else {
				analyMap.setSource(satelliteMap); //satelliteMap, grayMap
			}
		});
	}
	
	mapChange();
})


// gis 우측 tool
let sourceLine = new ol.source.Vector();
let sourceCircle = new ol.source.Vector();
let sourcePolygon = new ol.source.Vector();

var sketch;
var measureTooltipElement;
var measureTooltip;

// 선 그리기 레이어
const layerVectorLine = new ol.layer.Vector({
    source: sourceLine,
    style : new ol.style.Style({
        fill: new ol.style.Fill({
	        color: 'rgba(255, 0, 127, 1)',
	    }), 
	    stroke: new ol.style.Stroke({
	        color: 'rgba(255, 0, 127, 1)',
	        width: 3,
	    }),
	    image: new ol.style.Circle({
	        radius: 5,
	        stroke: new ol.style.Stroke({
	            color: 'rgba(255, 0, 255, 1)',
	        }),
	        fill: new ol.style.Fill({
	            color: 'rgba(255, 255, 255, 1)',
	        }),
	    }),
	}),
});


// 반경 그리기 레이어
const layerVectorCircle = new ol.layer.Vector({
  source: sourceCircle,
	style : new ol.style.Style({
	    fill: new ol.style.Fill({
		    color: 'rgba(127, 0, 255, 0.2)',
		}), 
		stroke: new ol.style.Stroke({
		    color: 'rgba(127, 0, 255, 0.5)',
		    width: 3,
		}),
		image: new ol.style.Circle({
		    radius: 5,
		    stroke: new ol.style.Stroke({
		        color: 'rgba(127, 0, 255, 1)',
		    }),
		    fill: new ol.style.Fill({
		        color: 'rgba(127, 0, 255, 0.7)',
		    }),
		}),
	}),
});

// 면적 구하기 레이어
const layerVectorPolygon = new ol.layer.Vector({
  source: sourcePolygon,
	style : new ol.style.Style({
	    fill: new ol.style.Fill({
		    color: 'rgba(0, 0, 255, 0.2)',
		}), 
		stroke: new ol.style.Stroke({
		    color: 'rgba(0, 0, 255, 1)',
		    width: 3,
		}),
		image: new ol.style.Circle({
		    radius: 5,
		    stroke: new ol.style.Stroke({
		        color: 'rgba(0, 0, 255, 1)',
		    }),
		    fill: new ol.style.Fill({
		        color: 'rgba(255, 255, 255, 0.7)',
		    }),
		}),
	}),
});

// 거리값 팝업창
//--------------------------------------------------------//
function createMeasureTooltip(popupId) {
    if (measureTooltipElement) {
	    measureTooltipElement.parentNode.removeChild(measureTooltipElement);
	}
	measureTooltipElement = document.createElement('div');
	measureTooltipElement.className = 'ol-tooltip ol-tooltip-measure';
	measureTooltip = new ol.Overlay({
	    id: popupId,
	    element: measureTooltipElement,
	    offset: [0, 0],
	    positioning: 'bottom-center',
	    stopEvent: false,
	    insertFirst: false,
	});
    map.addOverlay(measureTooltip);
}


// 팝업창 지우기 클릭시 -> 선택된 레이어 + 팝업 제거
//--------------------------------------------------------//
function deletePoly(selectedFeatureID, selectNum, popupId) {
    
	//line, circle, polygon 선택시 레이어 담을 변수
	var source;
	// source에 대한 feature
	var features;
	  
	if(selectNum == 1) {
        source = sourceLine;
		features = sourceLine.getFeatures();
	  }
	else if(selectNum == 2) {
        source = sourceCircle;
		features = sourceCircle.getFeatures();
	}
	else if(selectNum == 3) {
	    source = sourcePolygon;
		features = sourcePolygon.getFeatures();
	}

	if(features != null && features.length > 0) {
        for(x in features) {
            var properties = features[x];
			var id = properties.getId();
			  
		    map.getOverlays().getArray().forEach(function(overlay) {
	            if(overlay.getId() == popupId) {
	        	    map.removeOverlay(overlay);
	        	}
	        });
		    
			if(id == selectedFeatureID) {
		        source.removeFeature(features[x]);       
		    
		    }
	    }
	}
}

// 거리 레이어 연산
//--------------------------------------------------------//
function formatLength(line) {
    const length = ol.Sphere.getLength(line);
    let output;
	if (length > 100) {
		output = Math.round((length / 1000) * 100) / 100 + ' ' + 'km';
	} 
	else {
		output = Math.round(length * 100) / 100 + ' ' + 'm';
	}
	return output;
}


// 면적 레이어 연산
//--------------------------------------------------------//
function formatArea(polygon) {
 var area = ol.Sphere.getArea(polygon);
	let output;
	if (area > 10000) {
		output = Math.round((area / 1000000) * 100) / 100 + ' ' + 'km<sup>2</sup>';
	} 
	else {
		output = Math.round(area * 100) / 100 + ' ' + 'm<sup>2</sup>';
	}
	return output;
}
	
// 반경 레이어 연산
//--------------------------------------------------------//
function formatCircle(circle) {
 var center = circle.getCenter();	
 let output;
	var radius = circle.getRadius();
		
	if(radius > 1000) {
	    output = Math.round((radius / 1000) * 100) / 100 + ' ' + 'km<sup>2</sup>';
	}
	else {
	    output = Math.round(radius * 100) / 100 + ' ' + 'm<supb>2</sup>';
	}
	return output;
}



let draw;
// 도구 기능 실행
//--------------------------------------------------------//
function addInteraction(e) {
    var type;
    var style;
    var source;
    	   
	if(e == 'line') {
        type='LineString';
        source = sourceLine;
        style = layerVectorLine.getStyle();
	}
	else if(e == 'polygon') {
		type='Polygon';
		source = sourcePolygon
		style = layerVectorPolygon.getStyle();
	}
	else if(e == 'circle') {
        type= 'Circle';
        source = sourceCircle;
        style = layerVectorCircle.getStyle();
	}
	
	if(draw) map.removeInteraction(draw);
	
	draw = new ol.interaction.Draw({
	    source: source,
	    type: type,
	    style: style,
    });    
	  
    map.addInteraction(draw);
    
    var popupId = sourceLine.getFeatures().length + sourceCircle.getFeatures().length + sourcePolygon.getFeatures().length + 1;
    
	createMeasureTooltip(popupId);
	
	let listener;
	draw.on('drawstart', function (evt) {

	    sketch = evt.feature;
	    
	    let tooltipCoord = evt.coordinate;

	    listener = sketch.getGeometry().on('change', function (evt) {
	        const geom = evt.target;
	        let output;
	        if (geom instanceof ol.geom.Polygon) {
	            output = '총 면적 ' + formatArea(geom);
	            tooltipCoord = geom.getLastCoordinate();
	        } 
	        else if (geom instanceof ol.geom.LineString) {
	            output = '총 거리 ' + formatLength(geom);
	            tooltipCoord = geom.getLastCoordinate();
	        } 
	        else if (geom instanceof ol.geom.Circle) {
	    	    output = '총 반경 ' + formatCircle(geom); 
	    	    tooltipCoord = geom.getLastCoordinate();
	        }
	        if(measureTooltipElement) measureTooltipElement.innerHTML = output;
	        if(measureTooltip) measureTooltip.setPosition(tooltipCoord);
	     
	    });
	  });

	  var selectedFeatureID;
	  
	  draw.on('drawend', function (evt) {
		  
		  var popupId = measureTooltip.getId();
		  var poly = evt.feature.getGeometry();
		  var count;
		  var selectNum;
		  
		  if(poly instanceof ol.geom.LineString) {
			  selectNum = 1;
		  }
		  else if(poly instanceof ol.geom.Circle) {
			  selectNum = 2;
		  }
		  else if(poly instanceof ol.geom.Polygon) {
			  selectNum = 3;
		  }	
		  count = sourceLine.getFeatures().length + sourceCircle.getFeatures().length + sourcePolygon.getFeatures().length + 1;
		  evt.feature.setId(count);
		  
		  var buildbutton;
		  buildbutton ='';
		  
		  measureTooltipElement.innerHTML = measureTooltipElement.innerHTML + buildbutton + "<div onclick='deletePoly(" + count + ","  + selectNum + "," + popupId + ")'>지우기</div>";;
	      measureTooltipElement.className = 'ol-tooltip ol-tooltip-static';
	      sketch = null;
	      measureTooltipElement = null;
	      ol.Observable.unByKey(listener);
	      map.removeInteraction(draw);
	  });
}

//우측 reset버튼 클릭시 EVENT
//--------------------------------------------------------//
function sideReset() {
	$(".sidebtn").on("click", function(){
		$(".sidenav").fadeToggle( function() {
			map.updateSize(); //size update
		});
	});
}

// 우측 버튼 클릭시 EVENT
//--------------------------------------------------------//
function sideTool() {
	$(".side .ruler, .side .radar, .side .crop").on("click", function() {
		if($(this).hasClass("ruler")) {
			addInteraction('line');	
		} else if($(this).hasClass("radar")) {
			addInteraction('circle');
		} else if($(this).hasClass("crop")) {
			addInteraction('polygon');
		}
	})
}

// 우측 +, - 클릭시 ZOOM 조절
//--------------------------------------------------------//
function mapZoomControl() {
	document.querySelector(".side .plus").addEventListener('click', function() {
		map.getView().setZoom(map.getView().getZoom()+1);
	});
	document.querySelector(".side .minus").addEventListener('click', function() {
		map.getView().setZoom(map.getView().getZoom()-1);
	});
}

// 지도 저장 (현재 x)
//--------------------------------------------------------//
function saveMap() {
	document.querySelector(".side .save").addEventListener("click", function() {
		map.updateSize();
		html2canvas(document.querySelector("#map"), {
			allowTaint: true,
			useCORS: true,
		}).then(function(canvas) {
			if (canvas.msToBlob) { //for IE 10, 11
				var blob = canvas.msToBlob();
				window.navigator.msSaveBlob(blob, "map.png");
			} else {
				var el = document.getElementById("save-img");
				el.href = canvas.toDataURL("image/png");
				el.download = 'map.png';
			}
			map.render();
			map.renderSync();
			map.updateSize();
			html2canvas(document.querySelector("#map"), {
				allowTaint: true,
				useCORS: true,
			}).then(function(canvas) {
				if (canvas.msToBlob) { //for IE 10, 11
					var blob = canvas.msToBlob();
					window.navigator.msSaveBlob(blob, "map.png");
				} else {
					var el = document.getElementById("save-img");
					el.href = canvas.toDataURL("image/png");
					el.download = 'map.png';
					el.click();
				}
			});
		});
	});	
}


