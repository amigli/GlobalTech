<%@ page import="model.Prodotto" %>
<%@ page import="model.Foto" %>
<%@ page import="java.util.*"%>

<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 14/07/2022
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Immagini Prodotto ${prodotto.id}</title>
    <%@include file="/WEB-INF/includes/links.html"%>
</head>
    <body>
        <%@include file="/WEB-INF/includes/navbar.jsp"%>
        <main id="result">
            <h1>Gestioni Immagini Prodotto ${prodotto.id}</h1>
            <%
                Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
                List<Foto> immagini =  prodotto.getImmagini();
            %>

            <div id="container-gestione-immagini">
                <%
                    if(!immagini.isEmpty()){
                        for(Foto img : immagini){%>
                <figure class="gestioni-immagini">
                    <img id="img-<%=img.getNumeroId()%>"src="<%=img.getDirectory()%>" alt="immagine prodotto${prodotto.nome}">
                    <figcaption>
                        <button onclick="rimuoviImmagini(<%=img.getNumeroId()%>, <%=img.getProdottoId()%>)">Rimuovi</button>
                    </figcaption>
                </figure>
                <%}%>
            </div>


            <h2>Puoi caricare altre immagini con il form qui sotto</h2>
            <%}else{%>
            <p>Il prodotto non ha immagini, per la visualizzazione verrà utilizzata l'immagine di default</p>
            <p>Puoi caricare una immagine con il form qui in basso</p>
            <%}%>
            <div id="caricaFoto">
                <form action="carica-foto" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="id"  value=${prodotto.id}>
                    <input type="file" name="foto" accept="image/*" required multiple>
                    <input type="submit" value="Carica immagini">
                </form>
            </div>
        </main>
        <%@include file="/WEB-INF/includes/footer.jsp"%>
    <script type="text/javascript" src="script/gestioneImmagini.js"></script>
    </body>
</html>
