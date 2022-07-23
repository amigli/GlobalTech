<%@ page import="model.Item" %>
<%@ page import="model.Prodotto"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.stream.Collectors" %>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 17/07/2022
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Catalogo</title>
        <%@include file="/WEB-INF/includes/links.html" %>
    </head>
    <body>

        <%
            List<Item> catalogo = (List<Item>) request.getAttribute("catalogo");
            List<Categoria> categorieAll =  (List<Categoria>) application.getAttribute("categorie");

            List<String> marche = catalogo.stream().map(p->p.getProdotto().getMarca()).distinct().collect(Collectors.toList());
        %>
        <%@include file="/WEB-INF/includes/navbar.jsp" %>
            <div id="filtri">
                    <ul id="ul-filtri">
                        <li id="first">Filtra per </li>
                        <li>
                            <select name="categoria" id="select-categoria" onchange="filtraProdotti()">
                                <option value="-1">Categoria</option>
                                <%for(Categoria c : categorieAll){%>
                                    <option value="<%=c.getId()%>"><%=c.getNome()%></option>
                                <%}%>
                            </select>
                        </li>
                        <li>
                            <select name="marca"  id="select-marca" onchange="filtraProdotti()">
                                <option value="-1">Marca</option>
                                <%for(String m : marche){%>
                                <option value="<%=m%>"><%=m%></option>
                                <%}%>
                            </select>
                        </li>
                    </ul>
            </div>
            <br>
            <section id="prodotti-catalogo">
                <%
                    for(Item item : catalogo){
                %>
                <figure class="prodotto-figure" onclick="location.href = 'dettaglio-prodotto?id=' + <%=item.getProdotto().getId()%>">
                    <%if(item.getProdotto().getImmagini().size() > 0){%>
                    <img class="prodotto-img" src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>"
                    <%}else{%>
                    <img src="./asset/default.png">
                    <%}%>
                    <figcaption>
                        <div id="title-prezzo">
                            <h1 id="marca-nome">
                                <%=item.getProdotto().getMarca()%>-<%=item.getProdotto().getNome().substring(0,Math.min(item.getProdotto().getNome().length(), 30))%><%if(item.getProdotto().getNome().length() > 32){%>...<%}%>
                            </h1>
                            <h2 id="prezzo"><%=item.getPrezzo()%>€</h2>
                        </div>
                        <div id="descrizione">
                            <%=item.getProdotto().getDescrizione().substring(0, Math.min(50, item.getProdotto().getDescrizione().length()))%>...
                        </div>
                        <form action="aggiungi-carrello" method="post">
                            <input type="hidden" name="quantita" value="1">
                            <input type="hidden" name="prodotto"  value="<%=item.getProdotto().getId()%>">
                            <input type="submit" class="aggiungi-carrello-catalogo"value="Aggiungi al carrello">
                        </form>
                    </figcaption>
                </figure>
                <%}%>
            </section>
        <%@include file="/WEB-INF/includes/footer.jsp"%>

        <script type="text/javascript" src="script/gestioneCatalogo.js"></script>
    </body>
</html>
