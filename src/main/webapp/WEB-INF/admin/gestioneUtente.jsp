<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 08/07/2022
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GestioneUtente</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
Utente u = (Utente) request.getAttribute("utenteId");
%>
<div id="modificaUtente">
    <form action="modifica-utente" method="get">
        <h2>ID utente: <%=u.getId()%></h2>
        <label for="emailUtente">Email utente:</label>
        <input type="text" id="emailUtente" name="emailUtente" value="<%=u.getEmail()%>" disabled>
        <label for="nomeUtente">Nome:</label>
        <input type="text" id="nomeUtente" name="nomeUtente" value="<%=u.getNome()%>" disabled>
        <label for="cognomeUtente">Cognome:</label>
        <input type="text" id="cognomeUtente" name="cognomeUtente" value="<%=u.getCognome()%>" disabled>
        <!--
        <label for="dataNascitaUtente">Data di nascita:</label>
        <input type="date" id="dataNascitaUtente" name="dataNascitaUtente" value="<%=u.getDataNascita()%>">
        -->
        <label for="viaUtente">Indirizzo:</label>
        <input type="text" id="viaUtente" name="viaUtente" value="<%=u.getIndirizzo()%>" disabled>
        <label for="civicoUtente">Numero civico:</label>
        <input type="text" id="civicoUtente" name="civicoUtente" value="<%=u.getNumCivico()%>" disabled>
        <label for="civicoUtente">Numero civico:</label>
        <input type="text" id="civicoUtente" name="civicoUtente" value="<%=u.getNumCivico()%>" disabled>
        <label for="cittaUtente">Città:</label>
        <input type="text" id="cittaUtente" name="cittaUtente" value="<%=u.getCitta()%>" disabled>
        <label for="capUtente">CAP:</label>
        <input type="text" id="capUtente" name="capUtente" value="<%=u.getCap()%>" disabled>
        <label for="numAcquistiUtente">Numero acquisti:</label>
        <input type="text" id="numAcquistiUtente" name="numAcquistiUtente" value="<%=u.getNumAcquisti()%>" disabled>
        <label for="numTelefonoUtente">Numero di telefono:</label>
        <input type="text" id="numTelefonoUtente" name="numTelefonoUtente" value="<%=u.getNumTelefono()%>" disabled>
        <%
        if (u.isAdmin()){
        %>
        <input type="checkbox" id="adminUtente" name="adminUtente" value="Admin" checked>
        <%}else{%>
        <input type="checkbox" id="adminUtente" name="adminUtente" value="Admin">
        <%}%>
        <label for="adminUtente"> Admin </label>
        <input type="hidden" name="id" value="<%=u.getId()%>">
        <br>
        <br>
        <input type="submit" value="Salva">
    </form>
</div>
</body>
</html>
