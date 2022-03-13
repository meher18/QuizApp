<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
<script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js"></script>
</head>
<body class = "bg-light">
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="/">
	   	 Quiz App
	  	</a>
	  	<div>
			<nav class="nav nav-pills nav-fill">
	   	 		<a class=" nav-item nav-link " href="adminDashboard">Home</a>
	   	 		<a class=" nav-item nav-link active" href="createQuestion">Create Question</a>
  				<a class=" nav-item nav-link" href="viewQuestions">View Questions Library</a> 	
  			</nav>
		</div>
	</nav>
	<form action="/addQuestion" method="get" class="form container" style="width: 100%;margin-top:35px;">
		
		<div class="form-group">
			<label class="input-group-addon" for="title">Enter QuestionTitle </label> 
			<input class="form-control" name="title"
							placeholder="Question title" />
		</div>
		<div class="form-row">
			<div class="col">
					<div class="form-group">
						<label class="input-group-addon" for="options">Enter number
							of options</label> <input class="form-control" name="options"
							placeholder="Question Option" id="optionsNum" />
					</div>
					<div class="form-group " style="height:200px;overflow-y:scroll;">
						<div class="card-body" id="optionsContainer"></div>
					</div>
					<div class="form-group">
						<input name="optionsVal" class="form-control" value=""
							id="optionsVal" readonly>
					</div>
			</div>
			<div class = "col">
					<div class="form-group">
						<label class="input-group-addon" for="topic">Question Topic
							Tag</label> <input class="form-control" name="topic" placeholder="topic tag" />
					</div>
					<div class="form-group">
						<label class="input-group-addon" for="difficulty">Question
							Difficulty</label> <input class="form-control" name="difficulty"
							placeholder="difficulty" />
					</div>
					<div class="form-group">
						<label class="input-group-addon" for="answer">Question Answer</label>
						<input type="number" class="form-control" name="answer"
							placeholder="answer" min="1" max="options.val" />
					</div>
					<div class="form-group">
						<label class="input-group-addon" for="mark">Question Mark</label> <input
							class="form-control" name="mark" placeholder="mark" min="1" max="30" />
					</div>
			
					<input type="submit" class="btn btn-primary" value="submit" />
			</div> <!-- end -->
		</div> <!-- end of row -->
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

			 var div = $(document.createElement('div'));
			div.html("Option "+(i+1));
			
			var a = $(document.createElement('input')).prop({
				type : 'text',
				className : 'form-control',
				placeholder : 'option ' + (i + 1)
			})
			a.on("input", function() {
				options = [];
				$("#optionsContainer").children().each(function() {

					//options += ($(this).val()) + ":";
					if ($(this).children("input").val() != "") {
						options.push($(this).children("input").val());
					}
				})
				$("#optionsVal").val(options.join(":"));
			})
			
			div.append(a)

			$("#optionsContainer").append(div);
		}
	})
</script>
</html>