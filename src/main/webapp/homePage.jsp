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
<h1> Twitterlike </h1>
<p> Tweets: </p></br>
<c:forEach items="${tweets}" var="tweet">
    ${tweet.created} ${tweet.user.username} ${tweet.text}</br>
</c:forEach>
</body>
</html>
