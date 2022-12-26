<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/update">
        id: <input type="text" name="c_id"> <br/>
        강사명: <input type="text" name="t_name"> <br/>
        강의명: <input type="text" name="s_name"> <br/>
        <input type="submit">
    </form>
</body>
</html>
