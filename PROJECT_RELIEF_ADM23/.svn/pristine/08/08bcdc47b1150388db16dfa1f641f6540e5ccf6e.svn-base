<%--
*******************************************************************************
***	명칭: popupReportPost.jsp
***	설명: CLIP Report Viewer
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021-09-28    LSH        First Coding.
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="common.util.CommUtils"%>
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

<form name="reportForm" method="post" action="http://localhost:8083/ClipReport5/report_relief.jsp">
<%
Map paramMap = (Map)request.getAttribute("paramMap");
if( paramMap != null ) {
    Iterator k = paramMap.keySet().iterator();
    String key = "";
    String val = "";
    while (k.hasNext()) {
        key = CommUtils.nvl((String) k.next());
        val = CommUtils.nvl((String) paramMap.get(key));
        out.println("<input type=\"hidden\" name=\""+key+"\" value=\""+val+"\"/>");
    }
}
%>
</form>
<script type='text/javascript'>
	document.reportForm.submit();
</script>

<%-- ############################# 내용 (종료) ############################# --%>
