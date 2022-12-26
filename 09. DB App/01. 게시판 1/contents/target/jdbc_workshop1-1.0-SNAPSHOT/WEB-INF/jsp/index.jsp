<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var="course" items="${courses}">
        id: ${course.id} <br/>
        강사명: ${course.teacher.name} <br/>
        강의명: ${course.subject.name} <br/>
        생성일시: ${course.createdAt} <br/>
    </c:forEach>

    <a href="/insert">강의등록</a>

    <a href="/update">수정하기</a>

    <form method="post" action="/delete">
        <select name="courseId">
            <c:forEach var="course" items="${courses}">
                <option value="${course.id}">${course.id}</option>
            </c:forEach>
        </select>
        <input type="submit" value="삭제하기">
    </form>

    <a href="/logout">로그아웃</a>
</body>
</html>
