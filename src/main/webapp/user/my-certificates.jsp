<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All test</title>
</head>
<body>
<h2>My certificates</h2>
<hr/>

	<table style="border-collapse: collapse;">
		<tr style="border: 1px solid black; border-collapse: collapse;">
			<th style="border: 1px solid black;">Test</th>
			<th style="border: 1px solid black;">Mark</th>
			<th style="border: 1px solid black;">Date</th>
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
		<input class="button" type="submit" value="Home">
	</form>
	<br />
	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>