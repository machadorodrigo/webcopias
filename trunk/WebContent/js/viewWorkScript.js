function getParam(param) {
	param = param.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + param + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(parent.window.location.href);
	if (results == null)
		return "";
	else
		return results[1];
}