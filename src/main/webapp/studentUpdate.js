/**
 * 
 */
var OneLS= localStorage.getItem("studentIDtoUpdate");
var studentId=OneLS;


	
	function studentSearch(studentId) {
	let url = "http://localhost:8080/WebAppExercises/studentSearch";
	let parameterData = "id="+studentId;

	let requestOptions = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: parameterData 
	};
	
	fetch(url, requestOptions)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw ("HTTP status code is " + response.status);
			}
		})

		.then(studentRow => printStudentRow(studentRow))
	 
		.catch(errorText => {alert("Post request failed: " + errorText);});

}

	
function printStudentRow(studentList) {
var student=studentList;
		
var form = document.forms["formStudentUpdate"];
form["txtId"].disabled = true;
form["txtId"].value = student[0].id;
form["txtFirstName"].value = student[0].firstname;
form["txtLastName"].value = student[0].lastname;
form["txtStreet"].value = student[0].streetaddress;
form["txtPostcode"].value = student[0].postcode;
form["txtPostOffice"].value = student[0].postoffice;
innerHtml = "<form><"
	
}

function studentUpdate() {
	
	let url = "http://localhost:8080/WebAppExercises/studentUpdate";
	var form = document.forms["formStudentUpdate"];
	var requestParameters =
		"id=" + form["txtId"].value +
		"&firstname=" + form["txtFirstName"].value +
		"&lastname=" + form["txtLastName"].value +
		"&streetaddress=" + form["txtStreet"].value +
		"&postcode=" + form["txtPostcode"].value +
		"&postoffice=" + form["txtPostOffice"].value;

	let parameterData = requestParameters;

	let requestOptions = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: parameterData
	};

	fetch(url, requestOptions)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw ("HTTP status code is " + response.status);
			}
		})

		.then(status => processResponseStatus(status))
		.catch(errorText => {alert("Post request failed:" + errorText);});

}


function processResponseStatus(status) {
	if (status === 1) {
		alert("Student data updated!");
	}
	else if (status === 0) {
		alert("Nothing updated. Unknown student id ("+id+")!");
	}
	else {
		alert("Nothing added. An unknown error occurred.");
	}
	 location.replace("http://localhost:8080/WebAppExercises/studentList.html");
}


studentSearch(studentId);
