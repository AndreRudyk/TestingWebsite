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
<title><fmt:message key="user.edit.title" bundle="${lang}"/></title>
</head>
<body>
<h2><fmt:message key="user.edit.title" bundle="${lang}"/></h2>

<c:set var = "userToEdit" scope = "page" value = "${sessionScope.userToEdit}"/>

<form method="post" action="${pageContext.request.contextPath}/serv/finish-u-edit">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><fmt:message key="username" bundle="${lang}"/>:</td>
				<td>${userToEdit.username }</td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="email"><fmt:message key="email" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="email" value="${userToEdit.email}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="firstname"><fmt:message key="firstname" bundle="${lang}"/></label></td>
				<td><input type="text" name="firstname" value="${userToEdit.firstname}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="lastname"><fmt:message key="lastname" bundle="${lang}"/></label></td>
				<td><input type="text" name="lastname" value="${userToEdit.lastname}"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="user.update" bundle="${lang}"/>"></td>
			</tr>
		</table>
	</form>
	
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