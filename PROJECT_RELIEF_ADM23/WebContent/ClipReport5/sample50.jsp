
<%@page import="com.clipsoft.clipreport.oof.connection.OOFConnectionHTTP"%>
<%@page import="com.clipsoft.clipreport.oof.OOFFile"%>
<%@page import="com.clipsoft.clipreport.oof.OOFDocument"%>
<%@page import="com.clipsoft.clipreport.oof.connection.*"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*"%>
<%@page import="com.clipsoft.clipreport.server.service.ReportUtil"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%

OOFDocument oof = OOFDocument.newOOF();

// 서식 파일 추가 
OOFFile file = oof.addFile("crf.root", "%root%/crf/CLIP.crf");


// 매개변수 필드 추가 (arg1:매개변수명, arg2:값)
// oof.addField("param1", "value");

/* Data Connection */

// 1. DB (arg1:데이터셋명, arg2:DataConnection.properties에 설정한 dbName)
// 모든 쿼리가 같은 DB서버를 바라볼 경우, 데이터셋명을 * 로 사용
// oof.addConnectionData("*","dbName");

// 2. XML, CSV, JSON
// 커넥션별로 데이터를 나누지 않고 전체 데이터를 한 번에 넘겨줄 경우, 커넥션명을 *로 사용
// File Type (arg1:커넥션명, arg2:데이터 파일 절대경로)
// OOFConnectionFile conn= oof.addConnectionFile("*","C:\\Users\\wan\\Desktop\\교육\\test.xml");

// Memo Type (arg1:커넥션명, arg2:데이터)
// OOFConnectionMemo conn = oof.addConnectionMemo("*", "<rexdataset><power><![CDATA[Xman]]></power><rexrow><ID><![CDATA[3]]></ID><Factory><![CDATA[Paris]]></Factory><Item><![CDATA[TV]]></Item><Production><![CDATA[36]]></Production><Badness><![CDATA[3]]></Badness><Stock><![CDATA[36]]></Stock></rexrow><rexrow><ID><![CDATA[4]]></ID><Factory><![CDATA[Paris]]></Factory><Item><![CDATA[Refrigerator]]></Item><Production><![CDATA[27]]></Production><Badness><![CDATA[5]]></Badness><Stock><![CDATA[78]]></Stock></rexrow><rexrow><ID><![CDATA[5]]></ID><Factory><![CDATA[Paris]]></Factory><Item><![CDATA[Washer]]></Item><Production><![CDATA[16]]></Production><Badness><![CDATA[7]]></Badness><Stock><![CDATA[132]]></Stock></rexrow><rexrow><ID><![CDATA[6]]></ID><Factory><![CDATA[Tokyo]]></Factory><Item><![CDATA[TV]]></Item><Production><![CDATA[68]]></Production><Badness><![CDATA[9]]></Badness><Stock><![CDATA[12]]></Stock></rexrow><rexrow><ID><![CDATA[7]]></ID><Factory><![CDATA[Paris]]></Factory><Item><![CDATA[Video]]></Item><Production><![CDATA[23]]></Production><Badness><![CDATA[12]]></Badness><Stock><![CDATA[78]]></Stock></rexrow><rexrow><ID><![CDATA[8]]></ID><Factory><![CDATA[Tokyo]]></Factory><Item><![CDATA[Audio]]></Item><Production><![CDATA[12]]></Production><Badness><![CDATA[3]]></Badness><Stock><![CDATA[63]]></Stock></rexrow><rexrow><ID><![CDATA[9]]></ID><Factory><![CDATA[London]]></Factory><Item><![CDATA[ElectricFan]]></Item><Production><![CDATA[78]]></Production><Badness><![CDATA[27]]></Badness><Stock><![CDATA[71]]></Stock></rexrow><rexrow><ID><![CDATA[10]]></ID><Factory><![CDATA[Tokyo]]></Factory><Item><![CDATA[ElectricFan]]></Item><Production><![CDATA[53]]></Production><Badness><![CDATA[2]]></Badness><Stock><![CDATA[23]]></Stock></rexrow><rexrow><ID><![CDATA[11]]></ID><Factory><![CDATA[London]]></Factory><Item><![CDATA[Audio]]></Item><Production><![CDATA[23]]></Production><Badness><![CDATA[10]]></Badness><Stock><![CDATA[56]]></Stock></rexrow><rexrow><ID><![CDATA[12]]></ID><Factory><![CDATA[London]]></Factory><Item><![CDATA[TV]]></Item><Production><![CDATA[89]]></Production><Badness><![CDATA[25]]></Badness><Stock><![CDATA[30]]></Stock></rexrow><rexrow><ID><![CDATA[13]]></ID><Factory><![CDATA[Paris]]></Factory><Item><![CDATA[AirConditioner]]></Item><Production><![CDATA[9]]></Production><Badness><![CDATA[2]]></Badness><Stock><![CDATA[20]]></Stock></rexrow><rexrow><ID><![CDATA[14]]></ID><Factory><![CDATA[Tokyo]]></Factory><Item><![CDATA[AirConditioner]]></Item><Production><![CDATA[2]]></Production><Badness><![CDATA[0]]></Badness><Stock><![CDATA[18]]></Stock></rexrow><rexrow><ID><![CDATA[15]]></ID><Factory><![CDATA[Tokyo]]></Factory><Item><![CDATA[Washer]]></Item><Production><![CDATA[89]]></Production><Badness><![CDATA[20]]></Badness><Stock><![CDATA[0]]></Stock></rexrow><rexrow><ID><![CDATA[16]]></ID><Factory><![CDATA[London]]></Factory><Item><![CDATA[Refrigerator]]></Item><Production><![CDATA[23]]></Production><Badness><![CDATA[10]]></Badness><Stock><![CDATA[36]]></Stock></rexrow></rexdataset>");

