<%@ page import="model.Categoria" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: migli
  Date: 20/06/2022
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica categoria</title>
    <%@include file="links.html"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<%
    ArrayList<String> list = (ArrayList<String>) request.getAttribute("error_parameter");

    if(list != null){
        Categoria c = (Categoria) request.getAttribute("categoria");
    }
%>
<br><br><br>
<div id="modificareCategoria">
    <form action="modifica-categoria" id="modificaCategoria" method="get">
        <h2>ID categoria da modificare: <%=c.getId()%></h2>
        <label for="nomeCategoria">Nome della categoria:</label>
        <input type="text" id="nomeCategoria" name="nomeCategoria" value="<%=c.getNome()%>" required>
        <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
        <textarea id="descrizioneCategoria" name="descrizioneCategoria" rows="5" cols="65" required>
        <%=c.getDescrizione()%>
        </textarea>
        <br>
        <br>
        <input type="submit" value="Modifica">
    </form>
</div>
</body>
</html>
