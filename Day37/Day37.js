
      function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

     var urlEnding = getParameterByName('id');
      console.log(urlEnding);
      console.log("Hello");
      var fullUrl = "http://localhost:8080/student/" + urlEnding;
      console.log(fullUrl);

		$(document).ready(function() {

			$.ajax({
            url: fullUrl
    		}).then(function(data) {
    			var studentString = "<tr><td>" + data.first_name + " " + data.last_name + "</td><td>" + data.sat + "</td><td>" + (data.gpa).toFixed(2) + "</td></tr>";
    			console.log(studentString);
				$("#studentList").append(studentString);
    		});


		}); <!-- end ready -->

