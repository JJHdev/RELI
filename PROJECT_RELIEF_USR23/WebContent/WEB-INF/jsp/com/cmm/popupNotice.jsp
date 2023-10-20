<%--
*******************************************************************************
***    명칭: popupNotice.jsp
***    설명: 메인 팝업 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0        2022.10.24       dyjeon        First Coding.
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
<%-- <%= application.getMajorVersion() %>.<%=application.getMinorVersion() %>--%>

<style type="text/css">
    /* 메인 윈도우 팝업 */
    .popupImg img {display: block; margin-left:auto; margin-right:auto; width: 100%; height: 100%; padding: 15px 0px 15px;}
</style>


<div id="popup_box${viewPopup.nttNo}" data-ntt-no="${viewPopup.nttNo }" >
	<div class="popupImg">
		<div>${viewPopup.nttCn }</div>
	</div>
	<div class="closeToday" style="float: right;">
		<input id="ck_close_not_today_${viewPopup.nttNo }" type="checkbox"  name="notToday_${viewPopup.nttNo }" value="true">
		오늘 하루 열지 않음
		<a href="javascript:closePopup(${viewPopup.nttNo });">[닫기]</a>
	</div>
</div>
