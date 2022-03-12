<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="/addQuestion" method="get" class="form container">
		<div class="input-group">
			<label class="input-group-addon" for="title">Enter Question
				Title </label> <input class="form-control" name="title"
				placeholder="Question title" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="options">Enter number
				of options</label> <input class="form-control" name="options"
				placeholder="Question Option" id="optionsNum" />
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
				Tag</label> <input class="form-control" name="topic" placeholder="topic tag" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="difficulty">Question
				Difficulty</label> <input class="form-control" name="difficulty"
				placeholder="difficulty" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="answer">Question Answer</label>
			<input type="number" class="form-control" name="answer"
				placeholder="answer" min="1" max="options.val" />
		</div>
		<div class="input-group">
			<label class="input-group-addon" for="mark">Question Mark</label> <input
				class="form-control" name="mark" placeholder="mark" min="1" max="30" />
		</div>

		<input type="submit" class="btn btn-primary" value="submit" />
	</form>

</body>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script>
	$("#optionsNum").on("input", function(e) {
		options = []
		$("#optionsVal").val(options.join(":"));
		var optionsNum = $(this).val();
		$("#optionsContainer").empty();

		for (var i = 0; i < Number(optionsNum); i++) {

			var a = $(document.createElement('input')).prop({
				type : 'text',
				className : 'form-control',
				placeholder : 'option ' + (i + 1)
			})
			a.on("input", function() {
				options = [];
				$("#optionsContainer").children().each(function() {

					//options += ($(this).val()) + ":";
					if ($(this).val() != "") {
						options.push($(this).val());
					}
				})
				$("#optionsVal").val(options.join(":"));
			})

			$("#optionsContainer").append(a);
		}
	})
</script>
</html>