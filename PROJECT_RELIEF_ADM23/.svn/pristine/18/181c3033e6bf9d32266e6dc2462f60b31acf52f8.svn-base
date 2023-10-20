/**
 * 행정안전부 개발자센터 주소검색 API
 */

var ADDRESS = {};
var INPUTYN = "N";
var ACTION  = "http://www.juso.go.kr/addrlink/addrLinkUrl.do";
// 모바일웹인 경우
//var ACTION  = "https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do";

function doSetAddress(inputYn, address) {
	INPUTYN = inputYn;
	ADDRESS = address;
}

$(function() {
	if (INPUTYN == "Y") {
		opener.jusoCallBack( ADDRESS );
		window.close();
	}
	else {
		if ($.commUtil.nvlTrim($('#confmKey').val()).length == 0) {
			$.commMsg.alert('API 연계 승인키가 부여되지 않았습니다.');
			return;
		}
		$('#addressForm').attr("action", ACTION).submit();
	}
});
