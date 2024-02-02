
function studentAdd() {
	location.replace("http://localhost:8080/WebAppExercises/studentAdd.html");
	let url = "http://localhost:8080/WebAppExercises/addStudent";
	let form = document.forms["formStudent"];
	let requestParameters =
		"id=" + form["txtId"].value +
		"&firstname=" + form["txtFirstName"].value +
		"&lastname=" + form["txtLastName"].value +
		"&streetaddress=" + form["txtStreet"].value +
		"&postcode=" + form["txtPostcode"].value +
		"&postoffice=" + form["txtPostOffice"].value;

	let parameterData = requestParameters;

	let requestOptions = {
		method: "POST",
		headers: {"Content-Type": "application/x-www-form-urlencoded"},
		body: parameterData
	};
	
	fetch(url, requestOptions)
		.then(response =>{
			if (response.ok){
				return response.json();
			} throw new Error ("HTTP status code is " + response.status);}
		)
		
		.then (status => {processResponse (status)})
		.catch (error => {alert ("Post request faild:" + error);});
}


		function processResponse(status) {
			if (status === 0) {
				alert("Student added.");
			}
			else if (status === 1) {
				alert("Nothing added. The student id is already in use");
			}
			else {
				alert("Nothing added. An unknown error occurred.");
			}
			 location.replace("http://localhost:8080/WebAppExercises/studentList.html");
		}



