<%@ page import="model.Foto" %>
<%@ page import="java.util.*"%>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 14/07/2022
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Caricamento Foto Completato con successo</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <p>Le seguenti foto sono state caricate con successo</p>
        <ul>
            <%
                List<Foto> immagini =  (List<Foto>) request.getAttribute("immagini");
                for(Foto f : immagini){%>
                    <li><%=f.getDirectory()%></li>
            <%}%>
        </ul>

        <p>Ritorna alla <a href="visualizza-prodotti">gestione prodotti</a></p>
    </body>
</html>
