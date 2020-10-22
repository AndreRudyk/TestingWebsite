<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<c:set var = "user" scope = "page" value = "${sessionScope.user}"/>
<body>
	<h2>Welcome, ${user.firstname} ${user.lastname}!</h2>
	<table>
		<caption>My information</caption>
		<tr>
			<td><b>Username:</b></td>
			<td>${user.username}</td>
			
		</tr>
		<tr>
			<td><b>Email:</b></td>
			<td>${user.email}</td>
		</tr>
		<tr>
			<td><b>Firstname:</b></td>
			<td>${user.firstname}</td>
		</tr>
		<tr>
			<td><b>Lastname:</b></td>
			<td>${user.lastname}</td>
		</tr>
		
	</table>
	<p>Your role is ${sessionScope.role}.</p>
	<hr />
	<form method="get" action="${pageContext.request.contextPath}/serv/choose-test">
		<input class="button" type="submit"	value="See available tests">
	</form>
	<form method="get" action="${pageContext.request.contextPath}/serv/display-certificates">
		<input class="button" type="submit"	value="See my passed tests">
	</form>
	<br />
	<hr/>
	
	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>
</body>
</html>