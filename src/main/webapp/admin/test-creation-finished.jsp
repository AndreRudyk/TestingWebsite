<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test created</title>
</head>
<body>
	<h2>All done!</h2>
	<p>The test ${sessionScope.test.name} was successfully created!</p>
	<hr />

	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-tests">
		<input class="button" type="submit"	value="Show all tests">
	</form>
	
	<br />
	
	<form method="get" action="${pageContext.request.contextPath}/admin/create-test.jsp">
		<input class="button" type="submit"	value="Create a new test">
	</form>
	
	<form method="get" action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit"	value="Home">
	</form>

	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>