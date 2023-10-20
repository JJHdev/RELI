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
<html lang="ko">
<head>
  	<title><spring:message code="title.sysname"/></title>
	<!-- Tiles RESOURCE -->
	<tiles:insertAttribute name="resource"/>

</head>
<body>

<!-- Tiles HEADER -->
<tiles:insertAttribute name="header"/>

<!-- CONTENS LAYOUT // -->
<div class="container-fluid" style="padding-top:80px; margin-bottom:100px;">
	<div class="row">
		<a href="#void" class="sidebtn">
			<img src="<c:url value='/images/common/navbtnArrow.svg'/>" alt="">
		</a>
		<nav class="col-3 sidenav">
			<!-- SIDTBAR -->
			<div id="sidebar" data-first="<c:out value="${PAGEINFO.pageInfo.firstMenuId}"/>"></div>
		</nav>
		<div class="col-9 page">
			<!-- TOP BREADCRUMBS -->
			<div class="page-title">
				<h2>${PAGEINFO.pageInfo.titleNm}</h2>
				<ul id="page-breadcrumbs"
					data-menu="<c:out value="${PAGEINFO.pageInfo.menuId}"/>"
					data-path="<c:out value="${PAGEINFO.pageInfo.menuPath}"/>"></ul>
			</div>
			<div id="page-body" style="width:100%; padding:0 10px;">
<!-- =============================  BODY START ============================= -->
				<tiles:insertAttribute name="body"/>
<!-- =============================  BODY END   ============================= -->
			</div>
		</div>
	</div>
</div>
<!-- // CONTENS LAYOUT -->

<!-- Tiles FOOTER -->
<tiles:insertAttribute name="footer" />

</body>
</html>

