<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 22/06/2022
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Categoria Caricata</title>
        <%@include file="/WEB-INF/includes/links.html"%>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <p>Categoria caricata correttamente</p>
        <p>id:${categoria_inserita.id}</p>
        <p>Ritorna alla <a href="index.html">home</a></p>

        <%@include file="/WEB-INF/includes/footer.jsp"%>

    </body>
</html>
