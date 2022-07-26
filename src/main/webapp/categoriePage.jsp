<%@ page import="model.Categoria" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 25/07/2022
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Le nostre categorie</title>
    <%@include file="/WEB-INF/includes/links.html" %>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp" %>

    <main id="result">
        <%
            List<Categoria> allCategorie = (List<Categoria>) application.getAttribute("categorie");
        %>
        <h1>Le Nostre Categorie</h1>
        <ul>
            <%for(Categoria c : allCategorie){%>
                <li><h2><a href="prodotti-per-categoria?id=<%=c.getId()%>"><%=c.getNome()%></a></h2></li>
            <%}%>
        </ul>
    </main>


    <%@include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
