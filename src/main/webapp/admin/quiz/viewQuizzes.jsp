<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

		<nav class="navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="/">
		   	 Quiz App
		  	</a>
		  	<div>
				<nav class="nav nav-pills nav-fill">
					<a class=" nav-item nav-link " href="adminDashboard">Home</a>
		   	 		<a class=" nav-item nav-link" href="createQuiz">Create Quiz</a>
	  				<a class=" nav-item nav-link active" href="viewQuizzes">View Quiz Library</a> 	
	  			</nav>
			</div>
		</nav>
	
	<div class="container" style = "margin-top:35px">
	<h2 class="text-center">Below are the quizzes</h2>
		<table id="table" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Quiz Id</th>
					<th>Quiz Name</th>
					<th>Questions</th>
					<th>Quiz Status</th>
					<th>Host Quiz</th>
					<th>Update Quiz</th>
					<th>Delete Quiz</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="quiz" items="${quizzes}">
					<tr>
						<td>${quiz.getId()}</td>
						<td>${quiz.getQuizName()}</td>
						<td><ul>
								<c:forEach items="${quiz.getQuestions().values()}"
									var="question">
									<li><a href="#">${question.getQuestionTitle()}</a></li>
								</c:forEach>
							</ul></td>
						<td>${quiz.getQuizTag()}</td>
						<td><a href="hostTheQuiz?id=${quiz.getId()}">HOST QUIZ</a></td>
						<td><a href="updateQuiz?id=${quiz.getId()}">Update</a></td>
						<td><a href="deleteTheQuiz?id=${quiz.getId()}">Delete</a></td>
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