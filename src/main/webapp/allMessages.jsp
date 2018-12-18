<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 18.12.18
  Time: 14:13
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
<h4> Otrzymane </h4>
<c:forEach items="${receivedMessageList}" var="message">
    <c:if test="${message.view == false}" > <b>${message.sender.username} : ${message.message}</b> </c:if></br>
    <c:if test="${message.view == true}" > ${message.sender.username} : ${message.message} </c:if>
</c:forEach>
<h4> Wysłane </h4>
<c:forEach items="${sendMessageList}" var="message">
    <c:if test="${message.view == false}" > <b>${message.receiver.username} : ${message.message}</b> </c:if></br>
    <c:if test="${message.view == true}" > ${message.receiver.username} : ${message.message} </c:if>
</c:forEach>
</body>
</html>
