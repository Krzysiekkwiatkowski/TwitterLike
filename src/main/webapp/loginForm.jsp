<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 15.12.18
  Time: 22:00
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
<c:import url="logbar.jsp" />
<p><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y" /></p>
<form:form method="post" modelAttribute="user">
    <c:if test="${not empty error}">
        Błędny login lub hasło!</br>
    </c:if>
    Email: <form:input path="email" /><form:errors path="email" cssClass="error" /></br>
    Password: <form:password path="password" /><form:errors path="password" cssClass="error" /></br>
    <input type="submit" value="Log in">
</form:form>
</body>
</html>