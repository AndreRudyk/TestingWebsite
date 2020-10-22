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
<h2>Tests</h2>
<hr/>
<form method="post"	action="${pageContext.request.contextPath}/serv/choose-test">
	<table>
		<tr>
			<td>Choose category</td>
			<td>
				<select name="category" id="category">
					<option value="all" selected="selected">All</option>
    				<option value="english">English Language</option>
		    		<option value="history">History</option>
    				<option value="math">Math</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td>Sort by</td>
			<td>
				<select name="sortBy" id="sortBy">
					<option value="name" selected="selected">Name</option>
    				<option value="difficulty_id">Difficulty</option>
    				<option value="request_number">Popularity</option>
  				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input class="button" type="submit" value="Apply filter"></td>
		</tr>
  	</table>
</form>
	<table style="border-collapse: collapse;">
		<tr style="border: 1px solid black; border-collapse: collapse;">
			<th style="border: 1px solid black;">Name</th>
			<th style="border: 1px solid black;">Description</th>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Number of requests</th>
			<th style="border: 1px solid black;">Category</th>
			<th style="border: 1px solid black;">Difficulty</th>
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
					href="${pageContext.request.contextPath}/serv/start-test?tname=${test.name}">Take test</a></td>
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