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
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
<%@include file="/WEB-INF/includes/navbar.jsp"%>
<%
    ArrayList<String> list = (ArrayList<String>) request.getAttribute("error_parameter");

    int id = 0;
    String nome = "";
    String descrizione = "";
    if (list == null) {
        id = (int) request.getAttribute("idCategoria");
        nome = (String) request.getAttribute("nomeCategoria");
        descrizione = (String) request.getAttribute("descrizioneCategoria");
    }
%>
<br><br><br>
<div id="modificareCategoria">
    <form action="modifica-categoria" id="modificaCategoria" method="post">
        <h2>ID categoria da modificare: <%=id%></h2>
        <label for="nomeCategoria">Nome della categoria:</label>
        <input type="text" id="nomeCategoria" name="nomeCategoria" value="<%=nome%>" required>
        <label for="descrizioneCategoria">Descrizione della categoria:</label><br>
        <textarea id="descrizioneCategoria" name="descrizioneCategoria" rows="5" cols="65" required><%=descrizione%></textarea>
        <input type="hidden" name="id" value="<%=id%>">
        <br>
        <br>
        <input type="submit" value="Modifica">
    </form>
</div>
</body>
</html>
