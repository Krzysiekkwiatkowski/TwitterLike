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
<c:import url="logout.jsp" />
<div><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y"/></div></br></br>
<c:import url="navbar.jsp" />
<h4> Szczegóły wiadomości </h4>
<c:if test="${empty cheater}">
Nadawca: ${message.sender.username} | Odbiorca: ${message.receiver.username} | Wiadomość: ${message.message}</br></br>
<a href="http://localhost:8080/twitter/message" > Cofnij </a>
</c:if>
<c:if test="${not empty cheater}">
    <h3 style="color: red"> Skup się na swoich wiadomościach! </h3>
</c:if>
</body>
</html>
