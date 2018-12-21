<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 15.12.18
  Time: 12:05
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
<h4><c:import url="addTweet.jsp" /></h4>
<h4> Tweets: </h4>
<c:forEach items="${tweets}" var="tweet">
    ${tweet.created} ${tweet.user.username} ${tweet.text} <a href="http://localhost:8080/twitter/message/${tweet.user.id}"> Wyślij wiadomość </a> | <a href="http://localhost:8080/twitter/comment/${tweet.id}"> Skomentuj </a></br>
    <c:if test="${not empty allow && tweet.id == allow}">
        <h4>
            <form method="post" action="http://localhost:8080/twitter/comment/${tweet.id}">
                <input type="hidden" name="allow" value="allow">
                Skomentuj: <input type="text" name="text">
                <input type="submit" value="Skomentuj">
            </form>
        </h4>
    </c:if>
</c:forEach>
<c:if test="${not empty error}" >
    <p style="color: red"> Nie możesz wysłać wiadomości do samego siebie! </p>
</c:if>
</body>
</html>