// Http Type (arg1:커넥션명, arg2:url, arg3:method(post, get))
// OOFConnectionHTTP conn = oof.addConnectionHTTP("커넥션명", "url", "method");
// 특정 URL을 Call 했을때 json데이터를 리턴해주는 경우



// 모든 데이터셋에 동일한 반복노드, 구분자를 설정할 경우 데이터셋명을 *로 사용
// 2-1. XML 
// 반복노드 지정 (서식에서 지정한 반복노드로 사용할 경우 : %dataset.xml.root%)
// conn.addContentParamXML("데이터셋명", "인코딩", "반복노드");
// conn.addContentParamXML("*", "UTF-8", "rexdataset/rexrow");
	
// 2-2. CSV
// 구분자 지정
//conn.addContentParamCSV("데이터셋명", "인코딩", "필드구분자", "레코드구분자", "데이터셋구분자", "데이터셋번호");
	
// 2-3. JSON 
// 반복노드 지정 (서식에서 지정한 반복노드로 사용할 경우 : %dataset.json.root%)
//conn.addContentParamJSON("데이터셋명", "인코딩", "반복노드");


/* Param 동적 처리 샘플 */
/*
Enumeration<String> params = request.getParameterNames();
while(params.hasMoreElements()) {
	String name = (String)params.nextElement(); 
	String value = request.getParameter(name); 
	
	// Param Add
    file.addField(name, value);
}
*/

%><%@include file="Property.jsp"%><%
String resultKey =  ReportUtil.createReport(request, oof, "false", "false", request.getRemoteAddr(), propertyPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="./css/clipreport5.css">
<link rel="stylesheet" type="text/css" href="./css/UserConfig5.css">
<link rel="stylesheet" type="text/css" href="./css/font.css">

<script type='text/javascript' src='./js/jquery-1.11.1.js'></script>
<script type='text/javascript' src='./js/clipreport5.js'></script>
<script type='text/javascript' src='./js/UserConfig5.js'></script>
<script type='text/javascript'>
	
function html2xml(divPath){	
    var reportkey = "<%=resultKey%>";
	var report = createReport("./report_server.jsp", reportkey, document.getElementById(divPath));
   

    report.view();
}
</script>
</head>
<body onload="html2xml('targetDiv1')">
<div id='targetDiv1' style='position:absolute;top:5px;left:5px;right:5px;bottom:5px;'>
	<span style="visibility: hidden; font-family:나눔고딕">.</span>
	<span style="visibility: hidden; font-family:NanumGothic">.</span>
</div>
</body>
</html>
