<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 24/05/2022
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrazione</title>
    <%@include file="links.html"%>
</head>
<body>
<%@ include file="navbar.jsp"%>
<br>
<form action="registra-utente" id="registrazione-form">
    <h2>Crea un account</h2>
    <fieldset>
        <legend> Dati di accesso</legend>
        <div>
            <label for="email">Email:</label>
            <input type="email" name="email" id="email">
        </div>
        <div>
            <label for="pass">Password</label>
            <input type="password" name="pass" id="pass">
        </div>
    </fieldset>
    <br>
    <fieldset>
        <legend>Dati personali</legend>
        <div>
            <label for="nome">Nome</label>
            <input type="text" name="nome" id="nome">
        </div>
        <div>
            <label for="cognome">Cognome</label>
            <input type="text" name="cognome" id="cognome">
        </div>
        <br>
        <div>
            <label for="data_nascita">Data di nascita</label>
            <input type="date" name="data_nascita" id="data_nascita" min="2005-01-01">
        </div>
        <br>
    </fieldset>
        <br>
    <input type="submit" value="Registrati">
</form>
</body>
</html>
