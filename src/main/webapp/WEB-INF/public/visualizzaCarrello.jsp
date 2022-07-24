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

        <main id="carrello">
            <h1>Carrello</h1>

            <section id="messages">
            </section>

            <%if(cart.isEmpty()){%>
                <div id="empty-cart">
                    <p>Il carrello è vuoto!</p>
                    <p>Sfoglia il nostro <a href="catalogo">catalogo</a> e riempilo</p>
                </div>

            <%}else{%>
            <section id="prodotti">
                <%for(ItemCart item : cart.getProdotti()){%>
                <div onclick="location.href = 'dettaglio-prodotto?id=' + <%=item.getProdotto().getId()%>"class="prodotto-carrello" id="prodotto-<%=item.getProdotto().getId()%>">
                    <h1><%=item.getProdotto().getMarca() + "-" + item.getProdotto().getNome()%></h1>
                    <%if(item.getProdotto().getImmagini().size() > 0){%>
                    <img src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>">
                    <%}else{%>
                    <img src="./asset/default.png">
                    <%}%>
                    <div class="dettagli-prodotto">
                        <h2 class="prezzo">€<%=item.getPrezzo()%></h2>
                        <h2>Quantita :  <%=item.getQuantita()%></h2>
                    </div>
                    <form method="post" action="rimuovi-prodotto-carrello">
                        <input type="hidden" name="id_prod" value="<%=item.getProdotto().getId()%>">
                        <input type="submit" value="Elimina Prodotto">
                    </form>

                </div>
                <%}%>
                <h1 id="totale-carrello"><span id="totale">TOTALE</span> <span id="totale-numero"><%=cart.getTotale()%>€</span></h1>
                <form method="post" action="completa-ordine">
                    <input type="submit" value="Conferma Ordine">
                </form>
                <%}%>
            </section>
        </main>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
