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
	  	<div>
			<nav class="nav nav-pills nav-fill">
	   	 		<nav class="nav nav-pills nav-fill">
	   	 		<a class=" nav-item nav-link " href="adminDashboard">Home</a>
	   	 		<a class=" nav-item nav-link " href="questionModule">Question Module</a>
	   	 		<a class=" nav-item nav-link" href="createQuestion">Create Question</a>
  				<a class=" nav-item nav-link active" href="viewQuestions">View Questions Library</a> 	
  			</nav>
  			</nav>
		</div>
	</nav>
	
	<div class="container-fluid" style="margin-top:35px">
	<h2 class="text-center">Below are the questions</h2>
		<table id="table" class="table table-striped table-bordered table-responsive" >
			${deletionStatus}
			<thead class = "thead-dark">
				<tr>
					<th>Question Id</th>
					<th>Question Title</th>
					<th>Question Options</th>
					<th>Question Answer</th>
					<th>Question Topic Tag</th>
					<th>Question Difficulty</th>
					<th>Question Mark</th>
					<th>Update Operation</th>
					<th>Delete Operation</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="question" items="${questions}">
					<tr>
						<td>${question.id}</td>
						<td>${question.questionTitle}</td>
						<td>
						<div class="dropright">
							  <button class="btn btn-light dropdown-toggle " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							   Options
							  </button>
							  <div class="dropdown-menu card-body" aria-labelledby="dropdownMenuButton">
							 	<c:set var="optionCount" value="1"></c:set>
							    <c:forEach items="${question.getQuestionOptions()}" var="option">
									${optionCount}. ${option}
									<br>
								<c:set var="optionCount" value="${optionCount+1}"></c:set>
								</c:forEach>
							  </div>
							</div>
						</td>
						
						<td>${question.answer}</td>
						<td>${question.topicTag}</td>
						<td>${question.difficultyTag}</td>
						<td>${question.mark}</td>
						<td><a href="updateQuestion?id=${question.getId()}">Update</a></td>
						<td><a href="deleteTheQuestion?id=${question.getId()}">Delete</a></td>
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