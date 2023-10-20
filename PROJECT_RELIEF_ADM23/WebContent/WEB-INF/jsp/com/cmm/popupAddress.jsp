<%--
*******************************************************************************
***	명칭: popupAddress.jsp
***	설명	: 행정안전부 개발자센터 OPEN API 사용 - 주소검색 팝업창
***
***	-----------------------------    Modified Log   ---------------------------
***	버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***	1.0      2021-08-05    LSH        First Coding.
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

<form id="addressForm" name="addressForm" method="post">
	<input type="hidden" id="confmKey"   name="confmKey"   value="${address.confmKey}"  />
	<input type="hidden" id="returnUrl"  name="returnUrl"  value="${address.returnUrl}" />
	<input type="hidden" id="resultType" name="resultType" value="${address.resultType}"/>
</form>
<%-- ############################# 내용 (종료) ############################# --%>

<script language="javascript">
	doSetAddress('${address.inputYn}', {
		addrDetail:    '${address.addrDetail}',
		roadFullAddr:  '${address.roadFullAddr}',
		roadAddrPart1: '${address.roadAddrPart1}',
		roadAddrPart2: '${address.roadAddrPart2}',
		jibunAddr:     '${address.jibunAddr}',
		engAddr:       '${address.engAddr}',
		zipNo:         '${address.zipNo}',
		admCd:         '${address.admCd}',
		bdKdcd:        '${address.bdKdcd}',
		rnMgtSn:       '${address.rnMgtSn}',
		bdMgtSn:       '${address.bdMgtSn}',
		detBdNmList:   '${address.detBdNmList}',
		bdNm:          '${address.bdNm}',
		siNm:          '${address.siNm}',
		sggNm:         '${address.sggNm}',
		emdNm:         '${address.emdNm}',
		liNm:          '${address.liNm}',
		rn:            '${address.rn}',
		mtYn:          '${address.mtYn}',
		udrtYn:        '${address.udrtYn}',
		buldMnnm:      '${address.buldMnnm}',
		buldSlno:      '${address.buldSlno}',
		lnbrMnnm:      '${address.lnbrMnnm}',
		lnbrSlno:      '${address.lnbrSlno}',
		emdNo:         '${address.emdNo}'
	});
</script>