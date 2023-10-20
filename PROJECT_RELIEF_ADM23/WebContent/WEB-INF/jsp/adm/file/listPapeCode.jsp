<%--
*******************************************************************************
***    명칭: listPapeCode.jsp
***    설명: 서류코드관리 관리 화면
***
***    -----------------------------    Modified Log   ---------------------------
***    버전        수정일자        수정자        내용
*** ---------------------------------------------------------------------------
***    1.0      2021.10.07    LSH        First Coding.
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

<style>
    #attachFile>a {
        display:inline-block;
        margin-right:9px;
        padding-right:11px;
        font-weight:400;
        line-height:1.2;
        color:#666;
        /*border-right:2px solid #ccc;*/
        padding:0 20px;
        background:url(../../images/common/upload_file_icon.png)0 0 no-repeat;
    }
</style>
<%-- ############################# 내용 (시작) ############################# --%>

		<div class="boxWrap type1">
			<div class="boxInner">
				<div class="boxTit type1">
					<h3>검색조건</h3>
				</div>
				<div class="searchForm formLayout">
		            <form id="searchForm" name="searchForm" method="post" onsubmit="return false;">
						<div class="formGroup">
							<div class="inputWrap">
		                        <input id="srchText" name="srchText" placeholder="코드,코드명칭 등을 입력하세요." style="width:400px" />
		                    </div>
						</div>
		            </form>
				</div>
			</div>
			<div class="btnWrap type1">
                <app:button id="btnSearch" title="검색" cls="app-m3 blue" />
                <app:button id="btnReset"  title="리셋" cls="app-m3" />
			</div>
		</div>

		<div class="app-space40"></div>

		<div class="listWrap div2 box">
			<!-- 테이블 -->
			<div class="tableGroup">
				<div class="subTit type2">
					<h4 class="app-left app-pt20">서류코드목록</h4>
					<div class="btnDiv">
					</div>
				</div>
				<div style="height: 600px">
					<table id="appGrid" class="easyui-treegrid"></table>
				</div>
			</div>

			<!-- 테이블 -->
			<div class="formLayout">
            <form id="registForm" name="registForm" method="post" enctype="multipart/form-data" onsubmit="return false;">

				<div class="subTit type2">
					<h4 class="app-left app-pt20" id="registTitle">서류코드등록</h4>
					<div class="btnDiv">
						<app:button id="btnRegist" title="등록" cls="btn blue" />
					</div>
				</div>

				<div class="boxInner type2 box">

	            	<input id="mode"  name="mode" type="hidden"/>

					<div class="formGroup col-md-12">
						<span class="col-md-2 must">상위코드</span>
						<div class="inputWrap col-md-10">
							<div id="upPapeArea">
								<input id="upPapeCd" name="upPapeCd" class="app-textbox" style="width:100%"/>
							</div>
                            <input id="orgUpPapeCd"  name="orgUpPapeCd" type="hidden"/>
							<div id="orgUpPapeNm"></div>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">서류코드</span>
						<div class="inputWrap col-md-10">
							<input type="text" id="papeCd" name="papeCd" class="w100" maxlength="6" />
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">서류명</span>
						<div class="inputWrap col-md-10">
							<input type="text" id="papeNm" name="papeNm" class="w100"  maxlength="100"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2">제한수</span>
						<div class="inputWrap col-md-10">
							<input type="text" id="limtCnt" name="limtCnt" class="app-w100 number" maxlength="5" class="app-w50 number"/>
						</div>
					</div>
					<!-- <div class="formGroup col-md-12">
						<span class="col-md-2">다운로드수</span>
						<div class="inputWrap col-md-10">
							<input type="text" id="downCnt" name="downCnt" class="w100" maxlength="5"/>
						</div>
					</div> -->
					<div class="formGroup col-md-12">
						<span class="col-md-2">순　서</span>
						<div class="inputWrap col-md-10">
							<input type="text" id="cdOrdr" name="cdOrdr" class="app-w100 number" maxlength="5"/>
						</div>
					</div>
					<div class="formGroup col-md-12">
						<span class="col-md-2 must">사용여부</span>
						<div class="inputWrap col-md-5">
							<div id="appUseYn"></div>
						</div>
					</div>
                    <div class="formGroup col-md-12">
                        <span class="col-md-2">다운로드대상여부</span>
                        <div class="inputWrap col-md-5">
                            <div id="appDownTrgtYn"></div>
                        </div>
                    </div>
				</div>

                <div class="subTit type2 layerAttachFile">
                    <h4 class="app-pt20" id="registTitle">관련 서류 등록</h4>
                </div>

                <div class="boxInner type2 box layerAttachFile">
                    <div class="formGroup col-md-12">
                        <span class="col-md-2">첨부파일</span>
                        <div class="inputWrap col-md-10">
                            <div id="attachFile">
                                <!-- <a href="#" data-type="null" data-no="178" data-seq="28" class="icon-anchor btnFileDown">공지11-22.txt</a> -->
                            </div>
                            <%-- 여기부터 반복 (여러개일때) --%>
                            <div class="file_wrap app-small">
                                <input type="hidden" name="docuNo"   value="FORMFILE" /><%-- 연결문서번호 --%>
                                <input type="hidden" name="fileType" value="FORMFILE" /><%-- 파일타입 --%>
                                <input type="hidden" name="filePath" value=""         /><%-- 기저장된 파일경로 --%>
                                <input type="hidden" name="fileNo"   value=""         /><%-- 기저장된 파일번호(sn) --%>
                                <input type="hidden" name="needYn"   value="N"        /><%-- 파일의 필수체크여부 --%>
                                <input type="hidden" name="fileYn"   value="N"        /><%-- 파일의 업로드여부 --%>
                                <input type="text"   name="fileName" value="" class="input_text" title="filebox" readonly /><%-- 파일명 표시박스 --%>
                                <div class="box_wrap">
                                    <input type="file" name="upfile" class="input_file">
                                    <button class="btn_file"></button>
                                </div>
                            </div>
                            <%-- 여기까지 반복 (여러개일때) --%>
                        </div>
                    </div>
                </div>

				<div class="btnWrap type1">
		             <app:button id="btnSave"   title="저장" cls="app-m3 blue"/>
		             <app:button id="btnRemove" title="삭제" cls="app-m3 blue"/>
		             <app:button id="btnUndo"   title="취소" cls="app-m3"/>
				</div>

            </form>
			</div>
		</div>

<%-- ############################# 내용 (종료) ############################# --%>
