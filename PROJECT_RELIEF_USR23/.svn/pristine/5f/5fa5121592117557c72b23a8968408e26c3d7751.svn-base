/**
******************************************************************************************
*** 파일명 : listPapeMng.js
*** 설명글 : [자료실] 신청서류양식 화면 스크립트
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021.10.02    김주호
******************************************************************************************
**/
$(function() {

    let   P_PDS_OBJ    = false;          // 신청구분 radio 객체
    let   P_UPPAPE_OBJ = false;          // 급여종류 radio 객체
    let   P_PAPE_OBJ   = false;          // 제출서류 checkbox 객체
    let   P_PDS_OBJ2    = false;          // 살생물 신청구분 radio 객체
    let   P_UPPAPE_OBJ2 = false;          // 살생물 급여종류 radio 객체
    let   P_PAPE_OBJ2   = false;          // 제출서류 checkbox 객체

    const P_PAPE_DTY_SE_CD1 = 'PP01';   // 구제급여
    const P_PAPE_DTY_SE_CD2 = 'PP02';   // 취약계층 소송지원
    const P_PAPE_DTY_SE_CD4 = 'PP04';   // 살생물 제품 구제급여
    

    const P_RELIEF_BTN      = $("#reliefDownBtn");
    const P_BIOCIDE_BTN     = $("#biocideDownBtn");
    const P_LWST_BTN        = $("#lwstDownBtn");

    /**
     * [구제급여] 신청구분 항목 구성 함수
     */
    function doLoadAplySeRadio(){
        $('#layerAplySe').append('<span id="appAplySeCd"></span>');
        P_PDS_OBJ = $('#appAplySeCd').appSelectBox({
            form   : 'radio',
            name   : 'aplySeCd',
            url    : getUrl('/usr/file/getListPapeMngAplySe.do'),
            params : {papeDtySeCd : P_PAPE_DTY_SE_CD1},
            click  : function(){
                // 급여종류 radio 구성
                doLoadUpPapeRadio(P_PDS_OBJ.getValue()[0]);
            },
            // 2023.10.12 필터처리 추가
            filter : function(o) {
            	return (o['code'] != 'R02');
            },
            callback : function(cmp) {

                var fstElm = cmp.getRow(0);
                if(fstElm && fstElm != null) {
                    // 첫번째 요소 선택
                    cmp.select(fstElm.code);
                    // 급여종류 radio 구성
                    doLoadUpPapeRadio(fstElm.code);
                }
            }
        });
    }
    /**
     * [구제급여] 급여 종류 콤보 항목 구성 함수
     * @param aplySeCdVal 신청구분
     */
    function doLoadUpPapeRadio(aplySeCdVal){
        $('#layerUpPape').html('');
        $('#layerUpPape').append('<span id="appUpPapeCd"></span>');
        P_UPPAPE_OBJ = $('#appUpPapeCd').appSelectBox({
            form   : 'checkbox',
            name   : 'upPapeCd',
            url    : getUrl('/usr/file/getListPapeMngUpPape.do'),
            params : {'papeDtySeCd' : P_PAPE_DTY_SE_CD1, 'aplySeCd' : aplySeCdVal},
            click  : function(){
                // 선택된 급여종류 값 array
                var upPapeCdVals = P_UPPAPE_OBJ.getValue();
                //첨부파일 목록 항목 구성
                doLoadPapeCheckBtn(P_PDS_OBJ.getValue()[0], upPapeCdVals);
            },
            callback : function(cmp) {
                var fstElm = cmp.getRow(0);
                if(fstElm && fstElm != null) {
                    cmp.select(fstElm.code);
                    //첨부파일 목록 항목 구성
                    doLoadPapeCheckBtn(P_PDS_OBJ.getValue()[0], [fstElm.code]);
                }
            }
        });
    }
    /**
     * [살생물 구제급여] 신청구분 항목 구성 함수
     */
    function doLoadAplySeRadioBiocide(){
        $('#layerAplySeBiocide').append('<span id="appAplySeCdBiocide"></span>');
        P_PDS_OBJ2 = $('#appAplySeCdBiocide').appSelectBox({
            form   : 'radio',
            name   : 'aplySeCdBiocide',
            url    : getUrl('/usr/file/getListPapeMngAplySe.do'),
            params : {papeDtySeCd : P_PAPE_DTY_SE_CD4},
            click  : function(){
                // 급여종류 radio 구성
                doLoadUpPapeRadioBiocide(P_PDS_OBJ2.getValue()[0]);
            },
            callback : function(cmp) {

                var fstElm = cmp.getRow(0);
                if(fstElm && fstElm != null) {
                    // 첫번째 요소 선택
                    cmp.select(fstElm.code);
                    // 급여종류 radio 구성
                    doLoadUpPapeRadioBiocide(fstElm.code);
                }
            }
        });
    }
     /**
     * [살생물 구제급여] 급여 종류 콤보 항목 구성 함수
     * @param aplySeCdBiocideVal 신청구분
     */
    function doLoadUpPapeRadioBiocide(aplySeCdBiocideVal){
        $('#layerUpPapeBiocide').html('');
        $('#layerUpPapeBiocide').append('<span id="appUpPapeCdBiocide"></span>');
        P_UPPAPE_OBJ2 = $('#appUpPapeCdBiocide').appSelectBox({
            form   : 'checkbox',
            name   : 'upPapeCdBiocide',
            url    : getUrl('/usr/file/getListPapeMngUpPape.do'),
            params : {'papeDtySeCd' : P_PAPE_DTY_SE_CD4, 'aplySeCd' : aplySeCdBiocideVal},
            click  : function(){
                // 선택된 급여종류 값 array
                var upPapeCdBiocideVals = P_UPPAPE_OBJ2.getValue();
                //첨부파일 목록 항목 구성
                doLoadPapeBiocideCheckBtn(P_PDS_OBJ2.getValue()[0], upPapeCdBiocideVals);
            },
            callback : function(cmp) {
                var fstElm2 = cmp.getRow(0);
                if(fstElm2 && fstElm2 != null) {
                    cmp.select(fstElm2.code);
                    //첨부파일 목록 항목 구성
                    doLoadPapeBiocideCheckBtn(P_PDS_OBJ2.getValue()[0], [fstElm2.code]);
                }
            }
        });
    }
    /**
     * 배열을 comma로 연결한 문자열로 변환
     */
    function getArrStrForListParam(arry){
        var retn = "";
        $.each(arry, function(index, item){
            retn += ( ((index!=0)? ",":"") + item );
        });
        return retn;
    }
    /**
     * [구제급여/취약계층 소송지원] 첨부파일 목록 항목 구성 함수
     * @param aplySeCdVal 신청구분
     * @param upPapeCdVal 급여종류
     * @param commonOptions 옵션 (입력하지 않으면 '구제급여'에 해당하는 값이 자동 설정)
     */
    function doLoadPapeCheckBtn(aplySeCdVal, upPapeCdVals, commonOptions){

        // 기본 값은 '구제급여'
        var layerId     = (commonOptions && commonOptions.layerId)     ? commonOptions.layerId     : 'layerPape';
        var spanId      = (commonOptions && commonOptions.spanId)      ? commonOptions.spanId      : 'appPapeCd';
        var name        = (commonOptions && commonOptions.name)        ? commonOptions.name        : 'reliefPapeCdList';
        var papeDtySeCd = (commonOptions && commonOptions.papeDtySeCd) ? commonOptions.papeDtySeCd : P_PAPE_DTY_SE_CD1;

        // 조건
        var paramData   = {'papeDtySeCd' : papeDtySeCd, 'downTrgtYn':'Y'};
        // 구제급여 일 경우만 aplySeCd, upPapeCd 전달됨
        if(papeDtySeCd == P_PAPE_DTY_SE_CD1){
            paramData.aplySeCd = aplySeCdVal;
            //paramData.upPapeCd = upPapeCdVals;
            paramData.upPapeCdList = getArrStrForListParam(upPapeCdVals);
        }

        // 구성 시작
        $('#'+layerId).html('');
        $('#'+layerId).append('<span id="'+spanId+'"></span>');
        P_PAPE_OBJ = $('#'+spanId).appSelectBox({
            form     : 'checkbox',
            name     : name,
            wrapCls  : 'inputWrap checkBtn',
            url      : getUrl('/usr/file/getListPapeMngPape.do'),
            params   : paramData,
            click    : function(){ },
            callback : function(cmp) {
                // 2021.12.29 CSLEE BASE64 ENCODE
                $("#"+spanId).find('input[type=checkbox]').each(function(){
                    $(this).val( btoa($(this).val()) );
                });
            }
        });
    }
       /**
     * [살생물제품피해 구제급여] 첨부파일 목록 항목 구성 함수 
     * @param aplySeCdBiocideVal 신청구분
     * @param upPapeCdBiocideVals 급여종류
     * @param commonOptions 옵션 (입력하지 않으면 '구제급여'에 해당하는 값이 자동 설정)
     */
    function doLoadPapeBiocideCheckBtn(aplySeCdBiocideVal, upPapeCdBiocideVals, commonOptions){

        // 기본 값은 '구제급여'
        var layerId     = (commonOptions && commonOptions.layerId)     ? commonOptions.layerId     : 'layerPapeBiocide';
        var spanId      = (commonOptions && commonOptions.spanId)      ? commonOptions.spanId      : 'appPapeCdBiocide';
        var name        = (commonOptions && commonOptions.name)        ? commonOptions.name        : 'biocidePapeCdList';
        var papeDtySeCd = (commonOptions && commonOptions.papeDtySeCd) ? commonOptions.papeDtySeCd : P_PAPE_DTY_SE_CD4;

        // 조건
        var paramData   = {'papeDtySeCd' : papeDtySeCd, 'downTrgtYn':'Y'};
        // 구제급여 일 경우만 aplySeCd, upPapeCd 전달됨
        if(papeDtySeCd == P_PAPE_DTY_SE_CD4){
            paramData.aplySeCd = aplySeCdBiocideVal;
            //paramData.upPapeCd = upPapeCdBiocideVals;
            paramData.upPapeCdList = getArrStrForListParam(upPapeCdBiocideVals);
        }

        // 구성 시작
        $('#'+layerId).html('');
        $('#'+layerId).append('<span id="'+spanId+'"></span>');
        P_PAPE_OBJ2 = $('#'+spanId).appSelectBox({
            form     : 'checkbox',
            name     : name,
            wrapCls  : 'inputWrap checkBtn',
            url      : getUrl('/usr/file/getListPapeMngPape.do'),
            params   : paramData,
            click    : function(){ },
            callback : function(cmp) {
                // 2021.12.29 CSLEE BASE64 ENCODE
                $("#"+spanId).find('input[type=checkbox]').each(function(){
                    $(this).val( btoa($(this).val()) );
                });
            }
        });
    }
    /**
     * 구제급여 제출서류 다운로드 클릭 이벤트 수행
     */
    function doReliefFileDownloadZip(){
        doFileDownloadZipCommon('form1');
    }
    /**
    * 살생물 피해구제급여 제출서류 다운로드 클릭 이벤트 수행
    */
    function doBiocideFileDownloadZip(){
        doFileDownloadZipCommon('form3');
    }
    /**
     * 취약계층 소송지원 제출서류 다운로드 클릭 이벤트 수행
     */
    function doLwstFileDownloadZip(){
        doFileDownloadZipCommon('form2');
    }
   
    /**
     * 다일 다운로드 공통
     */
    function doFileDownloadZipCommon(formId){

        //reliefPapeCdList
        //lwstPapeCdList
        let checkFormName = "";
        let typeName      = "";
        if(formId == 'form1'){
            checkFormName = 'reliefPapeCdList';
            typeName      = '환경오염 피해구제급여';
        }
        else if(formId == 'form2'){
            checkFormName = 'lwstPapeCdList';
            typeName      = '취약계층 소송지원';
        }
		else if(formId == 'form3'){
        	checkFormName = 'biocidePapeCdList';
            typeName      = '살생물제품 피해구제급여';
		}

        let checkCount = $('#'+formId).find('input[name='+checkFormName+']:checked').length;

        if(checkCount == 0) {
            $.commMsg.alert('다운로드할 '+typeName+' 제출서류를 선택해주세요.');
            return false;
        }
        $.commMsg.confirm('선택된 '+ typeName+' 제출서류를 다운로드 하시겠습니까?', function() {
            $.formUtil.submitForm(getUrl('/usr/file/downPageMngFileZip.do'), {
                formId : formId
            });
        });
    }

    // 버튼 클릭 이벤트 연결
    P_RELIEF_BTN.on("click", doReliefFileDownloadZip);
    P_BIOCIDE_BTN.on("click", doBiocideFileDownloadZip);
    P_LWST_BTN.on("click",   doLwstFileDownloadZip);

    //[구제급여] 신청구분 항목 구성
    doLoadAplySeRadio();
    doLoadAplySeRadioBiocide();

    //[취약계층 소송지원] 제출서류 항목 구성
    doLoadPapeCheckBtn(null, null,
        {'papeDtySeCd' : P_PAPE_DTY_SE_CD2,
         'layerId'     : 'layerLwstPape',
         'spanId'      : 'appLwstPapeCd',
         'name'        : 'lwstPapeCdList'}
    );
});
