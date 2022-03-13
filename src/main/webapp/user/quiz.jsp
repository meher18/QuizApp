<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet" />
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
<style>
body {
  position: relative;
}
/* .scrollspy-example{
height:200px;
overflow:scroll
} */

.quiz{
width:50%;
margin:auto}
</style>
</head>
<body>,
<div class = "container ">
	<div class = "quiz">
		<div class="alert alert-primary" role="alert">
	  		Attempt the quiz !
		</div>
		<form action="saveTheQuiz">
				<c:set var="questionCount" value="1"></c:set>
				<div class="list-group list-group-horizontal" id = "questionIds">
					<c:forEach var="question" items="${quiz.getQuestions().values()}">
						<a class="list-group-item" href = "#${question.getId()}"> ${questionCount}</a>
					<c:set var="questionCount" value="${questionCount+1}"></c:set>
					</c:forEach>
				</div>
				
				<c:set var="questionCount" value="1"></c:set>
				<div data-spy="scroll" data-target="#questionIds" data-offset="0" class="scrollspy-example">
				 	 	<c:forEach var="question" items="${quiz.getQuestions().values()}">
					 	 	<div class = "question card card-body">
						  		<h4 id="${question.getId()}" >${questionCount}. ${question.getQuestionTitle()}</h4>
						  				<c:set var="optionCount" value="1"></c:set>
							  		<c:forEach var="option" items="${question.getOptions()}">
							  			<div class = "form-group form-inline">
									  		<input type = "radio" id="${option.getId()}" name = "${question.getId()}" value = "${option.getId()}" />
									  		<p> ${option.getOptionTitle()}</p>
											<c:set var="option" value="${optionCount+1}"></c:set>
										</div>
									</c:forEach>
								<c:set var="questionCount" value="${questionCount+1}"></c:set>
							</div>
						</c:forEach>
				</div>
				<br>
			<input  type = "submit" class = "btn btn-primary ">
		</form>
	</div>
</div>
</body>
<script type="text/javascript">

 $('body').scrollspy({ target: '#questionIds' })

</script>

</html>