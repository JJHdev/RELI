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

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="common.user.UserInfo" %>
<%@ page import="common.util.CommUtils" %>
<%@ page import="common.util.properties.ApplicationProperty"%>

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
	
	<%-- 나타났다 사라지는 타이틀 때문에 불가피하게 MENU_ID로 표시여부를 설정함 
	    (나에게맞는제도찾기[MU_USR_RQT0100] 는 타이틀이 없어야함)
	--%>
	<c:if test="${PAGEINFO.pageInfo.menuId != 'MU_USR_RQT0100'}">
	<section class="sub-visual">
		<div class="sub-visual-wrap">
			<h2>${PAGEINFO.pageInfo.titleNm}</h2>
			<ul class="box">
				<li><i><img src="<c:url value='/images/common/i-home.svg'/>" alt="" /></i> > </li>
				${f:getPathHtml(PAGEINFO.pageInfo.menuPath, ",", " > ")}
			</ul>
		</div>
	</section>
	</c:if>

	<!-- Tiles BODY -->
	<tiles:insertAttribute name="body"/>

	<!-- Tiles FOOTER -->
	<tiles:insertAttribute name="footer" />

</body>
</html>
