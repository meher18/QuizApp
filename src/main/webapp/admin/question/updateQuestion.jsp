<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | Update Question</title>
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
				<a class=" nav-item nav-link " href="questionModule">Question Module</a>
	   	 		<a class=" nav-item nav-link" href="createQuestion">Create Question</a>
  				<a class=" nav-item nav-link " href="viewQuestions">View Questions Library</a> 
  			</nav>
		</div>
	</nav>
	<form action="updateTheQuestion" method="get" class = "container" style="margin-top:35px">
	
			<div class="form-group">
			
				<label class="input-group-addon" for="questionTitle">Enter Question
					Title</label> <input class="form-control" name="questionTitle"
					placeholder="Question title" value="${question.questionTitle}" />
					<small class=" text-danger">${errors.get("title")}</small>
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
								<input oninput="showOptions()" name = "questionOptions" class = "form-control" value="${option.getOptionTitle()}" required>
								<c:set var="optionCount" value="${optionCount+1}">
								</c:set>
							</div>
							<small class=" text-danger">${errors.get("options")}</small>
						</c:forEach>
					</div>
				</div>
				<%-- <div class="form-group">
					<input name="optionsVal" class="form-control" value=""
						id="optionsVal" readonly>
					<small class=" text-danger">${errors.get("optionsVal")}</small>
				</div> --%>
		</div>
		
		<div class = "col">
				<input class="form-control" name="id" value="${question.getId()}" readonly />	
				<div class="form-group">
					<label class="input-group-addon" for="topicTag">Question Topic
						Tag</label> <input class="form-control" name="topicTag" placeholder="topic tag"
						value="${question.topicTag}" required />
						<small class=" text-danger">${errors.get("topic")}</small>
				</div>
				<div class="form-group">
						<label class="input-group-addon" for="difficultyTag">Question
							Difficulty</label> 
						  <select class="form-control" name="difficultyTag" id="difficultyTag" required>
							<option value ="EASY" ${question.difficultyTag.equals("EASY") ? "selected" : ""}>EASY</option>
							<option value = "MEDIUM" ${question.difficultyTag.equals("MEDIUM") ? "selected" : ""}>MEDIUM</option>
							<option value = "HARD" ${question.difficultyTag.equals("HARD") ? "selected" : ""}>HARD</option>
						  </select>
						<small class=" text-danger">${errors.get("difficulty")}</small>
				</div>
				<div class="form-group">
					<label class="input-group-addon" for="answer">Question Answer</label>
					<input type="number" class="form-control" name="answer" id="answer"
						placeholder="answer" min="1" max="options.val"
						value="${question.answer}" required />
					<small class=" text-danger">${errors.get("answer")}</small>
				</div>
				<div class="form-group">
					<label class="input-group-addon" for="mark">Question Mark</label> 
					<input
						type = "number"
						class="form-control" name="mark" placeholder="mark" min="1" max="30"
						value="${question.mark}"  required/>
					<small class=" text-danger">${errors.get("mark")}</small>
				</div>
				<input type="submit" class="btn btn-primary" value="Update Question" />
		</div>
	</div>
	</form>
	
	
</body>



<script>
//optionsArray = []

$(document).ready(function(){

	$("#answer").attr("max", ${question.getOptions().size()});
})

$("#addNewOption").on("click", function(){

		numberOfOptions = $("#optionsContainer").children("div").length;
		
		console.log(numberOfOptions);
		if(numberOfOptions >= 6)
		{
			alert("Not more than 6 options are allowed");
			
		}else{
			var div = $(document.createElement('div'));
			div.html("Option "+(numberOfOptions+1));
			
			var optionInput = $(document.createElement('input')).prop({
				type : 'text',
				className : 'form-control',
				placeholder : 'option ' + (numberOfOptions + 1),
				name : 'questionOptions',
				required : 'true'
				//value: "${question.getOptions().get(i).optionTitle}"
			})
		
			optionInput.on("input", showOptions())
			div.append(optionInput);
			$("#optionsContainer").append(div);
			
			
			$("#answer").val("");
			numberOfOptions++;
			$("#answer").attr("max", numberOfOptions );
	}
})

function showOptions() {
	/* options = "";
	optionsArray = [];
	$("#optionsContainer").children().each(function() {

		if($(this).children("input").val() != "")
		{
			if($(this).children("input").val() != ""){
				//options += ($(this).children("input").val()) + ":";
				optionsArray.push($(this).children("input").val());
			}
		}
	})
	$("#optionsVal").val(optionsArray.join(":")); */
	//$("#optionsVal").val(options);
}


$("#removeOption").on("click", function(){
	
	var optionsCount = $("#optionsContainer").children("div").length;
		
	if(optionsCount <= 2)
	{
		alert("Cannot remove, minimum 2 options are required");
	}else{
		$("#optionsContainer").children().last().remove();
		
		$("#answer").val("");
		optionsCount--;
		if(optionsCount >= 0){
			$("#answer").attr("max", optionsCount );
		}	
	}
	
	
})



</script>
</html>