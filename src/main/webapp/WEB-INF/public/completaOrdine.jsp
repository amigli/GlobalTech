<%@ page import="model.Carrello" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/07/2022
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Completa Ordine</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <%
            Carrello cart = (Carrello) session.getAttribute("carrello");
            Utente u =  (Utente) session.getAttribute("utente");
        %>

        <form id="dati-spedizione">
            <input type="text" name="via" id="via" <%if(u.getVia() != null){%>
                value="<%=u.getVia()%>"
            <%}%>
        </form>

        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
