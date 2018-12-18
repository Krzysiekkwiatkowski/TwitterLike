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
<h2> Twitterlike </h2>
<h4><c:import url="addTweet.jsp" /></h4>
<h4> Tweets: </h4>
<c:forEach items="${tweets}" var="tweet">
    ${tweet.created} ${tweet.user.username} ${tweet.text} <a href="http://localhost:8080/twitter/message/${tweet.user.id}"> Wyślij wiadomość </a></br>
</c:forEach>
<c:if test="${not empty error}" >
    <p style="color: red"> Nie możesz wysłać wiadomości do samego siebie! </p>
</c:if>
</body>
</html>
