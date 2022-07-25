<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 05/07/2022
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.*"%>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Categorie prodotto ${prodotto.id}</title>
    <%@include file="/WEB-INF/includes/links.html"%>
    <script src="script/gestione-categorie-prodotto.js" defer></script>
</head>
<body>
<%@include file="/WEB-INF/includes/navbar.jsp"%>
<h1>Categorie del prodotto ${prodotto.id}</h1>
<div id="gestione-categorie-prodotto">

    <%
        List<Categoria>  categorie =  (List<Categoria>) application.getAttribute("categorie");
        Prodotto prodotto =  (Prodotto) request.getAttribute("prodotto");

        List<Categoria> categorieProd = categorie.stream().filter(c->c.contains(prodotto)).collect(Collectors.toList());
    %>

    <input type="hidden" id="id-prod" value = ${prodotto.id}>
<div id="eliminaProdottoDaCategoria">
<section id="categorie-prodotto">
        <p>Seleziona le categorie da cui vuoi eliminare il prodotto</p>
        <%for(Categoria c : categorieProd){%>
            <div>
                <input type="checkbox" name="categoria-prod" id="categoria-<%=c.getId()%>" value="<%=c.getId()%>">
                <label for="categoria-<%=c.getId()%>"><%=c.getNome()%></label>
            </div>
        <%}%>
    </section>
    <button  onclick="rimuoviCategoria()" id="rimuoviProdottoDaCategoria">Elimina categorie</button>
</div>
<div id="aggiungiProdottoACategoria">
    <section id="altre-categorie">
        <p>Seleziona le categorie in cui vuoi aggiungere il prodotto</p>
        <%for(Categoria c : categorie){%>
            <%if(!categorieProd.contains(c)){%>
                <div>
                    <input type="checkbox" name="categoria-other" id="categoria-<%=c.getId()%>" value="<%=c.getId()%>">
                    <label for="categoria-<%=c.getId()%>"><%=c.getNome()%></label><br>
                </div>
        <%}
        }%>
    </section>
    <button onclick="aggiungiCategoria()" id="aggiungiProdottoCategoria">Aggiungi categorie</button>
    </div>
</div>
<%@include file="/WEB-INF/includes/footer.jsp" %>
</body>
</html>
