<%@ page import="model.Ordine" %>
<%@ page import="model.ItemCart" %>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 21/07/2022
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dettagli Ordine ${ordine.id}</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
        <%
            Ordine ordine = (Ordine) request.getAttribute("ordine");
        %>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <section id="dati-utente">
            <fieldset id="dati_spedizione">
                <legend>Dati Spedizione</legend>
                ${ordine.indirizzoSpedizione}
            </fieldset>

            <fieldset id="dati_pagamento">
                <legend>Dettagli Pagamento</legend>
                <p>Carta che termina con <%=ordine.getCcPagamento().substring(ordine.getCcPagamento().length() - 4)%></p>
            </fieldset>
        </section>
        <section id="prodotti">
            <fieldset>
                <legend>Prodotti</legend>
                <%for(ItemCart item : ordine.getProdotti()){%>
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
                <p>Totale : <%=ordine.getPrezzoTotale()%></p>
            </fieldset>
        </section>

        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
