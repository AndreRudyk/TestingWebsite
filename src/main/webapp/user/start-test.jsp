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
<c:set var = "test" scope = "page" value = "${sessionScope.testInProgress}"/>
<title>${test.name}</title>
</head>
<body>
<h2>Test</h2>
<hr/>
<p>After you press <b>start</b>, you'll have ${test.time} minutes to complete it.</p>
<p>You won't be able to go back to skipped questions. So, better not skip them.</p>
<p>If you don't finish the test in time, only the submitted answers will be counted during the evaluation.</p>
<p><a href="${pageContext.request.contextPath}/serv/answer-first">Take test</a>
	<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit" value="Home">
	</form>
	<br />
	<a href="${pageContext.request.contextPath}/serv/logout">Logout</a>

</body>
</html>