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
<c:import url="logout.jsp" />
<p><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y" /></p>
<c:import url="navbar.jsp" />
<h4> Szczegóły tweeta </h4>
${tweet.id} ${tweet.created} ${tweet.user.username} ${tweet.text}</br>
<c:if test="${not empty commentList}">
    <h4>Komentarze:</h4>
    <c:forEach items="${commentList}" var="comment">
        ${comment.user.username}: ${comment.text}</br>
    </c:forEach>
</c:if>
<c:if test="${empty commentList}">
    <p>Nie dodano jeszcze komentarzy!</p>
</c:if>
<h4>
    <form method="post" action="http://localhost:8080/twitter/comment/${tweet.id}">
        Comment: <input type="text" name="text">
        <input type="submit" value="Add comment">
    </form>
</h4>
</body>
</html>
