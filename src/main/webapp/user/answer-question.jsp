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
<title><fmt:message key="test" bundle="${lang}"/></title>
</head>
<body>
<h2><fmt:message key="test.question" bundle="${lang}"/> ${sessionScope.index}</h2>
<c:set var = "questionText" scope = "session" value = "${sessionScope.question.text}"/>
<c:set var = "answer1" scope = "page" value = "${sessionScope.question.firstAnswer}"/>
<c:set var = "answer2" scope = "page" value = "${sessionScope.question.firstAnswer.nextAnswer}"/>
<c:set var = "answer3" scope = "page" value = "${sessionScope.question.firstAnswer.nextAnswer.nextAnswer}"/>
<c:set var = "answer4" scope = "page" value = "${sessionScope.question.firstAnswer.nextAnswer.nextAnswer.nextAnswer}"/>
<form method="post"	action="${pageContext.request.contextPath}/serv/answer-next">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><h4>${questionText}</h4></td>
			</tr>
			<tr>
				<td>${answer1}</td>
				<td><input type="checkbox" id="answer1correct" name="answer1correct"></td>
			</tr>
			<tr>
				<td>${answer2}</td>
				<td><input type="checkbox" id="answer2correct" name="answer2correct"></td>
			</tr>
			<tr>
				<td>${answer3}</td>
				<td><input type="checkbox" id="answer3correct" name="answer3correct"></td>
			</tr>
			<tr>
				<td>${answer4}</td>
				<td><input type="checkbox" id="answer4correct" name="answer4correct"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="test.next" bundle="${lang}"/>"></td>
			</tr>
		</table>
	</form>
	
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