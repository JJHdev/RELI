/**
******************************************************************************************
*** 파일명    : comm_validate.js
*** 설명      : jQuery Validate 관련 유틸 함수
***
*** -----------------------------    Modified Log   --------------------------------------
*** ver             date                author                  description
*** --------------------------------------------------------------------------------------
*** 1.0         2021-10-20              LSH
******************************************************************************************
**/

/**
 * 검증관련 유틸
 * 2021.09.24 LSH 추가
 */
$.validateUtil = {
	// 전화번호 검증
	phone: function( value ) {
		value = value.replace(/\(|\)|\s+|-/g,'');
		if (value.length > 9 && value.match(/^(0[0-9]{2,3})-?[0-9]{3,4}-?[0-9]{4}$/))
			return "TRUE";
		else
			return "전화번호를 정확하게 입력하세요.";
	},
	// 휴대전화번호 검증
	mobile: function( value ) {
		value = value.replace(/\(|\)|\s+|-/g,'');
		if (value.length > 9 && value.match(/^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/))
			return "TRUE";
		else
			return "휴대전화를 정확하게 입력하세요.";
	},
	// 2023.01.12 날짜형식(YYYY-MM-DD) 검증
	dateHyphen: function( value ) {
		if (value.length == 10 && value.match(/^\d{4}-((0\d)|(1[012]))-(([012]\d)|3[01])$/))
			return "TRUE";
		else
			return "날짜를 정확하게 입력하세요. (YYYY-MM-DD)";
	},
	// 날짜형식(YYYYMMDD) 검증
	date: function( value ) {
		if (value.length == 8 && value.match(/^\d{4}((0\d)|(1[012]))(([012]\d)|3[01])$/))
			return "TRUE";
		else
			return "날짜를 정확하게 숫자로만 입력하세요.";
	},
	// 년도(YYYY) 검증
	year: function( value ) {
		if (value.length == 4 && value.match(/^\d{4}$/))
			return "TRUE";
		else
			return "년도를 정확하게 숫자로만 입력하세요.";
	},
	// 2021.08.05 jQuery Validate Plugin 사용시 공통 핸들러
	handler: function(form, validator) {
		var errors = validator.numberOfInvalids();
		if (errors) {
			var errorObj = validator.errorList[0];
			$.commMsg.alert(errorObj.message, function() {
				var input = errorObj.element;
				$(input).focus();
			});
		}
	},
	// 2021.08.05 jQuery Validate Plugin 사용시 공통 에러표시방식
	placement: function(error, element) {
		//element.closest('td').find('.app-error').append(error);
		// do nothing
		//error.appendTo('.app_error');
	},
	// 2021.10.20 jQuery Validate Plugin 사용시 공통 핸들러
	handler01: function(form, validator) {
		var errors = validator.numberOfInvalids();
		if (errors) {
			var errorObj = validator.errorList[0];
			$(errorObj.element).focus();
		}
	},
	// 2021.10.20 jQuery Validate Plugin 사용시 공통 에러표시방식
	placement01: function(error, element) {
		element.closest('td').find('.app-error').append(error);
	},
	// 2021.08.05 jQuery Validate Plugin 팝업 사용시 공통 핸들러
	popupHandler: function(form, validator) {
		var errors = validator.numberOfInvalids();
		if (errors) {
			var errorObj = validator.errorList[0];
			$.popupMsg.alert(errorObj.message, function() {
				var input = errorObj.element;
				$(input).focus();
			});
		}
	},
	appendHandler: function(form, validator) {
		var errors = validator.numberOfInvalids();
		if (errors) {
			var errorObj = validator.errorList[0];
			$(form).find('[name="'+errorObj.element+'"]').focus();
		}
	},
	appendPlacement: function(error, element) {
		element.closest('.inputGroup').append(error);
	}
}

function validateHandler(form, validator) {
	$.validateUtil.handler(form, validator);
}

function validatePlacement(error, element) {
	$.validateUtil.placement(error, element);
}
