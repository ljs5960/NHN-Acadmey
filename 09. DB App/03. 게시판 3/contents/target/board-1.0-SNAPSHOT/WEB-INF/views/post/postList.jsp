<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>Post List</title>
</head>
<body>
  <h1>게시글 목록</h1>

  <c:forEach var="post" items="${posts}">
    제목: ${post.title} <br/>
    작성자: ${post.writer}<br/>
    작성일시: ${post.createdAt}<br/>
    댓글 개수:
    <br/>
  </c:forEach>

  <a href="/post/write">게시글 등록하기</a>
</body>
