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
<c:import url="logout.jsp" />
<p><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y" /></p>
Add tweet <c:import url="addTweet.jsp" />
<h4> Twoje wpisy </h4>
<c:forEach items="${userTweets}" var="tweet">
    ${tweet.created} (${tweet.user.username}) ${tweet.text} <a href="http://localhost:8080/twitter/user/${tweet.id}" > Szczegóły </a></br>
</c:forEach>
</body>
</html>
