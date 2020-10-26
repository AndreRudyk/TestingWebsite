<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var = "test" scope = "page" value = "${sessionScope.testInProgress}"/>
<title>${test.name}</title>
</head>
<body>
<h2>Test</h2>
<hr/>
<fmt:message key="test.startMessageOne" bundle="${lang}"/><b>${test.time}</b><fmt:message key="test.startMessageTwo" bundle="${lang}"/> 
<p><a href="${pageContext.request.contextPath}/serv/answer-first"><fmt:message key="test.start" bundle="${lang}"/></a>
	<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/user/home.jsp">
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