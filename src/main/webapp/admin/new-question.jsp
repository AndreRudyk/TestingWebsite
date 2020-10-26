<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="test.create.title" bundle="${lang}"/></title>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/serv/add-question">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><label for="question"><fmt:message key="test.question" bundle="${lang}"/></label></td>
				<td><input type="text" name="question" size=100></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer1"><fmt:message key="test.answer" bundle="${lang}"/> 1</label></td>
				<td><input type="text" name="answer1" size=100></td>
				<td><input type="checkbox" id="answer1correct" name="answer1correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer2"><fmt:message key="test.answer" bundle="${lang}"/> 2</label></td>
				<td><input type="text" name="answer2" size=100></td>
				<td><input type="checkbox" id="answer2correct" name="answer2correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer3"><fmt:message key="test.answer" bundle="${lang}"/> 3</label></td>
				<td><input type="text" name="answer3" size=100></td>
				<td><input type="checkbox" id="answer3correct" name="answer3correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer1"><fmt:message key="test.answer" bundle="${lang}"/> 4</label></td>
				<td><input type="text" name="answer4" size=100></td>
				<td><input type="checkbox" id="answer4correct" name="answer4correct"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="test.question.addNext" bundle="${lang}"/>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="<fmt:message key="test.finishCreationButton" bundle="${lang}"/>" formaction="${pageContext.request.contextPath}/serv/finish-test"></td>
			</tr>
		</table>
	</form>
	
	<form method="get" action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit"	value="<fmt:message key="home" bundle="${lang}"/>">
	</form>
	
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>
</body>
</html>
