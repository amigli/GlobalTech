<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/06/2022
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Errore</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <h4>Si e' verificato un errore</h4>

    <% switch(response.getStatus()){
        case 400 :%>
        <p>
            La richiesta formulata non è corretta
        </p>
    <%  break;
        case 404 : %>
        <p>
            La risorsa cercata non esiste
        </p>
    <%  break;
        case 401 :%>
        <p>
            Sembra che tu non sia autorizzato ad eseguire il servizio richiesto.
            Se non è così contatta l'amministratore
        </p>
    <%  break;
        default:%>
        <p>
            Si è verificato un errore non previsto.
        </p>
    <%break;
    }%>

    <%if (exception != null) { %>
        <h5>For debug</h5>
        <p>
            <%=exception.getMessage()%>
            <%=exception.getStackTrace()%>
        </p>
    <%}%>
</body>
</html>
