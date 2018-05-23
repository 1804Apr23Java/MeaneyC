/**
 * 
 */

function sendAjaxGet(url, func) {

	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");

	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};

	xhr.open("GET", url, true);
	xhr.send();
}

function populateUser(xhr) {
	if (xhr.responseText) {
		console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		console.log(res);
		if (res.username) {
			document.getElementById("user").innerHTML = "you are logged in as "
					+ res.username;
		} else {
			window.location = "http://localhost:8083/Projec1/login";
		}
	} else {
		window.location = "http://localhost:8083/Project1/login";
	}
}

window.onload = function() {
	console.log("executed window.onload");
	sendAjaxGet("http://localhost:8083/Project1/session", populateUser);
}