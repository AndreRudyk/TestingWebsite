<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<h2>All done!</h2>
	<p>The user <b>${sessionScope.userToEdit.username}</b> was successfully updated!</p>
	<hr />
	
	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-registered">
		<input class="button" type="submit"	value="Show all users">
	</form>

	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-tests">
		<input class="button" type="submit"	value="Show all tests">
	</form>
	
	<br />
	
	<form method="get" action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit"	value="Home">
	</form>

	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>