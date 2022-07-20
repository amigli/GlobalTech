<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/07/2022
  Time: 18:54
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

<form action="modifica-datiPUtente" onsubmit="return validateFormDatiPUtente()">
Nome:
<input type="text" id="nome" name="nome"
    <%
        if(u.getNome()!=null){
        %>
       value="<%=u.getNome()%>"
    <%}%>
>

Cognome:
<input type="text" id="cognome" name="cognome"
    <%
        if(u.getCognome()!=null){
        %>
       value="<%=u.getCognome()%>"
    <%}%>
>

Data di nascita:
<input type="date" id="dataNascita" name="dataNascita" max="2005-01-01"
    <%
        if(u.getDataNascita()!=null){
        %>
       value="<%=u.getDataNascita()%>"
    <%}%>
>

Numero di telefono:
<input type="text" id="telefono" name="telefono" maxlength="10"
    <%
        if(u.getNumTelefono()!=null){
        %>
       value="<%=u.getNumTelefono()%>"
    <%}%>
>
    <input type="submit" value="Salva">

</form>
<script type="text/javascript" src="script/datiPersonali.js"></script>
</body>
</html>
