<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 20/07/2022
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica dati di spedizione</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
    Utente u = (Utente) session.getAttribute("utente");
%>

<form action="modifica-datiSUtente" onsubmit="return validateFormDatiSUtente()">
    Indirizzo:
    <input type="text" id="indirizzo" name="indirizzo"
        <%
        if(u.getVia()!=null){
        %>
           value="<%=u.getVia()%>"
        <%}%>
    >

    Numero civico:
    <input type="text" id="civico" name="civico" maxlength="3"
        <%
        if(u.getNumCivico()>0){
        %>
           value="<%=u.getNumCivico()%>"
        <%}%>
    >

    Citt&agrave:
    <input type="text" id="citta" name="citta" maxlength="30"
        <%
        if(u.getCitta()!=null){
        %>
           value="<%=u.getCitta()%>"
        <%}%>
    >

    CAP:
    <input type="text" id="cap" name="cap" maxlength="5"
        <%
        if(u.getCap()>0){
        %>
           value="<%=u.getCap()%>"
        <%}%>
    >
    <input type="submit" value="Salva">
</form>
<script type="text/javascript" src="script/datiSpedizione.js"></script>
</body>
</html>
