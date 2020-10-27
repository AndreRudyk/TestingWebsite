<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="allRegisteredUsers.title" bundle="${lang}"/></title>
</head>
<body>
<h2><fmt:message key="allRegisteredUsers.admins" bundle="${lang}"/></h2>
<table style="border-collapse: collapse;">
	<tr style="border:1px solid black; border-collapse: collapse;">
		<th style="border: 1px solid black;"><fmt:message key="username" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="email" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="firstname" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="lastname" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="dateCreated" bundle="${lang}"/></th>
	</tr>
	<c:forEach items="${sessionScope.admins}" var="user">
	<tr>
		<td style="border: 1px solid black;">${user.username}</td>
		<td style="border: 1px solid black;">${user.email}</td>
		<td style="border: 1px solid black;">${user.firstname}</td>
		<td style="border: 1px solid black;">${user.lastname}</td>
		<td style="border: 1px solid black;">${user.timeCreated}</td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/delete-u?uname=${user.username}"><fmt:message key="user.delete" bundle="${lang}"/></a></td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/revoke-admin-rights?uname=${user.username}"><fmt:message key="user.revokeAdminRights" bundle="${lang}"/></a></td>
		
	</tr>
	</c:forEach>
</table>
<h2><fmt:message key="allRegisteredUsers.users" bundle="${lang}"/></h2>
<table style="border-collapse: collapse;">
	<tr style="border:1px solid black; border-collapse: collapse;">
		<th style="border: 1px solid black;"><fmt:message key="username" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="email" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="firstname" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="lastname" bundle="${lang}"/></th>
		<th style="border: 1px solid black;"><fmt:message key="dateCreated" bundle="${lang}"/></th>
	</tr>
	<c:forEach items="${sessionScope.users}" var="user">
	<tr>
		<td style="border: 1px solid black;">${user.username}</td>
		<td style="border: 1px solid black;">${user.email}</td>
		<td style="border: 1px solid black;">${user.firstname}</td>
		<td style="border: 1px solid black;">${user.lastname}</td>
		<td style="border: 1px solid black;">${user.timeCreated}</td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/delete-u?uname=${user.username}"><fmt:message key="user.delete" bundle="${lang}"/></a></td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/grant-admin-rights?uname=${user.username}"><fmt:message key="user.grantAdminRights" bundle="${lang}"/></a></td>
		<td style="border: 1px solid black;"><a href="${pageContext.request.contextPath}/serv/edit-u-info?uname=${user.username}"><fmt:message key="user.edit" bundle="${lang}"/></a></td>
		
	</tr>
	</c:forEach>
</table>
<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit" value="<fmt:message key="home" bundle="${lang}"/>">
	</form>
	<br />
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>

</body>
</html>