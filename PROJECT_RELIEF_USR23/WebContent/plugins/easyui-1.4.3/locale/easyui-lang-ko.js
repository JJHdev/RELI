if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Page';
	$.fn.pagination.defaults.afterPageText = 'of {pages}';
	$.fn.pagination.defaults.displayMsg = 'Displaying {from} to {to} of {total} items';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Processing, please wait ...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'Ok';
	$.messager.defaults.cancel = 'Cancel';
}
$.map(['validatebox','textbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = '이 항목은 필수 입력사항입니다.';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = 'Please enter a valid email address.';
	$.fn.validatebox.defaults.rules.url.message = 'Please enter a valid URL.';
	$.fn.validatebox.defaults.rules.length.message = 'Please enter a value between {0} and {1}.';
	$.fn.validatebox.defaults.rules.remote.message = 'Please fix this field.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['일','월','화','수','목','금','토'];
	$.fn.calendar.defaults.months = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '오늘';
	$.fn.datebox.defaults.closeText = '닫기';
	$.fn.datebox.defaults.okText = '확인';
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText
	});
}
