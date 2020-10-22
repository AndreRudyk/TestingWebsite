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
<title>All registered</title>
</head>
<body>
<h2>Admins</h2>
<table style="border-collapse: collapse;">
	<tr style="border:1px solid black; border-collapse: collapse;">
		<th style="border: 1px solid black;">ID</th>
		<th style="border: 1px solid black;">Username</th>
		<th style="border: 1px solid black;">Email</th>
		<th style="border: 1px solid black;">Firstname</th>
		<th style="border: 1px solid black;">Lastname</th>
		<th style="border: 1px solid black;">Date created</th>
		<th style="border: 1px solid black;">Role</th>
	</tr>
	<c:forEach items="${sessionScope.admins}" var="user">
	<tr>
		<td style="border: 1px solid black;">${user.id}</td>
		<td style="border: 1px solid black;">${user.username}</td>
		<td style="border: 1px solid black;">${user.email}</td>
		<td style="border: 1px solid black;">${user.firstname}</td>
		<td style="border: 1px solid black;">${user.lastname}</td>
		<td style="border: 1px solid black;">${user.timeCreated}</td>
		<td style="border: 1px solid black;">${user.role}</td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/delete-u?uid=${user.id}">Delete</a></td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/revoke-admin-rights?uname=${user.username}">Revoke admin rights</a></td>
		
	</tr>
	</c:forEach>
</table>
<h2>Users</h2>
<table style="border-collapse: collapse;">
	<tr style="border:1px solid black; border-collapse: collapse;">
		<th style="border: 1px solid black;">ID</th>
		<th style="border: 1px solid black;">Username</th>
		<th style="border: 1px solid black;">Email</th>
		<th style="border: 1px solid black;">Firstname</th>
		<th style="border: 1px solid black;">Lastname</th>
		<th style="border: 1px solid black;">Date created</th>
		<th style="border: 1px solid black;">Role</th>
	</tr>
	<c:forEach items="${sessionScope.users}" var="user">
	<tr>
		<td style="border: 1px solid black;">${user.id}</td>
		<td style="border: 1px solid black;">${user.username}</td>
		<td style="border: 1px solid black;">${user.email}</td>
		<td style="border: 1px solid black;">${user.firstname}</td>
		<td style="border: 1px solid black;">${user.lastname}</td>
		<td style="border: 1px solid black;">${user.timeCreated}</td>
		<td style="border: 1px solid black;">${user.role}</td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/delete-u?uid=${user.id}">Delete</a></td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/grant-admin-rights?uname=${user.username}">Grant admin rights</a></td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/edit-u-info?uname=${user.username}">Edit information</a></td>
		
	</tr>
	</c:forEach>
</table>
<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit" value="Home">
	</form>
	<br />
	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>