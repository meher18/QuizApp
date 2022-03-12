<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="updateTheQuestion" method="get" class="form container">
		<input class="form-control" name="id" value="${question.getId()}" />
		<div class="input-group">
			<label class="input-group-addon" for="title">Enter Question
				Title</label> <input class="form-control" name="title"
				placeholder="Question title" value="${question.questionTitle}" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="options">Enter number
				of options</label> <input class="form-control" name="options"
				placeholder="Question Option" id="optionsNum"
				value="${question.getOptions().size()}" />
		</div>
		<div class="input-group card">
			<div id="addNewOption">+</div>
			<div id="removeOption">+</div>
			<div id="optionsContainer">
				<c:set var="optionCount" value="1">
				</c:set>
				<c:forEach items="${question.getOptions()}" var="option">
					<div>
						Option ${optionCount}
						<c:set var="optionCount" value="${optionCount+1}">
						</c:set>
						<input oninput="bb()" value="${option.getOptionTitle()}">
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="input-group">

			<input name="optionsVal" class="form-control" value=""
				id="optionsVal">
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="topic">Question Topic
				Tag</label> <input class="form-control" name="topic" placeholder="topic tag"
				value="${question.topicTag}" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="difficulty">Question
				Difficulty </label> <input class="form-control" name="difficulty"
				value="${question.difficultyTag}" placeholder="difficulty" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="answer">Question Answer</label>
			<input type="number" class="form-control" name="answer"
				placeholder="answer" min="1" max="options.val"
				value="${question.answer}" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="mark">Question Mark</label> <input
				class="form-control" name="mark" placeholder="mark" min="1" max="30"
				value="${question.mark}" />
		</div>
		<input type="submit" class="btn btn-primary" value="submit" />
	</form>
</body>

<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
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