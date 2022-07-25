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
        <main id="ordine-dettaglio">
            <h1>Dettagli ordine #<%=ordine.getId()%></h1>
            <section id="dati-utente">
                <fieldset id="dati_spedizione">
                    <legend>Dati Spedizione</legend>
                    ${ordine.indirizzoSpedizione}
                </fieldset>
                <br>
                <fieldset id="dati_pagamento">
                    <legend>Dettagli Pagamento</legend>
                    <p>Carta che termina con <%=ordine.getCcPagamento().substring(ordine.getCcPagamento().length() - 4)%></p>
                </fieldset>
            </section>
            <section id="prodotti">
                <%for(ItemCart item : ordine.getProdotti()){%>
                <div class="prodotto-ordine" id="prodotto-<%=item.getProdotto().getId()%>">
                    <h1><%=item.getProdotto().getMarca() + "-" + item.getProdotto().getNome()%></h1>

                    <%if(item.getProdotto().getImmagini().size() > 0){%>
                    <img class="img-ordine" src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>">
                    <%}else{%>
                    <img class="img-ordine" src="./asset/default.png">
                    <%}%>
                    <div class="prezzo-quantita">
                        <h2 class="prezzo">€<%=item.getPrezzo()%></h2>
                        <h2>Quantita :  <%=item.getQuantita()%></h2>
                    </div>

                </div>
                <%}%>
                <h1><span class="totale">Spese spedizione</span> <span class="totale-numero"><%=ordine.getSpeseSpedizione()%>€</span></h1>
                <h1><span class="totale">Totale</span> <span class="totale-numero"><%=ordine.getPrezzoTotale()%>€</span></h1>
            </section>
        </main>


        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
