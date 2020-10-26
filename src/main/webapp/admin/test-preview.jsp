<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="test.preview.title" bundle="${lang}"/></title>
</head>
<body>
<h2>${sessionScope.test.name}</h2>

	<ol>
			<c:forEach items="${sessionScope.test.questions}" var="question">
			<li>
				<p>${question.text}</p>
				<ul>
					<li>${question.firstAnswer}</li>
					<li>${question.firstAnswer.nextAnswer}</li>
					<li>${question.firstAnswer.nextAnswer.nextAnswer}</li>
					<li>${question.firstAnswer.nextAnswer.nextAnswer.nextAnswer}</li>
				</ul>
			
			</li>
			</c:forEach>
	</ol>

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