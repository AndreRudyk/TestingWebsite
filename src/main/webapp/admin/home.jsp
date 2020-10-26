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
	<meta charset="UTF-8">
	<title><fmt:message key="home.title" bundle="${lang}"/></title>
</head>
<c:set var = "user" scope = "page" value = "${sessionScope.user}"/>
<body>
	<h2><fmt:message key="welcome" bundle="${lang}"/>, ${user.firstname} ${user.lastname}!</h2>
	<hr />

	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-registered">
		<input class="button" type="submit"	value="<fmt:message key="allRegisteredUsers" bundle="${lang}"/>">
	</form>
	
	<br />
	
	<form method="get" action="${pageContext.request.contextPath}/serv/find-all-tests">
		<input class="button" type="submit"	value="<fmt:message key="allAvailableTests" bundle="${lang}"/>">
	</form>
	
	<br />
	
	<form method="get" action="${pageContext.request.contextPath}/admin/create-test.jsp">
		<input class="button" type="submit"	value="<fmt:message key="test.createNew" bundle="${lang}"/>">
	</form>
	
	<hr />

	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>

</body>
</html>