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
    <title>Dettaglio caricamento</title>
    <%@include file="/links.html"%>
</head>
<body>
    <h2>Dettagli prodotto caricato</h2>

    <div id="dettagli-caricamento">
        <p>ID:&emsp; ${prodotto.id}</p>
        <p>Nome:&emsp;${prodotto.nome}</p>
        <p>Marca:&emsp;${prodotto.marca}</p>
        <p>Colore:&emsp;${prodotto.colore}</p>
        <p>Prezzo:&emsp;${prodotto.prezzoListino}</p>
        <p>Descrizione:&emsp;${prodotto.descrizione}</p>
        <p>Nome:&emsp;${prodotto.nome}</p>
        <p>Sistema Operativo:&emsp;${prodotto.nome}</p>
        <p>Tipo RAM:&emsp;${prodotto.tipoRam}</p>
        <p>Quantita RAM&emsp;${prodotto.quantitaRam}</p>
        <p>CPU:&emsp;${prodotto.cpuNome}</p>
        <p>Batteria :&emsp;${prodotto.batteria ? "Si" : "No"} </p>
        <p>Disponibilit&agrave:&emsp;<${prodotto.disponibilita}</p>

    </div>
    <form action="carica-foto" enctype="multipart/form-data" method="post">
        <input type="hidden" name="id"  value=${prodotto.id}>
        <input type="file" name="foto" accept="image/*" multiple>
        <input type="submit" value="carica-immagini">
    </form>

</body>
</html>
