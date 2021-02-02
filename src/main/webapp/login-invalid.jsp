<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<title><fmt:message key="index.title" bundle="${lang}"/></title>
</head>
<body>
	<h1 style="text-align: center">
		<fmt:message key="testing" bundle="${lang}"/>
	</h1>
	<hr />
	<h3 style="text-align: center"><fmt:message key="login" bundle="${lang}"/></h3>
	<form method="post"
		action="${pageContext.request.contextPath}/serv/login">
		<table style="margin-left: auto; margin-right: auto">
			<caption><fmt:message key="loginIvalidMessage" bundle="${lang}"/></caption>
			<tr>
				<td style="text-align: right"><label for="username"><fmt:message key="username" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="username" value="${param.username}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="password"><fmt:message key="password" bundle="${lang}"/>:</label></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button"
					type="submit" value="<fmt:message key="login.button" bundle="${lang}"/>"></td>
			</tr>
			<tr>
				<td><fmt:message key="no.account" bundle="${lang}"/></td>
				<td style="text-align: left"><a style="text-align: center"
					href="${pageContext.request.contextPath}/registration.jsp"><fmt:message key="register" bundle="${lang}"/></a></td>
			</tr>
		</table>
	</form>
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
</body>
</html>
