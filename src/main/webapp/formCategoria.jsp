<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CategoriaDAO" %><%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 20/06/2022
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
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

<div id="inserireCategoria">
    <form action="#" id="inserisciCategoria" method="get">
        <h2>Inserisci una nuova categoria</h2>
        <label for="nomeCategoria">Nome della categoria:</label>
        <input type="text" id="nomeCategoria" name="idCategoria" required>
        <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
        <textarea id="descrizioneCategoria" name="descrizioneCategoria" rows="5" cols="48" required></textarea>
        <br><br>
        <input type="submit" value="Inserisci">
    </form>
</div>
<div id="rimuovereCategoria">
    <form action="#" id="rimuoviCategoria" method="get">
        <label for="categorie" style="font-size: 20px;">Scegli una categoria:</label><br>
        <select name="categorie" id="categorie" size="10">
        <%for (int i=0; i<allCategories.size(); i++){%>
            <option value="<%=allCategories.get(i).getNome()%>"<%=allCategories.get(i).getNome()%></option>
       <% } %>
        </select>
        <input type="submit" value="Rimuovi">
    </form>
</div>
<div id="modificareCategoria">
    <label for="categorie" style="font-size: 20px;">Scegli una categoria:</label><br>
    <select name="categorie" id="categorie2" size="3">
        <%for (int i=0; i<allCategories.size(); i++){%>
        <option value="<%=allCategories.get(i).getNome()%>"<%=allCategories.get(i).getNome()%></option>
        <% } %>
    </select>
    <label for="nomeCategoria">Nome della categoria:</label>
    <input type="text" id="nomeCategoria2" name="idCategoria" required>
    <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
    <textarea id="descrizioneCategoria2" name="descrizioneCategoria" rows="5" cols="48" required></textarea>
    <input type="submit" value="Modifica">
</div>
</body>
</html>
