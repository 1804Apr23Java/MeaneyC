function sendAjax(url, func) {
	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send();
};
function viewSingle(url, func) {
	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};
	xhr.open("POST", url, true);
	empId = document.querySelector("input[id='singleEmp']");
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("empId="+empId.value);
}
function viewReimbursements(xhr) {
	let table = document.createElement("TABLE");
	let cell1 = document.createElement("TD");
	let cell2 = document.createElement("TD");
	let cell3 = document.createElement("TD");
	let cell4 = document.createElement("TD");
	let cell5 = document.createElement("TD");
	let cell6 = document.createElement("TD");
	let row = document.createElement("TR");
	cell1.innerHTML = "Reimbursement ID";
	cell2.innerHTML = "Employee ID";
	cell3.innerHTML = "Amount";
	cell4.innerHTML = "Image Location";
	cell5.innerHTML = "State";
	cell6.innerHTML = "Resolving Manager";
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	row.appendChild(cell5);
	row.appendChild(cell6);
	table.appendChild(row);
	var splitResponse = xhr.responseText.split(":");
	let row1 = document.createElement("TR");
	for (var i = 0; i < splitResponse.length - 1; i++) {
		let cell = document.createElement("TD");
		cell.innerHTML = splitResponse[i];
		row1.appendChild(cell);
		if ((i + 1) % 6 == 0) {
			table.appendChild(row1);
			row1 = document.createElement("TR");
		}
	}
	document.body.appendChild(table);
}
function newForm() {

}
function viewEmployees(xhr) {
	let table = document.createElement("TABLE");
	let cell1 = document.createElement("TD");
	let cell2 = document.createElement("TD");
	let cell3 = document.createElement("TD");
	let cell4 = document.createElement("TD");
	let row = document.createElement("TR");
	cell1.innerHTML = "Employee ID";
	cell2.innerHTML = "First Name";
	cell3.innerHTML = "Last Name";
	cell4.innerHTML = "Email";
	row.appendChild(cell1);
	row.appendChild(cell2);
	row.appendChild(cell3);
	row.appendChild(cell4);
	table.appendChild(row);
	var splitResponse = xhr.responseText.split(":");
	let row1 = document.createElement("TR");
	for (var i = 0; i < splitResponse.length - 1; i++) {
		let cell = document.createElement("TD");
		cell.innerHTML = splitResponse[i];
		row1.appendChild(cell);
		if ((i + 1) % 4 == 0) {
			table.appendChild(row1);
			row1 = document.createElement("TR");
		}
	}
	document.body.appendChild(table);
}