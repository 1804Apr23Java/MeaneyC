/*window.onload = function() {
	console.log("ef window.onload");
	sendAjaxGet("http://localhost:8083/ServletLoginApp/session", populateUser);
}*/

function submitReimbursementRequests(url, func) {

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
/*	if (xhr.responseText) {
		console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		console.log(res);
		if (res.username) {
			document.getElementById("user").innerHTML = "you are logged in as "
					+ res.username;
		} else {
			window.location = "http://localhost:8083/Project1/error";
		}
	} else {
		window.location = "http://localhost:8083/Project1/error";
	}*/
};

function onclickSubmit() {
	var element = document.querySelector('button[id = "submitRequests"]');
	element.addEventListener("click", submitReimbursementRequests("http://localhost:8083/Project1/submitReimbursement", populateUser)
);};
	
window.onload = function() {
	onclickSubmit();
}
