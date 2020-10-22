<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test finished</title>
</head>
<body>
	<h2>All done!</h2>
	<p>You completed the test <b>${sessionScope.finishedTestName}</b>!</p>
	<p>The final grade is <b>${sessionScope.mark}</b></p>
	<hr />

	<form method="get" action="${pageContext.request.contextPath}/user/home.jsp">
		<input class="button" type="submit"	value="Home">
	</form>

	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>