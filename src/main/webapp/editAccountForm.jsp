<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 19.12.18
  Time: 11:38
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
<c:import url="logout.jsp" />
<p><img style="display: block; margin-left: auto; margin-right: auto; width: 80%" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY8AhTc1GOz0Ep8pvQeMgdlQwV1riBqeYIh645RuoYGUXRQ83y" /></p>
<c:import url="navbar.jsp" />
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
