<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 24/05/2022
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="styleAccesso.css">
    <title>Accedi</title>
</head>
<body>
<div id="logo">
    <h1>
        GlobalTech
    </h1>
</div>
<div id="accesso">
    <h2>Sei già un cliente GlobalTech?</h2>
    <br>
    <form action="#">
        <label for="email">Email:</label><br>
        <input type="text" name="email" id="email"><br>
        <label for="password">Password:</label><br>
        <input type="password" name="password" id="password">
        <br>
        <input type="submit" value="Login">
        <br>
        <br>
        <br>
        <a href="#">Sei un nuovo cliente?<br>Registrati</a>
    </form>
</div>
</body>
</html>
