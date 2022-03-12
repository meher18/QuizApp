<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.Arrays" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
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
	<form action="updateTheQuiz" method="get" class="form container">
		<input class="form-control" name="id" value="${quiz.getId()}" />
		<div class="input-group">
			<label class="input-group-addon" for="quizName">Enter Quiz
				Name </label> 
				<input class="form-control" name="quizName"
				placeholder="Quiz Name" value="${quiz.getQuizName()}" />
		</div>
		<input type="text" class="form-control" id="questionId" name="questionId" value='${String.join(",", ids)}' />
		<div class="input-group">
			<label class="input-group-addon" for="quizTag">Quiz Host
				Status </label> 
			<input class="form-control" name="quizTag"
				placeholder="Quiz Host Status" value="${quiz.getQuizTag()}" />
		</div>
		<input type="submit" class="btn btn-primary" value="submit" />
	</form>
	
	
	
	
		<h2 class="text-center">Below are the questions</h2>
	<div class="container">
		<table id="table" class="table table-striped table-bordered">
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
					<th>Update Operation</th>
					<th>Delete Operation</th>
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
						<td><a href="updateQuestion?id=${question.getId()}">Update</a></td>
						<td><a href="deleteTheQuestion?id=${question.getId()}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
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