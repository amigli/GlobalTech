<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 17/07/2022
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Prodotto Eliminato Con successo</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
        <h2>Dettagli prodotto eliminato</h2>

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

        <div>
            <p>Se hai eliminato per errore questo prodotto puoi ripristinarlo con il seguente pulsante:</p>
            <form method="post" action="ripristina-prodotto">
                <input type="hidden" name="id_prod" value="${prodotto.id}">
                <input type="hidden" name="disponibilita" value="${prodotto.disponibilita}">
                <input type="submit" value="Ripristina">
            </form>
        </div>

        <%@include file="/WEB-INF/includes/footer.jsp" %>
    </body>
</html>
