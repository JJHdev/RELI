/**
******************************************************************************************
*** 파일명    : comm_utils.js
*** 설명      : 공통 JavaScript
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-02-10              ntarget
******************************************************************************************
**/

$(document).ready(function() {
    // 입력폼 Mask
    formInputMask();
});

/**
 * ContextPath 포함 URL 반환 
 */
function getUrl( url ) {
	return ROOT_PATH + url;
}

/**
 * ContextPath 제거 URL 반환 
 */
function getRealUrl( url ) {
	if (url != null && url.indexOf(ROOT_PATH) == 0)
		return url.substring(ROOT_PATH.length);
	return url;
}

/**
 * 페이지 이동
 */
function goUrl( url ) {
	window.location.href = url;
}

/**
 * 관련사이트 이동 (디자인영역)
 */
function goSite(obj) {
    var url =  obj.children('select').val();
    if (url != '') {
        var newWindowSite = window.open("about:blank");
        newWindowSite.location.href = url;
    }
}

/**
 * Form Input Mask 사용. (jquery.inputmask)
 * 
 * @returns
 */
function formInputMask() {
    // 소문자, 대문자
    $('.lowercase').inputmask({casing:'lower'});
    $('.uppercase').inputmask({casing:'upper'});
    // 숫자만 입력 2021.08.20 LSH 추가
    $('.number').inputmask("numeric");
}

/**
 * 2021.08.05
 * 텍스트박스 엔터 이벤트 처리
 */
function bindEnter( box, btn ) {
	box.bind('keydown', function(e) {
		if (e.keyCode != 13)
			return;
		// 버튼의 클릭이벤트를 발생시킨다.
		btn.trigger('click');
		return false;
	});
}

// 날짜 하이픈(-) 삽입 이벤트
function bindDateHyphen( box ) {
	box.bind('keyup', function(e) {
		// @see DELETE 키버튼이 눌리지 않은 경우에만 실행
		if (e.keyCode == 8)
			return false;
		let v = $(this).val();
		// @see 숫자와 하이픈(-)기호의 값만 존재하는 경우 실행
		if (v.replace(/[0-9 \-]/g, "").length != 0) {
	        //alert("숫자 이외의 값은 입력하실 수 없습니다.");
	        //@see 숫자와 하이픈(-)기호 이외의 모든 값은 삭제한다.
			$(this).val(v.replace(/[^0-9 ^\-]/g,""));
	        return false;
		}
	    // @see 하이픈(-)기호를 제거한다.
	    let number = v.replace(/[^0-9]/g,"");
	    // @see 문자열의 길이에 따라 Year, Month, Day 앞에 하이픈(-)기호를 삽입한다.
	    if (number.length < 4) {
	        return number;
	    } 
		else if(number.length < 6) {
			$(this).val(number.substr(0, 4)+"-"+number.substr(4));
	    } 
		else {
			$(this).val(number.substr(0, 4)+"-"+number.substr(4, 2)+"-"+number.substr(6));
	    }
	});
}

// 전화번호 하이픈(-) 삽입 이벤트
function bindPhoneHyphen( box ) {
	box.bind('keyup', function(e) {
		// @see DELETE 키버튼이 눌리지 않은 경우에만 실행
		//if (e.keyCode == 8)
		//	return false;
		let v = $(this).val();
		let r = v.replace(/[^0-9]/g, "")
			     .replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3")
			     .replace("--", "-");
		$(this).val(r);
		return r;
	});
}

// 휴대전화번호 하이픈(-) 삽입 이벤트
function bindMobileHyphen( box ) {
	box.bind('keyup', function(e) {
		// @see DELETE 키버튼이 눌리지 않은 경우에만 실행
		//if (e.keyCode == 8)
		//	return false;
		let v = $(this).val();
		let r = ""; 
		v = v.replace(/-/gi, "");
		if      (v.length < 4)  r = v; 
		else if (v.length < 7)  r = (v.substr(0,3)+"-"+v.substr(3)); 
		else if (v.length < 11) r = (v.substr(0,3)+"-"+v.substr(3,3)+"-"+v.substr(6));
		else                    r = (v.substr(0,3)+"-"+v.substr(3,4)+"-"+v.substr(7));
		$(this).val(r);
		return r;
	});
}

// 숫자만 입력 이벤트
function bindOnlyNumber( box ) {
	box.bind('keyup', function(e) {
		$(this).val($(this).val().replace(/[^0-9]/g, ""));
		/*
   		if (  (e.keyCode > 48 && e.keyCode < 57 ) 
      		|| e.keyCode == 8  //backspace
      		|| e.keyCode == 37 //방향키 →
			|| e.keyCode == 39 //방향키 ←
      		|| e.keyCode == 46 //delete키
      		|| e.keyCode == 39) {
   		}
		else{
   			e.returnValue=false;
   		}
		*/
	});
}

/**
 * 공통함수
 * 
 * @returns
 */
