<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NHN-Mart::장바구니</title>
</head>
<body>
<h1>Basket-JSP</h1>

<form method="post" target="/pay">
    양파 : <input type="number" name="onion" value="<%=request.getParameter("onion")%>">개<br>
    계란 : <input type="number" name="egg" value="<%=request.getParameter("egg")%>">개<br>
    파 : <input type="number" name="greenOnion" value="<%=request.getParameter("greenOnion")%>">개<br>
    사과 : <input type="number" name="apple" value="<%=request.getParameter("apple")%>">개<br>
    <input type="submit" value="구매하기">
</form>
</body>
</html>
