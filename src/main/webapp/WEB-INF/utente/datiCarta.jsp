<%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 20/07/2022
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica dati di pagamento</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
    Utente u = (Utente) session.getAttribute("utente");
%>

<form action="modifica-datiCUtente" onsubmit="return validateFormDatiCUtente()">

    Numero di carta:
    <input type="text" id="numCarta" name="numCarta" maxlength="16"
        <%
        if(u.getNumeroCarta()!=null){
        %>
           value="<%=u.getNumeroCarta()%>"
        <%}%>
    >

    CVV carta:
    <input type="text" id="cvv" name="cvv" maxlength="3"
        <%
        if(u.getCvvCarta()>0){
        %>
           value="<%=u.getCvvCarta()%>"
        <%}%>
    >

    Data scadenza carta:
    <input type="date" id="dataCarta" name="dataCarta"
        <%
        if(u.getDataScadenzaCarta()!=null){
        %>
           value="<%=u.getDataScadenzaCarta()%>"
        <%}%>
    >
    <input type="submit" value="Salva">
</form>
<script type="text/javascript" src="script/datiCarta.js"></script>
</body>
</html>
