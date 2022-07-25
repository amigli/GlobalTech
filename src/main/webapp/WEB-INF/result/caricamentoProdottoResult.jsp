<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 17/06/2022
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            Boolean modifica = (Boolean) request.getAttribute("modifica");
        %>
        <title>Dettaglio <%=modifica !=null && modifica ? "modifica" : "caricamento" %> </title>
        <%@include file="/WEB-INF/includes/links.html"%>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <main id="result">
            <h1>Dettagli prodotto <%=modifica !=null && modifica ? "modificato" : "caricato" %></h1>

            <div id="dettagli-caricamento">
                <p>ID:&emsp; ${prodotto.id}</p>
                <p>Nome:&emsp;${prodotto.nome}</p>
                <p>Marca:&emsp;${prodotto.marca}</p>
                <p>Colore:&emsp;${prodotto.colore}</p>
                <p>Prezzo:&emsp;${prodotto.prezzoListino}</p>
                <p>Descrizione:&emsp;${prodotto.descrizione}</p>
                <p>Sistema Operativo:&emsp;${prodotto.sistemaOperativo}</p>
                <p>Tipo RAM:&emsp;${prodotto.tipoRam}</p>
                <p>Quantita RAM&emsp;${prodotto.quantitaRam}</p>
                <p>CPU:&emsp;${prodotto.cpuNome}</p>
                <p>Batteria :&emsp;${prodotto.batteria ? "Si" : "No"} </p>
                <p>Disponibilit&agrave:&emsp;${prodotto.disponibilita}</p>
            </div>
            <br>
            <br>
            <div id="prodotto-result-operation">
                <p>Il prodotto è stato <%=modifica !=null && modifica ? "modificato" : "caricato" %> con successo. Ora puoi:</p>
                <ul>
                    <li>
                        <a href="gestione-immagini-prodotto?id_prod=${prodotto.id}">Aggiungere delle immagini al prodotto</a>
                    </li>
                    <li>
                        <a href="gestione-categoria-prodotto?id_prod=${prodotto.id}">Aggiungere il prodotto a uno o più categorie</a>
                    </li>
                    <li>
                        <a href="gestione-offerte-prodotto?id_prod=${prodotto.id}">Applicare delle offerte al prodotto</a>
                    </li>
                </ul>
            </div>
        </main>

        <%@include file="/WEB-INF/includes/footer.jsp"%>
    </body>
</html>
