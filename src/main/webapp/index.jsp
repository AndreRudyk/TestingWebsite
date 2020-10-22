<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Testing website</title>
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
			<tr>
				<td style="text-align: right"><label for="username"><fmt:message key="username" bundle="${lang}"/>:</label></td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="password"><fmt:message key="password" bundle="${lang}"/>:</label></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button"
					type="submit" value="Log in"></td>
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
