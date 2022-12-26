<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle var="locale" basename="locale"/>
<html>
<head>
    <title>NHN-Mart::Home</title>
</head>
<body>
<h1>Home</h1>
<a href="foodstand.jsp"> <fmt:message bundle="${locale}" key="foodStand"/> </a><br>
<a href="basket.jsp"> <fmt:message bundle="${locale}" key="basket"/> </a><br>
<p> <fmt:message bundle="${locale}" key="balance"/>: ${initParam["balance"]}<fmt:message bundle="${locale}" key="currency"/> </p>
<p> <fmt:message bundle="${locale}" key="language"/>:  <%= request.getLocale().getDisplayLanguage() %> </p>
<a href="/changeLang"> <fmt:message bundle="${locale}" key="changeLangLink"/> </a><br>
<a href="/logout"> <fmt:message bundle="${locale}" key="logout"/> </a>
</body>
</html>
