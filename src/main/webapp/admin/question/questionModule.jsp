<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | Question Module</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="/">
		   	 Quiz App
		  	</a>
		  	<h5 class = "text-center text-light">Admin / Question Module</h5>
		  	<div>
				<nav class="nav nav-pills nav-fill">
		   	 		<a class=" nav-item nav-link" href="adminDashboard">Home</a>
		   	 		<a class=" nav-item nav-link active" href="questionModule">Question Module</a>
	  				<a class=" nav-item nav-link" href="adminLogout">Logout</a> 	
	  			</nav>
			</div>
</nav>
<div  class = "container card card-body" style = "margin-top:35px">

<div class="card-columns mx-auto">
		<div class="card">
			<div class="card-body">
				<a href = "createQuestion">Create Question</a>
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<a href = "viewQuestions">View all questions</a>
			</div>
		</div>
	</div>

</div>
</body>
</html>


