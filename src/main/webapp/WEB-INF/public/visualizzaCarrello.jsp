<%@ page import="model.Carrello" %>
<%@ page import="model.ItemCart" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 18/07/2022
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <%@include file="/WEB-INF/includes/links.html" %>
</head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <%
            Carrello cart = (Carrello) request.getAttribute("carrello");
        %>
        <section id="carrello">

            <%if(cart.isEmpty()){%>
                <p>Il carrello è vuoto!</p>
            <%}else{
                for(ItemCart item : cart.getProdotti()){%>
                <div class="prodotto">
                    <h1><%=item.getProdotto().getMarca() + "-" + item.getProdotto().getNome()%></h1>
                    <img src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>">
                    <p>€<%=item.getPrezzo()%></p>
                    <p>Quantita :  <%=item.getQuantita()%></p>
                </div>
            <%}%>
            <p><b>TOTALE <%=cart.getTotale()%></b></p>
            <%}%>
        </section>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
