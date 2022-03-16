<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.Arrays" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | Update Quiz</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css" />
<script>
arrOfIds = [${String.join(",",ids)}]
const  a = (e) => {
	console.log(e);
	if(e.checked == true)
	{
			arrOfIds.push(e.value);
	}else{
		arrOfIds = arrOfIds.filter(function(a){
			return a != e.value;
	})
		
	}
	$("#questionId").val(arrOfIds.join(","));
}
</script>
</head>
<body>

	<nav class="navbar navbar-dark bg-dark">
			<a class="navbar-brand" href="/">
		   	 Quiz App
		  	</a>
		  	<div>
				<nav class="nav nav-pills nav-fill">
					<a class=" nav-item nav-link " href="adminDashboard">Home</a>
		   	 		<a class=" nav-item nav-link " href="createQuiz">Create Quiz</a>
		   	 		<a class=" nav-item nav-link " href="quizModule">Quiz Module</a>
	  				<a class=" nav-item nav-link" href="viewQuizzes">View Quiz Library</a> 	
	  			</nav>
			</div>
	</nav>
	<div class = "row container-fluid" >
		<div class = "col-4">
			<form action="updateTheQuiz" method="get" class="card card-body" style="margin-top:35px">
				<input class="form-control" name="id" value="${quiz.getId()}" readonly/>
				<div class="form-group">
					<label  for="quizName">Enter Quiz
						Name </label> 
						<input name="quizName"
						placeholder="Quiz Name" value="${quiz.getQuizName()}" class = "form-control" />
				</div>
				<alert class="alert alert-info">Check and Uncheck the checkbox in first column of every row for selecting and deselecting the question for quiz</alert>
				<input type="text" class="form-control" id="questionId" name="questionId" value='${String.join(",", ids)}' 
					readonly/>
				<div class="form-group">
					<label for="quizTag">Quiz Host
						Status</label>
					  <select class="form-control" name="quizTag" id="quizTag" value = "${quiz.getQuizTag()}">
						<option value = "HOSTED" ${quiz.getQuizTag().equals("HOSTED") ? "selected" : ""}> HOSTED </option>
						<option value = "NOT HOSTED" ${quiz.getQuizTag().equals("NOT HOSTED") ? "selected" : ""}> NOT HOSTED</option>
					  </select>
				</div>
				<input type="submit" class="btn btn-primary" value="Update Quiz" />
			</form>
			
		</div>
		<div class = "col-8">
			<div class="" style = "margin-top:35px">
				
				<table id="table" class="table table-striped table-bordered table-responsive">
					<thead>
						<tr>
							<th>Select Questions</th>
							<th>Question Id</th>
							<th>Question Title</th>
							<th>Question Options</th>
							<th>Question Answer</th>
							<th>Question Topic Tag</th>
							<th>Question Difficulty</th>
							<th>Question Mark</th>
						</tr>
					</thead>
					<tbody>
		
						<c:forEach var="question" items="${questions}">
							<tr>
								<td><input type="checkbox" id="selectedId" onclick="a(this)"
									name="selectedId" value="${question.id}" ${ids.contains(question.id.toString())  ? "checked" : ""}></td>
								<td>${question.id}</td>
								<td>${question.questionTitle}</td>
								<td><ul>
										<c:forEach items="${question.getOptions()}" var="option">
											<li><a href="#">${option.getOptionTitle()}</a></li>
										</c:forEach>
									</ul></td>
								<td>${question.answer}</td>
								<td>${question.topicTag}</td>
								<td>${question.difficultyTag}</td>
								<td>${question.mark}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	
	
	
</body>



<script type="text/javascript">
	$(document).ready(function() {
		$('#table').DataTable();
		
	});
	
</script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
</html>