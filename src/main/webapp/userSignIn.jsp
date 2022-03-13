<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
</head>
<body class = "bg-light">
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="/">
	   	 Quiz App
	  	</a>
	  	<div>
			<nav class="nav nav-pills nav-fill">
	   	 		<a class=" nav-item nav-link active" href="createQuestion">Create Question</a>
  				<a class=" nav-item nav-link" href="viewQuestions">View Questions Library</a> 	
  			</nav>
		</div>
	</nav>
	<form action = "signInTheUser" class="">
	  <div class="form-group">
	    <label for="userName" class="sr-only">User Name</label>
	    <input type="text" class="form-control" name = "userName" id="userName" placeholder = "Enter user name">
	  </div>
	  <div class="form-group ">
	    <label for="userPassword" class="sr-only">Password</label>
	    <input type="password" class="form-control" name = "userPassword" id="userPassword" placeholder="Enter password">
	  </div>
	  <button type="submit" class="btn btn-primary mb-2">Confirm identity</button>
	</form>
</body>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</html>