(function ($) {
    /**
     * 공통 ajax 처리
     */
    $.ajaxSetup({ cache: false });
    $.ajaxUtil = {
        // Ajax default (타입 json)
        // return true, false, data
        // $.ajaxUtil.ajaxDefault(url, param)
        ajaxDefault: function (url, param) {
            var isResult = true;

            $.ajax({
                url: url,
                data: param,
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data == 'true') {
                        isResult = true;
                    } else if (data == 'false') {
                        isResult = false;
                    }
                    else {
                        isResult = data;
                    }
                }, error: function (request, status, error) {
                	/*
                    // error 처리(권한)
                    if (request.status == '401' || request.status == '403') {
                        // MSG : 권한이 없거나 세션이 만료되었습니다.
                        alert(MSG_COMM_E016);
                    }
                    else {
                        // MSG : 시스템 오류가 발생하였습니다.
                        alert(MSG_COMM_1002);
                    }
                    */
                    isResult = error;
                }
            });

            return isResult;
        },
        // Ajax 배열 전송 (타입 json)
        // return true, false, data;
        // $.ajaxUtil.ajaxArray(url, param)
        ajaxArray: function (url, param) {
            $.ajaxSettings.traditional = true;
            var isResult = true;

            $.ajax({
                url: url,
                data: param,
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data == 'true') {
                        isResult = true;
                    } else if (data == 'false') {
                        isResult = false;
                    }
                    else {
                        isResult = data;
                    }
                },
                error: function (request, status, error) {
                    // error 처리(권한)
                    if (request.status == '401' || request.status == '403') {
                        alert(MSG_COMM_E016);
                    }
                    else {
                        alert(MSG_COMM_1002);
                    }
                    isResult = 'syserr';
                }
            });

            return isResult;
        },
        /**
         * Ajax (타입 Html) 
         * 사용 예)
         * var result = $.ajaxUtil.loadHtml("test-tab", ROOT_PATH +"/usr/temp/listTemp.do", {
         *                  testParam  : "test",
         *                  testParam2 : "test2"
         *              });
         */        
        ajaxHtml : function(url, param, etcOption) {
            var isResult = true;

            etcOption = etcOption ? etcOption : {};

            $.ajax({
                url: url,
                data: param,
                type: 'post',
                dataType: 'html',
                async: false,
                success: function (data) {
                    isResult = data;
                },
                error: function (request, status, error) {

                    if (etcOption.errorFn && etcOption.errorFn != null && $.type(etcOption.errorFn) == "function") {
                        errorFn(request, status, error);
                    }
                    else {
                        // error 처리(권한)
                        if (request.status == '401' || request.status == '403') {
                            alert(MSG_COMM_E016);
                        }
                        else {
                            alert(MSG_COMM_1002);
                        }
                    }
                    isResult = 'syserr';
                }
            });

            return isResult;
        },
        /**
         * jquery-form 플러그인을 이용한..... SUBMIT 기능구현
         * 
         * 파일첨부 가능
         * 
         * jquery.form.js 파일이 포함되어야 함.
         * arg 속성 :
         *     - formId   : 대상 fora태그의 id
         *     - target   : form의 target 속성에 적용 (기본값:_self)
         *     - params   : 추가 파라미터 정의
         *
         * 사용 예)
         *      var result = $.ajaxUtil.ajaxForm(ROOT_PATH+"/usr/temp/deltSampleJson.do", {
         *          formId   : "form1",
         *          target   : "_self",
         *          params   : {
         *              aaaa : "aaaval",
         *              bbbb : "bbbval"
         *          }
         *      })
         *
         * 사용 예2) 기본은 async 속성이 false로 됨, 동시에 여러 ajax를 수행할 수 없음. true로 바꾸기 위해
         *           'asyncSuccess' 함수를 구현해주면 async 속성이 true 로 변경되면서 구현된 함수를 통해 결과를 처리할 수 있음.
         *           이 때 리턴받은 result는 의미 없음
         *
         *      var result = $.ajaxUtil.ajaxForm(ROOT_PATH+"/usr/temp/regiSampleExcel.do", {
         *          formId       : "form1",
         *          asyncSuccess : function(result){
         *              // 결과 처리
         *              if (result && result.rtnCode == '1') }{
         *                  ...
         *              }
         *      });
         */
        ajaxForm : function(url, args) {
            var isResult = true ;
            var errMsg   = null ;
            var isAlert  = false;   // alert 창으로 설정오류 표시 여부

            if (!args) {
                errMsg = '[ajaxUtil.ajaxForm] : 함수인자가 정의되지 않았습니다.';
                if (isAlert) alert(errMsg);
                return false;
            }
            if (!args.formId || args.formId == undefined || args.formId == null) {
                errMsg = '[ajaxUtil.ajaxForm] : 대상 FORM의 아이디가 지정되지 않았습니다.';
                if (isAlert) alert(errMsg);
                return false;
            }

            //----------------------------
            // form 객체 및 form 기본설정
            //----------------------------
            var $tgFormObj = $('#' + args.formId);

            if ($tgFormObj.length == 0) {
                errMsg = '[ajaxUtil.ajaxForm] : 지정된 FORM이 존재하지 않습니다.';
                if (isAlert) alert(errMsg);
                return false;
            }

            var enctypeVal  = 'multipart/form-data';    // enctype 기본 값

            //----------------------------
            // 파일객체 확인 및 객체 name 재설정
            //----------------------------
            var $fileObjs   = $tgFormObj.find('input[type=file]');
            if ($fileObjs.length > 0){
                // multipart로 변경
                enctypeVal  = 'application/x-www-form-urlencoded';

                // 파일 객체의 name 항목이 동일하지 않게 변경(동일하면 1개로 인식되는 문제있음)
                var fileIdx = 0;
                $fileObjs.each(function(){

                    var $fileObj = $(this);
                    var fileObjNm = "";
                    var orgObjNm  = $fileObj.data("org-name");

                    if(orgObjNm != undefined && orgObjNm != null && orgObjNm != ""){
                        fileObjNm = orgObjNm;
                    }
                    else {
                        fileObjNm = $fileObj.attr("name");
                        // 원래명 백업
                        $fileObj.attr("data-org-name", fileObjNm);
                    }
                    // 파일명을 순서대로 _0, _1, _2, ...를 붙여 준다
                    $fileObj.attr("name", fileObjNm+"_"+fileIdx)

                    fileIdx ++;
                });
            }

            //----------------------------
            // 최종 option 설정 및 submit 처리
            //----------------------------
            var formOptions = {
                //action  : (args.url),
                method: ((args.target) ? args.target : '_self'),
                enctype: enctypeVal,
            };
            if (url != null) {
                formOptions.action = url;
            }

            // form 객체 기본 속성설정
            $tgFormObj.attr(formOptions);

            // jquery-form 속성 설정
            var option = {
                dataType: 'json',
                processData: false,
                //contentType  : false,
                type: 'POST',
                data: args.params || {},
                async: false,
                success: function (result) {
                    isResult = result;
                },
                error: function (request, status, error) {
                    console.log(request.status +"/"+ request.responseText );
                    // error 처리(권한)
                    if (request.status == '401' || request.status == '403') {
                        alert(MSG_COMM_E016);
                    }
                    else {
                        alert(MSG_COMM_1002);
                    }
                    isResult = 'syserr';
                }
            }

            //----------------------------
            // async 속성을 false로 해서 진행하고 싶을 때
            // asyncSuccess (함수) 속성을 정의하면 됨
            //----------------------------
            var asyncSuccessFn = args.asyncSuccess;
            if (asyncSuccessFn && asyncSuccessFn != null && $.type(asyncSuccessFn) =="function" ) {
                option.async = true;
                option.success = function(result){
                    asyncSuccessFn(result);
                }
            }

            //----------------------------
            // 최종 submit 처리
            //----------------------------
            // ajaxForm을 설정 (jquery-form plugin)
            $tgFormObj.ajaxForm(option);
            // 설정후 submit 처리하면 수행 됨.
            $tgFormObj.submit();

            //----------------------------
            // 파일 객체명 원복
            // - 파일 객체명(name)이 변경됐을 때 발생할 문제 방지를 위해 원래대로 변경
            //----------------------------
            $fileObjs.each(function(){
                var $fileObj = $(this);
                var orgObjNm = $fileObj.data("org-name");
                $fileObj.attr("name", orgObjNm);
            });

            return isResult;
        },
        /**
         * Ajax Load (타입 json)
         * return true, false
         * AJAX 로딩후 결과데이터를 받아서 처리하는 콜백함수를 인자로 받는다.
         * $.ajaxUtil.ajaxLoad(url, param, callback)
         * 2021.07.15 LSH add
         */
        ajaxLoad: function (url, param, callback) {
            var isResult = true;

            $.ajax({
                url: url,
                data: param,
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data == 'true') {
                        isResult = true;
                    } else if (data == 'false') {
                        isResult = false;
                    }
                    else {
                        isResult = data;
                    	if (callback) {
                    		callback(data);
                    	}
                    }
                }, error: function (request, status, error) {
                    // error 처리(권한)
                    if (request.status == '401' || request.status == '403') {
                        // MSG : 권한이 없거나 세션이 만료되었습니다.
                        alert(MSG_COMM_E016);
                    }
                    else {
                        // MSG : 시스템 오류가 발생하였습니다.
                        alert(MSG_COMM_1002);
                    }
                    isResult = 'syserr';
                }
            });
            return isResult;
        },
        /**
         * Ajax Save (타입 json List)
         * return true, false
         * JSON LIST 데이터를 저장한다.
         * $.ajaxUtil.ajaxSave(url, param, callback)
         * 2021.08.10 LSH add
         */
        ajaxSave: function (url, param, callback, errorCallback) {
            $.ajax({
                url: url,
                data: param,
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    if (data && callback) {
                   		callback(data);
                    }
                },
                error: function(request, status, error) {
					$.ajaxUtil.error(request, status, error, errorCallback);
				}
            });
        },
        /**
         * AJAX 통신 결과 메세지 처리
         * 2022.02.07 LSH ADD
         */
		result: function (ret, callback, failCallback) {
			let r = $(ret);
			if (r.find('.exception_detail_message') &&
				r.find('.exception_detail_message').length > 0) {
				$.commMsg.alert(r.find('.exception_detail_message').html(), failCallback);
				return false;
			}
			if (r.find('.exception_message') &&
				r.find('.exception_message').length > 0) {
				$.commMsg.alert(r.find('.exception_message').html(), failCallback);
				return false;
			}
			if (r.find('h3.error') &&
				r.find('h3.error').length > 0) {
				$.commMsg.alert(r.find('h3.error').html(), failCallback);
				return false;
			}
			if (ret['Code'] < 0) {
				$.commMsg.alert('[Code:' + ret['Code'] + '] ' + ret['Message'], failCallback);
				return;
			}
			else {
				if (callback) 
					callback(ret);
			}
		},
        /**
         * AJAX 통신 오류 발생시 공통 처리 로직
         * ajax의 error 함수에 맵핑하여 사용한다.
         * 2021.08.11 LSH ADD
         */
        error: function(request, status, error, errorCallback) {
            // error 처리(권한)
            if (request.status == '401' || request.status == '403') {
                // MSG : 권한이 없거나 세션이 만료되었습니다.
                alert(MSG_COMM_E016);
            }
            else {
				// 2022.02.07 결과메세지 공통처리
				$.ajaxUtil.result(request.responseText, function() {
	                // MSG : 시스템 오류가 발생하였습니다.
	                alert(MSG_COMM_1002);
				}, errorCallback)
            }
        },
        /**
         * AJAX 통신 결과 공통 처리 로직
         * ajax의 success 함수에서 필요시 사용한다.
         * 2021.08.11 LSH ADD
         * 2021.12.01 LSH 에러페이지 확인로직 추가
         */
        success: function(data, callback, failCallback) {
			// 2022.02.07 결과메세지 공통처리
			$.ajaxUtil.result(data, callback, failCallback);
        }
    };
    // END of '$.ajaxUtil'

    /**
     * form 관련 유틸 함수
     */
    $.formUtil = {
        /**
         * form에 대해 submit 처리하는 유틸 함수
         *
         * <기능>
         * - formId가 지정되지 않거나 해당 id의 form이 존재하지 않으면 임시 form을 생성
         * - 존재하는 formId 값이 지정되면 해당 form으로 submit 진행
         * - 해당 form에 첨부파일객체(type='file')이 있으면 enctype을 'application/x-www-form-urlencoded'로 자동 설정
         *
         * <option 항목>
         *      formId : form의 id 항목 (지정되지 않으면 임시 form 생성)
         *      method : form의 method 속성값 (default : post)
         *      target : form의 target 속성값 (default : _self)
         *      params : 추가 파라미터 정의 (hidden 객체로 추가됨)
         *
         * <샘플 1> : form1 지정
         * $.formUtil.submitForm(ROOT_PATH +"/usr/temp/viewSample.do", {
         *           formId : 'form1',
         *           method : 'post',
         *           params : {
         *               tempSeq : tempSeqVal
         *           }
         *       });
         *
         * <샘플 2> : form 지정 안함(임시 form 생성)
         * $.formUtil.submitForm(ROOT_PATH +"/usr/temp/viewSample.do", {
         *           params : {
         *               tempSeq : tempSeqVal
         *           }
         *       });
         */
        submitForm : function(url, option){
            var defOpt = {
                method : "post",
                target : "_self",
                params : {}
            };

            var newOption   = $.extend({}, defOpt, option);
            var formId      = newOption.formId;
            var tempParamCls= "TEMP_PARAM_89874578JAFU766FHKF";

            //----------------------------
            // 지정된 formId가 없거나 해당 form이 존재하지 않을 때 임시 form 객체를 생성
            //----------------------------
            if (formId == undefined || formId == null || $.trim(formId) == "" || $("#"+formId).length == 0 ) {
                // 임시 form id
                formId  = "56137EYAF-COMM-FORM845KJFI";

                // 임시 form 생성
                $("#"+formId).remove();
                $("body").append("<form id='"+formId+"'></form>");
            }

            // form 객체
            var enctypeVal  = 'multipart/form-data';    // enctype 기본 값
            var $form       = $("#"+formId);

            // 만일 form 내에 임시 생성한 param이 존재하면 삭제처리
            $form.find("."+tempParamCls).remove();

            //----------------------------
            // 파일객체 확인 및 객체 name 재설정
            //----------------------------

            var $fileObjs   = $form.find('input[type=file]');
            if ($fileObjs.length > 0) {
                // multipart로 변경
                enctypeVal  = 'application/x-www-form-urlencoded';

                // 파일 객체의 name 항목이 동일하지 않게 변경(동일하면 1개로 인식되는 문제있음)
                var fileIdx = 0;
                $fileObjs.each(function() {

                    var $fileObj = $(this);
                    var fileObjNm = "";
                    var orgObjNm  = $fileObj.data("org-name");

                    if (orgObjNm != undefined && orgObjNm != null && orgObjNm != "") {
                        fileObjNm = orgObjNm;
                    } else {
                        fileObjNm = $fileObj.attr("name");
                        // 원래명 백업
                        $fileObj.attr("data-org-name", fileObjNm);
                    }
                    
                    // 파일명을 순서대로 _0, _1, _2, ...를 붙여 준다
                    $fileObj.attr("name", fileObjNm+"_"+fileIdx)

                    fileIdx ++;
                });
            }

            //----------------------------
            // 지정된 파라미터 추가 작업
            //----------------------------
            if (newOption.params) {
                for (var prm in newOption.params ) {
                    var $lastChildObj = $form.find(":last");
                    var objHtml       = "<input type='hidden' name='"+prm+"' value='"+newOption.params[prm]+"' class='"+tempParamCls+"'/>";
                    
                    // child가 없으면 (form의 html으로 대체)
                    if ($lastChildObj.length == 0) {
                        $form.html(objHtml);
                    }
                    // 존재하면 (객체 뒤에 추가)
                    else {
                        $lastChildObj.after(objHtml);
                    }
                }
            }

            //----------------------------
            // 최종 submit 처리
            //----------------------------
            if (url && $.trim(url) != "") {
                $form.attr("action",  url)
                     .attr("method",  newOption.method)
                     .attr("target",  newOption.target)
                     .attr("enctype", enctypeVal)
                     // 2020.08.25 제거
                     //.submit()
                     ;

                // 2020.08.25 수정 : form을 jquery의 객체로 submit했을 때 해당 form이 jquery-form으로 사용되었다면 이상 동작을 함 (ajax로 전송됨)
                // => html dom객체로 submit하면 해결됨
                $form.get(0).submit();
            }
            else {
                console.error("[FORM ACTION NULL ERROR!!]");
            }
        },
        /**
         * 폼체크
         * $.formUtil.formCheck( $obj,     formNm,   isRequired, maxLength)
         * $.formUtil.formCheck( $("#id"), '아이디', true,       50);
         *
         * <항목>
         * $obj : 해당 항목으로 전달(jquery 객체), radio, checkbox -> $("input[name=useStts]")
         * formNm     : 해당 항목 명
         * isRequired : true이면 필수, false이면 필수 아님
         * maxLength  : 최대 허용 문자열 길이
         *
         */
        formCheck : function($obj, formNm, isRequired, maxLength){
            // 전달된 object가 없을 때
            if($obj == undefined || $obj == null || $obj.length == 0) {
                console.log("#Not found object");
                return null;
            }

            if(!maxLength || maxLength == undefined || maxLength == null){
                maxLength = 0;
            }

            var tagName = $obj.prop("tagName").toLowerCase();
            var formType= $obj.attr("type");
            var formVal = $.trim($obj.val());
            var $form   = $obj.closest("form");
            var msgTail = " 항목을 입력(선택)하세요.";
            var errorMsg= "";

            var isInput = true;
            if(isRequired) {
                switch(formType) {
                    case "text":
                        if( formVal == '' || formVal == null){
                            $obj.val("");
                            $obj.focus();
                            isInput = false;
                        }
                        break;
                    case "checkbox":
                        if($obj.is(":checked") == false) {
                            msgTail = " 항목을 선택하세요."
                            isInput = false;
                        }
                        break;
                    case "radio":
                        if($obj.is(":checked") == false) {
                            msgTail = " 항목을 선택하세요."
                            isInput = false;
                        }
                        break;
                    default : // textarea
                        if( formVal == '' || formVal == null){
                            $obj.val("");
                            $obj.focus();
                            isInput = false;
                        }
                        break;
                }// end of swicth

                if(!isInput){
                    errorMsg = formNm + msgTail;
                    // TODO : 메시지 출력은 추후 출력 방식에 맞게 변경해야 함
                    alert(errorMsg);
                    return false;
                }
            }// end of 'if(isRequired)'

            if(maxLength > 0 ){

                if(maxLength < parseInt($.commUtil.getStrByteLength(formVal))) {
                    errorMsg = formNm+" 항목은 한글 " + parseInt(maxLength/2)+"자, 영문 "+parseInt(maxLength)+"자, 숫자 " + parseInt(maxLength) + "자 이상 입력할 수 없습니다.";
                    // TODO : 메시지 출력은 추후 출력 방식에 맞게 변경해야 함
                    alert(errorMsg);
                    return false;
                }
            }

            return true;
        }, 
        // END of 'formCheck'
        /**
         * 객체의 데이터를 폼 객체 개별항목(ID기준)에 셋팅하는 함수
         * 2021.07.21 LSH 추가
         */
        toForm: function(data, form, prefix) {

            for (var p in data) {
                
                var key = (prefix ? prefix : '')+p;
                var val = data[p];
        
                if (form) {
	                var obj = form.find('[name="'+key+'"]');
	                if (!obj || !obj.length)
	                    continue;
	                if (obj.is("input:checkbox") || 
	                	obj.is("input:radio")) {
	                	obj.each(function() {
	                		if($(this).val() == val)
	                			$(this).prop('checked', true);
	                	});
	                }
	                else {
		                obj.val(val);
	                }
                }
                else {
	                var obj = $('#'+key);
	                
	                if (!obj || !obj.length)
	                    continue;
	        
	                if (obj.is("span") || obj.is("div") || obj.is("p")) {
	                    obj.html(val);
	                }
	                else if (obj.is("input:checkbox") || obj.is("input:radio")) {
	                    if (obj.val() == val)
	                        obj.prop("checked", true);
	                }
	                else {
	                    obj.val(val);
	                }
                }
            }
        },

        /**
         * 폼 박스의 값을 객체로 생성, 반환하는 함수
         * serializeObject와 비슷하나, 
         * 인자로 넘어온 키에 해당하는 데이터만 생성한다.
         * 2021.08.06 LSH 추가
         * 
         * $.formUtil.toObject( $('#selectForm'), ['userId','userNm']);
         */
        toObject: function(form, keys) {
        	
        	var obj = form.serializeObject();
        	var ret = {};

        	$.each(keys, function(i,key) {
                if (obj && obj[key]) {
                	ret[key] = $.commUtil.nvl(obj[key]);
                }
        	});
        	return ret;
        },
        /**
         * 객체의 값을 KEY에 해당하는 ID를 가진 레이어에 셋팅하는 함수
         * 
         * 2021.08.06 LSH 추가
         * 
         * $.formUtil.toHtml( $('#selectForm'), data, 'select-' );
         */
        toHtml: function(dom, data, prefix) {
        	dom.find("[id^='"+prefix+"']").each(function(i,e) {
	            var key = $(this).prop('id').substring(prefix.length);

	            if (data[key]) 
	                $(this).html(data[key]);
	            else
	                $(this).html('&nbsp;');
	        });
        },
        /**
         * 객체의 값을 KEY에 해당하는 ID를 가진 레이어에 셋팅하는 함수
         * 값이 데이터만 교체
         * 
         * 2022.11.15 JWH 추가
         * 
         * $.formUtil.toHtmlPatch( $('#selectForm'), data, 'select-' );
         */
        toHtmlPatch: function(dom, data, prefix) {
        	dom.find("[id^='"+prefix+"']").each(function(i,e) {
	            var key = $(this).prop('id').substring(prefix.length);

	            if (data[key]) 
	                $(this).html(data[key]);
	        });
        },
        /**
         * prefix로 시작하는 ID를 가진 레이어의 값을 리셋하는 함수
         * 
         * 2021.10.20 LSH 추가
         * 
         * $.formUtil.toHtmlReset( $('#selectForm'), 'select-' );
         */
        toHtmlReset: function(dom, prefix) {
        	dom.find("[id^='"+prefix+"']").each(function(i,e) {
	            var key = $(this).prop('id').substring(prefix.length);
	            $(this).html('&nbsp;');
	        });
        },        

        // 아이디 유효성 체크 영문자(소) + 숫자 + 6~15자리
        // $.formUtil.isIdCheck(obj);
        isIdCheck: function (obj) {
            var chk1 = /[\S]{6,15}$/;
            var chk2 = /^[a-z0-9]+$/g;

            return chk1.test(obj) && chk2.test(obj) ? true : false;
        },
        // 비밀번호체크 9~20자리 + 영문자(대)+영문자(소)+숫자+특수문자
        // $.formUtil.isPwdCheck(obj);
        isPwdCheck: function (obj) {
            var chk1 = /[\S]{9,20}$/;
            var chk2 = /[a-z]/;
            var chk3 = /[A-Z]/;
            var chk4 = /\d/;
            var chk5 = /[^a-zA-Z0-9]/;

            return chk1.test(obj) && chk2.test(obj) && chk4.test(obj) && chk5.test(obj) ? true : false;
        },
        // 비밀번호 반복문자 4개 이상 처리
        // $.formUtil.isPwdOverCheck(obj);
        isPwdOverCheck: function (obj) {
            var chk1 = /(\w)\1\1\1/;

            return chk1.test(obj) ? true : false;
        },
        // list 체크박스 array id
        // $.formUtil.isArrCheck(obj);
        isArrCheck: function (obj) {
            var arr = new Array();
            var i = 0;

            $('input[id="' + obj + '"]:checked').each(function () {
                arr[i] = $(this).val();
                i++;
            });

            return arr;
        },
        // list 체크박스 array name
        // $.formUtil.isArrNameCheck(obj);
        isArrNameCheck: function (obj) {
            var arr = new Array();
            var i = 0;

            $('input[name="' + obj + '"]:checked').each(function () {
                arr[i] = $(this).val();
                i++;
            });

            return arr;
        },
        // list 체크박스 array id, attr
        // $.formUtil.isArrAttrCheck(obj, attrName);
        isArrAttrCheck: function (obj, attrName) {
            var arr = new Array();
            var i = 0;

            $('input[id="' + obj + '"]:checked').each(function () {
                arr[i] = $(this).attr(attrName);
                i++;
            });

            return arr;
        },
        // list 체크박스 array id, attr, 암호화
        // $.formUtil.isArrAttrCheckHash(obj, attrName);
        isArrAttrCheckHash: function (obj, attrName) {
            var arr = new Array();
            var i = 0;

            $('input[id="' + obj + '"]:checked').each(function () {
                arr[i] = hex_sha512($(this).attr(attrName)).toLowerCase();
                i++;
            });

            return arr;
        },
        // $.formUtil.isCheckBoxAll(obj); obj = '.변수명'
        isCheckBoxAll: function (obj) {
            if ($(obj + ':checked').length != $(obj).length) {
                return false;
            }
            else {
                return true;
            }
        },
        // 텍스트박스 array id
        // $.formUtil.isArrIdText(obj);
        isArrIdText: function (obj) {
            var arr = new Array();
            var i = 0;

            $('input[id="' + obj + '"]').each(function () {
                if ($.trim($(this).val()) != '') {
                    arr[i] = $.trim($(this).val());
                    i++;
                }
            });

            return arr;
        },
        // 텍스트박스 array name
        // $.formUtil.isArrNameText(obj);
        isArrNameText: function (obj) {
            var arr = new Array();
            var i = 0;

            $('input[name="' + obj + '"]').each(function () {
                if ($.trim($(this).val()) != '') {
                    arr[i] = $.trim($(this).val());
                    i++;
                }
            });

            return arr;
        },
        // 텍스트박스 숫자 Check
        // $.formUtil.isNumCheck(obj);
        isNumCheck: function (obj, msg) {
            var reg = /^(\s|\d)+$/;

            if (reg.test($(obj).val())) {
                return true;
            }
            else {
                //$.commModal.alertView(msg + '은(는) 숫자만 입력하세요.');
                $.commMsg.alert(msg + '은(는) 숫자만 입력하세요.');
                $(obj).val('');
                return false;
            }
        },
        // radio id
        // $.formUtil.isIdRadio(obj);
        isIdRadio: function (obj) {
            return val = $(':radio[id="' + obj + '"]:checked').val();
        },
        // radio name
        // $.formUtil.isNameRadio(obj);
        isNameRadio: function (obj) {
            return $(':radio[name="' + obj + '"]:checked').val();
        },
        // Birthday check
        // $.formUtil.isBirthday(obj);
        isBirthday: function (obj) {
            var reg = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
            if (reg.test($(obj).val())) {
                return true;
            }
            else {
                //$.commModal.alertView('생년월일을 정확히 입력해주세요.');
                $.commMsg.alert('생년월일을 정확히 입력해주세요.');
                $(obj).val('');
                return false;
            }
        },
        /**
         * Email Check
         * @param   email
         * @return  boolean
         * 
         * $.formUtil.isEmail(obj);
         */
        isEmail: function(email) {
            re = /[^@]+@[A-Za-z0-9_-]+[.]+[A-Za-z]+/;

            if (re.test(email)) {
                return  true;
            }
            return  false;
        },
        /**
         * 2021.10.06 LSH
         * 주민등록번호,이메일,전화번호,휴대전화번호,날짜를 분리하며 INPUT에 맵핑
         * 분리된 데이터가 담길 element는 idKey+(1부터 시작하는 sequence) 로 
		 * ID가 정의되어 있어야 한다.
         * @param idKey form element 기준ID
         * @param stype 종류('rno', 'email', 'phone', 'mobile', 'date', 'month')
         * 
         * $.formUtil.splitData('emlAddr', 'email');
         */
        splitData: function(idKey, stype) {
			
			let data = $('#'+idKey).val();
			
			if ($.commUtil.empty(data))
				return;
			
			if (stype == 'email') {
				let arr = data.split('@');
				$(arr).each(function(i,d) {
					let sel = $('#'+idKey+(i+1));
					if (sel && sel.length > 0) {
						sel.val(d);
					}
				});
			}
			else if (stype == 'rno') {
				data = data.replace(/\-/g, ''); // 구분자 '-' 제거
				if (data.length == 13) {
					$('#'+idKey+'1').val(data.substring(0,6));
					$('#'+idKey+'2').val(data.substring(6));
				}
			}
			else if (stype == 'phone' || stype == 'mobile') {
				// 전화번호 형식화
				data = $.commUtil.phoneFormatter(data);
				let arr = data.split('-');
				$(arr).each(function(i,d) {
					let sel = $('#'+idKey+(i+1));
					sel.val(d);
				});
			}
			// 날짜(YYYYMMDD 타입)
			else if (stype == 'date') {
				data = data.replace(/\-/g, ''); // 구분자 '-' 제거
				data = data.replace(/\./g, ''); // 구분자 '.' 제거
				if (data.length == 8) {
					$('#'+idKey+'1').val(data.substring(0,4));
					$('#'+idKey+'2').val(data.substring(4,6));
					$('#'+idKey+'3').val(data.substring(6,8));
				}
			}
			// 년월(YYYYMM 타입)
			else if (stype == 'month') {
				data = data.replace(/\-/g, ''); // 구분자 '-' 제거
				data = data.replace(/\./g, ''); // 구분자 '.' 제거
				if (data.length == 6) {
					$('#'+idKey+'1').val(data.substring(0,4));
					$('#'+idKey+'2').val(data.substring(4,6));
				}
			}
        },
        /**
         * 2021.10.06 LSH
         * 분리된 주민번호,이메일,전화번호,휴대전화번호,날짜를 병합하여 INPUT에 맵핑
         * 분리된 데이터가 담긴 element는 idKey+(1부터 시작하는 sequence) 로 
		 * ID가 정의되어 있어야 한다.
         * @param idKey form element 기준ID
         * @param stype 종류('rno', 'email', 'phone', 'mobile', 'date', 'month')
         * @param len   분리된 element 갯수
         * 
         * $.formUtil.mergeData('emlAddr', 'email');
         */
        mergeData: function(idKey, stype, len) {
	
			$('#'+idKey).val('');
			
			let arr = [];
			for (let i = 1; i < (len+1); i++) {
				let v = $('#'+idKey+i).val();
				if (!$.commUtil.empty(v))
					arr.push(v);
			}
			if (stype == 'email') {
				if (arr.length == 2) {
					$('#'+idKey).val(arr[0]+'@'+arr[1]);
				}
			}
			else if (stype == 'rno') {
				if (arr.length == 2) {
					$('#'+idKey).val(arr[0]+''+arr[1]);
				}
			}
			else if (stype == 'phone' || stype == 'mobile') {
				if (arr.length == 3) {
					$('#'+idKey).val(arr[0]+''+arr[1]+''+arr[2]);
				}
			}
			else if (stype == 'date') {
				if (arr.length == 3) {
					$('#'+idKey).val(arr[0]+''+arr[1]+''+arr[2]);
				}
			}
			else if (stype == 'month') {
				if (arr.length == 2) {
					$('#'+idKey).val(arr[0]+''+arr[1]);
				}
			}
        }
    }; 
    // END of '$.formUtil'

    /**
     * 공통 함수 관련
     */
    $.commUtil = {
        /**
         * 문자열이 비어 있는지 확인
         * $.commUtil.isEmpty(value);
         */
        isEmpty : function(str) {
            if(str == null){
                return true;
            }
            if($.type(str) == "string") {
                return $.trim(str) == "";
            }
            return false;
        },
        /**
         * 유효한 날짜 체크
         * $.commUtil.isDate(value);
         */
        isDate: function (str) {
            if ($.type(str) == 'string')
                str = str.replace(/\D/g, '');
            else
                str = str.val().replace(/\D/g, '');

            if (isNaN(str) || str.length != 8) {
                return false;
            }

            var year = Number(str.substring(0, 4));
            var month = Number(str.substring(4, 6));
            var day = Number(str.substring(6, 8));

            var dd = day / 0;

            if (month < 1 || month > 12) {
                return false;
            }

            var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            var maxDay = maxDaysInMonth[month - 1];

            // 윤년 체크
            if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
                maxDay = 29;
            }

            if (day <= 0 || day > maxDay) {
                return false;
            }

            return true;
        },

        /**
         * 문자열 길이값 구하기
         * $.commUtil.getStrByteLength(value);
         */
        getStrByteLength : function(str) {
            var len = 0;

            if (str == '') {
                return len;
            } else {
                for (var i=0;i<str.length;i++) {
                    var chr = str.charCodeAt(i);
                    if (chr > 0 && chr < 255) {
                        len ++;
                    } else {
                        len = len + 2;
                    }
                }
            }
            return len;
        },

        /**
         * 문자열 Null 체크
         * $.commUtil.nvlTrim(str);
         */
        nvlTrim : function(str) {
            if (str == undefined || str == null){
                return "";
            }
            return $.trim(str);
        },

        /**
         * 문자열 전체 replace
         * $.commUtil.replaceAll(str, before, after);
         */
        replaceAll : function(str, before, after){
            if(str == undefined || str == null){
                return "";
            }

            if($.type(before) == 'regexp')
                return str.replace(before,after);
            else
                return str.replace(new RegExp(before,'g'), after);
        },
        
        /**
         * 2021.08.05 LSH
         * undefined 공백처리
         * $.commUtil.nvl(str)
         */
        nvl : function(val) {
        	if (val == null      ||
        		val == undefined ||
        		val == 'undefined') {
        		return '';
        	}
        	return val;
        },
        
        /**
         * 2021.08.05 LSH
         * 객체데이터의 NVL처리
         * $.commUtil.nvlObject(str)
         */
        nvlObject: function(data) {
        	for (var p in data) {
        		data[p] = $.commUtil.nvl(data[p]);
        	}
        	return data;
        },
        
        /**
         * 2021.12.06 LSH
         * undefined 0으로 변환
         * $.commUtil.nvlInt(str)
         */
        nvlInt : function(val) {
        	if (val == null      ||
        		val == undefined ||
        		val == 'undefined') {
        		return 0;
        	}
			val = val.replace(/,/g,'');
			return (val == '' ? 0 : parseInt(val));
        },
        
        /**
         * 2022.12.22 LSH
         * undefined 0으로 변환
         * $.commUtil.nvlFloat(str)
         */
        nvlFloat : function(val) {
        	if (val == null      ||
        		val == undefined ||
        		val == 'undefined') {
        		return 0;
        	}
			val = val.replace(/,/g,'');
			return (val == '' ? 0 : parseFloat(val));
        },

        /**
         * 2021.08.05 LSH
         * 빈값 체크
         * $.commUtil.empty(str)
         */
        empty : function(val) {

        	if (typeof val == "array") {
        		if (val.length>0)
        			return false;
        		return true;
        	}
        	if (!val)
        		return true;
        	if (val == null)
        		return true;
        	if (val == 'undefined')
        		return true;
        	if (val == '')
        		return true;

        	return false;
        },
        /**
         * 2021.10.06
         * 전화번호를 형식에 맞게 변환하여 반환한다.
		 * num : '-' 문자가 들어있지않은 숫자로된 전화번호
		 * type : 0을 보내면 가운데자리를 숨겨준다
         * $.commUtil.phoneFormatter(num,type)
         */
		phoneFormatter : function(num, type) {
			var formatNum = '';
			if (num == null)
				return '';
	    	if (num.length==11) {
	        	if (type==0)
	            	formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
				else
					formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
	    	}
			else if (num.length==9) {
               	formatNum = num.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
	    	}
			else if (num.length==8) {
	        	formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
			}
			else {
	        	if (num.indexOf('02')==0) {
	            	if (type==0)
	                	formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
					else
	                	formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
	        	}
				else {
	            	if (type==0)
	                	formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
	            	else
	                	formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
	        	}
	    	}
	    	return formatNum;
		},
		// 날짜를 Date 타입으로 변환
		toDate: function( str ) {
			if (str == null)
				return null;
			str = str.replace(/-/gi, "");
			if (!/^[0-9]*$/.test(str))
				return null;
			if (str.length < 8)
				return null;
			const y = parseInt(str.substring(0,4));
			const m = parseInt(str.substring(4,6))-1;
			const d = parseInt(str.substring(6,8));
			return new Date(y,m,d);
		},
		// 날짜비교
		compareDate: function (str1, str2) {
			let d1 = $.commUtil.toDate(str1);
			let d2 = $.commUtil.toDate(str2);
			if      (d1.getTime() < d2.getTime()) return -1;
			else if (d1.getTime() > d2.getTime()) return  1; 
			else return 0; 
		},
		toFormatDate: function( d, delim ) {
			if (d == null)
				return null;
 			const year  = d.getFullYear(); 
			const month = d.getMonth() + 1; 
			const date  = d.getDate(); 
			return year + delim +
			      (month >= 10 ? month : '0'+month) + delim +
				  (date  >= 10 ? date  : '0'+date );
		},
		// 날짜 더하기
		addDays: function( str, days ) {
			let d = null;
			if ($.type(str) == 'date')
				d = str;
			else {
				d = $.commUtil.toDate(str);
				if (d == null)
					d = new Date();
			}
			let nd = new Date(d.getTime());
			nd.setDate(d.getDate()+days);
			return ($.type(str) == 'date' ? nd : $.commUtil.toFormatDate(nd,''));
		},
		addMonths: function( str, months ) {
			let d = null;
			if ($.type(str) == 'date')
				d = str;
			else {
				d = $.commUtil.toDate(str);
				if (d == null)
					d = new Date();
			}
			let nd = new Date(d.getTime());
			nd.setMonth(d.getMonth()+months);
			return ($.type(str) == 'date' ? nd : $.commUtil.toFormatDate(nd,''));
		},
		addYears: function( str, years ) {
			let d = null;
			if ($.type(str) == 'date')
				d = str;
			else {
				d = $.commUtil.toDate(str);
				if (d == null)
					d = new Date();
			}
			let nd = new Date(d.getTime());
			nd.setFullYear(d.getFullYear()+years);
			return ($.type(str) == 'date' ? nd : $.commUtil.toFormatDate(nd,''));
		},
		// 해당월 마지막 날짜 구하기
		lastDay: function( str ) {
			let d = null;
			if ($.type(str) == 'date')
				d = str;
			else {
				d = $.commUtil.toDate(str);
				if (d == null)
					d = new Date();
			}
			let nd = new Date(d.getTime());
			nd.setMonth(d.getMonth()+1);
			nd.setDate(0);
			return ($.type(str) == 'date' ? nd : $.commUtil.toFormatDate(nd,''));
		},
		// 날짜입력값을 YYYYMMDD 로 변환
		toSimpleDate: function( str ) {
			if (str == null)
				return null;
			str = str.replace(/-/gi, "");
			if (!/^[0-9]*$/.test(str))
				return null;
			if (str.length < 8)
				return null;
			return str;
		},
		// 날짜입력값에서 YYYY 를 반환
		getYearStr: function( str ) {
			str = $.commUtil.toSimpleDate(str);
			if (str == null)
				return null;
			return str.substring(0,4);
		},
		// 날짜입력값에서 MM 를 반환
		getMonthStr: function( str ) {
			str = $.commUtil.toSimpleDate(str);
			if (str == null)
				return null;
			return str.substring(4,6);
		},
		// 날짜입력값에서 DD 를 반환
		getDayStr: function( str ) {
			str = $.commUtil.toSimpleDate(str);
			if (str == null)
				return null;
			return str.substring(6,8);
		},
		// 현재 년월을 YYYYMM으로 반환
		getCurYearMonth: function() {
			let d = new Date();
			let m = d.getMonth() + 1; 
			return d.getFullYear() + '' +
			      (m >= 10 ? m : '0'+m);
		},
		// 현재 년도 반환
		getCurYear: function() {
			let d = new Date();
			return d.getFullYear();
		},
		// 현재 월 반환
		getCurMonth: function() {
			let d = new Date();
			let m = d.getMonth() + 1; 
			return (m >= 10 ? m : '0'+m);
		},
    };   
    // END of '$.commUtil'

    /**
     * 폼 태그 값 Clear
     * 
     * $.fn.resetForm('#form1 .style li', false);
     * $.fn.resetForm('대상 Form Object', hidden 태그 대상여부);
     */
    $.fn.resetForm = function (objForm, isClearHidden) {
        var $formObj = null;

        if (objForm && $.type(objForm) == 'string') {
            $formObj = $(objForm);
        }
        else {
            $formObj = objForm;
        }
        if ($formObj != null && $formObj.length > 0) {
            var isHidden = true;

            if (isClearHidden != undefined && ($.type(isClearHidden) == 'boolean' && !isClearHidden)) {
                isHidden = false;
            }
            if (isHidden) {
                $formObj.find('input[type=hidden]').val('');
            }

            $formObj.find('input:text').val('');
            $formObj.find('input:password').val('');
            $formObj.find('textarea').val('');
            $formObj.find('input:radio').prop('checked', false);
            $formObj.find('input:checkbox').prop('checked', false);
            $formObj.find('select').each(function () {
                // aria-controls attribute를 보유하고 있는 대상은 제외한다.
                if( !$(this).is('[aria-controls]')) {
                    $(this).find('option:eq(0)').prop('selected', true);
                    $(this).trigger('change');
                }
            });
        }

        return $formObj;
    };
    
    /**
     * 콤보박스 로드
     * 
     * $("#srchRoleId").loadSelect(result.list).trigger('change');
     */
    $.fn.loadSelect = function (optionsDataArray, firstIdx) {
        return this.emptySelect(firstIdx).each(function () {
            if (this.tagName == 'SELECT') {
                var selectElement = this;

                $.each(optionsDataArray, function (idx, optionData) {
                    var option = new Option(optionData.text, optionData.code);

                    selectElement.add(option, null);
                });
            }
        });
    };
    $.fn.emptySelect = function (firstIdx) {
        return this.each(function () {
            if (firstIdx == undefined) firstIdx = 1;
            if (this.tagName == 'SELECT') this.options.length = firstIdx;
        });
    };
    
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    
    /**
     * 파일관련 공통 유틸
     * 2021.08.19 LSH 추가
     */
    $.fileUtil = {
    		
		// 파일 포맷 문자열 반환
		formatBytes: function( bytes ) {
			var units = new Array('Bytes', 'KB', 'MB', 'GB');
			var i = 0;
			var c = bytes;
			while (c > 900) {
				c /= 1024;
				i++;
			}
			var s = (Math.round(c*100)/100)+' '+units[i];
			return s;
		},
		
		// 파일 용량체크
		checkMaxbytes: function( fileObj, maxBytes, isAlert ) {
			var bytes = 0;
			// IE용인데 IE8이하는 안됨...
			if (window.ActiveXObject) {
				var fso = new ActiveXObject("Scripting.FileSystemObject");
				var f = fileObj[0].value;
				var o = fso.getFile(f);
				bytes = o.size;
			}
			// IE 외
			else {
				bytes = fileObj[0].files[0].size;
			}
			var s1 = $.fileUtil.formatBytes(maxBytes);
			var s2 = $.fileUtil.formatBytes(bytes);

			if (bytes > maxBytes) {
				if (isAlert) {
					var msg = "첨부파일은 ["+ s1 +"] 이하로 등록가능합니다. (현재크기 : "+s2+")";
					if ($.type(isAlert) === 'function')
						isAlert(msg);
					else
						$.commMsg.alert(msg);
				}
				return false;
			}
			return true;
		},

		// 첨부파일 확장자체크
		checkExtension: function( fileObj, extensions, isAlert ) {
			var fname = fileObj.val();
			var fext  = fname.split(".").pop().toLowerCase();
			if ($.inArray(fext, extensions) == -1) {
				if (isAlert) {
					var msg = "첨부파일은 ["+extensions.join()+"] 파일만 등록 가능합니다.";
					if ($.type(isAlert) === 'function')
						isAlert(msg);
					else
						$.commMsg.alert(msg);
				}
				return false;
			}
			return true;
		},

		// 2022.01.21 첨부파일 파일명길이체크
		checkMaxLength: function( fileName, maxLength, isAlert ) {
			if (!fileName)
				return true;
			if (fileName.length > maxLength) {
				if (isAlert) {
					var msg = [
						'파일명의 최대길이는',
						'['+maxLength+']자를 초과할 수 없습니다.',
						'(현재길이: ['+fileName.length+']자)'
					].join(' ');
					if ($.type(isAlert) === 'function')
						isAlert(msg);
					else
						$.commMsg.alert(msg);
				}
				return false;
			}
			return true;
		},
		
		// 파일명에서 확장자명 추출
		getExtension: function( fileName ) {
		    /** 
		     * lastIndexOf('.') 
		     * 뒤에서부터 '.'의 위치를 찾기위한 함수
		     * 검색 문자의 위치를 반환한다.
		     * 파일 이름에 '.'이 포함되는 경우가 있기 때문에 lastIndexOf() 사용
		     */
		    var _lastDot = fileName.lastIndexOf('.');
			if (_lastDot < fileName.length) {
			    // 확장자 명만 추출한 후 소문자로 변경
			    return fileName.substring(_lastDot+1).toLowerCase();
			}
			return '';
		},

		// BASE64 이미지 파일 다운로드
		downloadBase64Image: function( base64Image, fileName ) {
			if (window.navigator && 
				window.navigator.msSaveOrOpenBlob) {
				// 실제 데이터는 iVBO...부터이므로 split한다.
				var imgData = atob(base64Image.split(',')[1]);
				var len  = imgData.length;
				var buf  = new ArrayBuffer(len); // 비트를 담을 버퍼를 만든다.
				var view = new Uint8Array(buf); // 버퍼를 8bit Unsigned Int로 담는다.
				var blob, i;
				for (i = 0; i < len; i++) {
				  view[i] = imgData.charCodeAt(i) & 0xff; // 비트 마스킹을 통해 msb를 보호한다.
				}
				// Blob 객체를 image/png 타입으로 생성한다. (application/octet-stream도 가능)
				blob = new Blob([view], { type: 'image/png' });
			    window.navigator.msSaveOrOpenBlob(blob, fileName);
			    return;
			}
			var download      = document.createElement('a');
			download.href     = base64Image;
			download.target   = '_blank';
			download.download = fileName;
			var evt = document.createEvent('MouseEvents');
			evt.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
			download.dispatchEvent(evt);
		},

		// 캔버스 이미지 생성
		createCanvasImage: function( element, fileName ) {
            var dataURL = element.toDataURL('image/png');
            dataURL = dataURL.replace(/^data:image\/[^;]*/, 'data:application/octet-stream');
            dataURL = dataURL.replace(/^data:application\/octet-stream/, 'data:application/octet-stream;headers=Content-Disposition%3A%20attachment%3B%20filename='+fileName);
			return dataURL;
		},

		// 2021.12.30 미리보기 가능여부 반환
		enablePreview: function( type ) {
			return ($.inArray(type, ['PDF','HWP','IMG','TXT']) >= 0);
		},
		// 2021.08.17 미리보기가 가능한지 확인
		getPreviewType: function( fileName ) {
			// 확장자 추출
			let ext = $.fileUtil.getExtension( fileName );
			//if      (isIE())
			//	return 'NON';
			if      ($.inArray(ext, ['pdf']) >= 0)
				return 'PDF';
			else if ($.inArray(ext, ['hwp']) >= 0)
				return 'HWP';
			else if ($.inArray(ext, ['jpg','gif','png']) >= 0)
				return 'IMG';
			else if ($.inArray(ext, ['txt']) >= 0)
				return 'TXT';
			else
				return 'NON';
		},
		// 첨부파일 다운로드
		// args.params : 조건
		// args.url    : 다운로드 URL
		// args.log    : 이력등록설정
		// args.log.params : 이력등록조건 (필수값: atchFileSn, progUrl)
		download: function( args ) {
			if (args.log) {
				popup.openDownLog({
					params: args.log.params,
					saveCallback: function() {
						// 파일 다운로드 실행
						$.formUtil.submitForm(args.url, {params: args.params});
					}
				});
			}
			else {
				// 파일 다운로드 실행
				$.formUtil.submitForm(args.url, {params: args.params});
			}
			return false;
		},
		// 2023.01.09 LSH 첨부파일 다운로드 및 진행상태 표시
		// args.params : 조건
		// args.url    : 다운로드 URL
		// args.progress.start : 시작시 진행상태 표시함수
		// args.progress.ing   : 진행중 진행상태 표시함수
		// args.progress.end   : 종료시 표시함수
		// [사용예제]
		// $.fileUtil.downloadProgress( $.extend(args, {
		// 		progress: {
		//	 		start: function(url) {
		//	 			$('#footer').html(url);
		//	 		},
		//	 		ing: function(pr) {
		//	 			$('#footer').html('loaded='+pr.loaded+',total='+pr.total);
		//	 		},
		//	 		end: function(fileName) {
		//	 			$('#footer').html(fileName);
		//	 		}
		// 		}
		// }));
		// 
		downloadProgress: function( args ) {
			
			let url = args.url + '?' + $.param(args.params);

			if (args.progress &&
				args.progress.start)
				args.progress.start(url);

		    let blob;
			let fileName = '';
		    let xmlHTTP = new XMLHttpRequest();
		    xmlHTTP.open('GET', url, true);
		    xmlHTTP.responseType = 'arraybuffer';
		    xmlHTTP.onreadystatechange = function() {
				if (xmlHTTP.readyState == XMLHttpRequest.DONE) {
					if (xmlHTTP.status == 200) {
						let disposition = xmlHTTP.getResponseHeader("Content-Disposition");
						if (disposition && 
							disposition.indexOf("attachment") !== -1) {
						    let filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
						    let matches = filenameRegex.exec(disposition);
						    if (matches != null && matches[1]) {
						        fileName = decodeURI(matches[1].replace(/['"]/g, ""));
						    }
						}
					}
					//ERROR
					else {
						$.commMsg.alert('OCR 분석 중 오류가 발생하였습니다.');						
					}
				}
		    };
		    xmlHTTP.onload = function(e) {
		        blob = new Blob([this.response]);
		    };
		    xmlHTTP.onprogress = function(pr) {
		        //pr.loaded - current state
		        //pr.total  - max
				if (args.progress &&
					args.progress.ing)
					args.progress.ing(pr);
		    };
		    xmlHTTP.onloadend = function(e){

				if (args.progress &&
					args.progress.end)
					args.progress.end(fileName);

				if (xmlHTTP.status == 200) {
					// for IE
	        		if (window.navigator && window.navigator.msSaveOrOpenBlob) {
	            		window.navigator.msSaveOrOpenBlob(blob, fileName);
	        		} 
					else {
	            		var URL         = window.URL || window.webkitURL;
	            		var downloadUrl = URL.createObjectURL(blob);
	            		if (fileName) {
	                		var a = document.createElement("a");
		                	// for safari
		                	if (a.download === undefined) {
		                    	window.location.href = downloadUrl;
		                	} 
							else {
		                    	document.body.appendChild(a);
								a.style    = "display: none";
		                    	a.href     = downloadUrl;
		                    	a.download = fileName;
		                    	a.click();
								URL.revokeObjectURL(downloadUrl);
		                	}
		            	} 
						else {
		                	window.location.href = downloadUrl;
		            	}
	        		}
				}
		    }
		    xmlHTTP.send();
		},
		// 첨부파일 미리보기
		// args.type : 미리보기타입 (HWP / PDF / IMG / TXT)
		// args.url  : 파일URL
		preview: function( args ) {
			
			if (!$.fileUtil.enablePreview(args.type))
				return false;
			if (args.type == 'PDF') {
				// PDF VIEWER OPTIONS
				let pdfOptions = {
					pdfOpenParams: {
						navpanes: 0,
						toolbar: 0,
						statusbar: 0,
						view: "FitV",
						page: 1
					},
					width : "550px",
					height: "400px",
					forcePDFJS: true
				};
				if (isIE())
					pdfOptions['PDFJS_URL'] = getUrl("/plugins/pdfjs-2.12.13/es5/web/viewer.html");
				else
				 	pdfOptions['PDFJS_URL'] = getUrl("/plugins/pdfjs-2.12.13/viewer.html");
				$.commModal.openView({
					size:    BootstrapDialog.SIZE_WIDE,
					title:   '파일 미리보기',
					message: '<div id="filePreview" style="height:800px;"></div>',
					func: function() {
						PDFObject.embed(args.url, "#filePreview", pdfOptions);
					}
				});
			}
			else if (args.type == 'HWP') {
				let url = getUrl("/plugins/hwpjs-0.0.3/viewer.html?file=")+ args.url;
				$.commModal.openView({
					size:    BootstrapDialog.SIZE_WIDE,
					title:   '파일 미리보기',
					message: '<iframe id="filePreview" style="width:100%;height:800px;" src="'+url+'"></iframe>'
				});
			}
			else if (args.type == 'IMG') {
				$.commModal.openView({
					size:    BootstrapDialog.SIZE_WIDE,
					title:   '파일 미리보기',
					message: '<img id="filePreview" src="'+args.url+'" style="width:100%;height:100%;object-fit:contain;" />',
					cssClass: 'app-c'
				});
			}
			else if (args.type == 'TXT') {
				$.commModal.openView({
					size:    BootstrapDialog.SIZE_WIDE,
					title:   '파일 미리보기',
					message: '<iframe id="filePreview" style="width:100%;height:800px;" class="app-frame" src="'+args.url+'"></iframe>'
				});
			}
			return false;
		},
		// 2022.01.07 LSH 파일명을 일정사이즈로 줄이기 (확장자는 유지)
		getMaskName: function( fileName, nameLen ) {
			if (!nameLen)
				return fileName;
			var _fileExt = '';
			var _filePre = '';
		    var _lastDot = fileName.lastIndexOf('.');
			if (_lastDot < fileName.length) {
			    _filePre = fileName.substring(0,_lastDot);
				_fileExt = fileName.substring(_lastDot+1);
			}
			else {
			    _filePre = fileName;
			}
			if (_filePre.length > nameLen) {
				_filePre = _filePre.substring(0,nameLen)+'...';
			}
			return _filePre+'.'+_fileExt;
		}
    }

    /*
     * 공통 modal RESET
     * 
     * $.fn.modalReset('myModal');
     */
    $.fn.modalReset = function (obj) {
        $(obj).on('hidden.bs.modal', function (e) {
            $(this).find('input').val('').end().find('input[type=checkbox], input[type=radio]').prop('checked', '').end();
        });
    }

    /*
     * HTML TAG 데이터 입력
     * 
     * $.setTagVal(tagObj, key, data); 
     */
    $.setTagVal = function (tagObj, key, data) {
        if ($.commUtil.nvlTrim(data) != '') {
            switch (tagObj.prop('tagName')) {
                case 'SELECT':
                    tagObj.val(data).trigger('change');
                    break;
                case 'TEXTAREA':
                    tagObj.val(data);
                    break;
                case 'INPUT':
                    switch (tagObj.prop('type')) {
                        case 'hidden':
                        case 'text':
                            tagObj.val(data);
                            break;
                        case 'checkbox':
                            var arr = data.split(',');
                            for (var i in arr) {
                                $("input:checkbox[name="+key+"][value="+arr[i]+"]").prop("checked", true);
                            }
                            break;
                        case 'radio':
                            $("input:radio[name="+key+"][value="+data+"]").prop("checked", true);
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }
    };

    /**
     * 포맷관련 유틸
     * 2021.10.15 LSH 추가
     */
    $.formatUtil = {
		// yyyymmdd를 yyyy년 mm월 dd일로 변환
		toKorDate: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			let d = s; 
			d = s.replace(/\-/gi, ""); // 구분자 '-' 제거
			d = d.replace(/\./gi, ""); // 구분자 '.' 제거
			if (d.length != 8)
				return '';
			return d.substring(0,4)+'년 '+
			       d.substring(4,6)+'월 '+
			       d.substring(6)+'일';
		},
		// yyyymm를 yyyy년 mm월로 변환
		toKorMonth: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			let d = s; 
			d = s.replace(/\-/gi, ""); // 구분자 '-' 제거
			d = d.replace(/\./gi, ""); // 구분자 '.' 제거
			if (d.length != 6)
				return '';
			return d.substring(0,4)+'년 '+
			       d.substring(4,6)+'월';
		},
		// yyyymmdd를 yyyy-mm-dd로 변환
		toDashDate: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			let n = s.replace(/-/gi, "");
			
			if (n.length != 8)
				return s;
			return n.substring(0,4)+'-'+
			       n.substring(4,6)+'-'+
			       n.substring(6);
		},
		// yyyymm을 yyyy.mm로 변환
		toDotMonth: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			if (s.length != 6)
				return s;
			return s.substring(0,4)+'.'+
			       s.substring(4);
		},
		// 사망여부명칭 (comm_const.js의 STORE 참조)
		toDthNm: function( s ) {
			if ($.commUtil.empty(s))
				return STORE.getName('DTH_YN', 'N');
			return STORE.getName('DTH_YN', s);
		},
		// 2022.12.16 인정여부명칭 (comm_const.js의 STORE 참조)
		toRcognNm: function( s ) {
			return STORE.getName('RCOGN_YN', s);
		},
		// 2022.12.20 등급 표시
		toGrdNm: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			if (s == CODE.GRD_ETC_CD)
				return CODE.GRD_ETC_NM;
			return $.commUtil.replaceAll(s, '등급', '')+'등급';
		},
		// 2022.12.27 점수 표시
		toScoreNm: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return s+'점';
		},
		// 2023.02.21 기간(년) 표시
		toYearNm: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return s.replace(/년/g,"")+'년';
		},
		// 2022.12.20 총액 금액 표시
		toTotalMoney: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return '총 '+$.formatUtil.toKorMoney(s);
		},
		// 2022.12.20 개월수를 년월로 표시
		toPeriod: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			let yr = Math.floor(s / 12); // 지급기간(년도)
			let mm = s - (yr*12);
			// 지급기간
			return [(yr > 0 ?     yr+'년'   : ''),
			        (mm > 0 ? ' '+mm+'개월' : '')].join(' ');
		},
		// 2022.12.20 년수 표시
		toYearCnt: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return s+'년';
		},
		// 2022.12.20 종류 표시
		toKindCnt: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return s+'종';
		},
		// 2022.12.20 포함여부명칭 (comm_const.js의 STORE 참조)
		toScopeNm: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return STORE.getName('SCOPE_YN', s);
		},
		// 2022.12.20 값이 없을 경우 dash(-) 표시
		toDash: function( s ) {
			if ($.commUtil.empty(s))
				return '-';
			return s;
		},
		// 2023.01.04 나이 표시
		toAgeNm: function( s ) {
			if ($.commUtil.empty(s))
				return '';
			return s+'세';
		},
		// 전화번호 형식화
		toPhone: function( s ) {
			return $.commUtil.phoneFormatter(s);
		},
		// 주민번호 형식화
		toRegNo: function( s ) {
			if (!s)
				return '';
		
		    var s1 = s.replace(/-/g,"");
		    if (s1.length != 13)
				return s;
			return s1.substring(0,6)+'-'+s1.substring(6);
		},
		// 금액 표시 (원)
		toKorMoney: function( s ) {
			return $.formatUtil.toMoney(s)+'원';
		},
		// 숫자 형식화
		toMoney: function( s ) {
			var minus = false;
			if (!s)
				return '0';
				
			if (typeof s == 'number')
				s = s+"";
		    if (s.indexOf("-") != -1)
		        minus = true;
		
		    var sMoney = s.replace(/(,|-)/g,"");
		    var tMoney = "";
		
			if (isNaN(sMoney)){
				return "0";
			}
		
		    var rMoney = "";
		    var rCheck = false;
		    if(sMoney.indexOf(".") != -1){
		        rMoney = sMoney.substring(sMoney.indexOf("."));
		  		sMoney = sMoney.substring(0, sMoney.indexOf("."));
		  		rCheck = true;
		    }
		
		    var len = sMoney.length;
		
		    if ( sMoney.length <= 3 ) return sMoney;
		
		    for(var i = 0; i < len; i++){
		        if(i != 0 && ( i % 3 == len % 3) ) tMoney += ",";
		        if(i < len ) tMoney += sMoney.charAt(i);
		    }
		    if(minus) tMoney = "-" + tMoney;
		    if(rCheck) tMoney = tMoney + rMoney;
		
		    return tMoney;
		},
		toYear: function( s ) { 
			if ($.commUtil.empty(s))
				return '';
			if (/^\d{4}/.test(s))
				return s+'년';
			return s;
		},
		toMonth: function( s ) { 
			if ($.commUtil.empty(s))
				return '';
			if (/^\d{1,2}/.test(s))
				return s+'월';
			return s;
		},
		toDate: function( s ) { 
			if ($.commUtil.empty(s))
				return '';
			if (/^\d{1,2}/.test(s))
				return s+'일';
			return s;
		},
		// millisecond를 HH:mm:ss 로 포맷 처리
		toTime: function( s ) {
			let ms = parseInt(s);
		    // 1- Convert to seconds:
		    let seconds = ms / 1000;
		    // 2- Extract hours:
		    const hours = parseInt( seconds / 3600 ); // 3,600 seconds in 1 hour
		    seconds = seconds % 3600; // seconds remaining after extracting hours
		    // 3- Extract minutes:
		    const minutes = parseInt( seconds / 60 ); // 60 seconds in 1 minute
		    // 4- Keep only seconds not extracted to minutes:
		    seconds = seconds % 60;
			return (hours   < 10 ? "0" : "")+hours   +":"+
				   (minutes < 10 ? "0" : "")+minutes +":"+
				   (seconds < 10 ? "0" : "")+seconds ;
		},
		// millisecond를 "HH시간 mm분 ss초" 로 포맷 처리
		toTimeKor: function( s ) {
			let ms = parseInt(s);
		    // 1- Convert to seconds:
		    let seconds = ms / 1000;
		    // 2- Extract hours:
		    const hours = parseInt( seconds / 3600 ); // 3,600 seconds in 1 hour
		    seconds = seconds % 3600; // seconds remaining after extracting hours
		    // 3- Extract minutes:
		    const minutes = parseInt( seconds / 60 ); // 60 seconds in 1 minute
		    // 4- Keep only seconds not extracted to minutes:
		    seconds = seconds % 60;

			let r = "";
			if (hours   > 0) 
				r += ((hours   < 10 ? "0" : "")+hours+"시간 ");
			if (minutes > 0) 
				r += ((minutes < 10 ? "0" : "")+minutes+"분 ");
			if (seconds > 0) 
				r += ((seconds < 10 ? "0" : "")+seconds+"초");
			return r;
		}
	};

    /**
     * 공통코드 유틸
     * 2021.10.15 LSH 추가
     */
    $.codeUtil = {
		// 사망여부
		dthYn: function(value) {
			return (value == 'Y' ? '사망' : '생존');
		}
	};
	
    /**
     * 화면 이벤트 관련 유틸
     * 2021.11.23 LSH 추가
     */
	$.eventUtil = {
		// 첨부파일 파일선택 변경시 텍스트박스에 파일명을 표시해주는 이벤트
		fileAttach: function( selector , maxcount ) {
			
			let elm = selector || '.file_wrap';
			let max = maxcount || 10;
			let frm = $(elm).closest('form');
			
			$(elm).on('change','.input_file', function() {
				// 파일명만 추출한다.
				var fname = $(this).val().split("\\").pop();
				// 텍스트박스에 셋팅한다.
				$(this).closest(elm).find('.input_text').val(fname);
			});
			
			// 파일선택 추가 이벤트
			$(elm).on("click", '.btn_add', function() {
				var oform = $(this).closest(elm);
				// 최대갯수 체크
				if (frm.find('.input_file').length >= max) {
					$.commMsg.alert('추가할 최대 갯수는 '+max+'개 입니다.');
					return false;
				}
				// 객체복사
				var cform = oform.clone(true).hide();
				cform.find('input[name="fileNo"]'  ).val("");
				cform.find('input[name="fileName"]').val("");
				cform.end().find('.input_file').off("change");
				cform.insertAfter(oform);
				cform.fadeIn(200);
				return false;
			});
			// 파일선택 삭제 이벤트
			$(elm).on("click", '.btn_del', function() {
				var oform = $(this).closest(elm);
				var title = oform.find('.input_text').attr("title");
				var len   = oform.parent().find('.input_text'+"[title='" + title + "']").length;
				var obox  = oform.find('.input_text');
				if (len <= 1) {
					$.commMsg.alert('단일 항목은 삭제할 수 없습니다.');
					return false;
				}
				// 화면에서 파일항목을 삭제한다.
				var fnDelete = function() {
					oform.fadeOut(200, function() { oform.remove(); });
					return false;
				};
				if (obox.val() === "") {
					fnDelete();
					return false;
				}
				if (confirm('파일을 삭제하시겠습니까?')) {
					fnDelete();
					return false;
				}
				return false;
			});
						
		},
		// 탭클릭 이벤트 처리
		tabClick: function( selector, index, callback, triggerAll ) {

			let elm = selector || '.boxWrap';
			let idx = index    || 0;

			$(elm+" > .tabWrap li").on("click",function(){
				var i = $(this).index()
				$(this).parent().find("li").removeClass("on");
				$(this).addClass("on");
				$(elm+' > .tabInner > ul > li').removeClass("on");
				$(elm+' > .tabInner > ul > li').eq(i).addClass("on");
				if (callback) {
					callback($(this), i);
				}
			})
			if (triggerAll) {
				$(elm+" > .tabWrap li").each(function() {
					$(this).trigger('click');
				});
			}
			// 첫번째 탭 클릭 이벤트 발생
			$(elm+" > .tabWrap li").eq(idx).trigger('click');
		},
		// TEXTAREA 자동높이조절
		autoTextarea: function() {
			$('.app-textarea').on( 'keyup', 'textarea', function (e){
				$(this).css('height', 'auto' );
				$(this).height( this.scrollHeight );
      		});
      		$('.app-textarea').find( 'textarea' ).keyup();
		}
	};
	
    /**
     * 쿠키설정
     */
    $.setCookie = function (name, value, expiredays) {
        if (expiredays == 0 || expiredays == 'undefined') {
            document.cookie = name+"="+ escape(value);
        } else {
            var todayDate = new Date();
            todayDate.setDate(todayDate.getDate() + expiredays);
            document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
        }
    }
    /**
     * 쿠키가져오기
     */
    $.getCookie = function (name) {
        var nameOfCookie = name + "=";
        var x = 0;
        while (x <= document.cookie.length) {
            var y = (x + nameOfCookie.length);
            if (document.cookie.substring(x, y) == nameOfCookie) {
                if ((endOfCookie = document.cookie.indexOf(";", y)) == -1) endOfCookie = document.cookie.length;
                return unescape(document.cookie.substring(y, endOfCookie));
            }
            x = document.cookie.indexOf(" ", x) + 1;
            if (x == 0) break;
        }
        return "";
    }	
    
    /**
     * LPAD
     */
    $.lpad = function (str, len, padStr) {
        if (padStr.length > len) {
            return str;
        }
        str += "";
        padStr += "";
        while (str.length < len)
            str = padStr + str;
        str = str.length >= len ? str.substring(0, len) : str;
        return str;
    };

    /**
     * RPAD
     */
    $.rpad = function (str, len, padStr) {
        if (padStr.length > len) {
            return str;
        }
        str += "";
        padStr += "";
        while (str.length < len)
            str += padStr;
        str = str.length >= len ? str.substring(0, len) : str;
        return str;
    };	
    
})($);

//=======================================================================//
//브라우저가 IE인지 확인
//2021.08.17 LSH  최초작성
//-----------------------------------------------------------------------//
var isIE = function() {
	const agent = navigator.userAgent.toLowerCase();
	if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || 
		(agent.indexOf("msie") != -1) ) {
		return true;
	}
	return false;
}
