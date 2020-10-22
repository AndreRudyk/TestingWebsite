<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit test</title>
</head>
<body>
<h2>${sessionScope.test.name}</h2>
<form method="post"	action="${pageContext.request.contextPath}/serv/finish-test-edit">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><label for="description">Description</label></td>
				<td><input type="text" name="description" value="${sessionScope.test.description}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="time">Duration</label></td>
				<td><input type="number" id="time" name="time" min="30" max="180" value="${sessionScope.test.time}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="category">Category</label></td>
				<td>
					<p>Current category is "${sessionScope.test.category}".</p>
					<p>Select a new category if needed.</p>
					<select name="category" id="category">
						<option value="defaul" selected="selected">Don't change</option>
    					<option value="english">English Language</option>
    					<option value="history">History</option>
    					<option value="math">Math</option>
  					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="difficulty">Difficulty</label></td>
				<td>
					<p>Current difficulty is "${sessionScope.test.difficulty}".</p>
					<p>Select a new difficulty if needed.</p>
					<select name="difficulty" id="difficulty">
						<option value="defaul" selected="selected">Don't change</option>
    					<option value="easy">Easy</option>
    					<option value="medium">Medium</option>
    					<option value="hard">Hard</option>
  					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button"
					type="submit" value="Save"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button"
					type="submit" value="Edit questions" formaction="${pageContext.request.contextPath}/serv/edit-question"></td>
			</tr>
		</table>
	</form>
<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit" value="Home">
	</form>
	<br />
	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>