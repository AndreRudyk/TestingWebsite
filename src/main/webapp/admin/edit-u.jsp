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
<title>All test</title>
</head>
<body>
<h2>Tests</h2>

<c:set var = "userToEdit" scope = "page" value = "${sessionScope.userToEdit}"/>

<form method="post" action="${pageContext.request.contextPath}/serv/finish-u-edit">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right">Username:</td>
				<td>${userToEdit.username }</td>
			</tr>
			<tr>
				<td style="text-align: right"><label for=""email"">Email:</label></td>
				<td><input type="text" name="email" value="${userToEdit.email}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="firstname">First Name</label></td>
				<td><input type="text" name="firstname" value="${userToEdit.firstname}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="lastname">Last Name</label></td>
				<td><input type="text" name="lastname" value="${userToEdit.lastname}"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="Update"></td>
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