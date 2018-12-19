<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 19.12.18
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="user" >
    <form:hidden path="username" /><form:errors path="username" cssClass="error" />
    <c:if test="${not empty wrongPassword}">
        Nieprawidłowe hasło!</br>
    </c:if>
    Old password: <form:password path="password" /><form:errors path="password" cssClass="error" /></br>
    New password: <input type="password" name="newPassword" /></br>
    <form:hidden path="enabled" /><form:errors path="enabled" cssClass="error" />
    <form:hidden path="email" /><form:errors path="email" cssClass="error" />
    <form:hidden path="dateOfBirth" placeholder="rrrr-mm-dd" /><form:errors path="dateOfBirth" cssClass="error" />
    <form:hidden path="aboutMe" />
    <form:hidden path="phone" />
    <input type="submit" value="Change password">
</form:form>
</body>
</html>
