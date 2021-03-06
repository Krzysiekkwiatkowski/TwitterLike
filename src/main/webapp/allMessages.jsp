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
<c:import url="logout.jsp" />
<div><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y"/></div></br>
<c:import url="navbar.jsp" />
<h4> Otrzymane </h4>
<c:forEach items="${receivedMessageList}" var="message">
    <a href="http://localhost:8080/twitter/message/${message.id}/${message.view}" ><c:if test="${message.view == false}" > <b>${message.sender.username} ${message.created} : ${message.message}</b> </c:if></a></br>
    <a href="http://localhost:8080/twitter/message/${message.id}/${message.view}" ><c:if test="${message.view == true}" > ${message.sender.username} ${message.created} : ${message.message} </c:if></a></br>
</c:forEach>
<h4> Wysłane </h4>
<c:forEach items="${sendMessageList}" var="message">
    <a href="http://localhost:8080/twitter/message/${message.id}/${message.view}" ><c:if test="${message.view == false}" > <b>${message.receiver.username} ${message.created} : ${message.message}</b> </c:if></a></br>
    <a href="http://localhost:8080/twitter/message/${message.id}/${message.view}" ><c:if test="${message.view == true}" > ${message.receiver.username} ${message.created} : ${message.message} </c:if></a></a></br>
</c:forEach>
</body>
</html>
