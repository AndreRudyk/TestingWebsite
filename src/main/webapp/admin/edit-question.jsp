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
<title><fmt:message key="test.edit.title" bundle="${lang}"/></title>
</head>
<body>
<h2><fmt:message key="test.editing" bundle="${lang}"/></h2>
<c:set var = "questionText" scope = "session" value = "${sessionScope.question.text}"/>
<c:set var = "oldAnswer1" scope = "page" value = "${sessionScope.question.firstAnswer}"/>
<c:set var = "oldAnswer2" scope = "page" value = "${sessionScope.question.firstAnswer.nextAnswer}"/>
<c:set var = "oldAnswer3" scope = "page" value = "${sessionScope.question.firstAnswer.nextAnswer.nextAnswer}"/>
<c:set var = "oldAnswer4" scope = "page" value = "${sessionScope.question.firstAnswer.nextAnswer.nextAnswer.nextAnswer}"/>
<form method="post"
		action="${pageContext.request.contextPath}/serv/edit-question">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><label for="question"><fmt:message key="test.question" bundle="${lang}"/></label></td>
				<td><input type="text" name="question" size=100 value="${questionText}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer1"><fmt:message key="test.answer" bundle="${lang}"/> 1</label></td>
				<td><input type="text" name="answer1" size=100 value="${oldAnswer1}"></td>
				<td>${oldAnswer1.correct}</td>
				<td><input type="checkbox" id="answer1correct" name="answer1correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer2"><fmt:message key="test.answer" bundle="${lang}"/> 2</label></td>
				<td><input type="text" name="answer2" size=100 value="${oldAnswer2}"></td>
				<td>${oldAnswer2.correct}</td>
				<td><input type="checkbox" id="answer2correct" name="answer2correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer3"><fmt:message key="test.answer" bundle="${lang}"/> 3</label></td>
				<td><input type="text" name="answer3" size=100 value="${oldAnswer3}"></td>
				<td>${oldAnswer3.correct}</td>
				<td><input type="checkbox" id="answer3correct" name="answer3correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer1"><fmt:message key="test.answer" bundle="${lang}"/> 4</label></td>
				<td><input type="text" name="answer4" size=100 value="${oldAnswer4}"></td>
				<td>${oldAnswer4.correct}</td>
				<td><input type="checkbox" id="answer4correct" name="answer4correct"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="test.next" bundle="${lang}"/>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" formaction="${pageContext.request.contextPath}/serv/delete-question" value="<fmt:message key="test.delete" bundle="${lang}"/>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" formaction="${pageContext.request.contextPath}/serv/skip-edit-question" value="<fmt:message key="test.question.skip" bundle="${lang}"/>"></td>
			</tr>
		</table>
	</form>
	
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