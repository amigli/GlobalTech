<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/06/2022
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/includes/links.html"%>
        <%
            String modifica =  (String) request.getAttribute("operation");
            boolean isModifica =  modifica != null && modifica.equalsIgnoreCase("modifica");
        %>
        <title>Offerta  <%=isModifica ? "modificata" : "caricata" %>con successo</title>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <div>
            <p>Offerta <%=isModifica ? "modificata" : "caricata" %> con successo!</p>
            <p>ID:${offerta.id}</p>
            <p>Nome:${offerta.nome}</p>
            <p>Data inizio:${offerta.dataInizio}</p>
            <p>Data fine: ${offerta.dataFine}</p>
            <p>Percentuale di sconto:${offerta.percentuale}%</p>
        </div>

        <p>Ritorna alla <a href="index.html">home</a></p>

        <%@include file="/WEB-INF/includes/footer.jsp"%>

    </body>
</html>
