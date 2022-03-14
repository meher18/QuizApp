<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | User Module</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="/">
		   	 Quiz App
		  	</a>
		  	<h5 class = "text-center text-light">User Dashboard</h5>
		  	<div>
				<nav class="nav nav-pills nav-fill">
		   	 		<a class=" nav-item nav-link active" href="userModule">Home</a>
	  				<a class=" nav-item nav-link" href="userLogout">Logout</a> 	
	  			</nav>
			</div>
</nav>
${userName}
${userEmail}




<div  class = "container card card-body" style = "margin-top:35px">

<div class="card-columns mx-auto">
		<div class="card">
			<div class="card-body">
				<a href = "viewHostedQuizzes">View All Hosted Quizzes</a>
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<a href = "viewParticipation" >View Participation</a>
			</div>
		</div>
	</div>

</div>
</body>
</html>