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

        <%
            Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
            List<Foto> immagini =  prodotto.getImmagini();
        %>
        <%
            if(!immagini.isEmpty()){
                for(Foto img : immagini){%>
                    <form action="#">
                        <img src="<%=img.getDirectory()%>" alt="immagine prodotto${prodotto.nome}">
                        <input type="submit" value="rimuovi">
                    </form>
                <%}%>
                <p>Puoi caricare altre immagini con il form qui sotto</p>
            <%}else{%>
                <p>Il prodotto non ha immagini, per la visualizzazione verrà utilizzata l'immagine di default</p>
                <p>Puoi caricare una immagine con il form qui in basso</p>
            <%}%>

        <form action="carica-foto" enctype="multipart/form-data" method="post">
            <input type="hidden" name="id"  value=${prodotto.id}>
            <input type="file" name="foto" accept="image/*" multiple>
            <input type="submit" value="carica-immagini">
        </form>


    </body>
</html>
