<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register</title>
</head>
	<body>
		<h1>Registration page</h1>
		<form method="post" action="${pageContext.request.contextPath}/serv/registration">
		<table>
			<tr>
				<td><label for="username">Username:</label></td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td><label for="password">Password:</label></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><label for="email">Email:</label></td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td><label for="firstname">First name:</label></td>
				<td><input type="text" name="firstname"></td>
			</tr>
			<tr>
				<td><label for="lastname">Last name:</label></td>
				<td><input type="text" name="lastname"></td>
			</tr>
			<tr>
				<td></td>
				<td  style="text-align:right"><input class="button" type="submit" value="Register"></td>
			</tr>
		</table>
			
		</form>
	</body>
</html>