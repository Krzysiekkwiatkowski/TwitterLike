<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 17.12.18
  Time: 13:03
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
Add tweet <c:import url="addTweet.jsp" />
<p> Twoje wpisy </p>
<c:forEach items="${userTweets}" var="tweet">
    ${tweet.created} (${tweet.user.username}) ${tweet.text} <a href="http://localhost:8080/user/${tweet.id}" > Szczegóły </a> </br>
</c:forEach>
</body>
</html>