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
<title><fmt:message key="tests.myFinished.title" bundle="${lang}"/></title>
</head>
<body>
<h2><fmt:message key="tests.myFinished.certificates" bundle="${lang}"/></h2>
<hr/>

	<table style="border-collapse: collapse;">
		<tr style="border: 1px solid black; border-collapse: collapse;">
			<th style="border: 1px solid black;"><fmt:message key="test" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="test.mark" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="test.date" bundle="${lang}"/></th>
		</tr>
		<c:forEach items="${sessionScope.certificates}" var="certificate">
			<tr>
				<td style="border: 1px solid black;">${certificate.testName}</td>
				<td style="border: 1px solid black;">${certificate.mark}</td>
				<td style="border: 1px solid black;">${certificate.date}</td>
			</tr>
		</c:forEach>
	</table>
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