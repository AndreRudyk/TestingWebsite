<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	<title><fmt:message key="test.edit.title" bundle="${lang}"/></title>
</head>
<body>
	<h2><fmt:message key="test.edit.done" bundle="${lang}"/></h2>
	<p><fmt:message key="test.edit.done.messageOne" bundle="${lang}"/>${sessionScope.test.name}<fmt:message key="test.edit.done.messageTwo" bundle="${lang}"/></p>
	<hr />

	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-tests">
		<input class="button" type="submit"	value="<fmt:message key="tests.all" bundle="${lang}"/>">
	</form>
	
	<form method="get" action="${pageContext.request.contextPath}/admin/create-test.jsp">
		<input class="button" type="submit"	value="<fmt:message key="test.createNew" bundle="${lang}"/>">
	</form>
	
	<form method="get" action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit"	value="<fmt:message key="home" bundle="${lang}"/>">
	</form>

	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>

</body>
</html>