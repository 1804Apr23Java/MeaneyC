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

	xhr.open("POST", url, true);
	//var obj = {employeeId : 5, imageLocation : "https://imgur.com/gallery/N98BYni", state : 1, resolvingManager : 2 }
	//var json = JSON.stringify(obj);
	//xhr.setRequestHeader('Content-type', 'application/json');//; charset=utf-8');
	//xhr.setRequestHeader("Content-type", "text/html");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	;
	xhr.send("employeeId="+document.querySelector('input[id="employeeId"]').
			value+"&imageLocation="+document.querySelector('input[id="imageLocation"]').
			value+"&state="+document.querySelector('input[id="state"]').
			value+"&resolvingManager="+document.querySelector('input[id="resolvingManager"]').
			value);
	//xhr.send('test = '+obj);
	//xhr.send("test = "+obj);
	//xhr.send(json);
}

function viewPending(url, func) {

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
function doTheSubmitting() {
	submitReimbursementRequests("http://localhost:8083/Project1/submitReimbursement", populateUser);
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
	element.addEventListener("click", doTheSubmitting
);};
function viewReimbursements() {
	var element = document.querySelector('button[id = "viewPending"]');
	element.addEventListener("click", viewPending("http://localhost:8083/Project1/viewPending", populateUser)
);};
	
window.onload = function() {
	onclickSubmit();
}