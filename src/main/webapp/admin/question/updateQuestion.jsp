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
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js" ></script>
</head>
<body>
	
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="/">
	   	 Quiz App
	  	</a>
	  	<div>
			<nav class="nav nav-pills nav-fill">
				<a class=" nav-item nav-link " href="adminDashboard">Home</a>
	   	 		<a class=" nav-item nav-link" href="createQuestion">Create Question</a>
  				<a class=" nav-item nav-link " href="viewQuestions">View Questions Library</a> 
  			</nav>
		</div>
	</nav>
	<form action="updateTheQuestion" method="get" class = "container" style="margin-top:35px">
	
			<div class="form-group">
			
				<label class="input-group-addon" for="title">Enter Question
					Title</label> <input class="form-control" name="title"
					placeholder="Question title" value="${question.questionTitle}" />
			</div>
		<div class = "row">
		<div class = "col">
				<div class="form-group">
					<p> original number of options : ${question.getOptions().size()} <p>
					Use this buttons to add/remove options
					<div id="addNewOption" class ="btn btn-primary">+</div>
					<div id="removeOption" class = "btn btn-danger">-</div>
				</div>
				<div class="form-group card">
					<div id="optionsContainer" class = "card-body">
						<c:set var="optionCount" value="1">
						</c:set>
						<c:forEach items="${question.getOptions()}" var="option">
							<div>
								Option ${optionCount}
								<c:set var="optionCount" value="${optionCount+1}">
								</c:set>
								<input oninput="bb()" class = "form-control" value="${option.getOptionTitle()}">
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="form-group">
					<input name="optionsVal" class="form-control" value=""
						id="optionsVal" readonly>
				</div>
		</div>
		
		<div class = "col">
				<input class="form-control" name="id" value="${question.getId()}" readonly />	
				<div class="form-group">
					<label class="input-group-addon" for="topic">Question Topic
						Tag</label> <input class="form-control" name="topic" placeholder="topic tag"
						value="${question.topicTag}" />
				</div>
				<div class="form-group">
					<label class="input-group-addon" for="difficulty">Question
						Difficulty </label> <input class="form-control" name="difficulty"
						value="${question.difficultyTag}" placeholder="difficulty" />
				</div>
				<div class="form-group">
					<label class="input-group-addon" for="answer">Question Answer</label>
					<input type="number" class="form-control" name="answer"
						placeholder="answer" min="1" max="options.val"
						value="${question.answer}" />
				</div>
				<div class="form-group">
					<label class="input-group-addon" for="mark">Question Mark</label> <input
						class="form-control" name="mark" placeholder="mark" min="1" max="30"
						value="${question.mark}" />
				</div>
				<input type="submit" class="btn btn-primary" value="submit" />
		</div>
	</div>
	</form>
</body>

<script>

$(document).ready(function()
		{
	for (var i = 0; i < Number(${question.getOptions().size()}); i++) {

		options = "";
		$("#optionsContainer").children().each(function() {

			options += ($(this).children("input").val()) + ":";
		})
		$("#optionsVal").val(options);
/* 	a.on("input", function() {
		options = " ";
		$("#optionsContainer").children().each(function() {

			options += ($(this).val()) + ":";
		})
		$("#optionsVal").val(options);
	}) */


}
		})



$("#addNewOption").on("click", function(){
	// console.log("add option");


			var numberOfOptions = $("#optionsContainer").children().length;
			
			var div = $(document.createElement('div'));
			div.html("Option "+(numberOfOptions+1));
			
			var a = $(document.createElement('input')).prop({
				type : 'text',
				className : 'form-control',
				placeholder : 'option ' + (numberOfOptions + 1),
				//value: "${question.getOptions().get(i).optionTitle}"
			})
		
			a.on("input", bb)
			div.append(a);
			$("#optionsContainer").append(div);

})

	function bb() {
				options = " ";
				$("#optionsContainer").children().each(function() {

					if($(this).children("input").val() != "")
						{
							options += ($(this).children("input").val()) + ":";
						}
				})
				$("#optionsVal").val(options);
		}


$("#removeOption").on("click", function(){
	// console.log("remove option");
	$("#optionsContainer").children().last().remove();
		
	//a.on("input", function() {
	//options = " ";
		//	$("#optionsContainer").children().each(function() {
			//			options += ($(this).val()) + ":";
			//	})
			//	$("#optionsVal").val(options);
			//})
			
 	var currentVal = $("#optionsVal").val();
	
	//currentVal = currentVal.substr(-1);
	//var indexOfColon = currentVal.lastIndexOf(":");
	//console.log(indexOfColon);
	
	//currentVal = currentVal.substr(0, indexOfColon);
/* 	var vals = currentVal.split(":");
	vals.slice(0, -1);
	console.log(vals);
	
	vals = vals.slice(0, -1);
	console.log(vals); */
 	options = " ";
	$("#optionsContainer").children().each(function() {

		if($(this).children("input").val() != "")
			{
				options += ($(this).children("input").val()) + ":";
			}
	})
	$("#optionsVal").val(options);
})



</script>
</html>