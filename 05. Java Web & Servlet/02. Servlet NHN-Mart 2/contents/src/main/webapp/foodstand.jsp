<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NHN-Mart::상품목록</title>
</head>
<body>
<h1>FoodStand-JSP</h1>
<ul>
    <li>양파 (1,000원): ${initParam["onion"]}개</li>
    <li>계란 (2,000원): ${initParam["egg"]}개</li>
    <li>파 (500원): ${initParam["greenOnion"]}개</li>
    <li>사과 (2,000원): ${initParam["apple"]}개</li>
</ul>
<a href="food.jsp">상품구매</a>
</body>
</html>
