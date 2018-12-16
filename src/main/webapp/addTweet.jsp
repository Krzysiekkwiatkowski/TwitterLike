<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 16.12.18
  Time: 20:09
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
<form action="http://localhost:8080/home" method="post">
    Tweet: <input type="text" name="text"/>
    <input type="submit" value="Tweet">
</form>
</body>
</html>
