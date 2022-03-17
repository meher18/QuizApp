<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz App | Create Question</title>
<link
	href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<nav class="navbar navbar-dark bg-dark">
		<a
			class="navbar-brand"
			href="/"> Quiz App 
		</a>
		<div>
			<nav class="nav nav-pills nav-fill">
				<a
					class=" nav-item nav-link "
					href="adminDashboard">Home</a> 
				<a
					class=" nav-item nav-link "
					href="questionModule">Question Module</a>
				 <a
					class=" nav-item nav-link active"
					href="createQuestion">Create Question</a>
				 <a
					class=" nav-item nav-link"
					href="viewQuestions">View Questions Library</a>
			</nav>
		</div>
	</nav>
	${questionCreationStatus}
	<form
		action="/addQuestion"
		method="get"
		class="form container"
		style="width: 100%; margin-top: 35px;">
		<div class="form-group">
			<label
				class="input-group-addon"
				for="title">Enter QuestionTitle </label> 
			<input
				class="form-control"
				name="questionTitle"
				placeholder="Question title"
				required />
			 <small class=" text-danger">${errors.get("title")}</small>
		</div>
		<div class="form-row">
			<div class="col">
				<div class="form-group">
					<label
						class="input-group-addon"
						for="optionsCount">Enter number of options</label> <input
						type="number"
						class="form-control"
						name="optionsCount"
						placeholder="Question Option"
						id="optionsNum"
						min="2"
						max="6"
						required /> <small class=" text-danger">${errors.get("options")}</small>
				</div>
				<div
					class="form-group "
					style="height: 200px; overflow-y: scroll;">
					<div
						class="card-body form-group"
						id="optionsContainer">
						<alert class = "alert alert-primary" >Options will appear here</alert>
					</div>
				</div>
				<%-- <div class="form-group">
					<input
						name="optionsVal"
						class="form-control"
						value=""
						id="optionsVal"
						readonly
						required> 
					<small class=" text-danger">${errors.get("optionsVal")}</small>
				</div> --%>
			</div>
			<div class="col">
				<div class="form-group">
					<label
						class="input-group-addon"
						for="topic">Question Topic Tag</label> <input
						class="form-control"
						name="topicTag"
						placeholder="topic tag"
						required /> <small class=" text-danger">${errors.get("topic")}</small>
				</div>
				<div class="form-group">
					<label
						class="input-group-addon"
						for="difficulty">Question Difficulty</label> <select
						class="form-control"
						name="difficultyTag"
						id="difficulty">
						<option value="EASY">EASY</option>
						<option value="MEDIUM">MEDIUM</option>
						<option value="HARD">HARD</option>
					</select>
					<!-- <input class="form-control" name="difficulty"
							placeholder="difficulty" /> -->
					<small class=" text-danger">${errors.get("difficulty")}</small>
				</div>
				<div class="form-group">
					<label
						class="input-group-addon"
						for="answer">Question Answer</label> <input
						type="number"
						class="form-control"
						name="answer"
						id="answer"
						placeholder="answer"
						min="1"
						max=""
						required /> <small class=" text-danger">${errors.get("answer")}</small>
				</div>
				<div class="form-group">
					<label
						class="input-group-addon"
						for="mark">Question Mark</label> 
					<input
						type="number"
						class="form-control"
						name="mark"
						placeholder="mark"
						min="1"
						max="30"
						required /> <small class=" text-danger">${errors.get("mark")}</small>
				</div>
				<input
					type="submit"
					class="btn btn-primary"
					value="Add Question" />
			</div>
			<!-- end -->
		</div>
		<!-- end of row -->
	</form>
	
</body>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script>

	$("#optionsNum").on("input", function(e) {
		var optionsNum = $(this).val();

		$("#answer").attr("max", optionsNum);
		if (Number(optionsNum) < 2 || Number(optionsNum) > 6) {
			
			alert("minmum 2 options, maximum 6 options can be entered");
			$("#optionsNum").val("2").trigger("input");
			
		} else {

			$("#optionsContainer").empty();

			for (var i = 0; i < Number(optionsNum); i++) {

				var div = $(document.createElement('div'));
				div.html("Option " + (i + 1));

				var a = $(document.createElement('input')).prop({
					type : 'text',
					className : 'form-control',
					placeholder : 'Enter option ' + (i + 1),
					required : 'true',
					name : "questionOptions"
				})
				div.append(a)

				$("#optionsContainer").append(div);
			}
		}
	})
</script>
</html>