<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
</head>

<body class = "bg-light">
	<nav class="navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="/">
		   	 Quiz App
		  	</a>
		  
		</nav>
	<div class="container text-center" style = "margin-top:35px">
		<div class = "card card-body">
			<h1>Welcome to Quiz app</h1>
			<br>
			<br>
			<br>
			<br>
			<div class="card-columns">
			
		
				<div class="card">
					<div class="card-body">
						<a href="admin" >Admin Login</a> 
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<a href="userSignIn" >User Login</a> 
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<a href="userSignUp" >User Register</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<footer class = "footer fixed-bottom bg-dark text-white text-center" style="padding:5px">
		<p>Copyright © Meher Sai Ram</p>
	</footer>

</body>
</html>