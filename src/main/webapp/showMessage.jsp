<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 18.12.18
  Time: 20:30
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
<h4> Szczegóły wiadomości </h4>
${message.sender.username} -> ${message.receiver.username} : ${message.message}</br>
<a href="http://localhost:8080/twitter/message" > Cofnij </a>
</body>
</html>
