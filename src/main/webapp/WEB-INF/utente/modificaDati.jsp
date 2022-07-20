<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 16/07/2022
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica dati personali</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
Utente u = (Utente) session.getAttribute("utente");
%>

<h1>Benvenuto/a nella tua area personale</h1>
<fieldset>
    <legend>
        Dati di accesso
    </legend>
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" value="<%=u.getEmail()%>" disabled>
    <a href="utente-service?s=modifica-password">Vuoi modificare la tua password?</a>
</fieldset>

    <%
    if((u.getNome()==null) || (u.getCognome()==null) || (u.getDataNascita()==null) || u.getNumTelefono()==null){
    %>
        <h2>Dati personali non presenti</h2>
        <a href="utente-service?s=dati-personali">Completa dati personali</a>
    <br>
    <%}else{%>
    <h2>Dati personali presenti</h2>
    <a href="utente-service?s=dati-personali">Modifica dati personali</a>
    <%}%>

    <%
    if((u.getVia()==null)|| (u.getNumCivico()<0) || (u.getCitta()==null) || (u.getCap()<0)){
    %>
        <h2>Dati di spedizione non presenti</h2>
        <a href="utente-service?s=dati-spedizione">Completa dati di spedizione</a>
    <%}else{%>
    <h2>Dati di spedizione presenti</h2>
    <a href="utente-service?s=dati-spedizione">Modifica dati di spedizione</a>
    <%}%>

    <%
    if((u.getNumeroCarta()==null) || (u.getCvvCarta()<0) || (u.getDataScadenzaCarta()==null)){
    %>
        <h2>Dati di pagamento non presenti</h2>
        <a href="utente-service?s=dati-pagamento">Completa dati di pagamento</a>
    <%}else{%>
    <h2>Dati di pagamento presenti</h2>
    <a href="utente-service?s=dati-pagamento">Modifica dati di pagamento</a>
    <%}%>
</body>
</html>
