<%--
  Created by IntelliJ IDEA.
  User: Annalaura
  Date: 23/06/2022
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Area Riservata</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
<body>
    <%@include file="/WEB-INF/includes/navbar.jsp"%>
    <section id="serviziAdmin">
    <h1>Scegli un servizio:</h1>

    <div id="gestioneProdotto">
            <a href="admin-service?s=caricamento-prodotto" class="gestioneAdmin">Caricamento prodotto</a>

            <a href="visualizza-prodotti" class="gestioneAdmin">Visualizza prodotti</a>
    </div>

    <div id="gestioneOfferte">
        <a href="admin-service?s=caricamento-offerta" class="gestioneAdmin">Caricamento offerta</a>

        <a href="visualizza-offerte" class="gestioneAdmin">Visualizza offerte</a>

    </div>

    <div id="gestioneCategorie">
        <a href="admin-service?s=caricamento-categoria" class="gestioneAdmin">Caricamento categoria</a>

        <a href="visualizza-categorie" class="gestioneAdmin">Visualizza categorie</a>

    </div>

    <div id="utentiOrdini">
        <a href="visualizza-utenti" class="gestioneAdmin">Gestione utenti</a>

        <a href="visualizza-ordini" class="gestioneAdmin">Gestione ordini</a>

    </div>

    </section>

    <%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>
