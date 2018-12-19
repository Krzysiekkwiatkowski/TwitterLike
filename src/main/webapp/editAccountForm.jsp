<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 19.12.18
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="user" >
    <form:hidden path="id" />
    <form:hidden path="email" />
    <form:hidden path="enabled" />
    <form:hidden path="password" />
    Username: <form:input path="username" /><form:errors path="username" cssClass="error" /></br>
    Birth: <form:input path="dateOfBirth" placeholder="rrrr-mm-dd" /><form:errors path="dateOfBirth" cssClass="error" /></br>
    About me: <form:input path="aboutMe" /></br>
    Phone: <form:input path="phone" /></br>
    <input type="submit" value="Edit">
</form:form>
</body>
</html>
