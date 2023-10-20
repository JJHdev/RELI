/**
******************************************************************************************
*** 파일명 : viewLwst.js
*** 설명글 : 마이페이지 - 취약계층 소송지원 현황 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    LSH
******************************************************************************************
**/
$(function() {
    //========================================================//
    // 화면 스크립트 내에 사용할 객체,상수 정의
    //--------------------------------------------------------//

    //========================================================//
    // FUNCTION 정의
    //--------------------------------------------------------//

    // 목록화면 이동
    //--------------------------------------------------------//
    function doListLwstAply(){
        $.formUtil.submitForm(getUrl('/usr/mypage/listLwst.do'));
    }

    // 향후 기일 목록의 '결과' 항목 스타일 설정
    //--------------------------------------------------------//
    function setRsltStyle() {
        $("#detailTbody tr").each(function(){
            var $tr       = $(this);
            var $styleB   = $tr.find("b.cusRsltCnNm");
            var rsltCnVal = $tr.data("rsltcn");
            var styleNm   = "";
            switch(rsltCnVal){
                case "01" :     // 속행
                    styleNm = "blue";
                    break;
                case "02" :     // 조정불성립
                    styleNm = "red";
                    break;
                case "03" :     // 변론종결
                    styleNm = "black";
                    break;
            }
            $styleB.addClass(styleNm);
        });
    }

    // '지원사항' 항목 재 설정
    //--------------------------------------------------------//
    function loadAmtDetail(){
        var $amtDetailObj = $("#amtDetailData");
        var amtDetailVal = $amtDetailObj.data("amtdetail");
        var arrAmtDetail = [];
        if(amtDetailVal != null){
            arrAmtDetail = amtDetailVal.split("/");

            if(arrAmtDetail.length > 0){
                $.each(arrAmtDetail, function(index,item){
                    $amtDetailObj.append('<span class="box" style="margin-right:5px;">'+item+'</span>');
                });
            }
        }
    }

    // 데이터 포멧 및 내용 변경 load
    //--------------------------------------------------------//
    function convertLoadFormatData(){
        $(".cusConvFormat").each(function(){
            var $obj     = $(this);
            var convData = $obj.data("value");

            // '~년~월~일 ' 형태로 변형
            if($obj.hasClass("convKorDate")){
                $obj.html($.formatUtil.toKorDate(convData));
            }
        })
    }

    // 초기 수행 함수
    //--------------------------------------------------------//
    function initLoad(){

        // 데이터 변경 수행
        convertLoadFormatData();

        // 지원사항 항목 내용 구성
        loadAmtDetail();

        // 스타일 설정
        setRsltStyle();
    }

    //========================================================//
    // FORM ELEMENT EVENT 정의
    //--------------------------------------------------------//

    // 목록으로 버튼 클릭
    $("#goListBtn").on("click",doListLwstAply);

    //========================================================//
    // 최초 자동 실행
    //--------------------------------------------------------//

    initLoad();
});
