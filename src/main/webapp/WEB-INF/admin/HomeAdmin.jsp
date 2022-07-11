<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 23/06/2022
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Area Riservata</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <p>Scegli un servizio:</p>
    <ul>
        <li>
            <a href="admin-service?s=caricamento-prodotto">Caricamento Prodotto</a>
        </li>
        <li>
            <a href="admin-service?s=caricamento-offerta">Caricamento offerta</a>
        </li>
        <li>
            <a href="admin-service?s=caricamento-categoria">Caricamento categoria</a>
        </li>
        <li>
            <a href="visualizza-categorie">Visualizza categorie</a>
        </li>
        <li>
            <a href="visualizza-prodotti">Visualizza prodotti</a>
        </li>
        <li>
            <a href="visualizza-utenti">Gestione utenti</a>
        </li>
        <li>
            <a href="visualizza-ordini">Gestione ordini</a>
        </li>
    </ul>

</body>
</html>
