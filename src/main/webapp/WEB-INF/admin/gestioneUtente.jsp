<%@ page import="java.util.ArrayList" %><%--
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
<%!
ArrayList<String> datiMancanti = new ArrayList<>();
%>

<div id="modificaUtente">
    <form action="modifica-utente" method="get">
        <h2>ID utente: <%=u.getId()%></h2>
        <%
            if (u.getEmail()!=null){
        %>
        <label for="emailUtente">Email utente:</label>
        <input type="text" id="emailUtente" name="emailUtente" value="<%=u.getEmail()%>" disabled>
        <%}else{
                datiMancanti.add("E-mail");
        }%>
        <%
            if (u.getNome()!=null){
        %>
        <label for="nomeUtente">Nome:</label>
        <input type="text" id="nomeUtente" name="nomeUtente" value="<%=u.getNome()%>" disabled>
        <%}else{
            datiMancanti.add("Nome");
        }%>
        <%
            if (u.getCognome()!=null){
        %>
        <label for="cognomeUtente">Cognome:</label>
        <input type="text" id="cognomeUtente" name="cognomeUtente" value="<%=u.getCognome()%>" disabled>
        <%}else{
            datiMancanti.add("Cognome");
        }%>
        <!--
        <label for="dataNascitaUtente">Data di nascita:</label>
        <input type="date" id="dataNascitaUtente" name="dataNascitaUtente" value="<%=u.getDataNascita()%>">
        -->
        <%
            if (u.getVia()!=null){
        %>
        <label for="viaUtente">Indirizzo:</label>
        <input type="text" id="viaUtente" name="viaUtente" value="<%=u.getVia()%>" disabled>
        <%}else{
            datiMancanti.add("Indirizzo");
        }%>
        <%
            if (u.getNumCivico()>0){
        %>
        <label for="civicoUtente">Numero civico:</label>
        <input type="text" id="civicoUtente" name="civicoUtente" value="<%=u.getNumCivico()%>" disabled>
        <%}else{
            datiMancanti.add("Numero civico");
        }%>
        <%
            if (u.getCitta()!=null){
        %>
        <label for="cittaUtente">Città:</label>
        <input type="text" id="cittaUtente" name="cittaUtente" value="<%=u.getCitta()%>" disabled>
        <%}else{
            datiMancanti.add("Città");
        }%>
        <%
            if (u.getCap()!=0){
        %>
        <label for="capUtente">CAP:</label>
        <input type="text" id="capUtente" name="capUtente" value="<%=u.getCap()%>" disabled>
        <%}else{
            datiMancanti.add("CAP");
        }%>
        <label for="numAcquistiUtente">Numero acquisti:</label>
        <input type="text" id="numAcquistiUtente" name="numAcquistiUtente" value="<%=u.getNumAcquisti()%>" disabled>
        <%
            if (u.getNumTelefono()!=null){
        %>
        <label for="numTelefonoUtente">Numero di telefono:</label>
        <input type="text" id="numTelefonoUtente" name="numTelefonoUtente" value="<%=u.getNumTelefono()%>" disabled>
        <%}else{
            datiMancanti.add("Numero di telefono");
        }%>
        <label for="adminUtente"> Admin: </label>
        <%
        if (u.isAdmin()){
        %>
        <input type="submit" id="adminUtente" name="rimuoviAdmin" value="Rimuovi da admin">
        <%}else{%>
        <input type="submit" id="adminUtente" name="aggiungiAdmin" value="Aggiungi come admin">
        <%}%>
        <input type="hidden" name="id" value="<%=u.getId()%>">
        <br>
        <br>
        <input type="submit" value="Salva">
    </form>
    <br>
    <%
    if (!datiMancanti.isEmpty()){
    %>
    <h3>Mancano i seguenti dati:</h3>
    <ul>
    <%
    for (String dato : datiMancanti){
    %>
    <li>
        <%=dato%>
    </li>
    <%}%>
        <%
        if (u.getNumeroCarta()!=null){
        %>
        <li>Dati della carta</li>
        <%}%>
    </ul>
    <%}%>
</div>
<br>
<form action="visualizza-ordiniC">
    <input type="hidden" name="idCliente" id="idCliente" value="<%=u.getId()%>">
    <input type="submit" value="Vedi tutti gli ordini di questo utente">
</form>
<br>
<a href="visualizzaUtenti.jsp">Indietro</a>
</body>
</html>
