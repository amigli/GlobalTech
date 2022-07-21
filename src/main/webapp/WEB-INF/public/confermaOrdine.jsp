<%@ page import="model.Carrello" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="model.ItemCart" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 20/07/2022
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Conferma Ordine</title>
    <%@include file="/WEB-INF/includes/links.html" %>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <%
            Utente u = (Utente) session.getAttribute("utente");
            Carrello cart =  (Carrello) session.getAttribute("carrello");
        %>

        <section id="dati-utente">
            <fieldset id="dati_spedizione">
                <legend>Dati Spedizione</legend>
                <p><%=u.getNome()%> <%=u.getCognome()%></p>
                <p>Via<%=u.getVia()%>, <%=u.getNumCivico()%>,</p>
                <p><%=u.getCap()%>, <%=u.getCitta()%> </p>
            </fieldset>

            <fieldset id="dati_pagamento">
                <legend>Dati Pagamento</legend>
                <p>Numero Carta : <%=u.getNumeroCarta()%></p>
                <p>CVV: <%=u.getCvvCarta()%></p>
                <p>Data Scadenza : <%=u.getDataScadenzaCarta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))%></p>
            </fieldset>
        </section>
        <section id="prodotti">
            <fieldset>
                <legend>Prodotti</legend>
                <%for(ItemCart item : cart.getProdotti()){%>
                    <div class="prodotto" id="prodotto-<%=item.getProdotto().getId()%>">
                    <h1><%=item.getProdotto().getMarca() + "-" + item.getProdotto().getNome()%></h1>
                        <%if(item.getProdotto().getImmagini().size() > 0){%>
                    <img src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>"
                        <%}else{%>
                    <img src="./asset/default.png">
                        <%}%>
                    <p class="prezzo">€<%=item.getPrezzo()%></p>
                    <p>Quantita :  <%=item.getQuantita()%></p>
                    </div>
                <%}%>
                    <p>Totale : <%=cart.getTotale()%></p>
            </fieldset>
            <form method="post" action="conferma-ordine">
                <%
                    int checkcode = (Integer) request.getAttribute("checkcode");
                %>

                <input type="hidden" name="checkcode" value="<%=checkcode%>">
                <input type="submit" value="Conferma Ordine">
            </form>

        </section>





    <%@include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
