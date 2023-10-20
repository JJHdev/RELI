<%--
*******************************************************************************
***	명칭: popupReport.jsp
***	설명: CLIP Report Viewer
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021-09-28    LSH        First Coding.
*******************************************************************************
--%>
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

<%-- ############################# 내용 (시작) ############################# --%>

<link rel="stylesheet" type="text/css" href="<c:url value='/ClipReport5/css/clipreport5.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/ClipReport5/css/UserConfig5.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/ClipReport5/css/font.css'/>">
<script type='text/javascript' src="<c:url value='/ClipReport5/js/clipreport5.js'/>"></script>
<script type='text/javascript' src="<c:url value='/ClipReport5/js/UserConfig5.js'/>"></script>
<script type='text/javascript'>
	doSetReport("${resultKey}", "targetDiv1");
</script>

<div id='targetDiv1' style='position:absolute;top:5px;left:5px;right:5px;bottom:5px;'>
	<span style="visibility: hidden; font-family:나눔고딕">.</span>
	<span style="visibility: hidden; font-family:NanumGothic">.</span>
</div>

<%-- ############################# 내용 (종료) ############################# --%>
