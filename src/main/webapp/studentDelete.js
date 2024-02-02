function studentDelete() {
	let url = "http://localhost:8080/WebAppExercises/deleteStudent";
	var form = document.forms["formDeleteStudent"];
	var requestParameters =
		"id=" + form["txtId"].value;

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
		
		.catch(errorText => {alert("Post request failed: " + errorText);});

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
	
	
}



