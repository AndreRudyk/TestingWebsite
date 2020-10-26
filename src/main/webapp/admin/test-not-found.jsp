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
<title><fmt:message key="tests.all" bundle="${lang}"/></title>
</head>
<body>
	<h2><fmt:message key="tests.all" bundle="${lang}"/></h2>
	<table style="border-collapse: collapse;">
		<tr style="border: 1px solid black; border-collapse: collapse;">
			<th style="border: 1px solid black;"><fmt:message key="test.name" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="test.description" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="test.time" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="test.requests" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="category" bundle="${lang}"/></th>
			<th style="border: 1px solid black;"><fmt:message key="difficulty" bundle="${lang}"/></th>
		</tr>
		<c:forEach items="${sessionScope.tests}" var="test">
			<tr>
				<td style="border: 1px solid black;">${test.name}</td>
				<td style="border: 1px solid black;">${test.description}</td>
				<td style="border: 1px solid black;">${test.time}</td>
				<td style="border: 1px solid black;">${test.numberOfRequests}</td>
				<td style="border: 1px solid black;">${test.category}</td>
				<td style="border: 1px solid black;">${test.difficulty}</td>
				<td style="border: 1px solid black;"><a
					href="${pageContext.request.contextPath}/serv/delete-test?tname=${test.name}"><fmt:message key="test.delete" bundle="${lang}"/></a></td>
				<td style="border: 1px solid black;"><a
					href="${pageContext.request.contextPath}/serv/edit-test?tname=${test.name}"><fmt:message key="test.edit" bundle="${lang}"/></a></td>
				<td style="border: 1px solid black;"><a
					href="${pageContext.request.contextPath}/serv/preview-test?tname=${test.name}"><fmt:message key="test.preview" bundle="${lang}"/></a></td>

			</tr>
		</c:forEach>
	</table>
	<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/admin/home.jsp">
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