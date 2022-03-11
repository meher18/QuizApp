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

	<table>
		<thead>
			<tr>
				<th>Question Title</th>
				<th>Question Operation</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="question" items="${questions}">
				<tr>
					<td>${question.questionTitle}</td>
					<td ><a href="updateQuestion?id=${question.getId()}">Update</a></td>
				</tr>
				
		</c:forEach>
		</tbody>
	</table>
</body>
</html>