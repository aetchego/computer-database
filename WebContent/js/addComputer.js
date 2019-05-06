function errorMessage(msg) {
	$("#error").html(msg);
	$("#error").show();
}

function checkName(name) {
	if (name.trim() == "") {
		errorMessage("Please provide computer's name.");
		return 0;
	}
	return 1;
}

function checkDate(introduced, discontinued) {
	console.log("date " + introduced, discontinued);
	if (introduced.trim() != "" && discontinued.trim() != "") {
		if((new Date(introduced).getTime() > new Date(discontinued).getTime())) {
			errorMessage("Introduction date cannot be after discontinuation date.");
			return 0;
		}
	}
	return 1;
}

$("#submit").click(function() {
	console.log("here");
	var name = $("#computerName").val();
	var introduced = $("#introduced").val();
	var discontinued = $("#discontinued").val();

	if (!checkName(name))
		return false;
	if (!checkDate(introduced, discontinued))
		return false;
});