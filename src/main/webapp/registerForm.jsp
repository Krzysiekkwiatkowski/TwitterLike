<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 15.12.18
  Time: 20:06
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
<c:import url="logbar.jsp" />
<p><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y" /></p>
<form:form method="post" modelAttribute="user" >
    Username: <form:input path="username" /><form:errors path="username" cssClass="error" /></br>
    Password: <form:password path="password" /><form:errors path="password" cssClass="error" /></br>
    Enabled: <form:checkbox path="enabled" /><form:errors path="enabled" cssClass="error" /></br>
    <c:if test="${not empty notUnique}">
        Już istnieje konto z tym emailem!</br>
    </c:if>
    Email: <form:input path="email" /><form:errors path="email" cssClass="error" /></br>
    Birth: <form:input path="dateOfBirth" placeholder="rrrr-mm-dd" /><form:errors path="dateOfBirth" cssClass="error" /></br>
    About me: <form:input path="aboutMe" /></br>
    Phone: <form:input path="phone" /></br>
    <input type="submit" value="Register">
</form:form>
</body>
</html>
