<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 08/07/2022
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestione utenti</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<%
List<Utente> u = (List<Utente>) request.getAttribute("utenti");
Utente me = (Utente) session.getAttribute("utente");
%>
<br>
<table id="utenti">
    <tr>
        <th>ID</th>
        <th>E-mail</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Altre informazioni</th>
    </tr>
    <%for (Utente ut : u){%>
    <% if (!(ut.getEmail().equals(me.getEmail()))){ %>
    <tr>
        <td><%=ut.getId()%></td>
        <td><%=ut.getEmail()%></td>
        <td><%=ut.getNome()%></td>
        <td><%=ut.getCognome()%></td>
        <td>
            <form action="gestione-utente" method="get">
                <input type="hidden" name="id" value="<%=ut.getId()%>">
                <button type="submit"><i class="fa fa-external-link"></i></button>
            </form>
        </td>
    </tr>
    <% }
    } %>
</table>
<br>
</body>
</html>
