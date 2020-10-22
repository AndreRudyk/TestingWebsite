<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create a test</title>
</head>
<body>
	<h1 style="text-align: center">
		<fmt:message key="testing" bundle="${lang}"/>
	</h1>
	<hr />
	<h3 style="text-align: center">Create a test</h3>
	<p>Please, provide 1 question and 4 possible answers.</p>	
	<form method="post"
		action="${pageContext.request.contextPath}/serv/add-question">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><label for="question">Question</label></td>
				<td><input type="text" name="question" size=100></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer1">Answer 1</label></td>
				<td><input type="text" name="answer1" size=100></td>
				<td><input type="checkbox" id="answer1correct" name="answer1correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer2">Answer 2</label></td>
				<td><input type="text" name="answer2" size=100></td>
				<td><input type="checkbox" id="answer2correct" name="answer2correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer3">Answer 3</label></td>
				<td><input type="text" name="answer3" size=100></td>
				<td><input type="checkbox" id="answer3correct" name="answer3correct"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="answer1">Answer 4</label></td>
				<td><input type="text" name="answer4" size=100></td>
				<td><input type="checkbox" id="answer4correct" name="answer4correct"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="Add next question"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button" type="submit" value="Finish" formaction="${pageContext.request.contextPath}/serv/finish-test"></td>
			</tr>
		</table>
	</form>
	
	
	<a href="?locale=uk">Українська</a>
	<br />
	<a href="?locale=en">English</a>
</body>
</html>
