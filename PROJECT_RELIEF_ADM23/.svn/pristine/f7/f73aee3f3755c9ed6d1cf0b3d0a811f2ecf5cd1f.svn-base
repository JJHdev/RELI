<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
  	<title>
  		<tiles:insertAttribute name="title" ignore="true" />	
	</title>
	<meta charset="utf-8">
    <meta http-equiv="Content-Type"     content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible"  content="IE=edge">
    <meta name="viewport"               content="user-scalable=yes, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width">
    <meta name="title"                  content="<spring:message code="title.sysname"/>">
    <meta name="author"                 content="<spring:message code="title.sysname"/>">
    <meta name="keywords"               content="<spring:message code="title.sysname"/>">
    <meta name="subject"                content="<spring:message code="title.sysname"/>">
    <meta name="Description"            content="<spring:message code="title.sysname"/>">
    <meta name="classification"         content="">

<%--
##========================================================================================
## 공통 스타일시트, 자바스크립트
## [각 프로젝트별로 퍼블리싱 HTML 적용]
## Velocity Template layout_stylescript.vm과 동일
##========================================================================================
--%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/plugins/easyui-1.4.3/themes/bootstrap/easyui.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/plugins/easyui-1.4.3/themes/icon.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/plugins/easyui-1.4.3/themes/color.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/awesome/font-awesome.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/usr/style.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>" />

    <script>
    	<%-- 
    	서버의 CONTEXT PATH를 자바스크립트 전역변수로 설정 
    	(comm_utils.js, comm_sys.js 에서 사용한다.) 
    	--%>
    	var ROOT_PATH = '${pageContext.request.contextPath}'; 
    </script>

    <script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/bootstrap/bootstrap.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/bootstrap/bootstrap-dialog.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/jquery/jquery.json-2.3.js'/>"></script>               <!-- jQuery FORM Plugin-->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.form.min-4.2.2.js'/>"></script>               <!-- jQuery FORM Plugin-->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.inputmask.bundle.js'/>"></script>             <!-- inputmask -->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.serializeObject.js'/>"></script>              <!-- serializeObject -->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.validate.js'/>"></script>                     <!-- jQuery Validation -->
    <script type="text/javascript" src="<c:url value='/jquery/jquery.number.js'/>"></script>                       <!-- jQuery Format Number -->
	
    <script type="text/javascript" src="<c:url value='/js/message.js?version=${ver}'/>"></script>          <!-- Message Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_const.js?version=${ver}'/>"></script>            <!-- Constants Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_utils.js?version=${ver}'/>"></script>            <!-- Utils Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_popup.js?version=${ver}'/>"></script>            <!-- Popup Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_validate.js?version=${ver}'/>"></script>         <!-- Validate Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_element.js?version=${ver}'/>"></script>          <!-- User Define Component Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_component.js?version=${ver}'/>"></script>        <!-- User Define Component Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_board.js?version=${ver}'/>"></script>        <!-- User Define Component Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_biz.js?version=${ver}'/>"></script>              <!-- Business Function Script -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_sys.js?version=${ver}'/>"></script>              <!-- System Function Script -->

<c:if test="${not empty scriptPage}">
    <!-- Business Script-->
    <script type="text/javascript" src="<c:url value='/js${scriptPage}.js?version=${ver}'/>"></script>
</c:if>
	
</head>
<body>

    <div class="container-fluid">
		<div class="page">
			<!-- Tiles BODY -->
	 		<tiles:insertAttribute name="body"/> 
		</div>
    </div>
<%-- 레이어팝업 : 레이어명은 반드시 "pop + 숫자"로 정의할것 --%>
<div id="layerPopup"></div>

<%-- 첨부파일 업로드 프레임 --%>
<div id="uploadFrame"></div>

<%-- 첨부파일 다운로드 프레임 --%>
<div id="downloadFrame"></div>

</body>
</html>
