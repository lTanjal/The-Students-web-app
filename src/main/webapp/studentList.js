/**
* 
*/
function main() {

	// Send a request to the server
	fetch("http://localhost:8080/WebAppExercises/students")
		// Convert the server's JSON response to a JavaScript object
		.then(response => response.json())
		// Call the method which will display the data
		.then(studentList => printStudentsList(studentList));
}

function printStudentsList(studentList) {

	let tableOutput = document.getElementById("tableOutput");
	let outputText = "";
	let toUpdateDelete = "";
	for (let student of studentList) {
		toUpdateDelete = createUpdateDeleteLinks(student.id);
		outputText += "<tr><td>" + student.id + "</td><td>" + student.lastname + "</td><td>" + student.firstname +
			"</td><td>" + student.streetaddress + "</td><td>" + student.postcode + "</td><td>" + student.postoffice + "</td><td>"+toUpdateDelete+"</td></tr>";
	}

	tableOutput.innerHTML = outputText;
}

function addStudent() {
	location.replace("http://localhost:8080/WebAppExercises/studentAdd.html");
}

function toUpdateStudentPage(studentUpDelId) {
location.replace("http://localhost:8080/WebAppExercises/studentUpdate.html");
localStorage.setItem("studentIDtoUpdate",studentUpDelId);
}


function createUpdateDeleteLinks(studentUpDelId) {
	return 	"<span class='link' onclick='toUpdateStudentPage(" + studentUpDelId + ");'>Update</span>" + 
	"&nbsp;&nbsp;" +"<span class='link' onclick='studentDelete(" + studentUpDelId + ");'>Delete</span>";
}



function studentDelete(studentUpDelId) {
	let text = "Delete student " + studentUpDelId+"?";
	if (confirm(text) == true) {

		let url = "http://localhost:8080/WebAppExercises/deleteStudent";
		var requestParameters = "id=" + studentUpDelId;

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

			.catch(errorText => { alert("Post request failed: " + errorText); });

	}
	else {
		text = "";
	}

}


function processResponseStatus(status) {
	if (status === 1) {
		alert("Student data deleted!");
	}
	else if (status === 0) {
		alert("Student data not deleted. Unknown student id!");
	}
	else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
	location.reload(true);
}



// Call the main function when the browser loads the HTML page
main();

// End