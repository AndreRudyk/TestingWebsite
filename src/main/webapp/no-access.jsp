<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="first" var="lang"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="noAccess.title" bundle="${lang}"/></title>
</head>
    <body>
        <h3><fmt:message key="noAccess.message" bundle="${lang}"/></h3>
    <br>
        <a href="${pageContext.request.contextPath}/${sessionScope.role}/index.jsp"><fmt:message key="home" bundle="${lang}"/></a>

    </body>
</html>