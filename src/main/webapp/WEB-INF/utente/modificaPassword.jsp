<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/07/2022
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica password</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<form action="modifica-password">
    <label for="oldPassword">Vecchia password:</label>
    <input type="password" id="oldPassword" name="oldPassword">
    <label for="newPassword1">Nuova password:</label>
    <input type="password" id="newPassword1" name="newPassword1">
    <label for="newPassword2">Ripeti nuova password:</label>
    <input type="password" id="newPassword2" name="newPassword2">

    <input type="submit" value="Modifica">
</form>
</body>
</html>
