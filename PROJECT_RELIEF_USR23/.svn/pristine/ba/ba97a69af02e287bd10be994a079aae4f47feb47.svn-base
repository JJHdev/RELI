<%--
*******************************************************************************
***    명칭: modalNotice.jsp
***    설명: 메인 팝업 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0        2022.10.06       JDY        First Coding.
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

<link rel="stylesheet" type="text/css" href="<c:url value='/css/usr/modalNotice.css'/>" />

<c:forEach var="lstPopup" items="${lstPopup}" varStatus="status"> 
	<div id="popup_box_${lstPopup.nttNo}" class="divpopLayer" 
			data-ntt-no="${lstPopup.nttNo}" 
			data-ntt-sj="${lstPopup.nttSj}"
			data-popup-hght="${lstPopup.popupHght}"
			data-popup-sqr="${lstPopup.popupSqr}">
            
		<div class="window_topslice">
			<div class="window_button_holder">
				<a href="javascript:closePopup(${lstPopup.nttNo });" style="color: whitesmoke; font-weight: 500;">X</a>
			</div>
		</div>
        
    	<div class="window_content" id="cancel_${lstPopup.nttNo}">
			<div class="window_area">
				<h3>${lstPopup.nttSj }</h3>
				<div class="discrip">${lstPopup.nttCn }</div>
			</div>
			<div class="closeToday">
				<form name="notice_form">
					<input id="ck_close_not_today_${lstPopup.nttNo }" type="checkbox" name="notToday" value="true">
					오늘 하루 이 창을 열지 않음
					<a href="#" onclick="javascript:closePopup(${lstPopup.nttNo });">[닫기]</a>
				</form>
			</div>
		</div>
        
	</div>
</c:forEach>