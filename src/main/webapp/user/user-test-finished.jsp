<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="test.finished.title" bundle="${lang}"/></title>
</head>
<body>
<fmt:message key="test.finished.messageOne" bundle="${lang}"/><b>${sessionScope.finishedTestName}!</b>
<fmt:message key="test.finished.messageTwo" bundle="${lang}"/><b>${sessionScope.mark}</b>.</p>
	<hr />

	<form method="get" action="${pageContext.request.contextPath}/user/home.jsp">
		<input class="button" type="submit"	value="<fmt:message key="home" bundle="${lang}"/>">
	</form>
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>

</body>
</html>