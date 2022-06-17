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
    <title>Title</title>
</head>
<body>
    <h2>Dettagli prodotto caricato</h2>

    <div id="dettagli">
        <p>ID(temporaneo):</p>
        <p>${prod.id}</p>
        <p>Nome:</p>
        <p>${prod.nome}</p>
        <p>Marca:</p>
        <p>${prod.marca}</p>
        <p>Colore:</p>
        <p>${prod.colore}</p>
        <p>Prezzo:</p>
        <p>${prod.prezzoListino}</p>
        <p>Descrizione:</p>
        <p>${prod.descrizione}</p>
        <p>Nome:</p>
        <p>${prod.nome}</p>
        <p>Sistema Operativo:</p>
        <p>${prod.nome}</p>
        <p>Tipo RAM:</p>
        <p>${prod.tipoRam}</p>
        <p>Quantita RAM</p>
        <p>${prod.quantitaRAM}</p>
        <p>CPU:</p>
        <p>${prod.cpuNome}</p>
        <p>Batteria :</p>
        <p><%= (boolean) request.getAttribute("prod") ? "Si" : "No"%></p>
        <p>Disponibilit&agrave:</p>
        <p><${prod.disponibilita}/p>

        <%--

            prod.setCpuNome(nomeCpu);
            prod.setBatteria(batteria);
            prod.setDisponibilita(disponibilita);

        --%>
    </div>
</body>
</html>
