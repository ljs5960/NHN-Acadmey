<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NHN-Mart::상점</title>
</head>
<body>
<h1>Food-JSP</h1>
<ul>
    <li>양파 (1,000원) 재고: ${initParam["onion"]}개</li>
    <li>계란 (2,000원) 재고: ${initParam["egg"]}개</li>
    <li>파 (500원) 재고: ${initParam["greenOnion"]}개</li>
    <li>사과 (2,000원) 재고: ${initParam["apple"]}개</li>
</ul>
<br />
<form method="post" action="/basket">
    양파 갯수 : <input type="number" name="onion" required><br>
    계란 갯수 : <input type="number" name="egg" required><br>
    파 갯수 : <input type="number" name="greenOnion" required><br>
    사과 갯수 : <input type="number" name="apple" required><br>
    <input type="submit" value="장바구니 담기">
</form>
</body>
</html>
