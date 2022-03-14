<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | Create Quiz</title>

<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css" />
<script>

arrOfIds = [];
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
					<a class=" nav-item nav-link " href="quizModule">Quiz Module</a>
		   	 		<a class=" nav-item nav-link active" href="createQuiz">Create Quiz</a>
	  				<a class=" nav-item nav-link" href="viewQuizzes">View Quiz Library</a> 	
	  			</nav>
			</div>
	</nav>
	<div class = "row container-fluid">
		<div class = "col-4">
			
			<form action="createTheQuiz" style="margin-top:35px" class = "card card-body">
				<label for="quizName"><h5>Enter A Quiz Title</h5></label>
				<input type="text" name="quizName" class="form-control" />
						<small class=" text-danger">${errors.get("quizName")}</small>
				
				<br>
				<!-- check question id -->
				<h4 class = "alert alert-info">Select the Questions from the Table (click on the checkbox on first column of each row)</h4>
				<input type="text" id="questionId" class ="form-control" name="questionId" value="" readonly /> 
				<small class=" text-danger">${errors.get("questionId")}</small>
				
				<br>
				<input
					type="submit" value="submit" />
			</form>
			
			
		</div>
		<div class = "col-8">		
		<div class="" style="margin-top:35px">
		
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
								name="selectedId" value="${question.id}"></td>
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
	</div> <!--  end of col-->

</div> <!--  end of row   -->
</body>


<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#table').DataTable();
		
	});
	

</script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>

</html>