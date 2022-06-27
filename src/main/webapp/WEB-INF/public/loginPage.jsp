<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 24/05/2022
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accedi</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
<%@include file="/WEB-INF/includes/navbar.jsp"%>
<br><br>
<div id="accesso">
    <form id="login-form" action="login" method="post">
        <h2>Sei gi&agrave; un cliente GlobalTech?</h2>
        <br>
        <%if(request.getAttribute("error_login") != null) {
            boolean error = (boolean) request.getAttribute("error_login");

            if(error){%>
        <div id="error">
            Email e/o password errata, ritentare.
        </div>
        <%}
        }%>
        <label for="email">Email:</label><br>
        <input type="text" name="email" id="email"><br>
        <label for="password">Password:</label><br>
        <input type="password" name="password" id="password">
        <br>
        <br>
        <input type="submit" value="Login">
        <br>
        <a href="registrazione">Sei un nuovo cliente? Registrati!</a>
    </form>
</div>
</body>
</html>
