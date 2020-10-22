<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h2>Welcome, ${sessionScope.user.firstname}
		${sessionScope.user.lastname}! ${sessionScope.lang}</h2>
	<p>Your role is ${sessionScope.role}.</p>
	<hr />
	<h3>Tests will be added soon!</h3>
	<hr />

	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-registered">
		<input class="button" type="submit"	value="Show all users">
	</form>
	
	<br />
	
	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-tests">
		<input class="button" type="submit"	value="Show all available tests">
	</form>
	
	<br />
	
	<form method="get" action="${pageContext.request.contextPath}/admin/create-test.jsp">
		<input class="button" type="submit"	value="Create a new test">
	</form>
	
	<hr />

	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>