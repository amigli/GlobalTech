<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/06/2022
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci categoria</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<br><br><br>
<div id="inserireCategoria">
    <form action="inserisci-categoria" id="inserisciCategoria" method="post">
        <h2>Inserisci una nuova categoria</h2>
        <label for="nomeCategoria">Nome della categoria:</label>
        <input type="text" id="nomeCategoria" name="nomeCategoria" required>
        <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
        <textarea id="descrizioneCategoria" name="descrizioneCategoria" rows="5" cols="65" required></textarea>
        <br><br>
        <input type="submit" value="Inserisci">
    </form>
</div>
</body>
</html>
