<%--
Created by IntelliJ IDEA.
User: Annalaura
Date: 20/06/2022
Time: 12:02
To change this template use File | Settings | File Templates.
--%>


<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CategoriaDAO" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestisci categorie</title>
    <%@include file="../includes/links.html"%>
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<div>
</div>
<%
    List<Categoria> cat = (List<Categoria>) request.getAttribute("categorie");
%>
<br>
<h1>Gestione categorie</h1>
<a href="formInserimentoCategoria.jsp">Inserire una nuova categoria</a>
<br><br>
<table id="categorie">
   <tr>
       <th>ID</th>
       <th>Nome</th>
       <th>Descrizione</th>
   </tr>
    <%for (Categoria c : cat){%>
        <tr>
            <td><%=c.getId()%></td>
            <td><%=c.getNome()%></td>
            <td><%=c.getDescrizione()%></td>
            <td>
                <form action="gestisci-categoria" id="modificaCategoria" method="get">
                    <input type="hidden" name="id" value="<%=c.getId()%>">
                    <input type="submit" value="Modifica">
                </form>
            </td>
            <td>
                <form action="rimuovi-categoria" id="rimuoviCategoria" method="post">
                    <input type="hidden" name="id" value="<%=c.getId()%>">
                    <input type="submit" value="Rimuovi">
                </form>
            </td>
        </tr>
    <% } %>
</table>
</body>
</html>
