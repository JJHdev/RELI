<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="app"    uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="f"      uri="/WEB-INF/tld/f.tld" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

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

	<!-- 카카오톡 링크 보낼 때 뜨는 이미지와 텍스트 설정 -->
	<meta property="og:type"        content="website">
	<meta property="og:title"       content=""> <!-- 제목에 뜰 내용(굵은글씨) -->
	<meta property="og:url"         content="">	<!-- 링크걸릴주소 -->
	<meta property="og:description" content=""> <!-- 제목아래쪽에 한줄 나오는 짧은 소개글 -->
	<meta property="og:image"       content=""> <!-- 썸네일이미지 경로 -->

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
    
    <script type="text/javascript" src="<c:url value='/jquery/design/usr/swiper.js'/>"></script>                       <!-- Design 관련 -->
    <script type="text/javascript" src="<c:url value='/jquery/design/usr/ebi.default.js'/>"></script>                  <!-- Design 관련 -->
    <script type="text/javascript" src="<c:url value='/jquery/design/usr/ebi.slider.js'/>"></script>                   <!-- Design 관련 -->
    <script type="text/javascript" src="<c:url value='/jquery/design/usr/ebi.gnb.js'/>"></script>                      <!-- Design 관련 -->
    <script type="text/javascript" src="<c:url value='/jquery/design/usr/ebi.js'/>"></script>                          <!-- Design 관련 -->
	
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
    <script type="text/javascript" src="<c:url value='/js/com/comm_usr.js?version=${ver}'/>"></script>              <!-- User Function Script -->

	<script type="text/javascript" src="<c:url value='/plugins/easyui-1.4.3/jquery.easyui.min.js'/>"></script>      <!-- EasyUI Plugin -->
	<script type="text/javascript" src="<c:url value='/plugins/easyui-1.4.3/locale/easyui-lang-ko.js'/>"></script>  <!-- EasyUI Language Plugin -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_easyui.js?version=${ver}'/>"></script>           <!-- EasyUI Function Script -->

    <script type="text/javascript" src="<c:url value='/plugins/ckeditor/ckeditor.js?t=B37D54V'/>"></script>         <!-- CKEditor Plugin -->
    <script type="text/javascript" src="<c:url value='/js/com/comm_ckeditor.js?version=${ver}'/>"></script>         <!-- CKEditor Function Script -->
	
    <script type="text/javascript" src="<c:url value='/plugins/pdfobject-2.2.5/pdfobject.js'/>"></script>           <!-- PDFObject Plugin -->

<c:if test="${not empty scriptPage}">
    <!-- Business Script-->
    <script type="text/javascript" src="<c:url value='/js${scriptPage}.js?version=${ver}'/>"></script>
</c:if>
