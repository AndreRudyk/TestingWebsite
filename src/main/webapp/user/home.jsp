<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<title><fmt:message key="home.title" bundle="${lang}"/></title>
</head>
<c:set var = "user" scope = "page" value = "${sessionScope.user}"/>
<body>
	<h2><fmt:message key="welcome" bundle="${lang}"/>, ${user.firstname} ${user.lastname}!</h2>
	<table>
		<caption style="caption-side: top;"><fmt:message key="myinformation" bundle="${lang}"/></caption>
		<tr>
			<td><b><fmt:message key="username" bundle="${lang}"/>:</b></td>
			<td>${user.username}</td>
			
		</tr>
		<tr>
			<td><b><fmt:message key="email" bundle="${lang}"/>:</b></td>
			<td>${user.email}</td>
		</tr>
		<tr>
			<td><b><fmt:message key="firstname" bundle="${lang}"/>:</b></td>
			<td>${user.firstname}</td>
		</tr>
		<tr>
			<td><b><fmt:message key="lastname" bundle="${lang}"/>:</b></td>
			<td>${user.lastname}</td>
		</tr>
		
	</table>
	<hr />
	<form method="get" action="${pageContext.request.contextPath}/serv/choose-test">
		<input class="button" type="submit"	value="<fmt:message key="tests.available" bundle="${lang}"/>">
	</form>
	<form method="get" action="${pageContext.request.contextPath}/serv/display-certificates">
		<input class="button" type="submit"	value="<fmt:message key="tests.passed" bundle="${lang}"/>">
	</form>
	<br />
	<hr/>
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>
</body>
</html>