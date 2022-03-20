<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | Admin Sign In</title>
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
			 
	</nav>
	
	${adminLoginStatus}
	<div class = "container" style = "margin-top:35px">
		<form action="signInTheAdmin" method="get" class = "card card-body">
			<h4  class = "text-center">Admin Sign In</h4>
			<input type ="text" name = "name" placeholder="Admin user name" class = "form-control" required/>
			<br>
			<input type ="text" name = "pass" placeholder = "Admin password" class = "form-control" required/>
			<br>
			<button type ="submit" class = "btn btn-primary">Submit</button>
		</form>
	</div>
	
	
	
</body>
</html>