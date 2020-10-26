<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
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