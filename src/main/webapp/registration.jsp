<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
		<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<title><fmt:message key="registration.title" bundle="${lang}"/></title>
</head>
	<body>
		<h1><fmt:message key="registration.title" bundle="${lang}"/></h1>
		<form method="post" action="${pageContext.request.contextPath}/serv/registration">
		<table>
			<tr>
				<td><label for="username"><fmt:message key="username" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td><label for="password"><fmt:message key="password" bundle="${lang}"/>:</label></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><label for="email"><fmt:message key="email" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td><label for="firstname"><fmt:message key="firstname" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="firstname"></td>
			</tr>
			<tr>
				<td><label for="lastname"><fmt:message key="lastname" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="lastname"></td>
			</tr>
			<tr>
				<td></td>
				<td  style="text-align:right"><input class="button" type="submit" value="<fmt:message key="register" bundle="${lang}"/>"></td>
			</tr>
		</table>
			
		</form>
		
		<a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="login.page" bundle="${lang}"/></a>
		<br/>
		<a href="?locale=uk">Українська</a>
		<br />
		<a href="?locale=en">English</a>
	</body>
</html>