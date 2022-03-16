<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | View</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
	
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>

</head>
<body>

<nav class="navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="/">
		   	 Quiz App
		  	</a>
		  	<h5 class = "text-center text-light">User Dashboard</h5>
		  	<div>
				<nav class="nav nav-pills nav-fill">
		   	 		<a class=" nav-item nav-link " href="userModule">Home</a>
	  				<a class=" nav-item nav-link" href="userLogout">Logout</a> 	
	  			</nav>
			</div>
</nav>
	
	<div class="container-fluid" style="margin-top:35px">
	<h2 class="text-center">Below Are the Hosted Quizzes</h2>
		<table id="table" class="table table-striped table-bordered" >
			
			<thead>
				<tr>
					<th>Quiz Code</th>
					<th>Quiz Name</th>
					<th>Take Quiz</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="hostedQuiz" items="${hostedQuizzes}">
					<tr>
						<td>${hostedQuiz.getId()}</td>
						<td>${hostedQuiz.getQuizName()}</td>
						<td><a href="takeTheQuiz?quizId=${hostedQuiz.getId()}">Take Quiz</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
</body>




<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		console.log("asdf")
		$('#table').DataTable();
	});
</script>
</html>