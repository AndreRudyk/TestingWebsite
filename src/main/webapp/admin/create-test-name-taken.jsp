<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>

<html>
<head>
		<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<title><fmt:message key="test.create.title" bundle="${lang}"/></title>
</head>
<body>
	<h2 style="text-align: center">
		<fmt:message key="test.create.title" bundle="${lang}"/>
	</h2>
	<hr />
	<p><fmt:message key="test.nameTaken" bundle="${lang}"/></p>
	<form method="post"	action="${pageContext.request.contextPath}/serv/create-test">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td style="text-align: right"><label for="name"><fmt:message key="test.name" bundle="${lang}"/></label></td>
				<td><input type="text" name="name" value="${param.name}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="description"><fmt:message key="test.description" bundle="${lang}"/></label></td>
				<td><input type="text" name="description" size=100 value="${param.description}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="time"><fmt:message key="test.time" bundle="${lang}"/></label></td>
				<td><input type="number" id="time" name="time" min="30" max="180" value="${param.time}"></td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="category"><fmt:message key="category" bundle="${lang}"/></label></td>
				<td>
					<select name="category" id="category">
						<option value="" selected disabled hidden><fmt:message key="category.choose" bundle="${lang}"/></option>
    					<option value="english"><fmt:message key="category.english" bundle="${lang}"/></option>
    					<option value="history"><fmt:message key="category.history" bundle="${lang}"/></option>
    					<option value="math"><fmt:message key="category.math" bundle="${lang}"/></option>
  					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right"><label for="difficulty"><fmt:message key="difficulty" bundle="${lang}"/></label></td>
				<td>
					<select name="difficulty" id="difficulty">
						<option value="" selected disabled hidden><fmt:message key="difficulty.choose" bundle="${lang}"/></option>
    					<option value="easy"><fmt:message key="difficulty.easy" bundle="${lang}"/></option>
    					<option value="medium"><fmt:message key="difficulty.medium" bundle="${lang}"/></option>
    					<option value="hard"><fmt:message key="difficulty.hard" bundle="${lang}"/></option>
  					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right"><input class="button"
					type="submit" value="<fmt:message key="test.questions.add" bundle="${lang}"/>"></td>
			</tr>
		</table>
	</form>
	<hr />
	<form method="get"
		action="${pageContext.request.contextPath}/admin/home.jsp">
		<input class="button" type="submit" value="<fmt:message key="home" bundle="${lang}"/>">
	</form>
	<br />
	<a href="?locale=uk">????????????????????</a>
	<br />
	<a href="?locale=en">English</a>
	<br/>
	
	<a href="${pageContext.request.contextPath}/serv/logout"><fmt:message key="logout" bundle="${lang}"/></a>
</body>
</html>
	
