<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="updateTheQuestion" method="get" class="form container">
		<div class="input-group">
			<label class="input-group-addon" for="title">Enter Question
				Title</label>
			 <input class="form-control" name="title"
				placeholder="Question title" value="${question.questionTitle}" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="options">Enter number
				of options</label> <input class="form-control" name="options"
				placeholder="Question Option" id="optionsNum" value="${question.mark}" />
		</div>
		<div class="input-group card">
			<div id="optionsContainer"></div>
		</div>
		<div class="input-group">
			<input name="optionsVal" class="form-control" value=""
				id="optionsVal">
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="topic">Question Topic
				Tag</label>
			 <input class="form-control" name="topic" placeholder="topic tag" value="${question.topicTag}" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="difficulty">Question
				Difficulty</label> 
			<input class="form-control" name="difficulty" value ="${question.difficultyTag}"
				placeholder="difficulty" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="answer">Question Answer</label>
			<input type="number" class="form-control" name="answer"
				placeholder="answer" min="1" max="options.val" value ="${question.answer}" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="mark">Question Mark</label> <input
				class="form-control" name="mark" placeholder="mark" min="1" max="30" value="${question.mark}"/>
		</div>

		<input type="submit" class="btn btn-primary" value="submit" />
	</form>

	
</body>
</html>