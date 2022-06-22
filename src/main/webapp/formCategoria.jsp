<%--
Created by IntelliJ IDEA.
User: migli
Date: 20/06/2022
Time: 12:02
To change this template use File | Settings | File Templates.
--%>


<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CategoriaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestisci categorie</title>
    <%@include file="links.html"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div>
    <%
        ArrayList<String> list = (ArrayList<String>) request.getAttribute("error_parameter");

        if(list != null){
            for(String s : list){
    %>
    <h1>Errore nell'inserimento dei dati:</h1>
        <%=s%>
    <%
            }
        }
    %>
</div>
<%
    CategoriaDAO service = new CategoriaDAO();
    ArrayList<Categoria> allCategories = (ArrayList<Categoria>) service.doRetrieveAll();

%>
<br>
<h1>Gestione categorie</h1>
<a href="formInserimentoCategoria.jsp">Inserire una nuova categoria</a>
<br><br>
<table id="categorie">
    <th>ID</th>
    <th>Nome</th>
    <th>Descrizione</th>
    <%for (int i=0; i<allCategories.size(); i++){%>
        <tr>
            <td><%=allCategories.get(i).getId()%></td>
            <td><%=allCategories.get(i).getNome()%></td>
            <td><%=allCategories.get(i).getDescrizione()%></td>
            <td>
                <form action="modificainter-categoria" id="modificaCategoria">
                    <input type="hidden" name="id" value="<%=allCategories.get(i).getId()%>">
                    <input type="submit" value="Modifica">
                </form>
            </td>
            <td>
                <form action="rimuovi-categoria" id="rimuoviCategoria" method="get">
                    <input type="hidden" name="id" value="<%=allCategories.get(i).getId()%>">
                    <input type="submit" value="Rimuovi">
                </form>
            </td>
        </tr>
    <% } %>
</table>
</body>
</html>
