/**
 * CLIP Report 팝업 뷰어
 */
var REPORT_KEY = false;
var TARGET_DIV = false;

function doSetReport( reportKey, targetDiv ) {
	REPORT_KEY = reportKey;
	TARGET_DIV = targetDiv;
}

$(function() {
	var report = createReport(getUrl("/ClipReport5/report_server.jsp"), REPORT_KEY, document.getElementById(TARGET_DIV));
	//리포트 실행
    report.view();
});
