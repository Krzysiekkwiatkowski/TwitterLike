<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 17.12.18
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Twitterlike </h1>
<p> Szczegóły tweeta </p>
${tweet.id} ${tweet.created} ${tweet.user.username} ${tweet.text}</br>
<c:if test="${not empty commentList}">
    <p>Komentarze:</p>
    <c:forEach items="${commentList}" var="comment">
        ${comment.user.username}: ${comment.text}</br>
    </c:forEach>
</c:if>
<c:if test="${empty commentList}">
    <p>Nie dodano jeszcze komentarzy!</p>
</c:if>
<p>Add comment:
    <form method="post" action="http://localhost:8080/comment/${tweet.id}">
        Comment: <input type="text" name="text">
        <input type="submit" value="Add comment">
    </form>
</p>
</body>
</html>
