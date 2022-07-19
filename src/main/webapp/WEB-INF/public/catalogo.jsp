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
            <div id="sidebar">
                <p>
                    Filtra per
                    <ul>
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
                </p>

            </div>
            <section id="prodotti-catalogo">
                <%
                    for(Item item : catalogo){
                %>
                <figure>
                    <%if(item.getProdotto().getImmagini().size() > 0){%>
                    <img src="<%=item.getProdotto().getImmagini().get(0).getDirectory()%>"
                    <%}else{%>
                    <img src="./asset/default.png">
                    <%}%>
                    <figcaption>
                        <h1 id="marca-nome">
                            <%=item.getProdotto().getMarca()%>-<%=item.getProdotto().getNome()%>
                        </h1>
                        <div id="descrizione">
                            <%=item.getProdotto().getDescrizione().substring(0, 50)%>
                        </div>
                        <div id="prezzo"><%=item.getPrezzo()%></div>
                        <form action="aggiungi-carrello" method="post">
                            <input type="hidden" name="quantita" value="1">
                            <input type="hidden" name="prodotto"  value="<%=item.getProdotto().getId()%>">
                            <input type="submit" value="Aggiungi al carrello">
                        </form>
                    </figcaption>
                </figure>
                <%}%>
            </section>
        <%@include file="/WEB-INF/includes/footer.jsp" %>
        <script type="text/javascript" src="script/gestioneCatalogo.js"></script>
    </body>
</html>
