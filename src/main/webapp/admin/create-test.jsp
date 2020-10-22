<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create a test</title>
</head>
<body>
	<h1 style="text-align: center">
		<fmt:message key="testing" bundle="${lang}"/>
	</h1>
	<hr />
	<h3 style="text-align: center">Create a test</h3>
	<form method="post"	action="${pageContext.request.contextPath}/serv/create-test">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><label for="name">Test name</label></td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="description">Description</label></td>
				<td><input type="text" name="description" size=100></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="time">Duration</label></td>
				<td><input type="number" id="time" name="time" min="30" max="180"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="category">Category</label></td>
				<td>
					<select name="category" id="category">
						<option value="" selected disabled hidden>Choose category</option>
    					<option value="english">English Language</option>
    					<option value="history">History</option>
    					<option value="math">Math</option>
  					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="difficulty">Difficulty</label></td>
				<td>
					<select name="difficulty" id="difficulty">
						<option value="" selected disabled hidden>Choose difficulty</option>
    					<option value="easy">Easy</option>
    					<option value="medium">Medium</option>
    					<option value="hard">Hard</option>
  					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button"
					type="submit" value="Add questions"></td>
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
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
</body>
</html>
