<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 15.12.18
  Time: 20:06
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
    Username: <form:input path="username" /><form:errors path="username" cssClass="error" /></br>
    Password: <form:password path="password" /><form:errors path="password" cssClass="error" /></br>
    Enabled: <form:checkbox path="enabled" /><form:errors path="enabled" cssClass="error" /></br>
    Email: <form:input path="email" /><form:errors path="email" cssClass="error" /></br>
    <input type="submit" value="Register">
</form:form>
</body>
</html>
